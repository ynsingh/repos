/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import com.myapp.struts.AcquisitionDao.AcquisitionDao;
import com.myapp.struts.hbm.AcqBudgetAllocation;
import com.myapp.struts.hbm.AcqBudgetTransaction;
import com.myapp.struts.hbm.AcqInvoiceDetail;
import com.myapp.struts.hbm.AcqRecievingDetails;
import com.myapp.struts.hbm.AcqRequestpaymentDetails;
import com.myapp.struts.hbm.AcqRequestpaymentHeader;
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
public class AcqPaymentUpdate2Action extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    List<Integer> list=new ArrayList<Integer>();

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        AcqInvoiceItemActionForm acqinvoiceaf=(AcqInvoiceItemActionForm)form;
        String prn_date=acqinvoiceaf.getPrn_date();
        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");

        String date[]=prn_date.split("-");
       

        List<AcqRequestpaymentDetails> acqforpaymentupdate=(List<AcqRequestpaymentDetails>)session.getAttribute("acqforpaymentupdate");
        for(int i=0; i<acqforpaymentupdate.size();i++)
        {
            String prn=acqforpaymentupdate.get(i).getId().getPrn();
            String invoice_no=acqforpaymentupdate.get(i).getId().getInvoiceNo();
            String recieving_no=acqforpaymentupdate.get(i).getId().getRecievingNo();
            String order_no=acqforpaymentupdate.get(i).getOrderNo();
            String vendor_id=acqforpaymentupdate.get(i).getVendorId();
            String tot_amt=acqforpaymentupdate.get(i).getTotalAmt();
            acqforpaymentupdate.get(i).setStatus("processed");
            acqforpaymentupdate.get(i).setPaymentUpdateDate(prn_date);

            AcqInvoiceDetail acqinvdetail=AcquisitionDao.searchByInovoiceDetails(library_id, sub_library_id, invoice_no, recieving_no);
            int recieving_item_id=acqinvdetail.getRecievingItemId();

            AcqRecievingDetails acqrecdetails=AcquisitionDao.searchRecievingDetailsByKey(library_id, sub_library_id, recieving_no, recieving_item_id);
            int control_no=acqrecdetails.getControlNo();
            int j=0;
            AcqBudgetTransaction acqbudgettranc=null;
            String acqbudgetheadid=null;
            for(j=0;j<list.size();j++)
            {
               if(list.get(j)!=control_no)
               {
                  list.add(control_no);
                  acqbudgettranc=AcquisitionDao.acqBudgetTransactionControlNo(library_id,String.valueOf(control_no));
                  acqbudgetheadid=acqbudgettranc.getAcqBudgetHeadId();
                  acqbudgettranc.setExpenseAmount(tot_amt);
               }
               else
               {
                  acqbudgettranc=AcquisitionDao.acqBudgetTransactionControlNo(library_id,String.valueOf(control_no));
                  acqbudgetheadid=acqbudgettranc.getAcqBudgetHeadId();
                  acqbudgettranc.setExpenseAmount(acqbudgettranc.getExpenseAmount()+","+tot_amt);
               }
            }
            if(j==0)
            {
                 list.add(control_no);
                 acqbudgettranc=AcquisitionDao.acqBudgetTransactionControlNo(library_id,String.valueOf(control_no));
                 acqbudgetheadid=acqbudgettranc.getAcqBudgetHeadId();
                 acqbudgettranc.setExpenseAmount(tot_amt);
            }



            AcqBudgetAllocation acqbudgetallocation=AcquisitionDao.acqBudgetAllocationDelete(library_id, acqbudgetheadid,date[0]);
            String expense_amount=acqbudgetallocation.getExpenseAmount();
            if(expense_amount==null)
            {
               expense_amount="0";
            }    
            expense_amount=String.valueOf(Double.parseDouble(expense_amount)+Double.parseDouble(tot_amt));
            acqbudgetallocation.setExpenseAmount(expense_amount);
            
            boolean result=AcquisitionDao.paymentUpdate(acqforpaymentupdate.get(i), acqbudgettranc, acqbudgetallocation); 
            if(result==false)
            {
              request.setAttribute("msg","Payment not updated");
              return mapping.findForward(SUCCESS);
            }    
           
           

        }
         AcqRequestpaymentHeader acqreqpaymentheader=AcquisitionDao.searchForPrnNo(library_id, sub_library_id, acqforpaymentupdate.get(0).getId().getPrn());
         acqreqpaymentheader.setStatus("processed");
         boolean result=AcquisitionDao.insertInPaymentRequestHeader(acqreqpaymentheader);
         if(result==false)
         {
            request.setAttribute("msg","Payment not updated");
            return mapping.findForward(SUCCESS);
         }

         List<PaymentUpdateClass> acqforpaymentupdate1=AcquisitionDao.getDistinctPrn(library_id, sub_library_id);
    //     if(!acqforpaymentupdate1.isEmpty())
         session.setAttribute("acqforpaymentupdate",acqforpaymentupdate1);
         request.setAttribute("msg","Payment updated");
         return mapping.findForward(SUCCESS);
    }
}
