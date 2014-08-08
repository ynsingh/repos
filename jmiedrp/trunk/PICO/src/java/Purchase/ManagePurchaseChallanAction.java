/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * @author Tanvir Ahmed and Manauwar Alam
 */
package Purchase;

import java.io.*;
import java.math.BigDecimal;
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

public class ManagePurchaseChallanAction extends DevelopmentSupport {

    private ErpmPurchasechallanMaster PChallanMast;
    private ErpmPurchasechallanSerial PCSerial;
    private ViewStockReceived vsr = new ViewStockReceived();
    private ErpmStockReceivedDAO stockRecDao = new ErpmStockReceivedDAO();
    private ErpmPurchaseChallanDetailDAO PCDetailDAO = new ErpmPurchaseChallanDetailDAO();
    private ErpmPurchasechallanSerialDAO PCSerialDAO = new ErpmPurchasechallanSerialDAO();
    private ErpmStockReceived esr;
    private ViewStockReceivedDAO viewStockRecDao = new ViewStockReceivedDAO();
    private ErpmIssueSerialDetail eisd = new ErpmIssueSerialDetail();
    private ErpmIssueSerialDetailDAO eisdDao = new ErpmIssueSerialDetailDAO();
    private ErpmPurchasechallanDetail PCDetail;
    private List<ErpmPurchasechallanDetail> PCDetailslist = new ArrayList<ErpmPurchasechallanDetail>();
    private List<ErpmPurchasechallanDetail> PCDetOnItemPOlist = new ArrayList<ErpmPurchasechallanDetail>();
    private List<String> PChallanMastList = new ArrayList<String>();
    private ErpmPurchaseChallanMasterDAO PChallanMastDao = new ErpmPurchaseChallanMasterDAO();
    private List<Institutionmaster> imList = new ArrayList<Institutionmaster>();
    private InstitutionmasterDAO imDao = new InstitutionmasterDAO();
    private List<Subinstitutionmaster> simList = new ArrayList<Subinstitutionmaster>();
    private SubinstitutionmasterDAO simDao = new SubinstitutionmasterDAO();
    private List<Departmentmaster> dmList = new ArrayList<Departmentmaster>();
    private DepartmentmasterDAO dmDao = new DepartmentmasterDAO();
    private List<Erpmusers> erpmuserlist = new ArrayList<Erpmusers>();
    private ErpmusersDAO erpmusersDao = new ErpmusersDAO();
    private ErpmPoDetails PODetail;
    private List<ErpmPoDetails> poditemlist = new ArrayList<ErpmPoDetails>();
    private ErpmPoDetailsDAO PODetailDAO = new ErpmPoDetailsDAO();
    private List<String> POMasterList = new ArrayList<String>();
    private ErpmPoMasterDAO pomasterDAO = new ErpmPoMasterDAO();
    private List<ErpmStockReceived> stockRecList = new ArrayList<ErpmStockReceived>();
    private List<ViewStockReceived> viewStockRecList = new ArrayList<ViewStockReceived>();
    private SuppliermasterDAO supDao = new SuppliermasterDAO();
    private List<Suppliermaster> supList = new ArrayList<Suppliermaster>();
    private List<ErpmItemCategoryMaster> erpmIcmList1 = new ArrayList<ErpmItemCategoryMaster>();
    private List<ErpmItemCategoryMaster> erpmIcmList2 = new ArrayList<ErpmItemCategoryMaster>();
    private List<ErpmItemCategoryMaster> erpmIcmList3 = new ArrayList<ErpmItemCategoryMaster>();
    private ErpmItemCategoryMasterDao erpmIcmDao = new ErpmItemCategoryMasterDao();
    private ErpmItemMasterDAO itemDao = new ErpmItemMasterDAO();
    private GfrProgramMappingDAO GfrProgramMappingDao = new GfrProgramMappingDAO();
    private List<ErpmGenMaster> tosWTList = new ArrayList<ErpmGenMaster>();
    private ErpmGenMasterDao egmDAO = new ErpmGenMasterDao();
    private InputStream inputStream;
    Erpmusers ermu;
    private String username;
    private Short DefaultInsitute;
    private Integer DefaultSubInsitute;
    private Integer DefaultDepartment;
    private String message;
    private Integer PCMPCMID;
    private Integer defaultPCM;
    private Integer defaultPCD;
    private Integer purchaseOrderNo;
    private Integer PCID;
    private String institutionName;
    private String subInstitutionName;
    private String departmentName;
    private String recieveDate;
    private String challanDate;
    private Integer VSRID;
    private String productNo;
    private static String itemName;
    private Boolean PrdNoReadOnly = true;
    private Boolean PrdNoReadOnly1 = true;
    private static Integer PurchaseChallanDetailId;
    private String serialNoFull;
    private Integer PCDETID;
    private Boolean checked;
    private Boolean verified;
    private String CheckAndVerifiedRemarks;
    private Boolean PcdDisable;
    private Boolean btnCheckAndVerifySave = true;
    private static Boolean varShowGFR;
    private Short l = 18;
    private Integer WarrantyType;
    private String WarrantyExpiryDate;

    static String dataSourceURL=null;

    public Boolean getVarShowGFR() {
        return varShowGFR;
    }

    public void setVarShowGFR(Boolean varShowGFR) {
        ManagePurchaseChallanAction.varShowGFR = varShowGFR;
    }

    public Boolean getPrdNoReadOnly1() {
        return PrdNoReadOnly1;
    }

    public void setPrdNoReadOnly1(Boolean PrdNoReadOnly1) {
        this.PrdNoReadOnly1 = PrdNoReadOnly1;
    }

    public Integer getWarrantyType() {
        return WarrantyType;
    }

    public void setWarrantyType(Integer WarrantyType) {
        this.WarrantyType = WarrantyType;
    }

    public String getWarrantyExpiryDate() {
        return WarrantyExpiryDate;
    }

    public void setWarrantyExpiryDate(String WarrantyExpiryDate) {
        this.WarrantyExpiryDate = WarrantyExpiryDate;
    }

    public Short getL() {
        return l;
    }

    public void setL(Short l) {
        this.l = l;
    }

    public List<ErpmGenMaster> getTosWTList() {
        return tosWTList;
    }

    public void setTosWTList(List<ErpmGenMaster> tosWTList) {
        this.tosWTList = tosWTList;
    }

    public List<Suppliermaster> getSupList() {
        return supList;
    }

    public void setSupList(List<Suppliermaster> supList) {
        this.supList = supList;
    }

    public Boolean getBtnCheckAndVerifySave() {
        return btnCheckAndVerifySave;
    }

    public void setBtnCheckAndVerifySave(Boolean btnCheckAndVerifySave) {
        this.btnCheckAndVerifySave = btnCheckAndVerifySave;
    }

    public Boolean getPcdDisable() {
        return PcdDisable;
    }

    public void setPcdDisable(Boolean PcdDisable) {
        this.PcdDisable = PcdDisable;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public String getCheckAndVerifiedRemarks() {
        return CheckAndVerifiedRemarks;
    }

    public void setCheckAndVerifiedRemarks(String CheckAndVerifiedRemarks) {
        this.CheckAndVerifiedRemarks = CheckAndVerifiedRemarks;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
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

    public Integer getVSRID() {
        return VSRID;
    }

    public void setVSRID(Integer VSRID) {
        this.VSRID = VSRID;
    }

    public Integer getPCDETID() {
        return PCDETID;
    }

    public void setPCDETID(Integer PCDETID) {
        this.PCDETID = PCDETID;
    }

    public ErpmIssueSerialDetail getEisd() {
        return eisd;
    }

    public void setEisd(ErpmIssueSerialDetail eisd) {
        this.eisd = eisd;
    }

    public ViewStockReceived getVsr() {
        return vsr;
    }

    public void setVsr(ViewStockReceived vsr) {
        this.vsr = vsr;
    }

    public List<ViewStockReceived> getViewStockRecList() {
        return viewStockRecList;
    }

    public void setViewStockRecList(List<ViewStockReceived> viewStockRecList) {
        this.viewStockRecList = viewStockRecList;
    }

    public String getSerialNoFull() {
        return serialNoFull;
    }

    public void setSerialNoFull(String serialNoFull) {
        this.serialNoFull = serialNoFull;
    }

    public static Integer getPurchaseChallanDetailId() {
        return PurchaseChallanDetailId;
    }

    public static void setPurchaseChallanDetailId(Integer PurchaseChallanDetailId) {
        ManagePurchaseChallanAction.PurchaseChallanDetailId = PurchaseChallanDetailId;
    }

    public Boolean getPrdNoReadOnly() {
        return PrdNoReadOnly;
    }

    public void setPrdNoReadOnly(Boolean PrdNoReadOnly) {
        this.PrdNoReadOnly = PrdNoReadOnly;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        ManagePurchaseChallanAction.itemName = itemName;
    }

    public ErpmStockReceived getEsr() {
        return esr;
    }

    public void setEsr(ErpmStockReceived esr) {
        this.esr = esr;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public Integer getPCID() {
        return PCID;
    }

    public void setPCID(Integer PCID) {
        this.PCID = PCID;
    }

    public String getChallanDate() {
        return challanDate;
    }

    public void setChallanDate(String challanDate) {
        this.challanDate = challanDate;
    }

    public String getRecieveDate() {
        return recieveDate;
    }

    public void setRecieveDate(String recieveDate) {
        this.recieveDate = recieveDate;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getSubInstitutionName() {
        return subInstitutionName;
    }

    public void setSubInstitutionName(String subInstitutionName) {
        this.subInstitutionName = subInstitutionName;
    }

    public void setPCDetailslist(List<ErpmPurchasechallanDetail> PCDetailslist) {
        this.PCDetailslist = PCDetailslist;
    }

    public List<ErpmPurchasechallanDetail> getPCDetailslist() {
        return this.PCDetailslist;
    }

    public List<ErpmPurchasechallanDetail> getPCDetOnItemPOlist() {
        return PCDetOnItemPOlist;
    }

    public void setPCDetOnItemPOlist(List<ErpmPurchasechallanDetail> PCDetOnItemPOlist) {
        this.PCDetOnItemPOlist = PCDetOnItemPOlist;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getPurchaseOrderNo() {
        return purchaseOrderNo;
    }

    public void setPurchaseOrderNo(Integer purchaseOrderNo) {
        this.purchaseOrderNo = purchaseOrderNo;
    }

    public void setusername(String username) {
        this.username = username;
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

    public void setPCSerial(pojo.hibernate.ErpmPurchasechallanSerial PCSerial) {
        this.PCSerial = PCSerial;
    }

    public pojo.hibernate.ErpmPurchasechallanSerial getPCSerial() {
        return this.PCSerial;
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

    public List<String> getPOMasterList() {
        return POMasterList;
    }

    public void setPOMasterList(List<String> POMasterList) {
        this.POMasterList = POMasterList;
    }

    public List<Erpmusers> geterpmuserlist() {
        return erpmuserlist;
    }

    public void seterpmuserlist(List<Erpmusers> erpmuserlist) {
        this.erpmuserlist = erpmuserlist;
    }

    public List<String> getPChallanMastList() {
        return PChallanMastList;
    }

    public void setPChallanMastList(List<String> PChallanMastList) {
        this.PChallanMastList = PChallanMastList;
    }

    public void setDefaultInsitute(Short DefaultInsitute) {
        this.DefaultInsitute = DefaultInsitute;
    }

    public Short getDefaultInsitute() {
        return this.DefaultInsitute;
    }

    public void setDefaultSubInsitute(Integer DefaultSubInsitute) {
        this.DefaultSubInsitute = DefaultSubInsitute;
    }

    public Integer getDefaultSubInsitute() {
        return this.DefaultSubInsitute;
    }

    public void setDefaultDepartment(Integer DefaultDepartment) {
        this.DefaultDepartment = DefaultDepartment;
    }

    public Integer getDefaultDepartment() {
        return this.DefaultDepartment;
    }

    public void setdefaultPCD(Integer defaultPCD) {
        this.defaultPCD = defaultPCD;
    }

    public Integer getdefaultPCD() {
        return this.defaultPCD;
    }

    public void setdefaultPCM(Integer defaultPCM) {
        this.defaultPCM = defaultPCM;
    }

    public Integer getdefaultPCM() {
        return this.defaultPCM;
    }

    public List<ErpmPoDetails> getPoditemlist() {
        return poditemlist;
    }

    public void setPoditemlist(List<ErpmPoDetails> poditemlist) {
        this.poditemlist = poditemlist;
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

    public List<ErpmStockReceived> getStockRecList() {
        return stockRecList;
    }

    public void setStockRecList(List<ErpmStockReceived> stockRecList) {
        this.stockRecList = stockRecList;
    }

    @Override
    public String execute() throws Exception {

        try {
            PChallanMast = null;
            InitializeLOVs();
            //These 3 lines fetch default Insitute,Subinstitute,Department(from user's profile)
            DefaultInsitute = Short.valueOf(getSession().getAttribute("imId").toString());
            DefaultSubInsitute = Integer.valueOf(getSession().getAttribute("simId").toString());
            DefaultDepartment = Integer.valueOf(getSession().getAttribute("dmId").toString());
            Date d = new Date();
            DateUtilities d1 = new DateUtilities();
            Short i = 21;
            Integer count = GfrProgramMappingDao.findCountByProgramId(i);

            if (count > 0) {
                setVarShowGFR(false);
            } else {
                setVarShowGFR(true);
            }
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Execute method -> ManagePurchaseChallanAxn" + e.getMessage() + e.getCause();
            return ERROR;
        }
    }

    public void InitializeLOVs() {
        imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
        if (getSession().getAttribute("isAdministrator").toString().compareTo("Administrator") == 0) {
            simList = simDao.findSubInstForAdmin(Short.valueOf(getSession().getAttribute("imId").toString()));
        } else {
            simList = simDao.findSubInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Short.valueOf(getSession().getAttribute("imId").toString()));
        }
        if (getSession().getAttribute("isAdministrator").toString().compareTo("Administrator") == 0) {
            dmList = dmDao.findBydmSimId(Integer.valueOf(getSession().getAttribute("simId").toString()));
        } else {
            dmList = dmDao.findDepartmentForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Integer.valueOf(getSession().getAttribute("simId").toString()));
        }
        POMasterList = pomasterDAO.poList(Short.valueOf(getSession().getAttribute("imId").toString()));
    }

    public String SavePurchaseChallan() throws Exception {
        try {
            username = getSession().getAttribute("userfullname").toString();
            //---------------for getting Login user name.---------------
            ermu = erpmusersDao.findByUserFullNames(username);
            PChallanMast.setErpmusers(ermu);
            //If part saves record for the first time; else parts is for record update
            if (PChallanMast.getPcmPcmId() == null) {

                DateUtilities dt = new DateUtilities();

                PChallanMast.setPcmChallanDate(dt.convertStringToDate(getChallanDate()));
                PChallanMast.setPcmRecvDate(dt.convertStringToDate(getRecieveDate()));
                PChallanMastDao.save(PChallanMast);
                //for getting the default POM on detail id

                defaultPCM = PChallanMast.getPcmPcmId();
                getSession().setAttribute(" ", defaultPCM);
                //for set the all field of PO Master Screen in add details screen
                PChallanMast = PChallanMastDao.findBypcmPcmId(defaultPCM);

                //for PO no. to be displayed on purchase challan detail jsp
                purchaseOrderNo = PChallanMast.getErpmPoMaster().getPomPoNo();
                //for getting all items for their insitute
                DefaultInsitute = PChallanMast.getInstitutionmaster().getImId();
                poditemlist = PODetailDAO.findItemListByPoMasterId(PChallanMast.getErpmPoMaster().getPomPoMasterId());

                institutionName = PChallanMast.getInstitutionmaster().getImName();
                subInstitutionName = PChallanMast.getSubinstitutionmaster().getSimName();
                departmentName = PChallanMast.getDepartmentmaster().getDmName();
                message = "Purchase Challan record saved successfully";
            } else {
                DateUtilities dt = new DateUtilities();
                ErpmPurchasechallanMaster pchallanMast1 = PChallanMastDao.findBypcmPcmId(PChallanMast.getPcmPcmId());
                purchaseOrderNo = pchallanMast1.getErpmPoMaster().getPomPoNo();
                poditemlist = PODetailDAO.findItemListByPoMasterId(pchallanMast1.getErpmPoMaster().getPomPoMasterId());
                PCDetailslist = PCDetailDAO.findBypcmPcmId(pchallanMast1.getPcmPcmId());
                PChallanMast.setPcmChallanDate(dt.convertStringToDate(getChallanDate()));
                PChallanMast.setPcmRecvDate(dt.convertStringToDate(getRecieveDate()));
                pchallanMast1 = PChallanMast;

                PChallanMastDao.update(pchallanMast1);

                pchallanMast1 = PChallanMastDao.findBypcmPcmId(PChallanMast.getPcmPcmId());
                institutionName = pchallanMast1.getInstitutionmaster().getImName();
                subInstitutionName = pchallanMast1.getSubinstitutionmaster().getSimName();
                departmentName = pchallanMast1.getDepartmentmaster().getDmName();

                message = "Purchase Challan Master saved successfully.";
            }


            return SUCCESS;
        } catch (Exception e) {
            if (e.getCause().toString().contains("Duplicate entry")) {
                message = "this challan number " + PChallanMast.getPcmChallanNo() + " already exists for your PO NO " + PChallanMast.getErpmPoMaster().getPomPoNo();
            } else {
                message = "Exception in Save method -> ManagePurchaseChallanAxn '" + e.getMessage() + "' Reported Cause is: '" + e.getCause() + "'";
            }
            return ERROR;
        }
    }

    public String SavePurchaseChallanDetail() throws Exception {

        DateUtilities dt = new DateUtilities();

        Integer TotPOItemQty = 0;
        try {
            if (PCDetail.getPcdPcdId() == null) {


                defaultPCM = PChallanMast.getPcmPcmId();
                PChallanMast = PChallanMastDao.findByPcmId(defaultPCM);
                if (PCDetail.getErpmItemMaster().getErpmimId() != null && PCDetail.getPcdRecvQuantity() != null) {
                    PODetail = PODetailDAO.findBy_pomPoMasterId_ItemId(PChallanMast.getErpmPoMaster().getPomPoMasterId(), PCDetail.getErpmItemMaster().getErpmimId());
                    PCDetOnItemPOlist = PCDetailDAO.findBy_pomPoMasterId_ItemId_chalanId(PChallanMast.getErpmPoMaster().getPomPoMasterId(), PCDetail.getErpmItemMaster().getErpmimId(), 0);

                    if (PCDetOnItemPOlist.size() > 0) {
                        for (int i = 0; i < PCDetOnItemPOlist.size(); i++) {
                            TotPOItemQty = TotPOItemQty + PCDetOnItemPOlist.get(i).getPcdRecvQuantity().intValue();
                        }
                    }

                    if ((PODetail.getPodQuantity().intValue() < PCDetail.getPcdRecvQuantity().intValue() + TotPOItemQty)) {
                        message = "You cannot receive more than " + (PODetail.getPodQuantity().intValue() - TotPOItemQty) + " as per Purchase Order";
                        poditemlist = PODetailDAO.findItemListByPoMasterId(PChallanMast.getErpmPoMaster().getPomPoMasterId());
                        PCDetailslist = PCDetailDAO.findBypcmPcmId(PChallanMast.getPcmPcmId());
                        return "SUCCESS1";
                    } else {

                        if (getChecked() == true) {
                            PCDetail.setPcdQNQChecked('Y');
                        } else {
                            PCDetail.setPcdQNQChecked('N');
                        }

                        if (getVerified() == true) {
                            PCDetail.setPcdQNQVerified('Y');
                        } else {
                            PCDetail.setPcdQNQVerified('N');
                        }

                        PCDetail.setErpmPurchasechallanMaster(PChallanMast);

                        PCDetail.setPcdVerifiedBy(getCheckAndVerifiedRemarks());

                        PCDetailDAO.save(PCDetail);

                        defaultPCD = PCDetail.getPcdPcdId();
                        getSession().setAttribute("POMIDSforserial", defaultPCD);
                        PCDetail = PCDetailDAO.findBypcdPcdId(defaultPCD);
                        PChallanMast = PChallanMastDao.findByPcmId(defaultPCM);

                        ErpmStockReceived stockRec = new ErpmStockReceived();
                        Integer max = stockRecDao.findMaxSrNo(PCDetail.getErpmItemMaster().getErpmimId(),
                                Short.valueOf(PChallanMast.getInstitutionmaster().getImId()),
                                Integer.valueOf(PChallanMast.getSubinstitutionmaster().getSimId()),
                                Integer.valueOf(PChallanMast.getDepartmentmaster().getDmId()));

                        if (PCDetail.getErpmItemMaster().getErpmimSerialNoApplicable() == 'Y') {

                            for (int i = 0; i < PCDetail.getPcdRecvQuantity().intValue(); i++) {
                                Integer serial = i + 1 + max;
                                String serialCode = "0000";
                                if (serial <= 9) {
                                    serialCode = "0000";
                                } else if (serial > 9 && serial <= 99) {
                                    serialCode = "000";
                                } else if (serial > 99 && serial <= 999) {
                                    serialCode = "00";
                                } else if (serial > 999 && serial <= 9999) {
                                    serialCode = "0";
                                }



                                String serialNoCode = serialCode + serial.toString();
                                stockRec.setInstitutionmaster(PChallanMast.getInstitutionmaster());
                                stockRec.setSubinstitutionmaster(PChallanMast.getSubinstitutionmaster());
                                stockRec.setDepartmentmaster(PChallanMast.getDepartmentmaster());
                                stockRec.setSuppliermaster(PChallanMast.getErpmPoMaster().getSuppliermaster());
                                stockRec.setErpmItemMaster(PCDetail.getErpmItemMaster());
                                stockRec.setStInStockSince(PChallanMast.getPcmChallanDate());
                                stockRec.setStChallanDetId(PCDetail.getPcdPcdId());

                                stockRec.setStPoNo(PChallanMast.getErpmPoMaster().getDepartmentmaster().getDmShortName() + "/" + dt.convertDateToString(PChallanMast.getErpmPoMaster().getPomPoDate(), "dd-MM-yyyy").substring(6) + "/" + PChallanMast.getErpmPoMaster().getPomPoNo().toString());
                                stockRec.setStPoDate(PChallanMast.getErpmPoMaster().getPomPoDate());
                                stockRec.setStChallanNo(PChallanMast.getPcmChallanNo());
                                stockRec.setStChallanDate(PChallanMast.getPcmChallanDate());
                                // set quantity = 1
                                stockRec.setStQuantity(BigDecimal.valueOf(1.00));
                                stockRec.setStStockSerialNo(serialNoCode);
                                stockRecDao.save(stockRec);

                                message = "Item saved successfully, Please 'Edit' and 'Save' the Product Number";
                            }
                            itemName = stockRec.getErpmItemMaster().getErpmimItemBriefDesc();
                            viewStockRecList = viewStockRecDao.findByPCDetailId(PCDetail.getPcdPcdId());
                            setPurchaseChallanDetailId(PCDetail.getPcdPcdId());
                            setPrdNoReadOnly(true);
                            setPrdNoReadOnly1(false);
                            return SUCCESS;

                        } else {

                            stockRec.setInstitutionmaster(PChallanMast.getInstitutionmaster());
                            stockRec.setSubinstitutionmaster(PChallanMast.getSubinstitutionmaster());
                            stockRec.setDepartmentmaster(PChallanMast.getDepartmentmaster());
                            stockRec.setSuppliermaster(PChallanMast.getErpmPoMaster().getSuppliermaster());
                            stockRec.setErpmItemMaster(PCDetail.getErpmItemMaster());
                            stockRec.setStInStockSince(PChallanMast.getPcmChallanDate());
                            stockRec.setStChallanDetId(PCDetail.getPcdPcdId());
                            stockRec.setStPoNo(PChallanMast.getErpmPoMaster().getDepartmentmaster().getDmShortName() + "/" + dt.convertDateToString(PChallanMast.getErpmPoMaster().getPomPoDate(), "dd-MM-yyyy").substring(6) + "/" + PChallanMast.getErpmPoMaster().getPomPoNo().toString());
                            stockRec.setStPoDate(PChallanMast.getErpmPoMaster().getPomPoDate());
                            stockRec.setStChallanNo(PChallanMast.getPcmChallanNo());
                            stockRec.setStChallanDate(PChallanMast.getPcmChallanDate());
                            stockRec.setStQuantity(PCDetail.getPcdRecvQuantity());
                            stockRecDao.save(stockRec);

                            poditemlist = PODetailDAO.findItemListByPoMasterId(PChallanMast.getErpmPoMaster().getPomPoMasterId());
                            DefaultInsitute = PChallanMast.getInstitutionmaster().getImId();
                            PCDetailslist = PCDetailDAO.findBypcmPcmId(PCDetail.getErpmPurchasechallanMaster().getPcmPcmId());
                            setPrdNoReadOnly(true);
                            PCDetail = null;
                            return "SUCCESS1";
                        }
                    }
                } else {
                    if (PCDetail.getErpmItemMaster().getErpmimId() == null && PCDetail.getPcdRecvQuantity() == null) {
                        addFieldError("PCDetail.erpmItemMaster.erpmimId", "Please select item name");
                        addFieldError("PCDetail.pcdRecvQuantity", "Please Enter Received Quantity");
                    } else if (PCDetail.getPcdRecvQuantity() == null) {
                        addFieldError("PCDetail.pcdRecvQuantity", "Please Enter Received Quantity");
                    } else if (PCDetail.getErpmItemMaster().getErpmimId() == null) {
                        addFieldError("PCDetail.erpmItemMaster.erpmimId", "Please select item name");
                    }
                    PCDetailslist = PCDetailDAO.findBypcmPcmId(PChallanMast.getPcmPcmId());
                    poditemlist = PODetailDAO.findItemListByPoMasterId(PChallanMast.getErpmPoMaster().getPomPoMasterId());
                    return "SUCCESS1";
                }
            } else {


                TotPOItemQty = 0;
                ErpmPurchasechallanDetail pcdetail1 = PCDetailDAO.findBypcdPcdId(PCDetail.getPcdPcdId());

                ErpmPurchasechallanMaster pcmaster1 = PChallanMastDao.findBypcmPcmId(pcdetail1.getErpmPurchasechallanMaster().getPcmPcmId());

                pcdetail1 = PCDetail;
                defaultPCM = PChallanMast.getPcmPcmId();

                PODetail = PODetailDAO.findBy_pomPoMasterId_ItemId(pcmaster1.getErpmPoMaster().getPomPoMasterId(), pcdetail1.getErpmItemMaster().getErpmimId());

                PCDetOnItemPOlist = PCDetailDAO.findBy_pomPoMasterId_ItemId_chalanId(pcmaster1.getErpmPoMaster().getPomPoMasterId(), pcdetail1.getErpmItemMaster().getErpmimId(), pcdetail1.getPcdPcdId());
                if (PCDetOnItemPOlist.size() > 0) {
                    for (int i = 0; i < PCDetOnItemPOlist.size(); i++) {
                        TotPOItemQty = TotPOItemQty + PCDetOnItemPOlist.get(i).getPcdRecvQuantity().intValue();
                    }

                }

                if ((PODetail.getPodQuantity().intValue() < PCDetail.getPcdRecvQuantity().intValue() + TotPOItemQty)) {
                    message = "You cannot receive more than " + (PODetail.getPodQuantity().intValue() - TotPOItemQty) + " as per Purchase Order";

                    poditemlist = PODetailDAO.findItemListByPoMasterId(pcmaster1.getErpmPoMaster().getPomPoMasterId());
                    PCDetailslist = PCDetailDAO.findBypcmPcmId(pcmaster1.getPcmPcmId());
                } else {
                    if (getChecked() == true) {
                        pcdetail1.setPcdQNQChecked('Y');
                    } else {
                        pcdetail1.setPcdQNQChecked('N');
                    }

                    if (getVerified() == true) {
                        pcdetail1.setPcdQNQVerified('Y');
                    } else {
                        pcdetail1.setPcdQNQVerified('N');
                    }

                    pcdetail1.setPcdVerifiedBy(getCheckAndVerifiedRemarks());
                    pcdetail1.setErpmPurchasechallanMaster(pcmaster1);

                    PCDetailDAO.update(pcdetail1);

                    //It will give the lovs of Item List all below three lines
                    PChallanMast = PChallanMastDao.findByPcmId(defaultPCM);
                    poditemlist = PODetailDAO.findItemListByPoMasterId(PChallanMast.getErpmPoMaster().getPomPoMasterId());
                    DefaultInsitute = PChallanMast.getInstitutionmaster().getImId();
                    PCDetailslist = PCDetailDAO.findBypcmPcmId(PCDetail.getErpmPurchasechallanMaster().getPcmPcmId());
                    PCDetail = null;
                    setVerified(false);
                    setChecked(false);
                    setCheckAndVerifiedRemarks("");
                    message = "Item received successfully";
                }

                return "SUCCESS1";

            }

        } catch (Exception e) {
            if (e.getCause().toString().contains("Duplicate entry")) {

                PCDetailslist = PCDetailDAO.findBypcmPcmId(PCDetail.getErpmPurchasechallanMaster().getPcmPcmId());
                poditemlist = PODetailDAO.findItemListByPoMasterId(PChallanMast.getErpmPoMaster().getPomPoMasterId());
                message = "The selected Items  already exists in Your Purchase Challan, Please select other items";
                return INPUT;
            } else {
                message = "Exception in save method-> MANAGE PCDetailsAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            }
            return ERROR;
        }
    }

    @SkipValidation
    public String SavePurchaseChallanStockInfo() throws Exception {
        try {

            DateUtilities dt = new DateUtilities();
            ErpmStockReceived esr1 = stockRecDao.findByesrId(getVSRID());
            esr1.setStCsrPgNo(esr.getStCsrPgNo());
            esr1.setStCsrNo(esr.getStCsrNo());
            esr1.setStProductNo(esr.getStProductNo());
            esr1.setStDeptSrNo(esr.getStDeptSrNo());
            esr1.setStDeptSrPgNo(esr.getStDeptSrPgNo());
            if (getWarrantyExpiryDate().length() != 0) {
                if (dt.isValidDate(getWarrantyExpiryDate()) == false) {
                    addFieldError("WarrantyExpiryDate", "Please give valid Warranty Date [dd-mm-yyyy]");
                } else {
                    esr1.setStWarrantyExpiryDate(dt.convertStringToDate(getWarrantyExpiryDate()));

                }
            }

            if (getWarrantyType() == null) {
                esr1.setErpmGenMaster(null);

            } else {
                esr1.getErpmGenMaster().setErpmgmEgmId(getWarrantyType());

            }
            stockRecDao.update(esr1);

            viewStockRecList = viewStockRecDao.findByPCDetailId(getPurchaseChallanDetailId());
            setPrdNoReadOnly1(false);
            esr = null;
            message = "stock items info saved successfully ";

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in SavePurchaseChallanStockInfo() method-> MANAGE ManagePurchaseChallanAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String AddSerialNo() throws Exception {

        DateUtilities dt = new DateUtilities();
        try {

            Integer TotPOItemQty = 0;
            PCDetail = PCDetailDAO.findByPCDetailsID(getPurchaseChallanDetailId());
            defaultPCD = PCDetail.getErpmPurchasechallanMaster().getPcmPcmId();
            PChallanMast = PChallanMastDao.findByPcmId(defaultPCD);
            PODetail = PODetailDAO.findBy_pomPoMasterId_ItemId(PChallanMast.getErpmPoMaster().getPomPoMasterId(), PCDetail.getErpmItemMaster().getErpmimId());

            PCDetOnItemPOlist = PCDetailDAO.findBy_pomPoMasterId_ItemId_chalanId(PChallanMast.getErpmPoMaster().getPomPoMasterId(), PCDetail.getErpmItemMaster().getErpmimId(), PCDetail.getPcdPcdId());
            if (PCDetOnItemPOlist.size() > 0) {
                for (int i = 0; i < PCDetOnItemPOlist.size(); i++) {
                    TotPOItemQty = TotPOItemQty + PCDetOnItemPOlist.get(i).getPcdRecvQuantity().intValue();
                }

            }

            if ((PODetail.getPodQuantity().intValue() <= PCDetail.getPcdRecvQuantity().intValue() + TotPOItemQty)) {
                message = "You cannot add more than " + (PODetail.getPodQuantity().intValue() - TotPOItemQty) + " as per Purchase Order";
                viewStockRecList = viewStockRecDao.findByPCDetailId(PCDetail.getPcdPcdId());
                setPurchaseChallanDetailId(PCDetail.getPcdPcdId());
                setPrdNoReadOnly(true);
            } else {

                ErpmStockReceived stockRec = new ErpmStockReceived();
                Integer max = stockRecDao.findMaxSrNo(PCDetail.getErpmItemMaster().getErpmimId(),
                        Short.valueOf(PChallanMast.getInstitutionmaster().getImId()),
                        Integer.valueOf(PChallanMast.getSubinstitutionmaster().getSimId()),
                        Integer.valueOf(PChallanMast.getDepartmentmaster().getDmId()));

                Integer serial = 1 + max;
                String serialCode = "0000";
                if (serial <= 9) {
                    serialCode = "0000";
                } else if (serial > 9 && serial <= 99) {
                    serialCode = "000";
                } else if (serial > 99 && serial <= 999) {
                    serialCode = "00";
                } else if (serial > 999 && serial <= 9999) {
                    serialCode = "0";
                }

                String serialNoCode = serialCode + serial.toString();
                stockRec.setInstitutionmaster(PChallanMast.getInstitutionmaster());
                stockRec.setSubinstitutionmaster(PChallanMast.getSubinstitutionmaster());
                stockRec.setDepartmentmaster(PChallanMast.getDepartmentmaster());
                stockRec.setSuppliermaster(PChallanMast.getErpmPoMaster().getSuppliermaster());
                stockRec.setErpmItemMaster(PCDetail.getErpmItemMaster());
                stockRec.setStInStockSince(PChallanMast.getPcmChallanDate());
                stockRec.setStChallanDetId(PCDetail.getPcdPcdId());
                stockRec.setStPoNo(PChallanMast.getErpmPoMaster().getDepartmentmaster().getDmShortName() + "/" + dt.convertDateToString(PChallanMast.getErpmPoMaster().getPomPoDate(), "dd-MM-yyyy").substring(6) + "/" + PChallanMast.getErpmPoMaster().getPomPoNo().toString());
                stockRec.setStPoDate(PChallanMast.getErpmPoMaster().getPomPoDate());
                stockRec.setStChallanNo(PChallanMast.getPcmChallanNo());
                stockRec.setStChallanDate(PChallanMast.getPcmChallanDate());
                stockRec.setStQuantity(BigDecimal.valueOf(1.00));
                stockRec.setStStockSerialNo(serialNoCode);
                stockRecDao.save(stockRec);
                PCDetail.setPcdRecvQuantity(PCDetail.getPcdRecvQuantity().add(BigDecimal.ONE));
                PCDetailDAO.update(PCDetail);

                message = "Item added successfully, Please 'Edit' and 'Save' the Product Number";

                itemName = stockRec.getErpmItemMaster().getErpmimItemBriefDesc();
                viewStockRecList = viewStockRecDao.findByPCDetailId(PCDetail.getPcdPcdId());
                setPurchaseChallanDetailId(PCDetail.getPcdPcdId());

            }
            setPrdNoReadOnly1(false);
            return SUCCESS;

        } catch (Exception e) {
            message = "Exception in AddSerialNo() method-> MANAGE PCDetailsAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    @SkipValidation
    public String BrowsePurchaseChallanMaster() throws Exception {
        try {

//            PChallanMastList = PChallanMastDao.poListChallan(Short.valueOf(getSession().getAttribute("imId").toString()),Integer.valueOf(getSession().getAttribute("userid").toString()));
            PChallanMastList = PChallanMastDao.poListChallan(Short.valueOf(getSession().getAttribute("imId").toString()), Integer.valueOf(getSession().getAttribute("userid").toString()));            
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in BrowsePurchaseChallanMaster method -> ManagePurchaseChallanAxn" + e.getMessage();
            return ERROR;
        }
    }

    public String EditViewSerialDetail() throws Exception {

        try {

            PCDetail = PCDetailDAO.findByPCDetailsID(getPCDETID());
            if (PCDetail.getErpmItemMaster().getErpmimSerialNoApplicable() == 'Y') {
                setItemName(PCDetail.getErpmItemMaster().getErpmimItemBriefDesc());
                setPrdNoReadOnly(true);
                setPurchaseChallanDetailId(PCDetail.getPcdPcdId());
                viewStockRecList = viewStockRecDao.findByPCDetailId(PCDetail.getPcdPcdId());
                setPrdNoReadOnly1(false);
                message = "Serial Detail for the selected Item ";
                return SUCCESS;
            } else {
                defaultPCM = PCDetail.getErpmPurchasechallanMaster().getPcmPcmId();
                PChallanMast = PChallanMastDao.findBypcmPcmId(defaultPCM);
                PCDetailslist = PCDetailDAO.findBypcmPcmId(PChallanMast.getPcmPcmId());
                poditemlist = PODetailDAO.findItemListByPoMasterId(PChallanMast.getErpmPoMaster().getPomPoMasterId());
                institutionName = PChallanMast.getInstitutionmaster().getImName();
                subInstitutionName = PChallanMast.getSubinstitutionmaster().getSimName();
                departmentName = PChallanMast.getDepartmentmaster().getDmName();
                purchaseOrderNo = PChallanMast.getErpmPoMaster().getPomPoNo();
                if (PCDetail.getPcdQNQVerified() == 'Y') {
                    setVerified(true);
                } else {
                    setVerified(false);
                }
                if (PCDetail.getPcdQNQChecked() == 'Y') {
                    setChecked(true);
                } else {
                    setChecked(false);
                }

                setCheckAndVerifiedRemarks(PCDetail.getPcdVerifiedBy());

                return "SUCCESS1";
            }
        } catch (Exception e) {
            message = "Exception in ViewSerialDetail method -> ManagePurchaseChallanAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String CheckAndVerify() throws Exception {

        try {

            PCDetail = PCDetailDAO.findByPCDetailsID(getPCDETID());
            defaultPCM = PCDetail.getErpmPurchasechallanMaster().getPcmPcmId();
            PChallanMast = PChallanMastDao.findBypcmPcmId(defaultPCM);
            PCDetailslist = PCDetailDAO.findBypcmPcmId(PChallanMast.getPcmPcmId());
            poditemlist = PODetailDAO.findItemListByPoMasterId(PChallanMast.getErpmPoMaster().getPomPoMasterId());
            institutionName = PChallanMast.getInstitutionmaster().getImName();
            subInstitutionName = PChallanMast.getSubinstitutionmaster().getSimName();
            departmentName = PChallanMast.getDepartmentmaster().getDmName();
            purchaseOrderNo = PChallanMast.getErpmPoMaster().getPomPoNo();
            if (PCDetail.getPcdQNQVerified() == 'Y') {
                setVerified(true);
            } else {
                setVerified(false);
            }
            if (PCDetail.getPcdQNQChecked() == 'Y') {
                setChecked(true);
            } else {
                setChecked(false);
            }

            setCheckAndVerifiedRemarks(PCDetail.getPcdVerifiedBy());
            setPcdDisable(true);
            setBtnCheckAndVerifySave(false);
            message = "please Check the items to be accepted";
            return SUCCESS;

        } catch (Exception e) {
            message = "Exception in CheckAndVerify method -> ManagePurchaseChallanAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String SaveCheckAndVerify() throws Exception {

        try {


            defaultPCM = PChallanMast.getPcmPcmId();
            PChallanMast = PChallanMastDao.findBypcmPcmId(defaultPCM);
            poditemlist = PODetailDAO.findItemListByPoMasterId(PChallanMast.getErpmPoMaster().getPomPoMasterId());
            ErpmPurchasechallanDetail pcdetail1 = PCDetailDAO.findBypcdPcdId(PCDetail.getPcdPcdId());
            if (getChecked() == true) {
                pcdetail1.setPcdQNQChecked('Y');
            } else {
                pcdetail1.setPcdQNQChecked('N');
            }

            if (getVerified() == true) {
                pcdetail1.setPcdQNQVerified('Y');
            } else {
                pcdetail1.setPcdQNQVerified('N');
            }

            pcdetail1.setPcdVerifiedBy(getCheckAndVerifiedRemarks());
            PCDetailDAO.update(pcdetail1);
            PCDetailslist = PCDetailDAO.findBypcmPcmId(PChallanMast.getPcmPcmId());
            setChecked(false);
            setVerified(false);
            setCheckAndVerifiedRemarks("");
            PCDetail = null;

            message = "Check and Verify updated successfully ";
            return SUCCESS;

        } catch (Exception e) {
            message = "Exception in SaveCheckAndVerify method -> ManagePurchaseChallanAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String Done() throws Exception {

        try {
            PCDetail = PCDetailDAO.findByPCDetailsID(getPurchaseChallanDetailId());
            PChallanMast = PChallanMastDao.findByPcmId(PCDetail.getErpmPurchasechallanMaster().getPcmPcmId());
            purchaseOrderNo = PChallanMast.getErpmPoMaster().getPomPoNo();
            PCDetailslist = PCDetailDAO.findBypcmPcmId(PChallanMast.getPcmPcmId());
            poditemlist = PODetailDAO.findItemListByPoMasterId(PChallanMast.getErpmPoMaster().getPomPoMasterId());

            institutionName = PChallanMast.getInstitutionmaster().getImName();
            subInstitutionName = PChallanMast.getSubinstitutionmaster().getSimName();
            departmentName = PChallanMast.getDepartmentmaster().getDmName();
            PCDetail = null;
            message = "Product No saved successfully, Please select another item for saving Purchase Challan Detail";
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Done method -> ManagePurchaseChallanAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String EditPurchaseChallanStockInfo() throws Exception {
        try {
            setPrdNoReadOnly(false);
            DateUtilities dt = new DateUtilities();
            esr = stockRecDao.findbystid(getVSRID());
            setItemName(esr.getErpmItemMaster().getErpmimItemBriefDesc());
            if (esr.getStWarrantyExpiryDate() == null) {
                setWarrantyExpiryDate("");
            } else {
                setWarrantyExpiryDate(dt.convertDateToString(esr.getStWarrantyExpiryDate(), "dd-MM-yyyy"));
            }
            if (esr.getErpmGenMaster() == null) {
                setWarrantyType(0);
            } else {
                setWarrantyType(esr.getErpmGenMaster().getErpmgmEgmId());
            }
            tosWTList = egmDAO.findByErpmGmType(l);
            PCDetail = PCDetailDAO.findBypcdPcdId(getPurchaseChallanDetailId());
            viewStockRecList = viewStockRecDao.findByPCDetailId(getPurchaseChallanDetailId());
            setPurchaseChallanDetailId(PCDetail.getPcdPcdId());
            vsr = viewStockRecDao.findByVSRId(getVSRID());
            message = "Edit for Serial No: " + vsr.getSerialCode();

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in EditPurchaseChallanStockInfo method -> ManagePurchaseChallanAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String EditPurchaseChallan() throws Exception {
        try {
            PChallanMast = PChallanMastDao.findByPcmId(getPCID());
            DateUtilities dt = new DateUtilities();
            challanDate = dt.convertDateToString(PChallanMast.getPcmChallanDate(), "dd-MM-yyyy");
            recieveDate = dt.convertDateToString(PChallanMast.getPcmRecvDate(), "dd-MM-yyyy");
            imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
            simList = simDao.findSubInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), PChallanMast.getInstitutionmaster().getImId());
            dmList = dmDao.findDepartmentForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), PChallanMast.getSubinstitutionmaster().getSimId());
//            POMasterList = pomasterDAO.poList(Short.valueOf(getSession().getAttribute("imId").toString()));
            POMasterList = pomasterDAO.poList2(Short.valueOf(getSession().getAttribute("imId").toString()));            
            DefaultInsitute = PChallanMast.getInstitutionmaster().getImId();
            DefaultSubInsitute = PChallanMast.getSubinstitutionmaster().getSimId();
            DefaultDepartment = PChallanMast.getDepartmentmaster().getDmId();
  
            return SUCCESS;

        } catch (Exception e) {
            message = "Exception in EditPurchaseChallan method -> ManagePurchaseChallanAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String DeletePurchaseChallanDetail() {

//        if(viewStockRecDao.findByPCDetailId(getPCDETID()).size()>0)

        PCDetail = PCDetailDAO.findByPCDetailsID(getPCDETID());


        if (PCDetail.getErpmItemMaster().getErpmimSerialNoApplicable() == 'Y') {
            defaultPCM = PCDetail.getErpmPurchasechallanMaster().getPcmPcmId();
            PChallanMast = PChallanMastDao.findBypcmPcmId(defaultPCM);
            //item list to select item in purchaseChallanDetail.jsp
            poditemlist = PODetailDAO.findItemListByPoMasterId(PChallanMast.getErpmPoMaster().getPomPoMasterId());
            purchaseOrderNo = PChallanMast.getErpmPoMaster().getPomPoNo();
            PCDetailslist = PCDetailDAO.findBypcmPcmId(PCDetail.getErpmPurchasechallanMaster().getPcmPcmId());
            institutionName = PChallanMast.getInstitutionmaster().getImName();
            subInstitutionName = PChallanMast.getSubinstitutionmaster().getSimName();
            departmentName = PChallanMast.getDepartmentmaster().getDmName();
            message = "You cannot Delete the item, You need to delete the each Serial number generated CLICK \"VIEW\"";
        } else {

            defaultPCM = PCDetail.getErpmPurchasechallanMaster().getPcmPcmId();
            PChallanMast = PChallanMastDao.findBypcmPcmId(defaultPCM);
            PCDetailDAO.delete(PCDetail);

            message = "Record successfully Deleted";
            
            PChallanMast = PChallanMastDao.findByPcmId(defaultPCM);
            DefaultInsitute = PChallanMast.getInstitutionmaster().getImId();
            institutionName = PChallanMast.getInstitutionmaster().getImName();
            subInstitutionName = PChallanMast.getSubinstitutionmaster().getSimName();
            departmentName = PChallanMast.getDepartmentmaster().getDmName();
            //item list to select item in purchaseChallanDetail.jsp
            poditemlist = PODetailDAO.findItemListByPoMasterId(PChallanMast.getErpmPoMaster().getPomPoMasterId());
            //for PO no. to be displayed on purchase challan detail jsp
            purchaseOrderNo = PChallanMast.getErpmPoMaster().getPomPoNo();
            PCDetailslist = PCDetailDAO.findBypcmPcmId(PCDetail.getErpmPurchasechallanMaster().getPcmPcmId());
            PCDetail = null;
        }
        return SUCCESS;
    }

    public String DeletePurchaseChallan() throws Exception {
        try {
            //retrieve the record to be deleted
            PChallanMast = PChallanMastDao.findByPcmId(getPCID());
            PChallanMastDao.delete(PChallanMast);
//            PChallanMastList = PChallanMastDao.findPOForUserDepartments(Integer.parseInt(getSession().getAttribute("userid").toString()));


            message = "Purchase challan deleted successfully";
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in DeletePurchaseChallan method -> ManagePurchaseChallanAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    @SkipValidation
    public String ClearPurchaseChallanDetail() throws Exception {

        try {

            defaultPCM = PChallanMast.getPcmPcmId();
            PChallanMast = PChallanMastDao.findBypcmPcmId(defaultPCM);
            PCDetailslist = PCDetailDAO.findBypcmPcmId(PChallanMast.getPcmPcmId());
            poditemlist = PODetailDAO.findItemListByPoMasterId(PChallanMast.getErpmPoMaster().getPomPoMasterId());
            message = "Screen cleared successfully, Now you can enter new data";
            setChecked(false);
            setVerified(false);
            setCheckAndVerifiedRemarks("");
            PCDetail = null;
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in ClearPurchaseChallanDetail method -> ManagePurchaseChallanAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String DeletePurchaseChallanItem() throws Exception {

        try {
            esr = stockRecDao.findByesrId(getVSRID());
            Integer stockId = eisdDao.findByStockSerialId(esr.getStId());
            if (stockId == 0) {
                PCDetail = PCDetailDAO.findBypcdPcdId(getPurchaseChallanDetailId());
                if (PCDetail.getPcdRecvQuantity().intValue() > 1) {
                    stockRecDao.delete(esr);
                    PCDetail.setPcdRecvQuantity(PCDetail.getPcdRecvQuantity().subtract(BigDecimal.ONE));
                    PCDetailDAO.update(PCDetail);
                    message = "" + PCDetail.getPcdRecvQuantity().intValue();
                    setItemName(esr.getErpmItemMaster().getErpmimItemBriefDesc());
                    viewStockRecList = viewStockRecDao.findByPCDetailId(PCDetail.getPcdPcdId());
                    setPrdNoReadOnly(true);
                    setPrdNoReadOnly1(false);
                    message = "Item Deleted successfully";
                    return SUCCESS;
                } else if (PCDetail.getPcdRecvQuantity().intValue() == 1) {
                    stockRecDao.delete(esr);
                    PCDetail.setPcdRecvQuantity(PCDetail.getPcdRecvQuantity().subtract(BigDecimal.ONE));
                    PCDetailDAO.update(PCDetail);
                    PCDetailDAO.delete(PCDetail);
                    PChallanMast = PChallanMastDao.findBypcmPcmId(PCDetail.getErpmPurchasechallanMaster().getPcmPcmId());
                    PCDetailslist = PCDetailDAO.findBypcmPcmId(PCDetail.getErpmPurchasechallanMaster().getPcmPcmId());
                    poditemlist = PODetailDAO.findItemListByPoMasterId(PChallanMast.getErpmPoMaster().getPomPoMasterId());
                    purchaseOrderNo = PChallanMast.getErpmPoMaster().getPomPoNo();
                    institutionName = PChallanMast.getInstitutionmaster().getImName();
                    subInstitutionName = PChallanMast.getSubinstitutionmaster().getSimName();
                    departmentName = PChallanMast.getDepartmentmaster().getDmName();
                    PCDetail = null;
                    defaultPCM = PChallanMast.getPcmPcmId();
                    setPrdNoReadOnly(true);
                    setPrdNoReadOnly1(false);
                    message = "Item Deleted successfully, You can add new Items";
                    return "SUCCESS1";

                }

            } else if (stockId == 1) {
                setItemName(esr.getErpmItemMaster().getErpmimItemBriefDesc());
                PCDetail = PCDetailDAO.findBypcdPcdId(getPurchaseChallanDetailId());
                viewStockRecList = viewStockRecDao.findByPCDetailId(PCDetail.getPcdPcdId());
                setPrdNoReadOnly(true);
                setPrdNoReadOnly1(false);
                message = "You cannot delete the Item, Item is issued";

            }
            return SUCCESS;

        } catch (Exception e) {
            message = "Exception in DeletePurchaseChallanItem method -> ManagePurchaseChallanAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    @SkipValidation
    public String ShowPOReport() throws Exception {
        HashMap hm = new HashMap();

        // Get System properties
        Properties properties = System.getProperties();

        // Get the path separator symbol, which is unfortunatly different, in different OS platform.
        String pathSeparator = properties.getProperty("file.separator");

        pathSeparator = pathSeparator + pathSeparator;
        String repPath = "pico" + pathSeparator + "PrePurchase" + pathSeparator + "Reports" + pathSeparator + "Purchase_Order.jasper";

        String fileName = getSession().getServletContext().getRealPath(repPath);
//        String fileName = getSession().getServletContext().getRealPath("pico\\PrePurchase\\Reports\\Purchase_Order.jasper");

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
            response.setHeader("Content-Disposition", "attachment; filename=Purchase_Order.pdf");
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();


            //Setup Where Condition Clause
            if (PChallanMast.getErpmPoMaster().getPomPoMasterId() == null) {
            } else {
                whereCondition = "erpm_po_master.`POM_PO_Master_ID` = " + PChallanMast.getErpmPoMaster().getPomPoMasterId();
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

    @Override
    public void validate() {

        try {
            DateUtilities dt = new DateUtilities();
            //validation of PCMASTER Screen NO-1


            if (getRecieveDate().length() == 0) {
                addFieldError("recieveDate", "Please give valid recieveDate [dd-mm-yyyy]");

            } else {
                if (dt.isValidDate(getRecieveDate()) == false) {
                    addFieldError("recieveDate", "Please give valid recieveDate [dd-mm-yyyy]");
                }
            }
            if (getChallanDate().length() == 0) {
                addFieldError("challanDate", "Please give valid challanDate [dd-mm-yyyy]");

            } else {
                if (dt.isValidDate(getChallanDate()) == false) {
                    addFieldError("challanDate", "Please give valid Challan Date [dd-mm-yyyy]");
                }
            }
            if (PChallanMast.getInstitutionmaster().getImId() == null) {
                addFieldError("PChallanMast.institutionmaster.imId", "Please select institution from the list");
            }

            if (PChallanMast.getSubinstitutionmaster().getSimId() == null) {
                addFieldError("PChallanMast.subinstitutionmaster.simId", "Please select Subinsitute from the list");
            }
            if (PChallanMast.getDepartmentmaster().getDmId() == null) {
                addFieldError("PChallanMast.departmentmaster.dmId", "Please select Department from the list");
            }


            if (PCDetail.getErpmItemMaster().getErpmimId() == null) {
                addFieldError("PCDetail.erpmItemMaster.erpmimId", "Please select item from the list ");
            }
            if (PCDetail.getPcdRecvQuantity() == null) {
                addFieldError("PCDetail.pcdRecvQuantity", "Quantity cannot be Blank");
            }
            if (Double.valueOf(PCDetail.getPcdRecvQuantity().toString()) < 0) //{
            {
                addFieldError("PCDetail.pcdRecvQuantity", "Quantity cannot  be negative");
            }
            if (Double.valueOf(PCDetail.getPcdRecvQuantity().toString()) > 999999.99) //{
            {
                addFieldError("PCDetail.pcdRecvQuantity", "Quantity cannot  exceed 999999.99");
            }

            // InitializeLOVs();
        } catch (NullPointerException npe) {
        }
        InitializeLOVs();

    }

    public String PurchaseChallanSerial() throws Exception {
        try {

            defaultPCD = Integer.parseInt(getSession().getAttribute("POMIDSforserial").toString());
            PCDetail = PCDetailDAO.findBypcdPcdId(defaultPCD);


            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in ManagePChallanSerial method ->  " + e.getMessage() + " Reported Cause is: " + e.getCause() + " " + "Plz add Item First and then proceed to add serial";
            return ERROR;
        }
    }

    public String SavePurchaseChallanSerial() throws Exception {
        try {
            defaultPCD = Integer.parseInt(getSession().getAttribute("POMIDSforserial").toString());
            PCDetail = PCDetailDAO.findBypcdPcdId(defaultPCD);
            PCSerial.setErpmPurchasechallanDetail(PCDetail);
            PCSerialDAO.save(PCSerial);
            message = "record succesfully saved";
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in SavePChallanSerial method ->  " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String ShowDetailPurchaseChallan() {
        PChallanMast = PChallanMastDao.findByPcmId(getPCID());

        ErpmPurchasechallanMaster pchallanMast1 = PChallanMastDao.findBypcmPcmId(PChallanMast.getPcmPcmId());
//        message = ""+pchallanMast1.getErpmPoMaster().getPomPoMasterId();
        purchaseOrderNo = pchallanMast1.getErpmPoMaster().getPomPoNo();
        poditemlist = PODetailDAO.findItemListByPoMasterId(pchallanMast1.getErpmPoMaster().getPomPoMasterId());

        institutionName = pchallanMast1.getInstitutionmaster().getImName();
        subInstitutionName = pchallanMast1.getSubinstitutionmaster().getSimName();
        departmentName = pchallanMast1.getDepartmentmaster().getDmName();
        PCDetailslist = PCDetailDAO.findBypcmPcmId(pchallanMast1.getPcmPcmId());

        return SUCCESS;
    }

    @SkipValidation
    public String PrintReceiptPurchaseChallan() throws Exception {
        HashMap hm = new HashMap();

        // Get System properties
        Properties properties = System.getProperties();

        // Get the path separator symbol, which is unfortunatly different, in different OS platform.
        String pathSeparator = properties.getProperty("file.separator");

        pathSeparator = pathSeparator + pathSeparator;
        String repPath = "pico" + pathSeparator + "Purchase" + pathSeparator + "Reports" + pathSeparator + "PurchaseChallanReceipt.jasper";

        String fileName = getSession().getServletContext().getRealPath(repPath);
//        String fileName = getSession().getServletContext().getRealPath("pico\\Purchase\\Reports\\PurchaseChallanReceipt.jasper");
        PChallanMast = PChallanMastDao.findByPcmId(getPCID());

        ErpmPurchasechallanMaster pchallanMast1 = PChallanMastDao.findBypcmPcmId(PChallanMast.getPcmPcmId());
        purchaseOrderNo = pchallanMast1.getErpmPoMaster().getPomPoNo();

        institutionName = pchallanMast1.getInstitutionmaster().getImName();
        subInstitutionName = pchallanMast1.getSubinstitutionmaster().getSimName();
        departmentName = pchallanMast1.getDepartmentmaster().getDmName();

        String whereCondition;

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
            response.setHeader("Content-Disposition", "attachment; filename=PurchaseChallanReceipt.pdf");
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();


            //Setup Where Condition Clause

            whereCondition = "erpm_purchasechallan_master.`PCM_PCM_ID`= " + PChallanMast.getPcmPcmId();


            hm.put("condition", whereCondition);
            //
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

        pathSeparator = pathSeparator + pathSeparator;
        String repPath = "pico" + pathSeparator + "Administration" + pathSeparator + "Reports" + pathSeparator + "ShowGFRMappedinProgram.jasper";

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


            whereCondition = "gfr_program_mapping.`GPM_Program_ID` = 21";

            hm.put("condition", whereCondition);
            hm.put("screen_name", "PURCHASE CHALLAN RECEIPT");
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
