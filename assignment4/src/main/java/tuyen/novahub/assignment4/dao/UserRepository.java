package tuyen.novahub.assignment4.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tuyen.novahub.assignment4.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	
	public List<User> findAll();
	
	
//	@Query("SELECT u FROM user u WHERE u.enabled =1")
	public List<User> findAllByEnabled(int enabled);
	
	
	@SuppressWarnings("unchecked")
	public User save(User newUser);


	public User findByEmail(String email);

	
	/*@Query("DELETE FROM user u WHERE id_user = :idUser")
	void deleteById(@Param("idUser") int idUser);*/
	//User findById(int idUser);
	
//  @Query("select enabled from user u where u.enabled=0")
//  public  List<User> findAllDisabled();
}
