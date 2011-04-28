/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;
import  com.myapp.struts.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.sql.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import com.myapp.struts.opacDAO.NewDemandDAO;
import java.util.List;
/**
 *
 * @author Faraz
 */
public class NewArrivalAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    boolean result;
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
    
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
                String loc,callno,cat,book,journal,other,sublib;
                String db,date,date1,sort,field,query="",query1="";
                int mm,month,year,day;
               HttpSession session=request.getSession();
               NewArrivalActionForm myForm = (NewArrivalActionForm)form;
                 String lib_id=myForm.getCMBLib();
                 sublib=myForm.getCMBSUBLib();
                Calendar cal = new GregorianCalendar();
     month = cal.get(Calendar.MONTH);
     month=month+1;
     year = cal.get(Calendar.YEAR);
     day = cal.get(Calendar.DAY_OF_MONTH);
     date=year+"-"+month+"-"+day;

if(session.getAttribute("arrivalRs")!=null) session.removeAttribute("arrivalRs");

    if(day<10 && month<10){date=year+"-0"+month+"-0"+day;}
     else {
         if(month<10){date=year+"-0"+month+"-"+day;}
         if(day<10){date=year+"-"+month+"-0"+day;}
          }

      cat=myForm.getR();

  mm=Integer.parseInt(myForm.getCMBPERIOD());

  if(month <= mm){
       month=month+12;
       year=year-1;
           }
   month=month-mm;

   date1=year+"-"+month+"-"+day;

   if(day<10 && month<10){date1=year+"-0"+month+"-0"+day;}
     else {
         if(month<10){date1=year+"-0"+month+"-"+day;}
         if(day<10){date1=year+"-"+month+"-0"+day;}
          }
  System.out.println("date"+date+"@@@@##$$%%"+"date1"+date1);
  List  newarrival=NewDemandDAO.NewArrival(lib_id, sublib,date1, date,cat);
  System.out.println("@@@@@@@@@@@@@@@########%%%%%%%%%$$$$"+newarrival.size()+lib_id+sublib+cat);
  if(!newarrival.isEmpty())
  {
    session.setAttribute("newarrival",newarrival) ;
    
    return mapping.findForward("success");

  }
  else
  {
    request.setAttribute("msg", "Record Not found");
    return mapping.findForward("failure");
  }


  
        
    }
}
