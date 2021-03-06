package etf.unsa.ba.BookDetails.Services;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import etf.unsa.ba.BookDetails.Entities.Author;
import etf.unsa.ba.BookDetails.Entities.Book;
import etf.unsa.ba.BookDetails.Entities.BookContent;
import etf.unsa.ba.BookDetails.Entities.Category;
import etf.unsa.ba.BookDetails.Entities.Category.BookCategory;
import etf.unsa.ba.BookDetails.Entities.Publisher;
import etf.unsa.ba.BookDetails.Entities.Section;
import etf.unsa.ba.BookDetails.Entities.Section.SectionType;
import etf.unsa.ba.BookDetails.Exceptions.EntryNotFoundException;
import etf.unsa.ba.BookDetails.Repositories.AuthorRepository;
import etf.unsa.ba.BookDetails.Repositories.BookContentRepository;
import etf.unsa.ba.BookDetails.Repositories.BookRepository;
import etf.unsa.ba.BookDetails.Repositories.CategoryRepository;
import etf.unsa.ba.BookDetails.Repositories.PublisherRepository;
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
	PublisherRepository publisherRepository;
	@Autowired
	BookContentRepository bookContentRepository;
	@Autowired
	GoogleDriveService driveService;
	
	@Autowired
	public BookService(BookRepository bookRepository,
					   AuthorRepository authorRepository,
					   SectionRepository sectionRepository,
					   CategoryRepository categoryRepository,
					   PublisherRepository publisherRepository,
					   BookContentRepository bookContentRepository) {
			this.bookRepository = bookRepository;
			this.authorRepository = authorRepository;
			this.sectionRepository = sectionRepository;
			this.categoryRepository = categoryRepository; 
			this.publisherRepository = publisherRepository; 
			this.bookContentRepository = bookContentRepository; 
	}
	
	
	public List<Book> findAll(){
		return bookRepository.findAll();
	}
	
	
	public Book findBookById(Long id) {
		
		Optional<Book> optionalBook = bookRepository.findById(id);
		
		if (!optionalBook.isPresent()) 
			throw new EntryNotFoundException("Book with given id not found");
		
		System.out.println("**************BOOK CONTENT: " + optionalBook.get().getCategory().getCategory());
		
		return optionalBook.get();
	}
	
	public List<Book> findBookByTitle(String title) {
		
		List<Book> books = bookRepository.findByTitle(title);
		return books;
	}
	
	public List<Book> findBooksBySection(SectionType section){
		
		Section sectionOptional = sectionRepository.findBySection(section);
		if(sectionOptional == null) 
			throw new EntryNotFoundException("Section does not exists.");
		
		return sectionOptional.getBooks();
	}
	

	public List<Book> findBooksByCategory(BookCategory category) {
		
		Category categoryOptional = categoryRepository.findByCategory(category);
		 if (categoryOptional == null) 
			throw new EntryNotFoundException("Category does not exists.");
		
		return categoryOptional.getBooks();
	}

	
	public List<Author> findBookAuthors(Long bookId){
		
		Optional<Book> optionalBook = bookRepository.findById(bookId);
		if (!optionalBook.isPresent()) 
			throw new EntryNotFoundException("Book with given id not found");
		
		return optionalBook.get().getAuthors();
		
	}
	
	public Book addBook(Book book, List<Author> authors, 
						Section section,Category category, 
						Publisher publisher) {
		
		//vrsta i kategorija se ne unose rucno nego se izaberu neke od vec postojecih iz baze
		book.setSection(sectionRepository.findBySection(section.getSection()));
		book.setCategory(categoryRepository.findByCategory(category.getCategory()));
		
		Optional<Publisher> publisherOptional = publisherRepository.findByName(publisher.getName());
		if(!publisherOptional.isPresent())
			publisherRepository.save(publisher);

		book.setPublisher(publisherOptional.get());
		book.setAuthors(authors);
		
		BookContent bookContent = new BookContent(book);
		book.setBookContent(bookContent);
		Book saved = bookRepository.save(book);
		bookContentRepository.save(bookContent);
		
		//File file = new File("C:\\Users\\PC\\Desktop\\pz.pdf");
		//driveService.upLoadFile(saved.getId(), file.getAbsolutePath(),"application/pdf");
		
		
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

	public ResponseEntity<?> deleteAllBooks() throws NotFoundException{
			
		try { 
			bookRepository.deleteAll();
		}
		catch(Exception e) {
			throw new NotFoundException("Book with given id not found");
		} 
        return ResponseEntity.ok().build();
	}


}
