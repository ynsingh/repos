/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;

import com.myapp.struts.AdminDAO.AdminRegistrationDAO;
import com.myapp.struts.hbm.*;
import java.sql.*;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author System Administrator
 */
public class AdminPendingAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    //  private int registration_id ;
    List rst;    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    
        Integer  registration_id=0;
        if(request.getParameter("id")!=null)
            registration_id=(Integer.parseInt(request.getParameter("id")));

    AdminRegistration adminReg = new AdminRegistration();
    AdminRegistrationDAO admindao = new AdminRegistrationDAO();
    List rst;
      //  System.out.println("Registration Id="+registration_id);
        try{
        //con=MyConnection.getMyConnection();
        //PreparedStatement stmt=con.prepareStatement("select * from admin_registration where  status is null");
        //rst=stmt.executeQuery();
        rst = (List) admindao.getAdminDeatilsById(registration_id);
            request.setAttribute("resultset", rst);
        return mapping.findForward("success");
        }
        catch(Exception e)
        {
        e.getMessage();
        }
           return mapping.findForward("success");
        
    }
}
