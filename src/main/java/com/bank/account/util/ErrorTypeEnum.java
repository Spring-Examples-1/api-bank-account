package com.bank.account.util;

/**
 * @author Rached
 *
 */
public enum ErrorTypeEnum {
	/**
	 *  Functional error
	 */
	FUNCTIONAL_ERROR(0, "Functional error"),
	
	/**
	 *  Format error
	 */
	FORMAT_ERROR(1, "Format error"),
	
	/**
	 * Technical error
	 */
	TECHNICAL_ERROR(2, "Technical error");
	
	/**
	 * The account type id
	 */
	private int id;
	
	/**
	 * The account description
	 */
	private String description;
	
	private ErrorTypeEnum(int id, String description) {
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
	
	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.description;
	}

}
