package com.sec.controller;


import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sec.entity.User;
import com.sec.service.EmailService;
import com.sec.service.UserService;


@Controller
public class HomeController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    private UserService userService;
    private EmailService emailService;
    
    @Autowired
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
    
	@RequestMapping("/")
	public String home(){
		return "login";
	}
	
	@RequestMapping(value = "/username", method = RequestMethod.GET)
	@ResponseBody
	public String currentUserName(Authentication authentication) {
		return authentication.getName();
	}
	
	@RequestMapping("/registration")
	public String registration(Model model){
		model.addAttribute("user", new User());
		return "registration";
	}
	
	@PostMapping("/reg")
    public String reg(@ModelAttribute User user) {
		log.info("New user!");
		log.debug(user.getUsername());
		log.debug(user.getEmail());
		log.debug(user.getPassword());
		String activationCode = userService.registerUser(user);
		user.setActivation(activationCode);
		//emailService.sendMessage(user);
        return "auth/login";
    }
	
	@RequestMapping(path = "/activation/{code}", method = RequestMethod.GET)
	public String activation(@PathVariable("code") String code, HttpServletResponse response) {
		log.debug("activation started");
		String result = userService.userActivation(code);
		log.debug("activation terminated:"+result);
		return "auth/login";
	}
}
