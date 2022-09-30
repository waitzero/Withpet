package com.playdata.petCommunity.command;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeVO {

	private Long nno;
	
	@Pattern(regexp = "[a-zA-Z0-9]{4,20}", message = "작성자는 대문자, 소문자, 숫자로 구성된 4자리이상 20이하여야 합니다")
	private String writer;
	
	@NotNull(message = "제목을 입력해주세요")
	private String title;
	
	@NotNull(message = "본문을 입력해주세요")
	private String content;

	private LocalDateTime noticeDate;
	
	private String noticeState;
	
}
