/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;


import com.myapp.struts.AcquisitionDao.AcqOrderDao;
import com.myapp.struts.AcquisitionDao.AcquisitionDao;
import com.myapp.struts.hbm.AcqApproval;
import com.myapp.struts.hbm.*;
import com.myapp.struts.hbm.AcqOrder1;
import com.myapp.struts.hbm.AcqOrderHeader;
import com.myapp.struts.hbm.AcqVendor;
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
 * @author EdRP-05
 */
public class AcqInvoiceAction extends org.apache.struts.action.Action {
     AcquisitionDao ado= new AcquisitionDao();
     AcqOrderDao acqodao=new AcqOrderDao();
  AcqOrderHeader acqordrhr=new AcqOrderHeader();
   List<ApprovalList> acqorder1= new ArrayList<ApprovalList>();

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {


        AcqOrderManagementActionForm aomd=new AcqOrderManagementActionForm();

                String vendor=aomd.getVendor();
         HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        String order_no=aomd.getOrder_no();
        String vendor_id=aomd.getVendor();
        String t1=request.getParameter("recievingno");
        if(t1!=null)
        {
            AcqRecievingHeader acqrhrdr=AcquisitionDao.searchOrderHeaderByRecievingNo(library_id, sub_library_id,t1);
       
             if(acqrhrdr!=null){
            List<AcqRecievingDetails> acq2=AcquisitionDao.searchRecieveByRecievingNo(library_id, sub_library_id, t1);
            if(!acq2.isEmpty())
            {
                for(int i=0;i<acq2.size();i++)
                {
                    
                      acqordrhr=acqodao.searchOrderHeaderByVendor(order_no, vendor_id, library_id, sub_library_id);
                      acqorder1=acqodao.searchOrderByVendor1(order_no,library_id, sub_library_id,t1);
                      session.setAttribute("opacList2",acqorder1);
                     return mapping.findForward("duplicateapprovalno");

                }
            }

        }

        }

       
      /*  if(t!=null)
        {
            AcqOrderHeader acq= AcquisitionDao.searchOrderHeaderByOrderNo(library_id, sub_library_id, t);

           if(acq!=null)
           {



             List<AcqOrder1> acq1=AcquisitionDao.searchOrderByOrderNo(library_id, sub_library_id, t);
             System.out.println("The List Size in Order no"+acq1.size());
            if(!acq1.isEmpty()){

            for(int i=0;i<acq1.size();i++)
            {
                    int con=acq1.get(i).getControlNo();
                     System.out.println("The List control no in Order no"+con +"....");

                    AcqApproval app=AcquisitionDao.searchApprovalStatus1(library_id, sub_library_id, con,acq1.get(i).getApprovalItemId());
                    System.out.println("Control No....List"+app);
                    if(app!=null)
                    {
                           System.out.println("The control no is of Approval type"+con +"....");
                    //For On Approval or Approved Deletion
                        AcqBibliographyDetails st=AcquisitionDao.searchAcqBibByControlNo(library_id, sub_library_id, con);
                        if(st!=null)
                        {
                            if(st.getNoOfCopies()==0)
                            {    st.setAcqMode("Approved");
                                    st.setStatus("Fully Approved");
                              }
                              else{
                                    st.setAcqMode("On Approval");
                                    st.setStatus("Partially Approved");

                                    }

                            }
              boolean   result=AcquisitionDao.updateAcqBib(st);
              app.setStatus("pending");
              boolean result1=AcquisitionDao.updateApproval(app);


       }
       else{
       //for Firm Order Deletion
                          System.out.println("The control is of Firm Order "+con +"....");
              AcqBibliographyDetails st=AcquisitionDao.searchAcqBibByControlNo(library_id, sub_library_id, con);
           if(st!=null){
            st.setAcqMode("Firm Order");
            st.setStatus("");
           }
            boolean   result=AcquisitionDao.updateAcqBib(st);

       }

       }



       }
           }
 boolean result=   AcqOrderDao.delete1(library_id,sub_library_id, t);


        }*/


        List<String> acqvendor=AcquisitionDao.searchDoc6(library_id, sub_library_id);
        
ArrayList list1=new ArrayList();
for(int i=0;i<acqvendor.size();i++){
String v=acqvendor.get(i);
AcqBibliographyDetails acqv=new AcqBibliographyDetails();
acqv.setVendor(v);
list1.add(acqv);
}
         session.setAttribute("vendors", list1);
         
        return mapping.findForward("order");
    }
}
