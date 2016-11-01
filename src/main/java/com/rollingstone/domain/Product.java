package com.rollingstone.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "ecomm_product")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false)
	private String productName;

	@Column(nullable = false)
	private String productShortDescription;

	@Column(nullable = false)
	private String productLongDescription;

	@Column(nullable = false)
	private String imageUrl;

	@Column(nullable = false)
	private String productType;

	@Column(nullable = false)
	private double productPrice;

	public Product() {

	}

	
	public Product(String productName, String productShortDescription, String productLongDescription, String imageUrl,
			String productType, double productPrice) {
		super();
		this.productName = productName;
		this.productShortDescription = productShortDescription;
		this.productLongDescription = productLongDescription;
		this.imageUrl = imageUrl;
		this.productType = productType;
		this.productPrice = productPrice;
	}



	public double getProductPrice() {
		return productPrice;
	}


	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getProductShortDescription() {
		return productShortDescription;
	}


	public void setProductShortDescription(String productShortDescription) {
		this.productShortDescription = productShortDescription;
	}


	public String getProductLongDescription() {
		return productLongDescription;
	}


	public void setProductLongDescription(String productLongDescription) {
		this.productLongDescription = productLongDescription;
	}


	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	public String getProductType() {
		return productType;
	}


	public void setProductType(String productType) {
		this.productType = productType;
	}


	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", productShortDescription="
				+ productShortDescription + ", productLongDescription=" + productLongDescription + ", imageUrl="
				+ imageUrl + ", productType=" + productType + ", productPrice=" + productPrice + ", getProductPrice()="
				+ getProductPrice() + ", getId()=" + getId() + ", getProductName()=" + getProductName()
				+ ", getProductShortDescription()=" + getProductShortDescription() + ", getProductLongDescription()="
				+ getProductLongDescription() + ", getImageUrl()=" + getImageUrl() + ", getProductType()="
				+ getProductType() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}




	
	

}
