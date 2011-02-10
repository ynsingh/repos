/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.cataloguing;

import com.myapp.struts.hbm.BibliographicDetails;
import com.myapp.struts.hbm.BibliographicDetailsId;
import com.myapp.struts.hbm.AcqFinalDemandList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.cataloguingDAO.BibliopgraphicEntryDAO;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;

/**
 *
 * @author Client
 */
public class BibliographicDetailEntryAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    BibliographicDetailsId bibid=new BibliographicDetailsId();
    BibliographicDetails bib=new BibliographicDetails();
   AcqFinalDemandList acqdemand=new AcqFinalDemandList();
    BibliopgraphicEntryDAO dao=new BibliopgraphicEntryDAO();
    //boolean isbn10_status=false;
    //boolean isbn13_status=false;
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BibliographicDetailEntryActionForm bibform=(BibliographicDetailEntryActionForm)form;
        HttpSession session1=request.getSession();        
        String library_id=(String)session1.getAttribute("library_id");
        String isbn10=bibform.getIsbn10();
        String button=bibform.getButton();
         if(button.equals("New")){
         bib=dao.searchIsbn10(isbn10, library_id);
        acqdemand = dao.searchacqIsbn10(isbn10, library_id);
         if(bib!=null){

           String msg1="This isbn already exists enter different";
           request.setAttribute("msg1",msg1);
           return mapping.findForward("failure");
         }
         else if(acqdemand!=null){
         System.out.println("&&&&&&"+acqdemand);
         bibform.setAuthor_main(acqdemand.getAuthor());
         bibform.setEdition(acqdemand.getEdition());
         bibform.setBind_type(acqdemand.getBindId());
         bibform.setIsbn10(acqdemand.getIsbn());
         bibform.setPrice(acqdemand.getPrice());        
         bibform.setPublisher_name(acqdemand.getPublisherId());        
         bibform.setSubtitle(acqdemand.getSubtitle());
         bibform.setTitle(acqdemand.getTitle());
         bibform.setCall_no("");
         return mapping.findForward("new");
         }
         else{
            System.out.println("&&&&&&"+bib);
         bibform.setAuthor_main("");
         bibform.setAuthor_sub("");
         bibform.setBind_type("");
         bibform.setControl_no("");
         bibform.setDocument_type("");
         bibform.setEdition("");
         bibform.setIndex_no("");
         bibform.setLibrary_id("");
         bibform.setNo_of_pages("");
         bibform.setPhysical_width("");
         bibform.setPrice("");
         bibform.setPublication_place("");
         bibform.setPublisher_name("");
         bibform.setPublishing_year(0);
         bibform.setSubtitle("");
         bibform.setTitle("");
         bibform.setCall_no("");
         bibform.setAccession_type("");
         bibform.setNo_of_copies("");
     
         return mapping.findForward("new");
         }
         }
         if(button.equals("Update")||button.equals("Delete")||button.equals("View")){
         bib=dao.searchIsbn10(isbn10, library_id);
         if(bib!=null){
         bibform.setAuthor_main(bib.getAuthorMain());
         bibform.setAuthor_sub(bib.getAuthorSub());
         bibform.setBiblio_id(bib.getId().getBiblioId());
         bibform.setBind_type(bib.getBindType());
         bibform.setControl_no(bib.getControlNo());
         bibform.setDocument_type(bib.getDocumentType());
         bibform.setEdition(bib.getEdition());
         bibform.setIndex_no(bib.getIndexNo());
         bibform.setIsbn10(bib.getId().getIsbn10());
         bibform.setIsbn13(bib.getIsbn13());
         bibform.setLibrary_id(bib.getId().getLibraryId());
         bibform.setNo_of_pages(bib.getNoOfPages());
         bibform.setPhysical_width(bib.getPhysicalWidth());
         bibform.setPrice(bib.getPrice());
         bibform.setPublication_place(bib.getPublicationPlace());
         bibform.setPublisher_name(bib.getPublisherName());
         bibform.setPublishing_year(bib.getPublishingYear());
         bibform.setSubtitle(bib.getSubtitle());
         bibform.setTitle(bib.getTitle());
         bibform.setCall_no(bib.getId().getCallNo());
         bibform.setAccession_type(bib.getAccessionType());
         bibform.setNo_of_copies(bib.getNoOfCopy());
      
         request.setAttribute("button", button);
         return mapping.findForward(SUCCESS);
         }
         else{
          String msg1="Record not found corresponding to entered isbn";
           request.setAttribute("msg1",msg1);
           return mapping.findForward("failure");
         }
       }
       if(button.equals("View All")){
       List bib2=dao.getBibliographicDetails(library_id);
       session1.setAttribute("opacList", bib2);
       return mapping.findForward("view");
       }
         if (button.equals("Back")){
         return mapping.findForward("main");
         }
    return mapping.findForward(SUCCESS);
    }
}