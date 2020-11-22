package com.bank.account.controller.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SortCriteria {
	private String direction;
	private String fieldName;

}
