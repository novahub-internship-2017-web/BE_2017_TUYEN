package novahub.tuyen.assignment3.entities;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class User {
  int    idUser;
  @NotEmpty(message="Vui lòng nhập vào email!")
  @Email(message="Vui lòng nhập email đúng định dạng!")
  String email;
  @NotEmpty(message="Vui lòng nhập vào password!")
  String password;
  @NotEmpty(message="Vui lòng nhập vào họ tên!")
  String firstName;
  String lastName;
  String picture;
  int    idRole;
  int    active;

  public User() {
    super();
  }

  public User(int idUser, String email, String password, String firstName, String lastName, String picture, int idRole,
      int active) {
    super();
    this.idUser = idUser;
    this.email = email;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.picture = picture;
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

  public String getPicture() {
    return picture;
  }

  public void setPicture(String picture) {
    this.picture = picture;
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
    return "User [idUser=" + idUser + ", email=" + email + ", password=" + password + ", firstName=" + firstName
        + ", lastName=" + lastName + ", picture=" + picture + ", idRole=" + idRole + ", active=" + active + "]";
  }
  

}
