/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Voter;

import com.myapp.struts.hbm.Election;
import com.myapp.struts.hbm.ElectionDAO;
import com.myapp.struts.hbm.Position1;
import com.myapp.struts.hbm.PositionDAO;
import com.myapp.struts.hbm.PositionWithCandidature;
import com.myapp.struts.utility.PasswordEncruptionUtility;
import java.util.Calendar;
import java.util.Date;

import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Edrp-04
 */
public class checkPassword extends org.apache.struts.action.Action {
    
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
        StringBuffer emp_ids = new StringBuffer();
       
       
HttpSession session=request.getSession();
        //String searchText = request.getParameter("getSubLibrary_Id");
        String pass=(String)session.getAttribute("password");
        String passw = request.getParameter("pass");

        emp_ids.append("<messages>");
        emp_ids.append("<message>");
        if(passw!=null)
        {
        String passd = PasswordEncruptionUtility.password_encrupt(passw);
        System.out.println(passd);
        if(passd.equals(pass))
        {
            emp_ids.append("pass");
        }
        else
        {
            emp_ids.append("Password Mismatch!");
        }
        }
        emp_ids.append("</message>");
        emp_ids.append("</messages>");


        if(emp_ids!=null)
        {response.setContentType("application/xml");
        response.getWriter().write(emp_ids.toString());
        }


         return null;
    }
}
