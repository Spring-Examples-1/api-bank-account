package com.bank.account.exception;

import com.bank.account.model.dto.common.Errors;

import lombok.Getter;
import lombok.ToString;

/**
 * Generic exception class used to throw all type of functional errors
 * 
 * @author Rached
 *
 */
@Getter
@ToString
public class FunctionalException extends Exception {
    /**
     * The serial Version UID
     */
    private static final long serialVersionUID = 894798122053539237L;

    private final transient Errors errors;
    
    /**
     * Constructs a FunctionalException with functional error code
     * @param errorCode
     * 		The functional error code define in errors.properties
     */
    public FunctionalException(Errors errors) {
        super("Business management rules are not respected");
        this.errors = errors;
    }
    
    public Errors getErrors() {
		return errors;
	}

}
