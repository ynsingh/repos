/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;

import com.myapp.struts.AdminDAO.LogsDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.util.*;
import javax.servlet.http.HttpSession;
/**
 *
 * @author edrp01
 */
public class LogGridAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String userid,startdate,enddate,libraryid,sublibraryid,print,find;
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        LogGridActionForm lg =(LogGridActionForm)form;
         HttpSession session=request.getSession();
        userid = lg.getUserid();
        startdate = lg.getStartdate();
        enddate = lg.getEnddate();
        libraryid = lg.getLibrary_id();
        sublibraryid = lg.getSublibrary();
       
if(userid==null) userid="";
        if(startdate==null) startdate="";
        if(enddate==null) enddate="";
        if(libraryid==null) libraryid="all";
        if(sublibraryid==null) sublibraryid="all";

         List LogGrid=(List)LogsDAO.searchlog(libraryid, sublibraryid, startdate, enddate, userid);

        
        System.out.println("logggggggggliststststststtstststss"+LogGrid.size());
       
         session.setAttribute("loglist", LogGrid);
        
        return mapping.findForward(SUCCESS);
    }
}
