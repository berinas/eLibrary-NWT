package etf.unsa.ba.BookDetails.Controllers;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import etf.unsa.ba.BookDetails.Entities.Author;
import etf.unsa.ba.BookDetails.Entities.Book;
import etf.unsa.ba.BookDetails.Entities.Category.BookCategory;
import etf.unsa.ba.BookDetails.Entities.Section.SectionType;
import etf.unsa.ba.BookDetails.Repositories.AuthorRepository;
import etf.unsa.ba.BookDetails.Repositories.BookRepository;
import etf.unsa.ba.BookDetails.Services.BookService;
import javassist.NotFoundException;

@RestController
public class BookController {
	
	
	@Autowired
	private BookService bookService;
	
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	
	@GetMapping("/books")
	public List<Book> getAllBooks() {
		return bookService.findAll();
	}

	@GetMapping("/books/{bookId}")
	public Book getBookById(@PathVariable Long bookId){
		return bookService.findBookById(bookId);		
	}
	
	@GetMapping("/books/title/{title}")
	public List<Book> getBookByTitle(@PathVariable String title){
		return bookService.findBookByTitle(title);		
	}
	
	@GetMapping("/books/section/{section}")
	public List<Book> getBookBySection(@PathVariable SectionType section){
		return bookService.findBooksBySection(section);		
	}
	
	@GetMapping("/books/category/{category}")
	public List<Book> getBookByCategory(@PathVariable BookCategory category){
		return bookService.findBooksByCategory(category);		
	}
	
	@GetMapping("/books/{bookId}/authors")
	public List<Author> getBookAuthors(@PathVariable Long bookId){
		return bookService.findBookAuthors(bookId);		
	}
	
	@PostMapping("/books/insert")
    public Book addBook(@RequestBody @Valid final Book book, Errors errors) throws Exception {

        if(errors.hasErrors()){
            throw new Exception(errors.getAllErrors().get(0).getDefaultMessage());
        }
        return bookService.addBook(book);
    }
	
	 @PutMapping("/books/update/{id}")
	    public Book updateBook(@PathVariable(value = "id") Long id, @RequestBody @Valid Book bookUpdate, Errors errors) throws NotFoundException, Exception {
		 if(errors.hasErrors()){
	            throw new Exception(errors.getAllErrors().get(0).getDefaultMessage());
	        }	
		 return bookService.updateBook(id, bookUpdate);
	    }
	 
	 @DeleteMapping("/books/delete/{id}")
	    public ResponseEntity<?> deleteBook(@PathVariable(value = "id") Long id) throws NotFoundException {
	        return bookService.deleteBook(id);
	    }

}
