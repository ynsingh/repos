/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.cataloguing;

import com.myapp.struts.cataloguingDAO.BibliographicEntryDAO;
import com.myapp.struts.systemsetupDAO.*;
import com.myapp.struts.hbm.BibliographicDetails;
import com.myapp.struts.hbm.DocumentCategory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author <a href="mailto:asif633@gmail.com">Asif Iqubal</a>
 */
public class BibliographicDetailAction2 extends org.apache.struts.action.Action {

    BibliographicEntryDAO dao = new BibliographicEntryDAO();

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DocumentCategoryDAO doccatdao=new DocumentCategoryDAO();
        BibliographicDetailEntryActionForm1 bibform = (BibliographicDetailEntryActionForm1) form;
        HttpSession session1 = request.getSession();
        String library_id = (String) session1.getAttribute("library_id");
        String sub_library_id = (String) session1.getAttribute("sublibrary_id");
        String id = request.getParameter("id");
        int ii = Integer.parseInt(id);
        BibliographicDetails bib = dao.getBiblio(library_id, sub_library_id, ii);
        bibform.setBiblio_id(ii);
        if(bib.getNoOfCopies()!=null)
        bibform.setNo_of_copies(bib.getNoOfCopies());
        bibform.setThesis_abstract(bib.getAbstract_());
        bibform.setNotes(bib.getNotes());
        bibform.setSubject(bib.getSubject());
        bibform.setMain_entry(bib.getMainEntry());
        bibform.setAdded_entry(bib.getAddedEntry());
        bibform.setAdded_entry(bib.getAddedEntry());
        bibform.setAdded_entry0(bib.getAddedEntry1());
        bibform.setAdded_entry1(bib.getAddedEntry2());
        bibform.setAdded_entry2(bib.getAddedEntry3());
        bibform.setBiblio_id(bib.getId().getBiblioId());
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
        System.out.println(bib.getSeries()+"....................");
        bibform.setSer_note(bib.getSeries());
        bibform.setAccession_type(bib.getAccessionType());
        bibform.setLanguage(bib.getEntryLanguage());
        DocumentCategory doc=doccatdao.searchDocumentCategory(library_id, sub_library_id,bib.getBookType());

if(doc!=null){
        bibform.setBook_type(doc.getDocumentCategoryName());
}else{
                    bibform.setBook_type("");
}
        bibform.setStatement_responsibility(bib.getStatementResponsibility());
        bibform.setAlt_title(bib.getAltTitle());
        String msg3 = "";
        request.setAttribute("booktype", bib.getBookType());
        request.setAttribute("msg1", msg3);
        return mapping.findForward("view");
    }
}
