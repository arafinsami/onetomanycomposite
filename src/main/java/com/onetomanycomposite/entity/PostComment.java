package com.onetomanycomposite.entity;

import com.onetomanycomposite.entity.composite.PostCommentPK;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Version;

@Getter
@Setter
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PostComment extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @EmbeddedId
    private PostCommentPK pk;

    private String description;

    @Version
    protected long version;
}
