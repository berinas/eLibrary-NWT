package etf.unsa.ba.Books.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import etf.unsa.ba.Books.Entities.Book;
import etf.unsa.ba.Books.Entities.User;
import etf.unsa.ba.Books.Repositories.BookRepository;
import etf.unsa.ba.Books.Repositories.UserRepository;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BookRepository bookRepository;
	
	
	@GetMapping("/users/{userId}/wishlist")
	public List<Book> getWishList(@PathVariable Long userId){
		
		Optional<User> userOptional = userRepository.findById(userId);
		
		if (!userOptional.isPresent()) 
			//throw new UserNotFoundException("id:" + knjigaId);
			return null;
		
		return userOptional.get().getBooksWishList();
		
	}
	
	
	@PostMapping("/users/{userId}/wishlist/{bookId}")
	public void addBookToWishList(@PathVariable("userId") Long userId, @PathVariable("bookId") Long bookId){
		
		Optional<User> userOptional = userRepository.findById(userId);
		
		//if (!userOptional.isPresent()) 
			//throw new UserNotFoundException("id:" + knjigaId);
			//return null;
		
		User user = userOptional.get();
		
		Optional<Book> bookOptional = bookRepository.findById(bookId);
		Book book = bookOptional.get();
		
		book.getUsersWishList().add(user);
		
		user.getBooksWishList().add(book);
		
		bookRepository.save(book);
		
	}
	
	
}
