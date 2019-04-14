package etf.unsa.ba.BookDetails.Controllers;

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
import etf.unsa.ba.BookDetails.Entities.Category;
import etf.unsa.ba.BookDetails.Repositories.CategoryRepository;
import javassist.NotFoundException;

@RestController
public class CategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping("/categories")
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}
	
	@GetMapping("/categories/{categoryId}")
	public Category getCategoryById (@PathVariable Long categoryId) throws NotFoundException {
		Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
		if (!categoryOptional.isPresent()) 
			throw new NotFoundException("Category with given id not found");
		
		return categoryOptional.get();
	}
	
	@PostMapping("/categories/insert")
    public Category addCategory(@RequestBody @Valid final Category category, Errors errors) throws Exception {

        if(errors.hasErrors()){
            throw new Exception(errors.getAllErrors().get(0).getDefaultMessage());
        }

        return categoryRepository.save(category);
    }
	
	 @PutMapping("/categories/update/{id}")
	    public Category updateCategory(@PathVariable(value = "id") Long id, @RequestBody @Valid Category categoryUpdate, Errors errors) throws NotFoundException, Exception {

	        if(errors.hasErrors()){
	            throw new Exception(errors.getAllErrors().get(0).getDefaultMessage());
	        }

	        Category category = categoryRepository
	                .findById(id)
	                .orElseThrow(
	                        () -> new NotFoundException("Category with given id not found")
	                );
	        
	        category.setCategory(categoryUpdate.getCategory());
	        
	        categoryUpdate = categoryRepository.save(category);
	        return categoryUpdate;
	    }
	 
	 @DeleteMapping("/categories/delete/{id}")
	    public ResponseEntity<?> deleteCategory(@PathVariable(value = "id") Long id) throws NotFoundException {
	        Category category = categoryRepository.findById(id)
	                .orElseThrow(() -> new NotFoundException("Category with given id not found"));

	        categoryRepository.delete(category);

	        return ResponseEntity.ok().build();
	    }
	    
	
}
