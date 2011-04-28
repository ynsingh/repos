/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.cataloguing;

import com.myapp.struts.hbm.BibliographicDetails;
import com.myapp.struts.hbm.BibliographicDetailsId;
import com.myapp.struts.hbm.AcqFinalDemandList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.cataloguingDAO.BibliopgraphicEntryDAO;
import com.myapp.struts.hbm.BookCategory;
import com.myapp.struts.hbm.BookCategoryId;
import com.myapp.struts.hbm.DocumentCategory;
import com.myapp.struts.hbm.DocumentCategoryId;
import com.myapp.struts.hbm.Location;
import com.myapp.struts.hbm.MixLocationSublibrary;
import com.myapp.struts.hbm.SubLibrary;
import com.myapp.struts.systemsetupDAO.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author <a href="mailto:asif633@gmail.com">Asif Iqubal</a>
 */
public class BibliographicDetailEntryAction extends org.apache.struts.action.Action {

    BibliographicDetailsId bibid = new BibliographicDetailsId();
    BibliographicDetails bib = new BibliographicDetails();
    AcqFinalDemandList acqdemand = new AcqFinalDemandList();
    BibliopgraphicEntryDAO dao = new BibliopgraphicEntryDAO();

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BibliographicDetailEntryActionForm bibform = (BibliographicDetailEntryActionForm) form;
        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        String isbn10 = bibform.getIsbn10();
        String title = bibform.getTitle();
        String doc_type = bibform.getDocument_type();
        String button = bibform.getButton();
        List a = dao.getTitles(title, library_id, sub_library_id, doc_type);//own
        List b = dao.getTitles(title, library_id, library_id, doc_type);//main
        List d = dao.getTitles2(title, library_id, doc_type);//all
        List c = dao.getTitles1(title, library_id, sub_library_id);//acquisition
        List<DocumentCategory> ll=DocumentCategoryDAO.ListbookType(library_id,sub_library_id);
                if(ll.isEmpty()){
                        String msg1 = "You need to set document category";
                        request.setAttribute("msg1", msg1);
                        return mapping.findForward("fail");
        }
        ArrayList list2=new ArrayList();
        System.out.println(ll.size());
        for(int i=0;i<ll.size();i++)
        {
          List obj = (List)ll.get(i);
          String details = (String)obj.get(0);
          String book_type=(String)obj.get(1);
          System.out.println("Book Type"+book_type);
          System.out.println("Book details"+details);
          DocumentCategory book=new DocumentCategory();
          DocumentCategoryId bookid=new DocumentCategoryId(book_type, library_id, sub_library_id);
                    
          book.setDocumentCategoryName(details);
          book.setId(bookid);
          list2.add(book);
         }
        session.setAttribute("DocumentCategory", list2);
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
                    }
                    if (!d.isEmpty()) {
                        session.setAttribute("opacList", d);
                        return mapping.findForward("view4");
                    }
                     else if (!b.isEmpty()) {
                        session.setAttribute("title", title);
                        session.setAttribute("isbn10", isbn10);
                        session.setAttribute("doc_type", doc_type);
                        session.setAttribute("opacList", b);
                        return mapping.findForward("view1");
                    }
                    else if (!c.isEmpty()) {
                        session.setAttribute("opacList", c);
                        return mapping.findForward("view2");
                    } else {
                        bibform.setDocument_type(doc_type);
                        bibform.setTitle(title);
                        bibform.setSubtitle("");
                        bibform.setStatement_responsibility("");
                        bibform.setMain_entry("");
                        bibform.setAdded_entry("");
                        bibform.setPublisher_name("");
                        bibform.setPublication_place("");
                        bibform.setPublishing_year("");
                        bibform.setLCC_no("");
                        bibform.setIsbn10(StringUtils.deleteWhitespace(isbn10));
                        bibform.setIsbn13("");
                        bibform.setEdition("");
                        bibform.setCall_no("");
                        bibform.setAlt_title("");
                        bibform.setAlt_title("");
                        bibform.setSubject("");
                        bibform.setThesis_abstract("");
                        bibform.setNotes("");
                        bibform.setNo_of_copies(0);
                        return mapping.findForward("new");
                    }
                } else if (!StringUtils.isEmpty(isbn10)) {
                    bib = dao.search1Isbn10(isbn10, library_id, sub_library_id);
                    if (bib != null) {
                        String msg1 = "This isbn already exists enter different";
                        request.setAttribute("msg1", msg1);
                        return mapping.findForward("fail");
                    } else {
                        bibform.setDocument_type(doc_type);
                        bibform.setTitle(title);
                        bibform.setSubtitle("");
                        bibform.setStatement_responsibility("");
                        bibform.setMain_entry("");
                        bibform.setAdded_entry("");
                        bibform.setPublisher_name("");
                        bibform.setPublication_place("");
                        bibform.setPublishing_year("");
                        bibform.setLCC_no("");
                        bibform.setIsbn10(StringUtils.deleteWhitespace(isbn10));
                        bibform.setIsbn13("");
                        bibform.setEdition("");
                        bibform.setCall_no("");
                        bibform.setAlt_title("");
                        bibform.setAlt_title("");
                        bibform.setSubject("");
                        bibform.setThesis_abstract("");
                        bibform.setNotes("");
                        bibform.setBook_type("");
                        bibform.setNo_of_copies(0);
                        return mapping.findForward("new");
                    }
                } else {
                    bibform.setDocument_type(doc_type);
                    bibform.setTitle(title);
                    bibform.setSubtitle("");
                    bibform.setStatement_responsibility("");
                    bibform.setMain_entry("");
                    bibform.setAdded_entry("");
                    bibform.setPublisher_name("");
                    bibform.setPublication_place("");
                    bibform.setPublishing_year("");
                    bibform.setLCC_no("");
                    bibform.setIsbn10(StringUtils.deleteWhitespace(isbn10));
                    bibform.setIsbn13("");
                    bibform.setEdition("");
                    bibform.setCall_no("");
                    bibform.setAlt_title("");
                    bibform.setAlt_title("");
                    bibform.setSubject("");
                    bibform.setThesis_abstract("");
                    bibform.setNotes("");
                    bibform.setBook_type("");
                    bibform.setNo_of_copies(0);
                    return mapping.findForward("new");
                }
            } else if (button.equals("Update")) {
                if (!StringUtils.isEmpty(isbn10)) {
                    bib = dao.search2Isbn10(title, isbn10, library_id, sub_library_id);
                    if (bib != null) {
                        System.out.println("GGGGGGGGHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH"+bib.getNoOfCopies());
                        bibform.setNo_of_copies(bib.getNoOfCopies());
                        bibform.setMain_entry(bib.getMainEntry());
                        bibform.setAdded_entry(bib.getAddedEntry());
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
                        return mapping.findForward("update");
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
                    bib = dao.search2Isbn10(title, isbn10, library_id, sub_library_id);
                    if (bib != null) {
                        bibform.setNo_of_copies(bib.getNoOfCopies());
                        bibform.setMain_entry(bib.getMainEntry());
                        bibform.setThesis_abstract(bib.getAbstract_());
                        bibform.setNotes(bib.getNotes());
                        bibform.setSubject(bib.getSubject());
                        bibform.setAdded_entry(bib.getAddedEntry());
                        bibform.setAdded_entry(bib.getAddedEntry());
                        bibform.setAdded_entry0(bib.getAddedEntry1());
                        bibform.setAdded_entry1(bib.getAddedEntry2());
                        bibform.setAdded_entry2(bib.getAddedEntry3());
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
                        bibform.setSer_note(bib.getSeries());
                        bibform.setAlt_title(bib.getAltTitle());
                        return mapping.findForward("delete1");
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
                    bib = dao.search2Isbn10(title, isbn10, library_id, sub_library_id);
                    if (bib != null) {
                        bibform.setNo_of_copies(bib.getNoOfCopies());
                        bibform.setMain_entry(bib.getMainEntry());
                        bibform.setThesis_abstract(bib.getAbstract_());
                        bibform.setNotes(bib.getNotes());
                        bibform.setSubject(bib.getSubject());
                        bibform.setAdded_entry(bib.getAddedEntry());
                        bibform.setAdded_entry(bib.getAddedEntry());
                        bibform.setAdded_entry0(bib.getAddedEntry1());
                        bibform.setAdded_entry1(bib.getAddedEntry2());
                        bibform.setAdded_entry2(bib.getAddedEntry3());
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
                        bibform.setSer_note(bib.getSeries());
                        bibform.setAccession_type(bib.getAccessionType());
                       
                        bibform.setBook_type(bib.getBookType());
                        
                        bibform.setStatement_responsibility(bib.getStatementResponsibility());
                        bibform.setAlt_title(bib.getAltTitle());
                        return mapping.findForward("view");
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
