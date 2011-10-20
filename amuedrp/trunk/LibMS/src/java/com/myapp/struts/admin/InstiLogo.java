

package com.myapp.struts.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import com.myapp.struts.hbm.AdminRegistration;
import com.myapp.struts.AdminDAO.AdminRegistrationDAO;


public class InstiLogo extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
     System.out.println("Image Update code");
        ImageUploadActionForm form1 = (ImageUploadActionForm)form;
        byte[] img;
        FormFile filename = form1.getImg();
        img = form1.getImg().getFileData();
   
      
        if (img!=null)
        {
            System.out.println("ImageUploadAction:"+img.length);
        session.setAttribute("image1", img);
        session.setAttribute("filename1", filename);
        request.setAttribute("imagechange1", 1);

          FormFile v=(FormFile)session.getAttribute("filename1");
       byte[] iii=null;
       if(v!=null)iii=v.getFileData();


       AdminRegistration admin=AdminRegistrationDAO.searchInstituteAdmin((String)session.getAttribute("login_id"));
System.out.println(admin);

         if (form1.getImg()!=null)
            admin.setInstiLogo(form1.getImg().getFileData());
         else
               if(iii!=null){admin.setInstiLogo(iii);}
               else{admin.setInstiLogo(null);}

            AdminRegistrationDAO admindao=new AdminRegistrationDAO();
            admindao.update(admin);
          admin=AdminRegistrationDAO.searchInstituteAdmin((String)session.getAttribute("login_id"));
session.setAttribute("AdminDetail",admin);

        return mapping.findForward(SUCCESS);
    }
        else{
            return mapping.findForward("failure");
        }
    }
}
