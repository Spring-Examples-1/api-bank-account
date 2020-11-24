package com.bank.account.controller.common;

import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bank.account.exception.FunctionalException;
import com.bank.account.model.dto.common.Error;
import com.bank.account.model.dto.common.Errors;
import com.bank.account.util.ErrorTypeEnum;

import lombok.extern.log4j.Log4j2;

/**
 * @author Rached
 *
 */
@Log4j2
@ControllerAdvice
public class HandlerController {

	@Autowired
	private Environment environment;

	/**
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(FunctionalException.class)
	@ResponseBody
	public ResponseEntity<Errors> handleFunctionalException(final FunctionalException ex) {
		log.error("handleFunctionalException() - FunctionalException: {}", ex.getMessage());
		Errors errors = getFunctionalErrors(ex);
		log.error(errors);
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	/**
	 * @param ex
	 * @return
	 */
	@ExceptionHandler({ MethodArgumentNotValidException.class })
	@ResponseBody
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		log.error("handleMethodArgumentNotValid() - MethodArgumentNotValidException: {}", ex.getMessage());

		Errors errorsBody = new Errors();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			addFormatError(error.getField() + ": " + error.getDefaultMessage(), errorsBody);
		}
		for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			addFormatError(error.getObjectName() + ": " + error.getDefaultMessage(), errorsBody);
		}

		return new ResponseEntity<>(errorsBody, HttpStatus.BAD_REQUEST);
	}

	/**
	 * @param ex
	 * @return
	 */
	@ExceptionHandler({ Exception.class })
	@ResponseBody
	public ResponseEntity<Object> handleServerErrorException(HttpServletRequest request, Exception ex) {
		log.error("handleServerErrorException() - MethodArgumentNotValidException: {}", ex.getMessage());

		HttpStatus status = getStatus(request);
		
		Errors errorsBody = addTechnicalError(ex.getMessage(), status);

		return new ResponseEntity<>(errorsBody, status);
	}

	private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
	
	/**
	 * @param ex
	 * @return
	 */
	private Errors getFunctionalErrors(final FunctionalException ex) {
		for (Error error : ex.getErrors().getErrorsList()) {
			addFunctionalError(error);
		}
		return ex.getErrors();
	}

	/**
	 * Add and format a new functional error
	 * 
	 * @param error The {@link Error}
	 */
	private void addFunctionalError(Error error) {
		error.setErrorMessage(formatMessage(error.getErrorCode(), error.getParams()));
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setErrorOrigin(ErrorTypeEnum.FUNCTIONAL_ERROR.getDescription());
	}

	/**
	 * Add and format a new format error
	 * 
	 * @param errorsBody
	 * @param errorMessage The error message
	 */
	private void addFormatError(String errorMessage, Errors errorsBody) {
		errorsBody.addError(HttpStatus.BAD_REQUEST.value(), ErrorTypeEnum.FORMAT_ERROR.getDescription(), errorMessage);
	}

	/**
	 * Add and format a new technical error
	 * 
	 * @param errorMessage The error message * @param status The Http Status
	 */
	private Errors addTechnicalError(String errorMessage, HttpStatus status) {
		Errors errorsBody = new Errors();
		errorsBody.addError(status.value(), ErrorTypeEnum.TECHNICAL_ERROR.getDescription(), errorMessage);
		return errorsBody;
	}

	/**
	 * @param errorCode
	 * @param params
	 * @return
	 */
	private String formatMessage(Integer errorCode, Object... params) {
		String messageFormat = environment.getProperty(String.valueOf(errorCode));
		messageFormat = MessageFormat.format(messageFormat, params);
		return  errorCode + " - " + messageFormat;
	}

}
