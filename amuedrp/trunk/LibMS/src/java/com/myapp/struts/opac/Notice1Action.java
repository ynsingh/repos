/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;

import com.myapp.struts.hbm.Notices;
import com.myapp.struts.opacDAO.*;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author edrp02
 */
public class Notice1Action extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String l_id,s_id;
    OpacSearchDAO osDAO=new OpacSearchDAO();
    NewDemandDAO newdemanddao=new NewDemandDAO();
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
            Notice1ActionForm naf=(Notice1ActionForm)form;
            HttpSession session=(HttpSession)request.getSession();
            l_id=naf.getCMBLib();
            s_id=naf.getCMBSUBLib();
            List<Notices> notices=(List<Notices>)newdemanddao.Notice(l_id, s_id);
          //  List noticelibray_id=osDAO.LibrarySearch(l_id);
          //  List noticesublib=osDAO.subLibrarySearch(l_id);
            if(notices!=null)
            {
               
              
          //    session.setAttribute("noticesublib",noticesublib);
          //    session.setAttribute("noticelibray_id",noticelibray_id);
              session.setAttribute("notices",notices);
              return mapping.findForward("success");

            }
            else
            {
              request.setAttribute("msg", "Record Not Selected");
              return mapping.findForward("success");
            }

        
        
    }
}
