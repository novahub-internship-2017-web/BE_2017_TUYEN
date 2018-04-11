package tuyen.novahub.assignment4.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tuyen.novahub.assignment4.dao.UserRepository;
import tuyen.novahub.assignment4.model.User;

@Service
@Transactional
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public User save(User newUser) {
		return userRepository.save(newUser);
		
	}
	
	public void deleteById(int idUser) {
		userRepository.deleteById(idUser);
	}
	
	public Optional<User> findById(int idUser) {
		return userRepository.findById(idUser);
	}

	public List<User> findAllByEnabled(int i) {
		return userRepository.findAllByEnabled(i);
	}
	
}
