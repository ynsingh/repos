/*
 * 
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package com.erp.nfes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.text.*;

public class DateTimepicker {
	//function to get the html string to render the calendar object in the form.
	public StringBuffer getObjectHtml(String name, String action, String choice, String code, String valueString) {
			StringBuffer html = new StringBuffer (1000);
			String value="";
			
			//get the value to be set for the calendar object.
			
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy h:mm a");
			if (!valueString.equalsIgnoreCase("")){
				valueString=valueString.replace("/", "-");				
				valueString=valueString.replace("PM", " PM");
				valueString=valueString.replace("AM", " AM");
				valueString=valueString.replace("  ", " ");
				DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy h:mm a");
				try {
					Date value1 =(Date)formatter.parse(valueString);
					value = sdf1.format(value1);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}else if(!code.equalsIgnoreCase("")){
				value =sdf1.format(code);
			}else {				
				Date date = new Date();				
				value = sdf1.format(date);
			}//end if						
			//set the html string to render.
			String space = "			";
			

			//html.append( space + "<script language=\"JavaScript\" src=\"./js/jquery.js\"></script>\n");
			html.append( space + "<script language=\"JavaScript\" src=\"./js/jquery-calendar.js\"></script>\n");
			html.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"./css/jquery-calendar.css\" />\n");
			html.append("<link rel=\"stylesheet\" type=\"text/css\"  href=\"./css/style_calendar.css\" />\n");
			
			html.append("<script type=\"text/javascript\">$(document).ready(function(){");
			html.append("$('#"+name+"').calendar();");			
			html.append("});</script>");
			html.append("<script type=\"text/javascript\">function dtfocus(){document.getElementById('"+name+"').focus();}</script>");
			html.append( space + "<INPUT TYPE=\"TEXT\" readonly=\"readonly\" VALUE=\"" + value + "\" NAME=\"" + name + "\" id=\""+name+"\" SIZE=\"20\"></INPUT>\n");
			html.append( space + "<img src=\"./images/calendar.gif\" onclick=\"dtfocus();\"/>");						
			//System.out.println(html);
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
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy h:mm a");			
			value = valueString;
			value = sdf1.format(value);
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
