package space.personalshowcase.blog.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import space.personalshowcase.blog.domain.entities.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {

}
