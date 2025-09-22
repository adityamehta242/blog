package space.personalshowcase.blog.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import space.personalshowcase.blog.domain.dtos.TagResponseDto;
import space.personalshowcase.blog.domain.entities.Tag;
import space.personalshowcase.blog.mappers.TagMapper;
import space.personalshowcase.blog.services.TagService;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/tags")
public class TagController {
	
	private final TagService tagService;
	private final TagMapper tagMapper;
	
	@GetMapping
	public ResponseEntity<List<TagResponseDto>> getAllTags()
	{
		List<Tag> tags = tagService.getTag();
		List<TagResponseDto> tagResponse = tags.stream().map(tagMapper::toTagResponseDto).toList();
		return ResponseEntity.ok(tagResponse);
	}
}
