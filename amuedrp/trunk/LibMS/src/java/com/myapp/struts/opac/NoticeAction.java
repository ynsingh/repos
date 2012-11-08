/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;
import com.myapp.struts.hbm.Library;
import com.myapp.struts.hbm.Notices;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.opacDAO.*;
import java.util.List;

/**
 *
 * @author Faraz
 */
public class NoticeAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private String library_id,sublibrary_id;
    OpacSearchDAO osdao=new OpacSearchDAO();
    
    NewDemandDAO newdemanddao=new NewDemandDAO();
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
            HttpSession session=(HttpSession)request.getSession();
            library_id=(String)session.getAttribute("library_id");
            sublibrary_id=(String)session.getAttribute("sublibrary_id");
            List notices=newdemanddao.Notice(library_id, sublibrary_id);
            List noticelibray_id=osdao.LibrarySearch(library_id);
            /*Library lib = new Library("sel", "Active");
            lib.setLibraryName("Select");
            if(noticelibray_id!=null) noticelibray_id.add(lib);*/
            List noticesublib=osdao.subLibrarySearch(library_id);
            if(notices!=null)
            {
                
              session.setAttribute("noticesublib",noticesublib);
              session.setAttribute("noticelibray_id",noticelibray_id);
              session.setAttribute("notices",notices);
              return mapping.findForward("success");

            }
            else
            {
              request.setAttribute("msg", "Record Not Selected");
              session.setAttribute("noticesublib",noticesublib);
              session.setAttribute("noticelibray_id",noticelibray_id);
              session.setAttribute("notices",notices);
              return mapping.findForward("success");
            }

       
       
       
    }
}
