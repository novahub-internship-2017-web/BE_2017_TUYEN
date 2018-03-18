package novahub.tuyen.assignment3.entities;

import java.util.Date;

public class Book {
  
  int    idBook;
  String title;
  String pictureBook;
  String author;
  String description;
  Date   created_at;
  Date   updated_at;
  int    idUser;
  public Book() {
    super();
  }
  public Book(int idBook, String title, String pictureBook, String author, String description, Date created_at,
      Date updated_at, int idUser) {
    super();
    this.idBook = idBook;
    this.title = title;
    this.pictureBook = pictureBook;
    this.author = author;
    this.description = description;
    this.created_at = created_at;
    this.updated_at = updated_at;
    this.idUser = idUser;
  }
  public int getIdBook() {
    return idBook;
  }
  public void setIdBook(int idBook) {
    this.idBook = idBook;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getPictureBook() {
    return pictureBook;
  }
  public void setPictureBook(String pictureBook) {
    this.pictureBook = pictureBook;
  }
  public String getAuthor() {
    return author;
  }
  public void setAuthor(String author) {
    this.author = author;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public Date getCreated_at() {
    return created_at;
  }
  public void setCreated_at(Date created_at) {
    this.created_at = created_at;
  }
  public Date getUpdated_at() {
    return updated_at;
  }
  public void setUpdated_at(Date updated_at) {
    this.updated_at = updated_at;
  }
  public int getIdUser() {
    return idUser;
  }
  public void setIdUser(int idUser) {
    this.idUser = idUser;
  }
  @Override
  public String toString() {
    return "Book [idBook=" + idBook + ", title=" + title + ", pictureBook=" + pictureBook + ", author=" + author
        + ", description=" + description + ", created_at=" + created_at + ", updated_at=" + updated_at + ", idUser="
        + idUser + "]";
  }
 
  
  

}
