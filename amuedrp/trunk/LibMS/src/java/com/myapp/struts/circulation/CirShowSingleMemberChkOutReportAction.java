/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;
import com.myapp.struts.CirDAO.*;
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
public class CirShowSingleMemberChkOutReportAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
         HttpSession session=request.getSession();
         String   library_id=(String)session.getAttribute("library_id");
       String sublibrary_id=(String)session.getAttribute("sublibrary_id");
      String  mem_id=(String)request.getParameter("id");
      String CheckOutId=(String)request.getParameter("ch");

        CirCheckout checkoutdetail=CirculationDAO.searchCheckoutMemDetails(library_id,sublibrary_id, mem_id,CheckOutId);
      //    System.out.println("@@@@@@@@@@"+cmemdetail.getMemberId());

        if(checkoutdetail!=null)
        {
           request.setAttribute("mem_id",mem_id);
           request.setAttribute("checkoutdetail",checkoutdetail);
           return mapping.findForward("success");
        }

        
        return mapping.findForward(SUCCESS);
    }
}
