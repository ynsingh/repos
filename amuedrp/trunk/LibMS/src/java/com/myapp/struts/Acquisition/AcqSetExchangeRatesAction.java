/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import com.myapp.struts.AcquisitionDao.AcqExchangeRateDao;
import com.myapp.struts.hbm.AcqCurrency;
import com.myapp.struts.hbm.AcqCurrencyId;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.utility.DateCalculation;

/**
 *
 * @author EdRP-05
 */
public class AcqSetExchangeRatesAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private String date;
    AcqExchangeRateDao acqexchngdao=new AcqExchangeRateDao();
  
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        AcqCurrency1ActionForm lf=(AcqCurrency1ActionForm)form;
        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String scurrency=lf.getsCountry();
        date=DateCalculation.now();
      
        
        String button=lf.getButton();
        AcqCurrency l=new AcqCurrency();
        AcqCurrencyId li=new AcqCurrencyId();
        int conversion_id = lf.getConid();
        
        System.out.println(conversion_id);
        if(button.equals("Submit")){
            //AcqCurrency lcheck = (AcqCurrency)AcqExchangeRateDao.getCurrencyBySourceName(library_id, conversion_id);
       // if(lcheck==null){
              conversion_id=acqexchngdao.returnMaxConversionId(library_id);
        l.setConversionRate(lf.getSecondBox());
        l.setSourceCurrency(lf.getsCountry());
        l.setTargetCurrency(lf.getdCountry());
        li.setConversionId(conversion_id);
        l.setSystemDate(date);
        li.setLibraryId(library_id);
        
        
        l.setId(li);
        acqexchngdao.insert(l);
        request.setAttribute("msg", "Data is saved successfully");
        session.removeAttribute("backlocation");
       // }else
       
        return mapping.findForward(SUCCESS);
        }
        if(button.equals("Update")){
          //  AcqCurrency lcheck = (AcqCurrency)AcqExchangeRateDao.getCurrencyBySourceName1(library_id, scurrency);
     /*   if(lcheck!=null)
            if(!lcheck.getId().getConversionId().equals(conversion_id))
            {
                request.setAttribute("msg1", "Duplicate Budget Head Id Name");
                return mapping.findForward(SUCCESS);
            }*/
            
        l.setConversionRate(lf.getSecondBox());
        l.setSourceCurrency(lf.getsCountry());
        l.setTargetCurrency(lf.getdCountry());
        li.setLibraryId(library_id);
        l.setSystemDate(date);
        li.setConversionId(lf.getConid());
        l.setId(li);
        acqexchngdao.update(l);
        request.setAttribute("msg", "Data is updated successfully");
        return mapping.findForward(SUCCESS);
        }
        if(button.equals("Delete")){

       System.out.println(lf.getConid());
        acqexchngdao.delete(library_id, lf.getConid());
        request.setAttribute("msg", "Data is deleted successfully");
        return mapping.findForward(SUCCESS);
        }
        return mapping.findForward(SUCCESS);
    }
}
