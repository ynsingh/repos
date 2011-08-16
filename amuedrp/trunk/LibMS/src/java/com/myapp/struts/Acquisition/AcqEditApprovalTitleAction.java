/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.hbm.*;
import com.myapp.struts.AcquisitionDao.AcquisitionDao;
import javax.servlet.http.HttpSession;

/**
 *
 * @author maqbool
 */
public class AcqEditApprovalTitleAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    AcquisitionDao ado=new AcquisitionDao();
    AcqBibliographyDetails acqbibdtails =new AcqBibliographyDetails();
    AcqBibliographyDetailsId acqbibdtailsid =new AcqBibliographyDetailsId();


    
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

         AcqBiblioActionForm acqbiblio=(AcqBiblioActionForm)form;
        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        String button=acqbiblio.getButton();
        int control_no=acqbiblio.getControl_no();
        acqbibdtailsid.setControlNo(control_no);
        acqbibdtailsid.setLibraryId(library_id);
        acqbibdtailsid.setSubLibraryId(sub_library_id);
        acqbibdtails.setId(acqbibdtailsid);
        acqbibdtails.setVolume(acqbiblio.getVolume());
        acqbibdtails.setNoOfCopies(acqbiblio.getNo_of_copies());
        
        acqbibdtails.setUnitPrice(acqbiblio.getUnit_price());
        acqbibdtails.setPrimaryBudget(acqbiblio.getPrimary_budget());
        acqbibdtails.setRequestedBy(acqbiblio.getRequested_by());
        acqbibdtails.setVendor(acqbiblio.getVendor());
        acqbibdtails.setAcqMode(acqbiblio.getAcq_mode());
        acqbibdtails.setRequestedDate(acqbiblio.getRequested_date());
        acqbibdtails.setTitleId(acqbiblio.getTitle_id());

        if(button.equals("Update")){

            ado.update(acqbibdtails);
            String msg4="Record Updated successfully";
            request.setAttribute("msg4", msg4);
            acqbiblio.setTitle("");
            acqbiblio.setDocument_type("");
            return mapping.findForward("editapprovaltitle");

        }



        
        
        return mapping.findForward(SUCCESS);
    }
}
