package com.cande.store.controller;
import com.cande.store.entity.Candle;
import com.cande.store.service.CandleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CandleController {
@Autowired
	CandleService candleService;
	@GetMapping("/")
	public String viewTemplate(Model model){
		boolean authenticated = SecurityContextHolder.getContext().getAuthentication().isAuthenticated();

		if(authenticated){
			List<Candle> candels = candleService.listAllCandels();
			model.addAttribute("candels", candels);
//			ChosenItemDto chosenItemDto = new ChosenItemDto();
//			model.addAttribute("chosenItemDto", chosenItemDto);
			return "candels";
		}else{
			return "login";
		}
	}
	@GetMapping("/candles")
	public String viewAllCandles(Model model){
		List<Candle>candleList= candleService.listAllCandels();
		model.addAttribute("candels",candleList);
		return "candels";
	}
}
