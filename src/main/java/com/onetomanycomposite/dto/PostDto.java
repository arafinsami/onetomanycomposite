package com.onetomanycomposite.dto;

import com.onetomanycomposite.entity.Post;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class PostDto {

    private Long id;

    @NotBlank
    @Size(min = 10, max = 100)
    private String name;

    public Post to() {
        Post post = new Post();
        post.setName(name);
        return post;
    }

    public void update(Post post) {
        post.setName(name);
    }

    public static PostDto from(Post post) {
        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setName(post.getName());
        return dto;
    }
}
