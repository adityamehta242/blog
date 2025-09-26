package space.personalshowcase.blog.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import space.personalshowcase.blog.domain.CreatePostRequest;
import space.personalshowcase.blog.domain.UpdatePostRequest;
import space.personalshowcase.blog.domain.dtos.CreatePostRequestDto;
import space.personalshowcase.blog.domain.dtos.PostDto;
import space.personalshowcase.blog.domain.dtos.UpdatePostRequestDto;
import space.personalshowcase.blog.domain.entities.Post;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {
	
	@Mapping(target = "author" , source = "author")
	@Mapping(target = "category" , source = "category")
	@Mapping(target = "tags" , source = "tags")
	PostDto toDto(Post post);
	
	
	CreatePostRequest toCreatePostRequest(CreatePostRequestDto createPostRequestDto);
	
	UpdatePostRequest toUpdatePostRequest(UpdatePostRequestDto updatePostRequestDto);
}
