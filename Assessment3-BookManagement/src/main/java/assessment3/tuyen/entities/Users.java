package assessment3.tuyen.entities;

import org.hibernate.validator.constraints.NotBlank;

public class Users {
  int    idUser;
  @NotBlank(message ="not null")
  String email;
  @NotBlank(message ="not null")
  String password;
  String firstName;
  String lastName;
  int    idRole;
  int    active;
  
  public Users() { 
    super();
  }

  public Users(int idUser, String email, String password, String firstName, String lastName, int idRole, int active) {
    super();
    this.idUser = idUser;
    this.email = email;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.idRole = idRole;
    this.active = active;
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

  public int getIdRole() {
    return idRole;
  }

  public void setIdRole(int idRole) {
    this.idRole = idRole;
  }

  public int getActive() {
    return active;
  }

  public void setActive(int active) {
    this.active = active;
  }

  @Override
  public String toString() {
    return "Users [idUser=" + idUser + ", email=" + email + ", password=" + password + ", firstName=" + firstName
        + ", lastName=" + lastName + ", idRole=" + idRole + ", active=" + active + "]";
  }
  
  
}
