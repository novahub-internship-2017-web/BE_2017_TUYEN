package tuyen.novahub.assignment4.service;

import java.util.List;

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
	
	public User findByIdUser(int idUser) {
		return userRepository.findByIdUser(idUser);
	}

	public List<User> findAllByEnabled(int i) {
		return userRepository.findAllByEnabled(i);
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public List<User> findAllByEnabledAndRemove(int enabled, int remove) {
		return userRepository.findAllByEnabledAndRemove(enabled,remove);
	}

	public List<User> findAllByRemove(int remove) {
		return userRepository.findAllByRemove(0);
	}

	public User findByIdUserAndRemove(int idUser, int remove) {
		return userRepository.findByIdUserAndRemove(idUser,remove);
	}

	public User findByEmailAndRemove(String email, int remove) {
		System.out.println("email service: "+email);
		return userRepository.findByEmailAndRemove(email,remove);
	}
	
	
}
