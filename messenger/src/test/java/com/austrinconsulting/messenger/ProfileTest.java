package com.austrinconsulting.messenger;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import com.austrinconsulting.messenger.model.Profile;

public class ProfileTest {


	@Test
	public void testProfile() {
		long id = 1000;
		String profileName = "Tester"; 
		String firstName = "Tore";
		String lastName = "Testledare";
		
		Profile profile = new Profile(id, profileName, firstName, lastName);
		Date date = new Date();
		
		assertEquals("Assert profile Id", profile.getId(),id);
		assertEquals("Assert profile content", profile.getProfileName(),profileName);
		assertEquals("Assert first name", profile.getFirstName(),firstName);
		assertEquals("Assert last name", profile.getLastName(),lastName);
		assertEquals("Assert Date", 0, date.compareTo(profile.getCreated()));
		
	}

}
