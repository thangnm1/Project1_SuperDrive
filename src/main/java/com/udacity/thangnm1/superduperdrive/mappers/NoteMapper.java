package com.udacity.thangnm1.superduperdrive.mappers;

import org.apache.ibatis.annotations.*;

import com.udacity.thangnm1.superduperdrive.enitites.Note;

@Mapper
public interface NoteMapper {
	@Select("SELECT * FROM NOTES WHERE userid = #{userId}")
	Note[] getNotesForUser(Integer userId);

	@Insert("INSERT INTO NOTES (notetitle, notedescription, userid) "
			+ "VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
	@Options(useGeneratedKeys = true, keyProperty = "noteId")
	int insert(Note note);

	@Select("SELECT * FROM NOTES")
	Note[] getNoteListings();

	@Select("SELECT * FROM NOTES WHERE noteid = #{noteId}")
	Note getNote(Integer noteId);

	@Delete("DELETE FROM NOTES WHERE noteid = #{noteId}")
	void deleteNote(Integer noteId);

	@Update("UPDATE NOTES SET notetitle = #{title}, notedescription = #{description} WHERE noteid = #{noteId}")
	void updateNote(Integer noteId, String title, String description);
}
