/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Serial;

import com.myapp.struts.SerialDAO.SerialDAO;
import com.myapp.struts.hbm.SerPublisher;
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
public class SerPublisherAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
       SerPublisherActionForm spaf=(SerPublisherActionForm)form;
       String pub_id=spaf.getPub_id();
       String button=spaf.getButton();
       HttpSession session = request.getSession();
       String library_id = (String) session.getAttribute("library_id");
       String sublibrary_id = (String) session.getAttribute("sublibrary_id");
       SerPublisher sp=SerialDAO.searchSerialPubisher(library_id, sublibrary_id, pub_id);

        if(button.equals("Add"))
        {
            if(sp!=null){
            request.setAttribute("msg1", "Language Id : "+pub_id+" already exists");
            return mapping.findForward("duplicate");
                       }
            else{
            request.setAttribute("button", button);
            request.setAttribute("pub_id", pub_id);
            return mapping.findForward("add");
                }
        }

       if(button.equals("Update")){
        if(sp==null)
        {
            request.setAttribute("msg1", "Publisher id does not exists");
            return mapping.findForward("duplicate");
        }
 else
        {
            request.setAttribute("button", button);
            request.setAttribute("pub_id", pub_id);
            spaf.setPub_id(sp.getId().getPubId());
            spaf.setPub_name(sp.getPubName());
            spaf.setPub_add(sp.getPubAdd());
            spaf.setCity(sp.getCity());
            spaf.setCountry(sp.getCountry());
            spaf.setEmail(sp.getEmail());
            spaf.setFax(sp.getFax());
            spaf.setPhone(sp.getPubPhone());
            spaf.setState(sp.getState());
            spaf.setPub_url(sp.getPubUrl());
            return mapping.findForward("add");
         }
        }
        if(button.equals("View")){
         if(sp==null){
            request.setAttribute("msg1", "Publisher id does not exists");
            return mapping.findForward("duplicate");
        }
 else
            {
             request.setAttribute("button", button);
             request.setAttribute("pub_id", pub_id);
             spaf.setPub_id(sp.getId().getPubId());
             spaf.setPub_name(sp.getPubName());
             spaf.setPub_add(sp.getPubAdd());
             spaf.setCity(sp.getCity());
             spaf.setCountry(sp.getCountry());
             spaf.setEmail(sp.getEmail());
             spaf.setFax(sp.getFax());
             spaf.setPhone(sp.getPubPhone());
             spaf.setState(sp.getState());
             spaf.setPub_url(sp.getPubUrl());
             return mapping.findForward("add");

            }
        }
        if(button.equals("Delete")){
                if(sp==null){
            request.setAttribute("msg1", "Publisher id does not exists");
            return mapping.findForward("duplicate");
        }
 else
        {
            request.setAttribute("button", button);
            request.setAttribute("pub_id", pub_id);
            spaf.setPub_id(sp.getId().getPubId());
            spaf.setPub_name(sp.getPubName());
            spaf.setPub_add(sp.getPubAdd());
            spaf.setCity(sp.getCity());
            spaf.setCountry(sp.getCountry());
            spaf.setEmail(sp.getEmail());
            spaf.setFax(sp.getFax());
            spaf.setPhone(sp.getPubPhone());
            spaf.setState(sp.getState());
            spaf.setPub_url(sp.getPubUrl());
            return mapping.findForward("add");

        }

        }

        return null;
    }
}
