package space.personalshowcase.blog.services;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import space.personalshowcase.blog.domain.entities.Tag;

public interface TagService {
	List<Tag> getTag();
	List<Tag> createTags(Set<String> tagName);
	void deleteTag(UUID id);
	Tag getTagById(UUID id);
}
