/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.hbm.*;
import com.myapp.struts.opacDAO.*;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author faraz
 */
public class DocumentDetailAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
HttpSession session = request.getSession();
 session.removeAttribute("documentDetail");
       session.removeAttribute("documentDetail1");

        int document_id = Integer.parseInt(request.getParameter("doc_id"));
        String library_id = (String)request.getParameter("library_id");
        String sublibrary_id = (String)request.getParameter("sublibrary_id");
        OpacSearchDAO opacDao = new OpacSearchDAO();
        List<DocumentDetails> doc = (List<DocumentDetails>)opacDao.DocumentSearchById(document_id, library_id, sublibrary_id);
        List<BibliographicDetails> doc1 = (List<BibliographicDetails>)opacDao.DocumentSearch1(document_id, library_id, sublibrary_id);
       session.setAttribute("documentDetail", doc);
       session.setAttribute("documentDetail1", doc1);
        return mapping.findForward(SUCCESS);
    }
}
