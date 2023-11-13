package telran.exeptions;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;
//import telran.spring.controller.GreetingsController;

@ControllerAdvice
@Slf4j
public class GlobalExeptionsHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class) 
	ResponseEntity<String> handlerMethodArgument(MethodArgumentNotValidException e) {
		List<ObjectError> errors = e.getAllErrors();
		String body = errors.stream().map(err -> err.getDefaultMessage())
				.collect(Collectors.joining(";"));
		return errorResponse(body, HttpStatus.BAD_REQUEST);
	}
	
	private ResponseEntity<String> errorResponse(String body, HttpStatus status) {
		log.error(body);
		return new ResponseEntity<String>(body, status);
	}

	@ExceptionHandler({ IllegalStateException.class, HttpMessageNotReadableException.class })
	ResponseEntity<String> badRequest(Exception e) {
		String message = e.getMessage();
		return errorResponse(message, HttpStatus.BAD_REQUEST);   
	}
	@ExceptionHandler({NotFoundExeption.class})
	ResponseEntity<String> notFound(NotFoundExeption e) { 
		String message = e.getMessage();
		return errorResponse(message, HttpStatus.NOT_FOUND);   
	 }
	
}
