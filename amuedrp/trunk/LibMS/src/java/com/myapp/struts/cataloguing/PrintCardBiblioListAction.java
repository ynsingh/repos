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
import com.myapp.struts.hbm.BibliographicDetails;
import java.util.List;

/**
 *
 * @author EdRP-05
 */
public class PrintCardBiblioListAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    BibliopgraphicEntryDAO dao=new BibliopgraphicEntryDAO();
    
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
             HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        List<BibliographicDetails> bb=dao.getAllTitles(library_id, sub_library_id);
        session.setAttribute("opacListb", bb);
        return mapping.findForward(SUCCESS);
    }
}
