package assignment2.tuyen.model.bean;

public class GiangVien{
	int idGiangVien;
	int idUser;
	int idKhoa;
	int soTietDay;
	public GiangVien() {
		super();
	}
	public GiangVien(int idGiangVien, int idUser, int idKhoa, int soTietDay) {
		super();
		this.idGiangVien = idGiangVien;
		this.idUser = idUser;
		this.idKhoa = idKhoa;
		this.soTietDay = soTietDay;
	}
	
	public int getIdGiangVien() {
		return idGiangVien;
	}
	
  public void setIdGiangVien(int idGiangVien) {
		this.idGiangVien = idGiangVien;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public int getIdKhoa() {
		return idKhoa;
	}
	public void setIdKhoa(int idKhoa) {
		this.idKhoa = idKhoa;
	}
	public int getSoTietDay() {
		return soTietDay;
	}
	public void setSoTietDay(int soTietDay) {
		this.soTietDay = soTietDay;
	}
	
	

}
