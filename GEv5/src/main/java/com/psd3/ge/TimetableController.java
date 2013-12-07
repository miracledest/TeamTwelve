package com.psd3.ge;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TimetableController {
	
	@RequestMapping(value = "/timetable", method = RequestMethod.GET)
	public String session(Locale locale, Model model) {
		if (Main.login && Main.userType.equals("user")){
			model.addAttribute("user_msg","<h3>Welcome "+Main.userName+"!</h3>");
			model.addAttribute("user_menu_1","<li><a href=\"timetable\">View Timetable</a></li>");
			model.addAttribute("timetableHeader", "<h3>Select A View</h3>");
			return "timetable";
		}else if (!Main.login){
			return "redirect:/login";
		}else if (!Main.userType.equals("admin")){
			return "redirect:/login";	
		}else{
			return "redirect:/login";
		}
	}
	
	@RequestMapping(value = "/timetable", method = RequestMethod.POST)
	public String mySession(Model model, @RequestParam("view_option") String option){
		model.addAttribute("user_msg","<h3>Welcome "+Main.userName+"!</h3>");
		model.addAttribute("user_menu_1","<li><a href=\"timetable\">View Timetable</a></li>");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String todayDate = df.format(new Date());
		if (option.equals("today")){
			model.addAttribute("timetableHeader", "<h3>Today Session</h3><hr>");
			String listOfItem = "";
			int count = 0;
			for (Session s : Main.mySession){
				if (s.getDate().equals(todayDate)){
					listOfItem += s.toDisplay();
					count++;
				}
			}
			if (count > 0){
				model.addAttribute("listOfSession", "<ul>"+listOfItem+"</ul>");
			}else{
				model.addAttribute("listOfSession", "No Session Available");
			}
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
