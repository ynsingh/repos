/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Serial;

import com.myapp.struts.SerialDAO.SerialDAO;
import com.myapp.struts.hbm.SerLanguage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author edrp02
 */
public class SerLanguageAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    SerialDAO serialdao=new SerialDAO();
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        SerLanguageActionForm slaf=(SerLanguageActionForm)form;
        String lan_id=slaf.getLan_id();
        String button=slaf.getButton();
        request.setAttribute("lan_id", lan_id);
        request.setAttribute("button", button);
        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        SerLanguage sl=serialdao.searchLanguage(library_id, lan_id);

        if(button.equals("Add"))
        {
            if(sl!=null){
            request.setAttribute("msg1", "Language Id : "+lan_id+" already exists");
            return mapping.findForward("duplicate");
                       }
            else{
            request.setAttribute("button", button);
            request.setAttribute("lan_id", lan_id);
            return mapping.findForward("add");
                }
        }


        if(button.equals("Update")){
        if(sl==null)
        {
            request.setAttribute("msg1", "Language id does not exists");
            return mapping.findForward("duplicate");
        }
 else
        {
            request.setAttribute("button", button);
            request.setAttribute("lan_id", lan_id);
            request.setAttribute("lan_name", sl.getLanName());
            return mapping.findForward("add");
         }
        }
        if(button.equals("View")){
         if(sl==null){
            request.setAttribute("msg1", "Language id does not exists");
            return mapping.findForward("duplicate");
        }
 else
            {
             request.setAttribute("button", button);
             request.setAttribute("lan_id", lan_id);
             request.setAttribute("lan_name", sl.getLanName());
            return mapping.findForward("add");

            }
        }
        if(button.equals("Delete")){
                if(sl==null){
            request.setAttribute("msg1", "Language id does not exists");
            return mapping.findForward("duplicate");
        }
 else
        {
            request.setAttribute("button", button);
            request.setAttribute("lan_id", lan_id);
            request.setAttribute("lan_name", sl.getLanName());
            return mapping.findForward("add");

        }

        }


        return null;
    }
}
