/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetup;
import com.myapp.struts.hbm.*;
import com.myapp.struts.systemsetupDAO.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author edrp01
 */
public class AddNotice1Action extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String library_id;
    Notices n= new Notices();
     NoticesId i= new NoticesId();
     boolean result;
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

        AddNoticeActionForm acdn  =(AddNoticeActionForm)form;
        HttpSession session=request.getSession();
        library_id=(String)session.getAttribute("library_id");
       String   sublibrary_id=(String)session.getAttribute("sublibrary_id");
       String button=acdn.getButton();
       String date=acdn.getDate();
       String detail= acdn.getDetail();
     //  String sot =  acdn.getSot();
       String subject= acdn.getSubject();
       String notice_id =acdn.getNotice_id();
       i.setLibraryId(library_id);
       i.setNoticeId(notice_id);
       i.setSublibraryId(sublibrary_id);
       n.setId(i);
       n.setDate(date);
       n.setDetail(detail);
      // n.setSot(sot);
       n.setSubject(subject);

       result=NoticeDAO.insert(n);
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
        //return mapping.findForward(SUCCESS);
    }
}
