package space.personalshowcase.blog.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import space.personalshowcase.blog.domain.dtos.PostDto;
import space.personalshowcase.blog.domain.entities.Post;
import space.personalshowcase.blog.domain.entities.User;
import space.personalshowcase.blog.mappers.PostMapper;
import space.personalshowcase.blog.services.PostService;
import space.personalshowcase.blog.services.UserService;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {
	
	private final PostService postService;
	private final PostMapper postMapper;
	private final UserService userService;
	
	@GetMapping()
	public ResponseEntity<List<PostDto>> getAllPosts(
			@RequestParam(required = false) UUID categoryId , 
			@RequestParam(required =  false) UUID tagId)
	{
		
		List<Post> posts = postService.getAllPosts(categoryId, tagId);
		
		return ResponseEntity.ok(posts
				.stream().map(postMapper::toDto).toList());
	}
	
	
	@GetMapping(path = "/drafts")
	public ResponseEntity<List<PostDto>> getDraftsPost(@RequestAttribute UUID userId)
	{
		User loggedInUser = userService.getUserById(userId);
		List<Post> draftPost =  postService.getAllDraftPosts(loggedInUser);
		 
		return ResponseEntity.ok(draftPost.stream()
				.map(postMapper::toDto).toList());
	}

}
