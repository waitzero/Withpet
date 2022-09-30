package com.playdata.petCommunity.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.playdata.petCommunity.command.CommentVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "comment")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //
	private Long cno;
	
	@Column(length = 50, nullable = false)
	private String writer;
	
	@Column(length = 2000)
	private String content;
	
	@Column(columnDefinition = "varchar(30) default '정상 등록'")
	private String commentState;
	
	@Column(columnDefinition = "varchar(30) default 'user'")
	private String userOrDoctor;
	
	@ManyToOne
	@JoinColumn(name="nno", referencedColumnName = "nno",nullable = false)
	private Notice notice;
	
	public Comment updateCommentByVO(CommentVO commentVO, Notice notice) {
		
		this.cno = commentVO.getCno();
		this.writer = commentVO.getWriter();
		this.content = commentVO.getContent();
		this.userOrDoctor = commentVO.getUserOrDoctor();
		this.notice = notice;
		this.commentState = commentVO.getCommentState();
		
		return this;
	}
	
}
