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
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

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
	
	
	public static void main(String[] args) {
		SpringApplication.run(BooksApplication.class, args);
	}
	
	
	@Override
	public void run(String... arg0) throws Exception {
	
		userRepository.save(new User("User 1", "aaaaa")); // ovo cemo kasnije dobavljati iz User serisa
		userRepository.save(new User("User 2", "bbbbb"));
		userRepository.save(new User("User 3", "ccccc"));
		bookRepository.save(new Book("Book 1"));
		bookRepository.save(new Book("Book 2"));
	}
	
	@StreamListener(target = Sink.INPUT)
	public void processRegisterBooks(String book) throws IOException{
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(book);
		String title = node.get("title").asText();
		bookRepository.save(new Book(title));
		System.out.println("Book Registered by Client: " + title);
	}
		
}
