/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.sql.*;
import java.util.*;
import javax.servlet.http.HttpSession;
import com.myapp.struts.hbm.HibernateUtil;

/**
 *
 * @author Dushyant
 */
public class LogOutAction extends org.apache.struts.action.Action {
    String user_id;
    String username;
    String password;
    Connection con;
    ResultSet rst,rst1,rst2,rst3,rst4,rst5,rst6;
    PreparedStatement stmt;
    String staff_id;
    String library_id;
    String button;

    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
    
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
    
     */
  
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
HttpSession session=request.getSession();
String userid = (String)session.getAttribute("staff_id");
if(userid==null){
userid=(String)request.getParameter("user");
}
System.out.println("user_id="+userid);
/*loginTempDAO logintempdao = new loginTempDAO();
logintempdao.delete(user_id);
*/

session.removeAttribute("LoginActionForm");
session.removeAttribute("library_id");
session.removeAttribute("notices");
session.removeAttribute("noticelibrary_id");
session.removeAttribute("noticesublib");
session.invalidate();


request.setAttribute("msg", "You have successfully Logged out.");


return mapping.findForward("success");
    }
}
