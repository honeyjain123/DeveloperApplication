package com.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.application.beans.Developer;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Integer> {
	//Repository Interface to execute CRUD operations for Developer Entity
	
	
	
}
