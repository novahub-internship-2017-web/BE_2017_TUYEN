package tuyen.novahub.assignment4.dao;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	
	@Autowired
  private EntityManager entityManagerUser;

	public UserDao() {
		super();
	}
	
	
}
