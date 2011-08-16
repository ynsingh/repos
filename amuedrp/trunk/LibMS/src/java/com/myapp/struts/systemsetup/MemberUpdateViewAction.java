/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetup;

import com.myapp.struts.hbm.*;
import com.myapp.struts.systemsetupDAO.*;
import java.util.List;
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
 * @author edrp02
 */
public class MemberUpdateViewAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String emptype_id;
    String emptype_full_name;
    String button;
    String library_id;
    boolean result;
    Locale locale=null;
   String locale1="en";
   String rtl="ltr";
   String align="left";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

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
       MemberUpdateViewActionForm muvaf=(MemberUpdateViewActionForm)form;
       emptype_id=muvaf.getEmptype_id();
       emptype_full_name=muvaf.getEmptype_full_name();
       button=muvaf.getButton();
       
       library_id=(String)session.getAttribute("library_id");
       EmployeeType emptype=MemberDAO.getEployeeName(library_id, emptype_id);
       
        if(button.equals("Update"))
        {
             
             result=MemberDAO.update(emptype,library_id,emptype_full_name);
             if(result==true)
             {
                 // request.setAttribute("msg", "Record Update Successfully");
                 request.setAttribute("msg", resource.getString("circulation.circulationnewmemberregAction.recupdatesucc"));
              return mapping.findForward("success");
             }
             else
             {
                 //request.setAttribute("msg", "Record Not Update");
                 request.setAttribute("msg", resource.getString("circulation.circulationnewmemberregAction.recupdatenotsecc"));
              return mapping.findForward("success");
             }

        }
        if(button.equals("Delete"))
        {
           List<CirMemberAccount> cir=   (List<CirMemberAccount>)MemberDAO.searchAccount(library_id,emptype_id);
           if(!cir.isEmpty()){

            //  request.setAttribute("msg1", "Account Created With This Member,Cannot Deleted");
            request.setAttribute("msg1", resource.getString("systemsetup.memberupdateviewaction.acccreatedwithtiscanotdel"));
              return mapping.findForward("success");
           }


            result=MemberDAO.Delete(emptype);
             if(result==true)
             {
               // request.setAttribute("msg", "Record Deleted Successfully");
              request.setAttribute("msg", resource.getString("circulation.circulationnewmemberregAction.recdelsucc"));
              return mapping.findForward("success");
             }
             else
             {

                List<SubEmployeeType> sub=(List<SubEmployeeType>)SubMemberDAO.searchSubEmployeeType(library_id,emptype_id);
                if(!sub.isEmpty())
                {

                   // request.setAttribute("msg1", "SubMemberCategory has related record Cannot be Deleted");
                request.setAttribute("msg1", resource.getString("systemsetup.memberupdateviewaction.hasrelreccanotdel"));
              return mapping.findForward("success");
                }

               // request.setAttribute("msg1", "Record Not Deleted");
              request.setAttribute("msg1", resource.getString("circulation.circulationnewmemberregAction.memnotdelsucc"));
              return mapping.findForward("success");
             }


        }


        return mapping.findForward("suc");

        
    }
}
