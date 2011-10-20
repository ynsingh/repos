/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;

import com.myapp.struts.AdminDAO.LibraryDAO;
import com.myapp.struts.AdminDAO.LogsDAO;
import com.myapp.struts.hbm.Logs;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author Dushyant
 */
public class LogInstituteAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    
    private String search_by;
    private String sort_by;
    private String search_keyword;
    private String sql;
   List rst;
   List<Item> readConfig;
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        SearchInstituteActionForm institute=(SearchInstituteActionForm)form;
        search_by=institute.getSearch_by();
        sort_by=institute.getSort_by();
        HttpSession session=request.getSession();
        search_keyword=institute.getSearch_keyword();
    System.out.println("alok..........."+search_by+"   "+search_keyword);

      

        ReadLog read = new ReadLog();

      String path = servlet.getServletContext().getRealPath("/");

   String os=(String)System.getProperty("os.name");
   System.out.println("OS----------->"+os);
   if(os.startsWith("Linux"))
   {

path=path.substring(0,path.lastIndexOf("/"));
path=path.substring(0,path.lastIndexOf("/"));
path=path.substring(0,path.lastIndexOf("/"));
   }
   else{
path=path.substring(0,path.lastIndexOf("\\"));
path=path.substring(0,path.lastIndexOf("\\"));
path=path.substring(0,path.lastIndexOf("\\"));


   }

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

    Document doc = null;

  try
   {

   factory.setAttribute(
    "http://java.sun.com/xml/jaxp/properties/schemaLanguage",
   "http://www.w3.org/2001/XMLSchema");
   factory.setAttribute(
   "http://java.sun.com/xml/jaxp/properties/schemaSource",
   "logrule.xsd");
   DocumentBuilder parser = factory.newDocumentBuilder();
   if(os.startsWith("Linux")){
   doc = parser.parse(path+"/web/logs/list.xml");
   }
   else{
   doc = parser.parse(path+"\\web\\logs\\list.xml");
   }
   }
   catch (ParserConfigurationException e)
   {
  e.printStackTrace();
   return null;
  }
   catch(SAXException e)
  {
   e.printStackTrace();
    return null;
   }
   catch (IOException e)
   {
   e.printStackTrace();
    return null;
   }
  
 if(os.startsWith("Linux"))
   {
	readConfig  = read.readConfig(path+"/web/logs/list.xml");
 }else{
 	readConfig  = read.readConfig(path+"\\web\\logs\\list.xml");
 }
		for (Item item : readConfig) {
			System.out.println(item);
		}

if(!search_keyword.equals(""))
               readConfig=getItem(search_by, search_keyword);


 session.setAttribute("search_institute_resultset1",readConfig );

        return mapping.findForward("institute_search");






    
    }

    private List<Item> getItem(String search_by1,String  search_keyword1){
        List<Item> log = new ArrayList<Item>();
        List<Item> log1=new ArrayList<Item>();
        int length=readConfig.size();
        System.out.println(length);
        int i=0,j=0;

        
        

        while(i<length)
        {
            if(search_by1.equalsIgnoreCase("library_id"))
            {
                if(readConfig.get(i).getLibrary_id().equals(search_keyword1)){
                j=1;
                log1.add(readConfig.get(i));

                }

            }
            else if(search_by1.equalsIgnoreCase("user_id"))
            {
                if(readConfig.get(i).getUserid().equals(search_keyword1)){
                j=1;
                log1.add(readConfig.get(i));
            
                }
            }
          

           i++;
        }
        


if(j==0){
                 log1=null;
    }
        return log1;
}//end getDog


}
