/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.cataloguing;

import com.myapp.struts.cataloguingDAO.BibliopgraphicEntryDAO;
import com.myapp.struts.hbm.AccessionRegister;
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
public class AccessionSingleEdit extends org.apache.struts.action.Action {

    BibliopgraphicEntryDAO dao = new BibliopgraphicEntryDAO();

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BibliographicDetailEntryActionForm1 bibform = (BibliographicDetailEntryActionForm1) form;
        HttpSession session1 = request.getSession();
        String library_id = (String) session1.getAttribute("library_id");
        String sub_library_id = (String) session1.getAttribute("sublibrary_id");
        String id = request.getParameter("id");
        int i;
        AccessionRegister bib = dao.searchacc(library_id, sub_library_id, id);
        if (bib.getBibliographicDetails().getNoOfCopies() == null) {
            i = 0;
        } else {
            i = bib.getBibliographicDetails().getNoOfCopies();
        }
        bibform.setBiblio_id(bib.getBiblioId());
        bibform.setRecord_no(bib.getId().getRecordNo());
        bibform.setMain_entry(bib.getBibliographicDetails().getMainEntry());
        bibform.setAdded_entry(bib.getBibliographicDetails().getAddedEntry());
        bibform.setAdded_entry0(bib.getBibliographicDetails().getAddedEntry1());
        bibform.setAdded_entry1(bib.getBibliographicDetails().getAddedEntry2());
        bibform.setAdded_entry2(bib.getBibliographicDetails().getAddedEntry3());
        bibform.setBiblio_id(bib.getBibliographicDetails().getId().getBiblioId());
        bibform.setLCC_no(bib.getBibliographicDetails().getLccNo());
        bibform.setSer_note(bib.getBibliographicDetails().getSeries());
        bibform.setDocument_type(bib.getBibliographicDetails().getDocumentType());
        bibform.setEdition(bib.getBibliographicDetails().getEdition());
        bibform.setIsbn10(bib.getBibliographicDetails().getIsbn10());
        bibform.setIsbn13(bib.getBibliographicDetails().getIsbn13());
        bibform.setLibrary_id(bib.getId().getLibraryId());
        bibform.setSub_library_id(bib.getId().getSublibraryId());
        bibform.setPublication_place(bib.getBibliographicDetails().getPublicationPlace());
        bibform.setPublisher_name(bib.getBibliographicDetails().getPublisherName());
        bibform.setPublishing_year(bib.getBibliographicDetails().getPublishingYear());
        bibform.setSubtitle(bib.getBibliographicDetails().getSubtitle());
        bibform.setTitle(bib.getBibliographicDetails().getTitle());
        bibform.setCall_no(bib.getBibliographicDetails().getCallNo());
        bibform.setNo_of_copies(i);
        bibform.setAccession_type(bib.getBibliographicDetails().getAccessionType());
        bibform.setBook_type(bib.getBibliographicDetails().getBookType());
        bibform.setStatement_responsibility(bib.getBibliographicDetails().getStatementResponsibility());
        bibform.setAlt_title(bib.getBibliographicDetails().getAltTitle());
        bibform.setAccession_no(bib.getAccessionNo());
        bibform.setVolume_no(bib.getVolumeNo());
        bibform.setLocation(bib.getLocation());
        bibform.setShelving_location(bib.getShelvingLocation());
        bibform.setNo_of_pages(bib.getNoOfPages());
        bibform.setIndex_no(bib.getIndexNo());
        bibform.setPhysical_width(bib.getPhysicalWidth());
        bibform.setBind_type(bib.getBindType());
        session1.setAttribute("acc_no", id);
        String msg3 = "";
        request.setAttribute("msg1", msg3);
        return mapping.findForward("view");
    }
}
