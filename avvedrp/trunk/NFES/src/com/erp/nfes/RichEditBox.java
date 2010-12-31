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
public class RichEditBox {
	//function to set the html string for rendering in the form. 
	public StringBuffer getObjectHtml(String name, String action, String choice, String code, String valueString) {
		StringBuffer html = new StringBuffer (10000);

		String rchValueString = (valueString == null || valueString.trim().equals("") ? (code == null || code.trim().equals("") ? "" : code) : valueString);
		if((rchValueString == null) || (rchValueString.equalsIgnoreCase(""))){
			html.append("\n<INPUT TYPE=\"HIDDEN\" NAME=\"" + name + "\" VALUE=\"\"></INPUT>\n");
		}else{
			
			html.append("\n<INPUT TYPE=\"HIDDEN\" NAME=\"" + name + "\" VALUE=\"" + rchValueString + "\"></INPUT>\n");
		}//end if.
//		html.append("<iframe id=\"edit_" + name + "\" width=\"670px\" height=\"250px\"  src=\"./Jsp/discharge/Style_Sheets/Blank.htm\" onLoad=\"edit_" + name + "_enableDesignMode();\" onBlur=\"setControlName(this.id);\"></iframe>\n");		
		html.append("<script language=\"JavaScript\" type=\"text/javascript\">\n");
//		html.append("<!--\nfunction edit_" + name + "_enableDesignMode() {\n");
//		html.append("\n \n\n");
//		html.append("	if (browser.isIE5up) {\n");
//		html.append("		frames.edit_" + name + ".document.designMode = \"On\";\n");
//		html.append("	}\n");
//		html.append("	else {\n");
//		html.append("		document.getElementById('edit_" + name + "').contentDocument.designMode = \"On\"\n");
//		html.append("	}\n\n");
//		html.append("	if(document.getElementById('edit_" + name + "').contentWindow.document.body != null){\n");
//		html.append("		document.getElementById('edit_" + name + "').contentWindow.document.body.innerHTML=document.myForm." + name + ".value;\n\n");
//		html.append("	}\n\n");
//		html.append("}\n\n");
		html.append("<!--\n");
		
		html.append("	writeRichText('edit_" + name + "',document.all." + name + ".value , 720, 250, true, false);");
		
		html.append("\n//-->\n");
		html.append("</script>\n");
		
//		html.append("<iframe id=\"edit_" + name + "\" width=\"670px\" height=\"250px\" onBlur=\"setControlName(this.id);\"></iframe>\n");
		
//		String rchValueString = (valueString == null || valueString.trim().equals("") ? (code == null || code.trim().equals("") ? "" : code) : valueString);
//		
//		if((rchValueString == null) || (rchValueString.equalsIgnoreCase(""))){
//			//if the valuestring is not passed to the function then.
//			//when displaying a fresh new form.
//			html.append("<INPUT TYPE=\"HIDDEN\" NAME=\"" + name + "\" VALUE=\" \"></INPUT>\n");
//		}else{
//			//html.append("<iframe id=\"edit_" + name + "\" width=\"670px\" height=\"250px\" onLoad=\"edit_" + name + "_enableDesignMode();\" onBlur=\"setControlName(this.id);\">" + valuestring + "</iframe>\n");
//			rchValueString = HisUtils.fixSpecialHTMLCharacters(rchValueString);
//			html.append("<INPUT TYPE=\"HIDDEN\" NAME=\"" + name + "\" VALUE=\"" + rchValueString + "\"></INPUT>\n");
//		}//end if.
		
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
		
		return valuesString;
	}
	
}
