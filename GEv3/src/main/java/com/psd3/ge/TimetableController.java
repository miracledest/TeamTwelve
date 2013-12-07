package com.psd3.ge;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TimetableController {
	
	private enum option {
	   today, week, all;
	}
	
	@RequestMapping(value = "/timetable", method = RequestMethod.GET)
	public String session(Locale locale, Model model) {
		model.addAttribute("timetableHeader", "<h3>Select A View</h3>");
		return "timetable";
	}
	
	@RequestMapping(value = "/timetable", method = RequestMethod.POST)
	public String mySession(Model model, @RequestParam("view_option") String option){
		if (option.equals("today")){
			model.addAttribute("timetableHeader", "<h3>Today Session</h3><hr>");
		}else if (option.equals("week")){
			model.addAttribute("timetableHeader", "<h3>This Week Session</h3><hr>");
		}else{
			model.addAttribute("timetableHeader", "<h3>All Session</h3><hr>");
			String listOfItem = "";
			if (Main.mySession.size() > 0){
				for (Session s : Main.mySession){
					listOfItem += s.toDisplay();
				}
				model.addAttribute("listOfSession", "<ul>"+listOfItem+"</ul>");
			}else{
				model.addAttribute("listOfSession", "No Session Available");
			}
			
		}
		return "timetable";
	}
}
