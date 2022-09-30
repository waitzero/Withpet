package com.playdata.petCommunity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.playdata.petCommunity.entity.User;

public interface UserRepository extends JpaRepository<User, Long>,
										QuerydslPredicateExecutor<User>{
	
	// 아이디 중복체크(쿼리메소드)
	User findByUserId(String userId);
	
	@Query(value="select * from user where user_id=? and user_state='정상 등록'",
		   nativeQuery = true)
	User findByUserIdWithoutDelete(String userId);

}
