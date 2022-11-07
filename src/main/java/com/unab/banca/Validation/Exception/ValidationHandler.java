package com.unab.banca.Validation.Exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



import com.unab.banca.Validation.Entity.Error;
import com.unab.banca.Validation.Entity.ErrorResponse;

@RestControllerAdvice
public class ValidationHandler  {


	@ExceptionHandler
	protected ResponseEntity<ErrorResponse> handleException(MissingRequestHeaderException ex) {
		List<Error>  error = new ArrayList<>();
        error.add(new Error("Encabezados","Falto incluir los encabezados requeridos"));
		return buildResponseEntity(HttpStatus.UNAUTHORIZED,
				new RuntimeException("no incluyo encabezados"),error);
	}

	@ExceptionHandler
	protected ResponseEntity<ErrorResponse> handleException(UniqueException ex) {
		List<Error>  error = new ArrayList<>();
		error.add(ex.getError());
		return buildResponseEntity(HttpStatus.BAD_REQUEST,
		ex,error);
	}
	
	@ExceptionHandler
	protected ResponseEntity<ErrorResponse> handleException(NoAuthorizeException ex) {
		List<Error>  error = new ArrayList<>();
		error.add(ex.getError());
		return buildResponseEntity(HttpStatus.BAD_REQUEST,
		ex,error);
	}
	@ExceptionHandler ResponseEntity<ErrorResponse> handleException(HttpMessageNotReadableException ex){
		List<Error>  error = new ArrayList<>();
        error.add(new Error("Body","El dato no viene adjunto"));
        return buildResponseEntity(HttpStatus.BAD_REQUEST, new RuntimeException("Datos body"), error);
	}
	@ExceptionHandler
	protected ResponseEntity<ErrorResponse> handleException(InvalidDataException ex) {
		List<Error> errores = new ArrayList<>();
		ex.getResult().getAllErrors().forEach((error) -> {
			Error err = new Error();
			err.setType("Campo : "+((FieldError) error).getField());
			err.setMessage(error.getDefaultMessage());
			errores.add(err);
		});
		return buildResponseEntity(HttpStatus.BAD_REQUEST, ex, errores);

	}

	private ResponseEntity<ErrorResponse> buildResponseEntity(HttpStatus httpStatus, Exception exc) {
		return buildResponseEntity(httpStatus, exc, null);
	}

	private ResponseEntity<ErrorResponse> buildResponseEntity(HttpStatus httpStatus, Exception exc,
			List<Error> errors) {
		ErrorResponse error = new ErrorResponse();
		error.setMessage("USRMSG-" + exc.getMessage());
		error.setStatus(httpStatus.value());
		error.setTimestamp(new Date());
		error.setErrors(errors);
		return new ResponseEntity<>(error, httpStatus);

	}
}
