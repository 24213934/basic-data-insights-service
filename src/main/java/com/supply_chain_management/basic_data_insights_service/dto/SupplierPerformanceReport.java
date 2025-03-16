package com.supply_chain_management.basic_data_insights_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SupplierPerformanceReport {
    private String supplierName;
    private int totalOrders;
    private int successfulDeliveries;
    private int failedDeliveries;
}
