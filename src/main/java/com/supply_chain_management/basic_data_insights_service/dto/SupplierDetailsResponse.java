package com.supply_chain_management.basic_data_insights_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDetailsResponse {
    private String supplierName;
    private List<ItemDetails> items;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ItemDetails {
        private String itemName;
        private int quantity;
    }
}
