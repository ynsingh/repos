/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Administration;
/**
 *
 * @author afreen
 */

import pojo.hibernate.LanguageMaster;
import pojo.hibernate.LanguageMasterDAO;
import utils.DevelopmentSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
public class IndexAction extends DevelopmentSupport {
    
    private String message;
     
  
   private LanguageMasterDAO LangDao = new LanguageMasterDAO();
   private List<LanguageMaster> LangList = new ArrayList<LanguageMaster>();
    public void setMesssge(String message) {
            this.message = message;
    }

     public String getMessage() {
        return message;
    }
 public void setLangList( List<LanguageMaster> LangList) {
            this.LangList = LangList;
    }

     public  List<LanguageMaster> getLangList() {
        return LangList;
    }
    @Override
    public String execute() throws Exception {
       try {

        getSession().setAttribute("request_locale", Locale.ENGLISH);
       LangList=LangDao.findAll();
       //message="hello"+LangList.get(0).getLangId();

           return SUCCESS;
        }
   catch (Exception e)
   {
   message = message + e.getCause() + "  " + e.getMessage();
   return ERROR;
   }

}
}
