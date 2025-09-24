package space.personalshowcase.blog.services.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import space.personalshowcase.blog.domain.entities.User;
import space.personalshowcase.blog.repositories.UserRepository;
import space.personalshowcase.blog.services.UserService;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;

	@Override
	public User getUserById(UUID id) {
		
		return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found with this Id " + id));
	}

}
