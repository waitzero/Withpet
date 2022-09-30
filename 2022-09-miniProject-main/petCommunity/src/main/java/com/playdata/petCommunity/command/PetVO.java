package com.playdata.petCommunity.command;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetVO {

	private Long pno;
	
	@NotEmpty(message = "반려동물의 이름은 필수 값입니다")
	private String petName;
	
	@NotEmpty(message = "반려동물의 생일은 필수 값입니다")
	private String petBirth;
	
	private String petNumber;
	
	@NotEmpty(message = "반려동물의 몸무게는 필수 값입니다, Kg 단위를 제외한 숫자만 적어주세요")
	private String petWeight;
	
	@NotEmpty(message = "반려동물의 종은 필수 값입니다")
	private String petCategory;
	
	@NotEmpty(message = "반려동물의 성별은 필수 값입니다")
	private String petGender;
	
	private String petCategoryDetail;
	
	private String petState;

	private Long uno;
	
}
