/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import com.myapp.struts.AcquisitionDao.AcquisitionDao;
import com.myapp.struts.hbm.AcqOrderHeader;
import com.myapp.struts.hbm.AcqRecievingDetails;
import com.myapp.struts.hbm.AcqRecievingHeader;
import com.myapp.struts.hbm.AcqVendor;
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
public class AcqReceiveItem11Action extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
         AcqReceiveItemActionForm acqreceivingaf=(AcqReceiveItemActionForm)form;
        String recieving_no=acqreceivingaf.getRecieving_no();
        String button=acqreceivingaf.getButton();
        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        AcquisitionDao acqdao=new AcquisitionDao();
        if(button.equals("New"))
        {
           AcqRecievingHeader acqrecheader=acqdao.searchByRecievingNo(library_id, sub_library_id, recieving_no);
           
           if(acqrecheader!=null)
           {
               request.setAttribute("msg1","This Receiving No. already exist");
               return mapping.findForward(SUCCESS);
           }
           List<AcqVendor> acqvendor=acqdao.searchDoc7(library_id, sub_library_id);
           List<AcqOrderHeader> acqvendor1=acqdao.searchOrderHeader(library_id, sub_library_id);
           List<PlacedOrderList> placedorderlist=acqdao.getOrderPlaced(library_id, sub_library_id);
           
           session.setAttribute("placedorderlist",placedorderlist);
           request.setAttribute("acqvendor", acqvendor);
           request.setAttribute("acqvendor1", acqvendor1);
           request.setAttribute("recieving_no", recieving_no);
           return mapping.findForward("new");
        }

        if(button.equals("View"))
        {
           AcqRecievingHeader acqrecheader=acqdao.searchByRecievingNo(library_id, sub_library_id, recieving_no);
           if(acqrecheader==null)
           {
               request.setAttribute("msg1","This Receiving No. not exist");
               return mapping.findForward(SUCCESS);
           }
           List<AcqRecievingDetails> acqRecDetailsList=acqdao.searchRecievingDetailsByReceivingNo(library_id, sub_library_id, recieving_no);
           request.setAttribute("acqRecDetailsList", acqRecDetailsList);
           return mapping.findForward("view");
        }

        if(button.equals("View All"))
        {
           List<AcqRecievingHeader> acqRecHeaderList=acqdao.searchForReceivinggNo(library_id, sub_library_id);
           request.setAttribute("acqRecHeaderList", acqRecHeaderList);
           return mapping.findForward("viewall");

        }
        return mapping.findForward(SUCCESS);
    }
}
