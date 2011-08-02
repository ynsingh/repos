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

public class ApprovalServlet extends HttpServlet{

	public void service (HttpServletRequest request, HttpServletResponse response) throws IOException{
			response.setContentType("text/html; charset=UTF-8");
			
			String action="";
				
			action=request.getParameter("action");
			//System.out.println("Action :"+action);
			PrintWriter out = response.getWriter();
			
			out.println("<HTML><HEAD><TITLE>Search</TITLE>");
	  	    out.println("<link href=\"./css/oiostyles.css\" rel=\"stylesheet\" type=\"text/css\"/>");
	  	    out.println("<script type=\"text/javascript\" src=\"./js/search.js\" ></script>");
	  	   
	  	    out.println("</HEAD><BODY class=\"bodystyle\">");			
			out.println("<form name=\"approval\" method=\"post\" action=\"ApprovalServlet?action=showRecords\">");
			
			 initPage(request,response);
			 if(action.equals("showRecords")){
				try {
					showRecords(request,response);
				} catch (SQLException e) {				
					e.printStackTrace();
				}
			}
			
			out.println("</div></form></BODY></HTML>");		
	}
	

	private void initPage(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		out.println("<div align=\"center\" class=\"listdiv\">");
		out.println("<br>");
		
		out.println("<table class=\"search_field_div\" width=98%>");
		
		out.println("<tr><td>University starts with:</td><td><Input Type=\"text\" id=\"university\" name=\"university\"></td></tr>");
		
		out.println("<tr><td>Institution starts with:</td><td><Input Type=\"text\" id=\"institution\" name=\"institution\"></td></tr>");
		
		out.println("<tr><td>Department starts with:</td><td><Input Type=\"text\" id=\"department\" name=\"department\"></td></tr>");
		
		out.println("<tr><td>Faculty Name starts with:</td><td><Input Type=\"text\" id=\"username\" name=\"username\"></td></tr><tr>");
		
		
		out.println("<tr>");
		out.println("<td>Category:<label class=\"mandatory\">*</label></td>");
		out.println("<td>");
		out.println("<select id=\"category\" name=\"category\">");
		out.println("<option value=\"\">-Select-</option>");		
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
		out.println("<tr><td>Show :<label class=\"mandatory\">*</label></td>");
		out.println("<td>");
		out.println("<select id=\"approval_status\" name=\"approval_status\">");
		out.println("<option value=\"=''\">-Select-</option>");
		out.println("<option value=\"='0'\">Pending</option>	");
		out.println("<option value=\"='1'\">Approved</option>");
		out.println("<option value=\"in('0','1')\">All</option>");	
		out.println("</select>");
		out.println("</td></tr>");
		out.println("<tr><td></td><td ><input type=\"submit\" value=\"Search\" /></td></tr>");
		
		out.println("</table>");
		out.println("<br>");
		
		  
	}
	
	private void showRecords(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException{
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
		
		String qryStr="";
		String rowclass="1";
		String classname="";
		String entity_type="";
		int userid;
		int document_id;
		
		
		try {
			username = request.getParameter("username");
			university=request.getParameter("university");
			institution=request.getParameter("institution");
			department=request.getParameter("department");
			entity=request.getParameter("category");
			approval_status=request.getParameter("approval_status");
					
			PrintWriter out = response.getWriter();
			
			/*out.println("username:"+username);
			out.println("university:"+university);
			out.println("institution:"+institution);
			out.println("department:"+department);
			out.println("category:"+entity+"\napproval_status:"+approval_status);
			*/
			
			out.println("<script>");			
			out.println("document.approval.username.value='"+username+"';");
			out.println("document.approval.university.value='"+university+"';");
			out.println("document.approval.institution.value='"+institution+"';");
			out.println("document.approval.department.value='"+ department +"';");
			out.println("document.approval.category.value='"+ entity +"';");
			out.println("document.approval.approval_status.value=\"" +approval_status+"\";");
			out.println("</script>");
			
			out.println("<table class=\"ListTable\" width=\"98%\" align=\"center\">");
			out.println("<TR>");
			out.println("<TD class=\"hidetd\">userid</TD>");
			out.println("<TD class=\"hidetd\">number</TD>");
			out.println("<TD class=\"ListHeader\">University</TD>");
			out.println("<TD class=\"ListHeader\">Institution</TD>");
			out.println("<TD class=\"ListHeader\">Department</TD>");
			out.println("<TD class=\"ListHeader\">Faculty Name</TD>");
			out.println("<TD class=\"ListHeader\">Details</TD>");
			out.println("<TD class=\"ListHeader\">Approved</TD>");
			out.println("<TD class=\"ListHeader\">View</TD>");
			out.println("</TR>");
		
		ConnectDB conObj=new ConnectDB();
		conn = conObj.getMysqlConnection();
		stentity=conn.createStatement();
		rsentity=stentity.executeQuery("select * from search_result_fields WHERE entity='"+ entity +"'");
		
		stentity_det=conn.createStatement();						

		
		while (rsentity.next()){
			//rsentity_det=stentity_det.executeQuery(rsentity.getString("result_fields"));
			if (qryStr.equals("")==false){qryStr=qryStr + " union all ";}
			qryStr=qryStr +rsentity.getString("result_fields");				
		}
		if (qryStr!=""){
			/*qryStr=qryStr+" AND  staff_profile_masterdetails_v0_values.university LIKE '"+ university +"%'";
			qryStr=qryStr+" AND  staff_profile_masterdetails_v0_values.institution LIKE '"+ institution +"%'";
			qryStr=qryStr+" AND  staff_profile_masterdetails_v0_values.department LIKE '"+ department +"%'";
			qryStr=qryStr+" AND  staff_profile_masterdetails_v0_values.user_full_name LIKE '"+ username +"%'";
			qryStr=qryStr+" AND entity_document_master.approved_yesno " + approval_status ;
			qryStr=qryStr+" order by staff_profile_masterdetails_v0_values.university,staff_profile_masterdetails_v0_values.institution,staff_profile_masterdetails_v0_values.department,staff_profile_masterdetails_v0_values.user_full_name;";*/
			
			qryStr="SELECT * FROM ("+ qryStr +") A" ;
			qryStr=qryStr+" where  university LIKE '"+ university +"%'";
			qryStr=qryStr+" AND  institution LIKE '"+ institution +"%'";
			qryStr=qryStr+" AND  department LIKE '"+ department +"%'";
			qryStr=qryStr+" AND  user_full_name LIKE '"+ username +"%'";
			qryStr=qryStr+" AND approved_yesno " + approval_status ;
			qryStr=qryStr+" order by university,institution,department,user_full_name;";
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
							
			userid=rsentity_det.getInt("idf");
			document_id=rsentity_det.getInt("document_id");
			institution=rsentity_det.getString("institution");				
			university=rsentity_det.getString("university");
			username=rsentity_det.getString("user_full_name");
			department=rsentity_det.getString("department");
			approval_status=rsentity_det.getString("approved_yesno");
			entity_type=rsentity_det.getString("entity_type");
			if(approval_status.equals("1")){
				classname="approved";				
			}
			out.println("<tr>");
			out.println("<td class=\"hidetd\" id=\"user_"+document_id+ "\"  >"+userid+"</td>");
			out.println("<td class=\"hidetd\"  id=\"docid_"+document_id+ "\" >"+document_id+"</td>");			
			out.println("<td class=\""+classname+"\">"+university+"</td>");
			out.println("<td class=\""+classname+"\">"+institution+"</td>");
			out.println("<td class=\""+classname+"\">"+department+"</td>");
			out.println("<td class=\""+classname+"\">"+username+"</td>");
			out.println("<TD class=\"" +classname +"\">");		
			 for(int col=12;col<=numberOfColumns;col++){
				 String details=rsentity_det.getString(rsMetaData.getColumnName(col));
				 details=details.replace("<b>", "");
				 details=details.replace("</b>", "");
				 out.println(details );
				 if (col!=numberOfColumns){out.println(",");}
			 }
			out.println("</TD>");					
			if(approval_status.equals("0")){
				approval_status="No";	
				out.println("<td class=\""+classname+"\">"+approval_status+"</TD>");
				out.println("<td class=\"" +classname +"\"><a href=\"#\" onclick=\"showdetals("+userid+","+document_id+",'"+entity_type+"')\">Approve</a></td>");				
				}else{
					approval_status="Yes";					
					out.println("<td class=\""+classname+"\">"+approval_status+"</TD>");
					out.println("<td class=\"" +classname +"\"><a href=\"#\" onclick=\"showdetals("+userid+","+document_id+",'"+entity_type+"')\">View</a></td>");
				}			
			
			out.println("</tr>");		
			
		}		
			
	}finally{
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
}
	}