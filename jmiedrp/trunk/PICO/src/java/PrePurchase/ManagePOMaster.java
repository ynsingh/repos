/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Sajid Aziz
 * @Revised by: SK Naqvi
 */
package PrePurchase;

import pojo.hibernate.Institutionmaster;
import pojo.hibernate.InstitutionmasterDAO;
import pojo.hibernate.ErpmIndentDetail;
import pojo.hibernate.ErpmIndentDetailDAO;
import pojo.hibernate.ErpmIndentMaster;
import pojo.hibernate.ErpmIndentMasterDAO;

import pojo.hibernate.Departmentmaster;
import pojo.hibernate.DepartmentmasterDAO;

import pojo.hibernate.Subinstitutionmaster;
import pojo.hibernate.SubinstitutionmasterDAO;

import pojo.hibernate.Suppliermaster;
import pojo.hibernate.SuppliermasterDAO;

import pojo.hibernate.SupplierAddress;
import pojo.hibernate.SupplierAddressDAO;

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

import pojo.hibernate.ErpmItemRate;
import pojo.hibernate.ErpmItemRateDAO;

import pojo.hibernate.ErpmPoLocations;
import pojo.hibernate.ErpmPoLocationsDAO;

import utils.DevelopmentSupport;

import java.io.*;

import utils.DateUtilities;
import java.util.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.apache.struts2.interceptor.validation.SkipValidation;


import java.sql.Connection;
import javax.servlet.http.HttpServletResponse;
import java.sql.DriverManager;
import org.apache.struts2.ServletActionContext;
import net.sf.jasperreports.engine.*;
import pojo.hibernate.GfrProgramMappingDAO;

import java.util.Locale;
import java.util.ResourceBundle;
import com.opensymphony.xwork2.ActionContext;

public class ManagePOMaster extends DevelopmentSupport {

    private short indtindentid;
    private Integer irItemRateId;
    private short indtdetailId;
    private ErpmPoDetails podetail = new ErpmPoDetails();
    private ErpmPoDetails podetail1 = new ErpmPoDetails();
    private ErpmPoLocations poLocation = new ErpmPoLocations();
    private ErpmPoLocationsDAO poLocationDao = new ErpmPoLocationsDAO();
    private List<ErpmPoLocations> poLocationList = new ArrayList<ErpmPoLocations>();
    private ErpmGeneralTerms Gterms;
    private ErpmPoTerms poTerm = new ErpmPoTerms();
    private ErpmPoTerms poTerm1 = new ErpmPoTerms();
    private List<Departmentmaster> departmentList = new ArrayList<Departmentmaster>();
    private DepartmentmasterDAO departmentDao = new DepartmentmasterDAO();
    private ErpmGenMaster generalMaster = new ErpmGenMaster();
    private String message;
    private Integer defaultPOM;
    private Short defaultPOD;
    private Integer podPodetailsId;
    private Integer PoMasterId;
    private Integer pomPoMasterId;
    private Integer POD_POMaster_ID;
    private Erpmusers ermu;
    private Institutionmaster im;
    private String username;
    private Integer DefaultPO;
    private ErpmIndentMaster erpmindtmast;
    private ErpmIndentDetail erpmindtdet;
    private Short defaultIndent;
    private Integer DefaultCheckvalue;
    private ErpmItemMaster erpmim;
    private Integer ErpmimId;
    private List<String> transferToPOItems = new ArrayList<String>();
    private GfrProgramMappingDAO GfrProgramMappingDao = new GfrProgramMappingDAO();
    private ErpmPoMaster pomaster;
    private ErpmPoMasterDAO pomasterDao = new ErpmPoMasterDAO();
    private List<ErpmPoMaster> poMasterList = new ArrayList<ErpmPoMaster>();
    private ErpmPoDetailsDAO podetailDAO = new ErpmPoDetailsDAO();
    private List<ErpmIndentMaster> IndentIDList = new ArrayList<ErpmIndentMaster>();
    private ErpmIndentDetailDAO erpmindetDao = new ErpmIndentDetailDAO();
    private List<Institutionmaster> imList = new ArrayList<Institutionmaster>();
    private InstitutionmasterDAO imDao = new InstitutionmasterDAO();
    private List<Departmentmaster> dmList = new ArrayList<Departmentmaster>();
    private DepartmentmasterDAO dmDao = new DepartmentmasterDAO();
    private List<Subinstitutionmaster> simList = new ArrayList<Subinstitutionmaster>();
    private SubinstitutionmasterDAO simDao = new SubinstitutionmasterDAO();
    private List<Suppliermaster> suppList = new ArrayList<Suppliermaster>();
    private SuppliermasterDAO suppDao = new SuppliermasterDAO();
    private List<SupplierAddress> saList = new ArrayList<SupplierAddress>();
    private SupplierAddressDAO supplieraddressDao = new SupplierAddressDAO();
    private List<ErpmGenMaster> currencyList = new ArrayList<ErpmGenMaster>();
    private ErpmGenMasterDao generalMasterDao = new ErpmGenMasterDao();
    private List<ErpmGenMaster> paymodelist = new ArrayList<ErpmGenMaster>();
    private List<Erpmusers> erpmuserlist = new ArrayList<Erpmusers>();
    private ErpmusersDAO erpmusersDao = new ErpmusersDAO();
    // private List<ErpmItemMaster>          itemlist        =     new ArrayList<ErpmItemMaster>();
    private List<ErpmPoDetails> PODetailList = new ArrayList<ErpmPoDetails>();
    private List<ErpmGenMaster> poGenericTermsList = new ArrayList<ErpmGenMaster>();
    private ErpmPoTermsDAO poTermDAO = new ErpmPoTermsDAO();
    private List<ErpmPoTerms> epotermslist = new ArrayList<ErpmPoTerms>();
    private List<ErpmGeneralTerms> Gtermslist = new ArrayList<ErpmGeneralTerms>();
    private List<ErpmGeneralTerms> GTypestermslist = new ArrayList<ErpmGeneralTerms>();
    private List<ErpmPoTerms> poTermList = new ArrayList<ErpmPoTerms>();
    private List<ErpmIndentDetail> indtitemlist = new ArrayList<ErpmIndentDetail>();
    private List<ErpmIndentMaster> indentList = new ArrayList<ErpmIndentMaster>();
    private ErpmIndentMasterDAO indentListDao = new ErpmIndentMasterDAO();
    private List<ErpmIndentDetail> indentItemList = new ArrayList<ErpmIndentDetail>();
    private ErpmIndentDetail indentDetail = new ErpmIndentDetail();
    private List<ErpmItemMaster> itemList = new ArrayList<ErpmItemMaster>();
    private ErpmItemMasterDAO itemMasterDao = new ErpmItemMasterDAO();
    private List<ErpmItemRate> itemRateList = new ArrayList<ErpmItemRate>();
    private ErpmItemRateDAO itemRateDao = new ErpmItemRateDAO();
    private PrePurchaseDecorator ppDecorator = new PrePurchaseDecorator();
    private Integer poN;
    private Integer SMID;
    private String deliveryDate;
    private String poDate;
    private String poNumber;
    private String indentToDate;
    private String indentFromDate;
    private InputStream inputStream;
    private String indentDetailId;
    private Short indentSelected;
    private BigDecimal selectedItemRate;
    private String clause;
    private Integer poGenericTerm;
    private Short defaultInsitute;
    private Integer defaultSubInsitute;
    private Integer defaultDepartment;
    private Integer defaultCurrency;
    private Integer defaultItem;
    private String UOP;
    private String selectedItemRateCurrency;
    private String selectedItemRateValidFrom;
    private String selectedItemRateValidTo;
    private Integer minOrderQuantity;
    private Integer maxOrderQuantity;
    private String approxcost;
    private String taxNarration;
    private String taxValue;
    private String totalCost;
    private String editPODetail;
    private Integer potPotId;
    private Integer selectedTerm;
    private Integer poLocationsId;
    private static Boolean varShowGFR;

    public Boolean getVarShowGFR() {
        return varShowGFR;
    }

    public void setVarShowGFR(Boolean varShowGFR) {
        this.varShowGFR = varShowGFR;
    }

    public void setpoLocationsId(Integer poLocationsId) {
        this.poLocationsId = poLocationsId;
    }

    public Integer getpoLocationsId() {
        return this.poLocationsId;
    }

    public void setpoLocation(ErpmPoLocations poLocation) {
        this.poLocation = poLocation;
    }

    public ErpmPoLocations getpoLocation() {
        return this.poLocation;
    }

    public void setpoLocationList(List<ErpmPoLocations> poLocationList) {
        this.poLocationList = poLocationList;
    }

    public List<ErpmPoLocations> getpoLocationList() {
        return this.poLocationList;
    }

    public void setdepartmentList(List<Departmentmaster> departmentList) {
        this.departmentList = departmentList;
    }

    public List<Departmentmaster> getdepartmentList() {
        return this.departmentList;
    }

    public void setselectedTerm(Integer selectedTerm) {
        this.selectedTerm = selectedTerm;
    }

    public Integer getselectedTerm() {
        return this.selectedTerm;
    }

    public void seteditPODetail(String editPODetail) {
        this.editPODetail = editPODetail;
    }

    public String geteditPODetail() {
        return this.editPODetail;
    }

    public void settotalCost(String totalCost) {
        this.totalCost = totalCost;
    }

    public String gettotalCost() {
        return this.totalCost;
    }

    public void settaxNarration(String taxNarration) {
        this.taxNarration = taxNarration;
    }

    public String gettaxNarration() {
        return this.taxNarration;
    }

    public void settaxValue(String taxValue) {
        this.taxValue = taxValue;
    }

    public String gettaxValue() {
        return this.taxValue;
    }

    public void setapproxcost(String approxcost) {
        this.approxcost = approxcost;
    }

    public String getapproxcost() {
        return this.approxcost;
    }

    public void setminOrderQuantity(Integer minOrderQuantity) {
        this.minOrderQuantity = minOrderQuantity;
    }

    public Integer getminOrderQuantity() {
        return this.minOrderQuantity;
    }

    public void setmaxOrderQuantity(Integer maxOrderQuantity) {
        this.maxOrderQuantity = maxOrderQuantity;
    }

    public Integer getmaxOrderQuantity() {
        return this.maxOrderQuantity;
    }

    public void setselectedItemRateValidFrom(String selectedItemRateValidFrom) {
        this.selectedItemRateValidFrom = selectedItemRateValidFrom;
    }

    public String getselectedItemRateValidFrom() {
        return this.selectedItemRateValidFrom;
    }

    public void setselectedItemRateValidTo(String selectedItemRateValidTo) {
        this.selectedItemRateValidTo = selectedItemRateValidTo;
    }

    public String getselectedItemRateValidTo() {
        return this.selectedItemRateValidTo;
    }

    public void setselectedItemRateCurrency(String selectedItemRateCurrency) {
        this.selectedItemRateCurrency = selectedItemRateCurrency;
    }

    public String getselectedItemRateCurrency() {
        return this.selectedItemRateCurrency;
    }

    public void setUOP(String UOP) {
        this.UOP = UOP;
    }

    public String getUOP() {
        return this.UOP;
    }

    public void setdefaultItem(Integer defaultItem) {
        this.defaultItem = defaultItem;
    }

    public Integer getdefaultItem() {
        return this.defaultItem;
    }

    public void setpoGenericTerm(Integer poGenericTerm) {
        this.poGenericTerm = poGenericTerm;
    }

    public Integer getpoGenericTerm() {
        return this.poGenericTerm;
    }

    public void setclause(String clause) {
        this.clause = clause;
    }

    public String getclause() {
        return this.clause;
    }

    public void setselectedItemRate(BigDecimal selectedItemRate) {
        this.selectedItemRate = selectedItemRate;
    }

    public BigDecimal getselectedItemRate() {
        return this.selectedItemRate;
    }

    public void setitemRateList(List<ErpmItemRate> itemRateList) {
        this.itemRateList = itemRateList;
    }

    public List<ErpmItemRate> getitemRateList() {
        return this.itemRateList;
    }

    public void setitemList(List<ErpmItemMaster> itemList) {
        this.itemList = itemList;
    }

    public List<ErpmItemMaster> getitemList() {
        return this.itemList;
    }

    public Short getindentSelected() {
        return this.indentSelected;
    }

    public void setindentSelected(Short indentSelected) {
        this.indentSelected = indentSelected;
    }

    public Integer getpoN() {
        return this.poN;
    }

    public void setpoN(Integer poN) {
        this.poN = poN;
    }

    public List<String> gettransferToPOItems() {
        return this.transferToPOItems;
    }

    public void settransferToPOItems(List<String> transferToPOItems) {
        this.transferToPOItems = transferToPOItems;
    }

    public String getindentDetailId() {
        return this.indentDetailId;
    }

    public void setindentDetailId(String indentDetailId) {
        this.indentDetailId = indentDetailId;
    }

    public List<ErpmIndentDetail> getindentItemList() {
        return this.indentItemList;
    }

    public void setindentItemList(List<ErpmIndentDetail> indentItemList) {
        this.indentItemList = indentItemList;
    }

    public String getindentToDate() {
        return this.indentToDate;
    }

    public void setindentToDate(String indentToDate) {
        this.indentToDate = indentToDate;
    }

    public String getindentFromDate() {
        return this.indentFromDate;
    }

    public void setindentFromDate(String indentFromDate) {
        this.indentFromDate = indentFromDate;
    }

    public List<ErpmIndentMaster> getindentList() {
        return this.indentList;
    }

    public void setindentList(List<ErpmIndentMaster> indentList) {
        this.indentList = indentList;
    }

    public String getpoNumber() {
        return this.poNumber;
    }

    public void setpoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public String getpoDate() {
        return this.poDate;
    }

    public void setpoDate(String poDate) {
        this.poDate = poDate;
    }

    public String getdeliveryDate() {
        return this.deliveryDate;
    }

    public void setdeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public Integer getErpmimId() {
        return this.ErpmimId;
    }

    public void setErpmimId(Integer ErpmimId) {
        this.ErpmimId = ErpmimId;
    }

    public void seterpmim(ErpmItemMaster erpmim) {
        this.erpmim = erpmim;
    }

    public ErpmItemMaster geterpmim() {
        return this.erpmim;
    }

    public void seterpmindtdet(ErpmIndentDetail erpmindtdet) {
        this.erpmindtdet = erpmindtdet;
    }

    public ErpmIndentDetail geterpmindtdet() {
        return this.erpmindtdet;
    }

    public void setirItemRateId(Integer irItemRateId) {
        this.irItemRateId = irItemRateId;
    }

    public Integer getirItemRateId() {
        return irItemRateId;
    }

    public void setDefaultCheckvalue(Integer DefaultCheckvalue) {
        this.DefaultCheckvalue = DefaultCheckvalue;
    }

    public Integer getDefaultCheckvalue() {
        return DefaultCheckvalue;
    }

    public void setSMID(Integer SMID) {
        this.SMID = SMID;
    }

    public Integer getSMID() {
        return this.SMID;
    }

    public void setindtindentid(short indtindentid) {
        this.indtindentid = indtindentid;
    }

    public short getindtindentid() {
        return indtindentid;
    }

    public void setindtitemlist(List<ErpmIndentDetail> indtitemlist) {
        this.indtitemlist = indtitemlist;
    }

    public List<ErpmIndentDetail> getindtitemlist() {
        return this.indtitemlist;
    }

    public void setindtdetailId(short indtdetailId) {
        this.indtdetailId = indtdetailId;
    }

    public short getindtdetailId() {
        return indtdetailId;
    }

    public void seterpmindtmast(ErpmIndentMaster erpmindtmast) {
        this.erpmindtmast = erpmindtmast;
    }

    public ErpmIndentMaster geterpmindtmast() {
        return this.erpmindtmast;
    }

    public void setdefaultIndent(Short defaultIndent) {
        this.defaultIndent = defaultIndent;
    }

    public Short getdefaultIndent() {
        return this.defaultIndent;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public String getusername() {
        return username;
    }

    public void setPOD_POMaster_ID(Integer POD_POMaster_ID) {
        this.POD_POMaster_ID = POD_POMaster_ID;
    }

    public Integer getPOD_POMaster_ID() {
        return POD_POMaster_ID;
    }

    public void setpodPodetailsId(Integer podPodetailsId) {
        this.podPodetailsId = podPodetailsId;
    }

    public Integer getpodPodetailsId() {
        return podPodetailsId;
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

    public void setpotPotId(Integer potPotId) {
        this.potPotId = potPotId;
    }

    public Integer getpotPotId() {
        return potPotId;
    }

    public void setdefaultInsitute(short defaultInsitute) {
        this.defaultInsitute = defaultInsitute;
    }

    public short getdefaultInsitute() {
        return this.defaultInsitute;
    }

    public void setdefaultSubInsitute(Integer defaultSubInsitute) {
        this.defaultSubInsitute = defaultSubInsitute;
    }

    public Integer getdefaultSubInsitute() {
        return this.defaultSubInsitute;
    }

    public void setdefaultDepartment(Integer defaultDepartment) {
        this.defaultDepartment = defaultDepartment;
    }

    public Integer getdefaultDepartment() {
        return this.defaultDepartment;
    }

    public void setdefaultCurrency(Integer defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }

    public Integer getdefaultCurrency() {
        return this.defaultCurrency;
    }

    public void setPODetailList(List<ErpmPoDetails> PODetailList) {
        this.PODetailList = PODetailList;
    }

    public List<ErpmPoDetails> getPODetailList() {
        return this.PODetailList;
    }

    public void setpoTermList(List<ErpmPoTerms> poTermList) {
        this.poTermList = poTermList;
    }

    public List<ErpmPoTerms> getpoTermList() {
        return this.poTermList;
    }

    public void setepotermslist(List< ErpmPoTerms> epotermslist) {
        this.epotermslist = epotermslist;
    }

    public List<ErpmPoTerms> getepotermslist() {
        return this.epotermslist;
    }

    public void setIndentIDList(List<ErpmIndentMaster> IndentIDList) {
        this.IndentIDList = IndentIDList;
    }

    public List< ErpmIndentMaster> getIndentIDList() {
        return this.IndentIDList;
    }

    public void setGtermslist(List<ErpmGeneralTerms> Gtermslist) {
        this.Gtermslist = Gtermslist;
    }

    public List<ErpmGeneralTerms> getGtermslist() {
        return this.Gtermslist;
    }

    public void setGTypestermslist(List<ErpmGeneralTerms> GTypestermslist) {
        this.GTypestermslist = GTypestermslist;
    }

    public List<ErpmGeneralTerms> getGTypestermslist() {
        return this.GTypestermslist;
    }

    public void setdefaultPOM(Integer defaultPOM) {
        this.defaultPOM = defaultPOM;
    }

    public Integer getdefaultPOM() {
        return this.defaultPOM;
    }

    public void setdefaultPOD(Short defaultPOD) {
        this.defaultPOD = defaultPOD;
    }

    public Short getdefaultPOD() {
        return this.defaultPOD;
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

    public void setpoGenericTermsList(List<ErpmGenMaster> poGenericTermsList) {
        this.poGenericTermsList = poGenericTermsList;
    }

    public List<ErpmGenMaster> getpoGenericTermsList() {
        return this.poGenericTermsList;
    }

    public void seterpmuserlist(List<Erpmusers> erpmuserlist) {
        this.erpmuserlist = erpmuserlist;
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

    public void setpoTerm(ErpmPoTerms poTerm) {
        this.poTerm = poTerm;
    }

    public ErpmPoTerms getpoTerm() {
        return this.poTerm;
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

    public void setsaList(List<SupplierAddress> saList) {
        this.saList = saList;
    }

    public List<SupplierAddress> getsaList() {
        return this.saList;
    }

    public void setpoMasterList(List<ErpmPoMaster> poMasterList) {
        this.poMasterList = poMasterList;
    }

    public List<ErpmPoMaster> getpoMasterList() {
        return this.poMasterList;
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


            InitializeLOVs();

            //Set PO Date
            Date d = new Date();
            DateUtilities d1 = new DateUtilities();
            poDate = d1.convertDateToString(d, "dd-MM-yyyy");

            Short i = 20;
            Integer count = GfrProgramMappingDao.findCountByProgramId(i);


            if (count > 0) {
                setVarShowGFR(false);
            } else {
                setVarShowGFR(true);
            }
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in -> POMASTERAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String SavePOMaster() throws Exception {
        try {

            DateUtilities dt = new DateUtilities();
            Date dt1 = new Date();

            //Set PO Date, PO Delivery Date and PO User in the pomaster Object
            pomaster.setPomDeliveryDate(dt.convertStringToDate(getdeliveryDate()));
            pomaster.setPomPoDate(dt.convertStringToDate(getpoDate()));
            deliveryDate=dt.convertDateToString(dt.convertStringToDate(getdeliveryDate()),"dd-MM-yyyy");
                        poDate=dt.convertDateToString(dt.convertStringToDate(getpoDate()),"dd-MM-yyyy");

            ermu = erpmusersDao.findByUserId(Integer.valueOf(getSession().getAttribute("userid").toString()));
            pomaster.setErpmusersByPomUserId(ermu);

            if (pomaster.getPomPoMasterId() == null) {

                //Set the PO Number after fetching the last PO Number alloted to the department in the current year
                pomaster.setPomPoNo(pomasterDao.findlastPOForDeptInCurrentYear(pomaster.getDepartmentmaster().getDmId(), pomaster.getPomPoDate()));

                //Save the data into po master table
                pomasterDao.save(pomaster);
            } else {
                //Else Part when we click on edit button
                ErpmPoMaster pomaster1 = pomasterDao.findByPoMasterId(pomaster.getPomPoMasterId());
                poN = pomaster1.getPomPoNo();
                pomaster1 = pomaster;
                pomaster1.setPomPoNo(poN);
                pomasterDao.update(pomaster1);

                message = "Record Updated Successfully";
            }

            pomaster = pomasterDao.findBypomPoMasterId(pomaster.getPomPoMasterId());
            poNumber = dmDao.findDepartmentShortName(pomaster.getDepartmentmaster().getDmId()) + "/" + poDate.substring(6) + "/" + pomaster.getPomPoNo();

            indentToDate = dt.convertDateToString(dt1, "dd-MM-yyyy");
            indentFromDate = dt.convertDateToString(dt1, "01-01-2012");
            indentList = indentListDao.findApprovedIndents(indentFromDate, indentToDate,
                    Integer.parseInt(getSession().getAttribute("userid").toString()),
                    pomaster.getErpmGenMasterByPomCurrencyId().getErpmgmEgmDesc());

            poN = pomaster.getPomPoMasterId();
            PODetailList = podetailDAO.findBypomPoMasterId(getpoN());

            return SUCCESS;
        } catch (Exception e) {
            InitializeLOVs();
            message = message + "Exception in PO MASTER save method-> pomasterAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    @SkipValidation
    public String getIndentItems() throws Exception {
        try {
            indentItemList = erpmindetDao.findRemainingIndentItems(getindentSelected(), getpoN());

            indentList = indentListDao.findApprovedIndents(indentFromDate, indentToDate,
                    Integer.parseInt(getSession().getAttribute("userid").toString()),
                    pomaster.getErpmGenMasterByPomCurrencyId().getErpmgmEgmDesc());

            PODetailList = podetailDAO.findBypomPoMasterId(getpoN());

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in ManagePOMaster->getIndentItems " + e.getMessage() + " Reported Cause is : " + e.getCause();
            return ERROR;
        }
    }

    @SkipValidation
    public String transferToPo() throws Exception {
        try {

            //Transfer the Indent Detail Record (containing item) to the PO Detail record

            DateUtilities dt = new DateUtilities();

            //Setup Purchase Master Record
            ErpmPoMaster poMasterTemp = pomasterDao.findBypomPoMasterId(getpoN());

            indentList = indentListDao.findApprovedIndents(indentFromDate, indentToDate,
                    Integer.parseInt(getSession().getAttribute("userid").toString()),
                    poMasterTemp.getErpmGenMasterByPomCurrencyId().getErpmgmEgmDesc());

            //Set top Level entries
            poDate = dt.convertDateToString(poMasterTemp.getPomPoDate(), "dd-MM-yyyy");
            poNumber = dmDao.findDepartmentShortName(poMasterTemp.getDepartmentmaster().getDmId()) + "/" + dt.convertDateToString(poMasterTemp.getPomPoDate(), "dd-MM-yyyy").substring(6) + "/" + poMasterTemp.getPomPoNo();
            poN = poMasterTemp.getPomPoMasterId();

            //Setup  Indent Detail Record to be transferred to PO
            indentDetail = erpmindetDao.findByindtDetailByID(Short.parseShort(getindentDetailId()));

            //Check, if 1) There are approved rates for the item
            //          2) The approved rates are <= Accepted Unit Rates

            if (ppDecorator.getApprovedIndentItemRate(indentDetail).toString().contains("-1")) {
                message = "No rates stand approved for the Item today; Item cannot be moved to the Purchase Order";
            }  else if (ppDecorator.getApprovedIndentItemRate(indentDetail).compareTo(indentDetail.getIndtAcceptedUnitRate()) == 1) {
                message = "Approved rates for the item exceeds the accepted Indent rate; Item cannot be moved to the Purchase Order";
            } else {
                //Check, if the approved rate of the same supplier in whose name the PO is being prepared;
                //If Yes, Allow item to be added in the PO; otherwise restrict

                if (!ppDecorator.getapprovedIndentItemSupplier(indentDetail).contentEquals(poMasterTemp.getSuppliermaster().getSmName())) {
                    message = "The approved supplier and PO's Supplier do not match. Item cannot be moved to the Purchase Order";
                } else {
                    podetail.setErpmIndentDetail(indentDetail);
                    podetail.setErpmItemMaster(indentDetail.getErpmItemMaster());
                    podetail.setErpmPoMaster(pomasterDao.findByPoMasterId(getpoN()));
                    podetail.setPodQuantity(new BigDecimal(indentDetail.getIndtApprovedQuantity()));
                    podetail.setPodRate(indentDetail.getIndtAcceptedUnitRate());
                    podetailDAO.save(podetail);

                    message = message + "Item transferred to PO";
                }

                //Prepare List of PO Items
                PODetailList = podetailDAO.findBypomPoMasterId(getpoN());

                pomaster = pomasterDao.findBypomPoMasterId(getpoN());
            }
            
            return SUCCESS;

        } catch (Exception e) {
            if (e.getCause().toString().contains("Duplicate entry"))
                message = "The cannot enter same Item twice in the same PO.";
            else
                message = "Exception in ManagePOMaster -> transferToPo" + e.getMessage() + " Reported Cause is: " + e.getCause() + e.getStackTrace().toString() + poN;
            return ERROR;

        }
    }

    public String DeletePoDetails() throws Exception {
        try {

            DateUtilities dt = new DateUtilities();
            Date dt1 = new Date();

            podetail = podetailDAO.findByPODetailsID(getpodPodetailsId());
            poN = podetail.getErpmPoMaster().getPomPoMasterId();

            //Prepare fresh list of Indent Items
            if (podetail.getErpmIndentDetail() == null) {
                indentToDate = dt.convertDateToString(dt1, "dd-MM-yyyy");
                indentFromDate = dt.convertDateToString(dt1, "01-01-2012");
                //indentList = indentListDao.findApprovedIndents(indentFromDate, indentToDate, Integer.parseInt(getSession().getAttribute("userid").toString()));
            } else {
                indentItemList = erpmindetDao.findRemainingIndentItems(podetail.getErpmIndentDetail().getErpmIndentMaster().getIndtIndentId(),
                        poN);
            }
            //Set top Level entries
            pomaster = pomasterDao.findBypomPoMasterId(poN);
            poDate = dt.convertDateToString(pomaster.getPomPoDate(), "dd-MM-yyyy");
            poNumber = dmDao.findDepartmentShortName(pomaster.getDepartmentmaster().getDmId()) + "/" + dt.convertDateToString(pomaster.getPomPoDate(), "dd-MM-yyyy").substring(6) + "/" + pomaster.getPomPoNo();
            indentToDate = dt.convertDateToString(dt1, "dd-MM-yyyy");
            indentFromDate = dt.convertDateToString(dt1, "01-01-2012");

            //Delete record
            podetailDAO.delete(podetail);

            podetail = null;

            //Prepare List of Indents to be shown to the user
            indentList = indentListDao.findApprovedIndents(indentFromDate, indentToDate, Integer.parseInt(getSession().getAttribute("userid").toString()), pomaster.getErpmGenMasterByPomCurrencyId().getErpmgmEgmDesc());

            //Prepare List of PO Items
            PODetailList = podetailDAO.findBypomPoMasterId(getpoN());

            message = "Item restored to Indent successfully.";

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Delete method -> Delete PO Detail "+getpodPodetailsId() + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    @SkipValidation
    public String editPODetails() throws Exception {
//        try {

            DateUtilities dt = new DateUtilities();

            editPODetail = "ON";

            if (getpoN() == null) {
                podetail = podetailDAO.findByPODetailsID(getpodPodetailsId());
                poN = podetail.getErpmPoMaster().getPomPoMasterId();
            }

            //Prepare Item List
            itemList = itemMasterDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));

            pomaster = pomasterDao.findBypomPoMasterId(getpoN());
            podetail = podetailDAO.findByPODetailsID(getpodPodetailsId());

            poDate = dt.convertDateToString(pomaster.getPomPoDate(), "dd-MM-yyyy");
            poNumber = dmDao.findDepartmentShortName(pomaster.getDepartmentmaster().getDmId()) + "/" + dt.convertDateToString(pomaster.getPomPoDate(), "dd-MM-yyyy").substring(6) + "/" + pomaster.getPomPoNo();
            poN = pomaster.getPomPoMasterId();
            defaultItem = podetail.getErpmItemMaster().getErpmimId();
            UOP = podetail.getErpmItemMaster().getErpmGenMaster().getErpmgmEgmDesc();
            selectedItemRate = podetail.getPodRate();
            taxNarration = ppDecorator.findTaxesNarration(podetail);
            taxValue = ppDecorator.calulateTaxes(podetail).toString();
            approxcost = podetail.getPodQuantity().multiply(podetail.getPodRate()).setScale(2, RoundingMode.HALF_UP).toString();
            totalCost = podetail.getPodQuantity().multiply(podetail.getPodRate()).add(ppDecorator.calulateTaxes(podetail)).setScale(2, RoundingMode.HALF_UP).toString();

            //If Rates of Item have been enetered directly. i.e. they are not through Indent Execute If part Otherwise execute Else Part
            if (podetail.getErpmItemRate() != null) {
                selectedItemRateCurrency = podetail.getErpmItemRate().getErpmGenMasterByIrCurrencyId().getErpmgmEgmDesc();
                selectedItemRateValidFrom = dt.convertDateToString(podetail.getErpmItemRate().getIrdWefDate(), "dd-MM-yyyy");
                selectedItemRateValidTo = dt.convertDateToString(podetail.getErpmItemRate().getIrdWetDate(), "dd-MM-yyyy");
                minOrderQuantity = podetail.getErpmItemRate().getIrMinQty();
                maxOrderQuantity = podetail.getErpmItemRate().getIrMaxQty();

            } else {
                if (podetail.getErpmIndentDetail() != null) {
                    selectedItemRateCurrency = podetail.getErpmIndentDetail().getErpmItemRate().getErpmGenMasterByIrCurrencyId().getErpmgmEgmDesc();
                    selectedItemRateValidFrom = dt.convertDateToString(podetail.getErpmIndentDetail().getErpmItemRate().getIrdWefDate(), "dd-MM-yyyy");
                    selectedItemRateValidTo = dt.convertDateToString(podetail.getErpmIndentDetail().getErpmItemRate().getIrdWetDate(), "dd-MM-yyyy");
                    minOrderQuantity = podetail.getErpmIndentDetail().getErpmItemRate().getIrMinQty();
                    maxOrderQuantity = podetail.getErpmIndentDetail().getErpmItemRate().getIrMaxQty();
                }
            }
            //Set up Values on the Screen
            //Prepare List of Indents to be shown to the user
            indentList = indentListDao.findApprovedIndents(indentFromDate, indentToDate,
                    Integer.parseInt(getSession().getAttribute("userid").toString()),
                    pomaster.getErpmGenMasterByPomCurrencyId().getErpmgmEgmDesc());
            //Prepare List of PO Items
            PODetailList = podetailDAO.findBypomPoMasterId(getpoN());

            return SUCCESS;
//        } catch (Exception e) {
//            message = message + "Exception in ManagePOMaster -> editPODetails method " + e.getMessage() + " Reported Cause is: " + e.getCause();
//            return ERROR;
//        }
    }

    @SkipValidation
    public String prepareNonIndentedItemsForPO() throws Exception {
        try {

            String[] splittedPON;
            splittedPON = getpoNumber().toString().split("/");

            pomaster = pomasterDao.findByPONumber(splittedPON[0], Integer.parseInt(splittedPON[1]), Integer.parseInt(splittedPON[2]));
            poN = pomaster.getPomPoMasterId();

            //Prepare List of PO Items            
            PODetailList = podetailDAO.findBypomPoMasterId(getpoN());

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in ManagePOMasterAxn -> addNonIndentedItemsToPO" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    @SkipValidation
    public String prepareIndentedItemsForPO() throws Exception {
        try {

            DateUtilities dt = new DateUtilities();
            Date dt1 = new Date();

            String[] splittedPON;
            splittedPON = getpoNumber().toString().split("/");

            pomaster = pomasterDao.findByPONumber(splittedPON[0], Integer.parseInt(splittedPON[1]), Integer.parseInt(splittedPON[2]));
            poN = pomaster.getPomPoMasterId();

            //Prepare List of PO Items
            PODetailList = podetailDAO.findBypomPoMasterId(getpoN());

            indentToDate = dt.convertDateToString(dt1, "dd-MM-yyyy");
            indentFromDate = dt.convertDateToString(dt1, "01-01-2012");
            indentList = indentListDao.findApprovedIndents(indentFromDate, indentToDate,
                    Integer.parseInt(getSession().getAttribute("userid").toString()),
                    pomaster.getErpmGenMasterByPomCurrencyId().getErpmgmEgmDesc());


            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in ManagePOMasterAxn -> addNonIndentedItemsToPO" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    @SkipValidation
    public String prepareItemDeliveryLocationsForPO() throws Exception {
        try {

            String[] splittedPON;
            splittedPON = getpoNumber().toString().split("/");

            pomaster = pomasterDao.findByPONumber(splittedPON[0], Integer.parseInt(splittedPON[1]), Integer.parseInt(splittedPON[2]));
            poN = pomaster.getPomPoMasterId();

            //Prepare List of PO Items
            PODetailList = podetailDAO.findBypomPoMasterId(getpoN());

            //Prepare List of departments
            if (PODetailList.size() > 0) {
                departmentList = departmentDao.findByImId(PODetailList.get(0).getErpmPoMaster().getInstitutionmaster().getImId());
            } else {
                message = "No Items in PO. Please add Items";
            }

            //Prepare List of Locations
            poLocationList = poLocationDao.findByPO(getpoN());


            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in ManagePOMasterAxn -> addNonIndentedItemsToPO" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String saveLocationToPO() throws Exception {
        try {


            if (poLocation.getPoLocationsId() == null) {

                ErpmPoMaster pom = pomasterDao.findByPoMasterId(poN);
                poLocation.setErpmPoMaster(pom);

                poLocationDao.save(poLocation);

                message = "Location successfully added";
            } else {
                ErpmPoLocations poLocationTemp = poLocationDao.findBypoLocationsId(poLocation.getPoLocationsId());
                ErpmPoMaster pom = pomasterDao.findByPoMasterId(poN);
                poLocation.setErpmPoMaster(pom);
                poLocationTemp = poLocation;

                poLocationDao.update(poLocationTemp);

                message = "Location successfullu updated";
            }

            //Prepare List of PO Items
            PODetailList = podetailDAO.findBypomPoMasterId(getpoN());

            //Prepare List of POLOcations
            poLocationList = poLocationDao.findByPO(getpoN());

            if (PODetailList.size() > 0) {
                departmentList = departmentDao.findByImId(PODetailList.get(0).getErpmPoMaster().getInstitutionmaster().getImId());
            } else {
                message = "No Items in PO. Please add Items";
            }

            poLocation = null;

            return SUCCESS;
        } catch (Exception e) {
            if (e.getMessage().toString().contains("unsaved transient instance - save the transient instance before flushing: pojo.hibernate.Departmentmaster"))
                message = "Please select a Department before adding Location";
            else
                message = "Exception in ManagePOMasterAxn -> saveLocationToPO " + e.getMessage() + " Reported Cause is: " + e.getCause() + "Item Id is :" + poLocation.getErpmItemMaster().getErpmimId();
            return ERROR;
        }
    }

    public String deletePoLocation() throws Exception {
        try {

            DateUtilities dt = new DateUtilities();

            //Retrieve the term to be deleted
            poLocation = poLocationDao.findBypoLocationsId(poLocationsId);

            //Delete Record
            poLocationDao.delete(poLocation);
            poLocation = null;

            //Prepare List of PO Items
            PODetailList = podetailDAO.findBypomPoMasterId(getpoN());

            //Prepare List of POLOcations
            poLocationList = poLocationDao.findByPO(getpoN());

            if (PODetailList.size() > 0) {
                departmentList = departmentDao.findByImId(PODetailList.get(0).getErpmPoMaster().getInstitutionmaster().getImId());
            } else {
                message = "No Items in PO. Please add Items";
            }

            //Retrieve the record to be updated into pomaster object
            pomaster = pomasterDao.findByPoMasterId(getpoN());

            //Set PO Number
            poNumber = dmDao.findDepartmentShortName(pomaster.getDepartmentmaster().getDmId()) + "/" + dt.convertDateToString(pomaster.getPomPoDate(), "dd-MM-yyyy").substring(6) + "/" + pomaster.getPomPoNo();

            poDate = dt.convertDateToString(pomaster.getPomPoDate(), "dd-MM-yyyy");

            message = "Record successfully deleted";

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Delete method -> Delete PO Terms " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String editPoLocation() throws Exception {
        try {

            DateUtilities dt = new DateUtilities();

            //Retrieve the Location to be edited
            poLocation = poLocationDao.findBypoLocationsId(poLocationsId);

            //Prepare List of PO Items
            PODetailList = podetailDAO.findBypomPoMasterId(getpoN());

            if (PODetailList.size() > 0) {
                departmentList = departmentDao.findByImId(PODetailList.get(0).getErpmPoMaster().getInstitutionmaster().getImId());
            } else {
                message = "No Items in PO. Please add Items";
            }

            //Retrieve the record to be updated into pomaster object
            pomaster = pomasterDao.findByPoMasterId(getpoN());

            //Set PO Number
            poNumber = dmDao.findDepartmentShortName(pomaster.getDepartmentmaster().getDmId()) + "/" + dt.convertDateToString(pomaster.getPomPoDate(), "dd-MM-yyyy").substring(6) + "/" + pomaster.getPomPoNo();

            poDate = dt.convertDateToString(pomaster.getPomPoDate(), "dd-MM-yyyy");

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Edit method -> EditPoLocationAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String addNonIndentedItemsToPO() throws Exception {
        try {

            DateUtilities dt = new DateUtilities();
            String[] splittedPON;
            splittedPON = getpoNumber().toString().split("/");

            if (podetail.getPodPodetailsId() != null) {

                podetail1 = podetailDAO.findByPODetailsID(podetail.getPodPodetailsId());
                podetail.setErpmItemMaster(podetail1.getErpmItemMaster());
                podetail.setErpmPoMaster(podetail1.getErpmPoMaster());
                podetail.setPodRate(selectedItemRate);
                podetail1 = podetail;

                podetailDAO.update(podetail1);

                //Prepare List of PO Items
                PODetailList = podetailDAO.findBypomPoMasterId(podetail1.getErpmPoMaster().getPomPoMasterId());

                message = "Record successfully updated";
            } else {
                pomaster = pomasterDao.findByPONumber(splittedPON[0], Integer.parseInt(splittedPON[1]), Integer.parseInt(splittedPON[2]));
                podetail.setErpmPoMaster(pomaster);
                podetail.setPodRate(getselectedItemRate());
                //podetail.setErpmItemRate(itemRateDao.findByirItemRateId(getirItemRateId()));
                podetailDAO.save(podetail);

                //Prepare List of PO Items
                PODetailList = podetailDAO.findBypomPoMasterId(pomaster.getPomPoMasterId());

                message = "Record Saved Successfully";
            }


            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in ManagePOMasterAxn -> addNonIndentedItemsToPO" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    @SkipValidation
    public String prepareTermsForPO() throws Exception {
        try {
            String[] splittedPON;
            splittedPON = getpoNumber().toString().split("/");

            pomaster = pomasterDao.findByPONumber(splittedPON[0], Integer.parseInt(splittedPON[1]), Integer.parseInt(splittedPON[2]));
            poN = pomaster.getPomPoMasterId();

            //Prepare List of PO Items
            PODetailList = podetailDAO.findBypomPoMasterId(getpoN());

            //Prepare List of Generic Terms
            poGenericTermsList = generalMasterDao.findByErpmGmType(Short.parseShort("12"));

            //Prepare List of Terms for the PO
            poTermList = poTermDAO.findByPOMasterId(getpoN());

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in ManagePOMasterAxn -> addNonIndentedItemsToPO" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    @SkipValidation
    public String saveTermToPO() throws Exception {
        try {

            String[] splittedPON;
            splittedPON = getpoNumber().toString().split("/");

            pomaster = pomasterDao.findByPONumber(splittedPON[0], Integer.parseInt(splittedPON[1]), Integer.parseInt(splittedPON[2]));

            if (poTerm.getPotPotId() == null) {
                //Set the Generic Term no in the Field of the Term Table
                if (getpoGenericTerm() != 0 && getpoGenericTerm().toString().length() != 0) {
                    generalMaster = generalMasterDao.findByErpmGmId(getpoGenericTerm());
                    poTerm.setErpmGenMaster(generalMaster);
                }

                //Save Entries in terms Table if the Clause text is present
                if (getclause().length() > 0) {
                    poTerm.setErpmPoMaster(pomaster);
                    poTerm.setPotTermsDescription(getclause());
                    poTermDAO.save(poTerm);

                }
                message = "Record Saved Successfully";
            } else {

                poTerm1 = poTermDAO.findBypotPotIds(poTerm.getPotPotId());
                poTerm1.setPotTermsDescription(getclause());
                poTermDAO.update(poTerm1);

                clause = "";
                poTerm = null;

                message = "Record Updated Successfully";
            }
            //Prepare List of PO Items
            PODetailList = podetailDAO.findBypomPoMasterId(pomaster.getPomPoMasterId());

            //Prepare List of generic terms
            poGenericTermsList = generalMasterDao.findByErpmGmType(Short.parseShort("12"));

            //Prepare List of Terms for the PO
            poTermList = poTermDAO.findByPOMasterId(pomaster.getPomPoMasterId());

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in ManagePOMasterAxn -> saveTermToPO" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String editPoTerm() throws Exception {
        try {

            DateUtilities dt = new DateUtilities();

            //Retrieve the term to be edited
            poTerm = poTermDAO.findBypotPotIds(getpotPotId());
            clause = poTerm.getPotTermsDescription();

            //Prepare List of PO Items
            PODetailList = podetailDAO.findBypomPoMasterId(getpoN());

            //Prepare List of Generic Terms
            poGenericTermsList = generalMasterDao.findByErpmGmType(Short.parseShort("12"));
            selectedTerm = poTerm.getErpmGenMaster().getErpmgmEgmId();

            //Retrieve the record to be updated into pomaster object
//            pomaster = pomasterDao.findByPoMasterId(getpoN());
            pomaster = pomasterDao.findByPoMasterId(poTerm.getErpmPoMaster().getPomPoMasterId());
            
            //Set PO Number
            poNumber = dmDao.findDepartmentShortName(pomaster.getDepartmentmaster().getDmId()) + "/" + dt.convertDateToString(pomaster.getPomPoDate(), "dd-MM-yyyy").substring(6) + "/" + pomaster.getPomPoNo();

            //Set PO Delivery Date
            deliveryDate = dt.convertDateToString(pomaster.getPomDeliveryDate(), "dd-MM-yyyy");
            poDate = dt.convertDateToString(pomaster.getPomPoDate(), "dd-MM-yyyy");

            InitializeLOVs();

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Edit method -> EditPo Terms TaxesAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String deletePoTerm() throws Exception {
        try {

            DateUtilities dt = new DateUtilities();

            //Retrieve the term to be edited
            poTerm = poTermDAO.findBypotPotIds(getpotPotId());

            //Delete Record
            poTermDAO.delete(poTerm);
            poTerm = null;

            //Prepare List of PO Items
            PODetailList = podetailDAO.findBypomPoMasterId(getpoN());

            //Prepare List of Terms for the PO
            poTermList = poTermDAO.findByPOMasterId(getpoN());

            //Prepare List of Generic Terms
            poGenericTermsList = generalMasterDao.findByErpmGmType(Short.parseShort("12"));

            //Retrieve the record to be updated into pomaster object
            pomaster = pomasterDao.findByPoMasterId(getpoN());

            //Set PO Number
            poNumber = dmDao.findDepartmentShortName(pomaster.getDepartmentmaster().getDmId()) + "/" + dt.convertDateToString(pomaster.getPomPoDate(), "dd-MM-yyyy").substring(6) + "/" + pomaster.getPomPoNo();

            //Set PO Delivery Date
            deliveryDate = dt.convertDateToString(pomaster.getPomDeliveryDate(), "dd-MM-yyyy");
            poDate = dt.convertDateToString(pomaster.getPomPoDate(), "dd-MM-yyyy");

            InitializeLOVs();

            message = "Record successfully deleted";

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Delete method -> Delete PO Terms " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    @SkipValidation
    public String browsePOs() throws Exception {
        try {

            //Prepare List of Purchase Orders for the User Departments
            poMasterList = pomasterDao.findPOForUserDepartments(Integer.parseInt(getSession().getAttribute("userid").toString()));

            return SUCCESS;

        } catch (Exception e) {
            message = "Exception in MangesupplierAxn -> browsePO method -> " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String preparePOEdit() throws Exception {
        try {
            DateUtilities dt = new DateUtilities();

            //Retrieve the record to be updated into pomaster object
            pomaster = pomasterDao.findByPoMasterId(getPoMasterId());

            //Set PO Number
            poNumber = dmDao.findDepartmentShortName(pomaster.getDepartmentmaster().getDmId()) + "/" + dt.convertDateToString(pomaster.getPomPoDate(), "dd-MM-yyyy").substring(6) + "/" + pomaster.getPomPoNo();

            //Set PO Delivery Date
            deliveryDate = dt.convertDateToString(pomaster.getPomDeliveryDate(), "dd-MM-yyyy");
            poDate = dt.convertDateToString(pomaster.getPomPoDate(), "dd-MM-yyyy");

            InitializeLOVs();

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in ManageIndentMasterAxn -> preparePOEdit " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public void InitializeLOVs() {
        imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
        simList = simDao.findSubInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Short.valueOf(getSession().getAttribute("imId").toString()));
        dmList = dmDao.findDepartmentForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Integer.valueOf(getSession().getAttribute("simId").toString()));
        suppList = suppDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
        saList = supplieraddressDao.findAll();
        currencyList = generalMasterDao.findByErpmGmType(Short.parseShort("6"));
        paymodelist = generalMasterDao.findByErpmGmType(Short.parseShort("10"));
        erpmuserlist = erpmusersDao.findUserCollegues(Integer.valueOf(getSession().getAttribute("userid").toString()));
        //itemlist=itemlistDAO.findByImId(defaultInsitute);

        //these four line gives Default Insitute,Subinsitute,Department(For which Users have current Profile) and Currency(Rupees)
        if (pomaster != null) {
            defaultInsitute = pomaster.getInstitutionmaster().getImId();
            defaultSubInsitute = pomaster.getSubinstitutionmaster().getSimId();
            defaultDepartment = pomaster.getDepartmentmaster().getDmId();
            defaultCurrency = pomaster.getErpmGenMasterByPomCurrencyId().getErpmgmEgmId();
        } else {
            defaultInsitute = Short.valueOf(getSession().getAttribute("imId").toString());
            defaultSubInsitute = Integer.valueOf(getSession().getAttribute("simId").toString());
            defaultDepartment = Integer.valueOf(getSession().getAttribute("dmId").toString());
            defaultCurrency = generalMasterDao.findDefaultCurrency("Rupees");
        }
    }

    public void validate() {

        try {

            //If part Validates First Stage of Purchase Order Generation
            if (pomaster.getPomPoMasterId() == null && podetail == null) {

                if (pomaster.getInstitutionmaster().getImId() == null) {
                    addFieldError("pomaster.institutionmaster.imId", "Please select institution from the list");
                }

                if (pomaster.getSubinstitutionmaster().getSimId() == null) {
                    addFieldError("pomaster.subinstitutionmaster.simId", "Please select Subinsitute from the list");
                }

                if (pomaster.getDepartmentmaster().getDmId() == null) {
                    addFieldError("pomaster.departmentmaster.dmId", "Please select Department from the list");
                }

                if (pomaster.getSuppliermaster().getSmId() == null) {
                    addFieldError("pomaster.suppliermaster.smId", "Please select Supplier From The List");
                }

                if (pomaster.getErpmGenMasterByPomCurrencyId() == null) {
                    addFieldError("pomaster.erpmGenMasterByPomCurrencyId", "Please select Currency From The List");
                }

                if (pomaster.getErpmGenMasterByPomPaymentModeId().getErpmgmEgmId() == null) {
                    addFieldError("pomaster.erpmGenMasterByPomPaymentModeId.erpmgmEgmId", "Please Select Payment Mode from The List");
                }

                if (pomaster.getErpmusersByPomApprovedById().getErpmuId() == null) {
                    addFieldError("pomaster.erpmusersByPomApprovedById.erpmuId", "Please select Approved from the list");
                }

                if (getdeliveryDate().length() == 0) {
                    addFieldError("deliveryDate", "Please enter Delivery Date");
                } else {
                    DateUtilities dt = new DateUtilities();
                    if (dt.isValidDate(getdeliveryDate()) == false) {
                        addFieldError("deliveryDate", "Please enter valid Delivery Date [dd-mm-yyyy]");
                    }
                }

                if (getpoDate().length() == 0) {
                    addFieldError("poDate", "Please enter PO Date");
                } else {
                    DateUtilities dt = new DateUtilities();
                    if (dt.isValidDate(getpoDate()) == false) {
                        addFieldError("poDate", "Please enter valid PO Date [dd-mm-yyyy]");
                    }
                }


                InitializeLOVs();
            } else if (podetail.getPodPodetailsId() != null) {

                if (podetail.getErpmItemRate().getIrItemRateId() == null) {
                    addFieldError("podetail.erpmItemRate.irItemRateId", "Please provide value for Item Rate");
                    itemList = itemMasterDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
                    podetail1 = podetailDAO.findByPODetailsID(podetail.getPodPodetailsId());
                    defaultItem = podetail1.getErpmItemMaster().getErpmimId();
                    editPODetail = "ON";
                    PODetailList = podetailDAO.findBypomPoMasterId(getpoN());
                }

            }
        } catch (NullPointerException e) {
            //message = e.getMessage() + e.getStackTrace();
        }
    }

    @SkipValidation
    public String PrintPO() throws Exception {
        HashMap hm = new HashMap();

        // Get System properties
        Properties properties = System.getProperties();

        // Get the path separator symbol, which is unfortunatly different, in different OS platform.
        String pathSeparator = properties.getProperty("file.separator");

        pathSeparator =pathSeparator + pathSeparator;
        String repPath = "pico" + pathSeparator + "PrePurchase"  + pathSeparator + "Reports" + pathSeparator + "Purchase_Order.jasper" ;

        String fileName = getSession().getServletContext().getRealPath(repPath);
//        String fileName = getSession().getServletContext().getRealPath("pico\\PrePurchase\\Reports\\Purchase_Order.jasper");


        String whereCondition = "";

        try {
            Locale locale = ActionContext.getContext().getLocale();
            ResourceBundle bundle = ResourceBundle.getBundle("pico", locale);
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bundle.getString("dbName"), bundle.getString("mysqlUserName"), bundle.getString("mysqlPassword"));

            HttpServletResponse response = ServletActionContext.getResponse();
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Content-Disposition", "attachment; filename=Purchase_Order.pdf");
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            //Setup Where Condition Clause
            if (pomaster.getPomPoMasterId() == null) {
                whereCondition = "erpm_po_master.`POM_PO_Master_ID` = " + getpoN();
            } else {
                whereCondition = "erpm_po_master.`POM_PO_Master_ID` = " + pomaster.getPomPoMasterId();
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
            Locale locale = ActionContext.getContext().getLocale();
            ResourceBundle bundle = ResourceBundle.getBundle("pico", locale);
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bundle.getString("dbName"), bundle.getString("mysqlUserName"), bundle.getString("mysqlPassword")); 

            HttpServletResponse response = ServletActionContext.getResponse();
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Content-Disposition", "attachment; filename=ShowGfr.pdf");
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();


            whereCondition = "gfr_program_mapping.`GPM_Program_ID` = 20";

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
}
