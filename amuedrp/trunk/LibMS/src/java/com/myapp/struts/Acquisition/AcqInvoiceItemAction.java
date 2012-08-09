/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import com.myapp.struts.AcquisitionDao.AcquisitionDao;
import com.myapp.struts.hbm.AcqInvoiceDetail;
import com.myapp.struts.hbm.AcqInvoiceHeader;
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
public class AcqInvoiceItemAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    List<InvoiceList> l=new ArrayList<InvoiceList>();
    String  o_number;
        
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        AcqInvoiceItemActionForm aiiaf=(AcqInvoiceItemActionForm)form;
        String receiving_no=aiiaf.getReceiving_no();
        String order_no=aiiaf.getOrder_no();
        String invoice_no=aiiaf.getInvoice_no();
        String button=aiiaf.getButton();
        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");

        if(button.equals("New"))
        {
          List<AcqInvoiceHeader> acqinvh=AcquisitionDao.searchByInovoiceHeader(library_id, sub_library_id, invoice_no);
          if(acqinvh.isEmpty())
          {
           List<InvoiceList> listforinvoice=(List<InvoiceList>)AcquisitionDao.getReceivedItems(library_id, sub_library_id, receiving_no, order_no);
           session.setAttribute("listforinvoice", listforinvoice);
           int i=0,j=0;
           for(i=0;i<listforinvoice.size();i++)
           {
               o_number=listforinvoice.get(i).getOrder_no();
               if(i==0)
               {   InvoiceList il=new InvoiceList();
                   il.setOrder_no(o_number);
                   l.add(il);
               }
               else
               {
                 for(j=0;j<l.size();j++)
                  if(l.get(j).getOrder_no().equals(o_number))break;
               }
               
               if(j==l.size())
               {
                 InvoiceList il=new InvoiceList();
                 il.setOrder_no(o_number);
                 l.add(il);
               }
           }
           for(i=0;i<l.size();i++)
           request.setAttribute("l",l);
           request.setAttribute("invoice_no",invoice_no);
           l=new ArrayList<InvoiceList>();
           return mapping.findForward(SUCCESS);
          }
          else
          {
            request.setAttribute("msg1","Invoice No. already exist");
            return mapping.findForward("fail");
          }
        }

        if(button.equals("View All"))
        {
            List<AcqInvoiceHeader> acqinvoiceheader=AcquisitionDao.allByInovoiceHeader(library_id, sub_library_id);
            request.setAttribute("acqinvoiceheader", acqinvoiceheader);
            return mapping.findForward("viewall");
        }

        if(button.equals("View"))
        {
            List<AcqInvoiceDetail> acqinvoicedetails=AcquisitionDao.searchInovoiceDetails(library_id, sub_library_id, invoice_no);
            request.setAttribute("acqinvoicedetails", acqinvoicedetails);
            return mapping.findForward("view");
        }
        return mapping.findForward(SUCCESS);
    }
}
