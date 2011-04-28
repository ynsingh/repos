/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;
import  com.myapp.struts.hbm.*;
import  com.myapp.struts.AdminDAO.*;

import java.sql.*;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Dushyant
 */
public class UserStaffAction extends org.apache.struts.action.Action {
    
    
    private String staff_id;
    private String button;
    private String library_id;
    private String sublibrary_id;
    

    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
      
        
        HttpSession session=request.getSession();
        library_id=(String)session.getAttribute("library_id");
        sublibrary_id=(String)session.getAttribute("sublibrary_id");
        staff_id=(String)session.getAttribute("staff_id");


        button="Update";


        String mainlib=(String)session.getAttribute("mainsublibrary");


        StaffDetail staffobj;
        if(sublibrary_id.equalsIgnoreCase(mainlib))
             {


                         staffobj=(StaffDetail)StaffDetailDAO.searchStaffId(staff_id,library_id);
                        if(staffobj==null)
                        {

                        request.setAttribute("msg1", "Staff Id: "+staff_id+" doesn't exists");
                        return mapping.findForward("duplicate");
                        }
             }
             else
             {
                        staffobj=(StaffDetail)StaffDetailDAO.searchStaffId(staff_id,library_id,sublibrary_id);
                        if(staffobj==null)
                        {

                        request.setAttribute("msg1", "Staff Id: "+staff_id+" doesn't exists");
                        return mapping.findForward("duplicate");
                        }


             }

         if(staffobj!=null)
         {



            StaffDetailActionForm staffform=new StaffDetailActionForm();
            staffform.setLibrary_id(staffobj.getId().getLibraryId());
            staffform.setStaff_id(staffobj.getId().getStaffId());
            staffform.setSublibrary_id(staffobj.getSublibraryId());

            if(staffobj.getTitle()!=null)
                 staffform.setCourtesy(staffobj.getTitle());

            staffform.setFirst_name(staffobj.getFirstName());
            staffform.setLast_name(staffobj.getLastName());

            if(staffobj.getContactNo()!=null)
            staffform.setContact_no(staffobj.getContactNo());

            staffform.setMobile_no(staffobj.getMobileNo());

            staffform.setEmail_id(staffobj.getEmailId());
            if(staffobj.getDateJoining()!=null)
            {
                staffform.setDo_joining(staffobj.getDateJoining().toString());

            }

            if(staffobj.getDateReleaving()!=null)
            {
                staffform.setDo_releaving(staffobj.getDateReleaving().toString());
            }
            staffform.setFather_name(staffobj.getFatherName());
             if(staffobj.getDateOfBirth()!=null)
            {
            staffform.setDate_of_birth(staffobj.getDateOfBirth().toString());
             }

            staffform.setGender(staffobj.getGender());

            staffform.setAddress1(staffobj.getAddress1());
            staffform.setCity1(staffobj.getCity1());
            staffform.setState1(staffobj.getState1());
            staffform.setCountry1(staffobj.getCountry1());
            if(staffobj.getZip1()!=null)

            staffform.setZip1(staffobj.getZip1());

            if(staffobj.getAddress2()!=null)
            staffform.setAddress2(staffobj.getAddress2());
            if(staffobj.getCity2()!=null)
            staffform.setCity2(staffobj.getCity2());
            if(staffobj.getState2()!=null)
            staffform.setState2(staffobj.getState2());

            if(staffobj.getCountry2()!=null)
            staffform.setCountry2(staffobj.getCountry2());
            if(staffobj.getZip2()!=null)
            staffform.setZip2(staffobj.getZip2());

            session.setAttribute("updateresultset",staffobj);

            request.setAttribute("button", button);
             List<SubLibrary>  sublib=SubLibraryDAO.searchSubLib(library_id);
             if(!sublib.isEmpty())
             {
                 request.setAttribute("sublib",sublib);

             }
         }
    return mapping.findForward("update");
 
}
}
