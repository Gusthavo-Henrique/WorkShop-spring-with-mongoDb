package br.com.gusthavo.WorkshopMongo.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import br.com.gusthavo.WorkshopMongo.services.exceptions.ObjectNotFoundException;

@RestControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, WebRequest request) {
		StandardError err = new StandardError(Instant.now(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity<StandardError>(err, HttpStatus.NOT_FOUND);
	}
}
