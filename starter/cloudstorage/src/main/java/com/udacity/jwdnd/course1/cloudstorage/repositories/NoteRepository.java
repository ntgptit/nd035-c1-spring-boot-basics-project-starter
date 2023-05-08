package com.udacity.jwdnd.course1.cloudstorage.repositories;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;

@Mapper
public interface NoteRepository {

	@Select("SELECT NOTES.NOTEID AS \"noteId\" , NOTES.NOTETITLE AS \"noteTitle\" , NOTES.NOTEDESCRIPTION AS \"noteDescription\" , NOTES.USERID AS \"userId\" FROM NOTES NOTES INNER JOIN USERS USERS ON NOTES.USERID = USERS.USERID WHERE USERS.USERID = #{userId}")
	List<Note> getAllNotes(@Param("userId") Integer userId);

	@Insert("INSERT INTO NOTES (NOTETITLE , NOTEDESCRIPTION , USERID) VALUES(#{note.noteTitle} , #{note.noteDescription} , #{note.userId})")
	int addNote(@Param("note") Note note);

	@Update("UPDATE NOTES SET NOTETITLE=#{note.noteTitle}, NOTEDESCRIPTION=#{note.noteDescription} WHERE NOTEID=#{note.noteId}")
	int updateNode(@Param("note") Note note);

	@Delete("DELETE FROM NOTES WHERE NOTEID = #{noteId}")
	int removeNote(@Param("noteId") Integer noteId);

}
