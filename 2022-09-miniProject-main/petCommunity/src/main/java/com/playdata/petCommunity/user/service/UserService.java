package com.playdata.petCommunity.user.service;


import com.playdata.petCommunity.command.UserLoginVO;
import com.playdata.petCommunity.command.UserUpdateVO;
import com.playdata.petCommunity.command.UserVO;


public interface UserService {
	
	public UserVO getUser(String userId);
	
	UserVO userIdCheck(UserVO vo); // 유저 아이디 중복체크
	
	UserVO userJoin(UserVO vo); //유저 회원가입
	
	UserVO userLogin(UserLoginVO vo); //유저 로그인
	
	UserVO userUpdate(UserUpdateVO vo); // 유저 정보 수정
	
	UserVO userDelete(String doctorId); // 유저 탈퇴

	

}
