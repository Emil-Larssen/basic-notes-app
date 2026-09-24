package com.github.emillarssen.basicnotesapp.controller;


import com.github.emillarssen.basicnotesapp.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/notes")
    public String submitNote(@RequestParam String noteinput){
        noteService.addNote(noteinput);
        return "redirect:/";
    }

    @GetMapping("/")
    public String getNotes(Model model){
        model.addAttribute("notes", noteService.getNotes());
        return "index";
    }
}
