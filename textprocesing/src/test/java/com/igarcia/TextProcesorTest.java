package com.igarcia;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TextProcesorTest {
    @Test
    public void numberTotalWords_1_1() {
        assertEquals(1, new TextProcesor().numberWords("Hello"));
    }

    @Test
    public void numberTotalWords_2_2() {
        assertEquals(2, new TextProcesor().numberWords("Hello, this"));
    }

    @Test
    public void numberTotalWords_n_n() {
        assertEquals(21, new TextProcesor().numberWords(
                "Hello, this is an example for you to practice. You should grab\nthis text and make it as your test case."));
    }

    @Test
    public void numberTotalWords_text_1() {
        assertEquals("The text has in total 1 words", new TextProcesor().numberWordsText(
                "Hello"));
    }

    @Test
    public void numberTotalWords_text_2() {
        assertEquals("The text has in total 2 words", new TextProcesor().numberWordsText(
                "Hello, this"));
    }

    @Test
    public void numberTotalWords_text_n() {
        assertEquals("The text has in total 21 words", new TextProcesor().numberWordsText(
                "Hello, this is an example for you to practice. You should grab\nthis text and make it as your test case."));
    }

    @Test
    public void headerText_1_word() {
        assertEquals("Those are the top 1 words used:\n\n", new TextProcesor().header("Hello"));
    }

    @Test
    public void headerText_2_word() {
        assertEquals("Those are the top 2 words used:\n\n", new TextProcesor().header("Hello, this"));
    }

    @Test
    public void headerText_n_word() {
        assertEquals("Those are the top 10 words used:\n\n", new TextProcesor().header(
                "Hello, this is an example for you to practice. You should grab\nthis text and make it as your test case."));
    }

    @Test
    public void topWordsList_1_word() {
        List<String> expected = Arrays.asList("hello");
        assertThat(expected, is(new TextProcesor().topWords("Hello")));
    }

    @Test
    public void topWordsList_2_word() {
        assertThat(new TextProcesor().topWords("Hello, this"), hasItems("hello", "this"));
    }

    @Test
    public void topWordsList_only_10_word() {
        assertThat(new TextProcesor().topWords(
                "Hello, this is an example for you to practice. You should grab"),
                hasSize(10));
    }

    @Test
    public void topWordsBdyText_1_word() {
        assertThat(new TextProcesor().bodyText("Hello"), is("1. hello\n"));
    }

    @Test
    public void topWordsBdyText_2_word() {
        assertThat(new TextProcesor().bodyText("Hello, this"), containsString("hello"));
        assertThat(new TextProcesor().bodyText("Hello, this"), containsString("this"));
    }

    @Test
    public void allText() {
        assertEquals(
                "Those are the top 10 words used:\n\n1. this\n2. you\n3. practice\n4. test\n5. for\n6. is\n7. your\n8. it\n9. an\n10. example\nThe text has in total 21 words",
                new TextProcesor().allText(
                        "Hello, this is an example for you to practice. You should grab\nthis text and make it as your test case."));
    }
}
