package com.api.contentctrl.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contentctrl/hotm")
public class HotmController {
	
	//http://localhost:8080/contentctrl/hotm 
	
	@GetMapping
    public String getMessage() {
        return "API HOTM work!";
    }
}
