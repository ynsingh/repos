/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.marc;

import com.myapp.struts.hbm.Biblio;
import com.myapp.struts.hbm.BiblioId;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.util.ArrayList;
import org.marc4j.marc.MarcFactory;
import org.apache.commons.lang.StringUtils;
import org.marc4j.marc.Leader;
import org.marc4j.marc.Record;
import org.marc4j.marc.DataField;
import com.myapp.struts.cataloguingDAO.BibliographicEntryDAO;
import com.myapp.struts.hbm.BibliographicDetails;
import com.myapp.struts.hbm.BibliographicDetailsId;
import java.util.Map;
public class MarcCommitAction1 extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    
     MarcHibDAO marchib=new MarcHibDAO();
    HashMap hm1=new HashMap();
  BibliographicDetails bibd=new BibliographicDetails();
   BibliographicDetailsId biblid=new BibliographicDetailsId();

   BibliographicEntryDAO dao=new BibliographicEntryDAO();
   MarcHibDAO mhd=new MarcHibDAO();

        HttpSession session=request.getSession();
            Object b=session.getAttribute("biblio_id");
            String library_id = (String) session.getAttribute("library_id");
            String sub_library_id = (String) session.getAttribute("sublibrary_id");

            int bibid=0;
            try{
            bibid =(Integer)b;
            }catch(Exception e){
            bibid=Integer.parseInt((String)b);
            }

        
            hm1=(HashMap) session.getAttribute("hsmp");    
            Biblio leaderobj=new Biblio();
            Set set1=hm1.keySet();
            Iterator is1=set1.iterator();
            while(is1.hasNext())
            {

                String key=(String)is1.next();
                Biblio bib=new Biblio();
                bib=(Biblio)hm1.get(key);
                System.out.println(key+"  ############ "+bib.get$a());
    if(key.equalsIgnoreCase("Leader"))
    {
    leaderobj=(Biblio)hm1.get("Leader");
    if(leaderobj==null)
    {
      request.setAttribute("msg1","Please Select Control Field Data");
      boolean m=mhd.deleteMarcBiblio(String.valueOf(bibid),library_id,sub_library_id);
      return mapping.findForward("controltag");
    }
    }

                if(key.equals("5")&& bib.get$a()==null){
                

                    request.setAttribute("msg1","Please Enter Value in 082 $a Field");

                      boolean m=mhd.deleteMarcBiblio(String.valueOf(bibid),library_id,sub_library_id);

                return mapping.findForward("forward0");
                }
                else if(key.equals("5")&& bib.get$a()!=null){
                    System.out.println("........................New Changes"+key+bib.get$a());
                Biblio bnbb=marchib.searchMarcCall(bibid, library_id, sub_library_id, bib.get$a());

                if(bnbb!=null)
                {
                   
                request.setAttribute("msg1","Please enter different value in 082 $a field");
                  boolean m=mhd.deleteMarcBiblio(String.valueOf(bibid),library_id,sub_library_id);
                return mapping.findForward("forward0");
                }

                }
                else if(key.equals("6")&& bib.get$a()==null){
                   
                    request.setAttribute("msg1","Please Enter Value in 100 $a Field");
                      boolean m=mhd.deleteMarcBiblio(String.valueOf(bibid),library_id,sub_library_id);
                return mapping.findForward("forward1");
                }
                else if(key.equals("10")&& bib.get$a()==null){
             
                    request.setAttribute("msg1","Please Enter Value in 245 $a Field");
                     boolean m=mhd.deleteMarcBiblio(String.valueOf(bibid),library_id,sub_library_id);
                return mapping.findForward("forward2");
                }
                else if(key.equals("10")&& bib.get$c()==null){
              
                    request.setAttribute("msg1","Please Enter Value in 245 $c Field");
                      boolean m=mhd.deleteMarcBiblio(String.valueOf(bibid),library_id,sub_library_id);
                return mapping.findForward("forward2");
                }



            }
//
            Set set=hm1.entrySet();
            Iterator is=set.iterator();
          List<Biblio> finalmarcdata=new ArrayList<Biblio>();
            while(is.hasNext())
            {
                Map.Entry me = (Map.Entry)is.next();
                Biblio bib=new Biblio();
                bib=(Biblio)hm1.get(me.getKey());
                if(bib.getId().getMarctag().equals("001")){
                bibd.setLccNo(bib.get$a());
                }
                if(bib.getId().getMarctag().equals("020")){
                bibd.setIsbn10(bib.get$a());
                }
                if(bib.getId().getMarctag().equals("100")){
                bibd.setMainEntry(bib.get$a());
                }
                if(bib.getId().getMarctag().equals("245")){
                bibd.setTitle(bib.get$a());
                bibd.setSubtitle(bib.get$b());
                bibd.setStatementResponsibility(bib.get$c());
                }
                if(bib.getId().getMarctag().equals("250")){
                bibd.setEdition(bib.get$a());
                }
                if(bib.getId().getMarctag().equals("260")){
                bibd.setPublicationPlace(bib.get$a());
                bibd.setPublisherName(bib.get$b());
                if(bib.get$c()!=null)
                bibd.setPublishingYear(Integer.parseInt(bib.get$c()));
                else
                    bibd.setPublishingYear(0);
                }
                if(bib.getId().getMarctag().equals("490")){
                bibd.setSeries(bib.get$a());
                }
                if(bib.getId().getMarctag().equals("600")){
                bibd.setAddedEntry(bib.get$a());
                }
                if(bib.getId().getMarctag().equals("082")){
                bibd.setCallNo(bib.get$a());
                String call_no=bib.get$a();
                BibliographicDetails bbd=dao.searchcall(call_no, library_id, sub_library_id);
                if(bbd!=null){
                        request.setAttribute("msg1","Please enter different value in 082 $a field");
                        boolean m=mhd.deleteMarcBiblio(String.valueOf(bibid),library_id,sub_library_id);
                        return mapping.findForward("forward0");
                }
                else{
                biblid.setBiblioId(dao.returnMaxBiblioId(library_id, sub_library_id));
                biblid.setLibraryId(library_id);
                biblid.setSublibraryId(sub_library_id);
                bibd.setDocumentType("Book");

                bibd.setId(biblid);
                dao.insert(bibd);
                }
                }
              System.out.println(bib.getId().getMarctag()+" At First "+me.getKey());
           finalmarcdata.add(bib);
              

            }
System.out.println(finalmarcdata.size()+"Total Record");
if(finalmarcdata.isEmpty()==false)
{
    
for(int i=0;i<finalmarcdata.size();i++)
{System.out.println(" List Has"+finalmarcdata.get(i).getId().getMarctag());
    
    marchib.insert(finalmarcdata.get(i));
}

}
             

            // create a factory instance
MarcFactory factory = MarcFactory.newInstance();
// create a record with leader
Record record = factory.newRecord();
StringBuffer gg=new StringBuffer();
int high=0;
System.out.println("Bib Id:::::::::"+bibid);
System.out.println("Lib Id:::::::::"+library_id);
System.out.println("Sub Lib Id:::::::::"+sub_library_id);
// add a control field
//record.addVariableField(factory.newControlField("001", "12883376"));
List<Biblio> lbb=marchib.searchDoc1(bibid, library_id, sub_library_id);

System.out.println("Bib Size"+lbb.size());
for(int g=0;g<lbb.size();g++){
String marctag=lbb.get(g).getId().getMarctag();
if(marctag.equals("Leader")){
continue;
}
//ControlField cf=factory.newControlField(marctag);
//List<ControlField> lcf=new ArrayList<ControlField>();

if(marctag.equals("001")){
record.addVariableField(factory.newControlField("001", lbb.get(g).get$a()));
gg.append(lbb.get(g).get$a());
}
if(marctag.equals("003")){
record.addVariableField(factory.newControlField("003", lbb.get(g).get$a()));
gg.append(lbb.get(g).get$a());
}
if(marctag.equals("005")){
record.addVariableField(factory.newControlField("005", lbb.get(g).get$a()));
gg.append(lbb.get(g).get$a());
}
if(marctag.equals("007")){
record.addVariableField(factory.newControlField("007", lbb.get(g).get$a()));
gg.append(lbb.get(g).get$a());
}

char ind1= '\u0020';
char ind2='\u0020';

if(lbb.get(g).getIndicator1()!=null && !StringUtils.isBlank(lbb.get(g).getIndicator1().toString()))
{
  ind1=lbb.get(g).getIndicator1();
  gg.append(lbb.get(g).getIndicator1());
}
if(lbb.get(g).getIndicator2()!=null && !StringUtils.isBlank(lbb.get(g).getIndicator2().toString()))
{
    ind2=lbb.get(g).getIndicator2();
    gg.append(lbb.get(g).getIndicator1());
}
DataField df = factory.newDataField(marctag, ind1, ind2);
List<DataField> ldf=new ArrayList<DataField>();
{
if(lbb.get(g).get$a()!=null)
{
  // System.out.println(lbb.get(g).get$a());
    df.addSubfield(factory.newSubfield('a', lbb.get(g).get$a()));
    //record.addVariableField(df);
gg.append(lbb.get(g).get$a());

}
if(lbb.get(g).get$b()!=null)
{
    df.addSubfield(factory.newSubfield('b', lbb.get(g).get$b()));
    //record.addVariableField(df);
gg.append(lbb.get(g).get$b());
}
if(lbb.get(g).get$c()!=null)
{
    df.addSubfield(factory.newSubfield('c', lbb.get(g).get$c()));
    //record.addVariableField(df);
gg.append(lbb.get(g).get$c());
}
if(lbb.get(g).get$d()!=null)
{
    df.addSubfield(factory.newSubfield('d', lbb.get(g).get$d()));
    //record.addVariableField(df);
gg.append(lbb.get(g).get$d());
}
if(lbb.get(g).get$e()!=null)
{
    df.addSubfield(factory.newSubfield('e', lbb.get(g).get$e()));
    //record.addVariableField(df);
gg.append(lbb.get(g).get$e());
}
if(lbb.get(g).get$f()!=null)
{
    df.addSubfield(factory.newSubfield('f', lbb.get(g).get$f()));
    //record.addVariableField(df);
gg.append(lbb.get(g).get$f());
}
if(lbb.get(g).get$g()!=null)
{
    df.addSubfield(factory.newSubfield('g', lbb.get(g).get$g()));
    //record.addVariableField(df);
gg.append(lbb.get(g).get$g());
}
if(lbb.get(g).get$h()!=null)
{
    df.addSubfield(factory.newSubfield('h', lbb.get(g).get$h()));
    //record.addVariableField(df);
gg.append(lbb.get(g).get$h());
}
if(lbb.get(g).get$i()!=null)
{
    df.addSubfield(factory.newSubfield('i', lbb.get(g).get$i()));
  //  record.addVariableField(df);
gg.append(lbb.get(g).get$i());
}
if(lbb.get(g).get$j()!=null)
{
    df.addSubfield(factory.newSubfield('j', lbb.get(g).get$j()));
//    record.addVariableField(df);
gg.append(lbb.get(g).get$j());
}
if(lbb.get(g).get$k()!=null)
{
    df.addSubfield(factory.newSubfield('k', lbb.get(g).get$k()));
   // record.addVariableField(df);
gg.append(lbb.get(g).get$k());
}
if(lbb.get(g).get$l()!=null)
{
    df.addSubfield(factory.newSubfield('l', lbb.get(g).get$l()));
        //record.addVariableField(df);
gg.append(lbb.get(g).get$l());
}
if(lbb.get(g).get$m()!=null)
{
    df.addSubfield(factory.newSubfield('m', lbb.get(g).get$m()));
        //record.addVariableField(df);
gg.append(lbb.get(g).get$m());
}
if(lbb.get(g).get$n()!=null)
{
    df.addSubfield(factory.newSubfield('n', lbb.get(g).get$n()));
        //record.addVariableField(df);
gg.append(lbb.get(g).get$n());
}
if(lbb.get(g).get$o()!=null)
{
    df.addSubfield(factory.newSubfield('o', lbb.get(g).get$o()));
        //record.addVariableField(df);
gg.append(lbb.get(g).get$o());
}
if(lbb.get(g).get$p()!=null)
{
    df.addSubfield(factory.newSubfield('p', lbb.get(g).get$p()));
        //record.addVariableField(df);
gg.append(lbb.get(g).get$p());
}
if(lbb.get(g).get$q()!=null)
{
    df.addSubfield(factory.newSubfield('q', lbb.get(g).get$q()));
        //record.addVariableField(df);
gg.append(lbb.get(g).get$q());
}
if(lbb.get(g).get$r()!=null)
{
    df.addSubfield(factory.newSubfield('r', lbb.get(g).get$r()));
        //record.addVariableField(df);
gg.append(lbb.get(g).get$r());
}
if(lbb.get(g).get$s()!=null)
{
    df.addSubfield(factory.newSubfield('s', lbb.get(g).get$s()));
        //record.addVariableField(df);
gg.append(lbb.get(g).get$s());
}
if(lbb.get(g).get$t()!=null)
{
    df.addSubfield(factory.newSubfield('t', lbb.get(g).get$t()));
        //record.addVariableField(df);
gg.append(lbb.get(g).get$t());
}
if(lbb.get(g).get$u()!=null)
{
    df.addSubfield(factory.newSubfield('u', lbb.get(g).get$u()));
        //record.addVariableField(df);
gg.append(lbb.get(g).get$u());
}
if(lbb.get(g).get$v()!=null)
{
    df.addSubfield(factory.newSubfield('v', lbb.get(g).get$v()));
        //record.addVariableField(df);
gg.append(lbb.get(g).get$v());
}
if(lbb.get(g).get$w()!=null)
{
    df.addSubfield(factory.newSubfield('w', lbb.get(g).get$w()));
      //  record.addVariableField(df);
gg.append(lbb.get(g).get$w());
}
if(lbb.get(g).get$x()!=null)
{
    df.addSubfield(factory.newSubfield('x', lbb.get(g).get$x()));
       // record.addVariableField(df);
gg.append(lbb.get(g).get$x());
}
if(lbb.get(g).get$y()!=null)
{
    df.addSubfield(factory.newSubfield('y', lbb.get(g).get$y()));
       // record.addVariableField(df);
gg.append(lbb.get(g).get$y());
}
if(lbb.get(g).get$z()!=null)
{
    df.addSubfield(factory.newSubfield('z', lbb.get(g).get$z()));
     //   record.addVariableField(df);
gg.append(lbb.get(g).get$z());
}
   ldf.add(df);
}
for(int k=0;k<ldf.size();k++){
record.addVariableField(ldf.get(k));
}
gg.append("^");
}
high=12*lbb.size()+25;
int subfieldl=gg.length();
//System.out.println("Sub field length      :"+subfieldl+"Leeeeeeeeeee         :"+high);
int high1=high+subfieldl;
int total_length[]=new int[5];
for(int ii=0;ii<5;ii++){
total_length[ii]=0;
}
int n=4;
while(high1>0)
{
    total_length[n--]=high1%10;
    high1/=10;
}
int record_length[]=new int[5];
for(int ii=0;ii<5;ii++){
record_length[ii]=0;
}
int nn=4;
while(high>0)
{
    record_length[nn--]=high%10;
    high/=10;
}
//System.out.println("KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK"+total_length[0]+total_length[1]+total_length[2]+total_length[3]+total_length[4]);

if(leaderobj==null){
      request.setAttribute("msg1","Please Select Control Field Data");
      boolean m=mhd.deleteMarcBiblio(String.valueOf(bibid),library_id,sub_library_id);
      return mapping.findForward("controltag");
}
String leader1=(String)leaderobj.get$a();
//***********************************************Check of Control Field Entry**************************//
if(leader1==null)
{
      request.setAttribute("msg1","Please Select Control Field Data");
      boolean m=mhd.deleteMarcBiblio(String.valueOf(bibid),library_id,sub_library_id);
      return mapping.findForward("controltag");
}

char leader[]=new char[24];
for(int ii=0;ii<24;ii++){
leader[ii]=leader1.charAt(ii);
}
for(int ff=0;ff<5;ff++)
    {
   String bb=String.valueOf(total_length[ff]);
   leader[ff]=bb.charAt(0);
    // leader[ff]=String.valueOf(total_length[ff]);
    }
n=0;
for(int ff=12;ff<17;ff++)
{
    String cc=String.valueOf(record_length[n++]);
    leader[ff]=cc.charAt(0);
    //leader[ff]=String.valueOf(record_length[n++]);
}
StringBuffer result = new StringBuffer();
    if (leader.length > 0) {
        result.append(leader[0]);
        for (int i=1; i < leader.length; i++) {
             result.append(leader[i]);
        }
    }
//System.out.println("Leader::::::::::::::::::::::"+leader[0].toString()+leader[1].toString()+leader[2].toString()+leader[3].toString()+leader[4].toString()+"Record     :"+leader[12].toString()+leader[13].toString()+leader[14].toString()+leader[15].toString()+leader[16].toString());
System.out.println("Leader       :"+result.toString());
System.out.println("Record       :"+record.toString());
//record.addVariableField(df);
Leader ll=factory.newLeader(result.toString());
System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM"+ll.toString());
record.setLeader(ll);
System.out.println("Totalllllllllllllllll"+record.toString());
Biblio bibr=new Biblio();
BiblioId bibrid=new BiblioId();
bibrid.setBibId(bibid);
bibrid.setLibraryId(library_id);
bibrid.setMarctag("Leader");
bibr.setId(bibrid);
bibr.setSublibraryId(sub_library_id);
bibr.set$a(ll.toString());
marchib.update(bibr);



 
 

         HashMap t=(HashMap)session.getAttribute("hsmp");
        if(t!=null && t.isEmpty()==false)
        {System.out.println("Get Value"+t.size());

          t.clear();
          System.out.println("Get Value"+t.size());
          session.setAttribute("hsmp",t);
         }
        hm1=null;
        System.out.println(session.getAttribute("hsmp")+".....................");
        session.removeAttribute("controltag");
        session.removeAttribute("st");
        session.removeAttribute("data");
        session.removeAttribute("editlist");
        session.removeAttribute("tag0");
        session.removeAttribute("tag1");
        session.removeAttribute("tag2");
        session.removeAttribute("tag3");
        session.removeAttribute("tag4");
        session.removeAttribute("tag5");
        session.removeAttribute("tag6");
        session.removeAttribute("tag7");
        session.removeAttribute("tag8");

        session.removeAttribute("data");
        session.removeAttribute("st");
        session.removeAttribute("controltag");
        session.removeAttribute("tag0");
        session.removeAttribute("tag1");
        session.removeAttribute("tag2");
        session.removeAttribute("tag3");
        session.removeAttribute("tag4");
        session.removeAttribute("tag5");
        session.removeAttribute("tag6");
        session.removeAttribute("tag7");
        session.removeAttribute("tag8");
        session.removeAttribute("hsmp");
        session.removeAttribute("marcbutton");
                session.removeAttribute("biblio_id");
                session.removeAttribute("title");
  session.removeAttribute("opacList");
        request.removeAttribute("BiblioActionForm");
         request.removeAttribute("CatControlActionForm");
          request.removeAttribute("CatActionForm1");
           request.removeAttribute("CatActionForm2");
            request.removeAttribute("CatActionForm2");
             request.removeAttribute("CatActionForm3");
              request.removeAttribute("CatActionForm4");
               request.removeAttribute("CatActionForm5");
request.removeAttribute("CatActionForm6");
request.removeAttribute("CatActionForm7");
request.removeAttribute("CatActionForm8");

   return mapping.findForward("success");
     
    }

}

