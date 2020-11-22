/**
 * 
 */
package com.bank.account.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Rached
 *
 */
public final class DateUtis {
	
	/**
	 * Pattern: "yyyy-MM-dd HH:mm:ss"
	 */
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * DateTimeFormatter : "yyyy-MM-dd HH:mm:ss"
	 */
	public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS);
	
	private DateUtis() {
	}
	
	/**
	 * @param localDateTime
	 * @return
	 */
	public static String formatDateTime(LocalDateTime localDateTime) {
		return dateTimeFormatter.format(localDateTime);
	}
	
	/**
	 * @param value
	 * @return
	 */
	public static LocalDateTime formatDateTime(String value) {
		return LocalDateTime.parse(value, DateUtis.dateTimeFormatter);
	}

}
