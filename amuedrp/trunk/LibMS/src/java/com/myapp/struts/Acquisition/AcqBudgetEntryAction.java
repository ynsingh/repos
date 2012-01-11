/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import com.myapp.struts.AcquisitionDao.BudgetDAO;
import com.myapp.struts.hbm.AcqBudget;
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
public class AcqBudgetEntryAction extends org.apache.struts.action.Action {
    
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
        AcqBudgetActionForm lf=(AcqBudgetActionForm)form;
        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        //String sub_library_id = (String) session.getAttribute("sublibrary_id");
        String button=lf.getButton();
        String budgethead_id=lf.getBudgethead_id();
        AcqBudget l=BudgetDAO.searchBudget(library_id, budgethead_id);
        //request.setAttribute("back", request.getAttribute("back"));
       // System.out.println("In Addition location process.."+request.getAttribute("back"));
        if(button.equals("Add"))
        {
            if(l!=null){
            request.setAttribute("msg1", "Budget Head Id : "+budgethead_id+" already exists");
            return mapping.findForward("duplicate");
                       }
            else{
            lf.setBudget_name("");
            lf.setBudgethead_id(budgethead_id);
            lf.setBudgethead_description("");
            request.setAttribute("button", button);
            return mapping.findForward("add");
                }
        }
        if(button.equals("Update")){
        if(l==null)
        {
            request.setAttribute("msg1", "Budget Head id does not exists");
            return mapping.findForward("duplicate");
        }
 else
        {
            lf.setBudget_name(l.getBudgetheadName());
            lf.setBudgethead_id(l.getId().getBudgetheadId());
            lf.setBudgethead_description(l.getBudgetDesc());
            request.setAttribute("button", button);
            return mapping.findForward("add");
         }
        }
        if(button.equals("View")){
         if(l==null){
            request.setAttribute("msg1", "Budget Head id does not exists");
            return mapping.findForward("duplicate");
        }
 else
            {
             lf.setBudget_name(l.getBudgetheadName());
            lf.setBudgethead_id(l.getId().getBudgetheadId());
            lf.setBudgethead_description(l.getBudgetDesc());
            request.setAttribute("button", button);
            return mapping.findForward("add");

            }
        }
        if(button.equals("Delete")){
                if(l==null){
            request.setAttribute("msg1", "Budget Head id does not exists");
            return mapping.findForward("duplicate");
        }
 else
        {
            lf.setBudget_name(l.getBudgetheadName());
            lf.setBudgethead_id(l.getId().getBudgetheadId());
            lf.setBudgethead_description(l.getBudgetDesc());
            request.setAttribute("button", button);
            return mapping.findForward("add");

        }
  
        }
        
        return mapping.findForward(SUCCESS);
    }
}
