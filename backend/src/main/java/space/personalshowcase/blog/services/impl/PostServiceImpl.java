package space.personalshowcase.blog.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import space.personalshowcase.blog.domain.PostStatus;
import space.personalshowcase.blog.domain.entities.Category;
import space.personalshowcase.blog.domain.entities.Post;
import space.personalshowcase.blog.domain.entities.Tag;
import space.personalshowcase.blog.repositories.PostRepository;
import space.personalshowcase.blog.services.CategoryService;
import space.personalshowcase.blog.services.PostService;
import space.personalshowcase.blog.services.TagService;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
	
	private final PostRepository postRepository;
	private final CategoryService categoryService;
	private final TagService tagService;

	@Override
	@Transactional(readOnly = true)
	public List<Post> getAllPosts(UUID categoryId, UUID tagId) {
		
		if (categoryId != null && tagId != null) {
			Category category = categoryService.getCategoryById(categoryId);
			Tag tag = tagService.getTagById(tagId);
			
			return postRepository.findAllByStatusAndCategoryAndTagsContaining(PostStatus.PUBLISHED, category, tag);
		}
		
		
		if(categoryId != null) {
			Category category = categoryService.getCategoryById(categoryId);
			return postRepository.findAllByStatusAndCategory(PostStatus.PUBLISHED, category);
		}
		
		
		if(tagId != null) {
			Tag tag = tagService.getTagById(tagId);
			return postRepository.findAllByStatusAndTags(PostStatus.PUBLISHED , tag);
		}
		
		return postRepository.findAllByStatus(PostStatus.PUBLISHED);
	}

}
