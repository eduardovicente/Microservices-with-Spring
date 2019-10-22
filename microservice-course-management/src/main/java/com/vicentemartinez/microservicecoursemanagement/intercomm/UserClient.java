package com.vicentemartinez.microservicecoursemanagement.intercomm;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service")
public interface UserClient {
	@PostMapping(value = "/service/names", consumes = "application/json")
	List<String> getUsers(@RequestBody List<Long> userIdList);
}
