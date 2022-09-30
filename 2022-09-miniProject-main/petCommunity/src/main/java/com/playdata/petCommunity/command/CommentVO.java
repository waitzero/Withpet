package com.playdata.petCommunity.command;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentVO {

	private Long cno;
	
	@Pattern(regexp = "[a-zA-Z0-9]{4,20}", message = "작성자는 대문자, 소문자, 숫자로 구성된 4자리이상 20이하여야 합니다")
	private String writer;
	
	@NotEmpty(message = "본문을 입력해주세요")
	private String content;
	
	private String userOrDoctor;
	
	private String commentState;
	
	private LocalDateTime commentDate;
	
	@NotNull(message = "게시글 선택은 필수입니다")
	private Long nno;
	
}
