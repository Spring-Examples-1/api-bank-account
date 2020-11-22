package com.bank.account.util;

import java.util.Objects;

/**
 * @author Rached
 *
 */
public enum OperationTypeEnum {
	/**
	 *  Deposit operation
	 */
	DEPOSIT_OPERATION(1, "Deposit"),
	
	/**
	 * Withdrawal operation
	 */
	WITHDRAWAL_OPERATION(2, "Withdrawal");
	
	/**
	 * The account type id
	 */
	private int id;
	
	/**
	 * The account description
	 */
	private String description;
	
	private OperationTypeEnum(int id, String description) {
		this.id = id;
		this.description = description;
	}
	
	/**
	 * @return the account type id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @return the account type description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * @return the account type description
	 */
	public static String getOperationType(Double amount) {
		if(Objects.nonNull(amount) && amount<0) {
			return WITHDRAWAL_OPERATION.toString();
		}else {
			return DEPOSIT_OPERATION.toString();
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.description;
	}

}
