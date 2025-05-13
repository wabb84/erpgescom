package com.produccion.gescom.exceptions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.validation.FieldError;

@RestControllerAdvice
@Order(1)
public class GlobalExceptionHandler {
	// Definir el logger para registrar errores
	 @ExceptionHandler(CustomException.class)
	 public ResponseEntity<MensajeResponse> handleCustomException(CustomException ex, WebRequest request) {
		 	// 	Obtener el stack trace y el nombre de la clase que causó el error
			//System.out.println("Llegue aca HttpStatus.NOT_FOUND");

		 String className = ex.getStackTrace()[0].getClassName();
	        MensajeResponse errorDetails = new MensajeResponse(
	        	0,
	        	"",
	            ex.getCodigoError(),                   // Código de error
	            ex.getMessage(),                       // Mensaje de error desde ErrorMessages
	            Arrays.asList(request.getDescription(false)),// Detalles adicionales
	            className                              // Nombre de la clase desde donde provino la excepción
	        );
	        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	 }
	 
	 
	 @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	 @ExceptionHandler(Exception.class)
	 public ResponseEntity<MensajeResponse> handleException(Exception e) {
		 	//System.out.println("HttpStatus.INTERNAL_SERVER_ERROR");
		 	// 	Obtener el stack trace y el nombre de la clase que causó el error
	        String className = e.getStackTrace()[0].getClassName();
	        //logger.error("Error general en la aplicación: ", e);
	        MensajeResponse errorDetails = new MensajeResponse(
	        	0,
	        	"",
	        	"INTE-500",      // Código de error
	            e.getMessage(), 
	            Collections.singletonList(e.getMessage()), // Mensaje de error desde ErrorMessages
	            className                             // Nombre de la clase desde donde provino la excepción
	        );
	        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR); // Puedes cambiar el estado HTTP
	 }
	 
	 @ResponseStatus(HttpStatus.BAD_REQUEST)
	 @ExceptionHandler(MethodArgumentNotValidException.class)
	 public ResponseEntity<MensajeResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
		 
		 System.out.println("HttpStatus.BAD_REQUEST");
		 
	     String className = ex.getStackTrace()[0].getClassName();
	     
	     List<String> errores = ex.getBindingResult()
	    	        .getFieldErrors()
	    	        .stream()
	    	        .map(error -> error.getField() + ": " + error.getDefaultMessage())
	    	        .toList();

	    	    MensajeResponse errorDetails = new MensajeResponse(
	    	        0,
	    	        "",
	    	        "VALI-400",
	    	        "Error de validación de campos",
	    	        errores,
	    	        className
	    	    );
	     
	     return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	 }
}
