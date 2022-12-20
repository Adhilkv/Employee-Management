package com.employeeManagment.EmployeeManagment.AdminManagement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employeeManagment.EmployeeManagment.Entity.User;

@Repository
public interface AdminManagementRepository extends JpaRepository<User,String>{
	
}
