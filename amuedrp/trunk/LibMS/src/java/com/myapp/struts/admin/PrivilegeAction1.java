/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;
import com.myapp.struts.MyQueryResult;
import java.sql.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Dushyant
 */
public class PrivilegeAction1 extends org.apache.struts.action.Action {
    String privilege_list;
    String staff_id,library_id,staff_name;
    String sql1,sql2,sql3,sql4,sql5;
    String button;
    ResultSet privilege_resultset,acq_privilege_resultset,cat_privilege_resultset,cir_privilege_resultset,ser_privilege_resultset;
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
    
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
           HttpSession session=request.getSession();
         library_id=(String)session.getAttribute("library_id");
        PrivilegeActionForm privilege=(PrivilegeActionForm)form;
        privilege_list=privilege.getPrivilege_list();
        staff_id=privilege.getStaff_id();
        staff_name=privilege.getStaff_name();
        button=privilege.getButton();
if(button.equals("Restore Previous Privilege"))
{
         ResultSet[] privilegebackup=(ResultSet[])session.getAttribute("privilege_backup");
        privilegebackup[0].next();        
        privilegebackup[0].beforeFirst();
            Privilege priv=new Privilege();
            priv.rollbackPrivilege(privilegebackup,staff_id,library_id);
            request.setAttribute("res","Privileage Successfully removed");
            request.setAttribute("staff_id", staff_id);
            request.setAttribute("staff_name", staff_name);
            
            request.setAttribute("privilege_list", privilege_list);
           System.out.println("staff name="+staff_name);
            return mapping.findForward("success");
}
else
{
            request.setAttribute("staff_id", staff_id);
            request.setAttribute("staff_name", staff_name);


           System.out.println("staff name111="+staff_name);
           return mapping.findForward("restore");
}
         
        
    }
}
