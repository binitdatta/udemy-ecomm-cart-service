package com.rollingstone.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rollingstone.domain.Product;

@FeignClient("product-service")
interface ProductClient {
	
	
	@RequestMapping(method = RequestMethod.GET, value="/productsservice/v1/products")
	List<Product> getProducts();
	
	@RequestMapping(method = RequestMethod.GET, value="/productsservice/v1/products/simple/{id}")
	Product getProduct(@PathVariable("id") Long id);
	
}