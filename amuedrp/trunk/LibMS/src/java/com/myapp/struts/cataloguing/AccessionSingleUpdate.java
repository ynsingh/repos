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
import com.myapp.struts.cataloguingDAO.BibliographicEntryDAO;
import com.myapp.struts.hbm.*;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import java.util.Locale;
import java.util.ResourceBundle;
/**
 *
 * @author <a href="mailto:asif633@gmail.com">Asif Iqubal</a>
 */
public class AccessionSingleUpdate extends org.apache.struts.action.Action {

    private static final String SUCCESS = "success";
    BibliographicEntryDAO dao = new BibliographicEntryDAO();
    BibliographicDetails bib = new BibliographicDetails();
    BibliographicDetailsId bid = new BibliographicDetailsId();
    DocumentDetails doc = new DocumentDetails();
    DocumentDetails doc1 = new DocumentDetails();
    DocumentDetailsId did = new DocumentDetailsId();
    AccessionRegister ac = new AccessionRegister();
    AccessionRegister ac1 = new AccessionRegister();
    AccessionRegisterId aid = new AccessionRegisterId();
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BibliographicDetailEntryActionForm bform = (BibliographicDetailEntryActionForm) form;
        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
                   try{
        locale1=(String)session.getAttribute("locale");
    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
        System.out.println("locale="+locale1);
    }
    else locale1="en";
    }catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align="left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);
        String acc_no1 = (String) bform.getAccession_no();
        String lan=(String) bform.getLanguage();
        String acc_no=lan+acc_no1;
        int record_no = bform.getRecord_no();
        int biblio_id = bform.getBiblio_id();
        doc1 = dao.searchDoc(biblio_id, record_no, library_id, sub_library_id);
        ac1 = dao.searchAccByRecord(library_id, sub_library_id, record_no);
        String button = (String) bform.getButton();
        if (button.equals("Update")) {
            if (StringUtils.isEmpty(acc_no)) {
             String msg1 = resource.getString("cataloguing.ownaccessionentryaction.accessblank");//Accession no field can not be left blank
                request.setAttribute("msg2", msg1);
            } else {
                    AccessionRegister hh = dao.searchaccupdate(record_no,library_id, sub_library_id, acc_no);
                    if (hh != null) {
                       String msg1 = resource.getString("cataloguing.ownaccessionentryaction.accessduplicate");//This accession no already exists enter different
                        request.setAttribute("msg2", msg1);
                        return mapping.findForward(SUCCESS);
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
                        String msg1= resource.getString("cataloguing.ownaccessionentryaction.reserveditem");//This item is either checked out or reserved, can not update details
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
                    doc.setAccessionNo(acc_no);
                    doc.setLocation(bform.getLocation());
                    doc.setShelvingLocation(bform.getShelving_location());
                    doc.setIndexNo(bform.getIndex_no());
                    doc.setNoOfPages(bform.getNo_of_pages());
                    doc.setPhysicalWidth(bform.getPhysical_width());
                    doc.setBindType(bform.getBind_type());
                    doc.setSeries(bform.getSer_note());
                    doc.setBiblioId(bform.getBiblio_id());
                    doc.setTypeOfDisc(bform.getType_of_disc());
                    doc.setPhysicalForm(bform.getPhysical_form());
                    doc.setColour(bform.getColour());
                    doc.setEntryLanguage(bform.getLanguage());
                    aid.setLibraryId(library_id);
                    aid.setSublibraryId(sub_library_id);
                    aid.setRecordNo(record_no);
                    ac.setId(aid);
                    ac.setAccessionNo(acc_no);
                    ac.setBiblioId(bform.getBiblio_id());
                    ac.setBibliographicDetails(bib);
                    ac.setVolumeNo(bform.getVolume_no());
                    ac.setLocation(bform.getLocation());
                    ac.setShelvingLocation(bform.getShelving_location());
                    ac.setIndexNo(bform.getIndex_no());
                    ac.setNoOfPages(bform.getNo_of_pages());
                    ac.setPhysicalWidth(bform.getPhysical_width());
                    ac.setBindType(bform.getBind_type());
                    ac.setPhysicalDescription(bform.getPhysical_desc());
                    ac.setPhysicalForm(bform.getPhysical_form());
                    ac.setColour(bform.getColour());
                    ac.setDateAcquired(bform.getDate_acquired());
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
                    bib.setPublishingYear(Integer.parseInt(bform.getPublishing_year()));
                    bib.setLccNo(bform.getLCC_no());
                    bib.setIsbn13(bform.getIsbn13());
                    bib.setIsbn10(bform.getIsbn10());
                    bib.setEdition(bform.getEdition());
                    bib.setCallNo(bform.getCall_no());
                    bib.setAltTitle(bform.getAlt_title());
                    bib.setSubject(bform.getSubject());
                    bib.setNotes(bform.getNotes());
                    bib.setSeries(bform.getSer_note());
                    bib.setAbstract_(bform.getThesis_abstract());
                    bib.setNoOfCopies(bform.getNo_of_copies());
                    bib.setNotes(bform.getNotes());
                    bib.setTypeOfDisc(bform.getType_of_disc());
                    bib.setEntryLanguage(bform.getLanguage());
                    dao.update1(doc);
                    dao.update2(ac);
                    dao.update(bib);
                    List items = dao.getItems(library_id, sub_library_id, biblio_id);
                    session.setAttribute("opacList", items);
                     String msg2= resource.getString("cataloguing.accessionsingleupdate.itemupdate");//Item detail is updated successfully
                    request.setAttribute("msg1", msg2);
                    return mapping.findForward(SUCCESS);
                }
            }
        }
        return mapping.findForward(SUCCESS);
    }
}
