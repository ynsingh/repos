

package com.erp.nfes;

import sun.misc.Request;

public class UploadFileControl {
	
//	function to get the html string to render the except object in the form.
	public StringBuffer getObjectHtml(String name, String action, String choice, String code, String valueString,String entityId,String number,String formName) {
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
			html.append("<td width=\"10%\"><input type=\"button\" value=\"Upload\" name=\"upload_button_"+name+"\" id=\"upload_button_"+name+"\" onclick=\"uploadFile(this.name);\" /></td><td width=\"60%\"><span id=\"upload_status_"+name+"\" ></span></td></tr></table><input type=\"HIDDEN\" value=\"" + value + "\" id=\"" + name + "files\" name=\"" + name + "files\" />");
			if (value.equals("")==false){
				String uploaded_Files[]=value.split("\\|");
				if(uploaded_Files.length>0){
					String htmlStr=null;
					htmlStr="<table class=\"tabBG4\" id=\""+name+"_filelist\" name=\""+name+"_filelist\" >";
					htmlStr=htmlStr + "<tr><td class=\"dataTableRowHead\">SlNo</td><td class=\"dataTableRowHead\">Uploaded File Name</td><td class=\"dataTableRowHead\">Delete</td></tr>";
					for(int i = 0;i< uploaded_Files.length; i++){
						htmlStr=htmlStr + "<tr id=\""+name+"_"+(i+1)+"\"><td class=\"dataTableRows\">"+(i+1)+"</td><td class=\"dataTableRows\"><a href=\"./DownloadFile?filename="+uploaded_Files[i]+"&amp;userId="+ entityId + "&amp;ctrlName="+ name + "\" target=\"_blank\">"+uploaded_Files[i]+"</a></td>"+
										  "<td class=\"dataTableRows\"><a href=\"./DeleteUploadedFile?number="+ number +"&amp;formname="+ formName +"&amp;filename="+uploaded_Files[i]+"&amp;userId="+ entityId + "&amp;ctrlName="+ name +"&amp;rowId="+(i+1)+"&amp;totalNumber="+uploaded_Files.length+"\" target=\"upload_target_iframe\">Delete</a></td></tr>"; 
					}
					htmlStr=htmlStr + "</table>";					
					html.append(htmlStr);
				}
			}else{
				html.append("<table id=\""+name+"_filelist\" name=\""+name+"_filelist\" ></table>");	
			}
			html.append("<script language=\"javascript\">\n" +
			"if(document.getElementById(\"approved\").value==1){\n" +
			"document.getElementById(\"upload_button_"+name+"\").disabled = true;\n}\n" +
			"</script>");
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