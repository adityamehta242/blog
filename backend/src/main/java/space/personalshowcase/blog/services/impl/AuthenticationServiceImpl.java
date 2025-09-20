package space.personalshowcase.blog.services.impl;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import space.personalshowcase.blog.services.AuthenticationService;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{
	
	private AuthenticationManager authenticationManager;
	private UserDetailsService userDetailsService;
	
	@Value("${jwt.secret}")
	private String secretKey;
	
	private final Long jwtExpireMs = 86400000L;

	@Override
	public UserDetails authenticate(String email, String password) {
		
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		
		return userDetailsService.loadUserByUsername(email);
	}

	@Override
	public String generateToken(UserDetails userDetails) {
		
		Map<String , Object> claims = new HashMap<>();
		
		return Jwts.builder()
			.setClaims(claims)
			.setSubject(userDetails.getUsername())
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + jwtExpireMs))
			.signWith(getSignInKey() , SignatureAlgorithm.HS256)
			.compact();
		
	}

	
	private Key getSignInKey()
	{
		byte[] keyBytes =  secretKey.getBytes();
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
