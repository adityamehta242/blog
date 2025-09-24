package space.personalshowcase.blog.services;

import java.util.UUID;

import space.personalshowcase.blog.domain.entities.User;

public interface UserService {
	User getUserById(UUID id);
}
