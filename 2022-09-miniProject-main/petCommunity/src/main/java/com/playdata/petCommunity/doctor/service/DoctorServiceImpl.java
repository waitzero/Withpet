package com.playdata.petCommunity.doctor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.playdata.petCommunity.command.DoctorLoginVO;
import com.playdata.petCommunity.command.DoctorUpdateVO;
import com.playdata.petCommunity.command.DoctorVO;
import com.playdata.petCommunity.entity.Doctor;
import com.playdata.petCommunity.entity.QDoctor;
import com.playdata.petCommunity.repository.DoctorRepository;
import com.playdata.petCommunity.response.DoctorResponse;
import com.playdata.petCommunity.util.page.Encrypt;
import com.querydsl.core.BooleanBuilder;

@Transactional
@Service("doctorService")
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	DoctorRepository doctorRepository;

	@Override
	public DoctorVO getDoctor(String doctorId) {
		return DoctorResponse.createDoctorVOByEntity(doctorRepository.findByDoctorId(doctorId));
	}

	@Override
	public DoctorVO doctorIdCheck(DoctorVO vo) {
		return DoctorResponse.createDoctorVOByEntity(doctorRepository.findByDoctorId(vo.getDoctorId()));
	}

	@Override // 의사 회원가입
	public DoctorVO doctorJoin(DoctorVO vo) {

		if (doctorRepository.findByDoctorId(vo.getDoctorId()) != null) {
			return null; // 회원가입시 아이디 중복체크해서 null값이 아니라면 null을 반환해서 controller에서 회원가입실패 메시지가 뜨게
		} else {

			String hashPw = Encrypt.getEncrypt(vo.getDoctorPw(), vo.getDoctorId());

			vo.setDoctorPw(hashPw);
			vo.setDoctorState("정상 등록");

			return DoctorResponse.createDoctorVOByEntity(doctorRepository.save(convertDoctorVOtoDoctor(vo)));
		}

	}

	@Override
	public DoctorVO doctorLogin(DoctorLoginVO vo) {

		String hashPw = Encrypt.getEncrypt(vo.getDoctorPw(), vo.getDoctorId());

		QDoctor qDoctor = QDoctor.doctor;

		BooleanBuilder builder = new BooleanBuilder();
		builder.and(qDoctor.doctorState.contains("정상 등록"));

		builder.and(qDoctor.doctorId.contains(vo.getDoctorId()));
		builder.and(qDoctor.doctorPw.contains(hashPw));

		return DoctorResponse.createDoctorVOByEntity(doctorRepository.findByDoctorId(vo.getDoctorId()));
	}

	@Override
	public DoctorVO doctorUpdate(DoctorUpdateVO vo) {
		Doctor doctor = doctorRepository.findByDoctorId(vo.getDoctorId());

		String hashPw = Encrypt.getEncrypt(vo.getDoctorPw(), vo.getDoctorId());

		if (hashPw.equals(doctor.getDoctorPw())) {
			
			vo.setDoctorPw(Encrypt.getEncrypt(vo.getDoctorNewPw(), doctor.getDoctorId()));
			
			Doctor result = doctor.updateDoctorByVO(vo);

			return DoctorResponse.createDoctorVOByEntity(doctorRepository.save(result));
		} else {
			return null;
		}
	}

	@Override
	public DoctorVO doctorDelete(String doctorId) {

		Doctor doctor = doctorRepository.findByDoctorId(doctorId);

		doctor.setDoctorState("탈퇴");

		return DoctorResponse.createDoctorVOByEntity(doctorRepository.save(doctor));
	}

	private Doctor convertDoctorVOtoDoctor(DoctorVO vo) {
		return new Doctor(null, vo.getDoctorName(), vo.getDoctorLicenseNumber(), vo.getDoctorPhoneNumber(),
				vo.getDoctorId(), vo.getDoctorPw(), vo.getDoctorLocation(), null, null);
	}
}
