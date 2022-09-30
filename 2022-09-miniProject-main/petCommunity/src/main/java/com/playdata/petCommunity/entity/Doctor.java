package com.playdata.petCommunity.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.playdata.petCommunity.command.DoctorUpdateVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "doctor")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Doctor extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long dno;
	
	@Column(length = 30, nullable = false)
	private String doctorName;
	
	@Column(length = 30, nullable = false, unique = true)
	private String doctorLicenseNumber;
	
	@Column(length = 30, nullable = false)
	private String doctorPhoneNumber;
	
	@Column(length = 30, nullable = false, unique = true)
	private String doctorId;
	
	@Column(length = 300, nullable = false)
	private String doctorPw;
	
	@Column(length = 300)
	private String doctorLocation;
	
	@Column(columnDefinition = "int default '0'")
	private Long doctorRecommend;
	
	@Column(columnDefinition = "varchar(30) default '정상 등록'")
	private String doctorState;
	
	public Doctor updateDoctorByVO(DoctorUpdateVO vo) {
		this.doctorName = vo.getDoctorName();
		this.doctorPhoneNumber = vo.getDoctorPhoneNumber();
		this.doctorId = vo.getDoctorId();
		this.doctorPw = vo.getDoctorPw();
		this.doctorLocation = vo.getDoctorLocation();
		return this;
	}
	
	
}
