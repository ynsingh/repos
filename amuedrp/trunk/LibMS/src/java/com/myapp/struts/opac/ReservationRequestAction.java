/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import org.apache.catalina.Session;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Faraz
 */
public class ReservationRequestAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
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
            HttpSession session = request.getSession();
            ReservationRequetActionForm myForm = (ReservationRequetActionForm)form;
            
            String lib_name,card_id,title,author,publ,edition;
            String req_date,callno,isbn,vol,remark,category;

            Calendar cal = new GregorianCalendar();
            int month = cal.get(Calendar.MONTH);
            int year = cal.get(Calendar.YEAR);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            req_date=day+"/"+(month+1)+"/"+year;

            lib_name=myForm.getTXTLIBNAME();
card_id=(String)session.getAttribute("id");
title=myForm.getTXTTITLE();
category=myForm.getCMBCAT();
author=myForm.getTXTAUTHOR();
publ=myForm.getTXTPUBL();
//card_id=request.getParameter("TXTCARDID");
isbn=myForm.getTXTISBN();
callno=myForm.getTXTCALLNO();
vol=myForm.getTXTVOL();
edition=myForm.getTXTEDITION();
remark=myForm.getTXTREMARKS();

String queryString = "INSERT INTO reservationlist VALUES ('"+ lib_name +"','"+ card_id +"','"+ title +"','"+ category +"','"+ author +"','"+ isbn +"','"+ callno +"',"+ edition +"," +
       "','"+ vol +"','"+ publ +"','"+ remark +"','"+ req_date +"')";

int no = MyQueryResult.getMyExecuteUpdate(queryString);
String msg="Your reservation request is sent to Library..";
request.setAttribute("msg", msg);
        return mapping.findForward(SUCCESS);
    }
}
