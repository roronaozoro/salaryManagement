package com.employee.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.entity.AccountEntity;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity,String>{

}
