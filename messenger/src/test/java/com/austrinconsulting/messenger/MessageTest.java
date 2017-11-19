package com.austrinconsulting.messenger;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import com.austrinconsulting.messenger.model.Message;

public class MessageTest {

	@Test
	public void testMessage() {
		long id = 1;
		String messageContent = "My mesage";
		String author = "Me";
		
		Message message = new Message(id,messageContent, author);
		Date date = new Date();
		
		assertEquals("Assert message Id", message.getId(),id);
		assertEquals("Assert message content", message.getMessage(),messageContent);
		assertEquals("Assert Author", message.getAuthor(),author);		
		assertEquals("Assert Date", 0, date.compareTo(message.getCreated()));
	}
	
	 @Test
	 public void testDefaultMessage() {
		 Message message = new Message();
		 Date date = new Date();
		 
		 assertEquals("Assert default message Id", message.getId(),0);
		 assertEquals("Assert default message content", message.getMessage(),"Undefined");
		 assertEquals("Assert default author", message.getAuthor(),"Undefined");
		 assertEquals("Assert Date", 0, date.compareTo(message.getCreated()));
	 }

}
