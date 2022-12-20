package com.employeeManagment.EmployeeManagment.AdminManagement;

import java.net.URI;
import java.security.Principal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.employeeManagment.EmployeeManagment.Authencationbean;
import com.employeeManagment.EmployeeManagment.Entity.User;
import com.employeeManagment.EmployeeManagment.UserFiles.ResponseMessage;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AdminManagementResource {

	@Autowired
	AdminManagmentService AdminService;

	@Autowired
	AdminManagementRepository AdminJpa;
	
	
	@PostMapping("/admin/add")
	public ResponseEntity<ResponseMessage> UpdateUser(@RequestBody User newUser) {
		
		String message = "";
		URI uri = null;
		try
		{	
			message = "Success";
			if(newUser.getUserName() == null || newUser.getUserName().trim().equals(""))
			{
				throw new Exception("Please Check Entered UserName Details");
			}else
			{
				Optional<User> optionalUser = AdminJpa.findById(newUser.getUserName());
				if(optionalUser.isPresent())
				{
					throw new Exception("User already exists");
				}
			}
			
			
			if(newUser.getFirstName() == null || newUser.getFirstName().trim().equals(""))
			{
				throw new Exception("Please Check Entered FirstName Details");
			}
			
			if(newUser.getLastName() == null || newUser.getLastName().trim().equals(""))
			{
				throw new Exception("Please Check Entered LastName Details");
			}
			
			if(newUser.getPassword() == null || newUser.getPassword().trim().equals(""))
			{
				throw new Exception("Please Check Entered Password Details");
			}
			
			if(newUser.getDepartment() == null || newUser.getDepartment().trim().equals(""))
			{
				throw new Exception("Please Check Entered Department Details");
			}
			
			newUser.setPassword(new BCryptPasswordEncoder().encode(newUser.getPassword()));
			
			User save = AdminJpa.save(newUser);
			
			 return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		}catch(Exception ex)
		{
			message = "Failed " + ex.getMessage();
		    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
			
		}
	}
	
	@PostMapping("/admin/update/password")
	public ResponseEntity<ResponseMessage>  changePassword(@RequestBody User User)
	{
		String message = "";
		try
		{
			
			if(User.getUserName() == null || User.getUserName().trim().equals(""))
			{
				throw new Exception("Please Check Entered UserName Details");
			}
		
			if(User.getPassword() == null || User.getPassword().trim().equals(""))
			{
				throw new Exception("Please Check Entered Password Details");
			}
			
			message = "Password Update Success";
			User userData = AdminJpa.findById(User.getUserName()).get();
			userData.setPassword(new BCryptPasswordEncoder().encode(User.getPassword()));
			AdminJpa.save(userData);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		}catch(Exception ex)
		{
			message = "Password Update Failed : " + ex.getMessage();
			//ystem.out.println("ex " + ex);
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
		
	}
	
	@PostMapping("/admin/update")
	public ResponseEntity<ResponseMessage>  changeDetails(@RequestBody User User)
	{
		String message = "";
		try
		{
			if(User.getUserName() == null || User.getUserName().trim().equals(""))
			{
				throw new Exception("Please Check Entered UserName Details");
			}
			if(User.getFirstName() == null || User.getFirstName().trim().equals(""))
			{
				throw new Exception("Please Check Entered FirstName Details");
			}
			
			if(User.getLastName() == null || User.getLastName().trim().equals(""))
			{
				throw new Exception("Please Check Entered LastName Details");
			}
			
			if(User.getDepartment() == null || User.getDepartment().trim().equals(""))
			{
				throw new Exception("Please Check Entered Department Details");
			}
			
			message = "Update Success";
			
			User userData = AdminJpa.findById(User.getUserName()).get();
			User.setPassword((userData.getPassword()));
			AdminJpa.save(User);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			
		}catch(Exception ex)
		{
			message = "Update Failed : " + ex.getMessage();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
		
	}

	@GetMapping("/admin/all")
	public List<User> getAllUsers() {
		return AdminJpa.findAll().stream().filter(obj -> obj.getRole().equals("USER"))
				.sorted(Comparator.comparing(User::getUserName)).collect(Collectors.toList());

	}

	@DeleteMapping("/admin/delete/{username}")
	public ResponseEntity<ResponseMessage> deleteUser(@PathVariable String username) {
		String message = "";
		try 
		{
			AdminJpa.deleteById(username);	
			message = "Delete Successful";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		}catch(Exception ex)
		{
			message = "Delete Failed : " + ex.getMessage();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}

	@GetMapping("/admin/login/{username}")
	public ResponseEntity<User> getData(@PathVariable String username) {
		try {
	
		User user = AdminJpa.findById(username).get();
		//user.setPassword("");
		return ResponseEntity.status(HttpStatus.OK).body(user);
		}
		catch(Exception ex)
		{
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new User());
		}
		
	}

	@GetMapping("/users/login")
	public ResponseEntity<User> getLogin(Principal principal) {
		try {
			String username = principal.getName();
			User user = AdminJpa.findById(username).get();
			String User = principal.getName();
			if(user.getUserName().equals(User))
			{
				return ResponseEntity.status(HttpStatus.OK).body(user);
				
			}else
			{
				throw new Exception("Invalid Login");
			}
		}
		catch(Exception ex)
		{
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new User());
		}
		
		
	}
	
}
