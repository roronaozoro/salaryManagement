package com.employee.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.entity.BankEntity;

@Repository
public interface BankRepository extends JpaRepository<BankEntity, Integer>{

}