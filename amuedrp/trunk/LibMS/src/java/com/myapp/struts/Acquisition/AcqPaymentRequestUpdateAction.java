/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import com.myapp.struts.AcquisitionDao.AcquisitionDao;
import com.myapp.struts.hbm.AcqRequestpaymentHeader;
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
public class AcqPaymentRequestUpdateAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
      
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        AcqInvoiceItemActionForm aiiaf=(AcqInvoiceItemActionForm)form;
        String prn=aiiaf.getPrn();
        String prn_date=aiiaf.getPrn_date();
        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
    
        if(!prn_date.equals(""))
        {
         AcqRequestpaymentHeader acqreqpaymentheader=AcquisitionDao.searchForPrnNo(library_id, sub_library_id, prn);
         acqreqpaymentheader.setPrnDate(prn_date);
         boolean result=AcquisitionDao.insertInPaymentRequestHeader(acqreqpaymentheader);
         if(result==false)
         {
               request.setAttribute("msg","prn date not updated");
               return mapping.findForward(SUCCESS);
         }
      
         request.setAttribute("prn",prn);
         return mapping.findForward(SUCCESS);
        }
     
        request.setAttribute("msg1","prn date not updated");
        return mapping.findForward(SUCCESS);
    }
}
