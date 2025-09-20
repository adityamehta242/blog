package space.personalshowcase.blog.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import space.personalshowcase.blog.services.AuthenticationService;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	private final AuthenticationService authenticationService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		try {
			String token = extractToken(request);
			
			if (token != null) {
				UserDetails userDetails = authenticationService.validateToken(token);
				
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails,
						null,
						userDetails.getAuthorities()
						);
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
				
				if (userDetails instanceof BlogUserDetails) {
					request.setAttribute("userId", ((BlogUserDetails)userDetails).getId());
				}
			}
		} catch (Exception e) {
				
			log.warn("Received Invalid auth token.");
		}
		
		filterChain.doFilter(request, response);
		
	}
	
	
	
	private String extractToken(HttpServletRequest request)
	{
		String bearertoken = request.getHeader("Authorization");
		
		if (bearertoken != null  && bearertoken.startsWith("Bearer ")) {
			return bearertoken.substring(7);
		}
		return null;
	}

}
