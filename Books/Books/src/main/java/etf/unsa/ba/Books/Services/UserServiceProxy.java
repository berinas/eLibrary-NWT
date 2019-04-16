package etf.unsa.ba.Books.Services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import etf.unsa.ba.Books.Entities.UserAccount;
import etf.unsa.ba.Books.Entities.UserInfo;

@FeignClient(name="user-service" )
public interface UserServiceProxy {

	@PostMapping("/users/login")
	public UserInfo getUserByUsernameAndPassword(@RequestBody UserAccount userAccount);
	//@RequestMapping("/users/test")
	//public List<String> test();
	
	
}
