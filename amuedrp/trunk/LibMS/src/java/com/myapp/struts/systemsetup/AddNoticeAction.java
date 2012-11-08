/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetup;
import com.myapp.struts.systemsetupDAO.NoticeDAO;
import com.myapp.struts.hbm.*;
import java.util.Locale;
import java.util.ResourceBundle;
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
   Locale locale=null;
   String locale1="en";
   String rtl="ltr";
   String align="left";
   NoticeDAO notdao=new NoticeDAO();
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

      AddNoticeActionForm anc=(AddNoticeActionForm)form;
         HttpSession session=request.getSession();
          try{

        locale1=(String)session.getAttribute("locale");
    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
        System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align = "left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);
        library_id=(String)session.getAttribute("library_id");
         sub_lib=(String)session.getAttribute("sublibrary_id");
        String button=anc.getButton();
        String notice_id=anc.getNotice_id();
         if(button.equals("Add"))
        {
            
         Notices notice=notdao.getNoticeName(library_id, notice_id, sub_lib);
         if(notice!=null)
         {
            //request.setAttribute("msg1", "Notice Id : "+notice_id+" already exists");
            request.setAttribute("msg1", resource.getString("systemsetup.manage_notice.noticesid")+notice_id+resource.getString("systemsetup.manage_notice.alreadyexists"));
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



                          notice=notdao.getNoticeName(library_id, notice_id, sub_lib);
                        if(notice==null)
                        {

                           // request.setAttribute("msg1", "Notice Id: "+notice_id+" doesn't exists");
                            request.setAttribute("msg1", resource.getString("systemsetup.manage_notice.noticesid")+notice_id+resource.getString("systemsetup.manage_notice.doesnotexist"));

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
