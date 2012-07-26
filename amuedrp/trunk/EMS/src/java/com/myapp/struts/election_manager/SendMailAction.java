/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.election_manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.hbm.VoterRegistration;
import  com.myapp.struts.utility.*;
import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.servlet.http.HttpSession;

/**
 *
 * @author edrp-03
 */
public class SendMailAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
     private final ExecutorService executor=Executors.newFixedThreadPool(1);
    	Email x;
    	private String admin_password;
    	private String admin_password1;
        String mailbody;
        String userid;

    
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


      MailBodyFormBean mailformbean=(MailBodyFormBean)form;
        HttpSession session = request.getSession();
       // String Id=request.getParameter("id");

        VoterRegistration voter=new VoterRegistration();
        userid=(String)session.getAttribute("user_id");


         mailbody=UserLog.readProperty("mail.properties", userid+"vm");
                                      


                request.setAttribute("voter", mailbody);

        return mapping.findForward(SUCCESS);
    }
}
