package assignment2.tuyen.model.bean;

public class Khoa {
	int idKhoa;
	String tenKhoa;
	public Khoa() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Khoa(int idKhoa, String tenKhoa) {
		super();
		this.idKhoa = idKhoa;
		this.tenKhoa = tenKhoa;
	}
	public int getIdKhoa() {
		return idKhoa;
	}
	public void setIdKhoa(int idKhoa) {
		this.idKhoa = idKhoa;
	}
	public String getTenKhoa() {
		return tenKhoa;
	}
	public void setTenKhoa(String tenKhoa) {
		this.tenKhoa = tenKhoa;
	}
	
}
