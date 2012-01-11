/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;
import  com.myapp.struts.*;
import com.myapp.struts.hbm.BibliographicDetails;
import com.myapp.struts.hbm.BibliographicDetailsLang;
import com.myapp.struts.hbm.DocumentDetails;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.sql.*;
import java.util.List;

import com.myapp.struts.utility.StringRegEx;
import java.util.ArrayList;
import com.myapp.struts.opacDAO.OpacSearchDAO;
/**
 *
 * @author Faraz
 */
public class AccessionSearchAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
     OpacSearchDAO osdao= new OpacSearchDAO();
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception  {
        HttpSession session = request.getSession();
        ResultSet rs = null;
        AccessionSearchActionForm myform = (AccessionSearchActionForm)form;
        String lib_id=myform.getCMBLib();
        String accessionno = myform.getTXTKEY();
          String sublib=myform.getCMBSUBLib();
       session.removeAttribute("documentDetail1");
        session.removeAttribute("documentDetail");
     session.removeAttribute("documentDetail2");

      if(accessionno.isEmpty()){
       
  request.setAttribute("msg1", "Please Enter Accession No");
       List documentdetail  =new ArrayList();

           
           
            session.setAttribute("documentDetail1", documentdetail);
  return mapping.findForward(SUCCESS);
      }

        boolean st=StringRegEx.containsOnlyNumbers(accessionno);
            if(st==true)
            {
          


            List<DocumentDetails> documentdetail  =(List<DocumentDetails>)osdao.accessionNoSearch(accessionno, lib_id, sublib);

                      List<BibliographicDetails>    biblio=null;
if(documentdetail.isEmpty()==false){
               biblio=osdao.accessionNoBibSearch(documentdetail.get(0).getBiblioId(),documentdetail.get(0).getId().getLibraryId(),documentdetail.get(0).getId().getSublibraryId());
}
       //     System.out.println(documentdetail.size()+"  "+biblio.size()+"  "+documentdetail.get(0).getBiblioId());
            session.setAttribute("documentDetail", documentdetail);
            session.setAttribute("documentDetail1", biblio);
            return mapping.findForward(SUCCESS);
            }




boolean upperFound = false;
for (char c : accessionno.toCharArray()) {
    if (Character.isUpperCase(c)) {
        upperFound = true;
        break;
    }
}
System.out.println(upperFound);
     if(upperFound==true){
//check for Inner Join Between BibliograhicLang and document detail


       List<MixAccessionRecord> documentdetail1  =(List<MixAccessionRecord>)osdao.accessionNoLangSearch(accessionno, lib_id, sublib);



             //List<BibliographicDetailsLang>    biblio1=osdao.accessionNoBibLangSearch(documentdetail1.get(0).getBiblioId(),documentdetail1.get(0).getId().getLibraryId(),documentdetail1.get(0).getId().getSublibraryId());

            System.out.println(documentdetail1.size());

          //  session.setAttribute("documentDetail2", documentdetail1);
            session.setAttribute("documentDetail2", documentdetail1);
            return mapping.findForward(SUCCESS);
   }
     else
     {
            
            request.setAttribute("msg1", "Enter AccessionNo CharCode in Captial Only");
                  
          
            
              
     }
        return mapping.findForward(SUCCESS);
    }
}
