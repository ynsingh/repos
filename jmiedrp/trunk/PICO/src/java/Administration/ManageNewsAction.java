/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Administration;

/**
 *
 * @author erp03
 */
import utils.DateUtilities;

import pojo.hibernate.ErpmNews;
import pojo.hibernate.Erpmusers;
import pojo.hibernate.ErpmNewsDAO;
import pojo.hibernate.ErpmusersDAO;

import utils.DevelopmentSupport;
import java.util.ArrayList;
import java.util.List;
import org.apache.struts2.interceptor.validation.SkipValidation;
import java.util.Iterator;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ManageNewsAction extends DevelopmentSupport {

    private ErpmNewsDAO erpmNewsDAO = new ErpmNewsDAO();
    private ErpmusersDAO erpmuserDao = new ErpmusersDAO();
    private List<ErpmNews> newsList = new ArrayList<ErpmNews>();
    private List<ErpmNews> showingNewsinPageList = new ArrayList<ErpmNews>();//this list contain news which we have to show main page
    private List<String> stringListofrNews = new ArrayList<String>();
//  private List<UserMessage> ummList = new ArrayList<UserMessage>();
    private ErpmNews erpmNews = new ErpmNews();
//   private List<ErpmGenMaster> statusList = new ArrayList<ErpmGenMaster>();
// private ErpmGenMasterDao erpmGmDao = new ErpmGenMasterDao();
    private String message;
    int ManageNewsId;//Mnagenewsid usedlocal variable  in action class
    String publishDate;//publish kerne ki date in string form
    String expiryDate;//expire kerne ki date in string form
    String news;

    public List<ErpmNews> getnewsList() {
        return newsList;
    }

    public void setnewsList(List<ErpmNews> newsList) {
        this.newsList = newsList;
    }

    public List<String> getstringListofrNews() {
        return stringListofrNews;
    }

    public void setstringListofrNews(List<String> stringListofrNews) {
        this.stringListofrNews = stringListofrNews;
    }

    public List<ErpmNews> getshowingNewsinPageList() {
        return showingNewsinPageList;
    }

    public void setshowingNewsinPageListt(List<ErpmNews> showingNewsinPageList) {
        this.showingNewsinPageList = showingNewsinPageList;
    }

    public ErpmNews geterpmNews() {
        return erpmNews;
    }

    public void seterpmNews(ErpmNews erpmNews) {
        this.erpmNews = erpmNews;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setManageNewsId(int ManageNewsId) {
        this.ManageNewsId = ManageNewsId;
    }

    public int getManageNewsId() {
        return this.ManageNewsId;
    }

    public void setpublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getpublishDate() {
        return this.publishDate;
    }

    public void setexpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getexpiryDate() {
        return this.expiryDate;
    }

    public void setnews(String news) {
        this.news = news;
    }

    public String getnews() {
        return this.news;
    }
    //getting current date in form of dd-mm-yyy
    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    Date date = new Date();
    DateUtilities dt = new DateUtilities();

    @Override
    @SkipValidation
    public String execute() throws Exception {
        try {


            erpmNews = null;
            publishDate = "";
            expiryDate = "";
//changing date from string form to Date formate to save in database
            try {
                //               message = ""+dt.convertStringToDate(""+dateFormat.format(date)) + " : " + dt.convertDateToString(date,"yyyy-mm-dd");
		//changing formate 
                               message = ""+dt.convertStringToDate(""+dateFormat.format(date)) + " : " + dt.convertDateToString(date,"yyyy-mm-dd");
                showingNewsinPageList = erpmNewsDAO.findbyDate(dt.convertStringToDate(dateFormat.format(date)));

            } catch (Exception e) {
                message = "Entered Date is not valid according to Given format";
                return ERROR;
            }
            Iterator itr = showingNewsinPageList.iterator();
            while (itr.hasNext()) {
                erpmNews = (ErpmNews) itr.next();
                stringListofrNews.add(erpmNews.getNewsText());
                // message=""+dateFormat.format(date);
            }
            erpmNews = null;


            return "SUCCESS";
        } catch (Exception e) {
            message = "Exception in -> ManageNewsAction" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String Save() throws Exception {
        //making stringListofrNews to show news  in page 
        try {
            //  message = ""+dateFormat.format(date);
            showingNewsinPageList = erpmNewsDAO.findbyDate(dt.convertStringToDate("" + dateFormat.format(date)));
//                erpmNews.setNewsExpiryDate(dt.convertStringToDate(getexpiryDate()));

        } catch (Exception e) {
            message = "Entered Date is not valid according to Given format";
            return ERROR;
        }
        ErpmNews localerpmNews;//this is used only in iterator
        Iterator itr = showingNewsinPageList.iterator();
        while (itr.hasNext()) {
            localerpmNews = (ErpmNews) itr.next();
            stringListofrNews.add(localerpmNews.getNewsText());
            // message="gfty"+dateFormat.format(date);
        }
        localerpmNews = null;



        try {
            //to save news data by user
            if (erpmNews.getNewsId() == null) {
                Erpmusers erpmusers = erpmuserDao.findByUserId(Integer.valueOf(getSession().getAttribute("userid").toString()));

                DateUtilities dt = new DateUtilities();//changing date from string form to Date formate to save in database
                erpmNews.setErpmusers(erpmusers);
                try {
                    erpmNews.setNewsPublishDate(dt.convertStringToDate(getpublishDate()));
                    erpmNews.setNewsExpiryDate(dt.convertStringToDate(getexpiryDate()));
                } catch (Exception e) {
                    message = "Entered Date is not valid according to Given format";
                    return "SUCCESS";
                }
                try {  //check publish date should be before expiry date
                    if (dt.convertStringToDate(getexpiryDate()).before(dt.convertStringToDate(getpublishDate()))) {
                        addFieldError("publishDate", "Publish date should be before Expiry date");
                        return "input";
                    }
                } catch (Exception e) {
                }
                message = "data is saved";

                erpmNewsDAO.save(erpmNews);


            } else {// to update news data
                DateUtilities dt = new DateUtilities();//changing date from string form to Date formate to save in database
                try {
                    erpmNews.setNewsPublishDate(dt.convertStringToDate(getpublishDate()));
                    erpmNews.setNewsExpiryDate(dt.convertStringToDate(getexpiryDate()));
                } catch (Exception e) {
                    message = "Entered Date is not valid according to Given format";
                    return "SUCCESS";
                }
                Erpmusers erpmusers = erpmuserDao.findByUserId(Integer.valueOf(getSession().getAttribute("userid").toString()));

                ErpmNews erpmNews1 = erpmNewsDAO.findByNewsId(erpmNews.getNewsId());//getting MnageNews object according newsid
                erpmNews1 = erpmNews;
                erpmNews1.setErpmusers(erpmusers);
                message = "data is Updated";

                erpmNewsDAO.update(erpmNews1);


            }
            publishDate = "";
            expiryDate = "";
            erpmNews = null;
        } catch (Exception e) {

            message = "Exception in Save method -> ManageNewsActionAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
        return "SUCCESS";
    }

    @SkipValidation
    public String BrowseManageNews() throws Exception {

        try {
            // getting news list according login userid in browse page
            newsList = erpmNewsDAO.findForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));

            return "SUCCESS";

        } catch (Exception e) {
            message = "Exception in Browse method -> BrowseManageNews" + e.getMessage();
            return "ERROR";
        }
    }

    public String Edit() throws Exception {
        //making stringListofrNews to show news  in page
        try {
            //  message = ""+dateFormat.format(date);
            showingNewsinPageList = erpmNewsDAO.findbyDate(dt.convertStringToDate("" + dateFormat.format(date)));
//                erpmNews.setNewsExpiryDate(dt.convertStringToDate(getexpiryDate()));

        } catch (Exception e) {
            message = "Entered Date is not valid according to Given format";
            return ERROR;
        }
        ErpmNews localerpmNews;//this is used only in iterator

        Iterator itr = showingNewsinPageList.iterator();
        while (itr.hasNext()) {
            localerpmNews = (ErpmNews) itr.next();
            stringListofrNews.add(localerpmNews.getNewsText());
        }
        localerpmNews = null;

        try {
//getting MnageNews object according ManageNewsId when we click in edit
            erpmNews = erpmNewsDAO.findByNewsId(getManageNewsId());

//changing date from string form to Date formate to save in database
            DateUtilities dt = new DateUtilities();
            publishDate = dt.convertDateToString(erpmNews.getNewsPublishDate(), "dd-MM-yyyy");
            expiryDate = dt.convertDateToString(erpmNews.getNewsExpiryDate(), "dd-MM-yyyy");
            // InitializeLOVsForEdit();
            return "SUCCESS";
        } catch (Exception e) {
            message = "Exception in Edit method ->ManageNewsAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String Delete() throws Exception {
        try {
            ////getting MnageNews object according ManageNewsId when we click in delete

            erpmNews = erpmNewsDAO.findByNewsId(getManageNewsId());
            //deleting that news object
            erpmNewsDAO.delete(erpmNews);
            //now getting remaining news list to show in brouse page
            newsList = erpmNewsDAO.findForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
            message = "Date is deleted with newsid" + getManageNewsId();

            return "SUCCESS";
        } catch (Exception e) {
            message = "Exception in Delete method -> ManageNewsAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public void validate() {//validation to every input manage news page
        try {


            if (erpmNews.getNewsText().isEmpty()) {
                addFieldError("erpmNews.newsText", "Please Entered the News Text");
            }
            if (getpublishDate().length() == 0) {
                addFieldError("publishDate", "Please Entered Publish Date");
            } else {//cheack date should be in()dd-mm-yy) formate
                DateUtilities dt = new DateUtilities();
                if (dt.isValidDate(getpublishDate()) == false) {
                    addFieldError("publishDate", "Enter date of Establishment[dd-mm-yyyy]");
                }
            }
            if (getexpiryDate().length() == 0) {//cheack date should be in()dd-mm-yy) formate
                addFieldError("expiryDate", "Please give Expiry Date");
            } else {
                DateUtilities dt = new DateUtilities();
                if (dt.isValidDate(getexpiryDate()) == false) {
                    addFieldError("expiryDate", "Enter date of Expiry Date[dd-mm-yyyy]");
                }
            }



            if (erpmNews.getNewsType().isEmpty()) {
                addFieldError("erpmNews.newsType", "Please give News Type");
            }



        } catch (NullPointerException e) {
        }
    }
}
