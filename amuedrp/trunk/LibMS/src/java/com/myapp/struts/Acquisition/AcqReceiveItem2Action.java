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
public class AcqReceiveItem2Action extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
      private static final String SUCCESS = "success";
      String delimiter = ",";
      String items[];
      @Override
      public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        AcqReceiveItemActionForm ariaf=(AcqReceiveItemActionForm)form;
        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        String list=ariaf.getList();
        if(!list.equals("undefined"))
        {
          items= list.split(delimiter);
          String value=null;
          System.out.println("LLLLLLLLLLLLLLLLLLLLLLLLLLLLl"+items[3]);
//          String value=String.valueOf(Integer.parseInt(items[1])-Integer.parseInt(items[3]));
          if(items[3].equals(""))
           // value=String.valueOf(Integer.parseInt(items[1])-0);
              value="0";
          else
            value=String.valueOf(Integer.parseInt(items[1])-Integer.parseInt(items[3]));
          request.setAttribute("item1", items[0]);
          request.setAttribute("item2", items[1]);
          if(items[3].equals("0"))
            request.setAttribute("item3", "0");
          else
            request.setAttribute("item3", value);
          request.setAttribute("item4", items[3]);
          request.setAttribute("item5", items[4]);
          request.setAttribute("item6", items[5]);
          request.setAttribute("item7", items[6]);
          request.setAttribute("item8", items[7]);
          request.setAttribute("item9", items[8]);
          request.setAttribute("item10", items[9]);
        }
        else
        {
          request.setAttribute("item1", null);
          request.setAttribute("item2", null);
          request.setAttribute("item3", null);
          request.setAttribute("item4", null);
        }

        return mapping.findForward(SUCCESS);
    }
}
