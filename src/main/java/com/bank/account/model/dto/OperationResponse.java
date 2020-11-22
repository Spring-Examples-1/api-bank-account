package com.bank.account.model.dto;

import java.time.LocalDateTime;

import com.bank.account.model.entity.Operation;
import com.bank.account.util.DateUtis;
import com.bank.account.util.OperationTypeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author Rached
 *
 */
@Data
@RequiredArgsConstructor
public class OperationResponse {

	/**
     * The operation Id
     */
	@JsonProperty(value = "operationId")
	private long operationId;
	
	/**
     * The operation
     */
	@JsonProperty(value = "operation")
    private String operation;
	
	/**
     * The operation date
     */
	@JsonProperty(value = "date")
	@JsonFormat(pattern=DateUtis.YYYY_MM_DD_HH_MM_SS)
    private LocalDateTime  date;
    
	/**
     * The operation amount
     */
	@JsonProperty(value = "amount")
    private Double amount;
	
	
	/**
	 * Constructor with {@link Operation} entity
	 * 
	 * @param op The {@link Operation} entity
	 */
	public OperationResponse(Operation op) {
		this.operationId = op.getOperationId();
		this.operation = OperationTypeEnum.getOperationType(amount);
		this.amount = op.getAmount();
		this.date = op.getDate();
	}
	
}
