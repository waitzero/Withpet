package com.playdata.petCommunity.doctor.service;

import com.playdata.petCommunity.command.DoctorLoginVO;
import com.playdata.petCommunity.command.DoctorUpdateVO;
import com.playdata.petCommunity.command.DoctorVO;

public interface DoctorService {
	
	public DoctorVO getDoctor(String doctorId);
	
	DoctorVO doctorIdCheck(DoctorVO vo); // 의사 아이디 중복체크
	
	DoctorVO doctorJoin(DoctorVO vo); //의사 회원가입
	
	DoctorVO doctorLogin(DoctorLoginVO vo); //의사 로그인
	
	DoctorVO doctorUpdate(DoctorUpdateVO vo); // 의사 정보 수정
	
	DoctorVO doctorDelete(String doctorId); // 의사 탈퇴
	
}
