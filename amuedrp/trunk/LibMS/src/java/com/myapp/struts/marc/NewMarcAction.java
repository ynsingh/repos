/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.marc;

import java.util.List;
import com.myapp.struts.hbm.*;
import java.util.Arrays;
import java.util.LinkedList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author zeeshan
 */
public class NewMarcAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    NewMARCDAO marcdao=new NewMARCDAO();

    EditmarcId editmarcid= new EditmarcId();
    Editmarc editmarc= new Editmarc();
    LinkedList tagnl;
    LinkedList subsyml;
    LinkedList tagnml;
    LinkedList subdescl;
    
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
        NewMarcActionForm nmaf=(NewMarcActionForm)form;
        if(nmaf.getBtn().equals("Save")){
               if(nmaf.getTagnumber()!=null)
       {
           int[] a=nmaf.getTagnumber();
           tagnl=new LinkedList(Arrays.asList(a)) ;
       }
       if(nmaf.getTagname()!=null)
       {
           String[] b=nmaf.getTagname();
           tagnml=new LinkedList(Arrays.asList(b));
       }
       if(nmaf.getSubdesc()!=null)
       {
           String[] c=nmaf.getSubdesc();
          subdescl=new LinkedList(Arrays.asList(c));
       }

       if(nmaf.getSubsymbol()!=null)
       {
           Character[] d=nmaf.getSubsymbol();
          subdescl=new LinkedList(Arrays.asList(d));
       }
        System.out.println("Data Saved");
        request.setAttribute("msg", "Record is saved Successfully!");
        
        return mapping.findForward(SUCCESS);
    }
        

request.setAttribute("msg", "Record could not be saved !");
return mapping.findForward("failure");
    }
}
