package com.onetomanycomposite.entity.composite;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.onetomanycomposite.entity.Post;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
@ToString(exclude = "post")
@EqualsAndHashCode(callSuper = false, exclude = {"post"})
public class PostCommentPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "comment_id")
    private Long postCommentId;

    public PostCommentPK(Post post, Long postCommentId) {
        this.post = post;
        this.postCommentId = postCommentId;
    }
}
