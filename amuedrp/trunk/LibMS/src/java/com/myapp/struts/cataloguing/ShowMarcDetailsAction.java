/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.cataloguing;

import com.myapp.struts.cataloguingDAO.MarcHibDAO;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.hbm.BiblioTemp;
/**
 *
 * @author EdRP-05
 */
public class ShowMarcDetailsAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    MarcHibDAO dao=new MarcHibDAO();
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
        session.removeAttribute("opacList");
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        int bib_id= Integer.parseInt(request.getParameter("id"));
        List<BiblioTemp> rst1 = dao.searchReposBibliobyid(library_id, sub_library_id,bib_id);
        session.setAttribute("opacList", rst1);
        return mapping.findForward(SUCCESS);
    }
}
