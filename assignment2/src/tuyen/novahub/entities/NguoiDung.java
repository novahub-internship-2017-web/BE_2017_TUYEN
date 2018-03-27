package tuyen.novahub.entities;

public class NguoiDung {
	int idUser;
	String username;
	String password;
	String ho;
	String ten;
	int yearOfBirth;
	String address;
	int idLoaiDangNhap;
	int idLoaiCanBo;
	public NguoiDung() {
		super();
	}
	public NguoiDung(int idUser, String username, String password, String ho, String ten, int yearOfBirth,
			String address, int idLoaiDangNhap, int idLoaiCanBo) {
		super();
		this.idUser = idUser;
		this.username = username;
		this.password = password;
		this.ho = ho;
		this.ten = ten;
		this.yearOfBirth = yearOfBirth;
		this.address = address;
		this.idLoaiDangNhap = idLoaiDangNhap;
		this.idLoaiCanBo = idLoaiCanBo;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHo() {
		return ho;
	}
	public void setHo(String ho) {
		this.ho = ho;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public int getYearOfBirth() {
		return yearOfBirth;
	}
	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getIdLoaiDangNhap() {
		return idLoaiDangNhap;
	}
	public void setIdLoaiDangNhap(int idLoaiDangNhap) {
		this.idLoaiDangNhap = idLoaiDangNhap;
	}
	public int getIdLoaiCanBo() {
		return idLoaiCanBo;
	}
	public void setIdLoaiCanBo(int idLoaiCanBo) {
		this.idLoaiCanBo = idLoaiCanBo;
	}
	
	
	
	
}
