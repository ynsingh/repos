/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;
import com.myapp.struts.hbm.BibliographicDetails;
import com.myapp.struts.hbm.DocumentDetails;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpSession;
import com.myapp.struts.opacDAO.*;
import com.myapp.struts.cataloguingDAO.BibliopgraphicEntryDAO;
import com.myapp.struts.hbm.BibliographicDetailsLang;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Faraz
 */
public class SearchByIsbnAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    OpacSearchDAO osdao= new OpacSearchDAO();
    BibliopgraphicEntryDAO bibdao=new BibliopgraphicEntryDAO();
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
        
        SearchByIsbnActionForm myForm = (SearchByIsbnActionForm)form;
        String lib_id=myForm.getCMBLib();
        String   isbn = myForm.getTXTKEY();
        String sublib=myForm.getCMBSUBLib();

          

        if (session.getAttribute("Result")!=null) session.removeAttribute("Result");

        if(myForm.getCheckbox().equals("Checked")){
 System.out.println("Languagebbb"+myForm.getLanguage());
  List<BibliographicDetailsLang> documentdetail  =(List)osdao.isbnLangSearch(isbn, lib_id, sublib,myForm.getLanguage().toUpperCase());
       ArrayList<BibliographicDetailsLang> bib1=new ArrayList<BibliographicDetailsLang>();

// if(!documentdetail.isEmpty())
//{
//    for(int f=0;f<documentdetail.size();f++)
//    {
//    int bibiid=documentdetail.get(f).getId().getBiblioId();
//    List<DocumentDetails> ddd=bibdao.searchDoc2(bibiid, lib_id, sublib);
//    if(!ddd.isEmpty()){
//        bib1.add(documentdetail.get(f));
//    }
//    }
//}
        session.removeAttribute("documentdetail");
        System.out.println(bib1.size());
      session.setAttribute("documentdetail1", documentdetail);
 }
 else{
      System.out.println("NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNLanguagebbb");

      List<BibliographicDetails> documentdetail  =(List)osdao.isbnSearch(isbn, lib_id, sublib);
       ArrayList<BibliographicDetails> bib1=new ArrayList<BibliographicDetails>();
      
// if(!documentdetail.isEmpty())
//{
//    for(int f=0;f<documentdetail.size();f++)
//    {
//    int bibiid=documentdetail.get(f).getId().getBiblioId();
//    List<DocumentDetails> ddd=bibdao.searchDoc2(bibiid, lib_id, sublib);
//    if(!ddd.isEmpty()){
//        bib1.add(documentdetail.get(f));
//    }
//    }
//}
       session.removeAttribute("documentdetail1");
      session.setAttribute("documentdetail", documentdetail);
       
    }
         return mapping.findForward(SUCCESS);
    }
}
