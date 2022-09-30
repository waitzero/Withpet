package com.playdata.petCommunity.response;

import com.playdata.petCommunity.command.PetVO;
import com.playdata.petCommunity.entity.Pet;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PetResponse {

	public static PetVO updatePetVOByEntity(Pet pet) {
		return new PetVO(pet.getPno(),
						 pet.getPetName(), 
						 pet.getPetBirth(), 
						 pet.getPetNumber(), 
						 pet.getPetWeight(), 
						 pet.getPetCategory(), 
						 pet.getPetGender(), 
						 pet.getPetCategoryDetail(),
						 pet.getPetState(),
						 pet.getUser().getUno());
	}
	
}
