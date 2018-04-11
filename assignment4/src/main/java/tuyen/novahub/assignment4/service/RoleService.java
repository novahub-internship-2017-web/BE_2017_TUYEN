package tuyen.novahub.assignment4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tuyen.novahub.assignment4.dao.RoleRepository;
import tuyen.novahub.assignment4.model.Role;

@Service
@Transactional
public class RoleService {
	
	@Autowired
	RoleRepository roleRepository;
	
	public List<Role> findAll() {
    return roleRepository.findAll();
  }

}
