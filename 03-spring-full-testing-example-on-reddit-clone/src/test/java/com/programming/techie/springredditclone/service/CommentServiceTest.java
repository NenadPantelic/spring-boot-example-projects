package com.programming.techie.springredditclone.service;

import com.programming.techie.springredditclone.exceptions.SpringRedditException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class CommentServiceTest {

    @Test
    @DisplayName("Test when comment does not contain bad language - swear words")
    void shouldNotContainSwearWordsInComment() {
        CommentService commentService = new CommentService(null, null, null,
                null, null, null, null);
        //Assertions.assertFalse(commentService.containsSwearWords("This is a clean comment"));
        assertThat(commentService.containsSwearWords("This is a clean comment")).isFalse();
    }


    @Test
    @DisplayName("Test exception throwing when comment contains bad language - swear words")
    void shouldThrowExceptionWhenContainsSwearWordsInComment() {
        CommentService commentService = new CommentService(null, null, null,
                null, null, null, null);
//        SpringRedditException exception = Assertions.assertThrows(SpringRedditException.class,
//                () -> commentService.containsSwearWords("This is a clean comment, oh shit, no"));
        assertThatThrownBy(() -> commentService.containsSwearWords("This is a clean comment, oh shit, no"))
                .isInstanceOf(SpringRedditException.class)
                .hasMessage("Comments contains unacceptable language");
        //        Assertions.assertThrows(SpringRedditException.class, () -> commentService.containsSwearWords("This is a clean comment, oh shit, no"));
        // Assertions.assertEquals(exception.getMessage(), "Comments contains unacceptable language");
    }
}