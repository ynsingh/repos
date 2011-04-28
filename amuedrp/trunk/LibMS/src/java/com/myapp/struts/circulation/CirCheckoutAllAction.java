/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;

import com.myapp.struts.CirculationDAO.CirculationDAO;
import com.myapp.struts.circulationDAO.cirDAO;
import com.myapp.struts.hbm.*;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author edrp02
 */
public class CirCheckoutAllAction extends org.apache.struts.action.Action {
    
    String library_id;
    String sublibrary_id;
    String memid;
    String status="issued";
    int document_id;
    int total_issued_book;
    int current_issued_book;
    String title;
    String author_name;
    String accession_no;
    String issue_date;
    String due_date;
    List control_list;
    List control_list1;
    int max_id;
    int max_id1;
    int checkout_id;
    int transaction_id;
    boolean result;
   
    CirCheckout cc=new CirCheckout();
    CirCheckoutId ccid=new CirCheckoutId();
    CirTransactionHistory cth=new CirTransactionHistory();
    CirTransactionHistoryId cthid=new CirTransactionHistoryId();


    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        CirCheckoutAllActionForm ccaaf=(CirCheckoutAllActionForm)form;
        title=ccaaf.getTitle();
        author_name=ccaaf.getAuthor_name();
        accession_no=ccaaf.getAccession_no();
        issue_date=ccaaf.getIssue_date();
        due_date=ccaaf.getDue_date();
        System.out.println(issue_date+"   "+due_date);

        document_id= Integer.parseInt(ccaaf.getDocument_id());

        System.out.println(document_id);

        HttpSession session=request.getSession();
        library_id=(String)session.getAttribute("library_id");
        sublibrary_id=(String)session.getAttribute("sublibrary_id");
        memid=ccaaf.getMemid();
        CirCheckout circheck = (CirCheckout)CirculationDAO.searchCheckOutDetails1(library_id, sublibrary_id, String.valueOf(document_id),"issued");
        if(circheck==null)
        {
        control_list=cirDAO.getMaxChkoutId(library_id,sublibrary_id);
               if(control_list.get(0)!=null)
               {
                max_id=(Integer) control_list.get(0) ;
                max_id++;
               }
               else
               {
                max_id=1;
               }
        checkout_id=max_id;

        control_list1=cirDAO.getMaxTransId(library_id);
               if(control_list1.get(0)!=null)
               {
                max_id1=(Integer) control_list1.get(0) ;
                max_id1++;
               }
               else
               {
                max_id1=1;
               }
        transaction_id=max_id1;

System.out.println(checkout_id+"..............................");
        ccid.setCheckoutId(checkout_id);
        ccid.setLibraryId(library_id);
        ccid.setSublibraryId(sublibrary_id);
        cc.setId(ccid);
        cc.setIssueDate(issue_date);
        cc.setDueDate(due_date);
        cc.setMemid(memid);
        cc.setDocumentId(String.valueOf(document_id));
        cc.setStatus(status);


        cthid.setLibraryId(library_id);
        cthid.setSublibraryId(sublibrary_id);
        cthid.setTransactionId(transaction_id);
        cth.setId(cthid);
        cth.setMemid(memid);
        cth.setTransactionDate(issue_date);
        cth.setDocumentId(String.valueOf(document_id));
        cth.setStatus(status);
        cth.setCheckoutId(checkout_id);
        cth.setCheckoutDate(issue_date);


        DocumentDetails docdetail=cirDAO.getDocument(library_id, sublibrary_id, document_id);
        docdetail.setStatus(status);


        CirMemberAccount cirmemac=cirDAO.getCirMem(library_id, sublibrary_id, memid);



        total_issued_book=Integer.parseInt(cirmemac.getTotalIssuedBook())+1;
        current_issued_book=Integer.parseInt(cirmemac.getCurrentIssuedBook())+1;

        System.out.println(total_issued_book+ "   "+current_issued_book);

        cirmemac.setTotalIssuedBook( String.valueOf(total_issued_book ));
        cirmemac.setCurrentIssuedBook(String.valueOf(current_issued_book));
        cirmemac.setLastchkoutdate(issue_date);

        result=cirDAO.update(cc, cth, cirmemac, docdetail) ;
        if(result==true)
        {

           CirOpacRequest ciropac=(CirOpacRequest)CirculationDAO.searchCirOpacRequest(library_id, sublibrary_id,String.valueOf(document_id), accession_no);

           if(ciropac!=null)
           {
               ciropac.setStatus("processed");
               result=CirculationDAO.updateCheckOut(ciropac);
               if(result==true)
               {
                   
              request.setAttribute("msg", "Item is checked out successfully.");
              request.setAttribute("memid", memid);
              return mapping.findForward("success");
               }
                else
                  {
              request.setAttribute("msg1", "Item not checked out due to some reason.");
              request.setAttribute("memid", memid);
             return mapping.findForward("success");
                     }

           }

           CirOpacRequest ciropac1=(CirOpacRequest)CirculationDAO.searchCirOpacRequest(library_id, sublibrary_id,String.valueOf(document_id), accession_no);

           if(ciropac1!=null)
           {
               ciropac1.setStatus("processed");
               result=CirculationDAO.updateCheckOut(ciropac1);
               


           }
             request.setAttribute("msg", "Item is checked out successfully");
              request.setAttribute("memid", memid);
              return mapping.findForward("success");


              
        }
        else
        {
              request.setAttribute("msg1", "Item not checked out due to some reason.");
              request.setAttribute("memid", memid);
             return mapping.findForward("success");
        }
        }else
           {
            CirOpacRequest ciropac=(CirOpacRequest)CirculationDAO.searchCirOpacRequest1(library_id, sublibrary_id,String.valueOf(document_id), accession_no);
System.out.println("CirOpac="+ciropac+" Lib="+library_id+" sublib="+sublibrary_id+" accession="+accession_no+ " docId="+ document_id);
           if(ciropac!=null)
           {
               ciropac.setStatus("Rejected");
               result=CirculationDAO.updateCheckOut(ciropac);
           }
            request.setAttribute("msg1", "Item already checked out.");
              request.setAttribute("memid", memid);
             return mapping.findForward("success");
        }
    }
}
