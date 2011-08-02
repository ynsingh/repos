
 /* Created on Dec 07, 2010
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package com.erp.nfes;

import sun.misc.Request;


/**
 * @author ahis
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class UploadFile {
	
//	function to get the html string to render the except object in the form.
	public StringBuffer getObjectHtml(String name, String action, String choice, String code, String valueString,String entityId) {
			StringBuffer html = new StringBuffer (1000);
			String value;
		/*	String size;	
			size="15";
			if (choice!=null){ 
				size=choice;
			} */
			if (valueString != null){
				value = valueString;
			}else if(!code.equalsIgnoreCase("")){
				value = code;
			}else {
				value = "";
			}			
			//set the html string to render.
			
			html.append("<table width=\"100%\"><tr width=\"100\"><td width=\"30%\"><input type=\"file\" value=\"" + value + "\" name=\"" + name + "\" id=\"" + name + "\" onclick=\""+ name +"_dirty()\" /></td>");
			html.append("<td width=\"10%\"><input type=\"button\" value=\"Upload\" name=\"upload_button_"+name+"\" id=\"upload_button_"+name+"\" onclick=\"uploadFile(this.name);\" /></td><td width=\"60%\"><span id=\"upload_status_"+name+"\" ></span></td></tr></table><input type=\"HIDDEN\" value=\"" + value + "\" name=\"" + name + "files\" />");
			if (value!=""){
				String uploaded_Files[]=value.split("\\|");
				if(uploaded_Files.length>0){
					String htmlStr=null;
					htmlStr="<ul id=\""+name+"_filelist\" name=\""+name+"_filelist\" >";
					for(int i = 0;i< uploaded_Files.length; i++){
						htmlStr=htmlStr + "<li><a href=\"./filedownload?filename="+uploaded_Files[i]+"&amp;userId="+ entityId + "&amp;ctrlName="+ name + "\" target=\"_blank\">"+uploaded_Files[i]+"</a></li>"; 
					}
					htmlStr=htmlStr + "</ul>";					
					html.append(htmlStr);
				}
			}else{
				html.append("<ul id=\""+name+"_filelist\" name=\""+name+"_filelist\" ></ul>");	
			}
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
		

}