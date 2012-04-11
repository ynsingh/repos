/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author afreen
 */
package Administration;
import org.displaytag.decorator.TableDecorator;
import pojo.hibernate.UserMessage;
import pojo.hibernate.ErpmGenMaster;
import pojo.hibernate.ErpmGenMasterDao;
import java.util.*;

public class ActorDecorator extends TableDecorator {
 private List<ErpmGenMaster> erpmGmIdList = new ArrayList<ErpmGenMaster>();
   private ErpmGenMasterDao erpmGmDao = new ErpmGenMasterDao();

    public String getUmMessage()
    {

      UserMessage  actorData = (UserMessage )getCurrentRowObject();
       String emailId = "<a href="+actorData.getUmActionName()+">"+actorData.getUmMessage()+ "</a>";
       
       return emailId;
    }
    
}
