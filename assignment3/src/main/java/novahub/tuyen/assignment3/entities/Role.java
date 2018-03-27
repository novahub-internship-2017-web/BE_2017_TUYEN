package novahub.tuyen.assignment3.entities;

public class Role {
  int idRole;
  String nameRole;
  
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
