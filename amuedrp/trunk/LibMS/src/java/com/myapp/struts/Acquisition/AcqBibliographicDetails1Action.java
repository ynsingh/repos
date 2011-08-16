/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.AcquisitionDao.AcquisitionDao;
import com.myapp.struts.hbm.*;
import javax.servlet.http.HttpSession;
/**
 *
 * @author maqbool
 */
public class AcqBibliographicDetails1Action extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    AcquisitionDao  ado=new AcquisitionDao();
    AcqBibliography acqbib=new AcqBibliography();
    AcqBibliographyId acqbibid=new AcqBibliographyId();

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
        AcqBiblioActionForm acqbibfrm= (AcqBiblioActionForm)form;
         HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        String title= acqbibfrm.getTitle();
        String doc_type=acqbibfrm.getDocument_type();
        String  button=acqbibfrm.getButton();
        String id=request.getParameter("id");
        int ii=Integer.parseInt(id);
        acqbib=ado.getBiblio(library_id, sub_library_id, ii);
        acqbibfrm.setTitle_id(ii);
        acqbibfrm.setTitle(acqbib.getTitle());
        acqbibfrm.setAuthor(acqbib.getAuthor());
        acqbibfrm.setSub_author(acqbib.getSubAuthor());
        acqbibfrm.setSub_author0(acqbib.getSubAuthor0());
        acqbibfrm.setSub_author1(acqbib.getSubAuthor1());
        acqbibfrm.setSub_author2(acqbib.getSubAuthor2());
        acqbibfrm.setDocument_type(acqbib.getDocType());
        acqbibfrm.setEdition(acqbib.getEdition());
        acqbibfrm.setIsbn(acqbib.getIsbn());
        acqbibfrm.setLcc_no(acqbib.getLccNo());
        acqbibfrm.setLibrary_id(acqbib.getId().getLibraryId());
        acqbibfrm.setSub_library_id(acqbib.getId().getSubLibraryId());
        acqbibfrm.setPublication_place(acqbib.getPublishingPlace());
        acqbibfrm.setPublisher_name(acqbib.getPublisherName());
        acqbibfrm.setPublishing_year(acqbib.getPublishingYr());
        acqbibfrm.setVolume_no(acqbib.getVolume_no());
        return mapping.findForward("update1");
    }
}
