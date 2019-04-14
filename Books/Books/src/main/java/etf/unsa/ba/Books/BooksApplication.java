package etf.unsa.ba.Books;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import etf.unsa.ba.Books.Entities.Book;
import etf.unsa.ba.Books.Entities.User;
import etf.unsa.ba.Books.Entities.UserAccount;
import etf.unsa.ba.Books.Repositories.BookRepository;
import etf.unsa.ba.Books.Repositories.UserRepository;

@SpringBootApplication
@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableFeignClients
public class BooksApplication implements CommandLineRunner {

	@Autowired 
	UserRepository userRepository; 
	@Autowired
	BookRepository bookRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(BooksApplication.class, args);
	}
	
	
	@Override
	public void run(String... arg0) throws Exception {
	
		userRepository.save(new User("User 1", "fdsf")); // ovo cemo kasnije dobavljati iz User serisa
		userRepository.save(new User("User 2", "fsfsd"));
		userRepository.save(new User("User 3", "sdffsd"));
		bookRepository.save(new Book("Book 1"));
		bookRepository.save(new Book("Book 2"));
		bookRepository.save(new Book("Book 3"));
		bookRepository.save(new Book("Book 4"));
		bookRepository.save(new Book("Book 5"));
				
		
	}
	  @Bean
	    @LoadBalanced
	    public RestTemplate restTemplate() {
	        return new RestTemplate();
	    }
		
}
