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
public class AcqPaymentUpdate3Action extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    AcquisitionDao acqdao=new AcquisitionDao();
    
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        AcqInvoiceItemActionForm acqinvoiceaf=(AcqInvoiceItemActionForm)form;
        String prn_date=acqinvoiceaf.getPrn_date();
        String list=acqinvoiceaf.getList();
        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        String prn_no[]=list.split(";");
        for(int i=0;i<prn_no.length;i++)
        {
          List<AcqRequestpaymentDetails> listforupdatedate=acqdao.searchForPrnList1(library_id, sub_library_id, prn_no[i]);
          for(int j=0;j<listforupdatedate.size();j++)
          {
              listforupdatedate.get(j).setPaymentUpdateDate(prn_date);
              boolean result=acqdao.processInPaymentRequestDetail(listforupdatedate.get(j));
              if(result==false)
              {
                request.setAttribute("msg","Date not Updated");
                return mapping.findForward(SUCCESS);
              }
          }

        }

       List<PaymentUpdateClass> acqforpaymentupdate1=acqdao.getDistinctPrn(library_id, sub_library_id);
      // if(!acqforpaymentupdate1.isEmpty())
       session.setAttribute("acqforpaymentupdate",acqforpaymentupdate1);
       request.setAttribute("msg","Date Updated");
       return mapping.findForward(SUCCESS);
    }
}
