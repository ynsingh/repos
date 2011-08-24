package com.erp.nfes;

import java.io.*;
import java.util.Enumeration;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.net.*;


public class CourseTaught extends HttpServlet{

	Connection theConnection;
	int id;
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
	  int entity_id=Integer.parseInt(req.getParameter("entity_id"));
	  int academic_term=Integer.parseInt(req.getParameter("academic_term"));
     
	try{
        ConnectDB conObj=new ConnectDB();
		theConnection = conObj.getMysqlConnection();
        Statement theStatement=theConnection.createStatement();
        ResultSet theResult=theStatement.executeQuery("select fld_value from general_master where id="+academic_term+" and active_yes_no=1 and category=\'Academic_Term\'");
        String at="";
        while(theResult.next()) //Fetch all the records and print in table
		{
        	at=theResult.getString("fld_value");
		}
        out.println("<HTML><HEAD><TITLE>Courses Taught</TITLE>");
  	    out.println("<link href=\"./css/oiostyles.css\" rel=\"stylesheet\" type=\"text/css\"/></HEAD>");
  	    out.println("<BODY class=\"bodystyle\">");
        out.println("<TABLE align=center class=\"dataTableRows\" cellspacing=0 width=\"100%\" border=\"1\" >");
        out.println("<TR><TD align=center colspan=\"4\" class=\"thead\" >Academic Term: "+at+"</TD></TR>");
        out.println("<TR class=\"dataTableRowHead\" >");
        out.println("<TD align=center >Class Name</TD>");
        out.println("<TD align=center > Course Name</TD>");
  	    out.println("<TD align=center >Students Registered</TD>");
  	    out.println("<TD align=center >  % Pass</TD>");
	    theResult=theStatement.executeQuery("select class_name,course_name,students_registered,percent_of_pass from course_taught where academic_term="+academic_term+" and idf="+entity_id+" and active_yes_no=1");
		while(theResult.next()) //Fetch all the records and print in table
		{
		    out.println();
    		out.println("<TR>");
    		out.println("<TD  >" + theResult.getString("class_name") + "</TD>");
    		out.println("<TD  >" + theResult.getString("course_name") + "</TD>");
			out.println("<TD align=center  >" + theResult.getString("students_registered") + "</TD>");
			out.println("<TD align=center  >" + theResult.getString("percent_of_pass") + "</TD>");
			out.println("</TR>");

		}
            theResult.close();//Close the result set
            theStatement.close();//Close statement
            //theConnection.close(); //Close database Connection

	     }catch(Exception e){
		   out.println(e.getMessage());//Print trapped error.
	}finally{
		try {
			theConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
       out.println("</TABLE></BODY></HTML>");


	}
	public void destroy(){

	}

}