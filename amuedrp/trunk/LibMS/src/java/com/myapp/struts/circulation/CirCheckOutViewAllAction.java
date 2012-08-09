/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;

import com.myapp.struts.opacDAO.CheckoutDeocumentDetails;
import com.myapp.struts.opacDAO.CirRequestfromOpacDAO;
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
public class CirCheckOutViewAllAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String library_id,sublibrary_id,starting_date,end_date,memid;
    
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
            HttpSession session=request.getSession();



        CirCheckOutViewAllActionForm ccvaaf=(CirCheckOutViewAllActionForm)form;
        starting_date=ccvaaf.getStarting_date();
        end_date=ccvaaf.getEnd_date();
        memid=ccvaaf.getMemid();
        String title=ccvaaf.getTitle();
        
        session.removeAttribute("cir_checkout_report");
        library_id=(String)session.getAttribute("library_id");
        sublibrary_id=(String)session.getAttribute("sublibrary_id");

        List<CheckoutDeocumentDetails>  requestList=null;



requestList = (List<CheckoutDeocumentDetails>)CirRequestfromOpacDAO.getCheckOuts(library_id, sublibrary_id, memid,starting_date,end_date,title);
System.out.println("size="+requestList.size());

session.setAttribute("membercheckoutDetail", requestList);


        return mapping.findForward("success");
    }
}
