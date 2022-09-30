package com.playdata.petCommunity.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.playdata.petCommunity.command.PetVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "pet")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Pet extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pno;
	
	@Column(length = 30, nullable = false)
	private String petName;
	
	@Column(length = 30, nullable = false)
	private String petBirth;
	
	@Column(length = 30, nullable = false, unique = true)
	private String petNumber;
	
	@Column(length = 30)
	private String petWeight;
	
	@Column(length = 30, nullable = false)
	private String petCategory;
	
	@Column(length = 30)
	private String petGender;
	
	@Column(length = 30)
	private String petCategoryDetail;
	
	@Column(columnDefinition = "varchar(30) default '정상 등록'")
	private String petState;
	
	@ManyToOne
	@JoinColumn(name="uno", referencedColumnName = "uno", nullable = false)
	private User user;

	public Pet updatePetbyVO(PetVO petVO, User user) {
		this.petName = petVO.getPetName();
		this.petBirth = petVO.getPetBirth();
		this.petNumber = petVO.getPetNumber();
		this.petWeight = petVO.getPetWeight();
		this.petCategory = petVO.getPetCategory();
		this.petGender = petVO.getPetGender();
		this.petCategoryDetail = petVO.getPetCategoryDetail();
		this.petState = petVO.getPetState();
		this.pno = petVO.getPno();
		this.user = user;
		return this;
	}
	
	
	
}
