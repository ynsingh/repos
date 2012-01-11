/*
      Design by Iqubal Ahmad
      Modified on 2011-02-02
      This Action Class is meant for uploading image from File to jsp page During Member registration process.
      This Action Class also Set Attribute(image)For Upload.jsp
 *
 */

package com.myapp.struts.circulation;

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
public class ImageUploadAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";

    /**
      Design by Iqubal Ahmad
      Modified on 2011-02-02
      This Action Class is meant for uploading image from File to jsp page During Member registration process.
     */
   public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
        String page = (String)session.getAttribute("page");
        String page1 = (String)session.getAttribute("page1");
        CirculationMemberActionForm form1 = (CirculationMemberActionForm)form;
        String ln=form1.getTXTLNAME();
        String fn=form1.getTXTFNAME();
        String memcat=form1.getMEMCAT();
        String submemcat=form1.getMEMSUBCAT();
        String add1=form1.getTXTADD1();
        String add2=form1.getTXTADD2();
        String city1=form1.getTXTCITY1();
        String city2=form1.getTXTCITY2();
        String country1=form1.getTXTCOUNTRY1();
        String country2=form1.getTXTCOUNTRY2();
        String course=form1.getTXTCOURSE();
        String dept =form1.getTXTDEPT();
        String desg=form1.getTXTDESG1();
        String mail_id=form1.getTXTEMAILID();
        String exp_date=form1.getTXTEXP_DATE();
        String faculty=form1.getTXTFACULTY();
        String fax=form1.getTXTFAX();
        String mem_id=form1.getTXTMEMID();
        String mname=form1.getTXTMNAME();
        String office=form1.getTXTOFFICE();
        String ph1=form1.getTXTPH1();
        String ph2=form1.getTXTPH2();
        String pin1=form1.getTXTPIN1();
        String pin2=form1.getTXTPIN2();
        String reg_date=form1.getTXTREG_DATE();
        String sem=form1.getTXTSEM();
        String state1=form1.getTXTSTATE1();
        String state2=form1.getTXTSTATE2();
        String uni=form1.getCollege();
        String uniadd=form1.getColladd();


       // byte[] img;
        FormFile filename = form1.getImg();
        if(form1.getImg()!=null)
        {
     byte[]   img = form1.getImg().getFileData();
        if (img!=null)
        System.out.println("ImageUploadAction:"+img.length);
        session.setAttribute("image", img);
        session.setAttribute("filename", filename);
        request.setAttribute("imagechange", 1);
        }
        request.setAttribute("lname", ln);
        request.setAttribute("fname", fn);
        request.setAttribute("mname", mname);
        request.setAttribute("memcat", memcat);
        request.setAttribute("submemcat", submemcat);
        request.setAttribute("add1", add1);
        request.setAttribute("add2", add2);
        request.setAttribute("city1", city1);
        request.setAttribute("city2", city2);
        request.setAttribute("country1",country1);
        request.setAttribute("country2",country2);
        request.setAttribute("course", course);
        request.setAttribute("dept", dept);
        request.setAttribute("desg", desg);
        request.setAttribute("mail_id", mail_id);
        request.setAttribute("exp_date", exp_date);
        request.setAttribute("faculty", faculty);
        request.setAttribute("fax",fax);
        request.setAttribute("mem_id", mem_id);
        request.setAttribute("office", office);
        request.setAttribute("ph1",ph1);
        request.setAttribute("ph2",ph2);
        request.setAttribute("reg_date", reg_date);
        request.setAttribute("sem", sem);
        request.setAttribute("state1", state1);
        request.setAttribute("state2", state2);
        request.setAttribute("pin1", pin1);
        request.setAttribute("pin2",pin2);
        request.setAttribute("uni", uni);
        request.setAttribute("uniadd", uniadd);
        if (page==null){
        return mapping.findForward(SUCCESS);
        }else{
            if (page1!=null && page1.equalsIgnoreCase("Register")){
                return mapping.findForward(SUCCESS);
        }
            return mapping.findForward("failure");
        }
    }
}
