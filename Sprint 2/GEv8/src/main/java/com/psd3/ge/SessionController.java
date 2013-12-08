package com.psd3.ge;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SessionController {

	@RequestMapping(value = "/session", method = RequestMethod.GET)
	public String session(Locale locale, Model model) {
		if (Main.login && Main.userType.equals("admin")){
			model.addAttribute("admin_msg","<h3>Welcome "+Main.name+"!</h3>");
			model.addAttribute("admin_menu_1","<li><a href=\"session\">Session Setup</a></li>");
			return "session";
		}else if (!Main.login){
			return "redirect:/login";
		}else if (!Main.userType.equals("admin")){
			return "redirect:/login";
		}else{
			return "redirect:/login";
		}
		
	}

	@RequestMapping(value = "/session", method = RequestMethod.POST)
	public void mySession(Model model, @RequestParam("session_name") String name,
			@RequestParam("session_date") String date,
			@RequestParam("session_time") String time,
			@RequestParam("session_duration") int duration,
			@RequestParam("session_frequency1") int freq1,
			@RequestParam("session_frequency2") String freq2,
			@RequestParam("session_lecturer") String lect_name,
			@RequestParam("session_max_attendance") int max_attendance,
			@RequestParam("session_compulsory") String compulsory,
			@RequestParam("session_venue") String venue) {
		model.addAttribute("admin_msg","<h3>Welcome "+Main.name+"!</h3>");
		model.addAttribute("admin_menu_1","<li><a href=\"session\">Session Setup</a></li>");
		if (Main.mySession.get(name.toUpperCase())==null){
			Main.mySession.put(name.toUpperCase(), new ArrayList<Session>());
		}
		String errorMsg ="Fail to create the follow session due to session overlapping.\\n\\n";
		boolean error = false;
		boolean Session = true;
		for (Map.Entry<String, ArrayList<Session>> myS : Main.mySession.entrySet()){
			for (Session s : myS.getValue()){
				if (s.getDate().equals(date) && s.getVenue().equals(venue) && getTimeInMin(time) >= getTimeInMin(s.getTime()) && getTimeInMin(time) < (getTimeInMin(s.getTime()) + s.getDuration())){
					Session = false;
					error = true;
					errorMsg += "Create   "+name.toUpperCase()+" - "+date+" "+time+" "+duration+"min(s) "+venue+"\\n"+s.toConflict();
					break;
				}
			}
			if (!Session){
				break;
			}
		}
		if (Session){
			Main.mySession.get(name.toUpperCase()).add(new Session(name.toUpperCase(), date, time, freq2, compulsory,	venue, duration, freq1, lect_name, max_attendance, 0));	
		}
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		for (int i = 0; i < freq1; i++) {
			try {c.setTime(df.parse(date));} catch (ParseException e) {}
			if (freq2.equals("week")) {
				c.add(Calendar.DATE, (i+1)*7);
			}
			if (freq2.equals("month")) {
				c.add(Calendar.MONTH, i+1);
			}
			if (freq2.equals("year")) {
				c.add(Calendar.YEAR, i+1);
			}
			Session = true;
			for (Map.Entry<String, ArrayList<Session>> myS : Main.mySession.entrySet()){
				for (Session s : myS.getValue()){
					if (s.getDate().equals(df.format(c.getTime()).toString()) && s.getVenue().equals(venue) && getTimeInMin(time) >= getTimeInMin(s.getTime()) && getTimeInMin(time) < (getTimeInMin(s.getTime()) + s.getDuration())){
						Session = false;
						error = true;
						errorMsg += "Create   "+name.toUpperCase()+" - "+df.format(c.getTime()).toString()+" "+time+" "+duration+"min(s) "+venue+"\\n"+s.toConflict();
						break;
					}
				}
				if (!Session){
					break;
				}
			}
			if (Session){
				Main.mySession.get(name.toUpperCase()).add(new Session(name.toUpperCase(), df.format(c.getTime()).toString(), time, freq2, compulsory,
						venue, duration, freq1, lect_name, max_attendance, 0));	
			}
			
			
		}
		Main.saveMySessionData();
		if (error){
			errorMsg += "\\nPlease to manual reschedule the above session.";
			model.addAttribute("session_script", "alert(\""+errorMsg+"\");");
		}else{
			model.addAttribute("session_script", "alert(\"All Sessions Created Successfully\");");
		}
		
	}
	
	private static int getTimeInMin(String time){
		int hour = Integer.parseInt(time.split(":")[0]);
		int min = Integer.parseInt(time.split(":")[1]);
		return hour * 60 + min; 
	}

}
