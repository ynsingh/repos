package in.ac.dei.edrp.pms.myValidator;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.apache.commons.validator.*;
import org.apache.commons.validator.util.*;
import org.apache.struts.validator.Resources;
public class DateComparator{

	public static boolean validateTwoFields(Object bean, ValidatorAction va,
            Field field, ActionMessages msg, Validator val,
            HttpServletRequest request) {
		boolean bool=true;
		try{
			Date str_date=null,end_date=null ; 
			DateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
			
			String endDate =ValidatorUtils.getValueAsString(bean, field.getProperty());
			String sProperty2 = field.getVarValue("secondProperty");
			String startDate = ValidatorUtils.getValueAsString(bean, sProperty2);
			//System.out.println("in date comparator startDate="+startDate+",endDate="+endDate);
			if(startDate!="" && endDate!="")
			{
			str_date = (Date)formatter.parse(startDate);    
			end_date = (Date)formatter.parse(endDate);
			}
			if(str_date!=null && end_date!=null)
			{
				/*
				 * Here the method compareTo is called for checking whether the starting date of a project 
	  				is exceed or not from the finished date of a project [s1.compareTo(s2)],
	  				1.)if returning value is 1 it means the starting date(s1) is greater than the finished date(s2) of a project
	  					in this case an error message will be displayed and insertion operation will not be performed.
	  				2.)if returning value is 0 or -1 then the insertion operation will be performed .
				 *
				 */
		
				if(str_date.compareTo(end_date)>0)//if str_date>end_date then 1 else -1,0
				{
					msg.add(field.getKey(),Resources.getActionMessage(val, request, va,field));
					bool=false;
				}
			}
		} catch (ParseException e)  {
			//System.out.println("Exception in datecomparator class :"+e);
			}  
		return bool;
	}
		
}
