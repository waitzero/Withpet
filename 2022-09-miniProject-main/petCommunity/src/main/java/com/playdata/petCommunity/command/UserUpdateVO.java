package com.playdata.petCommunity.command;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateVO {
	
	@NotEmpty
	private String userName;
	
	@Pattern(regexp = "[0-9]{3}-[0-9]{3,4}-[0-9]{4}", message = "000-0000-0000형식 이어야 합니다")
	private String userPhoneNumber;
	
	@Pattern(regexp = "[a-zA-Z0-9]{4,20}", message = "ID는 대문자, 소문자, 숫자로 구성된 4자리이상 20이하입니다")
	private String userId;
	
	@Pattern(regexp ="^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$"
			, message = "비밀번호 영문자,숫자,특수문자 조합 8글자 이상입니다")
	private String userPw;
	
	@Pattern(regexp ="^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$"
			, message = "비밀번호 영문자,숫자,특수문자 조합 8글자 이상입니다")
	private String userNewPw;
	
	@Pattern(regexp ="^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$"
			, message = "비밀번호 영문자,숫자,특수문자 조합 8글자 이상입니다")
	public String userNewPwCheck;
	
	@NotNull(message ="지역은 필수 입력값 입니다")
	private String userLocation;
	
	@NotNull(message ="지역은 필수 입력값 입니다")
	private String userLocationDetail;

	
}
