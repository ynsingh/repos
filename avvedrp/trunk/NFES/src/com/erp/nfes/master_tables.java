package com.erp.nfes;

import java.io.*;
import java.util.Enumeration;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.net.*;


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
	  res.setContentType("text/html");
	  PrintWriter out = res.getWriter();
	  action=req.getParameter("action");
	  tab_name=req.getParameter("tab_name");
	  	  
	try{
        ConnectDB conObj=new ConnectDB();
		conn = conObj.getMysqlConnection();
		out.println("<HTML><HEAD><TITLE>Courses Taught</TITLE>");
  	    out.println("<link href=\"./css/oiostyles.css\" rel=\"stylesheet\" type=\"text/css\"/>");
  	    out.println("<script type=\"text/javascript\" src=\"./js/master_tables.js\" ></script>");
  	    out.println("</HEAD><BODY CLASS=\"bodystyle\">");
  	    out.println("<form method=\"post\" action=\"./master_tables\" NAME=\"myForm\" ID=\"mt01\" >");
  	    out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"action\" value=\""+action+"\" id=\"action\" />");
  	    out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"tab_name\" value=\""+tab_name+"\" id=\"tab_name\" />");
  	    out.println("<div class=\"listdiv_toggle\"><BR>");
  	    if(tab_name.equals("general_master")){
 		   if(action.equals("show_general_master")){	
 			  show_general_master(conn,out,req);
 		   }else if(action.equals("edit_general_master")){
 			  edit_general_master(out,req);
 		   }else if(action.equals("save_edit_general_master")){
 			  save_edit_general_master(conn,req,res);
 		   }else if(action.equals("add_new_general_master")){
 			  add_new_general_master(out,req);
 		   }else if(action.equals("save_add_new_general_master")){
 			  save_add_new_general_master(conn,req,res);
 		   }
 	    }else if(tab_name.equals("principal_investigator_master")){
 	       if(action.equals("show_principal_investigator_master")){	
 	 			  show_principal_investigator_master(conn,out,req);
 	 	   }else if(action.equals("edit_principal_investigator_master")){	
 	 			  edit_principal_investigator_master(conn,out,req);
 	 	   }else if(action.equals("save_edit_principal_investigator_master")){	
 	 			  save_edit_principal_investigator_master(conn,req,res);
 	 	   }else if(action.equals("add_new_principal_investigator_master")){	
 	 			  add_new_principal_investigator_master(conn,out,req);
 	 	   }else if(action.equals("save_add_new_principal_investigator_master")){	
 	 			  save_add_new_principal_investigator_master(conn,req,res);
 	 	   }
 	    }else if(tab_name.equals("courses_taught_master")){
 	       if(action.equals("show_courses_taught_master")){	
	 			  show_courses_taught_master(conn,out,req);
	 	   }else if(action.equals("edit_courses_taught_master")){	
	 			  edit_courses_taught_master(conn,out,req);
	 	   }else if(action.equals("save_edit_courses_taught_master")){	
	 		  save_edit_courses_taught_master(conn,req,res);
	 	   }else if(action.equals("add_new_courses_taught_master")){	
	 		  add_new_courses_taught_master(conn,out,req);
	 	   }else if(action.equals("save_add_new_courses_taught_master")){	
	 		  save_add_new_courses_taught_master(conn,req,res);
	 	   }
 	    }else if(tab_name.equals("university_master")){
 	       if(action.equals("show_university_master")){	
	 		  show_university_master(conn,out);
	 	   }else if(action.equals("edit_university_master")){	
	 		  edit_university_master(out,req);
	 	   }else if(action.equals("save_edit_university_master")){	
	 		  save_edit_university_master(conn,req,res);
	 	   }else if(action.equals("add_new_university_master")){	
	 		  add_new_university_master(out,req);
	 	   }else if(action.equals("save_add_new_university_master")){	
	 		  save_add_new_university_master(conn,req,res);
	 	   }
 	    }else if(tab_name.equals("institution_master")){
  	       if(action.equals("show_institution_master")){	
 	 	   	  show_institution_master(conn,out);
 	 	   }else if(action.equals("edit_institution_master")){	
 	 		  edit_institution_master(conn,out,req);
 	 	   }else if(action.equals("save_edit_institution_master")){	
 	 		  save_edit_institution_master(conn,req,res);
 	 	   }else if(action.equals("add_new_institution_master")){	
 	 		  add_new_institution_master(conn,out,req);
 	 	   }else if(action.equals("save_add_new_institution_master")){	
 	 		  save_add_new_institution_master(conn,req,res);
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

	private void show_general_master(Connection conn, PrintWriter out,HttpServletRequest req){
		try{
			    String category=req.getParameter("category");
		        Statement theStatement=conn.createStatement();
		        ResultSet theResult=theStatement.executeQuery("select distinct category from general_master order by category");
		        theResult.last();String s[]=new String[theResult.getRow()];int i=0;
		        out.println("Category:&nbsp;&nbsp;<select name=\"category_combo\" id=\"cb01\" onchange=\"general_master_combo_change(this.value);\" >");
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
		        out.println("</select>&nbsp;<input type=\"button\" name\"add_new\" value=\"Add New\" onclick=\"general_master_button_change();\" /><br><br>");
		        theResult=theStatement.executeQuery("select fld_value,active_yes_no from general_master where category=\'"+category+"\'");
		        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"category\" value=\""+category+"\" id=\"category\" />");
		        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"fld_value\" value=\"\" id=\"fld_value\" />");
		        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"active_yes_no\" value=\"\" id=\"active_yes_no\" />");
		        out.println("<TABLE border=\"1\" align=center class=\"ListTable\" cellspacing=0 width=\"100%\">");
		        out.println("<TR><TD align=center colspan=\"3\" class=\"thead\" >Category: "+category+"</TD></TR>");
		        out.println("<TR>");
		        out.println("<TD align=center class=\"thead\" >Value</TD>");
		        out.println("<TD align=center class=\"thead\">Active</TD>");
		        out.println("<TD align=center class=\"thead\">Edit</TD>");
		        out.println("</TR>");
		        String at="";String fv="";String str="";int x=0;
		  	   	while(theResult.next()) //Fetch all the records and print in table
				{
				    out.println();
		    		out.println("<TR>");
		    		fv=theResult.getString("fld_value");
		    		out.println("<TD class=\"tabBG4\" >" +fv+ "</TD>");
		    		at=theResult.getString("active_yes_no");
		    		if(at.equals("1")){str="<span style=\"color:green\">Yes</span>";at="Yes";}else{str="<span style=\"color:red\">No</span>";at="No";}
					out.println("<TD align=center class=\"tabBG4\" >" +str+ "</TD>");
					out.println("<TD align=center class=\"tabBG4\" >" +
							"<INPUT TYPE=\"HIDDEN\" id=\"fld_value_"+x+"\" value=\""+fv+"\" />"+
							"<INPUT TYPE=\"HIDDEN\" id=\"active_yes_no_"+x+"\" value=\""+at+"\" />"+
							"<input type=\"button\" name=\"button_"+x+"\" value=\"Edit\" onclick=\"edit_general_master(this.name);\" />" +
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
	private void edit_general_master(PrintWriter out,HttpServletRequest req){
		try{
		    String category=req.getParameter("category");
		    String fld_value=req.getParameter("fld_value");
		    String active_yes_no=req.getParameter("active_yes_no");
	        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"category\" value=\""+category+"\" id=\"category\" />");
	        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"fld_value\" value=\""+fld_value+"\" id=\"fld_value\" />");
	        out.println("<TABLE border=\"1\" align=center class=\"ListTable\" cellspacing=0 width=\"100%\">");
	        out.println("<TR><TD align=center colspan=\"2\" class=\"thead\" >Edit</TD></TR>");
	        out.println("<TR><TD align=center colspan=\"2\" class=\"thead\" >Category: "+category+"</TD></TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"tabBG4\" width=\"30%\" >Value</TD>");
	        out.println("<TD class=\"tabBG4\" width=\"70%\" ><input type=\"text\" name=\"new_fld_value\" value=\""+fld_value+"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"tabBG4\" width=\"30%\" >Active</TD>");
	        out.println("<TD class=\"tabBG4\" width=\"70%\" >");
	        if(active_yes_no.equals("Yes")){
	        	out.println("<select name=\"active_yes_no\" onchange=\"enable_save_button();\" ><option value=\"1\" >Yes</option><option value=\"0\" >No</option></select>");
	        }else if(active_yes_no.equals("No")){
	        	out.println("<select name=\"active_yes_no\" onchange=\"enable_save_button();\" ><option value=\"0\" >No</option><option value=\"1\" >Yes</option></select>");
	        }
	        out.println("</TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"tabBG4\" colspan=\"2\" align=\"center\" >" +
	        		"<input type=\"button\" value=\"Save\" name=\"save\" disabled=true  onclick=\"save_edit_general_master();\" >" +
	        		"&nbsp;<input type=\"button\" value=\"Back\" name=\"back\" onclick=\"back_general_master();\" >" +
	        		"</TD>");
	        out.println("</TR>");
	        out.println("</TABLE>");
	  	   	    
	     }catch(Exception e){
		  e.printStackTrace();//Print trapped error.
	     }
	}//end of function edit_general_master.
	private void save_edit_general_master(Connection conn,HttpServletRequest req,HttpServletResponse res){
		try{
		    String category=req.getParameter("category");
		    String fld_value=req.getParameter("fld_value");
		    String new_fld_value=req.getParameter("new_fld_value");
		    String active_yes_no=req.getParameter("active_yes_no");
	        Statement theStatement=conn.createStatement();
	        theStatement.executeUpdate("update general_master set fld_value=\'"+new_fld_value+"\',active_yes_no=\'"+active_yes_no+"\' where category=\'"+category+"\' and fld_value=\'"+fld_value+"\'");
	      	theStatement.close();//Close statement
	  	    res.sendRedirect("./master_tables?category="+category+"&action=show_general_master&tab_name=general_master");
	     }catch(Exception e){
		    e.printStackTrace();
	     }
	}//end of function save_edit_general_master.
	private void add_new_general_master(PrintWriter out,HttpServletRequest req){
		try{
		    String category=req.getParameter("category");
		    out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"category\" value=\""+category+"\" id=\"category\" />");
	        out.println("<TABLE border=\"0\" align=center class=\"ListTable\" cellspacing=0 width=\"100%\">");
	        out.println("<TR><TD align=center colspan=\"2\" class=\"thead\" >Add New</TD></TR>");
	        out.println("<TR><TD align=center colspan=\"2\" class=\"thead\" >Category: "+category+"</TD></TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"tabBG4\" width=\"30%\" >Value</TD>");
	        out.println("<TD class=\"tabBG4\" width=\"70%\" ><input name=\"fld_value\" type=\"text\" value=\"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\"  /></TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"tabBG4\" width=\"30%\" >Active</TD>");
	        out.println("<TD class=\"tabBG4\" width=\"70%\" >");
	        out.println("<select name=\"active_yes_no\" onchange=\"enable_save_button();\" ><option value=\"1\" >Yes</option><option value=\"0\" >No</option></select>");
	        out.println("</TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"tabBG4\" colspan=\"2\" align=\"center\" >" +
	        		"<input type=\"button\" value=\"Save\" name=\"save\" disabled=true onclick=\"save_add_new_general_master();\" >" +
	        		"&nbsp;<input type=\"button\" value=\"Back\" name=\"back\" onclick=\"back_general_master();\" >" +
	        		"</TD>");
	        out.println("</TR>");
	        out.println("</TABLE>");
	  	   	    
	     }catch(Exception e){
		   out.println(e.getMessage());//Print trapped error.
	     }
	}//end of function add_new_general_master.
	private void save_add_new_general_master(Connection conn,HttpServletRequest req,HttpServletResponse res){
		try{
		    String category=req.getParameter("category");
		    String fld_value=req.getParameter("fld_value");
		    String active_yes_no=req.getParameter("active_yes_no");
	        Statement theStatement=conn.createStatement();
	        theStatement.executeUpdate("insert into general_master(category,fld_value,active_yes_no) values('"+category+"','"+fld_value+"','"+active_yes_no+"')");
	      	theStatement.close();//Close statement
	  	    res.sendRedirect("./master_tables?category="+category+"&action=show_general_master&tab_name=general_master");
	     }catch(Exception e){
		    e.printStackTrace();
	     }
	}//end of function save_add_new_general_master.
	private void show_principal_investigator_master(Connection conn, PrintWriter out,HttpServletRequest req){
		try{
			    out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"pi_name\" value=\"\" />");
		        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"department\" value=\"\" />");
		        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"designation\" value=\"\" />");
		        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"email\" value=\"\" />");
		        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"address\" value=\"\" />");
		        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"active\" value=\"\" />");
		        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"id\" value=\"\" />");
		        out.println("<INPUT TYPE=\"button\" NAME=\"add\" value=\"Add New\" onclick=\"add_new_principal_investigator_master();\" /><br><br>");
			    out.println("<TABLE border=\"1\" align=center class=\"ListTable\" cellspacing=0 width=\"100%\">");
		        out.println("<TR><TD align=center colspan=\"7\" class=\"thead\" >Principal Investigator Master</TD></TR>");
		        out.println("<TR>");
		        out.println("<TD align=center class=\"thead\" >Name</TD>");
		        out.println("<TD align=center class=\"thead\">Department</TD>");
		        out.println("<TD align=center class=\"thead\" >Designation</TD>");
		        out.println("<TD align=center class=\"thead\">Email</TD>");
		        out.println("<TD align=center class=\"thead\" >Address</TD>");
		        out.println("<TD align=center class=\"thead\">Active</TD>");
		        out.println("<TD align=center class=\"thead\">Edit</TD>");
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
			        if(s[5].equals("1")){str="<span style=\"color:green\">Yes</span>";s[5]="Yes";}else{str="<span style=\"color:red\">No</span>";s[5]="No";}
		        	out.println("<TR>");
			        out.println("<TD  class=\"tabBG4\" >"+s[0]+"</TD>");
			        out.println("<TD align=center class=\"tabBG4\" >"+s[1]+"</TD>");
			        out.println("<TD align=center class=\"tabBG4\" >"+s[2]+"</TD>");
			        out.println("<TD align=center class=\"tabBG4\" >"+s[3]+"</TD>");
			        out.println("<TD  class=\"tabBG4\" >"+s[4]+"</TD>");
			        out.println("<TD align=center class=\"tabBG4\" >"+str+"</TD>");
			        out.println("<TD align=center class=\"tabBG4\" >" +
			        		"<INPUT TYPE=\"HIDDEN\" id=\"pi_name_"+x+"\" value=\""+s[0]+"\" />" +
			                "<INPUT TYPE=\"HIDDEN\" id=\"department_"+x+"\" value=\""+s[1]+"\" />" +
			                "<INPUT TYPE=\"HIDDEN\" id=\"designation_"+x+"\" value=\""+s[2]+"\" />" +
			                "<INPUT TYPE=\"HIDDEN\" id=\"email_"+x+"\" value=\""+s[3]+"\" />" +
			                "<INPUT TYPE=\"HIDDEN\" id=\"address_"+x+"\" value=\""+s[4]+"\" />" +
			                "<INPUT TYPE=\"HIDDEN\" id=\"active_"+x+"\" value=\""+s[5]+"\" />" +
			                "<INPUT TYPE=\"HIDDEN\" id=\"id_"+x+"\" value=\""+s[6]+"\" />" +
			        		"<input type=\"button\" name=\"button_"+x+"\" value=\"Edit\" onclick=\"edit_principal_investigator_master(this.name);\" />" +
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
	private void edit_principal_investigator_master(Connection conn,PrintWriter out,HttpServletRequest req){
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
	        out.println("<TR><TD align=center colspan=\"2\" class=\"thead\" >Edit</TD></TR>");
	        out.println("<TR><TD align=center colspan=\"2\" class=\"thead\" >Principal Investigator Master</TD></TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"tabBG4\" width=\"30%\" >Name</TD>");
	        out.println("<TD class=\"tabBG4\" width=\"70%\" ><input type=\"text\" name=\"pi_name\" value=\""+s[0]+"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"tabBG4\" width=\"30%\" >Department</TD>");
	        out.println("<TD class=\"tabBG4\" width=\"70%\" >");
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
	        out.println("<TD class=\"tabBG4\" width=\"30%\" >Designation</TD>");
	        out.println("<TD class=\"tabBG4\" width=\"70%\" ><input type=\"text\" name=\"designation\" value=\""+s[2]+"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"tabBG4\" width=\"30%\" >Email</TD>");
	        out.println("<TD class=\"tabBG4\" width=\"70%\" ><input type=\"text\" name=\"email\" value=\""+s[3]+"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"tabBG4\" width=\"30%\" >Address</TD>");
	        out.println("<TD class=\"tabBG4\" width=\"70%\" ><input type=\"text\" name=\"address\" value=\""+s[4]+"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"tabBG4\" width=\"30%\" >Active</TD>");
	        out.println("<TD class=\"tabBG4\" width=\"70%\" >");
	        if(s[5].equals("Yes")){
	        	out.println("<select name=\"active\" onchange=\"enable_save_button();\" ><option value=\"1\" >Yes</option><option value=\"0\" >No</option></select>");
	        }else if(s[5].equals("No")){
	        	out.println("<select name=\"active\" onchange=\"enable_save_button();\" ><option value=\"0\" >No</option><option value=\"1\" >Yes</option></select>");
	        }
	        out.println("</TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"tabBG4\" colspan=\"2\" align=\"center\" >" +
	        		"<input type=\"button\" value=\"Save\" name=\"save\" disabled=true  onclick=\"save_edit_principal_investigator_master();\" >" +
	        		"&nbsp;<input type=\"button\" value=\"Back\" name=\"back\" onclick=\"back_principal_investigator_master();\" >" +
	        		"</TD>");
	        out.println("</TR>");
	        out.println("</TABLE>");
	  	   	    
	     }catch(Exception e){
		   e.printStackTrace();//Print trapped error.
	     }
	}//end of function edit_principal_investigator_master.
	private void save_edit_principal_investigator_master(Connection conn,HttpServletRequest req,HttpServletResponse res){
		try{
			String s[]=new String[7];
			s[0]=req.getParameter("pi_name");
		    s[1]=req.getParameter("department");
		    s[2]=req.getParameter("designation");
		    s[3]=req.getParameter("email");
		    s[4]=req.getParameter("address");
		    s[5]=req.getParameter("active");
		    s[6]=req.getParameter("id");
	        Statement theStatement=conn.createStatement();
	        theStatement.executeUpdate("update investigator_master set name=\'"+s[0]+"\',department_id="+s[1]+",designation=\'"+s[2]+"\',email=\'"+s[3]+"\',address=\'"+s[4]+"\',active_yes_no="+s[5]+" where id="+s[6]);
	      	theStatement.close();//Close statement
	  	    res.sendRedirect("./master_tables?action=show_principal_investigator_master&tab_name=principal_investigator_master");
	     }catch(Exception e){
		    e.printStackTrace();
	     }
	}//end of function save_edit_principal_investigator_master.
	private void add_new_principal_investigator_master(Connection conn,PrintWriter out,HttpServletRequest req){
		try{
			out.println("<TABLE border=\"1\" align=center class=\"ListTable\" cellspacing=0 width=\"100%\">");
	        out.println("<TR><TD align=center colspan=\"2\" class=\"thead\" >Add New</TD></TR>");
	        out.println("<TR><TD align=center colspan=\"2\" class=\"thead\" >Principal Investigator Master</TD></TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"tabBG4\" width=\"30%\" >Name</TD>");
	        out.println("<TD class=\"tabBG4\" width=\"70%\" ><input type=\"text\" name=\"pi_name\" value=\"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"tabBG4\" width=\"30%\" >Department</TD>");
	        out.println("<TD class=\"tabBG4\" width=\"70%\" >");
	        Statement theStatement=conn.createStatement();
	        ResultSet theResult=theStatement.executeQuery("select id,fld_value from general_master where category=\'Department\' and active_yes_no=1");
	        out.println("<select name=\"department\" onchange=\"enable_save_button();\" >");
	        while(theResult.next()){
	        	out.println("<option value=\""+theResult.getInt("id")+"\" >"+theResult.getString("fld_value")+"</option>");
	        }
	        theResult.close();theStatement.close();
	        out.println("</select></TD></TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"tabBG4\" width=\"30%\" >Designation</TD>");
	        out.println("<TD class=\"tabBG4\" width=\"70%\" ><input type=\"text\" name=\"designation\" value=\"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"tabBG4\" width=\"30%\" >Email</TD>");
	        out.println("<TD class=\"tabBG4\" width=\"70%\" ><input type=\"text\" name=\"email\" value=\"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"tabBG4\" width=\"30%\" >Address</TD>");
	        out.println("<TD class=\"tabBG4\" width=\"70%\" ><input type=\"text\" name=\"address\" value=\"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"tabBG4\" width=\"30%\" >Active</TD>");
	        out.println("<TD class=\"tabBG4\" width=\"70%\" >");
	        out.println("<select name=\"active\" onchange=\"enable_save_button();\" ><option value=\"1\" >Yes</option><option value=\"0\" >No</option></select>");
	        out.println("</TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"tabBG4\" colspan=\"2\" align=\"center\" >" +
	        		"<input type=\"button\" value=\"Save\" name=\"save\" disabled=true  onclick=\"save_add_new_principal_investigator_master();\" >" +
	        		"&nbsp;<input type=\"button\" value=\"Back\" name=\"back\" onclick=\"back_principal_investigator_master();\" >" +
	        		"</TD>");
	        out.println("</TR>");
	        out.println("</TABLE>");
	  	   	    
	     }catch(Exception e){
		   e.printStackTrace();//Print trapped error.
	     }
	}//end of function add_new_principal_investigator_master.
	private void save_add_new_principal_investigator_master(Connection conn,HttpServletRequest req,HttpServletResponse res){
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
	     }catch(Exception e){
		    e.printStackTrace();
	     }
	}//end of function save_edit_principal_investigator_master.
	private void show_courses_taught_master(Connection conn, PrintWriter out,HttpServletRequest req){
		try{
		    String faculty_name=req.getParameter("faculty_name");
	        Statement theStatement=conn.createStatement();
	        ResultSet theResult=theStatement.executeQuery("select id,user_full_name from users WHERE id<>1  order by user_full_name");
	        theResult.last();int len=theResult.getRow();String s[]=new String[len];String n[]=new String[len];int i=0;String fn="";
	        out.println("Name:&nbsp;&nbsp;<select name=\"faculty_name_combo\" id=\"cb01\" onchange=\"courses_taught_master_combo_change(this.value);\" >");
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
	        out.println("</select>&nbsp;<input type=\"button\" name\"add_new\" value=\"Add New\" onclick=\"courses_taught_master_button_change();\" /><br><br>");
	        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"faculty_name\" value=\""+faculty_name+"\" />");
	        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"id\" value=\"\" />");
	        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"academic_term\" value=\"\" />");
	        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"class_name\" value=\"\" />");
	        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"course_name\" value=\"\" />");
	        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"students_registered\" value=\"\" />");
	        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"percent_of_pass\" value=\"\" />");
	        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"active\" value=\"\" />");
	        out.println("<TABLE border=\"1\" align=center class=\"ListTable\" cellspacing=0 width=\"100%\">");
	        out.println("<TR><TD align=center colspan=\"7\" class=\"thead\" >Courses Taught</TD></TR>");
	        out.println("<TR><TD align=center colspan=\"7\" class=\"thead\" >Faculty Name: "+fn+"</TD></TR>");
	        out.println("<TR>");
	        out.println("<TD align=center class=\"thead\" >Academic Term</TD>");
	        out.println("<TD align=center class=\"thead\">Class Name</TD>");
	        out.println("<TD align=center class=\"thead\">Course Name</TD>");
	        out.println("<TD align=center class=\"thead\">Students Registered</TD>");
	        out.println("<TD align=center class=\"thead\">Percent of Pass</TD>");
	        out.println("<TD align=center class=\"thead\">Active</TD>");
	        out.println("<TD align=center class=\"thead\">Edit</TD>");
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
	  	   	    if(str[6].equals("1")){ayn="<span style=\"color:green\">Yes</span>";}else{ayn="<span style=\"color:red\">No</span>";}
	  	   	    out.println("<TR>");
		        out.println("<TD align=center class=\"tabBG4\" >"+at+"</TD>");
		        out.println("<TD align=center class=\"tabBG4\">"+str[2]+"</TD>");
		        out.println("<TD align=center class=\"tabBG4\">"+str[3]+"</TD>");
		        out.println("<TD align=center class=\"tabBG4\">"+str[4]+"</TD>");
		        out.println("<TD align=center class=\"tabBG4\">"+str[5]+"</TD>");
		        out.println("<TD align=center class=\"tabBG4\">"+ayn+"</TD>");
		        out.println("<TD align=center class=\"tabBG4\">" +
		        		"<INPUT TYPE=\"HIDDEN\" id=\"id_"+x+"\" value=\""+str[0]+"\" />"+
		        		"<INPUT TYPE=\"HIDDEN\" id=\"academic_term_"+x+"\" value=\""+str[1]+"\" />"+
		        		"<INPUT TYPE=\"HIDDEN\" id=\"class_name_"+x+"\" value=\""+str[2]+"\" />"+
		        		"<INPUT TYPE=\"HIDDEN\" id=\"course_name_"+x+"\" value=\""+str[3]+"\" />"+
		        		"<INPUT TYPE=\"HIDDEN\" id=\"students_registered_"+x+"\" value=\""+str[4]+"\" />"+
		        		"<INPUT TYPE=\"HIDDEN\" id=\"percent_of_pass_"+x+"\" value=\""+str[5]+"\" />"+
		        		"<INPUT TYPE=\"HIDDEN\" id=\"active_"+x+"\" value=\""+str[6]+"\" />"+
		        		"<input type=\"button\" name=\"button_"+x+"\" value=\"Edit\" onclick=\"edit_courses_taught_master(this.name);\" />" +
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
	private void edit_courses_taught_master(Connection conn, PrintWriter out,HttpServletRequest req){
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
	        out.println("<TR><TD align=center colspan=\"2\" class=\"thead\" >Edit</TD></TR>");
	        out.println("<TR><TD align=center colspan=\"2\" class=\"thead\" >Courses Taught Master</TD></TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"tabBG4\" width=\"30%\" >Academic Term</TD>");
	        out.println("<TD class=\"tabBG4\" width=\"70%\" >");
	        Statement theStatement=conn.createStatement();
	        ResultSet theResult=theStatement.executeQuery("select id,fld_value from general_master where category=\'Academic_Term\' and active_yes_no=1");
	        out.println("<select name=\"academic_term\" onchange=\"enable_save_button();\" >");
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
	        out.println("<TD class=\"tabBG4\" width=\"30%\" >Class Name</TD>");
	        out.println("<TD class=\"tabBG4\" width=\"70%\" ><input type=\"text\" name=\"class_name\" value=\""+s[3]+"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"tabBG4\" width=\"30%\" >Course Name</TD>");
	        out.println("<TD class=\"tabBG4\" width=\"70%\" ><input type=\"text\" name=\"course_name\" value=\""+s[4]+"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"tabBG4\" width=\"30%\" >Students Registered</TD>");
	        out.println("<TD class=\"tabBG4\" width=\"70%\" ><input type=\"text\" name=\"students_registered\" value=\""+s[5]+"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"tabBG4\" width=\"30%\" >Percent of Pass</TD>");
	        out.println("<TD class=\"tabBG4\" width=\"70%\" ><input type=\"text\" name=\"percent_of_pass\" value=\""+s[6]+"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"tabBG4\" width=\"30%\" >Active</TD>");
	        out.println("<TD class=\"tabBG4\" width=\"70%\" >");
	        if(s[7].equals("1")){
	        	out.println("<select name=\"active\" onchange=\"enable_save_button();\" ><option value=\"1\" >Yes</option><option value=\"0\" >No</option></select>");
	        }else if(s[7].equals("0")){
	        	out.println("<select name=\"active\" onchange=\"enable_save_button();\" ><option value=\"0\" >No</option><option value=\"1\" >Yes</option></select>");
	        }
	        out.println("</TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"tabBG4\" colspan=\"2\" align=\"center\" >" +
	        		"<input type=\"button\" value=\"Save\" name=\"save\" disabled=true  onclick=\"save_edit_courses_taught_master();\" >" +
	        		"&nbsp;<input type=\"button\" value=\"Back\" name=\"back\" onclick=\"back_courses_taught_master();\" >" +
	        		"</TD>");
	        out.println("</TR>");
	        out.println("</TABLE>");
	  	   	    
	     }catch(Exception e){
		   e.printStackTrace();//Print trapped error.
	     }
	}//end of function edit_courses_taught_master.
	private void save_edit_courses_taught_master(Connection conn,HttpServletRequest req,HttpServletResponse res){
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
		    Statement theStatement=conn.createStatement();
	        theStatement.executeUpdate("update course_taught set academic_term=\'"+s[2]+"\',class_name=\'"+s[3]+"\',course_name=\'"+s[4]+"\',students_registered=\'"+s[5]+"\',percent_of_pass=\'"+s[6]+"\',active_yes_no="+s[7]+" where id="+s[1]+" and idf="+s[0]);
	      	theStatement.close();//Close statement
	  	    res.sendRedirect("./master_tables?action=show_courses_taught_master&tab_name=courses_taught_master&faculty_name="+s[0]);
	     }catch(Exception e){
		    e.printStackTrace();
	     }
	}//end of function save_edit_courses_taught_master.
	private void add_new_courses_taught_master(Connection conn, PrintWriter out,HttpServletRequest req){
		try{
			String faculty_name=req.getParameter("faculty_name");
		    out.println("<INPUT TYPE=\"HIDDEN\" name=\"faculty_name\" value=\""+faculty_name+"\" />");
		    out.println("<TABLE border=\"1\" align=center class=\"ListTable\" cellspacing=0 width=\"100%\">");
	        out.println("<TR><TD align=center colspan=\"2\" class=\"thead\" >Add New</TD></TR>");
	        out.println("<TR><TD align=center colspan=\"2\" class=\"thead\" >Courses Taught Master</TD></TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"tabBG4\" width=\"30%\" >Academic Term</TD>");
	        out.println("<TD class=\"tabBG4\" width=\"70%\" >");
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
	        out.println("<TD class=\"tabBG4\" width=\"30%\" >Class Name</TD>");
	        out.println("<TD class=\"tabBG4\" width=\"70%\" ><input type=\"text\" name=\"class_name\" value=\"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"tabBG4\" width=\"30%\" >Course Name</TD>");
	        out.println("<TD class=\"tabBG4\" width=\"70%\" ><input type=\"text\" name=\"course_name\" value=\"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"tabBG4\" width=\"30%\" >Students Registered</TD>");
	        out.println("<TD class=\"tabBG4\" width=\"70%\" ><input type=\"text\" name=\"students_registered\" value=\"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"tabBG4\" width=\"30%\" >Percent of Pass</TD>");
	        out.println("<TD class=\"tabBG4\" width=\"70%\" ><input type=\"text\" name=\"percent_of_pass\" value=\"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"tabBG4\" width=\"30%\" >Active</TD>");
	        out.println("<TD class=\"tabBG4\" width=\"70%\" >");
	        out.println("<select name=\"active\" onchange=\"enable_save_button();\" ><option value=\"1\" >Yes</option><option value=\"0\" >No</option></select>");
	        out.println("</TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"tabBG4\" colspan=\"2\" align=\"center\" >" +
	        		"<input type=\"button\" value=\"Save\" name=\"save\" disabled=true  onclick=\"save_add_new_courses_taught_master();\" >" +
	        		"&nbsp;<input type=\"button\" value=\"Back\" name=\"back\" onclick=\"back_courses_taught_master();\" >" +
	        		"</TD>");
	        out.println("</TR>");
	        out.println("</TABLE>");
	  	   	    
	     }catch(Exception e){
		   e.printStackTrace();//Print trapped error.
	     }
	}//end of function add_new_courses_taught_master.
    private void save_add_new_courses_taught_master(Connection conn,HttpServletRequest req,HttpServletResponse res){
    	try{
			String s[]=new String[7];
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
	       }catch(Exception e){
		    e.printStackTrace();
	     }
	}//end of function save_add_new_courses_taught_master.
    private void show_university_master(Connection conn, PrintWriter out){
    	try{
		    out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"id\" value=\"\" />");
	        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"university_name\" value=\"\" />");
	        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"short_name\" value=\"\" />");
	        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"address\" value=\"\" />");
	        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"active\" value=\"\" />");
	        out.println("<INPUT TYPE=\"button\" NAME=\"add\" value=\"Add New\" onclick=\"add_new_university_master();\" /><br><br>");
		    out.println("<TABLE border=\"1\" align=center class=\"ListTable\" cellspacing=0 width=\"100%\">");
	        out.println("<TR><TD align=center colspan=\"5\" class=\"thead\" >University Master</TD></TR>");
	        out.println("<TR>");
	        out.println("<TD align=center class=\"thead\" >Name</TD>");
	        out.println("<TD align=center class=\"thead\">Short Name</TD>");
	        out.println("<TD align=center class=\"thead\" >Address</TD>");
	        out.println("<TD align=center class=\"thead\">Active</TD>");
	        out.println("<TD align=center class=\"thead\">Edit</TD>");
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
		        if(s[4].equals("1")){str="<span style=\"color:green\">Yes</span>";}else{str="<span style=\"color:red\">No</span>";}
	        	out.println("<TR>");
		        out.println("<TD  class=\"tabBG4\" >"+s[1]+"</TD>");
		        out.println("<TD align=center class=\"tabBG4\" >"+s[2]+"</TD>");
		        out.println("<TD align=center class=\"tabBG4\" >"+s[3]+"</TD>");
		        out.println("<TD align=center class=\"tabBG4\" >"+str+"</TD>");
		        out.println("<TD align=center class=\"tabBG4\" >" +
		        		"<INPUT TYPE=\"HIDDEN\" id=\"id_"+x+"\" value=\""+s[0]+"\" />" +
		                "<INPUT TYPE=\"HIDDEN\" id=\"university_name_"+x+"\" value=\""+s[1]+"\" />" +
		                "<INPUT TYPE=\"HIDDEN\" id=\"short_name_"+x+"\" value=\""+s[2]+"\" />" +
		                "<INPUT TYPE=\"HIDDEN\" id=\"address_"+x+"\" value=\""+s[3]+"\" />" +
		                "<INPUT TYPE=\"HIDDEN\" id=\"active_"+x+"\" value=\""+s[4]+"\" />" +
		               	"<input type=\"button\" name=\"button_"+x+"\" value=\"Edit\" onclick=\"edit_university_master(this.name);\" />" +
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
    private void edit_university_master(PrintWriter out,HttpServletRequest req){
    	try{
    		String s[]=new String[5];
    		s[0]=req.getParameter("id");
    		s[1]=req.getParameter("university_name");
    		s[2]=req.getParameter("short_name");
    		s[3]=req.getParameter("address");
    		s[4]=req.getParameter("active");
    		out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"id\" value=\""+s[0]+"\" />");
    		out.println("<TABLE border=\"1\" align=center class=\"ListTable\" cellspacing=0 width=\"100%\">");
    		out.println("<TR><TD align=center colspan=\"2\" class=\"thead\" >Edit</TD></TR>");
	        out.println("<TR><TD align=center colspan=\"2\" class=\"thead\" >University Master</TD></TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"tabBG4\" width=\"30%\" >Name</TD>");
	        out.println("<TD class=\"tabBG4\" width=\"70%\" ><input type=\"text\" name=\"university_name\" value=\""+s[1]+"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"tabBG4\" width=\"30%\" >Short Name</TD>");
	        out.println("<TD class=\"tabBG4\" width=\"70%\" ><input type=\"text\" name=\"short_name\" value=\""+s[2]+"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"tabBG4\" width=\"30%\" >Address</TD>");
	        out.println("<TD class=\"tabBG4\" width=\"70%\" ><input type=\"text\" name=\"address\" value=\""+s[3]+"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR>");
	        out.println("<TD class=\"tabBG4\" width=\"30%\" >Active</TD>");
	        out.println("<TD class=\"tabBG4\" width=\"70%\" >");
	        if(s[4].equals("1")){
	        	out.println("<select name=\"active\" onchange=\"enable_save_button();\" ><option value=\"1\" >Yes</option><option value=\"0\" >No</option></select>");
	        }else if(s[4].equals("0")){
	        	out.println("<select name=\"active\" onchange=\"enable_save_button();\" ><option value=\"0\" >No</option><option value=\"1\" >Yes</option></select>");
	        }
	        out.println("</TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"tabBG4\" colspan=\"2\" align=\"center\" >" +
	        		"<input type=\"button\" value=\"Save\" name=\"save\" disabled=true  onclick=\"save_edit_university_master();\" >" +
	        		"&nbsp;<input type=\"button\" value=\"Back\" name=\"back\" onclick=\"back_university_master();\" >" +
	        		"</TD>");
	        out.println("</TR>");
	        out.println("</TABLE>");
    	}catch(Exception e){
 		   e.printStackTrace();//Print trapped error.
	     }
    }//end of function edit_university_master.
    private void save_edit_university_master(Connection conn,HttpServletRequest req,HttpServletResponse res){
    	try{
			String s[]=new String[5];
			s[0]=req.getParameter("id");
		    s[1]=req.getParameter("university_name");
		    s[2]=req.getParameter("short_name");
		    s[3]=req.getParameter("address");
		    s[4]=req.getParameter("active");
		    Statement theStatement=conn.createStatement();
	        theStatement.executeUpdate("update university_master set name=\'"+s[1]+"\',short_name=\'"+s[2]+"\',address=\'"+s[3]+"\',active_yes_no="+s[4]+" where id="+s[0]);
	      	theStatement.close();//Close statement
	  	    res.sendRedirect("./master_tables?action=show_university_master&tab_name=university_master");
	       }catch(Exception e){
		    e.printStackTrace();
	     }
	}//end of function save_add_new_courses_taught_master.
    private void add_new_university_master(PrintWriter out,HttpServletRequest req){
    	try{
    		out.println("<TABLE border=\"1\" align=center class=\"ListTable\" cellspacing=0 width=\"100%\">");
    		out.println("<TR><TD align=center colspan=\"2\" class=\"thead\" >Add New</TD></TR>");
	        out.println("<TR><TD align=center colspan=\"2\" class=\"thead\" >University Master</TD></TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"tabBG4\" width=\"30%\" >Name</TD>");
	        out.println("<TD class=\"tabBG4\" width=\"70%\" ><input type=\"text\" name=\"university_name\" value=\"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"tabBG4\" width=\"30%\" >Short Name</TD>");
	        out.println("<TD class=\"tabBG4\" width=\"70%\" ><input type=\"text\" name=\"short_name\" value=\"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"tabBG4\" width=\"30%\" >Address</TD>");
	        out.println("<TD class=\"tabBG4\" width=\"70%\" ><input type=\"text\" name=\"address\" value=\"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR>");
	        out.println("<TD class=\"tabBG4\" width=\"30%\" >Active</TD>");
	        out.println("<TD class=\"tabBG4\" width=\"70%\" >");
	        out.println("<select name=\"active\" onchange=\"enable_save_button();\" ><option value=\"1\" >Yes</option><option value=\"0\" >No</option></select>");
	        out.println("</TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"tabBG4\" colspan=\"2\" align=\"center\" >" +
	        		"<input type=\"button\" value=\"Save\" name=\"save\" disabled=true  onclick=\"save_add_new_university_master();\" >" +
	        		"&nbsp;<input type=\"button\" value=\"Back\" name=\"back\" onclick=\"back_university_master();\" >" +
	        		"</TD>");
	        out.println("</TR>");
	        out.println("</TABLE>");
    	}catch(Exception e){
 		   e.printStackTrace();//Print trapped error.
	     }
    }//end of function add_new_university_master.
    private void save_add_new_university_master(Connection conn,HttpServletRequest req,HttpServletResponse res){
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
	       }catch(Exception e){
		    e.printStackTrace();
	     }
	}//end of function save_add_new_courses_taught_master.
    private void show_institution_master(Connection conn,PrintWriter out){
    	try{
		    out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"id\" value=\"\" />");
	        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"institution_name\" value=\"\" />");
	        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"short_name\" value=\"\" />");
	        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"address\" value=\"\" />");
	        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"location\" value=\"\" />");
	        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"university_id\" value=\"\" />");
	        out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"active\" value=\"\" />");
	        out.println("<INPUT TYPE=\"button\" NAME=\"add\" value=\"Add New\" onclick=\"add_new_institution_master();\" /><br><br>");
		    out.println("<TABLE border=\"1\" align=center class=\"ListTable\" cellspacing=0 width=\"100%\">");
	        out.println("<TR><TD align=center colspan=\"7\" class=\"thead\" >Institution Master</TD></TR>");
	        out.println("<TR>");
	        out.println("<TD align=center class=\"thead\" >Name</TD>");
	        out.println("<TD align=center class=\"thead\">Short Name</TD>");
	        out.println("<TD align=center class=\"thead\" >Address</TD>");
	        out.println("<TD align=center class=\"thead\">Location</TD>");
	        out.println("<TD align=center class=\"thead\">University</TD>");
	        out.println("<TD align=center class=\"thead\">Active</TD>");
	        out.println("<TD align=center class=\"thead\">Edit</TD>");
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
		        if(s[6].equals("1")){str="<span style=\"color:green\">Yes</span>";}else{str="<span style=\"color:red\">No</span>";}
	        	out.println("<TR>");
		        out.println("<TD  class=\"tabBG4\" >"+s[1]+"</TD>");
		        out.println("<TD align=center class=\"tabBG4\" >"+s[2]+"</TD>");
		        out.println("<TD class=\"tabBG4\" >"+s[3]+"</TD>");
		        out.println("<TD align=center class=\"tabBG4\" >"+s[4]+"</TD>");
		        ResultSet rs=st.executeQuery("select name from university_master where id="+s[5]);
		        rs.first(); out.println("<TD align=center class=\"tabBG4\" >"+rs.getString("name")+"</TD>");rs.close();
		        out.println("<TD align=center class=\"tabBG4\" >"+str+"</TD>");
		        out.println("<TD align=center class=\"tabBG4\" >" +
		        		"<INPUT TYPE=\"HIDDEN\" id=\"id_"+x+"\" value=\""+s[0]+"\" />" +
		                "<INPUT TYPE=\"HIDDEN\" id=\"institution_name_"+x+"\" value=\""+s[1]+"\" />" +
		                "<INPUT TYPE=\"HIDDEN\" id=\"short_name_"+x+"\" value=\""+s[2]+"\" />" +
		                "<INPUT TYPE=\"HIDDEN\" id=\"address_"+x+"\" value=\""+s[3]+"\" />" +
		                "<INPUT TYPE=\"HIDDEN\" id=\"location_"+x+"\" value=\""+s[4]+"\" />" +
		                "<INPUT TYPE=\"HIDDEN\" id=\"university_id_"+x+"\" value=\""+s[5]+"\" />" +
		                "<INPUT TYPE=\"HIDDEN\" id=\"active_"+x+"\" value=\""+s[6]+"\" />" +
		               	"<input type=\"button\" name=\"button_"+x+"\" value=\"Edit\" onclick=\"edit_institution_master(this.name);\" />" +
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
    private void edit_institution_master(Connection conn,PrintWriter out,HttpServletRequest req){
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
	        out.println("<TR><TD align=center colspan=\"2\" class=\"thead\" >Edit</TD></TR>");
	        out.println("<TR><TD align=center colspan=\"2\" class=\"thead\" >Institution Master</TD></TR>");
	        out.println("<TR>");
	        out.println("<TD width=\"30%\" class=\"tabBG4\" >Name</TD>");
	        out.println("<TD class=\"tabBG4\" width=\"70%\" ><input type=\"text\" name=\"institution_name\" value=\""+s[1]+"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR><TR>");
	        out.println("<TD width=\"30%\" class=\"tabBG4\" >Short Name</TD>");
	        out.println("<TD class=\"tabBG4\" width=\"70%\" ><input type=\"text\" name=\"short_name\" value=\""+s[2]+"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR><TR>");
	        out.println("<TD width=\"30%\" class=\"tabBG4\" >Address</TD>");
	        out.println("<TD class=\"tabBG4\" width=\"70%\" ><input type=\"text\" name=\"address\" value=\""+s[3]+"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR><TR>");
	        out.println("<TD width=\"30%\" class=\"tabBG4\" >Location</TD>");
	        out.println("<TD class=\"tabBG4\" width=\"70%\" ><input type=\"text\" name=\"location\" value=\""+s[4]+"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR><TR>");
	        out.println("<TD class=\"tabBG4\" width=\"30%\" >University</TD><TD class=\"tabBG4\" width=\"70%\" >");
	        Statement theStatement=conn.createStatement();
	        ResultSet theResult=theStatement.executeQuery("select id,name from university_master");
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
	        out.println("<TD class=\"tabBG4\" width=\"30%\" >Active</TD><TD class=\"tabBG4\" width=\"70%\" >");
	        if(s[6].equals("1")){
	        	out.println("<select name=\"active\" onchange=\"enable_save_button();\" ><option value=\"1\" >Yes</option><option value=\"0\" >No</option></select>");
	        }else if(s[6].equals("0")){
	        	out.println("<select name=\"active\" onchange=\"enable_save_button();\" ><option value=\"0\" >No</option><option value=\"1\" >Yes</option></select>");
	        }
	        out.println("</TD></TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"tabBG4\" colspan=\"2\" align=\"center\" >" +
	        		"<input type=\"button\" value=\"Save\" name=\"save\" disabled=true  onclick=\"save_edit_institution_master();\" >" +
	        		"&nbsp;<input type=\"button\" value=\"Back\" name=\"back\" onclick=\"back_institution_master();\" >" +
	        		"</TD>");
	        out.println("</TR>");
	       	out.println("</TABLE>");
	     }catch(Exception e){
		   e.printStackTrace();//Print trapped error.
	     }
    }//end of function edit_institution_master.
    private void save_edit_institution_master(Connection conn,HttpServletRequest req,HttpServletResponse res){
    	try{
    		String s[]=new String[7];
			s[0]=req.getParameter("id");
		    s[1]=req.getParameter("institution_name");
		    s[2]=req.getParameter("short_name");
		    s[3]=req.getParameter("address");
		    s[4]=req.getParameter("location");
		    s[5]=req.getParameter("university_id");
		    s[6]=req.getParameter("active");
		    Statement theStatement=conn.createStatement();
	        theStatement.executeUpdate("update institution_master set name=\'"+s[1]+"\',short_name=\'"+s[2]+"\',address=\'"+s[3]+"\',location=\'"+s[4]+"\',university_id="+s[5]+",active_yes_no="+s[6]+" where id="+s[0]);
	      	theStatement.close();//Close statement
	  	    res.sendRedirect("./master_tables?action=show_institution_master&tab_name=institution_master");
	       }catch(Exception e){
		    e.printStackTrace();
	     }
    }//end of function save_edit_institution_master.
    private void add_new_institution_master(Connection conn,PrintWriter out,HttpServletRequest req){
    	try{
    		out.println("<TABLE border=\"1\" align=center class=\"ListTable\" cellspacing=0 width=\"100%\">");
	        out.println("<TR><TD align=center colspan=\"2\" class=\"thead\" >Add New</TD></TR>");
	        out.println("<TR><TD align=center colspan=\"2\" class=\"thead\" >Institution Master</TD></TR>");
	        out.println("<TR>");
	        out.println("<TD width=\"30%\" class=\"tabBG4\" >Name</TD>");
	        out.println("<TD class=\"tabBG4\" width=\"70%\" ><input type=\"text\" name=\"institution_name\" value=\"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR><TR>");
	        out.println("<TD width=\"30%\" class=\"tabBG4\" >Short Name</TD>");
	        out.println("<TD class=\"tabBG4\" width=\"70%\" ><input type=\"text\" name=\"short_name\" value=\"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR><TR>");
	        out.println("<TD width=\"30%\" class=\"tabBG4\" >Address</TD>");
	        out.println("<TD class=\"tabBG4\" width=\"70%\" ><input type=\"text\" name=\"address\" value=\"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR><TR>");
	        out.println("<TD width=\"30%\" class=\"tabBG4\" >Location</TD>");
	        out.println("<TD class=\"tabBG4\" width=\"70%\" ><input type=\"text\" name=\"location\" value=\"\" onchange=\"enable_save_button();\" onkeypress=\"enable_save_button();\" onkeydown=\"return event.keyCode != 13 && event.which != 13;\" /></TD>");
	        out.println("</TR><TR>");
	        out.println("<TD class=\"tabBG4\" width=\"30%\" >University</TD><TD class=\"tabBG4\" width=\"70%\" >");
	        Statement theStatement=conn.createStatement();
	        ResultSet theResult=theStatement.executeQuery("select id,name from university_master");
	        out.println("<select name=\"university_id\" onchange=\"enable_save_button();\" >");
	        String at="";
	        while(theResult.next()){
	        	at=Integer.toString(theResult.getInt("id"));
	        	out.println("<option value=\""+at+"\" >"+theResult.getString("name")+"</option>");
	        }
	        theResult.close();//Close the result set
	        theStatement.close();//Close statement
	        out.println("</select></TD></TR><TR>");
	        out.println("<TD class=\"tabBG4\" width=\"30%\" >Active</TD><TD class=\"tabBG4\" width=\"70%\" >");
	        out.println("<select name=\"active\" onchange=\"enable_save_button();\" ><option value=\"1\" >Yes</option><option value=\"0\" >No</option></select>");
	        out.println("</TD></TR>");
	        out.println("<TR>");
	        out.println("<TD class=\"tabBG4\" colspan=\"2\" align=\"center\" >" +
	        		"<input type=\"button\" value=\"Save\" name=\"save\" disabled=true  onclick=\"save_add_new_institution_master();\" >" +
	        		"&nbsp;<input type=\"button\" value=\"Back\" name=\"back\" onclick=\"back_institution_master();\" >" +
	        		"</TD>");
	        out.println("</TR>");
	       	out.println("</TABLE>");
	     }catch(Exception e){
		   e.printStackTrace();//Print trapped error.
	     }
    }//end of function add_new_institution_master.
    private void save_add_new_institution_master(Connection conn,HttpServletRequest req,HttpServletResponse res){
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
	       }catch(Exception e){
		    e.printStackTrace();
	     }
    }//end of function save_add_new_institution_master.
}
