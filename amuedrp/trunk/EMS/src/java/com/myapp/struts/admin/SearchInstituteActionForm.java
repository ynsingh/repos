/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author Dushyant
 */
public class SearchInstituteActionForm extends org.apache.struts.action.ActionForm {
    
    private String search_by;
    private String sort_by;
    private String search_keyword;

   
    /**
     *
     */
    public SearchInstituteActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
  
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
       
        return null;
    }

    /**
     * @return the search_by
     */
    public String getSearch_by() {
        return search_by;
    }

    /**
     * @param search_by the search_by to set
     */
    public void setSearch_by(String search_by) {
        this.search_by = search_by;
    }

    /**
     * @return the sort_by
     */
    public String getSort_by() {
        return sort_by;
    }

    /**
     * @param sort_by the sort_by to set
     */
    public void setSort_by(String sort_by) {
        this.sort_by = sort_by;
    }

    /**
     * @return the search_keyword
     */
    public String getSearch_keyword() {
        return search_keyword;
    }

    /**
     * @param search_keyword the search_keyword to set
     */
    public void setSearch_keyword(String search_keyword) {
        this.search_keyword = search_keyword;
    }

    /**
     * @return the search_keyword
     */
    
}
