package etf.unsa.ba.User.Controllers;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import etf.unsa.ba.User.Entities.User;
import etf.unsa.ba.User.Repositories.UserRepository;

@RestController	
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	
	@GetMapping("/users")
	public Collection<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	@GetMapping("/users/{userId}")
	public User getUserById(@PathVariable Long userId) {
		
		Optional<User> userOptional = userRepository.findById(userId);
		
		if (!userOptional.isPresent()) 
			return null;
			//throw new UserNotFoundException("id:" + userID);
		
		return userOptional.get();
		
	}
	
}
