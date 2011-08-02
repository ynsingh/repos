package com.erp.nfes;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;

public class advanceSearch extends HttpServlet{

	Connection conn;
	String action="";
	String tab_name="";

	/*private ServletConfig config;

	public void init(ServletConfig config)
		throws ServletException{
		this.config=config;

	}*/

    public void service (HttpServletRequest req, HttpServletResponse res)
      throws ServletException, IOException {

	  HttpSession session = req.getSession(true);
	  res.setContentType("text/html");
	  PrintWriter out = res.getWriter();
	  //action=req.getParameter("action");

	try{
        ConnectDB conObj=new ConnectDB();
		conn = conObj.getMysqlConnection();
		CallableStatement cs;
		String optgroup="";
		cs = conn.prepareCall("{call search_column}");
		cs.executeQuery();
		ResultSet rs = cs.getResultSet();


		out.println("<HTML><HEAD><TITLE>Search</TITLE>");
  	    out.println("<link href=\"./css/oiostyles.css\" rel=\"stylesheet\" type=\"text/css\"/>");
  	    out.println("<script type=\"text/javascript\" src=\"./js/search.js\" ></script>");
  	    out.println("</HEAD><BODY class=\"bodystyle\">");
  	    out.println("<form method=\"post\" action=\"./search\"  NAME=\"searchForm\" ID=\"search_form\" >");
  	    out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"action\" value=\""+action+"\" id=\"action\" />");


  	    //Search By
  	    out.println("<div class=\"listdiv\">");
  	    out.println("<br><table class=\"search_field_div\" align=\"center\" width=\"98%\">");
  	    out.println("<tr align=\"left\"><td>Search By</td>");
  	    out.println("<td><select name=\"searchfld\" id=\"searchfldid\">");
  	    while(rs.next()) {
  	    		String optiontxt=rs.getString("name");
  	    		String optionvalue=rs.getString("tabl")+"."+optiontxt;
  	    		if(optgroup.equals(rs.getString("tabl"))==false){
  	    			if(optgroup.equals("")==false) {
  	    				out.println("</optgroup>");}
    				optgroup=rs.getString("tabl");
    				optgroup=optgroup.replace("staff_profile_", "");
    				optgroup=optgroup.replace("_v0_items", "");
    				optgroup=optgroup.replace("_", " ");
    				optgroup=optgroup.substring(0, 1).toUpperCase()+optgroup.substring(1);
    				out.println("<optgroup label=\""+optgroup+"\">");
    				optgroup=rs.getString("tabl");
  	    		}
  	    		optiontxt=optiontxt.replace("_"," ");
  	    		optiontxt=optiontxt.substring(0, 1).toUpperCase()+optiontxt.substring(1);
  	    		out.println("<option value=\""+ optionvalue +"\">"+optiontxt+"</option>");
  			}
			out.println("</select></td>");

//			Result Fields Caption
			cs.close();
			CallableStatement cslstFld;
			optgroup="";
			cslstFld = conn.prepareCall("{call search_column}");
			cslstFld.executeQuery();
			ResultSet rslstfld= cslstFld.getResultSet();
			out.println("<td class=\"hidetd\" font color=\"red\">");
			out.println("Result Fields(ctrl+click to select multiple fields)</td>");


		//Search Operators
			//out.println("<tr align=\"left\"><td></td>");
			out.println("<td align=\"center\"><select name=\"searchop\" id=\"searchopid\">");
			out.println("<option value=\"equals\">Equals</option>");
			out.println("<option value=\"start_with\">Start with</option>");
			out.println("<option value=\"end_with\">End With</option>");
			out.println("<option value=\"includes\">Includes</option>");
			out.println("</select></td>");
//			Result Fields
			out.println("<td class=\"hidetd\" rowspan=\"5\"><select name=\"listfld\" id=\"listfldid\" MULTIPLE SIZE=5 >");
  	    	while(rslstfld.next()) {
  	    		String optiontxt=rslstfld.getString("name");
  	    		String optionvalue=rslstfld.getString("tabl")+"."+optiontxt;
  	    		if(optgroup.equals(rslstfld.getString("tabl"))==false){
  	    			if(optgroup.equals("")==false) {
  	    				out.println("</optgroup>");}
    				optgroup=rslstfld.getString("tabl");
    				out.println("<optgroup label=\""+optgroup+"\">");
  	    		}
  	    		optiontxt=optiontxt.replace("_"," ");
  	    		optiontxt=optiontxt.substring(0, 1).toUpperCase()+optiontxt.substring(1);
  	    		out.println("<option value=\""+ optionvalue +"\">"+optiontxt+"</option>");
  			}
			out.println("</select></td>");
			//out.println("</tr>");

		// Search Value
			//out.println("<tr align=\"left\">");
			out.println("<td>Value</td>");
			out.println("<td><INPUT TYPE=\"TEXT\" NAME=\"searchvalue\" ID=\"searchvalue\" SIZE=\"25\"/></td>");
			//out.println("</tr>");
		//Add Search Criteria
			//out.println("<tr align=\"center\">");
			//out.println("<td colspan=\"2\"><input type=button value=\"Add To Search Condition\" onclick=\"addtoSearchCondition(document.searchForm.searchfld.value,document.searchForm.searchop.value,document.searchForm.searchvalue.value);\"/></td></tr>");
			out.println("<td><input type=button value=\"Add To Search\" onclick=\"addtoSearchCondition(document.searchForm.searchfld.value,document.searchForm.searchop.value,document.searchForm.searchvalue.value);\"/></td></tr>");
			out.println("<tr align=\"left\"><td>Search Conditions</td>");
			out.println("<td colspan=\"5\"><TEXTAREA  NAME=\"searchConditions\" ID=\"searchConditions\" COLS=\"50\" ROWS=\"2\"></TEXTAREA></td></tr>");

		//Add Search Criteria
			out.println("<tr align=\"center\"><td colspan=\"6\"><input type=button value=\"Submit\" onclick=\"search();\"/>");
			out.println("<input type=button value=\"Reset\" onClick=\"reset();\"/>");
			out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"result_field\" id=\"result_field\" />");
			//out.println("<input type=button value=\"Result Fields\" onclick=\"getResultFields(document.searchForm.listfld);\"/></td></tr>");

			out.println("</td></tr></table>");
			
			out.println("<p>");
		// action checking
			if(action.equals("search")){
				search(conn,out,req);
	 		}
			out.println("</div><p>");
			out.println("</form></BODY></HTML>");

        conn.close();

	     }catch(Exception e){
	    	 e.printStackTrace();
	     }

	}//end of function service.
	/*public void destroy(){

	}*/


	private void search(Connection conn, PrintWriter out,HttpServletRequest req){
		String table_name ="";
		String criteria="";
		String[] search_fld_value;
		String qry_string="";
		int rec_count=0;
		String entity_ids="";
		String search_condition=req.getParameter("searchConditions");
		if(search_condition!=""){
			//out.println("<p>Search Condition :"+search_condition + "<p><p>");
			out.println("<script>showSearchCondition('"+search_condition+"');</script>");
			String result_field=req.getParameter("result_field");
			//System.out.println("Result Field========="+result_field);
			String[] tmparr=search_condition.split("\\.");
			table_name = tmparr[0].replace("_items","_values");
			criteria=tmparr[1];
			if (criteria.indexOf("equals")>0){
				search_fld_value=criteria.split("equals");
				qry_string=search_fld_value[0]+"='"+search_fld_value[1].trim()+"'";
			}
			if (criteria.indexOf("start_with")>0){
				search_fld_value=criteria.split("start_with");
				qry_string=search_fld_value[0]+" LIKE '"+search_fld_value[1].trim()+"%'";
			}
			if (criteria.indexOf("end_with")>0){
				search_fld_value=criteria.split("end_with");
				qry_string=search_fld_value[0]+" LIKE '%"+search_fld_value[1].trim()+"'";
			}
			if (criteria.indexOf("includes")>0){
				search_fld_value=criteria.split("includes");
				qry_string=search_fld_value[0]+" LIKE '%"+search_fld_value[1].trim()+"%'";
			}
			qry_string="select distinct idf from " + table_name + " where "+ qry_string ;
			//qry_string="SELECT id, user_full_name , username FROM users WHERE id IN(" + qry_string + ")";
			//

/*			SELECT  `entity_id`,`document_id` FROM `entity_document_master`  WHERE `entity_type`='awards'
				AND document_id IN
				(SELECT awards FROM `staff_profile_report_v0_values`  WHERE idf IN (SELECT idf FROM `staff_profile_awards_v0_values` WHERE `award_name` LIKE 'c%'))
*/
			try  {
			Statement st = conn.createStatement();
			ResultSet rs_users=null;
			rs_users = st.executeQuery(qry_string);
			//System.out.println("qry_string:"+qry_string);
			while(rs_users.next()){
				if (entity_ids!=""){
					entity_ids=entity_ids + ",";
				}
				deleteUnusedRecords(rs_users.getInt("idf")); // Add through child form, but not saved , then document id not in staff profile record fields
				entity_ids=entity_ids + rs_users.getInt("idf");
			}
			//System.out.println("========ent:"+entity_ids);
			if (entity_ids==""){
				out.println("<h1 align=\"center\">No Search Results</h1>");
			}
			else{
				getprofiledetails(conn,out,req,entity_ids);
			}

		}catch (SQLException e) {
			e.printStackTrace();
		}
		}
		else{
			getprofiledetails(conn,out,req,entity_ids);
		}
	}

	private void getprofiledetails(Connection conn, PrintWriter out,HttpServletRequest req,String entity_ids){
		try  {
			Statement st_institutes=conn.createStatement();
			Statement st_users = conn.createStatement();
			Statement st_searchres = conn.createStatement();
			ResultSet rs_profiledet=null;
			ResultSet rs_searchres=null;
			ResultSet rs_institutes=null;
			String search_res_qry="";
			String tabHead="";
			String tmpquery="";
			String subHead="";
			int instId;
			String instName="";
			rs_institutes=st_institutes.executeQuery("SELECT id,name FROM institution_master where active_yes_no=1 order by name ");
			while(rs_institutes.next()){
				instId=rs_institutes.getInt("id");
				instName=rs_institutes.getString("name");
				out.println("<table BORDER=1 frame=\"above\" RULES=NONE width=\"80%\" align=\"center\">");
				out.println("<TR><TD>"+instName+"</td></tr>");
				out.println("</table>");
				rs_profiledet = st_users.executeQuery("select * from  search_result_fields order by sequence");
				while (rs_profiledet.next()){
					if (entity_ids!=""){
						//if (rs_profiledet.getString("result_fields").indexOf("where ")>0){
							tmpquery=rs_profiledet.getString("result_fields")+ " and idf IN ("+ entity_ids + ")AND users.institution_id="+ instId + " order by user_full_name";
						//}
							//else{
								//tmpquery=rs_profiledet.getString("result_fields")+ " where idf IN ("+ entity_ids + ")AND users.institution_id="+ instId + " order by user_full_name";
					//}
					}
						else{
							//if (rs_profiledet.getString("result_fields").indexOf("where ")>0){
								tmpquery=rs_profiledet.getString("result_fields")+ " AND users.institution_id="+ instId + "  order by user_full_name";
							//}
							//else{
							//	tmpquery=rs_profiledet.getString("result_fields")+ " where users.institution_id="+ instId + "  order by user_full_name";
							//}
						}
					System.out.println("\n==="+tmpquery);
					rs_searchres=st_searchres.executeQuery(tmpquery);
					//out.println("<div id='"+ rs_profiledet.getString("description").replace(" ","_") +"' style=\"display:block\">");
					String tab_id=rs_profiledet.getString("description").replace(" ","_");
					out.println("<table BORDER=1 frame=\"above\" RULES=NONE width=\"80%\" align=\"center\">");
					out.println("<tr><td class=\"search_resul_category\" onclick=\"showHideSearchResults('"+tab_id+"_"+instId+"');\">"+rs_profiledet.getString("description")+ " </td></tr>");

					out.println("<tr><td id='"+ tab_id+"_"+instId +"' style=display:none>");
					//System.out.println("<tr><td id='"+ tab_id +"' style=display:none>");
					out.println("<TABLE  BORDER=\"0\" WIDTH=\"100%\">");
					//out.println("<TR ><TD>Name</TD>");
					//out.println("<TD>"+rs_profiledet.getString("description")+"</TD></TR>");
					while (rs_searchres.next()){
						 ResultSetMetaData rsMetaData = rs_searchres.getMetaData();
						 int numberOfColumns = rsMetaData.getColumnCount();
						 out.println("<TR>");
						 out.println("<TD>"+ rs_searchres.getString("user_full_name") + "</TD>");
						 out.println("<TD>");
						 for(int col=3;col<numberOfColumns;col++){
							 out.println(rs_searchres.getString(rsMetaData.getColumnName(col)) + " , ");
						 }
						 out.println("</TD>");
						 out.println("</TR>");
					}
					out.println("</table></td></tr></table>");
					rs_searchres.close();
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		}
	public void deleteUnusedRecords(int entity_id){
		try{
		Connection connect = null;
		ConnectDB conObj=new ConnectDB();
		connect = conObj.getMysqlConnection();

		/*To delete records from child table  , which not link to staff profile report */
		CallableStatement cs;
		cs = connect.prepareCall("{call remove_unused_entities("+ entity_id+")}");
		cs.executeQuery();
		connect.close();
	     }catch(Exception e){
	    	 e.printStackTrace();
	     }
	}

}