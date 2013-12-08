package com.psd3.ge;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

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
			model.addAttribute("user_msg","<h3>Welcome "+Main.name+"!</h3>");
			model.addAttribute("user_menu_1","<li><a href=\"timetable\">View Timetable</a></li>");
			model.addAttribute("user_menu_2","<li><a href=\"signup\">Session Sign Up</a></li>");
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
		model.addAttribute("user_msg","<h3>Welcome "+Main.name+"!</h3>");
		model.addAttribute("user_menu_1","<li><a href=\"timetable\">View Timetable</a></li>");
		model.addAttribute("user_menu_2","<li><a href=\"signup\">Session Sign Up</a></li>");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String todayDate = df.format(new Date());
		if (option.equals("today")){
			String listOfItem = "";
			int c = 0;
			for (Map.Entry<String, ArrayList<Session>> entry : Main.mySession.entrySet()){
				if (Main.listOfUser.get(Main.username).getMyAttendSession().contains(entry.getKey())){
					for (Session s : entry.getValue()){
						if (s.getDate().equals(todayDate)){
							listOfItem += s.toDisplay();
							c++;
						}
					}
				}
			}
			model.addAttribute("listOfSession", listOfItem);
			model.addAttribute("timetableHeader", "<h3>Today Session ("+c+")</h3><hr>");			
		}else if (option.equals("week")){
			String listOfItem = "";
			int c = 0;
			Calendar mon = Calendar.getInstance();
			mon.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
			mon.set(Calendar.HOUR_OF_DAY,0);
			mon.set(Calendar.MINUTE,0);
			mon.set(Calendar.SECOND,0);
			mon.add(Calendar.DATE,1);
			
			Calendar sun = Calendar.getInstance();
			sun.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
			sun.set(Calendar.HOUR_OF_DAY,0);
			sun.set(Calendar.MINUTE,0);
			sun.set(Calendar.SECOND,0);
			sun.add(Calendar.DATE,7);
			
			for (Map.Entry<String, ArrayList<Session>> entry : Main.mySession.entrySet()){
				if (Main.listOfUser.get(Main.username).getMyAttendSession().contains(entry.getKey())){
					for (Session s : entry.getValue()){
						try {
							Date temp = df.parse(s.getDate());
							if (temp.compareTo(mon.getTime())>=0 && temp.compareTo(sun.getTime())<=0){
								listOfItem += s.toDisplay();
								c++;
							}
						} catch (ParseException e) {}
						
					}
				}
			}
			model.addAttribute("listOfSession", listOfItem);
			model.addAttribute("timetableHeader", "<h3>This Week Session ("+c+")</h3><hr>");
		}else{
			model.addAttribute("timetableHeader", "<h3>All Session</h3><hr>");
			String listOfItem = "";
			int c = 0;
			for (Map.Entry<String, ArrayList<Session>> entry : Main.mySession.entrySet()){
				if (Main.listOfUser.get(Main.username).getMyAttendSession().contains(entry.getKey())){
					for (Session s : entry.getValue()){
						listOfItem += s.toDisplay();
						c++;
					}
					
				}
			}
			model.addAttribute("timetableHeader", "<h3>All Session ("+c+")</h3><hr>");
			model.addAttribute("listOfSession", listOfItem);			
		}
		return "timetable";
	}
}
