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
import com.myapp.struts.systemsetupDAO.DocumentCategoryDAO;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import java.util.Locale;
import java.util.ResourceBundle;
import com.myapp.struts.utility.DateCalculation;


/**
 *
 * @author edrp02
 */
public class AccessionEntryJournalAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
    BibliographicEntryDAO dao = new BibliographicEntryDAO();
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
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
          BibliographicDetailEntryActionForm bform = (BibliographicDetailEntryActionForm) form;
        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        DocumentCategoryDAO doccatdao=new DocumentCategoryDAO();

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
        String button = (String) bform.getButton();
        String lan=(String) bform.getLanguage().toUpperCase();
        System.out.println(lan+"             :::::::::::::");
        String acc_no=lan+acc_no1;
        System.out.println("Sambhalke         "+acc_no);
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
                    DocumentCategory dc = (DocumentCategory)doccatdao.searchDocumentCategoryByName(library_id, sub_library_id, bform.getBook_type());
                    if(dc!=null)
                        doc.setBookType(dc.getId().getDocumentCategoryId());
                    else
                    doc.setBookType(bform.getBook_type());
                    doc.setSubject(bform.getSubject());
                    doc.setPublisherName(bform.getPublisher_name());
                    doc.setPublicationPlace(bform.getPublication_place());
                    doc.setPublishingYear(bform.getPublishing_year());
                    doc.setLccNo(bform.getLCC_no());
                    doc.setCallNo(bform.getCall_no());
                    doc.setVolumeNo(bform.getVolume_no());
                    doc.setAccessionNo(acc_no);
                    doc.setLocation(bform.getLocation());
                    doc.setShelvingLocation(bform.getShelving_location());
                    doc.setIndexNo(bform.getIndex_no());
                    doc.setNoOfPages(bform.getNo_of_pages());
                    doc.setSeries(bform.getSer_note());
                    doc.setCollation1(bform.getPhysical_width());
                    doc.setBindType(bform.getBind_type());
                    doc.setNotes(bform.getNotes());
                    doc.setBiblioId(bform.getBiblio_id());
                    doc.setPhysicalForm(bform.getPhysical_form());
                    doc.setTypeOfDisc(bform.getType_of_disc());
                    doc.setColour(bform.getColour());
                    doc.setEntryLanguage(bform.getLanguage());
                    doc.setPublisherName(bform.getPublisher_name());
                    doc.setPublicationPlace(bform.getPublication_place());
                    doc.setLatest(bform.getLatest());
                    doc.setWebsite(bform.getWebsite());
                    doc.setClas(bform.getClas());
                    doc.setFrequency(bform.getFrequency());
                    doc.setHistory(bform.getHistory());
                    doc.setIssn(bform.getIssn());
                    
                    aid.setLibraryId(library_id);
                    aid.setSublibraryId(sub_library_id);
                    aid.setRecordNo(maxrecord);
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
                    ac.setDateAcquired(DateCalculation.now());
                    dao.insert2(ac);
                    bid.setBiblioId(bform.getBiblio_id());
                    bid.setLibraryId(library_id);
                    bid.setSublibraryId(sub_library_id);
                    bib.setId(bid);
                    //bib.setBookType(bform.getBook_type());
                    DocumentCategory dc1 = (DocumentCategory)doccatdao.searchDocumentCategoryByName(library_id, sub_library_id, bform.getBook_type());
                    if(dc1!=null)
                        bib.setBookType(dc1.getId().getDocumentCategoryId());
                    else
                        bib.setBookType(bform.getBook_type());
                    bib.setDocumentType(bform.getDocument_type());
                    bib.setTitle(bform.getTitle());
                    bib.setPublisherName(bform.getPublisher_name());
                    bib.setPublicationPlace(bform.getPublication_place());
                    if(bform.getPublishing_year()!=null && bform.getPublishing_year().isEmpty()==false)
                      bib.setPublishingYear(Integer.parseInt(bform.getPublishing_year()));
                    else
                      bib.setPublishingYear(0);
                    bib.setLccNo(bform.getLCC_no());
                    bib.setCallNo(bform.getCall_no());
                    bib.setSubject(bform.getSubject());
                    bib.setSeries(bform.getSer_note());
                    bib.setNotes(bform.getNotes());
                    bib.setEntryLanguage(bform.getLanguage());
                    bib.setCollation1(bform.getPhysical_width());
                    bib.setTypeOfDisc(bform.getType_of_disc());
                    bib.setPublisherName(bform.getPublisher_name());
                    bib.setPublicationPlace(bform.getPublication_place());
                    bib.setLatest(bform.getLatest());
                    bib.setWebsite(bform.getWebsite());
                    bib.setClas(bform.getClas());
                    bib.setFrequency(bform.getFrequency());
                    bib.setHistory(bform.getHistory());
                    bib.setIssn(bform.getIssn());

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
