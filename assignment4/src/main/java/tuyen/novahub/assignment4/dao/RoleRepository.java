package tuyen.novahub.assignment4.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tuyen.novahub.assignment4.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long>{

	public List<Role> findAll() ;

	//public List<String> findRoleByEmail(String email);

//	@Query("SELECT id_role FROM role")
//	public List<User> a();
}

