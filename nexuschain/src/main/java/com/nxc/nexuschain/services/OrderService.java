package com.nxc.nexuschain.services;

import com.nxc.nexuschain.dtos.OrderCreateDTO;
import com.nxc.nexuschain.dtos.OrderResponseDTO;
import com.nxc.nexuschain.entities.Order;
import com.nxc.nexuschain.entities.OrderDetail;
import com.nxc.nexuschain.enums.OrderStatusEnum;

import java.util.List;
import java.util.Map;

public interface OrderService {
    OrderResponseDTO createOrder(OrderCreateDTO orderCreateDTO);
    OrderResponseDTO getOrderById(Integer orderId);
    List<OrderResponseDTO> getAllOrders();
    OrderResponseDTO updateOrderStatus(Integer orderId, OrderStatusEnum newStatus);
    void deleteOrder(Integer orderId);
}
