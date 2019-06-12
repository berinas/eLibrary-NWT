package etf.unsa.ba.User.Entities;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface UserLoginSource {
	@Output("userLoginChannel")
	MessageChannel userLogin();
}
