/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetup;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpSession;
import com.myapp.struts.hbm.*;
import com.myapp.struts.systemsetupDAO.FacultyDAO;
import java.util.List;
/**
 *
 * @author edrp02
 */
public class AddFacultyAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String faculty_id;
    String faculty_name;
    String library_id;
    String button;
    boolean result;
    List control_list;
    int max_id;
    String max_new_id;
    Faculty f=new Faculty();
    FacultyId fid=new FacultyId();
    

   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        AddFacultyActionForm afaf=(AddFacultyActionForm)form;
        faculty_id=afaf.getFaculty_id();
        button=afaf.getButton();
        faculty_name=afaf.getFaculty_name();
        HttpSession session=request.getSession();
        library_id=(String)session.getAttribute("library_id");
        
            Faculty fcheck=FacultyDAO.getFacultyId(library_id, faculty_name);
        System.out.println("faculty"+fcheck);
            if(fcheck==null)
            {
        fid.setFacultyId(faculty_id);
        fid.setLibraryId(library_id);
        f.setId(fid);
       
        f.setFacultyName(faculty_name);
        result=FacultyDAO.insert(f);
        if(result==true)
        {
            request.setAttribute("msg", "Record Inserted Successfully");
            return mapping.findForward("success");

        }
        else
        {
            request.setAttribute("msg", "Record Not Inserted");
            return mapping.findForward("success");
        }
            }else{
                request.setAttribute("msg", "Duplicate Faculty Name");
            return mapping.findForward("success");
            }
       

        
        
    }
}
