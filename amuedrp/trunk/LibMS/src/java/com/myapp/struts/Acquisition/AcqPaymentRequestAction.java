/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import com.myapp.struts.AcquisitionDao.AcquisitionDao;
import com.myapp.struts.hbm.AcqRequestpaymentHeader;
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
public class AcqPaymentRequestAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        AcqInvoiceItemActionForm aiiaf=(AcqInvoiceItemActionForm)form;
        String prn=aiiaf.getPrn();
        String button=aiiaf.getButton();
        String invoice_no=aiiaf.getInvoice_no();
        String order_no=aiiaf.getOrder_no();
        String vendor_id=aiiaf.getVendor_id();
        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        
        if(button.equals("Process"))
        {
            List<RequestForPayment> acqprn=AcquisitionDao.getViewPrn(library_id, sub_library_id, prn) ;
            if(acqprn.isEmpty())
            {
              AcqRequestpaymentHeader acqreqheader=AcquisitionDao.searchForPrnNo(library_id, sub_library_id, prn);
              if(acqreqheader!=null)
              {
                  if(acqreqheader.getStatus().equals("processed"))
                      request.setAttribute("msg1", "This prn has already processed");
                  else
                      if(acqreqheader.getStatus().equals("processing"))
                         request.setAttribute("msg1", "This prn is processing");
                      else
                         request.setAttribute("msg1", "This prn is not exist");
              }
              else
                  request.setAttribute("msg1", "This prn is not exist");
              return mapping.findForward("fail");
            }
            else
            {
              request.setAttribute("acqprn",acqprn);
              return mapping.findForward("process");
            }
        }

        if(button.equals("New"))
        {
           List<AcqRequestpaymentHeader> acqprn=AcquisitionDao.searchForPrn(library_id, sub_library_id, prn);
           if(!acqprn.isEmpty())
           {
             request.setAttribute("msg1", "This prn is already exist");
             return mapping.findForward("fail");
           }
           else
           {
             List<AllInvoiceList> allinvoice=AcquisitionDao.getAllInvoice(library_id, sub_library_id,invoice_no,order_no,vendor_id);
             session.setAttribute("allinvoice",allinvoice);
             request.setAttribute("prn", prn);
             return mapping.findForward(SUCCESS);
           }

        }

         if(button.equals("View"))
        {
            List<RequestForPayment> acqprn=AcquisitionDao.getViewPrn(library_id, sub_library_id, prn) ;
            if(acqprn.isEmpty())
            {
              AcqRequestpaymentHeader acqreqheader=AcquisitionDao.searchForPrnNo(library_id, sub_library_id, prn);
              if(acqreqheader!=null)
              {
                  if(acqreqheader.getStatus().equals("processed"))
                      request.setAttribute("msg1", "This prn has already processed");
                  else
                      if(acqreqheader.getStatus().equals("processing"))
                         request.setAttribute("msg1", "This prn is processing");
                      else
                         request.setAttribute("msg1", "This prn is not exist");
              }
              else
                  request.setAttribute("msg1", "This prn is not exist");

              return mapping.findForward("fail");
            }
            else
            {
              request.setAttribute("acqprn",acqprn);
              return mapping.findForward("view");
            }
        }

         if(button.equals("Update"))
        {
            List<RequestForPayment> acqprn=AcquisitionDao.getViewPrn(library_id, sub_library_id, prn) ;
            if(acqprn.isEmpty())
            {
              AcqRequestpaymentHeader acqreqheader=AcquisitionDao.searchForPrnNo(library_id, sub_library_id, prn);
              if(acqreqheader!=null)
              {
                  if(acqreqheader.getStatus().equals("processed"))
                      request.setAttribute("msg1", "This prn has already processed");
                  else
                      if(acqreqheader.getStatus().equals("processing"))
                         request.setAttribute("msg1", "This prn is processing");
                      else
                         request.setAttribute("msg1", "This prn is not exist");
              }
              else
                  request.setAttribute("msg1", "This prn is not exist");

              return mapping.findForward("fail");
            }
            else
            {
              request.setAttribute("acqprn",acqprn);
              request.setAttribute("prn",prn);
              return mapping.findForward("update");
            }
        }

         if(button.equals("Delete"))
        {
            List<RequestForPayment> acqprn=AcquisitionDao.getViewPrn(library_id, sub_library_id, prn) ;
            if(acqprn.isEmpty())
            {
              AcqRequestpaymentHeader acqreqheader=AcquisitionDao.searchForPrnNo(library_id, sub_library_id, prn);
              if(acqreqheader!=null)
              {
                  if(acqreqheader.getStatus().equals("processed"))
                      request.setAttribute("msg1", "This prn has already processed");
                  else
                      if(acqreqheader.getStatus().equals("processing"))
                         request.setAttribute("msg1", "This prn is processing");
                      else
                         request.setAttribute("msg1", "This prn is not exist");
              }
              else
                  request.setAttribute("msg1", "This prn is not exist");

              return mapping.findForward("fail");
            }
            else
            {
              request.setAttribute("acqprn",acqprn);
              return mapping.findForward("delete");
            }
        }

         if(button.equals("Process List"))
        {
            List<AcqRequestpaymentHeader> acqprn=AcquisitionDao.searchForAllPrn1(library_id, sub_library_id);
            if(acqprn.isEmpty())
            {
              request.setAttribute("msg1", "No prn list exist");
              return mapping.findForward("fail");
            }
            else
            {
              request.setAttribute("acqprn",acqprn);
              return mapping.findForward("view_all_list");
            }
         }

          if(button.equals("UnProcess List"))
        {
            List<AcqRequestpaymentHeader> acqprn=AcquisitionDao.searchForAllPrn2(library_id, sub_library_id);
            if(acqprn.isEmpty())
            {
              request.setAttribute("msg1", "No prn list exist");
              return mapping.findForward("fail");
            }
            else
            {
              request.setAttribute("acqprn",acqprn);
              return mapping.findForward("view_all_list");
            }
         }

         if(button.equals("View All"))
        {
            List<AcqRequestpaymentHeader> acqprn=AcquisitionDao.searchForAllPrn(library_id, sub_library_id);
            if(acqprn.isEmpty())
            {
              request.setAttribute("msg1", "No prn list exist");
              return mapping.findForward("fail");
            }
            else
            {
              request.setAttribute("acqprn",acqprn);
              return mapping.findForward("view_all_list");
            }
         }
       return mapping.findForward(SUCCESS);
    }
}
