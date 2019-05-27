package etf.unsa.ba.Books.Services;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import etf.unsa.ba.Books.Entities.BookDetails;

@FeignClient(name="book-details-service")
public interface BookDetailsProxy {

	@GetMapping("/books/{bookId}")
	public BookDetails getBookDetailsById(@PathVariable Long bookId);
}
