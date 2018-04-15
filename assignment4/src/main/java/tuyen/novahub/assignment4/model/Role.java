package tuyen.novahub.assignment4.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {
	
	@Id
	@GeneratedValue
	@Column(name = "idRole", nullable = false)
	int			idRole;
	
	@Column(name = "nameRole", length = 45, nullable = false)
	String	nameRole;
	
	public Role() {
		super();
	}
	
	public Role(int idRole, String nameRole) {
		super();
		this.idRole = idRole;
		this.nameRole = nameRole;
	}
	
	public int getIdRole() {
		return idRole;
	}
	
	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}
	
	public String getNameRole() {
		return nameRole;
	}
	
	public void setNameRole(String nameRole) {
		this.nameRole = nameRole;
	}
	
	@Override
	public String toString() {
		return "Role [idRole=" + idRole + ", nameRole=" + nameRole + "]";
	}
	
}