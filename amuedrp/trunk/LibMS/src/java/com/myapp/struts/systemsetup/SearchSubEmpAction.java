/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetup;

import com.myapp.struts.systemsetupDAO.BookCategoryDAO;
import com.myapp.struts.admin.*;
import com.myapp.struts.hbm.EmployeeType;
import com.myapp.struts.utility.PasswordEncruptionUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Dushyant
 */
public class SearchSubEmpAction extends org.apache.struts.action.Action {
    
  
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
         SystemSetupAjaxDAO systemsetupDAO = new SystemSetupAjaxDAO();
        String searchText = request.getParameter("getEmpType_Id");
       String library_id=request.getParameter("getLibraryId");
        if(library_id==null)
       library_id = (String)session.getAttribute("library_id");
     
       String emails ="";
      
       emails= systemsetupDAO.getSubEmpTypeID(library_id,searchText);
       
        if (!emails.equals(""))
        {
        response.setContentType("application/xml");
        response.getWriter().write(emails);

        }
        return null;
    }
}
