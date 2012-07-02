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
import java.util.Locale;
import java.util.ResourceBundle;
import com.myapp.struts.hbm.*;
import java.util.ArrayList;

/**
 *
 * @author <a href="mailto:asif633@gmail.com">Asif Iqubal</a>
 */

public class AccessionEntry extends org.apache.struts.action.Action {

    BibliographicDetailsId bibid = new BibliographicDetailsId();
    AccessionRegister bib = new AccessionRegister();
    AcqFinalDemandList acqdemand = new AcqFinalDemandList();
    BibliopgraphicEntryDAO dao = new BibliopgraphicEntryDAO();
    int i;
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        BibliographicDetailEntryActionForm bibform = (BibliographicDetailEntryActionForm) form;
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
             List<DocumentCategory> ll=DocumentCategoryDAO.ListbookType(library_id,sub_library_id);
                if(ll.isEmpty()){
                      String msg1 = resource.getString("cataloguing.catoldtitle.setdocumentcategory");//You need to set document category
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
          DocumentCategory book=new DocumentCategory();
          DocumentCategoryId bookid=new DocumentCategoryId(book_type, library_id, sub_library_id);

          book.setDocumentCategoryName(details);
          book.setId(bookid);
          list2.add(book);
         }
        session.setAttribute("DocumentCategory", list2);
      List<Location> loc=LocationDAO.listlocation(library_id, sub_library_id);
                if(loc.isEmpty()){
                        String msg1 = resource.getString("cataloguing.catoldtitle.setlocation");//You need to set location
                        request.setAttribute("msg1", msg1);
                        return mapping.findForward("fail");
        }
        List<MixLocationSublibrary> lmls=new ArrayList<MixLocationSublibrary>();
//       if(library_id.equals(sub_library_id))
//       {
//           List<SubLibrary> sub1=DeptDAO.listsub1(library_id);
//                           if(sub1.isEmpty()){
//                        String msg1 = resource.getString("cataloguing.catoldtitle.setsublibrary");//You need to set sublibrary
//                        request.setAttribute("msg1", msg1);
//                        return mapping.findForward("fail");
//        }
//           if(loc.size()>1)
//           {
//            for (int j = 0; j < loc.size(); j++)
//            {
//            MixLocationSublibrary mls=new MixLocationSublibrary();
//            mls.setLocationId(loc.get(j).getId().getLocationId());
//            mls.setLocationName(loc.get(j).getLocationName());
//            lmls.add(mls);
//            }
//              List<SubLibrary> sub=DeptDAO.listsub(library_id,sub_library_id);
//            if(sub.isEmpty()){
//                        String msg1 = resource.getString("cataloguing.catoldtitle.setsublibrary");//You need to set sublibrary
//                        request.setAttribute("msg1", msg1);
//                        return mapping.findForward("fail");
//        }
//            for (int k = 0; k < sub.size(); k++)
//            {
//             MixLocationSublibrary mls=new MixLocationSublibrary();
//            mls.setLocationName(sub.get(k).getSublibName());
//            mls.setLocationId(sub.get(k).getId().getSublibraryId());
//            lmls.add(mls);
//            }
//           }
//          else{
//           if(sub1.size()==1){
//           MixLocationSublibrary mls=new MixLocationSublibrary();
//           mls.setLocationName(sub1.get(0).getSublibName());
//           mls.setLocationId(sub1.get(0).getId().getSublibraryId());
//           lmls.add(mls);
//           }
//           else{
//               List<SubLibrary> sub=DeptDAO.listsub(library_id,sub_library_id);
//           for (int j = 0; j < loc.size(); j++)
//            {
//            MixLocationSublibrary mls=new MixLocationSublibrary();
//            mls.setLocationId(loc.get(j).getId().getLocationId());
//            mls.setLocationName(loc.get(j).getLocationName());
//            lmls.add(mls);
//            }
//           for (int k = 0; k < sub.size(); k++)
//            {
//             MixLocationSublibrary mls=new MixLocationSublibrary();
//            mls.setLocationName(sub.get(k).getSublibName());
//            mls.setLocationId(sub.get(k).getId().getSublibraryId());
//            lmls.add(mls);
//            }
//       }
//       }
//       }
//      else{
        System.out.println(loc.size());
            for (int j = 0; j < loc.size(); j++) {
            MixLocationSublibrary mls=new MixLocationSublibrary();
            mls.setLocationId(loc.get(j).getId().getLocationId());
            mls.setLocationName(loc.get(j).getLocationName());
            lmls.add(mls);
        }
 //}
        session.setAttribute("mixlist", lmls);
        String isbn10 = bibform.getAccession_no();
        String title = bibform.getTitle();
        String doc_type = bibform.getDocument_type();
        String button = bibform.getButton();
        List a = dao.getTitles(title, library_id, sub_library_id, doc_type);//grid
        if (doc_type.equals("Book")) {
            isbn10 = StringUtils.trim(isbn10);
            if (button.equals("New")) {
                if (StringUtils.isEmpty(isbn10)) {
                    if (!a.isEmpty()) {
                        session.setAttribute("title", title);
                        session.setAttribute("isbn10", isbn10);
                        session.setAttribute("doc_type", doc_type);
                        session.setAttribute("opacList", a);
                        session.setAttribute("button", button);
                        return mapping.findForward("grid");
                    } else {
                        String msg1 = resource.getString("cataloguing.catoldtitle.titlenotfound1");//Title is not found.
                        request.setAttribute("msg1", msg1);
                        return mapping.findForward("fail");
                    }
                } else if (!StringUtils.isEmpty(isbn10)) {
                    bib = dao.searchacc(library_id, sub_library_id,isbn10);
                    if (bib != null) {
                        String msg1 = resource.getString("cataloguing.gridaccessionentryaction.accessduplicate");//Title is not found.
                        request.setAttribute("msg1", msg1);
                        return mapping.findForward("fail");
                    }
                    else{
//                    if (bib.getBibliographicDetails().getNoOfCopies() == null) {
//                            i = 0;
//                        } else {
//                            i = bib.getBibliographicDetails().getNoOfCopies();
//                        }
//                        bibform.setNo_of_copies(i);
//                        bibform.setMain_entry(bib.getBibliographicDetails().getMainEntry());
//                        bibform.setAdded_entry(bib.getBibliographicDetails().getAddedEntry());
//                        bibform.setAdded_entry0(bib.getBibliographicDetails().getAddedEntry1());
//                        bibform.setAdded_entry1(bib.getBibliographicDetails().getAddedEntry2());
//                        bibform.setAdded_entry2(bib.getBibliographicDetails().getAddedEntry3());
//                        bibform.setSer_note(bib.getBibliographicDetails().getSeries());
//                        bibform.setBiblio_id(bib.getBibliographicDetails().getId().getBiblioId());
//                        bibform.setLCC_no(bib.getBibliographicDetails().getLccNo());
//                        bibform.setDocument_type(bib.getBibliographicDetails().getDocumentType());
//                        bibform.setEdition(bib.getBibliographicDetails().getEdition());
//                        bibform.setIsbn10(bib.getBibliographicDetails().getIsbn10());
//                        bibform.setIsbn13(bib.getBibliographicDetails().getIsbn13());
//                        bibform.setLibrary_id(bib.getId().getLibraryId());
//                        bibform.setSub_library_id(bib.getId().getSublibraryId());
//                        bibform.setPublication_place(bib.getBibliographicDetails().getPublicationPlace());
//                        bibform.setPublisher_name(bib.getBibliographicDetails().getPublisherName());
//                        bibform.setPublishing_year(bib.getBibliographicDetails().getPublishingYear());
//                        bibform.setSubtitle(bib.getBibliographicDetails().getSubtitle());
//                        bibform.setTitle(bib.getBibliographicDetails().getTitle());
//                        bibform.setCall_no(bib.getBibliographicDetails().getCallNo());
//                        bibform.setAccession_type(bib.getBibliographicDetails().getAccessionType());
//                        bibform.setBook_type(bib.getBibliographicDetails().getBookType());
//                        bibform.setStatement_responsibility(bib.getBibliographicDetails().getStatementResponsibility());
//                        bibform.setAlt_title(bib.getBibliographicDetails().getAltTitle());
//                        bibform.setAccession_no(bib.getAccessionNo());
//                        bibform.setVolume_no(bib.getVolumeNo());
//                        bibform.setLocation(bib.getLocation());
//                        bibform.setShelving_location(bib.getShelvingLocation());
//                        bibform.setNo_of_pages(bib.getNoOfPages());
//                        bibform.setIndex_no(bib.getIndexNo());
//                        bibform.setPhysical_width(bib.getPhysicalWidth());
//                        bibform.setBind_type(bib.getBindType());
//                        session.setAttribute("button", button);
//                        return mapping.findForward("new");
                        if (!a.isEmpty()) {
                        session.setAttribute("title", title);
                        session.setAttribute("isbn10", isbn10);
                        session.setAttribute("doc_type", doc_type);
                        session.setAttribute("opacList", a);
                        session.setAttribute("button", button);
                        return mapping.findForward("grid");
                    } else {
                        String msg1 = resource.getString("cataloguing.catoldtitle.titlenotfound1");//Title is not found.
                        request.setAttribute("msg1", msg1);
                        return mapping.findForward("fail");
                    }
                    }
                } else {
                    String msg1 = resource.getString("cataloguing.catoldtitle.titlenotfound1");//Title is not found.
                    request.setAttribute("msg1", msg1);
                    return mapping.findForward("fail");
                }
            } else if (button.equals("Update")) {
                if (!StringUtils.isEmpty(isbn10)) {
                    bib = dao.searchacc(library_id, sub_library_id,isbn10);
                    if (bib != null) {
                        if (bib.getBibliographicDetails().getNoOfCopies() == null) {
                            i = 0;
                        } else {
                            i = bib.getBibliographicDetails().getNoOfCopies();
                        }
                        bibform.setNo_of_copies(i);
                        bibform.setMain_entry(bib.getBibliographicDetails().getMainEntry());
                        bibform.setAdded_entry(bib.getBibliographicDetails().getAddedEntry());
                        bibform.setAdded_entry0(bib.getBibliographicDetails().getAddedEntry1());
                        bibform.setAdded_entry1(bib.getBibliographicDetails().getAddedEntry2());
                        bibform.setAdded_entry2(bib.getBibliographicDetails().getAddedEntry3());
                        bibform.setBiblio_id(bib.getBibliographicDetails().getId().getBiblioId());
                        bibform.setSer_note(bib.getBibliographicDetails().getSeries());
                        bibform.setLCC_no(bib.getBibliographicDetails().getLccNo());
                        bibform.setDocument_type(bib.getBibliographicDetails().getDocumentType());
                        bibform.setEdition(bib.getBibliographicDetails().getEdition());
                        bibform.setIsbn10(bib.getBibliographicDetails().getIsbn10());
                        bibform.setIsbn13(bib.getBibliographicDetails().getIsbn13());
                        bibform.setLibrary_id(bib.getId().getLibraryId());
                        bibform.setSub_library_id(bib.getId().getSublibraryId());
                        bibform.setPublication_place(bib.getBibliographicDetails().getPublicationPlace());
                        bibform.setPublisher_name(bib.getBibliographicDetails().getPublisherName());
                        bibform.setPublishing_year(String.valueOf(bib.getBibliographicDetails().getPublishingYear()));
                        bibform.setSubtitle(bib.getBibliographicDetails().getSubtitle());
                        bibform.setTitle(bib.getBibliographicDetails().getTitle());
                        bibform.setCall_no(bib.getBibliographicDetails().getCallNo());
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
                        bibform.setLanguage(bib.getBibliographicDetails().getEntryLanguage());
                        session.setAttribute("button", button);
                        return mapping.findForward("new");
                    } else {
                        String msg3 = resource.getString("cataloguing.catoldtitle.titlenotfound");//Title corresponding to the isbn is not found.
                        request.setAttribute("msg1", msg3);
                        String msg2 = "";
                        request.setAttribute("msg2", msg2);
                        return mapping.findForward("fail");
                    }
                }
                if (!a.isEmpty()) {
                    session.setAttribute("title", title);
                    session.setAttribute("isbn10", isbn10);
                    session.setAttribute("doc_type", doc_type);
                    session.setAttribute("opacList", a);
                    session.setAttribute("button", button);
                    return mapping.findForward("grid");
                } else {
                    String msg3 = resource.getString("cataloguing.catoldtitle.titlenotfound1");//Title is not found.
                    request.setAttribute("msg1", msg3);
                    String msg2 = "";
                    request.setAttribute("msg2", msg2);
                    return mapping.findForward("fail");
                }
            } else if (button.equals("Delete")) {
                if (!StringUtils.isEmpty(isbn10)) {
                    bib = dao.searchacc(library_id, sub_library_id,isbn10);
                    if (bib != null) {
                         if (bib.getBibliographicDetails().getNoOfCopies() == null) {
                            i = 0;
                        } else {
                            i = bib.getBibliographicDetails().getNoOfCopies();
                        }
                        bibform.setNo_of_copies(i);
                        bibform.setMain_entry(bib.getBibliographicDetails().getMainEntry());
                        bibform.setAdded_entry(bib.getBibliographicDetails().getAddedEntry());
                        bibform.setAdded_entry0(bib.getBibliographicDetails().getAddedEntry1());
                        bibform.setAdded_entry1(bib.getBibliographicDetails().getAddedEntry2());
                        bibform.setAdded_entry2(bib.getBibliographicDetails().getAddedEntry3());
                        bibform.setBiblio_id(bib.getBibliographicDetails().getId().getBiblioId());
                        bibform.setLCC_no(bib.getBibliographicDetails().getLccNo());
                        bibform.setDocument_type(bib.getBibliographicDetails().getDocumentType());
                        bibform.setEdition(bib.getBibliographicDetails().getEdition());
                        bibform.setSer_note(bib.getBibliographicDetails().getSeries());
                        bibform.setIsbn10(bib.getBibliographicDetails().getIsbn10());
                        bibform.setIsbn13(bib.getBibliographicDetails().getIsbn13());
                        bibform.setLibrary_id(bib.getId().getLibraryId());
                        bibform.setSub_library_id(bib.getId().getSublibraryId());
                        bibform.setPublication_place(bib.getBibliographicDetails().getPublicationPlace());
                        bibform.setPublisher_name(bib.getBibliographicDetails().getPublisherName());
                        bibform.setPublishing_year(String.valueOf(bib.getBibliographicDetails().getPublishingYear()));
                        bibform.setSubtitle(bib.getBibliographicDetails().getSubtitle());
                        bibform.setTitle(bib.getBibliographicDetails().getTitle());
                        bibform.setCall_no(bib.getBibliographicDetails().getCallNo());
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
                        bibform.setLanguage(bib.getBibliographicDetails().getEntryLanguage());
                        session.setAttribute("button", button);
                        return mapping.findForward("new");
                    } else {
                        String msg3 = resource.getString("cataloguing.catoldtitle.titlenotfound");//Title corresponding to the isbn is not found.
                        request.setAttribute("msg1", msg3);
                        String msg2 = "";
                        request.setAttribute("msg2", msg2);
                        return mapping.findForward("fail");
                    }
                }
                if (!a.isEmpty()) {
                    session.setAttribute("title", title);
                    session.setAttribute("isbn10", isbn10);
                    session.setAttribute("doc_type", doc_type);
                    session.setAttribute("opacList", a);
                    session.setAttribute("button", button);
                    return mapping.findForward("grid");
                } else {
                    String msg3 = resource.getString("cataloguing.catoldtitle.titlenotfound1");//Title is not found.
                    request.setAttribute("msg1", msg3);
                    String msg2 = "";
                    request.setAttribute("msg2", msg2);
                    return mapping.findForward("fail");
                }
            } else if (button.equals("View")) {
                if (!StringUtils.isEmpty(isbn10)) {
                    bib = dao.searchacc(library_id, sub_library_id,isbn10);
                    if (bib != null) {
                         if (bib.getBibliographicDetails().getNoOfCopies() == null) {
                            i = 0;
                        } else {
                            i = bib.getBibliographicDetails().getNoOfCopies();
                        }
                        bibform.setNo_of_copies(i);
                        bibform.setMain_entry(bib.getBibliographicDetails().getMainEntry());
                        bibform.setAdded_entry(bib.getBibliographicDetails().getAddedEntry());
                        bibform.setAdded_entry0(bib.getBibliographicDetails().getAddedEntry1());
                        bibform.setAdded_entry1(bib.getBibliographicDetails().getAddedEntry2());
                        bibform.setAdded_entry2(bib.getBibliographicDetails().getAddedEntry3());
                        bibform.setBiblio_id(bib.getBibliographicDetails().getId().getBiblioId());
                        bibform.setSer_note(bib.getBibliographicDetails().getSeries());
                        bibform.setLCC_no(bib.getBibliographicDetails().getLccNo());
                        bibform.setDocument_type(bib.getBibliographicDetails().getDocumentType());
                        bibform.setEdition(bib.getBibliographicDetails().getEdition());
                        bibform.setIsbn10(bib.getBibliographicDetails().getIsbn10());
                        bibform.setIsbn13(bib.getBibliographicDetails().getIsbn13());
                        bibform.setLibrary_id(bib.getId().getLibraryId());
                        bibform.setSub_library_id(bib.getId().getSublibraryId());
                        bibform.setPublication_place(bib.getBibliographicDetails().getPublicationPlace());
                        bibform.setPublisher_name(bib.getBibliographicDetails().getPublisherName());
                        bibform.setPublishing_year(String.valueOf(bib.getBibliographicDetails().getPublishingYear()));
                        bibform.setSubtitle(bib.getBibliographicDetails().getSubtitle());
                        bibform.setTitle(bib.getBibliographicDetails().getTitle());
                        bibform.setCall_no(bib.getBibliographicDetails().getCallNo());
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
                         bibform.setLanguage(bib.getBibliographicDetails().getEntryLanguage());
                        session.setAttribute("button", button);
                        return mapping.findForward("new");
                    } else {
                        String msg3 = resource.getString("cataloguing.catoldtitle.titlenotfound");//Title corresponding to the isbn is not found.
                        request.setAttribute("msg1", msg3);
                        String msg2 = "";
                        request.setAttribute("msg2", msg2);
                        return mapping.findForward("fail");
                    }
                }
                if (!a.isEmpty()) {
                    session.setAttribute("title", title);
                    session.setAttribute("isbn10", isbn10);
                    session.setAttribute("doc_type", doc_type);
                    session.setAttribute("opacList", a);
                    session.setAttribute("button", button);
                    return mapping.findForward("grid");
                } else {
                    String msg3 = resource.getString("cataloguing.catoldtitle.titlenotfound1");//Title is not found.
                    request.setAttribute("msg1", msg3);
                    String msg2 = "";
                    request.setAttribute("msg2", msg2);
                    return mapping.findForward("fail");
                }
            }
            if (button.equals("Back")) {
                return mapping.findForward("main");
            }
        }

                  if (doc_type.equals("CD")) {
            isbn10 = StringUtils.trim(isbn10);
            if (button.equals("New")) {
                if (StringUtils.isEmpty(isbn10)) {
                    if (!a.isEmpty()) {
                        session.setAttribute("title", title);
                        session.setAttribute("isbn10", isbn10);
                        session.setAttribute("doc_type", doc_type);
                        session.setAttribute("opacList", a);
                        session.setAttribute("button", button);
                        return mapping.findForward("grid");
                    } else {
                        String msg1 = resource.getString("cataloguing.catoldtitle.titlenotfound1");//Title is not found.
                        request.setAttribute("msg1", msg1);
                        return mapping.findForward("fail");
                    }
                } else if (!StringUtils.isEmpty(isbn10)) {
                    bib = dao.searchacc(library_id, sub_library_id,isbn10);
                    if (bib != null) {
                        String msg1 = resource.getString("cataloguing.gridaccessionentryaction.accessduplicate");//Title is not found.
                        request.setAttribute("msg1", msg1);
                        return mapping.findForward("fail");
                    }
                    else{
//                    if (bib.getBibliographicDetails().getNoOfCopies() == null) {
//                            i = 0;
//                        } else {
//                            i = bib.getBibliographicDetails().getNoOfCopies();
//                        }
//                        bibform.setNo_of_copies(i);
//                        bibform.setMain_entry(bib.getBibliographicDetails().getMainEntry());
//                        bibform.setAdded_entry(bib.getBibliographicDetails().getAddedEntry());
//                        bibform.setAdded_entry0(bib.getBibliographicDetails().getAddedEntry1());
//                        bibform.setAdded_entry1(bib.getBibliographicDetails().getAddedEntry2());
//                        bibform.setAdded_entry2(bib.getBibliographicDetails().getAddedEntry3());
//                        bibform.setSer_note(bib.getBibliographicDetails().getSeries());
//                        bibform.setBiblio_id(bib.getBibliographicDetails().getId().getBiblioId());
//                        bibform.setLCC_no(bib.getBibliographicDetails().getLccNo());
//                        bibform.setDocument_type(bib.getBibliographicDetails().getDocumentType());
//                        bibform.setEdition(bib.getBibliographicDetails().getEdition());
//                        bibform.setIsbn10(bib.getBibliographicDetails().getIsbn10());
//                        bibform.setIsbn13(bib.getBibliographicDetails().getIsbn13());
//                        bibform.setLibrary_id(bib.getId().getLibraryId());
//                        bibform.setSub_library_id(bib.getId().getSublibraryId());
//                        bibform.setPublication_place(bib.getBibliographicDetails().getPublicationPlace());
//                        bibform.setPublisher_name(bib.getBibliographicDetails().getPublisherName());
//                        bibform.setPublishing_year(bib.getBibliographicDetails().getPublishingYear());
//                        bibform.setSubtitle(bib.getBibliographicDetails().getSubtitle());
//                        bibform.setTitle(bib.getBibliographicDetails().getTitle());
//                        bibform.setCall_no(bib.getBibliographicDetails().getCallNo());
//                        bibform.setAccession_type(bib.getBibliographicDetails().getAccessionType());
//                        bibform.setBook_type(bib.getBibliographicDetails().getBookType());
//                        bibform.setStatement_responsibility(bib.getBibliographicDetails().getStatementResponsibility());
//                        bibform.setAlt_title(bib.getBibliographicDetails().getAltTitle());
//                        bibform.setAccession_no(bib.getAccessionNo());
//                        bibform.setVolume_no(bib.getVolumeNo());
//                        bibform.setLocation(bib.getLocation());
//                        bibform.setShelving_location(bib.getShelvingLocation());
//                        bibform.setNo_of_pages(bib.getNoOfPages());
//                        bibform.setIndex_no(bib.getIndexNo());
//                        bibform.setPhysical_width(bib.getPhysicalWidth());
//                        bibform.setBind_type(bib.getBindType());
//                        session.setAttribute("button", button);
//                        return mapping.findForward("new");
                        if (!a.isEmpty()) {
                        session.setAttribute("title", title);
                        session.setAttribute("isbn10", isbn10);
                        session.setAttribute("doc_type", doc_type);
                        session.setAttribute("opacList", a);
                        session.setAttribute("button", button);
                        return mapping.findForward("grid");
                    } else {
                        String msg1 = resource.getString("cataloguing.catoldtitle.titlenotfound1");//Title is not found.
                        request.setAttribute("msg1", msg1);
                        return mapping.findForward("fail");
                    }
                    }
                } else {
                    String msg1 = resource.getString("cataloguing.catoldtitle.titlenotfound1");//Title is not found.
                    request.setAttribute("msg1", msg1);
                    return mapping.findForward("fail");
                }
            } else if (button.equals("Update")) {
                if (!StringUtils.isEmpty(isbn10)) {
                bib = dao.searchacc(library_id, sub_library_id,isbn10);
                    if (bib != null) {
                        if (bib.getBibliographicDetails().getNoOfCopies() == null) {
                            i = 0;
                        } else {
                            i = bib.getBibliographicDetails().getNoOfCopies();
                        }
                        bibform.setNo_of_copies(i);
                        bibform.setMain_entry(bib.getBibliographicDetails().getMainEntry());
                        bibform.setAdded_entry(bib.getBibliographicDetails().getAddedEntry());
                        bibform.setAdded_entry0(bib.getBibliographicDetails().getAddedEntry1());
                        bibform.setAdded_entry1(bib.getBibliographicDetails().getAddedEntry2());
                        bibform.setAdded_entry2(bib.getBibliographicDetails().getAddedEntry3());
                        bibform.setBiblio_id(bib.getBibliographicDetails().getId().getBiblioId());
                        bibform.setSer_note(bib.getBibliographicDetails().getSeries());
                        bibform.setLCC_no(bib.getBibliographicDetails().getLccNo());
                        bibform.setDocument_type(bib.getBibliographicDetails().getDocumentType());
                        bibform.setEdition(bib.getBibliographicDetails().getEdition());
                        bibform.setIsbn10(bib.getBibliographicDetails().getIsbn10());
                        bibform.setIsbn13(bib.getBibliographicDetails().getIsbn13());
                        bibform.setLibrary_id(bib.getId().getLibraryId());
                        bibform.setSub_library_id(bib.getId().getSublibraryId());
                        bibform.setPublication_place(bib.getBibliographicDetails().getPublicationPlace());
                        bibform.setPublisher_name(bib.getBibliographicDetails().getPublisherName());
                        bibform.setPublishing_year(String.valueOf(bib.getBibliographicDetails().getPublishingYear()));
                        bibform.setSubtitle(bib.getBibliographicDetails().getSubtitle());
                        bibform.setTitle(bib.getBibliographicDetails().getTitle());
                        bibform.setCall_no(bib.getBibliographicDetails().getCallNo());
                        bibform.setAccession_type(bib.getBibliographicDetails().getAccessionType());
                        bibform.setBook_type(bib.getBibliographicDetails().getBookType());
                        bibform.setStatement_responsibility(bib.getBibliographicDetails().getStatementResponsibility());
                        bibform.setAlt_title(bib.getBibliographicDetails().getAltTitle());
                        bibform.setType_of_disc(bib.getBibliographicDetails().getTypeOfDisc());
                        bibform.setPhysical_desc(bib.getPhysicalDescription());
                        bibform.setPhysical_form(bib.getPhysicalForm());
                         bibform.setLanguage(bib.getBibliographicDetails().getEntryLanguage());
                        return mapping.findForward("cdaccession");
                    } else {
                        String msg3 = "Title corresponding to the isbn is not found.";
                        request.setAttribute("msg1", msg3);
                        String msg2 = "";
                        request.setAttribute("msg2", msg2);
                        return mapping.findForward("fail");
                    }
                }
                if (!a.isEmpty()) {
                    session.setAttribute("title", title);
                    session.setAttribute("isbn10", isbn10);
                    session.setAttribute("doc_type", doc_type);
                    session.setAttribute("opacList", a);
                    session.setAttribute("button", button);
                    return mapping.findForward("grid");
                } else {
                    String msg3 = resource.getString("cataloguing.catoldtitle.titlenotfound1");//Title is not found.
                    request.setAttribute("msg1", msg3);
                    String msg2 = "";
                    request.setAttribute("msg2", msg2);
                    return mapping.findForward("fail");
                }
            } else if (button.equals("Delete")) {
                if (!StringUtils.isEmpty(isbn10)) {
                    bib = dao.searchacc(library_id, sub_library_id,isbn10);
                    if (bib != null) {
            if (bib.getBibliographicDetails().getNoOfCopies() == null) {
                            i = 0;
                        } else {
                            i = bib.getBibliographicDetails().getNoOfCopies();
                        }
                        bibform.setNo_of_copies(i);
                        bibform.setMain_entry(bib.getBibliographicDetails().getMainEntry());
                        bibform.setAdded_entry(bib.getBibliographicDetails().getAddedEntry());
                        bibform.setAdded_entry0(bib.getBibliographicDetails().getAddedEntry1());
                        bibform.setAdded_entry1(bib.getBibliographicDetails().getAddedEntry2());
                        bibform.setAdded_entry2(bib.getBibliographicDetails().getAddedEntry3());
                        bibform.setBiblio_id(bib.getBibliographicDetails().getId().getBiblioId());
                        bibform.setSer_note(bib.getBibliographicDetails().getSeries());
                        bibform.setLCC_no(bib.getBibliographicDetails().getLccNo());
                        bibform.setDocument_type(bib.getBibliographicDetails().getDocumentType());
                        bibform.setEdition(bib.getBibliographicDetails().getEdition());
                        bibform.setIsbn10(bib.getBibliographicDetails().getIsbn10());
                        bibform.setIsbn13(bib.getBibliographicDetails().getIsbn13());
                        bibform.setLibrary_id(bib.getId().getLibraryId());
                        bibform.setSub_library_id(bib.getId().getSublibraryId());
                        bibform.setPublication_place(bib.getBibliographicDetails().getPublicationPlace());
                        bibform.setPublisher_name(bib.getBibliographicDetails().getPublisherName());
                        bibform.setPublishing_year(String.valueOf(bib.getBibliographicDetails().getPublishingYear()));
                        bibform.setSubtitle(bib.getBibliographicDetails().getSubtitle());
                        bibform.setTitle(bib.getBibliographicDetails().getTitle());
                        bibform.setCall_no(bib.getBibliographicDetails().getCallNo());
                        bibform.setAccession_type(bib.getBibliographicDetails().getAccessionType());
                        bibform.setBook_type(bib.getBibliographicDetails().getBookType());
                        bibform.setStatement_responsibility(bib.getBibliographicDetails().getStatementResponsibility());
                        bibform.setAlt_title(bib.getBibliographicDetails().getAltTitle());
                        bibform.setType_of_disc(bib.getBibliographicDetails().getTypeOfDisc());
                        bibform.setPhysical_desc(bib.getPhysicalDescription());
                        bibform.setPhysical_form(bib.getPhysicalForm());
                         bibform.setLanguage(bib.getBibliographicDetails().getEntryLanguage());
                        return mapping.findForward("cdaccession");
                    } else {
                        String msg3 = "Title corresponding to the isbn is not found.";
                        request.setAttribute("msg1", msg3);
                        String msg2 = "";
                        request.setAttribute("msg2", msg2);
                        return mapping.findForward("fail");
                    }
                }
                if (!a.isEmpty()) {
                    session.setAttribute("title", title);
                    session.setAttribute("isbn10", isbn10);
                    session.setAttribute("doc_type", doc_type);
                    session.setAttribute("opacList", a);
                    session.setAttribute("button", button);
                    return mapping.findForward("grid");
                } else {
                    String msg3 = resource.getString("cataloguing.catoldtitle.titlenotfound1");//Title is not found.
                    request.setAttribute("msg1", msg3);
                    String msg2 = "";
                    request.setAttribute("msg2", msg2);
                    return mapping.findForward("fail");
                }
            } else if (button.equals("View")) {
                if (!StringUtils.isEmpty(isbn10)) {
                    if (!StringUtils.isEmpty(isbn10)) {
                    bib = dao.searchacc(library_id, sub_library_id,isbn10);
                    if (bib != null) {
            if (bib.getBibliographicDetails().getNoOfCopies() == null) {
                            i = 0;
                        } else {
                            i = bib.getBibliographicDetails().getNoOfCopies();
                        }
                        bibform.setNo_of_copies(i);
                        bibform.setMain_entry(bib.getBibliographicDetails().getMainEntry());
                        bibform.setAdded_entry(bib.getBibliographicDetails().getAddedEntry());
                        bibform.setAdded_entry0(bib.getBibliographicDetails().getAddedEntry1());
                        bibform.setAdded_entry1(bib.getBibliographicDetails().getAddedEntry2());
                        bibform.setAdded_entry2(bib.getBibliographicDetails().getAddedEntry3());
                        bibform.setBiblio_id(bib.getBibliographicDetails().getId().getBiblioId());
                        bibform.setSer_note(bib.getBibliographicDetails().getSeries());
                        bibform.setLCC_no(bib.getBibliographicDetails().getLccNo());
                        bibform.setDocument_type(bib.getBibliographicDetails().getDocumentType());
                        bibform.setEdition(bib.getBibliographicDetails().getEdition());
                        bibform.setIsbn10(bib.getBibliographicDetails().getIsbn10());
                        bibform.setIsbn13(bib.getBibliographicDetails().getIsbn13());
                        bibform.setLibrary_id(bib.getId().getLibraryId());
                        bibform.setSub_library_id(bib.getId().getSublibraryId());
                        bibform.setPublication_place(bib.getBibliographicDetails().getPublicationPlace());
                        bibform.setPublisher_name(bib.getBibliographicDetails().getPublisherName());
                        bibform.setPublishing_year(String.valueOf(bib.getBibliographicDetails().getPublishingYear()));
                        bibform.setSubtitle(bib.getBibliographicDetails().getSubtitle());
                        bibform.setTitle(bib.getBibliographicDetails().getTitle());
                        bibform.setCall_no(bib.getBibliographicDetails().getCallNo());
                        bibform.setAccession_type(bib.getBibliographicDetails().getAccessionType());
                        bibform.setBook_type(bib.getBibliographicDetails().getBookType());
                        bibform.setStatement_responsibility(bib.getBibliographicDetails().getStatementResponsibility());
                        bibform.setAlt_title(bib.getBibliographicDetails().getAltTitle());
                        bibform.setType_of_disc(bib.getBibliographicDetails().getTypeOfDisc());
                        bibform.setPhysical_desc(bib.getPhysicalDescription());
                        bibform.setPhysical_form(bib.getPhysicalForm());
                         bibform.setLanguage(bib.getBibliographicDetails().getEntryLanguage());
                        return mapping.findForward("cdaccession");
                    } else {
                        String msg3 = "Title corresponding to the isbn is not found.";
                        request.setAttribute("msg1", msg3);
                        String msg2 = "";
                        request.setAttribute("msg2", msg2);
                        return mapping.findForward("fail");
                    }
                }
                if (!a.isEmpty()) {
                    session.setAttribute("title", title);
                    session.setAttribute("isbn10", isbn10);
                    session.setAttribute("doc_type", doc_type);
                    session.setAttribute("opacList", a);
                    session.setAttribute("button", button);
                    return mapping.findForward("grid");
                } else {
                    String msg3 = resource.getString("cataloguing.catoldtitle.titlenotfound1");//Title is not found.
                    request.setAttribute("msg1", msg3);
                    String msg2 = "";
                    request.setAttribute("msg2", msg2);
                    return mapping.findForward("fail");
                }
            }
            if (button.equals("Back")) {
                return mapping.findForward("main");
            }
        }
    }
        return mapping.findForward("fail");
    }

}
