package com.github.emillarssen.basicnotesapp.service;

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
    public void noteListShouldContainAddedNotes(){
        NoteService noteservice = new NoteService();
        noteservice.addNote("hello");
        noteservice.addNote("world");
        assertEquals("hello", noteservice.getNotes().get(0).getText());
        assertEquals("world", noteservice.getNotes().get(1).getText());
    }
}
