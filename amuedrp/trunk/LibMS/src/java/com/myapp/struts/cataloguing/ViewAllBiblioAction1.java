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
import java.util.List;
import javax.servlet.http.HttpSession;
public class ViewAllBiblioAction1 extends org.apache.struts.action.Action {
    
    BibliopgraphicEntryDAO dao=new BibliopgraphicEntryDAO();

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HttpSession session = request.getSession();
        session.removeAttribute("AccList");
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        BibliographicDetailEntryActionForm1 institute = (BibliographicDetailEntryActionForm1) form;
        String search_by = institute.getSearch_by();
        String sort_by = institute.getSort_by();
        String search_keyword = institute.getSearch_keyword();
           int pageno=Integer.parseInt((String)(request.getParameter("page")==null || request.getParameter("page").isEmpty() ?"0":request.getParameter("page")));
       System.out.println(pageno);
           List rst = dao.getAccession(library_id, sub_library_id,search_by,search_keyword, sort_by,pageno);
        session.setAttribute("AccList", rst);
        return mapping.findForward("all");
    }
}
