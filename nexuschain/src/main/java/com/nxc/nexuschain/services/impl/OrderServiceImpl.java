package com.nxc.nexuschain.services.impl;

import com.nxc.nexuschain.dtos.OrderCreateDTO;
import com.nxc.nexuschain.dtos.OrderDetailDTO;
import com.nxc.nexuschain.dtos.OrderDetailResponseDTO;
import com.nxc.nexuschain.dtos.OrderResponseDTO;
import com.nxc.nexuschain.entities.*;
import com.nxc.nexuschain.enums.OrderStatusEnum;
import com.nxc.nexuschain.exceptions.InsufficientStockException;
import com.nxc.nexuschain.repositories.*;
import com.nxc.nexuschain.services.OrderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;
    private final InvoiceRepository invoiceRepository;

    @Override
    public OrderResponseDTO createOrder(OrderCreateDTO orderCreateDTO) {
        User user = userRepository.findById(orderCreateDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Order order = new Order();
        order.setUser(user);
        order.setType(orderCreateDTO.getType());
        order.setStatus(OrderStatusEnum.PENDING);
        order.setCreatedDate(LocalDateTime.now());
        order.setIsDeleted(false);
        order.setIsConfirm(false);

        Set<OrderDetail> orderDetails = new HashSet<>();
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (OrderDetailDTO detailDTO : orderCreateDTO.getOrderDetails()) {
            Product product = productRepository.findById(detailDTO.getProductId())
                    .orElseThrow(() -> new EntityNotFoundException("Product not found"));

            Inventory inventory = inventoryRepository.findByProductId(product.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Inventory not found for product"));

            if (inventory.getQuantity() < detailDTO.getQuantity()) {
                throw new InsufficientStockException("Insufficient stock for product: " + product.getName());
            }

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setProduct(product);
            orderDetail.setQuantity(detailDTO.getQuantity());
            orderDetail.setUnitPrice(product.getPrice());

            orderDetails.add(orderDetail);
            totalAmount = totalAmount.add(product.getPrice().multiply(BigDecimal.valueOf(detailDTO.getQuantity())));

            // Update inventory
            inventory.setQuantity(inventory.getQuantity() - detailDTO.getQuantity());
            inventoryRepository.save(inventory);
        }

        order.setOrderDetails(orderDetails);
        Order savedOrder = orderRepository.save(order);

        // Create invoice
        Invoice invoice = new Invoice();
        invoice.setOrder(savedOrder);
        invoice.setTotalAmount(totalAmount);
        invoiceRepository.save(invoice);

        return mapOrderToResponseDTO(savedOrder);
    }

    @Override
    public OrderResponseDTO getOrderById(Integer orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
        return mapOrderToResponseDTO(order);
    }

    @Override
    public List<OrderResponseDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(this::mapOrderToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponseDTO updateOrderStatus(Integer orderId, OrderStatusEnum newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
        order.setStatus(newStatus);
        order.setUpdatedDate(LocalDateTime.now());
        Order updatedOrder = orderRepository.save(order);
        return mapOrderToResponseDTO(updatedOrder);
    }

    @Override
    public void deleteOrder(Integer orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
        order.setIsDeleted(true);
        order.setUpdatedDate(LocalDateTime.now());
        orderRepository.save(order);
    }

    private OrderResponseDTO mapOrderToResponseDTO(Order order) {
        OrderResponseDTO responseDTO = new OrderResponseDTO();
        responseDTO.setId(order.getId());
        responseDTO.setUserId(order.getUser().getId());
        responseDTO.setCreatedDate(order.getCreatedDate());
        responseDTO.setStatus(order.getStatus());
        responseDTO.setType(order.getType());

        BigDecimal totalAmount = BigDecimal.ZERO;
        List<OrderDetailResponseDTO> orderDetailDTOs = new ArrayList<>();

        for (OrderDetail detail : order.getOrderDetails()) {
            OrderDetailResponseDTO detailDTO = new OrderDetailResponseDTO();
            detailDTO.setProductId(detail.getProduct().getId());
            detailDTO.setProductName(detail.getProduct().getName());
            detailDTO.setQuantity(detail.getQuantity());
            detailDTO.setUnitPrice(detail.getUnitPrice());
            detailDTO.setTotalPrice(detail.getUnitPrice().multiply(BigDecimal.valueOf(detail.getQuantity())));

            orderDetailDTOs.add(detailDTO);
            totalAmount = totalAmount.add(detailDTO.getTotalPrice());
        }

        responseDTO.setOrderDetails(orderDetailDTOs);
        responseDTO.setTotalAmount(totalAmount);

        return responseDTO;
    }
}