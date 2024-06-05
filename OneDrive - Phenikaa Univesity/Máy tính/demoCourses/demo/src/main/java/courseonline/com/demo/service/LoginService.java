package courseonline.com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import courseonline.com.demo.enity.Login;
import courseonline.com.demo.respository.LoginRespostory;

@Service
public class LoginService {
    @Autowired
	private LoginRespostory repo;
  
	public Login login(String username, String password) {
		Login user = repo.findByUsernameAndPassword(username, password);
		return user;
	}

	public boolean isValidUser(Login user) {
		return false;
	}

	public void save(Login login){
		repo.save(login);
	}

	public Login getById(int id){
		return repo.findById(id);
	}
}
