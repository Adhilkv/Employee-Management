package com.employeeManagment.EmployeeManagment.UserFiles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employeeManagment.EmployeeManagment.Entity.FileDB;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {

}