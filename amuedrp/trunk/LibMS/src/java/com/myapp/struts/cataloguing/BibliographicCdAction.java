/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.cataloguing;

import com.myapp.struts.cataloguingDAO.BibliographicEntryDAO;
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
import org.hibernate.HibernateException;
import org.apache.commons.lang.StringUtils;
import java.util.Locale;
import java.util.ResourceBundle;
/**
 *
 * @author khushnood
 */
/**
 *
 * @author <a href="mailtokhusnood@gmail.com">khushnood Abbas</a>
 */
public class BibliographicCdAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    BibliographicDetailsId bibid = new BibliographicDetailsId();
    BibliographicDetails bib = new BibliographicDetails();
    BibliographicDetails bib2 = new BibliographicDetails();
    BibliographicDetails bib1 = new BibliographicDetails();
     BibliographicDetails bib3 = new BibliographicDetails();
    BibliographicEntryDAO dao = new BibliographicEntryDAO();
    DocumentDetails dd = new DocumentDetails();
    DocumentDetailsId ddid = new DocumentDetailsId();
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";

   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BibliographicDetailEntryActionForm bibform = (BibliographicDetailEntryActionForm) form;
        String button = bibform.getButton();
        String call_no = bibform.getCall_no();
        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        String isbn10 = (String) bibform.getIsbn10();
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
//        {
//            Integer biblio_id = dao.returnMaxBiblioId(library_id, sub_library_id);
//            bibid.setBiblioId(biblio_id);
//            bibid.setLibraryId(library_id);
//            bibid.setSublibraryId(sub_library_id);
//            bib.setId(bibid);
//            bib.setDocumentType(bibform.getDocument_type());
//            bib.setTypeOfDisc(bibform.getType_of_disc());
//            bib.setSubtitle(bibform.getSubtitle());
//            bib.setMainEntry(bibform.getMain_entry());
//            bib.setPublisherName(bibform.getPublisher_name());
//            bib.setPublishingYear(bibform.getPublishing_year());
//            bib.setIsbn10(bibform.getIsbn10());
//            bib.setIsbn13(bibform.getIsbn13());
//            bib.setEdition(bibform.getEdition());
//            bib.setAltTitle(bibform.getAlt_title());
//            bib.setSeries(bibform.getSeries());
//            //   bib.setPhysicalForm(bibform.getPhysical_form()); in AccesstionRegister pojo khushnood
//            //bib.setphysical_desc(bibform.getPhysical_desc());      it has to be added in table
//            bib.setAbstract_(bibform.getThesis_abstract());
//            bib.setTitle(bibform.getTitle());
//            bib.setStatementResponsibility(bibform.getStatement_responsibility());
//            bib.setAddedEntry(bibform.getAdded_entry());
//            bib.setAddedEntry1(bibform.getAdded_entry0());
//            bib.setAddedEntry2(bibform.getAdded_entry1());
//            bib.setAddedEntry3(bibform.getAdded_entry2());
//            bib.setPublicationPlace(bibform.getPublication_place());
//            bib.setSource1(bibform.getSource());
//            bib.setDuration(bibform.getDuration());
//            //  bib.setColour(bibform.getColour());in AccesstionRegister pojo khushnood
//            bib.setCallNo(bibform.getCall_no());
//            bib.setSubject(bibform.getSubject());
//            bib.setNotes(bibform.getNotes());
//            bib.setAbstract_(bibform.getThesis_abstract());
//            bib.setBookType(bibform.getBook_type());
//        }
//            if (StringUtils.isEmpty(isbn10)) {
//            isbn10 = null;
//        }
        if (StringUtils.isEmpty(isbn10)) {
            isbn10 = null;
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
            } else {
                Integer biblio_id = dao.returnMaxBiblioId(library_id, sub_library_id);
                bibid.setBiblioId(biblio_id);
                bibid.setLibraryId(library_id);
                bibid.setSublibraryId(sub_library_id);
                bib.setId(bibid);
                bib.setDocumentType(bibform.getDocument_type());
                bib.setTypeOfDisc(bibform.getType_of_disc());
                bib.setSubtitle(bibform.getSubtitle());
                bib.setMainEntry(bibform.getMain_entry());
                bib.setPublisherName(bibform.getPublisher_name());
                if(bibform.getPublishing_year()!=null && bibform.getPublishing_year().isEmpty()==false)
                bib.setPublishingYear(Integer.parseInt(bibform.getPublishing_year()));
                else
                    bib.setPublishingYear(0);
                bib.setIsbn10(isbn10);
                bib.setIsbn13(bibform.getIsbn13());
                bib.setEdition(bibform.getEdition());
                bib.setAltTitle(bibform.getAlt_title());
                bib.setSeries(bibform.getSeries());
                bib.setAddedEntry(bibform.getAdded_entry());
                bib.setAddedEntry1(bibform.getAdded_entry0());
                bib.setAddedEntry2(bibform.getAdded_entry1());
                bib.setAddedEntry3(bibform.getAdded_entry2());
                // bib.setPhysicalForm(bibform.getPhysical_form()); in AccesstionRegister pojo khushnood
                //bib.setphysical_desc(bibform.getPhysical_desc());      it has to be added in table
                bib.setAbstract_(bibform.getThesis_abstract());
                bib.setTitle(bibform.getTitle());
                bib.setStatementResponsibility(bibform.getStatement_responsibility());
                bib.setPublicationPlace(bibform.getPublication_place());
                bib.setSource1(bibform.getSource());
                bib.setDuration(bibform.getDuration());
                //    bib.setColour(bibform.getColour());in AccesstionRegister pojo khushnood
                bib.setCallNo(bibform.getCall_no());
                bib.setSubject(bibform.getSubject());
                bib.setNotes(bibform.getNotes());
                bib.setAbstract_(bibform.getThesis_abstract());
                bib.setBookType(bibform.getBook_type());
                    dao.insert(bib);
                    //insert data into table
                    String msg2 = "Data is saved successfully with biblio Id:" + biblio_id;
                    request.setAttribute("msg2", msg2);
                    bibform.setTitle("");
                    bibform.setIsbn10("");
                    return mapping.findForward(SUCCESS);
            }
        }
        if (button.equals("Save and go for accession")) {
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
            } else {
                try {
  Integer biblio_id = dao.returnMaxBiblioId(library_id, sub_library_id);
                bibid.setBiblioId(biblio_id);
                bibid.setLibraryId(library_id);
                bibid.setSublibraryId(sub_library_id);
                bib.setId(bibid);
                bib.setDocumentType(bibform.getDocument_type());
                bib.setTypeOfDisc(bibform.getType_of_disc());
                bib.setSubtitle(bibform.getSubtitle());
                bib.setMainEntry(bibform.getMain_entry());
                bib.setPublisherName(bibform.getPublisher_name());
                if(bibform.getPublishing_year()!=null && bibform.getPublishing_year().isEmpty()==false)
                bib.setPublishingYear(Integer.parseInt(bibform.getPublishing_year()));
                else
                    bib.setPublishingYear(0);
                bib.setIsbn10(isbn10);
                bib.setIsbn13(bibform.getIsbn13());
                bib.setEdition(bibform.getEdition());
                bib.setAltTitle(bibform.getAlt_title());
                bib.setSeries(bibform.getSeries());
                bib.setAddedEntry(bibform.getAdded_entry());
                bib.setAddedEntry1(bibform.getAdded_entry0());
                bib.setAddedEntry2(bibform.getAdded_entry1());
                bib.setAddedEntry3(bibform.getAdded_entry2());
                // bib.setPhysicalForm(bibform.getPhysical_form()); in AccesstionRegister pojo khushnood
                //bib.setphysical_desc(bibform.getPhysical_desc());      it has to be added in table
                bib.setAbstract_(bibform.getThesis_abstract());
                bib.setTitle(bibform.getTitle());
                bib.setStatementResponsibility(bibform.getStatement_responsibility());
                bib.setPublicationPlace(bibform.getPublication_place());
                bib.setSource1(bibform.getSource());
                bib.setDuration(bibform.getDuration());
                //    bib.setColour(bibform.getColour());in AccesstionRegister pojo khushnood
                bib.setCallNo(bibform.getCall_no());
                bib.setSubject(bibform.getSubject());
                bib.setNotes(bibform.getNotes());
                bib.setBookType(bibform.getBook_type());
                dao.insert(bib);
                bibform.setBiblio_id(biblio_id);
                String msg2 = "Data is saved successfully with biblio Id:" + biblio_id;
                request.setAttribute("msg1", msg2);
                return mapping.findForward("accession");
                } catch (HibernateException e) {
                    request.setAttribute("msg2", "Data could not be saved due to database error");
                    return mapping.findForward("failure");
                }
            }
        }
        if (button.equals("Update")) {
          System.out.println("BBBBBBBBBBBBBBBBB           ::::"+bibform.getButton());
            try {
                /*if(request.getAttribute("beanform1_instance")!=null){
                bibform=(BibliographicDetailEntryActionForm)request.getAttribute("beanform1_instance");
                }*/
                bibid.setBiblioId(bibform.getBiblio_id());
                bibid.setLibraryId(library_id);
                bibid.setSublibraryId(sub_library_id);
                bib.setId(bibid);
                bib.setDocumentType(bibform.getDocument_type());
                bib.setTypeOfDisc(bibform.getType_of_disc());
                bib.setSubtitle(bibform.getSubtitle());
                bib.setMainEntry(bibform.getMain_entry());
                bib.setPublisherName(bibform.getPublisher_name());
                bib.setPublishingYear(Integer.parseInt(bibform.getPublishing_year()));
                bib.setIsbn10(isbn10);
                bib.setIsbn13(bibform.getIsbn13());
                bib.setEdition(bibform.getEdition());
                bib.setAltTitle(bibform.getAlt_title());
                bib.setSeries(bibform.getSeries());
                // bib.setPhysicalForm(bibform.getPhysical_form());in AccesstionRegister pojo khushnood
                //bib.setphysical_desc(bibform.getPhysical_desc());      it has to be added in table
                bib.setAbstract_(bibform.getThesis_abstract());
                bib.setTitle(bibform.getTitle());
                bib.setStatementResponsibility(bibform.getStatement_responsibility());
                 bib.setAddedEntry(bibform.getAdded_entry());
                bib.setAddedEntry1(bibform.getAdded_entry0());
                bib.setAddedEntry2(bibform.getAdded_entry1());
                bib.setAddedEntry3(bibform.getAdded_entry2());
                bib.setPublicationPlace(bibform.getPublication_place());
                bib.setSource1(bibform.getSource());
                bib.setDuration(bibform.getDuration());
                //  bib.setColour(bibform.getColour());in AccesstionRegister pojo khushnood
                bib.setCallNo(bibform.getCall_no());
                bib.setSubject(bibform.getSubject());
                bib.setNotes(bibform.getNotes());
                dao.update(bib);
                request.setAttribute("msg2", "Data has been successfully updated of biblio ID : " + bibform.getBiblio_id());
                return mapping.findForward("success");
            } catch (HibernateException e) {
                request.setAttribute("msg1", "Data could not be saved due to " + e);
                request.setAttribute("btnval", "Update");
                return mapping.findForward("failure");
            }
        }
        if (button.equals("Delete")) {
            int biblio_id = bibform.getBiblio_id();
            try {
                dao.delete(biblio_id, library_id, sub_library_id);
                if (dd != null) {
                    dao.delete1(biblio_id, library_id, sub_library_id);
                }
                bibform.setIsbn10("");
                String msg2 = "Record deleted successfully";
                request.setAttribute("msg2", msg2);
                return mapping.findForward("success");
            } catch (HibernateException e) {
                request.setAttribute("msg2", "Data could not be deleted due to database error");
                return mapping.findForward("failure");
            }
        }
        return mapping.findForward(SUCCESS);
    }
}
