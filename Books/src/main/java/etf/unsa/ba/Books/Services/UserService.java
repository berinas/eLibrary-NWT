package etf.unsa.ba.Books.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import etf.unsa.ba.Books.Entities.Book;
import etf.unsa.ba.Books.Entities.User;
import etf.unsa.ba.Books.Repositories.BookRepository;
import etf.unsa.ba.Books.Repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	BookRepository bookRepository;
	User user = new User();
	
	@Autowired 
	public UserService(UserRepository userRepository,
					   BookRepository bookRepository){
		this.userRepository = userRepository;
		this.bookRepository = bookRepository;
	}
	
	
	
	public Book addBookToWishList(Long bookId, User user) {
		
		Optional<Book> bookOptional = bookRepository.findById(bookId);
		
		//if(!bookOptional.isPresent())
		
		Book book = bookOptional.get();
		book.getUsersWishList().add(user);
		user.getBooksWishList().add(book);
		
		return bookRepository.save(book);
	}



	public Book addBookToReadingList(Long bookId, User user) {
		
		Optional<Book> bookOptional = bookRepository.findById(bookId);
		
		Book book = bookOptional.get();
		book.getUsersReading().add(user);
		user.getBooksReading().add(book);
		
		return bookRepository.save(book);
		
	}

	public Book addBookToReadList(Long bookId, User user) {
		
		Optional<Book> bookOptional = bookRepository.findById(bookId);
		
		Book book = bookOptional.get();
		book.getUsersRead().add(user);
		user.getBooksRead().add(book);
		
		return bookRepository.save(book);
	}
	
	public List<Book> getAllTitles(){
		return bookRepository.findAll();
	}
	
	public void userLogin(String username, String password) {
		user.setUsername(username);
		user.setPassword(password);
	}
	
}
