/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetup;
import com.myapp.struts.systemsetupDAO.*;
import java.util.List;
import com.myapp.struts.hbm.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author edrp01
 */
public class ViewAllNoticeAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
     String library_id;
      String sub_lib;
   NoticeDAO notdao=new NoticeDAO();
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
             HttpSession session=request.getSession();
             library_id=(String)session.getAttribute("library_id");
              sub_lib=(String)session.getAttribute("sublibrary_id");
             List<Notices> list=(List<Notices>)notdao.searchNotices(library_id, sub_lib);
             session.setAttribute("list", list);
        return mapping.findForward(SUCCESS);
    }
}
