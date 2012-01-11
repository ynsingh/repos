/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.cataloguing;

import com.myapp.struts.cataloguingDAO.MarcHibDAO;
import com.myapp.struts.hbm.Biblio;
import com.myapp.struts.hbm.BibliographicDetails;
import com.myapp.struts.hbm.BibliographicDetailsId;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author EdRP-05
 */
public class ViewMarcReposAction1 extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    MarcHibDAO dao=new MarcHibDAO();
    BibliographicDetails bib=new BibliographicDetails();
    BibliographicDetailsId bibid=new BibliographicDetailsId();
    List<BibliographicDetails> ls=new ArrayList<BibliographicDetails>();
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
        StrutsUploadForm uploadForm = (StrutsUploadForm) form;
        HttpSession session = request.getSession();
        session.removeAttribute("opacList");
        ls.removeAll(ls);
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        List<Integer> rst = dao.searchDoc2(library_id, sub_library_id);
        for(int g=0;g<rst.size();g++){
        int bib_id=rst.get(g);
        bibid.setBiblioId(bib_id);
        bibid.setLibraryId(library_id);
        bibid.setSublibraryId(sub_library_id);
        bib.setId(bibid);
        List<Biblio> rst1 = dao.searchBiblioId(library_id, sub_library_id,bib_id);
        for(int j=0;j<rst1.size();j++){
        if(rst1.get(j).getId().getMarctag().equals("245"))
        {
        bib.setTitle(rst1.get(j).get$a());
        }
        if(rst1.get(j).getId().getMarctag().equals("100"))
        {
        bib.setMainEntry(rst1.get(j).get$a());
        }
        }
        ls.add(bib);
        }
        session.setAttribute("opacList", ls);
        return mapping.findForward(SUCCESS);
    }
}
