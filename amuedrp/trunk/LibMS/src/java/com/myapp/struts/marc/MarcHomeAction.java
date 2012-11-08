/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.marc;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author edrp-07
 */
public class MarcHomeAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
        
       
           HashMap t=(HashMap)session.getAttribute("hsmp");
        if(t!=null && t.isEmpty()==false)
        {System.out.println("Get Value"+t.size());

          t.clear();
          System.out.println("Get Value"+t.size());
          session.setAttribute("hsmp",t);
         }

        System.out.println(session.getAttribute("hsmp")+".....................");
        session.removeAttribute("hsmp");
        session.removeAttribute("controltag");
        session.removeAttribute("st");
        session.removeAttribute("data");
         session.removeAttribute("editlist");
        session.removeAttribute("tag0");
        session.removeAttribute("tag1");
        session.removeAttribute("tag2");
        session.removeAttribute("tag3");
        session.removeAttribute("tag4");
        session.removeAttribute("tag5");
        session.removeAttribute("tag6");
        session.removeAttribute("tag7");
        session.removeAttribute("tag8");
        session.removeAttribute("opacList");
        request.removeAttribute("BiblioActionForm");
        session.removeAttribute("biblio_id");
        session.removeAttribute("title");
        session.removeAttribute("marcbutton");



        return mapping.findForward(SUCCESS);
    }
}
