package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import global.BeanContextHelper;
import service.FirstService;

@RestController
@RequestMapping("/user")
@SuppressWarnings("unused")
public class UserController {

	//@Autowired
	//private FirstService firstService;

	@RequestMapping(value = "/hi", method = { RequestMethod.GET })
	public String index() {
		return "{\"hello\":\"world\"}";
	}
}
