/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Sajid Aziz
 */
package PrePurchase;

import pojo.hibernate.Institutionmaster;
import pojo.hibernate.InstitutionmasterDAO;

import pojo.hibernate.Departmentmaster;
import pojo.hibernate.DepartmentmasterDAO;

import pojo.hibernate.Subinstitutionmaster;
import pojo.hibernate.SubinstitutionmasterDAO;

import pojo.hibernate.Suppliermaster;
import pojo.hibernate.SuppliermasterDAO;

import pojo.hibernate.Erpmusers;
import pojo.hibernate.ErpmusersDAO;

import pojo.hibernate.ErpmPoMaster;
import pojo.hibernate.ErpmPoMasterDAO;

import pojo.hibernate.ErpmPoDetails;
import pojo.hibernate.ErpmPoDetailsDAO;

import pojo.hibernate.ErpmGenMaster;
import pojo.hibernate.ErpmGenMasterDao;

import pojo.hibernate.ErpmItemMaster;
import pojo.hibernate.ErpmItemMasterDAO;

import pojo.hibernate.ErpmGeneralTerms;
import pojo.hibernate.ErpmGeneralTermsDAO;
import pojo.hibernate.ErpmPoTerms;
import pojo.hibernate.ErpmPoTermsDAO;
import utils.DevelopmentSupport;
import java.util.ArrayList;
import java.util.List;
import javax.jms.Session;
import java.util.*;
import java.lang.*;

public class ManagePOMaster extends DevelopmentSupport {

  private ErpmPoMaster  pomaster;
  private ErpmPoDetails  podetail;
  private ErpmGeneralTerms Gterms;
  private ErpmPoTerms epoterms;
  private ErpmGenMaster egm;
  private String message;
  private Integer defaultPOM;
  private Integer PodetailsId;
  private Integer PodPodetailsId;
  private Short DefaultInsitute;
  private Integer PoMasterId;
  private Integer pomPoMasterId;
  private Integer POD_POMaster_ID;
  private Integer PotpoId;
  private Integer potPotId;
  Erpmusers ermu;
  Institutionmaster im;
  private String username;
  private Short DefaultInsitute1;
  private Integer DefaultSubInsitute;
  private Integer DefaultDepartment;
  private Integer defaultCurrency;
  private Integer DefaultPO;

  private ErpmPoMasterDAO               pomasterDAO     =     new ErpmPoMasterDAO();
  private ErpmPoDetailsDAO              podetailDAO     =     new ErpmPoDetailsDAO();
  private List<Institutionmaster>       imList          =     new ArrayList<Institutionmaster>();
  private InstitutionmasterDAO          imDao           =     new InstitutionmasterDAO();
  private List<Departmentmaster>        dmList          =     new ArrayList<Departmentmaster>();
  private DepartmentmasterDAO           dmDao           =     new DepartmentmasterDAO();
  private List<Subinstitutionmaster>    simList         =     new ArrayList<Subinstitutionmaster>();
  private SubinstitutionmasterDAO       simDao          =     new SubinstitutionmasterDAO ();
  private List<Suppliermaster>          suppList        =     new ArrayList<Suppliermaster>();
  private SuppliermasterDAO             suppDao         =     new SuppliermasterDAO();
  private List<ErpmGenMaster>           currencyList    =     new ArrayList<ErpmGenMaster>();
  private ErpmGenMasterDao              GMDao     =           new ErpmGenMasterDao();
  private List<ErpmGenMaster>           paymodelist     =     new ArrayList<ErpmGenMaster>();
  private List<Erpmusers>               erpmuserlist    =     new ArrayList<Erpmusers>();
  private ErpmusersDAO                  erpmusersDao    =     new ErpmusersDAO();
  private List<ErpmItemMaster>          itemlist        =     new ArrayList<ErpmItemMaster>();
  private ErpmItemMasterDAO             itemlistDAO     =     new ErpmItemMasterDAO();
  private List< ErpmPoDetails>          podetailslist   =     new ArrayList< ErpmPoDetails>();
  private List<ErpmPoMaster>            POMasterList    =     new ArrayList<ErpmPoMaster>();
  private List<ErpmGenMaster>           potermslist     =     new ArrayList<ErpmGenMaster>();
  private ErpmPoTermsDAO                epotermsDAO     =     new ErpmPoTermsDAO();
  private List<ErpmPoTerms>             epotermslist    =     new ArrayList<ErpmPoTerms>();
  private List<ErpmGeneralTerms>        Gtermslist    =     new ArrayList<ErpmGeneralTerms>();
   private List<ErpmGeneralTerms>       GTypestermslist    =     new ArrayList<ErpmGeneralTerms>();
  private ErpmGeneralTermsDAO           GtermsDAO    =     new ErpmGeneralTermsDAO();
  private List<ErpmPoTerms>             erpotermlist    =     new ArrayList<ErpmPoTerms>();
  private ErpmPoTermsDAO                erpotermsDAO    =     new ErpmPoTermsDAO();


  public void setusername(String username) {
                   this.username= username;
    }
  public String getusername() {
                   return username;
    }
  public void setPOD_POMaster_ID(Integer POD_POMaster_ID) {
                   this.POD_POMaster_ID= POD_POMaster_ID;
    }
  public Integer getPOD_POMaster_ID() {
                   return POD_POMaster_ID;
    }
  public void setPodPodetailsId(Integer PodPodetailsId) {
                  this.PodPodetailsId = PodPodetailsId;
    }
  public Integer getPodPodetailsId() {
                    return PodPodetailsId;
    }
  public void setPodetailsId(Integer PodetailsId) {
                  this.PodetailsId = PodetailsId;
    }
  public Integer getPodetailsId() {
                    return PodetailsId;
    }
  public void setpomPoMasterId(Integer pomPoMasterId) {
                  this.pomPoMasterId = pomPoMasterId;
    }
  public Integer getpomPoMasterId() {
                    return pomPoMasterId;
    }
  public void setDefaultPO(Integer DefaultPO) {
                  this.DefaultPO = DefaultPO;
    }
  public Integer getDefaultPO() {
                    return DefaultPO;
    }
  public void setPoMasterId(Integer PoMasterId) {
                  this.PoMasterId = PoMasterId;
    }
  public Integer getPoMasterId() {
                    return PoMasterId;
    }

  public void setPotpoId(Integer PotpoId) {
                  this.PotpoId = PotpoId;
    }
  public Integer getPotpoId() {
                    return PotpoId;
    }

  public void setpotPotId(Integer potPotId) {
                  this.potPotId = potPotId;
    }
  public Integer getpotPotId() {
                    return potPotId;
    }
  public void setDefaultInsitute(short DefaultInsitute) {
           this.DefaultInsitute = DefaultInsitute;
    }
  public short getDefaultInsitute() {
           return this. DefaultInsitute;
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
  public void setdefaultCurrency(Integer defaultCurrency) {
           this.defaultCurrency = defaultCurrency;
    }
  public Integer getdefaultCurrency() {
           return this. defaultCurrency;
    }
  public void setpodetailslist(List< ErpmPoDetails> podetailslist) {
                      this.podetailslist= podetailslist;
                                }
  public List< ErpmPoDetails> getpodetailslist() {
                         return this.podetailslist;
                                }
  public void seterpotermlist(List<ErpmPoTerms> erpotermlist) {
                      this.erpotermlist= erpotermlist;
                                }
  public List<ErpmPoTerms> geterpotermlist() {
                         return this.erpotermlist;
                                }

  public void setepotermslist(List< ErpmPoTerms> epotermslist) {
                      this.epotermslist= epotermslist;
                                }
  public List<ErpmPoTerms> getepotermslist() {
                         return this.epotermslist;
                                }
 
  public void setGtermslist(List<ErpmGeneralTerms> Gtermslist) {
                      this.Gtermslist= Gtermslist;
                                }
  public List<ErpmGeneralTerms> getGtermslist() {
                         return this.Gtermslist;
                                }
  public void setGTypestermslist(List<ErpmGeneralTerms> GTypestermslist) {
                      this.GTypestermslist= GTypestermslist;
                                }
  public List<ErpmGeneralTerms> getGTypestermslist() {
                         return this.GTypestermslist;
                                }
  public void setdefaultPOM(Integer defaultPOM) {
           this.defaultPOM = defaultPOM;
    }
  public Integer getdefaultPOM() {
           return this. defaultPOM;
    }
  public void setitemlist(List<ErpmItemMaster> itemlist) {
                      this.itemlist= itemlist;
                                }
  public List<ErpmItemMaster> getitemlist() {
                         return this.itemlist;
                                }
  public void setsimList(List<Subinstitutionmaster> simList) {
                       this.simList = simList;
    }
  public List<Subinstitutionmaster> getsimList() {
                   return this.simList;
    }
  public void setpaymodelist(List<ErpmGenMaster> paymodelist) {
                  this.paymodelist = paymodelist;
    }
  public List<ErpmGenMaster> getpaymodelist() {
           return this.paymodelist;
                  }
  public void setcurrencyList(List<ErpmGenMaster> currencyList) {
                  this.currencyList = currencyList;
    }
  public List<ErpmGenMaster> getcurrencyList() {
           return this.currencyList;
                  }
  public void setpotermslist(List<ErpmGenMaster> potermslist) {
                  this.potermslist = potermslist;
    }
  public List<ErpmGenMaster> getpotermslist() {
           return this.potermslist;
                  }
  public void seterpmuserlist(List<Erpmusers> erpmuserlist) {
                 this.erpmuserlist= erpmuserlist;
    }
  public List<Erpmusers> geterpmuserlist() {
             return this.erpmuserlist;
    }
  public void setimList(List<Institutionmaster> imList) {
                this.imList = imList;
    }
  public List<Institutionmaster> getimList() {
                    return this.imList;
    }
  public void setdmList(List<Departmentmaster> dmList) {
                 this.dmList = dmList;
    }
  public List<Departmentmaster> getdmList() {
             return this.dmList;
    }
  public void setpomaster(ErpmPoMaster pomaster) {
                 this.pomaster = pomaster;
    }
  public ErpmPoMaster getpomaster() {
                          return this.pomaster;
    }
  public void setGterms(ErpmGeneralTerms Gterms) {
                 this.Gterms = Gterms;
    }
  public ErpmGeneralTerms getGterms() {
                          return this.Gterms;
    }

  public void setegm(ErpmGenMaster egm) {
                 this.egm = egm;
    }
  public ErpmGenMaster getegm() {
                          return this.egm;
    }
  public void setepoterms(ErpmPoTerms epoterms) {
                 this.epoterms = epoterms;
    }
  public ErpmPoTerms getepoterms() {
                          return this.epoterms;
    }
  public void setpodetail(ErpmPoDetails podetail) {
                 this.podetail = podetail;
    }
  public ErpmPoDetails getpodetail() {
                          return this.podetail;
    }
  public void setsuppList(List<Suppliermaster> suppList) {
                  this.suppList = suppList;
    }
  public List<Suppliermaster> getsuppList() {
           return this.suppList;
                  }

   public void setPOMasterList(List<ErpmPoMaster> POMasterList) {
                  this.POMasterList = POMasterList;
    }
  public List<ErpmPoMaster> getPOMasterList() {
           return this.POMasterList;
                  }
  public void setMessage(String message) {
                     this.message = message;
    }
  public String getMessage() {
                  return this.message;
    }
  @Override
 public String execute() throws Exception {
    try {
       imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
       //simList=simDao.findSubInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Short.parseShort("1"));
        simList=simDao.findSubInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Short.valueOf(getSession().getAttribute("imId").toString()));
        dmList=dmDao.findDepartmentForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Integer.valueOf(getSession().getAttribute("simId").toString()));
        suppList=suppDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
        currencyList=GMDao.findByErpmGmType(Short.parseShort("6"));
        paymodelist=GMDao.findByErpmGmType(Short.parseShort("10"));
        erpmuserlist=erpmusersDao.findAll();
        itemlist=itemlistDAO.findByImId(DefaultInsitute);
        //these four line gives Default Insitute,Subinsitute,Department(For which Users have current Profile) and Currency(Rupees)
        DefaultInsitute1=Short.valueOf(getSession().getAttribute("imId").toString()); //imDao.findDefaultInstByID(Short.valueOf(getSession().getAttribute("imId").toString()));
        DefaultSubInsitute=Integer.valueOf(getSession().getAttribute("simId").toString());//simDao.findDefaultSubInsitute(Integer.valueOf(getSession().getAttribute("simId").toString()));
        DefaultDepartment=Integer.valueOf(getSession().getAttribute("dmId").toString());//dmDao.findDefaultDepartment(Integer.valueOf(getSession().getAttribute("dmId").toString()));
        defaultCurrency=GMDao.findDefaultCurrency("Rupees");
       // potermslist=GMDao.findByErpmGmType(Short.parseShort("12"));
        return SUCCESS;
        }
        catch (Exception e) {
        message = "Exception in -> POMASTERAxn"  + e.getMessage() + " Reported Cause is: " + e.getCause();
        return ERROR;
        }
        }

 public String SavePOMaster() throws Exception {
    try {
           username   =getSession().getAttribute("userfullname").toString();
       /*---------------for getting Login user name.---------------*/
        ermu=erpmusersDao.findByUserFullNames(username);
        pomaster.setErpmusersByPomUserId(ermu);
       // imname=getSession().getAttribute("imname").toString();
        //im=imDao.findInstByIMFullName(imname);
        //pomaster.setInstitutionmaster(im);
        if(pomaster.getPomPoMasterId()==null) {
             if(pomaster.getInstitutionmaster()==null)
             message="Please select Insitute";
             else if(pomaster.getPomPoMasterId()==null)

       //Save the data into po master table
        pomasterDAO.save(pomaster);
       // pomaster=null;
       //for getting the default POM on detail id
       defaultPOM=pomaster.getPomPoMasterId();
       getSession().setAttribute("POMID", defaultPOM);
       //for set the all field of PO Master Screen in add details screen
       pomaster=pomasterDAO.findBypomPoMasterId(defaultPOM);
       //for getting all items for their insitute
       DefaultInsitute=pomaster.getInstitutionmaster().getImId();
       itemlist=itemlistDAO.findByImId(DefaultInsitute);
      // pomaster=null;
        }
        else
        {
            //Else Part when we click on edit button
            ErpmPoMaster pomaster1=pomasterDAO.findByPoMasterId(pomaster.getPomPoMasterId());
            pomaster1=pomaster;
            pomasterDAO.update(pomaster1);
            defaultPOM=pomaster.getPomPoMasterId();
            //itemlist=itemlistDAO.findAll();
            pomaster=pomasterDAO.findBypomPoMasterId(defaultPOM);

        }
       return SUCCESS;
       }
       catch (Exception e)
       {
           InitializeLOVs();
       message = "Exception in PO MASTER save method-> pomasterAxn"  + e.getMessage() + " Reported Cause is: " + e.getCause();
       return ERROR;
       }
  }

 public String  SavePODetails() throws Exception {
    try {
       if (podetail.getPodPodetailsId()== null)
         {
         if(podetail.getPodDiscount()==null)
         message="Please give Discount";
         else if(podetail.getPodPodetailsId()== null)
         pomaster = pomasterDAO.findByPoMasterId(defaultPOM);
         podetail.setErpmPoMaster(pomaster);
         podetailDAO.save(podetail);
         pomaster = pomasterDAO.findByPoMasterId(defaultPOM);
         DefaultInsitute=pomaster.getInstitutionmaster().getImId();
         itemlist=itemlistDAO.findByImId(DefaultInsitute);
         podetailslist=podetailDAO.findBypomPoMasterId(podetail.getErpmPoMaster().getPomPoMasterId());
         podetail=null;
         }
         else
          {
          ErpmPoDetails podetail1 =podetailDAO.findByPODetailsID(podetail.getPodPodetailsId());
          podetail1=podetail;
          ErpmPoMaster pomaster1=pomasterDAO.findByPoMasterId(defaultPOM);
          podetail1.setErpmPoMaster(pomaster1);
          podetailDAO.update(podetail1);
          //It will give the lovs of Item List all below three lines
          pomaster = pomasterDAO.findByPoMasterId(defaultPOM);
          DefaultInsitute=pomaster.getInstitutionmaster().getImId();
          itemlist=itemlistDAO.findByImId(DefaultInsitute);
           podetailslist=podetailDAO.findBypomPoMasterId(podetail.getErpmPoMaster().getPomPoMasterId());
          }
          return SUCCESS;
          }
          catch (Exception e)
          {
        if (e.getCause().toString().contains("Duplicate entry"))
        message = "The Item Would not be Added Because it is already Exits in your ";
        else
        message = "Exception in save method-> MANAGE PODetailsAxn"  + e.getMessage() + " Reported Cause is: " + e.getCause();
        return ERROR;
        }
  }
 public String DeletePoDetails () throws Exception {
    try {

      podetail=podetailDAO.findByPODetailsID(getPodetailsId());
      itemlist=itemlistDAO.findByImId(DefaultInsitute);
      defaultPOM=podetail.getErpmPoMaster().getPomPoMasterId();
      //for set the default POM on  Add Detail after record would be deleted
      pomaster=pomasterDAO.findBypomPoMasterId(defaultPOM);
      podetailDAO.delete(podetail);
      message = "Record successfully deleted";
      podetail.setPodPodetailsId(PodPodetailsId);
      //It will give the lovs of Item List all below three lines
      pomaster = pomasterDAO.findByPoMasterId(defaultPOM);
      DefaultInsitute=pomaster.getInstitutionmaster().getImId();
      itemlist=itemlistDAO.findByImId(DefaultInsitute);
      podetailslist=podetailDAO.findBypomPoMasterId(podetail.getErpmPoMaster().getPomPoMasterId());

     podetail=null;
     return SUCCESS;
        }
      catch (Exception e) {
      message = "Exception in Delete method -> Delete PO Detail " + e.getMessage() + " Reported Cause is: " + e.getCause();
      return ERROR;
      }
    }
 public String EditPODetails() throws Exception {
    try {
        //Retrieve the record to be updated into podetail bu PoDetail Id here Id will Retrive.
      podetail=podetailDAO.findByPODetailsID(getPodetailsId());
       //for set the Default POM on Add PO Detail Screen
      defaultPOM=podetail.getErpmPoMaster().getPomPoMasterId();
      //for getting Insitute on Add Po Detail Screen its set the field of PO master Screen
      pomaster=pomasterDAO.findBypomPoMasterId(defaultPOM);
      //for getting the Item List By Insitute
      itemlist=itemlistDAO.findByImId(pomaster.getInstitutionmaster().getImId());
      return SUCCESS;
      }catch (Exception e) {
      message = "Exception in Edit method -> EditPo Rate TaxesAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
      return ERROR;
      }
      }
      //This function  Browse the given PO Master data
 public String BrowseIndentPOMaster () throws Exception {
   try {
         POMasterList=pomasterDAO.findAll();//findPOForUserDepartments(Integer.parseInt(getSession().getAttribute("userid").toString()));
         
        return SUCCESS;
        } catch (Exception e) {
        message = "Exception in Browse method -> MangeBrowsePOAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
        return ERROR;
        } }
  //This function  Browse the given Indent Detail Item data
 public String BrowsePOMasterDetail () throws Exception {
   try {
        pomaster=pomasterDAO.findByPoMasterId(getPoMasterId());
        podetailslist=podetailDAO.findByPOD_POMaster_ID(getPoMasterId());
        defaultPOM=pomaster.getPomPoMasterId();
         //It will give the lovs of Item List all below three lines
      pomaster = pomasterDAO.findByPoMasterId(defaultPOM);
      DefaultInsitute=pomaster.getInstitutionmaster().getImId();
      itemlist=itemlistDAO.findByImId(DefaultInsitute);
       return SUCCESS;
       }
       catch (Exception e) {
       message = "Exception in BrowsePODetail -> BrowsePODetailAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
       return ERROR;
       } }
  //it will Delete Po Master Screen Data.
 public String DeletePOMaster () throws Exception {
    try {
            //TO DELETE THE INDENT DETAIL BY ID
        pomaster=pomasterDAO.findByPoMasterId(getPoMasterId());
           //DELETE ABOVE ID IN ERPMINDTDET
        pomasterDAO.delete(pomaster);
        POMasterList=pomasterDAO.findPOForUserDepartments(Integer.parseInt(getSession().getAttribute("userid").toString()));
        message = "Record successfully deleted";
        pomaster= null;
        return SUCCESS;
        }
       catch (Exception e) {
        if (e.getCause().toString().contains("fk_INDENTDET_IND_master"))
        message = "The PO Master cannot be  deleted because of this PO is associated with other details,would you like to delete or not";
        else
        message = "Exception in Delete method -> ManagePOAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
        return ERROR;
        }
        }
 public String EditPOMaster () throws Exception {
      try {
           //Retrieve the record to be updated into pomaster object
       pomaster=pomasterDAO.findByPoMasterId(getPoMasterId());
       execute();
       return SUCCESS;
       }catch (Exception e) {
       message = "Exception in Edit method -> ManageIndentMasterAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
       return ERROR;
       }
       }
 public void InitializeLOVs() {
        imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
       //simList=simDao.findSubInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Short.parseShort("1"));
        simList=simDao.findSubInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Short.valueOf(getSession().getAttribute("imId").toString()));
        dmList=dmDao.findDepartmentForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Integer.valueOf(getSession().getAttribute("simId").toString()));
        suppList=suppDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
        currencyList=GMDao.findByErpmGmType(Short.parseShort("6"));
        paymodelist=GMDao.findByErpmGmType(Short.parseShort("10"));
        erpmuserlist=erpmusersDao.findAll();
        itemlist=itemlistDAO.findByImId(DefaultInsitute);
        //these four line gives Default Insitute,Subinsitute,Department and Currency(Rupees)
        DefaultInsitute1=Short.valueOf(getSession().getAttribute("imId").toString());
        //imDao.findDefaultInstByID(Short.valueOf(getSession().getAttribute("imId").toString()));
        DefaultSubInsitute=Integer.valueOf(getSession().getAttribute("simId").toString());//simDao.findDefaultSubInsitute(Integer.valueOf(getSession().getAttribute("simId").toString()));
        DefaultDepartment=Integer.valueOf(getSession().getAttribute("dmId").toString());//dmDao.findDefaultDepartment(Integer.valueOf(getSession().getAttribute("dmId").toString()));
        defaultCurrency=GMDao.findDefaultCurrency("Rupees");//Need to be setup as same as that of Country Currency
      }
  //this will Perform Validation of Manage Po Master Data
 public void validate() {
        try {
           if(pomaster.getPomPoMasterId() == null) {
            if (pomaster.getInstitutionmaster().getImId() == null) {
                addFieldError("pomaster.institutionmaster.imId", "Please select institution from the list");
            }
            if(pomaster.getSubinstitutionmaster().getSimId()==null) {
            addFieldError("pomaster.subinstitutionmaster.simId","Please select Subinsitute from the list");
            }
            if(pomaster.getDepartmentmaster().getDmId()==null){
                addFieldError("pomaster.departmentmaster.dmId" ,"Please select Department from the list");
             }
            if(pomaster.getPomPoNo() ==0){
                addFieldError("pomaster.pomPoNo","Please enter PO No");
            }
            if(pomaster.getPomPoDate()==null){
                addFieldError("pomaster.pomPoDate","Please enter Date");
            }
            if(pomaster.getSuppliermaster().getSmId()==null) {
                addFieldError("pomaster.suppliermaster.smId","Please select Supplier From The List");
            }
            if(pomaster.getErpmGenMasterByPomCurrencyId()==null){
                addFieldError("pomaster.erpmGenMasterByPomCurrencyId","Please select Currency From The List");
            }
            if(pomaster.getErpmGenMasterByPomPaymentModeId().getErpmgmEgmId()==null) {
                addFieldError("pomaster.erpmGenMasterByPomPaymentModeId.erpmgmEgmId","Please Select Payment Mode from The List");
               }
            if(pomaster.getErpmusersByPomApprovedById().getErpmuId()==null) {
                addFieldError("pomaster.erpmusersByPomApprovedById.erpmuId","Please select Approved from the list");
            }
            if(pomaster.getPomDeliveryDate()==null){
                addFieldError("pomaster.pomDeliveryDate","Please enter Po Delivery Date");
            }
           
            InitializeLOVs();
            }
            else {
            if(podetail.getErpmItemMaster().getErpmimId()==null){
                addFieldError("podetail.erpmItemMaster.erpmimId","Please Double Click here to select an  Item");
              }
           if(podetail.getPodQuantity()==null ) {//|| Integer.valueOf(podetail.getPodQuantity().toString())<0 ){
                addFieldError("podetail.podQuantity","Please enter Quantity");
            }
            else if(Double.valueOf(podetail.getPodQuantity().toString())<0 ){
                addFieldError("podetail.podQuantity","Please enter valid Quantity");
           }
            if(podetail.getPodRate()==null) {
                addFieldError("podetail.podRate","Please enter Rate");
            }
            else if(Double.valueOf(podetail.getPodRate().toString())<0 ){
                addFieldError("podetail.podRate","Please enter valid Rate");
           }
            if(podetail.getPodDiscount()==null) {
              addFieldError("podetail.podDiscount","Please enter Discount");
            }
            else if(Double.valueOf(podetail.getPodDiscount().toString()) < 0)
            {
            addFieldError("podetail.podDiscount","Please enter Valid Discount");
            }
            InitializeLOVs();  
            } //end of else*/
            //if(epoterms.getPotPotId()==null) {
            if(epoterms.getErpmGenMaster().getErpmgmEgmId().toString().isEmpty()){
                addFieldError("epoterms.erpmGenMaster.erpmgmEgmId","Please  select PO Types");
             //}
     }
     }
         catch (NullPointerException npe) {
          // message="hi" +npe.getCause();
        }
    }
 public String POTerms() throws Exception {
        try{
             //for getting the POMID into Session which come from erpmuseraction class
           defaultPOM=Integer.parseInt(getSession().getAttribute("POMID").toString());
           pomaster = pomasterDAO.findByPoMasterId(defaultPOM);
           //Removing the POMID from Session
          // getSession().removeAttribute("POMID");
           //potermslist=GMDao.findByErpmGmType(Short.parseShort("12"));
          // Gtermslist=GtermsDAO.findPOtermsforInsitute(Short.valueOf(getSession().getAttribute("imId").toString()));
         potermslist=GMDao.findByErpmGmType(Short.parseShort("12"));
         //potermslist=GMDao.findByErpmGmTypebyInsitute(Short.parseShort("12"),Short.valueOf(getSession().getAttribute("imId").toString()));                      
        return SUCCESS;
        }
        catch (Exception e) {
            message = "Exception in ManagePOTERMS method ->  " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }
 public String SavePOTerms() throws Exception {
        try{
       if (epoterms.getPotPotId()== null)
         {
         if(epoterms.getPotTermsDescription()==null)
         message="Please give Terms Description";
         else if(epoterms.getPotPotId()== null)
             if(epoterms.getPotPotId()==null)

             defaultPOM=Integer.parseInt(getSession().getAttribute("POMID").toString());

             pomaster = pomasterDAO.findByPoMasterId(defaultPOM);
             epoterms.setErpmPoMaster(pomaster);

             egm=GMDao.findByErpmGmId(epoterms.getErpmGenMaster().getErpmgmEgmId());
             epoterms.setErpmGenMaster(egm);

            epotermsDAO.save(epoterms);
            epoterms=epotermsDAO.findByPotpoId(epoterms.getPotPotId());
            erpotermlist=erpotermsDAO.findBytest(epoterms.getErpmPoMaster().getPomPoMasterId());
            potermslist=GMDao.findByErpmGmType(Short.parseShort("12"));
            epoterms=null;
            }
           else
          {
          ErpmPoTerms poterms1 =epotermsDAO.findBypotPotIds(epoterms.getPotPotId());
          poterms1=epoterms;
          ErpmPoMaster pomaster1=pomasterDAO.findByPoMasterId(defaultPOM);
          poterms1.setErpmPoMaster(pomaster1);
          epotermsDAO.update(epoterms);
          potermslist=GMDao.findByErpmGmType(Short.parseShort("12"));
          erpotermlist = erpotermsDAO.findBytest(epoterms.getErpmPoMaster().getPomPoMasterId());
          epoterms=null;
          }
         return SUCCESS;
         }
        catch (Exception e) {
        if (e.getCause().toString().contains("Duplicate entry"))
        message = "The Po terms type ' " + epoterms.getErpmGenMaster().getErpmgmEgmDesc() + "' already exists for Your Purchase Order.";
       else
        message = "Exception in Save method -> ManagePo TermsAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
        return ERROR;
        }
       }
           
        
 public String GetDefaultGeneralTerms() throws Exception {
        try{
            //For getting The Terms List by its institute type
            Gtermslist=GtermsDAO.findPOtermsforInsitute(Short.valueOf(getSession().getAttribute("imId").toString()));            
            
                     for (int i = 0; i < Gtermslist.size() ; i++) {
                     
                        epoterms.setErpmPoMaster(pomaster);      
                        epoterms.setErpmGenMaster(Gtermslist.get(i).getErpmGenMaster());
                        epoterms.setPotTermsDescription(Gtermslist.get(i).getGtTermsDescription());
                        epotermsDAO.save(epoterms);
                        message="Record Successfully Saved";
                         epoterms=epotermsDAO.findByPotpoId(epoterms.getPotPotId());
                        //epoterms=epotermsDAO.findByPotpoId(getPotpoId());
                       // potermslist=GMDao.findByErpmGmType(Short.parseShort("12"));
                        erpotermlist=erpotermsDAO.findBytest(epoterms.getErpmPoMaster().getPomPoMasterId());
                        //epoterms=null;
                     }
                   return SUCCESS;
        }
            catch (Exception e) {
         if (e.getCause().toString().contains("Duplicate entry"))
                    message = "The Po terms type ' " + epoterms.getErpmGenMaster().getErpmgmEgmDesc() + "' already exists for Your Purchase Order";
            else
                message = "Exception in Save method -> ManagePo TermsAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
       }
    }

        //THIS WILL DELETE THE DATA
 public String DeletePoTerms () throws Exception {
    try {
        epoterms=epotermsDAO.findBypotPotIds(getPotpoId());
       defaultPOM=Integer.parseInt(getSession().getAttribute("POMID").toString());
       pomaster = pomasterDAO.findByPoMasterId(defaultPOM);
       epotermsDAO.delete(epoterms);
       message = "Record successfully deleted";
      //lOV'S OF TERMS TYPES AND THE BROWSE LIST AFTER A ROW WOULD BE DELETED
       potermslist=GMDao.findByErpmGmType(Short.parseShort("12"));
       erpotermlist=erpotermsDAO.findBytest(epoterms.getErpmPoMaster().getPomPoMasterId());   
       epoterms=null;
     return SUCCESS;
        }
      catch (Exception e) {
      message = "Exception in Delete method -> Delete PO Terms " + e.getMessage() + " Reported Cause is: " + e.getCause();
      return ERROR;
      }
        
    }
 public String EditPoTerms() throws Exception {
    try {
         epoterms=epotermsDAO.findBypotPotIds(getPotpoId());
         defaultPOM=Integer.parseInt(getSession().getAttribute("POMID").toString());
         pomaster = pomasterDAO.findByPoMasterId(defaultPOM);
         potermslist=GMDao.findByErpmGmType(Short.parseShort("12"));
         return SUCCESS;
      }catch (Exception e) {
      message = "Exception in Edit method -> EditPo Terms TaxesAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
      return ERROR;
      }
      }
}
