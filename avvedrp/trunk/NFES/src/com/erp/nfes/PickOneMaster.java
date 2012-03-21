
package com.erp.nfes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PickOneMaster {
	//function to render the html string for pickone_master action object in the form.
	public StringBuffer getObjectHtml(String name, String action, String choice, String code, String valueString) {
				StringBuffer html = new StringBuffer (1000);
				String colstr, rowstr, selected;

				//split the choice option with the comma oparator.				

				if (choice.contains("DUAL")){
					choice=getTableData(choice);
					code=getTableData(code);
				}else{
				choice=getTableData("SELECT '-Select-' FROM DUAL UNION select * from ("+choice+")A1");
				code=getTableData("SELECT '' FROM DUAL UNION select * from ("+code+")A1");
				}	
				//System.out.println("Choice :"+choice);
				//System.out.println("Code :"+code);

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
				//html.append("		<INPUT TYPE=\"HIDDEN\" VALUE=\"\" NAME=\"" + name + "\" style=\"WIDTH: 265px\"></INPUT>\n");
				html.append("		<SELECT NAME=\"" + name + "\">\n");
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
	}//end of function.

	public StringBuffer getObjectHtml01(String name, String action, String choice, String code, String valueString,String entityId) {
		StringBuffer html = new StringBuffer (1000);
		String colstr, rowstr, selected;

		//split the choice option with the comma oparator.
        //choice=field name, code=table name
		String choice_temp=choice;
		choice=getTableData("SELECT '-Select-' FROM DUAL UNION select "+choice_temp+" from "+code+" where idf="+entityId);

		//code=choice;Commented on 21-06-11 Anish
		code=getTableData("SELECT '' FROM DUAL UNION select "+choice_temp+" from "+code+" where idf="+entityId);

		//System.out.println("Choice :"+choice);
		//System.out.println("Code :"+code);

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
		html.append("		<SELECT NAME=\"" + name + "\">\n");
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

	public static String getTableData(String qry){
		Connection con =  null;
		String dataArray="";
		try  {
			//System.out.println(qry);
			ConnectDB conObj=new ConnectDB();
			con=conObj.getMysqlConnection();
			Statement st = con.createStatement();
			ResultSet rs_items = st.executeQuery(qry);
			while (rs_items.next()){
				if (dataArray!=""){
						dataArray=dataArray  + ",";
						dataArray=dataArray  +  rs_items.getString(1) ;
				}
				else{dataArray= rs_items.getString(1) ;}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }
		return dataArray;
	}

}