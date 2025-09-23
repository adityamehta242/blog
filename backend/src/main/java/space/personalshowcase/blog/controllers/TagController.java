package space.personalshowcase.blog.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import space.personalshowcase.blog.domain.dtos.CreateTagsRequest;
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
	
	@PostMapping
	public ResponseEntity<List<TagResponseDto>> createTags(@RequestBody CreateTagsRequest createTagsRequest)
	{
		List<Tag> saveTags = tagService.createTags(createTagsRequest.getName());
		List<TagResponseDto> createTagResponse = saveTags.stream().map(tagMapper::toTagResponseDto).toList();
		return new ResponseEntity<List<TagResponseDto>>(createTagResponse , HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTag(@PathVariable UUID id){
		
		tagService.deleteTag(id);
		return ResponseEntity.noContent().build();
	}
}
