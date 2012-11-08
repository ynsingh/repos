/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;
import  com.myapp.struts.hbm.*;
import com.myapp.struts.opacDAO.NewDemandDAO;
import java.util.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Faraz
 */
public class FeedbackAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String name, email, comments,cardno, date ,library_id,sublibrary_id;
    boolean result;
    Feedback f=new Feedback();
    FeedbackId fid=new FeedbackId();
    NewDemandDAO newdemanddao=new NewDemandDAO();
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

    FeedbackActionForm myForm = (FeedbackActionForm)form;

    HttpSession session=request.getSession();

    Calendar cal = new GregorianCalendar();
    int month = cal.get(Calendar.MONTH);
    int year = cal.get(Calendar.YEAR);
    int day = cal.get(Calendar.DAY_OF_MONTH);
    date=day+ "/"+ (month+1) + "/"+year;
    name=myForm.getName();
    email=myForm.getEmail();
    comments=myForm.getComments();
    library_id=myForm.getCMBLib();
    sublibrary_id=myForm.getCMBSUBLib();

    fid.setLibraryId(library_id);
    fid.setSublibraryId(sublibrary_id);
    f.setId(fid);
    f.setName(name);
    f.setEmail(email);
    f.setComments(comments);
    f.setDate(date);
     result=newdemanddao.insert2(f);
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


           
        
    }
}
