package in.ac.dei.edrp.pms.myValidator;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.apache.commons.validator.*;
import org.apache.commons.validator.util.*;
import org.apache.struts.validator.Resources;


public class DuplicacyChecker
{
		public static boolean checkExistence(Object bean, ValidatorAction va,
		            Field field, ActionMessages msg, Validator val,
		            HttpServletRequest request) {
		String value =ValidatorUtils.getValueAsString(bean, field.getProperty());
		String sProperty2 = field.getVarValue("secondProperty");
		String value2 = ValidatorUtils.getValueAsString(bean, sProperty2);
		//System.out.println("value1="+value+",value 2="+value2);
		boolean bool=true;
		if(value!=null)
		{
			if(value.trim().equalsIgnoreCase(value2.trim()))
			{
				msg.add(field.getKey(),Resources.getActionMessage(val, request, va,field));
				bool=false;
			}
					
		}
		return bool;
	}   
		
}
