package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/mvc")
public class MvcController {

	@RequestMapping(value = "/{name}", method = { RequestMethod.GET })
	public String handle(@PathVariable("name") String name, Model model) {
		model.addAttribute("name", name);
		return "hello";
	}
}
