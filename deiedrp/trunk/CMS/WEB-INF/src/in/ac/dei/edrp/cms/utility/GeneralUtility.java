package in.ac.dei.edrp.cms.utility;


public class GeneralUtility {


	public static boolean isValidNumber(String s) {
	try {
	int i = Integer.parseInt(s);
	return s.length() > 1 && s.length() < 5;
	}
	catch (NumberFormatException nfe) {
	return false;
	}
	}
	 	
	 public static String isValidNumberMarks(String s) {
			String resultString = "0";
			try {
				@SuppressWarnings("unused")
				int i = Integer.parseInt(s);
				if(s.length() > 1 && s.length() < 5){
					return "1";
				}
			}
			catch (NumberFormatException nfe) {
				System.out.println("inside number format exception "+s);
				resultString = "-1";
				return resultString;
			}
			return resultString;
		}
}
