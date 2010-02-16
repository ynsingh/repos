package org.dei.edrp.pms.myValidator;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.apache.commons.validator.*;
import org.apache.commons.validator.util.*;

import org.apache.struts.validator.Resources;

public class OptionValidator
{
	public static boolean checkSelectedOption(Object bean,
                       ValidatorAction va,
                       Field field,
                       ActionMessages msg,
                       Validator val,
                       HttpServletRequest request)
	{
		String s=ValidatorUtils.getValueAsString(bean,field.getKey());
		boolean bool=true;
		if(s!=null)
		{
			if(s.trim().equals("--Select--"))
			{
				msg.add(field.getKey(),Resources.getActionMessage(val, request, va,field));
				bool=false;
			}
		}
		return bool;
	}               		
}
