package novahub.tuyen.assignment3.library;

public class ChuanHoaChuoi {
	public static String chuanHoa(String str) {
		str = str.replaceAll("\\s+", " "); //dùng để thay thế các ký tự khoảng trắng thành 1 khoảng trắng duy nhất
	    str = str.replaceAll("(^\\s+|\\s+$)", "");//dùng để loại bỏ khoảng trắng ở đầu vào cuối của dữ liệu
		return str;
	}
}