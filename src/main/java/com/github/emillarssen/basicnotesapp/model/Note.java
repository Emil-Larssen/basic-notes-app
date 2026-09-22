package com.github.emillarssen.basicnotesapp.model;

public class Note {
    private String note;

    public Note(String note) {
        this.note = note;
    }

    public String getText() {
        return note;
    }
}
