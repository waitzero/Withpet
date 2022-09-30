package com.playdata.petCommunity.comment.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.playdata.petCommunity.command.CommentVO;
import com.playdata.petCommunity.entity.Comment;
import com.playdata.petCommunity.entity.Notice;
import com.playdata.petCommunity.repository.CommentRepository;
import com.playdata.petCommunity.repository.NoticeRepository;
import com.playdata.petCommunity.response.CommentResponse;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	NoticeRepository noticeRepository;

	@Override
	public List<CommentVO> getCommentList(Long nno) {
		
		List<Comment> list = commentRepository.findByNno(nno);
		
		List<CommentVO> result = new ArrayList<>();
		
		for(Comment c : list) {
			result.add( CommentResponse.updateCommentVOByEntity(c));
		}
		
		return result;
	}

	@Override
	public CommentVO registComment(HttpSession session ,CommentVO commentVO) {
		
		if(session.getAttribute("doctorId") != null) {
			String doctorId = (String) session.getAttribute("doctorId");
			commentVO.setWriter(doctorId);
			commentVO.setUserOrDoctor("doctor");
			commentVO.setCommentState("정상 등록");
		} else if(session.getAttribute("userId") != null) {
			String userId = (String) session.getAttribute("userId");
			commentVO.setWriter(userId);
			commentVO.setUserOrDoctor("user");
			commentVO.setCommentState("정상 등록");
		} else {
			return null;
		}
		
		Notice notice = noticeRepository.findByIdWithoutDelete(commentVO.getNno());
		
		if(notice == null) {
			return null;
		} else {
			Comment comment = new Comment().updateCommentByVO(commentVO, notice);
			
			Comment saved = commentRepository.save(comment);
			
			return CommentResponse.updateCommentVOByEntity(saved);
		}
		
	}

	@Override
	public CommentVO updateComment(HttpSession session, CommentVO commentVO) {

		Comment beforeComment = commentRepository.findById(commentVO.getCno()).get();
		
		Notice notice = noticeRepository.findByIdWithoutDelete(commentVO.getNno());
		
		CommentVO result;
		
		if(session.getAttribute("userId") != null) {
			String userId = (String) session.getAttribute("userId");

			if(userId.equals(beforeComment.getWriter())) {
				commentVO.setUserOrDoctor("user");
				Comment afterComment = new Comment().updateCommentByVO(commentVO, notice);
				result = CommentResponse.updateCommentVOByEntity(commentRepository.save(afterComment));
			} else {
				return null;
			}
			
		} else if (session.getAttribute("doctorId") != null) {
			String doctorId = (String) session.getAttribute("doctorId");
			
			if(doctorId.equals(beforeComment.getWriter())) {
				commentVO.setUserOrDoctor("doctor");
				Comment afterComment = new Comment().updateCommentByVO(commentVO, notice);
				result = CommentResponse.updateCommentVOByEntity(commentRepository.save(afterComment));
			} else {
				return null;
			}
			
		} else {
			return null;
		}
		
		return result;
	}
	
	@Override
	public CommentVO deleteComment(HttpSession session, CommentVO commentVO) {
		
		Comment beforeComment = commentRepository.findById(commentVO.getCno()).get();
		
		Notice notice = noticeRepository.findByIdWithoutDelete(commentVO.getNno());
		
		CommentVO result;
		
		if(session.getAttribute("userId") != null) {
			String userId = (String) session.getAttribute("userId");
			
			if(userId.equals(beforeComment.getWriter())) {
				commentVO.setUserOrDoctor("user");
				commentVO.setCommentState("삭제");
				commentVO.setContent(beforeComment.getContent());
				Comment afterComment = new Comment().updateCommentByVO(commentVO, notice);
				result = CommentResponse.updateCommentVOByEntity(commentRepository.save(afterComment));
			} else {
				return null;
			}
			
		} else if (session.getAttribute("doctorId") != null) {
			String doctorId = (String) session.getAttribute("doctorId");
			
			if(doctorId.equals(beforeComment.getWriter())) {
				commentVO.setUserOrDoctor("doctor");
				commentVO.setCommentState("삭제");
				Comment afterComment = new Comment().updateCommentByVO(commentVO, notice);
				result = CommentResponse.updateCommentVOByEntity(commentRepository.save(afterComment));
			} else {
				return null;
			}
			
		} else {
			return null;
		}
		
		return result;
	}
}
