package com.psd3.ge;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignUpController {
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String session(Locale locale, Model model) {
		if (Main.login && Main.userType.equals("user")){
			model.addAttribute("user_msg","<h3>Welcome "+Main.name+"!</h3>");
			model.addAttribute("user_menu_1","<li><a href=\"timetable\">View Timetable</a></li>");
			model.addAttribute("user_menu_2","<li><a href=\"signup\">Session Sign Up</a></li>");
			String listOfItem = "";
			int c = 0;
			for (Map.Entry<String, ArrayList<Session>> entry : Main.mySession.entrySet()){
				if (!Main.listOfUser.get(Main.username).getMyAttendSession().contains(entry.getKey())){
					if (Main.mySession.get(entry.getKey()).get(0).isFull()){
						listOfItem += "<tr><td><input type=\"checkbox\" disabled=\"disabled\" name=\"signup_session[]\" value=\""+entry.getKey()+"\">"+Main.mySession.get(entry.getKey()).get(0).toSignUp()+"</td></tr>";
					}else if (Main.mySession.get(entry.getKey()).get(0).getCompulsory().equals("Yes")){
						listOfItem += "<tr><td><input type=\"checkbox\" checked=\"checked\" name=\"signup_session[]\" value=\""+entry.getKey()+"\">"+Main.mySession.get(entry.getKey()).get(0).toSignUp()+"</td></tr>";
						c++;
					}else{
						listOfItem += "<tr><td><input type=\"checkbox\" name=\"signup_session[]\" value=\""+entry.getKey()+"\">"+Main.mySession.get(entry.getKey()).get(0).toSignUp()+"</td></tr>";
						c++;
					}
				}
			}
			if (c > 0){
				model.addAttribute("signup_button", "<input type=\"submit\" value=\"Sign Up\"/>");
			}else{
				model.addAttribute("signup_button", "<p>No new or possible session to sign up.</p>");
			}
			model.addAttribute("listOfSession", listOfItem);
			return "signup";
		}else if (!Main.login){
			return "redirect:/login";
		}else if (!Main.userType.equals("admin")){
			return "redirect:/login";	
		}else{
			return "redirect:/login";
		}
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String mySession(Model model, @RequestParam("signup_session[]") String[] signUpSession){
		model.addAttribute("user_msg","<h3>Welcome "+Main.name+"!</h3>");
		model.addAttribute("user_menu_1","<li><a href=\"timetable\">View Timetable</a></li>");
		model.addAttribute("user_menu_2","<li><a href=\"signup\">Session Sign Up</a></li>");
		for (String s : signUpSession){
			if(s.equals("")){
				break;
			}
			Main.listOfUser.get(Main.username).getMyAttendSession().add(s);
			for (Session session : Main.mySession.get(s)){
				session.addAttendance();
			}
		}
		Main.saveMyAccount();
		Main.saveMySessionData();
		return "redirect:/signup";
	}
}
