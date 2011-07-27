package in.ac.dei.edrp.pms.addorg_in_portal;

import java.util.*;
import java.text.*;

public class AddDate {
	public static void main(String[] args) throws ParseException
	    {
		
		String str_date="2010-07-02";//yyyy-mm-dd
		//String end_date="2010-08-06";
	         DateFormat formatter=new SimpleDateFormat("yyyy-MM-dd"); 
	     	Date date1,date2 ; 
	     	//date2 = (Date)formatter.parse(end_date);
	              date1 = formatter.parse(str_date);    
//	               System.out.println("Today is " +date1 );
	        Calendar cal = Calendar.getInstance();
//	        System.out.println("Current date : " + cal.getTime());
	        cal.setTime(date1);
	        System.out.println("Today anil: " + cal.getTime());
	System.out.println("year="+getSemesterDate(01,06,2010));
	        
	       // Substract 30 days from the calendar
//	        cal.add(Calendar.DATE, -30);
//	        System.out.println("30 days ago: " + cal.getTime());
//	 
	        // Add 10 months to the calendar
//	        cal.add(Calendar.MONTH, 10);
//	        System.out.println("10 months later: " + cal.getTime());
	 
	        // Substract 1 year from the calendar
	        cal.add(Calendar.YEAR, 1);
	        System.out.println("1 year after: " + cal.getTime());
	        
	      String  retvalue=formatter.format(cal.getTime());
	      System.out.println("returning date="+retvalue);
	    }
	
	public static int getSemesterDate(int startMonth, int endMonth, int startYear){		
		int endYear=0;
		
		if(startMonth < endMonth){
			endYear = startYear;
		}
		else if(startMonth >= endMonth){
			endYear = startYear + 1;
		}
		return endYear;
	}
	
}
