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
import pojo.hibernate.UserMessageDAO;
import pojo.hibernate.ErpmGenMaster;
import pojo.hibernate.ErpmGenMasterDao;

import java.util.*;

public class IndentDecorator extends TableDecorator {
 private List<ErpmGenMaster> erpmGmIdList = new ArrayList<ErpmGenMaster>();
   private ErpmGenMasterDao erpmGmDao = new ErpmGenMasterDao();
   private List<UserMessage> statusList = new ArrayList<UserMessage>();
   private UserMessageDAO umDao = new UserMessageDAO();
  
   private String userid;
 private String x;
int umid;
 public int getumid() {
        return this.umid;
    }

    public void setumid(int umid) {
        this.umid = umid;
    }

   public String getuserid() {
        return this.userid;
    }

    public void setuserid(String userid) {
        this.userid = userid;
    }
 public String getx() {
        return this.x;
    }

    public void setx(String x) {
        this.x= x;
    }
 public String getErpmusersByUmFromErpmuId()
{

UserMessage  actorData = (UserMessage )getCurrentRowObject();
String a="<a href="+"/pico/Administration/MessageReport.action?umid="+actorData.getUmId()+">";
a=a+actorData.getErpmusersByUmFromErpmuId().getErpmuFullName()+"</a>";
//return a+actorData.getErpmusersByUmFromErpmuId().getErpmuFullName()+"</a>";
//return actorData.getErpmusersByUmFromErpmuId().getErpmuFullName();
return a;
 }

    public int getUmId()
{

UserMessage  actorData = (UserMessage )getCurrentRowObject();

return actorData.getUmId();

}

   public String getUmActionName()
    {
 UserMessage  actorData = (UserMessage )getCurrentRowObject();
       //onchange="getSubinstitutionList('SaveDepartmentAction_dm_institutionmaster_imId', 'SaveDepartmentAction_dm_subinstitutionmaster_simId');"
  erpmGmIdList = erpmGmDao.findByErpmGmType(Short.parseShort("8".toString()));
//umList=umDao.findByUserId(Integer.valueOf(devsup.getSession().getAttribute("userid").toString()));
 statusList=umDao.findByUserId(actorData.getErpmusersByUmToErpmuId().getErpmuId());
  //String lists="<select name=D1 onchange="+"'getCurrentStatus()'"+" ><option selected>Please Select...";
   //  String lists="<select name=D1 onchange="+"getCurrentStatus(this.options[this.selectedIndex].value))"+" ><option >Please Select...";
 
int id=actorData.getUmId();
String umid=id+"";
String lists="<select name=D1 onchange="+"getCurrentStatus(this.options[this.selectedIndex].text,"+umid+")><option selected="+"selected"+">"+actorData.getErpmGenMaster().getErpmgmEgmDesc()+"</option>";
for (int i = 0; i < erpmGmIdList.size() ; i++) {
    boolean v=erpmGmIdList.get(i).getErpmgmEgmDesc().equals(actorData.getErpmGenMaster().getErpmgmEgmDesc());
       if(v);
            else
         lists= lists + "<option value="+erpmGmIdList.get(i).getErpmgmEgmId()+">"+erpmGmIdList.get(i).getErpmgmEgmDesc()+"</option>";
                }
lists = lists + "</select>";
            return lists;
    }

   
}

