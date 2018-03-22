package assignment2.tuyen.model.bean;

public class NhanVien{
	int idNhanVien;
	int idUser;
	int idPhongBan;
	int soNgayLamViec;
	public NhanVien() {
		super();
	}
	public NhanVien(int idNhanVien, int idUser, int idPhongBan, int soNgayLamViec) {
		super();
		this.idNhanVien = idNhanVien;
		this.idUser = idUser;
		this.idPhongBan = idPhongBan;
		this.soNgayLamViec = soNgayLamViec;
	}
	
  public int getIdNhanVien() {
		return idNhanVien;
	}
	public void setIdNhanVien(int idNhanVien) {
		this.idNhanVien = idNhanVien;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public int getIdPhongBan() {
		return idPhongBan;
	}
	public void setIdPhongBan(int idPhongBan) {
		this.idPhongBan = idPhongBan;
	}
	public int getSoNgayLamViec() {
		return soNgayLamViec;
	}
	public void setSoNgayLamViec(int soNgayLamViec) {
		this.soNgayLamViec = soNgayLamViec;
	}
	

}
