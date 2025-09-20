package space.personalshowcase.blog.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import space.personalshowcase.blog.domain.entities.Category;
import space.personalshowcase.blog.repositories.CategoryRepository;
import space.personalshowcase.blog.services.CategoryService;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
	
	private final CategoryRepository categoryRepository;

	@Override
	public List<Category> listCategories() {
		return categoryRepository.findAllWithPostCount();
	}

	@Transactional
	@Override
	public Category createCategory(Category category) {
		
		if (categoryRepository.existsByNameIgnoreCase(category.getName())) {
			throw new IllegalArgumentException("Category already exist by name : "+category.getName());
		}
		
		return categoryRepository.save(category);
	}

	@Override
	@Transactional
	public void deleteCategory(UUID id) {
		
		Optional<Category> category =  categoryRepository.findById(id);
		
		if (category.isPresent()) {
			
			if (!category.get().getPosts().isEmpty()) {
				throw new IllegalStateException("Category has posts associated with it.");
			}
			
			categoryRepository.deleteById(id);
		}
		
	}

}
