package tuyen.novahub.assignment4.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tuyen.novahub.assignment4.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	
	public List<User> findAll();
	
	public List<User> findAllByEnabled(int enabled);
	
	@SuppressWarnings("unchecked")
	public User save(User newUser);
	
	public User findByEmail(String email);
	
	public User findByIdUser(int idUser);

	public List<User> findAllByEnabledAndRemove(int enabled, int remove);

	public List<User> findAllByRemove(int i);

	public User findByIdUserAndRemove(int idUser, int remove);
	
}
