package com.impoort.impoortapi.bootstrap;

import com.impoort.impoortapi.domain.comment.Comment;
import com.impoort.impoortapi.domain.post.Post;
import com.impoort.impoortapi.domain.user.User;
import com.impoort.impoortapi.repository.CommentsRepository;
import com.impoort.impoortapi.repository.PostRepository;
import com.impoort.impoortapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UserBootsrap implements CommandLineRunner {

    private UserRepository userRepository;
    private CommentsRepository commentsRepository;
    private PostRepository postRepository;

    @Autowired
    public UserBootsrap(UserRepository userRepository, CommentsRepository commentsRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.commentsRepository = commentsRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
