/*
 * Created on Jan 12, 2011
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package com.erp.nfes;

import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DetailForm {
	public static String recordCount="";
	public static String table_name_count="";
	//function to get the html string to render the detail_form object in the form.
	public StringBuffer getObjectHtml(String name, String action, String choice, String code, String valueString,String entityId,String language) throws SQLException {
			StringBuffer html = new StringBuffer (1000);
			String value=null;;
			String button_id=null;
            MultiLanguageString ml=new MultiLanguageString();
            ml.init("DetailForm.java", language);
			if (code!=null){
				value=code;
				}
			if (choice!=null){
				button_id=choice;
				}

			String entity_type=button_id;
			entity_type=entity_type.replace("staff_profile_", "");
			entity_type=entity_type.replace("_v0", "");

			String htmltab=getTableData(button_id,entityId,entity_type,valueString,language,ml);
			html.append("<div style=\"height:22px;\"><div style=\"position:absolute;\"><INPUT TYPE=\"BUTTON\" VALUE=\""+ml.getValue("add_new")+"\" NAME=\"" + name + "_Add\" ID=\""+ button_id + "\" ONCLICK=\"showchildform('"+ button_id  + "','','" + entity_type  +"')\" ></INPUT></div>"+"<div style=\"position:absolute;right:35px;font-size:11px;font-weight:bold;padding-top:7px;\">Total Records:</div>"+"<div style=\"position:absolute;right:25px;font-size:11px;font-weight:bold;vertical-align:bottom;padding-top:7px;\" id=\"div_recordcount_"+""+table_name_count+"\">"+recordCount+"</div></div>"+"<input type=\"HIDDEN\" value=\"" + valueString+ "\" name=\"" + name + "\" ID=\""+ name + "_ID\" />"+ "<input type=\"HIDDEN\" value=\"" + value + "\" name=\"" + name + "_FIELDS\" ID=\""+ name + "_FIELDSID\" />"+htmltab);
			
			//System.out.println("<INPUT TYPE=\"BUTTON\" VALUE=\"" + value + "\" NAME=\"" + name + "_Add\" ID=\""+ button_id + "\" ONCLICK=\"showchildform('"+ button_id  + "','','" + entity_type  +"')\" ></INPUT>"+ "<input type=\"TEXT\" value=\"" + valueString+ "\" name=\"" + name + "\" ID=\""+ name + "_ID\" />"+htmltab);
			return html;
	}//end of function.


	public static String getTableData( String table_name,String entityid,String entity_type,String document_Ids,String language,MultiLanguageString ml) throws SQLException {
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
			Statement st3=con.createStatement();
			ResultSet rs_items=null;
			if(document_Ids.equals("")){
				document_Ids="-1";
			}
			
			String listedFields="";
			ResultSet rs_listedFields = st2.executeQuery("SELECT CODE FROM staff_profile_report_v0_itemtypes WHERE NAME='"+ entity_type + "_childform'");
			while(rs_listedFields.next()) {
				listedFields=rs_listedFields.getString("CODE");
			}
			//System.out.println("listedFields:"+listedFields);
			
			/* Avoid the need of multiple saving for child records. i.e.  no need to save the  document ids in staff_profile_report_vo_values entity field values   
			SqlStr="SELECT  "+ listedFields +",entmst.document_id  FROM ";
			SqlStr=SqlStr+ table_name +"_values  sp_values";
			SqlStr=SqlStr+" INNER JOIN entity_document_master entmst";
			SqlStr=SqlStr+" ON sp_values.idf=entmst.entity_id";
			SqlStr=SqlStr+" WHERE sp_values.idf='"+ entityid + "' AND entity_type='"+ entity_type +"'";
			SqlStr=SqlStr+" AND sp_values.number=entmst.number";
			SqlStr=SqlStr+" AND entmst.document_id IN ("+document_Ids +")";
			SqlStr=SqlStr+" ORDER BY sp_values.number";
			*/
			
			SqlStr="SELECT  "+ listedFields +",entmst.document_id  FROM ";
			SqlStr=SqlStr+ table_name +"_values  sp_values";
			SqlStr=SqlStr+" INNER JOIN entity_document_master entmst";
			SqlStr=SqlStr+" ON sp_values.idf=entmst.entity_id";
			SqlStr=SqlStr+" WHERE sp_values.idf='"+ entityid + "' AND entity_type='"+ entity_type +"'";
			SqlStr=SqlStr+" AND sp_values.number=entmst.number";			
			SqlStr=SqlStr+" ORDER BY sp_values.number";			
			
			
			
			ResultSet rs_values = st.executeQuery(SqlStr);
			rs_values.last();
      		recordCount=Integer.toString(rs_values.getRow());
      		rs_values.beforeFirst();

			listedFields="'" + listedFields.replace(",", "','")+"'";
			
			//	Tested OK(12-05-2011): SELECT staff_profile_awards_v0_items.name,SUBSTRING_INDEX(language_localisation.language_string, '|',-1 ) AS abbreviation FROM staff_profile_awards_v0_items,language_localisation WHERE language_localisation.control_name=staff_profile_awards_v0_items.name AND language_localisation.language_code='en' AND language_localisation.active_yes_no=1 AND language_localisation.file_code = (SELECT id FROM file_master WHERE active_yes_no=1 AND SUBSTRING_INDEX(NAME, '.',1 ) = 'staff_profile_awards_v0') AND staff_profile_awards_v0_items.name IN('award_name','agency_name','receiving_month_year')

			//rs_items = st1.executeQuery("Select A.*,SUBSTR( prompt,(INSTR(prompt,'|'))-LENGTH(prompt)) AS abbreviation from " + table_name + "_items A where NAME IN(" + listedFields + ")");
			
			rs_items = st1.executeQuery("Select "+table_name+"_items.name,SUBSTRING_INDEX(language_localisation.language_string, '|',-1 ) AS abbreviation from "+table_name+"_items,language_localisation WHERE language_localisation.control_name="+table_name+"_items.name AND language_localisation.language_code=\'"+language+"\' AND language_localisation.active_yes_no=1 AND language_localisation.file_code = (SELECT id FROM file_master WHERE active_yes_no=1 AND SUBSTRING_INDEX(NAME, '.',1 ) = \'"+table_name+"\') And "+table_name+"_items.name IN(" + listedFields + ")");
			TableStr="<TABLE WIDTH=\"100%\"><tr><td height=\"1px\" ><INPUT TYPE=\"HIDDEN\" VALUE=\""+recordCount+"\" NAME=\"recordcount_"+ table_name + "\" ID=\"recordcount_"+ table_name + "\"/> </td></tr></TABLE>";
			table_name_count=table_name;
			TableStr=TableStr +"<TABLE CLASS=\"dataTableRows\" ID='"+ tab_id + "' BORDER=\"1\" WIDTH=\"100%\">";
			TableStr=TableStr + "<TR CLASS=\"dataTableRowHead\">";
			TableStr=TableStr + "<TD CLASS=\"dataTableRowHead\" ID=\"TD_"+ tab_id +"\">doc_Id</TD>";
			while (rs_items.next()){
				//TableStr=TableStr + "<TD>"+ rs_items.getString("prompt")+ "</TD>";
				TableStr=TableStr + "<TD CLASS=\"dataTableRowHead\">"+ rs_items.getString("abbreviation").replace("&", "&amp;")+ "</TD>";
			}
			TableStr=TableStr + "<TD CLASS=\"dataTableRowHead\" style=\"text-align:center\" >"+ml.getValue("edit")+"</TD>"; //Edit
			TableStr=TableStr + "<TD CLASS=\"dataTableRowHead\" style=\"text-align:center\" >"+ml.getValue("delete")+"</TD>"; //Delete
			TableStr=TableStr + "</TR>";

			while(rs_values.next()){
				String document_id=rs_values.getString("document_id");
				String record_approved="0";
				ResultSet rsapprove= st3.executeQuery("Select approved_yesno from entity_document_master where document_id="+document_id);
				while (rsapprove.next()){
					record_approved= rsapprove.getString("approved_yesno");
				}
				TableStr=TableStr + "<TR CLASS=\"dataTableRows\" ID=\"TR_"+ document_id +"\">";
				rs_items.beforeFirst();//rs_items.first();rs_items.previous();
				TableStr=TableStr + "<TD CLASS=\"dataTableRows\" ID=\"TD_" + document_id + "\">"+ document_id +"</TD>";
				while (rs_items.next()){
					//System.out.println("<TD>"+rs_values.getString(rs_items.getString("name"))+"</TD>");
					TableStr=TableStr + "<TD CLASS=\"dataTableRows\">"+rs_values.getString(rs_items.getString("name")).replace("&","&amp;")+"</TD>";
				}
				//System.out.println("docid:"+document_id+","+record_approved);
				if(record_approved.equals("0")){
					TableStr=TableStr + "<TD CLASS=\"dataTableRows\" style=\"text-align:center\" ><INPUT TYPE=\"BUTTON\" VALUE=\""+ml.getValue("edit")+"\" NAME=\"EDIT"+ document_id + "\" ONCLICK=\"showchildform('"+ table_name  + "','"+ document_id + "','" + entity_type  +"')\"/> </TD>";
					TableStr=TableStr + "<TD CLASS=\"dataTableRows\" style=\"text-align:center\" ><INPUT TYPE=\"BUTTON\" VALUE=\""+ml.getValue("delete")+"\" NAME=\"DELETE"+ document_id + "\" onclick=\"deleteRow(this.parentNode.parentNode.rowIndex,'"+ tab_id + "')\"/> </TD>";
				}else{
					TableStr=TableStr + "<TD CLASS=\"dataTableRows\" style=\"text-align:center\" ><INPUT TYPE=\"BUTTON\" VALUE=\""+ml.getValue("edit")+"\" NAME=\"EDIT"+ document_id + "\" ONCLICK=\"showchildform('"+ table_name  + "','"+ document_id + "','" + entity_type  +"')\"/> </TD>";
					//System.out.println(TableStr=TableStr + "<TD CLASS=\"dataTableRows\" style=\"text-align:center\" ><INPUT TYPE=\"BUTTON\" disabled=\"disabled\" VALUE=\""+ml.getValue("edit")+"\" NAME=\"EDIT"+ document_id + "\" ONCLICK=\"showchildform('"+ table_name  + "','"+ document_id + "','" + entity_type  +"')\"/> </TD>");
					TableStr=TableStr + "<TD CLASS=\"dataTableRows\" style=\"text-align:center\" ><INPUT TYPE=\"BUTTON\" disabled=\"disabled\" VALUE=\""+ml.getValue("delete")+"\" NAME=\"DELETE"+ document_id + "\" onclick=\"deleteRow(this.parentNode.parentNode.rowIndex,'"+ tab_id + "')\"/> </TD>";					
				}
				TableStr=TableStr + "</TR>";

			}


			TableStr=TableStr + "</TABLE>";
			TableStr=TableStr + "<script language=\"javascript\">hideIdColumn('" +tab_id+"')</script>";
			//System.out.println(TableStr);			
			}catch (SQLException e) {
				e.printStackTrace();
			}			
			finally{
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		    }
		return TableStr;
		}

}
