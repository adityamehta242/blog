package space.personalshowcase.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import space.personalshowcase.blog.repositories.UserRepository;
import space.personalshowcase.blog.security.BlogUserDetailsService;
import space.personalshowcase.blog.security.JwtAuthenticationFilter;
import space.personalshowcase.blog.services.AuthenticationService;
import space.personalshowcase.blog.domain.entities.User;

@Configuration
public class SecurityConfig {

	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter(AuthenticationService authenticationService) {
		return new JwtAuthenticationFilter(authenticationService);
	}

	@Bean
	UserDetailsService userDetailsService(UserRepository userRepository) {
		BlogUserDetailsService blogUserDetailsService = new BlogUserDetailsService(userRepository);
		
		String email = "test@mail.com";
		
		userRepository.findByEmail(email).orElseGet(()->{
			User newUser = User.builder()
					.email(email)
					.name("test user")
					.password(passwordEncoder().encode("pass123"))
					.build();
			
			return userRepository.save(newUser);
			
		});
		
		return blogUserDetailsService;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter)
			throws Exception {
		http.authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.POST, "/api/v1/auth").permitAll()
				.requestMatchers(HttpMethod.GET, "/api/v1/posts/drafts").authenticated()
				.requestMatchers(HttpMethod.GET, "/api/v1/posts/**").permitAll()
				.requestMatchers(HttpMethod.GET, "/api/v1/categories/**").permitAll()
				.requestMatchers(HttpMethod.GET, "/api/v1/tags/**").permitAll().anyRequest().authenticated())
				.csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}
