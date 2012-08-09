/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import com.myapp.struts.AcquisitionDao.AcquisitionDao;
import com.myapp.struts.hbm.AcqInvoiceDetail;
import com.myapp.struts.hbm.AcqRequestpaymentDetails;
import com.myapp.struts.hbm.AcqRequestpaymentDetailsId;
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
public class AcqPaymentRequestDeleteAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String value[];
    int no_of_invoices=0;double total_amount=0,total_amt=0;
    
   
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
    //System.out.println("testtttttttttttttttttttttttttttttttttt"+values);
        for(int i=0;i<values.length;i++)
        {
          value=values[i].split(",") ;
          total_amount=Double.parseDouble(value[5]);
          total_amt=total_amt+Double.parseDouble(value[4]);
          no_of_invoices=no_of_invoices+1;
          AcqRequestpaymentDetails acqreqpaymentdetails=new AcqRequestpaymentDetails();
          AcqRequestpaymentDetailsId acqreqpaymentdetailsid=new AcqRequestpaymentDetailsId();
          acqreqpaymentdetailsid.setLibraryId(library_id);
          acqreqpaymentdetailsid.setSubLibraryId(sub_library_id);
          acqreqpaymentdetailsid.setPrn(value[0]);
          acqreqpaymentdetailsid.setInvoiceNo(value[1]);
          acqreqpaymentdetailsid.setRecievingNo(value[3]);
          acqreqpaymentdetails.setId(acqreqpaymentdetailsid);
          acqreqpaymentdetails.setOrderNo(value[2]);
          AcqInvoiceDetail acqinvdetails=AcquisitionDao.searchByInovoiceDetails(library_id, sub_library_id, value[1],value[3]);
          acqinvdetails.setStatus(" ");
          boolean result=AcquisitionDao.deleteInPaymentRequestDetail(acqreqpaymentdetails,acqinvdetails);
           if(result==false)
           {
              request.setAttribute("msg","Row corresponding invoice no. "+value[0]+" not inserted");
              return mapping.findForward(SUCCESS);
           }

        }
        total_amount=total_amount-total_amt;
        AcqRequestpaymentHeader acqreqpaymentheader=AcquisitionDao.searchForPrnNo(library_id, sub_library_id, values[0].split(",")[0]);
        String noi=acqreqpaymentheader.getNoOfInvoices();
        noi=String.valueOf(Integer.parseInt(noi)-no_of_invoices);
        acqreqpaymentheader.setTotalAmount(String.valueOf(total_amount));
        acqreqpaymentheader.setNoOfInvoices(noi);
        boolean result=AcquisitionDao.insertInPaymentRequestHeader(acqreqpaymentheader);
            if(result==false)
            {
              request.setAttribute("msg","Record not inserted");
              return mapping.findForward(SUCCESS);
            }



        request.setAttribute("msg","Record deleted Successfully");
        return mapping.findForward(SUCCESS);
    }
}
