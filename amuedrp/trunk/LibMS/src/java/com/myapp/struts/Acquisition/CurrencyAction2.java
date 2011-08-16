/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import com.myapp.struts.AcquisitionDao.CurrencyDao;
import com.myapp.struts.Acquisition.CurrencyForm2;
import com.myapp.struts.hbm.AcqCurrency;
import com.myapp.struts.hbm.AcqCurrencyId;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author mca53amu
 */
public class CurrencyAction2 extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private String sCountry,dCountry,firstBox,thirdBox,button;
    int ConversionId;
    float secondBox;
    AcqCurrency currency=new AcqCurrency();
        AcqCurrencyId currencyId = new AcqCurrencyId();
CurrencyDao cdao = new CurrencyDao();
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
                CurrencyForm2 cf=(CurrencyForm2)form;
        ConversionId=cf.getConid();
        sCountry=cf.getsCountry();
        dCountry=cf.getdCountry();
        //firstBox=cf.getFirstBox();
        secondBox=cf.getSecondBox();
        //thirdBox=cf.getThirdBox();
        button=cf.getButton();
        if(button.equals("Submit")){

        currencyId.setLibraryId("amu");
        currencyId.setConversionId(ConversionId);
        currency.setId(currencyId);
        currency.setTargetCurrency(dCountry);
        
        currency.setConversionRate(secondBox);
        currency.setSourceCurrency(sCountry);
        currency.setTargetCurrency(dCountry);
//        currency.setSyatemDate("");
        }

cdao.insert(currency);
System.out.println("saved");
        return mapping.findForward(SUCCESS);
    }
}
