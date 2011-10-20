/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import com.myapp.struts.AcquisitionDao.BudgetDAO;
import com.myapp.struts.AcquisitionDao.AcqApprovalDao;

import com.myapp.struts.hbm.AcqBudgetAllocation;
import com.myapp.struts.hbm.AcqBudgetAllocationId;
import com.myapp.struts.hbm.AcqBibliographyDetails;

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
public class AcqBudgetHeadAddAction1 extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
  
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        AcqBudgetActionForm lf=(AcqBudgetActionForm)form;
        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
                String sublibrary_id = (String) session.getAttribute("sublibrary_id");
        String budgethead_id=(String)lf.getBudgethead_id();
        String button=lf.getButton();
        String financial_yr=lf.getFinancial_yr();
        AcqBudgetAllocation l=new AcqBudgetAllocation();
        AcqBudgetAllocationId li=new AcqBudgetAllocationId();      
        if(button.equals("Submit")){
        l.setReqdate(lf.getDate());
        l.setOpeningBalance(lf.getOpening_balance());
        l.setRecievedAmount(lf.getRecieved_amount());
        l.setFinancialYr1(lf.getFinancial_yr());
        l.setRemarks(lf.getRemarks());
        l.setTotalAmount(lf.getTotal_amount());       
        l.setBudgetheadId(budgethead_id);
        li.setLibraryId(library_id);
        Integer transactionid=BudgetDAO.returnMaxBiblioId(library_id);
        li.setTransactionId(transactionid);
        l.setId(li);
        BudgetDAO.insert1(l);
        request.setAttribute("msg", "Data is saved successfully");
        session.removeAttribute("backlocation");
        return mapping.findForward(SUCCESS);
        }
       if(button.equals("Update")){
        l.setReqdate(lf.getDate());
        l.setOpeningBalance(lf.getOpening_balance());
        l.setRecievedAmount(lf.getRecieved_amount());
        l.setFinancialYr1(lf.getFinancial_yr());
        l.setRemarks(lf.getRemarks());
        l.setTotalAmount(lf.getTotal_amount());       
        l.setBudgetheadId(budgethead_id);      
        li.setLibraryId(library_id);
        int trid=lf.getTransaction_id();
        li.setTransactionId(trid);
        l.setId(li);
        BudgetDAO.update1(l);
        request.setAttribute("msg", "data is updated successfully");
        session.removeAttribute("backlocation");
        return mapping.findForward(SUCCESS);
        }
        if(button.equals("Delete")){

           List<AcqBibliographyDetails> acq= (List<AcqBibliographyDetails>)AcqApprovalDao.searchBudgetHead(library_id, sublibrary_id, budgethead_id);
           if(!acq.isEmpty()){
            request.setAttribute("msg1", "BudgetHead Id Used for Approval List cannot Delete");

        return mapping.findForward("fail");


           }

            BudgetDAO.delete1(library_id, lf.getTransaction_id());
        request.setAttribute("msg", "data is deleted successfully");
        session.removeAttribute("backlocation");
        return mapping.findForward(SUCCESS);
        }
        return mapping.findForward(SUCCESS);
    }
}
