/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;

import com.myapp.struts.CirculationDAO.CirculationDAO;
import com.myapp.struts.hbm.*;
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
public class CirCheckoutAllOpacAction extends org.apache.struts.action.Action {
    
  
    String library_id;
    String sublibrary_id;
     Locale locale=null;
   String locale1="en";
   String rtl="ltr";
   String align="left";

   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception 
    {

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

       List<CirOpacRequest> ciropac=(List<CirOpacRequest>)CirculationDAO.getOpacCheckOut(library_id, sublibrary_id,"Pending");
       System.out.println(ciropac.size());
       if(!ciropac.isEmpty())
       {
           session.setAttribute("opaclist1", ciropac);
           return mapping.findForward("success");

       }else{

          // session.setAttribute("status","no record found");
           session.setAttribute("status",resource.getString("circulation.cir_viewall_mem_detail.norecfond"));
            return mapping.findForward("success");
       }


      
    }
}
