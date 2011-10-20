/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import com.myapp.struts.AcquisitionDao.AcqCurrencyDao;
import com.myapp.struts.hbm.BaseCurrency;
import com.myapp.struts.hbm.AcqCurrency;

import com.myapp.struts.hbm.BaseCurrencyId;
import java.util.List;
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
public class AcqCurrencyConversionAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
  
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        AcqCurrencyActionForm lf=(AcqCurrencyActionForm)form;
        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        
       // request.setAttribute("back", request.getAttribute("back"));

       // System.out.println("In Addition location process.."+request.getAttribute("back"));

        
        String button=lf.getButton();
        BaseCurrency l=new BaseCurrency();
        BaseCurrencyId li=new BaseCurrencyId();
        String base_currency_symbol = lf.getBase_currency_symbol();
        
        
        if(button.equals("Submit")){
            BaseCurrency lcheck = (BaseCurrency)AcqCurrencyDao.getCurrencyByName(library_id,base_currency_symbol);
        if(lcheck==null){
        l.setDirection(lf.getDirection());
        l.setFormalName(lf.getFormal_name());
        li.setBaseCurrencySymbol(lf.getBase_currency_symbol());
        li.setLibraryId(library_id);
        
        
        l.setId(li);
        AcqCurrencyDao.insert(l);
        request.setAttribute("msg", "Data is saved successfully");
        session.removeAttribute("backlocation");
        }else
        {
               request.setAttribute("msg1", "Duplicate Budget Head Id Name");
        }
        return mapping.findForward(SUCCESS);
        }
        if(button.equals("Update")){
            BaseCurrency lcheck = (BaseCurrency)AcqCurrencyDao.getCurrencyByName(library_id, base_currency_symbol);
        if(lcheck!=null)
            if(!lcheck.getId().getBaseCurrencySymbol().equals(base_currency_symbol))
            {
                request.setAttribute("msg1", "Duplicate Budget Head Id Name");
                return mapping.findForward(SUCCESS);
            }
        l.setDirection(lf.getDirection());
        l.setFormalName(lf.getFormal_name());
        li.setLibraryId(library_id);
        
        li.setBaseCurrencySymbol(lf.getBase_currency_symbol());
        l.setId(li);
        AcqCurrencyDao.update(l);
        request.setAttribute("msg", "Data is updated successfully");
        return mapping.findForward(SUCCESS);
        }
        if(button.equals("Delete"))
        {

        List<AcqCurrency> curr=(List<AcqCurrency>)AcqCurrencyDao.getCurrencyList(library_id, base_currency_symbol);
        if(!curr.isEmpty()){
         request.setAttribute("msg1", "Exchnage Rate Set For Base Currency Data cannot deleted");
        return mapping.findForward(SUCCESS);

        }

        AcqCurrencyDao.delete(library_id, base_currency_symbol);
        request.setAttribute("msg", "Data is deleted successfully");
        return mapping.findForward(SUCCESS);
        }
        return mapping.findForward(SUCCESS);
    }
}
