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
/**
 *
 * @author <a href="mailto:asif633@gmail.com">Asif Iqubal</a>
 */
public class BibliographicDetailEntryAction extends org.apache.struts.action.Action {
    BibliographicDetailsId bibid = new BibliographicDetailsId();
    BibliographicDetails bib = new BibliographicDetails();
    AcqFinalDemandList acqdemand = new AcqFinalDemandList();
    BibliopgraphicEntryDAO dao = new BibliopgraphicEntryDAO();
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
        if(library_id.equals(sub_library_id))
       {
           List<SubLibrary> sub1=DeptDAO.listsub1(library_id);
                                      if(sub1.isEmpty()){
                        String msg1 = resource.getString("cataloguing.catoldtitle.setsublibrary");//You need to set sublibrary
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
                        String msg1 = resource.getString("cataloguing.catoldtitle.setsublibrary");//You need to set sublibrary
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
                        session.setAttribute("opacLista", a);
                        session.setAttribute("opacListb", empty);
                        session.setAttribute("opacListc", empty);
                        session.setAttribute("opacListd", empty);
                        session.setAttribute("button", button);
                        return mapping.findForward("grid");
                    }
                    if (!d.isEmpty()) {
                        session.setAttribute("doc_type", doc_type);
                        session.setAttribute("opacListd", d);
                        session.setAttribute("opacListb", empty);
                        session.setAttribute("opacListc", empty);
                        session.setAttribute("opacLista", empty);
                        session.setAttribute("button", button);
                        return mapping.findForward("grid");
                    }
                     else if (!b.isEmpty()) {
                        session.setAttribute("title", title);
                        session.setAttribute("isbn10", isbn10);
                        session.setAttribute("doc_type", doc_type);
                        session.setAttribute("opacListb", b);
                        session.setAttribute("opacLista", empty);
                        session.setAttribute("opacListc", empty);
                        session.setAttribute("opacListd", empty);
                        session.setAttribute("button", button);
                        return mapping.findForward("grid");
                    }
                    else if (!c.isEmpty()) {
                        session.setAttribute("doc_type", doc_type);
                        session.setAttribute("opacListc", c);
                        session.setAttribute("opacLista", empty);
                        session.setAttribute("opacListb", empty);
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
                    if (bib != null) {
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
                        bibform.setPublishing_year(String.valueOf(bib.getPublishingYear()));
                        bibform.setSubtitle(bib.getSubtitle());
                        bibform.setTitle(bib.getTitle());
                        bibform.setCall_no(bib.getCallNo());
                        bibform.setAccession_type(bib.getAccessionType());                     
                        bibform.setBook_type(bib.getBookType());
                        bibform.setStatement_responsibility(bib.getStatementResponsibility());
                        bibform.setAlt_title(bib.getAltTitle());
                        bibform.setDate_acquired1(bib.getDateAcquired());
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
                    session.setAttribute("doc_type", doc_type);
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
                        bibform.setPublishing_year(String.valueOf(bib.getPublishingYear()));
                        bibform.setSubtitle(bib.getSubtitle());
                        bibform.setTitle(bib.getTitle());
                        bibform.setCall_no(bib.getCallNo());
                        bibform.setAccession_type(bib.getAccessionType());                    
                        bibform.setBook_type(bib.getBookType());
                        bibform.setStatement_responsibility(bib.getStatementResponsibility());
                        bibform.setSer_note(bib.getSeries());
                        bibform.setAlt_title(bib.getAltTitle());
                        bibform.setDate_acquired1(bib.getDateAcquired());
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
                        session.setAttribute("doc_type", doc_type);
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
                        bibform.setPublishing_year(String.valueOf(bib.getPublishingYear()));
                        bibform.setSubtitle(bib.getSubtitle());
                        bibform.setTitle(bib.getTitle());
                        bibform.setCall_no(bib.getCallNo());
                        bibform.setSer_note(bib.getSeries());
                        bibform.setAccession_type(bib.getAccessionType());                     
                        bibform.setBook_type(bib.getBookType());
                        bibform.setStatement_responsibility(bib.getStatementResponsibility());
                        bibform.setAlt_title(bib.getAltTitle());
                        bibform.setDate_acquired1(bib.getDateAcquired());
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
                        session.setAttribute("doc_type", doc_type);
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

        else if (doc_type.equals("CD")) {
            if (button.equals("New")) {
                isbn10 = StringUtils.trim(isbn10);
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
                        session.setAttribute("doc_type", doc_type);
                        session.setAttribute("opacListd", d);
                        session.setAttribute("opacListb", empty);
                        session.setAttribute("opacListc", empty);
                        session.setAttribute("opacLista", empty);
                        session.setAttribute("button", button);
                        return mapping.findForward("grid");
                    }
                     else if (!b.isEmpty()) {
                        session.setAttribute("title", title);
                        session.setAttribute("isbn10", isbn10);
                        session.setAttribute("doc_type", doc_type);
                        session.setAttribute("opacListb", b);
                        session.setAttribute("opacLista", empty);
                        session.setAttribute("opacListc", empty);
                        session.setAttribute("opacListd", empty);
                        session.setAttribute("button", button);
                        return mapping.findForward("grid");
                    }
                    else if (!c.isEmpty()) {
                        session.setAttribute("doc_type", doc_type);
                        session.setAttribute("opacListc", c);
                        session.setAttribute("opacLista", empty);
                        session.setAttribute("opacListb", empty);
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
                        bibform.setBook_type("");
                        session.setAttribute("button", button);
                        return mapping.findForward("cd");
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
                        session.setAttribute("button", button);
                        return mapping.findForward("cd");
                    }
                } else {
                    bibform.setMain_entry("");
                    bibform.setAdded_entry("");
                    bibform.setSource("");
                    bibform.setDuration("");
                    bibform.setPhysical_desc("");
                    bibform.setSeries("");
                    bibform.setPhysical_form("");
                    bibform.setColour("");
                    bibform.setDocument_type(doc_type);
                    bibform.setEdition("");
                    bibform.setIndex_no("");
                    bibform.setLibrary_id("");
                    bibform.setNo_of_pages("");
                    bibform.setPhysical_width("");
                    bibform.setPrice("");
                    bibform.setPublication_place("");
                    bibform.setPublisher_name("");
                    bibform.setPublishing_year("");
                    bibform.setSubtitle("");
                    bibform.setTitle(title);
                    bibform.setCall_no("");
                    bibform.setAccession_type("");
                    bibform.setNo_of_copies(0);
                    session.setAttribute("button", button);
                    return mapping.findForward("cd");
                }
            } else if (button.equals("Update")) {
                if (!StringUtils.isEmpty(isbn10)) {
                    bib = dao.search2Isbn10(title, isbn10, library_id, sub_library_id);
                    if (bib != null) {
                        bibform.setDocument_type(bib.getDocumentType());
                        bibform.setType_of_disc(bib.getTypeOfDisc());
                        bibform.setMain_entry(bib.getMainEntry());
                        bibform.setAdded_entry(bib.getAddedEntry());
                        bibform.setBiblio_id(bib.getId().getBiblioId());
                        bibform.setSeries(bib.getSeries());
                       // bibform.setPhysical_form(bib.getPhysicalForm());
                        // bibform.setPhysical_desc(bib.getphysicalDesc()); ///////////// it has to add in database
                        bibform.setEdition(bib.getEdition());
                        bibform.setIsbn10(bib.getIsbn10());
                        bibform.setIsbn13(bib.getIsbn13());
                        bibform.setLibrary_id(bib.getId().getLibraryId());
                        bibform.setSub_library_id(bib.getId().getSublibraryId());
                        bibform.setPublication_place(bib.getPublicationPlace());
                        bibform.setPublisher_name(bib.getPublisherName());
                        bibform.setPublishing_year(String.valueOf(bib.getPublishingYear()));
                        bibform.setSubtitle(bib.getSubtitle());
                        bibform.setAdded_entry(bib.getAddedEntry());
                        bibform.setAdded_entry0(bib.getAddedEntry1());
                        bibform.setAdded_entry1(bib.getAddedEntry2());
                        bibform.setAdded_entry2(bib.getAddedEntry3());
                        bibform.setTitle(bib.getTitle());
                        bibform.setCall_no(bib.getCallNo());
                        bibform.setAccession_type(bib.getAccessionType());
                        bibform.setBook_type(bib.getBookType());
                        bibform.setStatement_responsibility(bib.getStatementResponsibility());
                        bibform.setAlt_title(bib.getAltTitle());
                        bibform.setThesis_abstract(bib.getAbstract_());
                        bibform.setDuration(bib.getDuration());
                        bibform.setSource(bib.getSource1());
                        bibform.setSubject(bib.getSubject());
                        bibform.setNotes(bib.getNotes());
                        bibform.setDate_acquired1(bib.getDateAcquired());
                        return mapping.findForward("cd");
                } else {
                         String msg3 = resource.getString("cataloguing.catoldtitle.titlenotfound");//Title corresponding to the isbn is not found.
                         request.setAttribute("msg1", msg3);
                        String msg2 = "";
                        request.setAttribute("msg2", msg2);
                        return mapping.findForward("fail");
                    }
                }
                if (!a.isEmpty()) {
                    session.setAttribute("doc_type", doc_type);
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
            } else if (button.equals("Delete")) {
                if (!StringUtils.isEmpty(isbn10)) {
                    bib = dao.search2Isbn10(title, isbn10, library_id, sub_library_id);
                    //System.out.println("BBBBBBBBBBBBBBBBBBB" + bib);
                    if (bib != null) {
                        bibform.setDocument_type(bib.getDocumentType());
                        bibform.setType_of_disc(bib.getTypeOfDisc());
                        bibform.setMain_entry(bib.getMainEntry());
                        bibform.setAdded_entry(bib.getAddedEntry());
                        bibform.setBiblio_id(bib.getId().getBiblioId());
                        bibform.setSeries(bib.getSeries());
                        bibform.setAdded_entry(bib.getAddedEntry());
                        bibform.setAdded_entry0(bib.getAddedEntry1());
                        bibform.setAdded_entry1(bib.getAddedEntry2());
                        bibform.setAdded_entry2(bib.getAddedEntry3());
                        // bibform.setPhysical_form(bib.getPhysicalForm());
                        // bibform.setPhysical_desc(bib.getphysicalDesc()); ///////////// it has to add in database
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
                        bibform.setDuration(bib.getDuration());
                        bibform.setSource(bib.getSource1());
                        bibform.setSubject(bib.getSubject());
                        bibform.setNotes(bib.getNotes());
                        bibform.setDate_acquired1(bib.getDateAcquired());
                        // request.setAttribute("buttn_click", "view");
                        request.setAttribute("btnval", "Delete");
                        request.setAttribute("buttn_click", "fron_old_jsp");
                        return mapping.findForward("cd");
                    } else {
                        String msg3 = "Title corresponding to the isbn is not found.";
                        request.setAttribute("msg1", msg3);
                        String msg2 = "";
                        request.setAttribute("msg2", msg2);
                        return mapping.findForward("fail");
                    }
                }
                // List a= dao.getTitles(title,library_id, sub_library_id,doc_type);
                if (!a.isEmpty()) {
                    session.setAttribute("doc_type", doc_type);
                    session.setAttribute("opacLista", a);
                    session.setAttribute("opacListb", empty);
                    session.setAttribute("opacListc", empty);
                    session.setAttribute("opacListd", empty);
                    session.setAttribute("button", button);
                    return mapping.findForward("grid");
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
                    //System.out.println("BBBBBBBBBBBBBBBBBBB" + bib);
                    if (bib != null) {
                        bibform.setDocument_type(bib.getDocumentType());
                        bibform.setType_of_disc(bib.getTypeOfDisc());
                        bibform.setMain_entry(bib.getMainEntry());
                        bibform.setAdded_entry(bib.getAddedEntry());
                        bibform.setBiblio_id(bib.getId().getBiblioId());
                        bibform.setSeries(bib.getSeries());
                        bibform.setAdded_entry(bib.getAddedEntry());
                        bibform.setAdded_entry0(bib.getAddedEntry1());
                        bibform.setAdded_entry1(bib.getAddedEntry2());
                        bibform.setAdded_entry2(bib.getAddedEntry3());
                        // bibform.setPhysical_form(bib.getPhysicalForm());
                        // bibform.setPhysical_desc(bib.getphysicalDesc()); ///////////// it has to add in database
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
                        bibform.setDuration(bib.getDuration());
                        bibform.setSource(bib.getSource1());
                        bibform.setSubject(bib.getSubject());
                        bibform.setNotes(bib.getNotes());
                        bibform.setDate_acquired1(bib.getDateAcquired());
                        request.setAttribute("buttn_click", "view");
                        return mapping.findForward("cd");
                    } else {
                        String msg3 = "Title corresponding to the isbn is not found.";
                        request.setAttribute("msg1", msg3);
                        String msg2 = "";
                        request.setAttribute("msg2", msg2);
                        return mapping.findForward("fail");
                    }
                }
//        List a= dao.getTitles(title,library_id, sub_library_id,doc_type);
                if (!a.isEmpty()) {
                    session.setAttribute("doc_type", doc_type);
                    session.setAttribute("opacLista", a);
                    session.setAttribute("opacListb", empty);
                    session.setAttribute("opacListc", empty);
                    session.setAttribute("opacListd", empty);
                    session.setAttribute("button", button);
                    return mapping.findForward("grid");
                } else {
                    String msg3 = "Title not found.";
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
