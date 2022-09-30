package com.playdata.petCommunity.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.playdata.petCommunity.command.UserVO;
import com.playdata.petCommunity.user.service.UserService;

@Controller
@RequestMapping("/maps")
public class MapsController {

	@Autowired
	@Qualifier("userService")
	UserService userService;
	
	@RequestMapping("map")
	public String map(HttpSession session, Model model) {
		
		String userId = (String) session.getAttribute("userId");
				
		UserVO vo = userService.getUser(userId);
		
		model.addAttribute("vo", vo);
		
		return "maps/map";
	}
	
}
