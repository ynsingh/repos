/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PrePurchase;
/**
 *
 * @author Sajid Aziz
 */
import pojo.hibernate.ErpmItemRate;
import pojo.hibernate.ErpmItemRateDAO;

import pojo.hibernate.ErpmItemRateDetails;
import pojo.hibernate.ErpmItemRateDetailsDAO;

import pojo.hibernate.Institutionmaster;
import pojo.hibernate.InstitutionmasterDAO;

import pojo.hibernate.ErpmGenMaster;
import pojo.hibernate.ErpmGenMasterDao;

import pojo.hibernate.ErpmItemMaster;
import pojo.hibernate.ErpmItemMasterDAO;

import pojo.hibernate.Suppliermaster;
import pojo.hibernate.SuppliermasterDAO;

import pojo.hibernate.ErpmItemRateTaxes;
import pojo.hibernate.ErpmItemRateTaxesDAO;


import utils.DevelopmentSupport;
import java.util.*;

public class ManageItemRates extends DevelopmentSupport {

  private ErpmItemRate itemrate;
  private ErpmItemRateDetails itemratedet;
  private ErpmItemRateTaxes itemratetax;
  Integer defaultRate;
  Integer defaultRat;
  Integer irdItemRateDetailsId;
  private  Integer irItemRateId;
  Integer IrtItemRateTaxesId;
  Integer irtItemRateTaxesId;
  private String message;
  private Integer defaultCurrency;
  private Short DefaultInsitute1;
  private Short DefaultInsitute;
  private Integer ItemrateID;



  private List<ErpmGenMaster>           wsfList             =     new ArrayList<ErpmGenMaster>();
  private ErpmGenMasterDao              GMDao              =     new ErpmGenMasterDao();
  private List<Institutionmaster>       imList              =     new ArrayList<Institutionmaster>();
  private    InstitutionmasterDAO       imDao               =     new InstitutionmasterDAO();
  private ErpmItemMasterDAO             itemDao             =     new ErpmItemMasterDAO();
  private List<ErpmItemMaster>          itemList            =     new ArrayList<ErpmItemMaster>();
  private List<ErpmGenMaster>           currencyList        =     new ArrayList<ErpmGenMaster>();
  private List<Suppliermaster>          suppList            =     new ArrayList<Suppliermaster>();
  private SuppliermasterDAO             suppDao             =     new SuppliermasterDAO();
  private ErpmItemRateDAO               itemrateDAO         =     new ErpmItemRateDAO();
  private ErpmItemRateDetailsDAO        itemratedetDAO      =     new ErpmItemRateDetailsDAO();
  private ErpmItemRateTaxesDAO          itemratetaxDAO      =     new ErpmItemRateTaxesDAO();
  private List<ErpmItemRateTaxes>       itemratetaxlist     =     new ArrayList<ErpmItemRateTaxes>();
  private List<ErpmItemRate>            itemratebrowseList  =     new ArrayList<ErpmItemRate>();

 public void setItemrateID(Integer ItemrateID) {
           this.ItemrateID = ItemrateID;
    }
  public Integer getItemrateID() {
           return this. ItemrateID;
    }

  public void setIrtItemRateTaxesId(Integer IrtItemRateTaxesId) {
                  this.IrtItemRateTaxesId = IrtItemRateTaxesId;
    }
  public Integer getIrtItemRateTaxesId() {
                    return IrtItemRateTaxesId;
    }
  public void setirtItemRateTaxesId(Integer irtItemRateTaxesId) {
                  this.irtItemRateTaxesId = irtItemRateTaxesId;
    }
  public Integer getirtItemRateTaxesId() {
                    return irtItemRateTaxesId;
    }
  public void setitemratetaxlist(List<ErpmItemRateTaxes> itemratetaxlist) {
                      this.itemratetaxlist= itemratetaxlist;
                              }
  public List<ErpmItemRateTaxes> getitemratetaxlist() {
                         return this.itemratetaxlist;
                                }
  public void setdefaultRate(Integer defaultRate) {
           this.defaultRate = defaultRate;
    }
  public Integer getdefaultRate() {
           return this. defaultRate;
    }
  public void setdefaultRat(Integer defaultRat) {
           this.defaultRat = defaultRat;
    }
  public Integer getdefaultRat() {
           return this. defaultRat;
    }
  public void setitemrate(ErpmItemRate itemrate) {
                 this.itemrate = itemrate;
    }
  public ErpmItemRate getitemrate() {
                          return this.itemrate;
    }
  public void setitemratetax(ErpmItemRateTaxes itemratetax) {
                 this.itemratetax = itemratetax;
    }
  public ErpmItemRateTaxes getitemratetax() {
                          return this.itemratetax;
    }
  public void setitemratedet(ErpmItemRateDetails itemratedet) {
                 this.itemratedet = itemratedet;
    }
  public ErpmItemRateDetails getitemratedet() {
                          return this.itemratedet;
    }
  public void setimList(List<Institutionmaster> imList) {
                this.imList = imList;
    }
  public List<Institutionmaster> getimList() {
                    return this.imList;
    }
  public void setcurrencyList(List<ErpmGenMaster> currencyList) {
                  this.currencyList = currencyList;
    }
  public List<ErpmGenMaster> getcurrencyList() {
           return this.currencyList;
                  }
  public void setitemList(List<ErpmItemMaster> itemList) {
                 this.itemList= itemList;
                                }
  public List<ErpmItemMaster> getitemList() {
                    return this.itemList;
          }
  public void setsuppList(List<Suppliermaster> suppList) {
                  this.suppList = suppList;
    }
  public List<Suppliermaster> getsuppList() {
           return this.suppList;
                  }
  public void setwsfList(List<ErpmGenMaster> wsfList) {
                  this.wsfList = wsfList;
    }
  public List<ErpmGenMaster> getwsfList() {
           return this.wsfList;
                  }
  public void setirdItemRateDetailsId(Integer irdItemRateDetailsId) {
                   this.irdItemRateDetailsId = irdItemRateDetailsId;
  }
  public Integer getirdItemRateDetailsId() {
                   return irdItemRateDetailsId;
    }
  public void setirItemRateId(Integer irItemRateId) {
                   this.irItemRateId = irItemRateId;
  }
  public Integer getirItemRateId() {
                   return irItemRateId;
    }
  public void setdefaultCurrency(Integer defaultCurrency) {
           this.defaultCurrency = defaultCurrency;
    }
  public Integer getdefaultCurrency() {
           return this. defaultCurrency;
    }
  public void setDefaultInsitute1(Short DefaultInsitute1) {
           this.DefaultInsitute1 = DefaultInsitute1;
    }
  public Short getDefaultInsitute1() {
           return this. DefaultInsitute1;
    }
  public void setDefaultInsitute(short DefaultInsitute) {
           this.DefaultInsitute = DefaultInsitute;
    }
  public short getDefaultInsitute() {
           return this. DefaultInsitute;
    }
  public void setMessage(String message) {
                     this.message = message;
    }
  public String getMessage() {
                  return this.message;
    }
  public void setitemratebrowseList(List< ErpmItemRate> itemratebrowseList) {
                      this.itemratebrowseList= itemratebrowseList;
                                }
  public List<ErpmItemRate> getitemratebrowseList() {
                         return this.itemratebrowseList;
                                }
   @Override
  public String execute() throws Exception {
    try {
        imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
        currencyList=GMDao.findByErpmGmType(Short.parseShort("6"));
       // itemList=itemDao.findByImId(itemrate.getInstitutionmaster().getImId());
        //message="hi" + itemrate.getInstitutionmaster().getImId();
        itemList=itemDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
        suppList=suppDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
        DefaultInsitute1=Short.valueOf(getSession().getAttribute("imId").toString());
        defaultCurrency=GMDao.findDefaultCurrency("Rupees");
        wsfList=GMDao.findByErpmGmType(Short.parseShort("9"));
        return SUCCESS;
        } catch (Exception e) {
        message = "Exception in -> Manage ItemRateAxn"  + e.getMessage() + " Reported Cause is: " + e.getCause();
        return ERROR;
        }
        }


   public String SaveIndentRate() throws Exception {
    try {
         if(itemrate.getIrItemRateId()==null) {
             if(itemrate.getInstitutionmaster()==null)
             message="Please select Insitute";
             else if(itemrate.getIrItemRateId()==null)
     //For Saving The Data on Item Rate Table
    itemrateDAO.save(itemrate);
     //For Saving The Data on ItemRate_Details Table
    itemratedet.setErpmItemRate(itemrate);
    itemratedetDAO.save(itemratedet);
    //For the getting Default rate Details Id on Add Taxes
    defaultRate = itemratedet.getIrdItemRateDetailsId();
    itemratedet = itemratedetDAO.findByirdItemRateDetailsId(defaultRate);   
    //for getting the item Name And Supplier Name On Taxes Screen
    defaultRat = itemrate.getIrItemRateId();
    itemrate = itemrateDAO.findItemRateId(defaultRat);
        }
         return SUCCESS;
        }
        catch (Exception e)
        {
        message = "Exception in save method-> itemRaterAxn"  + e.getMessage() + " Reported Cause is: " + e.getCause();
        return ERROR;
        }
  }

   public String SaveIndentRateTaxes() throws Exception {
    try {      
         if (itemratetax.getIrtItemRateTaxesId()== null) 
          {
             if(itemratetax.getIrtTaxName()==null)
                 message="Please give tax name";
             else if(itemratetax.getIrtItemRateTaxesId()== null) 
           //  if(itemratetax.getIrtItemRateTaxesId()== null)
             itemratedet = itemratedetDAO.findIndentRateDetailId(defaultRate);
             
      itemratetax.setErpmItemRateDetails(itemratedet);
      itemratetaxDAO.save(itemratetax);
      itemratedet = itemratedetDAO.findIndentRateDetailId(defaultRate);
         
     //For Showing The List below the Add Text Screen
     itemratetaxlist=itemratetaxDAO.findByirdItemRateDetailsId(itemratetax.getErpmItemRateDetails().getIrdItemRateDetailsId());
     itemratetax=null;
         }
          else
         {
          ErpmItemRateTaxes itemratetax1 =itemratetaxDAO.findByirtItemRateTaxesId(itemratetax.getIrtItemRateTaxesId());
          itemratetax1=itemratetax;
          ErpmItemRateDetails itemratedet1=itemratedetDAO.findIndentRateDetailId(defaultRate);
          itemratetax1.setErpmItemRateDetails(itemratedet1);
          itemratetaxDAO.update(itemratetax1);
          itemratetaxlist=itemratetaxDAO.findByirdItemRateDetailsId(itemratetax.getErpmItemRateDetails().getIrdItemRateDetailsId());
           }

     // execute();
        return SUCCESS;
        }
        catch (Exception e)
        {
        message = "Exception in save method-> itemRaterTaxesAxn"  + e.getMessage() + " Reported Cause is: " + e.getCause();
        return ERROR;
        }
  }

   public String DeleteIndentRateTaxes () throws Exception {
    try {

     //TO DELETE THE Rate Taxex by Rate_Detail ID
      //erpmindtdet=erpmindetDao.findByindtDetailId(getindtdetailId());        
    itemratetax=itemratetaxDAO.findByirtItemRateTaxesId( getIrtItemRateTaxesId());
    defaultRate = itemratedet.getIrdItemRateDetailsId();
    itemratedet = itemratedetDAO.findByirdItemRateDetailsId(defaultRate);
    itemratetaxDAO.delete(itemratetax);
    message = "Record successfully deleted";
      defaultRat = itemrate.getIrItemRateId();
    itemrate = itemrateDAO.findItemRateId(defaultRat);
   // itemratetax.setErpmItemRateDetails(itemrate.getErpmItemMaster().getErpmimId().toString());
   itemratetax.setIrtItemRateTaxesId(getIrtItemRateTaxesId());
    itemratetaxlist=itemratetaxDAO.findByirdItemRateDetailsId(itemratetax.getErpmItemRateDetails().getIrdItemRateDetailsId());
    itemratetax= null;


      return SUCCESS;
      }
       catch (Exception e) {
        message = "Exception in Delete method -> Delete Rate Taxes" + e.getMessage() + " Reported Cause is: " + e.getCause();
        return ERROR;
        }
    }
   public String EditIndentRateTaxes() throws Exception {
    try {

        //Retrieve the record to be updated into itemratetax object
         itemratetax=itemratetaxDAO.findByirtItemRateTaxesId( getIrtItemRateTaxesId());

        defaultRate = itemratedet.getIrdItemRateDetailsId();
        itemratedet = itemratedetDAO.findByirdItemRateDetailsId(defaultRate);
       return SUCCESS;
        }catch (Exception e) {


        message = "Exception in Edit method -> EditItemsRate TaxesAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
        return ERROR;
        }
        }

public String BrowseitemRatesDetails() throws Exception {
   try {
         //POMasterList=pomasterDAO.findPOForUserDepartments(Integer.parseInt(getSession().getAttribute("userid").toString()));
           itemratebrowseList=itemrateDAO.findAll();
           message="hi" +itemratebrowseList;
        return SUCCESS;
        } catch (Exception e) {
        message = "Exception in Browse method -> MangeBrowseRateAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
        return ERROR;
        } }

public String DeletePOitemRate() throws Exception {
    try {
       //TO DELETE THE item  BY ID
      //it will give item rate Id On Browse Screen Data id on Delete Symbol
        itemrate=itemrateDAO.findItemRateId(getItemrateID());
           //DELETE ABOVE ID IN itemrate
        itemrateDAO.delete(itemrate);
        itemratebrowseList=itemrateDAO.findAll();
        message = "Record successfully deleted";
        itemrate= null;
        return SUCCESS;
        }
       catch (Exception e) {
       // if (e.getCause().toString().contains("fk_INDENTDET_IND_master"))
       //message = "The item rate  cannot be  deleted because of this details is associated with other details,would you like to delete or not "
       // + "then u need to first delete child/parent Record";
       // else
        message = "Exception in Delete method -> ManageitemAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
        return ERROR;
        }
        }

public String EdititemRate() throws Exception {
      try {
           //Retrieve the record to be updated into pomaster object
       itemrate=itemrateDAO.findItemRateId(getItemrateID());
       execute();
       return SUCCESS;
       }catch (Exception e) {
       message = "Exception in Edit method -> ManageIndentMasterAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
       return ERROR;
       }
       }
}

