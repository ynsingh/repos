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
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author EdRP-05
 */
public class DiscAccessionEntryAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    BibliopgraphicEntryDAO dao = new BibliopgraphicEntryDAO();
    BibliographicDetails bib = new BibliographicDetails();
    BibliographicDetailsId bid = new BibliographicDetailsId();
    DocumentDetails doc = new DocumentDetails();
    DocumentDetailsId did = new DocumentDetailsId();
    AccessionRegister ac = new AccessionRegister();
    AccessionRegisterId aid = new AccessionRegisterId();
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
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
        String acc_no = (String) bform.getAccession_no();
        String button = (String) bform.getButton();
        if (button.equals("Add Item")) {
            if (StringUtils.isEmpty(acc_no)) {
String msg1 = resource.getString("cataloguing.ownaccessionentryaction.accessblank");//Accession no field can not be left blank
                request.setAttribute("msg2", msg1);
            } else {
                AccessionRegister hh = dao.searchacc(library_id, sub_library_id, acc_no);
                if (hh != null) {
                    String msg1 = resource.getString("cataloguing.ownaccessionentryaction.accessduplicate");//This accession no already exists enter different
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
                    doc.setRecordNo(maxrecord);
                    doc.setTitle(bform.getTitle());
                    doc.setSubtitle(bform.getSubtitle());
                    DocumentCategory dc = (DocumentCategory)DocumentCategoryDAO.searchDocumentCategoryByName(library_id, sub_library_id, bform.getBook_type());
                    if(dc!=null)
                        doc.setBookType(dc.getId().getDocumentCategoryId());
                    else
                    doc.setBookType(bform.getBook_type());
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
                    doc.setNoOfPages(bform.getNo_of_pages());
                    doc.setPhysicalWidth(bform.getPhysical_width());
                    doc.setBindType(bform.getBind_type());
                    doc.setNotes(bform.getSer_note());
                    doc.setBiblioId(bform.getBiblio_id());
                    doc.setPhysicalForm(bform.getPhysical_form());
                    doc.setTypeOfDisc(bform.getType_of_disc());
                    doc.setSeries(bform.getSer_note());
                    aid.setLibraryId(library_id);
                    aid.setSublibraryId(sub_library_id);
                    aid.setRecordNo(maxrecord);
                    ac.setId(aid);
                    ac.setAccessionNo(bform.getAccession_no());
                    ac.setBiblioId(bform.getBiblio_id());
                    ac.setBibliographicDetails(bib);
                  //  ac.setVolumeNo(bform.getVolume_no());
                    ac.setLocation(bform.getLocation());
                   ac.setPhysicalForm(bform.getPhysical_form());
                   ac.setPhysicalDescription(bform.getPhysical_desc());
                   ac.setColour(bform.getColour());
                   // ac.setShelvingLocation(bform.getShelving_location());
                   // ac.setIndexNo(bform.getIndex_no());
                   // ac.setNoOfPages(bform.getNo_of_pages());
                   // ac.setPhysicalWidth(bform.getPhysical_width());
                    ac.setBindType(bform.getBind_type());
                    dao.insert2(ac);
                    bid.setBiblioId(bform.getBiblio_id());
                    bid.setLibraryId(library_id);
                    bid.setSublibraryId(sub_library_id);
                    bib.setId(bid);
                    //bib.setBookType(bform.getBook_type());
                    DocumentCategory dc1 = (DocumentCategory)DocumentCategoryDAO.searchDocumentCategoryByName(library_id, sub_library_id, bform.getBook_type());
                    if(dc1!=null)
                        bib.setBookType(dc1.getId().getDocumentCategoryId());
                    else
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
                    bib.setAbstract_(bform.getThesis_abstract());
                    bib.setSeries(bform.getSer_note());
                    bib.setTypeOfDisc(bform.getType_of_disc());
                    bib.setNotes(bform.getNotes());
                    List a = dao.getItems(library_id, sub_library_id, bform.getBiblio_id());
                    int b = a.size();
                    bform.setNo_of_copies(b);
                    doc.setNoOfCopies(b);
                    bib.setNoOfCopies(b);
                    dao.update(bib);
                    dao.insert1(doc);
                     String msg2 = resource.getString("cataloguing.ownaccessionentryaction.accessdatasave")+maxrecord;//Item is saved successfully with record no:
                    request.setAttribute("msg1", msg2);
                    return mapping.findForward(SUCCESS);
                }
            }
        }
        return mapping.findForward(SUCCESS);
    }
}

