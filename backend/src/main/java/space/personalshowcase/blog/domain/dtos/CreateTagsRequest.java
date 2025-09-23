package space.personalshowcase.blog.domain.dtos;

import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateTagsRequest {
	
	@NotEmpty(message = "At least one tag name is required.")
	@Size(max = 10 , message = "Maximum {max} tags is allowed")
	private Set< @Size(min = 2 , max = 30 , message = "Tag name must be in between {min} and {max} characters")
		@Pattern(regexp = "^[\\w\\s-]+$" , message = "Tag name can only conatain letter, number ,space , hyphens")
		String> name;
	
	
}
