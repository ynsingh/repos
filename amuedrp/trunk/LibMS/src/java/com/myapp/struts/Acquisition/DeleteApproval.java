/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.hbm.AcqBibliography;
import com.myapp.struts.AcquisitionDao.AcquisitionDao;
import com.myapp.struts.AcquisitionDao.BudgetDAO;
import com.myapp.struts.AcquisitionDao.VendorDAO;
import com.myapp.struts.hbm.AcqApproval;
import com.myapp.struts.hbm.AcqCurrency;
import com.myapp.struts.hbm.AcqVendor;
import com.myapp.struts.hbm.AcqBibliographyDetails;
import java.util.List;
/**
 *
 * @author maqbool
 */
public class DeleteApproval extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        AcqBiblioActionForm acqbiblio=(AcqBiblioActionForm)form;
        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        String button=(String)acqbiblio.getButton();
        String approval_no=acqbiblio.getApproval_no();

        List<AcqApproval> approval=AcquisitionDao.searchApproval2(library_id, sub_library_id, approval_no);
        if(!approval.isEmpty()){
            for(int i=0;i<approval.size();i++)
            {
            AcqBibliographyDetails acq=AcquisitionDao.searchAcqBibByControlNo(library_id, sub_library_id,approval.get(i).getControlNo());
            if(acq!=null)
            {       if(acq.getNoOfCopies()==0)
                    {    acq.setAcqMode("On Approval");
                         acq.setStatus("");
                    }else{
                        acq.setAcqMode("On Approval");
                         acq.setStatus("Partially Approved");

                    }
                   acq.setNoOfCopies(acq.getNoOfCopies()+approval.get(i).getNoOfCopies());
                   boolean result= AcquisitionDao.updateAcqBib(acq);
                   if(result==false){
                   System.out.println("Cannot Update"+acq.getId().getControlNo());
                   }
            }






            }



        }


        if(button.equalsIgnoreCase("Delete")){


      boolean result=  AcquisitionDao.deleteApprovalHeader(library_id,sub_library_id,approval_no);
      if(result==true){
          result=  AcquisitionDao.deleteApproval(library_id,sub_library_id,approval_no);
            if(result==true){
                    String msg="Record Deleted Successfully";
                    request.setAttribute("msg", msg);
                    return mapping.findForward("success");
          }else{
                    String msg1="Record not Deleted Successfully";
                    request.setAttribute("msg1", msg1);
                    return mapping.findForward("failure");


          }

      }
      else{
      String msg1="Record not Deleted Successfully";
      request.setAttribute("msg1", msg1);
      return mapping.findForward("failure");

      }

        }

        return null;

    }
}
