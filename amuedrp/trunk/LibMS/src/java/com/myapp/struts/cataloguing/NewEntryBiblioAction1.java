/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.cataloguing;

import com.myapp.struts.cataloguingDAO.BibliopgraphicEntryDAO;
import com.myapp.struts.hbm.BibliographicDetails;
import com.myapp.struts.hbm.BibliographicDetailsId;
import com.myapp.struts.hbm.BibliographicDetailsLang;
import com.myapp.struts.hbm.BibliographicDetailsLangId;
import com.myapp.struts.hbm.DocumentCategory;
import com.myapp.struts.hbm.DocumentDetails;
import com.myapp.struts.hbm.DocumentDetailsId;
import com.myapp.struts.systemsetupDAO.DocumentCategoryDAO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.commons.lang.StringUtils;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author EdRP-05
 */
public class NewEntryBiblioAction1 extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    BibliographicDetailsId bibid = new BibliographicDetailsId();
    BibliographicDetailsId bibid1 = new BibliographicDetailsId();
    BibliographicDetailsLang biblang = new BibliographicDetailsLang();
     BibliographicDetailsLang biblang1 = new BibliographicDetailsLang();
      BibliographicDetailsLang biblang2 = new BibliographicDetailsLang();
    BibliographicDetailsLangId biblangid = new BibliographicDetailsLangId();
    BibliographicDetails bib = new BibliographicDetails();
    BibliographicDetails bib2 = new BibliographicDetails();
    BibliographicDetails bib3 = new BibliographicDetails();
    BibliographicDetails bib1 = new BibliographicDetails();
    BibliopgraphicEntryDAO dao = new BibliopgraphicEntryDAO();
    DocumentDetails dd = new DocumentDetails();
    DocumentDetailsId ddid = new DocumentDetailsId();
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
  BibliographicDetailEntryActionForm bibform = (BibliographicDetailEntryActionForm) form;
        String add1 = bibform.getAdded_entry0();
        String button = bibform.getButton();
        String call_no = bibform.getCall_no();
        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String isbn10 = (String) bibform.getIsbn10();
        String isbn101=(String)bibform.getIsbn101();
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
          request.setCharacterEncoding("UTF-8");
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
        if (StringUtils.isEmpty(isbn10)) {
            isbn10 = null;
        }
            if (StringUtils.isEmpty(isbn101)) {
            isbn101 = null;
        }
        if (button.equals("Save")) {
            bib3 = dao.search1Isbn10(isbn10, library_id, sub_library_id);
            if (bib3 != null) {
                String msg3 = resource.getString("cataloguing.catoldtitleentry1.duplicateisbn");//You are trying to enter duplicate isbn enter different
                request.setAttribute("msg1", msg3);
                return mapping.findForward("fail");
            }
            bib2 = dao.searchcall(call_no, library_id, sub_library_id);
            if (bib2 != null) {
                 String msg3 = resource.getString("cataloguing.catoldtitleentry1.duplicatecall");//You are trying to enter duplicate call no enter different
                request.setAttribute("msg1", msg3);
                return mapping.findForward("fail");
            }
//                    biblang1 = dao.search1LangIsbn10(bibform.getIsbn101(), library_id, sub_library_id);
//            if (biblang1 != null) {
//                String msg3 = resource.getString("cataloguing.catoldtitleentry1.duplicateisbn");//You are trying to enter duplicate isbn enter different
//                request.setAttribute("msg1", msg3);
//                return mapping.findForward("fail");
//            }
//            biblang2 = dao.searchLangcall(bibform.getCall_no1(), library_id, sub_library_id);
//            if (biblang2 != null) {
//                 String msg3 = resource.getString("cataloguing.catoldtitleentry1.duplicatecall");//You are trying to enter duplicate call no enter different
//                request.setAttribute("msg1", msg3);
//                return mapping.findForward("fail");
//            }
            else {
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
                bib.setEntryLanguage(bibform.getLanguage());
                dao.insert(bib);
                if(bibform.getCheckbox().equals("Checked"))
                {
                biblangid.setBiblioId(biblio_id);
                biblangid.setLibraryId(library_id);
                biblangid.setSublibraryId(sub_library_id);
                biblang.setId(biblangid);
                biblang.setIsbn10(isbn101);
                biblang.setBookType(bibform.getBook_type());
                biblang.setDocumentType(bibform.getDocument_type());
                biblang.setTitle(bibform.getTitle1());
                biblang.setSubtitle(bibform.getSubtitle1());
                biblang.setStatementResponsibility(bibform.getStatement_responsibility1());
                biblang.setMainEntry(bibform.getMain_entry1());
                biblang.setAddedEntry(bibform.getAdded_entryl());
                biblang.setAddedEntry1(bibform.getAdded_entry01());
                biblang.setAddedEntry2(bibform.getAdded_entry11());
                biblang.setAddedEntry3(bibform.getAdded_entry21());
                biblang.setPublisherName(bibform.getPublisher_name1());
                biblang.setPublicationPlace(bibform.getPublication_place1());
                biblang.setPublishingYear(bibform.getPublishing_year1());
                biblang.setLccNo(bibform.getLCC_no1());
                biblang.setIsbn13(bibform.getIsbn131());
                biblang.setEdition(bibform.getEdition1());
                biblang.setCallNo(bibform.getCall_no1());
                biblang.setAltTitle(bibform.getAlt_title1());
                biblang.setSubject(bibform.getSubject1());
                biblang.setSeries(bibform.getSer_note1());
                biblang.setAbstract_(bibform.getThesis_abstract1());
                biblang.setNoOfCopies(bibform.getNo_of_copies());
                biblang.setNotes(bibform.getNotes());
                biblang.setEntryLanguage(bibform.getLanguage());
                dao.insertBiblang(biblang);
                }
                bibform.setTitle("");
                bibform.setIsbn10("");
                bibform.setDocument_type("");
                  String msg2 = resource.getString("cataloguing.catoldtitleentry1.savemsg")+biblio_id;//Data is saved successfully with biblio Id
                request.setAttribute("msg2", msg2);
                return mapping.findForward(SUCCESS);
            }
        }
        if (button.equals("Save and go for accessioning")) {
  bib3 = dao.search1Isbn10(isbn10, library_id, sub_library_id);
            if (bib3 != null) {
                String msg3 = resource.getString("cataloguing.catoldtitleentry1.duplicateisbn");//You are trying to enter duplicate isbn enter different
                request.setAttribute("msg1", msg3);
                return mapping.findForward("fail");
            }
            bib2 = dao.searchcall(call_no, library_id, sub_library_id);
            if (bib2 != null) {
                 String msg3 = resource.getString("cataloguing.catoldtitleentry1.duplicatecall");//You are trying to enter duplicate call no enter different
                request.setAttribute("msg1", msg3);
                return mapping.findForward("fail");
            }
//                    biblang1 = dao.search1LangIsbn10(bibform.getIsbn101(), library_id, sub_library_id);
//            if (biblang1 != null) {
//                String msg3 = resource.getString("cataloguing.catoldtitleentry1.duplicateisbn");//You are trying to enter duplicate isbn enter different
//                request.setAttribute("msg1", msg3);
//                return mapping.findForward("fail");
//            }
//            biblang2 = dao.searchLangcall(bibform.getCall_no1(), library_id, sub_library_id);
//            if (biblang2 != null) {
//                 String msg3 = resource.getString("cataloguing.catoldtitleentry1.duplicatecall");//You are trying to enter duplicate call no enter different
//                request.setAttribute("msg1", msg3);
//                return mapping.findForward("fail");
//            }
            else {
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
                bib.setIsbn10(isbn10);
                bib.setEdition(bibform.getEdition());
                bib.setCallNo(bibform.getCall_no());
                bib.setAltTitle(bibform.getAlt_title());
                bib.setSubject(bibform.getSubject());
                bib.setSeries(bibform.getSer_note());
                bib.setAbstract_(bibform.getThesis_abstract());
                bib.setNotes(bibform.getNotes());
                bib.setNoOfCopies(bibform.getNo_of_copies());
                bib.setEntryLanguage(bibform.getLanguage());
                dao.insert(bib);
                 if(bibform.getCheckbox().equals("Checked"))
                {
                biblangid.setBiblioId(biblio_id);
                biblangid.setLibraryId(library_id);
                biblangid.setSublibraryId(sub_library_id);
                biblang.setId(biblangid);
                biblang.setIsbn10(isbn101);
                biblang.setBookType(bibform.getBook_type());
                biblang.setDocumentType(bibform.getDocument_type());
                biblang.setTitle(bibform.getTitle1());
                biblang.setSubtitle(bibform.getSubtitle1());
                biblang.setStatementResponsibility(bibform.getStatement_responsibility1());
                biblang.setMainEntry(bibform.getMain_entry1());
                biblang.setAddedEntry(bibform.getAdded_entryl());
                biblang.setAddedEntry1(bibform.getAdded_entry01());
                biblang.setAddedEntry2(bibform.getAdded_entry11());
                biblang.setAddedEntry3(bibform.getAdded_entry21());
                biblang.setPublisherName(bibform.getPublisher_name1());
                biblang.setPublicationPlace(bibform.getPublication_place1());
                biblang.setPublishingYear(bibform.getPublishing_year1());
                biblang.setLccNo(bibform.getLCC_no1());
                biblang.setIsbn13(bibform.getIsbn131());
                biblang.setEdition(bibform.getEdition1());
                biblang.setCallNo(bibform.getCall_no1());
                biblang.setAltTitle(bibform.getAlt_title1());
                biblang.setSubject(bibform.getSubject1());
                biblang.setSeries(bibform.getSer_note1());
                biblang.setAbstract_(bibform.getThesis_abstract1());
                biblang.setNoOfCopies(bibform.getNo_of_copies());
                biblang.setNotes(bibform.getNotes());
                biblang.setEntryLanguage(bibform.getLanguage());
                dao.insertBiblang(biblang);
                }
                bibform.setBiblio_id(biblio_id);
                session.setAttribute("biblio_id", biblio_id);
                String msg2 = resource.getString("cataloguing.catoldtitleentry1.savemsg")+biblio_id;//Data is saved successfully with biblio Id
                request.setAttribute("msg1", msg2);
                String msg1 = "";
                request.setAttribute("msg2", msg1);
                DocumentCategory doc=(DocumentCategory)DocumentCategoryDAO.searchDocumentCategory(library_id, sub_library_id, bib.getBookType());
                        if(doc!=null)
                            bibform.setBook_type(doc.getDocumentCategoryName());
                return mapping.findForward("accession");
           }
        }
        if(button.equals("Update"))
      {
        int  biblio_id=bibform.getBiblio_id();
        bibid.setBiblioId(biblio_id);
        bibid.setLibraryId(library_id);
        bibid.setSublibraryId(sub_library_id);
        bib.setId(bibid);
        bib.setIsbn10(isbn10);
        bib.setBookType(bibform.getBook_type());
        bib.setDocumentType(bibform.getDocument_type());
        bib.setAccessionType(bibform.getAccession_type());
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
        bib.setAbstract_(bibform.getThesis_abstract());
        bib.setSeries(bibform.getSer_note());
        bib.setNotes(bibform.getNotes());
        bib.setNoOfCopies(bibform.getNo_of_copies());
        bib.setEntryLanguage(bibform.getLanguage());
          if(bibform.getCheckbox().equals("Checked"))
                {
                biblangid.setBiblioId(biblio_id);
                biblangid.setLibraryId(library_id);
                biblangid.setSublibraryId(sub_library_id);
                biblang.setId(biblangid);
                biblang.setIsbn10(isbn101);
                biblang.setBookType(bibform.getBook_type());
                biblang.setDocumentType(bibform.getDocument_type());
                biblang.setTitle(bibform.getTitle1());
                biblang.setSubtitle(bibform.getSubtitle1());
                biblang.setStatementResponsibility(bibform.getStatement_responsibility1());
                biblang.setMainEntry(bibform.getMain_entry1());
                biblang.setAddedEntry(bibform.getAdded_entryl());
                biblang.setAddedEntry1(bibform.getAdded_entry01());
                biblang.setAddedEntry2(bibform.getAdded_entry11());
                biblang.setAddedEntry3(bibform.getAdded_entry21());
                biblang.setPublisherName(bibform.getPublisher_name1());
                biblang.setPublicationPlace(bibform.getPublication_place1());
                biblang.setPublishingYear(bibform.getPublishing_year1());
                biblang.setLccNo(bibform.getLCC_no1());
                biblang.setIsbn13(bibform.getIsbn131());
                biblang.setEdition(bibform.getEdition1());
                biblang.setCallNo(bibform.getCall_no1());
                biblang.setAltTitle(bibform.getAlt_title1());
                biblang.setSubject(bibform.getSubject1());
                biblang.setSeries(bibform.getSer_note1());
                biblang.setAbstract_(bibform.getThesis_abstract1());
                biblang.setNoOfCopies(bibform.getNo_of_copies());
                biblang.setNotes(bibform.getNotes());
                biblang.setEntryLanguage(bibform.getLanguage());
                dao.updateBiblioLang(biblang);
                }
       System.out.println(bibform.getBiblio_id());
        BibliographicDetails biblio = dao.searchIsbn10ByBiblio(bibform.getCall_no(),bibform.getIsbn10(), bibform.getBiblio_id(), library_id, sub_library_id);
       BibliographicDetails uniq_calsearch = dao.searchCallNOByBiblio(bibform.getCall_no(), bibform.getBiblio_id(), library_id, sub_library_id);
     //  BibliographicDetailsLang bibliolang=dao.searchIsbn10LangByBiblio(isbn10, biblio_id, library_id, sub_library_id);
    //   BibliographicDetailsLang calllang=dao.searchCallNoLangByBiblio(call_no, biblio_id, library_id, sub_library_id);
       if (biblio != null) {
       String msg1 = resource.getString("cataloguing.catoldtitleentry1.duplicateisbn");//You are trying to enter duplicate isbn enter different
       request.setAttribute("msg1", msg1);
       bibform.setButton("Update");
       return mapping.findForward("failure");
       }
       else if (uniq_calsearch != null) {
       String msg1 = resource.getString("cataloguing.catoldtitleentry1.duplicatecall");//You are trying to enter duplicate call no enter different
       request.setAttribute("msg1", msg1);
       bibform.setButton("Update");
       return mapping.findForward("failure");
       }
 else{
       List  jj=dao.searchDoc1(biblio_id, library_id,sub_library_id);
       if(!jj.isEmpty())
       {
        int i=jj.size();
        for( i=0;i<jj.size();i++)
        {
        dd=(DocumentDetails)jj.get(i);
        ddid.setSublibraryId(sub_library_id);
        ddid.setLibraryId(library_id);
        ddid.setDocumentId(dd.getId().getDocumentId());
        dd.setId(ddid);
        dd.setStatus(dd.getStatus());
        dd.setIsbn10(isbn10);
        dd.setIsbn13(bibform.getIsbn13());
        dd.setSeries(bibform.getSer_note());
        dd.setBiblioId(biblio_id);
        dd.setCallNo(bibform.getCall_no());
        dd.setMainEntry(bibform.getMain_entry());
        dd.setAddedEntry(bibform.getAdded_entry());
        dd.setAddedEntry1(bibform.getAdded_entry0());
        dd.setAddedEntry2(bibform.getAdded_entry1());
        dd.setAddedEntry3(bibform.getAdded_entry2());
        dd.setDocumentType(bibform.getDocument_type());
        dd.setEdition(bibform.getEdition());
        dd.setIndexNo(bibform.getIndex_no());
        dd.setNoOfCopies(bibform.getNo_of_copies());
        dd.setNoOfPages(bibform.getNo_of_pages());
        dd.setPhysicalWidth(bibform.getPhysical_width());
        dd.setPublicationPlace(bibform.getPublication_place());
        dd.setPublisherName(bibform.getPublisher_name());
        dd.setPublishingYear(bibform.getPublishing_year());
        dd.setSubtitle(bibform.getSubtitle());
        dd.setTitle(bibform.getTitle());
        dd.setEntryLanguage(bibform.getLanguage());
        dd.setBibliographicDetails(bib);
        DocumentCategory dc = (DocumentCategory)DocumentCategoryDAO.searchDocumentCategoryByName(library_id, sub_library_id, bibform.getBook_type());
                    if(dc!=null)
                        dd.setBookType(dc.getId().getDocumentCategoryId());
                    else
                        dd.setBookType(bibform.getBook_type());
        dao.update1(dd);
        }
       }
         dao.update(bib);
         
        bibform.setIsbn10("");
        bibform.setTitle("");
        String msg1 =resource.getString("cataloguing.catoldttitleupdate1.recordupdate"); //Record updated successfully
        request.setAttribute("msg2",msg1);
        return mapping.findForward(SUCCESS);
 }
    }
    if(button.equals("Delete")){
          int  biblio_id=bibform.getBiblio_id();
          List  jj=dao.searchDoc1(biblio_id, library_id,sub_library_id);
       if(!jj.isEmpty())
       {
       String msg1 =resource.getString("cataloguing.catoldttitleupdate1.cannotdelete"); //There are accessioned items of this title can not delete this title
       request.setAttribute("msg1", msg1);
       bibform.setButton("Delete");
       return mapping.findForward("failure");
       }
       dao.delete( biblio_id, library_id,sub_library_id);
       dao.deleteBiblioLang(biblio_id, library_id, sub_library_id);
        bibform.setIsbn10("");
        bibform.setTitle("");
        String msg1 =resource.getString("cataloguing.catoldttitleupdate1.recordelete"); //Record deleted successfully
        request.setAttribute("msg2",msg1);
        return mapping.findForward(SUCCESS);
        }
    return mapping.findForward(SUCCESS);
    }
}
