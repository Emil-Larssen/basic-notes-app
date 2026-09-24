package com.github.emillarssen.basicnotesapp.controller;


import com.github.emillarssen.basicnotesapp.service.NoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NoteController.class)
@Import(NoteService.class)
public class NoteControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private NoteService noteService;

    @Test
    void submitShouldAddPostedNote() throws Exception {
        mockMvc.perform(post("/notes")
                .param("noteinput", "hello"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
        assertEquals("hello", noteService.getNotes().getFirst().getText());
    }


    @Test
    void postedNotesShouldBeDisplayedInTheBrowserPage() throws Exception {
        mockMvc.perform(post("/notes")
                        .param("noteinput", "hello"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("hello")));
    }
}
