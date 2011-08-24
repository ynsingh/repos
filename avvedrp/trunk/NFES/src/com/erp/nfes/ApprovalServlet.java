/* Created on Feb 10, 2011
 * @author nfes
*/
package com.erp.nfes;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Locale;

public class ApprovalServlet extends HttpServlet{

	public void service (HttpServletRequest request, HttpServletResponse response) throws IOException{
			
			String language=(String) request.getSession().getAttribute("language");
			response.setContentType("text/html; charset=UTF-8");
			Locale locale = new Locale(language, "");
			MultiLanguageString ml=new MultiLanguageString();
			ml.init("ApprovalServlet.java", language);
						
			
			String action="";				
			action=request.getParameter("action");			
			PrintWriter out = response.getWriter();
			
			out.println("<HTML><HEAD><TITLE>Search</TITLE>");
	  	    out.println("<link href=\"./css/oiostyles.css\" rel=\"stylesheet\" type=\"text/css\"/>");
	  	    out.println("<script type=\"text/javascript\" src=\"./js/search.js\" ></script>");
	  	    out.println("<script>'"+action+"'</script>");
	  	    out.println("</HEAD><BODY class=\"bodystyle\">");			
			out.println("<form name=\"approval\" method=\"post\" action=\"ApprovalServlet\">");
					
			 initPage(request,response,ml);
			 if(action.equals("initPage")){
				 	out.print(ml.getValue("loading_status"));
				 	out.print("<br/>");
				 	out.println("<Script>showRecords();</script>");
				}
			 else if(action.equals("showRecords")){
				try {
					showRecords(request,response,ml);
				} catch (SQLException e) {				
					e.printStackTrace();
				}				
			}else if(action.equals("approve")){				
				String doc_ids=request.getParameter("selected_document_ids");
				//out.println(doc_ids);
				if (doc_ids.equals("null")==false){
					try {
						approveRecords(doc_ids,request,response,ml,out);
					} catch (SQLException e) {						
						e.printStackTrace();
					}
				}
			}
			out.println("</div></form></BODY></HTML>");		
	}
	

	private void initPage(HttpServletRequest request, HttpServletResponse response,MultiLanguageString ml) throws IOException{
		PrintWriter out = response.getWriter();
		out.println("<div align=\"center\" class=\"listdiv\">");
		out.println("<br>");
		
		out.println("<input type=\"HIDDEN\" ID=\"action\" name=\"action\" value=\"\"/>");
		out.println("<input type=\"HIDDEN\" ID=\"selected_document_ids\" name=\"selected_document_ids\"/>");
		
		out.println("<table class=\"search_field_div\" width=98%>");
		
		out.println("<tr><td>"+ml.getValue("university_starts_with")+"</td>");
		
  	    GetRecordValue getUserDetails=new GetRecordValue();  	    
  	    if (getUserDetails.getRole(request.getUserPrincipal().getName()).equals("ROLE_ADMIN_UNIVERSITY")){
  	    	out.println("<td><input tye=\"text\" SIZE=\"35\" id=\"university\" name=\"university\" disabled value=\""+ getUserDetails.getUniversity(request.getUserPrincipal().getName()) +"\" ></td></tr>");
  	    }else{
  	    	out.println("<td><input tye=\"text\" SIZE=\"35\" id=\"university\" name=\"university\" ></td></tr>");
  	    }
		
		out.println("<tr><td>"+ml.getValue("institution_starts_with")+"</td><td><Input Type=\"text\" SIZE=\"35\" id=\"institution\" name=\"institution\"></td></tr>");
		
		out.println("<tr><td>"+ml.getValue("department_starts_with")+"</td><td><Input Type=\"text\" SIZE=\"35\" id=\"department\" name=\"department\"></td></tr>");
		
		out.println("<tr><td>"+ml.getValue("faculty_name_starts_with")+"<td><Input Type=\"text\" SIZE=\"35\" id=\"username\" name=\"username\"></td></tr><tr>");
		
		
		out.println("<tr>");
		//out.println("<td>Category:<label class=\"mandatory\">*</label></td>");
		out.println("<td>"+ml.getValue("category")+"</td>");
		out.println("<td>");
		out.println("<select id=\"category\" name=\"category\">");
		//out.println("<option value=%>-Select-</option>");
		out.println("<option value=%>All</option>");
		out.println("<option value=\"personal_details\">PersonalDetails</option>	");
		out.println("<option value=\"qualification\">Qualification</option>");		
		out.println("<option value=\"awards\">Award</option>");
		out.println("<option value=\"journal_papers\">Publications</option>");
		out.println("<option value=\"conference_papers\">Conference Papers</option>");
		out.println("<option value=\"books_chapter\">Books or Chapter</option>");
		out.println("<option value=\"talks_lectures\">Invited Talks</option>");
		out.println("<option value=\"training\">Seminars</option>");
		out.println("<option value=\"projects\">Projects</option>");
		out.println("<option value=\"patents\">Patents</option>");
		out.println("<option value=\"media_publication\">Media Publication</option>");
		out.println("<option value=\"thesis\">Student Project</option>");
		out.println("<option value=\"faculty_exchange\">Faculty Exchange</option>");
		out.println("<option value=\"professional_body\">Professional Bodies</option>");
		out.println("<option value=\"consultancy_offered\">Consultancy Offered</option>");
		out.println("<option value=\"governance\">Governance</option>");
		out.println("<option value=\"review_committees\">Review Committees</option>");
		out.println("<option value=\"community_service\">Community Service</option>");
		//out.println("<option value=\"masterdetails\">Faculty Profile</option>");
		out.println("</select>");
		out.println("</td></tr>");
		out.println("<tr><td>"+ml.getValue("filter_by")+"<label class=\"mandatory\">*</label></td>");
		out.println("<td>");
		out.println("<select id=\"approval_status\" name=\"approval_status\">");
		//out.println("<option value=\"=''\">-Select-</option>");
		out.println("<option value=\"='0'\">Pending</option>	");
		out.println("<option value=\"='1'\">Approved</option>");
		out.println("<option value=\"in('0','1')\">All</option>");	
		out.println("</select>");
		out.println("</td></tr>");
		out.println("<tr><td></td><td><input type=\"button\" value=\""+ ml.getValue("search") + "\" onclick=\"showRecords();\"/></td></tr>");
		out.println("</table>");		
		out.println("<br>");
		
		  
	}
	private void approveRecords(String document_ids,HttpServletRequest request, HttpServletResponse response,MultiLanguageString ml,PrintWriter out) throws SQLException, IOException{
		ConnectDB conObj=new ConnectDB();
		Connection connect = conObj.getMysqlConnection();
		Statement sst = null;
		sst = connect.createStatement();
		String update_approval_dets=",approved_by='" + request.getUserPrincipal().getName()+"',approved_date=NOW() ";
		String sql="UPDATE entity_document_master SET approved_yesno=1"+update_approval_dets +" WHERE document_id IN("+document_ids+")";
		//System.out.println(sql);
		//showRecords(request, response, ml);
		out.println("<script type=\"text/javascript\">showRecords();</script>");
		try {
			sst.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
     }
	}
	private void showRecords(HttpServletRequest request, HttpServletResponse response,MultiLanguageString ml) throws IOException, SQLException{
		Connection conn = null;
		Statement stentity=null;
		ResultSet rsentity=null;
		Statement stentity_det=null;
		ResultSet rsentity_det=null;
				
		String username="";
		String university="";  
		String institution="";
		String department="";		
		String entity="";
		String approval_status="";
		
		String username_criteria="";
		String university_criteria="";  
		String institution_criteria="";
		String department_criteria="";		
		String entity_criteria="";
		String approval_status_criteria="";
		
		String qryStr="";
		String rowclass="1";
		String classname="";
		String entity_type="";		
		String searchRec="";
		int userid;
		int document_id;
		long rec_cnt=0;
		
		try {
			
			ConnectDB conObj=new ConnectDB();
			conn = conObj.getMysqlConnection();
			stentity=conn.createStatement();
			
			username_criteria = request.getParameter("username");
			university_criteria=request.getParameter("university");
			institution_criteria=request.getParameter("institution");
			department_criteria=request.getParameter("department");
			entity_criteria=request.getParameter("category");			
			approval_status_criteria=request.getParameter("approval_status");
					
			PrintWriter out = response.getWriter();
			
			/*out.println("username:"+username_criteria );
			out.println("university:"+university_criteria );
			out.println("institution:"+institution_criteria );
			out.println("department:"+department_criteria );
			out.println("category:"+entity+"\napproval_status:"+approval_status_criteria );
			*/
			
			out.println("<script type=\"text/javascript\">");			
			out.println("document.approval.username.value='"+username_criteria+"';");
			out.println("document.approval.university.value='"+university_criteria+"';");
			out.println("document.approval.institution.value='"+institution_criteria+"';");
			out.println("document.approval.department.value='"+ department_criteria +"';");
			out.println("document.approval.category.value='"+ entity_criteria +"';");
			out.println("document.approval.approval_status.value=\"" +approval_status_criteria+"\";");
			out.println("</script>");			
			out.println("<table class=\"ListTable\" width=\"98%\" align=\"center\">");
			//out.println("<tr><td colspan=\"2\"><input align=\"left\" type=\"button\" value=\"Approve Selected Records\" onclick=\"approve_without_verification();\"/></td></tr>");
			out.println("<TR>");
			out.println("<TD  class=\"hidetd\">userid</TD>");
			out.println("<TD  class=\"hidetd\">number</TD>");
			out.println("<TD width=\"2%\" class=\"ListHeader\"><input type=\"checkbox\" name=\"select_for_approval\" id=\"select_all\" onClick='javascript:funcSelect_or_Deselect_All()'  title=\"Select/Deselect\"/></TD>");//Select/Dselect
			out.println("<TD width=\"14%\" class=\"ListHeader\">"+ml.getValue("col_facultyName")+"</TD>");
			out.println("<TD width=\"14%\" class=\"ListHeader\">"+ml.getValue("col_university")+"</TD>");
			out.println("<TD width=\"14%\" class=\"ListHeader\">"+ml.getValue("col_institution")+"</TD>");
			out.println("<TD width=\"12%\" class=\"ListHeader\">"+ml.getValue("col_department")+"</TD>");
			out.println("<TD width=\"12%\" class=\"ListHeader\">"+ml.getValue("col_category")+"</TD>");
			out.println("<TD width=\"20%\" class=\"ListHeader\">"+ml.getValue("col_details")+"</TD>");
			out.println("<TD class=\"hidetd\">"+ml.getValue("col_aproval_status")+"</TD>");
			out.println("<TD width=\"10%\" class=\"ListHeader\">"+ml.getValue("approved_by")+"</TD>");
			//out.println("<TD class=\"ListHeader\">"+"Approved Date"+"</TD>");
			//out.println("<TD class=\"ListHeader\">View</TD>");
			out.println("</TR>");
		

		if(entity_criteria.equals("%")){
			rsentity=stentity.executeQuery("select * from search_result_fields where entity<>'masterdetails' order by entity");
		}else {
			rsentity=stentity.executeQuery("select * from search_result_fields WHERE entity='"+ entity_criteria +"' and entity<>'masterdetails' order by entity");
		}
		
		stentity_det=conn.createStatement();						

		
		while (rsentity.next()){

			qryStr=rsentity.getString("result_fields");			
		
		if (qryStr!=""){
			
			
			GetRecordValue  getUserDetails= new GetRecordValue();		
			if (getUserDetails.getRole(request.getUserPrincipal().getName()).equals("ROLE_ADMIN_UNIVERSITY")){		  	    
				qryStr=qryStr+" AND  staff_profile_masterdetails_v0_values.university = '" + getUserDetails.getUniversity(request.getUserPrincipal().getName())+"'";
				out.println("<script>");			
				out.println("document.approval.university.value='"+getUserDetails.getUniversity(request.getUserPrincipal().getName())+"';");
				out.println("</script>");
			}else{			
				qryStr=qryStr+" AND  staff_profile_masterdetails_v0_values.university LIKE '"+ university_criteria +"%'";
			}
			
			qryStr=qryStr+" AND  staff_profile_masterdetails_v0_values.institution LIKE '"+ institution_criteria +"%'";
			qryStr=qryStr+" AND  staff_profile_masterdetails_v0_values.department LIKE '"+ department_criteria +"%'";
			qryStr=qryStr+" AND  staff_profile_masterdetails_v0_values.user_full_name LIKE '"+ username_criteria +"%'";
			qryStr=qryStr+" AND entity_document_master.approved_yesno " + approval_status_criteria ;
			qryStr=qryStr+" order by staff_profile_masterdetails_v0_values.university,staff_profile_masterdetails_v0_values.institution,staff_profile_masterdetails_v0_values.department,staff_profile_masterdetails_v0_values.user_full_name;";
		}						
		//out.println(qryStr);
		//System.out.println(qryStr);
		rsentity_det=stentity_det.executeQuery(qryStr);
		ResultSetMetaData rsMetaData = rsentity_det.getMetaData();
		int numberOfColumns = rsMetaData.getColumnCount();
		while(rsentity_det.next()){
			if (rowclass=="1"){
				rowclass="0";			
				classname="ListRow";
			}
			else	{
			rowclass="1";
			classname="ListRownext";
			}
			rec_cnt=rec_cnt+1;			
			userid=rsentity_det.getInt("idf");
			document_id=rsentity_det.getInt("document_id");			
			institution=rsentity_det.getString("institution");				
			university=rsentity_det.getString("university");
			username=rsentity_det.getString("user_full_name");
			department=rsentity_det.getString("department");
			String approvalstatus=rsentity_det.getString("approved_yesno");
			String approved_by=rsentity_det.getString("approved_by");
			String approved_date=rsentity_det.getString("approved_date");
			
			if(approved_by==null){
				approved_by="";
				approved_date="";				
			}else{
				approved_by=approved_by.replace("@","@ ")+" , "+approved_date;
			}
			entity_type=rsentity_det.getString("entity_type");		
			entity_type = entity_type.toLowerCase();
			entity_type =entity_type.substring(0,1).toUpperCase()+entity_type.substring(1,entity_type.length());
			out.println("<tr>");
			out.println("<td  class=\"hidetd\" id=\"user_"+document_id+ "\"  >"+userid+"</td>");
			out.println("<td  class=\"hidetd\"  id=\"docid_"+document_id+ "\" >"+document_id+"</td>");
			if(approvalstatus.equals("1")){
				classname="approved";		
				approvalstatus="Yes";
				out.println("<td width=\"2%\" class=\""+classname+"\"></td>");
			}else{
				approvalstatus="No";
				out.println("<td width=\"2%\" class=\""+classname+"\"><input type=\"checkbox\" value="+document_id+" name=\"select_for_approval\" id=\"select_for_approval\"/></td>");
			}		
			out.println("<td width=\"14%\" class=\""+classname+"\">"+username+"</td>");
			out.println("<td width=\"14%\" class=\""+classname+"\">"+university+"</td>");
			out.println("<td width=\"14%\" class=\""+classname+"\">"+institution+"</td>");
			out.println("<td width=\"12%\" class=\""+classname+"\">"+department+"</td>");		
			out.println("<td width=\"12%\" class=\""+classname+"\">"+entity_type.replace("_"," ")+"</td>");
			out.println("<TD width=\"20%\" class=\"" +classname +"\"><a href=\"#\" onclick=\"showdetals("+userid+","+document_id+",'"+entity_type+"')\">");		
			 for(int col=15;col<=numberOfColumns;col++){
				 if(rsentity_det.getString(rsMetaData.getColumnName(col))!=null){
				 String details=rsentity_det.getString(rsMetaData.getColumnName(col));
				 details=details.replace("<b>", "");
				 details=details.replace("</b>", "");
				 out.println(details );
				 if (col!=numberOfColumns){out.println(",");}
				 }
			 }
			out.println("</a></TD>");
			out.println("<td  class=\"hidetd\">"+approvalstatus+"</TD>");
			out.println("<td width=\"10%\" class=\""+classname+"\">"+approved_by+"</TD>");
			//out.println("<td class=\""+classname+"\">"+approved_date+"</TD>");
			
			out.println("</tr>");	
			
		}		
	}	out.println("<tr><td colspan=\"2\"><input align=\"left\" type=\"button\" value=\""+ ml.getValue("approve_selected_records") +"\" onclick=\"approve_without_verification();\"/></td></tr>");
		out.println("</table>");
		out.println("<br/>");
		out.println("<table class=\"search_field_div\"><tr><td>&nbsp;&nbsp;&nbsp;"+ ml.getValue("summary") + rec_cnt +"&nbsp;&nbsp;&nbsp;</td></tr></table>");
		out.println("<br/>");		
		
	}finally{
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
}
	}