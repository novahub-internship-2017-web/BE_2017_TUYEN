package novahub.tuyen.assignment3.library;

public class KiemTraRong {
	@SuppressWarnings("unused")
	public static int dem(String str) {
		int letterCount = 0;
		int digitCount = 0;
		int wordCount = 0;
		for (int i = 0; i < str.length(); i++) {
			if (Character.isLetter(str.charAt(i)))
				letterCount++;
			else if (Character.isDigit(str.charAt(i)))
				digitCount++;
		}
		String[] strArr = str.split("\\s", 0);
		for (String strArr1 : strArr) {
			if (!strArr1.isEmpty())
				wordCount++;
		}
		return wordCount;
	}

	public static void main(String[] args) {
		//Scanner input = new Scanner(System.in);
		//String str = input.nextLine();
		// int letterCount=0,digitCount=0,wordCount=0;
		// for(int i=0;i<str.length();i++){
		// if(Character.isLetter(str.charAt(i))) letterCount++;
		// else if(Character.isDigit(str.charAt(i))) digitCount++;
		// }
		// String[] strArr = str.split("\\s",0);
		// for (String strArr1 : strArr) {
		// if (!strArr1.isEmpty())
		// wordCount++;
		// }
		//System.out.println("So ky tu: " + dem(str));
		// System.out.println("So chu so: "+digitCount);
		// System.out.println("So tu: "+wordCount);
	}
}