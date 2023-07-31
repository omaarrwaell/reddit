package com.example.reddit.service;

import com.example.reddit.domain.Comment;
import com.example.reddit.domain.Link;
import com.example.reddit.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }



    public Comment save(Comment comment){
        return commentRepository.save(comment);
    }
}
