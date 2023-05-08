package com.udacity.jwdnd.course1.cloudstorage.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.repositories.NoteRepository;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteServices;

@Service
public class NoteServiceImpl implements NoteServices {

	@Autowired
	private NoteRepository noteRepository;

	@Override
	public List<Note> getAllNotes(Integer userId) {
		return noteRepository.getAllNotes(userId);
	}

	@Override
	public int saveNote(Note note) {
		if (note.getNoteId() == null) {
			return noteRepository.addNote(note);
		}
		return noteRepository.updateNode(note);
	}

	@Override
	public int removeNote(Integer noteId) {
		return noteRepository.removeNote(noteId);
	}

}
