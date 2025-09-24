package space.personalshowcase.blog.mappers;

import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import space.personalshowcase.blog.domain.PostStatus;
import space.personalshowcase.blog.domain.dtos.TagDto;
import space.personalshowcase.blog.domain.entities.Post;
import space.personalshowcase.blog.domain.entities.Tag;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagMapper {

    @Mapping(target = "postCount", source = "posts", qualifiedByName = "calculatePostCount")
    TagDto toTagResponseDto(Tag tag);

    @Named("calculatePostCount")
    default Integer calculatePostCount(Set<Post> posts) {
        if (posts == null) {
            return 0;
        }
        return (int) posts.stream()
            .filter(post -> PostStatus.PUBLISHED.equals(post.getPostStatus()))
            .count();
    }
}
