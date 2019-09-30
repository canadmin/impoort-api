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
        User user=new User();
        user.setFirstName("Can");
        user.setLastName("yard");
        user.setCity("aydın");

        User user2=new User();
        user2.setFirstName("asd");
        user2.setLastName("fkfd");
        user2.setCity("aydın");

        this.userRepository.save(user);
        this.userRepository.save(user2);

        Post post=new Post();
        post.setPostDescription("Deneme deneme deneme ");
        post.setUser(user);

        Post post1=new Post();
        post1.setPostDescription("asdjaklsjflk alkklsd");
        post1.setUser(user);

        postRepository.save(post1);
        this.postRepository.save(post);

        Comment commentYeni = new Comment();
        commentYeni.setCommentText("merheba");
        commentYeni.setPostId(2);
        commentYeni.setUser(user2);

        commentsRepository.save(commentYeni);

        Comment comment = new Comment();
        comment.setCommentText("merheba");
        comment.setPostId(1);
        comment.setUser(user);

        commentsRepository.save(comment);

        Comment comment2 = new Comment();
        comment2.setCommentText("deneme");
        comment2.setPostId(1);
        comment2.setUser(user);

        commentsRepository.save(comment2);

        List<Comment> comments=commentsRepository.findByPostId(0);
        for(Comment com:comments){
            System.out.println(com);
        }

    }
}
