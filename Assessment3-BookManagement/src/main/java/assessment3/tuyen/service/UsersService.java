package assessment3.tuyen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import assessment3.tuyen.dao.UsersDao;
import assessment3.tuyen.entities.Users;

@Service
@Transactional
public class UsersService {
  
  @Autowired
   UsersDao usersDao;
  
  public List<Users> findAll() {
    return usersDao.findAll();
  }


  public Users checkLogin(String email, String password) {
    return usersDao.checkLogin(email,password);
  }


  public Users findById(int id) {
    // TODO Auto-generated method stub
    return usersDao.findById(id);
  }
  
}
