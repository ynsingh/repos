/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.cataloguing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.cataloguingDAO.BibliopgraphicEntryDAO;
import com.myapp.struts.hbm.DocumentDetails;
import java.util.List;
import javax.servlet.http.HttpSession;
/**
 *
 * @author EdRP-05
 */
public class PrintCardItemListAction extends org.apache.struts.action.Action {
    
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
        String bib_id=request.getParameter("a");
        int bib_id1=Integer.parseInt(bib_id);
        List<DocumentDetails> bb=dao.searchDoc1(bib_id1, library_id, sub_library_id);
        session.setAttribute("opacListb", bb);
        return mapping.findForward(SUCCESS);
}
}
