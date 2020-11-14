package com.employee.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.entity.GradeEntity;

@Repository
public interface GradeRepository extends JpaRepository<GradeEntity,Integer>
{
	
}
