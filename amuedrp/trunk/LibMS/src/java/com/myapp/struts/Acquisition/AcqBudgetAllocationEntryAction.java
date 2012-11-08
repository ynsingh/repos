/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import com.myapp.struts.AcquisitionDao.BudgetDAO;
import com.myapp.struts.hbm.AcqBudgetAllocation;
import com.myapp.struts.hbm.AcqBudget;
import com.myapp.struts.hbm.AcqBudgetTransaction;

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
public class AcqBudgetAllocationEntryAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private float bal;
    BudgetDAO bugdao=new BudgetDAO();
  
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        AcqBudgetActionForm lf=(AcqBudgetActionForm)form;
        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String fyear=lf.getFinancial_yr();
        String button=lf.getButton();
        String budgethead_id=lf.getBudgethead_id();
        String budgetheadname;

        AcqBudget budget=bugdao.getBudgetid(library_id,budgethead_id);

        budgetheadname=budget.getBudgetheadName();

        AcqBudgetAllocation l=bugdao.searchBudgetAllocation(library_id, budgethead_id, fyear);
        System.out.println("Button Value="+button+budgethead_id+fyear);
        
        if(button.equals("Add"))
        {
                   if(l!=null){
            request.setAttribute("msg1", "Budget Head Allocation for This Fiscal already exists");
            return mapping.findForward("duplicate");
                    }
                   else{


            lf.setBudget_name(budgetheadname);
            lf.setBudgethead_id(budgethead_id);
         
            lf.setFinancial_yr(lf.getFinancial_yr());
               l=bugdao.searchBudgetAllocation(library_id,budgethead_id,String.valueOf(Integer.parseInt(fyear)-1));
           
            if(l!=null)
                lf.setOpening_balance(l.getTotalAmount());
            else
                lf.setOpening_balance("0");






            lf.setEstimited_exp(String.valueOf(0));

            lf.setNet_bal(String.valueOf(0));





            request.setAttribute("button", button);
            return mapping.findForward("add");
                   }
        }
        if(button.equals("Update")){
        if(l==null){
            request.setAttribute("msg1", "Budget Head Allocation for This Fiscal does not exists");
            return mapping.findForward("duplicate");
        }
 else{
           lf.setBudget_name(budgetheadname);
            lf.setBudgethead_id(l.getBudgetheadId());
           lf.setDate(l.getReqdate());
           lf.setFinancial_yr(l.getFinancialYr1());
           lf.setOpening_balance(l.getOpeningBalance());
           lf.setRecieved_amount(l.getRecievedAmount());
           lf.setRemarks(l.getRemarks());
           lf.setTotal_amount(l.getTotalAmount());
           lf.setTransaction_id(l.getId().getTransactionId());

            String acq=bugdao.getBudgetTransaction(library_id,budgethead_id);
     

if(acq!=null)
{      lf.setEstimited_exp(acq);
bal=Float.parseFloat(l.getTotalAmount())-Float.parseFloat(acq);
System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"+bal);
lf.setNet_bal(String.valueOf(bal));
}
else
{    lf.setEstimited_exp("0");
      lf.setNet_bal(l.getTotalAmount());

}
           

            request.setAttribute("button", button);
            return mapping.findForward("add");
 }
        }
        if(button.equals("View")){
         if(l==null){
            request.setAttribute("msg1", "Budget Head Allocation for This Fiscal does not exists");
            return mapping.findForward("duplicate");
        }
 else{
             lf.setBudget_name(budgetheadname);
            lf.setBudgethead_id(l.getBudgetheadId());
           lf.setDate(l.getReqdate());
           lf.setFinancial_yr(l.getFinancialYr1());
           lf.setOpening_balance(l.getOpeningBalance());
           lf.setRecieved_amount(l.getRecievedAmount());
           lf.setRemarks(l.getRemarks());
           lf.setTotal_amount(l.getTotalAmount());
        lf.setTransaction_id(l.getId().getTransactionId());

        String acq=bugdao.getBudgetTransaction(library_id,budgethead_id);


if(acq!=null)
{      lf.setEstimited_exp(acq);
bal=Float.parseFloat(l.getTotalAmount())-Float.parseFloat(acq);
 lf.setNet_bal(String.valueOf(bal));
}
else
{    lf.setEstimited_exp("0");
      lf.setNet_bal(l.getTotalAmount());

}
       

            request.setAttribute("button", button);
            return mapping.findForward("add");

 }
        }
        if(button.equals("Delete")){
                if(l==null){
            request.setAttribute("msg1", "Budget Head Allocation for This Fiscal does not exists");
            return mapping.findForward("duplicate");
        }
 else{
         lf.setBudget_name(budgetheadname);
            lf.setBudgethead_id(l.getBudgetheadId());
           lf.setDate(l.getReqdate());
           lf.setFinancial_yr(l.getFinancialYr1());
           lf.setOpening_balance(l.getOpeningBalance());
           lf.setRecieved_amount(l.getRecievedAmount());
           lf.setRemarks(l.getRemarks());
           lf.setTotal_amount(l.getTotalAmount());
lf.setTransaction_id(l.getId().getTransactionId());

 String acq=bugdao.getBudgetTransaction(library_id,budgethead_id);
           



if(acq!=null)
{      lf.setEstimited_exp(acq);
bal=Float.parseFloat(l.getTotalAmount())-Float.parseFloat(acq);
 lf.setNet_bal(String.valueOf(bal));
}
else
{    lf.setEstimited_exp("0");
      lf.setNet_bal(l.getTotalAmount());

}
       

            request.setAttribute("button", button);
            return mapping.findForward("add");
 }  
        }        
        return mapping.findForward(SUCCESS);
    }
}
