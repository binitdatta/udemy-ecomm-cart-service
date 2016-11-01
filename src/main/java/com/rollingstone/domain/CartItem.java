package com.rollingstone.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "ecomm_cart_item")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
class CartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	
	//@ManyToOne(fetch = FetchType.EAGER)
	//@JoinColumn(name="product_id",referencedColumnName="id")
	//@JoinColumn(name="product_id")
	@OneToOne
	@JoinColumn(name="product_id")
	private Product product;

	@Column(nullable = false)
	private int quantity;
	
    @ManyToOne(optional=true, fetch=FetchType.EAGER)
	@JoinColumn(name = "cart_id")
	@JsonBackReference
	ShoppingCart cart;

	public CartItem(){
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

	public ShoppingCart getCart() {
		return cart;
	}

	public void setCart(ShoppingCart cart) {
		this.cart = cart;
	}

	@Override
	public String toString() {
		return "CartItem [id=" + id + ", product=" + product + ", quantity=" + quantity + "]";
	}

	public CartItem(Long id, Product product, int quantity) {
		super();
		this.id = id;
		this.product = product;
		this.quantity = quantity;
	}

}