/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package PrePurchase;

import com.opensymphony.xwork2.ActionContext;
import java.math.BigDecimal;
import utils.DevelopmentSupport;
import java.util.*;


import pojo.hibernate.ErpmIndentMaster;
import pojo.hibernate.ErpmIndentMasterDAO;
import pojo.hibernate.ErpmIndentDetail;
import pojo.hibernate.ErpmIndentDetailDAO;
import pojo.hibernate.UserMessage;
import pojo.hibernate.UserMessageDAO;
import pojo.hibernate.Workflowmaster;
import pojo.hibernate.WorkflowmasterDAO;
import pojo.hibernate.Workflowtransaction;
import pojo.hibernate.WorkflowtransactionDAO;
import pojo.hibernate.WorkflowdetailDAO;
import pojo.hibernate.Committeemaster;
import pojo.hibernate.Workflowactions;
import pojo.hibernate.WorkflowactionsDAO;
import pojo.hibernate.Erpmusers;
import pojo.hibernate.ErpmusersDAO;
import pojo.hibernate.ErpmGenMaster;
import pojo.hibernate.ErpmGenMasterDao;
import org.apache.struts2.interceptor.validation.SkipValidation;
import pojo.hibernate.ErpmuserroleDAO;
import pojo.hibernate.Erpmusers;
import pojo.hibernate.ErpmusersDAO;
import utils.DateUtilities;

import utils.sendMail;
/**
 *
 * @author sknaqvi
 */
public class SubmitIndent extends DevelopmentSupport {


    private ErpmIndentMaster erpmIndentMaster = new ErpmIndentMaster();
    private ErpmIndentMasterDAO erpmIndentMasterDao = new ErpmIndentMasterDAO();

    private ErpmIndentDetail erpmIndentDetail = new ErpmIndentDetail();
    private ErpmIndentDetailDAO erpmIndentDetailDao = new ErpmIndentDetailDAO();

    private List<Workflowmaster> indentWorkFlowList = new ArrayList<Workflowmaster>();
    private WorkflowmasterDAO workFlowMasterDao = new WorkflowmasterDAO();

    private WorkflowdetailDAO workFlowwDetailDao = new WorkflowdetailDAO();

    private Committeemaster committeeMaster = new Committeemaster();

    private WorkflowactionsDAO workFlowActionsDao = new WorkflowactionsDAO();
    private List<Workflowactions> workFlowActionList = new ArrayList<Workflowactions>();
    
    private Workflowtransaction workFlowTransaction = new Workflowtransaction();
    private List<Workflowtransaction> workFlowTransactionList = new ArrayList <Workflowtransaction>();
    private WorkflowtransactionDAO workFlowTransactionDao = new WorkflowtransactionDAO();

    private UserMessage userMessage = new UserMessage();
    private UserMessageDAO userMessageDao = new UserMessageDAO();

    private ErpmusersDAO erpmUsersDao = new ErpmusersDAO();

    private ErpmGenMaster erpmGeneralMaster = new ErpmGenMaster();
    private ErpmGenMasterDao erpmGeneralMasterDao = new ErpmGenMasterDao();

    private Short indentId;
    private String message;
    private Integer numberOfIndentItems;
    private String approxIndentValue;
    private Integer workFlowId;
    private Integer defaultWfmId;
    private String sourceCommittee;
    private String destinationCommittee;
    private Integer msgId;
    private Boolean btnEditEnabled;

    public void setbtnEditEnabled(Boolean btnEditEnabled) {
        this.btnEditEnabled = btnEditEnabled;
    }

    public Boolean getbtnEditEnabled() {
        return this.btnEditEnabled;
    }

    public void setmsgId(Integer msgId) {
        this.msgId = msgId;
    }

    public Integer getmsgId() {
          return this.msgId;
    }

    public void setsourceCommittee(String sourceCommittee) {
        this.sourceCommittee = sourceCommittee;
    }

    public String getsourceCommittee() {
          return this.sourceCommittee;
    }

    public void setdestinationCommittee(String destinationCommittee) {
        this.destinationCommittee = destinationCommittee;
    }

    public String getdestinationCommittee() {
          return this.destinationCommittee;
    }

    public void setdefaultWfmId(Integer defaultWfmId) {
        this.defaultWfmId = defaultWfmId;
    }

    public Integer getdefaultWfmId() {
          return this.defaultWfmId;
    }

    public void setindentId(Short indentId) {
        this.indentId = indentId;
    }

    public Short getindentId() {
          return this.indentId;
    }

    public void setmessage(String message) {
        this.message = message;
    }

    public String getmessage() {
          return this.message;
    }

    public void seterpmIndentMaster(ErpmIndentMaster erpmIndentMaster) {
        this.erpmIndentMaster = erpmIndentMaster;
    }

    public ErpmIndentMaster geterpmIndentMaster() {
          return this.erpmIndentMaster;
    }

    public void seterpmIndentDetail(ErpmIndentDetail erpmIndentDetail) {
        this.erpmIndentDetail = erpmIndentDetail;
    }

    public ErpmIndentDetail geterpmIndentDetail() {
          return this.erpmIndentDetail;
    }

    public void setnumberOfIndentItems(Integer numberOfIndentItems) {
        this.numberOfIndentItems = numberOfIndentItems;
    }

    public Integer getnumberOfIndentItems() {
          return this.numberOfIndentItems;
    }

    public void setapproxIndentValue(String approxIndentValue) {
        this.approxIndentValue = approxIndentValue;
    }

    public String getapproxIndentValue() {
          return this.approxIndentValue;
    }

    public void setindentWorkFlowList(List<Workflowmaster>  indentWorkFlowList) {
        this.indentWorkFlowList = indentWorkFlowList;
    }

    public List<Workflowmaster>  getindentWorkFlowList() {
          return this.indentWorkFlowList;
    }

    public void setworkFlowActionList(List<Workflowactions>  workFlowActionList) {
        this.workFlowActionList = workFlowActionList;
    }

    public List<Workflowactions>  getworkFlowActionList() {
          return this.workFlowActionList;
    }

    public void setworkFlowTransaction(Workflowtransaction  workFlowTransaction) {
        this.workFlowTransaction = workFlowTransaction;
    }

    public Workflowtransaction getworkFlowTransaction() {
          return this.workFlowTransaction;
    }

    public void setworkFlowTransactionList(List<Workflowtransaction>  workFlowTransactionList) {
        this.workFlowTransactionList = workFlowTransactionList;
    }

    public List<Workflowtransaction> getworkFlowTransactionList() {
          return this.workFlowTransactionList;
    }


    public void setworkFlowId(Integer  workFlowId) {
        this.workFlowId = workFlowId;
    }

    public Integer  getworkFlowId() {
          return this.workFlowId;
    }

    public void setuserMessage(UserMessage  userMessage) {
        this.userMessage = userMessage;
    }

    public UserMessage  getuserMessage() {
          return this.userMessage;
    }

    @Override

    public String execute() throws Exception {
    try {

        message = "You are submitting Indent No: " +  getindentId();
        erpmIndentMaster = erpmIndentMasterDao.findIndentMasterId(getindentId());
        numberOfIndentItems = erpmIndentDetailDao.countIndentItems(getindentId());
        approxIndentValue = erpmIndentDetailDao.indentValue(getindentId()).toString();

        //Find Current Stage of Work; If it is Not yet in WorkFlowTransaction Then we proceed normally
        //otherwise we need to initialise the Stage, Source Committtee, destination Committee, Convener Mail
        if(workFlowTransactionDao.findCurrentWFTStageByWorkId(getindentId()) == 0) {
            //Prepare List of Work Flows Applicable to Indent
            indentWorkFlowList = workFlowMasterDao.findByErpmGmID(72);
            
        }
        else {
            //Check Authority of User
            //Get the latest record for this work from the WorkflowTransaction Table

            String authorisedUser = workFlowTransactionDao.findAuthorisedUser(getindentId());
            String sessionUser = getSession().getAttribute("username").toString() ;
            
            if (sessionUser.equalsIgnoreCase(authorisedUser)) {

                //Prepare LOV for WorkFlow
                indentWorkFlowList = workFlowMasterDao.findByErpmGmID(72);
                defaultWfmId = workFlowTransactionDao.findWorkFlowID(getindentId());
                workFlowTransaction.setWorkflowmaster(workFlowMasterDao.findWorkFlowById(defaultWfmId));
                
                //Setup Committees and Action
                setupCommitteesAndAction();
                
            }
            else {                
                message = "Sorry, You are not authorised for this work/Work is already submitted.";
                return ERROR;
            }
        }
        
        return SUCCESS;
        } catch (Exception e) {
            message = message + "Exception in -> ManageIndentItemsAxn "  + e.getMessage() + " Reported Cause is: " + e.getCause();
        return ERROR;
        }
   }

    public String SubmitIndentAction() throws Exception {
        try {

            SaveWorkFlowTransaction();
            
            SendMailtoIndentStakeholders();
            
            AddMessage();

            //Set Work Close
            CloseMessage();
            message = message + "Indent No: " + getindentId() + " has  been submitted on Workflow";
  
            return SUCCESS;

        } catch (Exception e) {
            message = "Exception in -> SubmitIndentAxn "  + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String SaveWorkFlowTransaction() {
        try {

            Date dt = new Date();
            workFlowTransaction.setWftDate(dt);

            workFlowTransaction.setWftWorkId(getindentId());

            if (workFlowTransaction.getCommitteemasterByWftSourceId().getCommitteeId() == 0) {               
                workFlowTransaction.setCommitteemasterByWftSourceId(null);
            }
            if(getdestinationCommittee().contains("Not Applicable")) {
                workFlowTransaction.setCommitteemasterByWftDestinationId(null);
            }
            
            workFlowTransactionDao.save(workFlowTransaction);
            
            return SUCCESS;
        }
        catch(Exception e) {
            message = "Exception in SubmitIndent -> SaveWorkFlowtransaction. Error Message is : " + e.getMessage() + " Reported Cause is:  "+ e.getCause();
            return ERROR ;
        }

    }
    

    public String AddMessage() {
        try {

            //Check, if the message (Jobs) table is to be updated
            userMessage.setErpmusersByUmFromErpmuId(erpmUsersDao.findByUserId(Integer.parseInt(getSession().getAttribute("userid").toString())));

            Erpmusers user = new Erpmusers();            
            user = erpmUsersDao.findByUserName(workFlowTransaction.getWftDestinationEmail());

            userMessage.setErpmusersByUmToErpmuId(user);
            userMessage.setUmRequestSubmissionDate(new Date());
            userMessage.setUmMessage("Please process the Indent ");
            userMessage.setUmActionName("http://localhost:8080/pico/PrePurchase/PrepareIndentSubmit.action?indentId=" +
                                        workFlowTransaction.getWftWorkId());
            userMessageDao.save(userMessage);

            UserMessage userMessage2;
            userMessage2 = userMessageDao.findByumId(userMessage.getUmId());
            
            userMessage2.setUmActionName("http://localhost:8080/pico/PrePurchase/PrepareIndentSubmit.action?indentId=" +
                                        workFlowTransaction.getWftWorkId() + 
                                        "&msgId="+userMessage.getUmId());
            userMessageDao.update(userMessage2);
             
             
            return "SUCCESS";
        }
        catch(Exception e) {
            return ERROR;
        }

    }

public String CloseMessage() {
        try {

            Date dt = new Date();

            userMessage = userMessageDao.findByumId(getmsgId());

            
            userMessage.setUmActionDate(dt);
            userMessage.setErpmGenMaster(erpmGeneralMasterDao.findByErpmGmId(workFlowTransaction.getErpmGenMaster().getErpmgmEgmId()));

            userMessageDao.update(userMessage);
            //message = "Close message : " + workFlowTransaction.getErpmGenMaster().getErpmgmEgmId();
             
            return "SUCCESS";
        }
        catch(Exception e) {            
            return ERROR;
        }
}

@SkipValidation
public String viewWorkFlow() {
    try {

        workFlowTransactionList = workFlowTransactionDao.findWFTbyWorkId(getindentId());
        
        return SUCCESS;

    } catch(Exception e) {
        message = "Exception in SubmitIndent -> viewWorkFlowAxn. Error Message is : " + e.getMessage() + " Reported Cause is:  "+ e.getCause();
        return ERROR;
    }
}

@SkipValidation
public String showWorkFlow() {
    try {

        if(workFlowTransactionDao.countWFTbyWorkId(getindentId()) > 1) {
            workFlowTransactionList = workFlowTransactionDao.findWFTbyWorkId(getindentId());
            numberOfIndentItems = erpmIndentDetailDao.countIndentItems(getindentId());
            approxIndentValue = erpmIndentDetailDao.indentValue(getindentId()).toString();                            
        }
        else
            message = "The Indent is not yet on Workflow";

        erpmIndentMaster = erpmIndentMasterDao.findIndentMasterId(getindentId());
            
        return SUCCESS;

    } catch(Exception e) {
        message = "Exception in SubmitIndent -> viewWorkFlowAxn. Error Message is : " + e.getMessage() + " Reported Cause is:  "+ e.getCause();
        return ERROR;
    }
}



@SkipValidation
public String editWork() {
    try {

        indentId = getindentId();
        btnEditEnabled = true;

        return SUCCESS;

    } catch(Exception e) {
        message = "Exception in SubmitIndent -> viewWorkFlowAxn. Error Message is : " + e.getMessage() + " Reported Cause is:  "+ e.getCause();
        return ERROR;
    }
}

public void validate() {

    try {

        
        if(workFlowTransaction.getWorkflowmaster().getWfmId() == null)
            addFieldError("workFlowTransaction.workflowmaster.wfmId" ,"Please select an Workflow");

        if(workFlowTransaction.getErpmGenMaster().getErpmgmEgmId() == null)
            addFieldError("workFlowTransaction.erpmGenMaster.erpmgmEgmId","Please select an action");
        
        //Get the latest record for this work from the WorkflowTransaction Table

        String authorisedUser = workFlowTransactionDao.findAuthorisedUser(getindentId());
        String sessionUser = getSession().getAttribute("username").toString() ;


        //Prepare LOV for WorkFlow
        indentWorkFlowList = workFlowMasterDao.findByErpmGmID(72);
        defaultWfmId = workFlowTransactionDao.findWorkFlowID(getindentId());
        workFlowTransaction.setWorkflowmaster(workFlowMasterDao.findWorkFlowById(defaultWfmId));
    
        //Prepare Current Stage
        workFlowTransaction.setWftStage(workFlowTransactionDao.findCurrentWFTStageByWorkId(getindentId())+1);

        setupCommitteesAndAction();
        
    }
    catch(NullPointerException ne) {
    ;
    }

    }
    
public void setupCommitteesAndAction() {
        
                try {
                //Find Current WorkFlow Stage
                Integer currentWFTStage = workFlowTransactionDao.findCurrentWFTStageByWorkId(getindentId());
                
                //Prepare Next WorkFlowStagee
                Integer nextWFTStage = currentWFTStage + 1;

                workFlowTransaction.setWftStage(nextWFTStage);

                //Check, if we have reached the last stage
                Integer lastWorkFlowStage = workFlowwDetailDao.findLastWorkFlowStage(defaultWfmId);

                //If we have arrived at last workflow Stage, set destination committee to null
                if(lastWorkFlowStage == nextWFTStage){
                    destinationCommittee = "Not Applicable";
                   
                    //Set WorkFlowActions for the Current Stage
                    workFlowActionList = workFlowActionsDao.getStageWorkFlowActions(defaultWfmId, nextWFTStage );

                    //Set Source Committee
                    committeeMaster = workFlowwDetailDao.findSourceCommittee(nextWFTStage, defaultWfmId);
                    sourceCommittee =  committeeMaster.getCommitteeName();
                    workFlowTransaction.setCommitteemasterByWftSourceId(committeeMaster);

                    }
                else {

                    //Prepare Source and Destination Committees
                    committeeMaster = workFlowwDetailDao.findSourceCommittee(nextWFTStage, defaultWfmId);
                    sourceCommittee =  committeeMaster.getCommitteeName();
                    workFlowTransaction.setCommitteemasterByWftSourceId(committeeMaster);

                    committeeMaster = workFlowwDetailDao.findDestinationCommittee(nextWFTStage, defaultWfmId);
                    destinationCommittee = committeeMaster.getCommitteeName();
                    workFlowTransaction.setCommitteemasterByWftDestinationId(committeeMaster);

                    //Destination Committee Convenre's EMail Address
                    workFlowTransaction.setWftDestinationEmail(committeeMaster.getCommitteeConvener());

                    //Set WorkFlowActions for the Current Stage
                    workFlowActionList = workFlowActionsDao.getStageWorkFlowActions(defaultWfmId, nextWFTStage );
                    }

                    for (int i=0; i < workFlowActionList.size(); ++i) {

                        if(workFlowActionList.get(i).getErpmGenMaster().getErpmgmEgmId() == 80 ||
                            workFlowActionList.get(i).getErpmGenMaster().getErpmgmEgmId() == 81 ||
                            workFlowActionList.get(i).getErpmGenMaster().getErpmgmEgmId() == 82 ||
                            workFlowActionList.get(i).getErpmGenMaster().getErpmgmEgmId() == 83)
                            btnEditEnabled = true;
                    }
        
                }
                catch (Exception e) {
                    ;
                }

}

public String SendMailtoIndentStakeholders() throws Exception {
    
    try {
    //Declare Variable
    String toEmailAddress;
    String ccEmailAddress;    
    DateUtilities d1 = new DateUtilities();
    
    //Find address of the Other People involed on teh Chain
    message = "Indent id " + indentId;
    //Find the Indent Title and Date 
    erpmIndentMaster = erpmIndentMasterDao.findIndentMasterId(indentId);
    approxIndentValue = erpmIndentDetailDao.indentValue(getindentId()).toString(); 
    numberOfIndentItems = erpmIndentDetailDao.countIndentItems(getindentId());
    
    //Find address of the Indent Submitter
    toEmailAddress = erpmIndentMaster.getErpmusers().getErpmuName();
    ccEmailAddress = "";
    
    workFlowTransactionList = workFlowTransactionDao.findWFTbyWorkId(getindentId());
    
    //Send Mail
    
    String userFullName;
    ErpmusersDAO erpmuDao = new ErpmusersDAO();

    userFullName = erpmuDao.findByUserName(toEmailAddress).getErpmuFullName();

    Locale locale = ActionContext.getContext().getLocale();
    ResourceBundle bundle = ResourceBundle.getBundle("pico", locale);
    String emailSubject = "Information on the Indent Submitted by You through PICO System";
    String emailMessage = "<html><head><title>Information on the Indent Submitted by You through PICO System</title></head>"
                    + "          <body><table width='100%' border='0' align='left' style='font-family:Verdana, Arial, Helvetica, sans-serif; font-size:10pt; color:#5a5a5a;'>"
                    + "                <tr><td align='left' colspan='5'> Dear " +  userFullName+ ", <br><br>"                    
                    + "                  Please note the status of the Indent which according to our Records has you as one of the stakeholders: </td>"
                    + "                </tr>"
                    + "                <tr><td><b>Indent No</b></td><td colspan='4'>" + getindentId()+ "</td></tr>"
                    + "                <tr><td><b>Indent Title</b></td><td colspan='4'>" + erpmIndentMaster.getIndtTitle()+ "</td></tr>"            
                    + "                   <tr><td><b>Indent Date</b></td><td colspan='4'>" + erpmIndentMaster.getIndtIndentDate() + "</td></tr>"   
                    + "                   <tr><td><b>Indent Currency</b></td><td colspan='4'>" + erpmIndentMaster.getErpmGenMasterByIndtCurrencyId().getErpmgmEgmDesc() + "</td></tr>"               
                    + "                   <tr><td><b>Approximate Indent Cost</b></td><td colspan='4'>" + approxIndentValue + "</td></tr>"                       
                    + "                   <tr><td><b>No of Items in the Indent</b></td><td colspan='4'>" + numberOfIndentItems + "</td></tr>"   
                    + "                   <tr bgcolor='gainsboro'><td><b>Workflow</b></td><td>Action Taken By</td><td>Next Action Resposibility</td><td>Action</td><td>Action Date</td></tr>";
            for(int i=0; i<workFlowTransactionList.size(); ++i) {
                int j = i + 1;
                
                if(workFlowTransactionList.get(i).getWftDestinationEmail() != null) {
                    ccEmailAddress = ccEmailAddress + workFlowTransactionList.get(i).getWftDestinationEmail() + ", ";
                }
                
                emailMessage = emailMessage + "<tr><td><b> Stage " + j + "</b></td>";
                 if (workFlowTransactionList.get(i).getCommitteemasterByWftSourceId() == null) {
                     emailMessage = emailMessage + "<td>" + toEmailAddress + "</td>";
                 }
                 else{
                     emailMessage = emailMessage + "<td>" + workFlowTransactionList.get(i).getCommitteemasterByWftSourceId().getCommitteeName() + "</td>";
                 }
                 if (workFlowTransactionList.get(i).getCommitteemasterByWftDestinationId() == null) {
                    emailMessage = emailMessage + "<td> Not Applicable </td>";
                 }
                 else {
                    emailMessage = emailMessage + "<td>" + workFlowTransactionList.get(i).getCommitteemasterByWftDestinationId().getCommitteeName() + "</td>";
                  }
                  emailMessage = emailMessage + "<td>" + workFlowTransactionList.get(i).getErpmGenMaster().getErpmgmEgmDesc() + "</td>" 
                                              + "<td>" + d1.convertDateToString(workFlowTransactionList.get(i).getWftDate(), "dd-MM-yyyy") + "</td></tr>";                                             
           }
            
            
          emailMessage = emailMessage + "<tr><td colspan='5'><br>Thank you for using this site.</td></tr>"
                                       + "<tr><td colspan='5'><br>Administrator, PICO Module</td></tr>"
                                       + "<tr><td colspan='5'><br><br><br>THIS IS AN AUTOMATED MESSAGE; PLEASE DO NOT REPLY.</td></tr>"
                                       + "</table></body></html>";
           
          sendMail.sendMail(bundle.getString("emailFrom"), bundle.getString("emailUser"), bundle.getString("emailFromPasswd"), 
                            toEmailAddress, ccEmailAddress, emailSubject, emailMessage);


         return SUCCESS;
         } catch (Exception e) {
                    message = "Exception in SubmitIndentAxn method -> SendMailtoIndentStakeholders " + e.getMessage() + " Reported Cause is: " + e.getCause();
                    return ERROR;
         }
}
}
