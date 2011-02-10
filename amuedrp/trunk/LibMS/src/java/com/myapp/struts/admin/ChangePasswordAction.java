/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;
import java.sql.*;
import com.myapp.struts.MyQueryResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author System Administrator
 */
public class ChangePasswordAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private String user_name;
    private String staff_id;
    private String password;
    private String library_id;
    private String email_id;
    int i;
    Connection con;
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
            throws Exception {
        CreateAccountActionForm caaction=(CreateAccountActionForm)form;
      email_id=caaction.getEmail_id();
      user_name=caaction.getUser_name();
      password=caaction.getPassword();
      staff_id=caaction.getStaff_id();
      HttpSession session=request.getSession();
      library_id=(String)session.getAttribute("library_id");

      System.out.println(staff_id);

      request.setAttribute("user_id", email_id);
      request.setAttribute("user_name", user_name);
      request.setAttribute("library_id", library_id);
      request.setAttribute("staff_id", staff_id);

        request.setAttribute("staff_name", user_name);


       String sql = "update  login set password='" +password + "' where staff_id='"+staff_id+"' and library_id='"+library_id+"'";




        i=MyQueryResult.getMyExecuteUpdate(sql);
        if(i!=0)

      {
            Privilege.assignStaffPrivilege(staff_id, library_id);


         return mapping.findForward("success");
        }

        return mapping.findForward("failure");
    }
}
