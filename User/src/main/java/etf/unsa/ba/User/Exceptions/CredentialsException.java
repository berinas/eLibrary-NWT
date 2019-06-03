package etf.unsa.ba.User.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CredentialsException extends RuntimeException{

	public CredentialsException(String exception) {
	    super(exception);
	  }
	
}
