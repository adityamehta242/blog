package space.personalshowcase.blog.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import space.personalshowcase.blog.domain.dtos.CategoryDTO;
import space.personalshowcase.blog.mappers.CategoryMapper;
import space.personalshowcase.blog.services.CategoryService;

@RestController
@RequestMapping(path = "/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

	private final CategoryService categoryService;
	private final CategoryMapper categoryMapper;
	
	@GetMapping
	public ResponseEntity<List<CategoryDTO>> listCategories(){
		
		List<CategoryDTO> categories = categoryService.listCategories()
				.stream().map(categoryMapper::toDto)
				.toList();
		return ResponseEntity.ok(categories);
	}
}
