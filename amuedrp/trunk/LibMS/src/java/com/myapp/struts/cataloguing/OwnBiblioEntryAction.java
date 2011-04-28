/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.cataloguing;

import com.myapp.struts.cataloguingDAO.BibliopgraphicEntryDAO;
import com.myapp.struts.hbm.BibliographicDetails;
import com.myapp.struts.hbm.BibliographicDetailsId;
import com.myapp.struts.hbm.DocumentDetails;
import com.myapp.struts.hbm.DocumentDetailsId;
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
public class OwnBiblioEntryAction extends org.apache.struts.action.Action {

    private static final String SUCCESS = "success";
    BibliographicDetailsId bibid = new BibliographicDetailsId();
    BibliographicDetailsId bibid1 = new BibliographicDetailsId();
    BibliographicDetails bib = new BibliographicDetails();
    BibliographicDetails bib2 = new BibliographicDetails();
    BibliographicDetails bib1 = new BibliographicDetails();
    BibliographicDetails bib3 = new BibliographicDetails();
    BibliopgraphicEntryDAO dao = new BibliopgraphicEntryDAO();
    DocumentDetails dd = new DocumentDetails();
    DocumentDetailsId ddid = new DocumentDetailsId();


    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BibliographicDetailEntryActionForm1 bibform = (BibliographicDetailEntryActionForm1) form;
        String button = bibform.getButton();
        String call_no = bibform.getCall_no();
        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String isbn10 = StringUtils.trim(bibform.getIsbn10());
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        if (StringUtils.isEmpty(isbn10)) {
            isbn10 = null;
        }
        if (button.equals("Save")) {
            if (StringUtils.isEmpty(call_no)) {
                String msg3 = "Call no field can not be left blank";
                request.setAttribute("msg1", msg3);
                return mapping.findForward("fail");
            }
            if (StringUtils.isEmpty(bibform.getStatement_responsibility())) {
                String msg3 = "Statement Responsibility field can not be left blank";
                request.setAttribute("msg1", msg3);
                return mapping.findForward("fail");
            }
            if (StringUtils.isEmpty(bibform.getBook_type())) {
                String msg3 = "Document Category field can not be left blank";
                request.setAttribute("msg1", msg3);
                return mapping.findForward("fail");
            }
            if (StringUtils.isEmpty(bibform.getMain_entry())) {
                String msg3 = "Main entry field can not be left blank";
                request.setAttribute("msg1", msg3);
                return mapping.findForward("fail");
            }
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
                bib.setNoOfCopies(bibform.getNo_of_copies());
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
                bib.setSeries(bibform.getSer_note());
                bib.setIsbn13(bibform.getIsbn13());
                bib.setEdition(bibform.getEdition());
                bib.setCallNo(bibform.getCall_no());
                bib.setAltTitle(bibform.getAlt_title());
                bib.setSubject(bibform.getSubject());
                bib.setAbstract_(bibform.getThesis_abstract());
                bib.setNotes(bibform.getNotes());
                dao.insert(bib);
                String msg2 = "Data is saved successfully with biblio Id:" + biblio_id;
                request.setAttribute("msg2", msg2);
                return mapping.findForward(SUCCESS);
            }
        }
        if (button.equals("Save and go for accessioning")) {
            if (StringUtils.isEmpty(call_no)) {
                String msg3 = "Call no field can not be left blank";
                request.setAttribute("msg1", msg3);
                return mapping.findForward("fail");
            }
            if (StringUtils.isEmpty(bibform.getBook_type())) {
                String msg3 = "Document Category field can not be left blank";
                request.setAttribute("msg1", msg3);
                return mapping.findForward("fail");
            }
            if (StringUtils.isEmpty(bibform.getMain_entry())) {
                String msg3 = "Main entry field can not be left blank";
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
                bib.setNoOfCopies(bibform.getNo_of_copies());
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
                bib.setIsbn10(bibform.getIsbn10());
                bib.setIsbn13(bibform.getIsbn13());
                bib.setSeries(bibform.getSer_note());
                bib.setEdition(bibform.getEdition());
                bib.setCallNo(bibform.getCall_no());
                bib.setAltTitle(bibform.getAlt_title());
                bib.setSubject(bibform.getSubject());
                bib.setAbstract_(bibform.getThesis_abstract());
                bib.setNotes(bibform.getNotes());
                dao.insert(bib);
                session.setAttribute("biblio_id", biblio_id);
                String msg2 = "Data is saved successfully with biblio Id:" + biblio_id;
                request.setAttribute("msg1", msg2);
                String msg1 = "";
                request.setAttribute("msg2", msg1);
                return mapping.findForward("accession");
            }
        }
        return mapping.findForward(SUCCESS);
    }
}
