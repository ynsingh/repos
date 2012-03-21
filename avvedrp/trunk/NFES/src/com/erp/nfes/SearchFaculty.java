package com.erp.nfes;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.util.Locale;

public class SearchFaculty extends HttpServlet{

	Connection conn;
	String action="";
	String tab_name="";
	String university="";String institution="";String search_by="";String value="";String and="";String submit=""; String reset="";
	String date_format="";String faculty="";String details="";
	String department="";String search_faculty="";String search_dept="";String photo="";
	Statement theStatement=null;
	ResultSet theResult=null;
		
    public void service (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

	  HttpSession session = req.getSession(true);
	  String language=(String) req.getSession().getAttribute("language");
	  res.setContentType("text/html; charset=UTF-8");
	  Locale locale = new Locale(language, "");
	  PrintWriter out = res.getWriter();
	  action=req.getParameter("action");
	  MultiLanguageString ml=new MultiLanguageString();
	  ml.init(language);
	  String search_flds="";
	 	  
	  try {
		 
		  
		  university=ml.getValue("university");
		  institution=ml.getValue("institution");
	      search_by=ml.getValue("search_by");
	      value=ml.getValue("value");
	      and=ml.getValue("and");
	      submit=ml.getValue("submit");
	      reset=ml.getValue("reset");
	      date_format=ml.getValue("date_format");
	      faculty=ml.getValue("faculty");
	      details=ml.getValue("details");
	      department=ml.getValue("department");
	      search_faculty=ml.getValue("first_name");
	      search_dept=ml.getValue("department");
	      photo=ml.getValue("photo");

		  
		  File file = new File(getServletConfig().getServletContext().getRealPath("")+"/xml/basic_search_items.xml");		  
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
		out.println("<HTML><HEAD><TITLE>Search</TITLE>");
  	    out.println("<link href=\"./css/oiostyles.css\" rel=\"stylesheet\" type=\"text/css\"/>");
  	    out.println("<script type=\"text/javascript\" src=\"./js/search.js\" ></script>");   	     	    
  	    out.println("</HEAD><BODY class=\"bodystyle\">");
  	    out.println("<form method=\"post\" action=\"./SearchFaculty\"  NAME=\"searchForm\" ID=\"searchForm\" >");
  	    out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"action\" value=\""+action+"\" id=\"action\" />");
  	    
  	    
  	    //Search By
  	    out.println("<div class=\"listdiv\">");
  	  out.println("<div style='text-align: center; font-size:14px;font-weight: bold;height:8px;'>"+ ml.getValue("search") +"</div>");
  	    //out.println("<table align=\"center\" BORDER=1 FRAME=BOX RULES=NONE width=\"100%\">");
  	    out.println("<br><table class=\"search_field_div\" align=\"center\" width=\"98%\">");
  	    	    
  	    
  	    out.println("<tr>");
  	    out.println("<td>"+university+"</td>");
  	    GetRecordValue getUserDetails=new GetRecordValue();  	    
  	    if (getUserDetails.getRole(req.getUserPrincipal().getName()).equals("ROLE_ADMIN_UNIVERSITY")){
  	    	out.println("<td><input tye=\"text\" name=\"university\"  SIZE=\"35\" disabled value=\""+ getUserDetails.getUniversity(req.getUserPrincipal().getName()) +"\" ></td>");
  	    }else{
  	    	out.println("<td><input tye=\"text\" name=\"university\" SIZE=\"35\" ></td>");
  	    }
  	    out.println("<td>"+institution+"</td>");
  	    out.println("<td><input tye=\"text\" name=\"institution\" SIZE=\"35\" ></td>" );
  	    out.println("</tr>");
  	    
  	    out.println("<tr>");
  	    out.println("<td>"+search_dept+"</td>");
	    out.println("<td><input tye=\"text\" name=\"department\" SIZE=\"35\" ></td>");
  	    out.println("<td>"+ml.getValue("title")+"</td>");
	    out.println("<td>");
	    out.println("<select name=\"title\">");
	    out.println("<option value=\"\" SELECTED>-Select-</option>");
	    Statement st = conn.createStatement();
		ResultSet rs_title=null;			
		rs_title = st.executeQuery("SELECT fld_value FROM `general_master` WHERE category='Title' AND  active_yes_no=1 ORDER BY fld_value");		
		while(rs_title.next()){
			out.println("<option value='"+rs_title.getString("fld_value")+"'>"+rs_title.getString("fld_value")+"</option>");
		}
	    out.println("</select>");
	    out.println("</td></tr>");
	    
	    out.println("<tr>");
  	    out.println("<td>"+search_faculty+"</td>");
	    out.println("<td><input tye=\"text\" name=\"faculty_name\" SIZE=\"35\" ></td>");
  	    out.println("<td>"+ml.getValue("last_name")+"</td>");
	    out.println("<td><input tye=\"text\" name=\"lastname\" SIZE=\"35\" ></td>");	    
  	    out.println("</tr>");
  	    
  	    
  	    out.println("<tr align=\"left\">");
  	    out.println("<td>"+ ml.getValue("designation") +"</td>");
  	    out.println("<td><input tye=\"text\" name=\"designation\" SIZE=\"35\" ></td>");
		out.println("</tr>");
		
		out.println("<tr>");
  	    out.println("<td >"+search_by+"</td>");
  	    out.println("<td colspan=\"3\"><select name=\"searchfld\" id=\"searchfldid\">");  	    
  	    out.println(search_flds);	
		out.println("</select>"+date_format+"["+ml.getValue("date_format_applicable_status")+"]" );	
		
		out.println("</td></tr>");
			
		//Search Operators			
			out.println("<tr align=\"left\">");
			out.println("<td></td>");
			out.println("<td><select name=\"searchop\" id=\"searchopid\">");
			out.println("<option value=\"start_with\">Start with</option>");
			out.println("<option value=\"equals\">Equals</option>");			
			out.println("<option value=\"end_with\">End With</option>");
			out.println("<option value=\"includes\">Includes</option>");
			out.println("<option value=\"between\">between</option>");
			out.println("</select></td>");
			out.println("</tr>");	
	
		// Search Value	
			out.println("<tr align=\"left\">");
			out.println("<td>"+value+"</td>");
			out.println("<td><INPUT TYPE=\"TEXT\" SIZE=\"35\"  NAME=\"searchvalue\" ID=\"searchvalue\"/></td>");
			out.println("<td> "+ml.getValue("and")+" </td><td><INPUT TYPE=\"TEXT\"  NAME=\"searchvalue2\" ID=\"searchvalue2\"/></td>");
			out.println("</tr>");
			
		//Add Search Criteria
			out.println("<td style=display:none><input type=button value=\"Add To Search Condition\" onclick=\"addtoSearchCondition(document.searchForm.searchfld.value,document.searchForm.searchop.value,document.searchForm.searchvalue.value);\"/></td></tr>");
			out.println("<tr  style=display:none align=\"left\"><td>Search Conditions</td>");
			out.println("<td colspan=\"5\"><TEXTAREA  NAME=\"searchConditions\" ID=\"searchConditions\" COLS=\"90\" ROWS=\"2\"></TEXTAREA></td></tr>");
			
		//Add Search Criteria	
			out.println("<tr><td>&nbsp;</td>");
			out.println("<td><input type=button value=\""+submit+"\" onclick=\"search();\"/>");
			out.println("<input type=button value=\""+reset+"\" onClick=\"reset();\"/> ");
			out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"result_field\" id=\"result_field\" />");			
			out.println("</td></tr></table>");
			out.println("<p><p>");	
			
		// action checking
			if (action!=null){
				if(action.equals("search")){					
					String[] ls = {university,institution,faculty,details,department,photo};
					//System.out.println("action:"+action);
					search_faculties(conn,out,req,ls);
		 		}
			}	
		out.println("</div><p>");
		out.println("</form></BODY></HTML>");		
       
	     }catch(Exception e){
	    	 e.printStackTrace(); 
	     }finally{
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		    }	

	}//end of function service.
/*	public void destroy(){

	}
*/
	
	private void search_faculties(Connection conn, PrintWriter out,HttpServletRequest req,String[] ls) throws IOException{
		String table_name ="";
		String criteria="";
		String[] search_fld_value;
		String qry_string="";
		int rec_count=0;
		String entity_ids="";		
		String search_condition=req.getParameter("searchConditions");
		String[]  search_between_value;
		
		if(search_condition!=""){		
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
				qry_string=table_name+"."+search_fld_value[0];
				qry_string=checkDataType(qry_string,req);
				qry_string=qry_string+" between '"+search_between_value[0].trim()+"' and '"+ search_between_value[1].trim()+"'";
				
			}
			String qry_string_new="select distinct idf from " + table_name + " where "+ qry_string ;
			//System.out.println(qry_string_new);
			try  {
			Statement st = conn.createStatement();
			ResultSet rs_users=null;			
			rs_users = st.executeQuery(qry_string_new);		
			while(rs_users.next()){
				if (entity_ids!=""){
					entity_ids=entity_ids + ",";
				}
				entity_ids=entity_ids + rs_users.getInt("idf");			
			}
			if (entity_ids==""){
				out.println("<h1 align=\"center\">No Search Results</h1>");
			}
			else{
				getprofiledetails(conn,out,req,entity_ids,table_name,qry_string,ls);
			}
		
		}catch (SQLException e) {
			e.printStackTrace();
		}
		}
	}	
	
	private void getprofiledetails(Connection conn, PrintWriter out,HttpServletRequest req,String entity_ids,String tabname,String searchcondition,String[] ls) throws IOException{			
		try  {			
			Statement st_institutes=conn.createStatement();			
			Statement st_users = conn.createStatement();
			Statement st_searchres = conn.createStatement();
			Statement st_photo_path=conn.createStatement();
			ResultSet rs_profiledet=null;
			ResultSet rs_searchres=null;
			ResultSet rs_institutes=null;
			ResultSet rs_photo=null;
			String search_res_qry="";
			String tmpquery="";		
			String subHead="";
			String details="";
			String classname="";
			String rowclass="1";
			
			String file_entity="";
			String tabHead="";
			String query="";
			String displayed_fields="";
			
			int instId;
			int user_id;
			String institution_name="";
			int university_Id;
			String university_name="";
			
			String conPath = req.getContextPath();
			String university=req.getParameter("university");
			String institution=req.getParameter("institution");
			String faculty_name=req.getParameter("faculty_name");
			String department=req.getParameter("department");
			String designation=req.getParameter("designation");
			String lastname=req.getParameter("lastname");
			String title=req.getParameter("title");
			
			out.println("<script>");			
			out.println("document.searchForm.university.value='"+university+"';");
			out.println("document.searchForm.institution.value='"+institution+"';");			
			out.println("document.searchForm.faculty_name.value='"+faculty_name+"';");			
			out.println("document.searchForm.designation.value='"+designation+"';");
			out.println("document.searchForm.lastname.value='"+lastname+"';");
			out.println("document.searchForm.title.value='"+title+"';");			
			out.println("document.searchForm.department.value='"+department+"';");			
			out.println("document.searchForm.searchfld.value='"+req.getParameter("searchfld")+"';");
			out.println("document.searchForm.searchop.value='"+req.getParameter("searchop")+"';");
			out.println("document.searchForm.searchvalue.value='"+req.getParameter("searchvalue")+"';");
			out.println("document.searchForm.searchvalue2.value='"+req.getParameter("searchvalue2")+"';");
			out.println("</script>");
			
			GetRecordValue  getUserDetails= new GetRecordValue();		
			if (getUserDetails.getRole(req.getUserPrincipal().getName()).equals("ROLE_ADMIN_UNIVERSITY")){		  	    
				searchcondition=searchcondition +" and `staff_profile_masterdetails_v0_values`.university = '" + getUserDetails.getUniversity(req.getUserPrincipal().getName())+"'";
				out.println("<script>");			
				out.println("document.searchForm.university.value='"+getUserDetails.getUniversity(req.getUserPrincipal().getName())+"';");
				out.println("</script>");
			}else{	
				searchcondition=searchcondition +" and `staff_profile_masterdetails_v0_values`.university like '" + university +"%'";
			}	
			searchcondition=searchcondition +" and `staff_profile_masterdetails_v0_values`.institution like '" + institution + "%'";
			searchcondition=searchcondition +" and staff_profile_masterdetails_v0_values.user_full_name like '" + faculty_name + "%'";
			searchcondition=searchcondition +" and staff_profile_masterdetails_v0_values.department like '" + department + "%'";
			
			searchcondition=searchcondition +" and staff_profile_masterdetails_v0_values.designation like '" + designation + "%'";
			searchcondition=searchcondition +" and staff_profile_masterdetails_v0_values.user_title like '" + title + "%'";
			searchcondition=searchcondition +" and staff_profile_masterdetails_v0_values.last_name like '" + lastname + "%'";
			
			String entity=tabname.replace("staff_profile_","");
			entity=entity.replace("_v0_values","");
			if(entity.equals("masterdetails")==false){
				searchcondition=searchcondition +" and entity_document_master.approved_yesno=1";
			}
			
/*====xml file*/
			
			File file = new File(getServletContext().getRealPath("/")+"xml/basic_search_settings.xml");				
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder docbuilder;
			try {
				docbuilder = dbf.newDocumentBuilder();
				Document doc = docbuilder.parse(file);
				doc.getDocumentElement().normalize();
				NodeList nodeLst;	
				nodeLst = doc.getElementsByTagName(entity);
				
				for (int s = 0; s < nodeLst.getLength(); s++) {
				    Node fstNode = nodeLst.item(s);		    
				    if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
				    	  Element rootElmnt = (Element) fstNode;
				    	  NodeList fldNameElmntLstentity = rootElmnt.getElementsByTagName("entity");
					      Element fld_nameElmtentity = (Element) fldNameElmntLstentity.item(0);
					      NodeList fld_nameentity = fld_nameElmtentity.getChildNodes();
					      file_entity=((Node) fld_nameentity.item(0)).getNodeValue();
					      //out.println("entity "  + file_entity);		
					      
						  NodeList fldCaptionElmntLst = rootElmnt.getElementsByTagName("description");
						  Element fld_caption_Elmnt = (Element) fldCaptionElmntLst.item(0);
						  NodeList fld_caption = fld_caption_Elmnt.getChildNodes();
						  tabHead=((Node) fld_caption.item(0)).getNodeValue();
						  //out.println("Description: "  + description);
						
						  NodeList fldNameElmntLst = rootElmnt.getElementsByTagName("query");
						  Element fld_nameElmt = (Element) fldNameElmntLst.item(0);
						  NodeList fld_name = fld_nameElmt.getChildNodes();
						  query=((Node) fld_name.item(0)).getNodeValue();
						  //out.println("Query :" + query);						
						
						  NodeList fldNameElmntLstfields = rootElmnt.getElementsByTagName("displayed_fields");
						  Element fld_nameElmtfields = (Element) fldNameElmntLstfields.item(0);
						  NodeList fld_namefields = fld_nameElmtfields.getChildNodes();
						  displayed_fields=((Node) fld_namefields.item(0)).getNodeValue();						  
						  //out.println("fields "  + displayed_fields);	
						  String[] displayedfields = displayed_fields.split(",");						  
						  
						if (entity_ids!=""){
								tmpquery=query + " where userid IN ("+ entity_ids + ") AND " + searchcondition;				
						}else{					
								tmpquery=query + " where " + searchcondition;							
							}
																		
						rs_searchres=st_searchres.executeQuery(tmpquery);												
						//String tab_id=rs_profiledet.getString("description").replace(" ","_");
						out.println("<div class=\"search_resul_category\">"+tabHead+ " </div>");
						out.println("<table class=\"ListTable\" width=\"98%\" align=\"center\">");
						//Column Heading
						out.println("<TR>");
						out.println("<TD class=\"ListHeader\">"+ls[5]+"</TD>");
						out.println("<TD class=\"ListHeader\">"+ls[2]+"</TD>");
						out.println("<TD class=\"ListHeader\">"+ls[0]+"</TD>");
						out.println("<TD class=\"ListHeader\">"+ls[1]+"</TD>");
						out.println("<TD class=\"ListHeader\">"+ls[4] +"</TD>");					 
						out.println("<TD class=\"ListHeader\">"+ls[3]+"</TD>");						
						out.println("</TR>");

						while (rs_searchres.next()){											
							if (rowclass=="1"){
								rowclass="0";			
								classname="ListRow";
							}else{
							rowclass="1";
							classname="ListRownext";
							}							
							user_id=rs_searchres.getInt("idf");						
							instId=rs_searchres.getInt("institution_id");
							institution_name=rs_searchres.getString("institution");
							university_Id=rs_searchres.getInt("university_id");
							university_name=rs_searchres.getString("university");
							ResultSetMetaData rsMetaData = rs_searchres.getMetaData();
							int numberOfColumns = rsMetaData.getColumnCount();							
							/*============== Geting Photo Path ================*/
							String imageName="";
							rs_photo=st_photo_path.executeQuery("SELECT upload_photo FROM staff_profile_report_v0_values WHERE idf="+user_id);
							while(rs_photo.next()){	
								if (rs_photo.getString(1).equals("")==false)
								imageName=user_id+"/photo/"+rs_photo.getString("upload_photo");							
							}
							/*=================== end ===================*/							
							out.println("<TR>");
							if (imageName.equals("")){
								out.println("<td class=\""+classname+"\" width=\"100\" height=\"100\" align=\"center\">Photo Not Available</td>");	
							}else{
								//out.println("<td class=\""+classname+"\" width=\"100\" height=\"100\"><a href="+conPath + "/jsp/staffdetails.jsp?userid="+user_id+" target=\"content\"><img src= \"./GetImageServlet?filename="+imageName +"\" height=\"100%\" width=\"100%\"></a></td>");								
								out.println("<td class=\""+classname+"\" width=\"100\" height=\"100\"><a href="+conPath + "/webpage/"+user_id+" target=\"_blank\"><img src= \"./GetImageServlet?filename="+imageName +"\" height=\"100%\" width=\"100%\"></a></td>");
							}
							 
							 out.println("<TD class=\""+classname+"\"> <a href="+conPath + "/webpage/"+user_id+" target=\"_blank\">"+ rs_searchres.getString("full_name") + "</a></TD>");
							 out.println("<TD class=\""+classname+"\">"+ university_name + "</TD>");
							 out.println("<TD class=\""+classname+"\">"+ institution_name + "</TD>");
							 out.println("<TD class=\""+classname+"\">"+ rs_searchres.getString("Department") + "</TD>");						 						 
							 out.println("<TD class=\"" +classname +"\">");		
							for(int col=0;col<displayedfields.length;col++){
								if(rs_searchres.getString(displayedfields[col])!=null){	    			
									details=rs_searchres.getString(displayedfields[col]);
									 details=details.replace("<b>", "");
									 details=details.replace("</b>", "");
									 out.println(details );
									 if (col!=displayedfields.length){out.println(",");}												
			    				 }
							}
														 
							 out.println("</TD>");
							 out.println("</TR>");					
						}					
						out.println("</table>");
						out.println("<P><P>");
						rs_searchres.close();	
				    }//end if					
				}//end for
				
			}catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			}			
		}//end try
		catch (SQLException e) {
			e.printStackTrace();
		}			
		}	
	private String checkDataType(String qryString,HttpServletRequest req){
		String datetype_search_flds="";
		try{			
			  File file = new File(getServletConfig().getServletContext().getRealPath("")+"/xml/basic_search_items.xml");		  
			  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			  DocumentBuilder db = dbf.newDocumentBuilder();
			  Document doc = db.parse(file);
			  doc.getDocumentElement().normalize();
			  //out.println("Root element " + doc.getDocumentElement().getNodeName());
			  NodeList nodeLst = doc.getElementsByTagName("date_fields");
			  for (int s = 0; s < nodeLst.getLength(); s++) {				  
				  Node fstNode = nodeLst.item(s);	
				  if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
					  Element rootElmnt = (Element) fstNode;
					  NodeList fldCaptionElmntLst = rootElmnt.getElementsByTagName("field_name");
					  Element fld_caption_Elmnt = (Element) fldCaptionElmntLst.item(0);
				      NodeList fld_caption = fld_caption_Elmnt.getChildNodes();
				      datetype_search_flds=datetype_search_flds+" "+((Node) fld_caption.item(0)).getNodeValue();
				  }
			  }
			  datetype_search_flds=datetype_search_flds.replace(".","__");
			  qryString=qryString.replace(".","__");
			  if(datetype_search_flds.indexOf(qryString)>0){				    
				  qryString="STR_TO_DATE("+qryString+",\"%d-%m-%Y\")";				  
				  	
			  }
		} catch (Exception e) {
		    e.printStackTrace();
		  }
		qryString=qryString.replace("__",".");
		return qryString;		
	}
}
