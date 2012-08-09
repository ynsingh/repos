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
import com.myapp.struts.hbm.AcqRequestpaymentHeaderId;
import java.util.ArrayList;
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
public class AcqPaymentRequest2Action extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String invoice_no,order_no,vendor_id,recieving_no,tamount,net_amt;
    String value[];
    int i,j,no_of_invoices=0;double total_amount=0;
    List<String> l=new ArrayList();
    AcqRequestpaymentHeader acqreqpaymentheader=new AcqRequestpaymentHeader();
    AcqRequestpaymentHeaderId acqreqpaymentheaderid=new AcqRequestpaymentHeaderId();
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        AcqInvoiceItemActionForm aiiaf=(AcqInvoiceItemActionForm)form;
        String prn=aiiaf.getPrn();
        String prn_date=aiiaf.getPrn_date();
        String list=aiiaf.getList();
        String values[]=list.split(";");
        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%"+values.length);
        for(i=0;i<values.length;i++)
        {
           for(j=i+1;j<values.length;j++)
           {
              if(!values[i].split(",")[2].equals(values[j].split(",")[2]))
               {
                break;
               }
           }
           if(j!=values.length)break;
        }
         if((i+1)<values.length)
        {
           request.setAttribute("msg1", "Select all those invoices of same vendor");
           return mapping.findForward("success");
        }

        String o_no=values[0].split(",")[1];
        l.add(o_no);
        for(i=1;i<values.length;i++)
        {

              if(!o_no.equals(values[i].split(",")[1]))
               {
                l.add(values[i].split(",")[1]);
               }

        }


        for(i=0;i<values.length;i++)
        {
           AcqRequestpaymentDetails acqreqpaymentdetails=new AcqRequestpaymentDetails();
           AcqRequestpaymentDetailsId acqreqpaymentdetailsid=new AcqRequestpaymentDetailsId();
           value=values[i].split(",") ;
           acqreqpaymentdetailsid.setLibraryId(library_id);
           acqreqpaymentdetailsid.setSubLibraryId(sub_library_id);
           acqreqpaymentdetailsid.setPrn(prn);
           acqreqpaymentdetailsid.setInvoiceNo(value[0]);
           acqreqpaymentdetailsid.setRecievingNo(value[3]);
           acqreqpaymentdetails.setId(acqreqpaymentdetailsid);
           acqreqpaymentdetails.setVendorId(value[2]);
           acqreqpaymentdetails.setTotalAmt(value[4]);
           acqreqpaymentdetails.setOrderNo(value[1]);
           total_amount=total_amount+Double.parseDouble(value[4]);
           no_of_invoices=no_of_invoices+1;
           AcqInvoiceDetail acqinvdetails=AcquisitionDao.searchByInovoiceDetails(library_id, sub_library_id, value[0],value[3]);
           acqinvdetails.setStatus("rfp");
           boolean result=AcquisitionDao.insertInPaymentRequestDetail(acqreqpaymentdetails,acqinvdetails);
           if(result==false)
           {
              request.setAttribute("msg","Row corresponding invoice no. "+value[0]+" not inserted");
              return mapping.findForward(SUCCESS);
           }

        }


            acqreqpaymentheaderid.setLibraryId(library_id);
            acqreqpaymentheaderid.setSubLibraryId(sub_library_id);
            acqreqpaymentheaderid.setPrn(prn);
         //   acqreqpaymentheaderid.setOrderNo(l.get(i));
            acqreqpaymentheader.setId(acqreqpaymentheaderid);
            acqreqpaymentheader.setPrnDate(prn_date);
            acqreqpaymentheader.setVendorId(values[0].split(",")[2]);
            acqreqpaymentheader.setTotalAmount(String.valueOf(total_amount));
            acqreqpaymentheader.setNoOfInvoices(String.valueOf(no_of_invoices));
            boolean result=AcquisitionDao.insertInPaymentRequestHeader(acqreqpaymentheader);
            if(result==false)
            {
              request.setAttribute("msg","Record not inserted");
              return mapping.findForward(SUCCESS);
            }



        request.setAttribute("msg","Record Inserted Successfully");
        return mapping.findForward(SUCCESS);
    }
}
