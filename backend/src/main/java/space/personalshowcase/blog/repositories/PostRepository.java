package space.personalshowcase.blog.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import space.personalshowcase.blog.domain.PostStatus;
import space.personalshowcase.blog.domain.entities.Category;
import space.personalshowcase.blog.domain.entities.Post;
import space.personalshowcase.blog.domain.entities.Tag;
import space.personalshowcase.blog.domain.entities.User;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
	
	List<Post> findAllByPostStatusAndCategoryAndTagsContaining(PostStatus postStatus , Category category ,Tag tag);
	List<Post> findAllByPostStatusAndCategory(PostStatus postStatus , Category category);
	List<Post> findAllByPostStatusAndTags(PostStatus postStatus , Tag tag);
	List<Post> findAllByPostStatus(PostStatus postStatus);
	List<Post> findAllByAuthorAndPostStatus(PostStatus postStatus , User author);
}
