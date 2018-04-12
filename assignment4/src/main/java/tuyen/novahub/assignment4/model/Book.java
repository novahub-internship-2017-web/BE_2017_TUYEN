package tuyen.novahub.assignment4.model;

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
	@Column(name = "id_book", nullable = false)
	int							idBook;
	
	@Column(name = "title", length = 100, nullable = false)
	private String	title;
	
	@Column(name = "author", length = 100, nullable = false)
	private String	author;
	
	@Column(name = "description", length = 255, nullable = true)
	private String	description;
	
	@Column(name = "created_at", nullable = false)
	private String	createdAt;
	
	@Column(name = "updated_at", nullable = false)
	private String	updatedAt;
	
	@Column(name = "image", length = 100, nullable = true)
	private String	image;
	
	@Column(name = "enabled", nullable = false)
	private int			enabled;
	
	@Column(name = "id_user", nullable = false)
	private int			idUser;
	
	@Column(name = "remove", nullable = false)
	private int			remove;

	public Book() {
		super();
	}

	public Book(int idBook, String title, String author, String description, String createdAt, String updatedAt,
	    String image, int enabled, int idUser, int remove) {
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
		this.remove = remove;
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

	public int getRemove() {
		return remove;
	}

	public void setRemove(int remove) {
		this.remove = remove;
	}

	@Override
	public String toString() {
		return "Book [idBook=" + idBook + ", title=" + title + ", author=" + author + ", description=" + description
		    + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", image=" + image + ", enabled=" + enabled
		    + ", idUser=" + idUser + ", remove=" + remove + "]";
	}
	
	
	
}
