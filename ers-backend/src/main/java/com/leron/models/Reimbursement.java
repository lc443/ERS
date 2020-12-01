package com.leron.models;

import java.sql.Blob;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Table(name = "reimbursement")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Reimbursement {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "amount")
	private double amount;
	
	@Column(name = "submitted")
	private Timestamp submitted;
	
	@Column(name = "resolved")
	private Timestamp resolved;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "receipt")
	private Blob receipt;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "author", insertable = false, updatable = false)
	private User author;
	
	@ManyToOne
	@JoinColumn(name = "resolver", nullable = true)
	private User resolver;
	
	@ManyToOne
	@JoinColumn(name = "status_id", nullable = false)
	private Status statusId;
	
	@ManyToOne
	@JoinColumn(name = "reimb_type", nullable = false)
	private Type reimbTypeId;

	
	
	public Reimbursement() {
		super();
	}
	
	public Reimbursement(long id, double amount, Timestamp submitted, Timestamp resolved, String description,
			Blob receipt, User author, User resolver, Status statusId, Type reimbTypeId) {
		super();
		this.id = id;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.receipt = receipt;
		this.author = author;
		this.resolver = resolver;
		this.statusId = statusId;
		this.reimbTypeId = reimbTypeId;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Timestamp getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Timestamp submitted) {
		this.submitted = submitted;
	}

	public Timestamp getResolved() {
		return resolved;
	}

	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Blob getReceipt() {
		return receipt;
	}

	public void setReceipt(Blob receipt) {
		this.receipt = receipt;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public User getResolver() {
		return resolver;
	}

	public void setResolver(User resolver) {
		this.resolver = resolver;
	}

	public Status getStatusId() {
		return statusId;
	}

	public void setStatusId(Status statusId) {
		this.statusId = statusId;
	}

	public Type getReimbTypeId() {
		return reimbTypeId;
	}

	public void setReimbTypeId(Type reimbTypeId) {
		this.reimbTypeId = reimbTypeId;
	}
	
}
