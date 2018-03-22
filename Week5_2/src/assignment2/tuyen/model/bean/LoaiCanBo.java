package assignment2.tuyen.model.bean;

public class LoaiCanBo {
	int idLoaiCanBo;
	String tenLoaiCanBo;
	public LoaiCanBo() {
		super();
	}
	public LoaiCanBo(int idLoaiCanBo, String tenLoaiCanBo) {
		super();
		this.idLoaiCanBo = idLoaiCanBo;
		this.tenLoaiCanBo = tenLoaiCanBo;
	}
	public int getIdLoaiCanBo() {
		return idLoaiCanBo;
	}
	public void setIdLoaiCanBo(int idLoaiCanBo) {
		this.idLoaiCanBo = idLoaiCanBo;
	}
	public String getTenLoaiCanBo() {
		return tenLoaiCanBo;
	}
	public void setTenLoaiCanBo(String tenLoaiCanBo) {
		this.tenLoaiCanBo = tenLoaiCanBo;
	}
	

}
