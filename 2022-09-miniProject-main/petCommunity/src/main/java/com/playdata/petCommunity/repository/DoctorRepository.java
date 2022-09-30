package com.playdata.petCommunity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.playdata.petCommunity.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long>,
										  QuerydslPredicateExecutor<Doctor> {

	Doctor findByDoctorId(String doctorId);

}
