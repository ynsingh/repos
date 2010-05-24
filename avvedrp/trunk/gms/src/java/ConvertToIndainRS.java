import java.text.DecimalFormat;


public  class ConvertToIndainRS {
    

    
    public static String ConvertToIndainRS(double value){
    	
    	DecimalFormat formatter = new DecimalFormat("0.00");
        String formattedValue = formatter.format(value);
        String integral = formattedValue.replaceAll("\\D\\d++", "");
        String fraction = formattedValue.replaceAll("\\d++\\D", "");
        if(integral.length() <= 3) 
        	return formattedValue; 
        else
        {
        char lastDigitOfIntegral = integral.charAt(integral.length()-1);
        integral = integral.replaceAll("\\d$", "");
        
        formattedValue= integral.replaceAll("(?<=.)(?=(?:\\d{2})+$)", ",")+
                lastDigitOfIntegral+"."+fraction;
                return formattedValue;  
        }
        
    }
}