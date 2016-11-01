package com.rollingstone.dao.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.rollingstone.domain.ShoppingCart;



public interface EcommShoppingCartRepository extends PagingAndSortingRepository<ShoppingCart, Long> {
    ShoppingCart findUserByRating(int rating);
    Page findAll(Pageable pageable);
}
