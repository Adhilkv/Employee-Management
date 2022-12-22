package com.employeeManagment.EmployeeManagment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.employeeManagment.EmployeeManagment.AdminManagement.AdminManagementRepository;
import com.employeeManagment.EmployeeManagment.Entity.User;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private AdminManagementRepository adminManagementRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = adminManagementRepository.findById(username).get();
		if(user == null)
		{
			throw new UsernameNotFoundException("User Not Found");
		}
		
		return new CustomUserDetails(user);
	}

}
