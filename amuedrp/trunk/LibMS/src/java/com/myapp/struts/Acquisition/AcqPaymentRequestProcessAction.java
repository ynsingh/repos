/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import com.myapp.struts.AcquisitionDao.AcquisitionDao;
import com.myapp.struts.hbm.AcqRequestpaymentDetails;
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
public class AcqPaymentRequestProcessAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    AcquisitionDao acqdao=new AcquisitionDao();
    String value[];
  
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        AcqInvoiceItemActionForm aiiaf=(AcqInvoiceItemActionForm)form;
        String list=aiiaf.getList();
        String values[]=list.split(";");
        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        for(int i=0;i<values.length;i++)
        {

          value=values[i].split(",") ;
          AcqRequestpaymentDetails acqreqpaymentdetails=acqdao.ProcessForPrnList(library_id, sub_library_id,value[0],value[1],value[2]);
          if(acqreqpaymentdetails!=null)
          {
               acqreqpaymentdetails.setStatus("Process");
               boolean result=acqdao.processInPaymentRequestDetail(acqreqpaymentdetails);
               if(result==false)
               {
                 request.setAttribute("msg1","Row corresponding prn no. ="+value[0]+" not updated");
                 return mapping.findForward(SUCCESS);
               }


          }
        }

        AcqRequestpaymentHeader acqreqpaymentheader=acqdao.searchForPrnNo(library_id, sub_library_id,values[0].split(",")[0] );
        acqreqpaymentheader.setStatus("processing");
        boolean res=acqdao.insertInPaymentRequestHeader(acqreqpaymentheader);
        if(res==false)
        {
          request.setAttribute("msg1","Request not Processed");
          return mapping.findForward(SUCCESS);
        }
        request.setAttribute("msg","Request Processed");
        return mapping.findForward(SUCCESS);
    }
}
