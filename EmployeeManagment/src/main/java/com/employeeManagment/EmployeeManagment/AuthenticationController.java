package com.employeeManagment.EmployeeManagment;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AuthenticationController {



	@GetMapping("/admin/basicauth")
	public Authencationbean AdminAuth() {
		return new Authencationbean("You are Authenticated As Admin");
	}

	@GetMapping("/users/basicauth")
	public Authencationbean UserAuth() {
		return new Authencationbean("You are Authenticated As User");

	}

}