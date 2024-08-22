package com.udacity.thangnm1.superduperdrive.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignInController {

	 @GetMapping()
	    public String loginView() {
	        return "login";
	    }
}
