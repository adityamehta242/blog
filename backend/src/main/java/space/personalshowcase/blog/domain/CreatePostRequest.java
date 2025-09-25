package space.personalshowcase.blog.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatePostRequest {

	private String title;
	
	private String content;
	
	private UUID categoryId;
	
	
	@Builder.Default
	private Set<UUID> tagsIds = new HashSet<>();
	
	private PostStatus postStatus;
}
