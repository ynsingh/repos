
package com.erp.nfes;


public class Except {
	//function to get the html string to render the except object in the form.
	public StringBuffer getObjectHtml(String name, String action, String choice, String code, String valueString) {
			StringBuffer html = new StringBuffer (1000);
			String value;
			String size;	
			size="15";
			if (choice!=null){ 
				size=choice;
			}
			if (valueString != null){
				value = valueString;
			}else if(!code.equalsIgnoreCase("")){
				value = code;
			}else {
				value = "";
			}
			
			//set the html string to render.
		//	html.append("<INPUT TYPE=\"TEXT\" VALUE=\"" + value + "\" NAME=\"" + name + "\" SIZE=\"15\"></INPUT>");
			html.append("<INPUT TYPE=\"TEXT\" VALUE=\"" + value + "\" NAME=\"" + name + "\" SIZE=\""+ size + "\" ></INPUT>");	
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
	public StringBuffer getObjectValueString(String name, String action, String choice, String code, String valueString) {
		StringBuffer valuesString = new StringBuffer (10000);
		
		return valuesString;
	}



public StringBuffer getService(String name, String action, String valuestring) {
	StringBuffer html = new StringBuffer (1000);

	html.append("<INPUT TYPE=\"TEXT\" VALUE=\"" + valuestring + "\" NAME=\"" + name + "\" SIZE=\"75\"></INPUT>");
	
	return html;

}

}
