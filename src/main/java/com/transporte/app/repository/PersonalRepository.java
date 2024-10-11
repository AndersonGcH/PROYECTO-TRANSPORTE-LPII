package com.transporte.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.transporte.app.entity.Personal;

@Repository
public interface PersonalRepository extends JpaRepository<Personal, Integer>{

}
