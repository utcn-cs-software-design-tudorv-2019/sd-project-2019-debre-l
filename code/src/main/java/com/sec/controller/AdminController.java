package com.sec.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sec.entity.Hotel;
import com.sec.entity.Location;
import com.sec.service.EmailService;
import com.sec.service.HotelService;
import com.sec.service.LocationService;
import com.sec.service.UserService;

@Controller
public class AdminController {
private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    private UserService userService;
    private EmailService emailService;
    private LocationService locationService;
    private HotelService hotelService;
    
    @Autowired
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Autowired
	public void setLocationService(LocationService locationService)
	{
		this.locationService=locationService;
	}
	
	@Autowired
	public void setHotelService(HotelService hotelService)
	{
		this.hotelService=hotelService;
	}
	
	@RequestMapping("/admin")
	public String initial1()
	{
		return "admin/adminAccount";
	}
	
	@RequestMapping("admin/adminAccount")
 	public String adminAccount() {
		return "admin/adminAccount";
	}
 
	@RequestMapping("admin/addLocation")
	public String addLocationn(Model model){
	 	model.addAttribute("location", new Location());
		return "admin/addLocation";
	}
 
	@PostMapping("/addLoc")
    public String newLoc(@ModelAttribute Location location) {
		log.info("New location:"+location);
		locationService.newLocation(location);
		//emailService.sendMessage(user);
        return "admin/adminAccount";
    }
	
	@RequestMapping("admin/addHotel")
	public String addHotel(Model model)
	{
		model.addAttribute("hotel",new Hotel());
		return "admin/addHotel";
	}
	
	@PostMapping("/addHotel")
	public String newHotel(@ModelAttribute Hotel hotel)
	{
		Location hotelLocation = locationService.getLocationByID(hotel.getLocation().getIdlocation());
		hotel.setLocation(hotelLocation);
		Hotel newHotel = hotelService.newHotel(hotel);
		log.info("New Hotel:"+newHotel);
		return "admin/adminAccount";
	}
}
