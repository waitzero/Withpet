package com.playdata.petCommunity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.playdata.petCommunity.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>, 
											QuerydslPredicateExecutor<Comment> {

	@Query(value = "select * from comment where nno = ? and comment_state = '정상 등록'", nativeQuery = true)
	List<Comment> findByNno(Long nno);

}
