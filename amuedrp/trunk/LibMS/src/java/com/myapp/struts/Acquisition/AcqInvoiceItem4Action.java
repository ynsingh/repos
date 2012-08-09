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
public class AcqInvoiceItem4Action extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String discnt,ntotal;
    int[] disc;
    String flage="true";
    String delimiter2 = ",";
    String indexes[];
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
        session.setAttribute("flage",flage);
         System.out.println("value of i=======================");
        AcqInvoiceItemActionForm aiiaf=(AcqInvoiceItemActionForm)form;
        String discount=aiiaf.getDiscount();
        String net_amt=aiiaf.getNet_amt();
        String title=aiiaf.getTitle();
        String no_of_copies=aiiaf.getNo_of_copies();
        String unit_price=aiiaf.getUnit_price();
        String total_amount=aiiaf.getTotal_amount();
        String sizearray=aiiaf.getSizearray();
        String sizearrayindex=aiiaf.getSizearrayindex();
      //  String i=aiiaf.getI();
        String i=(String)session.getAttribute("i");
        String falge=(String)session.getAttribute("flage");
        System.out.println("value of i="+i+"    aqeel");
        indexes=i.split(delimiter2);
        System.out.println("check flage="+falge);
        if(flage.equals("true"))
        {
            disc=new int[Integer.parseInt(sizearray)] ;
            flage="false";
            session.setAttribute("flage", flage);
        }
        else
        {  int k=0;
           disc=(int[])session.getAttribute("disc");
           for(k=0;k<indexes.length;k++)
             if(disc[Integer.parseInt(indexes[k])]==0)break;
           if(k+1!=indexes.length)
              if(k<indexes.length)
               sizearrayindex=String.valueOf(indexes[k]);
//              for(int j=0;j<Integer.parseInt(sizearray);j++)
//              {
//                 if(disc[k]==0)
//              }

        }

        System.out.println("sizearray"+sizearray+"  arrayindex test"+sizearrayindex);

        disc[Integer.parseInt(sizearrayindex)]=Integer.parseInt(discount);
        session.setAttribute("disc", disc);
     //   session.setAttribute("i",i);
        session.setAttribute("sizearray", sizearray);
        request.setAttribute("noofcopies",no_of_copies);
        request.setAttribute("unitprice",unit_price);
        request.setAttribute("tamount",Integer.parseInt(total_amount));
        request.setAttribute("title",title);
        request.setAttribute("discount",discount);
        request.setAttribute("net_amt",net_amt);
        session.setAttribute("disbl","disbl");

        return mapping.findForward("fail");
       
    }
}
