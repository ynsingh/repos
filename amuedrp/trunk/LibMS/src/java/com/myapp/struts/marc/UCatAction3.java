/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.marc;

import com.myapp.struts.hbm.Biblio;
import com.myapp.struts.hbm.BiblioId;
import java.util.HashMap;
import java.util.List;
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
public class UCatAction3 extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
HashMap hm1=new HashMap();
     private MarcHibDAO marchib=new MarcHibDAO();

    MarcHibDAO dao=new MarcHibDAO();

    private Biblio biblio=new Biblio();
    private BiblioId biblioid= new BiblioId();

    private Biblio biblio1=new Biblio();
    private BiblioId biblioid1= new BiblioId();

    private Biblio biblio2=new Biblio();
    private BiblioId biblioid2= new BiblioId();
    
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

         System.out.println("inside cataction3 !");
        CatActionForm3 caf3=(CatActionForm3)form;
        HttpSession session = request.getSession();
       int bibid = Integer.parseInt((String)session.getAttribute("biblio_id"));

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
if(hm1.containsKey("15")){
            hm1.remove("15");
        }
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

if(hm1.containsKey("16")){
            hm1.remove("16");
        }
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
if(hm1.containsKey("18")){
            hm1.remove("18");
        }
 hm1.put("18", biblio2);



            System.out.println("All three objects saved now NAvigating to page "+t);
            String bib_id=(String)session.getAttribute("biblio_id") ;
               List<Biblio> biblist= marchib.getdataforupdate(bib_id, library_id, sub_library_id);
 //code for mapping forwards......
         if(t.equals("0"))
        {

            System.out.println("BBBBGGGGGGGGGGGGGGGGGGGGGGGGGGGG"+biblist.size());

            for(int i=0;biblist.size()>i;i++)
            {
                   if(biblist.get(i).getId().getMarctag().equals("020")){

                     request.setAttribute("020", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("022")){

                     request.setAttribute("022", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("041")){
                      // System.out.println("^^^^^^^^^^^^^ "+biblist.get(i).get$a());
                     request.setAttribute("041", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("043")){
                        //System.out.println("^^^^^^^^^^^^^ "+biblist.get(i).get$a());
                     request.setAttribute("043", biblist.get(i));
                    }

                   if(biblist.get(i).getId().getMarctag().equals("082")){
    //                    System.out.println("^^^^^^^^^^^^^ "+biblist.get(i).get$a());
                     request.setAttribute("082", biblist.get(i));
                    }

            }
            return mapping.findForward("forward0");
        }

        else if(t.equals("1"))
        {

            System.out.println("1111111111111111111111   "+biblist.size());

            for(int i=0;biblist.size()>i;i++)
            {
                   if(biblist.get(i).getId().getMarctag().equals("100")){

                     request.setAttribute("100", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("110")){

                     request.setAttribute("110", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("130")){
                      // System.out.println("^^^^^^^^^^^^^ "+biblist.get(i).get$a());
                     request.setAttribute("130", biblist.get(i));
                    }
            }
            return mapping.findForward("forward1");
        }

        else if(t.equals("2"))
        {
        
                System.out.println("22222222222222    "+biblist.size());
                for(int i=0;biblist.size()>i;i++)
            {
                   if(biblist.get(i).getId().getMarctag().equals("210")){

                     request.setAttribute("210", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("245")){

                     request.setAttribute("245", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("250")){
                      // System.out.println("^^^^^^^^^^^^^ "+biblist.get(i).get$a());
                     request.setAttribute("250", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("256")){

                     request.setAttribute("256", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("260")){

                     request.setAttribute("260", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("263")){
                      // System.out.println("^^^^^^^^^^^^^ "+biblist.get(i).get$a());
                     request.setAttribute("263", biblist.get(i));
                    }
            }
                return mapping.findForward("forward2");
        }
        else if(t.equals("4"))
        {


                System.out.println("44444444444    "+biblist.size());
                for(int i=0;biblist.size()>i;i++)
            {
                   if(biblist.get(i).getId().getMarctag().equals("490")){

                     request.setAttribute("490", biblist.get(i));
                    }

            }
                return mapping.findForward("forward4");
        }
        else if(t.equals("5"))
        {

                System.out.println("555555555555    "+biblist.size());
                for(int i=0;biblist.size()>i;i++)
            {
                   if(biblist.get(i).getId().getMarctag().equals("500")){

                     request.setAttribute("500", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("502")){

                     request.setAttribute("502", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("504")){
                      // System.out.println("^^^^^^^^^^^^^ "+biblist.get(i).get$a());
                     request.setAttribute("504", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("505")){

                     request.setAttribute("505", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("520")){

                     request.setAttribute("520", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("546")){
                      // System.out.println("^^^^^^^^^^^^^ "+biblist.get(i).get$a());
                     request.setAttribute("546", biblist.get(i));
                    }
            }
                return mapping.findForward("forward5");
        }
        else if(t.equals("6"))
        {
          
                System.out.println("666666666666    "+biblist.size());
                for(int i=0;biblist.size()>i;i++)
            {
                   if(biblist.get(i).getId().getMarctag().equals("600")){

                     request.setAttribute("600", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("650")){

                     request.setAttribute("650", biblist.get(i));
                    }

            }
                return mapping.findForward("forward6");
        }

        else if(t.equals("7"))
        {
             
                        System.out.println("77777777777    "+biblist.size());
                        for(int i=0;biblist.size()>i;i++)
                    {
                           if(biblist.get(i).getId().getMarctag().equals("700")){

                             request.setAttribute("700", biblist.get(i));
                            }
                           if(biblist.get(i).getId().getMarctag().equals("740")){

                             request.setAttribute("740", biblist.get(i));
                            }
                    }
                return mapping.findForward("forward7");
        }
        else if(t.equals("8"))
        {

                        System.out.println("888888888888    "+biblist.size());
                        for(int i=0;biblist.size()>i;i++)
                         {
                           if(biblist.get(i).getId().getMarctag().equals("800"))
                           {

                             request.setAttribute("800", biblist.get(i));
                            }
                           if(biblist.get(i).getId().getMarctag().equals("830"))
                           {

                             request.setAttribute("830", biblist.get(i));
                            }
                           if(biblist.get(i).getId().getMarctag().equals("850"))
                           {
                              // System.out.println("^^^^^^^^^^^^^ "+biblist.get(i).get$a());
                             request.setAttribute("850", biblist.get(i));
                            }
                           if(biblist.get(i).getId().getMarctag().equals("852"))
                           {

                             request.setAttribute("852", biblist.get(i));
                            }
                           if(biblist.get(i).getId().getMarctag().equals("856"))
                           {

                             request.setAttribute("856", biblist.get(i));
                            }

                       }
                return mapping.findForward("forward8");
        }
        else if(t.equals("9"))
        {
                return mapping.findForward("forward9");
        }
 else if(t.equals("10"))
        {
       
System.out.println("1111111111111111111111   "+biblist.size());

            for(int i=0;biblist.size()>i;i++)
            {
                   if(biblist.get(i).getId().getMarctag().equals("Leader")){

                     request.setAttribute("Leader", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("001")){

                     request.setAttribute("001", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("003")){
                      // System.out.println("^^^^^^^^^^^^^ "+biblist.get(i).get$a());
                     request.setAttribute("003", biblist.get(i));
                    }
                 if(biblist.get(i).getId().getMarctag().equals("005")){
                      // System.out.println("^^^^^^^^^^^^^ "+biblist.get(i).get$a());
                     request.setAttribute("005", biblist.get(i));
                    }
                      if(biblist.get(i).getId().getMarctag().equals("007")){
                      // System.out.println("^^^^^^^^^^^^^ "+biblist.get(i).get$a());
                     request.setAttribute("007", biblist.get(i));
                    }
                     if(biblist.get(i).getId().getMarctag().equals("008")){
                      // System.out.println("^^^^^^^^^^^^^ "+biblist.get(i).get$a());
                     request.setAttribute("008", biblist.get(i));
                    }
            }
            return mapping.findForward("forward10");
        }

                System.out.println("22222222222222    "+biblist.size());
                for(int i=0;biblist.size()>i;i++)
            {
                   if(biblist.get(i).getId().getMarctag().equals("300")){

                     request.setAttribute("300", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("306")){

                     request.setAttribute("306", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("336")){
                      // System.out.println("^^^^^^^^^^^^^ "+biblist.get(i).get$a());
                     request.setAttribute("336", biblist.get(i));
                    }

            }
        return mapping.findForward("forward3");
    }
       
}
