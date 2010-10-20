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
public class ProjectAndTaskDateComparator {

	public static boolean validateFourFields(Object bean, ValidatorAction va,
            Field field, ActionMessages msg, Validator val,
            HttpServletRequest request) {
		boolean bool=true;
		try{
		Date task_end_date,task_start_date,proj_sstart_date,proj_send_date ;
		DateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
		
		String taskEndDate =ValidatorUtils.getValueAsString(bean, field.getProperty());
		
		String sProperty2 = field.getVarValue("secondProperty");
		String taskStartDate = ValidatorUtils.getValueAsString(bean, sProperty2);
		
		String sProperty3 = field.getVarValue("thirdProperty");
		String projSStartDate = ValidatorUtils.getValueAsString(bean, sProperty3);
		
		String sProperty4 = field.getVarValue("fourthProperty");
		String projSEndDate = ValidatorUtils.getValueAsString(bean, sProperty4);
		
		task_end_date = (Date)formatter.parse(taskEndDate);    
		task_start_date = (Date)formatter.parse(taskStartDate);
		proj_sstart_date = (Date)formatter.parse(projSStartDate);    
		proj_send_date = (Date)formatter.parse(projSEndDate);
		
		//System.out.println("value1="+value+",value 2="+value2+",value3="+value3+",value4="+value4);
if(task_end_date!=null)
{
	/*
	 * [s1.compareTo(s2)]
	 * if s1>s2 then 1
	 * else if s1<s2 then -1
	 * then 0,
	 *
	 */
	
		if(task_start_date.compareTo(proj_sstart_date)>=0)//if taskStartDate>=projSStartDate
		{
			if(task_end_date.compareTo(proj_send_date)>0)//if taskEndDate>projEndDate
			{
				msg.add(field.getKey(),Resources.getActionMessage(val, request, va,field));
				bool=false;
			}	
		}
		else
		{
			msg.add(field.getKey(),Resources.getActionMessage(val, request, va,field));
			bool=false;
		}
}
		} catch (ParseException e)  {System.out.println("Exception in project and task date comparator class :"+e);    }  
		return bool;
	}
		
}
