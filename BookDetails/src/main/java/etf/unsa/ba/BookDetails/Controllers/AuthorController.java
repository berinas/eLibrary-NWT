package etf.unsa.ba.BookDetails.Controllers;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import etf.unsa.ba.BookDetails.Entities.Author;
import etf.unsa.ba.BookDetails.Entities.Book;
import etf.unsa.ba.BookDetails.Repositories.AuthorRepository;
import etf.unsa.ba.BookDetails.Repositories.BookRepository;
import javassist.NotFoundException;

@RestController
public class AuthorController {
	
	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private BookRepository bookRepository;
	
	@GetMapping("/authors")
	public List<Author> getAllAuthors() {
		return authorRepository.findAll();
	}
	
	@GetMapping("/authors/{authorId}")
	public Author getAuthorById (@PathVariable Integer authorId) throws NotFoundException {
		Optional<Author> authorOptional = authorRepository.findById(authorId);
		if (!authorOptional.isPresent()) 
			throw new NotFoundException("Author with given id not found");
		
		return authorOptional.get();
	}
	
	@GetMapping("/authors/{authorId}/allBooks")
	public Collection<Book> getAllBooksByAuthorId(@PathVariable Integer authorId) throws NotFoundException {
		Optional<Author> authorOptional = authorRepository.findById(authorId);
		if (!authorOptional.isPresent()) 
			throw new NotFoundException("Author with given id not found");
			
		return authorOptional.get().getBooks();
	}
	
	@PostMapping("/authors/insert")
    public Author addAuthor(@RequestBody @Valid final Author author, Errors errors) throws Exception {

        if(errors.hasErrors()){
            throw new Exception(errors.getAllErrors().get(0).getDefaultMessage());
        }

        return authorRepository.save(author);
    }
	
	 @PutMapping("/authors/update/{id}")
	    public Author updateAuthor(@PathVariable(value = "id") Integer id, @RequestBody @Valid Author authorUpdate, Errors errors) throws NotFoundException, Exception {

	        if(errors.hasErrors()){
	            throw new Exception(errors.getAllErrors().get(0).getDefaultMessage());
	        }

	        Author author = authorRepository
	                .findById(id)
	                .orElseThrow(
	                        () -> new NotFoundException("Author with given id not found")
	                );
	        
	        author.setFirstName(authorUpdate.getFirstName());
	        author.setLastName(authorUpdate.getLastName());
	        
	        authorUpdate = authorRepository.save(author);
	        return authorUpdate;
	    }
	 
	 @DeleteMapping("/authors/delete/{id}")
	    public ResponseEntity<?> deleteAuthor(@PathVariable(value = "id") Integer id) throws NotFoundException {
	        Author author = authorRepository.findById(id)
	                .orElseThrow(() -> new NotFoundException("Author with given id not found"));

	        authorRepository.delete(author);

	        return ResponseEntity.ok().build();
	    }
	    
	
}
