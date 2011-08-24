package com.erp.nfes;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddEventsServlet
 */
public class AddEventsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AddEventsServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String userId=null;
		String event_date = null;
		String event_start_time = null;
		String event_end_time = null;
		String event_description = null;
		Connection conn = null;
		String mode= null;
		String edit_id= null;
		String val= "0";
		try {
			mode = request.getParameter("mode");
			userId = request.getParameter("userId");
			event_date = request.getParameter("event_date");
			event_start_time = request.getParameter("event_start_time");
			event_end_time = request.getParameter("event_end_time");
			event_description = request.getParameter("event_description");
			
            //System.out.println("Mode is -"+mode);
			ConnectDB conObj=new ConnectDB(); 
			conn = conObj.getMysqlConnection();
			Statement theStatement=conn.createStatement();
			ResultSet theResult=theStatement.executeQuery("select * from  event_scheduler where event_date='"+event_date +"'and event_description='"+event_description+"'");
		    if(!theResult.next())
		    {
			if(mode.equals("add"))
			{
				PreparedStatement st = conn.prepareStatement("insert into event_scheduler(idf,event_date,event_start_time,event_end_time,event_description) values ('"+userId+"','"+event_date+"','"+event_start_time+"','"+event_end_time+"','"+event_description+"')");							
				st.executeUpdate();
				val = "1";
				st.close();
			}if(mode.equals("edit")) {
				edit_id = request.getParameter("edit_id");				
				PreparedStatement st = conn.prepareStatement("update event_scheduler set idf='"+userId+"',event_date='"+event_date +"',event_start_time='"+event_start_time+"',event_end_time='"+event_end_time +"',event_description='"+event_description+"' where id='"+edit_id+"'");							
				st.executeUpdate();
				val = "2";
				st.close();
			}
		    }
			response.setContentType("text/html; charset=utf-8");
			response.sendRedirect("jsp/add_events.jsp?userId="+userId+"&sel_date="+event_date+"&val="+val);
			
		}catch (SQLException e) {			
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	}

}
