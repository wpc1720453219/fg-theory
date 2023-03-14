package org.ruhr.architecture.http.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/user")
public class TestController {

	@RequestMapping(value = "/hi/{username}", method = { RequestMethod.GET })
	public Object test(@PathVariable String username, HttpServletRequest request, HttpServletResponse response) {
		log.info("test of userController invoked ...,bingo!");
		return "{\"hello\":\"world\"}";
	}

	@RequestMapping(value = "/getvoid/{username}", method = { RequestMethod.GET })
	public void returnVoid(@PathVariable String username, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.info("test of userController invoked ...,bingo!");
//		if(1==1) {
//			throw new Exception("error");
//		}
	}
}
