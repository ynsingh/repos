/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetup;

import com.myapp.struts.CirculationDAO.CirculationDAO;
import com.myapp.struts.hbm.*;

import com.myapp.struts.systemsetupDAO.*;
import com.myapp.struts.hbm.*;
import java.util.List;
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
public class DocumentCategoryAddAction extends org.apache.struts.action.Action {
    
    private static final String SUCCESS = "success";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DocumentCategoryActionForm lf=(DocumentCategoryActionForm)form;
        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        String doc_category_id=(String)lf.getDocument_category_id();
        String docname=lf.getDocument_category_name();
        String button=lf.getButton();
        DocumentCategory l=new DocumentCategory();
        DocumentCategoryId li=new DocumentCategoryId();
        if(button.equals("Submit"))
        {
        DocumentCategory dcheck=DocumentCategoryDAO.searchDocumentCategoryByName(library_id,sub_library_id,docname);
        if(dcheck==null){
        l.setDocumentCategoryName(docname);
        l.setIssueCheck(lf.getIssue_check());
        li.setLibraryId(library_id);
        li.setSublibraryId(sub_library_id);
        li.setDocumentCategoryId(doc_category_id);
        l.setId(li);
        DocumentCategoryDAO.insert(l);
        request.setAttribute("msg", "Data is saved successfully");
        return mapping.findForward(SUCCESS);
        }else
        {
            request.setAttribute("msg1", "Duplicate Document Category Name");
        return mapping.findForward(SUCCESS);
        }
        }
        if(button.equals("Update"))
        {

            if(lf.getIssue_check().equals("NotIssuable"))
            {
            List<MixDocumentType> checkout=CirculationDAO.getDocument_Cat_Details(library_id, sub_library_id,lf.getDocument_category_id());
            if(!checkout.isEmpty())
            {
                System.out.println("Not Working..................");
             request.setAttribute("msg1", "Document type Already Issused Cannot Update");
                return mapping.findForward(SUCCESS);

            }
            }













        l.setDocumentCategoryName(lf.getDocument_category_name());
        l.setIssueCheck(lf.getIssue_check());
        li.setLibraryId(library_id);
        li.setSublibraryId(sub_library_id);
        li.setDocumentCategoryId(doc_category_id);
        l.setId(li);
        DocumentCategoryDAO.update(l);
        request.setAttribute("msg", "Data is updated successfully");
        return mapping.findForward(SUCCESS);
        }
        if(button.equals("Delete")){

        List<DocumentDetails> doc=(List<DocumentDetails>)DocumentCategoryDAO.searchDocumentDetailByDocumentCategory(library_id, sub_library_id, doc_category_id);
        if(!doc.isEmpty()){
         System.out.println("Not Working..................");
             request.setAttribute("msg1", "Document type Already Used For Accessioning cannot Deleted");
                return mapping.findForward(SUCCESS);


        }

            List<MixDocumentType> checkout=CirculationDAO.getDocument_Cat_Details(library_id, sub_library_id,lf.getDocument_category_id());
            if(!checkout.isEmpty())
            {
                System.out.println("Not Working..................");
             request.setAttribute("msg1", "Document type Already Issused Cannot Delete");
                return mapping.findForward(SUCCESS);

            }












        l.setDocumentCategoryName(lf.getDocument_category_name());
        l.setIssueCheck(lf.getIssue_check());
        li.setLibraryId(library_id);
        li.setSublibraryId(sub_library_id);
        li.setDocumentCategoryId(doc_category_id);
        l.setId(li);
        DocumentCategoryDAO.delete(library_id, sub_library_id,doc_category_id);
        request.setAttribute("msg", "Data is deleted successfully");
        return mapping.findForward(SUCCESS);
        }
        return mapping.findForward(SUCCESS);
    }
}
