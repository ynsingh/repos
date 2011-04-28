/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.cataloguing;

import com.myapp.struts.cataloguingDAO.BibliopgraphicEntryDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.hbm.*;

/**
 *
 * @author <a href="mailto:asif633@gmail.com">Asif Iqubal</a>
 */
public class BibliographicDetailAction1 extends org.apache.struts.action.Action {

    BibliopgraphicEntryDAO dao = new BibliopgraphicEntryDAO();

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BibliographicDetailEntryActionForm1 bibform = (BibliographicDetailEntryActionForm1) form;
        HttpSession session1 = request.getSession();
        String library_id = (String) session1.getAttribute("library_id");
        String sub_library_id = (String) session1.getAttribute("sublibrary_id");
        String control_no = request.getParameter("id");
        AcqFinalDemandList bib = dao.searchacq(control_no, library_id, sub_library_id);
        bibform.setMain_entry(bib.getAuthor());
        bibform.setEdition(bib.getEdition());
        bibform.setIsbn10(bib.getIsbn());
        bibform.setLibrary_id(bib.getId().getLibraryId());
        bibform.setSub_library_id(bib.getId().getSublibraryId());
        bibform.setPublisher_name(bib.getPublisherId());
        bibform.setSubtitle(bib.getSubtitle());
        bibform.setTitle(bib.getTitle());
        String msg3 = "";
        request.setAttribute("msg1", msg3);
        return mapping.findForward("view");
    }
}
