package com.bank.account.util;

/**
 * @author Rached
 *
 */
public enum AccountTypeEnum {
	/**
	 * Checking accounts type
	 */
	CHECKING_ACCOUNTS(1, "Checking accounts"),
	
	/**
	 * Savings accounts type
	 */
	SAVINGS_ACCOUNTS(2, "Savings accounts"),
	
	/**
	 * Money market accounts type
	 */
	MONEY_MARKET_ACCOUNTS(3, "Money market accounts"),
	
	/**
	 * Certificates of deposit (CDs) account type
	 */
	CERTIFICATES_DEPOSIT(4, "Certificates of deposit (CDs)");
	
	/**
	 * The account type id
	 */
	private int id;
	
	/**
	 * The account description
	 */
	private String description;
	
	private AccountTypeEnum(int id, String description) {
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
