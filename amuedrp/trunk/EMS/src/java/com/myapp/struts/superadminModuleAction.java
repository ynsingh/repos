/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts;

import com.myapp.struts.hbm.AdminRegistrationDAO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
                   
                    session.setAttribute("resultset", rst);
                   

                   
                    int count = admindao.getAdminRequestCount("NotRegistered");

                    session.setAttribute("count", count);

                      // get List of Rejected Library
                 rst = admindao.getAdminDetailsByStatus("Rejected");
                      session.setAttribute("rejected", rst);




                    //registered
                  
                    rst = admindao.getAdminDetailsByStatus("Registered");

                    session.setAttribute("resultset1", rst);

                    count = admindao.getAdminRequestCount("Registered");
                    session.setAttribute("count1", count);


                    //view All

                    rst = admindao.getAdminDetails();
                    session.setAttribute("resultset2", rst);

                 
                    count = admindao.getAdminRequestCount();
                    session.setAttribute("count2", count);

test();
                  
        
        return mapping.findForward(SUCCESS);
    }
     void test(){
                  //0123456789012345678901
    String text = "Hello,my name is=Helen";
    Map<Character,Integer> map = new HashMap<Character,Integer>();

    boolean lastIsLetter = false;
    for (int i = 0; i < text.length(); i++) {
        char ch = text.charAt(i);
        boolean currIsLetter = Character.isLetter(ch);
        if (!lastIsLetter && currIsLetter) {
            map.put(ch, i);
        }
        lastIsLetter = currIsLetter;
    }

    System.out.println(map);

    }
}
