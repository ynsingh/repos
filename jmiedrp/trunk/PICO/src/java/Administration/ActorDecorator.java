/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author afreen
 */
package Administration;
//import pojo.hibernate.*;
import org.displaytag.decorator.TableDecorator;
import pojo.hibernate.UserMessage;
import pojo.hibernate.ErpmGenMaster;
import pojo.hibernate.ErpmNews;
import pojo.hibernate.GfrMaster;
import pojo.hibernate.ErpmGenMasterDao;
import pojo.hibernate.DepartmentalBudgetAllocation;
import utils.DateUtilities;
import java.util.*;
import pojo.hibernate.ErpmItemCategoryMaster;

public class ActorDecorator extends TableDecorator {
 private List<ErpmGenMaster> erpmGmIdList = new ArrayList<ErpmGenMaster>();
 private ErpmGenMasterDao erpmGmDao = new ErpmGenMasterDao();
    private String ActionLink;
    private String message;

    public String getUmMessage()
    {

      UserMessage  actorData = (UserMessage)getCurrentRowObject();
       String emailId = "<a href="+actorData.getUmActionName()+">"+actorData.getUmMessage()+ "</a>";
       
       return emailId;
    }

    public String getmessage() {
        return this.message;
    }

    public String getumActionName() throws Exception{
        try {
      UserMessage um  = (UserMessage)getCurrentRowObject();
      return "<a href="+um.getUmActionName()+">Open</a>";
        }
        catch (Exception e){
            message = message + e.getMessage() + e.getCause();
            return "Error";
        }
    }
public String getformattednewsPublishDate() {
      ErpmNews erpmnews = (ErpmNews)getCurrentRowObject();;
       DateUtilities dt = new DateUtilities();
       try {
        return dt.convertDateToString(erpmnews.getNewsPublishDate(), "dd-MM-yyyy");
       }
       catch (Exception e) {
        return "Error in date formatting";
    }

}
public String getformattednewsExpiryDate() {
      ErpmNews erpmnews = (ErpmNews)getCurrentRowObject();;
       DateUtilities dt = new DateUtilities();
       try {
        return dt.convertDateToString(erpmnews.getNewsExpiryDate(), "dd-MM-yyyy");
       }
       catch (Exception e) {
        return "Error in date formatting";
    }

}

public String getformatteddbaFromDate() {
      DepartmentalBudgetAllocation dba = (DepartmentalBudgetAllocation)getCurrentRowObject();;
       DateUtilities dt = new DateUtilities();
       try {
        return dt.convertDateToString(dba.getDbaFromDate(), "dd-MM-yyyy");
       }
       catch (Exception e) {
        return "Error in date formatting";
    }

}
public String getformatteddbaToDate() {
      DepartmentalBudgetAllocation dba = (DepartmentalBudgetAllocation)getCurrentRowObject();;
       DateUtilities dt = new DateUtilities();
       try {
        return dt.convertDateToString(dba.getDbaToDate(), "dd-MM-yyyy");
       }
       catch (Exception e) {
        return "Error in date formatting";
    }

}

    public String getCategoryDepreciationMethod() {

        ErpmItemCategoryMaster  erpmItemCategoryMaster =  (ErpmItemCategoryMaster) getCurrentRowObject();
        if (Character.toUpperCase(erpmItemCategoryMaster.getErpmicmDepreciationMethod()) == 'W')
            return "Written Down Value";
        else
            return "Straight Line";
    }
    public String getformattedGFRinstituteRule() {
      GfrMaster gfr = (GfrMaster)getCurrentRowObject();;
         if (Character.toUpperCase(gfr.getGfrorInstituteRule()) == 'I')
            return "Institute Specific Rule";
        else
            return "General Financial Rule";

}
    
}


