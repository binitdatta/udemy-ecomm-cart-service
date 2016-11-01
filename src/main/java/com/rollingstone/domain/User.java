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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonManagedReference;


/*
 * A User POJO serving as an Entity as well as a Data Transfer Object i.e DTO
 */
@Entity
@Table(name = "ecomm_user")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false)
	private double creditAmount;

	@Column(nullable = false)
	private int bonusPoints;

	@Temporal(TemporalType.DATE)
	@Column(name = "MEMBER_SINCE", unique = false, nullable = false, length = 10)
	private Date memberSince;

	/*
	 * One-to-Many association using generics. If the collection is defined using generics to specify the element type, 
	  the associated target entity type need not be specified; 
	 * otherwise the target entity class must be specified. 
	 * If the relationship is bidirectional, the   mappedBy element must be used to specify the relationship 
	 * field or property of the entity that is the owner of the relationship
	 * 
	 * CascadeType.PERSIST: When persisting an entity, also persist the entities held in this field. 
	 * We suggest liberal application of this cascade rule, because if the EntityManager finds a field that references a new entity during flush, 
	 * and the field does not use CascadeType.PERSIST, it is an error.
	 * 	CascadeType.REMOVE: When deleting an entity, also delete the entities held in this field.
	 * CascadeType.REFRESH: When refreshing an entity, also refresh the entities held in this field.
	 * CascadeType.MERGE: When merging entity state, also merge the entities held in this field.
	 */
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
	private Set<Address> addresses = new HashSet<Address>();

	@Column()
	private int rating;

	public User() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(double creditAmount) {
		this.creditAmount = creditAmount;
	}

	public int getBonusPoints() {
		return bonusPoints;
	}

	public void setBonusPoints(int bonusPoints) {
		this.bonusPoints = bonusPoints;
	}

	
	public Date getMemberSince() {
		return memberSince;
	}

	public void setMemberSince(Date memberSince) {
		this.memberSince = memberSince;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Set<Address> getAddresses() {
		return this.addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", creditAmount="
				+ creditAmount + ", bonusPoints=" + bonusPoints + ", memberSince=" + memberSince + ", addresses="
				+ addresses + ", rating=" + rating + "]";
	}

	
}
