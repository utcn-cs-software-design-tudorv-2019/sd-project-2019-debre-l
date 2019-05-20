package com.sec.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.sec.entity.User;

@Service
public class EmailService {
    private final Log log = LogFactory.getLog(this.getClass());
    
	@Value("${spring.mail.username}")
	private String MESSAGE_FROM;
	
	private JavaMailSender javaMailSender;

	@Autowired
	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	public void sendMessage1()
	{
		SimpleMailMessage message = null;
		
		try {
			message=new SimpleMailMessage();
			message.setFrom(MESSAGE_FROM);
			message.setTo("debre.lorand@yahoo.com");
			message.setSubject("ALFA");
			message.setText("BETA");
			javaMailSender.send(message);
		}
		catch (Exception e)
		{
			log.error("Mail error 1 " + "  " + e);
		}
	}
	

	public void sendMessage(User user) {
		SimpleMailMessage message = null;
		
		try {
			message = new SimpleMailMessage();
			message.setFrom(MESSAGE_FROM);
			message.setTo(user.getEmail());
			message.setSubject("You have successfully registered to Travel Agency");
			message.setText("Dear " + user.getName() + "! \n \n Your new Travel Agency account has been created! \n From now on, please log in to your account using your username and password, but first you have to activate your profile using the following link: \n\n http://localhost:8080/activation/"+user.getActivation());
			javaMailSender.send(message);
			
		} catch (Exception e) {
			log.error("Mail error: " + user.getEmail() + "  " + e);
		}
		

	}
	
	
}
