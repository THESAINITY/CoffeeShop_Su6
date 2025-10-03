package com.setec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.setec.entities.Booked;
import com.setec.repos.BookedRepo;
import com.setec.telegrambot.MyTelegramBot;

@Controller
public class MyController {
	
	@GetMapping({"/","/home"})
	public String home(Model mod) {
		Booked booked = new Booked(
				1,"Khemry Munireach",
				"078466145",
				"munireach@gmail.com",
				"10/09/2025",
				"9:00 Am",
				1
				);
		mod.addAttribute("booked",booked);
		return "index";
	}
	
	@GetMapping("/about")
		public String about(){
			return "about";
		}
	
	@GetMapping ("/service")
		public String service(){
			return "service";
		}
	
	@GetMapping ("/menu")
	public String menu(){
		return "menu";
	}
	
	@GetMapping ("/reservation")
	public String reservation(Model mod) {
		Booked booked = new Booked(
				1,"Khemry Munireach",
				"078466145",
				"munireach@gmail.com",
				"10/09/2025",
				"9:00 Am",
				1
				);
		mod.addAttribute("booked",booked);
		return "reservation";
	}
	
	@GetMapping ("/testimonial")
	public String testimonial(){
		return "testimonial";
	}
	
	@GetMapping ("/contact")
	public String contact(){
		return "contact";
	}
	
	@Autowired
	private BookedRepo bookedRepo;
	
	@Autowired
	private MyTelegramBot bot;
	
	@PostMapping ("/success")
	public String success(@ModelAttribute Booked booked){
		bookedRepo.save(booked);
		bot.message("Booking Success 🤩"+"\n"
				+ "🆔 ID : "+(booked.getId()+"\n"+
				"👤 Name : "+(booked.getName()+"\n"+
				"📩 Email : "+(booked.getEmail()+"\n"+
				"🗓 Date : "+(booked.getDate()+"\n"+
				"⏰ Time : "+(booked.getTime()+"\n"+
				"👥 Person : "+(booked.getPerson())))))));
		return "success";
	}


}
