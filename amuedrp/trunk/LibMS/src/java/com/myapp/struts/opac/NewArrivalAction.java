/*
 * NEW ARRIVAL SEARCH OPAC
 */

package com.myapp.struts.opac;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.util.Calendar;
import java.util.GregorianCalendar;
import com.myapp.struts.opacDAO.NewDemandDAO;
import com.myapp.struts.opacDAO.OpacSearchDAO;
import com.myapp.struts.utility.LoggerUtils;
import java.util.List;
import org.apache.log4j.Logger;
public class NewArrivalAction extends org.apache.struts.action.Action
{
    
    boolean result;
    private static Logger log4j =LoggerUtils.getLogger();
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception 
    {
        try
        {
               int pageno=Integer.parseInt((String)(request.getParameter("page")==null || request.getParameter("page").isEmpty() ?"0":request.getParameter("page")));
               String cat,sublib;
               String date,date1;
               Integer p;
               int mm,month,year,day;
               HttpSession session=request.getSession();
               NewArrivalActionForm myForm = (NewArrivalActionForm)form;
                session.removeAttribute("arrivalRs");
                   session.removeAttribute("simple_search_nor");

               String lib_id=myForm.getCMBLib();
               sublib=myForm.getCMBSUBLib();
                cat=myForm.getR();
                 p=Integer.parseInt(myForm.getCMBPERIOD());

               if(lib_id!=null)
                    session.setAttribute("newlib_id", lib_id);
                if(sublib!=null)
                    session.setAttribute("newsublib", sublib);
                if(cat!=null)
                    session.setAttribute("newcat", cat);
                 if(p!=null)
                    session.setAttribute("newperiod", p);

                 mm=p;

               if(lib_id==null)
                    lib_id=(String)session.getAttribute("newlib_id");
                if(sublib==null)
                    sublib=(String)session.getAttribute("newsublib");
                if(cat==null)
                    cat=(String)session.getAttribute("newcat");


                Calendar cal = new GregorianCalendar();
                month = cal.get(Calendar.MONTH);
                month=month+1;
                year = cal.get(Calendar.YEAR);
                day = cal.get(Calendar.DAY_OF_MONTH);
                date=year+"-"+month+"-"+day;
                
               

                if(day<10 && month<10)
                {
                    date=year+"-0"+month+"-0"+day;
                }
                else
                {
                    if(month<10)
                    {
                        date=year+"-0"+month+"-"+day;
                    }
                    if(day<10)
                    {
                        date=year+"-"+month+"-0"+day;
                    }
                }
               
               
                if(month <= mm)
                {
                    month=month+12;
                    year=year-1;
                }
                month=month-mm;
               date1=year+"-"+month+"-"+day;
                if(day<10 && month<10)
                {
                   date1=year+"-0"+month+"-0"+day;
                }
                else
                {
                    if(month<10){date1=year+"-0"+month+"-"+day;}
                    if(day<10){date1=year+"-"+month+"-0"+day;}
                }
                List  newarrival=NewDemandDAO.NewArrival(lib_id, sublib,date1, date,cat,pageno);
              
                        session.setAttribute("newarrival",newarrival) ;
                        int size=OpacSearchDAO.getSize();
                  session.setAttribute("simple_search_nor",size);

              

        }
        catch(Exception e)
        {
        log4j.error(e);
        }

         return mapping.findForward("success");
        
    }
}
