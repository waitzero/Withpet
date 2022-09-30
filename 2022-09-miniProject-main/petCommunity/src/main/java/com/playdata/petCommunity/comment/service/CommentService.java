package com.playdata.petCommunity.comment.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.playdata.petCommunity.command.CommentVO;

public interface CommentService {

	List<CommentVO> getCommentList(Long nno);

	CommentVO registComment(HttpSession session, CommentVO commentVO);

	CommentVO updateComment(HttpSession session, CommentVO commentVO);

	CommentVO deleteComment(HttpSession session, CommentVO commentVO);

}
