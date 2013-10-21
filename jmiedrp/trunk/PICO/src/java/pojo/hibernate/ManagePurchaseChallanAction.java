/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * @author Tanvir Ahmed and Sajid
 */
package pojo.hibernate;

import pojo.hibernate.ErpmPurchasechallanMaster;
import pojo.hibernate.ErpmPurchaseChallanMasterDAO;

import pojo.hibernate.Subinstitutionmaster;
import pojo.hibernate.SubinstitutionmasterDAO;

import pojo.hibernate.Institutionmaster;
import pojo.hibernate.InstitutionmasterDAO;

import pojo.hibernate.Departmentmaster;
import pojo.hibernate.DepartmentmasterDAO;

import pojo.hibernate.Erpmusers;
import pojo.hibernate.ErpmusersDAO;

import pojo.hibernate.ErpmItemMaster;
import pojo.hibernate.ErpmItemMasterDAO;

import pojo.hibernate.ErpmPoMaster;
import pojo.hibernate.ErpmPoMasterDAO;

import utils.DevelopmentSupport;

import java.util.ArrayList;
import java.util.List;
import pojo.hibernate.ErpmPurchaseChallanDetailDAO;
import pojo.hibernate.ErpmPurchasechallanDetail;

public class ManagePurchaseChallanAction extends DevelopmentSupport{

private ErpmPurchasechallanMaster       PChallanMast;           //=     new ErpmPurchasechallanMaster();
private ErpmPurchaseChallanDetailDAO    PCDetailDAO     =     new ErpmPurchaseChallanDetailDAO();
private ErpmPurchasechallanDetail       PCDetail;// = new ErpmPurchasechallanDetail();
private List<ErpmPurchasechallanDetail>          PCDetailslist   =     new ArrayList<ErpmPurchasechallanDetail>();
private List<ErpmPurchasechallanMaster> PChallanMastList =     new ArrayList<ErpmPurchasechallanMaster>();
private ErpmPurchaseChallanMasterDAO    PChallanMastDao  =     new ErpmPurchaseChallanMasterDAO ();
private List<Institutionmaster>         imList           =     new ArrayList<Institutionmaster>();
private InstitutionmasterDAO            imDao            =     new InstitutionmasterDAO();
private List<Subinstitutionmaster>      simList          =     new ArrayList<Subinstitutionmaster>();
private SubinstitutionmasterDAO         simDao           =     new SubinstitutionmasterDAO ();
private List<Departmentmaster>          dmList           =     new ArrayList<Departmentmaster>();
private DepartmentmasterDAO             dmDao            =     new DepartmentmasterDAO();
private List<Erpmusers>                 erpmuserlist     =     new ArrayList<Erpmusers>();
private ErpmusersDAO                    erpmusersDao     =     new ErpmusersDAO();
private List<ErpmItemMaster>            itemlist         =     new ArrayList<ErpmItemMaster>();
private ErpmItemMasterDAO               itemlistDAO      =     new ErpmItemMasterDAO();
private List<ErpmPoMaster>              POMasterList     =      new ArrayList<ErpmPoMaster>();
private ErpmPoMasterDAO                 pomasterDAO      =     new ErpmPoMasterDAO();

Erpmusers ermu;
private String username;
private Short DefaultInsitute;
private Integer DefaultSubInsitute;
private Integer DefaultDepartment;
private String message;
private Integer PCMPCMID;
private Integer defaultPCM;
private Integer pcdPcdId;
private Integer PCDetailsId;



Institutionmaster im;

 public void setpcdPcdId(Integer pcdPcdId) {
                  this.pcdPcdId = pcdPcdId;
    }
  public Integer getpcdPcdId() {
                    return pcdPcdId;
    }
  public void setPCDetailsId(Integer PCDetailsId) {
                  this.PCDetailsId = PCDetailsId;
    }
  public Integer getPCDetailsId() {
                    return PCDetailsId;
    }

  public void setPCDetailslist(List<ErpmPurchasechallanDetail> PCDetailslist) {
                      this.PCDetailslist= PCDetailslist;
                                }
  public List <ErpmPurchasechallanDetail> getPCDetailslist() {
                         return this.PCDetailslist;
                                }
public String getMessage() {
        return message;
    }

  public void setMessage(String message) {
        this.message = message;
    }

  public void setusername(String username) {
                   this.username= username;
    }
  public String getusername() {
                   return username;
    }

    public void setPChallanMast(ErpmPurchasechallanMaster PChallanMast) {
        this.PChallanMast = PChallanMast;
    }

    public ErpmPurchasechallanMaster getPChallanMast() {
        return this.PChallanMast;
    }

    public List<Institutionmaster> getimList() {
        return imList;
    }
    public void setimList(List<Institutionmaster> imList) {
        this.imList = imList;
    }

    public List<Subinstitutionmaster> getsimList() {
        return simList;
    }
    public void setsimList(List<Subinstitutionmaster> simList) {
        this.simList = simList;
    }

    public List<Departmentmaster> getdmList() {
        return dmList;
    }
    public void setdmList(List<Departmentmaster> dmList) {
        this.dmList = dmList;
    }

    public List<ErpmPoMaster> getPOMasterList() {
        return POMasterList;
    }
    public void setPOMasterList(List<ErpmPoMaster> POMasterList) {
        this.POMasterList = POMasterList;
    }

    public List<Erpmusers> geterpmuserlist() {
        return erpmuserlist;
    }
    public void seterpmuserlist(List<Erpmusers> erpmuserlist) {
        this.erpmuserlist = erpmuserlist;
    }

    public List<ErpmPurchasechallanMaster> getPChallanMastList() {
        return PChallanMastList;
    }

    public void setPChallanMastList(List<ErpmPurchasechallanMaster> PChallanMastList) {
        this.PChallanMastList = PChallanMastList;
    }

   public void setDefaultInsitute(Short DefaultInsitute) {
           this.DefaultInsitute = DefaultInsitute;
    }
   public Short getDefaultInsitute() {
           return this. DefaultInsitute;
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
  public void setdefaultPCM(Integer defaultPCM) {
           this.defaultPCM = defaultPCM;
    }
  public Integer getdefaultPCM() {
           return this. defaultPCM;
    }
  public void setitemlist(List<ErpmItemMaster> itemlist) {
                      this.itemlist= itemlist;
                                }
  public List<ErpmItemMaster> getitemlist() {
                         return this.itemlist;
                                }

    public Integer getPCMPCMID() {
        return PCMPCMID;
    }

    public void setPCMPCMID(Integer PCMPCMID) {
        this.PCMPCMID = PCMPCMID;
    }

    public void setPCDetail(ErpmPurchasechallanDetail PCDetail) {
        this.PCDetail = PCDetail;
    }

    public ErpmPurchasechallanDetail getPCDetail() {
        return this.PCDetail;
    }



    @Override

    public String execute() throws Exception {

        try {
            imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
            simList=simDao.findSubInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Short.valueOf(getSession().getAttribute("imId").toString()));
            dmList=dmDao.findDepartmentForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Integer.valueOf(getSession().getAttribute("simId").toString()));
            erpmuserlist=erpmusersDao.findAll();
            //POMasterList = pomasterDAO.findAll();
            //These 3 lines fetch default Insitute,Subinstitute,Department(from user's profile)
            DefaultInsitute=Short.valueOf(getSession().getAttribute("imId").toString());
            DefaultSubInsitute=Integer.valueOf(getSession().getAttribute("simId").toString());
            DefaultDepartment=Integer.valueOf(getSession().getAttribute("dmId").toString());

            return SUCCESS;
            } catch (Exception e) {
                message = "Exception in Execute method -> ManagePurchaseChallanAxn"  + e.getMessage() + e.getCause();
                return ERROR;
       }
    }

 public String SavePurchaseChallan() throws Exception {
        try {
            username   =getSession().getAttribute("userfullname").toString();
           //---------------for getting Login user name.---------------
            ermu = erpmusersDao.findByUserFullNames(username);
            PChallanMast.setErpmusers(ermu);
            //message = "" + ermu;
            //If part saves record for the first time; else parts is for record update
            if (PChallanMast.getPcmPcmId() == null) {
                PChallanMastDao.save(PChallanMast);
                //for getting the default POM on detail id
                defaultPCM=PChallanMast.getPcmPcmId();
                getSession().setAttribute("POMID", defaultPCM);
                //for set the all field of PO Master Screen in add details screen
                PChallanMast=PChallanMastDao.findBypcmPcmId(defaultPCM);
                //for getting all items for their insitute
                DefaultInsitute=PChallanMast.getInstitutionmaster().getImId();
                itemlist=itemlistDAO.findByImId(DefaultInsitute);
                message = "Purchase Challan record saved successfully. Purchase Challan Id is " + PChallanMast.getPcmPcmId();
            } else {
                ErpmPurchasechallanMaster pchallanMast1 =  PChallanMastDao.findBypcmPcmId(PChallanMast.getPcmPcmId());
                pchallanMast1 = PChallanMast;
                PChallanMastDao.update(pchallanMast1);
                message = "General Terms record saved successfully.";
            }
            imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
            simList=simDao.findSubInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Short.valueOf(getSession().getAttribute("imId").toString()));
            dmList=dmDao.findDepartmentForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Integer.valueOf(getSession().getAttribute("simId").toString()));
            erpmuserlist=erpmusersDao.findAll();
            return SUCCESS;
        } catch (Exception e) {
            if (e.getCause().toString().contains("Out of range value for column 'PCM_ImportExchange_Rate'"))
                message = "Please insert correct value of Import Exchange Rate i.e. between 0 to 999.";
            else if  (e.getCause().toString().contains("PCM_ID_SIM_DM_PO_CHNO_UNIQUE"))
                message = "You cannot enter duplicate Challan No. for the same PO of same Institute, Sub-Institute and Department";
            else
                message = "Exception in Save method -> ManagePurchaseChallanAxn '" + e.getMessage() + "' Reported Cause is: '" + e.getCause() +"'";
            return ERROR;
        }
    }

  public String  SavePurchaseChallanDetail() throws Exception {
    try {
       if (PCDetail.getPcdPcdId()== null) {
         if(PCDetail.getPcdRecvQuantity().toString()==null)
         message="Please give Quantity";
         else if(PCDetail.getPcdPcdId()== null)
         defaultPCM=PChallanMast.getPcmPcmId();
         PChallanMast = PChallanMastDao.findByPcmId(defaultPCM);
         PCDetail.setErpmPurchasechallanMaster(PChallanMast);
         PCDetailDAO.save(PCDetail);
         message="recored saved";

         // +PChallanMast.getPcmPcmId() +PCDetail.getPcdPcdId();
         PChallanMast = PChallanMastDao.findByPcmId(defaultPCM);
         DefaultInsitute=PChallanMast.getInstitutionmaster().getImId();
         itemlist=itemlistDAO.findByImId(DefaultInsitute);
         PCDetailslist=PCDetailDAO.findBypcmPcmId(PCDetail.getErpmPurchasechallanMaster().getPcmPcmId());
         PCDetail=null;
         }
       else
          {
          ErpmPurchasechallanDetail pcdetail1 =PCDetailDAO.findByPCDetailsID(PCDetail.getPcdPcdId());
          pcdetail1=PCDetail;
          ErpmPurchasechallanMaster pcmaster1=PChallanMastDao.findByPcmId(defaultPCM);
          pcdetail1.setErpmPurchasechallanMaster(pcmaster1);
          PCDetailDAO.update(pcdetail1);
          //It will give the lovs of Item List all below three lines
          PChallanMast = PChallanMastDao.findByPcmId(defaultPCM);
          DefaultInsitute=PChallanMast.getInstitutionmaster().getImId();
          itemlist=itemlistDAO.findByImId(DefaultInsitute);
          PCDetailslist=PCDetailDAO.findBypcmPcmId(PCDetail.getErpmPurchasechallanMaster().getPcmPcmId());
          }
        
          return SUCCESS;
          }
          catch (Exception e)
          {
          message = "Exception in save method-> MANAGE PCDetailsAxn"  + e.getMessage() + " Reported Cause is: " + e.getCause();
          return ERROR;
        }
  }
    /*
public String BrowsePurchaseChallan() throws Exception {
    try {
        PChallanMastList = PChallanMastDao.findAll();
        return SUCCESS;
        } catch (Exception e) {
             message = "Exception in Browse method -> ManagePurchaseChallanAxn" + e.getMessage();
             return ERROR;
            }
        }

public String EditPurchaseChallan() throws Exception {
        try {
            PChallanMast = PChallanMastDao.findBypcmPcmId(PCMPCMID);
//            imList=imDao.findAll();
            imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
            simList=simDao.findSubInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Short.valueOf(getSession().getAttribute("imId").toString()));
            dmList=dmDao.findDepartmentForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Integer.valueOf(getSession().getAttribute("simId").toString()));
            erpmuserlist=erpmusersDao.findAll();
//            termsTypeList =termsTypeDao.findByErpmGmType(Short.parseShort("12"));
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Edit method -> ManagePurchaseChallanAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }
*/

public String DeletePurchaseChallan() {
        PCDetail=PCDetailDAO.findByPCDetailsID(getPCDetailsId());
         itemlist=itemlistDAO.findByImId(DefaultInsitute);
         defaultPCM=PCDetail.getErpmPurchasechallanMaster().getPcmPcmId();//.getErpmPoMaster().getPomPoMasterId();
        PChallanMast = PChallanMastDao.findBypcmPcmId(defaultPCM);
        PChallanMastDao.delete(PChallanMast);
        message = "Record successfully deleted";
        PCDetail.setPcdPcdId(pcdPcdId);
         PChallanMast = PChallanMastDao.findByPcmId(defaultPCM);
          DefaultInsitute=PChallanMast.getInstitutionmaster().getImId();
          itemlist=itemlistDAO.findByImId(DefaultInsitute);
          PCDetailslist=PCDetailDAO.findBypcmPcmId(PCDetail.getErpmPurchasechallanMaster().getPcmPcmId());
          PCDetail=null;
        return SUCCESS;
        }

public String ClearPurchaseChallan() throws Exception {
    try {
            PChallanMast = null;
            imList=imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
//            imList=imDao.findAll();
            imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
            simList=simDao.findSubInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Short.valueOf(getSession().getAttribute("imId").toString()));
            dmList=dmDao.findDepartmentForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Integer.valueOf(getSession().getAttribute("simId").toString()));
            erpmuserlist=erpmusersDao.findAll();
//            termsTypeList =termsTypeDao.findByErpmGmType(Short.parseShort("12"));
            return SUCCESS;
            } catch (Exception e) {
                message = "Exception in Clear method -> ManagePurchaseChallanAxn " + e.getMessage() + e.getCause();
                return ERROR;
       }

    }

}