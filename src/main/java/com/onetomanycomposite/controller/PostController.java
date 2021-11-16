package com.onetomanycomposite.controller;

import com.onetomanycomposite.dto.CommentDto;
import com.onetomanycomposite.dto.PostDto;
import com.onetomanycomposite.entity.Post;
import com.onetomanycomposite.exception.ResourceNotFoundException;
import com.onetomanycomposite.helper.PostHelper;
import com.onetomanycomposite.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static com.onetomanycomposite.exception.ApiError.fieldError;
import static com.onetomanycomposite.utils.ResponseBuilder.error;
import static com.onetomanycomposite.utils.ResponseBuilder.success;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("post")
@RequiredArgsConstructor
@Api(tags = "Post Data")
public class PostController {

    private final PostService service;

    private final PostHelper helper;

    @PostMapping("/save")
    @ApiOperation(value = "save post", response = String.class)
    public ResponseEntity<JSONObject> save(@RequestBody PostDto dto,
                                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Post post = dto.to();
        service.save(post);
        return ok(success(PostDto.from(post)).getJson());
    }

    @PostMapping("/comment/save")
    @ApiOperation(value = "save comment", response = String.class)
    public ResponseEntity<JSONObject> saveSubject(@RequestBody CommentDto dto,
                                                  BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Post post = service.findById(dto.getPostId()).orElseThrow(
                () -> new ResourceNotFoundException("post not found with id: " + dto.getPostId())
        );

        helper.commentsUpdate(dto, post);
        service.save(post);
        return ok(success("comments successfully saved").getJson());
    }

    @PutMapping("/update")
    @ApiOperation(value = "update post", response = String.class)
    public ResponseEntity<JSONObject> update(@RequestBody PostDto dto,
                                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Post post = service.findById(dto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("post not found with id: " + dto.getId())
        );
        dto.update(post);
        service.update(post);
        return ok(success(PostDto.from(post)).getJson());
    }

    @PutMapping("/comment/update")
    @ApiOperation(value = "update comment", response = String.class)
    public ResponseEntity<JSONObject> updateComment(@RequestBody CommentDto dto,
                                                    BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Post post = service.findById(dto.getPostId()).orElseThrow(
                () -> new ResourceNotFoundException("post not found with id: " + dto.getPostId())
        );

        helper.commentsUpdate(dto, post);
        service.save(post);
        return ok(success("comments successfully updated").getJson());
    }

    @GetMapping("/{postId}")
    @ApiOperation(value = "get post by id", response = PostDto.class)
    public ResponseEntity<JSONObject> updateComment(@PathVariable Long postId) {

        Post post = service.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post not found with id: " + postId)
        );
        return ok(success(post).getJson());
    }
}
