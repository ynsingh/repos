/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import com.myapp.struts.AcquisitionDao.AcquisitionDao;
import com.myapp.struts.hbm.AcqRequestpaymentDetails;
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
public class AcqPaymentUpdate1Action extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        AcqInvoiceItemActionForm acqinvoiceaf=(AcqInvoiceItemActionForm)form;
        String prn=acqinvoiceaf.getPrn();
        String button=acqinvoiceaf.getButton();
        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");

        if(button.equals("Process"))
        {
          List<AcqRequestpaymentDetails> acqforpaymentupdate=AcquisitionDao.searchForPrnList(library_id, sub_library_id, prn);
          session.setAttribute("acqforpaymentupdate", acqforpaymentupdate);
          return mapping.findForward("processed");
        }

        if(button.equals("Processed List"))
        {
           List<PaymentUpdateClass2> processedprn=AcquisitionDao.getDistinctPrnProcessed(library_id, sub_library_id);
//           if(!processedprn.isEmpty())
//           {
              session.setAttribute("processedprn", processedprn);
              return mapping.findForward("processed list");
          // }
        }

        return mapping.findForward(SUCCESS);
    }
}
