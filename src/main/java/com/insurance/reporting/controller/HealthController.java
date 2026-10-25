package com.insurance.reporting.controller;
import com.insurance.reporting.dto.ApiResponse;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController @RequestMapping("/api/v1") public class HealthController {
 @GetMapping("/health") public ApiResponse<Map<String,String>> health(){
  return ApiResponse.success("Reporting service is running",
          Map.of("service","reporting-service","status",
                  "UP","dataSource","DUMMY_DATA"));}
}
