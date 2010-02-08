/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package login;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.*;
import java.sql.*;
import javax.sql.DataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataBaseConnection.MyDataSource;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/** 
 * MyEclipse Struts
 * Creation date: 06-25-2009
 * 
 * XDoclet definition:
 * @struts.action input="login.jsp" validate="true"
 */
/**
 * This Action class is related with login the user.
 * This class have only one method that is execute. 
 */
public class LoginAction extends Action {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute is used for checking whether the user is admin or not.
	 * @param mapping It holds the action mapping information used to invoke a Struts action.
	 * @param form This holds the object of the bean class named LoginForm
	 * @param request The HTTP servlet request we are processing
	 * @param response The HTTP servlet response we are creating
	 * @return ActionForward called "admin or valid or invalid", which is defined in the struts-config.xml file
	 * For forwarding we use the {@link mapping#findForward(String) findForword} method.
	 * @see 'admin' then index.jsp page will be called.
	 * @see 'valid' then index.jsp page will be called.
	 * @see 'invalid' then invalid.jsp page will be called.
	
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		/*
		 * The variable 's' holds such values like 'admin' or 'valid' or 'invalid'.
		 * if s contains 'admin' then index.jsp page will be called.
		 * if s contains 'valid' then index.jsp page will be called.
		 * if s contains 'invalid' then invalid.jsp page will be called.
		 * */
		
		String s=null;
		Connection con=null;		
		LoginForm loginform = (LoginForm) form;// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		
		//System.out.println("session new in login action="+session.isNew());
		/**
		 * Setting the entered user id in the session scope.
		 */
		session.setAttribute("mysession",loginform.getUid().trim().toLowerCase());
		try
		{
			Context ctx = new InitialContext();
			//System.out.println("ctx="+ctx);
			// Look up the data source 
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mydb");
			//System.out.println("ds="+ds);
			MyDataSource.setDataSource(ds);
			//Get a connection from the pool 
			con=MyDataSource.getConnection();
			//System.out.println("con="+con);
			
		PreparedStatement ps=con.prepareStatement("select * from login where User_ID=? and Password=SHA1(?)");// and Authority=?");
		ps.setString(1,loginform.getUid().toLowerCase());
		ps.setString(2,loginform.getPass().trim());
		//ps.setString(2,loginform.getAuthority());
		ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
			/*
			 * Setting the attribute uid that can be used in whole application.
			 * 
			 * */
			
			//this.getServlet().getServletContext().setAttribute("uid",loginform.getUid().trim().toLowerCase());
			session.setAttribute("uid", loginform.getUid().trim().toLowerCase());
			if(rs.getString(2).equals("Admin"))				
			{
				s="admin";
			}
			else
			{
				s="valid";
			}
		}
			else
			{
				ActionErrors errors = new ActionErrors();
				ActionMessage error = new ActionMessage("error.login.invalid");
				errors.add("login",error);
				saveErrors(request,errors);
				
				session.invalidate();
				s="invalid";
			}
		}
		catch(Exception e)
		{
			ActionErrors errors = new ActionErrors();
			ActionMessage error = new ActionMessage("error.login.invalid");
			errors.add("login",error);
			saveErrors(request,errors);

			session.invalidate();
			s="invalid";
		}
		finally
		{
			MyDataSource.freeConnection(con);
		}
		//}
		/*
		 * calling to that page which is assigned in variable s.
		 * */	
		return mapping.findForward(s);
		
	}

	
}

