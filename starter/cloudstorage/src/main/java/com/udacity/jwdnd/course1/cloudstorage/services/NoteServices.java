package com.udacity.jwdnd.course1.cloudstorage.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;

@Service
public interface NoteServices {

	List<Note> getAllNotes(Integer userId);

	int saveNote(Note note);

	int removeNote(Integer noteId);
}
