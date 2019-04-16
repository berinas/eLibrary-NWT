package etf.unsa.ba.Books;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.junit4.SpringRunner;

import etf.unsa.ba.Books.Entities.Book;
import etf.unsa.ba.Books.Entities.BookDetails;
import etf.unsa.ba.Books.Entities.Publisher;
import etf.unsa.ba.Books.Repositories.BookRepository;
import etf.unsa.ba.Books.Services.BookDetailsServiceProxy;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootApplication
public class TestCommunication {


	@Autowired
	BookDetailsServiceProxy bookDetailsServiceProxy;
	@Autowired
	BookRepository bookRepository;
	
	
	@Test
	public void getBookDetails() {
		
		BookDetails bookDetails1 = new BookDetails("History Book","","eng",new Date(),new Publisher((long)1, "izdavac"));
		BookDetails bookDetails2 = bookDetailsServiceProxy.getBookDetailsById((long)1);
		
		assertThat(bookDetails1.getTitle()).isEqualTo(bookDetails2.getTitle());
	
		
	}
	
}
