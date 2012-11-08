/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetup;

import com.myapp.struts.hbm.Faculty;
import com.myapp.struts.systemsetupDAO.FacultyDAO;
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
public class FacultyViewAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String faculty_id;
    String button;
    String library_id;
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        FacultyDAO facdao=new FacultyDAO();
         HttpSession session=request.getSession();
         library_id=(String)session.getAttribute("library_id");

         faculty_id=request.getParameter("id");

         Faculty faculty=facdao.getFacultyName(library_id, faculty_id);
         if(faculty!=null)
         {request.setAttribute("button", "View");
               request.setAttribute("sublib", faculty);
               return  mapping.findForward("view");
         }

     return null;
    }
}
