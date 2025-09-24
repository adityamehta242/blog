package space.personalshowcase.blog.services;

import java.util.List;
import java.util.UUID;

import space.personalshowcase.blog.domain.entities.Post;

public interface PostService {
	List<Post> getAllPosts(UUID categoryId , UUID tagId);
}
