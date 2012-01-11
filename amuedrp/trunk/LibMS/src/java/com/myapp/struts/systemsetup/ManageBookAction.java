/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetup;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.systemsetupDAO.*;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author edrp01
 */
public class ManageBookAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String library_id,sublibrary_id;
    List list1,list2,list3;
   Locale locale=null;
   String locale1="en";
   String rtl="ltr";
   String align="left";
   
    @Override
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
        library_id=(String)session.getAttribute("library_id");
        sublibrary_id=(String)session.getAttribute("sublibrary_id");

   list1=(List)MemberCategoryDAO.searchEmpType(library_id);
   list2=(List)MemberCategoryDAO.searchSubEmpType(library_id);
   list3=(List)DocumentCategoryDAO.listdoccategory1(library_id,sublibrary_id);
  if((list1.isEmpty() && list1==null)||(list2.isEmpty() && list2==null)){
  // String msg="You need to set member and submembers";
  String msg=resource.getString("systemsetup.managebookaction.youneedtosetmember");
  request.setAttribute("msg", msg);
    return mapping.findForward("success"); 
  }
   if((!list1.isEmpty())&& !(list2.isEmpty()))
   {
      session.setAttribute("list1",list1);
      session.setAttribute("list2",list2);
      session.setAttribute("list3", list3);
      return mapping.findForward("success"); 
   }

        
        return mapping.findForward("SUCCESS");
    }
}
