package space.personalshowcase.blog.domain.dtos;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import space.personalshowcase.blog.domain.PostStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatePostRequestDto {

	@NotBlank(message = "Title is required")
	@Size(min = 3, max = 200, message = "Title must be between {min} and {max} characters.")
	private String title;

	@NotBlank(message = "Content is required")
	@Size(min = 3, max = 50000, message = "Content must be between {min} and {max} characters.")
	private String content;

	@NotNull(message = "categoryId is required")
	private UUID categoryId;

	@Builder.Default
	@Size(max = 10, message = "Maximum {max} tags allowed.")
	private Set<UUID> tagsIds = new HashSet<>();

	@NotNull(message = "Status is required")
	private PostStatus postStatus;

}
