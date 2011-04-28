/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;

import com.myapp.struts.systemsetupDAO.BookCategoryDAO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.circulationDAO.cirDAO;
import com.myapp.struts.hbm.BookCategory;
import com.myapp.struts.hbm.CirMemberAccount;
import com.myapp.struts.hbm.DocumentCategory;
import com.myapp.struts.systemsetupDAO.DocumentCategoryDAO;
import com.myapp.struts.utility.DateCalculation;

/**
 *
 * @author edrp02
 */
public class CallnoForBookAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String call_no;
    String library_id;
    String sublibrary_id;

    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        CallnoForBookActionForm cfbaf=(CallnoForBookActionForm)form;
        long days=0;
        call_no=cfbaf.getCall_no();
        HttpSession session=request.getSession();
        library_id=(String)session.getAttribute("library_id");
        sublibrary_id=(String)session.getAttribute("sublibrary_id");
        String memid= (String)session.getAttribute("memid");


         CirMemberAccount cma=cirDAO.getAccount(library_id,sublibrary_id, memid);
                   if(cma!=null)
          {
          String date_reg=cma.getReqDate();
          String date_ex=cma.getExpiryDate();
          String system_date=DateCalculation.now();
          days=DateCalculation.getDifference( date_ex,system_date);
          
          }
        String document_category=cirDAO.DistinctDocType(library_id, sublibrary_id, call_no);
        System.out.println(document_category);
        if(document_category==null)
        {

             String msg1="No Books are available for Checkout";
            request.setAttribute("msg1", msg1);
            return mapping.findForward("fail");



        }
        DocumentCategory dc=DocumentCategoryDAO.searchDocumentCategory(library_id, sublibrary_id, document_category);
        System.out.println(dc+"..........In CallNO");

        String status_check=dc.getIssueCheck();

        if(status_check.equalsIgnoreCase("NotIssuable")){
     String msg1="You Cannot Checkout NonIssuable Type Titles.";
            request.setAttribute("msg1", msg1);
            return mapping.findForward("fail");



        }


        String mem_type=cma.getMemType();
        String submem_type=cma.getSubMemberType();
        System.out.println(document_category+"   "+mem_type);

      BookCategory bookobj=BookCategoryDAO.searchBookTypeDetails(library_id, mem_type, submem_type, document_category);
        if(bookobj==null){
             String msg1="Please Configure Fine Detail For Member Type from System Setup";
            request.setAttribute("msg1", msg1);
            return mapping.findForward("fail");

        }


      Integer m=BookCategoryDAO.returnIssueLimit(library_id, document_category, mem_type,submem_type);

    
      int n=m.intValue();
      System.out.println(days+"              "+n);

      if(n!=0)
      {
          if (days < n) {
        String msg="The maximum issue limit days of this title exceeds member's registration expiry date.Title can not be issued";
        request.setAttribute("msg1", msg);
         return mapping.findForward("fail");
        }
        }
        if(status_check.equals("Issuable")){
        List list=cirDAO.searchDoc1(library_id,sublibrary_id, call_no);
        session.setAttribute("list", list);
        return mapping.findForward("success");
        }
 else{
 String msg1="The title is of Not Issuable type can not be issued";
 request.setAttribute("msg1", msg1);
  return mapping.findForward("fail");
 }      
    }
}
