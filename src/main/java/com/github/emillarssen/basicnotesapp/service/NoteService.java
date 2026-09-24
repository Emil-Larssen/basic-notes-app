package com.github.emillarssen.basicnotesapp.service;

import com.github.emillarssen.basicnotesapp.model.Note;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NoteService {

    private final ArrayList<Note> notesList = new ArrayList<>();
    
    public void addNote(String str) {
        Note note = new Note(str);
        notesList.add(note);
    }
    public ArrayList<Note> getNotes(){
        return notesList;
    }
}
