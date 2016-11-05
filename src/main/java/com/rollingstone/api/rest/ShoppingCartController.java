package com.rollingstone.api.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rollingstone.domain.ShoppingCart;
import com.rollingstone.domain.User;
import com.rollingstone.exception.HTTP400Exception;
import com.rollingstone.service.EcommShoppingCartService;


/*
 * Demonstrates how to set up RESTful API endpoints using Spring MVC
 */
@EnableDiscoveryClient
@RestController
@EnableFeignClients
@RequestMapping(value = "/shoppingcartservice/v1/cart")
public class ShoppingCartController extends AbstractRestController {

    @Autowired
    private EcommShoppingCartService cartService;
    
    @Autowired
    UserServiceClient userServiceClient;

    @RequestMapping(value = "",
            method = RequestMethod.POST, 
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody ShoppingCart cart,
                                 HttpServletRequest request, HttpServletResponse response) {
        ShoppingCart createdCart;
		try {
			createdCart = this.cartService.createCart(cart);
	        response.setHeader("Location", request.getRequestURL().append("/").append(createdCart.getId()).toString());

		} catch (Exception e) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
		}
    }

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    Page<ShoppingCart> getAllCarts(@RequestParam(value = "page", required = true, defaultValue = DEFAULT_PAGE_NUM) Integer page,
                                      @RequestParam(value = "size", required = true, defaultValue = DEFAULT_PAGE_SIZE) Integer size,
                                      HttpServletRequest request, HttpServletResponse response) {
        return this.cartService.getAllCarts(page, size);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    ShoppingCart getCart(@PathVariable("id") Long id,
                             HttpServletRequest request, HttpServletResponse response) throws Exception {
        ShoppingCart user = this.cartService.getCart(id);
        checkResourceFound(user);
        //todo: http://goo.gl/6iNAkz
        return user;
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable("id") Long id, @RequestBody ShoppingCart user,
                                 HttpServletRequest request, HttpServletResponse response) {
        checkResourceFound(this.cartService.getCart(id));
        if (id != user.getId()) throw new HTTP400Exception("ID doesn't match!");
        this.cartService.updateCart(user);
    }

    //todo: @ApiImplicitParams, @ApiResponses
    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") Long id, HttpServletRequest request,
                                 HttpServletResponse response) {
        checkResourceFound(this.cartService.getCart(id));
        this.cartService.deleteCart(id);
    }
    
    @FeignClient("user-service")
    interface UserServiceClient {

    	@RequestMapping(method = RequestMethod.GET, value="/userservice/v1/users")
    	User getUser(@PathVariable("userId") String userId);
    }
}
