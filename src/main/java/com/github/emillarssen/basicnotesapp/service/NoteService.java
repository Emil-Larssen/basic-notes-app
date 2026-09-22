package com.github.emillarssen.basicnotesapp.service;

import com.github.emillarssen.basicnotesapp.model.Note;

import java.util.ArrayList;

public class NoteService {

    private final ArrayList<Note> noteslist = new ArrayList<>();
    
    public void addNote(String str) {
        Note note = new Note(str);
        noteslist.add(note);
    }
    public ArrayList<Note> getNotes(){
        return noteslist;
    }
}
