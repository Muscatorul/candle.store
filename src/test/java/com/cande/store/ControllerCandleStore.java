package com.cande.store;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class ControllerCandleStore {
	@GetMapping("/")
	public String viewTemplate(){
		return "login";
	}
}
