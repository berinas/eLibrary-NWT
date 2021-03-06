package etf.unsa.ba.BookDetails;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import etf.unsa.ba.BookDetails.Entities.Author;
import etf.unsa.ba.BookDetails.Entities.Book;
import etf.unsa.ba.BookDetails.Entities.Category;
import etf.unsa.ba.BookDetails.Entities.Category.BookCategory;
import etf.unsa.ba.BookDetails.Entities.Section;
import etf.unsa.ba.BookDetails.Entities.Section.SectionType;
import etf.unsa.ba.BookDetails.Repositories.AuthorRepository;
import etf.unsa.ba.BookDetails.Repositories.BookRepository;
import etf.unsa.ba.BookDetails.Repositories.CategoryRepository;
import etf.unsa.ba.BookDetails.Repositories.SectionRepository;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories
@EntityScan
@EnableDiscoveryClient
public class BookDetailsApplication implements CommandLineRunner {

	@Autowired
	BookRepository bookRepository;
	@Autowired
	AuthorRepository authorRepository;
	@Autowired
	SectionRepository sectionRepository;
	@Autowired
	CategoryRepository categoryRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BookDetailsApplication.class, args);
	}
	
	@Override
	public void run(String... arg0) throws Exception {
	
		categoryRepository.save(new Category(BookCategory.SCIENCE));
		categoryRepository.save(new Category(BookCategory.HISTORY));
		categoryRepository.save(new Category(BookCategory.MEDICINE));
		categoryRepository.save(new Category(BookCategory.FANTASY));
		categoryRepository.save(new Category(BookCategory.ART));
		categoryRepository.save(new Category(BookCategory.ROMANCE));
		categoryRepository.save(new Category(BookCategory.MUSIC));
		categoryRepository.save(new Category(BookCategory.CHILDREN));
		categoryRepository.save(new Category(BookCategory.RELIGION));
	
		sectionRepository.save(new Section(SectionType.BOOK));
		sectionRepository.save(new Section(SectionType.MAGAZINE));
		sectionRepository.save(new Section(SectionType.ARTICLE));
		
		/*Book book = new Book("History Book", "desc", "eng", new Date());
		Category category = categoryRepository.findByCategory(BookCategory.HISTORY);
		Section section = sectionRepository.findBySection(SectionType.BOOK);
		
		book.setCategory(category);
		book.setSection(section);
		bookRepository.save(book);
		Author author1 = new Author("Author1", "Author11");
		Author author2 = new Author("Author2", "Author22");
		
		List<Author> authors = new ArrayList<Author>();
		List<Book> books = new ArrayList<Book>();
		
		authors.add(author1);
		authors.add(author2);
		
		books.add(book);
		
		book.setAuthors(authors);
		author1.setBooks(books);
		author2.setBooks(books);
		
		bookRepository.save(book);
		authorRepository.save(author1);
		authorRepository.save(author2); */
		
	}
	
	
	@Bean
    @Primary
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource("application.yml"));
        propertySourcesPlaceholderConfigurer.setProperties(yaml.getObject());
        return propertySourcesPlaceholderConfigurer;
    }
}
