/*
 * Created on Jan 12, 2011
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package com.erp.nfes;


import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
/*=============06-12-2010 */
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.sql.Array;
/**
 * @author nfes
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class DetailForm {
	//function to get the html string to render the detail_form object in the form.
	public StringBuffer getObjectHtml(String name, String action, String choice, String code, String valueString,String entityId) throws SQLException {
			StringBuffer html = new StringBuffer (1000);
			String value=null;;
			String button_id=null;	
			
			if (code!=null){
				value=code;
				}	
			if (choice!=null){
				button_id=choice;
				}
			
			String entity_type=button_id;
			entity_type=entity_type.replace("staff_profile_", "");
			entity_type=entity_type.replace("_v0", "");
			
			String htmltab=getTableData(button_id,entityId,entity_type,valueString);			
			html.append("<INPUT TYPE=\"BUTTON\" VALUE=\"Add New \" NAME=\"" + name + "_Add\" ID=\""+ button_id + "\" ONCLICK=\"showchildform('"+ button_id  + "','','" + entity_type  +"')\" ></INPUT>"+ "<input type=\"HIDDEN\" value=\"" + valueString+ "\" name=\"" + name + "\" ID=\""+ name + "_ID\" />"+htmltab);
			//System.out.println("<INPUT TYPE=\"BUTTON\" VALUE=\"" + value + "\" NAME=\"" + name + "_Add\" ID=\""+ button_id + "\" ONCLICK=\"showchildform('"+ button_id  + "','','" + entity_type  +"')\" ></INPUT>"+ "<input type=\"TEXT\" value=\"" + valueString+ "\" name=\"" + name + "\" ID=\""+ name + "_ID\" />"+htmltab);
			return html;
	}//end of function.


	public static String getTableData( String table_name,String entityid,String entity_type,String document_Ids) throws SQLException {
	   	//System.out.println("=============getTableData");
		Connection con =  null;    
		String TableStr="";
		String tab_id="Tab_"+table_name;
		String SqlStr="";	
		try  {						
			ConnectDB conObj=new ConnectDB();
			con=conObj.getMysqlConnection();
			Statement st = con.createStatement();
			Statement st1 = con.createStatement();
			Statement st2 = con.createStatement();
			ResultSet rs_items=null;
	    	if (document_Ids==""){
				document_Ids="-1";
			}
			String listedFields="";			
			ResultSet rs_listedFields = st2.executeQuery("SELECT CODE FROM staff_profile_report_v0_itemtypes WHERE NAME='"+ entity_type + "_childform'");
			while(rs_listedFields.next()) {
				listedFields=rs_listedFields.getString("CODE");
			}
			//System.out.println("listedFields:"+listedFields);
			SqlStr="SELECT  "+ listedFields +",entmst.document_id  FROM ";  
			SqlStr=SqlStr+ table_name +"_values  sp_values";
			SqlStr=SqlStr+" INNER JOIN entity_document_master entmst";
			SqlStr=SqlStr+" ON sp_values.idf=entmst.entity_id";
			SqlStr=SqlStr+" WHERE sp_values.idf='"+ entityid + "' AND entity_type='"+ entity_type +"'"; 
			SqlStr=SqlStr+" AND sp_values.number=entmst.number";
			SqlStr=SqlStr+" AND entmst.document_id IN ("+document_Ids +")"; 
			SqlStr=SqlStr+" ORDER BY sp_values.number";
			//System.out.println("SqlStr :"+SqlStr);			
			ResultSet rs_values = st.executeQuery(SqlStr);			
			
			listedFields="'" + listedFields.replace(",", "','")+"'";
			rs_items = st1.executeQuery("Select * from " + table_name + "_items where NAME IN(" + listedFields + ")");
						
			TableStr="<TABLE CLASS=\"dataTableRows\" ID='"+ tab_id + "' BORDER=\"1\" WIDTH=\"100%\">";   
			TableStr=TableStr + "<TR CLASS=\"dataTableRowHead\">";			
			TableStr=TableStr + "<TD ID=\"TD_"+ tab_id +"\">doc_Id</TD>";
			while (rs_items.next()){
				TableStr=TableStr + "<TD>"+ rs_items.getString("prompt")+ "</TD>";					
			}			
			TableStr=TableStr + "<TD></TD>"; //Edit
			TableStr=TableStr + "<TD></TD>"; //Delete			
			TableStr=TableStr + "</TR>";
			
			while(rs_values.next()){
				String document_id=rs_values.getString("document_id");
				TableStr=TableStr + "<TR ID=\"TR_"+ document_id +"\">";				
				rs_items.first();
				rs_items.previous();
				TableStr=TableStr + "<TD ID=\"TD_" + document_id + "\">"+ document_id +"</TD>";				
				while (rs_items.next()){
					//System.out.println("<TD>"+rs_values.getString(rs_items.getString("name"))+"</TD>");
					TableStr=TableStr + "<TD>"+rs_values.getString(rs_items.getString("name"))+"</TD>";						
				}
				TableStr=TableStr + "<TD><INPUT TYPE=\"BUTTON\" VALUE=\"Edit\" NAME=\"EDIT"+ document_id + "\" ONCLICK=\"showchildform('"+ table_name  + "','"+ document_id + "','" + entity_type  +"')\"/> </TD>";
				TableStr=TableStr + "<TD><INPUT TYPE=\"BUTTON\" VALUE=\"Delete\" NAME=\"DELETE"+ document_id + "\" onclick=\"deleteRow(this.parentNode.parentNode.rowIndex,'"+ tab_id + "')\"/> </TD>";
				TableStr=TableStr + "</TR>";
				
			}	
			
			
			TableStr=TableStr + "</TABLE>";	
			TableStr=TableStr + "<script language=\"javascript\">hideIdColumn('" +tab_id+"')</script>";
			//System.out.println(TableStr);			
			}catch (SQLException e) {				
				e.printStackTrace();				
			} 
			return TableStr;
		}
	
}
