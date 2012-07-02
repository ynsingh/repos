/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.cataloguing;

import com.myapp.struts.cataloguingDAO.BibliopgraphicEntryDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.hbm.BibliographicDetails;

/**
 *
 * @author <a href="mailto:asif633@gmail.com">Asif Iqubal</a>
 */
public class BibliographicDetailActionLang extends org.apache.struts.action.Action {

    BibliopgraphicEntryDAO dao = new BibliopgraphicEntryDAO();
    int i;
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BibliographicDetailEntryActionForm bibform = (BibliographicDetailEntryActionForm) form;
        HttpSession session1 = request.getSession();
        String library_id = (String) session1.getAttribute("library_id");
        String sub_library_id = (String) session1.getAttribute("sublibrary_id");
        String buttonhand=bibform.getButton();
                if(buttonhand==null || buttonhand.isEmpty())
            buttonhand=(String)session1.getAttribute("edit3");
        System.out.println("MASTIIIIIIIIIIII"+buttonhand+"/");

        session1.removeAttribute("edit3");
      //  int ii=bibform.getBiblio_id();
     //   BibliographicDetails bib = dao.getBiblio(bibform.getLibrary_id(),bibform.getSublibrary_id(), ii);
        if(buttonhand.equals("View"))
        {
        int ii=bibform.getBiblio_id();
        BibliographicDetails bib = dao.getBiblio(bibform.getLibrary_id(),bibform.getSublibrary_id(), ii);
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
        bibform.setPublishing_year(String.valueOf(bib.getPublishingYear()));
        bibform.setSubtitle(bib.getSubtitle());
        bibform.setTitle(bib.getTitle());
        bibform.setCall_no(bib.getCallNo());
        bibform.setAccession_type(bib.getAccessionType());
        bibform.setBook_type(bib.getBookType());
        bibform.setSer_note(bib.getSeries());
        bibform.setStatement_responsibility(bib.getStatementResponsibility());
        bibform.setLanguage(bib.getEntryLanguage());
        bibform.setAlt_title(bib.getAltTitle());
                                if (bib.getNoOfCopies() == null) {
                            i = 0;
                        } else {
                            i = bib.getNoOfCopies();
                        }
        bibform.setNo_of_copies(i);
        String msg3 = "";
        request.setAttribute("msg1", msg3);
        if(bib.getDocumentType().equals("Book")){
        return mapping.findForward("success");
        }
        else if(bib.getDocumentType().equals("CD")){
        return mapping.findForward("cd");
        }
        return mapping.findForward("success");
        }
         if(buttonhand.equals("Import Data")||buttonhand.equals("Edit")||buttonhand.equals("Delete"))
        {
        int ii=bibform.getBiblio_id();
        BibliographicDetails bib = dao.getBiblio(bibform.getLibrary_id(),bibform.getSublibrary_id(), ii);
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
        bibform.setPublishing_year(String.valueOf(bib.getPublishingYear()));
        bibform.setSubtitle(bib.getSubtitle());
        bibform.setTitle(bib.getTitle());
        bibform.setCall_no(bib.getCallNo());
        bibform.setAccession_type(bib.getAccessionType());
        bibform.setBook_type(bib.getBookType());
        bibform.setSer_note(bib.getSeries());
        bibform.setStatement_responsibility(bib.getStatementResponsibility());
        bibform.setLanguage(bib.getEntryLanguage());
        bibform.setAlt_title(bib.getAltTitle());
        bibform.setDate_acquired1(bib.getDateAcquired());
                                if (bib.getNoOfCopies() == null) {
                            i = 0;
                        } else {
                            i = bib.getNoOfCopies();
                        }
        bibform.setNo_of_copies(i);
        String msg3 = "";
        request.setAttribute("msg1", msg3);
        if(bib.getDocumentType().equals("Book")){
        return mapping.findForward("new");
        }
        else if(bib.getDocumentType().equals("CD")){
        return mapping.findForward("cd");
        }
        return mapping.findForward("new");
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
            String doc_type=(String)session1.getAttribute("doc_type");
            if(doc_type.equals("Book")){
            return mapping.findForward("new");
            }
            else if(doc_type.equals("CD")){
            return mapping.findForward("cd");
            }
        }
        return mapping.findForward("success");
    }
}
