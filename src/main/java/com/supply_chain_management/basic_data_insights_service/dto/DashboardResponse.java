package com.supply_chain_management.basic_data_insights_service.dto;

import lombok.Data;

@Data
public class DashboardResponse {
    private int totalInventoryItems;
    private int totalOrders;
    private int deliveredOrders;
    private int shippedOrders;
    private int pendingOrders;
    private int cancelledOrders;
}
