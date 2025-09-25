package space.personalshowcase.blog.services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import space.personalshowcase.blog.domain.CreatePostRequest;
import space.personalshowcase.blog.domain.PostStatus;
import space.personalshowcase.blog.domain.entities.Category;
import space.personalshowcase.blog.domain.entities.Post;
import space.personalshowcase.blog.domain.entities.Tag;
import space.personalshowcase.blog.domain.entities.User;
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
	
	private static final int WORDS_PER_MINUTE = 150;

	@Override
	@Transactional(readOnly = true)
	public List<Post> getAllPosts(UUID categoryId, UUID tagId) {
		
		if (categoryId != null && tagId != null) {
			Category category = categoryService.getCategoryById(categoryId);
			Tag tag = tagService.getTagById(tagId);
			
			return postRepository.findAllByPostStatusAndCategoryAndTagsContaining(PostStatus.PUBLISHED, category, tag);
		}
		
		
		if(categoryId != null) {
			Category category = categoryService.getCategoryById(categoryId);
			return postRepository.findAllByPostStatusAndCategory(PostStatus.PUBLISHED, category);
		}
		
		
		if(tagId != null) {
			Tag tag = tagService.getTagById(tagId);
			return postRepository.findAllByPostStatusAndTags(PostStatus.PUBLISHED , tag);
		}
		
		return postRepository.findAllByPostStatus(PostStatus.PUBLISHED);
	}


	@Override
	public List<Post> getAllDraftPosts(User user) {
		
		return postRepository.findAllByAuthorAndPostStatus(PostStatus.DRAFT, user);
	}


	@Override
	@Transactional
	public Post createPost(User user, CreatePostRequest createPostRequest) {
		Post newPost = new Post();
		newPost.setTitle(createPostRequest.getTitle());
		newPost.setContent(createPostRequest.getContent());
		newPost.setPostStatus(createPostRequest.getPostStatus());
		newPost.setAuthor(user);
		newPost.setReadingTime(calculateReadingTime(createPostRequest.getContent()));
		
		Category category  =  categoryService.getCategoryById(createPostRequest.getCategoryId());
		newPost.setCategory(category);
		
		
		Set<UUID> tagsIds = createPostRequest.getTagsIds();
		
		List<Tag> foundTags = tagService.getTagByIds(tagsIds);
		
		newPost.setTags(new HashSet<>(foundTags));
		
		return postRepository.save(newPost);
	}
	
	private Integer calculateReadingTime(String content)
	{
		if (content.isEmpty() || content == null) {
			return 0;
		}
		
		int wordCount = content.trim().split("\\s+").length;
		
		return (int) Math.ceil((double) wordCount / WORDS_PER_MINUTE);
		
	}

}
