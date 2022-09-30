//package com.playdata.petCommunity;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//
//import com.playdata.petCommunity.entity.Notice;
//import com.playdata.petCommunity.entity.QNotice;
//import com.playdata.petCommunity.notice.service.NoticeRepository;
//import com.playdata.petCommunity.util.page.Criteria;
//import com.playdata.petCommunity.util.page.PageDTO;
//import com.querydsl.core.BooleanBuilder;
//
//
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//class PetCommunityApplicationTests {
//
//	@Autowired
//	NoticeRepository noticeRepository;
//	
//	@Test
//	public void testCode01(Criteria cri) {
//		
////		// 동적쿼리를 만듬
////		QNotice qNotice = QNotice.notice;
////		
////		// 조건을 조합할 불린빌더
////		BooleanBuilder builder = new BooleanBuilder();
////		
////		// ID에 값이 있다면? express로 표현
////		if(cri.getWriter() != null && !cri.getWriter().equals("")) {
////			builder.and(qNotice.writer.like("%"+cri.getWriter()+"%"));
////		} 
////		
////		if (cri.getContent() != null && !cri.getContent().equals("")) {
////			builder.and(qNotice.content.like("%"+cri.getContent()+"%"));
////		}
////		
////		if (cri.getTitle() != null && !cri.getTitle().equals("")) {
////			builder.and(qNotice.title.like("%"+cri.getTitle()+"%"));
////		}
//		
//		//Page<Notice> result = noticeRepository.findAll(PageRequest.of(0, 10, Sort.by("nno").descending()));
//		
//		System.out.println(noticeRepository.findAll());
//		
////		PageDTO<Notice> pageDTO = new PageDTO<>(result);
//		
//		//System.out.println(result);
//	}
//
//}
