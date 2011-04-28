/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetup;

import com.myapp.struts.hbm.*;
import com.myapp.struts.systemsetupDAO.MemberDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author edrp02
 */
public class MemberViewAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String emptype_id;
    String button;
    String library_id;
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

         HttpSession session=request.getSession();
         library_id=(String)session.getAttribute("library_id");
         emptype_id=request.getParameter("id");
         EmployeeType employeetype=MemberDAO.getEployeeName(library_id,emptype_id);
         if(employeetype!=null)
         {     request.setAttribute("button", "View");
               request.setAttribute("employeetype", employeetype);
               return  mapping.findForward("view");
         }
        
        return null;
    }
}
