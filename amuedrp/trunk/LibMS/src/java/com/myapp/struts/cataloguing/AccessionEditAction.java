/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.cataloguing;

import com.myapp.struts.hbm.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.cataloguingDAO.BibliopgraphicEntryDAO;
import com.myapp.struts.systemsetupDAO.DocumentCategoryDAO;
import java.util.List;

/**
 *
 * @author <a href="mailto:asif633@gmail.com">Asif Iqubal</a>
 */
public class AccessionEditAction extends org.apache.struts.action.Action {

    BibliopgraphicEntryDAO dao = new BibliopgraphicEntryDAO();
    BibliographicDetails bib1 = new BibliographicDetails();
    BibliographicDetailsId bid = new BibliographicDetailsId();
    DocumentDetails doc = new DocumentDetails();
    DocumentDetailsId did = new DocumentDetailsId();
    AccessionRegister ac = new AccessionRegister();
    AccessionRegisterId aid = new AccessionRegisterId();
    int i;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BibliographicDetailEntryActionForm bibform = (BibliographicDetailEntryActionForm) form;
        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        String buttonhand=bibform.getButton();
         
        
        if(buttonhand==null || buttonhand.isEmpty())
            buttonhand=(String)session.getAttribute("edit1");
        System.out.println("MASTIIIIIIIIIIII"+buttonhand+"/");

        session.removeAttribute("edit1");

        if(buttonhand.equals("New Item"))
        {
        int ii=bibform.getBiblio_id();
        BibliographicDetails bib = dao.getBiblio(bibform.getLibrary_id(),bibform.getSublibrary_id(), ii);
        bibform.setType_of_disc(bib.getTypeOfDisc());
        bibform.setMain_entry(bib.getMainEntry());
        bibform.setAdded_entry(bib.getAddedEntry());
        bibform.setAdded_entry0(bib.getAddedEntry1());
        bibform.setAdded_entry1(bib.getAddedEntry2());
        bibform.setAdded_entry2(bib.getAddedEntry3());
        bibform.setLCC_no(bib.getLccNo());
        bibform.setDocument_type(bib.getDocumentType());
        bibform.setEdition(bib.getEdition());
        bibform.setIsbn10(bib.getIsbn10());
        bibform.setIsbn13(bib.getIsbn13());
        bibform.setLibrary_id(bib.getId().getLibraryId());
        bibform.setSub_library_id(bib.getId().getSublibraryId());
        bibform.setPublication_place(bib.getPublicationPlace());
        bibform.setPublisher_name(bib.getPublisherName());
        bibform.setPublishing_year(bib.getPublishingYear());
        bibform.setSubtitle(bib.getSubtitle());
        bibform.setTitle(bib.getTitle());
        bibform.setCall_no(bib.getCallNo());
        bibform.setAccession_type(bib.getAccessionType());
        bibform.setBook_type(bib.getBookType());
        bibform.setSer_note(bib.getSeries());
        bibform.setStatement_responsibility(bib.getStatementResponsibility());
        bibform.setAlt_title(bib.getAltTitle());     
        bibform.setType_of_disc(bib.getTypeOfDisc());
        DocumentCategory doc1=(DocumentCategory)DocumentCategoryDAO.searchDocumentCategory(library_id, sub_library_id, bib.getBookType());
                        if(doc1!=null)
                            bibform.setBook_type(doc1.getDocumentCategoryName());
        bibform.setStatement_responsibility(bib.getStatementResponsibility());
        if (bib.getNoOfCopies() == null) {
            i = 0;
        } else {
            i = bib.getNoOfCopies();
        }
        bibform.setNo_of_copies(i);
        String msg3 = "";
        request.setAttribute("msg1", msg3);
                if(bib.getDocumentType().equals("Book")){
        return mapping.findForward("add");
        }
        else if(bib.getDocumentType().equals("CD")){
        return mapping.findForward("cd");
        }
        return mapping.findForward("add");
        }
         if(buttonhand.equals("View")||buttonhand.equals("Edit")||buttonhand.equals("Delete"))
        {
 
        int ii=bibform.getBiblio_id();
        BibliographicDetails bib = dao.getBiblio(bibform.getLibrary_id(),bibform.getSublibrary_id(), ii);
        bibform.setType_of_disc(bib.getTypeOfDisc());
        bibform.setMain_entry(bib.getMainEntry());
        bibform.setAdded_entry(bib.getAddedEntry());
        bibform.setAdded_entry0(bib.getAddedEntry1());
        bibform.setAdded_entry1(bib.getAddedEntry2());
        bibform.setAdded_entry2(bib.getAddedEntry3());
        bibform.setLCC_no(bib.getLccNo());
        bibform.setDocument_type(bib.getDocumentType());
        bibform.setEdition(bib.getEdition());
        bibform.setIsbn10(bib.getIsbn10());
        bibform.setIsbn13(bib.getIsbn13());
        bibform.setLibrary_id(bib.getId().getLibraryId());
        bibform.setSub_library_id(bib.getId().getSublibraryId());
        bibform.setPublication_place(bib.getPublicationPlace());
        bibform.setPublisher_name(bib.getPublisherName());
        bibform.setPublishing_year(bib.getPublishingYear());
        bibform.setSubtitle(bib.getSubtitle());
        bibform.setTitle(bib.getTitle());
        bibform.setCall_no(bib.getCallNo());
        bibform.setAccession_type(bib.getAccessionType());
        bibform.setBook_type(bib.getBookType());
        bibform.setSer_note(bib.getSeries());
        bibform.setStatement_responsibility(bib.getStatementResponsibility());
        bibform.setAlt_title(bib.getAltTitle());
        bibform.setType_of_disc(bib.getTypeOfDisc());
        DocumentCategory doc1=(DocumentCategory)DocumentCategoryDAO.searchDocumentCategory(library_id, sub_library_id, bib.getBookType());
                        if(doc1!=null)
                            bibform.setBook_type(doc1.getDocumentCategoryName());
        bibform.setStatement_responsibility(bib.getStatementResponsibility());
        if (bib.getNoOfCopies() == null) {
            i = 0;
        } else {
            i = bib.getNoOfCopies();
        }
        bibform.setNo_of_copies(i);
        bibform.setAlt_title(bib.getAltTitle());
        List a = dao.getItems(library_id, sub_library_id, ii);
        session.setAttribute("opacList", a);
        String msg3 = "";
        request.setAttribute("msg1", msg3);
                if(bib.getDocumentType().equals("Book")){
        return mapping.findForward("show");
        }
        else if(bib.getDocumentType().equals("CD")){
        return mapping.findForward("cdupdate");
        }
        return mapping.findForward("show");
        }
        if (buttonhand.equals("New")) {
            bibform.setTitle(bibform.getTitle());
            bibform.setLibrary_id(library_id);
            bibform.setSublibrary_id(sub_library_id);
            bibform.setIsbn10(bibform.getIsbn10());
            bibform.setDocument_type(bibform.getDocument_type());
            bibform.setStatement_responsibility("");
            bibform.setCall_no("");
            bibform.setMain_entry("");
            bibform.setNo_of_copies(0);
            String msg1 = "";
            request.setAttribute("msg1", msg1);
            return mapping.findForward("new");
        }
        return mapping.findForward("success");
    }
}
