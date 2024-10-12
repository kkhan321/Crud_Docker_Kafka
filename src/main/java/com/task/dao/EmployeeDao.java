package com.task.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task.entity.EmployeeMasterBean;

@Repository
public interface EmployeeDao extends JpaRepository<EmployeeMasterBean, Integer> {

}
