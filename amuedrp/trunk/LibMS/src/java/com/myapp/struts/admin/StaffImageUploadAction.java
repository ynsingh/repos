/*
      Design by Iqubal Ahmad
      Modified on 2011-02-02
      This Action Class is meant for uploading image from File to jsp page During Member registration process.
      This Action Class also Set Attribute(image)For Upload.jsp
 *
 */

package com.myapp.struts.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

/**
 *
 * @author Iqubal Ahmad
 */
public class StaffImageUploadAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
    /**
      Design by Iqubal Ahmad
      Modified on 2011-02-02
      This Action Class is meant for uploading image from File to jsp page During Member registration process.
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
      //  String page = (String)session.getAttribute("page");
        ImageUploadActionForm form1 = (ImageUploadActionForm)form;
        byte[] img;
        FormFile filename = form1.getImg();
        img = form1.getImg().getFileData();
        if (img!=null)
        {
            System.out.println("ImageUploadAction:"+img.length);
        session.setAttribute("image1", img);
        session.setAttribute("filename", filename);
        request.setAttribute("imagechange1", 1);
     
        return mapping.findForward("found");
    }
        else{
            return mapping.findForward("failure");
        }
    }
}
