/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PrePurchase;
/**
 *
 * @author Sajid Aziz
 */
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
import pojo.hibernate.ErpmItemMaster;
import pojo.hibernate.ErpmItemMasterDAO;
import pojo.hibernate.ErpmIndentDetail;
import pojo.hibernate.ErpmIndentDetailDAO;
import utils.DevelopmentSupport;

import java.util.*;
import pojo.hibernate.Erpmusers;
import pojo.hibernate.ErpmusersDAO;

import pojo.hibernate.UserMessage;
import pojo.hibernate.UserMessageDAO;


public class ManageIndent extends DevelopmentSupport {

  private ErpmIndentMaster erpmindtmast;
  private ErpmIndentDetail erpmindtdet;
  private short indtdetailId;
  private short indtindentid;
  private String message;
  private String username;
  private String ErpmuFullName;
  Integer Id;
  Short defaultIndent;
  Erpmusers ermu;
  Erpmusers ermuf;

  private short indt_indt_mst_Indent_Id;
  private short bhmId;
  Integer erpmuId;

  private ErpmIndentMasterDAO        erpminDao     =     new ErpmIndentMasterDAO();
  private ErpmIndentDetailDAO        erpmindetDao  =     new ErpmIndentDetailDAO();

  private List<Institutionmaster>     imList       =     new ArrayList<Institutionmaster>();
  private    InstitutionmasterDAO     imDao        =     new InstitutionmasterDAO();

  private List<Subinstitutionmaster>  simList      =     new ArrayList<Subinstitutionmaster>();
  private    SubinstitutionmasterDAO  simDao       =     new SubinstitutionmasterDAO ();

  private List<Departmentmaster>      dmList       =     new ArrayList<Departmentmaster>();
  private    DepartmentmasterDAO      dmDao        =     new DepartmentmasterDAO();

  private List<Budgetheadmaster>      bhmList      =     new ArrayList<Budgetheadmaster>();
  private    BudgetheadmasterDAO      bhmDao       =     new BudgetheadmasterDAO();

  private List<ErpmGenMaster>        currencyList  =     new ArrayList<ErpmGenMaster>();
  private ErpmGenMasterDao           currencyDao   =     new ErpmGenMasterDao();

  private List<ErpmIndentMaster>     IndentList    =     new ArrayList<ErpmIndentMaster>();
  private List<ErpmIndentDetail>     indtitemlist  =     new ArrayList<ErpmIndentDetail>();

  private List<ErpmGenMaster>        UOPList       =     new ArrayList<ErpmGenMaster>();
  private ErpmGenMasterDao           UOPListDao    =     new ErpmGenMasterDao();

  private ErpmItemMasterDAO          indtitemlistDao =   new ErpmItemMasterDAO();
  private List<ErpmItemMaster>       itemList        =   new ArrayList<ErpmItemMaster>();

 // private ErpmusersDAO ermuDao=new  ErpmusersDAO();

  private List<ErpmIndentDetail>     Browselist  =     new ArrayList<ErpmIndentDetail>();

  private List<Erpmusers> erpmuserList1 = new ArrayList<Erpmusers>();
  private ErpmusersDAO erpmusersDao = new ErpmusersDAO();
  private UserMessageDAO UMDao = new UserMessageDAO();
  private UserMessage        um     =     new UserMessage();

    /*--------------------------setter getter for erpm_indent_master--------------------------*/

  public void seterpmuserList1(List<Erpmusers> erpmuserList1) {
                 this.erpmuserList1 = erpmuserList1;
    }

  public List<Erpmusers> geterpmuserList1() {
             return this.erpmuserList1;
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

  public void setMessage(String message) {
                     this.message = message;
    }

  public String getMessage() {
                  return this.message;
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

      //for getting the default Id from Indent Master
      defaultIndent = erpmindtmast.getIndtIndentId();
      //for getting the item in item drop down
      itemList=indtitemlistDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
      //for getting the UOP in UOP field
      UOPList=UOPListDao.findByErpmGmType(Short.parseShort("3".toString()));
      //for getting the All indent List in Indent Field
      //IndentList = erpminDao.findIndentsForUserDepartments(Integer.parseInt(getSession().getAttribute("userid").toString()));
        //for getting the master field in add indent form
     //erpmindtmast = erpminDao.findIndentMasterId(erpmindtmast.getIndtIndentId());
            }
  public void InitializeitemsLOVs() {

            //for getting the All indent List in Indent Field
      IndentList = erpminDao.findIndentsForUserDepartments(Integer.parseInt(getSession().getAttribute("userid").toString()));            //indtitemlist=erpmindetDao.findByindtIndentId(erpmindtdet.getErpmIndentMaster().getIndtIndentId());
            //for getting the default Id from Indent Master
      defaultIndent = erpmindtmast.getIndtIndentId();
                  //for getting the master field in add indent form
      erpmindtmast = erpminDao.findIndentMasterId(erpmindtmast.getIndtIndentId());
      //for getting the default Indent
      erpmindtmast = erpminDao.findIndentMasterId(defaultIndent);
             //for getting the item in item drop down
      itemList=indtitemlistDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
             //for getting the UOP in UOP field
      UOPList=UOPListDao.findByErpmGmType(Short.parseShort("3".toString()));
          //indtitemlist=erpmindetDao.findByindtIndentId(erpmindtdet.getErpmIndentMaster().getIndtIndentId());
      indtitemlist=erpmindetDao.findByindtIndentId(erpmindtdet.getErpmIndentMaster().getIndtIndentId());}

   @Override
  public String execute() throws Exception {
    try {
        imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
        simList = simDao.findAll();
        dmList=dmDao.findDepartmentForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Integer.valueOf(getSession().getAttribute("simId").toString()));
        bhmList=bhmDao.findforInsitutetBudgetHeadId(Integer.parseInt(getSession().getAttribute("userid").toString()));
        currencyList=currencyDao.findByErpmGmType(Short.parseShort("6"));
        itemList=indtitemlistDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
        //itemList=indtitemlistDao.findAll();
        UOPList=UOPListDao.findByErpmGmType(Short.parseShort("3".toString()));
        erpmuserList1=erpmusersDao.findAll();
       username   =getSession().getAttribute("userfullname").toString();
       message="Pico User Name :"+username;
        return SUCCESS;
        } catch (Exception e) {
        message = "Exception in -> IndentAxn"  + e.getMessage() + " Reported Cause is: " + e.getCause();
        return ERROR;
        }
        }

  //This function Save given IndentMaster Records
  public String SaveIndentMaster() throws Exception {
    try {

        username   =getSession().getAttribute("userfullname").toString();
       /*---------------for getting Login user name.---------------*/
        ermu=erpmusersDao.findByUserFullNames(username);
        erpmindtmast.setErpmusersByIndtUserId(ermu);

        um.setErpmusersByUmFromErpmuId(ermu);

        um.setErpmusersByUmToErpmuId(erpmindtmast.getErpmusersByIndtForwardedToUserId());

        String message1=getSession().getAttribute("userfullname").toString()+ " has submitted an Indent for your approval";

        um.setUmMessage(message1);

        String message2="http://localhost:8080/pico/Administration/ManageIndentAction.action?";

        um.setUmActionName(message2);

        um.setUmRequestSubmissionDate(erpmindtmast.getIndtIndentDate());

        UMDao.save(um);

        if (erpmindtmast.getIndtIndentId()== null)
        {
         if(erpmindtmast.getInstitutionmaster().getImId() == null)
                message = "Please select Insitution";
         else if(erpmindtmast.getSubinstitutionmaster().getSimId()==0)
                message = "Please select college/faculty/school";
         else if(erpmindtmast.getDepartmentmaster().getDmId()==0)
                message = "Please select Department Name";
         else if(erpmindtmast.getBudgetheadmaster().getBhmId()==0)
         message = "Please select Budget Type";
         //this will Save the Data
         erpminDao.save(erpmindtmast);
         InitializeLOVs();
         erpmindtmast=null;
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
        }   }

  //This function Save and Browse given IndentDetails Records
    public String SaveIndentDetailsItems() throws Exception {
    try {
        if (erpmindtdet.getIndtDetailId()== null)
        { if(erpmindtdet.getErpmItemMaster().getErpmimId()==null)
        message = "Please select Item";
        else if(erpmindtdet.getIndtDetailId()==null)
        if (erpmindtdet.getIndtDetailId()== null)
        erpmindtmast = erpminDao.findIndentMasterId(defaultIndent);
        erpmindtdet.setErpmIndentMaster(erpmindtmast);
        erpmindetDao.save(erpmindtdet);
        erpmindtmast = erpminDao.findIndentMasterId(defaultIndent);
        indtitemlist=erpmindetDao.findByindtIndentId(erpmindtdet.getErpmIndentMaster().getIndtIndentId());
        InitializeitemsLOVs();
        erpmindtdet=null;
        }
        else
        {
        ErpmIndentDetail erpmindtdet1 =erpmindetDao.findByindtDetailId(getindtdetailId());
        erpmindtdet1=erpmindtdet;
        ErpmIndentMaster erpmindtmast1=erpminDao.findIndentMasterId(defaultIndent);
      // message="default"+defaultIndent;
         erpmindtdet1.setErpmIndentMaster(erpmindtmast1);
        erpmindetDao.update(erpmindtdet1);
         itemList=indtitemlistDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
        indtitemlist=erpmindetDao.findByindtIndentId(erpmindtdet.getErpmIndentMaster().getIndtIndentId());
        erpmindtdet=null;
         }
        return SUCCESS;
        } catch (Exception e) {
        message = "Exception in -> SaveINDENTItemsAxn"  + e.getMessage() + " Reported Cause is: " + e.getCause();
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
        message = "The Indent Master cannot be  deleted because of this items is associated with other details,would you like to delete or not";
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
    try {
           //Retrieve the record to be updated into erpindtdet object
       erpmindtmast=erpminDao.findIndentMasterId(getindtindentid());
       imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
       simList = simDao.findAll();
       dmList=dmDao.findDepartmentForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Integer.valueOf(getSession().getAttribute("simId").toString()));
       bhmList=bhmDao.findforInsitutetBudgetHeadId(Integer.parseInt(getSession().getAttribute("userid").toString()));
       currencyList=currencyDao.findByErpmGmType(Short.parseShort("6"));
       erpmuserList1=erpmusersDao.findAll();
       return SUCCESS;

       }catch (Exception e) {
       message = "Exception in Edit method -> ManageIndentMasterAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
       return ERROR;
       }
       }

  //This function Edit given Indent Detail Records
  public String EditIndentDetail() throws Exception {
    try {

        //Retrieve the record to be updated into erpindtdet object
        erpmindtdet=erpmindetDao.findByindtDetailId(getindtdetailId());
       //to get the default indent onadd items screen changes by
        defaultIndent = erpmindtdet.getErpmIndentMaster().getIndtIndentId();
        IndentList = erpminDao.findIndentsForUserDepartments(Integer.parseInt(getSession().getAttribute("userid").toString()));
        itemList=indtitemlistDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
        UOPList=UOPListDao.findByErpmGmType(Short.parseShort("3".toString()));
        return SUCCESS;
        }catch (Exception e) {

        message = "Exception in Edit method -> ManageEditItemsAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
        return ERROR;
        }
        }

  //This function  Browse the given Indent Master data
  public String BrowseIndentsMaster () throws Exception {
   try {
        IndentList = erpminDao.findIndentsForUserDepartments(Integer.parseInt(getSession().getAttribute("userid").toString()));
        message="hi" +IndentList;
        erpmuserList1=erpmusersDao.findAll();
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
         InitializeLOVs();       
       return SUCCESS;
       }
       catch (Exception e) {
       message = "Exception in BrowseIndentDetail -> BrowseIndentDetailAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
       return ERROR;
       } }

   public String ClearIndentMaster() throws Exception {
        try {
            //Clear form field
            erpmindtmast = null;
            execute();
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in -> DepartmentalBudgetAllocationAxn"  + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

   public String ClearIndentDetail() throws Exception {
        try {
            //Clear form field
            erpmindtdet = null;
            execute();
           // imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
            return SUCCESS;
           } catch (Exception e) {
            message = "Exception in -> DepartmentalBudgetAllocationAxn"  + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

}