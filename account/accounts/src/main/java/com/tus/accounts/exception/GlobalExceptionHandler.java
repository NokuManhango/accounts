package com.tus.accounts.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import com.tus.accounts.dto.ErrorResponseDto;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.ObjectError;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.validation.FieldError;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	 @ExceptionHandler(CustomerAlreadyExistsException.class)
	    public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyExistsException(CustomerAlreadyExistsException exception,
	                                                                                 WebRequest webRequest){
	        ErrorResponseDto errorResponseDTO = new ErrorResponseDto(
	                webRequest.getDescription(false),
	                HttpStatus.BAD_REQUEST,
	                exception.getMessage(),
	                LocalDateTime.now()
	        );
	        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
	    }
	 
	 @Override
	    protected ResponseEntity<Object> handleMethodArgumentNotValid(
	            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
	        Map<String, String> validationErrors = new HashMap<>();
	        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

	        validationErrorList.forEach((error) -> {
	            String fieldName = ((FieldError) error).getField();
	            String validationMsg = error.getDefaultMessage();
	            validationErrors.put(fieldName, validationMsg);
	        });
	        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
	    }
	 @ExceptionHandler(Exception.class)
	    public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception exception,
	                                                                                 WebRequest webRequest){
	        ErrorResponseDto errorResponseDTO = new ErrorResponseDto(
	                webRequest.getDescription(false),
	                HttpStatus.INTERNAL_SERVER_ERROR,
	                exception.getMessage(),
	                LocalDateTime.now()
	        );
	        return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	 
	 @ExceptionHandler(ResourceNotFoundException.class)
	    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException exception,
	                                                                                 WebRequest webRequest) {
	        ErrorResponseDto errorResponseDTO = new ErrorResponseDto(
	                webRequest.getDescription(false),
	                HttpStatus.NOT_FOUND,
	                exception.getMessage(),
	                LocalDateTime.now()
	        );
	        return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
	    }
	 

}
