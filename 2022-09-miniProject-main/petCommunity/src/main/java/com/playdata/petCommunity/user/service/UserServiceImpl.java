package com.playdata.petCommunity.user.service;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.playdata.petCommunity.command.UserLoginVO;
import com.playdata.petCommunity.command.UserUpdateVO;
import com.playdata.petCommunity.command.UserVO;
import com.playdata.petCommunity.entity.QUser;
import com.playdata.petCommunity.entity.User;
import com.playdata.petCommunity.repository.UserRepository;
import com.playdata.petCommunity.response.UserResponse;
import com.playdata.petCommunity.util.page.Encrypt;
import com.querydsl.core.BooleanBuilder;

@Transactional
@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserVO getUser(String userId) {
		return UserResponse.createUserVOByEntity(userRepository.findByUserId(userId));
	}

	
	@Override
	public UserVO userIdCheck(UserVO vo) {
		System.out.println(vo.getUserId());
		return UserResponse.createUserVOByEntity(userRepository.findByUserId(vo.getUserId()));
	}

	@Override
	public UserVO userJoin(UserVO vo) {
	
		if(userRepository.findByUserId(vo.getUserId()) != null) {
			return null;
		} else {

			String hashPw = Encrypt.getEncrypt(vo.getUserPw(), vo.getUserId());
			
			vo.setUserPw(hashPw);
			vo.setUserState("정상 등록"); // 일단 이렇게
			return UserResponse.createUserVOByEntity(userRepository.save(convertUserVOtoUser(vo)));
		}
	}
	
	@Override
	public UserVO userLogin(UserLoginVO vo) {

		String hashPw = Encrypt.getEncrypt(vo.getUserPw(), vo.getUserId());
		
		QUser qUser = QUser.user;
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(qUser.userState.contains("정상 등록"));
		
		builder.and(qUser.userId.contains(vo.getUserId()));
		builder.and(qUser.userPw.contains(hashPw));
		
		Page<User> result = userRepository.findAll(builder, PageRequest.of(0, 1));
		
		if(result == null) {
			return null;
		} else if(result.getContent().size() == 1) {
			return UserResponse.createUserVOByEntity(result.getContent().get(0));
		} else {
			return null;
		}
		
	}

	@Override
	public UserVO userUpdate(UserUpdateVO vo) {
		User user = userRepository.findByUserId(vo.getUserId());
		
		String hashPw = Encrypt.getEncrypt(vo.getUserPw(), user.getUserId());
		
		if(hashPw.equals(user.getUserPw())) {
			
			vo.setUserPw(Encrypt.getEncrypt(vo.getUserNewPw(), user.getUserId()));
			
			User result = user.updateUserByVO(vo);
			
			return UserResponse.createUserVOByEntity(userRepository.save(result));
		} else {
			return null;
		}
		
	}
	
	@Override
	public UserVO userDelete(String userId) {
		
		User user = userRepository.findByUserIdWithoutDelete(userId);
		
		user.setUserState("탈퇴");
		
		return UserResponse.createUserVOByEntity(userRepository.save(user));
	}

	// save는 entity만 매개변수로 받을 수 있기 때문에
	// vo를 entity로 바꿔주는 역할
	private User convertUserVOtoUser(UserVO vo) {
		return new User(null, 
				vo.getUserName(), 
				vo.getUserPhoneNumber(), 
				vo.getUserId(), 
				vo.getUserPw(),
				vo.getUserLocation(),
				vo.getUserLocationDetail(),
				vo.getUserState()
				);

	}
	

}
