package com.lilly;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MyServlet extends HttpServlet{

	
	public void doGet(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException
{
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		
		String cname=request.getParameter("cname");
		
		String cnumber=request.getParameter("cnumber");
		
		String cemail=request.getParameter("cemail");
		
		String ctextArea=request.getParameter("ctextArea");
		
			
		 	final String username = "lillyscateringservice@gmail.com";
			final String password = "lilly@123";
		
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			
			Session session = Session.getInstance(props,
					  new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(username, password);
						}
					 });
			 try{  
		         MimeMessage message = new MimeMessage(session);  
		         message.setFrom(new InternetAddress("lillyscateringservice@gmail.com"));  
		         message.addRecipient(Message.RecipientType.TO,new InternetAddress("lillyscateringservice@gmail.com"));  
		         message.setSubject("Booking Mail From:"+cname+" MyContact Number "+cnumber);  
		         message.setText(ctextArea);  
		  
		         // Send message  
		         
		       
		         Transport.send(message);  
		         //System.out.println("message sent successfully...."); 
		         message.addRecipient(Message.RecipientType.BCC,new InternetAddress(cemail));
		         message.setSubject("Confirmation Mail");  
		         /*String Mess="Hi"+cname+"  "
					        + "This Mail will Confirm our arrangements for your order"+"  "
					+"Thanks & Regards"+"  "
					 +"Admin".replaceAll("  +","  ");*/
		         String Mess="Hi"+" "+cname+",\n"
		        		           +"          "+"     "+ "This Mail will Confirm our arrangements for your order \n"
		        		 +"\n"
		        		                                                            +"Thanks & Regards\n"
		        		 +"\n"
		        		 +"Admin";
		         message.setText(Mess);
		         /*String[] str = Mess.split("  ");
		         for(String temp: str){
		        	 message.setText(temp);
				    }*/
		         Transport.send(message);  
		         //System.out.println("message sent successfully....");
		      }catch (MessagingException mex) {mex.printStackTrace();}  
			 response.sendRedirect("index.html");
		
		
}
}
