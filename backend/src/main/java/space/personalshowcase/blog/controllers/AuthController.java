package space.personalshowcase.blog.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import space.personalshowcase.blog.domain.dtos.AuthResponseDto;
import space.personalshowcase.blog.domain.dtos.LoginRequestDto;
import space.personalshowcase.blog.services.AuthenticationService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
	private final AuthenticationService authenticationService;
	
	@PostMapping
	public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto loginRequestDto)
	{
		UserDetails userDetails  = authenticationService.authenticate(loginRequestDto.getEmail(), loginRequestDto.getPassword());
		
		String token = authenticationService.generateToken(userDetails);
		
		AuthResponseDto authResponseDto = AuthResponseDto.builder().token(token).expireIn(86400).build();
		
		return ResponseEntity.ok(authResponseDto);
	}
}
