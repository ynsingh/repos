import java.text.DecimalFormat;


public  class ConvertToIndainRS {
    

    
    public static String ConvertToIndainRS(double value){
    	boolean isNegative = false;
    	if(value < 0) {
    		value = value * -1;
    		isNegative = true;
    	}
    	DecimalFormat formatter = new DecimalFormat("0.00");
    	String formattedValue = formatter.format(value);
        String integral = formattedValue.replaceAll("\\D\\d++", "");
        String fraction = formattedValue.replaceAll("\\d++\\D", "");
        if(integral.length() <= 3) 
        {
        	if(isNegative)
        		formattedValue = formatToNegative(formattedValue);
        	return formattedValue; 
        }
        else
        {
        char lastDigitOfIntegral = integral.charAt(integral.length()-1);
        integral = integral.replaceAll("\\d$", "");
        
        formattedValue= integral.replaceAll("(?<=.)(?=(?:\\d{2})+$)", ",")+
                lastDigitOfIntegral+"."+fraction;
        if(isNegative) {
        	formattedValue = formatToNegative(formattedValue);
        }
                return formattedValue;  
        }
        
    }
    public static String formatToNegative(String formattedValue)
    {
    	formattedValue = "- " + formattedValue;
    	return formattedValue;   	
    }
}