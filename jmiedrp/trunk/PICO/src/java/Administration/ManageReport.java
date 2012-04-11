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
import pojo.hibernate.ErpmIndentMaster;
import pojo.hibernate.ErpmIndentMasterDAO;
import pojo.hibernate.SubinstitutionmasterDAO;
import pojo.hibernate.Subinstitutionmaster;
import pojo.hibernate.Departmentmaster;
import pojo.hibernate.DepartmentmasterDAO;
import pojo.hibernate.Institutionmaster;
import pojo.hibernate.InstitutionmasterDAO;
import pojo.hibernate.Budgetheadmaster;
import pojo.hibernate.BudgetheadmasterDAO;
import pojo.hibernate.ErpmIndentDetail;
import pojo.hibernate.ErpmIndentDetailDAO;
import utils.DevelopmentSupport;
import java.util.ArrayList;
import java.util.List;

public class ManageReport extends DevelopmentSupport   {
 private UserMessageDAO umDao = new UserMessageDAO();
 private SubinstitutionmasterDAO smDao = new SubinstitutionmasterDAO();
  private DepartmentmasterDAO dmDao = new DepartmentmasterDAO();
 private ErpmIndentMaster  indtmaster= new ErpmIndentMaster();
 private Subinstitutionmaster  smmaster= new Subinstitutionmaster();
  private List<ErpmIndentDetail>  indtDetail= new ArrayList<ErpmIndentDetail>();
 private ErpmIndentDetailDAO  indtDetailDao= new ErpmIndentDetailDAO();
 private Departmentmaster  dmmaster= new Departmentmaster();
 private Institutionmaster  immaster= new Institutionmaster();
 private InstitutionmasterDAO imDao = new InstitutionmasterDAO();
  private Budgetheadmaster  bhmaster= new Budgetheadmaster();
 private BudgetheadmasterDAO bhmDao = new BudgetheadmasterDAO();
 private ErpmIndentMasterDAO indtmDao = new ErpmIndentMasterDAO();
    private List<UserMessage> umList = new ArrayList<UserMessage>();
  private List<UserMessage> ummList = new ArrayList<UserMessage>();
    private UserMessage  um= new UserMessage();
   private List<ErpmGenMaster> statusList = new ArrayList<ErpmGenMaster>();
 private ErpmGenMasterDao erpmGmDao = new ErpmGenMasterDao();
   private String message;
   private String ReqType;
int umid;
short ReqId;
short IndentId;
int SimId;
String smName;
String dmName;
String imName;
String inDate;
String bhmName;
String PreparedBy;
String ForwardedTo;
public List<UserMessage> getumList() {
        return umList;
    }
    public void setumList(List<UserMessage> umList) {
        this.umList = umList;
    }
public List<UserMessage> getummList() {
        return ummList;
    }
    public void setummList(List<UserMessage> ummList) {
        this.ummList = ummList;
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

 public void setumid(int umid) {
        this.umid = umid;
    }

    public int getumid() {
        return this.umid;
    }
 public void setReqId(short  ReqId) {
        this.ReqId =  ReqId;
    }

    public short getReqId() {
        return this.ReqId;
    }
   public void setIndentId(short IndentId) {
        this.IndentId =  IndentId;
    }

    public short getIndentId() {
        return this.IndentId;
    }

      public void setimName(String  imName) {
        this.imName =  imName;
    }

    public String getimName() {
        return this.imName;
    }
    public void setsmName(String  smName) {
        this.smName =  smName;
    }

    public String getsmName() {
        return this.smName;
    }

    public void setdmName(String  dmName) {
        this.dmName =  dmName;
    }

    public String getdmName() {
        return this.dmName;
    }
    public void setinDate(String  inDate) {
        this.inDate =  inDate;
    }

    public String getinDate() {
        return this.inDate;
    }

     public void setbhmName(String  bhmName) {
        this.bhmName =  bhmName;
    }

    public String getbhmName() {
        return this.bhmName;
    }

    public void setPreparedBy(String  PreparedBy) {
        this.PreparedBy =  PreparedBy;
    }

    public String getPreparedBy() {
        return this.PreparedBy;
    }
    public void setForwardedTo(String  ForwardedTo) {
        this.ForwardedTo =  ForwardedTo;
    }

    public String getForwardedTo() {
        return this.ForwardedTo;
    }



    public void setindtDetail( List<ErpmIndentDetail> indtDetail) {
            this.indtDetail = indtDetail;
    }

     public  List<ErpmIndentDetail> getindtDetail() {
        return indtDetail;
    }
    @Override

     public String execute() throws Exception {
        try {
           //Prepare LOV containing User Institutions
            umList = umDao.findByUserMessage(getumid());
           //setmsgid(getumList().get(0).getUmId());
          // message="hellomsg"+getmsgid();
           return SUCCESS;
            } catch (Exception e) {
            message = "Exception in -> ManageBudgetHeadAxn"  + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

 public String generateReport() throws Exception {
        try {
           //Prepare LOV containing User Institutions
           // umList = umDao.findByUserMessage(getumid());
           //setmsgid(getumList().get(0).getUmId());
          // message="hellomsg"+getmsgid();
          IndentId=getReqId();
         indtmaster=indtmDao .findSimIdbyIndentId(IndentId);
          smmaster=smDao.findBySimId(indtmaster.getSubinstitutionmaster().getSimId());
          smName=smmaster.getSimName();
          dmmaster=dmDao.findByDmId(indtmaster.getDepartmentmaster().getDmId());
          dmName=dmmaster.getDmName();
          immaster=imDao.findByImId(indtmaster.getInstitutionmaster().getImId());
          imName=immaster.getImName();
          inDate=indtmaster.getIndtIndentDate()+"";
          bhmaster=bhmDao.findBybhmId(indtmaster.getDepartmentalBudgetAllocation().getBudgetheadmaster().getBhmId());
          bhmName=bhmaster.getBhmName();
          indtDetail=indtDetailDao.findByIndt_indt_mst_Indent_Id(indtmaster.getIndtIndentId());
         ForwardedTo=indtmaster.getErpmusers().getErpmuFullName();//getErpmusersByIndtForwardedToUserId().getErpmuFullName();
         PreparedBy=indtmaster.getIndtGeneratedBy();
        
         return SUCCESS;
            } catch (Exception e) {
            message = "Exception in -> ManageBudgetHeadAxn"  + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }
 
    }







