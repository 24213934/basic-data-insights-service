package com.supply_chain_management.basic_data_insights_service.dto;

import lombok.Data;
import com.supply_chain_management.basic_data_insights_service.enums.OrderStatus;

@Data
public class OrderResponse {
    private Long orderId;
    private Long itemId;
    private String itemName;
    private int quantity;
    private OrderStatus status;
    private double totalCost;
    private Long supplierId;
}
