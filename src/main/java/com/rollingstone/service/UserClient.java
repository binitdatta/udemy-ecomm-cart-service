package com.rollingstone.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rollingstone.domain.User;

@FeignClient("user-service")
interface UserClient {
	
	
	@RequestMapping(method = RequestMethod.GET, value="/userservice/v1/users/all")
	List<User> getUsers();
	
	@RequestMapping(method = RequestMethod.GET, value="/userservice/v1/users/simple/{id}")
	User getUser(@PathVariable("id") Long id);
	
}