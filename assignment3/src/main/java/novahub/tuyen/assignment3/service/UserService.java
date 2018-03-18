package novahub.tuyen.assignment3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import novahub.tuyen.assignment3.dao.UserDao;
import novahub.tuyen.assignment3.entities.Role;
import novahub.tuyen.assignment3.entities.User;

@Service
@Transactional
public class UserService {
  @Autowired
  UserDao userDao;

  public User checkLogin(String email, String password) {
    return userDao.checkLogin(email, password);
  }

  public List<User> getListUser() {
    return userDao.getListUser();
  }

  public List<Role> getListRole() {
    return userDao.getListRole();
  }

  public Role getNameRoleById(int idRole) {
    return userDao.getNameRoleById(idRole);
  }

  public int addUser(User objUser) {
   return userDao.addUser(objUser);
  }

  public User getUserById(int id) {
    return userDao.getUserById(id);
  }

  public int editUser(User objUser) {
    return userDao.editUser(objUser);

  }

  public int delUser(int id) {
    return userDao.delUser(id);
    
  }

  public User checkEmail(String aemail) {
    // TODO Auto-generated method stub
    return userDao.checkEmail(aemail);
  }

}
