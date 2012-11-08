/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;

import com.myapp.struts.hbm.Notices;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpSession;
import com.myapp.struts.opacDAO.NewDemandDAO;

/**
 *
 * @author edrp02
 */
public class ViewNoticesAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String notice_id;
    String library_id,sublibrary_id,l_id,s_id;
    Notice1ActionForm naf=new Notice1ActionForm();
    public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
            throws Exception {
         HttpSession session=(HttpSession)request.getSession();
         NewDemandDAO newdemanddao=new NewDemandDAO();
        l_id=(String)session.getAttribute("l_id");
        
        s_id=(String)session.getAttribute("s_id");
        
        library_id=(String)session.getAttribute("library_id");
        sublibrary_id=(String)session.getAttribute("sublibrary_id");
        if(l_id!=null)
        {
         library_id=l_id;
         sublibrary_id=s_id;
        }
        notice_id= request.getParameter("name");
        System.out.println("%%%%%%%%%%%%%%%%%%"+notice_id);
        System.out.println("library_id"+library_id+"sublibrary_id"+sublibrary_id+"notice_id"+notice_id);
        Notices notice=newdemanddao.ViewNotice(library_id, sublibrary_id, notice_id);
        
    
       if(notice!=null)
       {
         request.setAttribute("notice",notice);
         return mapping.findForward("success");

       }
      else
      {
        request.setAttribute("msg", "Record Not found");
        return mapping.findForward("failure");
      }

        
    }
}
