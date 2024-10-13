package com.transporte.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.transporte.app.entity.Bus;

@Repository
public interface BusRepository extends JpaRepository<Bus, Integer>{

}
