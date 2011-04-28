/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetup;

import com.myapp.struts.hbm.DocumentCategory;
import com.myapp.struts.systemsetupDAO.DocumentCategoryDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Asif
 */
public class DocumentCategoryAction extends org.apache.struts.action.Action {

    private static final String SUCCESS = "success";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DocumentCategoryActionForm lf=(DocumentCategoryActionForm)form;
        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        String button=lf.getButton();
        String doc_category_id=lf.getDocument_category_id();
        DocumentCategory l=DocumentCategoryDAO.searchDocumentCategory(library_id, sub_library_id, doc_category_id);
        if(button.equals("Add"))
        {
            if(l!=null){
            request.setAttribute("msg1", "Document Category Id : "+doc_category_id+" already exists");
            return mapping.findForward("duplicate");
                       }
            else{
            lf.setDocument_category_name("");
            lf.setDocument_category_id(doc_category_id);
            request.setAttribute("button", button);
            return mapping.findForward("add");
                }
        }
        if(button.equals("Update")){
        if(l==null){
            request.setAttribute("msg1", "Document Category Id does not exists");
            return mapping.findForward("duplicate");
        }
 else{
             lf.setDocument_category_name(l.getDocumentCategoryName());
            lf.setDocument_category_id(doc_category_id);
            lf.setIssue_check(l.getIssueCheck());
            request.setAttribute("button", button);
            return mapping.findForward("add");
 }
        }
        if(button.equals("View")){
         if(l==null){
            request.setAttribute("msg1", "Document Category Id does not exists");
            return mapping.findForward("duplicate");
        }
 else{
             lf.setDocument_category_name(l.getDocumentCategoryName());
            lf.setDocument_category_id(doc_category_id);
            lf.setIssue_check(l.getIssueCheck());
            request.setAttribute("button", button);
            return mapping.findForward("add");

 }
        }
        if(button.equals("Delete")){
                if(l==null){
            request.setAttribute("msg1", "Document Category Id does not exists");
            return mapping.findForward("duplicate");
        }
 else{
             lf.setDocument_category_name(l.getDocumentCategoryName());
            lf.setDocument_category_id(doc_category_id);
            lf.setIssue_check(l.getIssueCheck());
            request.setAttribute("button", button);
            return mapping.findForward("add");

 }

        }

        return mapping.findForward(SUCCESS);
    }
}
