/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;
import  com.myapp.struts.*;
import java.sql.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.sql.*;
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
    String phrase,title,author,accno;
    String doc_type,id,callno,publ,loc,place,sort,field,sublib_id,lib_id;
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
    
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session=request.getSession();
        session.removeAttribute("Result");
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
    List browse_search_list=opacSearchDAO.browseSearch(lib_id, sublib_id,phrase, doc_type, sort, field);
    session.setAttribute("browse_search_list",browse_search_list);




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
