
package com.erp.nfes;


public class PickMany {
	
	//	function to render the html string for pickmany action object in the form.
	public StringBuffer getObjectHtml(String name, String action, String choice, String code, String valueString) {
		StringBuffer htmlStr = new StringBuffer(1500);
		
		int start = 1, end = 1;
		
		//split the choice option with the comma oparator.
		String rows[] = choice.split(",");
		
		//split the code option with the comma oparator.
		String cols[] = code.split(",");
		
		//split the value string with the comma oparator.
		String vals[] = valueString.split(",");
		
		//get the length of columns.
		int iter1 = cols.length;
		
		//get the length of rows.
		int iter2 = rows.length;
		
		//get the length of value string.
		int iter3 = vals.length;
		
		//get the minimum value for the iteration.
		int iter = (iter1 >= iter2 ? iter2 : iter1);
		int cnt = 0, num = 0;
		
		//get the html code for render.
		htmlStr.append("		<INPUT TYPE=\"HIDDEN\" VALUE=\"\" NAME=\"" + name + "\"></INPUT>\n");
		
		htmlStr.append("		<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n");
		
		//put the options with splited columns and rows.
		while (cnt < iter){
			
			String colstr = cols[cnt];
			String rowstr = rows[cnt];
			String checked = "";
			
			//put the value of the row as the selected in the pickone box.
			while(num < iter3){
				
				if(rowstr.trim().equals(vals[num]) || colstr.trim().equals(vals[num])){
					
					checked = "CHECKED=\"CHECKED\"";
					break;
					
				}else{
					
					checked = "";
					
				}//end if . else.
				
				num++;
					
			}//end while.
			
			if(start == 1){
				htmlStr.append("		<tr>\n		<td width=\"33%\">\n");	
				start = 2;
			}else if(start == 2 ){
				htmlStr.append("		<td width=\"33%\">\n");
				start = 3;
			}else if(start == 3){
				htmlStr.append("		<td width=\"33%\">\n");
				start = 1;
			}
			
			htmlStr.append("		<INPUT TYPE=\"CHECKBOX\" NAME=\"" + name + "\" VALUE=\"" + colstr + 
									",\" " + checked + "></INPUT> " + rowstr + " ;\n");
			
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
			num = 0;
			
		}//end if while.
		
		if(end != 1) htmlStr.append("		</tr>\n");
		
		htmlStr.append("		</table>\n");
		
		//return the html string.
		return htmlStr;
	}






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
