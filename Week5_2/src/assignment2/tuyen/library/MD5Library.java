package assignment2.tuyen.library;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Library {
	public static String md5(String str){
		String result = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			BigInteger bigInteger = new BigInteger(1,md.digest());
			result = bigInteger.toString(16);//16 để đồng bộ với sql
			//System.out.println(result);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}
	public static void main(String[] args) {
		//StringLibrary.md5("2687jkjdshf");
	}
}
