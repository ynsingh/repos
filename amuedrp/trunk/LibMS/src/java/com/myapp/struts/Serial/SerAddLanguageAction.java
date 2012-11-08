/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Serial;

import com.myapp.struts.SerialDAO.SerialDAO;
import com.myapp.struts.hbm.SerLanguage;
import com.myapp.struts.hbm.SerLanguageId;
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
public class SerAddLanguageAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    SerLanguage sl=new SerLanguage();
    SerLanguageId slid=new SerLanguageId();
    SerialDAO serialdao=new SerialDAO();
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        SerAddLanguageActionForm salaf=(SerAddLanguageActionForm)form;
        String lan_id=salaf.getLan_id();
        String lan_name=salaf.getLanguage_name();
        String button=salaf.getButton();
        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        System.out.println("@@@@@@@@@@@@"+lan_id);
        if(button.equals("Submit")){
          
          slid.setLanguageId(lan_id);
          slid.setLibraryId(library_id);
          sl.setLanName(lan_name);
          sl.setId(slid);
          serialdao.insert(sl);
          request.setAttribute("msg", "Data is saved successfully");
         }

        if(button.equals("Update")){
          SerLanguage slcheck=serialdao.searchLanguage(library_id, lan_id);
          if(slcheck!=null){
//            slcheck.getId().setLibraryId(library_id);
//            slcheck.getId().setLanguageId(lan_id);
            slcheck.setLanName(lan_name);
            serialdao.update(slcheck);
            request.setAttribute("msg", "Data is updated successfully");
            return mapping.findForward(SUCCESS);
          }
        }
        
        if(button.equals("Delete")){
          SerLanguage slcheck=serialdao.searchLanguage(library_id, lan_id);
          if(slcheck!=null){
//            slcheck.getId().setLibraryId(library_id);
//            slcheck.getId().setLanguageId(lan_id);
            slcheck.setLanName(lan_name);
            serialdao.delete(slcheck);
            request.setAttribute("msg", "Record is deleted successfully");
            return mapping.findForward(SUCCESS);
          }
        }
        
        return mapping.findForward(SUCCESS);
    }
}
