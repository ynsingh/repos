/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetup;
import com.myapp.struts.systemsetupDAO.NoticeDAO;
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
public class AddNoticeAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
     String library_id;
     String sub_lib;
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

      AddNoticeActionForm anc=(AddNoticeActionForm)form;
         HttpSession session=request.getSession();
        library_id=(String)session.getAttribute("library_id");
         sub_lib=(String)session.getAttribute("sublibrary_id");
        String button=anc.getButton();
        String notice_id=anc.getNotice_id();
         if(button.equals("Add"))
        {
         Notices notice=NoticeDAO.getNoticeName(library_id, notice_id, sub_lib);
         if(notice!=null)
         {
            request.setAttribute("msg1", "Notice Id : "+notice_id+" already exists");
            return mapping.findForward("duplicate");
         }
         else
         {

             request.setAttribute("notice_id",notice_id);
             return mapping.findForward("success");


         }
        }

        Notices notice;
        if(button.equals("Update")||button.equals("View")||button.equals("Delete"))
        {



                          notice=NoticeDAO.getNoticeName(library_id, notice_id, sub_lib);
                        if(notice==null)
                        {

                        request.setAttribute("msg1", "Notice Id: "+notice_id+" doesn't exists");

                        return mapping.findForward("duplicate");
                        }






         if(notice_id!=null)
         {



          request.setAttribute("button",button);
          request.setAttribute("notice", notice);
          return mapping.findForward("update/view/delete");
         }



    }


        return mapping.findForward(SUCCESS);
    }
}
