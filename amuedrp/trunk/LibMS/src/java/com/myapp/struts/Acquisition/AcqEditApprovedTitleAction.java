/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import com.myapp.struts.AcquisitionDao.AcquisitionDao;
import com.myapp.struts.AcquisitionDao.VendorDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.hbm.*;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author maqbool
 */
public class AcqEditApprovedTitleAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";

    AcquisitionDao ado=new AcquisitionDao();
    AcqBibliography acqbib=new AcqBibliography();
    AcqBibliographyId acqbibid=new AcqBibliographyId();
    AcqBibliographyDetails acqbibdtail=new AcqBibliographyDetails();
    AcqBibliographyDetailsId acqbibdtailid=new AcqBibliographyDetailsId();
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
        String id= request.getParameter("id");
        List<AcqVendor> acqvendor=VendorDAO.searchDoc5(library_id, sub_library_id);
        int ii=Integer.parseInt(id);
        acqbibdtail=ado.BibliobyControlId(library_id, sub_library_id, ii);
       System.out.println("DDDDDDDDDDDDDDDDDDD"+acqbibdtail);
       
       int no_copy=0;
     //  System.out.println("UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU"+no_copy);
       if(acqbibdtail.getNoOfCopies()==null){
    //    no_copy=0;
       }
       else{
       no_copy=acqbibdtail.getNoOfCopies();
       System.out.println("NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN"+no_copy);
       }
        acqbiblio.setNo_of_copies(no_copy);
        acqbiblio.setAcq_mode(acqbibdtail.getAcqMode());
        acqbiblio.setVolume(acqbibdtail.getVolume());
        acqbiblio.setVendor(acqbibdtail.getVendor());
        acqbiblio.setUnit_price(acqbibdtail.getUnitPrice());
        acqbiblio.setPrimary_budget(acqbibdtail.getPrimaryBudget());
        acqbiblio.setRequested_by(acqbibdtail.getRequestedBy());
        acqbiblio.setRequested_date(acqbibdtail.getRequestedDate());
        acqbiblio.setTitle_id(acqbibdtail.getTitleId());
        acqbiblio.setDocument_type(acqbibdtail.getAcqBibliography().getDocType());
        acqbiblio.setTitle(acqbibdtail.getAcqBibliography().getTitle());
        acqbiblio.setPublisher_name(acqbibdtail.getAcqBibliography().getPublisherName());
        acqbiblio.setPublication_place(acqbibdtail.getAcqBibliography().getPublishingPlace());
        acqbiblio.setEdition(acqbibdtail.getAcqBibliography().getEdition());
        acqbiblio.setLcc_no(acqbibdtail.getAcqBibliography().getLccNo());
        acqbiblio.setIsbn(acqbibdtail.getAcqBibliography().getIsbn());
        acqbiblio.setSub_library_id(acqbibdtail.getId().getSubLibraryId());
        acqbiblio.setLibrary_id(acqbibdtail.getId().getLibraryId());
        acqbiblio.setControl_no(ii);
        session.setAttribute("vendors",acqvendor);
       return mapping.findForward(SUCCESS);
    }
}
