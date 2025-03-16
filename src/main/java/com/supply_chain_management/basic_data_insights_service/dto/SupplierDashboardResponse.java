package com.supply_chain_management.basic_data_insights_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SupplierDashboardResponse {
    private int totalOrders;
    private int successfulDeliveries;
    private int failedDeliveries;
}
