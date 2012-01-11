/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.marc;

import com.myapp.struts.hbm.Biblio;
import com.myapp.struts.hbm.BiblioId;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
 *
 * @author EdRP-05
 */
public class UpdateSimpleMarcAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    Biblio bb=new Biblio();
    BiblioId bbid=new BiblioId();
    MarcHibDAO mdao=new MarcHibDAO();
    LinkedList al;
    LinkedList bl;
    LinkedList cl;
    LinkedList dl;
    LinkedList el;
    LinkedList fl;
    LinkedList gl;
    LinkedList hl;
    LinkedList il;
    LinkedList jl;
    LinkedList kl;
    LinkedList ll;
    LinkedList ml;
    LinkedList nl;
    LinkedList ol;
    LinkedList pl;
    LinkedList ql;
    LinkedList rl;
    LinkedList sl;
    LinkedList tl;
    LinkedList ul;
    LinkedList vl;
    LinkedList wl;
    LinkedList xl;
    LinkedList yl;
    LinkedList zl;
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
       EditMarcActionForm edf=(EditMarcActionForm)form;
       System.out.println("Get Values"+edf.getA());
       HttpSession session = request.getSession();
       if(edf.getA()!=null)
       {
           String[] a=edf.getA();
           al=new LinkedList(Arrays.asList(a)) ;
       }
       if(edf.getB()!=null)
       {
           String[] b=edf.getB();
           bl=new LinkedList(Arrays.asList(b));
       }
       if(edf.getC()!=null)
       {
           String[] c=edf.getC();
           cl=new LinkedList(Arrays.asList(c));
       }
       if(edf.getD()!=null)
       {
           String[] d=edf.getD();
           dl=new LinkedList(Arrays.asList(d));
       }
       if(edf.getE()!=null)
       {
           String[] e=edf.getE();
           el=new LinkedList(Arrays.asList(e));
       }
       if(edf.getF()!=null)
       {
           String[] f=edf.getF();
           fl=new LinkedList(Arrays.asList(f));
       }
       if(edf.getG()!=null)
       {
           String[] g=edf.getG();
           gl=new LinkedList(Arrays.asList(g));
       }
       if(edf.getH()!=null)
       {
           String[] h=edf.getH();
           hl=new LinkedList(Arrays.asList(h));
       }
       if(edf.getI()!=null)
       {
           String[] i=edf.getI();
           il=new LinkedList(Arrays.asList(i));
       }
       if(edf.getJ()!=null)
       {
           String[] j=edf.getJ();
           jl=new LinkedList(Arrays.asList(j));
       }
       if(edf.getK()!=null)
       {
           String[] k=edf.getK();
           kl=new LinkedList(Arrays.asList(k));
       }
       if(edf.getL()!=null)
       {
           String[] l=edf.getL();
           ll=new LinkedList(Arrays.asList(l));
       }
       if(edf.getM()!=null)
       {
           String[] m=edf.getM();
           ml=new LinkedList(Arrays.asList(m));
       }
       if(edf.getN()!=null)
       {
           String[] n=edf.getN();
           nl=new LinkedList(Arrays.asList(n));
       }
       if(edf.getO()!=null)
       {
           String[] o=edf.getO();
           ol=new LinkedList(Arrays.asList(o));
       }
       if(edf.getP()!=null)
       {
           String[] p=edf.getP();
           pl=new LinkedList(Arrays.asList(p));
       }
       if(edf.getQ()!=null)
       {
           String[] q=edf.getQ();
           ql=new LinkedList(Arrays.asList(q));
       }
       if(edf.getR()!=null)
       {
           String[] r=edf.getR();
           rl=new LinkedList(Arrays.asList(r));
       }
       if(edf.getS()!=null)
       {
           String[] s=edf.getS();
           sl=new LinkedList(Arrays.asList(s));
       }
       if(edf.getT()!=null)
       {
          String[] t=edf.getT();
          tl=new LinkedList(Arrays.asList(t));
       }
       if(edf.getU()!=null)
       {
           String[] u=edf.getU();
           ul=new LinkedList(Arrays.asList(u));
       }
       if(edf.getV()!=null)
       {
           String[] v=edf.getV();
           vl=new LinkedList(Arrays.asList(v));
       }
       if(edf.getW()!=null)
       {
           String[] w=edf.getW();
           wl=new LinkedList(Arrays.asList(w));
       }
       if(edf.getX()!=null)
       {
           String[] x=edf.getX();
           xl=new LinkedList(Arrays.asList(x));
       }
       if(edf.getY()!=null)
       {
           String[] y=edf.getY();
           yl=new LinkedList(Arrays.asList(y));
       }
       if(edf.getZ()!=null)
       {
           String[] z=edf.getZ();
           zl=new LinkedList(Arrays.asList(z));
       }
       String library_id = (String) session.getAttribute("library_id");
       String sub_library_id = (String) session.getAttribute("sublibrary_id");
       List<Biblio> bib=(List<Biblio>)session.getAttribute("editlist");
       for(int in=0;in<bib.size();in++){
       String marctag=bib.get(in).getId().getMarctag();
       if(bib.get(in).get$a()!=null){
       bb.set$a(al.getFirst().toString());
       al.removeFirst();
       }
       if(bib.get(in).get$b()!=null){
       bb.set$b(bl.getFirst().toString());
       bl.removeFirst();
       }
       if(bib.get(in).get$c()!=null){
       bb.set$c(cl.getFirst().toString());
       cl.removeFirst();
       }
       if(bib.get(in).get$d()!=null){
       bb.set$d(dl.getFirst().toString());
       dl.removeFirst();
       }
       if(bib.get(in).get$e()!=null){
       bb.set$e(el.getFirst().toString());
       el.removeFirst();
       }
       if(bib.get(in).get$f()!=null){
       bb.set$f(fl.getFirst().toString());
       fl.removeFirst();
       }
       if(bib.get(in).get$g()!=null){
       bb.set$g(gl.getFirst().toString());
       gl.removeFirst();
       }
       if(bib.get(in).get$h()!=null){
       bb.set$h(hl.getFirst().toString());
       hl.removeFirst();
       }
       if(bib.get(in).get$i()!=null){
       bb.set$i(il.getFirst().toString());
       il.removeFirst();
       }
       if(bib.get(in).get$j()!=null){
       bb.set$j(jl.getFirst().toString());
       jl.removeFirst();
       }
       if(bib.get(in).get$k()!=null){
       bb.set$k(kl.getFirst().toString());
       kl.removeFirst();
       }
       if(bib.get(in).get$l()!=null){
       bb.set$l(ll.getFirst().toString());
       ll.removeFirst();
       }
       if(bib.get(in).get$m()!=null){
       bb.set$m(ml.getFirst().toString());
       ml.removeFirst();
       }
       if(bib.get(in).get$o()!=null){
       bb.set$o(ol.getFirst().toString());
       ol.removeFirst();
       }
       if(bib.get(in).get$p()!=null){
       bb.set$p(pl.getFirst().toString());
       pl.removeFirst();
       }
       if(bib.get(in).get$q()!=null){
       bb.set$q(ql.getFirst().toString());
       ql.removeFirst();
       }
       if(bib.get(in).get$r()!=null){
       bb.set$r(rl.getFirst().toString());
       rl.removeFirst();
       }
       if(bib.get(in).get$s()!=null){
       bb.set$s(sl.getFirst().toString());
       sl.removeFirst();
       }
       if(bib.get(in).get$t()!=null){
       bb.set$t(tl.getFirst().toString());
       tl.removeFirst();
       }
       if(bib.get(in).get$u()!=null){
       bb.set$u(ul.getFirst().toString());
       ul.removeFirst();
       }
       if(bib.get(in).get$v()!=null){
       bb.set$v(vl.getFirst().toString());
       vl.removeFirst();
       }
       if(bib.get(in).get$w()!=null){
       bb.set$w(wl.getFirst().toString());
       wl.removeFirst();
       }
       if(bib.get(in).get$x()!=null){
       bb.set$x(xl.getFirst().toString());
       xl.removeFirst();
       }
       if(bib.get(in).get$y()!=null){
       bb.set$y(yl.getFirst().toString());
       yl.removeFirst();
       }
       if(bib.get(in).get$z()!=null){
       bb.set$z(zl.getFirst().toString());
       zl.removeFirst();
       }
       bb.setSublibraryId(sub_library_id);
       bbid.setBibId(bib.get(in).getId().getBibId());
       bbid.setLibraryId(library_id);
       bbid.setMarctag(bib.get(in).getId().getMarctag());
       bb.setId(bbid);
       mdao.update(bb);
       }
        return mapping.findForward(SUCCESS);
    }
}
