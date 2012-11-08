/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetup;

import com.myapp.struts.hbm.Department;
import com.myapp.struts.systemsetupDAO.DeptDAO;
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
public class DepartmentViewAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String dept_id;
    String library_id;
    String button;
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
DeptDAO deptdao=new DeptDAO();
        HttpSession session=request.getSession();
        library_id=(String)session.getAttribute("library_id");
        dept_id=request.getParameter("id");
        Department dept=deptdao.getDeptName(library_id, dept_id);
         if(dept!=null)
         {request.setAttribute("button", "View");
               request.setAttribute("sublib", dept);
               return  mapping.findForward("view");
         }

     return null;
    }
}
