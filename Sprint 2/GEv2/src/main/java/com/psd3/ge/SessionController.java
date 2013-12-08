package com.psd3.ge;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SessionController {
	
	@RequestMapping(value = "/session", method = RequestMethod.GET)
	public String session(Locale locale, Model model) {		
		return "session";
	}
	
	@RequestMapping(value = "/session", method = RequestMethod.POST)
	public void mySession(
			@RequestParam("session_name") String name,
			@RequestParam("session_date") String date,
			@RequestParam("session_time") String time,
			@RequestParam("session_duration") int duration,
			@RequestParam("session_frequency1") int freq1,
			@RequestParam("session_frequency2") String freq2,
			@RequestParam("session_lecturer") String lect_name,
			@RequestParam("session_max_attendance") int max_attendance,
			@RequestParam("session_compulsory") String compulsory,
			@RequestParam("session_venue") String venue
			){
		Main.mySession.add(new Session(name, date, time, freq2, compulsory, venue, duration, freq1, lect_name, max_attendance));
		DateFormat df = new SimpleDateFormat();
		for (int i =0; i < freq1; i++){
			
		}
	}
	

}
