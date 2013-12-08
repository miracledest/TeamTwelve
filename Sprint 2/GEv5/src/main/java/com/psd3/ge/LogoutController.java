package com.psd3.ge;

import java.util.Locale;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogoutController {
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		Main.login = false;
		Main.userType = "";
		return "redirect:/login";
	}
	
}
