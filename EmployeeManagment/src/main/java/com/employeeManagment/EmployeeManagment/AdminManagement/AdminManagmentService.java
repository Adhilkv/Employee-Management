package com.employeeManagment.EmployeeManagment.AdminManagement;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.employeeManagment.EmployeeManagment.Entity.User;
import com.employeeManagment.EmployeeManagment.UserFiles.ResponseMessage;

@Service
public class AdminManagmentService {

	@Autowired
	AdminManagementRepository adminJpa;

	String message = "";
	
	public void authentication(String username) throws Exception{
		
		Optional<User> optionalUser = adminJpa.findById(username);
		if (!optionalUser.isPresent()) {
			throw new Exception("User does not exists");
		}
	}
	

	public User add(User user) throws Exception {
		if (user.getUserName() == null || user.getUserName().trim().equals("")) {
			throw new Exception("Please Check Entered UserName Details");
		} else {
			Optional<User> optionalUser = adminJpa.findById(user.getUserName());
			if (optionalUser.isPresent()) {
				throw new Exception("User already exists");
			}
		}

		if (user.getFirstName() == null || user.getFirstName().trim().equals("")) {
			throw new Exception("Please Check Entered FirstName Details");
		}

		if (user.getLastName() == null || user.getLastName().trim().equals("")) {
			throw new Exception("Please Check Entered LastName Details");
		}

		if (user.getPassword() == null || user.getPassword().trim().equals("")) {
			throw new Exception("Please Check Entered Password Details");
		}

		if (user.getDepartment() == null || user.getDepartment().trim().equals("")) {
			throw new Exception("Please Check Entered Department Details");
		}

		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		User save = adminJpa.save(user);

		return user;
	}

	public void password(User user) throws Exception {

		if (user.getUserName() == null || user.getUserName().trim().equals("")) {
			throw new Exception("Please Check Entered UserName Details");
		}

		if (user.getPassword() == null || user.getPassword().trim().equals("")) {
			throw new Exception("Please Check Entered Password Details");
		}

		User userData = adminJpa.findById(user.getUserName()).get();
		userData.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

		adminJpa.save(userData);
	}
	
	
	public void update(User user) throws Exception{
		
		if (user.getUserName() == null || user.getUserName().trim().equals("")) {
			throw new Exception("Please Check Entered UserName Details");
		}
		if (user.getFirstName() == null || user.getFirstName().trim().equals("")) {
			throw new Exception("Please Check Entered FirstName Details");
		}

		if (user.getLastName() == null || user.getLastName().trim().equals("")) {
			throw new Exception("Please Check Entered LastName Details");
		}

		if (user.getDepartment() == null || user.getDepartment().trim().equals("")) {
			throw new Exception("Please Check Entered Department Details");
		}
		
		User userData = adminJpa.findById(user.getUserName()).get();
		user.setPassword((userData.getPassword()));
		adminJpa.save(user);
	}
	
	
	public void delete(String username) throws Exception{
		
		if (username == null || username.trim().equals("")) {
			throw new Exception("Please Check Entered UserName Details");
		} else {
			
			Optional<User> optionalUser = adminJpa.findById(username);
			if (!optionalUser.isPresent()) {
				throw new Exception("User does not exists");
			}else
			{
				adminJpa.deleteById(username);
			}
			
		}	
	}
	
	public List<User> allUsers()
	{
		return adminJpa.findAll().stream().filter(obj -> obj.getRole().equals("USER"))
				.sorted(Comparator.comparing(User::getUserName)).collect(Collectors.toList());	
	}
	
	public User login(String username) throws Exception
	{
		User user = adminJpa.findById(username).get();
		String User = username;
		if (user.getUserName().equals(User)) {
			return user;
		} else {
			throw new Exception("Invalid Login");
		}

	}
	
	
}


