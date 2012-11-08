/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import com.myapp.struts.AcquisitionDao.AcqCurrencyDao;
import com.myapp.struts.AcquisitionDao.BudgetDAO;
import com.myapp.struts.hbm.AcqBudget;
import com.myapp.struts.hbm.BaseCurrency;
import com.myapp.struts.systemsetupDAO.LocationDAO;
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
public class AcqBaseCurrencyEntryAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    AcqCurrencyDao acqcurrdao=new AcqCurrencyDao();
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        AcqCurrencyActionForm lf=(AcqCurrencyActionForm)form;
        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        
        String button=lf.getButton();
        String base_currency_symbol=lf.getBase_currency_symbol();
        
        
        BaseCurrency l=acqcurrdao.searchCurrency(library_id, base_currency_symbol);



        //request.setAttribute("back", request.getAttribute("back"));
       // System.out.println("In Addition location process.."+request.getAttribute("back"));
        if(button.equals("Add"))
        {
            if(l!=null)
            {
            request.setAttribute("msg1", "Base currency symbol : "+base_currency_symbol+" already exists");
            return mapping.findForward("duplicate");
            }
            else
            {
              BaseCurrency l1=acqcurrdao.searchCurrency1(library_id);

              if(l1!=null){
            request.setAttribute("msg1", "Base currency symbol Already Set with Id:"+l1.getId().getBaseCurrencySymbol());
            return mapping.findForward("duplicate");
                       }


            lf.setBase_currency_symbol(base_currency_symbol);
            
            request.setAttribute("button", button);
            return mapping.findForward("add");
                }
        }
        if(button.equals("Update")){
        if(l==null){
            request.setAttribute("msg1", "Base Currency Symbol does not exists");
            return mapping.findForward("duplicate");
        }

        else

        {
             lf.setBase_currency_symbol(l.getId().getBaseCurrencySymbol());
             lf.setDirection(l.getDirection());
             lf.setFormal_name(l.getFormalName());
          
            request.setAttribute("button", button);
            return mapping.findForward("add");
 }
        }
        if(button.equals("View")){
         if(l==null){
            request.setAttribute("msg1", "Base Currency Symbol does not exists");
            return mapping.findForward("duplicate");
        }
 else{
             lf.setBase_currency_symbol(l.getId().getBaseCurrencySymbol());
             lf.setDirection(l.getDirection());
             lf.setFormal_name(l.getFormalName());
           
            request.setAttribute("button", button);
            return mapping.findForward("add");

 }
        }
        if(button.equals("Delete")){
                if(l==null){
            request.setAttribute("msg1", "Base Currency Symbol does not exists");
            return mapping.findForward("duplicate");
        }
 else{
             lf.setBase_currency_symbol(l.getId().getBaseCurrencySymbol());
             lf.setDirection(l.getDirection());
             lf.setFormal_name(l.getFormalName());
           
            
            request.setAttribute("button", button);
            return mapping.findForward("add");

 }
  
        }
        
        return mapping.findForward(SUCCESS);
    }
}
