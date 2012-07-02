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
import com.myapp.struts.hbm.BibliographicDetailsLang;

/**
 *
 * @author <a href="mailto:asif633@gmail.com">Asif Iqubal</a>
 */
public class BibliographicDetailAction extends org.apache.struts.action.Action {

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
            buttonhand=(String)session1.getAttribute("edit");
        System.out.println("MASTIIIIIIIIIIII"+buttonhand+"/");

        session1.removeAttribute("edit");

        if(buttonhand.equalsIgnoreCase("View"))
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
        bibform.setSubject(bib.getSubject());
        bibform.setNotes(bib.getNotes());
        bibform.setThesis_abstract(bib.getAbstract_());
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
        BibliographicDetailsLang  biblang=dao.searchlangbyBiblioid(ii, library_id, sub_library_id);
        if(biblang!=null)
        {
        bibform.setMain_entry1(biblang.getMainEntry());
        bibform.setAdded_entryl(biblang.getAddedEntry());
        bibform.setAdded_entry01(biblang.getAddedEntry1());
        bibform.setAdded_entry11(biblang.getAddedEntry2());
        bibform.setAdded_entry21(biblang.getAddedEntry3());
        bibform.setSer_note1(biblang.getSeries());
        bibform.setLCC_no1(biblang.getLccNo());
        bibform.setEdition1(biblang.getEdition());
        bibform.setIsbn101(biblang.getIsbn10());
        bibform.setIsbn131(biblang.getIsbn13());
        bibform.setThesis_abstract(biblang.getAbstract_());
        bibform.setSubject(biblang.getSubject());
        bibform.setPublication_place1(biblang.getPublicationPlace());
        bibform.setPublisher_name1(biblang.getPublisherName());
        bibform.setPublishing_year1(biblang.getPublishingYear());
        bibform.setSubtitle1(biblang.getSubtitle());
        bibform.setTitle1(biblang.getTitle());
        bibform.setCall_no1(biblang.getCallNo());
        bibform.setSubject1(biblang.getSubject());
        bibform.setThesis_abstract1(biblang.getAbstract_());
        bibform.setNotes1(biblang.getNotes());
        bibform.setStatement_responsibility1(biblang.getStatementResponsibility());
        bibform.setAlt_title1(biblang.getAltTitle());
        bibform.setLanguage(biblang.getEntryLanguage());
        }
        String msg3 = "";
        request.setAttribute("msg1", msg3);
        return mapping.findForward("success");
        }
         if(buttonhand.equalsIgnoreCase("Import Data")||buttonhand.equalsIgnoreCase("Edit")||buttonhand.equalsIgnoreCase("Delete"))
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
        bibform.setThesis_abstract(bib.getAbstract_());
        bibform.setSubject(bib.getSubject());
        System.out.println(bib.getAbstract_()+"  "+bib.getNotes());
        bibform.setNotes(bib.getNotes());
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
        BibliographicDetailsLang  biblang=dao.searchlangbyBiblioid(ii, library_id, sub_library_id);
        if(biblang!=null)
        {
        bibform.setMain_entry1(biblang.getMainEntry());
        bibform.setAdded_entryl(biblang.getAddedEntry());
        bibform.setAdded_entry01(biblang.getAddedEntry1());
        bibform.setAdded_entry11(biblang.getAddedEntry2());
        bibform.setAdded_entry21(biblang.getAddedEntry3());
        bibform.setSer_note1(biblang.getSeries());
        bibform.setLCC_no1(biblang.getLccNo());
        bibform.setEdition1(biblang.getEdition());
        bibform.setThesis_abstract(biblang.getAbstract_());
        bibform.setSubject(biblang.getSubject());
        bibform.setIsbn101(biblang.getIsbn10());
        bibform.setIsbn131(biblang.getIsbn13());      
        bibform.setPublication_place1(biblang.getPublicationPlace());
        bibform.setPublisher_name1(biblang.getPublisherName());
        bibform.setPublishing_year1(biblang.getPublishingYear());
        bibform.setSubtitle1(biblang.getSubtitle());
        bibform.setTitle1(biblang.getTitle());
        bibform.setCall_no1(biblang.getCallNo());
        bibform.setSubject1(biblang.getSubject());
        bibform.setThesis_abstract1(biblang.getAbstract_());
        bibform.setNotes1(biblang.getNotes());
        bibform.setStatement_responsibility1(biblang.getStatementResponsibility());
        bibform.setAlt_title1(biblang.getAltTitle());
        bibform.setLanguage(biblang.getEntryLanguage());
        }
        String msg3 = "";
        request.setAttribute("msg1", msg3);
        if(bib.getDocumentType().equals("Book")){
        return mapping.findForward("new");
        }
//        else if(bib.getDocumentType().equals("CD")){
//
//        }
        return mapping.findForward("new");
        }
        if (buttonhand.equalsIgnoreCase("New")) {
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
        return null;
    }
}
