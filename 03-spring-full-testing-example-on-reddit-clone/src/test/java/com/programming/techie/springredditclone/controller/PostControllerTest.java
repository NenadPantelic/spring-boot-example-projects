package com.programming.techie.springredditclone.controller;

import com.programming.techie.springredditclone.dto.PostResponse;
import com.programming.techie.springredditclone.security.JwtProvider;
import com.programming.techie.springredditclone.service.PostService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

@WebMvcTest(PostController.class)
public class PostControllerTest {

    // mockovace i zavisnosti PostService objekta
    @MockBean
    private PostService postService;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private JwtProvider jwtProvider;

    // MockMvc nam omogucava da pozovemo REST endpointe kroz servlet okruzenje
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Should list all posts when making GET request to /api/posts/")
    public void shoudlListAllPosts() throws Exception {
        PostResponse postResponse1 = new PostResponse(1L, "Post name", "http://url.site",
                "Description", "User 1", "Subreddit name", 0,
                0, "1 day ago", false, false);

        PostResponse postResponse2 = new PostResponse(2L, "Post name 2", "http://url.site2",
                "Description 2", "User 2", "Subreddit name 2", 0,
                0, "2 day ago", false, false);

        Mockito.when(postService.getAllPosts()).thenReturn(Arrays.asList(postResponse1, postResponse2));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/posts/"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].postName", Matchers.is("Post name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].url", Matchers.is("http://url.site")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].postName", Matchers.is("Post name 2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].url", Matchers.is("http://url.site2")));


    }

}
