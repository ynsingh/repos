/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts;

import com.myapp.struts.hbm.AdminRegistrationDAO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class superadminModuleAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
 
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        List rst;
        HttpSession session = request.getSession();
         AdminRegistrationDAO admindao = new AdminRegistrationDAO();
                    session.removeAttribute("resultset");
                    rst = admindao.getAdminDetailsByStatus("NotRegistered");
                     //rst = stmt.executeQuery();
                    session.setAttribute("resultset", rst);
                    //rst=null;

                   // stmt = con.prepareStatement("select count(*) from admin_registration where status = 'NotRegistered'");
                    //rst = stmt.executeQuery();
                    //rst.next();
                    int count = admindao.getAdminRequestCount("NotRegistered");

                    session.setAttribute("count", count);

                    //registered
                   // con = MyConnection.getMyConnection();
                    //stmt = con.prepareStatement("select * from admin_registration where status='Registered' ");
                    //rst = stmt.executeQuery();
                    rst = admindao.getAdminDetailsByStatus("Registered");

                    session.setAttribute("resultset1", rst);

                    //stmt = con.prepareStatement("select count(*) from admin_registration where status ='Registered'");
                   // rst = stmt.executeQuery();
                   // rst.next();
                   // count = rst.getInt(1);
                    count = admindao.getAdminRequestCount("Registered");
                    session.setAttribute("count1", count);


                    //view All

                    //con = MyConnection.getMyConnection();
                    //stmt = con.prepareStatement("select * from admin_registration");

                    rst = admindao.getAdminDetails();
                    session.setAttribute("resultset2", rst);

                    //stmt = con.prepareStatement("select count(*) from admin_registration");
                    //rst = stmt.executeQuery();
                   // rst.next();
                    count = admindao.getAdminRequestCount();
                    session.setAttribute("count2", count);


                    //java mailing code to send it on Admin registration page



















        
        return mapping.findForward(SUCCESS);
    }
}
