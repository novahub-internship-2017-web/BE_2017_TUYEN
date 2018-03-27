package assignment2.tuyen.model.bean;

public class ChucVu {
	int idChucVu;
	String chucVu;
	double phuCap;
	int idLoaicanBo;
	public ChucVu() {
		super();
	}
	public ChucVu(int idChucVu, String chucVu, double phuCap, int idLoaicanBo) {
		super();
		this.idChucVu = idChucVu;
		this.chucVu = chucVu;
		this.phuCap = phuCap;
		this.idLoaicanBo = idLoaicanBo;
	}
	public int getIdChucVu() {
		return idChucVu;
	}
	public void setIdChucVu(int idChucVu) {
		this.idChucVu = idChucVu;
	}
	public String getChucVu() {
		return chucVu;
	}
	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}
	public double getPhuCap() {
		return phuCap;
	}
	public void setPhuCap(double phuCap) {
		this.phuCap = phuCap;
	}
	public int getIdLoaicanBo() {
		return idLoaicanBo;
	}
	public void setIdLoaicanBo(int idLoaicanBo) {
		this.idLoaicanBo = idLoaicanBo;
	}
	
}
