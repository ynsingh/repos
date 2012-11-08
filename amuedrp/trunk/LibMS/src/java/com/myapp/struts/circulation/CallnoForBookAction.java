/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;

import com.myapp.struts.systemsetupDAO.BookCategoryDAO;
import java.util.List;
import com.myapp.struts.CirDAO.CirculationDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.myapp.struts.hbm.BookCategory;
import com.myapp.struts.hbm.CirMemberAccount;
import com.myapp.struts.hbm.DocumentCategory;
import com.myapp.struts.systemsetupDAO.DocumentCategoryDAO;
import com.myapp.struts.utility.DateCalculation;
import java.util.Locale;
import java.util.ResourceBundle;

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
   Locale locale=null;
   String locale1="en";
   String rtl="ltr";
   String align="left";
CirculationDAO cirdao=new CirculationDAO();
DocumentCategoryDAO doccatobj=new DocumentCategoryDAO();
BookCategoryDAO bookcatobj=new BookCategoryDAO();
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session=request.getSession();
         try{

        locale1=(String)session.getAttribute("locale");
    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
        System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align = "left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

        CallnoForBookActionForm cfbaf=(CallnoForBookActionForm)form;
        long days=0;
        call_no=cfbaf.getAccessionno();
        if(call_no==null)
        {
            call_no = request.getParameter("callno");
        }
        library_id=(String)session.getAttribute("library_id");
        sublibrary_id=(String)session.getAttribute("sublibrary_id");
        String memid= (String)session.getAttribute("memid");


         CirMemberAccount cma=cirdao.getAccount(library_id,sublibrary_id, memid);
                   if(cma!=null)
          {
          String date_reg=cma.getReqDate();
          String date_ex=cma.getExpiryDate();
          String system_date=DateCalculation.now();
          days=DateCalculation.getDifference( date_ex,system_date);
          
          }
        String document_category=cirdao.DistinctDocType(library_id, sublibrary_id, call_no);
        System.out.println(document_category);
        if(document_category==null)
        {

             //String msg1="No Books are available for Checkout";
            String msg1=resource.getString("circulation.callnoforbook.nobooksareavailable");
            request.setAttribute("msg1", msg1);
            return mapping.findForward("fail");



        }
        DocumentCategory dc=doccatobj.searchDocumentCategory(library_id, sublibrary_id, document_category);
        System.out.println(dc+"..........In CallNO");

        String status_check=dc.getIssueCheck();

        if(status_check.equalsIgnoreCase("NotIssuable")){
           // String msg1="You Cannot Checkout NonIssuable Type Titles.";
            String msg1=resource.getString("circulation.callnoforbook.youcanotckhoutnonissue");
            request.setAttribute("msg1", msg1);
            return mapping.findForward("fail");



        }


        String mem_type=cma.getMemType();
        String submem_type=cma.getSubMemberType();
        System.out.println(document_category+"   "+mem_type+submem_type);

      BookCategory bookobj=bookcatobj.searchBookTypeDetails(library_id, mem_type, submem_type, document_category);
        if(bookobj==null){

           //  String msg1="Please Configure Fine Detail For Member Type from System Setup";
             String msg1=resource.getString("circulation.callnoforbook.plzconfigfinedetail");
            request.setAttribute("msg1", msg1);
            return mapping.findForward("fail");

        }


      Integer m=bookcatobj.returnIssueLimit(library_id, document_category, mem_type,submem_type);

    
      int n=m.intValue();
      System.out.println(days+"              "+n);

      if(n!=0)
      {
          if (days < n) {

        //   String msg="The maximum issue limit days of this title exceeds member's registration expiry date.Title can not be issued";     
         String msg=resource.getString("circulation.callnoforbook.maxissuelmt");
        request.setAttribute("msg1", msg);
         return mapping.findForward("fail");
        }
        }
        if(status_check.equals("Issuable")){
        List list=cirdao.searchDoc1(library_id,sublibrary_id, call_no);
        session.setAttribute("list", list);
        return mapping.findForward("success");
        }
 return null;
    }
}
