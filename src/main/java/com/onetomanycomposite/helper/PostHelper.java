package com.onetomanycomposite.helper;

import com.onetomanycomposite.dto.CommentDto;
import com.onetomanycomposite.entity.Post;
import com.onetomanycomposite.entity.PostComment;
import com.onetomanycomposite.entity.composite.PostCommentPK;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class PostHelper {

    public void commentsUpdate(CommentDto dto, Post post) {
        post.getComments().clear();
        List<PostComment> comments = new ArrayList<>();
        Iterator<PostComment> iterator = dto.getComments().iterator();
        int i = 0;
        for (Iterator<PostComment> it = iterator; it.hasNext(); i++) {
            PostComment comment = it.next();
            comment.setPk(
                    new PostCommentPK(post, comment.getPk().getPostCommentId())
            );
            comments.add(comment);
        }
        post.getComments().addAll(comments);
    }
}
