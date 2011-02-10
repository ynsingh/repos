/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;

import com.myapp.struts.MyQueryResult;
import java.sql.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Dushyant
 */
public class SearchInstituteAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    
    private String search_by;
    private String sort_by;
    private String search_keyword;
    private String sql;
    ResultSet rst;
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
        SearchInstituteActionForm institute=(SearchInstituteActionForm)form;
        search_by=institute.getSearch_by();
        sort_by=institute.getSort_by();
        search_keyword=institute.getSearch_keyword();
        sql="select a.*,b.library_id from admin_registration a inner join library b on a.registration_id=b.registration_id  where a."+search_by+" like '"+search_keyword+"%' order by a."+sort_by+" asc";
        rst=MyQueryResult.getMyExecuteQuery(sql);
        request.setAttribute("search_institute_resultset",rst );
        return mapping.findForward("institute_search");
    }
}
