package com.nxc.nexuschain.controllers.admin;

import com.nxc.nexuschain.dtos.OrderCreateDTO;
import com.nxc.nexuschain.dtos.OrderResponseDTO;
import com.nxc.nexuschain.enums.OrderStatusEnum;
import com.nxc.nexuschain.services.OrderService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/orders")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public String listOrders(Model model) {
        List<OrderResponseDTO> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "listorders";
    }

    @GetMapping("/{id}")
    public String viewOrder(@PathVariable Integer id, Model model) {
        OrderResponseDTO order = orderService.getOrderById(id);
        model.addAttribute("order", order);
        return "vieworders";
    }

    @GetMapping("/create")
    public String createOrderForm(Model model) {
        model.addAttribute("orderCreateDTO", new OrderCreateDTO());
        return "createorder";
    }

    @PostMapping("/create")
    public String createOrder(@ModelAttribute OrderCreateDTO orderCreateDTO) {
        orderService.createOrder(orderCreateDTO);
        return "redirect:/admin/orders";
    }

    @PostMapping("/{id}/update-status")
    public String updateOrderStatus(@PathVariable Integer id, @RequestParam OrderStatusEnum newStatus) {
        orderService.updateOrderStatus(id, newStatus);
        return "redirect:/admin/orders/" + id;
    }

    @PostMapping("/{id}/delete")
    public String deleteOrder(@PathVariable Integer id) {
        orderService.deleteOrder(id);
        return "redirect:/admin/orders";
    }
}