package com.cande.store.controller;

import com.cande.store.dto.CandleDto;
import com.cande.store.dto.ChosenCandleDto;
import com.cande.store.entity.Candle;
import com.cande.store.entity.ChosenCandle;
import com.cande.store.service.CandleService;
import com.cande.store.service.CandleValidator;
import com.cande.store.service.ChosenCandleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class CandleController {
    @Autowired
    CandleService candleService;
    @Autowired
    private CandleValidator candleValidator;
	@Autowired
	private ChosenCandleService chosenCandleService;

    @GetMapping("/")
    public String viewTemplate(Model model) {
        boolean authenticated = SecurityContextHolder.getContext().getAuthentication().isAuthenticated();

        if (authenticated) {
            List<Candle> candels = candleService.listAllCandels();
            model.addAttribute("candels", candels);
			ChosenCandleDto chosenCandleDto =  ChosenCandleDto.builder().build();
			model.addAttribute("chosenCandleDto", chosenCandleDto);
            return "candels";
        } else {
            return "login";
        }
    }

    @GetMapping("/candels")
    public String viewAllCandles(Model model) {
        List<Candle> candleList = candleService.listAllCandels();
        model.addAttribute("candels", candleList);
        ChosenCandleDto chosenCandleDto =  ChosenCandleDto.builder().build();
        model.addAttribute("chosenCandleDto", chosenCandleDto);
        return "candels";
    }

    @GetMapping("/candle")
    public String viewCandleForm(Model model) {
        CandleDto candleDto = new CandleDto();
        model.addAttribute("candle", candleDto);

        return "candle";
    }

    @PostMapping("/candle/save")
    public String saveCandle(@ModelAttribute("candle") CandleDto candleDto,
                             BindingResult blindingResult,
                             Model model,
                             @RequestParam("coverImage") MultipartFile file) throws IOException {

        candleValidator.validate(candleDto, blindingResult);
        if (blindingResult.hasErrors()) {
            model.addAttribute("candle", candleDto);
            return "candle";

        }
        candleService.saveCandle(candleDto, file);
        List<Candle> list = candleService.listAllCandels();
        model.addAttribute("candles", list);
        ChosenCandleDto chosenCandleDto =  ChosenCandleDto.builder().build();
        model.addAttribute("chosenCandleDto", chosenCandleDto);
        return "redirect:/candels";
    }
	@PostMapping("/candle/{candleId}")
	public String addToShoppingList(@PathVariable(value = "candleId")String candleId,
									@ModelAttribute ChosenCandleDto candleDto,
									BindingResult bindingResult,
									Model model){
		model.addAttribute("chosenCandleDto",candleDto);
		String email=SecurityContextHolder.getContext().getAuthentication().getName();
		chosenCandleService.addToCart(candleDto,candleId,email);
        return "redirect:/cart";

	}

}
