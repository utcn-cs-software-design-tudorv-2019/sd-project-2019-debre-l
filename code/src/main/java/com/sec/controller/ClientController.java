package com.sec.controller;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.sec.entity.Booking;
import com.sec.entity.Hotel;
import com.sec.entity.Location;
import com.sec.entity.Offer;
import com.sec.entity.User;
import com.sec.service.BookingService;
import com.sec.service.HotelService;
import com.sec.service.LocationService;
import com.sec.service.OfferService;
import com.sec.service.UserService;

@Controller
public class ClientController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
    
	private UserService userService;
    private LocationService locationService;
    private HotelService hotelService;
    private OfferService offerService;
    private BookingService bookingService;
	
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
	
	@Autowired
	public void setBookingService(BookingService bookingService)
	{
		this.bookingService=bookingService;
	}
	
	@RequestMapping("/client")
	public String initial1()
	{
		return "client/clientAccount";
	}
	
	@RequestMapping("client/clientAccount")
 	public String adminAccount(Model model,Authentication authentication) {
		User loggedUser = userService.getByUsername(authentication.getName());
		model.addAttribute("loggeduser_name", loggedUser.getName());
		model.addAttribute("loggeduser_email", loggedUser.getEmail());
		model.addAttribute("loggeduser_nrTel", loggedUser.getNrTel());
		model.addAttribute("loggeduser_cnp", loggedUser.getCnp());
		model.addAttribute("loggeduser_username", loggedUser.getUsername());
		model.addAttribute("loggeduser_address", loggedUser.getAddress());
		model.addAttribute("user", new User());
		return "client/clientAccount";
	}
	
	@PostMapping("/updatedClientAccount")
	public String updateAccount(@ModelAttribute User newUser,Authentication authentication)
	{
		log.info("Currently username:"+authentication.getName()+"Account updated:"+newUser);
		User userdata = userService.getByUsername(authentication.getName());
		userService.updateUser(userdata,newUser);
		return "client/clientAccount";
	}
	
	@RequestMapping("client/offers")
	public String offersPage(Model model)
	{
		model.addAttribute("location", new Location());
		model.addAttribute("booking", new Booking());
		return "client/offers";
	}
	
	@PostMapping("offers")
	public String displayOffers(@ModelAttribute Location location,@ModelAttribute Booking booking, Model model)
	{
		
		Location locationDatabase = locationService.getLocationByCity(location.getCity());
		Iterable<Hotel> hotels = hotelService.getAllHotelsByLocation(locationDatabase);
		List<Offer> ofertele = offerService.getAllOffersByHotels(hotels);
		model.addAttribute("ofertele",ofertele);
		
		
		return "client/offers";
	}
	
	
	@PostMapping("reservationMaked")
	public String reservationMaked(@ModelAttribute Booking booking,@ModelAttribute Location location,Authentication authentication)
	{
		try {
			Offer ofertaDeRezervat = offerService.getOfferById(booking.getBookedOffer().getIdoffer());
			User user = userService.getByUsername(authentication.getName());
			Calendar calendar = Calendar.getInstance();
			Booking bookingSaveDB = new Booking(user,ofertaDeRezervat,booking.getQuantity(),new Date(calendar.getTime().getTime()));
			Booking rezervareSalvat = bookingService.newBooking(bookingSaveDB);
			ofertaDeRezervat.setStock(ofertaDeRezervat.getStock()-rezervareSalvat.getQuantity());
			offerService.newOffer(ofertaDeRezervat);
			log.info("New reservation:"+rezervareSalvat);
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "client/offers";
	}
	
	@RequestMapping("client/mybookings")
	public String mybookingsPage(Authentication authentication,Model model)
	{
		User user = userService.getByUsername(authentication.getName());
		List<Booking> userBookings = bookingService.getAllBookingByUser(user);
		for(Booking b : userBookings)
		{
			System.out.println(b+b.getBookedOffer().getHotel().getLocation().getCity());
			
		}
		model.addAttribute("bookings", userBookings);
		return "client/mybookings";
	}
	
	@RequestMapping("client/hotelDetail")
	public String hotelDetailPage(Model model)
	{
		Iterable<Hotel> hotels = hotelService.getAllHotels();
		model.addAttribute("hotels", hotels);
		model.addAttribute("hotel", new Hotel());
		return "client/hotelDetail";
	}
	
	@PostMapping("/gethotelname")
	public String displayHotelMap(@ModelAttribute Hotel hotel,Model model)
	{
		try {
			Hotel hotelDB = hotelService.getById(hotel.getIdhotel());
			String addressGoogleMaps = hotelDB.getLocation().getCountry()+", "+hotelDB.getLocation().getCity()+", "+hotelDB.getAddress()+", "+hotelDB.getName();
			GeoApiContext context = new GeoApiContext.Builder()
				    .apiKey("AIzaSyD8t5CIirM8Oa8im5JR51CUvcuWfgdAXvA")
				    .build();
			GeocodingResult[] results =  GeocodingApi.geocode(context,addressGoogleMaps).await();
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				System.out.println("Address:"+addressGoogleMaps);
				System.out.println(gson.toJson(results[0].geometry.location));
				model.addAttribute("latt", results[0].geometry.location.lat);
				model.addAttribute("lngg",results[0].geometry.location.lng);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return "client/map";
	}
	
	@RequestMapping("client/map")
	public String asd()
	{
		
		return "client/map";
	}
	
}

