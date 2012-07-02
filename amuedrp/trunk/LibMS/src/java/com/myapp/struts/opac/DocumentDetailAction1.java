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

public class DocumentDetailAction1 extends org.apache.struts.action.Action {

    
    private static final String SUCCESS = "success";

   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HttpSession session = request.getSession();
        session.removeAttribute("head");
        session.removeAttribute("documentDetail");
        session.removeAttribute("documentDetail1");
        session.removeAttribute("MLIdocumentDetail");
        int document_id = Integer.parseInt(request.getParameter("doc_id"));
        String library_id = (String)request.getParameter("library_id");
        String sublibrary_id = (String)request.getParameter("sublibrary_id");
        String h=(String)request.getParameter("h");
        if(h!=null)
        {
            session.setAttribute("head", "yes");
            System.out.println("I am here.................");
        }
        library_id=library_id.toLowerCase();
        sublibrary_id=sublibrary_id.toLowerCase();
        OpacSearchDAO opacDao = new OpacSearchDAO();
        List<DocumentDetails> doc = (List<DocumentDetails>)opacDao.DocumentSearchById(document_id, library_id, sublibrary_id);
        List<BibliographicDetailsLang> doc1 = (List<BibliographicDetailsLang>)opacDao.DocumentSearch(doc.get(0).getBiblioId(), library_id, sublibrary_id);
        System.out.println(doc1+""+doc.get(0).getBiblioId()+" "+document_id+library_id+sublibrary_id);
        session.setAttribute("documentDetail", doc);
        session.setAttribute("MLIdocumentDetail", doc1);
        System.out.println(doc1+"MLI");
        return mapping.findForward(SUCCESS);
    }
}
