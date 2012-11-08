

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
import com.myapp.struts.utility.UserLog;


public class InstiLogo extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
        String library_id=(String)session.getAttribute("library_id");
AdminRegistrationDAO admindao=new AdminRegistrationDAO();
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


       AdminRegistration admin=admindao.searchInstituteAdmin((String)session.getAttribute("login_id"));

String ext=null;
 ext=UserLog.returnextension(v.getFileName());


ext=ext.toLowerCase();


if (form1.getImg()!=null)
         {
                
                 
                  UserLog.writeImage1(admin.getLibraryId()+"."+ext, iii);
              
             }
//         else  if(iii!=null)
//         {
//
//                  UserLog.writeImage1(admin.getLibraryId()+admin.getLoginId()+"."+ext, iii);
//         }

       admin.setInstiLogo(admin.getLibraryId()+"."+ext);
       admindao.update1(admin);


        return mapping.findForward(SUCCESS);
    }
        else{
            return mapping.findForward("failure");
        }
    }
}
