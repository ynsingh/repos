/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Faraz
 */
public class NewDemandAction extends org.apache.struts.action.Action {
    
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
String lib_name,title,author,publ,publ_yr,id,edition;
String req_date,isbn,copy,vol,remark,category;
String date;
HttpSession session=request.getSession();

NewDemandActionForm myform = (NewDemandActionForm)form;

Calendar cal = new GregorianCalendar();
    int month = cal.get(Calendar.MONTH);
    int year = cal.get(Calendar.YEAR);
    int day = cal.get(Calendar.DAY_OF_MONTH);
    //date=day+"/"+(month+1)+"/"+year;
    date=year+"/"+(month+1)+"/"+day;

    lib_name=myform.getTXTLIBNAME();
id=(String)session.getAttribute("id");
title=myform.getTXTTITLE();
category=myform.getCMBCAT();
author=myform.getTXTAUTHOR();
publ=myform.getTXTPUB();
publ_yr=myform.getTXTPUBYR();
isbn=myform.getTXTISBN();
copy=myform.getTXTCOPY();
vol=myform.getTXTVOL();
edition=myform.getTXTEDITION();
remark=myform.getTXTREMARK();
req_date=date;

String queryString = "INSERT INTO demandlist VALUES ('"+ lib_name +"','"+ id +"','"+ title +"','"+ category +"','"+ author +"','"+ publ +"',"+ publ_yr +"," +
       "'"+ isbn +"','"+ copy +"','"+ vol +"','"+ edition +"','"+ remark +"','"+ req_date +"')";

    int no = MyQueryResult.getMyExecuteUpdate(queryString);
    String msg="Your demand is sent to Library..";
    if (no>0){
             
             request.setAttribute("msg", msg);
         }
    System.out.println("no of records inserted:"+no+ " msg="+msg);
    System.out.println("Query="+queryString);
        return mapping.findForward(SUCCESS);
    }
}
