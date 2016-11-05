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
import com.rollingstone.domain.*;

 
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
    
    @Autowired
	private UserClient userClient;

    @Autowired
	private ProductClient productClient;

    public EcommShoppingCartService() {
    }

    @Transactional
    public ShoppingCart createCart(ShoppingCart cart) throws Exception {
    	ShoppingCart shoppingCart = null;
    	boolean areCartItemsValid = true;
    	log.info("In service create");
    	if (cart != null && cart.getUser() != null){
    		log.info("In service create"+ cart.getUser().getId());
    		if (userClient == null){
        		log.info("In userServiceClient null got user");
    		}
    		else {
    			log.info("In userServiceClient not null got user");
    		}
    		
    		User user = userClient.getUser((new Long(cart.getUser().getId())));
    		if (user != null){
        		log.info("In service got user"+user.getId());
				log.info("In Service size of cart :"+cart.getCartItems().size());

        		for (CartItem cartItem : cart.getCartItems()){
    				log.info("Inside cart loop");
        			if (cartItem.getProduct() != null){
        				log.info("In service Product is not null");
        				Product product  = productClient.getProduct(new Long(cartItem.getProduct().getId())); 
        				if (product == null){
        	        		log.info("In service did not get product");
        	        		areCartItemsValid = false;
        				}else {
        					log.info("In Service Valid product");
        				}
        			}else {
        				log.info("In service Product is null");
        			}
        		}
    			if (areCartItemsValid){
    				shoppingCart = cartRepository.save(cart);
    			}
    		}else {
    			throw new Exception("Invalid USer");
    		}
    	}
        return shoppingCart;
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

    public Page<ShoppingCart> getAllCarts(Integer page, Integer size) {
        Page pageOfCarts = cartRepository.findAll(new PageRequest(page, size));
        // example of adding to the /metrics
        if (size > 50) {
            counterService.increment("com.rollingstone.getAll.largePayload");
        }
        return pageOfCarts;
    }
    
  
}
