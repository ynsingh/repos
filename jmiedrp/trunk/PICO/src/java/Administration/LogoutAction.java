/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Administration;

import utils.DevelopmentSupport;
import java.util.ArrayList;
import java.util.List;
import pojo.hibernate.ErpmNews;
import pojo.hibernate.ErpmNewsDAO;
import utils.DateUtilities;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


/**
 *
 * @author kazim
 */
public class LogoutAction extends DevelopmentSupport {
    private String message;
    private List<ErpmNews> showingNewsinPageList = new ArrayList<ErpmNews>();//this list contain news which we have to show main page
    private ErpmNewsDAO erpmNewsDAO = new ErpmNewsDAO();

     public String getMessage() {
        return message;
    }

    void setMessage(String message) {
        this.message = message ;
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
            getSession().removeAttribute("username");
            getSession().removeAttribute("userfullname");
            getSession().removeAttribute("imshortname");
            getSession().removeAttribute("simshortname");
            getSession().removeAttribute("dmshortname");
            getSession().invalidate();
            showingNewsinPageList=erpmNewsDAO.findbyDate(dt.convertStringToDate(dateFormat.format(date)));
            message="Thanks for using the PICO System";
            return SUCCESS;
            }
        catch (Exception e) {
            message = "Unable to remove session";
            return ERROR;
        }
    }
}
