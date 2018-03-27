package tuyen.novahub.entities;

public class Luong {
	int idLuong;
	int idUser;
	int idChucVu;
	float heSoLuong;
	public Luong() {
		super();
	}
	public Luong(int idLuong, int idUser, int idChucVu, float heSoLuong) {
		super();
		this.idLuong = idLuong;
		this.idUser = idUser;
		this.idChucVu = idChucVu;
		this.heSoLuong = heSoLuong;
	}
	public int getIdLuong() {
		return idLuong;
	}
	public void setIdLuong(int idLuong) {
		this.idLuong = idLuong;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public int getIdChucVu() {
		return idChucVu;
	}
	public void setIdChucVu(int idChucVu) {
		this.idChucVu = idChucVu;
	}
	public float getHeSoLuong() {
		return heSoLuong;
	}
	public void setHeSoLuong(float heSoLuong) {
		this.heSoLuong = heSoLuong;
	}
	
	

}
