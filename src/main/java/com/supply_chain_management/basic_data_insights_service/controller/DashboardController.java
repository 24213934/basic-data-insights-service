package com.supply_chain_management.basic_data_insights_service.controller;

import com.supply_chain_management.basic_data_insights_service.dto.SupplierDashboardResponse;
import com.supply_chain_management.basic_data_insights_service.dto.SupplierPerformanceReport;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.supply_chain_management.basic_data_insights_service.dto.DashboardResponse;
import com.supply_chain_management.basic_data_insights_service.service.DashboardService;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {
    private final DashboardService dashboardService;

    @GetMapping("/insights")
    public ResponseEntity<?> getDashboardData() {
        try {
            DashboardResponse dashboard = dashboardService.getDashboardData();
            return new ResponseEntity<>(dashboard, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error fetching dashboard data", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/generate-report")
    public ResponseEntity<?> getSupplierPerformance() {
        try {
            SupplierPerformanceReport report = dashboardService.getSupplierPerformance();
            return new ResponseEntity<>(report, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to generate supplier performance report", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
