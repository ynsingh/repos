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
import com.myapp.struts.systemsetupDAO.DocumentCategoryDAO;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author <a href="mailto:asif633@gmail.com">Asif Iqubal</a>
 */
public class AccessionEntryAction1 extends org.apache.struts.action.Action {

    private static final String SUCCESS = "success";
    BibliopgraphicEntryDAO dao = new BibliopgraphicEntryDAO();
    BibliographicDetails bib = new BibliographicDetails();
    BibliographicDetailsId bid = new BibliographicDetailsId();
    DocumentDetails doc = new DocumentDetails();
    DocumentDetailsId did = new DocumentDetailsId();
    AccessionRegister ac = new AccessionRegister();
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
        String button = (String) bform.getButton();
        if (button.equals("Add Item")) {
            if (StringUtils.isEmpty(acc_no)) {
                String msg1 = "Accession no field can not be left blank";
                request.setAttribute("msg2", msg1);
            } else {
                AccessionRegister hh = dao.searchacc(library_id, sub_library_id, acc_no);
                if (hh != null) {
                    String msg1 = "This accession no already exists enter different";
                    request.setAttribute("msg2", msg1);
                } else {
                    BibliographicDetailsId dd = new BibliographicDetailsId(bform.getBiblio_id(), library_id, sub_library_id);
                    bib.setId(dd);
                    Integer maxdoc = dao.returnMaxDocumentId(library_id, sub_library_id);
                    Integer maxrecord = (Integer) dao.returnMaxRecord(library_id, sub_library_id);
                    did.setDocumentId(maxdoc);
                    did.setLibraryId(library_id);
                    did.setSublibraryId(sub_library_id);
                    doc.setId(did);
                    doc.setBibliographicDetails(bib);
                    doc.setStatus("available");
                    doc.setDocumentType(bform.getDocument_type());
                    doc.setAccessionType(bform.getAccession_type());
                    doc.setTitle(bform.getTitle());
                    doc.setRecordNo(maxrecord);
                    doc.setSubtitle(bform.getSubtitle());
                    DocumentCategory dc = (DocumentCategory)DocumentCategoryDAO.searchDocumentCategoryByName(library_id, sub_library_id, bform.getBook_type());
                    if(dc!=null)
                        doc.setBookType(dc.getId().getDocumentCategoryId());
                    else
                        doc.setBookType(bform.getBook_type());
                   // doc.setBookType(bform.getBook_type());
                    doc.setSubject(bform.getSubject());
                    doc.setAltTitle(bform.getAlt_title());
                    doc.setEdition(bform.getEdition());
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
                    doc.setNotes(bform.getNotes());
                    doc.setNoOfPages(bform.getNo_of_pages());
                    doc.setPhysicalWidth(bform.getPhysical_width());
                    doc.setBindType(bform.getBind_type());
                    doc.setBiblioId(bform.getBiblio_id());
                    doc.setSeries(bform.getSer_note());
                    aid.setLibraryId(library_id);
                    aid.setSublibraryId(sub_library_id);
                    aid.setRecordNo(maxrecord);
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
                    DocumentCategory dc1 = (DocumentCategory)DocumentCategoryDAO.searchDocumentCategoryByName(library_id, sub_library_id, bform.getBook_type());
                    if(dc!=null)
                        bib.setBookType(dc1.getId().getDocumentCategoryId());
                    else
                        bib.setBookType(bform.getBook_type());
                    //bib.setBookType(bform.getBook_type());
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
                    bib.setNotes(bform.getSer_note());
                    bib.setSeries(bform.getSer_note());
                    bib.setAbstract_(bform.getThesis_abstract());
                    bib.setNotes(bform.getNotes());                   
                    dao.insert2(ac);
                    List a = dao.getItems(library_id, sub_library_id, bform.getBiblio_id());
                    int b = a.size();
                    bform.setNo_of_copies(b);
                    bib.setNoOfCopies(b);
                    doc.setNoOfCopies(b);
                    dao.insert1(doc);
                    dao.update(bib);              
                    String msg2 = "Item is saved successfully with record no:" + maxrecord;
                    request.setAttribute("msg1", msg2);
                    return mapping.findForward(SUCCESS);
                }
            }
        }
        return mapping.findForward(SUCCESS);
    }
}
