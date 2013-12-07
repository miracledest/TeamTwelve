package com.psd3.ge;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class Login {
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		if (Main.login){
			if (Main.userType.equals("admin")){
				return "redirect:/session";
			}else if (Main.userType.equals("user")){
				return "redirect:/timetable";
			}
		}else{
			Main.loadMyAccount();
		}
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model,
			@RequestParam("textboxUser") String username,
			@RequestParam("textboxPw") String password) {
		if (Main.listOfUser.get(username) != null){
			if (Main.listOfUser.get(username).getPassword().equals(password)){
				Main.login = true;
				Main.loadMySessionData();
				Main.userType = Main.listOfUser.get(username).getUserType();
				Main.name = Main.listOfUser.get(username).getName();
				Main.username = Main.listOfUser.get(username).getUsername();
				if (Main.listOfUser.get(username).getUserType().equals("admin")){
					return "redirect:/session";
				}else{
					return "redirect:/timetable";
				}
			}
		}
		model.addAttribute("error", "Invalid Username or Password!");
		return "login";
	}
}
