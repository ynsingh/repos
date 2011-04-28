/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;
import  com.myapp.struts.hbm.*;
import  com.myapp.struts.AdminDAO.*;

import java.sql.*;
import java.util.*;
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
public class LoginPasswordAction extends org.apache.struts.action.Action {
    
    
    private String staff_id;
    private String button;
    private String library_id;
    private String sublibrary_id;
    

    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
      
        
        HttpSession session=request.getSession();
        library_id=(String)session.getAttribute("library_id");
        sublibrary_id=(String)session.getAttribute("sublibrary_id");
        staff_id=(String)session.getAttribute("staff_id");
Login loginobj;

       




                         loginobj=(Login)LoginDAO.searchStaffLogin(staff_id, library_id);





             if(loginobj!=null)
                {



                                request.setAttribute("account_resultset", loginobj);

             }





return mapping.findForward("success");


 
}
}
