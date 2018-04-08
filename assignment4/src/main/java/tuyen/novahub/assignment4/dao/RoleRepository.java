package tuyen.novahub.assignment4.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import tuyen.novahub.assignment4.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{

	public List<Role> findAll() ;

	
}

