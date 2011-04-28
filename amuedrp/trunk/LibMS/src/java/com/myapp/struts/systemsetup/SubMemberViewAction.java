/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetup;

import com.myapp.struts.hbm.*;
import com.myapp.struts.systemsetupDAO.*;
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
public class SubMemberViewAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String sub_emptype_id;
    String button;
    String library_id;
    String sublibrary_id;
    String emptype_id;
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
         HttpSession session=request.getSession();
         library_id=(String)session.getAttribute("library_id");
         sublibrary_id=(String)session.getAttribute("sublibrary_id");
         sub_emptype_id=request.getParameter("id");

      /*  // SubEmployeeType subemployeetype=SubMemberDAO.getSubEployeeName(library_id,sublibrary_id,sub_emptype_id);
         emptype_id=subemployeetype.getId().getEmptypeId();
         EmployeeType emptype=MemberDAO.getEployeeName(library_id, emptype_id);
         if(subemployeetype!=null)
         {     request.setAttribute("button", "View");
               request.setAttribute("emptype", emptype);
               request.setAttribute("subemployeetype", subemployeetype);
               return  mapping.findForward("view");
         }
*/
        return mapping.findForward(SUCCESS);
    }
}
