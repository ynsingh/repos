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
 * Servlet implementation class UserDetailsServlet
 */
public class UserDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action == null){
			action = "";
		}
		if(action.equals("EDIT_USER")){
		    String userId = request.getParameter("userId");
		    String userName = request.getParameter("userName");
		    String title = request.getParameter("title");
		    String firstName = request.getParameter("firstName");
		    String lastName = request.getParameter("lastName");
		    String emailId = request.getParameter("emailId");
		  //String institution = request.getParameter("institution");
		    String department = request.getParameter("department");
		    String designation = request.getParameter("designation");
		    String role = request.getParameter("role");
		    String joining_date=request.getParameter("joining_date");
		    String openId=request.getParameter("openId");
		    Connection conn1=null;
		  try{
		    ConnectDB db = new  ConnectDB();
		   
		    Statement pst=null;
		    
		    conn1 = db.getMysqlConnection();
		    pst=conn1.createStatement();
		    PreparedStatement openIdInstStmt=null;
		    
		    pst.executeUpdate("update users set user_full_name='"+firstName+"',title='"+title+"',last_name='"+lastName+"',email='"+emailId+"' where id="+userId);
		    pst.executeUpdate("update authorities set authority='"+role+"' where username='"+userName+"'");
		    pst.executeUpdate("update staff_master set department_id="+department+",designation_id="+designation+",join_date='"+joining_date+"' where userid="+userId+ " and active_yesno=1");
		    /*-------------------------- Open ID Mapping ----------------*/		    		    	
		    	Statement sst = conn1.createStatement();
				ResultSet Rset = sst.executeQuery("Select openId from user_openID_map where user_id='"+ userId +"'" );
				if (Rset.next()){
					//System.out.println("Updation:========");
					openIdInstStmt=conn1.prepareStatement("update user_openID_map set openId='"+openId +"' where user_id='" + userId + "'");
					openIdInstStmt.executeUpdate();
					openIdInstStmt.close();	
				}else{
					//System.out.println("Insertion:========");
					if(!openId.equals("")){							
						openIdInstStmt=conn1.prepareStatement("INSERT INTO user_openID_map(user_id,openId) VALUES(" + userId + ",'" + openId + "')");
						openIdInstStmt.executeUpdate();
						openIdInstStmt.close();	
					}
				}			
		    /*----------------------- End Of Open Id Mapping -------------*/
		    pst.close();
		    conn1.close();
		    response.sendRedirect("jsp/Account.jsp?action=CREATE_USER&DocumentStatus=SAVED");
		  }catch (SQLException e) {			  
			 if(e.getMessage().startsWith("Duplicate entry") && (e.getMessage().endsWith("for key 'PRIMARY'"))){
				 response.sendRedirect("jsp/Account.jsp?action=EDIT_USER&userId="+userId+"&error_msg=Open ID Already Exists");
			 }else{
				 response.sendRedirect("jsp/Account.jsp?action=EDIT_USER&userId="+userId+"&error_msg=Unable Edit");
			 }
		  }catch(Exception e){            	        
			e.printStackTrace();
		  }finally{
			  try {
				conn1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		  }
		}
	}

}
