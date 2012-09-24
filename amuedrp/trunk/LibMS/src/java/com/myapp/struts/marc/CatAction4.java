/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.marc;

import com.myapp.struts.hbm.Biblio;
import com.myapp.struts.hbm.BiblioId;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author zeeshan
 */
public class CatAction4 extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HashMap hm1=new HashMap();
     MarcHibDAO marchib=new MarcHibDAO();

    MarcHibDAO dao=new MarcHibDAO();

  Biblio biblio=new Biblio();
     BiblioId biblioid= new BiblioId();


        System.out.println("inside cataction4 !");
        CatActionForm4 caf4=(CatActionForm4)form;
        HttpSession session = request.getSession();
       int bibid = (Integer)session.getAttribute("biblio_id");

        System.out.println("************************************************  "+bibid);
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");



        String t=caf4.getZclick();             // t is click value on jsp
        Character in4901,in4902;
        String z490a,z490v,z490x,z4903;

          // getting values of indicator fields from CatActionForm4
                in4901=caf4.getIn4901();
                in4902=caf4.getIn4902();

          //getting values of subfields from CatActionForm4
                z490a=caf4.getZ490a();
                z490v=caf4.getZ490v();
                z490x=caf4.getZ490x();
                z4903=caf4.getZ4903();

          //filling object with data for MARC Tag 490
            biblioid.setLibraryId(library_id);
            biblio.setSublibraryId(sub_library_id);
            biblioid.setMarctag("490");
           if(caf4.getIn4901()!=null)
            if(StringUtils.isNotBlank(in4901.toString())&&StringUtils.isNotEmpty(in4901.toString()))
            biblio.setIndicator1(in4901);
           if(caf4.getIn4902()!=null)
            if(StringUtils.isNotBlank(in4902.toString())&&StringUtils.isNotEmpty(in4902.toString()))
            biblio.setIndicator2(in4902);
           if(StringUtils.isNotBlank(z490a)&&StringUtils.isNotEmpty(z490a))
            biblio.set$a(z490a);
           if(StringUtils.isNotBlank(z490v)&&StringUtils.isNotEmpty(z490v))
                biblio.set$v(z490v);
           if(StringUtils.isNotBlank(z490x)&&StringUtils.isNotEmpty(z490x))
            biblio.set$x(z490x);
          if(StringUtils.isNotBlank(z4903)&&StringUtils.isNotEmpty(z4903))
            biblio.set$3(z4903);

              biblioid.setBibId(bibid);
                   biblio.setId(biblioid);
//                   marchib.insert(biblio);
hm1=(HashMap)session.getAttribute("hsmp");
if(hm1==null)
    hm1=new HashMap();

//if(hm1.containsKey("19")){
        //    hm1.remove("19");
      //  }
 hm1.put("19", biblio);
session.setAttribute("hsmp", hm1);
        //code for mapping forwards......
         if(t.equals("0"))
        {
            return mapping.findForward("forward0");
        }
        else if(t.equals("1")){
                return mapping.findForward("forward1");
        }
        else if(t.equals("2")){
                return mapping.findForward("forward2");
        }
        else if(t.equals("3")){

                return mapping.findForward("forward3");
        }
        else if(t.equals("5")){
                return mapping.findForward("forward5");
        }
        else if(t.equals("6")){
                return mapping.findForward("forward6");
        }
        else if(t.equals("7")){
                return mapping.findForward("forward7");
        }
        else if(t.equals("8")){
                return mapping.findForward("forward8");
        }
        else if(t.equals("9"))
        {
                return mapping.findForward("forward9");
        }
        else if(t.equals("10"))
        {
        return mapping.findForward("forward10");
        }

        return mapping.findForward("forward4");
    }
}
