/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import com.myapp.struts.hbm.BibliographicDetails;
import com.myapp.struts.hbm.BibliographicDetailsLang;
import com.myapp.struts.hbm.DocumentDetails;
import com.myapp.struts.cataloguingDAO.BibliopgraphicEntryDAO;
import java.util.ArrayList;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.opacDAO.OpacSearchDAO;
import java.util.List;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Faraz
 */
public class browseSearchAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    OpacSearchDAO opacSearchDAO=new OpacSearchDAO();
     BibliopgraphicEntryDAO bibdao=new BibliopgraphicEntryDAO();
    String phrase,title,author,accno;
    String doc_type,id,callno,publ,loc,place,sort,field,sublib_id,lib_id;
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session=request.getSession();
        session.removeAttribute("Result");
          request.setCharacterEncoding("UTF-8");
        browseSearchActionForm myform = (browseSearchActionForm)form;
        lib_id= myform.getCMBLib();
        sublib_id=myform.getCMBSUBLib();
        phrase=myform.getTXTTITLE();
        doc_type=myform.getCMBDB();
        sort=myform.getCMBSORT();
        field=myform.getCMBFIELD();
        int flag=0;
        System.out.println("lib_id="+lib_id+"sublib_id="+sublib_id+"phrase="+phrase+"doc_type="+doc_type+"sort="+sort+"field="+field+"...................");
        //public List browseSearch(String library_id,String sub_lib,String searching_word,String doc_type,String sortby,String searching_field)

        List<BibliographicDetailsLang> browse_search_list=new ArrayList();
 List<BibliographicDetails> browse_search_list1=new ArrayList();
  ArrayList<BibliographicDetailsLang> bib=new ArrayList<BibliographicDetailsLang>();
 ArrayList<BibliographicDetails> bib1=new ArrayList<BibliographicDetails>();
 System.out.println("Check Box"+myform.getCheckbox());
 if(myform.getCheckbox().equals("Checked"))
 {
 System.out.println("Languagebbb");
 browse_search_list=opacSearchDAO.browseLangSearch(lib_id, sublib_id,phrase, doc_type, sort, field,myform.getLanguage().toUpperCase());
if(!browse_search_list.isEmpty())
{
    for(int f=0;f<browse_search_list.size();f++)
    {
    int bibiid=browse_search_list.get(f).getId().getBiblioId();
    List<DocumentDetails> ddd=bibdao.searchDoc2(bibiid, lib_id, sublib_id);
    if(!ddd.isEmpty()){
        bib.add(browse_search_list.get(f));
    }     
    }

}
  System.out.println(bib.size());
  session.removeAttribute("browse_search_list");
  session.setAttribute("browse_search_list1", bib);
 }
 else{
     System.out.println("NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNLanguagebbb"+lib_id+sublib_id+phrase+doc_type+sort+field);
 browse_search_list1=opacSearchDAO.browseSearch(lib_id, sublib_id,phrase, doc_type, sort, field);
 System.out.println(browse_search_list1+".............");
if(!browse_search_list1.isEmpty())
{
    for(int f=0;f<browse_search_list1.size();f++)
    {
    int bibiid=browse_search_list1.get(f).getId().getBiblioId();
    List<DocumentDetails> ddd=bibdao.searchDoc1(bibiid, lib_id, sublib_id);
    System.out.println(ddd.size()+"   "+bibiid+lib_id+sublib_id);
    if(!ddd.isEmpty()){
        bib1.add(browse_search_list1.get(f));
    }      
    }

}
 System.out.println(bib1.size());
 session.removeAttribute("browse_search_list1");
session.setAttribute("browse_search_list", bib1);
 }
      //  List browse_search_list=opacSearchDAO.browseSearch(lib_id, sublib_id,phrase, doc_type, sort, field);



 //   session.setAttribute("browse_search_list",browse_search_list);




        /*
                query="select * from document_details";
     
        if(db.equals("combined")==false){query=query+" where document_type='"+db+"' and";flag=1;}

     if (flag==0) query += " where ";
    if(field.equals("any field"))
       {
         
         query=query+" ( author_main like '%"+phrase+"%' or title like '%"+phrase+"%' or  publishing_year like '%"+phrase+"%' " +
                    "or publisher_name like '%"+phrase+"%' or publication_place like '%"+phrase+"%')";


     }
     else {query=query+" "+field+" like'%"+phrase+"%'";}
if (!lib_id.equalsIgnoreCase("all")) query += " and library_id='" + lib_id + "'";
    
               query=query+" order by "+sort+" asc";

System.out.println("query="+query);
     rs = MyQueryResult.getMyExecuteQuery(query);
     
     if(rs.next())
  {

  session.setAttribute("Result", rs);
  session.setAttribute("database",db );
    }
     */

        return mapping.findForward(SUCCESS);
    }
}
