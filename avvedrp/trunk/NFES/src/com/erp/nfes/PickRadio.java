/*
 * Created on Mar 17, 2004
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
public class PickRadio {

	//	function to render the html string for radio button action object in the form.
	public StringBuffer getObjectHtml(String name, String action, String choice, String code, String valueString) {
		StringBuffer htmlStr = new StringBuffer(1500);		
		int start = 1, end = 1;	
		if ( valueString != null && valueString.startsWith( "," ) == true ) {
			valueString = valueString.substring( 1 );
		}
		//split the choice option with the comma oparator.
		String rows[] = choice.split(",");		
		//split the code option with the comma oparator.
		String cols[] = code.split(",");		
		//get the length of columns.
		int iter1 = cols.length;
		//get the length of rows.
		int iter2 = rows.length;		
		//get the minimum value for the iteration.
		int iter = (iter1 >= iter2 ? iter2 : iter1);
		int cnt = 0;
		
		//get the html code for render.
		htmlStr.append("		<INPUT TYPE=\"HIDDEN\" VALUE=\"\" NAME=\"" + name + "\"></INPUT>\n");
		
		//htmlStr.append("		<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n");
		htmlStr.append("		<table  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n"); // to eliminate gap between radio button
		
		//put the options with splited columns and rows.
		while (cnt < iter){
			
			String colstr = cols[cnt];
			String rowstr = rows[cnt];
			
			//put the value of the row as the selected in the pickone box.
			String checked = (rowstr.trim().equals(valueString) || colstr.trim().equals(valueString) ? "CHECKED=\"CHECKED\"" : "");
			
			if (cnt==0){checked="CHECKED=\"CHECKED\"";}
			
			if(start == 1){
				//htmlStr.append("		<tr>\n		<td width=\"33%\">\n");
				htmlStr.append("		<tr>\n		<td>\n");
				start = 2;
			}else if(start == 2 ){
				//htmlStr.append("		<td width=\"33%\">\n");
				htmlStr.append("		<td >\n");
				start = 3;
			}else if(start == 3){
				//htmlStr.append("		<td width=\"33%\">\n");
				htmlStr.append("		<td>\n");
				start = 1;
			}
						
			htmlStr.append("		<INPUT TYPE=\"RADIO\" NAME=\"" + name + "\" VALUE=\"" + colstr + 
									"\" " + checked + "></INPUT> " + rowstr + "\n");
			
			if(end == 1){
				htmlStr.append("		</td>\n");
				end = 2;
			}else if(end == 2 ){
				htmlStr.append("		</td>\n");
				end = 3;
			}else if(end == 3){
				htmlStr.append("		</td>\n		</tr>\n");
				end = 1;
			}
					
			//increment the count by one.
			cnt++;
			
		}//end if while.
		
		if(end != 1) htmlStr.append("		</tr>\n");
		htmlStr.append("		</table>\n");
		//System.out.println(htmlStr);
		//return the html string.		
		return htmlStr;
	}

// Akhil
	public StringBuffer getObjectHtml01(String name, String action, String choice, String code, String valueString) {
		StringBuffer htmlStr = new StringBuffer(1500);		
		int start = 1, end = 1;	
		if ( valueString != null && valueString.startsWith( "," ) == true ) {
			valueString = valueString.substring( 1 );
		}
		//split the choice option with the comma oparator.
		String rows[] = choice.split(",");		
		//split the code option with the comma oparator.
		String cols[] = code.split(",");		
		//get the length of columns.
		int iter1 = cols.length;
		//get the length of rows.
		int iter2 = rows.length;		
		//get the minimum value for the iteration.
		int iter = (iter1 >= iter2 ? iter2 : iter1);
		int cnt = 0;
		
		//get the html code for render.
		htmlStr.append("		<INPUT TYPE=\"HIDDEN\"  VALUE=\"\" NAME=\"" + name + "\"></INPUT>\n");
		
		//htmlStr.append("		<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n");
		htmlStr.append("		<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n"); // to eliminate gap between radio button
		
		//put the options with splited columns and rows.
		while (cnt < iter){
			
			String colstr = cols[cnt];
			String rowstr = rows[cnt];
			
			//put the value of the row as the selected in the pickone box.
			String checked = (rowstr.trim().equals(valueString) || colstr.trim().equals(valueString) ? "CHECKED=\"CHECKED\"" : "");
			if (cnt==0){checked="CHECKED=\"CHECKED\"";}
			
			if(start == 1){
				//htmlStr.append("		<tr>\n		<td width=\"33%\">\n");
				htmlStr.append("		<tr>\n		<td>\n");
				start = 2;
			}else if(start == 2 ){
				//htmlStr.append("		<td width=\"33%\">\n");
				htmlStr.append("		<td >\n");
				start = 3;
			}else if(start == 3){
				//htmlStr.append("		<td width=\"33%\">\n");
				htmlStr.append("		<td >\n");
				start = 1;
			}
						
			htmlStr.append("		<INPUT TYPE=\"RADIO\" NAME=\"" + name + "\" onclick=\""+name+"_click(value)\" VALUE=\"" + colstr + 
									"\" " + checked + "></INPUT> " + rowstr + "\n");
			
			if(end == 1){
				htmlStr.append("		</td>\n");
				end = 2;
			}else if(end == 2 ){
				htmlStr.append("		</td>\n");
				end = 3;
			}else if(end == 3){
				htmlStr.append("		</td>\n		</tr>\n");
				end = 1;
			}
					
			//increment the count by one.
			cnt++;
			
		}//end if while.
		
		if(end != 1) htmlStr.append("		</tr>\n");
		htmlStr.append("		</table>\n");
		
		
		
		//return the html string.
		return htmlStr;
	}


//	 Akhil - End



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
		StringBuffer valuesString = new StringBuffer(1500);
		
		return valuesString;
	}

}
