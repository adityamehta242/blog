package space.personalshowcase.blog.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import space.personalshowcase.blog.domain.entities.User;


@Getter
@RequiredArgsConstructor
public class BlogUserDetails implements UserDetails{

	private final User user;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

}
