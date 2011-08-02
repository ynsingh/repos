package com.erp.nfes;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.sql.*;
import java.util.Locale;



public class master_tables extends HttpServlet{

    Connection conn;
	String action="";
	String tab_name="";
	private ServletConfig config;

	public void init(ServletConfig config)
		throws ServletException{
		this.config=config;

	}

    public void service (HttpServletRequest req, HttpServletResponse res)
      throws ServletException, IOException {

	  HttpSession session = req.getSession(true);
	  String language=(String) req.getSession().getAttribute("language");
	  res.setContentType("text/html; charset=UTF-8");
	  Locale locale = new Locale(language, "");
	  PrintWriter out = res.getWriter();
	  action=req.getParameter("action");
	  tab_name=req.getParameter("tab_name");
	  	  
	try{
        ConnectDB conObj=new ConnectDB();
		conn = conObj.getMysqlConnection();
		mlObj ml=new mlObj();
		ml.init("master_tables.java", language);
		out.println("<HTML><HEAD><TITLE>Master Tables</TITLE>");
  	    out.println("<link href=\"./css/oiostyles.css\" rel=\"stylesheet\" type=\"text/css\"/>");
  	    out.println("<script type=\"text/javascript\" src=\"./js/master_tables.js\" ></script>");
  	    String errmsg=req.getParameter("errmsg");
  	    if(errmsg!=null){
  	    	out.println("<script>alert(\'"+ req.getParameter("errmsg").replace("_"," ") +"\');</script>");	
  	    }
  	    out.println("</HEAD><BODY CLASS=\"bodystyle\">");
  	    out.println("<form method=\"post\" action=\"./master_tables\" NAME=\"myForm\" ID=\"mt01\" >");
  	    out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"action\" value=\""+action+"\" id=\"action\" />");
  	    out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"tab_name\" value=\""+tab_name+"\" id=\"tab_name\" />");
  	    out.println("<div class=\"listdiv_toggle\"><BR>");
  	    if(tab_name.equals("general_master")){
 		   if(action.equals("show_general_master")){	
 			  show_general_master(conn,out,req,ml);
 		   }else if(action.equals("edit_general_master")){
 			  edit_general_master(out,req,ml);
 		   }else if(action.equals("save_edit_general_master")){
 			  save_edit_general_master(conn,req,res);
 		   }else if(action.equals("add_new_general_master")){
 			  add_new_general_master(out,req,ml);
 		   }else if(action.equals("save_add_new_general_master")){
 			  save_add_new_general_master(conn,req,res,out);
 		   }
 	    }else if(tab_name.equals("principal_investigator_master")){
 	       if(action.equals("show_principal_investigator_master")){	
 	 			  show_principal_investigator_master(conn,out,req,ml);
 	 	   }else if(action.equals("edit_principal_investigator_master")){	
 	 			  edit_principal_investigator_master(conn,out,req,ml);
 	 	   }else if(action.equals("save_edit_principal_investigator_master")){	
 	 			  save_edit_principal_investigator_master(conn,req,res);
 	 	   }else if(action.equals("add_new_principal_investigator_master")){	
 	 			  add_new_principal_investigator_master(conn,out,req,ml);
 	 	   }else if(action.equals("save_add_new_principal_investigator_master")){	
 	 			  save_add_new_principal_investigator_master(conn,req,res);
 	 	   }
 	    }else if(tab_name.equals("courses_taught_master")){
 	       if(action.equals("show_courses_taught_master")){	
	 			  show_courses_taught_master(conn,out,req,ml);
	 	   }else if(action.equals("edit_courses_taught_master")){	
	 			  edit_courses_taught_master(conn,out,req,ml);
	 	   }else if(action.equals("save_edit_courses_taught_master")){	
	 		  save_edit_courses_taught_master(conn,req,res);
	 	   }else if(action.equals("add_new_courses_taught_master")){	
	 		  add_new_courses_taught_master(conn,out,req,ml);
	 	   }else if(action.equals("save_add_new_courses_taught_master")){	
	 		  save_add_new_courses_taught_master(conn,req,res);
	 	   }
 	    }else if(tab_name.equals("university_master")){
 	       if(action.equals("show_university_master")){	
	 		  show_university_master(conn,out,ml);
	 	   }else if(action.equals("edit_university_master")){	
	 		  edit_university_master(out,req,ml);
	 	   }else if(action.equals("save_edit_university_master")){	
	 		  save_edit_university_master(conn,req,res);
	 	   }else if(action.equals("add_new_university_master")){	
	 		  add_new_university_master(out,req,ml);
	 	   }else if(action.equals("save_add_new_university_master")){	
	 		  save_add_new_university_master(conn,req,res);
	 	   }
 	    }else if(tab_name.equals("institution_master")){
  	       if(action.equals("show_institution_master")){	
 	 	   	  show_institution_master(conn,out,ml);
 	 	   }else if(action.equals("edit_institution_master")){	
 	 		  edit_institution_master(conn,out,req,ml);
 	 	   }else if(action.equals("save_edit_institution_master")){	
 	 		  save_edit_institution_master(conn,req,res);
 	 	   }else if(action.equals("add_new_institution_master")){	
 	 		  add_new_institution_master(conn,out,req,ml);
 	 	   }else if(action.equals("save_add_new_institution_master")){	
 	 		  save_add_new_institution_master(conn,req,res);
 	 	   }
  	    }else if(tab_name.equals("institution_department_master")){
   	       if(action.equals("show_institution_department_master")){	
  	 	   	  show_institution_department_master(conn,req,out,ml);
  	 	   }else if(action.equals("edit_institution_department_master")){	
  	 		  edit_institution_department_master(conn,req,out,ml);
  	 	   }else if(action.equals("save_edit_institution_department_master")){	
  	 		  save_edit_institution_department_master(conn,req,res);
  	 	   }else if(action.equals("add_new_institution_department_master")){	
  	 		  add_new_institution_department_master(conn,req,out,ml);
  	 	   }else if(action.equals("save_add_new_institution_department_master")){	
  	 		  save_add_new_institution_department_master(conn,req,res);
  	 	   }
   	    }
  	    out.println("<BR><div>");
        out.println("</form></BODY></HTML>");  
        conn.close(); //Close database Connection

	     }catch(Exception e){

	    	 e.printStackTrace();//Print trapped error.
	     }

	}//end of function service.
	public void destroy(){

	}

	private void show_general_master(Connection conn, PrintWriter out,HttpServletRequest req,mlObj ml){
		try{
			    String category=req.getParameter("category");
		        Statement theStatement=conn.createStatement();
		        ResultSet theResult=theStatement.executeQuery("select distinct category from general_master order by category");
		        theResult.last();String s[]=new String[theResult.getRow()];int i=0;int id=0;
		        out.println(ml.getValue("category")+"<label  class=\"mandatory\">*</label>&nbsp;&nbsp;<select name=\"category_combo\" id=\"cb01\" onchange=\"general_master_combo_change(this.value);\" >");
                theResult.beforeFirst();
		        while(theResult.next()){
		        	s[i]=theResult.getString("category");
		        	if(!category.equals("") && category.equals(s[i])){
		        	     out.println("<option value=\""+s[i]+"\" selected=\"selected\" >"+s[i]+"</option>");
		        	}else{
		        		 out.println("<option value=\""+s[i]+"\" >"+s[i]+"</option>");
		        	}
		        	i++;
		        }
		        if(category.equals("")){category=s[0];}
		        out.println("</select>&nbsp;<input type=\"button\" name\"add_new\" value=\""+ml.getValue("add_new")+"\" onclick=\"general_master_button_change();\" /><br><br>");
		        theResult=theStatement.executeQuery("select fld_value,active_yes_no,id from general_master where category=\'"+category+"\'");
		        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"id\" value=\""+id+"\" id=\"id\" />");
		        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"category\" value=\""+category+"\" id=\"category\" />");
		        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"fld_value\" value=\"\" id=\"fld_value\" />");
		        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"active_yes_no\" value=\"\" id=\"active_yes_no\" />");
		        out.println("<TABLE border=\"1\" align=center class=\"ListTable\" cellspacing=0 width=\"100%\">");
		        out.println("<TR><TD align=center colspan=\"3\" class=\"ListHeader\" >"+ml.getValue("category")+category+"</TD></TR>");
		        out.println("<TR>");
		        out.println("<TD align=center class=\"ListHeader\" >"+ml.getValue("value")+"</TD>");
		        out.println("<TD align=center class=\"ListHeader\">"+ml.getValue("active")+"</TD>");
		        out.println("<TD align=center class=\"ListHeader\">"+ml.getValue("edit")+"</TD>");
		        out.println("</TR>");
		        String at="";String fv="";String str="";int x=0;
		  	   	while(theResult.next()) //Fetch all the records and print in table
				{
				    out.println();
		    		out.println("<TR>");
		    		fv=theResult.getString("fld_value");
		    		id=theResult.getInt("id");
		    		out.println("<TD class=\"ListRow\" >" +fv+ "</TD>");
		    		at=theResult.getString("active_yes_no");
		    		if(at.equals("1")){str="<span style=\"color:green\">"+ml.getValue("yes")+"</span>";at="Yes";}else{str="<span style=\"color:red\">"+ml.getValue("no")+"</span>";at="No";}
					out.println("<TD align=center class=\"ListRow\" >" +str+ "</TD>");
					out.println("<TD align=center class=\"ListRow\" >" +
							"<INPUT TYPE=\"HIDDEN\" NAME=\"id_"+x+"\" value=\""+id+"\" id=\"id_"+x+"\" />"+
							"<INPUT TYPE=\"HIDDEN\" id=\"fld_value_"+x+"\" value=\""+fv+"\" />"+
							"<INPUT TYPE=\"HIDDEN\" id=\"active_yes_no_"+x+"\" value=\""+at+"\" />"+
							"<input type=\"button\" name=\"button_"+x+"\" value=\""+ml.getValue("edit")+"\" onclick=\"edit_general_master(this.name);\" />" +
									"</TD>");
					out.println("</TR>");x=x+1;

				}
		  	    out.println("</TABLE>");
		  	    theResult.close();//Close the result set
		        theStatement.close();//Close statement
		  	    
		     }catch(Exception e){
			  e.printStackTrace();//Print trapped error.
		     }
		
	}//end of function show_general_master.
	private void edit_general_master(PrintWriter out,HttpServletRequest req,mlObj ml){
		try{
			String id=req.getParameter("id");
		    String category=req.getParameter("category");
		    String fld_value=req.getParameter("fld_value");
		    String active_yes_no=req.getParameter("active_yes_no");
		    out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"id\" value=\""+id+"\" id=\"id\" />");
	        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"category\" value=\""+category+"\" id=\"category\" />");
	        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"fld_value\" value=\""+fld_value+"\" id=\"fld_value\" />");
	        out.println("<TABLE border=\"1\" align=center class=\"ListTable\" cellspacing=0 width=\"100%\">");
	        out.println("<TR><TD align=center colspan=\"2\" class=\"thead\" >"+ml.getValue("edit")+"</TD></TR>");
	        out.println("<TR><TD align=center colspan=\"2\" class=\"thead\" >"+ml.getValue("category")+category+"</TD></TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"ListRow\" width=\"30%\" >"+ml.getValue("value")+"<label  class=\"mandatory\">*</label></TD>");
	        out.println("<TD class=\"ListRow\" width=\"70%\" ><input type=\"text\" size=\"50\" name=\"new_fld_value\" value=\""+fld_value+"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"ListRow\" width=\"30%\" >"+ml.getValue("active")+"<label  class=\"mandatory\">*</label></TD>");
	        out.println("<TD class=\"ListRow\" width=\"70%\" >");
	        if(active_yes_no.equals("Yes")){
	        	out.println("<select name=\"active_yes_no\" onchange=\"enable_save_button();\" ><option value=\"1\" >"+ml.getValue("yes")+"</option><option value=\"0\" >"+ml.getValue("no")+"</option></select>");
	        }else if(active_yes_no.equals("No")){
	        	out.println("<select name=\"active_yes_no\" onchange=\"enable_save_button();\" ><option value=\"0\" >"+ml.getValue("no")+"</option><option value=\"1\" >"+ml.getValue("yes")+"</option></select>");
	        }
	        out.println("</TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"ListRow\" colspan=\"2\" align=\"center\" >" +
	        		"<input type=\"button\" value=\""+ml.getValue("save")+"\" name=\"save\" disabled=true  onclick=\"save_edit_general_master();\" >" +
	        		"&nbsp;<input type=\"button\" value=\""+ml.getValue("back")+"\" name=\"back\" onclick=\"back_general_master();\" >" +
	        		"</TD>");
	        out.println("</TR>");
	        out.println("</TABLE>");
	  	   	    
	     }catch(Exception e){
		  e.printStackTrace();//Print trapped error.
	     }
	}//end of function edit_general_master.
	private void save_edit_general_master(Connection conn,HttpServletRequest req,HttpServletResponse res) throws IOException{
		String category="";
		try{
			String id=req.getParameter("id");
		    category=req.getParameter("category");
		    String fld_value=req.getParameter("fld_value");
		    String new_fld_value=req.getParameter("new_fld_value");
		    String active_yes_no=req.getParameter("active_yes_no");
		    if(active_yes_no.equals("0"))
		    {
		    int status = table_relation(conn,req,id,category);
	        if(status == 1)
	        {
	        	res.sendRedirect("./master_tables?category="+category+"&action=show_general_master&tab_name=general_master&errmsg=This_record_is_related_with_some_another_records.");
	        	return;
	        }
		    }
			Statement theStatement=conn.createStatement();
	        theStatement.executeUpdate("update general_master set fld_value=\'"+new_fld_value+"\',active_yes_no=\'"+active_yes_no+"\' where category=\'"+category+"\' and fld_value=\'"+fld_value+"\'");
	      	theStatement.close();//Close statement
	  	    res.sendRedirect("./master_tables?category="+category+"&action=show_general_master&tab_name=general_master");
	        }catch(java.sql.SQLIntegrityConstraintViolationException e) {  
             System.out.println ("Caught java.sql.SQLIntegrityConstraintViolationException") ;
             res.sendRedirect("./master_tables?category="+category+"&action=show_general_master&tab_name=general_master&errmsg=Same_Record_Already_Exists.");
	      }      
	      catch (java.sql.SQLException e) {  
	             System.out.println   
	            ("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " +   
	                                      e.getMessage() ) ; 
	      } 
	      catch(Exception e){	    	
		    	res.sendRedirect("./master_tables?category="+category+"&action=show_general_master&tab_name=general_master&errmsg=Eorr_in_Save"); 
			    e.printStackTrace();			    
		     }
	}//end of function save_edit_general_master.
	private void add_new_general_master(PrintWriter out,HttpServletRequest req,mlObj ml){
		try{
		    String category=req.getParameter("category");
		    out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"category\" value=\""+category+"\" id=\"category\" />");
	        out.println("<TABLE border=\"1\" align=center class=\"ListTable\" cellspacing=0 width=\"100%\">");
	        out.println("<TR><TD align=center colspan=\"2\" class=\"thead\" >"+ml.getValue("add_new")+"</TD></TR>");
	        out.println("<TR><TD align=center colspan=\"2\" class=\"thead\" >"+ml.getValue("category")+category+"</TD></TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"ListRow\" width=\"30%\" >"+ml.getValue("value")+"<label  class=\"mandatory\">*</label></TD>");
	        out.println("<TD class=\"ListRow\" width=\"70%\" ><input name=\"fld_value\" type=\"text\" size=\"50\" value=\"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\"  /></TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"ListRow\" width=\"30%\" >"+ml.getValue("active")+"<label  class=\"mandatory\">*</label></TD>");
	        out.println("<TD class=\"ListRow\" width=\"70%\" >");
	        out.println("<select name=\"active_yes_no\" onchange=\"enable_save_button();\" ><option value=\"1\" >"+ml.getValue("yes")+"</option><option value=\"0\" >"+ml.getValue("no")+"</option></select>");
	        out.println("</TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"ListRow\" colspan=\"2\" align=\"center\" >" +
	        		"<input type=\"button\" value=\""+ml.getValue("save")+"\" name=\"save\" disabled=true onclick=\"save_add_new_general_master();\" >" +
	        		"&nbsp;<input type=\"button\" value=\""+ml.getValue("back")+"\" name=\"back\" onclick=\"back_general_master();\" >" +
	        		"</TD>");
	        out.println("</TR>");
	        out.println("</TABLE>");
	  	   	    
	     }catch(Exception e){
		   out.println(e.getMessage());//Print trapped error.
	     }
	}//end of function add_new_general_master.
	private void save_add_new_general_master(Connection conn,HttpServletRequest req,HttpServletResponse res,PrintWriter out) throws IOException{
			String category="";
		try{
		    category=req.getParameter("category");
		    String fld_value=req.getParameter("fld_value");
		    String active_yes_no=req.getParameter("active_yes_no");
	        Statement theStatement=conn.createStatement();
	        theStatement.executeUpdate("insert into general_master(category,fld_value,active_yes_no) values('"+category+"','"+fld_value+"','"+active_yes_no+"')");
	      	theStatement.close();//Close statement
	  	    res.sendRedirect("./master_tables?category="+category+"&action=show_general_master&tab_name=general_master");
	     }catch(java.sql.SQLIntegrityConstraintViolationException e) {  
             System.out.println ("Caught java.sql.SQLIntegrityConstraintViolationException") ;
             res.sendRedirect("./master_tables?category="+category+"&action=add_new_general_master&tab_name=general_master&errmsg=Same_Record_Already_Exists.");
	      }      
	      catch (java.sql.SQLException e) {  
	             System.out.println   
	            ("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " +   
	                                      e.getMessage() ) ; 
	      } 
	      catch(Exception e){	    	
		    	res.sendRedirect("./master_tables?category="+category+"&action=add_new_general_master&tab_name=general_master&errmsg=Eorr_in_Save"); 
			    e.printStackTrace();			    
		     }
	}//end of function save_add_new_general_master.
	
	private int table_relation(Connection conn,HttpServletRequest req,String id,String category) throws IOException{
		int flag=0;
		try{
		  File file = new File(req.getRealPath("")+"/xml/table_relation.xml");		  
		  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		  DocumentBuilder db = dbf.newDocumentBuilder();
		  Document doc = db.parse(file);
		  doc.getDocumentElement().normalize();
		  //out.println("Root element " + doc.getDocumentElement().getNodeName());
		  NodeList nodeLst = doc.getElementsByTagName("relation");
		  //out.println("Information of all employees");
		  for (int s = 0; s < nodeLst.getLength(); s++) {
		    Node fstNode = nodeLst.item(s);		    
		    if (fstNode.getNodeType() == Node.ELEMENT_NODE) {		  
		      Element rootElmnt = (Element) fstNode;		      
		      NodeList categoryElmntLst = rootElmnt.getElementsByTagName("category");
		      Element category_Elmnt = (Element) categoryElmntLst.item(0);
		      NodeList category_value = category_Elmnt.getChildNodes();
		      //out.println("First Name : "  + ((Node) fld_caption.item(0)).getNodeValue());
		      if (category_value.item(0).getNodeValue().equals(category)){
		      NodeList fldNameElmntLst = rootElmnt.getElementsByTagName("related_field");
		      for(int i=0;i<fldNameElmntLst.getLength();i++)
		      {
		      Element fld_nameElmt = (Element) fldNameElmntLst.item(i);
		      NodeList fld_name = fld_nameElmt.getChildNodes();
		      String[] tmparr=fld_name.item(0).getNodeValue().split("\\.");
		      String table_name = tmparr[0];
		      String field_name = tmparr[1];
		      Statement theStatement=conn.createStatement();
		      ResultSet theResult=theStatement.executeQuery("select * from  "+table_name+" where "+field_name+"="+id+"");
		      if(theResult.next())
		      {
		      flag=1;
		      break;
		      }
		      }
		      }
		      }	
		  }
		  
	}	    
		catch(Exception e){
		   System.out.println(e.getMessage());//Print trapped error.
    }
	return flag;
	}//
	
	
	private void show_principal_investigator_master(Connection conn, PrintWriter out,HttpServletRequest req,mlObj ml){
		try{
			    out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"pi_name\" value=\"\" />");
		        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"department\" value=\"\" />");
		        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"designation\" value=\"\" />");
		        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"email\" value=\"\" />");
		        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"address\" value=\"\" />");
		        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"active\" value=\"\" />");
		        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"id\" value=\"\" />");
		        out.println("<INPUT TYPE=\"button\" NAME=\"add\" value=\""+ml.getValue("add_new")+"\" onclick=\"add_new_principal_investigator_master();\" /><br><br>");
			    out.println("<TABLE border=\"1\" align=center class=\"ListTable\" cellspacing=0 width=\"100%\">");
		        out.println("<TR><TD align=center colspan=\"7\" class=\"ListHeader\" >"+ml.getValue("principal_investigator_master")+"</TD></TR>");
		        out.println("<TR>");
		        out.println("<TD align=center class=\"ListHeader\" >"+ml.getValue("name")+"</TD>");
		        out.println("<TD align=center class=\"ListHeader\">"+ml.getValue("department")+"</TD>");
		        out.println("<TD align=center class=\"ListHeader\" >"+ml.getValue("designation")+"</TD>");
		        out.println("<TD align=center class=\"ListHeader\">"+ml.getValue("email")+"</TD>");
		        out.println("<TD align=center class=\"ListHeader\" >"+ml.getValue("address")+"</TD>");
		        out.println("<TD align=center class=\"ListHeader\">"+ml.getValue("active")+"</TD>");
		        out.println("<TD align=center class=\"ListHeader\">"+ml.getValue("edit")+"</TD>");
		        out.println("</TR>");
		        Statement theStatement=conn.createStatement();
		        Statement st=conn.createStatement();
		        ResultSet theResult=theStatement.executeQuery("select * from investigator_master");
		        ResultSet rs=null;
		        String str="";int department_id;int x=0;String s[]=new String[7];
		        while(theResult.next()){
		        	s[0]=theResult.getString("name");
		        	department_id=theResult.getInt("department_id");
				    rs=st.executeQuery("select fld_value from general_master where category=\'Department\' and id="+department_id);
				    rs.first();
		        	s[1]=rs.getString("fld_value");
		        	s[2]=theResult.getString("designation");
		        	s[3]=theResult.getString("email");
		        	s[4]=theResult.getString("address");
		        	s[5]=theResult.getString("active_yes_no");
		        	s[6]=Integer.toString(theResult.getInt("id"));
			        if(s[5].equals("1")){str="<span style=\"color:green\">"+ml.getValue("yes")+"</span>";s[5]="Yes";}else{str="<span style=\"color:red\">"+ml.getValue("no")+"</span>";s[5]="No";}
		        	out.println("<TR>");
			        out.println("<TD  class=\"ListRow\" >"+s[0]+"</TD>");
			        out.println("<TD align=center class=\"ListRow\" >"+s[1]+"</TD>");
			        out.println("<TD align=center class=\"ListRow\" >"+s[2]+"</TD>");
			        out.println("<TD align=center class=\"ListRow\" >"+s[3]+"</TD>");
			        out.println("<TD  class=\"ListRow\" >"+s[4]+"</TD>");
			        out.println("<TD align=center class=\"ListRow\" >"+str+"</TD>");
			        out.println("<TD align=center class=\"ListRow\" >" +
			        		"<INPUT TYPE=\"HIDDEN\" id=\"pi_name_"+x+"\" value=\""+s[0]+"\" />" +
			                "<INPUT TYPE=\"HIDDEN\" id=\"department_"+x+"\" value=\""+s[1]+"\" />" +
			                "<INPUT TYPE=\"HIDDEN\" id=\"designation_"+x+"\" value=\""+s[2]+"\" />" +
			                "<INPUT TYPE=\"HIDDEN\" id=\"email_"+x+"\" value=\""+s[3]+"\" />" +
			                "<INPUT TYPE=\"HIDDEN\" id=\"address_"+x+"\" value=\""+s[4]+"\" />" +
			                "<INPUT TYPE=\"HIDDEN\" id=\"active_"+x+"\" value=\""+s[5]+"\" />" +
			                "<INPUT TYPE=\"HIDDEN\" id=\"id_"+x+"\" value=\""+s[6]+"\" />" +
			        		"<input type=\"button\" name=\"button_"+x+"\" value=\""+ml.getValue("edit")+"\" onclick=\"edit_principal_investigator_master(this.name);\" />" +
			        		"</TD>");
			        out.println("</TR>");x=x+1;
		        }
		       	out.println("</TABLE>");
		       	rs.close();
		       	st.close();
		  	    theResult.close();//Close the result set
		        theStatement.close();//Close statement
		  	    
		     }catch(Exception e){
			   e.printStackTrace();//Print trapped error.
		     }
		
	}//end of function show_principal_investigator_master.
	private void edit_principal_investigator_master(Connection conn,PrintWriter out,HttpServletRequest req,mlObj ml){
		try{
			String s[]=new String[7];
			s[0]=req.getParameter("pi_name");
		    s[1]=req.getParameter("department");
		    s[2]=req.getParameter("designation");
		    s[3]=req.getParameter("email");
		    s[4]=req.getParameter("address");
		    s[5]=req.getParameter("active");
		    s[6]=req.getParameter("id");
		    out.println("<INPUT TYPE=\"HIDDEN\" name=\"id\" value=\""+s[6]+"\" />");
	        out.println("<TABLE border=\"1\" align=center class=\"ListTable\" cellspacing=0 width=\"100%\">");
	        out.println("<TR><TD align=center colspan=\"2\" class=\"thead\" >"+ml.getValue("edit")+"</TD></TR>");
	        out.println("<TR><TD align=center colspan=\"2\" class=\"thead\" >"+ml.getValue("principal_investigator_master")+"</TD></TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"ListRow\" width=\"30%\" >"+ml.getValue("name")+"<label  class=\"mandatory\">*</label></TD>");
	        out.println("<TD class=\"ListRow\" width=\"70%\" ><input type=\"text\" size=\"50\" name=\"pi_name\" value=\""+s[0]+"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"ListRow\" width=\"30%\" >"+ml.getValue("department")+"<label  class=\"mandatory\">*</label></TD>");
	        out.println("<TD class=\"ListRow\" width=\"70%\" >");
	        Statement theStatement=conn.createStatement();
	        ResultSet theResult=theStatement.executeQuery("select id,fld_value from general_master where category=\'Department\' and active_yes_no=1");
	        out.println("<select name=\"department\" onchange=\"enable_save_button();\" >");
	        String fv="";
	        while(theResult.next()){
	        	fv=theResult.getString("fld_value");
	        	if(fv.equals(s[1])){
	        		out.println("<option value=\""+theResult.getInt("id")+"\" selected=\"selected\" >"+fv+"</option>");
	        	}else{
	        		out.println("<option value=\""+theResult.getInt("id")+"\" >"+fv+"</option>");
	        	}
	        }
	        theResult.close();theStatement.close();
	        out.println("</select></TD></TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"ListRow\" width=\"30%\" >"+ml.getValue("designation")+"<label  class=\"mandatory\">*</label></TD>");
	        out.println("<TD class=\"ListRow\" width=\"70%\" ><input type=\"text\" size=\"50\" name=\"designation\" value=\""+s[2]+"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"ListRow\" width=\"30%\" >"+ml.getValue("email")+"</TD>");
	        out.println("<TD class=\"ListRow\" width=\"70%\" ><input type=\"text\" size=\"50\" name=\"email\" value=\""+s[3]+"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"ListRow\" width=\"30%\" >"+ml.getValue("address")+"</TD>");
	        out.println("<TD class=\"ListRow\" width=\"70%\" ><input type=\"text\" size=\"50\" name=\"address\" value=\""+s[4]+"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"ListRow\" width=\"30%\" >"+ml.getValue("active")+"<label  class=\"mandatory\">*</label></TD>");
	        out.println("<TD class=\"ListRow\" width=\"70%\" >");
	        if(s[5].equals("Yes")){
	        	out.println("<select name=\"active\" onchange=\"enable_save_button();\" ><option value=\"1\" >"+ml.getValue("yes")+"</option><option value=\"0\" >"+ml.getValue("no")+"</option></select>");
	        }else if(s[5].equals("No")){
	        	out.println("<select name=\"active\" onchange=\"enable_save_button();\" ><option value=\"0\" >"+ml.getValue("no")+"</option><option value=\"1\" >"+ml.getValue("yes")+"</option></select>");
	        }
	        out.println("</TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"ListRow\" colspan=\"2\" align=\"center\" >" +
	        		"<input type=\"button\" value=\""+ml.getValue("save")+"\" name=\"save\" disabled=true  onclick=\"save_edit_principal_investigator_master();\" >" +
	        		"&nbsp;<input type=\"button\" value=\""+ml.getValue("back")+"\" name=\"back\" onclick=\"back_principal_investigator_master();\" >" +
	        		"</TD>");
	        out.println("</TR>");
	        out.println("</TABLE>");
	  	   	    
	     }catch(Exception e){
		   e.printStackTrace();//Print trapped error.
	     }
	}//end of function edit_principal_investigator_master.
	private void save_edit_principal_investigator_master(Connection conn,HttpServletRequest req,HttpServletResponse res) throws IOException{
		try{
			String s[]=new String[7];
			s[0]=req.getParameter("pi_name");
		    s[1]=req.getParameter("department");
		    s[2]=req.getParameter("designation");
		    s[3]=req.getParameter("email");
		    s[4]=req.getParameter("address");
		    s[5]=req.getParameter("active");
		    s[6]=req.getParameter("id");
		    if(s[5].equals("0"))
		    {
		    int status = table_relation(conn,req,s[6],"Principal Investigator");
	        if(status == 1)
	        {
	        	res.sendRedirect("./master_tables?action=show_principal_investigator_master&tab_name=principal_investigator_master&errmsg=This_record_is_related_with_some_another_records.");	
	        	return;
	        }
		    }
	        Statement theStatement=conn.createStatement();
	        theStatement.executeUpdate("update investigator_master set name=\'"+s[0]+"\',department_id="+s[1]+",designation=\'"+s[2]+"\',email=\'"+s[3]+"\',address=\'"+s[4]+"\',active_yes_no="+s[5]+" where id="+s[6]);
	      	theStatement.close();//Close statement
	  	    res.sendRedirect("./master_tables?action=show_principal_investigator_master&tab_name=principal_investigator_master");
	        }catch(java.sql.SQLIntegrityConstraintViolationException e) {  
             System.out.println ("Caught java.sql.SQLIntegrityConstraintViolationException") ;             
             res.sendRedirect("./master_tables?action=show_principal_investigator_master&tab_name=principal_investigator_master&errmsg=Same_Record_Already_Exists.");
	      }      
	      catch (java.sql.SQLException e) {  
	             System.out.println   
	            ("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " +   
	                                      e.getMessage() ) ; 
	      } 
	      catch(Exception e){	    	
	    	  res.sendRedirect("./master_tables?action=show_principal_investigator_master&tab_name=principal_investigator_master&errmsg=Eorr_in_Save"); 
  		      e.printStackTrace();			    
		     }
	}//end of function save_edit_principal_investigator_master.
	private void add_new_principal_investigator_master(Connection conn,PrintWriter out,HttpServletRequest req,mlObj ml){
		try{
			out.println("<TABLE border=\"1\" align=center class=\"ListTable\" cellspacing=0 width=\"100%\">");
	        out.println("<TR><TD align=center colspan=\"2\" class=\"thead\" >"+ml.getValue("add_new")+"</TD></TR>");
	        out.println("<TR><TD align=center colspan=\"2\" class=\"thead\" >"+ml.getValue("principal_investigator_master")+"</TD></TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"ListRow\" width=\"30%\" >"+ml.getValue("name")+"<label  class=\"mandatory\">*</label></TD>");
	        out.println("<TD class=\"ListRow\" width=\"70%\" ><input type=\"text\" size=\"50\" name=\"pi_name\" value=\"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"ListRow\" width=\"30%\" >"+ml.getValue("department")+"<label  class=\"mandatory\">*</label></TD>");
	        out.println("<TD class=\"ListRow\" width=\"70%\" >");
	        Statement theStatement=conn.createStatement();
	        ResultSet theResult=theStatement.executeQuery("select id,fld_value from general_master where category=\'Department\' and active_yes_no=1");
	        out.println("<select name=\"department\" onchange=\"enable_save_button();\" >");
	        while(theResult.next()){
	        	out.println("<option value=\""+theResult.getInt("id")+"\" >"+theResult.getString("fld_value")+"</option>");
	        }
	        theResult.close();theStatement.close();
	        out.println("</select></TD></TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"ListRow\" width=\"30%\" >"+ml.getValue("designation")+"<label  class=\"mandatory\">*</label></TD>");
	        out.println("<TD class=\"ListRow\" width=\"70%\" ><input type=\"text\" size=\"50\" name=\"designation\" value=\"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"ListRow\" width=\"30%\" >"+ml.getValue("email")+"</TD>");
	        out.println("<TD class=\"ListRow\" width=\"70%\" ><input type=\"text\" size=\"50\" name=\"email\" value=\"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"ListRow\" width=\"30%\" >"+ml.getValue("address")+"</TD>");
	        out.println("<TD class=\"ListRow\" width=\"70%\" ><input type=\"text\" size=\"50\" name=\"address\" value=\"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"ListRow\" width=\"30%\" >"+ml.getValue("active")+"<label  class=\"mandatory\">*</label></TD>");
	        out.println("<TD class=\"ListRow\" width=\"70%\" >");
	        out.println("<select name=\"active\" onchange=\"enable_save_button();\" ><option value=\"1\" >"+ml.getValue("yes")+"</option><option value=\"0\" >"+ml.getValue("no")+"</option></select>");
	        out.println("</TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"ListRow\" colspan=\"2\" align=\"center\" >" +
	        		"<input type=\"button\" value=\""+ml.getValue("save")+"\" name=\"save\" disabled=true  onclick=\"save_add_new_principal_investigator_master();\" >" +
	        		"&nbsp;<input type=\"button\" value=\""+ml.getValue("back")+"\" name=\"back\" onclick=\"back_principal_investigator_master();\" >" +
	        		"</TD>");
	        out.println("</TR>");
	        out.println("</TABLE>");
	  	   	    
	     }catch(Exception e){
		   e.printStackTrace();//Print trapped error.
	     }
	}//end of function add_new_principal_investigator_master.
	private void save_add_new_principal_investigator_master(Connection conn,HttpServletRequest req,HttpServletResponse res) throws IOException{
		try{
			String s[]=new String[6];
			s[0]=req.getParameter("pi_name");
		    s[1]=req.getParameter("department");
		    s[2]=req.getParameter("designation");
		    s[3]=req.getParameter("email");
		    s[4]=req.getParameter("address");
		    s[5]=req.getParameter("active");
		    Statement theStatement=conn.createStatement();
	        theStatement.executeUpdate("insert into investigator_master(name,department_id,designation,email,address,active_yes_no) values('"+s[0]+"','"+s[1]+"','"+s[2]+"','"+s[3]+"','"+s[4]+"',"+s[5]+")");
	      	theStatement.close();//Close statement
	  	    res.sendRedirect("./master_tables?action=show_principal_investigator_master&tab_name=principal_investigator_master");
	     }catch(java.sql.SQLIntegrityConstraintViolationException e) {  
             System.out.println ("Caught java.sql.SQLIntegrityConstraintViolationException") ;             
             res.sendRedirect("./master_tables?action=add_new_principal_investigator_master&tab_name=principal_investigator_master&errmsg=Same_Record_Already_Exists.");
	      }      
	      catch (java.sql.SQLException e) {  
	             System.out.println   
	            ("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " +   
	                                      e.getMessage() ) ; 
	      } 
	      catch(Exception e){	    	
	    	  res.sendRedirect("./master_tables?action=add_new_principal_investigator_master&tab_name=principal_investigator_master&errmsg=Eorr_in_Save"); 
  		      e.printStackTrace();			    
		     }
	}//end of function save_edit_principal_investigator_master.
	private void show_courses_taught_master(Connection conn, PrintWriter out,HttpServletRequest req,mlObj ml){
		try{
		    String faculty_name=req.getParameter("faculty_name");
	        Statement theStatement=conn.createStatement();
	        ResultSet theResult=theStatement.executeQuery("select id,user_full_name from users WHERE id<>1  order by user_full_name");
	        theResult.last();int len=theResult.getRow();String s[]=new String[len];String n[]=new String[len];int i=0;String fn="";
	        out.println(ml.getValue("name")+"&nbsp;&nbsp;<label  class=\"mandatory\">*</label><select name=\"faculty_name_combo\" id=\"cb01\" onchange=\"courses_taught_master_combo_change(this.value);\" >");
            theResult.beforeFirst();
	        while(theResult.next()){
	        	s[i]=Integer.toString(theResult.getInt("id"));
	        	n[i]=theResult.getString("user_full_name");
	        	if(!faculty_name.equals("") && faculty_name.equals(s[i])){
	        	     out.println("<option value=\""+s[i]+"\" selected=\"selected\" >"+n[i]+"</option>");fn=n[i];
	        	}else{
	        		 out.println("<option value=\""+s[i]+"\" >"+n[i]+"</option>");
	        	}
	        	i++;
	        }
	        if(faculty_name.equals("")){faculty_name=s[0];fn=n[0];}
	        out.println("</select>&nbsp;<input type=\"button\" name\"add_new\" value=\""+ml.getValue("add_new")+"\" onclick=\"courses_taught_master_button_change();\" /><br><br>");
	        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"faculty_name\" value=\""+faculty_name+"\" />");
	        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"id\" value=\"\" />");
	        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"academic_term\" value=\"\" />");
	        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"class_name\" value=\"\" />");
	        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"course_name\" value=\"\" />");
	        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"students_registered\" value=\"\" />");
	        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"percent_of_pass\" value=\"\" />");
	        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"active\" value=\"\" />");
	        out.println("<TABLE border=\"1\" align=center class=\"ListTable\" cellspacing=0 width=\"100%\">");
	        out.println("<TR><TD align=center colspan=\"7\" class=\"ListHeader\" >"+ml.getValue("courses_taught")+"</TD></TR>");
	        out.println("<TR><TD align=center colspan=\"7\" class=\"ListHeader\" >"+ml.getValue("faculty_name")+fn+"</TD></TR>");
	        out.println("<TR>");
	        out.println("<TD align=center class=\"ListHeader\" >"+ml.getValue("academic_term")+"</TD>");
	        out.println("<TD align=center class=\"ListHeader\">"+ml.getValue("class_name")+"</TD>");
	        out.println("<TD align=center class=\"ListHeader\">"+ml.getValue("course_name")+"</TD>");
	        out.println("<TD align=center class=\"ListHeader\">"+ml.getValue("students_registered")+"</TD>");
	        out.println("<TD align=center class=\"ListHeader\">"+ml.getValue("pass_percent")+"</TD>");
	        out.println("<TD align=center class=\"ListHeader\">"+ml.getValue("active")+"</TD>");
	        out.println("<TD align=center class=\"ListHeader\">"+ml.getValue("edit")+"</TD>");
	        out.println("</TR>");
	        String str[]=new String[7];String ayn="";int x=0;String at="";
	        theResult=theStatement.executeQuery("select * from course_taught where idf="+faculty_name);
	        Statement st=conn.createStatement();
	        while(theResult.next()) //Fetch all the records and print in table
			{
	  	   	    str[0]=Integer.toString(theResult.getInt("id"));
	  	   		str[1]=theResult.getString("academic_term");
	  	   	    str[2]=theResult.getString("class_name");
	  	   	    str[3]=theResult.getString("course_name");
	  	   	    str[4]=theResult.getString("students_registered");
	  	   	    str[5]=theResult.getString("percent_of_pass");
	  	   	    str[6]=theResult.getString("active_yes_no");
	  	   	    ResultSet rs=st.executeQuery("select fld_value from general_master where category=\'Academic_Term\' and id="+str[1]);
	  	   	    rs.first();at=rs.getString("fld_value");
	  	   	    if(str[6].equals("1")){ayn="<span style=\"color:green\">"+ml.getValue("yes")+"</span>";}else{ayn="<span style=\"color:red\">"+ml.getValue("no")+"</span>";}
	  	   	    out.println("<TR>");
		        out.println("<TD align=center class=\"ListRow\" >"+at+"</TD>");
		        out.println("<TD align=center class=\"ListRow\">"+str[2]+"</TD>");
		        out.println("<TD align=center class=\"ListRow\">"+str[3]+"</TD>");
		        out.println("<TD align=center class=\"ListRow\">"+str[4]+"</TD>");
		        out.println("<TD align=center class=\"ListRow\">"+str[5]+"</TD>");
		        out.println("<TD align=center class=\"ListRow\">"+ayn+"</TD>");
		        out.println("<TD align=center class=\"ListRow\">" +
		        		"<INPUT TYPE=\"HIDDEN\" id=\"id_"+x+"\" value=\""+str[0]+"\" />"+
		        		"<INPUT TYPE=\"HIDDEN\" id=\"academic_term_"+x+"\" value=\""+str[1]+"\" />"+
		        		"<INPUT TYPE=\"HIDDEN\" id=\"class_name_"+x+"\" value=\""+str[2]+"\" />"+
		        		"<INPUT TYPE=\"HIDDEN\" id=\"course_name_"+x+"\" value=\""+str[3]+"\" />"+
		        		"<INPUT TYPE=\"HIDDEN\" id=\"students_registered_"+x+"\" value=\""+str[4]+"\" />"+
		        		"<INPUT TYPE=\"HIDDEN\" id=\"percent_of_pass_"+x+"\" value=\""+str[5]+"\" />"+
		        		"<INPUT TYPE=\"HIDDEN\" id=\"active_"+x+"\" value=\""+str[6]+"\" />"+
		        		"<input type=\"button\" name=\"button_"+x+"\" value=\""+ml.getValue("edit")+"\" onclick=\"edit_courses_taught_master(this.name);\" />" +
		        		"</TD>");
		        out.println("</TR>");x=x+1; rs.close();
			}
	        out.println("</TABLE>");
	       
	        st.close();
	  	    theResult.close();//Close the result set
	        theStatement.close();//Close statement
	  	    
	     }catch(Exception e){
		   e.printStackTrace();//Print trapped error.
	     }
	}//end of function show_courses_taught_master.
	private void edit_courses_taught_master(Connection conn, PrintWriter out,HttpServletRequest req,mlObj ml){
		try{
			String s[]=new String[8];
			s[0]=req.getParameter("faculty_name");
		    s[1]=req.getParameter("id");
		    s[2]=req.getParameter("academic_term");
		    s[3]=req.getParameter("class_name");
		    s[4]=req.getParameter("course_name");
		    s[5]=req.getParameter("students_registered");
		    s[6]=req.getParameter("percent_of_pass");
		    s[7]=req.getParameter("active");
		    out.println("<INPUT TYPE=\"HIDDEN\" name=\"faculty_name\" value=\""+s[0]+"\" />");
		    out.println("<INPUT TYPE=\"HIDDEN\" name=\"id\" value=\""+s[1]+"\" />");
	        out.println("<TABLE border=\"1\" align=center class=\"ListTable\" cellspacing=0 width=\"100%\">");
	        out.println("<TR><TD align=center colspan=\"2\" class=\"thead\" >"+ml.getValue("edit")+"</TD></TR>");
	        out.println("<TR><TD align=center colspan=\"2\" class=\"thead\" >"+ml.getValue("courses_taught_master")+"</TD></TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"ListRow\" width=\"30%\" >"+ml.getValue("academic_term")+"</TD>");
	        out.println("<TD class=\"ListRow\" width=\"70%\" >");
	        Statement theStatement=conn.createStatement();
	        ResultSet theResult=theStatement.executeQuery("select id,fld_value from general_master where category=\'Academic_Term\' and active_yes_no=1");
	        out.println("<select name=\"academic_term\" onchange=\"enable_save_button();\" ><label  class=\"mandatory\">*</label>");
	        String at="";
	        while(theResult.next()){
	        	at=Integer.toString(theResult.getInt("id"));
	        	if(at.equals(s[2])){
	        		out.println("<option value=\""+at+"\" selected=\"selected\" >"+theResult.getString("fld_value")+"</option>");
	        	}else{
	        		out.println("<option value=\""+at+"\" >"+theResult.getString("fld_value")+"</option>");
	        	}
	        }
	        theResult.close();theStatement.close();
	        out.println("</select></TD></TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"ListRow\" width=\"30%\" >"+ml.getValue("class_name")+"<label  class=\"mandatory\">*</label></TD>");
	        out.println("<TD class=\"ListRow\" width=\"70%\" ><input type=\"text\" size=\"50\" name=\"class_name\" value=\""+s[3]+"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"ListRow\" width=\"30%\" >"+ml.getValue("course_name")+"<label  class=\"mandatory\">*</label></TD>");
	        out.println("<TD class=\"ListRow\" width=\"70%\" ><input type=\"text\" size=\"50\" name=\"course_name\" value=\""+s[4]+"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"ListRow\" width=\"30%\" >"+ml.getValue("students_registered")+"<label  class=\"mandatory\">*</label></TD>");
	        out.println("<TD class=\"ListRow\" width=\"70%\" ><input type=\"text\" size=\"50\" name=\"students_registered\" value=\""+s[5]+"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"ListRow\" width=\"30%\" >"+ml.getValue("pass_percent")+"<label  class=\"mandatory\">*</label></TD>");
	        out.println("<TD class=\"ListRow\" width=\"70%\" ><input type=\"text\" size=\"50\" name=\"percent_of_pass\" value=\""+s[6]+"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"ListRow\" width=\"30%\" >"+ml.getValue("active")+"<label  class=\"mandatory\">*</label></TD>");
	        out.println("<TD class=\"ListRow\" width=\"70%\" >");
	        if(s[7].equals("1")){
	        	out.println("<select name=\"active\" onchange=\"enable_save_button();\" ><option value=\"1\" >"+ml.getValue("yes")+"</option><option value=\"0\" >"+ml.getValue("no")+"</option></select>");
	        }else if(s[7].equals("0")){
	        	out.println("<select name=\"active\" onchange=\"enable_save_button();\" ><option value=\"0\" >"+ml.getValue("no")+"</option><option value=\"1\" >"+ml.getValue("yes")+"</option></select>");
	        }
	        out.println("</TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"ListRow\" colspan=\"2\" align=\"center\" >" +
	        		"<input type=\"button\" value=\""+ml.getValue("save")+"\" name=\"save\" disabled=true  onclick=\"save_edit_courses_taught_master();\" >" +
	        		"&nbsp;<input type=\"button\" value=\""+ml.getValue("back")+"\" name=\"back\" onclick=\"back_courses_taught_master();\" >" +
	        		"</TD>");
	        out.println("</TR>");
	        out.println("</TABLE>");
	  	   	    
	     }catch(Exception e){
		   e.printStackTrace();//Print trapped error.
	     }
	}//end of function edit_courses_taught_master.
	private void save_edit_courses_taught_master(Connection conn,HttpServletRequest req,HttpServletResponse res) throws IOException{
		String s[]=new String[8];
		try{
			
			s[0]=req.getParameter("faculty_name");
		    s[1]=req.getParameter("id");
		    s[2]=req.getParameter("academic_term");
		    s[3]=req.getParameter("class_name");
		    s[4]=req.getParameter("course_name");
		    s[5]=req.getParameter("students_registered");
		    s[6]=req.getParameter("percent_of_pass");
		    s[7]=req.getParameter("active");
		    Statement theStatement=conn.createStatement();
	        theStatement.executeUpdate("update course_taught set academic_term=\'"+s[2]+"\',class_name=\'"+s[3]+"\',course_name=\'"+s[4]+"\',students_registered=\'"+s[5]+"\',percent_of_pass=\'"+s[6]+"\',active_yes_no="+s[7]+" where id="+s[1]+" and idf="+s[0]);
	      	theStatement.close();//Close statement
	  	    res.sendRedirect("./master_tables?action=show_courses_taught_master&tab_name=courses_taught_master&faculty_name="+s[0]);
	     }catch(java.sql.SQLIntegrityConstraintViolationException e) {  
             System.out.println ("Caught java.sql.SQLIntegrityConstraintViolationException") ;             
             res.sendRedirect("./master_tables?action=show_courses_taught_master&tab_name=courses_taught_master&faculty_name="+s[0]+"&errmsg=Same_Record_Already_Exists.");
	      }      
	      catch (java.sql.SQLException e) {  
	             System.out.println   
	            ("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " +   
	                                      e.getMessage() ) ; 
	      } 
	      catch(Exception e){	    	
	    	  res.sendRedirect("./master_tables?action=show_courses_taught_master&tab_name=courses_taught_master&faculty_name="+s[0]+"&errmsg=Eorr_in_Save"); 
  		      e.printStackTrace();			    
		     }	
	}//end of function save_edit_courses_taught_master.
	private void add_new_courses_taught_master(Connection conn, PrintWriter out,HttpServletRequest req,mlObj ml){
		try{
			String faculty_name=req.getParameter("faculty_name");
		    out.println("<INPUT TYPE=\"HIDDEN\" name=\"faculty_name\" value=\""+faculty_name+"\" />");
		    out.println("<TABLE border=\"1\" align=center class=\"ListTable\" cellspacing=0 width=\"100%\">");
	        out.println("<TR><TD align=center colspan=\"2\" class=\"thead\" >"+ml.getValue("add_new")+"</TD></TR>");
	        out.println("<TR><TD align=center colspan=\"2\" class=\"thead\" >"+ml.getValue("courses_taught_master")+"</TD></TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"ListRow\" width=\"30%\" >"+ml.getValue("academic_term")+"<label  class=\"mandatory\">*</label></TD>");
	        out.println("<TD class=\"ListRow\" width=\"70%\" >");
	        Statement theStatement=conn.createStatement();
	        ResultSet theResult=theStatement.executeQuery("select id,fld_value from general_master where category=\'Academic_Term\' and active_yes_no=1");
	        out.println("<select name=\"academic_term\" onchange=\"enable_save_button();\" >");
	        String at="";
	        while(theResult.next()){
	        	at=Integer.toString(theResult.getInt("id"));
	        	out.println("<option value=\""+at+"\" >"+theResult.getString("fld_value")+"</option>");
	        }
	        theResult.close();theStatement.close();
	        out.println("</select></TD></TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"ListRow\" width=\"30%\" >"+ml.getValue("class_name")+"<label  class=\"mandatory\">*</label></TD>");
	        out.println("<TD class=\"ListRow\" width=\"70%\" ><input type=\"text\" size=\"50\" name=\"class_name\" value=\"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"ListRow\" width=\"30%\" >"+ml.getValue("course_name")+"<label  class=\"mandatory\">*</label></TD>");
	        out.println("<TD class=\"ListRow\" width=\"70%\" ><input type=\"text\" size=\"50\" name=\"course_name\" value=\"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"ListRow\" width=\"30%\" >"+ml.getValue("students_registered")+"<label  class=\"mandatory\">*</label></TD>");
	        out.println("<TD class=\"ListRow\" width=\"70%\" ><input type=\"text\" size=\"50\" name=\"students_registered\" value=\"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"ListRow\" width=\"30%\" >"+ml.getValue("pass_percent")+"<label  class=\"mandatory\">*</label></TD>");
	        out.println("<TD class=\"ListRow\" width=\"70%\" ><input type=\"text\" size=\"50\" name=\"percent_of_pass\" value=\"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"ListRow\" width=\"30%\" >"+ml.getValue("active")+"<label  class=\"mandatory\">*</label></TD>");
	        out.println("<TD class=\"ListRow\" width=\"70%\" >");
	        out.println("<select name=\"active\" onchange=\"enable_save_button();\" ><option value=\"1\" >"+ml.getValue("yes")+"</option><option value=\"0\" >"+ml.getValue("no")+"</option></select>");
	        out.println("</TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"ListRow\" colspan=\"2\" align=\"center\" >" +
	        		"<input type=\"button\" value=\""+ml.getValue("save")+"\" name=\"save\" disabled=true  onclick=\"save_add_new_courses_taught_master();\" >" +
	        		"&nbsp;<input type=\"button\" value=\""+ml.getValue("back")+"\" name=\"back\" onclick=\"back_courses_taught_master();\" >" +
	        		"</TD>");
	        out.println("</TR>");
	        out.println("</TABLE>");
	  	   	    
	     }catch(Exception e){
		   e.printStackTrace();//Print trapped error.
	     }
	}//end of function add_new_courses_taught_master.
    private void save_add_new_courses_taught_master(Connection conn,HttpServletRequest req,HttpServletResponse res) throws IOException{
    	String s[]=new String[7];
    	try{			
			s[0]=req.getParameter("faculty_name");
		    s[1]=req.getParameter("academic_term");
		    s[2]=req.getParameter("class_name");
		    s[3]=req.getParameter("course_name");
		    s[4]=req.getParameter("students_registered");
		    s[5]=req.getParameter("percent_of_pass");
		    s[6]=req.getParameter("active");
		    Statement theStatement=conn.createStatement();
	        theStatement.executeUpdate("insert into course_taught(idf,academic_term,class_name,course_name,students_registered,percent_of_pass,active_yes_no) values("+s[0]+",'"+s[1]+"','"+s[2]+"','"+s[3]+"','"+s[4]+"','"+s[5]+"',"+s[6]+")");
	      	theStatement.close();//Close statement
	  	    res.sendRedirect("./master_tables?action=show_courses_taught_master&tab_name=courses_taught_master&faculty_name="+s[0]);
	       }catch(java.sql.SQLIntegrityConstraintViolationException e) {  
	             System.out.println ("Caught java.sql.SQLIntegrityConstraintViolationException") ;             
	             res.sendRedirect("./master_tables?action=add_new_courses_taught_master&tab_name=courses_taught_master&faculty_name="+s[0]+"&errmsg=Same_Record_Already_Exists.");
		      }      
		      catch (java.sql.SQLException e) {  
		             System.out.println   
		            ("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " +   
		                                      e.getMessage() ) ; 
		      } 
		      catch(Exception e){	    	
		    	  res.sendRedirect("./master_tables?action=add_new_courses_taught_master&tab_name=courses_taught_master&faculty_name="+s[0]+"&errmsg=Eorr_in_Save"); 
	  		      e.printStackTrace();			    
			     }	     
	}//end of function save_add_new_courses_taught_master.
    private void show_university_master(Connection conn, PrintWriter out,mlObj ml){
    	try{
		    out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"id\" value=\"\" />");
	        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"university_name\" value=\"\" />");
	        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"short_name\" value=\"\" />");
	        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"address\" value=\"\" />");
	        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"active\" value=\"\" />");
	        out.println("<INPUT TYPE=\"button\" NAME=\"add\" value=\""+ml.getValue("add_new")+"\" onclick=\"add_new_university_master();\" /><br><br>");
		    out.println("<TABLE border=\"1\" align=center class=\"ListTable\" cellspacing=0 width=\"100%\">");
	        out.println("<TR><TD align=center colspan=\"5\" class=\"ListHeader\" >"+ml.getValue("university_master")+"</TD></TR>");
	        out.println("<TR>");
	        out.println("<TD align=center class=\"ListHeader\" >"+ml.getValue("name")+"</TD>");
	        out.println("<TD align=center class=\"ListHeader\">"+ml.getValue("short_name")+"</TD>");
	        out.println("<TD align=center class=\"ListHeader\" >"+ml.getValue("address")+"</TD>");
	        out.println("<TD align=center class=\"ListHeader\">"+ml.getValue("active")+"</TD>");
	        out.println("<TD align=center class=\"ListHeader\">"+ml.getValue("edit")+"</TD>");
	        out.println("</TR>");
	        Statement theStatement=conn.createStatement();
	        ResultSet theResult=theStatement.executeQuery("select * from university_master");
	        String str="";int x=0;String s[]=new String[5];
	        while(theResult.next()){
	        	s[0]=Integer.toString(theResult.getInt("id"));
	        	s[1]=theResult.getString("name");
			   	s[2]=theResult.getString("short_name");
	        	s[3]=theResult.getString("address");
	        	s[4]=theResult.getString("active_yes_no");
		        if(s[4].equals("1")){str="<span style=\"color:green\">"+ml.getValue("yes")+"</span>";}else{str="<span style=\"color:red\">"+ml.getValue("no")+"</span>";}
	        	out.println("<TR>");
		        out.println("<TD  class=\"ListRow\" >"+s[1]+"</TD>");
		        out.println("<TD align=center class=\"ListRow\" >"+s[2]+"</TD>");
		        out.println("<TD align=center class=\"ListRow\" >"+s[3]+"</TD>");
		        out.println("<TD align=center class=\"ListRow\" >"+str+"</TD>");
		        out.println("<TD align=center class=\"ListRow\" >" +
		        		"<INPUT TYPE=\"HIDDEN\" id=\"id_"+x+"\" value=\""+s[0]+"\" />" +
		                "<INPUT TYPE=\"HIDDEN\" id=\"university_name_"+x+"\" value=\""+s[1]+"\" />" +
		                "<INPUT TYPE=\"HIDDEN\" id=\"short_name_"+x+"\" value=\""+s[2]+"\" />" +
		                "<INPUT TYPE=\"HIDDEN\" id=\"address_"+x+"\" value=\""+s[3]+"\" />" +
		                "<INPUT TYPE=\"HIDDEN\" id=\"active_"+x+"\" value=\""+s[4]+"\" />" +
		               	"<input type=\"button\" name=\"button_"+x+"\" value=\""+ml.getValue("edit")+"\" onclick=\"edit_university_master(this.name);\" />" +
		        		"</TD>");
		        out.println("</TR>");x=x+1;
	        }
	       	out.println("</TABLE>");
	        theResult.close();//Close the result set
	        theStatement.close();//Close statement
	  	    
	     }catch(Exception e){
		   e.printStackTrace();//Print trapped error.
	     }
    }//end of function show_university_master.
    private void edit_university_master(PrintWriter out,HttpServletRequest req,mlObj ml){
    	try{
    		String s[]=new String[5];
    		s[0]=req.getParameter("id");
    		s[1]=req.getParameter("university_name");
    		s[2]=req.getParameter("short_name");
    		s[3]=req.getParameter("address");
    		s[4]=req.getParameter("active");
    		out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"id\" value=\""+s[0]+"\" />");
    		out.println("<TABLE border=\"1\" align=center class=\"ListTable\" cellspacing=0 width=\"100%\">");
    		out.println("<TR><TD align=center colspan=\"2\" class=\"thead\" >"+ml.getValue("edit")+"</TD></TR>");
	        out.println("<TR><TD align=center colspan=\"2\" class=\"thead\" >"+ml.getValue("university_master")+"</TD></TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"ListRow\" width=\"30%\" >"+ml.getValue("name")+"<label  class=\"mandatory\">*</label></TD>");
	        out.println("<TD class=\"ListRow\" width=\"70%\" ><input type=\"text\" size=\"50\" name=\"university_name\" value=\""+s[1]+"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"ListRow\" width=\"30%\" >"+ml.getValue("short_name")+"<label  class=\"mandatory\">*</label></TD>");
	        out.println("<TD class=\"ListRow\" width=\"70%\" ><input type=\"text\" size=\"50\" name=\"short_name\" value=\""+s[2]+"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"ListRow\" width=\"30%\" >"+ml.getValue("address")+"<label  class=\"mandatory\">*</label></TD>");
	        out.println("<TD class=\"ListRow\" width=\"70%\" ><input type=\"text\" size=\"50\" name=\"address\" value=\""+s[3]+"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR>");
	        out.println("<TD class=\"ListRow\" width=\"30%\" >"+ml.getValue("active")+"<label  class=\"mandatory\">*</label></TD>");
	        out.println("<TD class=\"ListRow\" width=\"70%\" >");
	        if(s[4].equals("1")){
	        	out.println("<select name=\"active\" onchange=\"enable_save_button();\" ><option value=\"1\" >"+ml.getValue("yes")+"</option><option value=\"0\" >"+ml.getValue("no")+"</option></select>");
	        }else if(s[4].equals("0")){
	        	out.println("<select name=\"active\" onchange=\"enable_save_button();\" ><option value=\"0\" >"+ml.getValue("no")+"</option><option value=\"1\" >"+ml.getValue("yes")+"</option></select>");
	        }
	        out.println("</TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"ListRow\" colspan=\"2\" align=\"center\" >" +
	        		"<input type=\"button\" value=\""+ml.getValue("save")+"\" name=\"save\" disabled=true  onclick=\"save_edit_university_master();\" >" +
	        		"&nbsp;<input type=\"button\" value=\""+ml.getValue("back")+"\" name=\"back\" onclick=\"back_university_master();\" >" +
	        		"</TD>");
	        out.println("</TR>");
	        out.println("</TABLE>");
    	}catch(Exception e){
 		   e.printStackTrace();//Print trapped error.
	     }
    }//end of function edit_university_master.
    private void save_edit_university_master(Connection conn,HttpServletRequest req,HttpServletResponse res) throws IOException{
    	try{
			String s[]=new String[5];
			s[0]=req.getParameter("id");
		    s[1]=req.getParameter("university_name");
		    s[2]=req.getParameter("short_name");
		    s[3]=req.getParameter("address");
		    s[4]=req.getParameter("active");
		    if(s[4].equals("0"))
		    {
		    int status = table_relation(conn,req,s[0], "University");
		    if(status == 1)
	        {
	        	res.sendRedirect("./master_tables?action=show_university_master&tab_name=university_master&errmsg=This_record_is_used_by_institution_master.");	
	        	return;
	        }
		    } 
		    Statement theStatement=conn.createStatement();
	        theStatement.executeUpdate("update university_master set name=\'"+s[1]+"\',short_name=\'"+s[2]+"\',address=\'"+s[3]+"\',active_yes_no="+s[4]+" where id="+s[0]);
	      	theStatement.close();//Close statement
	  	    res.sendRedirect("./master_tables?action=show_university_master&tab_name=university_master");
	        }catch(java.sql.SQLIntegrityConstraintViolationException e) {  
	             System.out.println ("Caught java.sql.SQLIntegrityConstraintViolationException") ;             
	             res.sendRedirect("./master_tables?action=show_university_master&tab_name=university_master&errmsg=Same_Record_Already_Exists.");
		      }      
		      catch (java.sql.SQLException e) {  
		             System.out.println   
		            ("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " +   
		                                      e.getMessage() ) ; 
		      } 
		      catch(Exception e){	    	
		    	  res.sendRedirect("./master_tables?action=show_university_master&tab_name=university_master&errmsg=Eorr_in_Save"); 
	  		      e.printStackTrace();			    
			     }	
	}//end of function save_add_new_courses_taught_master.
    private void add_new_university_master(PrintWriter out,HttpServletRequest req,mlObj ml){
    	try{
    		out.println("<TABLE border=\"1\" align=center class=\"ListTable\" cellspacing=0 width=\"100%\">");
    		out.println("<TR><TD align=center colspan=\"2\" class=\"thead\" >"+ml.getValue("add_new")+"</TD></TR>");
	        out.println("<TR><TD align=center colspan=\"2\" class=\"thead\" >"+ml.getValue("university_master")+"</TD></TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"ListRow\" width=\"30%\" >"+ml.getValue("name")+"<label  class=\"mandatory\">*</label></TD>");
	        out.println("<TD class=\"ListRow\" width=\"70%\" ><input type=\"text\" size=\"50\" name=\"university_name\" value=\"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"ListRow\" width=\"30%\" >"+ml.getValue("short_name")+"<label  class=\"mandatory\">*</label></TD>");
	        out.println("<TD class=\"ListRow\" width=\"70%\" ><input type=\"text\" size=\"50\" name=\"short_name\" value=\"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"ListRow\" width=\"30%\" >"+ml.getValue("address")+"<label  class=\"mandatory\">*</label></TD>");
	        out.println("<TD class=\"ListRow\" width=\"70%\" ><input type=\"text\" size=\"50\" name=\"address\" value=\"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR>");
	        out.println("<TD class=\"ListRow\" width=\"30%\" >"+ml.getValue("active")+"<label  class=\"mandatory\">*</label></TD>");
	        out.println("<TD class=\"ListRow\" width=\"70%\" >");
	        out.println("<select name=\"active\" onchange=\"enable_save_button();\" ><option value=\"1\" >"+ml.getValue("yes")+"</option><option value=\"0\" >"+ml.getValue("no")+"</option></select>");
	        out.println("</TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"ListRow\" colspan=\"2\" align=\"center\" >" +
	        		"<input type=\"button\" value=\""+ml.getValue("save")+"\" name=\"save\" disabled=true  onclick=\"save_add_new_university_master();\" >" +
	        		"&nbsp;<input type=\"button\" value=\""+ml.getValue("back")+"\" name=\"back\" onclick=\"back_university_master();\" >" +
	        		"</TD>");
	        out.println("</TR>");
	        out.println("</TABLE>");
    	}catch(Exception e){
 		   e.printStackTrace();//Print trapped error.
	     }
    }//end of function add_new_university_master.
    private void save_add_new_university_master(Connection conn,HttpServletRequest req,HttpServletResponse res) throws IOException{
    	try{
			String s[]=new String[4];
			s[0]=req.getParameter("university_name");
		    s[1]=req.getParameter("short_name");
		    s[2]=req.getParameter("address");
		    s[3]=req.getParameter("active");
		    Statement theStatement=conn.createStatement();
	        theStatement.executeUpdate("insert into university_master(name,short_name,address,active_yes_no) values('"+s[0]+"','"+s[1]+"','"+s[2]+"',"+s[3]+")");
	      	theStatement.close();//Close statement
	  	    res.sendRedirect("./master_tables?action=show_university_master&tab_name=university_master");
	       }catch(java.sql.SQLIntegrityConstraintViolationException e) {  
	             System.out.println ("Caught java.sql.SQLIntegrityConstraintViolationException") ;             
	             res.sendRedirect("./master_tables?action=add_new_university_master&tab_name=university_master&errmsg=Same_Record_Already_Exists.");
		      }      
		      catch (java.sql.SQLException e) {  
		             System.out.println   
		            ("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " +   
		                                      e.getMessage() ) ; 
		      } 
		      catch(Exception e){	    	
		    	  res.sendRedirect("./master_tables?action=add_new_university_master&tab_name=university_master&errmsg=Eorr_in_Save"); 
	  		      e.printStackTrace();			    
			     }	   
	}//end of function save_add_new_courses_taught_master.
    private void show_institution_master(Connection conn,PrintWriter out,mlObj ml){
    	try{
		    out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"id\" value=\"\" />");
	        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"institution_name\" value=\"\" />");
	        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"short_name\" value=\"\" />");
	        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"address\" value=\"\" />");
	        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"location\" value=\"\" />");
	        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"university_id\" value=\"\" />");
	        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"active\" value=\"\" />");
	        out.println("<INPUT TYPE=\"button\" NAME=\"add\" value=\""+ml.getValue("add_new")+"\" onclick=\"add_new_institution_master();\" /><br><br>");
		    out.println("<TABLE border=\"1\" align=center class=\"ListTable\" cellspacing=0 width=\"100%\">");
	        out.println("<TR><TD align=center colspan=\"7\" class=\"ListHeader\" >"+ml.getValue("institution_master")+"</TD></TR>");
	        out.println("<TR>");
	        out.println("<TD align=center class=\"ListHeader\" >"+ml.getValue("name")+"</TD>");
	        out.println("<TD align=center class=\"ListHeader\">"+ml.getValue("short_name")+"</TD>");
	        out.println("<TD align=center class=\"ListHeader\" >"+ml.getValue("address")+"</TD>");
	        out.println("<TD align=center class=\"ListHeader\">"+ml.getValue("location")+"</TD>");
	        out.println("<TD align=center class=\"ListHeader\">"+ml.getValue("university")+"</TD>");
	        out.println("<TD align=center class=\"ListHeader\">"+ml.getValue("active")+"</TD>");
	        out.println("<TD align=center class=\"ListHeader\">"+ml.getValue("edit")+"</TD>");
	        out.println("</TR>");
	        Statement theStatement=conn.createStatement();
	        Statement st=conn.createStatement();
	        ResultSet theResult=theStatement.executeQuery("select * from institution_master");
	        String str="";int x=0;String s[]=new String[7];
	        while(theResult.next()){
	        	s[0]=Integer.toString(theResult.getInt("id"));
	        	s[1]=theResult.getString("name");
			   	s[2]=theResult.getString("short_name");
	        	s[3]=theResult.getString("address");
	        	s[4]=theResult.getString("location");
	        	s[5]=Integer.toString(theResult.getInt("university_id"));
	        	s[6]=theResult.getString("active_yes_no");
		        if(s[6].equals("1")){str="<span style=\"color:green\">"+ml.getValue("yes")+"</span>";}else{str="<span style=\"color:red\">"+ml.getValue("no")+"</span>";}
	        	out.println("<TR>");
		        out.println("<TD  class=\"ListRow\" >"+s[1]+"</TD>");
		        out.println("<TD align=center class=\"ListRow\" >"+s[2]+"</TD>");
		        out.println("<TD class=\"ListRow\" >"+s[3]+"</TD>");
		        out.println("<TD align=center class=\"ListRow\" >"+s[4]+"</TD>");
		        ResultSet rs=st.executeQuery("select name from university_master where id="+s[5]);
		        rs.first(); out.println("<TD align=center class=\"ListRow\" >"+rs.getString("name")+"</TD>");rs.close();
		        out.println("<TD align=center class=\"ListRow\" >"+str+"</TD>");
		        out.println("<TD align=center class=\"ListRow\" >" +
		        		"<INPUT TYPE=\"HIDDEN\" id=\"id_"+x+"\" value=\""+s[0]+"\" />" +
		                "<INPUT TYPE=\"HIDDEN\" id=\"institution_name_"+x+"\" value=\""+s[1]+"\" />" +
		                "<INPUT TYPE=\"HIDDEN\" id=\"short_name_"+x+"\" value=\""+s[2]+"\" />" +
		                "<INPUT TYPE=\"HIDDEN\" id=\"address_"+x+"\" value=\""+s[3]+"\" />" +
		                "<INPUT TYPE=\"HIDDEN\" id=\"location_"+x+"\" value=\""+s[4]+"\" />" +
		                "<INPUT TYPE=\"HIDDEN\" id=\"university_id_"+x+"\" value=\""+s[5]+"\" />" +
		                "<INPUT TYPE=\"HIDDEN\" id=\"active_"+x+"\" value=\""+s[6]+"\" />" +
		               	"<input type=\"button\" name=\"button_"+x+"\" value=\""+ml.getValue("edit")+"\" onclick=\"edit_institution_master(this.name);\" />" +
		        		"</TD>");
		        out.println("</TR>");x=x+1;
	        }
	       	out.println("</TABLE>");
	       	st.close();
	        theResult.close();//Close the result set
	        theStatement.close();//Close statement
	  	    
	     }catch(Exception e){
		   e.printStackTrace();//Print trapped error.
	     }
    }//end of function show_institution_master.
    private void edit_institution_master(Connection conn,PrintWriter out,HttpServletRequest req,mlObj ml){
    	try{
    		String s[]=new String[7];
			s[0]=req.getParameter("id");
		    s[1]=req.getParameter("institution_name");
		    s[2]=req.getParameter("short_name");
		    s[3]=req.getParameter("address");
		    s[4]=req.getParameter("location");
		    s[5]=req.getParameter("university_id");
		    s[6]=req.getParameter("active");
		    out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"id\" value=\""+s[0]+"\" />");
	        out.println("<TABLE border=\"1\" align=center class=\"ListTable\" cellspacing=0 width=\"100%\">");
	        out.println("<TR><TD align=center colspan=\"2\" class=\"thead\" >"+ml.getValue("edit")+"</TD></TR>");
	        out.println("<TR><TD align=center colspan=\"2\" class=\"thead\" >"+ml.getValue("institution_master")+"</TD></TR>");
	        out.println("<TR>");
	        out.println("<TD width=\"30%\" class=\"ListRow\" >"+ml.getValue("name")+"<label  class=\"mandatory\">*</label></TD>");
	        out.println("<TD class=\"ListRow\" width=\"70%\" ><input type=\"text\" size=\"50\" name=\"institution_name\" value=\""+s[1]+"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR><TR>");
	        out.println("<TD width=\"30%\" class=\"ListRow\" >"+ml.getValue("short_name")+"<label  class=\"mandatory\">*</label></TD>");
	        out.println("<TD class=\"ListRow\" width=\"70%\" ><input type=\"text\" size=\"50\" name=\"short_name\" value=\""+s[2]+"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR><TR>");
	        out.println("<TD width=\"30%\" class=\"ListRow\" >"+ml.getValue("address")+"<label  class=\"mandatory\">*</label></TD>");
	        out.println("<TD class=\"ListRow\" width=\"70%\" ><input type=\"text\" size=\"50\" name=\"address\" value=\""+s[3]+"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR><TR>");
	        out.println("<TD width=\"30%\" class=\"ListRow\" >"+ml.getValue("location")+"<label  class=\"mandatory\">*</label></TD>");
	        out.println("<TD class=\"ListRow\" width=\"70%\" ><input type=\"text\" size=\"50\" name=\"location\" value=\""+s[4]+"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR><TR>");
	        out.println("<TD class=\"ListRow\" width=\"30%\" >"+ml.getValue("university")+"<label  class=\"mandatory\">*</label></TD><TD class=\"ListRow\" width=\"70%\" >");
	        Statement theStatement=conn.createStatement();
	        ResultSet theResult=theStatement.executeQuery("select id,name from university_master where active_yes_no=1");
	        out.println("<select name=\"university_id\" onchange=\"enable_save_button();\" >");
	        String at="";
	        while(theResult.next()){
	        	at=Integer.toString(theResult.getInt("id"));
	        	if(at.equals(s[5])){
	        		out.println("<option value=\""+at+"\" selected=\"selected\" >"+theResult.getString("name")+"</option>");
	        	}else{
	        		out.println("<option value=\""+at+"\" >"+theResult.getString("name")+"</option>");
	        	}
	        }
	        theResult.close();//Close the result set
	        theStatement.close();//Close statement
	        out.println("</select></TD></TR><TR>");
	        out.println("<TD class=\"ListRow\" width=\"30%\" >"+ml.getValue("active")+"<label  class=\"mandatory\">*</label></TD><TD class=\"ListRow\" width=\"70%\" >");
	        if(s[6].equals("1")){
	        	out.println("<select name=\"active\" onchange=\"enable_save_button();\" ><option value=\"1\" >"+ml.getValue("yes")+"</option><option value=\"0\" >"+ml.getValue("no")+"</option></select>");
	        }else if(s[6].equals("0")){
	        	out.println("<select name=\"active\" onchange=\"enable_save_button();\" ><option value=\"0\" >"+ml.getValue("no")+"</option><option value=\"1\" >"+ml.getValue("yes")+"</option></select>");
	        }
	        out.println("</TD></TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"ListRow\" colspan=\"2\" align=\"center\" >" +
	        		"<input type=\"button\" value=\""+ml.getValue("save")+"\" name=\"save\" disabled=true  onclick=\"save_edit_institution_master();\" >" +
	        		"&nbsp;<input type=\"button\" value=\""+ml.getValue("back")+"\" name=\"back\" onclick=\"back_institution_master();\" >" +
	        		"</TD>");
	        out.println("</TR>");
	       	out.println("</TABLE>");
	     }catch(Exception e){
		   e.printStackTrace();//Print trapped error.
	     }
    }//end of function edit_institution_master.
    private void save_edit_institution_master(Connection conn,HttpServletRequest req,HttpServletResponse res) throws IOException{
    	try{
    		String s[]=new String[7];
			s[0]=req.getParameter("id");
		    s[1]=req.getParameter("institution_name");
		    s[2]=req.getParameter("short_name");
		    s[3]=req.getParameter("address");
		    s[4]=req.getParameter("location");
		    s[5]=req.getParameter("university_id");
		    s[6]=req.getParameter("active");
		    if(s[6].equals("0"))
		    {
		    int status = table_relation(conn,req,s[0], "Institution");
		    if(status == 1)
	        {
	        	res.sendRedirect("./master_tables?action=show_institution_master&tab_name=institution_master&errmsg=This_record_is_used_by_department_master.");	
	        	return;
	        }
		    }
		    Statement theStatement=conn.createStatement();
	        theStatement.executeUpdate("update institution_master set name=\'"+s[1]+"\',short_name=\'"+s[2]+"\',address=\'"+s[3]+"\',location=\'"+s[4]+"\',university_id="+s[5]+",active_yes_no="+s[6]+" where id="+s[0]);
	      	theStatement.close();//Close statement
	  	    res.sendRedirect("./master_tables?action=show_institution_master&tab_name=institution_master");
	        }catch(java.sql.SQLIntegrityConstraintViolationException e) {  
	             System.out.println ("Caught java.sql.SQLIntegrityConstraintViolationException") ;             
	             res.sendRedirect("./master_tables?action=show_institution_master&tab_name=institution_master&errmsg=Same_Record_Already_Exists.");
		      }      
		      catch (java.sql.SQLException e) {  
		             System.out.println   
		            ("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " +   
		                                      e.getMessage() ) ; 
		      } 
		      catch(Exception e){	    	
		    	  res.sendRedirect("./master_tables?action=show_institution_master&tab_name=institution_master&errmsg=Eorr_in_Save"); 
	  		      e.printStackTrace();			    
			     }
    }//end of function save_edit_institution_master.
    private void add_new_institution_master(Connection conn,PrintWriter out,HttpServletRequest req,mlObj ml){
    	try{
    		out.println("<TABLE border=\"1\" align=center class=\"ListTable\" cellspacing=0 width=\"100%\">");
	        out.println("<TR><TD align=center colspan=\"2\" class=\"thead\" >"+ml.getValue("add_new")+"</TD></TR>");
	        out.println("<TR><TD align=center colspan=\"2\" class=\"thead\" >"+ml.getValue("institution_master")+"</TD></TR>");
	        out.println("<TR>");
	        out.println("<TD width=\"30%\" class=\"ListRow\" >"+ml.getValue("name")+"<label  class=\"mandatory\">*</label></TD>");
	        out.println("<TD class=\"ListRow\" width=\"70%\" ><input type=\"text\" size=\"50\" name=\"institution_name\" value=\"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR><TR>");
	        out.println("<TD width=\"30%\" class=\"ListRow\" >"+ml.getValue("short_name")+"<label  class=\"mandatory\">*</label></TD>");
	        out.println("<TD class=\"ListRow\" width=\"70%\" ><input type=\"text\" size=\"50\" name=\"short_name\" value=\"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR><TR>");
	        out.println("<TD width=\"30%\" class=\"ListRow\" >"+ml.getValue("address")+"<label  class=\"mandatory\">*</label></TD>");
	        out.println("<TD class=\"ListRow\" width=\"70%\" ><input type=\"text\" size=\"50\" name=\"address\" value=\"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR><TR>");
	        out.println("<TD width=\"30%\" class=\"ListRow\" >"+ml.getValue("location")+"<label  class=\"mandatory\">*</label></TD>");
	        out.println("<TD class=\"ListRow\" width=\"70%\" ><input type=\"text\" size=\"50\" name=\"location\" value=\"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR><TR>");
	        out.println("<TD class=\"ListRow\" width=\"30%\" >"+ml.getValue("university")+"<label  class=\"mandatory\">*</label></TD><TD class=\"ListRow\" width=\"70%\" >");
	        Statement theStatement=conn.createStatement();
	        ResultSet theResult=theStatement.executeQuery("select id,name from university_master where active_yes_no=1");
	        out.println("<select name=\"university_id\" onchange=\"enable_save_button();\" >");
	        String at="";
	        while(theResult.next()){
	        	at=Integer.toString(theResult.getInt("id"));
	        	out.println("<option value=\""+at+"\" >"+theResult.getString("name")+"</option>");
	        }
	        theResult.close();//Close the result set
	        theStatement.close();//Close statement
	        out.println("</select></TD></TR><TR>");
	        out.println("<TD class=\"ListRow\" width=\"30%\" >"+ml.getValue("active")+"<label  class=\"mandatory\">*</label></TD><TD class=\"ListRow\" width=\"70%\" >");
	        out.println("<select name=\"active\" onchange=\"enable_save_button();\" ><option value=\"1\" >"+ml.getValue("yes")+"</option><option value=\"0\" >"+ml.getValue("no")+"</option></select>");
	        out.println("</TD></TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"ListRow\" colspan=\"2\" align=\"center\" >" +
	        		"<input type=\"button\" value=\""+ml.getValue("save")+"\" name=\"save\" disabled=true  onclick=\"save_add_new_institution_master();\" >" +
	        		"&nbsp;<input type=\"button\" value=\""+ml.getValue("back")+"\" name=\"back\" onclick=\"back_institution_master();\" >" +
	        		"</TD>");
	        out.println("</TR>");
	       	out.println("</TABLE>");
	     }catch(Exception e){
		   e.printStackTrace();//Print trapped error.
	     }
    }//end of function add_new_institution_master.
    private void save_add_new_institution_master(Connection conn,HttpServletRequest req,HttpServletResponse res) throws IOException{
    	try{
    		String s[]=new String[6];
			s[0]=req.getParameter("institution_name");
		    s[1]=req.getParameter("short_name");
		    s[2]=req.getParameter("address");
		    s[3]=req.getParameter("location");
		    s[4]=req.getParameter("university_id");
		    s[5]=req.getParameter("active");
		    Statement theStatement=conn.createStatement();
	        theStatement.executeUpdate("insert into institution_master(name,short_name,address,location,university_id,active_yes_no) values(\'"+s[0]+"\',\'"+s[1]+"\',\'"+s[2]+"\',\'"+s[3]+"\',"+s[4]+","+s[5]+")");
	      	theStatement.close();//Close statement
	  	    res.sendRedirect("./master_tables?action=show_institution_master&tab_name=institution_master");
	       }catch(java.sql.SQLIntegrityConstraintViolationException e) {  
	             System.out.println ("Caught java.sql.SQLIntegrityConstraintViolationException") ;             
	             res.sendRedirect("./master_tables?action=add_new_institution_master&tab_name=institution_master&errmsg=Same_Record_Already_Exists.");
		      }      
		      catch (java.sql.SQLException e) {  
		             System.out.println   
		            ("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " +   
		                                      e.getMessage() ) ; 
		      } 
		      catch(Exception e){	    	
		    	  res.sendRedirect("./master_tables?action=add_new_institution_master&tab_name=institution_master&errmsg=Eorr_in_Save"); 
	  		      e.printStackTrace();			    
			     }
    }//end of function save_add_new_institution_master.
    private void show_institution_department_master(Connection conn,HttpServletRequest req,PrintWriter out,mlObj ml){
    	try{
    		String institution=req.getParameter("institution_id");if(institution==null){institution="";}
    		Statement theStatement=conn.createStatement();
		    ResultSet theResult=theStatement.executeQuery("select id,name from institution_master where active_yes_no=1");
		    theResult.last();int f=0;int i=theResult.getRow();String s[]=new String[i];String id[]=new String[i];i=0;
		    out.println(ml.getValue("intitution")+"<label  class=\"mandatory\">*</label>&nbsp;&nbsp;<select name=\"institution_combo\" id=\"cb_institution\" onchange=\"institution_department_master_combo_change(this.value);\" >");
            theResult.beforeFirst();
		    while(theResult.next()){
		          s[i]=theResult.getString("name");
		          id[i]=Integer.toString(theResult.getInt("id"));
		          if(!institution.equals("") && institution.equals(id[i])){
		        	     out.println("<option value=\""+id[i]+"\" selected=\"selected\" >"+s[i]+"</option>");f=i;
		        	}else{
		        		 out.println("<option value=\""+id[i]+"\" >"+s[i]+"</option>");
		        	}
		        	i++;
		    }
		    out.println("</select>&nbsp;<INPUT TYPE=\"button\" NAME=\"institution_button\" value=\""+ml.getValue("add_new")+"\" onclick=\"add_new_institution_department_master_button_click();\" /><br><br>");
		    out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"institution_id\" id=\"institution_id\" value=\""+id[f]+"\" />");
		    out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"institution_name\" id=\"institution_name\" value=\""+s[f]+"\" />");
		    out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"id\" id=\"id\" value=\"\" />");
		    out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"department_name\" id=\"department_name\" value=\"\" />");
		    out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"department_id\" id=\"department_id\" value=\"\" />");
		    out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"active\" id=\"active\" value=\"\" />");
    	    out.println("<TABLE border=\"1\" align=center class=\"ListTable\" cellspacing=0 width=\"100%\">");
	        out.println("<TR><TD align=center colspan=\"3\" class=\"ListHeader\" >"+ml.getValue("intitution")+s[f]+"</TD></TR>");
	        out.println("<TR>");
	        out.println("<TD align=center class=\"ListHeader\" >"+ml.getValue("department")+"</TD>");
	        out.println("<TD align=center class=\"ListHeader\">"+ml.getValue("active")+"</TD>");
	        out.println("<TD align=center class=\"ListHeader\">"+ml.getValue("edit")+"</TD>");
	        out.println("</TR>");
	        theResult=theStatement.executeQuery("select department_master.department_id,department_master.id,department_master.active_yes_no,general_master.fld_value as department from department_master,general_master where department_master.institution_id="+id[f]+" and department_master.department_id=general_master.id");
	        String str="";int x=0;String d[]=new String[4];
	        while(theResult.next()){
	        	d[0]=theResult.getString("department");
	        	d[1]=theResult.getString("active_yes_no");
	        	d[2]=Integer.toString(theResult.getInt("id"));
	        	d[3]=Integer.toString(theResult.getInt("department_id"));
		        if(d[1].equals("1")){str="<span style=\"color:green\">"+ml.getValue("yes")+"</span>";}else{str="<span style=\"color:red\">"+ml.getValue("no")+"</span>";}
	        	out.println("<TR>");
		        out.println("<TD  class=\"ListRow\" >"+d[0]+"</TD>");
		        out.println("<TD align=center class=\"ListRow\" >"+str+"</TD>");
		        out.println("<TD align=center class=\"ListRow\" >" +
		        		"<INPUT TYPE=\"HIDDEN\" id=\"id_"+x+"\" value=\""+d[2]+"\" />" +
		                "<INPUT TYPE=\"HIDDEN\" id=\"department_name_"+x+"\" value=\""+d[0]+"\" />" +
		                "<INPUT TYPE=\"HIDDEN\" id=\"department_id_"+x+"\" value=\""+d[3]+"\" />" +
		                "<INPUT TYPE=\"HIDDEN\" id=\"active_"+x+"\" value=\""+d[1]+"\" />" +
		               	"<input type=\"button\" name=\"button_"+x+"\" value=\""+ml.getValue("edit")+"\" onclick=\"edit_institution_department_master(this.name);\" />" +
		        		"</TD>");
		        out.println("</TR>");x=x+1;
	        }
	       	out.println("</TABLE>");
	       	theResult.close();//Close the result set
	        theStatement.close();//Close statement
	  	 
	     }catch(Exception e){
		   e.printStackTrace();//Print trapped error.
	     }
    }//end of function show_institution_department_master.
    private void edit_institution_department_master(Connection conn,HttpServletRequest req,PrintWriter out,mlObj ml){
    	try{String p[]=new String[6];
    		p[0]=req.getParameter("institution_id");
    		p[1]=req.getParameter("institution_name");
    		p[2]=req.getParameter("id");
    		p[3]=req.getParameter("department_name");
    		p[4]=req.getParameter("department_id");
    		p[5]=req.getParameter("active");
    		out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"institution_id\" id=\"institution_id\" value=\""+p[0]+"\" />");
		    out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"institution_name\" id=\"institution_name\" value=\""+p[1]+"\" />");
		    out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"id\" id=\"id\" value=\""+p[2]+"\" />");
		    out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"department_name\" id=\"department_name\" value=\""+p[3]+"\" />");
		    out.println("<TABLE border=\"1\" align=center class=\"ListTable\" cellspacing=0 width=\"100%\">");
    		out.println("<TR><TD align=center colspan=\"2\" class=\"thead\" >"+ml.getValue("edit")+"</TD></TR>");
	        out.println("<TR><TD align=center colspan=\"2\" class=\"thead\" >"+ml.getValue("intitution")+p[1]+"</TD></TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"ListRow\" >"+ml.getValue("department")+"<label  class=\"mandatory\">*</label></TD>");
	        out.println("<TD class=\"ListRow\"><select name=\"department_id\" onchange=\"enable_save_button();\" >");
	        Statement theStatement=conn.createStatement();
	        ResultSet theResult=theStatement.executeQuery("select id,fld_value from general_master where category=\'Department\'and active_yes_no=1");
	        String at="";
	        while(theResult.next()){
	        	at=Integer.toString(theResult.getInt("id"));
	        	if(at.equals(p[4])){
	        	     out.println("<option value=\""+at+"\" selected=\"selected\" >"+theResult.getString("fld_value")+"</option>");
	        	}else{
	        		 out.println("<option value=\""+at+"\" >"+theResult.getString("fld_value")+"</option>");
	        	}
	        }
	        theResult.close();//Close the result set
	        theStatement.close();//Close statement
	        out.println("</select></TD></TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"ListRow\" >"+ml.getValue("active")+"<label  class=\"mandatory\">*</label></TD>");
	        out.println("<TD class=\"ListRow\">");
	        if(p[5].equals("1")){
	        	out.println("<select name=\"active\" onchange=\"enable_save_button();\" ><option value=\"1\" >"+ml.getValue("yes")+"</option><option value=\"0\" >"+ml.getValue("no")+"</option></select>");
	        }else if(p[5].equals("0")){
	        	out.println("<select name=\"active\" onchange=\"enable_save_button();\" ><option value=\"0\" >"+ml.getValue("no")+"</option><option value=\"1\" >"+ml.getValue("yes")+"</option></select>");
	        }
	        out.println("</TD></TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"ListRow\" colspan=\"2\" align=\"center\" >" +
	        		"<input type=\"button\" value=\""+ml.getValue("save")+"\" name=\"save\" disabled=true  onclick=\"save_edit_institution_department_master();\" >" +
	        		"&nbsp;<input type=\"button\" value=\""+ml.getValue("back")+"\" name=\"back\" onclick=\"back_institution_department_master();\" >" +
	        		"</TD>");
	        out.println("</TR>");
	       	out.println("</TABLE>");
	       		  	 
	     }catch(Exception e){
		   e.printStackTrace();//Print trapped error.
	     }
    }//end of function edit_institution_department_master.
    private void save_edit_institution_department_master(Connection conn,HttpServletRequest req,HttpServletResponse res) throws IOException{
    	String s[]=new String[4];
    	try{    		
			s[0]=req.getParameter("id");
		    s[1]=req.getParameter("department_id");
		    s[2]=req.getParameter("active");
		    s[3]=req.getParameter("institution_id");
		    if(s[2].equals("0"))
		    {
		    int status = table_relation(conn,req,s[0], "Department");
		    if(status == 1)
	        {
	        	res.sendRedirect("./master_tables?action=show_institution_department_master&tab_name=institution_department_master&errmsg=This_record_is_related_with_some_another_records.");	
	        	return;
	        }
		    }
		    Statement theStatement=conn.createStatement();
	        theStatement.executeUpdate("update department_master set department_id=\'"+s[1]+"\',active_yes_no="+s[2]+" where id="+s[0]);
	      	theStatement.close();//Close statement
	  	    res.sendRedirect("./master_tables?action=show_institution_department_master&tab_name=institution_department_master&institution_id="+s[3]);
	       }catch(java.sql.SQLIntegrityConstraintViolationException e) {  
	             System.out.println ("Caught java.sql.SQLIntegrityConstraintViolationException") ;             
	             res.sendRedirect("./master_tables?action=show_institution_department_master&tab_name=institution_department_master&institution_id="+s[3]+"&errmsg=Same_Record_Already_Exists.");
		      }      
		      catch (java.sql.SQLException e) {  
		             System.out.println   
		            ("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " +   
		                                      e.getMessage() ) ; 
		      } 
		      catch(Exception e){	    	
		    	  res.sendRedirect("./master_tables?action=show_institution_department_master&tab_name=institution_department_master&institution_id="+s[3]+"&errmsg=Eorr_in_Save"); 
	  		      e.printStackTrace();			    
			     }
    }//end of function save_edit_institution_department_master.
    private void add_new_institution_department_master(Connection conn,HttpServletRequest req,PrintWriter out,mlObj ml){
    	try{String p[]=new String[2];
    		p[0]=req.getParameter("institution_id");
    		p[1]=req.getParameter("institution_name");
    		out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"institution_id\" id=\"institution_id\" value=\""+p[0]+"\" />");
		    out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"institution_name\" id=\"institution_name\" value=\""+p[1]+"\" />");
		    out.println("<TABLE border=\"1\" align=center class=\"ListTable\" cellspacing=0 width=\"100%\">");
    		out.println("<TR><TD align=center colspan=\"2\" class=\"thead\" >"+ml.getValue("add_new")+"</TD></TR>");
	        out.println("<TR><TD align=center colspan=\"2\" class=\"thead\" >"+ml.getValue("intitution")+p[1]+"</TD></TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"ListRow\" >"+ml.getValue("department")+"<label  class=\"mandatory\">*</label></TD>");
	        out.println("<TD class=\"ListRow\"><select name=\"department_id\" >");
	        Statement theStatement=conn.createStatement();
	        ResultSet theResult=theStatement.executeQuery("select id,fld_value from general_master where category=\'Department\' and active_yes_no=1");
	        String at="";
	        while(theResult.next()){
	        	at=Integer.toString(theResult.getInt("id"));
	        	out.println("<option value=\""+at+"\" >"+theResult.getString("fld_value")+"</option>");
	        }
	        theResult.close();//Close the result set
	        theStatement.close();//Close statement
	        out.println("</select></TD></TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"ListRow\" >"+ml.getValue("active")+"<label  class=\"mandatory\">*</label></TD>");
	        out.println("<TD class=\"ListRow\">");
	       	out.println("<select name=\"active\" ><option value=\"1\" >"+ml.getValue("yes")+"</option><option value=\"0\" >"+ml.getValue("no")+"</option></select>");
	        out.println("</TD></TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"ListRow\" colspan=\"2\" align=\"center\" >" +
	        		"<input type=\"button\" value=\""+ml.getValue("save")+"\" name=\"save\" onclick=\"save_add_new_institution_department_master();\" >" +
	        		"&nbsp;<input type=\"button\" value=\""+ml.getValue("back")+"\" name=\"back\" onclick=\"back_institution_department_master();\" >" +
	        		"</TD>");
	        out.println("</TR>");
	       	out.println("</TABLE>");
	       		  	 
	     }catch(Exception e){
		   e.printStackTrace();//Print trapped error.
	     }
    }//end of function add_new_institution_department_master.
    private void save_add_new_institution_department_master(Connection conn,HttpServletRequest req,HttpServletResponse res) throws IOException{
    	String s[]=new String[4];
    	try{    		
			s[0]=req.getParameter("institution_id");
		    s[1]=req.getParameter("department_id");
		    s[2]=req.getParameter("active");
		    s[3]=req.getParameter("institution_name");
		    Statement theStatement=conn.createStatement();
	        theStatement.executeUpdate("insert into department_master(institution_id,department_id,active_yes_no) values("+s[0]+","+s[1]+","+s[2]+")");
	      	theStatement.close();//Close statement
	  	    res.sendRedirect("./master_tables?action=show_institution_department_master&tab_name=institution_department_master&institution_id="+s[0]);
	       }catch(java.sql.SQLIntegrityConstraintViolationException e) {  
	             System.out.println ("Caught java.sql.SQLIntegrityConstraintViolationException") ;             
	             res.sendRedirect("./master_tables?action=add_new_institution_department_master&tab_name=institution_department_master&institution_id="+s[0]+"&institution_name="+ s[3] +"&errmsg=Same_Record_Already_Exists.");
		      }      
		      catch (java.sql.SQLException e) {  
		             System.out.println   
		            ("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " +   
		                                      e.getMessage() ) ; 
		      } 
		      catch(Exception e){	    	
		    	  res.sendRedirect("./master_tables?action=add_new_institution_department_master&tab_name=institution_department_master&institution_id="+s[0]+"&errmsg=Eorr_in_Save"); 
	  		      e.printStackTrace();			    
			     }
    }//end of function save_add_new_institution_department_master.
}