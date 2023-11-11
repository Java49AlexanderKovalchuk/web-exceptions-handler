package telran.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;
//import telran.spring.controller.GreetingsController;

@ControllerAdvice
@Slf4j
public class GlobalExeptionsHandler {
	@ExceptionHandler({ IllegalStateException.class })
	ResponseEntity<String> badRequest(IllegalStateException e) {
		String message = e.getMessage();
		log.error("bad request. {}", message);
		return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);   
	}
	@ExceptionHandler({NotFoundExeption.class})
	ResponseEntity<String> notFound(NotFoundExeption e) {
		String message = e.getMessage();
		log.error("not found. {}", message);
		return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({MethodArgumentNotValidException.class})
	ResponseEntity<String> noValidArg(MethodArgumentNotValidException e) {
		String message = e.getFieldError().getDefaultMessage();
		String argField = e.getFieldError().getField();
		log.error("Method noValidArg: exeption {} argument {}", argField, message);
		return new ResponseEntity<String>(argField + " " + message, HttpStatus.BAD_REQUEST);
	}
	
	
	
}
