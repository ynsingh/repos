/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.cataloguing;

import com.myapp.struts.cataloguingDAO.BibliopgraphicEntryDAO;
import com.myapp.struts.hbm.BibliographicDetails;
import com.myapp.struts.hbm.BibliographicDetailsId;
import com.myapp.struts.hbm.DocumentCategory;
import com.myapp.struts.hbm.DocumentDetails;
import com.myapp.struts.hbm.DocumentDetailsId;
import com.myapp.struts.systemsetupDAO.DocumentCategoryDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author <a href="mailto:asif633@gmail.com">Asif Iqubal</a>
 */
public class NewEntryBiblioAction extends org.apache.struts.action.Action {

    private static final String SUCCESS = "success";
    BibliographicDetailsId bibid = new BibliographicDetailsId();
    BibliographicDetailsId bibid1 = new BibliographicDetailsId();
    BibliographicDetails bib = new BibliographicDetails();
    BibliographicDetails bib2 = new BibliographicDetails();
    BibliographicDetails bib3 = new BibliographicDetails();
    BibliographicDetails bib1 = new BibliographicDetails();
    BibliopgraphicEntryDAO dao = new BibliopgraphicEntryDAO();
    DocumentDetails dd = new DocumentDetails();
    DocumentDetailsId ddid = new DocumentDetailsId();

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BibliographicDetailEntryActionForm bibform = (BibliographicDetailEntryActionForm) form;
        String add1 = bibform.getAdded_entry0();
        String button = bibform.getButton();
        String call_no = bibform.getCall_no();
        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String isbn10 = (String) bibform.getIsbn10();
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        if (StringUtils.isEmpty(isbn10)) {
            isbn10 = null;
        }
        if (button.equals("Save")) {
            bib3 = dao.search1Isbn10(isbn10, library_id, sub_library_id);
            if (bib3 != null) {
                String msg3 = "You are trying to enter duplicate isbn enter different";
                request.setAttribute("msg1", msg3);
                return mapping.findForward("fail");
            }
            bib2 = dao.searchcall(call_no, library_id, sub_library_id);
            if (bib2 != null) {
                String msg3 = "You are trying to enter duplicate call no enter different";
                request.setAttribute("msg1", msg3);
                return mapping.findForward("fail");
            } else {
                Integer biblio_id = dao.returnMaxBiblioId(library_id, sub_library_id);
                bibid.setBiblioId(biblio_id);
                bibid.setLibraryId(library_id);
                bibid.setSublibraryId(sub_library_id);
                bib.setId(bibid);
                bib.setIsbn10(isbn10);
                bib.setBookType(bibform.getBook_type());
                bib.setDocumentType(bibform.getDocument_type());
                bib.setTitle(bibform.getTitle());
                bib.setSubtitle(bibform.getSubtitle());
                bib.setStatementResponsibility(bibform.getStatement_responsibility());
                bib.setMainEntry(bibform.getMain_entry());
                bib.setAddedEntry(bibform.getAdded_entry());
                bib.setAddedEntry1(bibform.getAdded_entry0());
                bib.setAddedEntry2(bibform.getAdded_entry1());
                bib.setAddedEntry3(bibform.getAdded_entry2());
                bib.setPublisherName(bibform.getPublisher_name());
                bib.setPublicationPlace(bibform.getPublication_place());
                bib.setPublishingYear(bibform.getPublishing_year());
                bib.setLccNo(bibform.getLCC_no());
                bib.setIsbn13(bibform.getIsbn13());
                bib.setEdition(bibform.getEdition());
                bib.setCallNo(bibform.getCall_no());
                bib.setAltTitle(bibform.getAlt_title());
                bib.setSubject(bibform.getSubject());
                bib.setSeries(bibform.getSer_note());
                bib.setAbstract_(bibform.getThesis_abstract());
                bib.setNoOfCopies(bibform.getNo_of_copies());
                bib.setNotes(bibform.getNotes());
                dao.insert(bib);
                bibform.setTitle("");
                bibform.setIsbn10("");
                bibform.setDocument_type("");
                String msg2 = "Data is saved successfully with biblio Id:" + biblio_id;
                request.setAttribute("msg2", msg2);
                return mapping.findForward(SUCCESS);
            }
        }
        if (button.equals("Save and go for accessioning")) {
            bib2 = dao.searchcall(call_no, library_id, sub_library_id);
            if (bib2 != null) {
                String msg3 = "You are trying to enter duplicate call no enter different";
                request.setAttribute("msg1", msg3);
                return mapping.findForward("fail");
            } else {
                Integer biblio_id = dao.returnMaxBiblioId(library_id, sub_library_id);
                bibid.setBiblioId(biblio_id);
                bibid.setLibraryId(library_id);
                bibid.setSublibraryId(sub_library_id);
                bib.setId(bibid);
                bib.setBookType(bibform.getBook_type());
                bib.setDocumentType(bibform.getDocument_type());
                bib.setTitle(bibform.getTitle());
                bib.setSubtitle(bibform.getSubtitle());
                bib.setStatementResponsibility(bibform.getStatement_responsibility());
                bib.setMainEntry(bibform.getMain_entry());
                bib.setAddedEntry(bibform.getAdded_entry());
                bib.setAddedEntry1(bibform.getAdded_entry0());
                bib.setAddedEntry2(bibform.getAdded_entry1());
                bib.setAddedEntry3(bibform.getAdded_entry2());
                bib.setPublisherName(bibform.getPublisher_name());
                bib.setPublicationPlace(bibform.getPublication_place());
                bib.setPublishingYear(bibform.getPublishing_year());
                bib.setLccNo(bibform.getLCC_no());
                bib.setIsbn13(bibform.getIsbn13());
                bib.setIsbn10(bibform.getIsbn10());
                bib.setEdition(bibform.getEdition());
                bib.setCallNo(bibform.getCall_no());
                bib.setAltTitle(bibform.getAlt_title());
                bib.setSubject(bibform.getSubject());
                bib.setSeries(bibform.getSer_note());
                bib.setAbstract_(bibform.getThesis_abstract());
                bib.setNotes(bibform.getNotes());
                bib.setNoOfCopies(bibform.getNo_of_copies());
                dao.insert(bib);
                session.setAttribute("biblio_id", biblio_id);
                String msg2 = "Data is saved successfully with biblio Id:" + biblio_id;
                request.setAttribute("msg1", msg2);
                String msg1 = "";
                request.setAttribute("msg2", msg1);
                DocumentCategory doc=(DocumentCategory)DocumentCategoryDAO.searchDocumentCategory(library_id, sub_library_id, bib.getBookType());
                        if(doc!=null)
                            bibform.setBook_type(doc.getDocumentCategoryName());
               
                return mapping.findForward("accession");
            }
        }
        return mapping.findForward(SUCCESS);
    }
}
