/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetup;

import com.myapp.struts.hbm.Location;
import com.myapp.struts.hbm.LocationId;
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
public class LocationAddAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
  
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        LocationActionForm lf=(LocationActionForm)form;
        HttpSession session = request.getSession();
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
            Location lcheck = (Location)LocationDAO.getLocationByName(library_id, sub_library_id,locName);
        if(lcheck==null){
        l.setLocationDesc(lf.getLocation_description());
        l.setLocationName(lf.getLocation_name());
        li.setLibraryId(library_id);
        li.setSublibraryId(sub_library_id);
        li.setLocationId(lf.getLocation_id());
        l.setId(li);
        LocationDAO.insert(l);
        request.setAttribute("msg", "Data is saved successfully");
        session.removeAttribute("backlocation");
        }else
        {
               request.setAttribute("msg1", "Duplicate Location Name");
        }
        return mapping.findForward(SUCCESS);
        }
        if(button.equals("Update")){
            Location lcheck = (Location)LocationDAO.getLocationByName(library_id, sub_library_id,locName);
        if(lcheck!=null)
            if(!lcheck.getId().getLocationId().equals(location_id))
            {
                request.setAttribute("msg1", "Duplicate Location Name");
                return mapping.findForward(SUCCESS);
            }
        l.setLocationDesc(lf.getLocation_description());
        l.setLocationName(lf.getLocation_name());
        li.setLibraryId(library_id);
        li.setSublibraryId(sub_library_id);
        li.setLocationId(lf.getLocation_id());
        l.setId(li);
        LocationDAO.update(l);
        request.setAttribute("msg", "Data is updated successfully");
        return mapping.findForward(SUCCESS);
        }
        if(button.equals("Delete")){
        l.setLocationDesc(lf.getLocation_description());
        l.setLocationName(lf.getLocation_name());
        li.setLibraryId(library_id);
        li.setSublibraryId(sub_library_id);
        li.setLocationId(lf.getLocation_id());
        l.setId(li);
        LocationDAO.delete(library_id, sub_library_id, location_id);
        request.setAttribute("msg", "Data is deleted successfully");
        return mapping.findForward(SUCCESS);
        }
        return mapping.findForward(SUCCESS);
    }
}
