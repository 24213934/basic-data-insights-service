package com.supply_chain_management.basic_data_insights_service.dto;

import lombok.Data;
import java.util.List;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

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
