package com.playdata.petCommunity.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.playdata.petCommunity.command.CommentVO;
import com.playdata.petCommunity.command.NoticeVO;
import com.playdata.petCommunity.comment.service.CommentService;
import com.playdata.petCommunity.entity.Notice;
import com.playdata.petCommunity.notice.service.NoticeService;
import com.playdata.petCommunity.util.page.Criteria;
import com.playdata.petCommunity.util.page.PageDTO;


@Controller
@RequestMapping("/notice")
public class NoticeController {

	@Autowired
	@Qualifier("noticeService")
	NoticeService noticeService;
	
	@Autowired
	@Qualifier("commentService")
	CommentService commentService;
	
	@RequestMapping("/main")
	public String main() {
		return "notice/main";
	}

	@RequestMapping("/noticeListAll")
	public String noticeListAll(Criteria cri, Model model, HttpSession session) {
		
		PageDTO<Notice> pageDTO = noticeService.getList(cri);
		
		model.addAttribute("pageDTO", pageDTO);
		
		return "notice/noticeListAll"; // 전체 목록 조회 페이지
	}
	
	@RequestMapping("/noticeMyList")
	public String noticeMyList(Criteria cri, Model model, HttpSession session) {
		
		if(session.getAttribute("userId") == null) {
			return "redirect:/"; // 작성자 id 값 없음으로 홈페이지로 리다이렉트
		} else {
			String userId = (String) session.getAttribute("userId");
			cri.setWriter(userId);
			PageDTO<Notice> pageDTO = noticeService.getListByWriter(cri);
			
			model.addAttribute("pageDTO", pageDTO);
			
			return "notice/noticeListMe"; // user 자기 글 조회 목록 페이지
		}
		
	}
	
	@RequestMapping("/noticeDetail")
	public String noticeDetail(@RequestParam("nno") Long nno, Model model, RedirectAttributes RA) {
		
		NoticeVO noticeVO = noticeService.getDetailById(nno);
		
		if(noticeVO == null) {
			RA.addFlashAttribute("msg", "잘못된 조회 방법입니다");
			return "redirect:/notice/noticeListAll"; // nno 값으로 조회했을 때 없는 글, 홈페이지로 리다이렉트
		} else if(noticeVO.getNoticeState().equals("삭제")){
			RA.addFlashAttribute("msg", "삭제된 게시글 입니다");
			return "redirect:/notice/noticeListAll"; // 삭제된 글이라고 메세지 뛰운 후 홈페이지로 리다이렉트
		} else {
			List<CommentVO> comments = commentService.getCommentList(nno);
			
			model.addAttribute("noticeVO", noticeVO);
			model.addAttribute("comments", comments); // 댓글들
			
			return "notice/noticeDetail"; // notice 디테일 페이지
		}
		
	}
	
	@PostMapping("/registNotice")
	public String registNotice(@Valid NoticeVO noticeVO, Errors errors, RedirectAttributes RA, Model model, HttpSession session) {
		
		if(errors.hasErrors()) {
			RA.addFlashAttribute("msg", errors.getFieldError().getDefaultMessage());
			return "redirect:/notice/noticeReg"; // 유효성 검사 실패로 등록 페이지로 리다이렉트
		} else {
			String userId = (String) session.getAttribute("userId");
			
			noticeVO.setWriter(userId);
			
			NoticeVO newNoticeVO = noticeService.registNotice(noticeVO);
			
			if(newNoticeVO == null) {
				RA.addFlashAttribute("msg", "등록 중 문제가 발생했습니다");
				return "redirect:/notice/noticeReg";
			} else {
				RA.addFlashAttribute("msg", "정상적으로 등록 됐습니다");
				return "redirect:/notice/noticeListAll"; // 작성된 글 페이지
			}
		}
		
		
	}
	
	@PostMapping("/noticeUpdate")
	public String noticeUpdate(@Valid NoticeVO noticeVO, Errors errors, RedirectAttributes RA, Model model, HttpSession session) {
		
		if(errors.hasErrors()) {
			model.addAttribute("msg", errors.getFieldError().getDefaultMessage());
			return "notice/noticeDetail?nno="+noticeVO.getNno(); // 유효성 검사 실패로 리다이렉트
		} else {
			String userId = (String) session.getAttribute("userId");
			
			NoticeVO newNoticeVO = noticeService.updateNotice(noticeVO,userId);
			if(newNoticeVO == null) {
				model.addAttribute("msg", "수정 중 문제가 발생했습니다");
				return "notice/noticeDetail?nno="+noticeVO.getNno();
			} else {
				RA.addFlashAttribute("msg", "정상적으로 수정 됐습니다");
				return "redirect:/notice/noticeDetail?nno="+noticeVO.getNno(); // 수정된 글 페이지
			}
		}
		
	}
	
	@PostMapping("/noticeDelete")
	public String noticeDelete(NoticeVO noticeVO, RedirectAttributes RA,HttpSession session) {
		
		String userId = (String) session.getAttribute("userId");
		
		if(noticeService.checkNotice(noticeVO,userId)) {
			NoticeVO newNoticeVO = noticeService.noticeDelete(noticeVO, userId);
		
			if(newNoticeVO == null) {
				RA.addFlashAttribute("msg", "삭제 중 문제가 발생했습니다");
				return "redirect:/notice/noticeMyList";
			} else {
				RA.addFlashAttribute("msg", "정상적으로 삭제 됐습니다");
				return "redirect:/notice/noticeMyList";
			}
		} else {
			return "redirect:/"; // 로그인한 사람과 작성자가 다름으로 삭제 불가
		}
		
	}
	
	@GetMapping("/noticeModify")
	public String noticeModify(NoticeVO noticeVO, HttpSession session, Model model, RedirectAttributes RA) {
		
		String userId = (String) session.getAttribute("userId");
		
		if(session.getAttribute("userId")!=null) {
			
			if(userId.equals(noticeVO.getWriter())) {
				
				NoticeVO vo = noticeService.getDetailById(noticeVO.getNno());
				
				model.addAttribute("vo", vo);
				
				System.out.println(vo);
				
				return "notice/noticeModify";
			} else {
				RA.addFlashAttribute("msg", "자신의 글만 수정 혹은 삭제가 가능합니다");
				return "redirect:/notice/noticeListAll";
			}
		} else {
			RA.addFlashAttribute("msg", "로그인이 필요합니다");
			return "redirect:/notice/noticeListAll";
		}
		
	}
	
	@RequestMapping("/noticeReg")
	public String noticeReg(HttpSession session, Model model) {
		
		String userId = (String) session.getAttribute("userId");
		
		model.addAttribute("userId", userId);
		
		return "notice/noticeReg";
	}
	
}
