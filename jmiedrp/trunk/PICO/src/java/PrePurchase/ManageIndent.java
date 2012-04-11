/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author SKNaqvi
 */
package PrePurchase;
import pojo.hibernate.ErpmIndentMaster;
import pojo.hibernate.ErpmIndentMasterDAO;
import pojo.hibernate.Institutionmaster;
import pojo.hibernate.InstitutionmasterDAO;
import pojo.hibernate.Subinstitutionmaster;
import pojo.hibernate.SubinstitutionmasterDAO;
import pojo.hibernate.Departmentmaster;
import pojo.hibernate.DepartmentmasterDAO;
import pojo.hibernate.Budgetheadmaster;
import pojo.hibernate.BudgetheadmasterDAO;
import pojo.hibernate.ErpmGenMaster;
import pojo.hibernate.ErpmGenMasterDao;

import pojo.hibernate.ErpmItemMasterDAO;
import pojo.hibernate.ErpmIndentDetail;
import pojo.hibernate.ErpmIndentDetailDAO;
import pojo.hibernate.Erpmusers;
import pojo.hibernate.ErpmusersDAO;
import pojo.hibernate.UserMessage;
import pojo.hibernate.UserMessageDAO;


import java.text.*;
import utils.DevelopmentSupport;
import utils.sendMail;

import pojo.hibernate.Workflowmaster;
import pojo.hibernate.WorkflowmasterDAO;
import pojo.hibernate.Workflowdetail;
import pojo.hibernate.WorkflowdetailDAO;
import pojo.hibernate.Workflowactions;
import pojo.hibernate.WorkflowactionsDAO;
import pojo.hibernate.Workflowtransaction;
import pojo.hibernate.WorkflowtransactionDAO;

import java.util.*;
import pojo.hibernate.ErpmItemRate;
import pojo.hibernate.ErpmItemRateDAO;
import pojo.hibernate.ErpmItemMaster;
import org.apache.struts2.interceptor.validation.SkipValidation;
import pojo.hibernate.Committeemaster;
import com.opensymphony.xwork2.ActionContext;

public class ManageIndent extends DevelopmentSupport {

  private ErpmIndentMaster erpmindtmast;
  private Committeemaster cm;
  private Workflowmaster wfm;
  private ErpmIndentDetail erpmindtdet;
  private ErpmItemMaster erpmitemmaster;
  private ErpmItemRate itemrate;
 // private Workflowtransaction wft;
  private short indtdetailId;
  private short indtindentid;
  private Integer irItemRateId;
  private String message;
  private String indentAction;
  private String username;
  private String ErpmuFullName;
  Integer Id;
  Short defaultIndent;
  Erpmusers ermu;
  Erpmusers ermuf;
  private Short DefaultInsitute1;
  private Integer DefaultSubInsitute;
  private Integer DefaultDepartment;
  private Integer defaultCurrency;

    private String defaultCurrencyName;
  private Integer defaultUOP;

  private short indt_indt_mst_Indent_Id;
  private short bhmId;
  Integer erpmuId;
  private Integer selectedItem;
  private String UOP;
  private Integer selectedItemRate;
  private String selectedItemRateValidFrom;
  private String selectedItemRateValidTo;
  private String selectedItemRateCurrency;
  private String irdRate1;

  private ErpmIndentDetailDAO        erpmindetDao   =       new ErpmIndentDetailDAO();

  private ErpmIndentMasterDAO        erpminDao      =       new ErpmIndentMasterDAO();
  private WorkflowtransactionDAO     wftDAO         =       new WorkflowtransactionDAO();
  private Workflowtransaction        wft            =       new Workflowtransaction();

  private List<Institutionmaster>    imList         =       new ArrayList<Institutionmaster>();
  private InstitutionmasterDAO       imDao          =       new InstitutionmasterDAO();

  private List<Subinstitutionmaster> simList        =       new ArrayList<Subinstitutionmaster>();
  private SubinstitutionmasterDAO    simDao         =       new SubinstitutionmasterDAO ();

  private List<Departmentmaster>     dmList         =       new ArrayList<Departmentmaster>();
  private DepartmentmasterDAO        dmDao          =       new DepartmentmasterDAO();

  private List<Budgetheadmaster>     bhmList        =       new ArrayList<Budgetheadmaster>();
  private BudgetheadmasterDAO        bhmDao         =       new BudgetheadmasterDAO();

  private List<ErpmGenMaster>        currencyList   =       new ArrayList<ErpmGenMaster>();
  private List<ErpmGenMaster>        WmnameList     =       new ArrayList<ErpmGenMaster>();
  private ErpmGenMasterDao           currencyDao    =       new ErpmGenMasterDao();

  private List<ErpmIndentMaster>     IndentList     =       new ArrayList<ErpmIndentMaster>();
  private List<ErpmIndentDetail>     indtitemlist   =       new ArrayList<ErpmIndentDetail>();

  private List<ErpmGenMaster>        UOPList        =       new ArrayList<ErpmGenMaster>();

  private List<ErpmItemRate>         itemRateList   =       new ArrayList<ErpmItemRate>();

  private ErpmItemRateDAO            itemRateDao    =       new ErpmItemRateDAO();
  private ErpmGenMasterDao           UOPListDao     =       new ErpmGenMasterDao();

  private ErpmItemMasterDAO          indtitemlistDao=       new ErpmItemMasterDAO();
  private List<ErpmItemMaster>       itemList       =       new ArrayList<ErpmItemMaster>();

 // private ErpmusersDAO ermuDao=new  ErpmusersDAO();

  private List<ErpmIndentDetail>     Browselist     =       new ArrayList<ErpmIndentDetail>();
  private List<String>               iRateList      =       new ArrayList<String>();
  private List<Erpmusers>            erpmuserlist   =       new ArrayList<Erpmusers>();
  private ErpmusersDAO               erpmusersDao   =       new ErpmusersDAO();
  private UserMessageDAO             UMDao          =       new UserMessageDAO();
  private UserMessage                um             =       new UserMessage();

  private WorkflowmasterDAO          WfmDao         =       new WorkflowmasterDAO();
  private List<Workflowmaster>       Wfmnamelist    =       new ArrayList<Workflowmaster>();
  private WorkflowdetailDAO          WfdDao         =       new WorkflowdetailDAO();
  private Workflowdetail             Wfd            =       new Workflowdetail();
  private WorkflowactionsDAO         WfaDao         =       new WorkflowactionsDAO();
  private Workflowactions            Wfa            =       new Workflowactions();
  private List<ErpmGenMaster>        WfaActionsList =       new ArrayList<ErpmGenMaster>();
  private List<Workflowtransaction>  Wftlist        =       new ArrayList<Workflowtransaction>();

  private ErpmGenMasterDao           egmDAO         =       new ErpmGenMasterDao();

  public void setindentAction1(String indentAction)  {
      this.indentAction = indentAction;
  }

  public String  getindentAction() {
      return this.indentAction;
  }
  

  public void setWmnameList(List<ErpmGenMaster> WmnameList) {
                  this.WmnameList = WmnameList;
    }

    public List<ErpmGenMaster> getWmnameList() {
           return this.WmnameList;
                  }

  public void setWfmnamelist(List<Workflowmaster> Wfmnamelist) {
           this.Wfmnamelist = Wfmnamelist;
    }

   public List<Workflowmaster> getWfmnamelist() {
          return this.Wfmnamelist;
 }

 public void setWfaActionsList(List<ErpmGenMaster> WfaActionsList) {
           this.WfaActionsList = WfaActionsList;
    }

 public List<ErpmGenMaster> getWfaActionsList() {
          return this.WfaActionsList;
 }

 public void setcm(Committeemaster cm) {
                 this.cm = cm;
    }

  public Committeemaster getcm() {
                          return this.cm;
  }

  public void setwfm(Workflowmaster wfm) {
                 this.wfm = wfm;
    }

  public Workflowmaster getwfm() {
                          return this.wfm;
  }

  public void setwft(Workflowtransaction wft) {
                 this.wft = wft;
  }
  public Workflowtransaction getwft() {
                          return this.wft;
    }
     

 public void setirdRate1(String irdRate1)  {
      this.irdRate1 = irdRate1;
  }

  public String  getirdRate1() {
      return this.irdRate1;
  }


 public void setselectedItemRateCurrency(String selectedItemRateCurrency)  {
      this.selectedItemRateCurrency = selectedItemRateCurrency;
  }

  public String  getselectedItemRateCurrency() {
      return this.selectedItemRateCurrency;
  }

  public void setselectedItemRateValidFrom(String selectedItemRateValidFrom)  {
      this.selectedItemRateValidFrom = selectedItemRateValidFrom;
  }

  public String  getselectedItemRateValidFrom() {
      return this.selectedItemRateValidFrom;
  }

 public void setselectedItemRateValidTo(String selectedItemRateValidTo)  {
      this.selectedItemRateValidTo = selectedItemRateValidTo;
  }

  public String  getselectedItemRateValidTo() {
      return this.selectedItemRateValidTo;
  }


 public void setiRateList(List<String> iRateList)  {
      this.iRateList = iRateList;
  }

  public List<String>  getiRateList() {
      return this.iRateList;
  }

  public void setselectedItem(Integer selectedItem)  {
      this.selectedItem = selectedItem;
  }

  public Integer  getselectedItem() {
      return this.selectedItem;
  }
  
  public void setUOP(String UOP)  {
      this.UOP = UOP;
  }

  public String getUOP() {
      return this.UOP;
  }

  public void setselectedItemRate(Integer selectedItemRate)  {
      this.selectedItemRate = selectedItemRate;
  }

  public Integer getselectedItemRate() {
      return this.selectedItemRate;
  }

  public void setirItemRateId(Integer irItemRateId) {
                   this.irItemRateId = irItemRateId;
  }

    public Integer getirItemRateId() {
                   return irItemRateId;
    }

  public void setitemrate(ErpmItemRate itemrate) {
                 this.itemrate = itemrate;
    }

    public ErpmItemRate getitemrate() {
                          return this.itemrate;
    }

    /*--------------------------setter getter for erpm_indent_master--------------------------*/

  public void setitemRateList(List<ErpmItemRate> itemRateList) {
           this.itemRateList = itemRateList;
    }

   public List<ErpmItemRate> getitemRateList() {
          return this.itemRateList;
    }


  public void setdefaultCurrency(Integer defaultCurrency) {
           this.defaultCurrency = defaultCurrency;
    }

   public Integer getdefaultCurrency() {
          return this. defaultCurrency;
    }
   
  public void setdefaultUOP(Integer defaultUOP) {
           this.defaultUOP = defaultUOP;
    }

   public Integer getdefaultUOP() {
          return this. defaultUOP;
    }


   public void setDefaultInsitute1(Short DefaultInsitute1) {
           this.DefaultInsitute1 = DefaultInsitute1;
    }

   public Short getDefaultInsitute1() {
           return this. DefaultInsitute1;
    }

   public void setDefaultSubInsitute(Integer DefaultSubInsitute) {
           this.DefaultSubInsitute = DefaultSubInsitute;
    }

   public Integer getDefaultSubInsitute() {
           return this. DefaultSubInsitute;
    }

   public void setDefaultDepartment(Integer DefaultDepartment) {
           this.DefaultDepartment = DefaultDepartment;
    }

   public Integer getDefaultDepartment() {
           return this. DefaultDepartment;
    }

   public void seterpmuserlist(List<Erpmusers> erpmuserlist) {
                 this.erpmuserlist= erpmuserlist;
    }

   public List<Erpmusers> geterpmuserlist() {
             return this.erpmuserlist;
    }

    public void seterpmindtmast(ErpmIndentMaster erpmindtmast) {
                 this.erpmindtmast = erpmindtmast;
    }

    public ErpmIndentMaster geterpmindtmast() {
                          return this.erpmindtmast;
    }

    public void setimList(List<Institutionmaster> imList) {
                this.imList = imList;
    }

    public List<Institutionmaster> getimList() {
                    return this.imList;
    }

    public void setsimList(List<Subinstitutionmaster> simList) {
                       this.simList = simList;
    }

    public List<Subinstitutionmaster> getsimList() {
                   return this.simList;
    }
    
    public void seterpmitemmaster(ErpmItemMaster erpmitemmaster) {
                       this.erpmitemmaster = erpmitemmaster;
    }

    public ErpmItemMaster geterpmitemmaster() {
                   return this.erpmitemmaster;
    }


    public void setMessage(String message) {
                     this.message = message;
    }

    public String getMessage() {
                  return this.message;
    }


    public void setdefaultCurrencyName(String defaultCurrencyName) {
                     this.defaultCurrencyName = defaultCurrencyName;
    }

    public String getdefaultCurrencyName() {
                  return this.defaultCurrencyName;
    }

    public void setIndentList(List<ErpmIndentMaster> IndentList) {
        this.IndentList = IndentList;
    }

    public List<ErpmIndentMaster> getIndentList() {
        return this.IndentList;
    }

    public void setdefaultIndent(Short defaultIndent) {
           this.defaultIndent = defaultIndent;
    }

    public Short getdefaultIndent() {
           return this. defaultIndent;
    }

    public void setBrowselist(List<ErpmIndentDetail>  Browselist) {
        this. Browselist =  Browselist;
    }

    public List<ErpmIndentDetail> getBrowselist() {
        return this. Browselist;
    }

    public void setdmList(List<Departmentmaster> dmList) {
                 this.dmList = dmList;
    }

    public List<Departmentmaster> getdmList() {
             return this.dmList;
    }

    public  void setbhmList(List<Budgetheadmaster> bhmList) {
                   this.bhmList = bhmList;
    }

    public List<Budgetheadmaster> getbhmList() {
             return this.bhmList;
    }

    public void setcurrencyList(List<ErpmGenMaster> currencyList) {
                  this.currencyList = currencyList;
    }

    public List<ErpmGenMaster> getcurrencyList() {
           return this.currencyList;
                  }

    public void setindtdetailId(short indtdetailId) {
                  this.indtdetailId = indtdetailId;
    }

    public short getindtdetailId() {
                    return indtdetailId;
    }

    public void setindtindentid(short indtindentid) {
                   this.indtindentid = indtindentid;    }

    public short getindtindentid() {
                   return indtindentid;
    }

    public void setErpmuFullName(String  ErpmuFullName) {
                   this.ErpmuFullName = ErpmuFullName;
    }

    public String getErpmuFullName() {
                   return ErpmuFullName;
    }

    /*--------------------------setter getter for erpm_indent_detail--------------------------*/

    public void seterpmindtdet(ErpmIndentDetail erpmindtdet) {
                this.erpmindtdet = erpmindtdet;
    }

    public ErpmIndentDetail geterpmindtdet() {
                        return this.erpmindtdet;
    }

    public void setindtitemlist(List<ErpmIndentDetail> indtitemlist) {
                      this.indtitemlist= indtitemlist;
    }

    public List<ErpmIndentDetail> getindtitemlist() {
                         return this.indtitemlist;
    }

    public void setUOPList(List<ErpmGenMaster> UOPList) {
                 this.UOPList= UOPList;
    }

    public List<ErpmGenMaster> getUOPList() {
                    return this.UOPList;
    }

    public void setitemList(List<ErpmItemMaster> itemList) {
                 this.itemList= itemList;
    }

    public List<ErpmItemMaster> getitemList() {
                    return this.itemList;
    }

    public void setindt_indt_mst_Indent_Id(short indt_indt_mst_Indent_Id) {
                   this.indt_indt_mst_Indent_Id= indt_indt_mst_Indent_Id;
    }

    public short getindt_indt_mst_Indent_Id() {
                   return indt_indt_mst_Indent_Id;
    }

    public void setbhmId(short bhmId) {
                   this.bhmId= bhmId;
    }

    public short getbhmId() {
                   return bhmId;
    }

    public void seterpmuId(Integer erpmuId) {
                   this.erpmuId= erpmuId;
    }

    public Integer geterpmuId() {
                   return erpmuId;
    }

    public void setum(UserMessage um) {
                 this.um = um;
    }

    public UserMessage getum() {
                          return this.um;
    }

    public void InitializeLOVs() {
       // defaultIndent = erpmindtmast.getIndtIndentId();
        imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
        simList=simDao.findSubInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Short.valueOf(getSession().getAttribute("imId").toString()));
        dmList=dmDao.findDepartmentForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Integer.valueOf(getSession().getAttribute("simId").toString()));
        bhmList=bhmDao.findforInsitutetBudgetHeadId(Integer.parseInt(getSession().getAttribute("userid").toString()));
        currencyList=currencyDao.findByErpmGmType(Short.parseShort("6"));
        Wfmnamelist=WfmDao.findByErpmGmID(Integer.parseInt("72"));
        

//        Wfmnamelist=WfmDao.findAll();//.findByErpmGmType(Short.parseShort("16"));


        // itemList=indtitemlistDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
       // UOPList=UOPListDao.findByErpmGmType(Short.parseShort("3".toString()));
        //erpmuserlist=erpmusersDao.findAll();//findByforwarededUserId(Integer.valueOf(getSession().getAttribute("userid").toString()));
        //username=getSession().getAttribute("userfullname").toString();
    }
  public void InitializeitemsLOVs() {

      //for getting the All indent List in Indent Field
      //IndentList = erpminDao.findIndentsForUserDepartments(Integer.parseInt(getSession().getAttribute("userid").toString()));            //indtitemlist=erpmindetDao.findByindtIndentId(erpmindtdet.getErpmIndentMaster().getIndtIndentId());

      //for getting the default Id from Indent Master
      //defaultIndent = erpmindtmast.getIndtIndentId();

      //for getting the master field in add indent form
      erpmindtmast = erpminDao.findIndentMasterId(erpmindtmast.getIndtIndentId());

      //for getting the default Indent
     // erpmindtmast = erpminDao.findIndentMasterId(defaultIndent);

      //for getting the item in item drop down
      itemList=indtitemlistDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));

      //for getting the UOP in UOP field
      //UOPList=UOPListDao.findByErpmGmType(Short.parseShort("3".toString()));
      
      //indtitemlist=erpmindetDao.findByindtIndentId(erpmindtdet.getErpmIndentMaster().getIndtIndentId());
//      Calendar c = Calendar.getInstance();
//      itemRateList = itemRateDao.findItemApprovedItems(75, c.getTime(), 6);
  }

   @Override

  public String execute() throws Exception {
    try {
        erpmindtmast=null;
        
        InitializeLOVs();

        //Set default values in LOVs
        DefaultInsitute1=Short.valueOf(getSession().getAttribute("imId").toString());
        DefaultSubInsitute=Integer.valueOf(getSession().getAttribute("simId").toString());
        DefaultDepartment=Integer.valueOf(getSession().getAttribute("dmId").toString());
        defaultCurrency=UOPListDao.findDefaultCurrency("Rupees");
        Wfmnamelist = WfmDao.findByErpmGmID(Integer.parseInt("72"));

        return SUCCESS;
        } catch (Exception e) {
        message = "Exception in -> IndentAxn "  + e.getMessage() + " Reported Cause is: " + e.getCause();
        return ERROR;
        }
        }

   public String ClearIndentMaster() throws Exception {
        try {
            //Clear Indent Master form fields
            erpmindtmast = null;
            InitializeLOVs();
            return SUCCESS;
        }

        catch (Exception e) {
            message = "Exception in -> ClearIndentMaster "  + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }


   //This function Save given IndentMaster Records
  public String SaveIndentMaster() throws Exception {
    try {
       DateFormat formatter ;
       formatter = new SimpleDateFormat("dd-MM-yyyy");
         username   =getSession().getAttribute("userfullname").toString();
         /*---------------for getting Login user name.---------------*/
         ermu=erpmusersDao.findByUserFullNames(username);
         erpmindtmast.setErpmusers(ermu);//setErpmusersByIndtUserId(ermu);

         um.setErpmusersByUmFromErpmuId(ermu);
        // um.setErpmusersByUmToErpmuId(sajidaziz00@));//getErpmusersByIndtForwardedToUserId());
         String message1=getSession().getAttribute("userfullname").toString()+ " has submitted an Indent for your approval";
         um.setUmMessage(message1);
         String message2="http://localhost:8080/pico/Administration/ManageIndentAction.action?";
         um.setUmActionName(message2);
         um.setUmRequestSubmissionDate(erpmindtmast.getIndtIndentDate());
        
         UMDao.save(um);
         
         DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
         Date date = new Date();
       //  String df=(dateFormat.format(date));
         
           DateFormat   formatter1 = new SimpleDateFormat("dd-MM-yyyy");
       // System.out.println(dateFormat.format(date));
        


        if (erpmindtmast.getIndtIndentId()== null)
        {
            erpminDao.save(erpmindtmast);
            message="hi" +erpmindtmast.getIndtIndentId() + ", " + erpmindtmast.getIndtIndentDate() + ", " + erpmindtmast.getWorkflowmaster().getWfmId();

           
            InitializeitemsLOVs();
            //Set Indent ID for passing on to the next screen i.e. Indent Item Details
            defaultIndent = erpmindtmast.getIndtIndentId();
            defaultCurrency = erpmindtmast.getErpmGenMasterByIndtCurrencyId().getErpmgmEgmId();
            defaultCurrencyName=erpmindtmast.getErpmGenMasterByIndtCurrencyId().getErpmgmEgmDesc();
         }
         else {
           // defaultIndent = erpmindtdet.getErpmIndentMaster().getIndtIndentId();
            ErpmIndentMaster erpmindtmast1 =erpminDao.findIndentMasterId(erpmindtmast.getIndtIndentId());
            erpmindtmast1=erpmindtmast;
            erpminDao.update (erpmindtmast1);
            InitializeLOVs();
         }
         return SUCCESS;
        }
        catch (Exception e)
        {
        message = "Exception in save method-> INDENTMasterAxn"  + e.getMessage() + " Reported Cause is: " + e.getCause();
        return ERROR;
        }
  }


  //This function Save and Browse given IndentDetails Records
    public String SaveIndentDetailsItems() throws Exception {
    try {
        if(erpmindtdet.getIndtDetailId()== null) {
        //Retrieve the Indent Master Object
        erpmindtmast = erpminDao.findIndentMasterId(defaultIndent);
        erpmindtdet.setErpmIndentMaster(erpmindtmast);

        if (erpmindtdet.getErpmItemRate().getIrItemRateId() == null)
            erpmindtdet.setErpmItemRate(null);
           
        erpmindetDao.save(erpmindtdet);
       //s validate();
       // erpmitemmaster=null;
       //erpmindtdet.setErpmItemMaster(null);//setErpmItemRate(null);
        //itemrate=null;
        
        //Prepare List of Items entered under the current Indent
        indtitemlist=erpmindetDao.findByindtIndentId(erpmindtdet.getErpmIndentMaster().getIndtIndentId());
        erpmindtdet = null;
        InitializeitemsLOVs();
        message = "Item Added successfully to the Indent";
        }
        else
        {
        //Indent Item Detail has been edited; the code below updates the record
        //Retrieve the Indent Detail record for update
        ErpmIndentDetail erpmindtdet1 =erpmindetDao.findByindtDetailId(getindtdetailId());
            //Assign the changed erpmindtdet to the retrieved record
            erpmindtdet1=erpmindtdet;
            //Retrieve the Indent Master Object
            erpmindtmast = erpminDao.findIndentMasterId(defaultIndent);
            erpmindtdet.setErpmIndentMaster(erpmindtmast);

            if (erpmindtdet.getErpmItemRate().getIrItemRateId() == null)
                erpmindtdet.setErpmItemRate(null);

            //Update the Indent Detail Record
            erpmindetDao.update(erpmindtdet1);
            //Prepare list of Items for the User's Institutions
            itemList=indtitemlistDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
            //Prepare list of Indent Items
            indtitemlist=erpmindetDao.findByindtIndentId(erpmindtdet.getErpmIndentMaster().getIndtIndentId());
            //Clear  the Record
            erpmindtdet=null;
            InitializeitemsLOVs();
            message = "Item updated successfully";
         }
        return SUCCESS;
        } catch (Exception e) 
        {
        // if (e.getCause().toString().contains("Duplicate entry")) {
           // message = "The Item could not be added as it already exits in the Indent";
            //return SUCCESS;
           // }
       // else
            message = "Exception in -> SaveIndentItemsAxn "  + e.getMessage() + " Reported Cause is: " + e.getCause();
        return ERROR;
       }
   }


  //This function Delete  given Indent Master Records
  public String DeleteIndentMaster () throws Exception {
    try {
        //TO DELETE THE INDENT DETAIL BY ID
        erpmindtmast=erpminDao.findIndentMasterId(getindtindentid());
        //DELETE ABOVE ID IN ERPMINDTDET
        erpminDao.delete(erpmindtmast);
        IndentList = erpminDao.findIndentsForUserDepartments(Integer.parseInt(getSession().getAttribute("userid").toString()));
        message = "Record successfully deleted";
        erpmindtmast= null;
        return SUCCESS;
        }
       catch (Exception e) {
        if (e.getCause().toString().contains("fk_INDENTDET_IND_master"))
        message = "The Indent Master cannot be  deleted because of this items is associated with other details";
        else
        message = "Exception in Delete method -> ManageIndentAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
        return ERROR;
        }
        }

  //This function deletes given Indent and Indent Detail Records
  public String DeleteIndentDetail () throws Exception {
    try {
     //TO DELETE THE INDENT DETAIL BY ID
       erpmindtdet=erpmindetDao.findByindtDetailId(getindtdetailId());
       defaultIndent = erpmindtdet.getErpmIndentMaster().getIndtIndentId();
       //DELETE ABOVE ID IN ERPMINDTDET
      erpmindetDao.delete(erpmindtdet);
      message = "Record successfully deleted";
      erpmindtdet.setIndtDetailId(getindtdetailId());
      indtitemlist=erpmindetDao.findByindtIndentId(erpmindtdet.getErpmIndentMaster().getIndtIndentId());
      itemList=indtitemlistDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
      UOPList=UOPListDao.findByErpmGmType(Short.parseShort("3".toString()));
      erpmindtdet= null;

      return SUCCESS;
      }
       catch (Exception e) {
        message = "Exception in Delete method -> Delete Indent Detail" + e.getMessage() + " Reported Cause is: " + e.getCause();
        return ERROR;
        }
    }

  //This function Edit given Indent Master Records
    public String EditIndentMaster () throws Exception {
        int currentStage;
        int wfd_Id_of_CurrStage;
        int nextStage=0;
        
        try {
        //Retrieve the record to be updated into erpindtdet object
          erpmindtmast=erpminDao.findIndentMasterId(getindtindentid());
          imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
          simList=simDao.findSubInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Short.valueOf(getSession().getAttribute("imId").toString()));
          dmList=dmDao.findDepartmentForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Integer.valueOf(getSession().getAttribute("simId").toString()));
          bhmList=bhmDao.findforInsitutetBudgetHeadId(Integer.parseInt(getSession().getAttribute("userid").toString()));
          currencyList=currencyDao.findByErpmGmType(Short.parseShort("6"));
          DefaultInsitute1=Short.valueOf(getSession().getAttribute("imId").toString());
          DefaultSubInsitute=Integer.valueOf(getSession().getAttribute("simId").toString());
          DefaultDepartment=Integer.valueOf(getSession().getAttribute("dmId").toString());
          erpmuserlist=erpmusersDao.findByforwarededUserId(Integer.valueOf(getSession().getAttribute("userid").toString()));
          defaultCurrency=UOPListDao.findDefaultCurrency("Rupees");
          //Find the current stage of Workflow Transaction
          try{
              currentStage = wftDAO.findCurrentWFTStage(erpmindtmast.getIndtIndentId().shortValue());
          }
          catch (Exception e) {
              message = "Stage Done 0";
              Wfmnamelist=WfmDao.findByErpmGmID(Integer.parseInt("72"));
//              Wfmnamelist=WfmDao.findAll();
              currentStage = 0;
          }
          if(currentStage == 0)
          {
              message = "Stage Done 0";
              Wfmnamelist=WfmDao.findByErpmGmID(Integer.parseInt("72"));
//            Wfmnamelist=WfmDao.findAll();
          }
          else
          {
              message = "Stage Done = " + currentStage;
              Wfmnamelist = WfmDao.findWorkFlowListById(wftDAO.findWorkFlowIDByWorkID(erpmindtmast.getIndtIndentId().shortValue()));
          }

// --------------------------------------------         TO DO NEXT    ------------------------------------------------------------

//      Here we have to set WfaActionsList by sending Workflow Detail ID to egmDAO.findErpmGmDescByWFActions function
//      Workflow Detail ID can be extracted by sending Workflow master ID and Stage to a query.
        nextStage = currentStage+1;
        message = "Next Stage = " + nextStage;

        wfd_Id_of_CurrStage = WfdDao.findWorkFlowDetailIdByStage(nextStage, 25);
//        wfd_Id_of_CurrStage = WfdDao.findWorkFlowDetailIdByStage(nextStage, erpmindtmast.getWorkflowmaster().getWfmId());

        message = message + " AND Workflow Detail ID = " + wfd_Id_of_CurrStage;
        WfaActionsList = egmDAO.findErpmGmDescByWFActions(wfd_Id_of_CurrStage);

// --------------------------------------------         TO DO NEXT    ------------------------------------------------------------


          indtitemlist=erpmindetDao.findByindtIndentId(getindtindentid());
          return SUCCESS;
        }
        catch (Exception e) {
            message = "Exception in Edit method -> ManageIndentMasterAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
   }

  //This function opens the given Indent Detail Record for Editing
  public String EditIndentDetail() throws Exception {
    try {

       DateFormat formatter ;
       formatter = new SimpleDateFormat("dd-MM-yyyy");

        //Retrieve the record to be updated into erpindtdet object
        erpmindtdet=erpmindetDao.findByindtDetailId(getindtdetailId());

        //Assign the default currency on AddItems Screen
       // defaultCurrency=UOPListDao.findDefaultCurrency("Rupees");
        defaultCurrencyName=erpmindtmast.getErpmGenMasterByIndtCurrencyId().getErpmgmEgmDesc();
        
        //Assign the Indent Id contained in the Indent Item to defaultIndent
        defaultIndent = erpmindtdet.getErpmIndentMaster().getIndtIndentId();

        //Prepare List of items for the User's Institution
        itemList=indtitemlistDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
        selectedItem = erpmindtdet.getErpmItemMaster().getErpmimId();
        UOP = erpmindtdet.getErpmItemMaster().getErpmGenMaster().getErpmgmEgmDesc();

       // if (erpmindtdet.getErpmItemMaster().getErpmimId() != null) {
        itemRateList = itemRateDao.findItemApprovedItemsForToday(   erpmindtdet.getErpmItemMaster().getErpmimId(),
                                                                    erpmindtdet.getErpmIndentMaster().getIndtIndentDate(),
                                                                    erpmindtdet.getErpmIndentMaster().getErpmGenMasterByIndtCurrencyId().getErpmgmEgmId());        
        //if (itemRateList != null && !itemRateList.isEmpty()) {

                irdRate1 = String.format("%-25s %20s %20s %10d",  itemRateList.get(0).getSuppliermaster().getSmName() ,
                                                                  formatter.format(itemRateList.get(0).getIrdWefDate()),
                                                                  formatter.format(itemRateList.get(0).getIrdWetDate()),
                                                                  itemRateList.get(0).getIrdRate().intValue());

        //}
        //}
/*            for (ErpmItemRate itemRate : itemRateList) {
                Integer val = itemRate.getIrItemRateId();
                String FrmDate = formatter.format(itemRate.getIrdWefDate());
                String ToDate = formatter.format(itemRate.getIrdWetDate());

                String str = String.format("%-25s %20s %20s %10d",  itemRate.getSuppliermaster().getSmName() ,
                                                                    FrmDate,
                                                                    ToDate,
                                                                    (itemRate.getIrdRate().intValue()));
                message = message + "==" + str;
                iRateList.add(val, str);
            }
        }
*/
        if (erpmindtdet.getErpmItemRate().getIrdRate() != null) {
                selectedItemRate = erpmindtdet.getErpmItemRate().getIrdRate().intValue();
        
            selectedItemRateCurrency = erpmindtdet.getErpmIndentMaster().getErpmGenMasterByIndtCurrencyId().getErpmgmEgmDesc();
            selectedItemRateValidFrom = formatter.format(erpmindtdet.getErpmItemRate().getIrdWefDate());
            selectedItemRateValidTo = formatter.format(erpmindtdet.getErpmItemRate().getIrdWetDate());
        }
        else
            message = "Null";
        //IndentList = erpminDao.findIndentsForUserDepartments(Integer.parseInt(getSession().getAttribute("userid").toString()));

        //UOPList=UOPListDao.findByErpmGmType(Short.parseShort("3".toString()));
        return SUCCESS;
        }catch  (Exception e) {

        message = "Exception in Edit method -> ManageEditItemsAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
        return SUCCESS;
        }
        }

  //This function  Browse the given Indent Master data
  public String BrowseIndentsMaster () throws Exception {
   try {
        IndentList = erpminDao.findIndentsForUserDepartments(Integer.parseInt(getSession().getAttribute("userid").toString()));

       // message="hi" +IndentList;
//        erpmuserlist=erpmusersDao.findAll();//findByforwarededUserId(Integer.valueOf(getSession().getAttribute("userid").toString()));
        //erpmindtmast=null;
        //message = "Record successfully deleted" + IndentList;
        return SUCCESS;
        } catch (Exception e) {
        message = "Exception in Browse method -> MangeBrowseIndentsAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
        return ERROR;
        } }

  //This function  Browse the given Indent Detail Item data
  public String BrowseIndentDetail () throws Exception {
   try {
       //Find Indent record to be shown
         erpmindtmast=erpminDao.findIndentMasterId(getindtindentid());
         indtitemlist=erpmindetDao.findByIndt_indt_mst_Indent_Id(getindtindentid());

         //Assign the Indent Id contained in the Indent Item to defaultIndent
        defaultIndent = erpmindtdet.getErpmIndentMaster().getIndtIndentId();
         //Assign the default currency on AddItems Screen
        //defaultCurrency=UOPListDao.findDefaultCurrency("Rupees");
        defaultCurrencyName=erpmindtmast.getErpmGenMasterByIndtCurrencyId().getErpmgmEgmDesc();
        /// erpmindtmast=null;
         InitializeLOVs();
       return SUCCESS;
       }
       catch (Exception e) {
       message = "Exception in BrowseIndentDetail -> BrowseIndentDetailAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
       return ERROR;
       } }

  @SkipValidation
   public String ShowIndentDetailItems () throws Exception {
   try {
       //Find Indent record to be shown
         erpmindtmast=erpminDao.findIndentMasterId(getindtindentid());

       //         indtitemlist=erpmindetDao.findAll();//findByIndt_indt_mst_Indent_Id(getindtindentid());
//       erpmindtmast=erpminDao.findIndentMasterId(getindtindentid());
       message = "THIS IS TO SHOW ITEMs LIST OF " + getindtindentid();
       indtitemlist=erpmindetDao.findByindtIndentId(getindtindentid());

         //Assign the Indent Id contained in the Indent Item to defaultIndent
       // defaultIndent = erpmindtdet.getErpmIndentMaster().getIndtIndentId();
         //Assign the default currency on AddItems Screen
        //defaultCurrency=UOPListDao.findDefaultCurrency("Rupees");
        //defaultCurrencyName=erpmindtmast.getErpmGenMasterByIndtCurrencyId().getErpmgmEgmDesc();
        /// erpmindtmast=null;
         //InitializeLOVs();
       return SUCCESS;
       }
       catch (Exception e) {
       message = "Exception in BrowseIndentDetail -> BrowseIndentDetailAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
       return ERROR;
       } }

   public String ClearIndentDetail() throws Exception {
        try {
            //Clear form field
            erpmindtdet = null;
            itemList=indtitemlistDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
           // imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
            return SUCCESS;
           } catch (Exception e) {
            message = "Exception in -> DepartmentalBudgetAllocationAxn"  + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

  public void validate()  {
    try {
        
        if(erpmindtmast.getIndtIndentId()==null ) {       // message="hi in validate with condition" +erpmindtmast.getIndtIndentId();
        if(erpmindtmast.getInstitutionmaster().getImId()==null)
            addFieldError("erpmindtmast.institutionmaster.imId", "Please double click here to select institution");
        if(erpmindtmast.getSubinstitutionmaster().getSimId()==null)
            addFieldError("erpmindtmast.subinstitutionmaster.simId","Please double click here to select Subinstitute");
        if(erpmindtmast.getDepartmentmaster().getDmId()==null)
            addFieldError("erpmindtmast.departmentmaster.dmId" ,"Please double click here to select Department");
        if(erpmindtmast.getIndtIndentDate()==null)
            addFieldError("erpmindtmast.indtIndentDate" ,"Please Select Date");
        if(erpmindtmast.getBudgetheadmaster().getBhmId()==null)
            addFieldError("erpmindtmast.budgetheadmaster.bhmId" ,"Please double click here to  Select Budget Name");
        if(erpmindtmast.getErpmGenMasterByIndtCurrencyId().getErpmgmEgmId()==null)
            addFieldError("erpmindtmast.erpmGenMasterByIndtCurrencyId.erpmgmEgmId" ,"Please select currency of purchase");
        if(erpmindtmast.getIndtForwardedToEmail().toString()==null)
            addFieldError("erpmindtmast.indtForwardedToEmail" ,"Please double click here to select Forwarded to");
        InitializeLOVs();
        }
        message="The validate is being performed ";
        if(erpmindtdet.getErpmItemMaster().getErpmimId()==null) {
        addFieldError("erpmindtdet.erpmItemMaster.erpmimId" ,"Please double click here to select Item");
        }
       /* else {
        Boolean duplicate = erpmindetDao.findDuplicateItem(defaultIndent, erpmindtdet.getErpmItemMaster().getErpmimId());
        if (duplicate.TRUE)
        addFieldError("erpmindtdet.erpmItemMaster.erpmimId" ,"Item already exists in Indent");
        }*/
        itemList=indtitemlistDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
        if(erpmindtdet.getIndtApproxcost()==0)
            addFieldError("erpmindtdet.indtApproxcost" ,"Please give Valid Approx Cost");
        if(erpmindtdet.getIndtApproxcost()<0)
            addFieldError("erpmindtdet.indtApproxcost" ," Approx Cost  cannot  be negative");
        if(erpmindtdet.getIndtQuantity()==0)
            addFieldError("erpmindtdet.indtQuantity" ,"Quantity should not be Blank");
        else  if(erpmindtdet.getIndtQuantity()<0)
            addFieldError("erpmindtdet.indtQuantity" ,"Quantity should not be Negative");
        
       /* Boolean duplicate = erpmindetDao.findDuplicateItem(defaultIndent, erpmindtdet.getErpmItemMaster().getErpmimId());
        if (duplicate.TRUE)
                    addFieldError("erpmindtdet.erpmItemMaster.erpmimId" ,"Item alreadyt exists in Indent");*/
    }
          // message="hi" +npe.getCause();
        catch (NullPointerException npe) {
          // message="hi" +npe.getCause();
        }
    }

   public String Browse() throws Exception {
        try {
             ErpmIndentMasterDAO        erpminDao1    =     new ErpmIndentMasterDAO();
             IndentList = erpminDao1.findAll();//findAll();

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Browse method -> MangesupplierAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

   public String SubmitWorkflowAction() throws Exception {
        int currentStage=0 ;
        Committeemaster sourceCommittee = new Committeemaster();
        Committeemaster destinationCommittee = new Committeemaster();

        Date Today = Calendar.getInstance().getTime();


        try {

        //Check the number of items in the Indent
        List<ErpmIndentDetail> indentList  =   erpmindetDao.findByindtIndentId(erpmindtmast.getIndtIndentId());
        if(indentList.size() == 0)
            message = "The indent has no items, please add item(s) before taking action";
        else {//If itenms are present in indent 
                //Is action selected by the user under his purview
                //Valiadate all requiste fields
                //Save Workflow Transaction

                //Find the current stage of Workflow Transaction
                try{
                    currentStage = wftDAO.findCurrentWFTStage(erpmindtmast.getIndtIndentId().shortValue());
                    }
                catch (Exception e) {
                        //message = "Entered exception" + e.getMessage() + e.getCause() + currentStage + " Indent Id is : "+erpmindtmast.getIndtIndentId().shortValue();
                        currentStage=0;
                    }
                currentStage = currentStage+1;

                //Find the destination Committee Id for the current stage of the workflow
                try{
                    sourceCommittee = WfdDao.findSourceCommittee(currentStage, erpmindtmast.getWorkflowmaster().getWfmId());
                    destinationCommittee = WfdDao.findDestinationCommittee(currentStage, erpmindtmast.getWorkflowmaster().getWfmId());
                     wft.setCommitteemasterByWftSourceId(sourceCommittee);
                     wft.setCommitteemasterByWftDestinationId(destinationCommittee);
                     wft.setWftWorkId(erpmindtmast.getIndtIndentId());
                     wft.setWftStage(currentStage);
                     wft.setWftDestinationEmail(destinationCommittee.getCommitteeConvener());
                     wft.setWftDate(Today);
                     wft.setWorkflowmaster(erpmindtmast.getWorkflowmaster());
                     wftDAO.save(wft);

                     //Notify the convener of the Destination Committee for the assigned work
                     notifyWork(destinationCommittee.getCommitteeConvener());
                     message = "This Indent has been acted upon and passed to " + destinationCommittee.getCommitteeConvener() + " as per the flow defined.";
                    }
                    catch (Exception e) {
                       message = message + "Exception in method -> SubmitWorkflowAction" + e.getMessage() + " Reported Cause is: " + e.getCause();
                    }
            }
        return SUCCESS;
        }   catch (Exception e) {
                message = "Exception in method -> SubmitWorkflowAction" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
            }
    }


public String  notifyWork(String toEmailAddress) throws Exception {
        try {

            Locale locale = ActionContext.getContext().getLocale();
            ResourceBundle bundle = ResourceBundle.getBundle("pico", locale);
            
            String emailSubject = "Indent submitted for action";
            String emailMessage = "<html><head><title>An indent has been submitted for action</title></head><body><table width='500' border='0' align='left' cellpadding='15' cellspacing='0' style='font-family:Verdana, Arial, Helvetica, sans-serif; font-size:12pt; color:#5a5a5a;'><tr><td align='left'><p>Dear ....,</p></td></tr><tr><td align='left'><p ></p><br/><p></p></td></tr></table></body></html>";
            sendMail.sendMail(bundle.getString("emailFrom"), bundle.getString("emailUser"), bundle.getString("emailFromPasswd"), toEmailAddress, emailSubject, emailMessage);


            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Notify work method -> DeleteUserProfileAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

  public String ShowPendingIndents() throws Exception {
        try {
           IndentList = wftDAO.findWorkflowTransPendingForAction(getSession().getAttribute("username").toString());
        return SUCCESS;
        } catch (Exception e) {
        message = "Exception in Show Pending Indent(s) method -> MangeBrowseIndentsAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
        return ERROR;
        } }

}