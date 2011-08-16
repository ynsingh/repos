/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;


import com.myapp.struts.admin.LogGridActionForm;
import com.myapp.struts.AdminDAO.LogsDAO;
import java.util.List;
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
public class callreportAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private String userid;
    private String startdate;
    private String enddate;
    private String libraryid;
    private String sublibraryid;
    private String print;
    private String find;
    
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
  
         HttpSession session=request.getSession();
        
   List LogGrid=(List)LogsDAO.searchlog("all", "all", null, null, "");

         session.setAttribute("LogGrid", LogGrid);
        
         List loglist=(List)LogsDAO.loglist("all", "all");
         session.setAttribute("loglist", loglist);
         System.out.println("logggggggggliststststststtstststss"+loglist.size());
        
        return mapping.findForward(SUCCESS);
    }
}
