package myValidator;
import javax.servlet.http.*;

import org.apache.struts.action.*;
import org.apache.commons.validator.*;
import org.apache.commons.validator.util.*;
import org.apache.struts.validator.Resources;
public class ProjectAndTaskDateComparator {

	public static boolean validateFourFields(Object bean, ValidatorAction va,
            Field field, ActionMessages msg, Validator val,
            HttpServletRequest request) {
//System.out.println("four fields");
		String value =ValidatorUtils.getValueAsString(bean, field.getProperty());
String sProperty2 = field.getVarValue("secondProperty");
String value2 = ValidatorUtils.getValueAsString(bean, sProperty2);
String sProperty3 = field.getVarValue("thirdProperty");
String value3 = ValidatorUtils.getValueAsString(bean, sProperty3);
String sProperty4 = field.getVarValue("fourthProperty");
String value4 = ValidatorUtils.getValueAsString(bean, sProperty4);
//System.out.println("value1="+value+",value 2="+value2+",value3="+value3+",value4="+value4);
boolean bool=true;
if(value!=null)
{
	/*
	 * Here the method compareTo is called for checking whether the starting date of a project 
	  is exceed or not from the finished date of a project,
	  1.)if returning value is -1 it means the starting date is greater than the finished date of a project
	  		in this case an error message will be displayed and insertion operation will not be performed.
	  2.)if returning value is 0 then the insertion operation will be performed .
	  *
	  */
		
		if(value2.compareTo(value3)>=0)
		{
			if(value.compareTo(value4)>0)
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
	return bool;
	}
		
}
