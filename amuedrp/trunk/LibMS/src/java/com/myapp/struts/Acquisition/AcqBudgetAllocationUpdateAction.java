/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import com.myapp.struts.AcquisitionDao.BudgetDAO;
import com.myapp.struts.hbm.AcqBudget;
import com.myapp.struts.hbm.AcqBudgetId;
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
public class AcqBudgetAllocationUpdateAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
  
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        AcqBudgetActionForm lf=(AcqBudgetActionForm)form;
        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
       // request.setAttribute("back", request.getAttribute("back"));

       // System.out.println("In Addition location process.."+request.getAttribute("back"));

        String budgethead_id=(String)lf.getBudgethead_id();
        String button=lf.getButton();
        AcqBudget l=new AcqBudget();
        AcqBudgetId li=new AcqBudgetId();
        String budegetheadname = lf.getBudget_name();
        
        
        if(button.equals("Submit")){
            AcqBudget lcheck = (AcqBudget)BudgetDAO.getLocationByName(library_id,budegetheadname);
        if(lcheck==null){
        l.setBudgetDesc(lf.getBudgethead_description());
        l.setBudgetheadName(lf.getBudget_name());
        li.setLibraryId(library_id);
        
        li.setBudgetheadId(lf.getBudgethead_id());
        l.setId(li);
        BudgetDAO.insert(l);
        request.setAttribute("msg", "Data is saved successfully");
        session.removeAttribute("backlocation");
        }else
        {
               request.setAttribute("msg1", "Duplicate Budget Head Id Name");
        }
        return mapping.findForward(SUCCESS);
        }
        if(button.equals("Update")){
            AcqBudget lcheck = (AcqBudget)BudgetDAO.getLocationByName(library_id,budegetheadname);
        if(lcheck!=null)
            if(!lcheck.getId().getBudgetheadId().equals(budgethead_id))
            {
                request.setAttribute("msg1", "Duplicate Budget Head Id Name");
                return mapping.findForward(SUCCESS);
            }
        l.setBudgetDesc(lf.getBudgethead_description());
        l.setBudgetheadName(lf.getBudget_name());
        li.setLibraryId(library_id);
        
        li.setBudgetheadId(lf.getBudgethead_id());
        l.setId(li);
        BudgetDAO.update(l);
        request.setAttribute("msg", "Data is updated successfully");
        return mapping.findForward(SUCCESS);
        }
        if(button.equals("Delete")){
        l.setBudgetDesc(lf.getBudgethead_description());
        l.setBudgetheadName(lf.getBudget_name());
        li.setLibraryId(library_id);
        
        li.setBudgetheadId(lf.getBudgethead_id());
        l.setId(li);
        BudgetDAO.delete(library_id,budgethead_id);
        request.setAttribute("msg", "Data is deleted successfully");
        return mapping.findForward(SUCCESS);
        }
        return mapping.findForward(SUCCESS);
    }
}
