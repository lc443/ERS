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
	
}
