package space.personalshowcase.blog.services;

import java.util.List;
import java.util.UUID;

import space.personalshowcase.blog.domain.CreatePostRequest;
import space.personalshowcase.blog.domain.UpdatePostRequest;
import space.personalshowcase.blog.domain.entities.Post;
import space.personalshowcase.blog.domain.entities.User;

public interface PostService {
	List<Post> getAllPosts(UUID categoryId , UUID tagId);
	List<Post> getAllDraftPosts(User user);
	Post createPost(User user , CreatePostRequest createPostRequest);
	Post updatePost(UUID id , UpdatePostRequest updatePostRequest);
}
