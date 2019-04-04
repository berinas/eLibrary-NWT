package etf.unsa.ba.BookDetails.Controllers;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.hibernate.annotations.common.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import etf.unsa.ba.BookDetails.Entities.Author;
import etf.unsa.ba.BookDetails.Entities.Book;
import etf.unsa.ba.BookDetails.Repositories.AuthorRepository;
import etf.unsa.ba.BookDetails.Repositories.BookRepository;
import javassist.NotFoundException;

@RestController
public class BookController {
	
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private AuthorRepository authorRepository;
	
	@GetMapping("/books")
	public List<Book> getAllAuthors() {
		return bookRepository.findAll();
	}

	@GetMapping("/books/{bookId}")
	public Book getBookById (@PathVariable Long bookId) throws NotFoundException {
		Optional<Book> bookOptional = bookRepository.findById(bookId);
		if (!bookOptional.isPresent()) 
			throw new NotFoundException("Book with given id not found");
		
		return bookOptional.get();
	}
	
	@PostMapping("/books/insert")
    public Book addBook(@RequestBody @Valid final Book book, Errors errors) throws Exception {

        if(errors.hasErrors()){
            throw new Exception(errors.getAllErrors().get(0).getDefaultMessage());
        }

        return bookRepository.save(book);
    }
	
	 @PutMapping("/books/update/{id}")
	    public Book updateBook(@PathVariable(value = "id") Long id, @RequestBody @Valid Book bookUpdate, Errors errors) throws NotFoundException, Exception {

	        if(errors.hasErrors()){
	            throw new Exception(errors.getAllErrors().get(0).getDefaultMessage());
	        }

	        Book book = bookRepository
	                .findById(id)
	                .orElseThrow(
	                        () -> new NotFoundException("Book with given id not found")
	                );
	        
	        book.setTitle(bookUpdate.getTitle());
	        book.setDescription(bookUpdate.getDescription());
	        book.setLanguage(bookUpdate.getLanguage());
	        book.setPublished(bookUpdate.getPublished());
	        
	        bookUpdate = bookRepository.save(book);
	        return bookUpdate;
	    }
	 
	 @DeleteMapping("/books/delete/{id}")
	    public ResponseEntity<?> deleteBook(@PathVariable(value = "id") Long id) throws NotFoundException {
	        Book book = bookRepository.findById(id)
	                .orElseThrow(() -> new NotFoundException("Book with given id not found"));

	        bookRepository.delete(book);

	        return ResponseEntity.ok().build();
	    }

	
}
