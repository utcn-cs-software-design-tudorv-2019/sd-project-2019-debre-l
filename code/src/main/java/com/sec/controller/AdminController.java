package com.sec.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sec.entity.Hotel;
import com.sec.entity.Location;
import com.sec.entity.Offer;
import com.sec.entity.User;
import com.sec.service.HotelService;
import com.sec.service.LocationService;
import com.sec.service.OfferService;
import com.sec.service.UserService;

@Controller
public class AdminController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
    
	private UserService userService;
    private LocationService locationService;
    private HotelService hotelService;
    private OfferService offerService;
    
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
	
	@Autowired
	public void setPackageService(OfferService offerService)
	{
		this.offerService=offerService;
	}
	
	@RequestMapping("/admin")
	public String initial1()
	{
		return "admin/adminAccount";
	}
	
	@RequestMapping("admin/adminAccount")
 	public String adminAccount(Model model,Authentication authentication) {
		User loggedUser = userService.getByUsername(authentication.getName());
		model.addAttribute("loggeduser_name", loggedUser.getName());
		model.addAttribute("loggeduser_email", loggedUser.getEmail());
		model.addAttribute("loggeduser_nrTel", loggedUser.getNrTel());
		model.addAttribute("loggeduser_cnp", loggedUser.getCnp());
		model.addAttribute("loggeduser_username", loggedUser.getUsername());
		model.addAttribute("loggeduser_address", loggedUser.getAddress());
		model.addAttribute("user", new User());
		return "admin/adminAccount";
	}
	
	@PostMapping("/updatedAdminAccount")
	public String updateAccount(@ModelAttribute User newUser,Authentication authentication)
	{
		log.info("Currently username:"+authentication.getName()+"Account updated:"+newUser);
		User userdata = userService.getByUsername(authentication.getName());
		userService.updateUser(userdata,newUser);
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
		model.addAttribute("locations",locationService.getAllLocations());
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

	@RequestMapping("admin/addOffer")
	public String addOffer(Model model)
	{
		model.addAttribute("offer", new Offer());
		model.addAttribute("hotels", hotelService.getAllHotels());
		return "admin/addOffer";
	}
	
	@RequestMapping("/addOffer")
	public String newOffer(@ModelAttribute Offer offer)
	{
		Hotel hotelOffer = hotelService.getById(offer.getHotel().getIdhotel());
		
		offer.setHotel(hotelOffer);
		
		Offer newOffer = offerService.newOffer(offer);
		log.info("New offer:"+newOffer.getName());
		return "admin/adminAccount";
	}
	
	@RequestMapping("admin/userManage")
	public String userManage(Model model)
	{
		model.addAttribute("alfa", new User());
		Iterable<User> users = userService.getAllRegistredUser();
		model.addAttribute("users",users);
		return "admin/userManage";
	}
	
	@PostMapping("/addAdminRole")
	public String changeRole(@ModelAttribute User userid)
	{
		try {
			User user = userService.getById(userid.getIduser());
			user.addRoles("ADMIN");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "admin/userManage";
	}
}
