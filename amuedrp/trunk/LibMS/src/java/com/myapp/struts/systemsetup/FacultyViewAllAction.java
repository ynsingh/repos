/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetup;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.systemsetupDAO.FacultyDAO;
import java.util.List;

/**
 *
 * @author edrp02
 */
public class FacultyViewAllAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String library_id;
 
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        FacultyDAO facdao=new FacultyDAO();
             HttpSession session=request.getSession();
             library_id=(String)session.getAttribute("library_id");
             List faculty=facdao.searchFaculty(library_id);

             if(faculty==null && faculty.isEmpty())
             {


             }
             session.setAttribute("faculty", faculty);
             return mapping.findForward("success");
    }
}
