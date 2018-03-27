package tuyen.novahub.entities;

public class LoaiDangNhap {
	int idLoaiDangNhap;
	String tenLoaiDangNhap;
	public LoaiDangNhap() {
		super();
	}
	public LoaiDangNhap(int idLoaiDangNhap, String tenLoaiDangNhap) {
		super();
		this.idLoaiDangNhap = idLoaiDangNhap;
		this.tenLoaiDangNhap = tenLoaiDangNhap;
	}
	public int getIdLoaiDangNhap() {
		return idLoaiDangNhap;
	}
	public void setIdLoaiDangNhap(int idLoaiDangNhap) {
		this.idLoaiDangNhap = idLoaiDangNhap;
	}
	public String getTenLoaiDangNhap() {
		return tenLoaiDangNhap;
	}
	public void setTenLoaiDangNhap(String tenLoaiDangNhap) {
		this.tenLoaiDangNhap = tenLoaiDangNhap;
	}
	
}
