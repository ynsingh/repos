/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author edrp02
 */
public class AcqInvoiceItem2Action extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String delimiter = ",";
    String items[];
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        AcqInvoiceItemActionForm aiiaf=(AcqInvoiceItemActionForm)form;
        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        String sizearray=aiiaf.getSizearray();
        String sizearrayindex=aiiaf.getSizearrayindex();
        String i=aiiaf.getI();
    //   System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+i);
        //  String list=aiiaf.getList();
        String list2=aiiaf.getList2();
        items= list2.split(delimiter);
        int amount=Integer.parseInt(items[2])*Integer.parseInt(items[3]);
        request.setAttribute("noofcopies", items[2]);
        request.setAttribute("unitprice", items[3]);
        request.setAttribute("title", items[4]);
        request.setAttribute("tamount", amount);
        request.setAttribute("sizearray",sizearray );
        request.setAttribute("sizearrayindex",sizearrayindex );
      //  request.setAttribute("i",i );
        session.setAttribute("i",i );
        return mapping.findForward(SUCCESS);
    }
}
