/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;

import java.util.*;
//import com.myapp.struts.AdminDAO.LogsDAO;
import com.myapp.struts.hbm.Logs;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author EdRP-05
 */
public class ShowLog extends org.apache.struts.action.Action {


  
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
HttpSession session=request.getSession();
//        List<String> logobj=LogsDAO.SearchlogLib();
 //       System.out.println(logobj.size());
  //      session.setAttribute("logliblist", logobj);


        return mapping.findForward("success");
}
}
