/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.cataloguing;

import com.myapp.struts.cataloguingDAO.BibliopgraphicEntryDAO;
import com.myapp.struts.hbm.BibliographicDetails;
import com.myapp.struts.hbm.BibliographicDetailsId;
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


/**
 *
 * @author <a href="mailto:asif633@gmail.com">Asif Iqubal</a>
 */
public class BiblioDetailsAction extends org.apache.struts.action.Action {
    private static final String SUCCESS = "success";
        BibliographicDetailsId bibid=new BibliographicDetailsId();
    BibliographicDetails bib=new BibliographicDetails();
    BibliographicDetails bib1=new BibliographicDetails();
    BibliopgraphicEntryDAO dao=new BibliopgraphicEntryDAO();
    DocumentDetailsId ddid=new DocumentDetailsId();

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
  BibliographicDetailEntryActionForm bibform=(BibliographicDetailEntryActionForm)form;
        HttpSession session1=request.getSession();
        String library_id=(String)session1.getAttribute("library_id");
        System.out.println("GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG");
        String sub_library_id=(String)session1.getAttribute("sublibrary_id");
        int  biblio_id=bibform.getBiblio_id();
        String button=bibform.getButton();
        String isbn10=bibform.getIsbn10();
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
        if(button.equals("Update"))
      {
       BibliographicDetails biblio = dao.searchIsbn10ByBiblio(bibform.getIsbn10(), bibform.getBiblio_id(), library_id, sub_library_id);
       BibliographicDetails uniq_calsearch = dao.searchCallNOByBiblio(bibform.getCall_no(), bibform.getBiblio_id(), library_id, sub_library_id);
       if (biblio != null) {
       String msg1 = "This isbn already exists enter different";
       request.setAttribute("msg1", msg1);
       bibform.setButton("Update");
       return mapping.findForward("failure");
       }
       else if (uniq_calsearch != null) {
       String msg1 = "This callno  already exists enter different";
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
        DocumentDetails dd=(DocumentDetails)jj.get(i);
        ddid.setSublibraryId(sub_library_id);
        ddid.setLibraryId(library_id);
        ddid.setDocumentId(dd.getId().getDocumentId());
        dd.setId(ddid);
        dd.setStatus(dd.getStatus());
        dd.setIsbn10(bibform.getIsbn10());
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
        System.out.println("^^^^^^^^^^^^^^^^^^&");
        bibform.setIsbn10("");
        bibform.setTitle("");
        String msg2="Record updated successfully";
        request.setAttribute("msg2",msg2);
        return mapping.findForward(SUCCESS);
 }

    }
        if(button.equals("Delete")){
       List  jj=dao.searchDoc1(biblio_id, library_id,sub_library_id);
       if(!jj.isEmpty())
       {
       String msg1 = "There are accessioned items of this title can not delete this title";
       request.setAttribute("msg1", msg1);
       bibform.setButton("Delete");
       return mapping.findForward("failure");
       }
       dao.delete( biblio_id, library_id,sub_library_id);
        bibform.setIsbn10("");
        bibform.setTitle("");
        String msg2="Record deleted successfully";
        request.setAttribute("msg2",msg2);
        return mapping.findForward(SUCCESS);
        }
    return mapping.findForward(SUCCESS);
    }

}