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
import com.myapp.struts.cataloguingDAO.BibliographicEntryDAO;
import com.myapp.struts.hbm.BibliographicDetailsLang;
import com.myapp.struts.hbm.BibliographicDetailsLangId;
import com.myapp.struts.hbm.DocumentCategory;
import com.myapp.struts.hbm.DocumentCategoryId;
import com.myapp.struts.hbm.Location;
import com.myapp.struts.hbm.MixLocationSublibrary;
import com.myapp.struts.hbm.SubLibrary;
import com.myapp.struts.systemsetupDAO.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import org.apache.commons.lang.StringUtils;

public class BibliographicDetailEntryAction1 extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
        BibliographicDetailsId bibid = new BibliographicDetailsId();
        BibliographicDetails bib = new BibliographicDetails();
        BibliographicDetailsLang biblang = new BibliographicDetailsLang();
        BibliographicDetailsLangId biblangid = new BibliographicDetailsLangId();
        AcqFinalDemandList acqdemand = new AcqFinalDemandList();
        BibliographicEntryDAO dao = new BibliographicEntryDAO();
        Locale locale=null;
        String locale1="en";
        String rtl="ltr";
        String align="left";
        int bb_id;
        int i;
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DocumentCategoryDAO doccatdao=new DocumentCategoryDAO();
        LocationDAO locdao=new LocationDAO();
        BibliographicDetailEntryActionForm bibform = (BibliographicDetailEntryActionForm) form;
        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        String isbn10 = bibform.getIsbn10();
        String title = bibform.getTitle();
        String doc_type = bibform.getDocument_type();
        String button = bibform.getButton();
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
        List a = dao.getTitles(title, library_id, sub_library_id, doc_type);//own
        List b = dao.getTitles(title, library_id, library_id, doc_type);//main
        List d = dao.getTitles2(title, library_id, doc_type);//all
        List c = dao.getTitles1(title, library_id, sub_library_id);//acquisition
        List empty=new ArrayList();
        List<DocumentCategory> ll=doccatdao.ListbookType(library_id,sub_library_id);
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
        List<Location> loc=locdao.listlocation(library_id, sub_library_id);
        if(loc.isEmpty()){
                        String msg1 = resource.getString("cataloguing.catoldtitle.setlocation");//You need to set location
                        request.setAttribute("msg1", msg1);
                        return mapping.findForward("fail");
        }
        List<MixLocationSublibrary> lmls=new ArrayList<MixLocationSublibrary>();
//        if(library_id.equals(sub_library_id))
//       {
//           List<SubLibrary> sub1=DeptDAO.listsub1(library_id);
//                                      if(sub1.isEmpty()){
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
//                        if(sub.isEmpty()){
//                        String msg1 = resource.getString("cataloguing.catoldtitle.setsublibrary");//You need to set sublibrary
//                        request.setAttribute("msg1", msg1);
//                        return mapping.findForward("fail");
//        }
//              for (int k = 0; k < sub.size(); k++)
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
            for (int j = 0; j < loc.size(); j++) {
            MixLocationSublibrary mls=new MixLocationSublibrary();
            mls.setLocationId(loc.get(j).getId().getLocationId());
            mls.setLocationName(loc.get(j).getLocationName());
            lmls.add(mls);
        }
// }
        session.setAttribute("mixlist", lmls);

         System.out.println("In Disseration..............."+doc_type);
        if (doc_type.equals("Book")) {
            isbn10 = StringUtils.trim(isbn10);
            if (button.equals("New")) {
                if (StringUtils.isEmpty(isbn10)) {
                    if (!a.isEmpty()) {
                        session.setAttribute("title", title);
                        session.setAttribute("isbn10", isbn10);
                        session.setAttribute("doc_type", doc_type);
                        session.setAttribute("opacLista", a);
                        session.setAttribute("opacListb", empty);
                        session.setAttribute("opacListc", empty);
                        session.setAttribute("opacListd", empty);
                        session.setAttribute("button", button);
                        return mapping.findForward("grid");
                    }
                    if (!d.isEmpty()) {
                        session.setAttribute("title", title);
                        session.setAttribute("isbn10", isbn10);
                        session.setAttribute("doc_type", doc_type);
                        session.setAttribute("opacLista", empty);
                        session.setAttribute("opacListb", empty);
                        session.setAttribute("opacListc", empty);
                        session.setAttribute("opacListd", d);
                        session.setAttribute("button", button);
                        return mapping.findForward("grid");
                    }
                     else if (!b.isEmpty()) {
                        session.setAttribute("title", title);
                        session.setAttribute("isbn10", isbn10);
                        session.setAttribute("doc_type", doc_type);
                        session.setAttribute("opacLista", empty);
                        session.setAttribute("opacListb", b);
                        session.setAttribute("opacListc", empty);
                        session.setAttribute("opacListd", empty);
                        session.setAttribute("button", button);
                        return mapping.findForward("grid");
                    }
                    else if (!c.isEmpty()) {
                        session.setAttribute("opacLista", empty);
                        session.setAttribute("opacListb", empty);
                        session.setAttribute("opacListc", c);
                        session.setAttribute("opacListd", empty);
                        session.setAttribute("button", button);
                        return mapping.findForward("grid");
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
                        session.setAttribute("button", button);
                        return mapping.findForward("new");
                    }
                } else if (!StringUtils.isEmpty(isbn10)) {
                    bib = dao.search1Isbn10(isbn10, library_id, sub_library_id);
                    if (bib != null) {
                        String msg1 = resource.getString("cataloguing.catoldtitle.isbnduplicate");//"This isbn already exists enter different";
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
                        session.setAttribute("button", button);
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
                    session.setAttribute("button", button);
                    
                    return mapping.findForward("new");
                }
             } else if (button.equals("Update")) {
                if (!StringUtils.isEmpty(isbn10)) {
                    bib = dao.search2Isbn10(title, isbn10, library_id, sub_library_id);                  
                   if(bib != null)
                   {
                       //bibform.setNo_of_copies(bib.getNoOfCopies());
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
                        bibform.setPublishing_year(String.valueOf(bib.getPublishingYear()));
                        bibform.setSubtitle(bib.getSubtitle());
                        bibform.setTitle(bib.getTitle());
                        bibform.setCall_no(bib.getCallNo());
                        bibform.setAccession_type(bib.getAccessionType());
                        bibform.setBook_type(bib.getBookType());
                        bibform.setStatement_responsibility(bib.getStatementResponsibility());
                        bibform.setAlt_title(bib.getAltTitle());
                        bibform.setThesis_abstract(bib.getAbstract_());
                        bibform.setNotes(bib.getNotes());
                        bibform.setSubject(bib.getSubject());
                        bibform.setLanguage(bib.getEntryLanguage());
                                           if (bib != null) {
                        if (bib.getNoOfCopies() == null) {
                            i = 0;
                        } else {
                            i = bib.getNoOfCopies();
                        }
                    }
                        bb_id=bib.getId().getBiblioId();
                        biblang=dao.searchlangbyBiblioid(bb_id, library_id, sub_library_id);
                   }                    
                    if (bib != null && biblang!=null) {                        
                        bibform.setMain_entry1(biblang.getMainEntry());
                        bibform.setAdded_entryl(biblang.getAddedEntry());
                        bibform.setAdded_entry01(biblang.getAddedEntry1());
                        bibform.setAdded_entry11(biblang.getAddedEntry2());
                        bibform.setAdded_entry21(biblang.getAddedEntry3());
                        bibform.setSer_note1(biblang.getSeries());
                        bibform.setLCC_no1(biblang.getLccNo());
                        bibform.setEdition1(biblang.getEdition());
                        bibform.setIsbn101(biblang.getIsbn10());
                        bibform.setIsbn131(biblang.getIsbn13());      
                        bibform.setPublication_place1(biblang.getPublicationPlace());
                        bibform.setPublisher_name1(biblang.getPublisherName());
                        bibform.setPublishing_year1(biblang.getPublishingYear());
                        bibform.setSubtitle1(biblang.getSubtitle());
                        bibform.setTitle1(biblang.getTitle());
                        bibform.setCall_no1(biblang.getCallNo());
                        bibform.setSubject1(biblang.getSubject());
                        bibform.setThesis_abstract1(biblang.getAbstract_());
                        bibform.setNotes1(biblang.getNotes());
                        bibform.setStatement_responsibility1(biblang.getStatementResponsibility());
                        bibform.setAlt_title1(biblang.getAltTitle());
                        bibform.setLanguage(biblang.getEntryLanguage());
                                           if (bib != null) {
                        if (bib.getNoOfCopies() == null) {
                            i = 0;
                        } else {
                            i = bib.getNoOfCopies();
                        }
                    }
                        session.setAttribute("button", button);
                        return mapping.findForward("new");
                    }
                    else if(bib != null){
                       // bibform.setNo_of_copies(bib.getNoOfCopies());
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
                        bibform.setPublishing_year(String.valueOf(bib.getPublishingYear()));
                        bibform.setSubtitle(bib.getSubtitle());
                        bibform.setTitle(bib.getTitle());
                        bibform.setCall_no(bib.getCallNo());
                        bibform.setAccession_type(bib.getAccessionType());
                        bibform.setBook_type(bib.getBookType());
                        bibform.setStatement_responsibility(bib.getStatementResponsibility());
                        bibform.setAlt_title(bib.getAltTitle());
                        bibform.setLanguage(bib.getEntryLanguage());
                                          if (bib != null) {
                        if (bib.getNoOfCopies() == null) {
                            i = 0;
                        } else {
                            i = bib.getNoOfCopies();
                        }
                    }
                        session.setAttribute("button", button);
                        return mapping.findForward("new");
                    }
                    else {
                         String msg3 = resource.getString("cataloguing.catoldtitle.titlenotfound");//Title corresponding to the isbn is not found.
                         request.setAttribute("msg1", msg3);
                        String msg2 = "";
                        request.setAttribute("msg2", msg2);
                        return mapping.findForward("fail");
                    }
                }
                if (!a.isEmpty()) {
                    session.setAttribute("opacListb", empty);
                    session.setAttribute("opacListc", empty);
                    session.setAttribute("opacListd", empty);
                    session.setAttribute("opacLista", a);
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
                    bib = dao.search2Isbn10(title, isbn10, library_id, sub_library_id);
                   if(bib != null)
                   {
                       bb_id=bib.getId().getBiblioId();
                       biblang=dao.searchlangbyBiblioid(bb_id, library_id, sub_library_id);
                   }
                    if (bib != null && biblang!=null) {
                       // bibform.setNo_of_copies(bib.getNoOfCopies());
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
                        bibform.setPublishing_year(String.valueOf(bib.getPublishingYear()));
                        bibform.setSubtitle(bib.getSubtitle());
                        bibform.setTitle(bib.getTitle());
                        bibform.setCall_no(bib.getCallNo());
                        bibform.setAccession_type(bib.getAccessionType());
                        bibform.setBook_type(bib.getBookType());
                        bibform.setStatement_responsibility(bib.getStatementResponsibility());
                        bibform.setAlt_title(bib.getAltTitle());
                        bibform.setThesis_abstract(bib.getAbstract_());
                        bibform.setNotes(bib.getNotes());
                        bibform.setSubject(bib.getSubject());
                        bibform.setMain_entry1(biblang.getMainEntry());
                        bibform.setAdded_entryl(biblang.getAddedEntry());
                        bibform.setAdded_entry01(biblang.getAddedEntry1());
                        bibform.setAdded_entry11(biblang.getAddedEntry2());
                        bibform.setAdded_entry21(biblang.getAddedEntry3());
                        bibform.setSer_note1(biblang.getSeries());
                        bibform.setLCC_no1(biblang.getLccNo());
                        bibform.setEdition1(biblang.getEdition());
                        bibform.setIsbn101(biblang.getIsbn10());
                        bibform.setIsbn131(biblang.getIsbn13());
                        bibform.setPublication_place1(biblang.getPublicationPlace());
                        bibform.setPublisher_name1(biblang.getPublisherName());
                        bibform.setPublishing_year1(biblang.getPublishingYear());
                        bibform.setSubtitle1(biblang.getSubtitle());
                        bibform.setTitle1(biblang.getTitle());
                        bibform.setCall_no1(biblang.getCallNo());
                        bibform.setSubject1(biblang.getSubject());
                        bibform.setThesis_abstract1(biblang.getAbstract_());
                        bibform.setNotes1(biblang.getNotes());
                        bibform.setStatement_responsibility1(biblang.getStatementResponsibility());
                        bibform.setAlt_title1(biblang.getAltTitle());
                        bibform.setLanguage(biblang.getEntryLanguage());
                                           if (bib != null) {
                        if (bib.getNoOfCopies() == null) {
                            i = 0;
                        } else {
                            i = bib.getNoOfCopies();
                        }
                    }
                        session.setAttribute("button", button);
                        return mapping.findForward("new");
                    }
                     else if(bib != null){
                      //  bibform.setNo_of_copies(bib.getNoOfCopies());
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
                        bibform.setPublishing_year(String.valueOf(bib.getPublishingYear()));
                        bibform.setSubtitle(bib.getSubtitle());
                        bibform.setTitle(bib.getTitle());
                        bibform.setCall_no(bib.getCallNo());
                        bibform.setAccession_type(bib.getAccessionType());
                        bibform.setBook_type(bib.getBookType());
                        bibform.setStatement_responsibility(bib.getStatementResponsibility());
                        bibform.setAlt_title(bib.getAltTitle());
                        bibform.setLanguage(bib.getEntryLanguage());
                                          if (bib != null) {
                        if (bib.getNoOfCopies() == null) {
                            i = 0;
                        } else {
                            i = bib.getNoOfCopies();
                        }
                    }
                        session.setAttribute("button", button);
                        return mapping.findForward("new");
                     }
                    else {
                     String msg3 = resource.getString("cataloguing.catoldtitle.titlenotfound");//Title corresponding to the isbn is not found.
                        request.setAttribute("msg1", msg3);
                        String msg2 = "";
                        request.setAttribute("msg2", msg2);
                        return mapping.findForward("fail");
                    }
                }
                if (!a.isEmpty()) {
                    session.setAttribute("opacLista", a);
                    session.setAttribute("opacListb", empty);
                    session.setAttribute("opacListc", empty);
                    session.setAttribute("opacListd", empty);
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
                    bib = dao.search2Isbn10(title, isbn10, library_id, sub_library_id);
                    if (bib != null) {
                       // bibform.setNo_of_copies(bib.getNoOfCopies());
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
                        bibform.setPublishing_year(String.valueOf(bib.getPublishingYear()));
                        bibform.setSubtitle(bib.getSubtitle());
                        bibform.setTitle(bib.getTitle());
                        bibform.setCall_no(bib.getCallNo());
                        bibform.setSer_note(bib.getSeries());
                        bibform.setAccession_type(bib.getAccessionType());
                        bibform.setBook_type(bib.getBookType());
                        bibform.setStatement_responsibility(bib.getStatementResponsibility());
                        bibform.setAlt_title(bib.getAltTitle());
                        bibform.setLanguage(bib.getEntryLanguage());
                                           if (bib != null) {
                        if (bib.getNoOfCopies() == null) {
                            i = 0;
                        } else {
                            i = bib.getNoOfCopies();
                        }
                    }
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
                        session.setAttribute("opacLista", a);
                        session.setAttribute("opacListb", empty);
                        session.setAttribute("opacListc", empty);
                        session.setAttribute("opacListd", empty);
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

        //Disseration Entry
        if (doc_type.equalsIgnoreCase("Diss")) {
         
            if (button.equals("New")) {
               // if (StringUtils.isEmpty(isbn10)) {
                    if (!a.isEmpty()) {
                        session.setAttribute("title", title);
                        session.setAttribute("isbn10", isbn10);
                        session.setAttribute("doc_type", doc_type);
                        session.setAttribute("opacLista", a);
                        session.setAttribute("opacListb", empty);
                        session.setAttribute("opacListc", empty);
                        session.setAttribute("opacListd", empty);
                        session.setAttribute("button", button);
                        return mapping.findForward("griddiss");
                    }
//                    if (!d.isEmpty()) {
//                        session.setAttribute("title", title);
//                        session.setAttribute("isbn10", isbn10);
//                        session.setAttribute("doc_type", doc_type);
//                        session.setAttribute("opacLista", empty);
//                        session.setAttribute("opacListb", empty);
//                        session.setAttribute("opacListc", empty);
//                        session.setAttribute("opacListd", d);
//                        session.setAttribute("button", button);
//                        return mapping.findForward("grid");
//                    }
//                     else if (!b.isEmpty()) {
//                        session.setAttribute("title", title);
//                        session.setAttribute("isbn10", isbn10);
//                        session.setAttribute("doc_type", doc_type);
//                        session.setAttribute("opacLista", empty);
//                        session.setAttribute("opacListb", b);
//                        session.setAttribute("opacListc", empty);
//                        session.setAttribute("opacListd", empty);
//                        session.setAttribute("button", button);
//                        return mapping.findForward("grid");
//                    }
//                    else if (!c.isEmpty()) {
//                        session.setAttribute("opacLista", empty);
//                        session.setAttribute("opacListb", empty);
//                        session.setAttribute("opacListc", c);
//                        session.setAttribute("opacListd", empty);
//                        session.setAttribute("button", button);
//                        return mapping.findForward("grid");
//                    }
                    else {
                        bibform.setDocument_type(doc_type);
                        bibform.setTitle(title);
                       // bibform.setSubtitle("");
                      //  bibform.setStatement_responsibility("");
                       // bibform.setMain_entry("");
                       // bibform.setAdded_entry("");
                       // bibform.setPublisher_name("");
                       // bibform.setPublication_place("");
                      //  bibform.setPublishing_year("");
                      //  bibform.setLCC_no("");
                      //  bibform.setIsbn10(StringUtils.deleteWhitespace(isbn10));
                      //  bibform.setIsbn13("");
                      //  bibform.setEdition("");
                        bibform.setCall_no("");
                        bibform.setSubject("");
                        bibform.setThesis_abstract("");
                        bibform.setNotes("");
                        bibform.setNo_of_copies(0);
                        bibform.setSubmittedBy("");
                       // bibform.setLast("");
                        bibform.setGuide_name("");
                        bibform.setDegree("");
                        bibform.setAcceptance_year("");
                        bibform.setEmail("");

                        session.setAttribute("button", button);
                        return mapping.findForward("newdis");
                    }
//                } else if (!StringUtils.isEmpty(isbn10)) {
//                    bib = dao.search1Isbn10(isbn10, library_id, sub_library_id);
//                    if (bib != null) {
//                        String msg1 = resource.getString("cataloguing.catoldtitle.isbnduplicate");//"This isbn already exists enter different";
//                        request.setAttribute("msg1", msg1);
//                        return mapping.findForward("fail");
//                    } else {
//                        bibform.setDocument_type(doc_type);
//                        bibform.setTitle(title);
//                        bibform.setSubtitle("");
//                        bibform.setStatement_responsibility("");
//                        bibform.setMain_entry("");
//                        bibform.setAdded_entry("");
//                        bibform.setPublisher_name("");
//                        bibform.setPublication_place("");
//                        bibform.setPublishing_year("");
//                        bibform.setLCC_no("");
//                        bibform.setIsbn10(StringUtils.deleteWhitespace(isbn10));
//                        bibform.setIsbn13("");
//                        bibform.setEdition("");
//                        bibform.setCall_no("");
//                        bibform.setAlt_title("");
//                        bibform.setAlt_title("");
//                        bibform.setSubject("");
//                        bibform.setThesis_abstract("");
//                        bibform.setNotes("");
//                        bibform.setBook_type("");
//                        bibform.setNo_of_copies(0);
//                        session.setAttribute("button", button);
//                        return mapping.findForward("new");
//                    }
//                } else {
//                    bibform.setDocument_type(doc_type);
//                    bibform.setTitle(title);
//                    bibform.setSubtitle("");
//                    bibform.setStatement_responsibility("");
//                    bibform.setMain_entry("");
//                    bibform.setAdded_entry("");
//                    bibform.setPublisher_name("");
//                    bibform.setPublication_place("");
//                    bibform.setPublishing_year("");
//                    bibform.setLCC_no("");
//                    bibform.setIsbn10(StringUtils.deleteWhitespace(isbn10));
//                    bibform.setIsbn13("");
//                    bibform.setEdition("");
//                    bibform.setCall_no("");
//                    bibform.setAlt_title("");
//                    bibform.setAlt_title("");
//                    bibform.setSubject("");
//                    bibform.setThesis_abstract("");
//                    bibform.setNotes("");
//                    bibform.setBook_type("");
//                    bibform.setNo_of_copies(0);
//                    session.setAttribute("button", button);
//
//                    return mapping.findForward("new");
//                }
             } else if (button.equals("Update")) {
                if (!StringUtils.isEmpty(isbn10)) {
                    bib = dao.search2Isbn10(title, isbn10, library_id, sub_library_id);
                   if(bib != null)
                   {
                       //bibform.setNo_of_copies(bib.getNoOfCopies());
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
                        bibform.setPublishing_year(String.valueOf(bib.getPublishingYear()));
                        bibform.setSubtitle(bib.getSubtitle());
                        bibform.setTitle(bib.getTitle());
                        bibform.setCall_no(bib.getCallNo());
                        bibform.setAccession_type(bib.getAccessionType());
                        bibform.setBook_type(bib.getBookType());
                        bibform.setStatement_responsibility(bib.getStatementResponsibility());
                        bibform.setAlt_title(bib.getAltTitle());
                        bibform.setThesis_abstract(bib.getAbstract_());
                        bibform.setNotes(bib.getNotes());
                        bibform.setSubject(bib.getSubject());
                        bibform.setLanguage(bib.getEntryLanguage());
                                           if (bib != null) {
                        if (bib.getNoOfCopies() == null) {
                            i = 0;
                        } else {
                            i = bib.getNoOfCopies();
                        }
                    }
                        bb_id=bib.getId().getBiblioId();
                        biblang=dao.searchlangbyBiblioid(bb_id, library_id, sub_library_id);
                   }
                    if (bib != null && biblang!=null) {
                        bibform.setMain_entry1(biblang.getMainEntry());
                        bibform.setAdded_entryl(biblang.getAddedEntry());
                        bibform.setAdded_entry01(biblang.getAddedEntry1());
                        bibform.setAdded_entry11(biblang.getAddedEntry2());
                        bibform.setAdded_entry21(biblang.getAddedEntry3());
                        bibform.setSer_note1(biblang.getSeries());
                        bibform.setLCC_no1(biblang.getLccNo());
                        bibform.setEdition1(biblang.getEdition());
                        bibform.setIsbn101(biblang.getIsbn10());
                        bibform.setIsbn131(biblang.getIsbn13());
                        bibform.setPublication_place1(biblang.getPublicationPlace());
                        bibform.setPublisher_name1(biblang.getPublisherName());
                        bibform.setPublishing_year1(biblang.getPublishingYear());
                        bibform.setSubtitle1(biblang.getSubtitle());
                        bibform.setTitle1(biblang.getTitle());
                        bibform.setCall_no1(biblang.getCallNo());
                        bibform.setSubject1(biblang.getSubject());
                        bibform.setThesis_abstract1(biblang.getAbstract_());
                        bibform.setNotes1(biblang.getNotes());
                        bibform.setStatement_responsibility1(biblang.getStatementResponsibility());
                        bibform.setAlt_title1(biblang.getAltTitle());
                        bibform.setLanguage(biblang.getEntryLanguage());
                                           if (bib != null) {
                        if (bib.getNoOfCopies() == null) {
                            i = 0;
                        } else {
                            i = bib.getNoOfCopies();
                        }
                    }
                        session.setAttribute("button", button);
                        return mapping.findForward("new");
                    }
                    else if(bib != null){
                       // bibform.setNo_of_copies(bib.getNoOfCopies());
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
                        bibform.setPublishing_year(String.valueOf(bib.getPublishingYear()));
                        bibform.setSubtitle(bib.getSubtitle());
                        bibform.setTitle(bib.getTitle());
                        bibform.setCall_no(bib.getCallNo());
                        bibform.setAccession_type(bib.getAccessionType());
                        bibform.setBook_type(bib.getBookType());
                        bibform.setStatement_responsibility(bib.getStatementResponsibility());
                        bibform.setAlt_title(bib.getAltTitle());
                        bibform.setLanguage(bib.getEntryLanguage());
                                          if (bib != null) {
                        if (bib.getNoOfCopies() == null) {
                            i = 0;
                        } else {
                            i = bib.getNoOfCopies();
                        }
                    }
                        session.setAttribute("button", button);
                        return mapping.findForward("new");
                    }
                    else {
                         String msg3 = resource.getString("cataloguing.catoldtitle.titlenotfound");//Title corresponding to the isbn is not found.
                         request.setAttribute("msg1", msg3);
                        String msg2 = "";
                        request.setAttribute("msg2", msg2);
                        return mapping.findForward("fail");
                    }
                }
                if (!a.isEmpty()) {
                    session.setAttribute("opacListb", empty);
                    session.setAttribute("opacListc", empty);
                    session.setAttribute("opacListd", empty);
                    session.setAttribute("opacLista", a);
                    session.setAttribute("button", button);
                    System.out.println(button+"  "+doc_type);
                    if(doc_type.equalsIgnoreCase("Diss"))
                        return mapping.findForward("griddiss");
                    else if(doc_type.equalsIgnoreCase("Book"))
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
                    bib = dao.search2Isbn10(title, isbn10, library_id, sub_library_id);
                   if(bib != null)
                   {
                       bb_id=bib.getId().getBiblioId();
                       biblang=dao.searchlangbyBiblioid(bb_id, library_id, sub_library_id);
                   }
                    if (bib != null && biblang!=null) {
                       // bibform.setNo_of_copies(bib.getNoOfCopies());
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
                        bibform.setPublishing_year(String.valueOf(bib.getPublishingYear()));
                        bibform.setSubtitle(bib.getSubtitle());
                        bibform.setTitle(bib.getTitle());
                        bibform.setCall_no(bib.getCallNo());
                        bibform.setAccession_type(bib.getAccessionType());
                        bibform.setBook_type(bib.getBookType());
                        bibform.setStatement_responsibility(bib.getStatementResponsibility());
                        bibform.setAlt_title(bib.getAltTitle());
                        bibform.setThesis_abstract(bib.getAbstract_());
                        bibform.setNotes(bib.getNotes());
                        bibform.setSubject(bib.getSubject());
                        bibform.setMain_entry1(biblang.getMainEntry());
                        bibform.setAdded_entryl(biblang.getAddedEntry());
                        bibform.setAdded_entry01(biblang.getAddedEntry1());
                        bibform.setAdded_entry11(biblang.getAddedEntry2());
                        bibform.setAdded_entry21(biblang.getAddedEntry3());
                        bibform.setSer_note1(biblang.getSeries());
                        bibform.setLCC_no1(biblang.getLccNo());
                        bibform.setEdition1(biblang.getEdition());
                        bibform.setIsbn101(biblang.getIsbn10());
                        bibform.setIsbn131(biblang.getIsbn13());
                        bibform.setPublication_place1(biblang.getPublicationPlace());
                        bibform.setPublisher_name1(biblang.getPublisherName());
                        bibform.setPublishing_year1(biblang.getPublishingYear());
                        bibform.setSubtitle1(biblang.getSubtitle());
                        bibform.setTitle1(biblang.getTitle());
                        bibform.setCall_no1(biblang.getCallNo());
                        bibform.setSubject1(biblang.getSubject());
                        bibform.setThesis_abstract1(biblang.getAbstract_());
                        bibform.setNotes1(biblang.getNotes());
                        bibform.setStatement_responsibility1(biblang.getStatementResponsibility());
                        bibform.setAlt_title1(biblang.getAltTitle());
                        bibform.setLanguage(biblang.getEntryLanguage());
                                           if (bib != null) {
                        if (bib.getNoOfCopies() == null) {
                            i = 0;
                        } else {
                            i = bib.getNoOfCopies();
                        }
                    }
                        session.setAttribute("button", button);
                        return mapping.findForward("new");
                    }
                     else if(bib != null){
                      //  bibform.setNo_of_copies(bib.getNoOfCopies());
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
                        bibform.setPublishing_year(String.valueOf(bib.getPublishingYear()));
                        bibform.setSubtitle(bib.getSubtitle());
                        bibform.setTitle(bib.getTitle());
                        bibform.setCall_no(bib.getCallNo());
                        bibform.setAccession_type(bib.getAccessionType());
                        bibform.setBook_type(bib.getBookType());
                        bibform.setStatement_responsibility(bib.getStatementResponsibility());
                        bibform.setAlt_title(bib.getAltTitle());
                        bibform.setLanguage(bib.getEntryLanguage());
                                          if (bib != null) {
                        if (bib.getNoOfCopies() == null) {
                            i = 0;
                        } else {
                            i = bib.getNoOfCopies();
                        }
                    }
                        session.setAttribute("button", button);
                        return mapping.findForward("new");
                     }
                    else {
                     String msg3 = resource.getString("cataloguing.catoldtitle.titlenotfound");//Title corresponding to the isbn is not found.
                        request.setAttribute("msg1", msg3);
                        String msg2 = "";
                        request.setAttribute("msg2", msg2);
                        return mapping.findForward("fail");
                    }
                }
                if (!a.isEmpty()) {
                    session.setAttribute("opacLista", a);
                    session.setAttribute("opacListb", empty);
                    session.setAttribute("opacListc", empty);
                    session.setAttribute("opacListd", empty);
                    session.setAttribute("button", button);
                   if(doc_type.equalsIgnoreCase("Diss"))
                        return mapping.findForward("griddiss");
                    else if(doc_type.equalsIgnoreCase("Book"))
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
                    bib = dao.search2Isbn10(title, isbn10, library_id, sub_library_id);
                    if (bib != null) {
                       // bibform.setNo_of_copies(bib.getNoOfCopies());
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
                        bibform.setPublishing_year(String.valueOf(bib.getPublishingYear()));
                        bibform.setSubtitle(bib.getSubtitle());
                        bibform.setTitle(bib.getTitle());
                        bibform.setCall_no(bib.getCallNo());
                        bibform.setSer_note(bib.getSeries());
                        bibform.setAccession_type(bib.getAccessionType());
                        bibform.setBook_type(bib.getBookType());
                        bibform.setStatement_responsibility(bib.getStatementResponsibility());
                        bibform.setAlt_title(bib.getAltTitle());
                        bibform.setLanguage(bib.getEntryLanguage());
                                           if (bib != null) {
                        if (bib.getNoOfCopies() == null) {
                            i = 0;
                        } else {
                            i = bib.getNoOfCopies();
                        }
                    }
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
                        session.setAttribute("opacLista", a);
                        session.setAttribute("opacListb", empty);
                        session.setAttribute("opacListc", empty);
                        session.setAttribute("opacListd", empty);
                    session.setAttribute("button", button);
                   if(doc_type.equalsIgnoreCase("Diss"))
                        return mapping.findForward("griddiss");
                    else if(doc_type.equalsIgnoreCase("Book"))
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
         //CD Entry
         //Disseration Entry
        if (doc_type.equalsIgnoreCase("cd")) {

            if (button.equals("New")) {
               // if (StringUtils.isEmpty(isbn10)) {
                    if (!a.isEmpty()) {
                        session.setAttribute("title", title);
                        session.setAttribute("isbn10", isbn10);
                        session.setAttribute("doc_type", doc_type);
                        session.setAttribute("opacLista", a);
                        session.setAttribute("opacListb", empty);
                        session.setAttribute("opacListc", empty);
                        session.setAttribute("opacListd", empty);
                        session.setAttribute("button", button);
                        return mapping.findForward("gridcd");
                    }
//                    if (!d.isEmpty()) {
//                        session.setAttribute("title", title);
//                        session.setAttribute("isbn10", isbn10);
//                        session.setAttribute("doc_type", doc_type);
//                        session.setAttribute("opacLista", empty);
//                        session.setAttribute("opacListb", empty);
//                        session.setAttribute("opacListc", empty);
//                        session.setAttribute("opacListd", d);
//                        session.setAttribute("button", button);
//                        return mapping.findForward("grid");
//                    }
//                     else if (!b.isEmpty()) {
//                        session.setAttribute("title", title);
//                        session.setAttribute("isbn10", isbn10);
//                        session.setAttribute("doc_type", doc_type);
//                        session.setAttribute("opacLista", empty);
//                        session.setAttribute("opacListb", b);
//                        session.setAttribute("opacListc", empty);
//                        session.setAttribute("opacListd", empty);
//                        session.setAttribute("button", button);
//                        return mapping.findForward("grid");
//                    }
//                    else if (!c.isEmpty()) {
//                        session.setAttribute("opacLista", empty);
//                        session.setAttribute("opacListb", empty);
//                        session.setAttribute("opacListc", c);
//                        session.setAttribute("opacListd", empty);
//                        session.setAttribute("button", button);
//                        return mapping.findForward("grid");
//                    }
                    else {
                        bibform.setDocument_type(doc_type);
                        bibform.setTitle(title);
                       // bibform.setSubtitle("");
                      //  bibform.setStatement_responsibility("");
                       // bibform.setMain_entry("");
                       // bibform.setAdded_entry("");
                       // bibform.setPublisher_name("");
                       // bibform.setPublication_place("");
                      //  bibform.setPublishing_year("");
                      //  bibform.setLCC_no("");
                      //  bibform.setIsbn10(StringUtils.deleteWhitespace(isbn10));
                      //  bibform.setIsbn13("");
                      //  bibform.setEdition("");
                        bibform.setCall_no("");
                        bibform.setSubject("");
                        bibform.setThesis_abstract("");
                        bibform.setNotes("");
                        bibform.setNo_of_copies(0);
                        bibform.setSubmittedBy("");
                       // bibform.setLast("");
                        bibform.setGuide_name("");
                        bibform.setDegree("");
                        bibform.setAcceptance_year("");
                        bibform.setEmail("");

                        session.setAttribute("button", button);
                        return mapping.findForward("newcd");
                    }
//                } else if (!StringUtils.isEmpty(isbn10)) {
//                    bib = dao.search1Isbn10(isbn10, library_id, sub_library_id);
//                    if (bib != null) {
//                        String msg1 = resource.getString("cataloguing.catoldtitle.isbnduplicate");//"This isbn already exists enter different";
//                        request.setAttribute("msg1", msg1);
//                        return mapping.findForward("fail");
//                    } else {
//                        bibform.setDocument_type(doc_type);
//                        bibform.setTitle(title);
//                        bibform.setSubtitle("");
//                        bibform.setStatement_responsibility("");
//                        bibform.setMain_entry("");
//                        bibform.setAdded_entry("");
//                        bibform.setPublisher_name("");
//                        bibform.setPublication_place("");
//                        bibform.setPublishing_year("");
//                        bibform.setLCC_no("");
//                        bibform.setIsbn10(StringUtils.deleteWhitespace(isbn10));
//                        bibform.setIsbn13("");
//                        bibform.setEdition("");
//                        bibform.setCall_no("");
//                        bibform.setAlt_title("");
//                        bibform.setAlt_title("");
//                        bibform.setSubject("");
//                        bibform.setThesis_abstract("");
//                        bibform.setNotes("");
//                        bibform.setBook_type("");
//                        bibform.setNo_of_copies(0);
//                        session.setAttribute("button", button);
//                        return mapping.findForward("new");
//                    }
//                } else {
//                    bibform.setDocument_type(doc_type);
//                    bibform.setTitle(title);
//                    bibform.setSubtitle("");
//                    bibform.setStatement_responsibility("");
//                    bibform.setMain_entry("");
//                    bibform.setAdded_entry("");
//                    bibform.setPublisher_name("");
//                    bibform.setPublication_place("");
//                    bibform.setPublishing_year("");
//                    bibform.setLCC_no("");
//                    bibform.setIsbn10(StringUtils.deleteWhitespace(isbn10));
//                    bibform.setIsbn13("");
//                    bibform.setEdition("");
//                    bibform.setCall_no("");
//                    bibform.setAlt_title("");
//                    bibform.setAlt_title("");
//                    bibform.setSubject("");
//                    bibform.setThesis_abstract("");
//                    bibform.setNotes("");
//                    bibform.setBook_type("");
//                    bibform.setNo_of_copies(0);
//                    session.setAttribute("button", button);
//
//                    return mapping.findForward("new");
//                }
             } else if (button.equals("Update")) {
                if (!StringUtils.isEmpty(isbn10)) {
                    bib = dao.search2Isbn10(title, isbn10, library_id, sub_library_id);
                   if(bib != null)
                   {
                       //bibform.setNo_of_copies(bib.getNoOfCopies());
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
                        bibform.setPublishing_year(String.valueOf(bib.getPublishingYear()));
                        bibform.setSubtitle(bib.getSubtitle());
                        bibform.setTitle(bib.getTitle());
                        bibform.setCall_no(bib.getCallNo());
                        bibform.setAccession_type(bib.getAccessionType());
                        bibform.setBook_type(bib.getBookType());
                        bibform.setStatement_responsibility(bib.getStatementResponsibility());
                        bibform.setAlt_title(bib.getAltTitle());
                        bibform.setThesis_abstract(bib.getAbstract_());
                        bibform.setNotes(bib.getNotes());
                        bibform.setSubject(bib.getSubject());
                        bibform.setLanguage(bib.getEntryLanguage());
                                           if (bib != null) {
                        if (bib.getNoOfCopies() == null) {
                            i = 0;
                        } else {
                            i = bib.getNoOfCopies();
                        }
                    }
                        bb_id=bib.getId().getBiblioId();
                        biblang=dao.searchlangbyBiblioid(bb_id, library_id, sub_library_id);
                   }
                    if (bib != null && biblang!=null) {
                        bibform.setMain_entry1(biblang.getMainEntry());
                        bibform.setAdded_entryl(biblang.getAddedEntry());
                        bibform.setAdded_entry01(biblang.getAddedEntry1());
                        bibform.setAdded_entry11(biblang.getAddedEntry2());
                        bibform.setAdded_entry21(biblang.getAddedEntry3());
                        bibform.setSer_note1(biblang.getSeries());
                        bibform.setLCC_no1(biblang.getLccNo());
                        bibform.setEdition1(biblang.getEdition());
                        bibform.setIsbn101(biblang.getIsbn10());
                        bibform.setIsbn131(biblang.getIsbn13());
                        bibform.setPublication_place1(biblang.getPublicationPlace());
                        bibform.setPublisher_name1(biblang.getPublisherName());
                        bibform.setPublishing_year1(biblang.getPublishingYear());
                        bibform.setSubtitle1(biblang.getSubtitle());
                        bibform.setTitle1(biblang.getTitle());
                        bibform.setCall_no1(biblang.getCallNo());
                        bibform.setSubject1(biblang.getSubject());
                        bibform.setThesis_abstract1(biblang.getAbstract_());
                        bibform.setNotes1(biblang.getNotes());
                        bibform.setStatement_responsibility1(biblang.getStatementResponsibility());
                        bibform.setAlt_title1(biblang.getAltTitle());
                        bibform.setLanguage(biblang.getEntryLanguage());
                                           if (bib != null) {
                        if (bib.getNoOfCopies() == null) {
                            i = 0;
                        } else {
                            i = bib.getNoOfCopies();
                        }
                    }
                        session.setAttribute("button", button);
                        return mapping.findForward("new");
                    }
                    else if(bib != null){
                       // bibform.setNo_of_copies(bib.getNoOfCopies());
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
                        bibform.setPublishing_year(String.valueOf(bib.getPublishingYear()));
                        bibform.setSubtitle(bib.getSubtitle());
                        bibform.setTitle(bib.getTitle());
                        bibform.setCall_no(bib.getCallNo());
                        bibform.setAccession_type(bib.getAccessionType());
                        bibform.setBook_type(bib.getBookType());
                        bibform.setStatement_responsibility(bib.getStatementResponsibility());
                        bibform.setAlt_title(bib.getAltTitle());
                        bibform.setLanguage(bib.getEntryLanguage());
                                          if (bib != null) {
                        if (bib.getNoOfCopies() == null) {
                            i = 0;
                        } else {
                            i = bib.getNoOfCopies();
                        }
                    }
                        session.setAttribute("button", button);
                        return mapping.findForward("new");
                    }
                    else {
                         String msg3 = resource.getString("cataloguing.catoldtitle.titlenotfound");//Title corresponding to the isbn is not found.
                         request.setAttribute("msg1", msg3);
                        String msg2 = "";
                        request.setAttribute("msg2", msg2);
                        return mapping.findForward("fail");
                    }
                }
                if (!a.isEmpty()) {
                    session.setAttribute("opacListb", empty);
                    session.setAttribute("opacListc", empty);
                    session.setAttribute("opacListd", empty);
                    session.setAttribute("opacLista", a);
                    session.setAttribute("button", button);
                    System.out.println(button+"  "+doc_type);
                    if(doc_type.equalsIgnoreCase("Diss"))
                        return mapping.findForward("griddiss");
                    else if(doc_type.equalsIgnoreCase("Book"))
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
                    bib = dao.search2Isbn10(title, isbn10, library_id, sub_library_id);
                   if(bib != null)
                   {
                       bb_id=bib.getId().getBiblioId();
                       biblang=dao.searchlangbyBiblioid(bb_id, library_id, sub_library_id);
                   }
                    if (bib != null && biblang!=null) {
                       // bibform.setNo_of_copies(bib.getNoOfCopies());
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
                        bibform.setPublishing_year(String.valueOf(bib.getPublishingYear()));
                        bibform.setSubtitle(bib.getSubtitle());
                        bibform.setTitle(bib.getTitle());
                        bibform.setCall_no(bib.getCallNo());
                        bibform.setAccession_type(bib.getAccessionType());
                        bibform.setBook_type(bib.getBookType());
                        bibform.setStatement_responsibility(bib.getStatementResponsibility());
                        bibform.setAlt_title(bib.getAltTitle());
                        bibform.setThesis_abstract(bib.getAbstract_());
                        bibform.setNotes(bib.getNotes());
                        bibform.setSubject(bib.getSubject());
                        bibform.setMain_entry1(biblang.getMainEntry());
                        bibform.setAdded_entryl(biblang.getAddedEntry());
                        bibform.setAdded_entry01(biblang.getAddedEntry1());
                        bibform.setAdded_entry11(biblang.getAddedEntry2());
                        bibform.setAdded_entry21(biblang.getAddedEntry3());
                        bibform.setSer_note1(biblang.getSeries());
                        bibform.setLCC_no1(biblang.getLccNo());
                        bibform.setEdition1(biblang.getEdition());
                        bibform.setIsbn101(biblang.getIsbn10());
                        bibform.setIsbn131(biblang.getIsbn13());
                        bibform.setPublication_place1(biblang.getPublicationPlace());
                        bibform.setPublisher_name1(biblang.getPublisherName());
                        bibform.setPublishing_year1(biblang.getPublishingYear());
                        bibform.setSubtitle1(biblang.getSubtitle());
                        bibform.setTitle1(biblang.getTitle());
                        bibform.setCall_no1(biblang.getCallNo());
                        bibform.setSubject1(biblang.getSubject());
                        bibform.setThesis_abstract1(biblang.getAbstract_());
                        bibform.setNotes1(biblang.getNotes());
                        bibform.setStatement_responsibility1(biblang.getStatementResponsibility());
                        bibform.setAlt_title1(biblang.getAltTitle());
                        bibform.setLanguage(biblang.getEntryLanguage());
                                           if (bib != null) {
                        if (bib.getNoOfCopies() == null) {
                            i = 0;
                        } else {
                            i = bib.getNoOfCopies();
                        }
                    }
                        session.setAttribute("button", button);
                        return mapping.findForward("new");
                    }
                     else if(bib != null){
                      //  bibform.setNo_of_copies(bib.getNoOfCopies());
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
                        bibform.setPublishing_year(String.valueOf(bib.getPublishingYear()));
                        bibform.setSubtitle(bib.getSubtitle());
                        bibform.setTitle(bib.getTitle());
                        bibform.setCall_no(bib.getCallNo());
                        bibform.setAccession_type(bib.getAccessionType());
                        bibform.setBook_type(bib.getBookType());
                        bibform.setStatement_responsibility(bib.getStatementResponsibility());
                        bibform.setAlt_title(bib.getAltTitle());
                        bibform.setLanguage(bib.getEntryLanguage());
                                          if (bib != null) {
                        if (bib.getNoOfCopies() == null) {
                            i = 0;
                        } else {
                            i = bib.getNoOfCopies();
                        }
                    }
                        session.setAttribute("button", button);
                        return mapping.findForward("new");
                     }
                    else {
                     String msg3 = resource.getString("cataloguing.catoldtitle.titlenotfound");//Title corresponding to the isbn is not found.
                        request.setAttribute("msg1", msg3);
                        String msg2 = "";
                        request.setAttribute("msg2", msg2);
                        return mapping.findForward("fail");
                    }
                }
                if (!a.isEmpty()) {
                    session.setAttribute("opacLista", a);
                    session.setAttribute("opacListb", empty);
                    session.setAttribute("opacListc", empty);
                    session.setAttribute("opacListd", empty);
                    session.setAttribute("button", button);
                   if(doc_type.equalsIgnoreCase("Diss"))
                        return mapping.findForward("griddiss");
                    else if(doc_type.equalsIgnoreCase("Book"))
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
                    bib = dao.search2Isbn10(title, isbn10, library_id, sub_library_id);
                    if (bib != null) {
                       // bibform.setNo_of_copies(bib.getNoOfCopies());
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
                        bibform.setPublishing_year(String.valueOf(bib.getPublishingYear()));
                        bibform.setSubtitle(bib.getSubtitle());
                        bibform.setTitle(bib.getTitle());
                        bibform.setCall_no(bib.getCallNo());
                        bibform.setSer_note(bib.getSeries());
                        bibform.setAccession_type(bib.getAccessionType());
                        bibform.setBook_type(bib.getBookType());
                        bibform.setStatement_responsibility(bib.getStatementResponsibility());
                        bibform.setAlt_title(bib.getAltTitle());
                        bibform.setLanguage(bib.getEntryLanguage());
                                           if (bib != null) {
                        if (bib.getNoOfCopies() == null) {
                            i = 0;
                        } else {
                            i = bib.getNoOfCopies();
                        }
                    }
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
                        session.setAttribute("opacLista", a);
                        session.setAttribute("opacListb", empty);
                        session.setAttribute("opacListc", empty);
                        session.setAttribute("opacListd", empty);
                    session.setAttribute("button", button);
                   if(doc_type.equalsIgnoreCase("Diss"))
                        return mapping.findForward("griddiss");
                    else if(doc_type.equalsIgnoreCase("Book"))
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
          //Thesis Entry
        if (doc_type.equalsIgnoreCase("thesis")) {

            if (button.equals("New")) {
               // if (StringUtils.isEmpty(isbn10)) {
                    if (!a.isEmpty()) {
                        session.setAttribute("title", title);
                        session.setAttribute("isbn10", isbn10);
                        session.setAttribute("doc_type", doc_type);
                        session.setAttribute("opacLista", a);
                        session.setAttribute("opacListb", empty);
                        session.setAttribute("opacListc", empty);
                        session.setAttribute("opacListd", empty);
                        session.setAttribute("button", button);
                        return mapping.findForward("grid");
                    }
//                    if (!d.isEmpty()) {
//                        session.setAttribute("title", title);
//                        session.setAttribute("isbn10", isbn10);
//                        session.setAttribute("doc_type", doc_type);
//                        session.setAttribute("opacLista", empty);
//                        session.setAttribute("opacListb", empty);
//                        session.setAttribute("opacListc", empty);
//                        session.setAttribute("opacListd", d);
//                        session.setAttribute("button", button);
//                        return mapping.findForward("grid");
//                    }
//                     else if (!b.isEmpty()) {
//                        session.setAttribute("title", title);
//                        session.setAttribute("isbn10", isbn10);
//                        session.setAttribute("doc_type", doc_type);
//                        session.setAttribute("opacLista", empty);
//                        session.setAttribute("opacListb", b);
//                        session.setAttribute("opacListc", empty);
//                        session.setAttribute("opacListd", empty);
//                        session.setAttribute("button", button);
//                        return mapping.findForward("grid");
//                    }
//                    else if (!c.isEmpty()) {
//                        session.setAttribute("opacLista", empty);
//                        session.setAttribute("opacListb", empty);
//                        session.setAttribute("opacListc", c);
//                        session.setAttribute("opacListd", empty);
//                        session.setAttribute("button", button);
//                        return mapping.findForward("grid");
//                    }
                    else {
                        bibform.setDocument_type(doc_type);
                        bibform.setTitle(title);
                       // bibform.setSubtitle("");
                      //  bibform.setStatement_responsibility("");
                       // bibform.setMain_entry("");
                       // bibform.setAdded_entry("");
                       // bibform.setPublisher_name("");
                       // bibform.setPublication_place("");
                      //  bibform.setPublishing_year("");
                      //  bibform.setLCC_no("");
                      //  bibform.setIsbn10(StringUtils.deleteWhitespace(isbn10));
                      //  bibform.setIsbn13("");
                      //  bibform.setEdition("");
                        bibform.setCall_no("");
                        bibform.setSubject("");
                        bibform.setThesis_abstract("");
                        bibform.setNotes("");
                        bibform.setNo_of_copies(0);
                        bibform.setSubmittedBy("");
                        bibform.setLast_Modified("");
                        bibform.setGuide_name("");
                        bibform.setDegree("");
                        bibform.setAcceptance_year("");
                        bibform.setEmail("");

                        session.setAttribute("button", button);
                        return mapping.findForward("thesis");
                    }
//                } else if (!StringUtils.isEmpty(isbn10)) {
//                    bib = dao.search1Isbn10(isbn10, library_id, sub_library_id);
//                    if (bib != null) {
//                        String msg1 = resource.getString("cataloguing.catoldtitle.isbnduplicate");//"This isbn already exists enter different";
//                        request.setAttribute("msg1", msg1);
//                        return mapping.findForward("fail");
//                    } else {
//                        bibform.setDocument_type(doc_type);
//                        bibform.setTitle(title);
//                        bibform.setSubtitle("");
//                        bibform.setStatement_responsibility("");
//                        bibform.setMain_entry("");
//                        bibform.setAdded_entry("");
//                        bibform.setPublisher_name("");
//                        bibform.setPublication_place("");
//                        bibform.setPublishing_year("");
//                        bibform.setLCC_no("");
//                        bibform.setIsbn10(StringUtils.deleteWhitespace(isbn10));
//                        bibform.setIsbn13("");
//                        bibform.setEdition("");
//                        bibform.setCall_no("");
//                        bibform.setAlt_title("");
//                        bibform.setAlt_title("");
//                        bibform.setSubject("");
//                        bibform.setThesis_abstract("");
//                        bibform.setNotes("");
//                        bibform.setBook_type("");
//                        bibform.setNo_of_copies(0);
//                        session.setAttribute("button", button);
//                        return mapping.findForward("new");
//                    }
//                } else {
//                    bibform.setDocument_type(doc_type);
//                    bibform.setTitle(title);
//                    bibform.setSubtitle("");
//                    bibform.setStatement_responsibility("");
//                    bibform.setMain_entry("");
//                    bibform.setAdded_entry("");
//                    bibform.setPublisher_name("");
//                    bibform.setPublication_place("");
//                    bibform.setPublishing_year("");
//                    bibform.setLCC_no("");
//                    bibform.setIsbn10(StringUtils.deleteWhitespace(isbn10));
//                    bibform.setIsbn13("");
//                    bibform.setEdition("");
//                    bibform.setCall_no("");
//                    bibform.setAlt_title("");
//                    bibform.setAlt_title("");
//                    bibform.setSubject("");
//                    bibform.setThesis_abstract("");
//                    bibform.setNotes("");
//                    bibform.setBook_type("");
//                    bibform.setNo_of_copies(0);
//                    session.setAttribute("button", button);
//
//                    return mapping.findForward("new");
//                }
             } else if (button.equals("Update")) {
                if (!StringUtils.isEmpty(isbn10)) {
                    bib = dao.search2Isbn10(title, isbn10, library_id, sub_library_id);
                   if(bib != null)
                   {
                       //bibform.setNo_of_copies(bib.getNoOfCopies());
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
                        bibform.setPublishing_year(String.valueOf(bib.getPublishingYear()));
                        bibform.setSubtitle(bib.getSubtitle());
                        bibform.setTitle(bib.getTitle());
                        bibform.setCall_no(bib.getCallNo());
                        bibform.setAccession_type(bib.getAccessionType());
                        bibform.setBook_type(bib.getBookType());
                        bibform.setStatement_responsibility(bib.getStatementResponsibility());
                        bibform.setAlt_title(bib.getAltTitle());
                        bibform.setThesis_abstract(bib.getAbstract_());
                        bibform.setNotes(bib.getNotes());
                        bibform.setSubject(bib.getSubject());
                        bibform.setLanguage(bib.getEntryLanguage());
                                           if (bib != null) {
                        if (bib.getNoOfCopies() == null) {
                            i = 0;
                        } else {
                            i = bib.getNoOfCopies();
                        }
                    }
                        bb_id=bib.getId().getBiblioId();
                        biblang=dao.searchlangbyBiblioid(bb_id, library_id, sub_library_id);
                   }
                    if (bib != null && biblang!=null) {
                        bibform.setMain_entry1(biblang.getMainEntry());
                        bibform.setAdded_entryl(biblang.getAddedEntry());
                        bibform.setAdded_entry01(biblang.getAddedEntry1());
                        bibform.setAdded_entry11(biblang.getAddedEntry2());
                        bibform.setAdded_entry21(biblang.getAddedEntry3());
                        bibform.setSer_note1(biblang.getSeries());
                        bibform.setLCC_no1(biblang.getLccNo());
                        bibform.setEdition1(biblang.getEdition());
                        bibform.setIsbn101(biblang.getIsbn10());
                        bibform.setIsbn131(biblang.getIsbn13());
                        bibform.setPublication_place1(biblang.getPublicationPlace());
                        bibform.setPublisher_name1(biblang.getPublisherName());
                        bibform.setPublishing_year1(biblang.getPublishingYear());
                        bibform.setSubtitle1(biblang.getSubtitle());
                        bibform.setTitle1(biblang.getTitle());
                        bibform.setCall_no1(biblang.getCallNo());
                        bibform.setSubject1(biblang.getSubject());
                        bibform.setThesis_abstract1(biblang.getAbstract_());
                        bibform.setNotes1(biblang.getNotes());
                        bibform.setStatement_responsibility1(biblang.getStatementResponsibility());
                        bibform.setAlt_title1(biblang.getAltTitle());
                        bibform.setLanguage(biblang.getEntryLanguage());
                                           if (bib != null) {
                        if (bib.getNoOfCopies() == null) {
                            i = 0;
                        } else {
                            i = bib.getNoOfCopies();
                        }
                    }
                        session.setAttribute("button", button);
                        return mapping.findForward("new");
                    }
                    else if(bib != null){
                       // bibform.setNo_of_copies(bib.getNoOfCopies());
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
                        bibform.setPublishing_year(String.valueOf(bib.getPublishingYear()));
                        bibform.setSubtitle(bib.getSubtitle());
                        bibform.setTitle(bib.getTitle());
                        bibform.setCall_no(bib.getCallNo());
                        bibform.setAccession_type(bib.getAccessionType());
                        bibform.setBook_type(bib.getBookType());
                        bibform.setStatement_responsibility(bib.getStatementResponsibility());
                        bibform.setAlt_title(bib.getAltTitle());
                        bibform.setLanguage(bib.getEntryLanguage());
                                          if (bib != null) {
                        if (bib.getNoOfCopies() == null) {
                            i = 0;
                        } else {
                            i = bib.getNoOfCopies();
                        }
                    }
                        session.setAttribute("button", button);
                        return mapping.findForward("new");
                    }
                    else {
                         String msg3 = resource.getString("cataloguing.catoldtitle.titlenotfound");//Title corresponding to the isbn is not found.
                         request.setAttribute("msg1", msg3);
                        String msg2 = "";
                        request.setAttribute("msg2", msg2);
                        return mapping.findForward("fail");
                    }
                }
                if (!a.isEmpty()) {
                    session.setAttribute("opacListb", empty);
                    session.setAttribute("opacListc", empty);
                    session.setAttribute("opacListd", empty);
                    session.setAttribute("opacLista", a);
                    session.setAttribute("button", button);
                   
                    return mapping.findForward("gridthesis");
                } else {
                     String msg3 = resource.getString("cataloguing.catoldtitle.titlenotfound1");//Title is not found.
                    request.setAttribute("msg1", msg3);
                    String msg2 = "";
                    request.setAttribute("msg2", msg2);
                    return mapping.findForward("fail");
                }
           } else if (button.equals("Delete")) {
                if (!StringUtils.isEmpty(isbn10)) {
                    bib = dao.search2Isbn10(title, isbn10, library_id, sub_library_id);
                   if(bib != null)
                   {
                       bb_id=bib.getId().getBiblioId();
                       biblang=dao.searchlangbyBiblioid(bb_id, library_id, sub_library_id);
                   }
                    if (bib != null && biblang!=null) {
                       // bibform.setNo_of_copies(bib.getNoOfCopies());
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
                        bibform.setPublishing_year(String.valueOf(bib.getPublishingYear()));
                        bibform.setSubtitle(bib.getSubtitle());
                        bibform.setTitle(bib.getTitle());
                        bibform.setCall_no(bib.getCallNo());
                        bibform.setAccession_type(bib.getAccessionType());
                        bibform.setBook_type(bib.getBookType());
                        bibform.setStatement_responsibility(bib.getStatementResponsibility());
                        bibform.setAlt_title(bib.getAltTitle());
                        bibform.setThesis_abstract(bib.getAbstract_());
                        bibform.setNotes(bib.getNotes());
                        bibform.setSubject(bib.getSubject());
                        bibform.setMain_entry1(biblang.getMainEntry());
                        bibform.setAdded_entryl(biblang.getAddedEntry());
                        bibform.setAdded_entry01(biblang.getAddedEntry1());
                        bibform.setAdded_entry11(biblang.getAddedEntry2());
                        bibform.setAdded_entry21(biblang.getAddedEntry3());
                        bibform.setSer_note1(biblang.getSeries());
                        bibform.setLCC_no1(biblang.getLccNo());
                        bibform.setEdition1(biblang.getEdition());
                        bibform.setIsbn101(biblang.getIsbn10());
                        bibform.setIsbn131(biblang.getIsbn13());
                        bibform.setPublication_place1(biblang.getPublicationPlace());
                        bibform.setPublisher_name1(biblang.getPublisherName());
                        bibform.setPublishing_year1(biblang.getPublishingYear());
                        bibform.setSubtitle1(biblang.getSubtitle());
                        bibform.setTitle1(biblang.getTitle());
                        bibform.setCall_no1(biblang.getCallNo());
                        bibform.setSubject1(biblang.getSubject());
                        bibform.setThesis_abstract1(biblang.getAbstract_());
                        bibform.setNotes1(biblang.getNotes());
                        bibform.setStatement_responsibility1(biblang.getStatementResponsibility());
                        bibform.setAlt_title1(biblang.getAltTitle());
                        bibform.setLanguage(biblang.getEntryLanguage());
                                           if (bib != null) {
                        if (bib.getNoOfCopies() == null) {
                            i = 0;
                        } else {
                            i = bib.getNoOfCopies();
                        }
                    }
                        session.setAttribute("button", button);
                        return mapping.findForward("new");
                    }
                     else if(bib != null){
                      //  bibform.setNo_of_copies(bib.getNoOfCopies());
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
                        bibform.setPublishing_year(String.valueOf(bib.getPublishingYear()));
                        bibform.setSubtitle(bib.getSubtitle());
                        bibform.setTitle(bib.getTitle());
                        bibform.setCall_no(bib.getCallNo());
                        bibform.setAccession_type(bib.getAccessionType());
                        bibform.setBook_type(bib.getBookType());
                        bibform.setStatement_responsibility(bib.getStatementResponsibility());
                        bibform.setAlt_title(bib.getAltTitle());
                        bibform.setLanguage(bib.getEntryLanguage());
                                          if (bib != null) {
                        if (bib.getNoOfCopies() == null) {
                            i = 0;
                        } else {
                            i = bib.getNoOfCopies();
                        }
                    }
                        session.setAttribute("button", button);
                        return mapping.findForward("new");
                     }
                    else {
                     String msg3 = resource.getString("cataloguing.catoldtitle.titlenotfound");//Title corresponding to the isbn is not found.
                        request.setAttribute("msg1", msg3);
                        String msg2 = "";
                        request.setAttribute("msg2", msg2);
                        return mapping.findForward("fail");
                    }
                }
                if (!a.isEmpty()) {
                    session.setAttribute("opacLista", a);
                    session.setAttribute("opacListb", empty);
                    session.setAttribute("opacListc", empty);
                    session.setAttribute("opacListd", empty);
                    session.setAttribute("button", button);
                    return mapping.findForward("gridthesis");
                } else {
                     String msg3 = resource.getString("cataloguing.catoldtitle.titlenotfound1");//Title is not found.
                    request.setAttribute("msg1", msg3);
                    String msg2 = "";
                    request.setAttribute("msg2", msg2);
                    return mapping.findForward("fail");
                }
            } else if (button.equals("View")) {
                if (!StringUtils.isEmpty(isbn10)) {
                    bib = dao.search2Isbn10(title, isbn10, library_id, sub_library_id);
                    if (bib != null) {
                       // bibform.setNo_of_copies(bib.getNoOfCopies());
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
                        bibform.setPublishing_year(String.valueOf(bib.getPublishingYear()));
                        bibform.setSubtitle(bib.getSubtitle());
                        bibform.setTitle(bib.getTitle());
                        bibform.setCall_no(bib.getCallNo());
                        bibform.setSer_note(bib.getSeries());
                        bibform.setAccession_type(bib.getAccessionType());
                        bibform.setBook_type(bib.getBookType());
                        bibform.setStatement_responsibility(bib.getStatementResponsibility());
                        bibform.setAlt_title(bib.getAltTitle());
                        bibform.setLanguage(bib.getEntryLanguage());
                                           if (bib != null) {
                        if (bib.getNoOfCopies() == null) {
                            i = 0;
                        } else {
                            i = bib.getNoOfCopies();
                        }
                    }
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
                        session.setAttribute("opacLista", a);
                        session.setAttribute("opacListb", empty);
                        session.setAttribute("opacListc", empty);
                        session.setAttribute("opacListd", empty);
                    session.setAttribute("button", button);
                    return mapping.findForward("gridthesis");
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

        return mapping.findForward("fail");
    }
}
