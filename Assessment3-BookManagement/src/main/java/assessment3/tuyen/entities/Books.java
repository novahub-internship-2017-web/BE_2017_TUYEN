package assessment3.tuyen.entities;

import java.sql.Date;

public class Books {
  int    idBook;
  String titleBook;
  String authorBook;
  String descriptionBook;
  Date   createdBook;
  Date   updatedBook;
  int    idUser;
  
  public Books() {
    super();
  }

  public Books(int idBook, String titleBook, String authorBook, String descriptionBook, Date createdBook,
      Date updatedBook, int idUser) {
    super();
    this.idBook = idBook;
    this.titleBook = titleBook;
    this.authorBook = authorBook;
    this.descriptionBook = descriptionBook;
    this.createdBook = createdBook;
    this.updatedBook = updatedBook;
    this.idUser = idUser;
  }

  public int getIdBook() {
    return idBook;
  }

  public void setIdBook(int idBook) {
    this.idBook = idBook;
  }

  public String getTitleBook() {
    return titleBook;
  }

  public void setTitleBook(String titleBook) {
    this.titleBook = titleBook;
  }

  public String getAuthorBook() {
    return authorBook;
  }

  public void setAuthorBook(String authorBook) {
    this.authorBook = authorBook;
  }

  public String getDescriptionBook() {
    return descriptionBook;
  }

  public void setDescriptionBook(String descriptionBook) {
    this.descriptionBook = descriptionBook;
  }

  public Date getCreatedBook() {
    return createdBook;
  }

  public void setCreatedBook(Date createdBook) {
    this.createdBook = createdBook;
  }

  public Date getUpdatedBook() {
    return updatedBook;
  }

  public void setUpdatedBook(Date updatedBook) {
    this.updatedBook = updatedBook;
  }

  public int getIdUser() {
    return idUser;
  }

  public void setIdUser(int idUser) {
    this.idUser = idUser;
  }
  
  

 
  
}
