package com.example.PCAS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PCAS.entities.Hospital;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long>{

}
