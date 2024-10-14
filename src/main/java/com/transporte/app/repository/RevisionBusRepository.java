package com.transporte.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.transporte.app.entity.RevisionBus;

@Repository
public interface RevisionBusRepository extends JpaRepository<RevisionBus, Integer>{

}
