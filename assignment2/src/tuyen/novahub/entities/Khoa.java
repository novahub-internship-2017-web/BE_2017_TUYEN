package tuyen.novahub.entities;

public class Khoa {
	int idKhoa;
	String tenKhoa;
	public Khoa() {
		super();
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
