package com.employeeManagment.EmployeeManagment.UserFiles;

import java.io.IOException;
import java.util.Comparator;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.employeeManagment.EmployeeManagment.Entity.FileDB;
import com.employeeManagment.EmployeeManagment.Entity.User;

@Service
public class FileStorageService {

  @Autowired
  private FileDBRepository fileDBRepository;

  public FileDB store(MultipartFile file,String userName) throws IOException {
   
	String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes(),userName,new Date());
    return fileDBRepository.save(FileDB);
    
  }

  public FileDB getFile(String id,String userName) {
    FileDB fileDB = fileDBRepository.findById(id).get();
    
    if(fileDB.getUsername().equals(userName))
    {
    	return fileDBRepository.findById(id).get();
    }
    
    return null;
  }
  
  public Stream<FileDB> getAllFiles(String userName) {
    return fileDBRepository.findAll().stream().filter(obj -> obj.getUsername().equals(userName)).sorted(Comparator.comparing(FileDB::getName));
	
  }
  
  public boolean deleteUserFile(String id,String userName)
  {
	  try
	  {
		  
		  FileDB User = fileDBRepository.findById(id).get();
		  if(User.getUsername().equals(userName))
		  {
			  fileDBRepository.deleteById(id);
			  return true;
		  }
		  return false;
		  
	  }catch(Exception ex)
	  {
		  return false;
	  }
		 
  }
  
}