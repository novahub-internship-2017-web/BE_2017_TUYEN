package tuyen.novahub.assignment4.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable {
	
	private static final long	serialVersionUID	= 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_user", nullable = false)
	int												idUser;
	
	@Column(name = "email", length = 100, nullable = false)
	private String						email;
	
	@Column(name = "password", length = 100, nullable = false)
	private String						password;
	
	@Column(name = "first_name", length = 100, nullable = true)
	private String						firstName;
	
	@Column(name = "last_name", length = 100, nullable = true)
	private String						lastName;
	
	@Column(name = "enabled", nullable = false)
	private int								enabled;
	
	@Column(name = "avatar", length = 100, nullable = true)
	private String						avatar;
	
//	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "id_role"),
//	inverseJoinColumns = @JoinColumn(name = "role_id"))
	@Column(name = "id_role", nullable = false)
	private int								idRole;
	
	@Column(name = "remove", nullable = false)
	private int								remove;
	
	
	public User() {
		super();
	}
	
	public User(User user) {
    this.idUser = user.getIdUser();
    this.email = user.getEmail();
    this.password = user.getPassword();
    this.enabled = user.getEnabled();
    this.idRole = user.getIdRole();    
  }
	
	public User(int idUser, String email, String password, String firstName, String lastName, int enabled, String avatar,
	    int idRole, int remove) {
		super();
		this.idUser = idUser;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.enabled = enabled;
		this.avatar = avatar;
		this.idRole = idRole;
		this.remove = remove;
	}
	
	

	public int getIdUser() {
		return idUser;
	}
	
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public int getEnabled() {
		return enabled;
	}
	
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	
	public String getAvatar() {
		return avatar;
	}
	
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	public int getIdRole() {
		return idRole;
	}
	
	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}
	
	public int getRemove() {
		return remove;
	}
	
	public void setRemove(int remove) {
		this.remove = remove;
	}
	
	

	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", email=" + email + ", password=" + password + ", firstName=" + firstName
		    + ", lastName=" + lastName + ", enabled=" + enabled + ", avatar=" + avatar + ", idRole=" + idRole + ", remove="
		    + remove + "]";
	}
	
	
}
