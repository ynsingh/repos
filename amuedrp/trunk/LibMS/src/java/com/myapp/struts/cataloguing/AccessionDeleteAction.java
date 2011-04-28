/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.cataloguing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.cataloguingDAO.BibliopgraphicEntryDAO;
import com.myapp.struts.hbm.*;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author <a href="mailto:asif633@gmail.com">Asif Iqubal</a>
 */
public class AccessionDeleteAction extends org.apache.struts.action.Action {

    private static final String SUCCESS = "success";
    BibliopgraphicEntryDAO dao = new BibliopgraphicEntryDAO();
    BibliographicDetails bib = new BibliographicDetails();
    BibliographicDetailsId bid = new BibliographicDetailsId();
    DocumentDetails doc = new DocumentDetails();
    DocumentDetails doc1 = new DocumentDetails();
    DocumentDetailsId did = new DocumentDetailsId();
    AccessionRegister ac = new AccessionRegister();
    AccessionRegister ac1 = new AccessionRegister();
    AccessionRegisterId aid = new AccessionRegisterId();

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BibliographicDetailEntryActionForm1 bform = (BibliographicDetailEntryActionForm1) form;
        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        String acc_no = (String) bform.getAccession_no();
        int record_no = bform.getRecord_no();
        int biblio_id = bform.getBiblio_id();
        doc1 = dao.searchDoc(biblio_id, record_no, library_id, sub_library_id);
        ac1 = dao.searchAccByRecord(library_id, sub_library_id, record_no);
        String button = (String) bform.getButton();
        if (button.equals("Delete")) {
            if (StringUtils.isEmpty(acc_no)) {
                String msg1 = "Accession no field can not be left blank";
                request.setAttribute("msg2", msg1);
            } else {
                BibliographicDetailsId dd = new BibliographicDetailsId(bform.getBiblio_id(), library_id, sub_library_id);
                bib.setId(dd);
                did.setDocumentId(doc1.getId().getDocumentId());
                did.setLibraryId(library_id);
                did.setSublibraryId(sub_library_id);
                doc.setId(did);
                doc.setRecordNo(record_no);
                doc.setBibliographicDetails(bib);
                                    if(!doc1.getStatus().equals("available"))
                    {
                        String msg1 = "This item is either checked out or reserved can not update details";
                        request.setAttribute("msg2", msg1);
                        return mapping.findForward(SUCCESS);
                    }
 else{
 doc.setStatus(doc1.getStatus());
 }
                doc.setDocumentType(bform.getDocument_type());
                doc.setAccessionType(bform.getAccession_type());
                doc.setTitle(bform.getTitle());
                doc.setSubtitle(bform.getSubtitle());
                doc.setBookType(bform.getBook_type());
                doc.setSubject(bform.getSubject());
                doc.setAltTitle(bform.getAlt_title());
                doc.setEdition(bform.getEdition());
                doc.setNoOfCopies(bform.getNo_of_copies());
                doc.setStatementResponsibility(bform.getStatement_responsibility());
                doc.setMainEntry(bform.getMain_entry());
                doc.setPublisherName(bform.getPublisher_name());
                doc.setPublicationPlace(bform.getPublication_place());
                doc.setAddedEntry(bform.getAdded_entry());
                doc.setAddedEntry1(bform.getAdded_entry0());
                doc.setAddedEntry2(bform.getAdded_entry1());
                doc.setAddedEntry3(bform.getAdded_entry2());
                doc.setPublishingYear(bform.getPublishing_year());
                doc.setLccNo(bform.getLCC_no());
                doc.setCallNo(bform.getCall_no());
                doc.setIsbn10(bform.getIsbn10());
                doc.setIsbn13(bform.getIsbn13());
                doc.setVolumeNo(bform.getVolume_no());
                doc.setAccessionNo(bform.getAccession_no());
                doc.setLocation(bform.getLocation());
                doc.setShelvingLocation(bform.getShelving_location());
                doc.setIndexNo(bform.getIndex_no());
                doc.setNoOfPages(bform.getNo_of_pages());
                doc.setPhysicalWidth(bform.getPhysical_width());
                doc.setBindType(bform.getBind_type());
                doc.setNoOfCopies(bform.getNo_of_copies());
                doc.setSeries(bform.getSer_note());
                doc.setBiblioId(bform.getBiblio_id());
                aid.setLibraryId(library_id);
                aid.setSublibraryId(sub_library_id);
                aid.setRecordNo(record_no);
                ac.setId(aid);
                ac.setAccessionNo(bform.getAccession_no());
                ac.setBiblioId(bform.getBiblio_id());
                ac.setBibliographicDetails(bib);
                ac.setVolumeNo(bform.getVolume_no());
                ac.setLocation(bform.getLocation());
                ac.setShelvingLocation(bform.getShelving_location());
                ac.setIndexNo(bform.getIndex_no());
                ac.setNoOfPages(bform.getNo_of_pages());
                ac.setPhysicalWidth(bform.getPhysical_width());
                ac.setBindType(bform.getBind_type());
                bid.setBiblioId(bform.getBiblio_id());
                bid.setLibraryId(library_id);
                bid.setSublibraryId(sub_library_id);
                bib.setId(bid);
                bib.setBookType(bform.getBook_type());
                bib.setDocumentType(bform.getDocument_type());
                bib.setTitle(bform.getTitle());
                bib.setSubtitle(bform.getSubtitle());
                bib.setStatementResponsibility(bform.getStatement_responsibility());
                bib.setMainEntry(bform.getMain_entry());
                bib.setAddedEntry(bform.getAdded_entry());
                bib.setAddedEntry1(bform.getAdded_entry0());
                bib.setAddedEntry2(bform.getAdded_entry1());
                bib.setAddedEntry3(bform.getAdded_entry2());
                bib.setPublisherName(bform.getPublisher_name());
                bib.setPublicationPlace(bform.getPublication_place());
                bib.setPublishingYear(bform.getPublishing_year());
                bib.setLccNo(bform.getLCC_no());
                bib.setIsbn10(bform.getIsbn10());
                bib.setIsbn13(bform.getIsbn13());
                bib.setEdition(bform.getEdition());
                bib.setCallNo(bform.getCall_no());
                bib.setAltTitle(bform.getAlt_title());
                bib.setSubject(bform.getSubject());
                bib.setNotes(bform.getNotes());
                bib.setSeries(bform.getSer_note());
                bib.setAbstract_(bform.getThesis_abstract());
                bib.setNoOfCopies(bform.getNo_of_copies());
                bib.setNotes(bform.getNotes());
                dao.deletedocItem(acc_no, library_id, sub_library_id);
                dao.deleteaccItem(acc_no, library_id, sub_library_id);
                dao.update(bib);
                List items = dao.getItems(library_id, sub_library_id, biblio_id);
                session.setAttribute("opacList", items);
                String msg2 = "Item detail is deleted successfully";
                request.setAttribute("msg1", msg2);
                return mapping.findForward(SUCCESS);
            }
        }
        return mapping.findForward(SUCCESS);
    }
}
