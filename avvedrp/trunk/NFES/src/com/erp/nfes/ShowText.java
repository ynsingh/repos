
package com.erp.nfes;


public class ShowText {
	//function to get the html string for rendering the showtext object in form.
	public StringBuffer getObjectHtml(String name, String action, String choice, String code, String valueString) {
			StringBuffer html = new StringBuffer (1000);
			//put the html string for the showtext object.
			if (valueString.equalsIgnoreCase("") || valueString == null){
				html.append("\n\n");
			}else{
				html.append(valueString + "\n");
			}
			//return the html string.
			return html;
	}//end if.





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
		StringBuffer valuesString = new StringBuffer (1000);
		
		if (valueString.equalsIgnoreCase("") || valueString == null){
			valuesString.append("\n\n");
		}else{
			valuesString.append(valueString + "\n");
		}
		
		return valuesString;
	}
	
}
