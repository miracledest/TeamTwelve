package com.psd3.ge;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class Login {

	private static String[] Username = { "admin", "tom", "mary", "john" };
	private static String[] Password = { "password1", "password2", "password3",
			"password4" };
	private static int[] type = { 0, 1, 1, 1 };

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		if (Main.login){
			if (Main.userType.equals("admin")){
				return "redirect:/session";
			}else if (Main.userType.equals("user")){
				return "redirect:/timetable";
			}
		}		
		return "login";
	}
	

	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model,
			@RequestParam("textboxUser") String username,
			@RequestParam("textboxPw") String password) {
		for (int i = 0; i < Username.length; i++) {
			if (Username[i].equals(username) && Password[i].equals(password)) {
				Main.login = true;
				Main.loadData();
				if (type[i] == 0){
					Main.userType = "admin";
					Main.userName = "Administrator";
					return "redirect:/session";
				}else{
					Main.userType = "user";
					Main.userName = Username[i].toUpperCase();
					return "redirect:/timetable";
				}
			}
		}
		model.addAttribute("error", "Invalid Username or Password!");
		return "login";
	}
}
