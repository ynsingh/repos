/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.cataloguing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.cataloguingDAO.BibliopgraphicEntryDAO;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import com.myapp.struts.systemsetupDAO.*;
import com.myapp.struts.hbm.*;
import java.util.ArrayList;

/**
 *
 * @author <a href="mailto:asif633@gmail.com">Asif Iqubal</a>
 */
public class AccessionEntry extends org.apache.struts.action.Action {

    private static final String SUCCESS = "success";
    BibliographicDetailsId bibid = new BibliographicDetailsId();
    BibliographicDetails bib = new BibliographicDetails();
    AcqFinalDemandList acqdemand = new AcqFinalDemandList();
    BibliopgraphicEntryDAO dao = new BibliopgraphicEntryDAO();
    int i;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BibliographicDetailEntryActionForm bibform = (BibliographicDetailEntryActionForm) form;
        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        List<Location> loc=LocationDAO.listlocation(library_id, sub_library_id);
                if(loc.isEmpty()){
                        String msg1 = "You need to set location";
                        request.setAttribute("msg1", msg1);
                        return mapping.findForward("fail");
        }
        List<MixLocationSublibrary> lmls=new ArrayList<MixLocationSublibrary>();
       if(library_id.equals(sub_library_id))
       {
           List<SubLibrary> sub1=DeptDAO.listsub1(library_id);
                           if(sub1.isEmpty()){
                        String msg1 = "You need to set sublibrary";
                        request.setAttribute("msg1", msg1);
                        return mapping.findForward("fail");
        }
           if(loc.size()>1)
           {
            for (int j = 0; j < loc.size(); j++)
            {
            MixLocationSublibrary mls=new MixLocationSublibrary();
            mls.setLocationId(loc.get(j).getId().getLocationId());
            mls.setLocationName(loc.get(j).getLocationName());
            lmls.add(mls);
            }
              List<SubLibrary> sub=DeptDAO.listsub(library_id,sub_library_id);
            if(sub.isEmpty()){
                        String msg1 = "You need to set sublibrary";
                        request.setAttribute("msg1", msg1);
                        return mapping.findForward("fail");
        }
            for (int k = 0; k < sub.size(); k++)
            {
             MixLocationSublibrary mls=new MixLocationSublibrary();
            mls.setLocationName(sub.get(k).getSublibName());
            mls.setLocationId(sub.get(k).getId().getSublibraryId());
            lmls.add(mls);
            }
           }
          else{
           if(sub1.size()==1){
           MixLocationSublibrary mls=new MixLocationSublibrary();
           mls.setLocationName(sub1.get(0).getSublibName());
           mls.setLocationId(sub1.get(0).getId().getSublibraryId());
           lmls.add(mls);
           }
           else{
               List<SubLibrary> sub=DeptDAO.listsub(library_id,sub_library_id);
           for (int j = 0; j < loc.size(); j++)
            {
            MixLocationSublibrary mls=new MixLocationSublibrary();
            mls.setLocationId(loc.get(j).getId().getLocationId());
            mls.setLocationName(loc.get(j).getLocationName());
            lmls.add(mls);
            }
           for (int k = 0; k < sub.size(); k++)
            {
             MixLocationSublibrary mls=new MixLocationSublibrary();
            mls.setLocationName(sub.get(k).getSublibName());
            mls.setLocationId(sub.get(k).getId().getSublibraryId());
            lmls.add(mls);
            }
       }
       }
       }
      else{
            for (int j = 0; j < loc.size(); j++) {
            MixLocationSublibrary mls=new MixLocationSublibrary();
            mls.setLocationId(loc.get(j).getId().getLocationId());
            mls.setLocationName(loc.get(j).getLocationName());
            lmls.add(mls);
        }
 }
        session.setAttribute("mixlist", lmls);
        String isbn10 = bibform.getIsbn10();
        String title = bibform.getTitle();
        String doc_type = bibform.getDocument_type();
        String button = bibform.getButton();
        List a = dao.getTitles(title, library_id, sub_library_id, doc_type);//own
        if (doc_type.equals("Book")) {
            isbn10 = StringUtils.trim(isbn10);
            if (button.equals("New")) {
                if (StringUtils.isEmpty(isbn10)) {
                    if (!a.isEmpty()) {
                        session.setAttribute("title", title);
                        session.setAttribute("isbn10", isbn10);
                        session.setAttribute("doc_type", doc_type);
                        session.setAttribute("opacList", a);                       
                        return mapping.findForward("own");
                    } else {
                        String msg1 = "This title does not exist";
                        request.setAttribute("msg1", msg1);
                        return mapping.findForward("fail");
                    }
                } else if (!StringUtils.isEmpty(isbn10)) {
                    bib = dao.search1Isbn10(isbn10, library_id, sub_library_id);
                    if (bib != null) {
                        if (bib.getNoOfCopies() == null) {
                            i = 0;
                        } else {
                            i = bib.getNoOfCopies();
                        }
                        bibform.setNo_of_copies(i);
                        bibform.setMain_entry(bib.getMainEntry());
                        bibform.setAdded_entry(bib.getAddedEntry());
                        bibform.setAdded_entry0(bib.getAddedEntry1());
                        bibform.setAdded_entry1(bib.getAddedEntry2());
                        bibform.setAdded_entry2(bib.getAddedEntry3());
                        bibform.setSer_note(bib.getSeries());
                        bibform.setBiblio_id(bib.getId().getBiblioId());
                        bibform.setLCC_no(bib.getLccNo());
                        bibform.setDocument_type(bib.getDocumentType());
                        bibform.setEdition(bib.getEdition());
                        bibform.setIsbn10(bib.getIsbn10());
                        bibform.setIsbn13(bib.getIsbn13());
                        bibform.setLibrary_id(bib.getId().getLibraryId());
                        bibform.setSub_library_id(bib.getId().getSublibraryId());
                        bibform.setPublication_place(bib.getPublicationPlace());
                        bibform.setPublisher_name(bib.getPublisherName());
                        bibform.setPublishing_year(bib.getPublishingYear());
                        bibform.setSubtitle(bib.getSubtitle());
                        bibform.setTitle(bib.getTitle());
                        bibform.setCall_no(bib.getCallNo());
                        bibform.setAccession_type(bib.getAccessionType());
                        bibform.setBook_type(bib.getBookType());
                        bibform.setStatement_responsibility(bib.getStatementResponsibility());
                        bibform.setAlt_title(bib.getAltTitle());
                        return mapping.findForward("new");
                    } else {
                        String msg1 = "This title does not exist";
                        request.setAttribute("msg1", msg1);
                        return mapping.findForward("fail");
                    }
                } else {
                    String msg1 = "This title does not exist";
                    request.setAttribute("msg1", msg1);
                    return mapping.findForward("fail");
                }
            } else if (button.equals("Update")) {
                if (!StringUtils.isEmpty(isbn10)) {
                    bib = dao.search1Isbn10(isbn10, library_id, sub_library_id);
                    if (bib != null) {
                        if (bib.getNoOfCopies() == null) {
                            i = 0;
                        } else {
                            i = bib.getNoOfCopies();
                        }
                        bibform.setNo_of_copies(i);
                        bibform.setMain_entry(bib.getMainEntry());
                        bibform.setAdded_entry(bib.getAddedEntry());
                        bibform.setAdded_entry0(bib.getAddedEntry1());
                        bibform.setAdded_entry1(bib.getAddedEntry2());
                        bibform.setAdded_entry2(bib.getAddedEntry3());
                        bibform.setBiblio_id(bib.getId().getBiblioId());
                        bibform.setSer_note(bib.getSeries());
                        bibform.setLCC_no(bib.getLccNo());
                        bibform.setDocument_type(bib.getDocumentType());
                        bibform.setEdition(bib.getEdition());
                        bibform.setIsbn10(bib.getIsbn10());
                        bibform.setIsbn13(bib.getIsbn13());
                        bibform.setLibrary_id(bib.getId().getLibraryId());
                        bibform.setSub_library_id(bib.getId().getSublibraryId());
                        bibform.setPublication_place(bib.getPublicationPlace());
                        bibform.setPublisher_name(bib.getPublisherName());
                        bibform.setPublishing_year(bib.getPublishingYear());
                        bibform.setSubtitle(bib.getSubtitle());
                        bibform.setTitle(bib.getTitle());
                        bibform.setCall_no(bib.getCallNo());
                        bibform.setAccession_type(bib.getAccessionType());
                        bibform.setBook_type(bib.getBookType());
                        bibform.setStatement_responsibility(bib.getStatementResponsibility());
                        bibform.setAlt_title(bib.getAltTitle());
                        return mapping.findForward("new");
                    } else {
                        String msg3 = "Title corresponding to the isbn is not found.";
                        request.setAttribute("msg1", msg3);
                        String msg2 = "";
                        request.setAttribute("msg2", msg2);
                        return mapping.findForward("fail");
                    }
                }
                if (!a.isEmpty()) {
                    session.setAttribute("opacList", a);
                    return mapping.findForward("view3");
                } else {
                    String msg3 = "Title not found.";
                    request.setAttribute("msg1", msg3);
                    String msg2 = "";
                    request.setAttribute("msg2", msg2);
                    return mapping.findForward("fail");
                }
            } else if (button.equals("Delete")) {
                if (!StringUtils.isEmpty(isbn10)) {
                    bib = dao.search1Isbn10(isbn10, library_id, sub_library_id);
                    if (bib != null) {
                         if (bib.getNoOfCopies() == null) {
                            i = 0;
                        } else {
                            i = bib.getNoOfCopies();
                        }
                        bibform.setNo_of_copies(i);
                        bibform.setMain_entry(bib.getMainEntry());
                        bibform.setAdded_entry(bib.getAddedEntry());
                        bibform.setAdded_entry0(bib.getAddedEntry1());
                        bibform.setAdded_entry1(bib.getAddedEntry2());
                        bibform.setAdded_entry2(bib.getAddedEntry3());
                        bibform.setBiblio_id(bib.getId().getBiblioId());
                        bibform.setLCC_no(bib.getLccNo());
                        bibform.setDocument_type(bib.getDocumentType());
                        bibform.setEdition(bib.getEdition());
                        bibform.setSer_note(bib.getSeries());
                        bibform.setIsbn10(bib.getIsbn10());
                        bibform.setIsbn13(bib.getIsbn13());
                        bibform.setLibrary_id(bib.getId().getLibraryId());
                        bibform.setSub_library_id(bib.getId().getSublibraryId());
                        bibform.setPublication_place(bib.getPublicationPlace());
                        bibform.setPublisher_name(bib.getPublisherName());
                        bibform.setPublishing_year(bib.getPublishingYear());
                        bibform.setSubtitle(bib.getSubtitle());
                        bibform.setTitle(bib.getTitle());
                        bibform.setCall_no(bib.getCallNo());
                        bibform.setAccession_type(bib.getAccessionType());
                        bibform.setBook_type(bib.getBookType());
                        bibform.setStatement_responsibility(bib.getStatementResponsibility());
                        bibform.setAlt_title(bib.getAltTitle());
                        return mapping.findForward("new");
                    } else {
                        String msg3 = "Title corresponding to the isbn is not found.";
                        request.setAttribute("msg1", msg3);
                        String msg2 = "";
                        request.setAttribute("msg2", msg2);
                        return mapping.findForward("fail");
                    }
                }
                if (!a.isEmpty()) {
                    session.setAttribute("opacList", a);
                    return mapping.findForward("delete");
                } else {
                    String msg3 = "Title not found.";
                    request.setAttribute("msg1", msg3);
                    String msg2 = "";
                    request.setAttribute("msg2", msg2);
                    return mapping.findForward("fail");
                }
            } else if (button.equals("View")) {
                if (!StringUtils.isEmpty(isbn10)) {
                    bib = dao.search1Isbn10(isbn10, library_id, sub_library_id);
                    if (bib != null) {
                         if (bib.getNoOfCopies() == null) {
                            i = 0;
                        } else {
                            i = bib.getNoOfCopies();
                        }
                        bibform.setNo_of_copies(i);
                        bibform.setMain_entry(bib.getMainEntry());
                        bibform.setAdded_entry(bib.getAddedEntry());
                        bibform.setAdded_entry0(bib.getAddedEntry1());
                        bibform.setAdded_entry1(bib.getAddedEntry2());
                        bibform.setAdded_entry2(bib.getAddedEntry3());
                        bibform.setBiblio_id(bib.getId().getBiblioId());
                        bibform.setSer_note(bib.getSeries());
                        bibform.setLCC_no(bib.getLccNo());
                        bibform.setDocument_type(bib.getDocumentType());
                        bibform.setEdition(bib.getEdition());
                        bibform.setIsbn10(bib.getIsbn10());
                        bibform.setIsbn13(bib.getIsbn13());
                        bibform.setLibrary_id(bib.getId().getLibraryId());
                        bibform.setSub_library_id(bib.getId().getSublibraryId());
                        bibform.setPublication_place(bib.getPublicationPlace());
                        bibform.setPublisher_name(bib.getPublisherName());
                        bibform.setPublishing_year(bib.getPublishingYear());
                        bibform.setSubtitle(bib.getSubtitle());
                        bibform.setTitle(bib.getTitle());
                        bibform.setCall_no(bib.getCallNo());
                        bibform.setAccession_type(bib.getAccessionType());
                        bibform.setBook_type(bib.getBookType());
                        bibform.setStatement_responsibility(bib.getStatementResponsibility());
                        bibform.setAlt_title(bib.getAltTitle());
                        return mapping.findForward("new");
                    } else {
                        String msg3 = "Title corresponding to the isbn is not found.";
                        request.setAttribute("msg1", msg3);
                        String msg2 = "";
                        request.setAttribute("msg2", msg2);
                        return mapping.findForward("fail");
                    }
                }
                if (!a.isEmpty()) {
                    session.setAttribute("opacList", a);
                    return mapping.findForward("viewAll");
                } else {
                    String msg3 = "Title not found.";
                    request.setAttribute("msg1", msg3);
                    String msg2 = "";
                    request.setAttribute("msg2", msg2);
                    return mapping.findForward("fail");
                }
            }
            if (button.equals("View All")) {
                List bib2 = dao.getBibliographicDetails(library_id, sub_library_id);
                session.setAttribute("opacList", bib2);
                return mapping.findForward("own");
            }
            if (button.equals("Back")) {
                return mapping.findForward("main");
            }

        }
        return mapping.findForward("fail");
    }
}
