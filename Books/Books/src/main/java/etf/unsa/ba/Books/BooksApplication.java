package etf.unsa.ba.Books;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import etf.unsa.ba.Books.Entities.Book;
import etf.unsa.ba.Books.Entities.User;
import etf.unsa.ba.Books.Repositories.BookRepository;
import etf.unsa.ba.Books.Repositories.UserRepository;

@SpringBootApplication
@EnableAutoConfiguration
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
	
		userRepository.save(new User("User 1"));
		userRepository.save(new User("User 2"));
		userRepository.save(new User("User 3"));
		bookRepository.save(new Book("Book 1"));
		bookRepository.save(new Book("Book 2"));
		bookRepository.save(new Book("Book 3"));
		bookRepository.save(new Book("Book 4"));
		bookRepository.save(new Book("Book 5"));
				
		
	}
	
}
