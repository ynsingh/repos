/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;
import  com.myapp.struts.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.sql.*;
import java.util.List;
import com.myapp.struts.opacDAO.*;

/**
 *
 * @author Faraz
 */
public class AccessionSearchAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
     OpacSearchDAO osdao= new OpacSearchDAO();
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception  {
        HttpSession session = request.getSession();
        ResultSet rs = null;
        AccessionSearchActionForm myform = (AccessionSearchActionForm)form;
        String lib_id=myform.getCMBLib();
        String accessionno = myform.getTXTKEY();
          String sublib=myform.getCMBSUBLib();
       session.removeAttribute("documentdetail");
        List documentdetail  =(List)osdao.accessionNoSearch(accessionno, lib_id, sublib);
        session.setAttribute("documentdetail", documentdetail);
        return mapping.findForward(SUCCESS);
    }
}
