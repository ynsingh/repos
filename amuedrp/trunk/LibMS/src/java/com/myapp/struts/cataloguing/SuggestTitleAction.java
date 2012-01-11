/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.cataloguing;

import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import fr.improve.struts.taglib.layout.suggest.SuggestAction;
import java.util.ArrayList;
import java.util.Iterator;
import com.myapp.struts.cataloguingDAO.BibliopgraphicEntryDAO;
import javax.servlet.http.HttpSession;
/**
 *
 * @author EdRP-05
 */
public class SuggestTitleAction extends SuggestAction {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    BibliopgraphicEntryDAO dao=new BibliopgraphicEntryDAO();
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
    public Collection getSuggestionList(HttpServletRequest hsr, String string) {
           
           HttpSession session = hsr.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
         Collection c=dao.suggestTitle(library_id, sub_library_id);
            System.out.println("FFFFFFFFFFFFFFFFFFFFFF"+c.size());
    ArrayList aa=new ArrayList();
    		if (string != null && string.length() > 0)
		{
			Iterator iter = c.iterator();
			while(iter.hasNext())
			{
				String currentWord = (String) iter.next();
				System.out.println("GGGGGGGGGGGGGGGGGGGGGG"+currentWord);
				if(currentWord.toLowerCase().startsWith(string.toLowerCase()))
				aa.add(currentWord);
			}
		}
     return aa;
    }
    }
