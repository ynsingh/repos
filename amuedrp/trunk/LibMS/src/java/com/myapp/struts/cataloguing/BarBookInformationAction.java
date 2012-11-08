/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.cataloguing;

import com.myapp.struts.cataloguingDAO.BibliographicEntryDAO;
import com.myapp.struts.hbm.DocumentDetails;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author edrp02
 */
public class BarBookInformationAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BarBookInformationActionForm bbiaf=(BarBookInformationActionForm)form;
        BibliographicEntryDAO bibdao=new BibliographicEntryDAO();
        String accession_no=bbiaf.getAccession_no();
System.out.println("AQeeeeeeeeeeeeeeel");
        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");

        DocumentDetails dd=bibdao.searchBook(accession_no, library_id, sub_library_id) ;
        if(dd==null)
        {
          request.setAttribute("msg","Book information not exit");
          return mapping.findForward("fail");
        }
        bbiaf.setAccession_no(dd.getAccessionNo());
        bbiaf.setTitle(dd.getTitle());
        bbiaf.setCall_no(dd.getCallNo());

        return mapping.findForward(SUCCESS);
    }
}
