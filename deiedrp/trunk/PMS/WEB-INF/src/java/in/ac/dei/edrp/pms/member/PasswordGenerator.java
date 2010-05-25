package in.ac.dei.edrp.pms.member;


public class PasswordGenerator {
	
//	public static void main(String arg[])
//	{
//	System.out.println(generatePassword(5,10).toUpperCase());
//	}
	// declares the main function which has to accept the values from within the function of integer type.	
    public static String generatePassword(int minLen, int maxLen) 
	{
    	
    	
    	//Declares the variable of type Integer int and also defining the range of the variable
    	final int RANGE = 10 + 26 + 26;
    	if ((maxLen < minLen) || (minLen <= 0)) 
		{           
					// Error Handling
            		throw new IllegalArgumentException();
        }

        //Declares the variables of type integer and an array of characters.
        int len = (int) (Math.random() * (maxLen - minLen)) + minLen;
        char[] charr = new char[len];
        
        /*
         * Place the variable in a FOR LOOP to generate a string of characters and numbers randomly using 
         *the predefined function random in the math class of java.awt.math and multiplying the variable 
         *with the range and then returning the string to the main function.
        */
        for (int i = 0; i < len; i++) 
	    {
            int n = (int) (Math.random() * RANGE);
            if (n < 10){
                	charr[i] = (char) (n + 48);
            	} 
            else if (n < 36){
                charr[i] = (char) (n + (65 - 10));
            	} 
            else{
                charr[i] = (char) (n + (97 - 36));
            	}
        }
        //Passing the value to the caller function
        return new String(charr);
    }
}