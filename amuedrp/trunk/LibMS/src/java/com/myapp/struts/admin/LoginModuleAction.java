/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;

import com.myapp.struts.AdminDAO.AdminRegistrationDAO;
import com.myapp.struts.AdminDAO.LogsDAO;
import com.myapp.struts.hbm.Logs;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.utility.DateCalculation;
import com.myapp.struts.utility.writeLog;

/**
 *
 * @author Shadab
 */
public class LoginModuleAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
  
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
       
HttpSession session=request.getSession();
 HttpServletRequest req=(HttpServletRequest)request;
       String ipAddress =request.getRemoteAddr();
 System.out.println("Request for URL="+ " By UserName="+((String)session.getAttribute("username"))+" From Libraary="+((String)session.getAttribute("library_id"))+ " SubLibrary="+((String)session.getAttribute("sublibrary_id"))+" LoginID="+((String)session.getAttribute("login_id"))+" Having Role="+ ((String)session.getAttribute("login_role")+" At "));
   if(((String)session.getAttribute("username")!=null) && ((String)session.getAttribute("library_id")!=null) && ((String)session.getAttribute("sublibrary_id")!=null) &&  ((String)session.getAttribute("login_id")!=null) && ((String)session.getAttribute("login_role")!=null))
          {
System.out.println("*************************    Log Information   ************************************");
            System.out.println("Request for URL="+ req.getRequestURI() +" By UserName="+((String)session.getAttribute("username"))+" From Libraary="+((String)session.getAttribute("library_id"))+ " SubLibrary="+((String)session.getAttribute("sublibrary_id"))+" LoginID="+((String)session.getAttribute("login_id"))+" Having Role="+ ((String)session.getAttribute("login_role")+" At "+DateCalculation.dateTime().toString()));
System.out.println("*************************************************************");
 Logs obj=new Logs();
          obj.setUrl(req.getRequestURI());
          obj.setDate(DateCalculation.dateTime().toString());
          obj.setUserId(session.getAttribute("login_id").toString());
          obj.setLibraryId((String)session.getAttribute("library_id"));
          obj.setSublibraryId((String)session.getAttribute("sublibrary_id"));
          obj.setUsername((String)session.getAttribute("username"));
          obj.setRole((String)session.getAttribute("login_role"));
//System.out.println("Object"+obj);
          LogsDAO.insertLog(obj);

          writeLog.writelog(req.getRealPath(req.getContextPath().toString()),DateCalculation.dateTime().toString(), ipAddress, req.getRequestURI(),((String)session.getAttribute("username")),((String)session.getAttribute("library_id")), ((String)session.getAttribute("sublibrary_id")),((String)session.getAttribute("login_id")), ((String)session.getAttribute("login_role")));
           }



 return mapping.findForward(SUCCESS);
    }
}
