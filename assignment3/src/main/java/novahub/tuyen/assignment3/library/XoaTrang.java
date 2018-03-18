package novahub.tuyen.assignment3.library;

public class XoaTrang {
	public static String deletespace(String str)
	{
			//\\s+ pattern tượng trưng cho một hoặc nhiều ký tự khoảng trắng
	    str = str.replaceAll("\\s+", " "); //dùng để thay thế các ký tự khoảng trắng thành 1 khoảng trắng duy nhất
	    str = str.replaceAll("(^\\s+|\\s+$)", "");//dùng để loại bỏ khoảng trắng ở đầu vào cuối của dữ liệu
	    return str;
	}
	public static void main(String[] args) {
		System.out.println(deletespace("1996"));
	}

}
