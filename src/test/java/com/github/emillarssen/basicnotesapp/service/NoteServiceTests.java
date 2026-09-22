package com.github.emillarssen.basicnotesapp.service;

import com.github.emillarssen.basicnotesapp.service.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NoteServiceTests {

    @Test
    public void noteListShouldNotBeEmptyWhenNoteHasBeenAdded() {
        NoteService noteservice = new NoteService();
        noteservice.addNote("hello");
        assertFalse(noteservice.getNotes().isEmpty());
    }

    @Test
    public void noteListShouldContainAddedNote(){
        NoteService noteservice = new NoteService();
        noteservice.addNote("hello");
        assertEquals("hello", noteservice.getNotes().get(0).getText());
    }
}
