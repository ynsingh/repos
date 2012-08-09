/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import com.myapp.struts.AcquisitionDao.AcquisitionDao;
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
public class AcqPaymentUpdateAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    List list= new ArrayList();
    PaymentUpdateClass puc=new PaymentUpdateClass();
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        puc.setPrn("no prn");
        list.add(puc);
        List<PaymentUpdateClass> acqforpaymentupdate=AcquisitionDao.getDistinctPrn(library_id, sub_library_id);
//        if(!acqforpaymentupdate.isEmpty())
         request.setAttribute("acqforpaymentupdate",acqforpaymentupdate);
//        else
//         request.setAttribute("acqforpaymentupdate",list);
       
        return mapping.findForward(SUCCESS);
    }
}
