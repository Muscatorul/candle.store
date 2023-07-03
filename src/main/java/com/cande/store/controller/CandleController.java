package com.cande.store.controller;
import com.cande.store.dto.CandleDto;
import com.cande.store.entity.Candle;
import com.cande.store.service.CandleService;
import com.cande.store.service.CandleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class CandleController {
@Autowired
	CandleService candleService;
@Autowired
private CandleValidator candleValidator;
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
	@GetMapping("/candels")
	public String viewAllCandles(Model model){
		List<Candle>candleList= candleService.listAllCandels();
		model.addAttribute("candels",candleList);
		return "candels";
	}
	@GetMapping("/candle")
	public String viewCandleForm(Model model){
		CandleDto candleDto=new CandleDto();
		model.addAttribute("candle",candleDto);

		return "candle";
	}
	@PostMapping("/candle/save")
	public String saveCandle(@ModelAttribute("candle") CandleDto candleDto,
							 BindingResult blindingResult,
							 Model model,
							 @RequestParam("coverImage") MultipartFile file) throws IOException {

		candleValidator.validate(candleDto,blindingResult);
		if (blindingResult.hasErrors()){
			model.addAttribute("candle",candleDto);
			return "candle";

	}
		candleService.saveCandle(candleDto,file);
		List<Candle>list=candleService.listAllCandels();
		model.addAttribute("candles",list);
		return "redirect:/candels";


	}
}
