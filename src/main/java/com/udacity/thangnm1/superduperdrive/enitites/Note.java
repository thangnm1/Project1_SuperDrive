package com.udacity.thangnm1.superduperdrive.enitites;



public class Note {
	private Integer noteId;
	private String title;
	private String description;
	private Integer userId;
	public Note(Integer noteId, String title, String description, Integer userId) {
		super();
		this.noteId = noteId;
		this.title = title;
		this.description = description;
		this.userId = userId;
	}
		
	
	public Note(String title, String description) {
		super();
		this.title = title;
		this.description = description;
	}



	public Integer getNoteId() {
		return noteId;
	}
	public void setNoteId(Integer noteId) {
		this.noteId = noteId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
}
