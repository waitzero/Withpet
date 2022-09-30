package com.playdata.petCommunity.response;

import com.playdata.petCommunity.command.DoctorVO;
import com.playdata.petCommunity.entity.Doctor;

public class DoctorResponse {

	public static DoctorVO createDoctorVOByEntity(Doctor doctor) {
		return new DoctorVO(
							doctor.getDoctorName(),
							doctor.getDoctorLicenseNumber(),
							doctor.getDoctorPhoneNumber(),
							doctor.getDoctorId(),
							doctor.getDoctorPw(),
							doctor.getDoctorLocation(),
							doctor.getDoctorRecommend(),
							doctor.getDoctorState()
							);
				
	}
}
