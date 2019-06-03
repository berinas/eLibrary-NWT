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
import etf.unsa.ba.Books.Services.BookDetailsProxy;
import etf.unsa.ba.Books.Services.ServiceProxy;
import etf.unsa.ba.Books.Services.UserService;

@RestController
public class UserController {

	private User user = new User(); 
	@Autowired
    private UserService userService;
	@Autowired
	private ServiceProxy serviceProxy;
	@Autowired
	private BookDetailsProxy bookDetailsProxy;
 

	public UserController(UserService userService){
        this.userService = userService;
    }
	
	@PostMapping("/users/login")
	public UserInfo getUserByUsernameAndPassword(@RequestBody UserAccount userAccount) {
		UserInfo userInfo = serviceProxy.getUserByUsernameAndPassword(userAccount);
		user.setUsername(userInfo.getUsername());
		user.setPassword(userInfo.getPassword());
		System.out.println("books-service -> User is logged in");
		return userInfo; 
	}
		
	@GetMapping("/all-books")
	public List<Book> getAllTitles(){
		return userService.getAllTitles();
	}
	
	@GetMapping("/user/wishlist")
	public List<Book> getWishList(){
		return user.getBooksWishList();
	}
	
	@GetMapping("/user/reading")
	public List<Book> getReadingList(){
		return user.getBooksReading();
	}
	
	@GetMapping("/user/read")
	public List<Book> getReadList(){
		return user.getBooksRead();
	}
	
	@GetMapping("/books/{bookId}")
	public BookDetails getBookDetails(@PathVariable("bookId") Long bookId){
		BookDetails bookDetails = bookDetailsProxy.getBookDetailsById(bookId);
		System.out.println(bookDetails.getPublisher().getName());
		return 	bookDetails;
	}
	
	@GetMapping("/user/wishlist/{bookId}")
	public BookDetails getBookDetailsFromWishList(@PathVariable("bookId") Long bookId){
		return bookDetailsProxy.getBookDetailsById(bookId);
	}
	
	@PostMapping("/user/wishlist/{bookId}")
	public Book addBookToWishList(@PathVariable("bookId") Long bookId){
		return userService.addBookToWishList(bookId, user);		
	}
	
	@PostMapping("/user/reading/{bookId}")
	public Book addBookToReadingList(@PathVariable("bookId") Long bookId){
		return userService.addBookToReadingList(bookId, user);		
	}
	
	@PostMapping("/user/read/{bookId}")
	public Book addBookToReadList(@PathVariable("bookId") Long bookId){
		return userService.addBookToReadList(bookId, user);		
	}
	
}