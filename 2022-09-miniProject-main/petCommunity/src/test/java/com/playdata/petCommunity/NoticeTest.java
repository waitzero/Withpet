package com.playdata.petCommunity;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.playdata.petCommunity.command.CommentVO;
import com.playdata.petCommunity.command.PetVO;
import com.playdata.petCommunity.command.UserVO;
import com.playdata.petCommunity.entity.Comment;
import com.playdata.petCommunity.entity.Doctor;
import com.playdata.petCommunity.entity.Notice;
import com.playdata.petCommunity.entity.Pet;
import com.playdata.petCommunity.entity.QDoctor;
import com.playdata.petCommunity.entity.QNotice;
import com.playdata.petCommunity.entity.QPet;
import com.playdata.petCommunity.entity.User;
import com.playdata.petCommunity.repository.CommentRepository;
import com.playdata.petCommunity.repository.DoctorRepository;
import com.playdata.petCommunity.repository.NoticeRepository;
import com.playdata.petCommunity.repository.PetRepository;
import com.playdata.petCommunity.repository.UserRepository;
import com.playdata.petCommunity.util.page.Criteria;
import com.playdata.petCommunity.util.page.Encrypt;
import com.playdata.petCommunity.util.page.PageDTO;
import com.querydsl.core.BooleanBuilder;

@SpringBootTest
public class NoticeTest {
	
	@Autowired
	NoticeRepository noticeRepository;
	
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	DoctorRepository doctorRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PetRepository petRepository;

//	@Test
//	public void testCode01() {
//		
//		QNotice qNotice = QNotice.notice;
//		
//		Criteria cri = new Criteria();
//
//		// 조건을 조합할 불린빌더
//		BooleanBuilder builder = new BooleanBuilder();
//		builder.and(qNotice.noticeState.contains("정상 등록"));
//		
//		// ID에 값이 있다면? express로 표현
//		if(cri.getWriter() != null && !cri.getWriter().equals("")) {
//			builder.and(qNotice.writer.like("%"+cri.getWriter()+"%"));
//		} 
//		
//		if (cri.getContent() != null && !cri.getContent().equals("")) {
//			builder.and(qNotice.content.like("%"+cri.getContent()+"%"));
//		}
//		
//		if (cri.getTitle() != null && !cri.getTitle().equals("")) {
//			builder.and(qNotice.title.like("%"+cri.getTitle()+"%"));
//		}
//		
//		Page<Notice> result = noticeRepository.findAll(builder, PageRequest.of(cri.getPage()-1, cri.getAmount(), Sort.by("nno").descending()));
//		
//		PageDTO<Notice> pageDTO = new PageDTO<>(result);
//		
//		System.out.println(pageDTO);
//		
//	}
	
//	@Test
//	public void testCode02() {
//		
//		Criteria cri = new Criteria();
//		
//		cri.setWriter("정정일");
//		
//		List<Notice> list = noticeRepository.findByWriter(cri.getWriter(), PageRequest.of(cri.getPage()-1, cri.getAmount(), Sort.by("nno").descending())).getContent();
//	
//		System.out.println(list);
//	}
	
//	@Test
//	public void testCode03() {
//		Criteria cri = new Criteria();
//		
//		QNotice qNotice = QNotice.notice;
//		
//		BooleanBuilder builder = new BooleanBuilder();
//		builder.and(qNotice.noticeState.contains("정상 등록"));
//		
//		builder.and(qNotice.writer.contains("정정일"));
//		
//		List<Notice> list = noticeRepository.findAll(builder,PageRequest.of(cri.getPage()-1, cri.getAmount(), Sort.by("nno").descending())).getContent();
//	
//		System.out.println(list);
//	}
	
//	@Test
//	public void testCode04() {
//		
//		System.out.println(noticeRepository.findByIdWithoutDelete(1L));
//		
//	}
	
//	@Test
//	public void testCode05() {
//		System.out.println(commentRepository.findByNno(1L));
//	}
	
//	@Test
//	public void testCode06() {
//		
//		System.out.println(userRepository.findById(1L));
//		System.out.println(doctorRepository.findById(1L));
//		
//	}
	
//	@Test
//	public void testCode07() {
//		QDoctor qDoctor = QDoctor.doctor;
//		
//		BooleanBuilder builder = new BooleanBuilder();
//		builder.and(qDoctor.doctorState.contains("정상 등록"));
//		
//		builder.and(qDoctor.doctorId.contains("jji0428"));
//		
//		builder.and(qDoctor.doctorPw.contains("1234"));
//		
//		System.out.println(doctorRepository.findAll(builder).iterator().next());
//		
//	}
	
//	@Test
//	public void testCode08() {
//		
//		PetVO petvo = new PetVO(null,"강아지","980428","980428-1777777","58","강아지","수컷","포ds121241241asa안",null,null);
//		
//		User userEntity = userRepository.findByUserId("jji0428");
//
//		Pet findResult = petRepository.selectByPetNumber(petvo.getPetNumber());
//		
//		petvo.setPno(findResult.getPno());
//		
//		Pet update = findResult.updatePetbyVO(petvo, userEntity);
//		
//		Pet result = petRepository.save(update);
//	}
	
//	@Test
//	public void testCode09() {
//		User userEntity = userRepository.findByUserId("jji0428");
//
//		List<PetVO> list = new ArrayList<>();
//		
//		List<Pet> result = petRepository.selectByUno(userEntity.getUno());
//	
//		System.out.println(result);
//	}
	
//	@Test
//	public void testCode10() {
//		
//		System.out.println(noticeRepository.findByIdWithoutDelete(1L));
//	}
	
//	@Test
//	public void testCode11() {
//		
//		Comment commnet = commentRepository.save(new Comment(null,"jji0428","dsada12312312s","정상 등록","user",noticeRepository.findByIdWithoutDelete(1L)));
//		
//		System.out.println(commnet);
//	}
	
//	@Test
//	public void testCode12() {
//		
//		UserVO vo = new UserVO("정정일", "01098006069", "jjjjjj", "123123", null, null);
//		
//		userRepository.save(new User().updateUserByVO(vo));
//		
//	}
	
//	@Test
//	public void testCode12() {
//		
//		String hash = Encrypt.getEncrypt("1234", "jji0428");
//		
//		System.out.println(hash);
//		
//	}
	
}
