package StandUpComedy.Application;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import StandUpComedy.Application.ComedianNotFoundException;

@ControllerAdvice
class ComedianNotFoundAdvice {

	@ResponseBody
	@ExceptionHandler(ComedianNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String comedianNotFoundHandler(ComedianNotFoundException ex) {
		return ex.getMessage();
	}
}
