package com.onetomanycomposite.dto;

import com.onetomanycomposite.entity.PostComment;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class CommentDto {

    @NotNull
    private Long postId;

    @Valid
    private List<PostComment> comments;
}
