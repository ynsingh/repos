/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.hbm.AcqBibliography;
import com.myapp.struts.AcquisitionDao.AcquisitionDao;
/**
 *
 * @author maqbool
 */
public class AcqInitiateAcquisition1Action extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    AcquisitionDao dao=new AcquisitionDao();
    AcqBibliography acqbib=new AcqBibliography();
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
        AcqBiblioActionForm acqbiblio=(AcqBiblioActionForm)form;
        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        String id = request.getParameter("id");
        int ii = Integer.parseInt(id);
        acqbib=dao.BibliobyTitleId(ii, library_id, sub_library_id);
        System.out.println("DDDDDDDDDDDDDDDDDDD"+acqbib);
        acqbiblio.setDocument_type(acqbib.getDocType());
        acqbiblio.setTitle(acqbib.getTitle());
        acqbiblio.setAuthor(acqbib.getAuthor());
        acqbiblio.setSub_author(acqbib.getSubAuthor());
        acqbiblio.setPublisher_name(acqbib.getPublisherName());
        acqbiblio.setPublication_place(acqbib.getPublishingPlace());
        acqbiblio.setPublishing_year(acqbib.getPublishingYr());
        acqbiblio.setLcc_no(acqbib.getLccNo());
        acqbiblio.setIsbn(acqbib.getIsbn());
        acqbiblio.setEdition(acqbib.getEdition());
        acqbiblio.setTitle_id(ii);
        return mapping.findForward("success1");
    }
}
