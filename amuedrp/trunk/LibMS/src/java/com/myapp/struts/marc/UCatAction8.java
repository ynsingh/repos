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
public class UCatAction8 extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";

     private MarcHibDAO marchib=new MarcHibDAO();

     HashMap hm1=new HashMap();

    private Biblio biblio=new Biblio();
    private BiblioId biblioid= new BiblioId();

    private Biblio biblio1=new Biblio();
    private BiblioId biblioid1= new BiblioId();

    private Biblio biblio2=new Biblio();
    private BiblioId biblioid2= new BiblioId();

    private Biblio biblio3=new Biblio();
    private BiblioId biblioid3= new BiblioId();

    private Biblio biblio4=new Biblio();
    private BiblioId biblioid4= new BiblioId();
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
        System.out.println("inside Ucataction8 !");
        CatActionForm8 caf8=(CatActionForm8)form;

        HttpSession session = request.getSession();
      int  bibid = Integer.parseInt((String)session.getAttribute("biblio_id"));
        System.out.println("************************************************  "+bibid);
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");



        String t=caf8.getZclick();             // t is click value on jsp

        String z800a,z800b,z800c,z800d,z800t,z800l,z800f,z800s,z800v,z8004, z830a,z830h,z830n,z830p,z830v,z830x,z8303,z8305, z850a;
     String  z852a,z852b,z852c,z852f,z852e,z852h,z852i,z852n,z852t,z852u, z856a,z856b,z856c,z856d,z856h,z856i,z856f,z856j,z856k,z856l,z856m,z856n,z856o,z856p,z856q,z856u,z856t,z856s,z856x,z856z;
    Character in8001,in8002,in8301,in8302,in8501,in8502,in8521,in8522,in8561,in8562;

    // getting values of indicator fields from CatActionForm8
            in8001=caf8.getIn8001();
            in8002=caf8.getIn8002();
            in8301=caf8.getIn8301();
            in8302=caf8.getIn8302();
            in8501=caf8.getIn8501();
            in8502=caf8.getIn8502();
            in8521=caf8.getIn8521();
            in8522=caf8.getIn8522();
            in8561=caf8.getIn8561();
            in8562=caf8.getIn8562();

     //getting values of subfields from CatActionForm8
            z800a=caf8.getZ800a();
            z800b=caf8.getZ800b();
            z800c=caf8.getZ800c();
            z800d=caf8.getZ800d();
            z800t=caf8.getZ800t();
            z800l=caf8.getZ800l();
            z800f=caf8.getZ800f();
            z800s=caf8.getZ800s();
            z800v=caf8.getZ800v();
            z8004=caf8.getZ8004();

                z830a=caf8.getZ830a();
                z830h=caf8.getZ830h();
                z830n=caf8.getZ830n();
                z830p=caf8.getZ830p();
                z830v=caf8.getZ830v();
                z830x=caf8.getZ830x();
                z8303=caf8.getZ8303();
                z8305=caf8.getZ8305();

                    z850a=caf8.getZ850a();

                        z852a=caf8.getZ852a();
                        z852b=caf8.getZ852b();
                        z852c=caf8.getZ852c();
                        z852f=caf8.getZ852f();
                        z852e=caf8.getZ852e();
                        z852h=caf8.getZ852h();
                        z852i=caf8.getZ852i();
                        z852n=caf8.getZ852n();
                        z852t=caf8.getZ852t();
                        z852u=caf8.getZ852u();

                            z856a=caf8.getZ856a();
                            z856b=caf8.getZ856b();
                            z856c=caf8.getZ856c();
                            z856d=caf8.getZ856d();
                            z856h=caf8.getZ856h();
                            z856i=caf8.getZ856i();
                            z856f=caf8.getZ856f();
                            z856j=caf8.getZ856j();
                            z856k=caf8.getZ856k();
                            z856l=caf8.getZ856l();
                            z856m=caf8.getZ856m();
                            z856n=caf8.getZ856n();
                            z856o=caf8.getZ856o();
                            z856p=caf8.getZ856p();
                            z856q=caf8.getZ856q();
                            z856u=caf8.getZ856u();
                            z856t=caf8.getZ856t();
                            z856s=caf8.getZ856s();
                            z856x=caf8.getZ856x();
                            z856z=caf8.getZ856z();

             //filling object with data for MARC Tag 800
                biblioid.setLibraryId(library_id);
                biblio.setSublibraryId(sub_library_id);
                biblioid.setMarctag("800");
                if(caf8.getIn8001()!=null)
                if(StringUtils.isNotBlank(in8001.toString())&&StringUtils.isNotEmpty(in8001.toString()))
                biblio.setIndicator1(in8001);
                if(caf8.getIn8002()!=null)
                if(StringUtils.isNotBlank(in8002.toString())&&StringUtils.isNotEmpty(in8002.toString()))
                biblio.setIndicator2(in8002);
                if(StringUtils.isNotBlank(z800a)&&StringUtils.isNotEmpty(z800a))
                biblio.set$3(z800a);
                if(StringUtils.isNotBlank(z800b)&&StringUtils.isNotEmpty(z800b))
                    biblio.set$a(z800b);
               if(StringUtils.isNotBlank(z800c)&&StringUtils.isNotEmpty(z800c))
                    biblio.set$3(z800c);
                if(StringUtils.isNotBlank(z800d)&&StringUtils.isNotEmpty(z800d))
                    biblio.set$a(z800d);
                if(StringUtils.isNotBlank(z800t)&&StringUtils.isNotEmpty(z800t))
                    biblio.set$3(z800t);
                 if(StringUtils.isNotBlank(z800l)&&StringUtils.isNotEmpty(z800l))
                    biblio.set$a(z800l);
                if(StringUtils.isNotBlank(z800f)&&StringUtils.isNotEmpty(z800f))
                biblio.set$3(z800f);
                if(StringUtils.isNotBlank(z800s)&&StringUtils.isNotEmpty(z800s))
                    biblio.set$a(z800s);
               if(StringUtils.isNotBlank(z800v)&&StringUtils.isNotEmpty(z800v))
                    biblio.set$3(z800v);
              if(StringUtils.isNotBlank(z8004)&&StringUtils.isNotEmpty(z8004))
                    biblio.set$a(z8004);


                   biblioid.setBibId(bibid);
                       biblio.setId(biblioid);
//                       marchib.insert(biblio);
  hm1=(HashMap)session.getAttribute("hsmp");
if(hm1.containsKey("30")){
            hm1.remove("30");
        }
 hm1.put("30", biblio);


                     //filling object with data for MARC Tag 830
                biblioid1.setLibraryId(library_id);
                biblio1.setSublibraryId(sub_library_id);
                biblioid1.setMarctag("830");
             if(caf8.getIn8301()!=null)
                if(StringUtils.isNotBlank(in8301.toString())&&StringUtils.isNotEmpty(in8301.toString()))
                biblio1.setIndicator1(in8301);
                if(caf8.getIn8302()!=null)
                if(StringUtils.isNotBlank(in8302.toString())&&StringUtils.isNotEmpty(in8302.toString()))
                biblio1.setIndicator2(in8302);
            if(StringUtils.isNotBlank(z830a)&&StringUtils.isNotEmpty(z830a))
                biblio1.set$a(z830a);
            if(StringUtils.isNotBlank(z830h)&&StringUtils.isNotEmpty(z830h))
                    biblio1.set$b(z830h);
            if(StringUtils.isNotBlank(z830n)&&StringUtils.isNotEmpty(z830n))
                biblio1.set$c(z830n);
            if(StringUtils.isNotBlank(z830p)&&StringUtils.isNotEmpty(z830p))
                biblio1.set$d(z830p);
             if(StringUtils.isNotBlank(z830v)&&StringUtils.isNotEmpty(z830v))
                biblio1.set$g(z830v);
              if(StringUtils.isNotBlank(z830x)&&StringUtils.isNotEmpty(z830x))
                biblio1.set$g(z830x);
             if(StringUtils.isNotBlank(z8303)&&StringUtils.isNotEmpty(z8303))
                biblio1.set$a(z8303);
           if(StringUtils.isNotBlank(z8305)&&StringUtils.isNotEmpty(z8305))
                biblio1.set$b(z8305);


                    biblioid1.setBibId(bibid);
                       biblio1.setId(biblioid1);
//                       marchib.insert(biblio1);
if(hm1.containsKey("31")){
            hm1.remove("31");
        }
 hm1.put("31", biblio1);


                    //filling object with data for MARC Tag 850
                biblioid2.setLibraryId(library_id);
                biblio2.setSublibraryId(sub_library_id);
                biblioid2.setMarctag("850");
                 if(caf8.getIn8501()!=null)
                if(StringUtils.isNotBlank(in8501.toString())&&StringUtils.isNotEmpty(in8501.toString()))
                biblio2.setIndicator1(in8501);
                if(caf8.getIn8502()!=null)
                if(StringUtils.isNotBlank(in8502.toString())&&StringUtils.isNotEmpty(in8502.toString()))
                biblio2.setIndicator2(in8502);
              if(StringUtils.isNotBlank(z850a)&&StringUtils.isNotEmpty(z850a))
                biblio2.set$a(z850a);
                          biblioid2.setBibId(bibid);
                       biblio2.setId(biblioid2);
//                       marchib.insert(biblio2);
if(hm1.containsKey("32")){
            hm1.remove("32");
        }
 hm1.put("32", biblio2);



                //filling object with data for MARC Tag 852
                biblioid3.setLibraryId(library_id);
                biblio3.setSublibraryId(sub_library_id);
                biblioid3.setMarctag("852");
                if(caf8.getIn8521()!=null)
                if(StringUtils.isNotBlank(in8521.toString())&&StringUtils.isNotEmpty(in8521.toString()))
                biblio3.setIndicator1(in8521);
                if(caf8.getIn8522()!=null)
                if(StringUtils.isNotBlank(in8522.toString())&&StringUtils.isNotEmpty(in8522.toString()))
                biblio3.setIndicator2(in8522);
                if(StringUtils.isNotBlank(z852a)&&StringUtils.isNotEmpty(z852a))
                biblio3.set$a(z852a);
                if(StringUtils.isNotBlank(z852b)&&StringUtils.isNotEmpty(z852b))
                biblio3.set$g(z852b);
                if(StringUtils.isNotBlank(z852c)&&StringUtils.isNotEmpty(z852c))
                biblio3.set$r(z852c);
              if(StringUtils.isNotBlank(z852e)&&StringUtils.isNotEmpty(z852e))
                biblio3.set$t(z852e);
               if(StringUtils.isNotBlank(z852f)&&StringUtils.isNotEmpty(z852f))
                biblio3.set$u(z852f);
                if(StringUtils.isNotBlank(z852h)&&StringUtils.isNotEmpty(z852h))
                biblio3.set$a(z852h);
               if(StringUtils.isNotBlank(z852i)&&StringUtils.isNotEmpty(z852i))
                biblio3.set$g(z852i);
               if(StringUtils.isNotBlank(z852n)&&StringUtils.isNotEmpty(z852n))
                biblio3.set$r(z852n);
               if(StringUtils.isNotBlank(z852t)&&StringUtils.isNotEmpty(z852t))
                biblio3.set$t(z852t);
               if(StringUtils.isNotBlank(z852u)&&StringUtils.isNotEmpty(z852u))
                biblio3.set$u(z852u);


                  biblioid3.setBibId(bibid);
                       biblio3.setId(biblioid3);
//                       marchib.insert(biblio3);
if(hm1.containsKey("33")){
            hm1.remove("33");
        }
 hm1.put("33", biblio3);



                    //filling object with data for MARC Tag 856
                biblioid4.setLibraryId(library_id);
                biblio4.setSublibraryId(sub_library_id);
                biblioid4.setMarctag("856");
                 if(caf8.getIn8561()!=null)
                if(StringUtils.isNotBlank(in8561.toString())&&StringUtils.isNotEmpty(in8561.toString()))
                biblio4.setIndicator1(in8561);
               if(caf8.getIn8562()!=null)
                if(StringUtils.isNotBlank(in8562.toString())&&StringUtils.isNotEmpty(in8562.toString()))
                biblio4.setIndicator2(in8562);
              if(StringUtils.isNotBlank(z856a)&&StringUtils.isNotEmpty(z856a))
                biblio4.set$a(z856a);
                if(StringUtils.isNotBlank(z856b)&&StringUtils.isNotEmpty(z856b))
                biblio4.set$b(z856b);
                if(StringUtils.isNotBlank(z856c)&&StringUtils.isNotEmpty(z856c))
                    biblio4.set$c(z856c);
               if(StringUtils.isNotBlank(z856d)&&StringUtils.isNotEmpty(z856d))
                biblio4.set$u(z856d);
            if(StringUtils.isNotBlank(z856f)&&StringUtils.isNotEmpty(z856f))
                    biblio4.set$2(z856f);
                if(StringUtils.isNotBlank(z856h)&&StringUtils.isNotEmpty(z856h))
                biblio4.set$3(z856h);
               if(StringUtils.isNotBlank(z856i)&&StringUtils.isNotEmpty(z856i))
                biblio4.set$a(z856i);
             if(StringUtils.isNotBlank(z856j)&&StringUtils.isNotEmpty(z856j))
                    biblio4.set$b(z856j);
                if(StringUtils.isNotBlank(z856k)&&StringUtils.isNotEmpty(z856k))
                biblio4.set$c(z856k);
              if(StringUtils.isNotBlank(z856l)&&StringUtils.isNotEmpty(z856l))
                biblio4.set$u(z856l);
                if(StringUtils.isNotBlank(z856m)&&StringUtils.isNotEmpty(z856m))
                biblio4.set$2(z856m);
              if(StringUtils.isNotBlank(z856n)&&StringUtils.isNotEmpty(z856n))
                biblio4.set$n(z856n);
             if(StringUtils.isNotBlank(z856o)&&StringUtils.isNotEmpty(z856o))
                biblio4.set$a(z856o);
             if(StringUtils.isNotBlank(z856p)&&StringUtils.isNotEmpty(z856p))
                    biblio4.set$b(z856p);
                if(StringUtils.isNotBlank(z856q)&&StringUtils.isNotEmpty(z856q))
                biblio4.set$q(z856q);
               if(StringUtils.isNotBlank(z856u)&&StringUtils.isNotEmpty(z856u))
                biblio4.set$u(z856u);
             if(StringUtils.isNotBlank(z856t)&&StringUtils.isNotEmpty(z856t))
                biblio4.set$t(z856t);
             if(StringUtils.isNotBlank(z856s)&&StringUtils.isNotEmpty(z856s))
                biblio4.set$s(z856s);
             if(StringUtils.isNotBlank(z856x)&&StringUtils.isNotEmpty(z856x))
                biblio4.set$x(z856x);
              if(StringUtils.isNotBlank(z856z)&&StringUtils.isNotEmpty(z856z))
                biblio4.set$z(z856z);

                   biblioid4.setBibId(bibid);
                       biblio4.setId(biblioid4);
//                       marchib.insert(biblio4);
if(hm1.containsKey("34")){
            hm1.remove("34");
        }
 hm1.put("34", biblio4);

       System.out.println("All five objects saved now NAvigating to page "+t);
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

        else if(t.equals("3"))
        {
 
                        System.out.println("3333333333    "+biblist.size());
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
           
                        System.out.println("6666666666666    "+biblist.size());
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
}
