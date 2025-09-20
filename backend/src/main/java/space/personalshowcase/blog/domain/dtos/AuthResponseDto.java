package space.personalshowcase.blog.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AuthResponseDto {

	private String token;
	private long expireIn;
	
	
}
