/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Serial;

import com.myapp.struts.SerialDAO.SerialDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.hbm.*;

/**
 *
 * @author edrp02
 */
public class SerAddPublisherAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    SerPublisher sp=new SerPublisher();
    SerPublisherId spid=new SerPublisherId();
    SerialDAO serialdao=new SerialDAO();
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        SerPublisherActionForm spaf=(SerPublisherActionForm)form;
        String pub_id=spaf.getPub_id();
        String pub_name=spaf.getPub_name();
        String pub_add=spaf.getPub_add();
        String city=spaf.getCity();
        String state=spaf.getState();
        String country=spaf.getCountry();
        String phone=spaf.getPhone();
        String email=spaf.getEmail();
        String fax=spaf.getFax();
        String pub_url=spaf.getPub_url();
        String button=spaf.getButton();
        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sublibrary_id = (String) session.getAttribute("sublibrary_id");
        if(button.equals("Submit")){

          spid.setLibraryId(library_id);
          spid.setSublibraryId(sublibrary_id);
          spid.setPubId(pub_id);
          sp.setId(spid);
          sp.setPubName(pub_name);
          sp.setPubAdd(pub_add);
          sp.setCity(city);
          sp.setState(state);
          sp.setCountry(country);
          sp.setPubPhone(phone);
          sp.setEmail(email);
          sp.setFax(fax);
          sp.setPubUrl(pub_url);
          serialdao.insert(sp);
          request.setAttribute("msg", "Data is saved successfully");
         }
         if(button.equals("Update")){

          spid.setLibraryId(library_id);
          spid.setSublibraryId(sublibrary_id);
          spid.setPubId(pub_id);
          sp.setId(spid);
          sp.setPubName(pub_name);
          sp.setPubAdd(pub_add);
          sp.setCity(city);
          sp.setState(state);
          sp.setCountry(country);
          sp.setPubPhone(phone);
          sp.setEmail(email);
          sp.setFax(fax);
          sp.setPubUrl(pub_url);
            serialdao.update(sp);
            request.setAttribute("msg", "Data is updated successfully");
            return mapping.findForward(SUCCESS);

        }

        if(button.equals("Delete")){
          SerPublisher spcheck=serialdao.searchSerialPubisher(library_id, sublibrary_id, pub_id);
          if(spcheck!=null){
            serialdao.delete(spcheck);
            request.setAttribute("msg", "Record is deleted successfully");
            return mapping.findForward(SUCCESS);
          }
        }

        return mapping.findForward(SUCCESS);
    }
}
