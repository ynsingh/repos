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
public class CatAction3 extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
   
  

    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        System.out.println("inside cataction3 !");
        CatActionForm3 caf3=(CatActionForm3)form;
        HttpSession session = request.getSession();
       int bibid = (Integer)session.getAttribute("biblio_id");
   MarcHibDAO marchib=new MarcHibDAO();
   HashMap hm1=new HashMap();
    MarcHibDAO dao=new MarcHibDAO();

   Biblio biblio=new Biblio();
   BiblioId biblioid= new BiblioId();

     Biblio biblio1=new Biblio();
     BiblioId biblioid1= new BiblioId();

    Biblio biblio2=new Biblio();
     BiblioId biblioid2= new BiblioId();
        System.out.println("************************************************  "+bibid);
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");



        String t=caf3.getZclick();             // t is click value on jsp

         Character in3001,in3002,in3061,in3062,in3361,in3362;
     String name,z300a,z300b,z300c,z300e,z300f,z300g,z3003, z306a, z336a,z336b,z3362,z3363 ,zclick;

     
     // getting values of indicator fields from CatActionForm3
                    in3001=caf3.getIn3001();
                    in3002=caf3.getIn3002();
                    in3061=caf3.getIn3061();
                    in3062=caf3.getIn3062();
                    in3361=caf3.getIn3361();
                    in3362=caf3.getIn3362();

      //getting values of subfields from CatActionForm3
                    z300a=caf3.getZ300a();
                    z300b=caf3.getZ300b();
                    z300c=caf3.getZ300c();
                    z300e=caf3.getZ300e();
                    z300f=caf3.getZ300f();
                    z300g=caf3.getZ300g();
                    z3003=caf3.getZ3003();

                    z306a=caf3.getZ306a();

                    z336a=caf3.getZ336a();
                    z336b=caf3.getZ336b();
                    z3362=caf3.getZ3362();
                    z3363=caf3.getZ3363();

        //filling object with data for MARC Tag 300
            biblioid.setLibraryId(library_id);
            biblio.setSublibraryId(sub_library_id);
            biblioid.setMarctag("300");
            if(caf3.getIn3001()!=null)
            if(StringUtils.isNotBlank(in3001.toString())&&StringUtils.isNotEmpty(in3001.toString()))
            biblio.setIndicator1(in3001);
            if(caf3.getIn3002()!=null)
            if(StringUtils.isNotBlank(in3002.toString())&&StringUtils.isNotEmpty(in3002.toString()))
            biblio.setIndicator2(in3002);
            if(StringUtils.isNotBlank(z300a)&&StringUtils.isNotEmpty(z300a))
            biblio.set$a(z300a);
            if(StringUtils.isNotBlank(z300b)&&StringUtils.isNotEmpty(z300b))
                biblio.set$b(z300b);
if(StringUtils.isNotBlank(z300c)&&StringUtils.isNotEmpty(z300c))
            biblio.set$c(z300c);
          if(StringUtils.isNotBlank(z300f)&&StringUtils.isNotEmpty(z300f))
            biblio.set$f(z300f);
           if(StringUtils.isNotBlank(z300e)&&StringUtils.isNotEmpty(z300e))
            biblio.set$e(z300e);
          if(StringUtils.isNotBlank(z300g)&&StringUtils.isNotEmpty(z300g))
            biblio.set$g(z300g);
          if(StringUtils.isNotBlank(z3003)&&StringUtils.isNotEmpty(z3003))
            biblio.set$3(z3003);

               biblioid.setBibId(bibid);
                   biblio.setId(biblioid);
//                   marchib.insert(biblio);
hm1 =(HashMap)session.getAttribute("hsmp");
if(hm1==null)
    hm1=new HashMap();
//if(hm1.containsKey("15")){
      //      hm1.remove("15");
      //  }
 hm1.put("15", biblio);

            

                 //filling object with data for MARC Tag 306
            biblioid1.setLibraryId(library_id);
            biblio1.setSublibraryId(sub_library_id);
            biblioid1.setMarctag("306");
           if(caf3.getIn3061()!=null)
            if(StringUtils.isNotBlank(in3061.toString())&&StringUtils.isNotEmpty(in3061.toString()))
            biblio1.setIndicator1(in3061);
           if(caf3.getIn3062()!=null)
            if(StringUtils.isNotBlank(in3062.toString())&&StringUtils.isNotEmpty(in3062.toString()))
            biblio1.setIndicator2(in3062);
           if(StringUtils.isNotBlank(z306a)&&StringUtils.isNotEmpty(z306a))
            biblio1.set$a(z306a);
               
                  biblioid1.setBibId(bibid);
                   biblio1.setId(biblioid1);
//                   marchib.insert(biblio1);

//if(hm1.containsKey("16")){
          //  hm1.remove("16");
       // }
 hm1.put("16", biblio1);

            

                //filling object with data for MARC Tag 336
            biblioid2.setLibraryId(library_id);
            biblio2.setSublibraryId(sub_library_id);
            biblioid2.setMarctag("336");
        if(caf3.getIn3361()!=null)
            if(StringUtils.isNotBlank(in3361.toString())&&StringUtils.isNotEmpty(in3361.toString()))
            biblio2.setIndicator1(in3361);
         if(caf3.getIn3362()!=null)
            if(StringUtils.isNotBlank(in3362.toString())&&StringUtils.isNotEmpty(in3362.toString()))
            biblio2.setIndicator2(in3362);
          if(StringUtils.isNotBlank(z336a)&&StringUtils.isNotEmpty(z336a))
            biblio2.set$a(z336a);
           if(StringUtils.isNotBlank(z336b)&&StringUtils.isNotEmpty(z336b))
            biblio2.set$b(z336b);
           if(StringUtils.isNotBlank(z3362)&&StringUtils.isNotEmpty(z3362))
            biblio2.set$2(z3362);
          if(StringUtils.isNotBlank(z3363)&&StringUtils.isNotEmpty(z3363))
            biblio2.set$3(z3363);

                biblioid2.setBibId(bibid);
                   biblio2.setId(biblioid2);
//                   marchib.insert(biblio2);
//if(hm1.containsKey("18")){
          //  hm1.remove("18");
      //  }
 hm1.put("18", biblio2);

             
            System.out.println("All three objects saved now NAvigating to page "+t);
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
        else if(t.equals("4")){

                return mapping.findForward("forward4");
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

        return mapping.findForward("forward3");
    }
}
