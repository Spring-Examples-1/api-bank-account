/**
 * 
 */
package com.bank.account.util;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.bank.account.model.entity.Account;

/**
 * @author Rached
 *
 */
public final class ApiUtils {

	private ApiUtils() {
	}

	/**
	 * Checks is accepted withdrawal operation.
	 * 
	 * @param account   The {@link Account} entity
	 * @param amount    The amount
	 * @param apiErrors The {@link ApiErrors}
	 * @return true if it is an authorized operation, else add a functional
	 *         exception to the ApiErrors return false.
	 */
	public static boolean checkIsAcceptedWithdrawalOperation(Account account, Double amount, ApiErrors apiErrors) {
		if (Objects.nonNull(amount) && Objects.nonNull(account) && amount < 0 && (account.getBalance() + amount) < 0) {
			apiErrors.addError(1002);
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Checks if a account number is associated to a user.
	 * 
	 * @param account   The {@link Account} entity
	 * @param userName  The user name
	 * @param apiErrors The {@link ApiErrors}
	 * @return true if a account number is associated to a user, else add a
	 *         functional exception to the ApiErrors return false.
	 */
	public static boolean checkIsUserAccount(Account account, String userName, ApiErrors apiErrors) {
		if (Objects.nonNull(account) && Objects.nonNull(userName)
				&& StringUtils.equalsIgnoreCase(account.getClient().getUserName(), userName)) {
			return true;
		} else {
			apiErrors.addError(1003);
			return false;
		}
	}

}
