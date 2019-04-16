package etf.unsa.ba.BookDetails;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface BookRegistrationSource {

	@Output("bookRegistrationChannel")
	MessageChannel bookRegistration();
}
