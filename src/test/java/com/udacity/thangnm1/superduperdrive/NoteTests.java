package com.udacity.thangnm1.superduperdrive;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.udacity.thangnm1.superduperdrive.enitites.Note;

/**
 * Note function:
 * Creation,
 * Viewing,
 * Editing,
 * Delete
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NoteTests extends SuperDuperDriveApplicationTests {
	/**
	 * Delete a note and it is not showed
	 */
	@Test
	public void testDelete() {
		String noteTitle = "It's not a bug";
		String noteDescription = "It's a function";
		HomePageTests homePage = signUpAndLogin();
		createNote(noteTitle, noteDescription, homePage);
		homePage.navToNotesTab();
		homePage = new HomePageTests(driver);
		Assertions.assertFalse(homePage.noNotes(driver));
		deleteNote(homePage);
		Assertions.assertTrue(homePage.noNotes(driver));
	}

	private void deleteNote(HomePageTests homePage) {
		homePage.deleteNote();
		ResultTests resultPage = new ResultTests(driver);
		resultPage.clickOk();
	}

	/**
	 * Creates a note and display it.
	 */
	@Test
	public void testCreateAndDisplay() {
		String noteTitle = "It's not a bug";
		String noteDescription = "It's a function";
		HomePageTests homePage = signUpAndLogin();
		createNote(noteTitle, noteDescription, homePage);
		homePage.navToNotesTab();
		homePage = new HomePageTests(driver);
		Note note = homePage.getFirstNote();
		Assertions.assertEquals(noteTitle, note.getTitle());
		Assertions.assertEquals(noteDescription, note.getDescription());
		deleteNote(homePage);
		homePage.logout();
	}

	/**
	 * Edits an existing note and show the changes.
	 */
	@Test
	public void testModify() {
		String noteTitle = "It's not a bug";
		String noteDescription = "It's a function";
		HomePageTests homePage = signUpAndLogin();
		createNote(noteTitle, noteDescription, homePage);
		homePage.navToNotesTab();
		homePage = new HomePageTests(driver);
		homePage.editNote();
		String modifiedNoteTitle = "Dont touch";
		homePage.modifyNoteTitle(modifiedNoteTitle);
		String modifiedNoteDescription = "My laptop";
		homePage.modifyNoteDescription(modifiedNoteDescription);
		homePage.saveNoteChanges();
		ResultTests resultPage = new ResultTests(driver);
		resultPage.clickOk();
		homePage.navToNotesTab();
		Note note = homePage.getFirstNote();
		Assertions.assertEquals(modifiedNoteTitle, note.getTitle());
		Assertions.assertEquals(modifiedNoteDescription, note.getDescription());
	}

	private void createNote(String noteTitle, String noteDescription, HomePageTests homePage) {
		homePage.navToNotesTab();
		homePage.addNewNote();
		homePage.setNoteTitle(noteTitle);
		homePage.setNoteDescription(noteDescription);
		homePage.saveNoteChanges();
		ResultTests resultPage = new ResultTests(driver);
		resultPage.clickOk();
		homePage.navToNotesTab();
	}
}
