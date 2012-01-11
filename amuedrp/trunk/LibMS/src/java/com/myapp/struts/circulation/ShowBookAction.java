/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;

import com.myapp.struts.hbm.*;
import com.myapp.struts.BookCategoryDAO.BookCategoryDAO;
import com.myapp.struts.CirDAO.CirculationDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.ResourceBundle;



/**
 *
 * @author edrp02
 */
public class ShowBookAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String accession_no;
    String memid;
    String library_id;
    String sublibrary_id;
    String memtype;
    int limit;
    String date;
    String sub_memtype;
    String book_type;
     Locale locale=null;
   String locale1="en";
   String rtl="ltr";
   String align="left";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
Calendar cal=Calendar.getInstance();

SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

date=sdf.format(cal.getTime());
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
         library_id=(String)session.getAttribute("library_id");
         sublibrary_id=(String)session.getAttribute("sublibrary_id");
         memid=request.getParameter("memId");
         accession_no=request.getParameter("id");
         if(memid==null)
         {
       memid=(String)session.getAttribute("memid");


         }

         request.setAttribute("memid",memid);
         if(accession_no!=null)
         request.setAttribute("back","back");
         else
             accession_no=request.getParameter("id1");

System.out.println(memid+"   "+accession_no);
         DocumentDetails docdetail=CirculationDAO.getBook(library_id,sublibrary_id, accession_no);
         if(docdetail!=null)
         {
         DocumentDetails docdetail1=CirculationDAO.getBookStatus(library_id,sublibrary_id, accession_no);

                 if(docdetail1!=null){
                    CirOpacRequest opacReq = (CirOpacRequest)CirculationDAO.searchCirOpacRequestByMemId(library_id, sublibrary_id, memid, accession_no);
                if(opacReq!=null)
                {
                    opacReq.setStatus("Rejected");
                   boolean result= CirculationDAO.updateCheckOut(opacReq);
                }
                 
                // request.setAttribute("msg1","Item already checked out, Request for Checkout is rejected.");   
                request.setAttribute("msg1",resource.getString("circulation.showbook.itemalreckhout"));
               return  mapping.findForward("failure");
                 }


             book_type=docdetail.getBookType();

             CirMemberAccount cmd=(CirMemberAccount)CirculationDAO.getAccount(library_id,sublibrary_id, memid);

             if(cmd!=null)
             {
             memtype=cmd.getMemType();
             sub_memtype=cmd.getSubMemberType();
             System.out.println(memtype+"         "+sub_memtype+"..........");





             BookCategory membook=(BookCategory)BookCategoryDAO.getMemid(library_id, memtype, sub_memtype,book_type);
             if(membook!=null){
             limit=membook.getIssueDaysLimit();}
             else{
                  //request.setAttribute("msg1","You Have to configure Fine Details for this service.");
                 request.setAttribute("msg1",resource.getString("circulation.showbook.youhavetoconfig"));
               return  mapping.findForward("failure");
             }


             cal.add(Calendar.DATE, limit);

             
               request.setAttribute("docdetail", docdetail);
               
               System.out.println(limit+"      ..........."+sdf.format(cal.getTime()));
             request.setAttribute("limit",sdf.format(cal.getTime()));
               return  mapping.findForward("success");
             }
         }else{
                CirOpacRequest opacReq = (CirOpacRequest)CirculationDAO.searchCirOpacRequestByMemId(library_id, sublibrary_id, memid, accession_no);
                if(opacReq!=null)
                {
                    opacReq.setStatus("Rejected");
                   boolean result= CirculationDAO.updateCheckOut(opacReq);
                }

               // request.setAttribute("msg1","Request for checkout is cancelled due to unavailabilty of document.");
                request.setAttribute("msg1",resource.getString("circulation.showbook.reqforchkoutiscancel"));
               return  mapping.findForward("failure");
         }

       return null;
    }
}
