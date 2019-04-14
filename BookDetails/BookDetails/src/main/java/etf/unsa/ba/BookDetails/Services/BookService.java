package etf.unsa.ba.BookDetails.Services;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import etf.unsa.ba.BookDetails.Entities.Author;
import etf.unsa.ba.BookDetails.Entities.Book;
import etf.unsa.ba.BookDetails.Entities.BookContent;
import etf.unsa.ba.BookDetails.Entities.Category;
import etf.unsa.ba.BookDetails.Entities.Category.BookCategory;
import etf.unsa.ba.BookDetails.Entities.Section;
import etf.unsa.ba.BookDetails.Entities.Section.SectionType;
import etf.unsa.ba.BookDetails.Exceptions.EntryNotFoundException;
import etf.unsa.ba.BookDetails.Repositories.AuthorRepository;
import etf.unsa.ba.BookDetails.Repositories.BookContentRepository;
import etf.unsa.ba.BookDetails.Repositories.BookRepository;
import etf.unsa.ba.BookDetails.Repositories.CategoryRepository;
import etf.unsa.ba.BookDetails.Repositories.SectionRepository;
import javassist.NotFoundException;

@Service
public class BookService {

	@Autowired
	BookRepository bookRepository;
	@Autowired 
	AuthorRepository authorRepository;
	@Autowired
	SectionRepository sectionRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	BookContentRepository bookContentRepository;
	@Autowired
	GoogleDriveService googleDriveService;
	
	@Autowired
	public BookService(BookRepository bookRepository,
					   AuthorRepository authorRepository,
					   SectionRepository sectionRepository,
					   CategoryRepository categoryRepository,
					   BookContentRepository bookContentRepository
					   ) {
		this.bookRepository = bookRepository;
		this.authorRepository = authorRepository;
		this.sectionRepository = sectionRepository;
		this.categoryRepository = categoryRepository; 
		this.bookContentRepository = bookContentRepository; 
		//this.googleDriveService = googleDriveService;
	}
	
	
	public List<Book> findAll(){
		return bookRepository.findAll();
	}
	
	
	public Book findBookById(Long id) {
		
		Optional<Book> optionalBook = bookRepository.findById(id);
		
		if (!optionalBook.isPresent()) 
			throw new EntryNotFoundException("Book with given id not found");
	
		return optionalBook.get();
	}
	
	public List<Book> findBookByTitle(String title) {
		
		List<Book> books = bookRepository.findByTitle(title);
		return books;
	}
	
	public List<Book> findBooksBySection(SectionType section){
		
		Optional<Section> sectionOptional = sectionRepository.findBySection(section);
		if (!sectionOptional.isPresent()) 
			throw new EntryNotFoundException("Section does not exists.");
		
		return sectionOptional.get().getBooks();
	}
	

	public List<Book> findBooksByCategory(BookCategory category) {
		
		Optional<Category> categoryOptional = categoryRepository.findByCategory(category);
		if (!categoryOptional.isPresent()) 
			throw new EntryNotFoundException("Category does not exists.");
		
		return categoryOptional.get().getBooks();
	}

	
	public List<Author> findBookAuthors(Long bookId){
		
		Optional<Book> optionalBook = bookRepository.findById(bookId);
		if (!optionalBook.isPresent()) 
			throw new EntryNotFoundException("Book with given id not found");
		
		return optionalBook.get().getAuthors();
		
	}
	
	public Book addBook(Book book) {
	
		//File file = new File("C:\\Users\\PC\\Desktop\\Projektni zadatak.pdf");
		//com.google.api.services.drive.model.File file2  = googleDriveService.upLoadFile(book.getId(), file.getAbsolutePath(),"application/pdf");
		/*try {
			System.err.println(file2.toPrettyString());
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	
		BookContent bookContent = new BookContent(book);
		book.setBookContent(bookContent);
		Book saved = bookRepository.save(book);
		bookContentRepository.save(bookContent);
		return saved;
	
	}
	
	public Book updateBook(Long id, Book bookUpdate) throws NotFoundException{
		

        Book book = bookRepository.findById(id)
        						  .orElseThrow(
        						  () -> new NotFoundException("Book with given id not found"));
       
        book.setTitle(bookUpdate.getTitle());
        book.setDescription(bookUpdate.getDescription());
        book.setLanguage(bookUpdate.getLanguage());
        book.setPublished(bookUpdate.getPublished());
        
        return bookRepository.save(book);
	}

	public ResponseEntity<?> deleteBook(Long id) throws NotFoundException{
		
		Book book = bookRepository.findById(id)
								   .orElseThrow(() 
								   -> new NotFoundException("Book with given id not found"));

        bookRepository.delete(book);
        return ResponseEntity.ok().build();
	}


	
}
