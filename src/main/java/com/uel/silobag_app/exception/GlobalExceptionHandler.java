package com.uel.silobag_app.exception;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.Instant;
import java.util.NoSuchElementException;

import org.apache.coyote.BadRequestException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	// Tratamento de exceções com base na classe de cada exception.
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ErroGenerico> badRequestExceptionHandler(BadRequestException e, HttpServletRequest request){
		
		ErroGenerico err = new ErroGenerico();
		err.setTimestamp(Instant.now());
		err.setPath(request.getRequestURI());
		err.setError("Bad Request.");
		err.setMessage(e.getMessage());
		err.setStatus(HttpStatus.BAD_REQUEST.value());
		
		return ResponseEntity.status(err.getStatus()).body(err);
		
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ErroGenerico> noSuchElementExceptionHandler(NoSuchElementException e, HttpServletRequest request){
		
		ErroGenerico err = new ErroGenerico();
		err.setTimestamp(Instant.now());
		err.setPath(request.getRequestURI());
		err.setError("No Such Element.");
		err.setMessage(e.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.value());
		
		return ResponseEntity.status(err.getStatus()).body(err);
		
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ErroGenerico> dataIntegrityViolationExceptionHandler(DataIntegrityViolationException e, HttpServletRequest request){
		
		ErroGenerico err = new ErroGenerico();
		err.setTimestamp(Instant.now());
		err.setPath(request.getRequestURI());
		err.setError("Data Integrity Violation.");
		err.setMessage("Verifique os parâmetros passados e tente novamente. Em caso de dúvida, consulte a documentação do projeto.");
		err.setStatus(HttpStatus.BAD_REQUEST.value());
		
		return ResponseEntity.status(err.getStatus()).body(err);
		
	}
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<ErroGenerico> sqlIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e, HttpServletRequest request){
		
		ErroGenerico err = new ErroGenerico();
		err.setTimestamp(Instant.now());
		err.setPath(request.getRequestURI());
		err.setError("SQL Integrity Exception.");
		err.setMessage(e.getMessage());
		err.setStatus(HttpStatus.BAD_REQUEST.value());
		
		return ResponseEntity.status(err.getStatus()).body(err);
		
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErroGenerico> runtimeExceptionHandler(RuntimeException e, HttpServletRequest request){
		
		ErroGenerico err = new ErroGenerico();
		err.setTimestamp(Instant.now());
		err.setPath(request.getRequestURI());
		err.setError("Erro durante a execucação do programa.");
		err.setMessage(e.getMessage());
		err.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		
		return ResponseEntity.status(err.getStatus()).body(err);
		
	}
	
}
