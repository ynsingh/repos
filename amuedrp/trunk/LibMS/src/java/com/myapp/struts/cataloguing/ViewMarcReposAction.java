/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.cataloguing;

import com.myapp.struts.cataloguingDAO.MarcHibDAO;
import com.myapp.struts.hbm.BiblioTemp;
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

public class ViewMarcReposAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    MarcHibDAO dao=new MarcHibDAO();
    List<BibliographicDetails> ls=new ArrayList<BibliographicDetails>();
    BibliographicDetails bib;
    BibliographicDetailsId bibid;
    List<String> str=new ArrayList<String>();
    int bib_id=0;
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
        List<Integer> rst = dao.searchReposBiblio(library_id, sub_library_id);
        for(int g=0;g<rst.size();g++){
        bib=new BibliographicDetails();
        bibid=new BibliographicDetailsId();
        bib_id=rst.get(g);
        bibid.setBiblioId(bib_id);
        bibid.setLibraryId(library_id);
        bibid.setSublibraryId(sub_library_id);
        bib.setId(bibid);
        List<BiblioTemp> rst1 = dao.searchReposBibliobyid(library_id, sub_library_id,bib_id);
        for(int j=0;j<rst1.size();j++){
        System.out.println("Marc    "+rst1.get(j).getId().getMarctag());
        if(rst1.get(j).getId().getMarctag().equals("245")==true)
        {
        System.out.println("Title");
        str.add(rst1.get(j).get$a());
      bib.setTitle(rst1.get(j).get$a());
        }
        if(rst1.get(j).getId().getMarctag().equals("100")==true)
        {
         str.add(rst1.get(j).get$a());
        bib.setMainEntry(rst1.get(j).get$a());
        }
         if(rst1.get(j).getId().getMarctag().equals("050")==true)
        {
         str.add(rst1.get(j).get$a());
        bib.setCallNo(rst1.get(j).get$a());
        }
        }     
     ls.add(bib);
      }
        session.setAttribute("opacList", ls);
        return mapping.findForward(SUCCESS);
    }
}
