package com.vicentemartinez.microservicecoursemanagement.intercomm;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "log-service")
public interface LogClient {
	
	@GetMapping(value = "/service/popular", consumes = "application/json")
	List<Long> getPopularCourses();
	
}
