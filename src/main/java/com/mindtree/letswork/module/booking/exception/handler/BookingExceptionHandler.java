package com.mindtree.letswork.module.booking.exception.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mindtree.letswork.module.booking.exception.BookingErrorResponse;
import com.mindtree.letswork.module.booking.exception.DateServiceException;
import com.mindtree.letswork.module.booking.exception.InvalidExpiryDateException;
import com.mindtree.letswork.module.booking.exception.RecordNotFoundException;

@RestControllerAdvice
public class BookingExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = InvalidExpiryDateException.class)
	public ResponseEntity<Object> exception(InvalidExpiryDateException exception, WebRequest request) {
		BookingErrorResponse errorDetails = new BookingErrorResponse(exception.getMessage(), new Date(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = RecordNotFoundException.class)
	public ResponseEntity<Object> exception(RecordNotFoundException exception, WebRequest request) {
		BookingErrorResponse errorDetails = new BookingErrorResponse(exception.getMessage(), new Date(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = DateServiceException.class)
	public ResponseEntity<Object> exception(DateServiceException exception, WebRequest request) {
		BookingErrorResponse errorDetails = new BookingErrorResponse(exception.getMessage(), new Date(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
//	@ExceptionHandler(value =DateServiceException.class)
//	public APIErrorResponse invalidDateException(DateServiceException exception, WebRequest request) {
//		APIErrorResponse errorDetails = new APIErrorResponse(new Date(), exception.getMessage(), request.getDescription(false), HttpStatus.BAD_REQUEST);
////		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
//		return errorDetails;
//	}

//	@Override
//	protected ResponseEntity<Object> handleMethodArgumentNotValid(
//			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//
//		return handleExceptionInternal(ex, "card number is invalid" , headers, status, request);
//	}
//	
//	@Override
//	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//			HttpHeaders headers, HttpStatus status, WebRequest request) {
//		return new ResponseEntity<>("Your card number is not valid!", HttpStatus.BAD_REQUEST);
//	}
//	
//	@Override
//	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
//			HttpHeaders headers, HttpStatus status, WebRequest request) {
//		CustomAuthException errorDetails = new CustomAuthException("Invalid Input", new Date(),
//				request.getDescription(false));
//		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
//	}

}
