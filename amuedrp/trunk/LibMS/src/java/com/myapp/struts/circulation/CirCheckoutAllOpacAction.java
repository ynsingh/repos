/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;

import com.myapp.struts.circulationDAO.cirDAO;
import com.myapp.struts.hbm.*;
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
public class CirCheckoutAllOpacAction extends org.apache.struts.action.Action {
    
  
    String library_id;
    String sublibrary_id;
    
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception 
    {

       HttpSession session=request.getSession();
       library_id=(String)session.getAttribute("library_id");
       sublibrary_id=(String)session.getAttribute("sublibrary_id");

       List<CirOpacRequest> ciropac=(List<CirOpacRequest>)cirDAO.getOpacCheckOut(library_id, sublibrary_id,"Pending");
       System.out.println(ciropac.size());
       if(!ciropac.isEmpty())
       {
           session.setAttribute("opaclist1", ciropac);
           return mapping.findForward("success");

       }else{
       session.setAttribute("status","no record found");
            return mapping.findForward("success");
       }


      
    }
}
