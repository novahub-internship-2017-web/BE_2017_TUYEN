package tuyen.novahub.assignment4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tuyen.novahub.assignment4.dao.UserDeleteRepository;
import tuyen.novahub.assignment4.model.UserDelete;

@Service
@Transactional
public class UserDeleteService {
	
	@Autowired
	UserDeleteRepository userDeleteRepository;

	public UserDelete save(UserDelete userDelete) {
		return userDeleteRepository.save(userDelete);
	}
	
}
