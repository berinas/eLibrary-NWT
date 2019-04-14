package etf.unsa.ba.Books.Controllers;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import etf.unsa.ba.Books.Entities.BookDetails;


@FeignClient(name="book-details-service" )
public interface BookDetailsServiceProxy {

	@RequestMapping("/books/{bookId}")
	public BookDetails getBookDetails(@PathVariable(value="bookId") Long bookId);
	@RequestMapping("/test")
	public BookDetails test();
	
}
