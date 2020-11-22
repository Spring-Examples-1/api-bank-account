/**
 * 
 */
package com.bank.account.model.dto;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.bank.account.model.entity.Operation;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author Rached
 *
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class OperationsHistoryResponse {
	
	/**
     * The operation balance
     */
	@JsonProperty(value = "balance")
    private Double balance;
    
	
	/**
	 * The error list
	 */
	@JsonProperty(value = "operations")
	private List<OperationResponse> operations;

	public OperationsHistoryResponse addHistory(Operation op) {
		if (operations==null) {
			operations = new ArrayList<OperationResponse>();
		}
		operations.add(new OperationResponse(op));
		return this;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OperationsHistoryResponse other = (OperationsHistoryResponse) obj;
		if (balance == null) {
			if (other.balance != null)
				return false;
		} else if (!balance.equals(other.balance))
			return false;
		if (operations == null) {
			if (other.operations != null)
				return false;
		} else if (!CollectionUtils.isEqualCollection(operations, other.operations))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((balance == null) ? 0 : balance.hashCode());
		result = prime * result + ((operations == null) ? 0 : operations.hashCode());
		return result;
	}
	
	
}
