/*
 * Created on Jul 11, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package com.erp.nfes;

/**
 * @author ahis
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class TextArea {
	//function to set the html string for rendering in the form. 
	public StringBuffer getObjectHtml(String name, String action, String choice, String code, String valueString) {
		StringBuffer html = new StringBuffer (10000);
		//get all the options available by spliting the choice by comma.
		String rowcols[] = choice.split(",");
		//get the count of rows and columns to set the values for rendering in the form.
		String rows = ( rowcols.length == 2 ? rowcols[0] : "5" );
		String cols = ( rowcols.length == 2 ? rowcols[1] : "80" );
		
	    String txtValueString = (valueString.trim().equals("") || valueString == null ? (code.trim().equals("") || code == null ? "" : code) : valueString);
		
			html.append("<TEXTAREA NAME=\"" + name +  "\" ROWS=\"" + rows + "\" COLS=\"" + cols + "\">" + txtValueString + "</TEXTAREA>");

		//return the html string for rendering the textarea in oio form.
		return html;
	}//end function.







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
		
		String rowcols[] = choice.split(",");
		
		String txtValueString = (valueString.trim().equals("") || valueString == null ? (code.trim().equals("") || code == null ? "" : code) : valueString);
		String rows = ( rowcols.length == 2 ? rowcols[0] : "5" );
		String cols = ( rowcols.length == 2 ? rowcols[1] : "80" );
		
		valuesString.append( rows ).append( "|" ).append( cols ).append( "|" ).append( txtValueString );
		
		return valuesString;
	}





/**
 * Added by Sankaranarayanan
 * For setting document number in the addendum text
 * @param name
 * @param action
 * @param choice
 * @param code
 * @param htmlValueString
 * @param parentDocumentId
 * @return
 */

public StringBuffer getAddendumObject(String name, String action, String choice, String code, String htmlValueString,String parentDocumentId) {
	
	StringBuffer html = new StringBuffer (10000);
	//get all the options available by spliting the choice by comma.
	String rowcols[] = choice.split(",");
	//get the count of rows and columns to set the values for rendering in the form.
	String rows = ( rowcols.length == 2 ? rowcols[0] : "5" );
	String cols = ( rowcols.length == 2 ? rowcols[1] : "80" );
	
	String txtValueString = (htmlValueString.trim().equals("") || htmlValueString == null ? (code.trim().equals("") || code == null ? "" : code) : htmlValueString);
	
	txtValueString = txtValueString.replaceAll("@",parentDocumentId);
		html.append("<TEXTAREA NAME=\"" + name +  "\" ROWS=\"" + rows + "\" COLS=\"" + cols + "\">" + txtValueString + "</TEXTAREA>");

	//return the html string for rendering the textarea in oio form.
	return html;

	
}
		
}
