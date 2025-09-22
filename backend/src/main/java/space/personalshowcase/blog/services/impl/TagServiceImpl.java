package space.personalshowcase.blog.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import space.personalshowcase.blog.domain.entities.Tag;
import space.personalshowcase.blog.repositories.TagRepository;
import space.personalshowcase.blog.services.TagService;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService{
	
	private final TagRepository tagRepository;

	@Override
	public List<Tag> getTag() {
		List<Tag> tag = tagRepository.findAllWithPostCount();
		return tag;
	}

}
