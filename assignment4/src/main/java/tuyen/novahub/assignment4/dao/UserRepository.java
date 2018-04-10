package tuyen.novahub.assignment4.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tuyen.novahub.assignment4.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	
	
	public List<User> findAll();
	
	/*@Query("SELECT u FROM user u WHERE u.enabled != 2")
	public List<User> findAllNoDel(); */
	
	@SuppressWarnings("unchecked")
	public User save(User newUser);

	
	/*@Query("DELETE FROM user u WHERE id_user = :idUser")
	void deleteById(@Param("idUser") int idUser);*/
	//User findById(int idUser);
}
