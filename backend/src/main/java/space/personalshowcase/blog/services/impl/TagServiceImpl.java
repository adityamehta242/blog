package space.personalshowcase.blog.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
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

	@Transactional
	@Override
	public List<Tag> createTags(Set<String> tagNames) {
		List<Tag> existingTags = tagRepository.findByNameIn(tagNames);
		
		Set<String> existingTagNames = existingTags.stream()
			    .map(Tag::getName)
			    .collect(Collectors.toSet());
		
		List<Tag> tags= tagNames.stream()
			.filter(name -> !existingTagNames.contains(name))
			.map(name -> Tag.builder().name(name).posts(new HashSet<>())
					.build())
			.toList();
		
		List<Tag> saveTag = new ArrayList<>();
		
		if (!tags.isEmpty()) {
			saveTag = tagRepository.saveAll(tags);
		}
		
		saveTag.addAll(existingTags);
		return saveTag;
	}

}
