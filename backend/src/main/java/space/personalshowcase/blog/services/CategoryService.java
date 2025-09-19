package space.personalshowcase.blog.services;

import java.util.List;

import space.personalshowcase.blog.domain.entities.Category;


public interface CategoryService {
	List<Category> listCategories();
	
	Category createCategory(Category category);
}
