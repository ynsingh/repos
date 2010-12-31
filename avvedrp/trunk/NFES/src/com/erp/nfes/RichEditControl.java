/*
 * Created on Aug 8, 2003
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
public class RichEditControl {
	//function to set the html string for rendering in the form.
	public StringBuffer getObjectHtml(String name, String action, String choice, String code, String valueString) {
		StringBuffer html = new StringBuffer (1000);

//			html.append("<iframe id=\"edit_" + name + "\" style=\"position: absolute; visibility: hidden; width: 0px; height: 0px;\"></iframe>\n");
//			html.append("<script language=\"JavaScript\" type=\"text/javascript\" src=\"./Jsp/discharge/Style_Sheets/browserdetect.js\"></script>\n");
//			html.append("<script language=\"JavaScript\" type=\"text/javascript\" src=\"./Jsp/discharge/Style_Sheets/richtext.js\"></script>\n");
//			html.append("<script language=\"JavaScript\" type=\"text/javascript\">\n");
//			html.append("<!--\nStart();\n//-->\n</script>\n");
			html.append("<script language=\"JavaScript\" type=\"text/javascript\" src=\"./js/richtext.js\">")
				.append("</script>\n")
				.append("<script language=\"JavaScript\" type=\"text/javascript\">\n")
				.append("	initRTE(\"./Jsp/discharge/Style_Sheets/images/\", \"./Jsp/discharge/Style_Sheets/\", \"\");")
				.append("\n</script>\n");

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
		String txtValueString  = "";
		

//		//String rowcols[] = choice.split(",");
//		if(!HisValidator.isEmpty(valueString)){
//
//		}
//		String txtValueString = (valueString.trim().equals("") || valueString == null ? (code.trim().equals("") || code == null ? "" : code) : valueString);
////		String rows = ( rowcols.length == 2 ? rowcols[0] : "5" );
////		String cols = ( rowcols.length == 2 ? rowcols[1] : "80" );
////
//		//valuesString.append( rows ).append( "|" ).append( cols ).append( "|" ).append( txtValueString );
//		valuesString.append(txtValueString);
//
//		//valueString = HisUtils.fixSpecialHTMLCharacters(valueString);
//		valuesString.append(valueString);
		return valuesString;
	}

}
