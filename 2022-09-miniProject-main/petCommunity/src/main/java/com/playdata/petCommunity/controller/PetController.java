package com.playdata.petCommunity.controller;


import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.playdata.petCommunity.command.PetVO;
import com.playdata.petCommunity.pet.service.PetService;
import com.playdata.petCommunity.repository.UserRepository;
import com.playdata.petCommunity.user.service.UserService;
import com.playdata.petCommunity.user.service.UserServiceImpl;
import com.playdata.petCommunity.util.font.Font;


@Controller
@RequestMapping("/pet")
public class PetController {

	@Autowired
	private PetService petService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("petSignup")
	public String pet_signup() {
		return"pet/pet_signup";
	}
	
	// 반려동물정보 입력화면으로 이동
	@GetMapping("/petJoin")
	public String join() {
		return "pet/petJoinForm";
	}
	
	// 반려동물정보 입력화면
	@PostMapping("/petJoinForm")
	public String petJoinForm(@Valid PetVO petVO, Errors errors, HttpSession session , RedirectAttributes RA) {
		
		if(errors.hasErrors()) {
			RA.addAttribute("msg", errors.getFieldError().getDefaultMessage());
			return "redirect:/pet/viewPetList"; // 마이 페이지
		} else {
			String userId = (String) session.getAttribute("userId");
			
			PetVO result = petService.petJoin(petVO, userId);
			
			if(result != null) { //반려동물 정보입력성공
				RA.addFlashAttribute("msg", "반려동물의 정보가 입력되었습니다");
				return "redirect:/pet/viewPetList"; // 마이 페이지
			} else {
				RA.addFlashAttribute("msg", "등록 과정에 문제가 생겼습니다 관리자에게 문의해주세요");
				return "redirect:/pet/viewPetList"; // 펫 등록 페이지 
			}
		}
		
		
	}
	
	@PostMapping("/petUpdateForm")
	public String petUpdateForm(@Valid PetVO petVO,Errors errors , HttpSession session, RedirectAttributes RA) {
		
		if(errors.hasErrors()) {
			RA.addAttribute("msg", errors.getFieldError().getDefaultMessage());
			return "redirect:/pet/viewPetList"; // 마이 페이지
		} else {
			String userId = (String) session.getAttribute("userId");
			
			PetVO result = petService.petUpdate(petVO, userId);
			
			if(result != null) { //반려동물 정보입력성공
				RA.addFlashAttribute("msg", "반려동물의 정보가 변경되었습니다");
				return "redirect:/pet/viewPetList"; // 마이 페이지
			} else {
				RA.addFlashAttribute("msg", "등록 과정에 문제가 생겼습니다 관리자에게 문의해주세요");
				return "redirect:/pet/viewPetList"; // 펫 등록 페이지 
			}
		}
		
		
	}
	
	@PostMapping("/petDelete")
	public String petDelete(PetVO petVO, HttpSession session, RedirectAttributes RA) {
		
		String userId = (String) session.getAttribute("userId");
		
		PetVO result = petService.petDelete(petVO, userId);
		
		if(result != null) { //반려동물 정보입력성공
			RA.addFlashAttribute("msg", "반려동물의 정보가 삭제되었습니다");
			return "redirect:/pet/viewPetList"; // 마이 페이지
		} else {
			RA.addFlashAttribute("msg", "삭제 과정에 문제가 생겼습니다 관리자에게 문의해주세요");
			return "redirect:/pet/viewPetList"; // 펫 등록 페이지 
		}
		
	}
	
	@GetMapping("/viewPetList")
	public String viewPetList(HttpSession session,Model model) {
		
		String userId = (String) session.getAttribute("userId");
		
		List<PetVO> list = petService.getPetList(userId);
		
		model.addAttribute("list", list);
		
		return "pet/petlist";
	}
	
	@GetMapping("/pdfPet")
	public String pdfPet(Model model, HttpSession session) {
			
		String userId = (String) session.getAttribute("userId");
		
		model.addAttribute("font", new Font().getFont());
		model.addAttribute("list", petService.getPetList(userId));
		model.addAttribute("user", userService.getUser(userId));
			
		return "pdf/pdf";
	}
	@RequestMapping("/petmodify")
	public String petmodify(@RequestParam("pno") Long pno, Model model) {
		
		PetVO vo = petService.getPetDetail(pno);
		
		model.addAttribute("vo", vo);
		
		return"pet/petmodify";
	}
	@RequestMapping("/petreg")
	public String petreg() {
		return"/pet/petreg";
	}
}
