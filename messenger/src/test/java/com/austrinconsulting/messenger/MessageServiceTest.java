package com.austrinconsulting.messenger;

import static org.junit.Assert.*;

import org.junit.Test;

import com.austrinconsulting.messenger.model.Message;
import com.austrinconsulting.messenger.service.MessageService;

public class MessageServiceTest {
	
	private MessageService ms = new MessageService();

	@Test
	public void testMessageService() {
		Message testMessage1 = ms.getMessage(1);
		Message testMessage2 = ms.getMessage(2);
		
		//First message
		assertEquals("Assert Id", testMessage1.getId(), 1L);
		assertEquals("Assert Message Content", testMessage1.getMessage(), "Hellow World!");
		assertEquals("Assert Message Content", testMessage1.getAuthor(), "Johan");
				
		//Second message
		assertEquals("Assert Id", testMessage2.getId(), 2L);
		assertEquals("Assert Message Content", testMessage2.getMessage(), "Hello Jersey!");
		assertEquals("Assert Message Content", testMessage2.getAuthor(), "Aujo");						
	}
	
	@Test
	public void testGetAllMessages() {
		assertEquals("Assert size", ms.getAllMessages().size(), 2);		
	}
	
	@Test 
	public void testUpdateMessage() {
		long id = 1;
		String messageContent = "Hello World!";
		String author = "Johan A";
		Message updatedMessage = new Message(id, messageContent, author);
		ms.updateMessage(updatedMessage);
				
		assertEquals("Assert Id", ms.getMessage(id).getId(), id);
		assertEquals("Assert Message Content", ms.getMessage(id).getMessage(), messageContent);
		assertEquals("Assert Message Content", ms.getMessage(id).getAuthor(), author);
	}
	
	@Test
	public void testAddMessage() {
		long id = 3;
		String messageContent = "Hello jUnit!";
		String author = "Johan";
		Message newMessage = new Message(id, messageContent, author);
		
		ms.addMessage(newMessage);
		
		assertEquals("Assert size", ms.getAllMessages().size(), 3);
		assertEquals("Assert Id", ms.getMessage(id).getId(), id);
		assertEquals("Assert Message Content", ms.getMessage(id).getMessage(), messageContent);
		assertEquals("Assert Message Content", ms.getMessage(id).getAuthor(), author);		
	}
	
	@Test
	public void testRemoveMessage() {
		long id = 3;
		ms.removeMessage(id);
		
		assertEquals("Assert size", ms.getAllMessages().size(), 2);
	}
	

}
