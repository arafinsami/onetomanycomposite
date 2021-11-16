package com.onetomanycomposite.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Post extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String name;

    @Version
    protected long version;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "pk.post",
            orphanRemoval = true
    )
    private List<PostComment> comments;
}
