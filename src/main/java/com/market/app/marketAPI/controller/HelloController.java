package com.market.app.marketAPI.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/helloWorld")
	public String helloWorld() {
		return "Hello, I'm Testing my Spring Application";
	}
	
}
