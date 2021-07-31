package com.programming.techie.springredditclone.service;

import com.programming.techie.springredditclone.dto.PostRequest;
import com.programming.techie.springredditclone.dto.PostResponse;
import com.programming.techie.springredditclone.mapper.PostMapper;
import com.programming.techie.springredditclone.model.Post;
import com.programming.techie.springredditclone.model.Subreddit;
import com.programming.techie.springredditclone.model.User;
import com.programming.techie.springredditclone.repository.PostRepository;
import com.programming.techie.springredditclone.repository.SubredditRepository;
import com.programming.techie.springredditclone.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

// ako necemo da mockujemo rucno, vec da koristimo mock anotaciju
@ExtendWith(MockitoExtension.class)
class PostServiceTest {
      // manual mocking
//    private PostRepository postRepository = Mockito.mock(PostRepository.class);
//    private SubredditRepository subredditRepository = Mockito.mock(SubredditRepository.class);
//    private UserRepository userRepository = Mockito.mock(UserRepository.class);
//    private AuthService authService = Mockito.mock(AuthService.class);
//    private PostMapper postMapper = Mockito.mock(PostMapper.class);


    @Mock private PostRepository postRepository;
    @Mock private SubredditRepository subredditRepository;
    @Mock private UserRepository userRepository;
    @Mock private AuthService authService;
    @Mock private PostMapper postMapper;
    @Captor
    private ArgumentCaptor<Post> postArgumentCaptor;
    private PostService postService;

    @BeforeEach
    void setUp() {
        postService = new PostService(postRepository, subredditRepository, userRepository,
                authService, postMapper);
    }

    @Test
    @DisplayName("Test if the post is fetched by id")
    void shouldFindPostById() {
        Post post = new Post(123L, "First post", "http://url.site", "Test", 0,
                null, Instant.now(), null);
        PostResponse expectedPostResponse = new PostResponse(123L, "First post", "http://url.site", "Test", "Test user",
                "Test Subreddit", 0, 0, "1 hour ago",
                false, false);
        Mockito.when(postRepository.findById(123L)).thenReturn(Optional.of(post));
        Mockito.when(postMapper.mapToDto(Mockito.any(Post.class))).thenReturn(expectedPostResponse);
        PostResponse actualPostResponse = postService.getPost(123L);

        assertThat(actualPostResponse.getId()).isEqualTo(expectedPostResponse.getId());
        assertThat(actualPostResponse.getPostName()).isEqualTo(expectedPostResponse.getPostName());
    }

    @Test
    @DisplayName("Test if the post is saved")
    void shouldSavePost() {
        User user = new User(123L, "test user", "secret password", "user@gmail.com",
                Instant.now(), true);
        Subreddit subreddit = new Subreddit(123L, "First subreddit", "Subreddit description",
                emptyList(), Instant.now(), user);
        Post post = new Post(123L, "First post", "http://url.site", "Test", 0,
                null, Instant.now(), null);
        PostRequest postRequest = new PostRequest(null, "First subreddit", "First post",
                "http://url.site", "some description");
        PostResponse expectedPostResponse = new PostResponse(123L, "First post", "http://url.site", "Test", "Test user",
                "Test Subreddit", 0, 0, "1 hour ago",
                false, false);
        Mockito.when(subredditRepository.findByName("First subreddit")).thenReturn(Optional.of(subreddit));
        Mockito.when(postMapper.map(postRequest, subreddit, user)).thenReturn(post);
        Mockito.when(authService.getCurrentUser()).thenReturn(user);

        // posto ova metoda ne vraca sacuvani objekat, verifikovacemo da je repo metoda pozvana
        postService.save(postRequest);
        // verfikacija poziva PostRepository.save metode
        //Mockito.verify(postRepository, Mockito.times(1)).save(Mockito.any(Post.class));
         // ako hocemo da uhvatimo objekat koji je prosledjen metodi, koristimo ArgumentCaptor
        Mockito.verify(postRepository, Mockito.times(1)).save(postArgumentCaptor.capture());

        assertThat(postArgumentCaptor.getValue().getPostId()).isEqualTo(123L);
        assertThat(postArgumentCaptor.getValue().getPostName()).isEqualTo(post.getPostName());

    }
}