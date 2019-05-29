package etf.unsa.ba.Books.Services;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import etf.unsa.ba.Books.Entities.BookDetails;
import etf.unsa.ba.Books.Entities.UserAccount;
import etf.unsa.ba.Books.Entities.UserInfo;

//@FeignClient(name="netflix-zuul-api-gateway-server")
@FeignClient(name="user-service")
@RibbonClient(name = "user-service")
public interface ServiceProxy {

	//@PostMapping("/user-service/users/login")
	@PostMapping("/users/login")
	public UserInfo getUserByUsernameAndPassword(@RequestBody UserAccount userAccount);	
}
