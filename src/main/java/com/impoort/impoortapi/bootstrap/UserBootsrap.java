package com.impoort.impoortapi.bootstrap;

import com.impoort.impoortapi.domain.comment.Comment;
import com.impoort.impoortapi.domain.post.Post;
import com.impoort.impoortapi.domain.user.User;
import com.impoort.impoortapi.repository.PostRepository;
import com.impoort.impoortapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserBootsrap implements CommandLineRunner {

    private UserRepository userRepository;
    private PostRepository postRepository;

    @Autowired
    public UserBootsrap(UserRepository userRepository,PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        User user =new User();

        user.setFirstName("Can");
        user.setCity("Aydın");

        userRepository.save(user);

        Post post = new Post();

        post.setUser(user);
        post.setPostDescription("Merhaba ben can");

        post.setCommentList(new ArrayList<Comment>());

        Comment comment=new Comment();
        comment.setUser(user);
        comment.setCommentText("Asdasd");

        List<Comment> commentsTemp=post.getCommentList();
        commentsTemp.add(comment);
        post.setCommentList(commentsTemp);

        postRepository.save(post);


    }
}
