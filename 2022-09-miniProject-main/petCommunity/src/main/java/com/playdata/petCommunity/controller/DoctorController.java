package com.playdata.petCommunity.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.playdata.petCommunity.command.DoctorLoginVO;
import com.playdata.petCommunity.command.DoctorUpdateVO;
import com.playdata.petCommunity.command.DoctorVO;
import com.playdata.petCommunity.doctor.service.DoctorService;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;

	// 의사 로그인화면
	@GetMapping("/doctorLogin")
	public String doctorLogin() {
		return "doctor/doctorLogin";
	}

	// 의사 회원가입화면
	@GetMapping("/doctorJoin")
	public String doctorJoin() {
		return "doctor/doctorJoin";
	}

	// 의사회원가입
	@PostMapping("/doctorJoinForm")
	public String doctorJoinForm(@Valid DoctorVO vo, Errors errors, RedirectAttributes RA) {

		if (errors.hasErrors()) {
			
			RA.addFlashAttribute("msg", errors.getFieldError().getDefaultMessage());
			return "redirect:/doctor/doctorJoinForm";
		} else {

			DoctorVO result = doctorService.doctorJoin(vo);

			if (result != null) { // 의사 회원가입성공
				RA.addFlashAttribute("msg", "가입을 축하드립니다");
				return "redirect:/doctor/doctorLogin";
			} else { // 회원가입실패
				RA.addFlashAttribute("msg", "가입을 실패했습니다. 입력 내용을 확인해주세요");
				return "redirect:/doctor/doctorJoin";
				// 실패하면 다시 의사회원가입페이지로
			}
		}
	}

	// 의사로그인
	@PostMapping("/doctorLoginForm")
	public String doctorLogin(@Valid DoctorLoginVO vo, Errors errors, RedirectAttributes RA,
			HttpSession session) {
	
		// 의사로그인처리
		if (errors.hasErrors()) {
			RA.addFlashAttribute("msg", errors.getFieldError().getDefaultMessage());
			return "redirect:/doctor/doctorLogin";
		} else {
			
			DoctorVO doctorVO = doctorService.doctorLogin(vo);
			
			if (doctorVO != null) { // 의사 로그인 성공

				session.setAttribute("doctorId", doctorVO.getDoctorId());
				session.setAttribute("doctorName", doctorVO.getDoctorName());

				return "redirect:/notice/main"; // 성공 시 메인하면으로 이동
			} else { // 의사 로그인 실패
				RA.addFlashAttribute("msg", "아이디 또는 비밀번호를 확인하세요");
				return "redirect:/doctor/doctorLogin";
			}
		}
	}

	// 의사 회원정보 수정
	@PostMapping("/doctorUpdateForm")
	public String doctorUpdateForm(@Valid @RequestBody DoctorUpdateVO vo, Errors errors, RedirectAttributes RA) {

		if (errors.hasErrors()) {
			RA.addFlashAttribute("msg", errors.getFieldError().getDefaultMessage());
			return "redirect:/doctor/doctorUpdate";
		} else {
			if (!vo.getDoctorNewPw().equals(vo.getDoctorNewPwCheck())) {
				RA.addFlashAttribute("msg", "비밀번호를 확인해주세요");
				return "redirect:/doctor/doctorUpdate";
			} else {
				DoctorVO check = doctorService.doctorUpdate(vo);

				if (check == null) {
					RA.addFlashAttribute("msg", "정보 변경도중 문제가 발생했습니다 관리자에게 문의해주세요");
					return "redirect:/doctor/doctorUpdate";
				} else {
					RA.addFlashAttribute("msg", "정상적으로 변경 됐습니다");
					return "redirect:/doctor/doctorUpdate";
				}

			}

		}

	}

	// 의사 탈퇴
	@GetMapping("/doctorDeleteForm")
	public String doctorDeleteForm(HttpSession session, RedirectAttributes RA) {

		String doctorId = (String) session.getAttribute("doctorId");

		DoctorVO doctorVO = doctorService.doctorDelete(doctorId);

		if (doctorVO == null) {
			RA.addFlashAttribute("msg", "탈퇴 도중 문제가 발생했습니다 관리자에게 문의해주세요");
			return "redirect:/doctor/doctorUpdate";
		} else if (doctorVO.getDoctorState().equals("탈퇴")) {
			RA.addFlashAttribute("msg", "탈퇴 완료 됐습니다");
			session.invalidate();

			return "reidrect:/main"; // 홈페이지로 리다이렉트
		} else {
			RA.addFlashAttribute("msg", "탈퇴 도중 문제가 발생했습니다 관리자에게 문의해주세요");
			return "redirect:/doctor/doctorUpdate";
		}

	}

}
