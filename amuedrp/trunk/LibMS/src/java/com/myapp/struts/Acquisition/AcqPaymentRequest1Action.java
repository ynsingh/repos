/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import com.myapp.struts.AcquisitionDao.AcquisitionDao;
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
public class AcqPaymentRequest1Action extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    AcquisitionDao acqdao=new AcquisitionDao();
  
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        AcqInvoiceItemActionForm aiiaf=(AcqInvoiceItemActionForm)form;
        String invoice_no=aiiaf.getInvoice_no();
        String order_no=aiiaf.getOrder_no();
        String vendor_id=aiiaf.getVendor_id();
        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        System.out.println("INVOICE NO="+invoice_no+"ORDER NO="+order_no+"VENDOR ID="+vendor_id);
        List<AllInvoiceList> allinvoice=acqdao.getAllInvoice(library_id, sub_library_id,invoice_no,order_no,vendor_id);
        session.setAttribute("allinvoice",allinvoice);
        
        return mapping.findForward(SUCCESS);
    }
}
