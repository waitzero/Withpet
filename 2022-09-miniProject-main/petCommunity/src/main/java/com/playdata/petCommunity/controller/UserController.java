package com.playdata.petCommunity.controller;


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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.playdata.petCommunity.command.UserLoginVO;
import com.playdata.petCommunity.command.UserUpdateVO;
import com.playdata.petCommunity.command.UserVO;
import com.playdata.petCommunity.user.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {


	@Autowired
	private UserService userService;
	
	//유저 로그인화면
	@GetMapping("/userLogin")
	public String userLogin() {
		return "user/userLogin";
	}
	
	// 유저 회원가입화면
	@GetMapping("/userJoin")
	public String userJoin() {
		return "user/userJoin";
	}
	
	// 유저 수정화면
	@GetMapping("/userUpdate")
	public String userUpdate() {
		return "user/userUpdateForm";
	}
	
	// 유저회원가입
	//회원가입 성공하면 바로 반려동물 정보입력페이지로
	@PostMapping("/userJoinForm")
	public String userJoinForm(@Valid UserVO vo, Errors errors, RedirectAttributes RA) {
	
		if(errors.hasErrors()) {
			
			RA.addFlashAttribute("msg", errors.getFieldError().getDefaultMessage());
			return "redirect:/user/userJoinForm";
		} else {
			
			UserVO result = userService.userJoin(vo);
			
			if(result != null) { // 유저회원가입성공
				RA.addFlashAttribute("msg", "가입을 축하드립니다");
				return "redirect:/user/userLogin"; // 가입성공 시 로그인창으로
			} else { //회원가입실패
				RA.addFlashAttribute("msg", "가입에 실패했습니다. 입력 내용을 확인해주세요");
				return "redirect:/user/userJoin";
				// 실패하면 다시 회원가입페이지로 가는 return
			}
		}
	}
	
	// 유저로그인
	@PostMapping("/userLoginForm")
	public String userLogin(@Valid UserLoginVO vo, Errors errors, RedirectAttributes RA, HttpSession session) {
	
		// 유저로그인처리
		if(errors.hasErrors()) {
			RA.addFlashAttribute("msg", errors.getFieldError().getDefaultMessage());
			return "redirect:/user/userLogin";
		} else {
			
			UserVO userVO = userService.userLogin(vo);
			
			if(userVO !=null ) { //유저 로그인 성공
				
				session.setAttribute("userId", userVO.getUserId());
				session.setAttribute("userName", userVO.getUserName());
				
				RA.addFlashAttribute("msg", "로그인 성공");
				return "redirect:/notice/main"; //성공 시 메인화면으로 이동
			} else { // 유저 로그인 실패
				RA.addFlashAttribute("msg", "아이디 또는 비밀번호를 확인하세요");
				return "redirect:/user/userLogin"; // 실패 시 다시 로그인화면으로
			}
		}
	}
	
	@RequestMapping("/mypage")
	public String mypage(HttpSession session, Model model) {
		
		String userId = (String) session.getAttribute("userId");
		
		UserVO vo = userService.getUser(userId);
		
		model.addAttribute("vo", vo);
		
		return"user/mypage";
	}
	
	// 유저 회원정보 수정
	@PostMapping("/userUpdateForm")
	public String userUpdateForm(@Valid UserUpdateVO vo , Errors errors, RedirectAttributes RA) {
		
		if(errors.hasErrors()) {
			RA.addFlashAttribute("msg", errors.getFieldError().getDefaultMessage());
			return "redirect:/user/mypage";
		} else {
			if(!vo.getUserNewPw().equals(vo.getUserNewPwCheck())) {
				RA.addFlashAttribute("msg", "비밀번호를 확인해주세요");
				return "redirect:/user/mypage";
			} else {
				
				UserVO check = userService.userUpdate(vo);
				
				if(check == null) {
					RA.addFlashAttribute("msg", "정보 변경도중 문제가 발생했습니다 관리자에게 문의해주세요");
					return "redirect:/user/mypage";
				} else {
					RA.addFlashAttribute("msg", "정상적으로 변경 됐습니다");
					return "redirect:/user/mypage";
				}
			}
			
		}	
	}
	
	// 유저 탈퇴
	@GetMapping("/userDeleteForm")
	public String userDeleteForm(HttpSession session, RedirectAttributes RA) {
		
		String userId = (String) session.getAttribute("userId");
		
		UserVO userVO = userService.userDelete(userId);
		
		if(userVO == null) {
			RA.addFlashAttribute("msg", "탈퇴 도중 문제가 발생했습니다 관리자에게 문의해주세요");
			return "redirect:/user/userUpdate";
		} else if(userVO.getUserState().equals("탈퇴")) {
			RA.addFlashAttribute("msg", "탈퇴 완료 됐습니다");
			session.invalidate();
			return "redirect:/"; // 홈페이지로 리다이렉트
		} else {
			RA.addFlashAttribute("msg", "탈퇴 도중 문제가 발생했습니다 관리자에게 문의해주세요");
			return "redirect:/user/userUpdate";
		}
		
	}
	
	
}
	
