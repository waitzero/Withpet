package com.playdata.petCommunity.pet.service;

import java.util.List;

import com.playdata.petCommunity.command.PetVO;

public interface PetService {
	
	public PetVO petJoin(PetVO petVO, String userId); //가입

	public PetVO petUpdate(PetVO petVO, String userId);

	public PetVO petDelete(PetVO petVO, String userId);

	public List<PetVO> getPetList(String userId);

	public PetVO getPetDetail(Long pno);
	
}
