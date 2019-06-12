package etf.unsa.ba.User.Services;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import etf.unsa.ba.User.Entities.User;
import etf.unsa.ba.User.Entities.UserAccount;
import etf.unsa.ba.User.Entities.UserLoginSource;
import etf.unsa.ba.User.Exceptions.CredentialsException;
import etf.unsa.ba.User.Exceptions.UserNotFoundException;
import etf.unsa.ba.User.Repositories.UserRepository;
import etf.unsa.ba.User.Repositories.UserRoleRepository;

@Service
@EnableBinding(UserLoginSource.class)
public class UserService {

	
	@Autowired
	UserRepository userRepository; 
	@Autowired 
	UserRoleRepository userRoleRepository;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
    UserLoginSource userLoginSource;
	
	
	@Autowired
	public UserService(UserRepository userRepository,
			 			UserRoleRepository userRoleRepository,
			 			BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository; 
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        
    }
	
	
    public List<User> findAll(){
        List<User> users = userRepository.findAll();
        return users;
    }
    
    
    public User findUserById(Long userId){
    	
    	Optional<User> userOptional = userRepository.findById(userId);
		
		if (!userOptional.isPresent()) 
			throw new UserNotFoundException("User not found --> id:" + userId);
		
		return userOptional.get();
		
    }
    
    
    public User findUserByEmail(String email){
    	
      	Optional<User> userOptional = userRepository.findByEmail(email);
		
		if (!userOptional.isPresent()) 
			throw new UserNotFoundException("User not found --> email:" + email);
		
		return userOptional.get();
    }
    
    public Iterable<User> findUsersByFirstName(String firstName) {
      	
    	Iterable<User> users = userRepository.findByFirstName(firstName);
		return users;
    }
    
	 public Iterable<User> findUsersByLastName(String lastName) {
	    	
      	Iterable<User> users = userRepository.findByLastName(lastName);
		return users;
	 }
	    
	 
    public User getUserByUsernameAndPassword(UserAccount userAccount) {
    	
    	Optional<User> userOptional = userRepository.findByUsername(userAccount.getUsername());
    	
    	if (!userOptional.isPresent()) 
			throw new UserNotFoundException("Username " + userAccount.getUsername() + " not found");
    	else {
    		if (bCryptPasswordEncoder.matches(userAccount.getPassword(), userOptional.get().getPassword()))
    			return userOptional.get();
    	}
    	return null; 
    }
    
    public User addUser(User user) {
    	
    	if(!isValidEmail(user.getEmail())) {
    		throw new CredentialsException("Please provide a valid email address.");
    	}
    	
    	if(!isValidName(user.getFirstName())){
    		throw new CredentialsException("First name should not contain numeric value.");
    	}
    	
    	if(!isValidName(user.getLastName())){
    		throw new CredentialsException("Last name should not contain numeric value.");
    	}
    	
    	user.setUserRole(userRoleRepository.getOne(2));
    	user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    	userRepository.save(user);
    	
    	return user; 
    }
    
    
    public User editUser(@PathVariable Long userId, @RequestBody User newUserInfo) {
    	
    	User user = userRepository.getOne(userId);
    	user.setFirstName(newUserInfo.getFirstName());
    	user.setLastName(newUserInfo.getLastName());
    	user.setEmail(newUserInfo.getEmail());
    	user.setUserRole(userRoleRepository.getOne(2));
    	user.setUsername(newUserInfo.getUsername());
    	user.setPassword(newUserInfo.getPassword());
    	userRepository.save(user);
    	return user;
    }
    
    public void deleteUser(Long userId) {
    	userRepository.deleteById(userId); 
    }
    
 
    public boolean isValidName(String name) {
    	return !name.matches(".*\\d.*");
    }
    
    public boolean isValidEmail(String email) {
    	String regex = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
    	Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    	Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


	public void saveAndSendUserInfo(String username, String password) {
		
		UserAccount acc = new UserAccount(username, password);
       userLoginSource.userLogin().send(MessageBuilder.withPayload(acc).setHeader("type", "USER").build());

	}

    
}
