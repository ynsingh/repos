/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;


import com.myapp.struts.AcquisitionDao.AcquisitionDao;
import com.myapp.struts.AcquisitionDao.VendorDAO;
import com.myapp.struts.AcquisitionDao.AcqOrderDao;
import com.myapp.struts.hbm.*;
import com.myapp.struts.hbm.AcqOrder1;
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
 * @author maqbool
 */
public class AcqDeleteOrderAction extends org.apache.struts.action.Action {
  AcquisitionDao ado= new AcquisitionDao();
  AcqBibliographyDetails acqdao=new AcqBibliographyDetails();
  
  AcqOrderHeader acqordrhr=new AcqOrderHeader();
  AcqOrderHeaderId acqorderhid =new AcqOrderHeaderId();
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private boolean result;
    
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
           AcqPurchaseOrderActionForm acqorder=(AcqPurchaseOrderActionForm)form;
 

          HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
      
        String order_no=acqorder.getOrder_no();



System.out.println(order_no+".........................");

        
       AcqOrderHeader acq= AcquisitionDao.searchOrderHeaderByOrderNo(library_id, sub_library_id, order_no);

           if(acq!=null)
           {
System.out.println(order_no+"....3523523.....................");
               List<ApprovalList> acq2=(List<ApprovalList>)AcquisitionDao.getViewApprovalList(library_id, sub_library_id, order_no);

               System.out.println(acq2.size()+"///////////////////");
               for(int i=0;i<acq2.size();i++){

                   List<AcqOrder1> app=AcquisitionDao.searchAcqOrder(library_id, sub_library_id, acq2.get(i).getControl_no(),order_no);
                        if(!app.isEmpty()){
                        for(int j=0;j<app.size();j++){
                            if(app.get(j).getApprovalItemId()==0){

                         
                            }
                            else
                            {
                                AcqApproval obj=AcquisitionDao.searchAcqApproval(library_id, sub_library_id, app.get(j).getApprovalItemId());
                        System.out.println(obj);
                                 obj.setStatus("pending");
                             
                                result=AcquisitionDao.updateApproval(obj);
                             }
                        }
                        
                      //  }
                        
                        }
                    //For On Approval or Approved Deletion
                        AcqBibliographyDetails st=AcquisitionDao.searchAcqBibByControlNo(library_id, sub_library_id, acq2.get(i).getControl_no());
                        if(st!=null)
                        {
                            if(st.getNoOfCopies()==0)
                            {   
                                    st.setStatus("Fully Approved");
                              }
                              else{
                                  
                                    st.setStatus("Partially Approved");

                                    }
                             if(st.getAcqMode().equalsIgnoreCase("Firm Order")){
                               st.setStatus("");
                             }


                        }
                      boolean   result=AcquisitionDao.updateAcqBib(st);
               }


    acq.setOrderStatus("cancel");
  result= AcqOrderDao.updateAcqOrderHeader(acq);
      if(result==true)
         {




        request.setAttribute("msg", "Order is Cancelled Successfully");
        session.removeAttribute("backlocation");
        return mapping.findForward(SUCCESS);
       }
         else
             {
        request.setAttribute("msg1", "Order is Not Cancelled Successfully");
        session.removeAttribute("backlocation");
        return mapping.findForward(SUCCESS);
       }


           }

return null;
        

    }
}
