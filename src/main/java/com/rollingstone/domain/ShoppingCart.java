package com.rollingstone.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/*
 * A User POJO serving as an Entity as well as a Data Transfer Object i.e DTO 
 */
@Entity
@Table(name = "ecomm_cart")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ShoppingCart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id",referencedColumnName="id")
	//@JsonBackReference
	User user;

	@Column(nullable = false)
	private double cartTotalValue;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "CART_DATE", unique = true, nullable = false, length = 10)
	private Date cartDate;

	
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    @JsonManagedReference
	private Set<CartItem> cartItems = new HashSet<CartItem>();;


	@Column()
	private int rating;

	public ShoppingCart() {
	}

	
	public ShoppingCart(User user, double cartTotalValue, Date cartDate, Set<CartItem> cartItems, int rating) {
		super();
		this.user = user;
		this.cartTotalValue = cartTotalValue;
		this.cartDate = cartDate;
		this.cartItems = cartItems;
		this.rating = rating;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public double getCartTotalValue() {
		return cartTotalValue;
	}


	public void setCartTotalValue(double cartTotalValue) {
		this.cartTotalValue = cartTotalValue;
	}


	public Date getCartDate() {
		return cartDate;
	}


	public void setCartDate(Date cartDate) {
		this.cartDate = cartDate;
	}


	public Set<CartItem> getCartItems() {
		return cartItems;
	}


	public void setCartItems(Set<CartItem> cartItems) {
		this.cartItems = cartItems;
	}


	public int getRating() {
		return rating;
	}


	public void setRating(int rating) {
		this.rating = rating;
	}


	

	
}
