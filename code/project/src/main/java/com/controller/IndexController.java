package com.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.entities.Client;
import com.servicies.ClientService;

@Controller
public class IndexController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Inject
	private ClientService clientService;
	
	@RequestMapping("/")
	public String home(){
		return "index";
	}
	
	@RequestMapping("/registration")
	public String registration(Model model){
		model.addAttribute("client", new Client());
		
		return "registration";
	}
	
//	@RequestMapping(value = "/reg", method = RequestMethod.POST)
	@PostMapping("/reg")
    public String greetingSubmit(@ModelAttribute Client client) {
		System.out.println("UJ USER");
		log.info("Uj user!");
//		log.debug(user.getUsername());
//		log.debug(user.getPassword());
        return "auth/login";
    }

}