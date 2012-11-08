/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetup;

import com.myapp.struts.systemsetupDAO.DeptDAO;
import java.util.List;
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
public class DepartmentViewAllAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String library_id;
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
DeptDAO deptdao=new DeptDAO();
         HttpSession session=request.getSession();
         library_id=(String)session.getAttribute("library_id");
         List dept=deptdao.searchDept(library_id);
          if(dept==null && dept.isEmpty())
             {


             }

         session.setAttribute("dept", dept);
         return mapping.findForward("success");
    }
}
