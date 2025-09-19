package space.personalshowcase.blog.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import space.personalshowcase.blog.domain.PostStatus;
import space.personalshowcase.blog.domain.dtos.CategoryDTO;
import space.personalshowcase.blog.domain.dtos.CreateCategoryRequestDto;
import space.personalshowcase.blog.domain.entities.Category;
import space.personalshowcase.blog.domain.entities.Post;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

	@Mapping(target = "postCount" , source = "posts" , qualifiedByName = "calculatePostCount")
	CategoryDTO toDto(Category category);
	

	Category toEntity(CreateCategoryRequestDto createCategoryRequestDto);
	
	@Named("calculatePostCount")
	default long calculatePostCount(List<Post> posts)
	{
		if (null ==posts) {
			return 0;
		}
		
		return posts.stream()
			.filter(post -> PostStatus.PUBLISHED.equals(post.getPostStatus()))
			.count();
	}
	
	
	
}
