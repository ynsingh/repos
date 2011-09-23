/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;
import  com.myapp.struts.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.sql.*;
import java.util.List;
import com.myapp.struts.opacDAO.*;
import com.myapp.struts.utility.StringRegEx;
import java.util.ArrayList;

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
      
     

      if(accessionno.isEmpty()){
       
  request.setAttribute("msg1", "Please Enter Accession No");
       List documentdetail  =new ArrayList();

            System.out.println(documentdetail.size());
             session.removeAttribute("accdocumentdetail1");
            session.setAttribute("accdocumentdetail", documentdetail);
  return mapping.findForward(SUCCESS);
      }

        boolean st=StringRegEx.containsOnlyNumbers(accessionno);
            if(st==true)
            {
            List documentdetail  =(List)osdao.accessionNoSearch(accessionno, lib_id, sublib);

            System.out.println(documentdetail.size());
             session.removeAttribute("accdocumentdetail1");
            session.setAttribute("accdocumentdetail", documentdetail);
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


        List documentdetail  =(List)osdao.accessionNoLangSearch(accessionno, lib_id, sublib);

        System.out.println("Size"+documentdetail.size());
        session.removeAttribute("accdocumentdetail");
        session.setAttribute("accdocumentdetail1", documentdetail);
     }
     else
     {
            
                request.setAttribute("msg1", "Enter AccessionNo CharCode in Captial Only");

     }
        return mapping.findForward(SUCCESS);
    }
}
