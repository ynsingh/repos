/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;

import com.myapp.struts.CirculationDAO.CirculationDAO;
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
        
        session.removeAttribute("cir_checkout_report");
        library_id=(String)session.getAttribute("library_id");
        sublibrary_id=(String)session.getAttribute("sublibrary_id");
        
        List cir_checkout_report=CirculationDAO.CheckoutReport(library_id,sublibrary_id, starting_date,end_date, memid);
       System.out.println("@@@@@@@@"+cir_checkout_report.size());

        if(!cir_checkout_report.isEmpty())
        {
         
          session.setAttribute("cir_checkout_report",cir_checkout_report);
          return mapping.findForward("success");

        }
        
        return mapping.findForward("success");
    }
}
