package space.personalshowcase.blog.domain.entities;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import space.personalshowcase.blog.domain.PostStatus;

@Entity
@Table(name = "posts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false , columnDefinition = "TEXT")
	private String context;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private PostStatus postStatus;
	
	@Column(nullable = false)
	private Integer readingTime;
	
	@Column(nullable = false)
	private LocalDateTime createdAt;
	
	@Column(nullable = false)
	private LocalDateTime updatedAt;
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(context, createdAt, id, postStatus, readingTime, title, updatedAt);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		return Objects.equals(context, other.context) && Objects.equals(createdAt, other.createdAt)
				&& Objects.equals(id, other.id) && postStatus == other.postStatus
				&& Objects.equals(readingTime, other.readingTime) && Objects.equals(title, other.title)
				&& Objects.equals(updatedAt, other.updatedAt);
	}



	@PrePersist
	protected void onCreate()
	{
		LocalDateTime now = LocalDateTime.now();
		this.createdAt = now;
		this.updatedAt = now;
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = LocalDateTime.now();
	}
	
	
}
