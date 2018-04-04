package tuyen.novahub.assignment4.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class Book {
	
	@Id
	@GeneratedValue
	@Column(name = "idBook", nullable = false)
	int							idBook;
	
	@Column(name = "title", length = 100, nullable = false)
	private String	title;
	
	@Column(name = "author", length = 100, nullable = false)
	private String	author;
	
	@Column(name = "description", length = 255, nullable = true)
	private String	description;
	
	@Column(name = "createdAt", nullable = false)
	private String	createdAt;
	
	@Column(name = "updatedAt", nullable = false)
	private String	updatedAt;
	
	@Column(name = "image", length = 100, nullable = true)
	private String	image;
	
	@Column(name = "enabled", nullable = false)
	private int			enabled;
	
	@Column(name = "idUser", nullable = false)
	private int			idUser;
	
	public Book() {
		super();
	}
	
	public Book(int idBook, String title, String author, String description, String createdAt, String updatedAt,
	    String image, int enabled, int idUser) {
		super();
		this.idBook = idBook;
		this.title = title;
		this.author = author;
		this.description = description;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.image = image;
		this.enabled = enabled;
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
	
	public String getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
	public String getUpdatedAt() {
		return updatedAt;
	}
	
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	public int getEnabled() {
		return enabled;
	}
	
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	
	public int getIdUser() {
		return idUser;
	}
	
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	@Override
	public String toString() {
		return "Book [idBook=" + idBook + ", title=" + title + ", author=" + author + ", description=" + description
		    + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", image=" + image + ", enabled=" + enabled
		    + ", idUser=" + idUser + "]";
	}
	
}
