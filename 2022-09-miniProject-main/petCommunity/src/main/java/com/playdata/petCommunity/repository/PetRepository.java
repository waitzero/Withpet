package com.playdata.petCommunity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.playdata.petCommunity.entity.Pet;

public interface PetRepository extends JpaRepository<Pet, Long>, 
										QuerydslPredicateExecutor<Pet>{
	
	@Query(value = "select count(*) from pet where regdate >  DATE(NOW())", nativeQuery = true)
	int findTodayRegistPet();

	@Query(value = "select * from pet where pet_number = ?", nativeQuery = true)
	Pet selectByPetNumber(String petNumber);

	@Query(value = "select * from pet where uno = ? and pet_state = '정상 등록'", nativeQuery = true)
	List<Pet> selectByUno(Long uno);
	
}
