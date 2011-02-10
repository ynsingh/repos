/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.cataloguing;

import com.myapp.struts.cataloguingDAO.BibliopgraphicEntryDAO;
import com.myapp.struts.hbm.BibliographicDetails;
import com.myapp.struts.hbm.BibliographicDetailsId;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 *
 * @author Client
 */
public class BiblioDetailsAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
        BibliographicDetailsId bibid=new BibliographicDetailsId();
    BibliographicDetails bib=new BibliographicDetails();
     BibliographicDetails bib1=new BibliographicDetails();
    BibliopgraphicEntryDAO dao=new BibliopgraphicEntryDAO();
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
        String control_no=bibform.getControl_no();
        int biblio_id=bibform.getBiblio_id();
        String isbn10=bibform.getIsbn10();
        String isbn13=bibform.getIsbn13();
        bibid.setIsbn10(isbn10);
        bib.setIsbn13(isbn13);
        bibid.setLibraryId(library_id);
        bibid.setBiblioId(biblio_id);
        bibid.setCallNo(bibform.getCall_no());
        bib.setControlNo(control_no);
        bib.setId(bibid);
        bib.setAccessionType(bibform.getAccession_type());
        bib.setNoOfCopy(bibform.getNo_of_copies());
        bib.setDocumentType(bibform.getDocument_type());
        bib.setTitle(bibform.getTitle());
        bib.setSubtitle(bibform.getSubtitle());
        bib.setAuthorMain(bibform.getAuthor_main());
        bib.setAuthorSub(bibform.getAuthor_sub());
        bib.setPublisherName(bibform.getPublisher_name());
        bib.setPublicationPlace(bibform.getPublication_place());
        bib.setPublishingYear(bibform.getPublishing_year());
        bib.setPrice(bibform.getPrice());
        bib.setEdition(bibform.getEdition());
        bib.setIndexNo(bibform.getIndex_no());
        bib.setNoOfPages(bibform.getNo_of_pages());
        bib.setPhysicalWidth(bibform.getPhysical_width());
        bib.setBindType(bibform.getBind_type());
       
        String button=bibform.getButton();
        if(button.equals("Update")){
        System.out.println("KKKKK----");
       /* bib1=dao.view(isbn10, isbn13, library_id);
        if(bib1!=null){
         String msg1="Entered isbn already exists enter different";
         request.setAttribute("msg1",msg1);
         return mapping.findForward("failure");
        }
        else
        {*/
        dao.update(bib);
        bibform.setIsbn10("");

        System.out.println("UUUU----");
        String msg2="Record updated successfully";
        request.setAttribute("msg2",msg2);
        return mapping.findForward(SUCCESS);
        }
       // }
        if(button.equals("Delete")){
        System.out.println("UUUU----");
        dao.delete( biblio_id, library_id);
        bibform.setIsbn10("");
   
        String msg2="Record deleted successfully";
        request.setAttribute("msg2",msg2);
        return mapping.findForward(SUCCESS);
        }
        if(button.equals("Cancel")||button.equals("Back"))
        {
        bibform.setIsbn10("");

        return mapping.findForward(SUCCESS);
        }
        return mapping.findForward(SUCCESS);
    }
}
