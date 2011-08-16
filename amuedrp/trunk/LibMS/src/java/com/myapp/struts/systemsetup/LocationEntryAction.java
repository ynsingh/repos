/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetup;

import com.myapp.struts.hbm.Location;
import com.myapp.struts.systemsetupDAO.LocationDAO;
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
 * @author EdRP-05
 */
public class LocationEntryAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
   Locale locale=null;
   String locale1="en";
   String rtl="ltr";
   String align="left";
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
         HttpSession session = request.getSession();
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

        LocationActionForm lf=(LocationActionForm)form;
       
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        String button=lf.getButton();
        String location_id=lf.getLocation_id();
        Location l=LocationDAO.searchLocation(library_id, sub_library_id, location_id);
        //request.setAttribute("back", request.getAttribute("back"));
       // System.out.println("In Addition location process.."+request.getAttribute("back"));
        if(button.equals("Add"))
        {
            if(l!=null){

             //   request.setAttribute("msg1", "Location Id : "+location_id+" already exists");
            request.setAttribute("msg1", resource.getString("systemsetup.manage_notice.locationid")+location_id+resource.getString("systemsetup.manage_notice.alreadyexists"));
            return mapping.findForward("duplicate");
                       }
            else{
            lf.setLocation_name("");
            lf.setLocation_id(location_id);
            lf.setLocation_description("");
            request.setAttribute("button", button);
            return mapping.findForward("add");
                }
        }
        if(button.equals("Update")){
        if(l==null){

            // request.setAttribute("msg1", "Location id does not exists");
            request.setAttribute("msg1", resource.getString("systemsetup.manage_notice.locationiddesnotexist"));
            return mapping.findForward("duplicate");
        }
 else{
             lf.setLocation_name(l.getLocationName());
            lf.setLocation_id(location_id);
            lf.setLocation_description(l.getLocationDesc());
            request.setAttribute("button", button);
            return mapping.findForward("add");
 }
        }
        if(button.equals("View")){
         if(l==null){

             //request.setAttribute("msg1", "Location id does not exists");
            request.setAttribute("msg1", resource.getString("systemsetup.manage_notice.locationiddesnotexist"));
            return mapping.findForward("duplicate");
        }
 else{
             lf.setLocation_name(l.getLocationName());
            lf.setLocation_id(location_id);
            lf.setLocation_description(l.getLocationDesc());
            request.setAttribute("button", button);
            return mapping.findForward("add");

 }
        }
        if(button.equals("Delete")){
                if(l==null){

             // request.setAttribute("msg1", "Location id does not exists");
            request.setAttribute("msg1", resource.getString("systemsetup.manage_notice.locationiddesnotexist"));
            return mapping.findForward("duplicate");
        }
 else{
             lf.setLocation_name(l.getLocationName());
            lf.setLocation_id(location_id);
            lf.setLocation_description(l.getLocationDesc());
            request.setAttribute("button", button);
            return mapping.findForward("add");

 }
  
        }
        
        return mapping.findForward(SUCCESS);
    }
}
