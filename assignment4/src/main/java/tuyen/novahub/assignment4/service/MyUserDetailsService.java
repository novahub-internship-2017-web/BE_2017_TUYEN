//package tuyen.novahub.assignment4.service;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import tuyen.novahub.assignment4.dao.RoleRepository;
//import tuyen.novahub.assignment4.dao.UserRepository;
//import tuyen.novahub.assignment4.model.MyUserDetail;
//import tuyen.novahub.assignment4.model.User;
//
//@Service
//public class MyUserDetailsService implements UserDetailsService {
//	UserRepository	userRepository;
//	RoleRepository	roleRepository;
//	UserService			userService;
//	RoleService roleService;
//	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		User userLogin = userService.findByEmail(username);
//		if(userLogin == null) {
//			throw new UsernameNotFoundException("No user exists "+username);
//		}else {
//			if(userLogin.getEnabled() == 1) {
//				//exist and no block
//			   List<String> userRoles = roleService.findRoleByEmail(username);
//			   return MyUserDetail(userLogin,userRoles);
//				
//			}else {
//				throw new UsernameNotFoundException("user is block "+username);
//			}
//		}
//	}
//	
//	@Autowired
//	public MyUserDetailsService(UserRepository userRepository, RoleRepository userRolesRepository) {
//		this.userRepository = userRepository;
//		this.roleRepository = userRolesRepository;
//		
//	}
//	
//}
