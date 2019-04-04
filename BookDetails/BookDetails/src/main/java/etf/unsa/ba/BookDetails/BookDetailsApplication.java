package etf.unsa.ba.BookDetails;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import etf.unsa.ba.BookDetails.Entities.Author;
import etf.unsa.ba.BookDetails.Entities.Book;
import etf.unsa.ba.BookDetails.Repositories.AuthorRepository;
import etf.unsa.ba.BookDetails.Repositories.BookDetailsRepository;

@SpringBootApplication
@EnableAutoConfiguration
public class BookDetailsApplication implements CommandLineRunner {

	@Autowired
	BookDetailsRepository bookDetailsRepository;
	@Autowired
	AuthorRepository authorRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BookDetailsApplication.class, args);
	}
	
	@Override
	public void run(String... arg0) throws Exception {
	
		bookDetailsRepository.save(new Book("title", "desc", "eng", new Date()));
		authorRepository.save(new Author("autor1", "autor11"));
		
	}

	

}
