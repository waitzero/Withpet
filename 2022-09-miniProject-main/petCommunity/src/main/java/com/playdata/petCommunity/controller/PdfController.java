package com.playdata.petCommunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.playdata.petCommunity.command.UserVO;
import com.playdata.petCommunity.repository.PetRepository;
import com.playdata.petCommunity.repository.UserRepository;
import com.playdata.petCommunity.util.font.Font;

@Controller
@RequestMapping("/pdf")
public class PdfController {

	@Autowired
	PetRepository petRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping("/pdf")
	public String pdf(Model model) {
		
		model.addAttribute("font", new Font().getFont());
		model.addAttribute("list", petRepository.findAll());
		model.addAttribute("user", userRepository.findByUserId("jji0428"));
		
		return "pdf/pdf";
	}
	
}
