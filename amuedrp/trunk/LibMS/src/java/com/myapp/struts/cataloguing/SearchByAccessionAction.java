/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.cataloguing;

import com.myapp.struts.cataloguingDAO.BibliographicEntryDAO;
import com.myapp.struts.systemsetupDAO.*;
import com.myapp.struts.hbm.*;
import com.myapp.struts.hbm.DocumentCategory;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class SearchByAccessionAction extends org.apache.struts.action.Action {

    BibliographicEntryDAO dao = new BibliographicEntryDAO();

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DocumentCategoryDAO doccatdao=new DocumentCategoryDAO();
        LocationDAO locdao=new LocationDAO();
        BibliographicDetailEntryActionForm1 bibform = (BibliographicDetailEntryActionForm1) form;
        HttpSession session1 = request.getSession();
        String library_id = (String) session1.getAttribute("library_id");
        String sub_library_id = (String) session1.getAttribute("sublibrary_id");
        String id = request.getParameter("id");
        
        DocumentDetails bib = dao.getAccession(library_id, sub_library_id, id);
        bibform.setBiblio_id(bib.getBiblioId());
        if(bib.getNoOfCopies()!=null)
        bibform.setNo_of_copies(bib.getNoOfCopies());
        bibform.setThesis_abstract(bib.getAbstract_());
        bibform.setNotes(bib.getNotes());
        bibform.setSubject(bib.getSubject());
        bibform.setMain_entry(bib.getMainEntry());
        bibform.setAdded_entry(bib.getAddedEntry());
        bibform.setAdded_entry(bib.getAddedEntry());
        bibform.setAdded_entry0(bib.getAddedEntry1());
        bibform.setAdded_entry1(bib.getAddedEntry2());
        bibform.setAdded_entry2(bib.getAddedEntry3());
        bibform.setBiblio_id(bib.getBiblioId());
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
        bibform.setNotes(bib.getNotes());
        bibform.setThesis_abstract(bib.getAbstract_());
        bibform.setAccession_type(bib.getAccessionType());
        bibform.setLanguage(bib.getEntryLanguage());
        bibform.setAcc_no1(bib.getAccessionNo());
        DocumentCategory doc=doccatdao.searchDocumentCategory(library_id, sub_library_id,bib.getBookType());
        if(doc!=null){
            bibform.setBook_type(doc.getDocumentCategoryName());
        }else{
                    bibform.setBook_type("");
        }
        bibform.setStatement_responsibility(bib.getStatementResponsibility());
        bibform.setAlt_title(bib.getAltTitle());
        String msg3 = "";
        //Regarding Accessioning
        bibform.setAccession_no(bib.getAccessionNo());
        bibform.setVolume_no(bib.getVolumeNo());
        bibform.setShelving_location(bib.getShelvingLocation());
        bibform.setBind_type(bib.getBindType());
        
        bibform.setCollation(bib.getCollation1());
        bibform.setIndex_no(bib.getIndexNo());
        bibform.setNo_of_pages(bib.getNoOfPages());
        List<Location> loc=locdao.listlocation(library_id, sub_library_id);
        List<MixLocationSublibrary> lmls=new ArrayList<MixLocationSublibrary>();
           for (int j = 0; j < loc.size(); j++) {
            MixLocationSublibrary mls=new MixLocationSublibrary();
            mls.setLocationId(loc.get(j).getId().getLocationId());
            mls.setLocationName(loc.get(j).getLocationName());
            lmls.add(mls);
        }

        bibform.setLocation(bib.getLocation());
        bibform.setShelving_location(bibform.getShelving_location());
        bibform.setBind_type(bibform.getBind_type());
        request.setAttribute("booktype", bib.getBookType());
        bibform.setRecord_no(bib.getRecordNo());
        bibform.setBiblio_id(bib.getBiblioId());
        bibform.setDate_acquired(bib.getDateAcquired());
        //Entry Related to Disseration
        bibform.setSubmittedBy(bib.getSubmittedBy());
        bibform.setSubmitted_on(bib.getSubmittedOn());
        bibform.setGuide_name(bib.getGuideName());
        bibform.setAcceptance_year(bib.getAcceptanceYear());
        bibform.setDegree(bib.getDegree());
        //Entry Related to Thesis
        bibform.setThesis_status(bib.getThesisStatus());
        bibform.setLast_Modified(bib.getLastModified());
        
        request.setAttribute("msg1", msg3);
       session1.setAttribute("mixlist", lmls);
       if(bib.getDocumentType().equalsIgnoreCase("Book"))
        return mapping.findForward("view");
       else if(bib.getDocumentType().equalsIgnoreCase("Diss"))
           return mapping.findForward("viewdiss");
       else if(bib.getDocumentType().equalsIgnoreCase("Thesis"))
           return mapping.findForward("viewthesis");

return null;
    }
}
