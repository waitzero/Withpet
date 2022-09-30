package com.playdata.petCommunity.pet.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.playdata.petCommunity.command.PetVO;
import com.playdata.petCommunity.command.UserVO;
import com.playdata.petCommunity.entity.Pet;
import com.playdata.petCommunity.entity.User;
import com.playdata.petCommunity.repository.PetRepository;
import com.playdata.petCommunity.repository.UserRepository;
import com.playdata.petCommunity.response.PetResponse;
import com.playdata.petCommunity.response.UserResponse;

@Service("petService")
public class PetServiceImpl implements PetService{
	
	@Autowired
	private PetRepository petRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public PetVO petJoin(PetVO petVO, String userId) {
		
		String petNumber = petVO.getPetBirth()+"-";
		
		User userEntity = userRepository.findByUserId(userId);
		
		UserVO userVO = new UserResponse().createUserVOByEntity(userEntity);
		
		if(petVO.getPetGender().equals("수컷")) {
			petNumber += "7";
		} else if (petVO.getPetGender().equals("암컷")) {
			petNumber += "8";
		} else if (petVO.getPetGender().equals("중성")) {
			petNumber += "9";
		}
		
		if(userVO.getUserLocation().equals("서울")) { // 서울, 경기, 경남, 경북, 전남, 전북, 충남, 충북, 대전, 광주, 부산, 제주, 대구, 인천, 강원, 울산
			petNumber += "01";
		} else if(userVO.getUserLocation().equals("광주")) {
			petNumber += "02";
		} else if(userVO.getUserLocation().equals("경기")) {
			petNumber += "03";
		} else if(userVO.getUserLocation().equals("경남")) {
			petNumber += "04";
		} else if(userVO.getUserLocation().equals("경북")) {
			petNumber += "05";
		} else if(userVO.getUserLocation().equals("강원")) {
			petNumber += "06";
		} else if(userVO.getUserLocation().equals("대구")) {
			petNumber += "07";
		} else if(userVO.getUserLocation().equals("대전")) {
			petNumber += "08";
		} else if(userVO.getUserLocation().equals("인천")) {
			petNumber += "09";
		} else if(userVO.getUserLocation().equals("울산")) {
			petNumber += "10";
		} else if(userVO.getUserLocation().equals("전남")) {
			petNumber += "11";
		} else if(userVO.getUserLocation().equals("전북")) {
			petNumber += "12";
		} else if(userVO.getUserLocation().equals("제주")) {
			petNumber += "13";
		} else if(userVO.getUserLocation().equals("충남")) {
			petNumber += "14";
		} else if(userVO.getUserLocation().equals("충북")) {
			petNumber += "15";
		} else if(userVO.getUserLocation().equals("부산")) {
			petNumber += "16";
		}
		
		String countNumber = ""+(petRepository.findTodayRegistPet()+1);
		
		System.out.println(countNumber);
		
		for(int i = 0; i < 4-countNumber.length(); i++) {
			petNumber += "0";
		}
		
		petNumber += countNumber;
		
		petVO.setPetNumber(petNumber);
		
		Pet pet = new Pet().updatePetbyVO(petVO, userEntity);
		
		pet.setPetState("정상 등록");
		
		Pet result = petRepository.save(pet);
		
		return PetResponse.updatePetVOByEntity(result);
	}

	@Override
	public PetVO petUpdate(PetVO petVO, String userId) {
		
		User userEntity = userRepository.findByUserId(userId);
		
		Pet findResult = petRepository.selectByPetNumber(petVO.getPetNumber());
		
		petVO.setPno(findResult.getPno());
		petVO.setPetState(findResult.getPetState());
		
		Pet update = findResult.updatePetbyVO(petVO, userEntity);
		
		Pet result = petRepository.save(update);
		
		return PetResponse.updatePetVOByEntity(result);
	}
	
	@Override
	public PetVO petDelete(PetVO petVO, String userId) {
		
		User userEntity = userRepository.findByUserId(userId);
		
		Pet findResult = petRepository.findById(petVO.getPno()).get();
		
		findResult.setPetState("삭제");
		
		Pet result = petRepository.save(findResult);
		
		return PetResponse.updatePetVOByEntity(result);
	}

	@Override
	public List<PetVO> getPetList(String userId) {
		
		User userEntity = userRepository.findByUserId(userId);

		List<PetVO> list = new ArrayList<>();
		
		List<Pet> result = petRepository.selectByUno(userEntity.getUno());
		
		for(Pet p : result) {
			list.add(PetResponse.updatePetVOByEntity(p));
		}
		
		return list;
	}

	@Override
	public PetVO getPetDetail(Long pno) {
		
		Pet findResult = petRepository.findById(pno).get();
		
		return PetResponse.updatePetVOByEntity(findResult);
	}
	

}
