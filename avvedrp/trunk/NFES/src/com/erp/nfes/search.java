package com.erp.nfes;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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

public class search extends HttpServlet{

	Connection conn;
	String action="";
	String tab_name="";
	
	/*private ServletConfig config;

	public void init(ServletConfig config)
		throws ServletException{
		this.config=config;

	}
*/
    public void service (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

	  HttpSession session = req.getSession(true);
	  res.setContentType("text/html");
	  PrintWriter out = res.getWriter();
	  action=req.getParameter("action");
	 
	  String search_flds="";
	 	  
	  try {
		  File file = new File(req.getRealPath("")+"/xml/basic_search_items.xml");		  
		  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		  DocumentBuilder db = dbf.newDocumentBuilder();
		  Document doc = db.parse(file);
		  doc.getDocumentElement().normalize();
		  //out.println("Root element " + doc.getDocumentElement().getNodeName());
		  NodeList nodeLst = doc.getElementsByTagName("table");
		  //out.println("Information of all employees");
		  for (int s = 0; s < nodeLst.getLength(); s++) {
		    Node fstNode = nodeLst.item(s);		    
		    if (fstNode.getNodeType() == Node.ELEMENT_NODE) {		  
		      Element rootElmnt = (Element) fstNode;		      
		      NodeList fldCaptionElmntLst = rootElmnt.getElementsByTagName("field_caption");
		      Element fld_caption_Elmnt = (Element) fldCaptionElmntLst.item(0);
		      NodeList fld_caption = fld_caption_Elmnt.getChildNodes();
		      //out.println("First Name : "  + ((Node) fld_caption.item(0)).getNodeValue());
		      
		      NodeList fldNameElmntLst = rootElmnt.getElementsByTagName("field_name");
		      Element fld_nameElmt = (Element) fldNameElmntLst.item(0);
		      NodeList fld_name = fld_nameElmt.getChildNodes();
		      //out.println("Last Name : " + ((Node) fld_name.item(0)).getNodeValue());
		      search_flds=search_flds+"<option value="+((Node) fld_name.item(0)).getNodeValue()+">"+((Node) fld_caption.item(0)).getNodeValue()+"</option>";		      
		    }
		  }		  //out.println(search_flds);
		  } catch (Exception e) {
		    e.printStackTrace();
		  }	  
	  
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
  	    //out.println("<table align=\"center\" BORDER=1 FRAME=BOX RULES=NONE width=\"100%\">");
  	    out.println("<br><table class=\"search_field_div\" align=\"center\" width=\"98%\">");
  	    out.println("<tr>");
  	    out.println("<td>University </td>");
  	    out.println("<td colspan=\"2\"><input tye=\"text\" name=\"university\" size=\"45\"></td>");
  	    //out.println("<td></td>");
  	    out.println("<td>Institution</td>");
  	    out.println("<td colspan=\"2\"><input tye=\"text\" name=\"institution\" size=\"50\" ></td>" );
  	    out.println("</tr>");
  	    out.println("<tr align=\"left\"><td>Search By</td>");
  	    out.println("<td><select name=\"searchfld\" id=\"searchfldid\">");
		out.println("<option value=staff_profile_personaldetails_v0_values.user_full_name>Faculty Name</option>");
		//out.println("<option value=staff_profile_personaldetails_v0_values.uniname>University</option>");
		//out.println("<option value=staff_profile_personaldetails_v0_values.instname>Institution</option>");
		
  	    /*while(rs.next()) {
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
  	    		
  			}*/
  	    	out.println(search_flds);	
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
			out.println("<option value=\"start_with\">Start with</option>");
			out.println("<option value=\"equals\">Equals</option>");			
			out.println("<option value=\"end_with\">End With</option>");
			out.println("<option value=\"includes\">Includes</option>");
			out.println("<option value=\"between\">between</option>");
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
			out.println("<td><INPUT TYPE=\"TEXT\" NAME=\"searchvalue\" ID=\"searchvalue\"/></td>");
			out.println("<td> and <INPUT TYPE=\"TEXT\" NAME=\"searchvalue2\" ID=\"searchvalue2\"/></td>");
			//out.println("</tr>");
		//Add Search Criteria	
			//out.println("<tr align=\"center\">");
			//out.println("<td colspan=\"2\"><input type=button value=\"Add To Search Condition\" onclick=\"addtoSearchCondition(document.searchForm.searchfld.value,document.searchForm.searchop.value,document.searchForm.searchvalue.value);\"/></td></tr>");
			out.println("<td style=display:none><input type=button value=\"Add To Search Condition\" onclick=\"addtoSearchCondition(document.searchForm.searchfld.value,document.searchForm.searchop.value,document.searchForm.searchvalue.value);\"/></td></tr>");
			out.println("<tr  style=display:none align=\"left\"><td>Search Conditions</td>");
			out.println("<td colspan=\"5\"><TEXTAREA  NAME=\"searchConditions\" ID=\"searchConditions\" COLS=\"90\" ROWS=\"2\"></TEXTAREA></td></tr>");
			
		//Add Search Criteria	
			out.println("<tr align=\"center\"><td colspan=\"6\"><input type=button value=\"Submit\" onclick=\"search();\"/>");
			out.println("<input type=button value=\"Reset\" onClick=\"reset();\"/> (Date Format :YYYY-MM-DD)");
			out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"result_field\" id=\"result_field\" />");
			//out.println("<input type=button value=\"Result Fields\" onclick=\"getResultFields(document.searchForm.listfld);\"/></td></tr>");
			
			out.println("</td></tr></table>");
			out.println("<p><p>");	
			
		// action checking
			if (action!=null){
				if(action.equals("search")){					
					search(conn,out,req);
		 		}
			}	
			out.println("</div><p>");
			out.println("</form></BODY></HTML>");
			
        conn.close(); 

	     }catch(Exception e){
	    	 e.printStackTrace(); 
	     }

	}//end of function service.
/*	public void destroy(){

	}
*/
	
	private void search(Connection conn, PrintWriter out,HttpServletRequest req){
		String table_name ="";
		String criteria="";
		String[] search_fld_value;
		String qry_string="";
		int rec_count=0;
		String entity_ids="";		
		String search_condition=req.getParameter("searchConditions");
		String[]  search_between_value;
			 
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
				qry_string=table_name+"."+search_fld_value[0];				
				qry_string=checkDataType(qry_string,req);
				qry_string=qry_string+"='"+search_fld_value[1].trim()+"'"; 	
			}
			if (criteria.indexOf("start_with")>0){
				search_fld_value=criteria.split("start_with");				
				qry_string=table_name+"."+search_fld_value[0];				
				qry_string=checkDataType(qry_string,req);
				qry_string=qry_string+" LIKE '"+search_fld_value[1].trim()+"%'"; 	
			}
			if (criteria.indexOf("end_with")>0){
				search_fld_value=criteria.split("end_with");
				qry_string=table_name+"."+search_fld_value[0];
				qry_string=checkDataType(qry_string,req);
				qry_string=qry_string+" LIKE '%"+search_fld_value[1].trim()+"'"; 	
			}
			if (criteria.indexOf("includes")>0){
				search_fld_value=criteria.split("includes");
				qry_string=table_name+"."+search_fld_value[0];
				qry_string=checkDataType(qry_string,req);
				qry_string=qry_string+" LIKE '%"+search_fld_value[1].trim()+"%'"; 	
			}
			if (criteria.indexOf("between")>0){
				search_fld_value=criteria.split("between");
				search_between_value=search_fld_value[1].trim().split("and");
				//qry_string="STR_TO_DATE("+table_name+"."+search_fld_value[0]+", \"%d-%m-%Y\") between '"+search_between_value[0].trim()+"' and '"+ search_between_value[1].trim()+"'";
				qry_string=table_name+"."+search_fld_value[0];
				qry_string=checkDataType(qry_string,req);
				qry_string=qry_string+" between '"+search_between_value[0].trim()+"' and '"+ search_between_value[1].trim()+"'";
				
			}
			String qry_string_new="select distinct idf from " + table_name + " where "+ qry_string ;
			//System.out.println("After Function :"+qry_string_new);
			//qry_string="SELECT id, user_full_name , username FROM users WHERE id IN(" + qry_string + ")";
			//
			
/*			SELECT  `entity_id`,`document_id` FROM `entity_document_master`  WHERE `entity_type`='awards' 
				AND document_id IN
				(SELECT awards FROM `staff_profile_report_v0_values`  WHERE idf IN (SELECT idf FROM `staff_profile_awards_v0_values` WHERE `award_name` LIKE 'c%'))
*/			
			try  {
			Statement st = conn.createStatement();
			ResultSet rs_users=null;			
			rs_users = st.executeQuery(qry_string_new);
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
				getprofiledetails(conn,out,req,entity_ids,table_name,qry_string);
			}
		
		}catch (SQLException e) {
			e.printStackTrace();
		}
		}
		/*else{
			getprofiledetails(conn,out,req,entity_ids);	
		}*/
	}	

	private void getprofiledetails1(Connection conn, PrintWriter out,HttpServletRequest req,String entity_ids){			
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
			String institution_name="";
			int university_Id;
			String university_name="";
			rs_institutes=st_institutes.executeQuery("SELECT  university_master.id uniId,university_master.name uniname, institution_master.id instId,institution_master.name instname FROM institution_master INNER JOIN university_master ON institution_master.id=university_master.id  WHERE institution_master.active_yes_no=1 ORDER BY university_master.name,institution_master.name ");
			while(rs_institutes.next()){
				instId=rs_institutes.getInt("instId");
				institution_name=rs_institutes.getString("instname");
				university_Id=rs_institutes.getInt("uniId");
				university_name=rs_institutes.getString("uniname");
				/*out.println("<table BORDER=1 frame=\"above\" RULES=NONE width=\"80%\" align=\"center\">");
				out.println("<TR><TD>"+instName+"</td></tr>");
				out.println("</table>");*/
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
					//System.out.println("\n==="+tmpquery);
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
						 out.println("<TD>"+ university_name + "</TD>");
						 out.println("<TD>"+ institution_name + "</TD>");
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
	private void getprofiledetails(Connection conn, PrintWriter out,HttpServletRequest req,String entity_ids,String tabname,String searchcondition){			
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
			String details="";
			int instId;
			int user_id;
			String institution_name="";
			int university_Id;
			String university_name="";
			String conPath = req.getContextPath();
			//System.out.println(searchcondition);
			String university=req.getParameter("university");
			String institution=req.getParameter("institution");
			searchcondition=searchcondition +" and university_master.name like '" + university +"%'";
			searchcondition=searchcondition +" and institution_master.name like '" + institution + "%'";
			//System.out.println(searchcondition);
			/*rs_institutes=st_institutes.executeQuery("SELECT  university_master.id uniId,university_master.name uniname, institution_master.id instId,institution_master.name instname FROM institution_master INNER JOIN university_master ON institution_master.id=university_master.id  WHERE institution_master.active_yes_no=1 ORDER BY university_master.name,institution_master.name ");
			while(rs_institutes.next()){
				instId=rs_institutes.getInt("instId");
				institution_name=rs_institutes.getString("instname");
				university_Id=rs_institutes.getInt("uniId");
				university_name=rs_institutes.getString("uniname");
				out.println("<table BORDER=1 frame=\"above\" RULES=NONE width=\"80%\" align=\"center\">");
				out.println("<TR><TD>"+instName+"</td></tr>");
				out.println("</table>");*/				
				rs_profiledet = st_users.executeQuery("select * from  search_result_fields where result_fields like '%"+ tabname + "%' order by sequence");				
				
				
				while (rs_profiledet.next()){
					//if (rs_profiledet.getString("result_fields").indexOf(tabname)>0){ show tabs
					if (entity_ids!=""){
							tmpquery=rs_profiledet.getString("result_fields")+ " and idf IN ("+ entity_ids + ") AND " + searchcondition;// +" ORDER BY university_master.name,institution_master.name, user_full_name";				
					}	
						else{					
							tmpquery=rs_profiledet.getString("result_fields");//+ "  ORDER BY university_master.name,institution_master.name, user_full_name";							
						}					
					//out.println (tmpquery);
					rs_searchres=st_searchres.executeQuery(tmpquery);
					//out.println("<div id='"+ rs_profiledet.getString("description").replace(" ","_") +"' style=\"display:block\">");
					String tab_id=rs_profiledet.getString("description").replace(" ","_");
					out.println("<table border=\"1\" class=\"searchtab\" width=\"98%\" align=\"center\">");
					//out.println("<tr><td class=\"search_resul_category\" onclick=\"showHideSearchResults('"+tab_id+"');\">"+rs_profiledet.getString("description")+ " </td></tr>");
					out.println("<tr><td colspan=\"5\" class=\"search_resul_category\">"+rs_profiledet.getString("description")+ " </td></tr>");
					
					//out.println("<tr><td id='"+ tab_id +"' style=display:none>");					
					//System.out.println("<tr><td id='"+ tab_id +"' style=display:none>");
					//out.println("<TABLE  BORDER=\"0\" WIDTH=\"100%\">");
					//out.println("<TR ><TD>Name</TD>");
					//out.println("<TD>"+rs_profiledet.getString("description")+"</TD></TR>");
					
					
					 //Column Heading
					 out.println("<TR class=\"search_resul_head\">");
					 //out.println("<TD class=\"hidetd\">ID</TD>");
					 out.println("<TD>University</TD>");
					 out.println("<TD>Institution</TD>");
					 out.println("<TD>Faculty</TD>");
					 out.println("<TD>Details</TD>");
					 out.println("<TD></TD>");
					 out.println("</TR>");
					 
//					//Column Data
					while (rs_searchres.next()){	
						user_id=rs_searchres.getInt("idf");						
						instId=rs_searchres.getInt("instId");
						institution_name=rs_searchres.getString("instname");
						university_Id=rs_searchres.getInt("uniId");
						university_name=rs_searchres.getString("uniname");
						 ResultSetMetaData rsMetaData = rs_searchres.getMetaData();
						 int numberOfColumns = rsMetaData.getColumnCount();
						 out.println("<TR>");	
						 //out.println("<TD>"+ user_id + "</TD>");
						 out.println("<TD>"+ university_name + "</TD>");
						 out.println("<TD>"+ institution_name + "</TD>");
						 out.println("<TD>"+ rs_searchres.getString("user_full_name") + "</TD>");						 
						 out.println("<TD>");						 
						 for(int col=7;col<=numberOfColumns;col++){
							 details=rs_searchres.getString(rsMetaData.getColumnName(col));
							 details=details.replace("<b>", "");
							 details=details.replace("</b>", "");
							 out.println(details );
							 if (col!=numberOfColumns){out.println(",");}
						 }					 
						 out.println("</TD>");
						 out.println("<TD><a href="+conPath + "/jsp/staffdetails.jsp?userid="+user_id+" target=\"content\">Details</a></TD>");
						 out.println("</TR>");					
					}					
					out.println("</table>");
					//out.println("</td></tr></table>");
					out.println("<P><P>");
					rs_searchres.close();
				//}//end of show tabs
		} 
			//}	
		}catch (SQLException e) {
			e.printStackTrace();
		}			
		}	
	private String checkDataType(String qryString,HttpServletRequest req){
		String datetype_search_flds="";
		//System.out.println("1.============="+qryString);
		try{			
			  File file = new File(req.getRealPath("")+"/xml/basic_search_items.xml");		  
			  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			  DocumentBuilder db = dbf.newDocumentBuilder();
			  Document doc = db.parse(file);
			  doc.getDocumentElement().normalize();
			  //out.println("Root element " + doc.getDocumentElement().getNodeName());
			  NodeList nodeLst = doc.getElementsByTagName("date_fields");
			 // System.out.println("2.============="+nodeLst.getLength());
			  for (int s = 0; s < nodeLst.getLength(); s++) {				  
				  Node fstNode = nodeLst.item(s);	
				  if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
					  Element rootElmnt = (Element) fstNode;
					  NodeList fldCaptionElmntLst = rootElmnt.getElementsByTagName("field_name");
					  Element fld_caption_Elmnt = (Element) fldCaptionElmntLst.item(0);
				      NodeList fld_caption = fld_caption_Elmnt.getChildNodes();
				      datetype_search_flds=datetype_search_flds+" "+((Node) fld_caption.item(0)).getNodeValue();
				     // System.out.println("3.============="+datetype_search_flds);
				  }
			  }
			  datetype_search_flds=datetype_search_flds.replace(".","__");
			  qryString=qryString.replace(".","__");
			  //System.out.println("4.============="+datetype_search_flds+"==="+qryString);
			  
			  if(datetype_search_flds.indexOf(qryString)>0){				    
				  qryString="STR_TO_DATE("+qryString+",\"%d-%m-%Y\")";				  
				  	
			  }
		} catch (Exception e) {
		    e.printStackTrace();
		  }
		//System.out.println("============="+qryString);
		qryString=qryString.replace("__",".");
		return qryString;		
	}
}
