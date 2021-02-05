package com.module.usermgmt.controller;

//import io.swagger.annotations.Api;
//import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

//@Api(tags = "Users API", value = "All the User related API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/users")
public class UserController {

	 @GetMapping("/")
	public String greeting(/*@RequestParam(value = "name", defaultValue = "World") String name*/) {
		return "Vishal";
	}
 
}
