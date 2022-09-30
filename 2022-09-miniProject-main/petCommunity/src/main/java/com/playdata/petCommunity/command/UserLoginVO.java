package com.playdata.petCommunity.command;

import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginVO {
	
	@Pattern(regexp = "[a-zA-Z0-9]{4,20}", message = "ID는 대문자, 소문자, 숫자로 구성된 4자리이상 20이하입니다")
	private String userId;
	
	@Pattern(regexp ="^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$"
			, message = "비밀번호 영문자,숫자,특수문자 조합 8글자 이상입니다")
	private String userPw;
}
