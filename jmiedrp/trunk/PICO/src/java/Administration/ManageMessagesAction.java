/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author afreen
 */

package Administration;


import pojo.hibernate.UserMessage;
import pojo.hibernate.ErpmGenMaster;
import pojo.hibernate.UserMessageDAO;
import pojo.hibernate.ErpmGenMasterDao;
import utils.DevelopmentSupport;
import java.util.ArrayList;
import java.util.List;

public class ManageMessagesAction extends DevelopmentSupport   {

   
    private UserMessageDAO umDao = new UserMessageDAO();
    private List<UserMessage> umList = new ArrayList<UserMessage>();
  private UserMessage  um= new UserMessage();
   private List<ErpmGenMaster> statusList = new ArrayList<ErpmGenMaster>();
 private ErpmGenMasterDao erpmGmDao = new ErpmGenMasterDao();
   private String message;
   private String ReqType;
  
    public List<UserMessage> getumList() {
        return umList;
    }
    public void setumList(List<UserMessage> umList) {
        this.umList = umList;
    }

   public List<ErpmGenMaster> getstatusList() {
        return statusList;
    }
    public void setstatusList(List<ErpmGenMaster> statusList) {
        this.statusList = statusList;
    }
 public UserMessage getum() {
        return um;
    }
    public void setum(UserMessage um) {
        this.um = um;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
 public void setReqType(String ReqType) {
        this.ReqType = ReqType;
    }

    public String getReqType() {
        return this.ReqType;
    }



    @Override

     public String execute() throws Exception {
        try {
           //Prepare LOV containing User Institutions
            umList = umDao.findByUserId(Integer.valueOf(getSession().getAttribute("userid").toString()));
            return SUCCESS;
            } catch (Exception e) {
            message = "Exception in -> ManageBudgetHeadAxn"  + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }



     public String indentApproval() throws Exception {
        try {
           //Prepare LOV containing User Institutions
           umList = umDao.findByUserIdAndReqType(Integer.valueOf(getSession().getAttribute("userid").toString()),getReqType());
          //message="hello"+umList.get(0).getUmReqType()+umList.get(1).getUmReqType()+umList.get(1).getUmReqType();
           statusList = erpmGmDao.findByErpmGmType(Short.parseShort("8".toString()));
           return SUCCESS;
            } catch (Exception e) {
            message = "Exception in -> ManageBudgetHeadAxn"  + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }




}




