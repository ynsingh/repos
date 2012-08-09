/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import com.myapp.struts.AcquisitionDao.AcquisitionDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import java.util.List;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author maqbool
 */
public class AcqViewSearchAction1 extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    AcquisitionDao acqdao= new AcquisitionDao();
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session=request.getSession();
        String library_id=(String)session.getAttribute("library_id");
        String Sub_library_id=(String)session.getAttribute("sublibrary_id");
        System.out.println("hhhhhhhhhhh"+library_id+Sub_library_id);
        AcqBiblioActionForm abaf=(AcqBiblioActionForm)form;
        String search_by=abaf.getSearch_by();
        String sort_by=abaf.getSort_by();
        String search_keyword=abaf.getSearch_keyword();
        List rst1=acqdao.getBiblioFromOpac(library_id, Sub_library_id, search_by, search_keyword, sort_by);
        // List rst1=acqdao.getAcqBibliography(library_id, Sub_library_id);
        System.out.println("hhhhhhhhhhh"+rst1.size());
        session.setAttribute("opacList", rst1);
        
        return mapping.findForward("search");
    }


}

