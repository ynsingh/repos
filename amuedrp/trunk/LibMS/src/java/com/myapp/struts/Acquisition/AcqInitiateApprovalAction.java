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
import com.myapp.struts.hbm.*;
import com.myapp.struts.AcquisitionDao.AcquisitionDao;
import com.myapp.struts.AcquisitionDao.VendorDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maqbool
 */
public class AcqInitiateApprovalAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    AcquisitionDao acqdao=new AcquisitionDao();
    AcqBibliographyDetails acqb=new AcqBibliographyDetails();
    AcqApprovalHeader acqaph=new AcqApprovalHeader();
    AcqApprovalHeaderId  acqaphid=new AcqApprovalHeaderId();

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        AcqBiblioActionForm aiaaf=(AcqBiblioActionForm)form;
         HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        String button=aiaaf.getButton();
        String approval_no=aiaaf.getApproval_no();
        List<AcqVendor> acqvendor=VendorDAO.searchDoc5(library_id, sub_library_id);
        List<ApprovalList> aa=acqdao.searchOnApprovalList(library_id, sub_library_id);
        request.setAttribute("button", button);
 if(button.equals("New")){
     session.setAttribute("vendors", acqvendor);
     session.setAttribute("opacList", aa);
     acqaph=acqdao.search1Approvalno(approval_no, library_id, sub_library_id);
     if(acqaph !=null)
     {
         String msg1="This Approval No is Already Exist";
         request.setAttribute("msg1", msg1);
         return mapping.findForward("duplicateapprovalno");
     }
      List<AcqBibliographyDetails> aa2=acqdao.getPendingApproval(library_id, sub_library_id);
      if(aa2.isEmpty()){

       String msg1="We have No Pending List to approved";
         request.setAttribute("msg1", msg1);
         return mapping.findForward("duplicateapprovalno");

      }



       return mapping.findForward("newapproval");
        }       
        if(button.equals("View")){
       
          AcqApprovalHeader aa1=acqdao.getApproval(library_id, sub_library_id,approval_no);
          if(aa1==null){
                    String msg1="This Approval No Not Found";
                    request.setAttribute("msg1", msg1);
                    return mapping.findForward("duplicateapprovalno");
            }
aiaaf.setApproval_no(approval_no);
aiaaf.setApproval_date(aa1.getApprovalDate());
aiaaf.setApproved_by(aa1.getApprovedBy());
aiaaf.setRecommended_by(aa1.getRecommendedBy());
aiaaf.setVendor(aa1.getVendorId());

          List<AcqApproval> aa2=acqdao.getApproval1(library_id, sub_library_id,approval_no);
          ArrayList<ApprovalList> acqbib=null;
       
       
          if(!aa2.isEmpty())

           acqbib=acqdao.getBibByControlNo(library_id,sub_library_id,approval_no);

       

       
           session.setAttribute("approval1", acqbib);
           session.setAttribute("approvalheader", aa1);

        return mapping.findForward("listApproval");
        }
         if(button.equals("Delete")){
          AcqApprovalHeader aa1=acqdao.getApproval(library_id, sub_library_id,approval_no);
          if(aa1==null){
                    String msg1="This Approval No Not Found";
                    request.setAttribute("msg1", msg1);
                    return mapping.findForward("duplicateapprovalno");
            }
aiaaf.setApproval_no(approval_no);
aiaaf.setApproval_date(aa1.getApprovalDate());
aiaaf.setApproved_by(aa1.getApprovedBy());
aiaaf.setRecommended_by(aa1.getRecommendedBy());
aiaaf.setVendor(aa1.getVendorId());

          List<AcqApproval> aa2=acqdao.getApproval1(library_id, sub_library_id,approval_no);
          ArrayList<ApprovalList> acqbib=null;


          if(!aa2.isEmpty())

           acqbib=acqdao.getBibByControlNo(library_id,sub_library_id,approval_no);




           session.setAttribute("approval1", acqbib);
           session.setAttribute("approvalheader", aa1);

        return mapping.findForward("listApproval");
       
         }
        
        return mapping.findForward(SUCCESS);
    }
}
