package etf.unsa.ba.Books;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import etf.unsa.ba.Books.Entities.Book;
import etf.unsa.ba.Books.Entities.User;
import etf.unsa.ba.Books.Repositories.BookRepository;
import etf.unsa.ba.Books.Repositories.UserRepository;
import etf.unsa.ba.Books.Services.UserService;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

@SpringBootApplication
@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableFeignClients
@EnableBinding(Sink.class)
public class BooksApplication implements CommandLineRunner {

	@Autowired 
	UserRepository userRepository; 
	@Autowired
	BookRepository bookRepository;
	@Autowired
	UserService userService;
	
	
	public static void main(String[] args) {
		SpringApplication.run(BooksApplication.class, args);
	}
	
	
	@Override
	public void run(String... arg0) throws Exception {
	
		userRepository.save(new User("User 1", "aaaaa"));
		userRepository.save(new User("User 2", "bbbbb"));
		userRepository.save(new User("User 3", "ccccc"));
		bookRepository.save(new Book("History Book", "Description", "https://images-na.ssl-images-amazon.com/images/I/517OihiR00L._SX378_BO1,204,203,200_.jpg"));
		bookRepository.save(new Book("Science", "Description", "https://images-na.ssl-images-amazon.com/images/I/91ZWb4eerRL.jpg"));
		bookRepository.save(new Book("Biology", "Description", "https://prodimage.images-bn.com/pimages/9781454910688_p0_v2_s550x406.jpg"));
		bookRepository.save(new Book("A Diamond in my pocket", "Description", "https://m.media-amazon.com/images/I/91ALtDFdILL._AC_UL654_FMwebp_QL65_.jpg"));
	}
	
	@StreamListener(target = Sink.INPUT)
	public void processRegisterBooks(Message<String> message) throws IOException{
		
		if(message.getHeaders().get("type").toString().equals("USER")) {
			
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(message.getPayload());
			String username = node.get("username").asText();
			String password = node.get("password").asText();
			userService.userLogin(username,password);
			System.out.println("User is logged in: " + username + " " + password);
			
		}
		else if (message.getHeaders().get("type").toString().equals("BOOK")) {

			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(message.getPayload());
			String title = node.get("title").asText();
			bookRepository.save(new Book(title, "Description", ""));
			System.out.println("Book Registered by Client: " + title);
		}
		
	}
		
}
