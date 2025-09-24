package space.personalshowcase.blog.domain.dtos;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import space.personalshowcase.blog.domain.PostStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

	private UUID id;
	private String title;
	private String content;
	private AuthorDto author;
	private CategoryDTO category;
	private Set<TagDto> tags;
	private Integer readingTime;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private PostStatus postStatus;
}
