package com.policy.Utility;

import java.util.Scanner;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
public class Test extends Normal_Methods
{
	public static void main(String[] args) throws Exception
	{
//		General_Methods g=new General_Methods();
//		g.readDataFromExcel("Login");
		
		Properties props = new Properties();        
		  props.setProperty("mail.imap.ssl.enable", "true");     
		  Session mailSession = Session.getInstance(props); 
		  mailSession.setDebug(true);
		  Store mailStore = mailSession.getStore("imap");
		  mailStore.connect("outlook.office365.com", "pravinshetty125@outlook.com", "Welcome@12");
		  
		  
		  Folder folder = mailStore.getFolder("INBOX");
		   folder.open(Folder.READ_ONLY);
		    
		    System.out.println("Total Message:" + folder.getMessageCount());
		    System.out.println("Unread Message:" + folder.getUnreadMessageCount());
		    Message[] messages = folder.getMessages();

		    for (Message mail : messages) {
		            System.out.println("*********************************");
		            System.out.println("MESSAGE : \n");

		            System.out.println("Subject: " + mail.getSubject());
		            System.out.println("From: " + mail.getFrom()[0]);
		            System.out.println("To: " + mail.getAllRecipients()[0]);
		            System.out.println("Date: " + mail.getReceivedDate());
		            System.out.println("Size: " + mail.getSize());
		            //System.out.println("Flags: " + mail.getFlags());
		            System.out.println("ContentType: " + mail.getContentType());
		            System.out.println("Body: \n" + getTextFromMessage(mail));
		            //System.out.println("Body is "+mail.getContent());
		            System.out.println("*******************************");          
		            
		    }
	}	
	
	private static String getTextFromMessage(Message message) throws MessagingException, IOException {
	    String result = "";
	    if (message.isMimeType("text/plain")) {
	        result = message.getContent().toString();
	    } else if (message.isMimeType("multipart/*")) {
	        MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
	        result = getTextFromMimeMultipart(mimeMultipart);
	    }
	    return result;
	}

	private static String getTextFromMimeMultipart(
	        MimeMultipart mimeMultipart)  throws MessagingException, IOException{
	    String result = "";
	    int count = mimeMultipart.getCount();
	    for (int i = 0; i < count; i++) {
	        BodyPart bodyPart = mimeMultipart.getBodyPart(i);
	        if (bodyPart.isMimeType("text/plain")) {
	            result = result + "\n" + bodyPart.getContent();
	            break; // without break same text appears twice in my tests
	        } else if (bodyPart.isMimeType("text/html")) {
	            String html = (String) bodyPart.getContent();
	            result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
	        } else if (bodyPart.getContent() instanceof MimeMultipart){
	            result = result + getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent());
	        }
	    }
	    return result;
	}
}