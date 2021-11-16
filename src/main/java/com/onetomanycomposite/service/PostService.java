package com.onetomanycomposite.service;

import com.onetomanycomposite.entity.Post;
import com.onetomanycomposite.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository repository;

    @Transactional()
    public Post save(Post post) {
        Post p = repository.save(post);
        return p;
    }

    @Transactional()
    public Post update(Post post) {
        Post p = repository.save(post);
        return p;
    }

    public Optional<Post> findById(Long id) {
        Optional<Post> student = repository.findById(id);
        return student;
    }
}
