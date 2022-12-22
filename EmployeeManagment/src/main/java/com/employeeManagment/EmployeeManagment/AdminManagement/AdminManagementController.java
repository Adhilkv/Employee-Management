package com.employeeManagment.EmployeeManagment.AdminManagement;

import java.net.URI;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employeeManagment.EmployeeManagment.Entity.User;
import com.employeeManagment.EmployeeManagment.UserFiles.ResponseMessage;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AdminManagementController {

	@Autowired
	AdminManagmentService adminService;

	String message = "";

	@GetMapping("/admin/basicauth")
	public ResponseEntity<ResponseMessage> adminAuth(Principal principal) {
		try {
			message = "You are Authenticated As Admin";
			adminService.authentication(principal.getName());
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		}catch(Exception ex)
		{
			message = "Authentication Error " + ex;
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseMessage(message));
		}
	}

	@GetMapping("/users/basicauth")
	public ResponseEntity<ResponseMessage> userAuth(Principal principal) {
		try {
			message = "You are Authenticated As User";
			adminService.authentication(principal.getName());
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		}catch(Exception ex)
		{
			message = "Authentication Error " + ex;
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseMessage(message));
		}
	}

	@PostMapping("/admin/add")
	public ResponseEntity<ResponseMessage> updateUser(@RequestBody User newUser) {

		message = "";
		URI uri = null;
		try {
			message = "Success";
			adminService.add(newUser);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

		} catch (Exception ex) {
			message = "Failed " + ex.getMessage();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));

		}
	}

	@PostMapping("/admin/update/password")
	public ResponseEntity<ResponseMessage> changePassword(@RequestBody User user) {
		message = "";
		try {
			
			message = "Password Update Success";
			adminService.password(user);
			
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			
		} catch (Exception ex) {
			message = "Password Update Failed : " + ex.getMessage();
			
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}

	@PostMapping("/admin/update")
	public ResponseEntity<ResponseMessage> changeDetails(@RequestBody User user) {
		message = "";
		try {
			message = "Update Success";
			adminService.update(user);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

		} catch (Exception ex) {
			message = "Update Failed : " + ex.getMessage();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}

	@GetMapping("/admin/all")
	public ResponseEntity<List<User>> getAllUsers() {
		return ResponseEntity.status(HttpStatus.OK).body(adminService.allUsers());
	}

	@DeleteMapping("/admin/delete/{username}")
	public ResponseEntity<ResponseMessage> deleteUser(@PathVariable String username) {
		message = "";
		try {
			adminService.delete(username);
			message = "Delete Successful";
			
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception ex) {
			
			message = "Delete Failed : " + ex.getMessage();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}

	@GetMapping("/admin/login/{username}") 
	public ResponseEntity<User> getData(@PathVariable String username) {
		try {
			User userLogin = adminService.login(username);
			return ResponseEntity.status(HttpStatus.OK).body(userLogin);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new User());
		}
	}

	@GetMapping("/users/login")
	public ResponseEntity<User> getLogin(Principal principal) {
		try {
			User userLogin = adminService.login(principal.getName());
			return ResponseEntity.status(HttpStatus.OK).body(userLogin);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new User());
		}

	}

}
