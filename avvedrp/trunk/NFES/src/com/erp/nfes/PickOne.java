/*
 * Created on Jul 13, 2003
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
public class PickOne {
	//function to render the html string for pickone action object in the form.
	public StringBuffer getObjectHtml(String name, String action, String choice, String code, String valueString) {
				StringBuffer html = new StringBuffer (1000);
				String colstr, rowstr, selected;
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
				//html.append("		<INPUT TYPE=\"HIDDEN\" VALUE=\"\" NAME=\"" + name + "\"></INPUT>\n");
				html.append("		<SELECT NAME=\"" + name + "\" ID=\"" + name + "\">\n");				
				//put the options with splited columns and rows.
				while (cnt < iter){
					colstr = cols[cnt];
					rowstr = rows[cnt];
					//put the value of the row as the selected in the pickone box.
					selected = (rowstr.trim().equals(valueString) || colstr.trim().equals(valueString) ? "SELECTED=\"SELECTED\"" : "");
					html.append("			<OPTION VALUE=\"" + colstr + "\" " + selected + ">" + rowstr + "</OPTION>\n");					
					//increment the count by one.
					cnt++;
				}//end if while.
				html.append("		</SELECT>\n");
				
				//return the html string to render.
				return html;
	}//end of if.






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