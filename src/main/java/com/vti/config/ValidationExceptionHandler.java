package com.vti.config;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ValidationExceptionHandler extends ResponseEntityExceptionHandler {
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, String> errors = new HashMap<>();
		for (ObjectError objectError : exception.getBindingResult().getAllErrors()) {
			String fieldName = ((FieldError) objectError).getField();
			String errorMessage = objectError.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		}
		return new ResponseEntity<>(errors, status);

	}

	// bean validation error
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException exception) {
		Map<String, String> errors = new HashMap<>();

		for (ConstraintViolation violation : exception.getConstraintViolations()) {
			String fieldName = violation.getPropertyPath().toString();
			String errorMessage = violation.getMessage();
			errors.put(fieldName, errorMessage);
		}

		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	@Autowired
	private MessageSource messageSource;

	private String getMessage(String key) {
		return messageSource.getMessage(key, null, "Default message", LocaleContextHolder.getLocale());
	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleAll(Exception exception) {

		String message = getMessage("Exception.message") != null ? getMessage("Exception.message")
				: exception.getMessage();
//		String detailMessage = exception.getLocalizedMessage();
//		int code = 1;
//		String moreInformation = "http://localhost:8080/api/v1/exception/1";
//			
//		ErrorResponse response = new ErrorResponse(message, detailMessage, null, code, moreInformation);
		Map<String, String> errors = new HashMap<>();
		errors.put("messageError",message);

		return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
