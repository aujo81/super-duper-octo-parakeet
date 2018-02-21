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
		assertEquals("Assert Message Content", testMessage1.getMessage(), "Hello World!");
		assertEquals("Assert Message Content", testMessage1.getAuthor(), "Johan");
				
		//Second message
		assertEquals("Assert Id", testMessage2.getId(), 2L);
		assertEquals("Assert Message Content", testMessage2.getMessage(), "Hello Jersey!");
		assertEquals("Assert Message Content", testMessage2.getAuthor(), "Aujo");						
	}
	
	@Test
	public void testGetAllMessages() {
		assertEquals("Assert size", 3, ms.getAllMessages().size());		
	}
	
	@Test 
	public void testUpdateMessage() {
		long id = 1;
		String messageContent = "Hello World";
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
	
	@Test
	public void testGetMessagesForYear() {
		int expectedYear = 2018;
		assertEquals("Assert messages for year 2018",ms.getAllMessagesForYear(expectedYear).size(), 3);
		
		assertEquals("Assert messages for year 2016",ms.getAllMessagesForYear(expectedYear+1).size(), 0);
		assertEquals("Assert messages for year 2016",ms.getAllMessagesForYear(expectedYear-1).size(), 0);
		
		assertEquals("Assert messages for year 2016",ms.getAllMessagesForYear(expectedYear+1000).size(), 0);
		assertEquals("Assert messages for year 2016",ms.getAllMessagesForYear(expectedYear-1000).size(), 0);
	}
	
	@Test
	public void testGetAllMassagesPaginated() {
		
		//Setup (add 10 new messages)
		for(int i=2;i<12;i++) {
			ms.addMessage(new Message(i, "Message #"+i, "Me"));
		}
		assertEquals("Assert successful Setup()", 12, ms.getAllMessages().size());
		
		//Actual tests...
		assertEquals("Assert 5 page response", 5, ms.getAllMessagesPaginated(0, 5).size());
		assertEquals("Assert 10 page response", 10, ms.getAllMessagesPaginated(1, 10).size());
		assertEquals("Assert 12 (all) page response", 12, ms.getAllMessagesPaginated(0, 12).size());		
		assertEquals("Assert empty response", 0, ms.getAllMessagesPaginated(1, 12).size());		
		
		//Tear down (remove the created messages
		for(int a=2;a<12;a++) {			
			ms.removeMessage(a);			
		}
		assertEquals("Assert successful TearDown()", 2, ms.getAllMessages().size());		
	}
	

}
