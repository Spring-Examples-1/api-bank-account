package com.bank.account.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "OPERATION")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Operation {

	/**
	 * The operation ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OPERATION_ID")
	private long operationId;

	/**
	 * The operation amount
	 */
	@Column(name = "AMOUNT", precision = 10, scale = 2)
	private Double amount;

	/**
	 * The operation description
	 */
	@Column(name = "DESCRIPTION", length = 200)
	private String description;

	/**
	 * The operation date
	 */
	@Column(name = "OPERATION_DATE")
	private LocalDateTime date;

	/**
	 * The operation links for a account
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCOUNT_ID")
	private Account account;

	/**
	 * Constructor require amount, description and the associated account
	 * 
	 * @param amount      The operation amount
	 * @param description The operation description
	 * @param account     The operation account
	 */
	public Operation(Double amount, String description, Account account) {
		super();
		this.amount = amount;
		this.description = description;
		this.date = LocalDateTime.now();
		this.account = account;
	}

}