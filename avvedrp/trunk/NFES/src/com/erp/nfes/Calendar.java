/*
 * 
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package com.erp.nfes;

import java.text.SimpleDateFormat;
import java.util.Date;



public class Calendar {
	//function to get the html string to render the calendar object in the form.
	public StringBuffer getObjectHtml(String name, String action, String choice, String code, String valueString) {
			StringBuffer html = new StringBuffer (1000);
			String value;
			
			//get the value to be set for the calendar object.
			if (!valueString.equalsIgnoreCase("")){
				value = valueString;
			}else if(!code.equalsIgnoreCase("")){
				value = code;
			}else {
				SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
				Date date = new Date();
				value = sdf1.format(date);
			}//end if
			
			//set the html string to render.
			String space = "			";
			html.append("<link href=\"./css/jquery.datepick.css\" rel=\"stylesheet\" type=\"text/css\" />");
			//html.append( space + "<script language=\"JavaScript\" src=\"./js/jquery-1.4.2.min.js\"></script>\n");
			html.append( space + "<script language=\"JavaScript\" src=\"./js/jquery.datepick.js\"></script>\n");
			html.append("<script>$(document).ready(function(){");
			html.append("$('#"+name+"').datepick({maxDate:'',dateFormat: 'dd-mm-yy',closeAtTop: false, showStatus: true,  showOn: 'both', buttonImageOnly: true, buttonImage: './images/calendar.gif'});");
			html.append("});</script>");
			html.append( space + "<INPUT TYPE=\"TEXT\" readonly=\"readonly\" VALUE=\"" + value + "\" NAME=\"" + name + "\" id=\""+name+"\" SIZE=\"15\"></INPUT>\n");
			
			//return the html string.
			return html;
	}//end of function.






/**
 * 
 * @param name
 * @param action
 * @param choice
 * @param code
 * @param valueString
 * @return
 */
	public StringBuffer getObjectValueString(String name, String action, String choice, String code, String valueString,String dtFormat) {
		StringBuffer valuesString = new StringBuffer (1000);
		
		String value="";
			
		//get the value to be set for the calendar object.
		if (valueString != null){
			value = valueString;
		//}else if(!code.equalsIgnoreCase("")){
		}else if(code!= null){
			SimpleDateFormat sdf1 = new SimpleDateFormat(code);
			Date date = new Date();
			value = sdf1.format(date);
		}else {
			try{
				SimpleDateFormat sdf1 = new SimpleDateFormat(code);
				Date date = new Date();
				value = sdf1.format(date);
			}catch(Exception e){
				
			}
		}//end if
		
		valuesString.append( value );
		
		
		return valuesString;
	}
	
}
