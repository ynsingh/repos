/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Administration;
/**
 *
 * @author afreen
 */

//import pojo.hibernate.LanguageMaster;
//import pojo.hibernate.LanguageMasterDAO;
import utils.DevelopmentSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import pojo.hibernate.ErpmNews;
import pojo.hibernate.ErpmNewsDAO;
import utils.DateUtilities;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class IndexAction extends DevelopmentSupport {
    
    private String message;
    private List<ErpmNews> showingNewsinPageList = new ArrayList<ErpmNews>();//this list contain news which we have to show main page
    private ErpmNewsDAO erpmNewsDAO = new ErpmNewsDAO();
       
  // private LanguageMasterDAO LangDao = new LanguageMasterDAO();
  // private List<LanguageMaster> LangList = new ArrayList<LanguageMaster>();

    public void setMesssge(String message) {
            this.message = message;
    }

     public String getMessage() {
        return message;
    }

    public List<ErpmNews> getshowingNewsinPageList() {
        return showingNewsinPageList;
    }

    public void setshowingNewsinPageList(List<ErpmNews> showingNewsinPageList) {
        this.showingNewsinPageList = showingNewsinPageList;
    }

    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                       Date date = new Date();
                            DateUtilities dt = new DateUtilities();

    @Override
    public String execute() throws Exception {
       try {

        getSession().setAttribute("request_locale", Locale.ENGLISH);
        showingNewsinPageList=erpmNewsDAO.findbyDate(dt.convertStringToDate(dateFormat.format(date)));
        return SUCCESS;
        }
    catch (Exception e) {
        message = message + e.getCause() + "  " + e.getMessage();
    return ERROR;
   }

}
}
