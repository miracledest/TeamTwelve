package com.psd3.ge;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TimetableController {
	
	@RequestMapping(value = "/timetable", method = RequestMethod.GET)
	public String session(Locale locale, Model model) {		
		return "timetable";
	}

}
