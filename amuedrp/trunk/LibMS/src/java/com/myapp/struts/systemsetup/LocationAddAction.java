/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetup;

import com.myapp.struts.hbm.DocumentDetails;
import com.myapp.struts.hbm.Location;
import com.myapp.struts.hbm.LocationId;
import com.myapp.struts.systemsetupDAO.LocationDAO;
import java.util.List;
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
public class LocationAddAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
     Locale locale=null;
   String locale1="en";
   String rtl="ltr";
   String align="left";
    
  
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        LocationDAO locdao=new LocationDAO();
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
       // request.setAttribute("back", request.getAttribute("back"));

       // System.out.println("In Addition location process.."+request.getAttribute("back"));

        String location_id=(String)lf.getLocation_id();
        String button=lf.getButton();
        Location l=new Location();
        LocationId li=new LocationId();
        String locName = lf.getLocation_name();
        
        
        if(button.equals("Submit")){
            Location lcheck = (Location)locdao.getLocationByName(library_id, sub_library_id,locName);
        if(lcheck==null){
        l.setLocationDesc(lf.getLocation_description());
        l.setLocationName(lf.getLocation_name());
        li.setLibraryId(library_id);
        li.setSublibraryId(sub_library_id);
        li.setLocationId(lf.getLocation_id());
        l.setId(li);
        locdao.insert(l);
        //request.setAttribute("msg", "Data is saved successfully");
        request.setAttribute("msg", resource.getString("circulation.circulationnewmemberregAction.recinsesucc"));
        session.removeAttribute("backlocation");
        }else
        {
                // request.setAttribute("msg1", "Duplicate Location Name");
               request.setAttribute("msg1", resource.getString("systemsetup.locationaddaction.duplicatelocname"));
        }
        return mapping.findForward(SUCCESS);
        }
        if(button.equals("Update")){
            Location lcheck = (Location)locdao.getLocationByName(library_id, sub_library_id,locName);
        if(lcheck!=null)
            if(!lcheck.getId().getLocationId().equals(location_id))
            {
                //request.setAttribute("msg1", "Duplicate Location Name");
                request.setAttribute("msg1", resource.getString("systemsetup.locationaddaction.duplicatelocname"));
                return mapping.findForward(SUCCESS);
            }
        l.setLocationDesc(lf.getLocation_description());
        l.setLocationName(lf.getLocation_name());
        li.setLibraryId(library_id);
        li.setSublibraryId(sub_library_id);
        li.setLocationId(lf.getLocation_id());
        l.setId(li);
        locdao.update(l);

        //request.setAttribute("msg", "Data is updated successfully");
        request.setAttribute("msg", resource.getString("circulation.circulationnewmemberregAction.recupdatesucc"));
        return mapping.findForward(SUCCESS);
        }
        if(button.equals("Delete")){
        l.setLocationDesc(lf.getLocation_description());
        l.setLocationName(lf.getLocation_name());
        li.setLibraryId(library_id);
        li.setSublibraryId(sub_library_id);
        li.setLocationId(lf.getLocation_id());
        l.setId(li);

       List <DocumentDetails> doc=locdao.Search(library_id,lf.getLocation_id());
      // System.out.println(doc);

       if(!doc.isEmpty()){
       //request.setAttribute("msg1", "Location used in Accessioning cannot deleted");
                request.setAttribute("msg1",resource.getString("systemsetup.locationAddAction.locusedcannotbedel"));
                return mapping.findForward(SUCCESS);

       }




        locdao.delete(library_id, sub_library_id, location_id);
        // request.setAttribute("msg", "Data is deleted successfully");
        request.setAttribute("msg", resource.getString("circulation.circulationnewmemberregAction.recdelsucc"));
        return mapping.findForward(SUCCESS);
        }
        return mapping.findForward(SUCCESS);
    }
}
