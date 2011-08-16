/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetup;

import com.myapp.struts.hbm.DocumentCategory;
import com.myapp.struts.systemsetupDAO.DocumentCategoryDAO;
import java.util.Locale;
import java.util.ResourceBundle;
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
   Locale locale=null;
   String locale1="en";
   String rtl="ltr";
   String align="left";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

         HttpSession session=request.getSession();
          try{

        locale1=(String)session.getAttribute("locale");
    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
        System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align = "left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);
        DocumentCategoryActionForm lf=(DocumentCategoryActionForm)form;
       
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        String button=lf.getButton();
        String doc_category_id=lf.getDocument_category_id();
        DocumentCategory l=DocumentCategoryDAO.searchDocumentCategory(library_id, sub_library_id, doc_category_id);
        if(button.equals("Add"))
        {
            if(l!=null){

                //request.setAttribute("msg1", "Document Category Id : "+doc_category_id+" already exists");
            request.setAttribute("msg1", resource.getString("systemsetup.manage_notice.doccategoryid")+doc_category_id+resource.getString("systemsetup.manage_notice.alreadyexists"));
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

            //request.setAttribute("msg1", "Document Category Id does not exists");
            request.setAttribute("msg1", resource.getString("systemsetup.manage_notice.doccategorydoesnotexist"));
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

            //  request.setAttribute("msg1", "Document Category Id does not exists");
            request.setAttribute("msg1", resource.getString("systemsetup.manage_notice.doccategorydoesnotexist"));
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

           //  request.setAttribute("msg1", "Document Category Id does not exists");
            request.setAttribute("msg1", resource.getString("systemsetup.manage_notice.doccategorydoesnotexist"));
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
