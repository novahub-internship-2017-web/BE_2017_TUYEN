//package tuyen.novahub.assignment4.model;
//
//import java.util.Collection;
//import java.util.List;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.util.StringUtils;
//
//public class MyUserDetail extends User implements UserDetails {
//	
//	private static final long	serialVersionUID	= 1L;
//	
//	private List<String>			userRoles;
//	
//	public MyUserDetail(User user, List<String> userRoles) {
//		super(user);
//		this.userRoles = userRoles;
//		
//	}
//	
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		
//		String roles = StringUtils.collectionToCommaDelimitedString(userRoles);
//		
//		return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
//	}
//	
//	@Override
//	public String getUsername() {
//		return super.getEmail();
//	}
//	
//	@Override
//	public boolean isAccountNonExpired() {
//		return false;
//	}
//	
//	@Override
//	public boolean isAccountNonLocked() {
//		return false;
//	}
//	
//	@Override
//	public boolean isCredentialsNonExpired() {
//		return false;
//	}
//	
//	@Override
//	public boolean isEnabled() {
//		if(super.getEnabled() == 1) return true;
//		return false;
//	}
//	
//}
