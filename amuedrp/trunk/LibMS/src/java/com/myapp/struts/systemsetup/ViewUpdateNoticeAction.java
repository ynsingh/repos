/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetup;
import com.myapp.struts.systemsetupDAO.*;
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
public class ViewUpdateNoticeAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    boolean result;
    String sub_lib;
   Locale locale=null;
   String locale1="en";
   String rtl="ltr";
   String align="left";
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
NoticeDAO notdao=new NoticeDAO();
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
       AddNoticeActionForm acn =(AddNoticeActionForm)form;
       String button= acn.getButton();
      String notice_id =acn.getNotice_id();
     
      String  library_id=(String)session.getAttribute("library_id");
      String  sub_lib=(String)session.getAttribute("sublibrary_id");
     Notices notice = notdao.getNoticeName(library_id, notice_id, sub_lib);

     System.out.println("Update............."+button);
      if(button.equals("Update"))
        {
      //       faculty.setFacultyId(faculty_id);
             notice.setDate(acn.getDate());
             notice.setDetail(acn.getDetail());
            // notice.setSot(acn.getSot());
             notice.setSubject(acn.getSubject());
             result=notdao.update(notice);
             if(result==true)
             {
                 //request.setAttribute("msg", "Record Update Successfully");
                 request.setAttribute("msg",  resource.getString("circulation.circulationnewmemberregAction.recupdatesucc"));
              return mapping.findForward("success");
             }
             else
             {
                // request.setAttribute("msg", "Record Not Update");
                 request.setAttribute("msg", resource.getString("circulation.circulationnewmemberregAction.recupdatenotsecc"));
              return mapping.findForward("success");
             }

        }

      if(button.equals("Delete"))
        {
            result=notdao.Delete(notice);
             if(result==true)
             {
                 //request.setAttribute("msg", "Record Deleted Successfully");
                 request.setAttribute("msg", resource.getString("circulation.circulationnewmemberregAction.recdelsucc"));
              return mapping.findForward("success");
             }
             else
             {
                // request.setAttribute("msg", "Record Not Deleted");
                request.setAttribute("msg", resource.getString("circulation.circulationnewmemberregAction.memnotdelsucc"));
              return mapping.findForward("success");
             }


        }



        return mapping.findForward(SUCCESS);
    }
}
