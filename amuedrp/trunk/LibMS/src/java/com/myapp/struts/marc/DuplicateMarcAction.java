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
public class DuplicateMarcAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private MarcHibDAO marchib=new MarcHibDAO();
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
         BiblioActionForm eaf=(BiblioActionForm)form;
         int bibid=0;
          HttpSession session = request.getSession();
             String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
         bibid = marchib.returnMaxBiblioId(library_id, sub_library_id);


           session.setAttribute("biblio_id", bibid);
             HashMap t=(HashMap)session.getAttribute("hsmp");
        if(t!=null && t.isEmpty()==false)
        {System.out.println("Get Value"+t.size());

          t.clear();
          System.out.println("Get Value"+t.size());
          session.setAttribute("hsmp",t);
         }
//hm1=null;
        System.out.println(session.getAttribute("hsmp")+".....................");
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

        session.removeAttribute("data");
        session.removeAttribute("st");
        session.removeAttribute("controltag");
        session.removeAttribute("tag0");
        session.removeAttribute("tag1");
        session.removeAttribute("tag2");
        session.removeAttribute("tag3");
        session.removeAttribute("tag4");
        session.removeAttribute("tag5");
        session.removeAttribute("tag6");
        session.removeAttribute("tag7");
        session.removeAttribute("tag8");
        session.removeAttribute("hsmp");
       
                

        return mapping.findForward(SUCCESS);
    }
}
