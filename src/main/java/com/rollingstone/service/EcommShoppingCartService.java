package com.rollingstone.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rollingstone.dao.jpa.EcommShoppingCartRepository;
import com.rollingstone.domain.ShoppingCart;

/*
 * Service class to do CRUD for User and Address through JPS Repository
 */
@Service
public class EcommShoppingCartService {

    private static final Logger log = LoggerFactory.getLogger(EcommShoppingCartService.class);

    @Autowired
    private EcommShoppingCartRepository cartRepository;

    @Autowired
    CounterService counterService;

    @Autowired
    GaugeService gaugeService;

    public EcommShoppingCartService() {
    }

    @Transactional
    public ShoppingCart createCart(ShoppingCart user) {
        return cartRepository.save(user);
    }

    public ShoppingCart getCart(long id) {
        return cartRepository.findOne(id);
    }

    public void updateCart(ShoppingCart user) {
    	cartRepository.save(user);
    }

    public void deleteCart(Long id) {
    	cartRepository.delete(id);
    }

    //http://goo.gl/7fxvVf
    public Page<ShoppingCart> getAllCarts(Integer page, Integer size) {
        Page pageOfCarts = cartRepository.findAll(new PageRequest(page, size));
        // example of adding to the /metrics
        if (size > 50) {
            counterService.increment("com.rollingstone.getAll.largePayload");
        }
        return pageOfCarts;
    }
}
