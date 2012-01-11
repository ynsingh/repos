/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpSession;
import com.myapp.struts.hbm.*;
import com.myapp.struts.CirDAO.CirculationDAO;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class CirCheckinAction extends org.apache.struts.action.Action {
    
    public static final String DATE_FORMAT_NOW = "yyyy-MM-dd";
    private static final String SUCCESS = "success";
     Locale locale=null;
   String locale1="en";
   String rtl="ltr";
   String align="left";
    CirculationDAO cirdao= new CirculationDAO();
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
     CirCheckinActionForm ccaf =(CirCheckinActionForm)form;
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

     String  button= ccaf.getButton();
     String accessionno=ccaf.getTXTACCESSION();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");



     if(button.equals("Submit"))
     {
       if(!accessionno.equals(""))
       {
         DocumentDetails  documentdetail = (DocumentDetails)cirdao.searchDocumentID(library_id,sub_library_id,accessionno);
       if(documentdetail!=null){
       String document_id = String.valueOf(documentdetail.getId().getDocumentId());
      // System.out.println("Accession No="+documentdetail.getAccessionNo());
      // System.out.println(""+documentdetail.getAccessionNo());
       CirCheckout cirCheckOut = (CirCheckout)cirdao.searchCheckOutDetailsByStatus(library_id, sub_library_id, document_id,"issued");
       
       if(cirCheckOut!=null && documentdetail!=null)
       {
           String mem_id = cirCheckOut.getMemid();
       CirMemberDetail cirmem = (CirMemberDetail)cirdao.searchCirMemDetails(library_id, mem_id);
        CirMemberAccount ciracc = (CirMemberAccount)cirdao.searchCirMemAccountDetails(library_id,sub_library_id,mem_id);
           ccaf.setTXTMEMID(cirCheckOut.getMemid());
           if(cirmem!=null)
           ccaf.setTXTMEMNAME(cirmem.getFname()+" "+cirmem.getLname());
           ccaf.setTXTACCESSION(documentdetail.getAccessionNo());
           ccaf.setTXTTITLE(documentdetail.getTitle());
           ccaf.setTXTAUTHOR(documentdetail.getMainEntry());
           ccaf.setTXTCALL(documentdetail.getCallNo());
           ccaf.setTXTDUEDATE(cirCheckOut.getDueDate());
           ccaf.setTXTRETURNINGDATE(now());

            Calendar ca1 = Calendar.getInstance();
            Calendar ca2 = Calendar.getInstance();
            Calendar ca3 = Calendar.getInstance();


            /*extract month day and year from now()*/

            String date=cirCheckOut.getDueDate();
            String year=date.substring(0,date.indexOf("-"));
          

            String month=date.substring(date.indexOf("-")+1,date.indexOf("-", date.indexOf("-")+1));
         

            String day=date.substring(date.lastIndexOf("-")+1,date.length());

           
System.out.println(year+"-"+month+"-"+day);
            long day1 = ca1.get(Calendar.DATE)-Integer.parseInt(day);
            long month1 = (ca1.get(Calendar.MONTH)+1)-Integer.parseInt(month);
            long year1 = ca1.get(Calendar.YEAR)-Integer.parseInt(year);

            /**************************/
            ca2.set(Integer.parseInt(year), Integer.parseInt(month),Integer.parseInt(day));

             /*extract month day and year from now()*/
             date=now();
             year=date.substring(0,date.indexOf("-"));


            month=date.substring(date.indexOf("-")+1,date.indexOf("-", date.indexOf("-")+1));


            day=date.substring(date.lastIndexOf("-")+1,date.length());


            ca3.set(Integer.parseInt(year), Integer.parseInt(month),Integer.parseInt(day));

            long milliseconds1 = ca2.getTimeInMillis();
            long milliseconds2 = ca3.getTimeInMillis();
            long diff = milliseconds2 - milliseconds1;
            long diffSeconds = diff / 1000;
            long diffMinutes = diff / (60 * 1000);
            long diffHours = diff / (60 * 60 * 1000);
            long diffDays = diff / (24 * 60 * 60 * 1000);

            System.out.println("Days"+diffDays);
            float fine=0;
            if(!documentdetail.getBookType().equals(""))
            {
                BookCategory bookCat = (BookCategory)cirdao.searchfineDetails(library_id, documentdetail.getBookType(),ciracc.getMemType(),ciracc.getSubMemberType());
                fine = bookCat.getFine()*diffDays;
            }
               

      System.out.println(fine+"       ......................") ;

if(fine>0)
{  ccaf.setTXTFINE(String.valueOf(fine));

}
else{
 ccaf.setTXTFINE(String.valueOf("0"));
}
        if(diffDays>0)   {
           ccaf.setTXTDELAYED(String.valueOf(diffDays));
        }else{
           ccaf.setTXTDELAYED(String.valueOf("0"));
        }
           ccaf.setTXTDAMAGEDSTATUS("No");
           ccaf.setTXTLOSSSTATUS("No");

           






          
          
            return mapping.findForward("submit");
       }
       else
       {
        // request.setAttribute("msg", accessionno +" not Found");
       request.setAttribute("msg", accessionno +resource.getString("circulation.circhkinAction.notfound"));
       return mapping.findForward("notfound");
       }
       }
       else
       {
      // request.setAttribute("msg",accessionno +" not Found");
             
       request.setAttribute("msg",accessionno +resource.getString("circulation.circhkinAction.notfound"));
       return mapping.findForward("notfound");
       }
         }
       else
       {
       
       // request.setAttribute("msg", "Please enter Accession No.");    
       request.setAttribute("msg", resource.getString("circulation.circhkinAction.plzenteraccessno"));
       return mapping.findForward("notfound");
       }
     }

return null;
        
     
    }

    public static String now() {
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
    
    return sdf.format(cal.getTime());


  }

}
