package com.playdata.petCommunity.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.playdata.petCommunity.entity.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long>, 
										  QuerydslPredicateExecutor<Notice> { 

	Page<Notice> findByWriter(String writer, Pageable page);
	
	@Query(value = "select * from notice where nno = ? and notice_state = '정상 등록'", nativeQuery = true)
	Notice findByIdWithoutDelete(Long nno);

//	@Query(value = "select * from notice where nno = ? and notice_state = '정상 등록'", nativeQuery = true)
//	Notice findByIdWithoutDelete(Long nno); 

}
