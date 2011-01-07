/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;
import java.sql.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.sql.*;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Faraz
 */
public class browseSearchAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
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
        HttpSession session=request.getSession();
        session.removeAttribute("Result");

        String phrase,title,author,accno;
        String db,id,callno,publ,loc,place,sort,field,query="";
        ResultSet rs=null;
        browseSearchActionForm myform = (browseSearchActionForm)form;
         String lib_id= myform.getCMBLib();
        phrase=myform.getTXTTITLE();
        db=myform.getCMBDB();
        sort=myform.getCMBSORT();
        field=myform.getCMBFIELD();

    query="select * from document";
    if(db.equals("combined")){query=query+" where db_category='combined'";}
      else                     {query=query+" where category='"+db+"'";}

     if(phrase.equals("")){phrase="";}


     if(field.equals("any field"))
       {query=query+" and( author like '%"+phrase+"%' or title like '%"+phrase+"%' or subject like '%"+phrase+"%' or pub_yr like '%"+phrase+"%' " +
                    "or publisher like '%"+phrase+"%' or accessionno like '%"+phrase+"%' or pubPlace like '%"+phrase+"%')";}

    

     else {query=query+" and "+field+" like'%"+phrase+"%'";}
if (!lib_id.equals("all")) query += " and library_id='" + lib_id + "'";
    
               query=query+" order by "+sort+" asc";


     rs = MyQueryResult.getMyExecuteQuery(query);
     if (rs.next()) session.setAttribute("Result", rs);
        return mapping.findForward(SUCCESS);
    }
}
