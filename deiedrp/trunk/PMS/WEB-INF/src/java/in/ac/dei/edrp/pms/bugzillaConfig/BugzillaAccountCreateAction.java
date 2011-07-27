package in.ac.dei.edrp.pms.bugzillaConfig;

import in.ac.dei.edrp.pms.dataBaseConnection.MyDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class BugzillaAccountCreateAction extends Action{
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		BugzillaAccountCreateForm bugForm=(BugzillaAccountCreateForm)form;
		Connection con=null;
		try {
			con=MyDataSource.getConnection();
			PreparedStatement ps = con.prepareStatement("select first_name,last_name from " +
					"user_info where user_id=?");
			ps.setString(1,bugForm.getUser_id());
			System.out.println("user id "+bugForm.getUser_id());
			ResultSet rs=ps.executeQuery();
			rs.next();	
			//dynamic url coding-modified by nupur dixit (15/02/2010)
			Map<String, String> urlData = ReadPropertiesFile.urlConfig(getServlet().getServletContext().getRealPath("/")+"WEB-INF/classes/");
			final String url = urlData.get("url").toString();
			System.out.println("url of bugzilla "+url);
			response.sendRedirect(url+"/editusers.cgi?login=" +
						bugForm.getUser_id()+"&name="+rs.getString(1)+" "+rs.getString(2)+"&password=&disabledtext=&action=new&token=025pTKE8Pr");			
		} catch (Exception e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		}
		System.out.println(bugForm.getUser_id());				
			return null;		
	}
}
