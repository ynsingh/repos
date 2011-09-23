/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.marc;

import java.util.List;
import com.myapp.struts.hbm.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    

    EditmarcId editmarcid1= new EditmarcId();
    Editmarc editmarc1= new Editmarc();

    EditmarcId editmarcid2= new EditmarcId();
    Editmarc editmarc2= new Editmarc();


    EditmarcId editmarcid3= new EditmarcId();
    Editmarc editmarc3= new Editmarc();

    EditmarcId editmarcid4= new EditmarcId();
    Editmarc editmarc4= new Editmarc();
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
        System.out.println("************************* "+nmaf.getTagname()+"________ "+nmaf.getRepeatable1()+"@@@@@ "+nmaf.getSubsymbol()+"#########"+nmaf.getSubdesc()+"%%%%%%%%%%%%"+nmaf.getTagnumber());

        if(nmaf.getBtn().equals("Save")){

        editmarcid.setTagnumber(nmaf.getTagnumber());
        editmarcid.setSubsymbol(nmaf.getSubsymbol());
        editmarc.setId(editmarcid);
        editmarc.setTagname(nmaf.getTagname());
        editmarc.setSubdescription(nmaf.getSubdesc());
        editmarc.setRepeatable1(nmaf.getRepeatable1());
        
        marcdao.insert(editmarc);

        editmarcid1.setTagnumber(nmaf.getTagnumber());
        editmarcid1.setSubsymbol(nmaf.getSubsymbol1());
        editmarc1.setId(editmarcid1);
        editmarc1.setTagname(nmaf.getTagname());
        editmarc1.setSubdescription(nmaf.getSubdesc1());
        editmarc1.setRepeatable1(nmaf.getRepeatable11());
        marcdao.insert(editmarc1);


        editmarcid2.setTagnumber(nmaf.getTagnumber());
        editmarcid2.setSubsymbol(nmaf.getSubsymbol2());
        editmarc2.setId(editmarcid2);
        editmarc2.setTagname(nmaf.getTagname());
        editmarc2.setSubdescription(nmaf.getSubdesc2());
        editmarc2.setRepeatable1(nmaf.getRepeatable12());
        marcdao.insert(editmarc2);
        
        editmarcid3.setTagnumber(nmaf.getTagnumber());
        editmarcid3.setSubsymbol(nmaf.getSubsymbol3());
        editmarc3.setId(editmarcid3);
        editmarc3.setTagname(nmaf.getTagname());
        editmarc3.setSubdescription(nmaf.getSubdesc3());
        editmarc3.setRepeatable1(nmaf.getRepeatable13());
        marcdao.insert(editmarc3);


        editmarcid4.setTagnumber(nmaf.getTagnumber());
        editmarcid4.setSubsymbol(nmaf.getSubsymbol4());
        editmarc4.setId(editmarcid4);
        editmarc4.setTagname(nmaf.getTagname());
        editmarc4.setSubdescription(nmaf.getSubdesc4());
        editmarc4.setRepeatable1(nmaf.getRepeatable14());
        marcdao.insert(editmarc4);

        System.out.println("Data Saved");
        request.setAttribute("msg", "Record is saved Successfully!");
        
        return mapping.findForward(SUCCESS);
    }
        

request.setAttribute("msg", "Record could not be saved !");
return mapping.findForward("failure");
    }
}
