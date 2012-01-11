/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;
import com.myapp.struts.cataloguingDAO.BibliopgraphicEntryDAO;
import com.myapp.struts.hbm.BibliographicDetails;
import com.myapp.struts.hbm.BibliographicDetailsLang;
import com.myapp.struts.hbm.DocumentDetails;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.sql.*;
import javax.servlet.http.HttpSession;
import com.myapp.struts.opacDAO.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
/**
 *
 * @author Faraz
 */
public class CallNoSearchAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    OpacSearchDAO osdao= new OpacSearchDAO();
    BibliopgraphicEntryDAO bibdao=new BibliopgraphicEntryDAO();
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
        ResultSet rs = null;
        CallNoSearchActionForm myform = (CallNoSearchActionForm)form;
        String lib_id=myform.getCMBLib();
        String callno = myform.getTXTKEY();
        String sublib =myform.getCMBSUBLib();
if(sublib==null)
    sublib="All";
           if(myform.getCheckbox().equals("Checked")){
 System.out.println("Languagebbb"+myform.getLanguage());
   List<BibliographicDetailsLang> documentdetail  =(List)osdao.callNoLangSearch(callno, lib_id, sublib,myform.getLanguage().toUpperCase());
//       ArrayList<BibliographicDetails> bib1=new ArrayList<BibliographicDetails>();
//        if(!documentdetail.isEmpty())
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
   session.removeAttribute("isbndocumentdetail");
   System.out.println(documentdetail.size());
       session.setAttribute("isbndocumentdetail1", documentdetail);
 }else {
       
       List<BibliographicDetails> documentdetail  =(List)osdao.callNoSearch(callno, lib_id, sublib);
//       ArrayList<BibliographicDetails> bib1=new ArrayList<BibliographicDetails>();
//        if(!documentdetail.isEmpty())
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
        session.removeAttribute("isbndocumentdetail1");
       session.setAttribute("isbndocumentdetail", documentdetail);
 }
        return mapping.findForward(SUCCESS);
    }
}
