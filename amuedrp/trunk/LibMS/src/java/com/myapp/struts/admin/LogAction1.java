/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;




import com.myapp.struts.hbm.Logs;
import com.myapp.struts.AdminDAO.*;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class LogAction1 extends org.apache.struts.action.Action {
   

    private static final String SUCCESS = "success";
   
    

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

//        List<Logs> log=(List<Logs>)LogsDAO.getUserLog();
  //      HttpSession session=request.getSession();
    //    session.setAttribute("loglist", log);

                    return mapping.findForward(SUCCESS);

    }
}
