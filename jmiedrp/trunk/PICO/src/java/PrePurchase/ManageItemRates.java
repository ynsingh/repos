/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PrePurchase;

/**
 *
 * @author sknaqvi
 */
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import pojo.hibernate.*;
import utils.DateUtilities;
import utils.DevelopmentSupport;

//import java.util.Locale;
//import java.util.ResourceBundle;
//import com.opensymphony.xwork2.ActionContext;

//import org.apache.commons.lang.time.DateUtils;
public class ManageItemRates extends DevelopmentSupport {

    private ErpmItemRate itemrate, itemrate2;
    private ErpmItemRateTaxes itemRateTax;
    private ErpmItemMaster erpmim;
    private Integer ItemRateId;
    private String message;
    private Integer defaultCurrency;
    private Short DefaultInsitute1;
    private Short DefaultInsitute;
    private Integer ItemrateID;
    private String effDate;
    private String validUptoDate;
    Integer Default_IR_Detail_ID;
    Integer Default_Item_Supplier;
    Integer irdItemRateDetailsId;
    Integer IrtItemRateTaxesId;
    Integer irtItemRateTaxesId;
    private Integer institutionId, subInstitutionId, departmentId, supplierId, ItemNameId, ItemTypeId, CategoryId, SubCategoryId;
    private String fromDate, toDate;
    private List<ErpmGenMaster> wsfList = new ArrayList<ErpmGenMaster>();
    private ErpmGenMasterDao GMDao = new ErpmGenMasterDao();
    private List<Institutionmaster> imList = new ArrayList<Institutionmaster>();
    private InstitutionmasterDAO imDao = new InstitutionmasterDAO();
    private ErpmItemMasterDAO itemDao = new ErpmItemMasterDAO();
    private List<ErpmItemMaster> itemList = new ArrayList<ErpmItemMaster>();
    private List<ErpmGenMaster> currencyList = new ArrayList<ErpmGenMaster>();
    private List<Suppliermaster> suppList = new ArrayList<Suppliermaster>();
    private List<ErpmGenMaster> taxList = new ArrayList<ErpmGenMaster>();
    private SuppliermasterDAO suppDao = new SuppliermasterDAO();
    private ErpmItemRateDAO itemrateDAO = new ErpmItemRateDAO();
    private ErpmItemRateTaxesDAO itemRateTaxDAO = new ErpmItemRateTaxesDAO();
    private List<ErpmItemRateTaxes> itemratetaxlist = new ArrayList<ErpmItemRateTaxes>();
    private List<ErpmItemRate> itemRateList = new ArrayList<ErpmItemRate>();
    private List<ErpmItemRate> itemRateList2 = new ArrayList<ErpmItemRate>();
    private List<Subinstitutionmaster> simList = new ArrayList<Subinstitutionmaster>();
    private SubinstitutionmasterDAO simDao = new SubinstitutionmasterDAO();
    private List<Departmentmaster> dmList = new ArrayList<Departmentmaster>();
    private DepartmentmasterDAO dmDao = new DepartmentmasterDAO();
    private List<ErpmItemCategoryMaster> erpmIcmList1 = new ArrayList<ErpmItemCategoryMaster>();
    private List<ErpmItemCategoryMaster> erpmIcmList2 = new ArrayList<ErpmItemCategoryMaster>();
    private List<ErpmItemCategoryMaster> erpmIcmList3 = new ArrayList<ErpmItemCategoryMaster>();
    private ErpmItemCategoryMasterDao erpmIcmDao = new ErpmItemCategoryMasterDao();
    private GfrProgramMappingDAO GfrProgramMappingDao = new GfrProgramMappingDAO();
    private InputStream inputStream;
    private static Boolean varShowGFR;

    static String dataSourceURL=null;

    public Boolean getVarShowGFR() {
        return varShowGFR;
    }

    public void setVarShowGFR(Boolean varShowGFR) {
        this.varShowGFR = varShowGFR;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void seteffDate(String effDate) {
        this.effDate = effDate;
    }

    public String geteffDate() {
        return this.effDate;
    }

    public void setvalidUptoDate(String validUptoDate) {
        this.validUptoDate = validUptoDate;
    }

    public Integer getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(Integer institutionId) {
        this.institutionId = institutionId;
    }

    public Integer getSubCategoryId() {
        return SubCategoryId;
    }

    public void setSubCategoryId(Integer SubCategoryId) {
        this.SubCategoryId = SubCategoryId;
    }

    public Integer getSubInstitutionId() {
        return subInstitutionId;
    }

    public void setSubInstitutionId(Integer subInstitutionId) {
        this.subInstitutionId = subInstitutionId;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public Integer getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(Integer CategoryId) {
        this.CategoryId = CategoryId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getItemNameId() {
        return ItemNameId;
    }

    public void setItemNameId(Integer ItemNameId) {
        this.ItemNameId = ItemNameId;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public List<Subinstitutionmaster> getSimList() {
        return simList;
    }

    public void setSimList(List<Subinstitutionmaster> simList) {
        this.simList = simList;
    }

    public List<Departmentmaster> getDmList() {
        return dmList;
    }

    public void setDmList(List<Departmentmaster> dmList) {
        this.dmList = dmList;
    }

    public List<ErpmItemCategoryMaster> getErpmIcmList1() {
        return erpmIcmList1;
    }

    public void setErpmIcmList1(List<ErpmItemCategoryMaster> erpmIcmList1) {
        this.erpmIcmList1 = erpmIcmList1;
    }

    public List<ErpmItemCategoryMaster> getErpmIcmList2() {
        return erpmIcmList2;
    }

    public void setErpmIcmList2(List<ErpmItemCategoryMaster> erpmIcmList2) {
        this.erpmIcmList2 = erpmIcmList2;
    }

    public List<ErpmItemCategoryMaster> getErpmIcmList3() {
        return erpmIcmList3;
    }

    public void setErpmIcmList3(List<ErpmItemCategoryMaster> erpmIcmList3) {
        this.erpmIcmList3 = erpmIcmList3;
    }

    public String getvalidUptoDate() {
        return this.validUptoDate;
    }

    public void seterpmim(ErpmItemMaster erpmim) {
        this.erpmim = erpmim;
    }

    public ErpmItemMaster geterpmim() {
        return this.erpmim;
    }

    public void setItemrateID(Integer ItemrateID) {
        this.ItemrateID = ItemrateID;
    }

    public Integer getItemrateID() {
        return this.ItemrateID;
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

    public Integer getItemTypeId() {
        return ItemTypeId;
    }

    public void setItemTypeId(Integer ItemTypeId) {
        this.ItemTypeId = ItemTypeId;
    }

    public Integer getirtItemRateTaxesId() {
        return irtItemRateTaxesId;
    }

    public void setitemratetaxlist(List<ErpmItemRateTaxes> itemratetaxlist) {
        this.itemratetaxlist = itemratetaxlist;
    }

    public List<ErpmItemRateTaxes> getitemratetaxlist() {
        return this.itemratetaxlist;
    }

    public void setDefault_IR_Detail_ID(Integer Default_IR_Detail_ID) {
        this.Default_IR_Detail_ID = Default_IR_Detail_ID;
    }

    public Integer getDefault_IR_Detail_ID() {
        return this.Default_IR_Detail_ID;
    }

    public void setDefault_Item_Supplier(Integer Default_Item_Supplier) {
        this.Default_Item_Supplier = Default_Item_Supplier;
    }

    public Integer getDefault_Item_Supplier() {
        return this.Default_Item_Supplier;
    }

    public void setitemrate(ErpmItemRate itemrate) {
        this.itemrate = itemrate;
    }

    public ErpmItemRate getitemrate() {
        return this.itemrate;
    }

    public void setitemRateTax(ErpmItemRateTaxes itemRateTax) {
        this.itemRateTax = itemRateTax;
    }

    public ErpmItemRateTaxes getitemRateTax() {
        return this.itemRateTax;
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
        this.itemList = itemList;
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

    public void settaxList(List<ErpmGenMaster> taxList) {
        this.taxList = taxList;
    }

    public List<ErpmGenMaster> gettaxList() {
        return this.taxList;
    }

    public void setirdItemRateDetailsId(Integer irdItemRateDetailsId) {
        this.irdItemRateDetailsId = irdItemRateDetailsId;
    }

    public Integer getirdItemRateDetailsId() {
        return irdItemRateDetailsId;
    }

    public void setItemRateId(Integer ItemRateId) {
        this.ItemRateId = ItemRateId;
    }

    public Integer getItemRateId() {
        return ItemRateId;
    }

    public void setdefaultCurrency(Integer defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }

    public Integer getdefaultCurrency() {
        return this.defaultCurrency;
    }

    public void setDefaultInsitute1(Short DefaultInsitute1) {
        this.DefaultInsitute1 = DefaultInsitute1;
    }

    public Short getDefaultInsitute1() {
        return this.DefaultInsitute1;
    }

    public void setDefaultInsitute(short DefaultInsitute) {
        this.DefaultInsitute = DefaultInsitute;
    }

    public short getDefaultInsitute() {
        return this.DefaultInsitute;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setitemRateList(List<ErpmItemRate> itemRateList) {
        this.itemRateList = itemRateList;
    }

    public List<ErpmItemRate> getitemRateList() {
        return this.itemRateList;
    }

    @Override
    public String execute() throws Exception {
        try {

            //Prepare List of Institutions under perview of the logged user
            imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));

            //Prepare List of Currencies
            currencyList = GMDao.findByErpmGmType(Short.parseShort("6"));

            //Prepare List of Items and Suppliers for the Users default Institute
            itemList = itemDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
            suppList = suppDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));

            //Set Default Institute and Cuurency for the rate
            DefaultInsitute1 = Short.valueOf(getSession().getAttribute("imId").toString());
            defaultCurrency = GMDao.findDefaultCurrency("Rupees");

            wsfList = GMDao.findByErpmGmType(Short.parseShort("9"));

            Short i=18;
            Integer count = GfrProgramMappingDao.findCountByProgramId(i);


            if(count > 0){
             setVarShowGFR(false);
            }
            else{
             setVarShowGFR(true);
            }
            //  itemrate.setIrMinQty(Integer.parseInt("1"));
            return SUCCESS;

        } catch (Exception e) {
            message = "Exception in -> Manage ItemRateAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public void InitializeLOVs() {
        //Prepare List of Institutions under perview of the logged user
        imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));

        //Prepare List of Currencies
        currencyList = GMDao.findByErpmGmType(Short.parseShort("6"));

        //Prepare List of Items and Suppliers for the Users default Institute
        itemList = itemDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
        suppList = suppDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));

        //Set Default Institute and Cuurency for the rate
        DefaultInsitute1 = Short.valueOf(getSession().getAttribute("imId").toString());
        defaultCurrency = GMDao.findDefaultCurrency("Rupees");

        //Prepare LOV for Warranty Start Options
        wsfList = GMDao.findByErpmGmType(Short.parseShort("9"));
    }

    public String SaveItemRate() throws Exception {
        try {
            if (itemrate.getIrItemRateId() == null) {

                itemRateList2 = itemrateDAO.findItemApprovedItems(itemrate.getErpmItemMaster().getErpmimId(),
                        itemrate.getIrdWefDate(),
                        itemrate.getErpmGenMasterByIrCurrencyId().getErpmgmEgmId(),
                        itemrate.getSuppliermaster().getSmId());
                if (itemRateList2.size() == 0) {
                    //For Saving The Data in Item Rate Table
                    DateUtilities dt = new DateUtilities();
                    itemrate.setIrdWefDate(dt.convertStringToDate(geteffDate()));
                    itemrate.setIrdWetDate(dt.convertStringToDate(getvalidUptoDate()));

                    itemrateDAO.save(itemrate);

                    //For getting the item Name And Supplier Name On Taxes Screen
                    Default_Item_Supplier = itemrate.getIrItemRateId();
                    itemrate = itemrateDAO.findItemRateId(Default_Item_Supplier);

                    message = "Item Rate record created successfully. Approval Id is : " + itemrate.getIrItemRateId();

                } else {
                    message = "The Item already has approved rates in the period : "
                            + itemRateList2.get(0).getIrdWefDate() + " to "
                            + itemRateList2.get(0).getIrdWetDate() + ". Cannot save record.";
                }
            } else {
                //Edited Record to be saved here
                ErpmItemRate itemrate2 = itemrateDAO.findByirItemRateId(itemrate.getIrItemRateId());

                DateUtilities dt = new DateUtilities();
                itemrate.setIrdWefDate(dt.convertStringToDate(geteffDate()));
                itemrate.setIrdWetDate(dt.convertStringToDate(getvalidUptoDate()));

                itemrate2 = itemrate;

                itemrateDAO.update(itemrate2);

                message = "Item Rate Record updated successfully.";
            }

            // Prepare LOVs
            InitializeLOVs();

            //Prepare LOV for showing Item Rates for the saved Items
            itemRateList = itemrateDAO.findItemRatesForInstitutionAndItem(itemrate.getInstitutionmaster().getImId(), itemrate.getErpmItemMaster().getErpmimId());

            return SUCCESS;
        } catch (Exception e) {
            message = message + "Exception in save method-> MANAGE ItemRatesAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String DeleteItemRate() throws Exception {
        try {
            //Retrieve the record to be deleted into itermrate object
            itemrate = itemrateDAO.findByirItemRateId(getItemRateId());
            itemrateDAO.delete(itemrate);
            message = "Record successfully deleted";

            //Prepare LOV for showing Item Rates for the saved Items
            itemRateList = itemrateDAO.findItemRatesForInstitutionAndItem(itemrate.getInstitutionmaster().getImId(), itemrate.getErpmItemMaster().getErpmimId());

            // Prepare LOVs
            InitializeLOVs();

            itemrate = null;
            return SUCCESS;
        } catch (Exception e) {
            if (e.getCause().toString().contains("FOREIGN KEY")) {
                message = "Cannot delete record as related record(s) exist(s). Reported cause is         :" + e.getCause();
            } else {
                message = "Exception in Delete method -> ManageGeneralMasterAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            }
            return ERROR;
        }

    }

    public String EditItemRate() throws Exception {
        try {

            //Prepare LOV for showing Control Parameters
            InitializeLOVs();

            //Retrieve the record to be edited into itemrate object
            itemrate = itemrateDAO.findByirItemRateId(getItemRateId());

            DateUtilities dt = new DateUtilities();
            effDate = dt.convertDateToString(itemrate.getIrdWefDate(), "dd-MM-yyyy");
            validUptoDate = dt.convertDateToString(itemrate.getIrdWetDate(), "dd-MM-yyyy");

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Edit method -> ManageItemRateAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    //To Skip The Validation on this Method
    @SkipValidation
    public String FetchItemRates() throws Exception {
        try {
		if(itemrate.getErpmItemMaster().getErpmimId() == null)
			message = "Please Select Item Name";

            itemRateList = itemrateDAO.findItemRatesForInstitutionAndItem(itemrate.getInstitutionmaster().getImId(), itemrate.getErpmItemMaster().getErpmimId());

            //Prepare LOVs
            InitializeLOVs();

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in FetchItemRates method -> ManageItemRatesAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    @SkipValidation
    public String FetchItemRatesDetailsFromTaxex() throws Exception {
        try {
            InitializeLOVs();

            //Retrieve the record to be edited into itemrate object
            itemrate = itemrateDAO.findByirItemRateId(itemrate.getIrItemRateId());

            DateUtilities dt = new DateUtilities();
            effDate = dt.convertDateToString(itemrate.getIrdWefDate(), "dd-MM-yyyy");
            validUptoDate = dt.convertDateToString(itemrate.getIrdWetDate(), "dd-MM-yyyy");

            //Populate the list of Rates defined for that Items
            itemRateList = itemrateDAO.findItemRatesForInstitutionAndItem(itemrate.getInstitutionmaster().getImId(), itemrate.getErpmItemMaster().getErpmimId());

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in FetchItemRates method -> ManageItemRatesAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    @SkipValidation
    public String ClearItemRates() throws Exception {
        try {
            itemrate = null;
            //Prepare LOVs
            InitializeLOVs();

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Clear method -> ManageItemRatesAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String PrepareItemTax() throws Exception {
        try {

            //Prepare List of Tax names
            taxList = GMDao.findByErpmGmType(Short.parseShort("13"));

            //Get the Item Rate Id whose tax is to added/edited
            itemrate = itemrateDAO.findByirItemRateId(getItemRateId());


            //Prepare List of saved taxes for the selected Item
            itemratetaxlist = itemRateTaxDAO.findByirItemRateId(getItemRateId());

            message = "You are now adding Taxes on the Item: " + itemrate.getErpmItemMaster().getErpmimItemBriefDesc();

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in -> Manage ItemRateAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

//This Method Save And Update(On Edit) on ErpmItemRate Table
    public String SaveItemRateTaxes() throws Exception {
        try {
            if (itemRateTax.getIrtItemRateTaxesId() == null) {

                //Retrive The ErpmItemRate ID and set it in the ErpmItemRateTax
                itemrate = itemrateDAO.findItemRateId(itemrate.getIrItemRateId());
                // itemRateTax.setErpmGenMaster(erpmgm);
                itemRateTax.setErpmItemRate(itemrate);

                //Save into it ErpmItemRateTax
                itemRateTaxDAO.save(itemRateTax);

                //Prepare List of Values of Tax names
                taxList = GMDao.findByErpmGmType(Short.parseShort("13"));

                //Retrive The ErpmItemRate ID  Again for Saving the another Taxes Percent
                itemrate = itemrateDAO.findItemRateId(itemrate.getIrItemRateId());
                itemratetaxlist = itemRateTaxDAO.findByirItemRateId(itemRateTax.getErpmItemRate().getIrItemRateId());
                itemRateTax = null;
            } else//else part is for update the record
            {
                //Prepare a object of  ErpmItemRateTaxes object itemratetax2 which contain Taxesx ID
                ErpmItemRateTaxes itemratetax2 = itemRateTaxDAO.findByirtItemRateTaxesId(itemRateTax.getIrtItemRateTaxesId());
                itemratetax2 = itemRateTax;
                //Retrive The ErpmItemRate ID and set it in the ErpmItemRateTax
                ErpmItemRate itemrate2 = itemrateDAO.findByirItemRateId(itemrate.getIrItemRateId());
                itemratetax2.setErpmItemRate(itemrate2);
                //it will update the record
                itemRateTaxDAO.update(itemratetax2);
                //After Editing Browse List of Taxesx is shown
                itemratetaxlist = itemRateTaxDAO.findByirItemRateId(itemRateTax.getErpmItemRate().getIrItemRateId());
                //itemRateList = itemrateDAO.findItemRatesForInstitutionAndItem(itemrate.getInstitutionmaster().getImId(), itemrate.getErpmItemMaster().getErpmimId());
                itemRateTax = null;
                message = "Item Rate Record updated successfully.";
            }

            // Prepare LOVs
            //  InitializeLOVs();

            //Prepare LOV for showing Item Rates for the saved Items
            return SUCCESS;
        } catch (Exception e) {
            message = message + "Exception in save method-> MANAGE ItemTaxRatesAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    //This method perform Deletion of Records
    public String DeleteItemRateTaxes() throws Exception {
        try {
            //TO DELETE THE Rate Taxex by Rate_Detail ID
            itemRateTax = itemRateTaxDAO.findByirtItemRateTaxesId(getIrtItemRateTaxesId());
            itemRateTaxDAO.delete(itemRateTax);
            message = "Record successfully deleted";
            itemrate = itemrateDAO.findItemRateId(itemrate.getIrItemRateId());
            itemRateTax.setErpmItemRate(itemrate);
            //Prepare List of Tax names
            taxList = GMDao.findByErpmGmType(Short.parseShort("13"));
            itemratetaxlist = itemRateTaxDAO.findByirItemRateId(itemRateTax.getErpmItemRate().getIrItemRateId());
            itemRateTax = null;
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Delete method -> Delete Rate Taxes" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    //This method perform Edit of Records
    public String EditItemRateTaxes() throws Exception {
        try {

            //Retrieve the record to be updated into itemratetax object
            itemRateTax = itemRateTaxDAO.findByirtItemRateTaxesId(getIrtItemRateTaxesId());

            //Prepare List of Tax names
            taxList = GMDao.findByErpmGmType(Short.parseShort("13"));

            itemrate = itemrateDAO.findByirItemRateId(itemRateTax.getErpmItemRate().getIrItemRateId());
            //For getting the Supplier Name On Taxes Screen
            Default_Item_Supplier = itemrate.getIrItemRateId();

//            itemrate = itemrateDAO.findItemRateId(Default_Item_Supplier);
            //Prepare List of saved taxes for the selected Item
            itemratetaxlist = itemRateTaxDAO.findByirItemRateId(itemrate.getIrItemRateId());
            
            return SUCCESS;
        } catch (Exception e) {


            message = "Exception in Edit method -> EditItemsRate TaxesAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }


    public void validate() {
        DateUtilities dt = new DateUtilities();

        try {

            //If Item Rate is to be validated
            if (itemRateTax == null && itemrate.getIrItemRateId() == null) {
                if (itemrate.getInstitutionmaster().getImId() == null) {
                    addFieldError("itemrate.institutionmaster.imId", "Please select institution");
                }
                if (itemrate.getErpmItemMaster().getErpmimId() == null) {
                    addFieldError("itemrate.erpmItemMaster.erpmimId", "Please select Item");
                }
                if (itemrate.getSuppliermaster().getSmId() == null) {
                    addFieldError("itemrate.suppliermaster.smId", "Please select Supplier ");
                }
                if (itemrate.getErpmGenMasterByIrCurrencyId().getErpmgmEgmId() == null) {
                    addFieldError("itemrate.erpmGenMasterByIrCurrencyId.erpmgmEgmId", "Please select Currency ");
                }
                if (itemrate.getIrdRate() == null) {
                    addFieldError("itemrate.irdRate", "Please enter Unit Rate for Item");
                }
                if (geteffDate().length() == 0) {
                    addFieldError("effDate", "Enter Approval Effective From Date [MM-DD-YYYY]");
                } else {
                    if (dt.isValidDate(geteffDate()) == false) {
                        addFieldError("effDate", "Enter Approval Effective From Date [MM-DD-YYYY]");
                    }
                }
                if (getvalidUptoDate().length() == 0) {
                    addFieldError("validUptoDate", "Enter last date of approval validity [MM-DD-YYYY]");
                } else {
                    if (dt.isValidDate(getvalidUptoDate()) == false) {
                        addFieldError("validUptoDate", "Enter last date of approval validity [MM-DD-YYYY]");
                    }
                }
                try {
                    if (dt.isFirstDateBeforeSecond(dt.convertStringToDate(geteffDate()), dt.convertStringToDate(getvalidUptoDate())) == false) {
                        addFieldError("validUptoDate", "Last Date of Approval cannot be in past");
                    }
                } catch (Exception e) {
                }

                //Validate Minimum Quantity Field
                if (itemrate.getIrMinQty() == null || itemrate.getIrMinQty() == 0) {
                    addFieldError("itemrate.irMinQty", "Enter Minimum Quantity for which the rates are valid");
                } else {
                    try {
                        Integer x = Integer.parseInt(itemrate.getIrMinQty().toString());
                    } catch (Exception nfe) {
                        addFieldError("itemrate.irMinQty", "Enter valid value for Minimum Quantity");
                        InitializeLOVs();
                    }
                }

                //Validate Maximum Quantity Field
                if (itemrate.getIrMaxQty() != null) {
                    try {
                        Integer x = Integer.parseInt(itemrate.getIrMinQty().toString());
                    } catch (Exception nfe) {
                        addFieldError("itemrate.irMaxQty", "Enter valid value for Maximum Quantity");
                        InitializeLOVs();
                    }
                }

                //Validate if the max quantity is greater than minimum quantity
                if (itemrate.getIrMinQty() > itemrate.getIrMaxQty()) {
                    addFieldError("itemrate.irMinQty", "Mimimum quantity cannot exceed maximum order quantity");
                }
                if (itemrate.getErpmGenMasterByIrWarrantyStartsFromId().getErpmgmEgmId() == null) {
                    addFieldError("itemrate.erpmGenMasterByIrWarrantyStartsFromId.erpmgmEgmId", "Please choose Warranty effect date");
                }

                if (itemrate.getIrWarrantyMonths() == null) {
                    addFieldError("itemrate.irWarrantyMonths", "Please enter warranty duration in months");
                }

                InitializeLOVs();
            } else {
                //Validation on Taxex Screen
                //validation fot TaxName        
                if (itemRateTax.getErpmGenMaster().getErpmgmEgmId() == null) {
                    addFieldError("itemRateTax.erpmGenMaster.erpmgmEgmId", "Please  click here to  select Tax Name ");
                }
                taxList = GMDao.findByErpmGmType(Short.parseShort("13"));
                //validation fot Tax On Percent
                if (itemRateTax.getIrtTaxPercent() == null) {
                    addFieldError("itemRateTax.irtTaxPercent", "TaxPercent cannot be Blank");
                }
                if (Double.valueOf(itemRateTax.getIrtTaxPercent().toString()) < 0) //{
                {
                    addFieldError("itemRateTax.irtTaxPercent", "Tax Percent cannot  be negative");
                } else if (Double.valueOf(itemRateTax.getIrtTaxPercent().toString()) > 100) //{
                {
                    addFieldError("itemRateTax.irtTaxPercent", "Tax Percent cannot exceed 100");
                }
                //validation fot Tax On Value
                if (itemRateTax.getIrtTaxOnValuePercent() == null) {
                    addFieldError("itemRateTax.irtTaxOnValuePercent", "Tax On Value Percent cannot be blank");
                }
                if (Double.valueOf(itemRateTax.getIrtTaxOnValuePercent().toString()) < 0) {
                    addFieldError("itemRateTax.irtTaxOnValuePercent", "Tax on value Percent cannot  be negative");
                } else if (Double.valueOf(itemRateTax.getIrtTaxOnValuePercent().toString()) > 100) //{
                {
                    addFieldError("itemRateTax.irtTaxOnValuePercent", "Tax on Value Percent cannot exceed 100");
                }
                //validation fot Tax On Surcharge Percent
                if (itemRateTax.getIrtSurchargePercent() == null) {
                    addFieldError("itemRateTax.irtSurchargePercent", "SurchargePercent cannot be Blank");
                }
                if (Double.valueOf(itemRateTax.getIrtSurchargePercent().toString()) < 0) {
                    addFieldError("itemRateTax.irtSurchargePercent", "Surcharge Percent cannot  be negative");
                } else if (Double.valueOf(itemRateTax.getIrtSurchargePercent().toString()) > 100) //{
                {
                    addFieldError("itemRateTax.irtSurchargePercent", "Tax Percent cannot exceed 100");
                }
                InitializeLOVs();
            }
        } catch (NullPointerException npe) {
            InitializeLOVs();
        }
    }

    @SkipValidation
    public String ExportItemRatesHistory() throws Exception {
        try {

            Short i = 1, j = 2, k = 3;
            imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
            simList = simDao.findSubInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Short.valueOf(getSession().getAttribute("imId").toString()));
            dmList = dmDao.findDepartmentForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Integer.valueOf(getSession().getAttribute("simId").toString()));
            suppList = suppDao.findAll();
            erpmIcmList1 = erpmIcmDao.findByErpmicmItemLevel(i);

            //Prepare Item Category List
            erpmIcmList2 = erpmIcmDao.findByErpmicmItemLevel(j);

            //Prepare Item Sub Category List
            erpmIcmList3 = erpmIcmDao.findByErpmicmItemLevel(k);

            //prepare item types
            itemList = itemDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));

            return SUCCESS;
        } catch (Exception e) {
            return ERROR;
        }
    }

    @SkipValidation
    public String SubmitItemRateHistory() throws Exception {
        HashMap hm = new HashMap();

        // Get System properties
        Properties properties = System.getProperties();

        // Get the path separator symbol, which is unfortunatly different, in different OS platform.
        String pathSeparator = properties.getProperty("file.separator");

        pathSeparator =pathSeparator + pathSeparator;
        String repPath = "pico" + pathSeparator + "PrePurchase"  + pathSeparator + "Reports" + pathSeparator + "ItemRateDetailReport.jasper" ;

        String fileName = getSession().getServletContext().getRealPath(repPath);
//        String fileName = getSession().getServletContext().getRealPath("pico\\PrePurchase\\Reports\\ItemRateDetailReport.jasper");
        String whereCondition = "";

        try {
//            Locale locale = ActionContext.getContext().getLocale();
//            ResourceBundle bundle = ResourceBundle.getBundle("pico", locale);
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bundle.getString("dbName"), bundle.getString("mysqlUserName"), bundle.getString("mysqlPassword")); 

            Context ctx = new InitialContext();
            if (ctx == null) {
                throw new RuntimeException("JNDI");
            }
            dataSourceURL = (String) ctx.lookup("java:comp/env/ReportURL").toString();
            Connection conn = DriverManager.getConnection(dataSourceURL);

            HttpServletResponse response = ServletActionContext.getResponse();
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Content-Disposition", "attachment; filename=ItemRateDetailReport.pdf");
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            //  Setup Where Condition Clause
            if (getInstitutionId() == null || getInstitutionId() == 0) {
                whereCondition = "erpm_item_rate.ir_im_id = " + getSession().getAttribute("imId");
            } else {
                whereCondition = "erpm_item_rate.ir_im_id = " + getInstitutionId().toString();
            }

            if (getItemNameId() == null || getItemNameId() == 0) {
                whereCondition = whereCondition + " and erpm_item_rate.IR_Item_Id <> 0";
            } else {
                whereCondition = whereCondition + " and erpm_item_rate.IR_Item_Id = " + getItemNameId();
            }

            if (getSubCategoryId() == null || getSubCategoryId() == 0) {
                whereCondition = whereCondition + " and erpm_item_master.ERPMIM_Item_Cat3 <>0";
            } else {
                whereCondition = whereCondition + " and erpm_item_master.ERPMIM_Item_Cat3 = " + getSubCategoryId();
            }

            if (getCategoryId() == null || getCategoryId() == 0) {
                whereCondition = whereCondition + " and erpm_item_master.ERPMIM_Item_Cat2 <> 0";
            } else {
                whereCondition = whereCondition + " and erpm_item_master.ERPMIM_Item_Cat2 = " + getCategoryId();
            }

            if (getItemTypeId() == null || getItemTypeId() == 0) {
                whereCondition = whereCondition + " and erpm_item_master.ERPMIM_Item_Cat1 <> 0";
            } else {
                whereCondition = whereCondition + " and erpm_item_master.ERPMIM_Item_Cat1 = " + getItemTypeId();
            }

            if (getSupplierId() == null || getSupplierId() == 0) {
                whereCondition = whereCondition + " and erpm_item_rate.IR_Supplier_Id <> 0";
            } else {
                whereCondition = whereCondition + " and erpm_item_rate.IR_Supplier_Id = " + getSupplierId();
            }

            if (getFromDate().length() != 10) {
                whereCondition = whereCondition + " and erpm_item_rate.IRD_WEF_Date > str_to_date('01-01-0001','%d-%m-%Y')";
            } else {
                whereCondition = whereCondition + " and erpm_item_rate.IRD_WEF_Date >= str_to_date('" + getFromDate() + "','%d-%m-%Y')";
            }

            if (getToDate().length() != 10) {
                whereCondition = whereCondition + " and erpm_item_rate.IRD_WET_Date >= str_to_date('01-01-0001','%d-%m-%Y')";
            } else {
                whereCondition = whereCondition + " and erpm_item_rate.IRD_WET_Date <= str_to_date('" + getToDate() + "','%d-%m-%Y')";
            }

            hm.put("condition", whereCondition);

            JasperPrint jp = JasperFillManager.fillReport(fileName, hm, conn);
            JasperExportManager.exportReportToPdfStream(jp, baos);
            response.setContentLength(baos.size());
            ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
            inputStream = bis;

            return SUCCESS;
        } catch (JRException e) {
            message = "Error is : " + e.getMessage() + e.getCause();
            return ERROR;
        }
    }

    @SkipValidation
    public String ExportItemRates() throws Exception {
        HashMap hm = new HashMap();

        // Get System properties
        Properties properties = System.getProperties();

        // Get the path separator symbol, which is unfortunatly different, in different OS platform.
        String pathSeparator = properties.getProperty("file.separator");

        pathSeparator =pathSeparator + pathSeparator;
        String repPath = "pico" + pathSeparator + "PrePurchase"  + pathSeparator + "Reports" + pathSeparator + "ItemRate.jasper" ;

        String fileName = getSession().getServletContext().getRealPath(repPath);
//        String fileName = getSession().getServletContext().getRealPath("pico\\PrePurchase\\Reports\\ItemRate.jasper");
        String whereCondition = "";

        try {
//            Locale locale = ActionContext.getContext().getLocale();
//            ResourceBundle bundle = ResourceBundle.getBundle("pico", locale);
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bundle.getString("dbName"), bundle.getString("mysqlUserName"), bundle.getString("mysqlPassword")); 

            Context ctx = new InitialContext();
            if (ctx == null) {
                throw new RuntimeException("JNDI");
            }
            dataSourceURL = (String) ctx.lookup("java:comp/env/ReportURL").toString();
            Connection conn = DriverManager.getConnection(dataSourceURL);

            HttpServletResponse response = ServletActionContext.getResponse();
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Content-Disposition", "attachment; filename=ItemRate.pdf");
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            //Setup Where Condition Clause
            if (itemrate.getInstitutionmaster().getImId() == null || itemrate.getInstitutionmaster().getImId() == 0) {
//                whereCondition = " and erpm_item_rate.ir_im_id = " + getSession().getAttribute("imId");
                whereCondition = "erpm_item_rate.ir_im_id = " + itemrate.getInstitutionmaster().getImId();                
            } else {
                whereCondition = " and erpm_item_rate.ir_im_id = " + itemrate.getInstitutionmaster().getImId();
            }

            if (itemrate.getErpmItemMaster().getErpmimId() == null || itemrate.getErpmItemMaster().getErpmimId() == 0) {
                whereCondition = whereCondition + " and erpm_item_rate.IR_Item_Id <> 0";
            } else {
                whereCondition = whereCondition + " and erpm_item_rate.IR_Item_Id = " + itemrate.getErpmItemMaster().getErpmimId();
            }

            if (itemrate.getSuppliermaster().getSmId() == null || itemrate.getSuppliermaster().getSmId() == 0) {
                whereCondition = whereCondition + " and erpm_item_rate.IR_Supplier_Id <> 0";
            } else {
                whereCondition = whereCondition + " and erpm_item_rate.IR_Supplier_Id = " + itemrate.getSuppliermaster().getSmId();
            }

            if (itemrate.getErpmGenMasterByIrCurrencyId().getErpmgmEgmId() == null || itemrate.getErpmGenMasterByIrCurrencyId().getErpmgmEgmId() == 0) {
                whereCondition = whereCondition + " and erpm_item_rate.IR_Currency_ID <> 0 ";
            } else {
                whereCondition = whereCondition + " and erpm_item_rate.IR_Currency_ID = " + itemrate.getErpmGenMasterByIrCurrencyId().getErpmgmEgmId();
            }

            if (geteffDate().length() != 10) {
                whereCondition = whereCondition + " and erpm_item_rate.IRD_WEF_Date > str_to_date('01-01-0001','%d-%m-%Y')";
            } else {
                whereCondition = whereCondition + " and erpm_item_rate.IRD_WEF_Date >= str_to_date('" + geteffDate() + "','%d-%m-%Y')";
            }

            if (getvalidUptoDate().length() != 10) {
                whereCondition = whereCondition + " and erpm_item_rate.IRD_WET_Date >= str_to_date('01-01-0001','%d-%m-%Y')";
            } else {
                whereCondition = whereCondition + " and erpm_item_rate.IRD_WET_Date <= str_to_date('" + getvalidUptoDate() + "','%d-%m-%Y')";
            }

            hm.put("condition", whereCondition);

            JasperPrint jp = JasperFillManager.fillReport(fileName, hm, conn);
            JasperExportManager.exportReportToPdfStream(jp, baos);
            response.setContentLength(baos.size());
            ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
            inputStream = bis;

            return SUCCESS;
        } catch (JRException e) {
            message = "Error is : " + e.getMessage() + e.getCause();
            return ERROR;
        }
    }


     @SkipValidation
    public String showGFRreport() throws Exception {
        HashMap hm = new HashMap();

        // Get System properties
        Properties properties = System.getProperties();

        // Get the path separator symbol, which is unfortunatly different, in different OS platform.
        String pathSeparator = properties.getProperty("file.separator");

        pathSeparator =pathSeparator + pathSeparator;
        String repPath = "pico" + pathSeparator + "Administration"  + pathSeparator + "Reports" + pathSeparator + "ShowGFRMappedinProgram.jasper" ;

        String fileName = getSession().getServletContext().getRealPath(repPath);
//        String fileName = getSession().getServletContext().getRealPath("pico\\Administration\\Reports\\ShowGFRMappedinProgram.jasper");

        String whereCondition = "";

        try {
//            Locale locale = ActionContext.getContext().getLocale();
//            ResourceBundle bundle = ResourceBundle.getBundle("pico", locale);
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bundle.getString("dbName"), bundle.getString("mysqlUserName"), bundle.getString("mysqlPassword")); 

            Context ctx = new InitialContext();
            if (ctx == null) {
                throw new RuntimeException("JNDI");
            }
            dataSourceURL = (String) ctx.lookup("java:comp/env/ReportURL").toString();
            Connection conn = DriverManager.getConnection(dataSourceURL);

            HttpServletResponse response = ServletActionContext.getResponse();
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Content-Disposition", "attachment; filename=ShowGfr.pdf");
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();


            whereCondition = "gfr_program_mapping.`GPM_Program_ID` = 18";

            hm.put("condition", whereCondition);
            hm.put("screen_name", "ITEM RATES");

            JasperPrint jp = JasperFillManager.fillReport(fileName, hm, conn);
            JasperExportManager.exportReportToPdfStream(jp, baos);
            response.setContentLength(baos.size());
            ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
            inputStream = bis;

            return SUCCESS;
        } catch (JRException e) {
            message = "Error is : " + e.getMessage() + e.getCause();
            return ERROR;
        }
    }
}
