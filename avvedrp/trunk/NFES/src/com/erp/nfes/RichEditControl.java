
package com.erp.nfes;

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

		return valuesString;
	}

}
