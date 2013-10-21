/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author SKNaqvi
 */
package PrePurchase;
import java.math.BigDecimal;
import pojo.hibernate.ErpmIndentMaster;
import pojo.hibernate.ErpmIndentMasterDAO;
import pojo.hibernate.ErpmItemRate;
import pojo.hibernate.ErpmItemRateDAO;
import pojo.hibernate.ErpmItemMaster;
import pojo.hibernate.ErpmItemMasterDAO;
import pojo.hibernate.ErpmIndentDetail;
import pojo.hibernate.ErpmIndentDetailDAO;
import pojo.hibernate.WorkflowtransactionDAO;

import utils.DateUtilities;
import utils.DevelopmentSupport;
import java.math.RoundingMode;

import java.util.*;
import org.apache.struts2.interceptor.validation.SkipValidation;

public class ManageIndentItems extends DevelopmentSupport {

    private ErpmIndentMaster erpmindtmast = new ErpmIndentMaster();
    private ErpmIndentDetail erpmindtdet = new ErpmIndentDetail();
    private ErpmIndentDetailDAO erpmindtdetDao = new ErpmIndentDetailDAO();
    private ErpmItemRateDAO erpmItemRateDao = new ErpmItemRateDAO();
    private WorkflowtransactionDAO workFlowTransactionDao = new WorkflowtransactionDAO();
    private String message;
    private Short indtDetailId;
    private Short defaultIndent;
    private Integer defaultCurrency;
    private String defaultIndentTitle;
    private String defaultCurrencyName;
    private Integer defaultUOP;
    private String UOP;
    private String selectedItemRate;
    private String selectedItemRateCurrency;
    private String selectedItemRateValidFrom;
    private String selectedItemRateValidTo;
    private BigDecimal totalCost;
    private List<String> iRateList;
    private Integer minOrderQuantity, maxOrderQuantity;
    private Short indentId;
    private Boolean btnEditEnabled;
    private Integer itemRateId;

    private ErpmIndentMasterDAO erpminDao = new ErpmIndentMasterDAO();
    private ErpmItemMasterDAO indtitemlistDao = new ErpmItemMasterDAO();
    private List<ErpmItemMaster> itemList = new ArrayList<ErpmItemMaster>();
    private ErpmIndentDetailDAO erpmindetDao = new ErpmIndentDetailDAO();
    private List<ErpmItemRate> itemRateList = new ArrayList<ErpmItemRate>();
    private List<ErpmIndentDetail> indentitemlist = new ArrayList<ErpmIndentDetail>();

    
   public BigDecimal gettotalCost() {
        return this.totalCost;
    }

    public void settotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }


    public Integer getitemRateId() {
        return this.itemRateId;
    }

    public void setitemRateId(Integer itemRateId) {
        this.itemRateId = itemRateId;
    }


    public Boolean getbtnEditEnabled() {
        return this.btnEditEnabled;
    }

    public void setbtnEditEnabled(Boolean btnEditEnabled) {
        this.btnEditEnabled = btnEditEnabled;
    }


    public Integer getminOrderQuantity() {
        return this.minOrderQuantity;
    }

    public Integer getmaxOrderQuantity() {
        return this.maxOrderQuantity;
    }


    public void setindentId(Short indentId) {
        this.indentId = indentId;
    }

    public Short getindentId() {
          return this.indentId;
    }


    public void setMessage(String message) {
                     this.message = message;
    }

    public String getMessage() {
                  return this.message;
    }

    public void setindtDetailId(Short indtDetailId) {
        this.indtDetailId = indtDetailId;
    }

    public Short getindtDetailId() {
        return this.indtDetailId;
    }

    public void seterpmindtmast(ErpmIndentMaster erpmindtmast) {
                     this.erpmindtmast = erpmindtmast;
    }

    public ErpmIndentMaster geterpmindtmast() {
                  return this.erpmindtmast;
    }

    public void seterpmindtdet(ErpmIndentDetail erpmindtdet) {
                this.erpmindtdet = erpmindtdet;
    }

    public ErpmIndentDetail geterpmindtdet() {
                        return this.erpmindtdet;
    }

    public void setitemList(List<ErpmItemMaster> itemList) {
                 this.itemList= itemList;
    }

    public void setitemRateList(List<ErpmItemRate> itemRateList) {
           this.itemRateList = itemRateList;
    }

   public List<ErpmItemRate> getitemRateList() {
          return this.itemRateList;
    }


    public List<ErpmItemMaster> getitemList() {
                    return this.itemList;
    }

    public void setdefaultIndentTitle(String defaultIndentTitle) {
        this.defaultIndentTitle = defaultIndentTitle;
    }

    public String getdefaultIndentTitle() {
        return this.defaultIndentTitle;
    }

    public void setdefaultCurrencyName(String defaultCurrencyName) {
                     this.defaultCurrencyName = defaultCurrencyName;
    }

    public String getdefaultCurrencyName() {
                  return this.defaultCurrencyName;
    }

    public void setdefaultUOP(Integer defaultUOP) {
           this.defaultUOP = defaultUOP;
    }

    public Integer getdefaultUOP() {
          return this. defaultUOP;
    }

    public void setdefaultCurrency(Integer defaultCurrency) {
           this.defaultCurrency = defaultCurrency;
    }

    public Integer getdefaultCurrency() {
          return this. defaultCurrency;
    }

   public void setdefaultIndent(Short defaultIndent) {
           this.defaultIndent = defaultIndent;
    }

    public Short getdefaultIndent() {
           return this. defaultIndent;
    }

    public void setUOP(String UOP)  {
        this.UOP = UOP;
    }

    public String getUOP() {
        return this.UOP;
    }

    public void setselectedItemRate(String selectedItemRate)  {
        this.selectedItemRate = selectedItemRate;
    }

    public String getselectedItemRate() {
        return this.selectedItemRate;
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

    public void setindentitemlist(List<ErpmIndentDetail> indentitemlist) {
        this.indentitemlist= indentitemlist;
    }

    public List<ErpmIndentDetail> getindentitemlist() {
        return this.indentitemlist;
    }


  @Override

  public String execute() throws Exception {
    try {

        String authorisedUser = workFlowTransactionDao.findAuthorisedUser(getindentId());       
        String sessionUser = getSession().getAttribute("username").toString() ;

        if (sessionUser.equalsIgnoreCase(authorisedUser) || authorisedUser.equalsIgnoreCase("No user")) {
            defaultIndent = getindentId();
            itemList=indtitemlistDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
            erpmindtmast = erpminDao.findIndentMasterId(getindentId());
            defaultCurrency = erpmindtmast.getErpmGenMasterByIndtCurrencyId().getErpmgmEgmId();
            defaultCurrencyName=erpmindtmast.getErpmGenMasterByIndtCurrencyId().getErpmgmEgmDesc();
            defaultIndentTitle = erpmindtmast.getIndtTitle();
            indentitemlist = erpmindetDao.findByindtIndentId(getindentId());
        }
         else {
             message = "Sorry, You are not authorised for this work/Work is already submitted.";
             return ERROR;
            }
      //  InitializeFieldsForItemsEntry();
        return SUCCESS;
        } catch (Exception e) {
        message = message + "Exception in -> ManageIndentItemsAxn "  + e.getMessage() + " Reported Cause is: " + e.getCause();
        return ERROR;
        }
   }

  public void InitializeFieldsForItemsEntry() {

        itemList=indtitemlistDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
        defaultIndent = erpmindtmast.getIndtIndentId();
        defaultCurrency = erpmindtmast.getErpmGenMasterByIndtCurrencyId().getErpmgmEgmId();
        defaultCurrencyName=erpmindtmast.getErpmGenMasterByIndtCurrencyId().getErpmgmEgmDesc();
        defaultIndentTitle = erpmindtmast.getIndtTitle();

        indentitemlist = erpmindtdetDao.findByindtIndentId(defaultIndent);
  }

public void ClearFields() {
            UOP = "";
            selectedItemRate = null;
            selectedItemRateCurrency = "";
            selectedItemRateValidFrom = "";
            selectedItemRateValidTo =   "";
            minOrderQuantity = null;
            maxOrderQuantity = null;
}
  //This function Save and Browse given IndentDetails Records

    public String SaveIndentItems() throws Exception {
    try {

        //The code under If statement adds a new Item to the Indent
        if(erpmindtdet.getIndtDetailId() == null) {

            erpmindtmast = erpminDao.findIndentMasterId(defaultIndent);
            erpmindtdet.setErpmIndentMaster(erpmindtmast);

            //Set Item Rate and Default Value for Approved Items Rate
            if (erpmindtdet.getErpmItemRate().getIrItemRateId() == null) {
                 erpmindtdet.setErpmItemRate(null);

                 if(erpmindtdet.getIndtQuantity() != 0) {    
                    BigDecimal divisor = new BigDecimal(erpmindtdet.getIndtQuantity());                     
                    erpmindtdet.setIndtAcceptedUnitRate(erpmindtdet.getIndtApproxcost().divide(divisor));
                 }
            }
            else           
            {
                ErpmItemRate itemRate = erpmItemRateDao.findByirItemRateId(erpmindtdet.getErpmItemRate().getIrItemRateId());
                erpmindtdet.setIndtAcceptedUnitRate(itemRate.getIrdRate());
            }

            //Set Default Value for Approved Quantities
            erpmindtdet.setIndtApprovedQuantity(erpmindtdet.getIndtQuantity());

            //Save record in Indent Detail Table
            erpmindetDao.save(erpmindtdet);

            //Prepare List of Items entered under the current Indent
            indentitemlist=erpmindetDao.findByindtIndentId(erpmindtdet.getErpmIndentMaster().getIndtIndentId());
            
            message ="Item Added successfully to the Indent";

        }
        else {
            //Indent Item Detail has been edited; the code below updates the record

            //Retrieve the Indent Detail record for update
            ErpmIndentDetail erpmindtdet1 = erpmindetDao.findByindtDetailId(erpmindtdet.getIndtDetailId());

            //Retrieve the Indent Master Object
            erpmindtmast = erpminDao.findIndentMasterId(defaultIndent);
            erpmindtdet.setErpmIndentMaster(erpmindtmast);

            if (erpmindtdet.getErpmItemRate().getIrItemRateId() == null)
            {
                if(itemRateId == null) {
                    erpmindtdet.setErpmItemRate(null);
                    if(erpmindtdet.getIndtQuantity() != 0) {
                        BigDecimal divisor = new BigDecimal(erpmindtdet.getIndtQuantity());
                        erpmindtdet.setIndtAcceptedUnitRate(erpmindtdet.getIndtApproxcost().divide(divisor,RoundingMode.HALF_UP));
                 }
                }
                else
                {
                    erpmindtdet.setErpmItemRate(erpmItemRateDao.findByirItemRateId(itemRateId));
                    erpmindtdet.setIndtAcceptedUnitRate(erpmItemRateDao.findByirItemRateId(itemRateId).getIrdRate());
                }
            }
            //Set Default Approved Item Rate
            if(erpmindtdet.getIndtAcceptedUnitRate() == null)
                erpmindtdet.setIndtAcceptedUnitRate(erpmItemRateDao.findByirItemRateId(itemRateId).getIrdRate());

            //Set Default Value for Approved Quantities
            if(erpmindtdet.getIndtApprovedQuantity() == null)
                erpmindtdet.setIndtApprovedQuantity(erpmindtdet.getIndtQuantity());

            //Assign the changed erpmindtdet to the retrieved record
            erpmindtdet1=erpmindtdet;

            //Update the Indent Detail Record
            erpmindetDao.update(erpmindtdet1);

            message = "Item updated successfully";
         }
  
        InitializeFieldsForItemsEntry();      
        erpmindtdet = null;
        ClearFields();
        indentitemlist = erpmindetDao.findByindtIndentId(erpmindtmast.getIndtIndentId());
        
        return SUCCESS;
        } catch (Exception e) {
            message = message + "Exception in -> SaveIndentItemsAxn "  + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
       }
   }


   //This function opens the given Indent Detail Record for Editing
    @SkipValidation
    public String EditIndentDetail() throws Exception {
        try {

            //Retrieve the record to be updated into erpindtdet object
            erpmindtdet = erpmindetDao.findByindtDetailId(Short.parseShort(getindtDetailId().toString()));
            
            erpmindtmast = erpminDao.findIndentMasterId(erpmindtdet.getErpmIndentMaster().getIndtIndentId());
            defaultIndent = erpmindtdet.getErpmIndentMaster().getIndtIndentId();

            InitializeFieldsForItemsEntry();
            UOP = erpmindtdet.getErpmItemMaster().getErpmGenMaster().getErpmgmEgmDesc();
            if(erpmindtdet.getErpmItemRate() == null) {
                selectedItemRate = "Self Estimate";
                BigDecimal qty = new BigDecimal (erpmindtdet.getIndtQuantity());
                totalCost = erpmindtdet.getIndtApproxcost().multiply(qty);
            }
                
            else
            {
                selectedItemRate = erpmindtdet.getErpmItemRate().getIrdRate().toString();
                itemRateId = erpmindtdet.getErpmItemRate().getIrItemRateId();

                selectedItemRateCurrency = defaultCurrencyName;

                DateUtilities dt = new DateUtilities();
                selectedItemRateValidFrom = dt.convertDateToString(erpmindtdet.getErpmItemRate().getIrdWefDate(),"dd-MM-yyyy");
                selectedItemRateValidTo =   dt.convertDateToString(erpmindtdet.getErpmItemRate().getIrdWetDate(),"dd-MM-yyyy");

                minOrderQuantity = erpmindtdet.getErpmItemRate().getIrMinQty();
                maxOrderQuantity = erpmindtdet.getErpmItemRate().getIrMaxQty();

                //Set default Approved Quantities and Approximate Cosr

                if(erpmindtdet.getIndtApprovedQuantity() == null)
                    erpmindtdet.setIndtApprovedQuantity(erpmindtdet.getIndtQuantity());

                if(erpmindtdet.getIndtAcceptedUnitRate()== null)
                    erpmindtdet.setIndtAcceptedUnitRate(erpmindtdet.getIndtApproxcost());

            }
           
           return SUCCESS;
        }catch  (Exception e) {

        message = "Exception in Edit method -> ManageEditItemsAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
        return SUCCESS;
        }
        }

    @SkipValidation
    public String SubmitIndent() throws Exception {
        try {

            indentId = erpmindtmast.getIndtIndentId();
            return SUCCESS;
        }
        catch (Exception e) {
            message = "Exception in SubmitIndentAxn method -> ManageEditItemsAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }



    //This method prepares editing of the Indent Items for approval purposes
    public String editIndent() {
    try{

        //Prepare List of Items in Indent

        erpmindtdet = erpmindetDao.findByindtDetailId(getindtDetailId());
        erpmindtmast = erpminDao.findIndentMasterId(getindentId());
        itemList=indtitemlistDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
        defaultIndent = erpmindtmast.getIndtIndentId();
        defaultCurrency = erpmindtmast.getErpmGenMasterByIndtCurrencyId().getErpmgmEgmId();
        defaultCurrencyName=erpmindtmast.getErpmGenMasterByIndtCurrencyId().getErpmgmEgmDesc();
        defaultIndentTitle = erpmindtmast.getIndtTitle();
        indentitemlist = erpmindetDao.findByindtIndentId(getindentId());

        if(erpmindtdet.getIndtApprovedQuantity() == null)
            erpmindtdet.setIndtApprovedQuantity(erpmindtdet.getIndtQuantity());

        if(erpmindtdet.getIndtAcceptedUnitRate()== null)
            erpmindtdet.setIndtAcceptedUnitRate(erpmindtdet.getIndtApproxcost());

        message = "You are now editing Indent No:  "  + getindentId();
        
        return SUCCESS;
    }catch(Exception e) {
        message = "Exception in SubmitIndent -> editIndentAxn. Error Message is : " + e.getMessage() + " Reported Cause is:  "+ e.getCause();
        return ERROR;

    }

}



   public void validate()  {
    try {

          if(erpmindtdet.getErpmItemMaster().getErpmimId()== null)
            addFieldError("erpmindtdet.erpmItemMaster.erpmimId" ,"Select Item/Double click to populate list");

          if(erpmindtdet.getIndtDetailId() == null)  {
                if(erpmindtdetDao.CountIndentItemsByItemId(erpmindtmast.getIndtIndentId() , erpmindtdet.getErpmItemMaster().getErpmimId()) > 0)
                    addFieldError("erpmindtdet.erpmItemMaster.erpmimId" ,"You have already eneterd this item");                    
            }

          if(erpmindtdet.getIndtQuantity() <= 0)
                addFieldError("erpmindtdet.indtQuantity" ,"Provide valid value for Quantity");

          if( !getselectedItemRate().contains("Self Estimate")) {
                if((erpmindtdet.getErpmItemRate().getIrItemRateId() == null) && (getbtnEditEnabled() != true ))
                    addFieldError("erpmindtdet.erpmItemRate.irItemRateId" ,"Provide valid value for Item Rate");
           }

          if(erpmindtdet.getIndtApproxcost() == null)
              addFieldError("erpmindtdet.indtApproxcost" ,"Provide value for the approximate cost");

          InitializeFieldsForItemsEntry();
      }
      catch (Exception e) {
           
        }
    }
}