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
public class AcqReceiveItem1Action extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        AcqReceiveItemActionForm ariaf=(AcqReceiveItemActionForm)form;
        String order_no=ariaf.getOrder_no();
        String checkbox=ariaf.getCheckbox();
        String sname=ariaf.getSname();
        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
       // int maxreceivingid=AcquisitionDao.returnReceivingItemId(library_id, sub_library_id);
        List<PlacedOrderList> placedorderlist=AcquisitionDao.getOrderPlaced1(library_id, sub_library_id,order_no,checkbox,sname);
        session.setAttribute("placedorderlist",placedorderlist);
        
        return mapping.findForward(SUCCESS);
    }
}
