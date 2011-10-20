/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import com.myapp.struts.AcquisitionDao.AcqCurrencyDao;
import com.myapp.struts.AcquisitionDao.AcqExchangeRateDao;
import com.myapp.struts.hbm.AcqCurrency;
import com.myapp.struts.hbm.BaseCurrency;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author EdRP-05
 */
public class AcqCurrencyConversion2Action extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        AcqCurrency1ActionForm lf=(AcqCurrency1ActionForm)form;
        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        //String sub_library_id = (String) session.getAttribute("sublibrary_id");
        String button=lf.getButton();
        int convrsion_id=lf.getConid();
        String scurrency=lf.getsCountry();
        String date=lf.getDate();
        
        
        AcqCurrency l=AcqExchangeRateDao.searchSourceCurrency1(library_id, scurrency,date);



        //request.setAttribute("back", request.getAttribute("back"));
       // System.out.println("In Addition location process.."+request.getAttribute("back"));
        if(button.equals("Add"))
        {
            if(l!=null){
            request.setAttribute("msg1", "Source Currency : "+scurrency+" already exists");
            return mapping.findForward("duplicate");
                       }
            else{
             
  BaseCurrency base=AcqCurrencyDao.searchCurrency1(library_id);
if(base!=null)
{
session.setAttribute("basecurrencyid", base.getId().getBaseCurrencySymbol());
session.setAttribute("basecurrencyname", base.getFormalName());
}
else{
 request.setAttribute("msg1", "Base Currency not Set");
            return mapping.findForward("duplicate");

}




            lf.setConid(convrsion_id);
            
            request.setAttribute("button", button);
            return mapping.findForward("add");
                }
        }
        if(button.equals("Update")){
        if(l==null){
            request.setAttribute("msg1", "Source Currency does not exists");
            return mapping.findForward("duplicate");
        }
 else{


             BaseCurrency base=AcqCurrencyDao.searchCurrency1(library_id);
if(base!=null)
{
session.setAttribute("basecurrencyid", base.getId().getBaseCurrencySymbol());
session.setAttribute("basecurrencyname", base.getFormalName());
}
else{
 request.setAttribute("msg1", "Base Currency not Set");
            return mapping.findForward("duplicate");

}

             lf.setConid(l.getId().getConversionId());

          
             lf.setsCountry(l.getSourceCurrency());
             lf.setSecondBox(l.getConversionRate());
          
            request.setAttribute("button", button);
            return mapping.findForward("add");
 }
        }
        if(button.equals("View")){
         if(l==null){
            request.setAttribute("msg1", "Source Currency  does not exists");
            return mapping.findForward("duplicate");
        }
 else{
             lf.setConid(l.getId().getConversionId());
            
             lf.setsCountry(l.getSourceCurrency());
            lf.setSecondBox(l.getConversionRate());
            request.setAttribute("button", button);
            return mapping.findForward("add");

 }
        }
        if(button.equals("Delete")){
                if(l==null){
            request.setAttribute("msg1", "Source Currency  does not exists");
            return mapping.findForward("duplicate");
        }
 else{
             lf.setConid(l.getId().getConversionId());
            
             lf.setsCountry(l.getSourceCurrency());
            lf.setSecondBox(l.getConversionRate());
            
            request.setAttribute("button", button);
            return mapping.findForward("add");

 }
  
        }
        
        return mapping.findForward(SUCCESS);
    }
}
