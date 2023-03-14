package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hessian.Hello;

@RestController
@RequestMapping("/hessian")
public class HessianController {

	@Autowired
	private Hello helloClient;

	@RequestMapping(value = "/test", method = { RequestMethod.GET })
	public String test() {
		String response = helloClient.sayHello("hello");
		return response;
	}
}
