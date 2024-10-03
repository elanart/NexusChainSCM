package com.nxc.nexuschain.dtos;

import com.nxc.nexuschain.enums.OrderStatusEnum;
import com.nxc.nexuschain.enums.OrderTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {
    private Integer id;
    private Integer userId;
    private LocalDateTime createdDate;
    private OrderStatusEnum status;
    private OrderTypeEnum type;
    private BigDecimal totalAmount;
    private List<OrderDetailResponseDTO> orderDetails;
}
