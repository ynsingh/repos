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
import java.util.Locale;


public class search extends HttpServlet{

	Connection conn;
	String action="";
	String tab_name="";
	String university="";String institution="";String search_by="";String value="";String and="";String submit=""; String reset="";
	String date_format="";String faculty="";String details="";
	Statement theStatement=null;
	ResultSet theResult=null;
	
	/*private ServletConfig config;

	public void init(ServletConfig config)
		throws ServletException{
		this.config=config;

	}
*/
    public void service (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

	  HttpSession session = req.getSession(true);
	  String language=(String) req.getSession().getAttribute("language");
	  res.setContentType("text/html; charset=UTF-8");
	  Locale locale = new Locale(language, "");
	  PrintWriter out = res.getWriter();
	  action=req.getParameter("action");
	 
	  String search_flds="";
	 	  
	  try {
		  ConnectDB conObj=new ConnectDB();
		  conn = conObj.getMysqlConnection();
		  theStatement=conn.createStatement();
		  theResult=theStatement.executeQuery("select control_name,language_string from language_localisation where active_yes_no=1 and file_code=28 and language_code=\'"+language+"\'");
		  theResult.last();int len=theResult.getRow();String cn[]=new String[len];String ls[]=new String[len];
		     int i=0;theResult.beforeFirst();
		     while(theResult.next()){
		          cn[i]=theResult.getString("control_name");
		          ls[i]=theResult.getString("language_string");
		          i++;
		     }
		     
		     for(i=0;i<len;i++){
		     	if(cn[i].equals("university")){
		     		university=ls[i];
		     	}else if(cn[i].equals("institution")){
		     		institution=ls[i];
		     	}else if(cn[i].equals("search_by")){
		     		search_by=ls[i];
		     	}else if(cn[i].equals("value")){
		     		value=ls[i];
		     	}else if(cn[i].equals("and")){
		     		and=ls[i];
		     	}else if(cn[i].equals("submit")){
		     		submit=ls[i];
		     	}else if(cn[i].equals("reset")){
		     		reset=ls[i];
		     	}else if(cn[i].equals("date_format")){
		     		date_format=ls[i];
		     	}else if(cn[i].equals("faculty")){
		     		faculty=ls[i];
		     	}else if(cn[i].equals("details")){
		     		details=ls[i];
		     	}
		     }
		  theResult.close();theStatement.close();
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
        	
		CallableStatement cs;
		String optgroup="";
		cs = conn.prepareCall("{call search_column}");
		cs.executeQuery();
		ResultSet rs = cs.getResultSet();
		
	
		out.println("<HTML><HEAD><TITLE>Search</TITLE>");
  	    out.println("<link href=\"./css/oiostyles.css\" rel=\"stylesheet\" type=\"text/css\"/>");
  	    out.println("<script type=\"text/javascript\" src=\"./js/search.js\" ></script>");   	     	    
  	    out.println("</HEAD><BODY class=\"bodystyle\">");
  	    out.println("<form method=\"post\" action=\"./search\"  NAME=\"searchForm\" ID=\"searchForm\" >");
  	    out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"action\" value=\""+action+"\" id=\"action\" />");
  	    
  	    
  	    //Search By
  	    out.println("<div class=\"listdiv\">");
  	    //out.println("<table align=\"center\" BORDER=1 FRAME=BOX RULES=NONE width=\"100%\">");
  	    out.println("<br><table class=\"search_field_div\" align=\"center\" width=\"98%\">");
  	    	    
  	    
  	    out.println("<tr>");
  	    out.println("<td>"+university+"</td>");
  	    out.println("<td><input tye=\"text\" name=\"university\" ></td>");
  	    out.println("</tr>");
  	    
  	    out.println("<tr>");
  	    out.println("<td>"+institution+"</td>");
  	    out.println("<td><input tye=\"text\" name=\"institution\" ></td>" );
  	    out.println("</tr>");
  	    
  	    
  	    out.println("<tr align=\"left\"><td>"+search_by+"</td>");
  	    out.println("<td><select name=\"searchfld\" id=\"searchfldid\">");
		//out.println("<option value=staff_profile_personaldetails_v0_values.user_full_name>Faculty Name</option>");
  	    out.println(search_flds);	
		out.println("</select></td>");
		out.println("</tr>");
		
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
			
//			Result Fields		 : Not Needed	
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
			out.println("<tr align=\"left\">");
			out.println("<td>"+value+"</td>");
			out.println("<td><INPUT TYPE=\"TEXT\" NAME=\"searchvalue\" ID=\"searchvalue\"/>");
			out.println(" &nbsp;&nbsp;"+and+"&nbsp; &nbsp;<INPUT TYPE=\"TEXT\" NAME=\"searchvalue2\" ID=\"searchvalue2\"/>"+date_format+"</td>");
			out.println("</tr>");
			
		//Add Search Criteria	
			//out.println("<tr align=\"center\">");
			//out.println("<td colspan=\"2\"><input type=button value=\"Add To Search Condition\" onclick=\"addtoSearchCondition(document.searchForm.searchfld.value,document.searchForm.searchop.value,document.searchForm.searchvalue.value);\"/></td></tr>");
			out.println("<td style=display:none><input type=button value=\"Add To Search Condition\" onclick=\"addtoSearchCondition(document.searchForm.searchfld.value,document.searchForm.searchop.value,document.searchForm.searchvalue.value);\"/></td></tr>");
			out.println("<tr  style=display:none align=\"left\"><td>Search Conditions</td>");
			out.println("<td colspan=\"5\"><TEXTAREA  NAME=\"searchConditions\" ID=\"searchConditions\" COLS=\"90\" ROWS=\"2\"></TEXTAREA></td></tr>");
			
		//Add Search Criteria	
			out.println("<tr><td>&nbsp;</td>");
			out.println("<td><input type=button value=\""+submit+"\" onclick=\"search();\"/>");
			out.println("<input type=button value=\""+reset+"\" onClick=\"reset();\"/> ");
			out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"result_field\" id=\"result_field\" />");
			//out.println("<input type=button value=\"Result Fields\" onclick=\"getResultFields(document.searchForm.listfld);\"/></td></tr>");			
			out.println("</td></tr></table>");
			out.println("<p><p>");	
			
		// action checking
			if (action!=null){
				if(action.equals("search")){
					String[] ls = {university,institution,faculty,details};
                            
					search(conn,out,req,ls);
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
	
	private void search(Connection conn, PrintWriter out,HttpServletRequest req,String[] ls){
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
			//System.out.println("<script>showSearchCondition('"+search_condition+"');</script>");
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
			//System.out.println(qry_string_new);
			try  {
			Statement st = conn.createStatement();
			ResultSet rs_users=null;			
			rs_users = st.executeQuery(qry_string_new);		
			while(rs_users.next()){
				if (entity_ids!=""){
					entity_ids=entity_ids + ",";
				}
				deleteUnusedRecords(rs_users.getInt("idf")); // Add through child form, but not saved , then document id not in staff profile record fields 
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
	private void getprofiledetails(Connection conn, PrintWriter out,HttpServletRequest req,String entity_ids,String tabname,String searchcondition,String[] ls){			
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
			String classname="";
			String rowclass="1";
			int instId;
			int user_id;
			String institution_name="";
			int university_Id;
			String university_name="";
			
			String conPath = req.getContextPath();
			String university=req.getParameter("university");
			String institution=req.getParameter("institution");
			
			out.println("<script>");			
			out.println("document.searchForm.university.value='"+university+"';");
			out.println("document.searchForm.institution.value='"+institution+"';");
			out.println("document.searchForm.searchfld.value='"+req.getParameter("searchfld")+"';");
			out.println("document.searchForm.searchop.value='"+req.getParameter("searchop")+"';");
			out.println("document.searchForm.searchvalue.value='"+req.getParameter("searchvalue")+"';");
			out.println("document.searchForm.searchvalue2.value='"+req.getParameter("searchvalue2")+"';");
			out.println("</script>");
					
			
			searchcondition=searchcondition +" and `staff_profile_masterdetails_v0_values`.university like '" + university +"%'";
			searchcondition=searchcondition +" and `staff_profile_masterdetails_v0_values`.institution like '" + institution + "%'";
			
			//rs_profiledet = st_users.executeQuery("select * from  search_result_fields where result_fields like '%"+ tabname + "%' order by sequence");
			String entity=tabname.replace("staff_profile_","");
			entity=entity.replace("_v0_values","");
			if(entity.equals("masterdetails")==false){
				searchcondition=searchcondition +" and entity_document_master.approved_yesno=1";
			}
			rs_profiledet = st_users.executeQuery("select * from  search_result_fields where entity='"+ entity + "'  order by sequence");
			//out.println("select * from  search_result_fields where result_fields like '%"+ tabname + "%' order by sequence");
			//System.out.println("select * from  search_result_fields where entity='"+ entity + "' order by sequence");
				
				
				while (rs_profiledet.next()){
					if (entity_ids!=""){
							tmpquery=rs_profiledet.getString("result_fields")+ " and userid IN ("+ entity_ids + ") AND " + searchcondition;				
					}else{					
							tmpquery=rs_profiledet.getString("result_fields");							
						}
					rs_searchres=st_searchres.executeQuery(tmpquery);
					
					//out.println(tmpquery);
					//System.out.println(tmpquery);
					
					String tab_id=rs_profiledet.getString("description").replace(" ","_");
					out.println("<div class=\"search_resul_category\">"+rs_profiledet.getString("description")+ " </div>");
					out.println("<table class=\"ListTable\" width=\"98%\" align=\"center\">");
					//out.println("<tr><td colspan=\"5\" class=\"search_resul_category\">"+rs_profiledet.getString("description")+ " </td></tr>");
				
					 //Column Heading
					 out.println("<TR>");
					 out.println("<TD class=\"ListHeader\">"+ls[0]+"</TD>");
					 out.println("<TD class=\"ListHeader\">"+ls[1]+"</TD>");
					 out.println("<TD class=\"ListHeader\">Department</TD>");
					 out.println("<TD class=\"ListHeader\">"+ls[2]+"</TD>");
					 out.println("<TD class=\"ListHeader\">"+ls[3]+"</TD>");
					 out.println("<TD class=\"ListHeader\">"+ls[3]+"</TD>");
					 //out.println("<TD class=\"ListHeader\"></TD>");
					 out.println("</TR>");
					 
//					//Column Data
					while (rs_searchres.next()){											
						if (rowclass=="1"){
							rowclass="0";			
							classname="ListRow";
						}
						else	{
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
						 out.println("<TR>");	
						 //out.println("<TD>"+ user_id + "</TD>");
						 out.println("<TD class=\""+classname+"\">"+ university_name + "</TD>");
						 out.println("<TD class=\""+classname+"\">"+ institution_name + "</TD>");
						 out.println("<TD class=\""+classname+"\">"+ rs_searchres.getString("Department") + "</TD>");
						 out.println("<TD class=\""+classname+"\">"+ rs_searchres.getString("user_full_name") + "</TD>");						 
						 out.println("<TD class=\"" +classname +"\">");						 
						 for(int col=12;col<=numberOfColumns;col++){
							 details=rs_searchres.getString(rsMetaData.getColumnName(col));
							 details=details.replace("<b>", "");
							 details=details.replace("</b>", "");
							 out.println(details );
							 if (col!=numberOfColumns){out.println(",");}
						 }					 
						 out.println("</TD>");
						 out.println("<TD class=\""+classname+"\"><a href="+conPath + "/jsp/staffdetails.jsp?userid="+user_id+" target=\"content\">"+ls[3]+"</a></TD>");
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
