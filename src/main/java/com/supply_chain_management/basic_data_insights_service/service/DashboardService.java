package com.supply_chain_management.basic_data_insights_service.service;

import java.util.List;

import com.supply_chain_management.basic_data_insights_service.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.supply_chain_management.basic_data_insights_service.enums.OrderStatus;

@Service
public class DashboardService {
    @Autowired
    private RestTemplate restTemplate;

    private final String inventoryServiceUrl = "http://localhost:8081/inventory/item";
    private final String orderServiceUrl = "http://localhost:8082/orders/list";
    private final String supplierServiceUrl = "http://localhost:8083/supplier/details/";

    public DashboardResponse getDashboardData() {
        DashboardResponse dashboard = new DashboardResponse();
        ResponseEntity<List> inventoryResponse = restTemplate.getForEntity(inventoryServiceUrl, List.class);
        if (inventoryResponse.getBody() != null) {
            dashboard.setTotalInventoryItems(inventoryResponse.getBody().size());
        }
        ResponseEntity<OrderResponse[]> orderResponse = restTemplate.getForEntity(orderServiceUrl, OrderResponse[].class);
        if (orderResponse.getBody() != null) {
            List<OrderResponse> orders = List.of(orderResponse.getBody());
            dashboard.setTotalOrders(orders.size());
            int delivered = 0, shipped = 0, pending = 0, cancelled = 0;
            for (OrderResponse order : orders) {
                if (order.getStatus() == OrderStatus.DELIVERED) delivered++;
                else if (order.getStatus() == OrderStatus.SHIPPED) shipped++;
                else if (order.getStatus() == OrderStatus.PENDING) pending++;
                else if (order.getStatus() == OrderStatus.CANCELLED) cancelled++;
            }
            dashboard.setDeliveredOrders(delivered);
            dashboard.setShippedOrders(shipped);
            dashboard.setPendingOrders(pending);
            dashboard.setCancelledOrders(cancelled);
        }
        return dashboard;
    }

    public SupplierPerformanceReport getSupplierPerformance(Long supplierId) {
        ResponseEntity<SupplierDetailsResponse> supplierResponse = restTemplate.getForEntity(
                supplierServiceUrl + supplierId, SupplierDetailsResponse.class
        );

        SupplierDetailsResponse supplier = supplierResponse.getBody();
        if (supplier == null) {
            throw new RuntimeException("Supplier not found");
        }

        System.out.println("ERROR1" + supplier);

        ResponseEntity<OrderResponse[]> orderResponse = restTemplate.getForEntity(orderServiceUrl, OrderResponse[].class);
        List<OrderResponse> orders = List.of(orderResponse.getBody());

        System.out.println("ERROR2" + orders);


        int successfulDeliveries = 0, failedDeliveries = 0, totalOrders = 0;
        for (OrderResponse order : orders) {
            if (order.getSupplierId() != null && order.getSupplierId().equals(supplierId)) {
                totalOrders++;
                if (order.getStatus() == OrderStatus.DELIVERED) successfulDeliveries++;
                else if (order.getStatus() == OrderStatus.CANCELLED) failedDeliveries++;
            }
        }

        return new SupplierPerformanceReport(
                supplier.getSupplierName(),
                totalOrders,
                successfulDeliveries,
                failedDeliveries
        );
    }

    public SupplierDashboardResponse getSupplierDashboard() {
        ResponseEntity<OrderResponse[]> orderResponse = restTemplate.getForEntity(orderServiceUrl, OrderResponse[].class);
        List<OrderResponse> orders = List.of(orderResponse.getBody());

        int totalOrders = orders.size();
        int successfulDeliveries = (int) orders.stream().filter(o -> o.getStatus() == OrderStatus.DELIVERED).count();
        int failedDeliveries = (int) orders.stream().filter(o -> o.getStatus() == OrderStatus.CANCELLED).count();

        return new SupplierDashboardResponse(totalOrders, successfulDeliveries, failedDeliveries);
    }
}

