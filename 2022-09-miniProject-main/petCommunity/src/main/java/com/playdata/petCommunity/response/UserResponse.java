package com.playdata.petCommunity.response;

import com.playdata.petCommunity.command.UserVO;
import com.playdata.petCommunity.entity.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse {
	
	public static UserVO createUserVOByEntity(User user) {
		return new UserVO(
						  user.getUserName(),
						  user.getUserPhoneNumber(),
						  user.getUserId(),
						  user.getUserPw(),
						  user.getUserLocation(),
						  user.getUserLocationDetail(),
						  user.getUserState()
						  );
	}

}
