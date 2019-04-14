package etf.unsa.ba.Books.Controllers;

import java.awt.PageAttributes.MediaType;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.discovery.EurekaClient;
import com.netflix.ribbon.proxy.annotation.Http.HttpMethod;

import etf.unsa.ba.Books.Entities.Book;
import etf.unsa.ba.Books.Entities.BookDetails;
import etf.unsa.ba.Books.Entities.User;
import etf.unsa.ba.Books.Entities.UserAccount;
import etf.unsa.ba.Books.Entities.UserInfo;
import etf.unsa.ba.Books.Repositories.BookRepository;

@RestController
public class UserController {

	@Autowired
	private BookRepository bookRepository;
	
	
	@Autowired
	private DiscoveryClient discoveryClient;
	@Autowired 
	private EurekaClient eurekaClient;
	@Autowired
    RestTemplate restTemplate;
	@Autowired
	private BookDetailsServiceProxy bookDetailsServiceProxy; 
	@Autowired
	private UserServiceProxy userServiceProxy; 
  
	
	 @RequestMapping("/service-instances/{applicationName}")
	    public List<ServiceInstance> serviceInstancesByApplicationName(
	            @PathVariable String applicationName) {
	        return this.discoveryClient.getInstances(applicationName);
	    }
	 

		@GetMapping("/test")
		public BookDetails proba() {
			return bookDetailsServiceProxy.test();
			//String result = restTemplate.getForObject("http://book-details-service/test", String.class);
			
			//return result; 
		}
		

		@PostMapping("/users/login")
		public UserInfo getUserByUsernameAndPassword(@RequestBody UserAccount userAccount) {
			return userServiceProxy.getUserByUsernameAndPassword(userAccount);
		}
		
	 
		@GetMapping("/books/{bookId}")
		public BookDetails getBookDetails(@PathVariable Long bookId) {
			return bookDetailsServiceProxy.getBookDetails(bookId);
			//String result = restTemplate.getForObject("http://book-details-service/test", String.class);
			
			//return result; 
		}
		
		
	 /*
	
	 @RequestMapping(value = "/proba", method = RequestMethod.GET)
	 public String getEmployee() {
		
		 ResponseEntity<String> response = restTemplate.exchange("http://book-details-service/proba", HttpMethod.GET, null, String.class);
		 String result = response.getBody();
		 }
	 */
	 
		/*
	@GetMapping("/users/{userId}/wishlist")
	public List<Book> getWishList(@PathVariable Long userId){
		
		Optional<UserAccount> userOptional = userAccountRepository.findById(userId);
		
		if (!userOptional.isPresent()) 
			//throw new UserNotFoundException("id:" + knjigaId);
			return null;
		
		return userOptional.get().getBooksWishList();
		
	}
	
	
	@PostMapping("/users/{userId}/wishlist/{bookId}")
	public void addBookToWishList(@PathVariable("userId") Long userId, @PathVariable("bookId") Long bookId){
		
		Optional<UserAccount> userOptional = userAccountRepository.findById(userId);
		
		//if (!userOptional.isPresent()) 
			//throw new UserNotFoundException("id:" + knjigaId);
			//return null;
		
		UserAccount user = userOptional.get();
		
		Optional<Book> bookOptional = bookRepository.findById(bookId);
		Book book = bookOptional.get();
		
		book.getUsersWishList().add(user);
		
		user.getBooksWishList().add(book);
		
		bookRepository.save(book);
		
	}
	*/
	
	
}
