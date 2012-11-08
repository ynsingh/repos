/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.cataloguing;

import com.myapp.struts.cataloguingDAO.BibliographicEntryDAO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author EdRP-05
 */
public class SuggestTitle {
  public List<String> getSuggestionList(String string) {
  BibliographicEntryDAO dao=new BibliographicEntryDAO();
  HttpServletRequest hsr=null;
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
