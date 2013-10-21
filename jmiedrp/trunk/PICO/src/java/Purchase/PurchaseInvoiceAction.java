/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Purchase;

/**
 *
 * Author : Tanvir Ahmed , Saeed
 */
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import pojo.hibernate.*;
import utils.DateUtilities;
import utils.DevelopmentSupport;

import java.util.Locale;
import java.util.ResourceBundle;
import com.opensymphony.xwork2.ActionContext;

public class PurchaseInvoiceAction extends DevelopmentSupport {

    private Integer pimPimId;
    private String message;
    private Integer Default_PO;
    private Integer Default_ChallanNo;
    private Integer Default_ItemName;
    private Integer podPodetailsId;
    private Integer pidPidId;
    private Boolean PrdNoReadOnly;
    private int VSRID;
    private static Integer VarPidpidId;
    private String productNo;
    private String invoicerecvDate;
    private String suplierinvoiceDate;
    private InputStream inputStream;
    private Boolean checked;
    private Boolean verified;
    private String CheckAndVerifiedRemarks;
    private Boolean PidDisable;
    private Boolean btnCheckAndVerifySave = true;
    private List<Institutionmaster> imList = new ArrayList<Institutionmaster>();
    private InstitutionmasterDAO imDao = new InstitutionmasterDAO();
    private List<Subinstitutionmaster> simList = new ArrayList<Subinstitutionmaster>();
    private SubinstitutionmasterDAO simDao = new SubinstitutionmasterDAO();
    private List<Departmentmaster> dmList = new ArrayList<Departmentmaster>();
    private DepartmentmasterDAO dmDao = new DepartmentmasterDAO();
    private List<Suppliermaster> smList = new ArrayList<Suppliermaster>();
    private SuppliermasterDAO smDao = new SuppliermasterDAO();
    private ErpmItemRate itemrate;
    private ErpmItemMaster item;
    private List<ErpmGenMaster> currencyList = new ArrayList<ErpmGenMaster>();
    private ErpmGenMasterDao GMDao = new ErpmGenMasterDao();
    private ErpmPurchaseinvoiceMaster pibm;
    private ErpmPurchaseinvoiceMasterDAO pibmDao = new ErpmPurchaseinvoiceMasterDAO();
    private List<ErpmPurchaseinvoiceMaster> pibmList = new ArrayList<ErpmPurchaseinvoiceMaster>();
    private List<String> pibmListForBrowse = new ArrayList<String>();
    private ErpmPoMasterDAO pomDao = new ErpmPoMasterDAO();
    private List<String> pomList = new ArrayList<String>();
    private ErpmPurchasechallanMaster pcm = new ErpmPurchasechallanMaster();
    private List<ErpmPurchasechallanMaster> pcmList = new ArrayList<ErpmPurchasechallanMaster>();
    private ErpmPurchaseChallanMasterDAO pcmDao = new ErpmPurchaseChallanMasterDAO();
    private List<ErpmPurchaseinvoiceDetail> erpmpidList = new ArrayList<ErpmPurchaseinvoiceDetail>();
    private ErpmPurchaseinvoiceDetailDao pidDao = new ErpmPurchaseinvoiceDetailDao();
    private ErpmPurchaseinvoiceDetail pid = new ErpmPurchaseinvoiceDetail();
    private List<ErpmPurchasechallanDetail> ppcdList = new ArrayList<ErpmPurchasechallanDetail>();
    private ErpmPurchaseChallanDetailDAO pcdDao = new ErpmPurchaseChallanDetailDAO();
    private ErpmPurchasechallanDetail challanDetail;
    private ErpmPoDetails poDetail = new ErpmPoDetails();
    private List<ErpmPoDetails> podList = new ArrayList<ErpmPoDetails>();
    private ErpmPoDetailsDAO podDao = new ErpmPoDetailsDAO();
    private ErpmPurchaseinvoiceTaxesDAO pitaxDao = new ErpmPurchaseinvoiceTaxesDAO();
    private ErpmPurchaseinvoiceTaxes pitax = new ErpmPurchaseinvoiceTaxes();
    private List<ErpmPurchaseinvoiceTaxes> pitaxList = new ArrayList<ErpmPurchaseinvoiceTaxes>();
    private ErpmPoTaxes poTax;
    private List<ErpmPoTaxes> poTaxList = new ArrayList<ErpmPoTaxes>();
    private ErpmPoTaxesDao poTaxDao = new ErpmPoTaxesDao();
    private List<ErpmStockReceived> stockRecList = new ArrayList<ErpmStockReceived>();
    private ErpmStockReceived stockRec = new ErpmStockReceived();
    private ErpmStockReceivedDAO stockRecDao = new ErpmStockReceivedDAO();
    private List<ViewStockReceived> viewStockRecList = new ArrayList<ViewStockReceived>();
    ViewStockReceivedDAO viewStockRecDao = new ViewStockReceivedDAO();
    ErpmIssueSerialDetailDAO eisdDao = new ErpmIssueSerialDetailDAO();
    private GfrProgramMappingDAO GfrProgramMappingDao = new GfrProgramMappingDAO();
    private static Boolean varShowGFR;

    public Boolean getVarShowGFR() {
        return varShowGFR;
    }

    public void setVarShowGFR(Boolean varShowGFR) {
        this.varShowGFR = varShowGFR;
    }

    public Boolean getBtnCheckAndVerifySave() {
        return btnCheckAndVerifySave;
    }

    public void setBtnCheckAndVerifySave(Boolean btnCheckAndVerifySave) {
        this.btnCheckAndVerifySave = btnCheckAndVerifySave;
    }

    public Boolean getPidDisable() {
        return PidDisable;
    }

    public void setPidDisable(Boolean PidDisable) {
        this.PidDisable = PidDisable;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getInvoicerecvDate() {
        return invoicerecvDate;
    }

    public void setInvoicerecvDate(String invoicerecvDate) {
        this.invoicerecvDate = invoicerecvDate;
    }

    public String getSuplierinvoiceDate() {
        return suplierinvoiceDate;
    }

    public void setSuplierinvoiceDate(String suplierinvoiceDate) {
        this.suplierinvoiceDate = suplierinvoiceDate;
    }

    public List<String> getPibmListForBrowse() {
        return pibmListForBrowse;
    }

    public void setPibmListForBrowse(List<String> pibmListForBrowse) {
        this.pibmListForBrowse = pibmListForBrowse;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public static Integer getVarPidpidId() {
        return VarPidpidId;
    }

    public static void setVarPidpidId(Integer VarPidpidId) {
        PurchaseInvoiceAction.VarPidpidId = VarPidpidId;
    }

    public int getVSRID() {
        return VSRID;
    }

    public void setVSRID(int VSRID) {
        this.VSRID = VSRID;
    }

    public Boolean getPrdNoReadOnly() {
        return PrdNoReadOnly;
    }

    public void setPrdNoReadOnly(Boolean PrdNoReadOnly) {
        this.PrdNoReadOnly = PrdNoReadOnly;
    }

    public List<ViewStockReceived> getViewStockRecList() {
        return viewStockRecList;
    }

    public void setViewStockRecList(List<ViewStockReceived> viewStockRecList) {
        this.viewStockRecList = viewStockRecList;
    }

    public List<ErpmStockReceived> getStockRecList() {
        return stockRecList;
    }

    public void setStockRecList(List<ErpmStockReceived> stockRecList) {
        this.stockRecList = stockRecList;
    }

    public ErpmPoTaxes getPoTax() {
        return poTax;
    }

    public void setPoTax(ErpmPoTaxes poTax) {
        this.poTax = poTax;
    }

    public List<ErpmPoTaxes> getPoTaxList() {
        return poTaxList;
    }

    public void setPoTaxList(List<ErpmPoTaxes> poTaxList) {
        this.poTaxList = poTaxList;
    }

    public ErpmPurchaseinvoiceMaster getPibm() {
        return pibm;
    }

    public void setPibm(ErpmPurchaseinvoiceMaster pibm) {
        this.pibm = pibm;
    }

    public List<ErpmPoDetails> getPodList() {
        return podList;
    }

    public void setPodList(List<ErpmPoDetails> podList) {
        this.podList = podList;
    }

    public ErpmPurchasechallanDetail getChallanDetail() {
        return challanDetail;
    }

    public void setChallanDetail(ErpmPurchasechallanDetail challanDetail) {
        this.challanDetail = challanDetail;
    }

    public ErpmPoDetails getPoDetail() {
        return poDetail;
    }

    public void setPoDetail(ErpmPoDetails poDetail) {
        this.poDetail = poDetail;
    }

    public List<ErpmPurchasechallanDetail> getPpcdList() {
        return ppcdList;
    }

    public void setPpcdList(List<ErpmPurchasechallanDetail> ppcdList) {
        this.ppcdList = ppcdList;
    }

    public List<ErpmPurchaseinvoiceDetail> getErpmpidList() {
        return erpmpidList;
    }

    public void setErpmpidList(List<ErpmPurchaseinvoiceDetail> erpmpidList) {
        this.erpmpidList = erpmpidList;
    }

    public ErpmPurchaseinvoiceDetail getPid() {
        return pid;
    }

    public void setPid(ErpmPurchaseinvoiceDetail pid) {
        this.pid = pid;
    }
    private List<ErpmItemMaster> itemmList = new ArrayList<ErpmItemMaster>();
    private ErpmItemMasterDAO itemmDao = new ErpmItemMasterDAO();

    public List<ErpmItemMaster> getItemmList() {
        return itemmList;
    }

    public void setItemmList(List<ErpmItemMaster> itemmList) {
        this.itemmList = itemmList;
    }

    public List<ErpmPurchasechallanMaster> getPcmList() {
        return pcmList;
    }

    public void setPcmList(List<ErpmPurchasechallanMaster> pcmList) {
        this.pcmList = pcmList;
    }

    public ErpmPurchasechallanMaster getPcm() {
        return pcm;
    }

    public void setPcm(ErpmPurchasechallanMaster pcm) {
        this.pcm = pcm;
    }

    public List<String> getPomList() {
        return pomList;
    }

    public void setPomList1(List<String> pomList) {
        this.pomList = pomList;
    }

    public Integer getPidPidId() {
        return pidPidId;
    }

    public void setPidPidId(Integer pidPidId) {
        this.pidPidId = pidPidId;
    }

    public Integer getPimPimId() {
        return pimPimId;
    }

    public void setPimPimId(Integer pimPimId) {
        this.pimPimId = pimPimId;
    }

    public List<ErpmPurchaseinvoiceMaster> getPibmList() {
        return pibmList;
    }

    public void setPibmList(List<ErpmPurchaseinvoiceMaster> pibmList) {
        this.pibmList = pibmList;
    }

    public List<ErpmGenMaster> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<ErpmGenMaster> currencyList) {
        this.currencyList = currencyList;
    }

    public ErpmItemRate getItemrate() {
        return itemrate;
    }

    public void setItemrate(ErpmItemRate itemrate) {
        this.itemrate = itemrate;
    }

    public List<Suppliermaster> getSmList() {
        return smList;
    }

    public void setSmList(List<Suppliermaster> smList) {
        this.smList = smList;
    }

    public List<Departmentmaster> getDmList() {
        return dmList;
    }

    public void setDmList(List<Departmentmaster> dmList) {
        this.dmList = dmList;
    }

    public List<Subinstitutionmaster> getSimList() {
        return simList;
    }

    public void setSimList(List<Subinstitutionmaster> simList) {
        this.simList = simList;
    }

    public List<Institutionmaster> getImList() {
        return imList;
    }

    public void setImList(List<Institutionmaster> imList) {
        this.imList = imList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getDefault_PO() {
        return Default_PO;
    }

    public void setDefault_PO(Integer Default_PO) {
        this.Default_PO = Default_PO;
    }

    public ErpmPurchaseinvoiceTaxes getPitax() {
        return pitax;
    }

    public void setPitax(ErpmPurchaseinvoiceTaxes pitax) {
        this.pitax = pitax;
    }

    public List<ErpmPurchaseinvoiceTaxes> getPitaxList() {
        return pitaxList;
    }

    public void setPitaxList(List<ErpmPurchaseinvoiceTaxes> pitaxList) {
        this.pitaxList = pitaxList;
    }

    public ErpmStockReceived getStockRec() {
        return stockRec;
    }

    public void setStockRec(ErpmStockReceived stockRec) {
        this.stockRec = stockRec;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public String getCheckAndVerifiedRemarks() {
        return CheckAndVerifiedRemarks;
    }

    public void setCheckAndVerifiedRemarks(String CheckAndVerifiedRemarks) {
        this.CheckAndVerifiedRemarks = CheckAndVerifiedRemarks;
    }

    @Override
    public String execute() throws Exception {
        try {

            prepare_lovs();
            Date d = new Date();
            DateUtilities d1 = new DateUtilities();
            setInvoicerecvDate(d1.convertDateToString(d, "dd-MM-yyyy"));
            setSuplierinvoiceDate(d1.convertDateToString(d, "dd-MM-yyyy"));
            Short i = 32;
            Integer count = GfrProgramMappingDao.findCountByProgramId(i);


            if (count > 0) {
                setVarShowGFR(false);
            } else {
                setVarShowGFR(true);
            }
            return SUCCESS;
        } catch (Exception e) {
            message = message + e.getMessage();
            return ERROR;
        }
    }

    public void prepare_lovs() {
        //Prepare Institution Type List
        imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));

        //Prepare SubInstitute List
        simList = simDao.findSubInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()),
                Short.valueOf(getSession().getAttribute("imId").toString()));

        //Prepare Department List
        dmList = dmDao.findAllDepartmentsForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));

        smList = smDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));

        //Prepare List of Purchase Order
        pomList = pomDao.poList(Short.valueOf(getSession().getAttribute("imId").toString()));

        pcmList = pcmDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
    }

    @SkipValidation
    public String Clear() throws Exception {
        try {

            pibm = null;
            pibmList = pibmDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));

            prepare_lovs();
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in -> PurchaseInvoice Clear methodd Cause is: " + e.getCause();
            return ERROR;
        }
    }

    @SkipValidation
    public String Browse() throws Exception {
        try {

            pibmList = pibmDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Browse method of PurchaseInvoiceAction cause :" + e.getCause();
            return ERROR;
        }
    }

    public String Edit() throws Exception {
        try {
            pibm = pibmDao.findByErpmId(getPimPimId());
            DateUtilities dt = new DateUtilities();
            invoicerecvDate = dt.convertDateToString(pibm.getPimInvoiceRecvdDate(), "dd-MM-yyyy");
            suplierinvoiceDate = dt.convertDateToString(pibm.getPimSupplierInvoiceDate(), "dd-MM-yyyy");
            prepare_lovs();

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Edit method -> DepartmentAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String EditPurchaseInvoiceDetailforChallan() throws Exception {
        try {

            pid = pidDao.findByErpId(getPidPidId());

            if (pid.getErpmItemMaster().getErpmimSerialNoApplicable() == 'Y') {

                viewStockRecList = viewStockRecDao.findByInvoiceNO_n_ItemId(pid.getErpmPurchaseinvoiceMaster().getPimSupplierInvoiceNo(), pid.getErpmItemMaster().getErpmimId());
                setPrdNoReadOnly(true);
                setVarPidpidId(getPidPidId());

                return "SUCCESS1";
            } else {

                Default_ChallanNo = pid.getErpmPurchaseinvoiceMaster().getPimPimId();
                pibm = pibmDao.findpimPimId(Default_ChallanNo);

                ppcdList = pcdDao.findBypcmPcmId(pibm.getErpmPurchasechallanMaster().getPcmPcmId());

                erpmpidList = pidDao.findBypimId(pibm.getPimPimId());

                if (pid.getPcdQNQVerified() == 'Y') {
                    setVerified(true);
                } else {
                    setVerified(false);
                }
                if (pid.getPcdQNQChecked() == 'Y') {
                    setChecked(true);
                } else {
                    setChecked(false);
                }

                setCheckAndVerifiedRemarks(pid.getPcdVerifiedBy());
                setPidDisable(true);
                setVarPidpidId(getPidPidId());

                return SUCCESS;
            }
        } catch (Exception e) {
            message = "Exception in EditPurchaseInvoiceDetailforChallan method " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String EditPurchaseInvoiceDetailforPO() throws Exception {
        try {


            pid = pidDao.findByErpId(getPidPidId());

            if (pid.getErpmItemMaster().getErpmimSerialNoApplicable() == 'Y') {

                viewStockRecList = viewStockRecDao.findByInvoiceNO_n_ItemId(pid.getErpmPurchaseinvoiceMaster().getPimSupplierInvoiceNo(), pid.getErpmItemMaster().getErpmimId());
                setPrdNoReadOnly(true);
                setVarPidpidId(getPidPidId());
                return "SUCCESS1";
            }

            Default_PO = pid.getErpmPurchaseinvoiceMaster().getPimPimId();

            pibm = pibmDao.findpimPimId(Default_PO);

            podList = podDao.findItemListByPoMasterId(pibm.getErpmPoMaster().getPomPoMasterId());

            erpmpidList = pidDao.findBypimId(pibm.getPimPimId());

            if (pid.getPcdQNQVerified() == 'Y') {
                setVerified(true);
            } else {
                setVerified(false);
            }
            if (pid.getPcdQNQChecked() == 'Y') {
                setChecked(true);
            } else {
                setChecked(false);
            }

            setCheckAndVerifiedRemarks(pid.getPcdVerifiedBy());
            setPidDisable(true);
            setVarPidpidId(getPidPidId());
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Edit method " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String Delete() throws Exception {
        try {

            pibm = pibmDao.findByErpmId(getPimPimId());
            pibmDao.delete(pibm);
            message = "Record deleted successfull";
            pibmList = pibmDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Delete method -> ProgramAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String Save() throws Exception {
        Integer varPIMID;
        try {
            // firstly we check the invoice duplication for the same supplier in any institute/subinstitute
            DateUtilities dt = new DateUtilities();
            if (pibm.getPimPimId() == null) {
                varPIMID = 0;
            } else {
                varPIMID = pibm.getPimPimId();
            }
//            pibmList = pibmDao.findByImId_SimId_SmId_SupplierInvoiceNo_pimPimId(pibm.getInstitutionmaster().getImId(), pibm.getSubinstitutionmaster().getSimId(), pibm.getSuppliermaster().getSmId(), pibm.getPimSupplierInvoiceNo(), varPIMID);
//
//            // if any matching record is found then alert user and don't let it to be saved
//            if (pibmList.size() > 0) {
//                message = "Invoice Number of this Supplier Already Exists";
//            }

            pibmList = pibmDao.findBySupplierName_ChallanNo(pibm.getInstitutionmaster().getImId(), pibm.getSubinstitutionmaster().getSimId(), varPIMID, pibm.getSuppliermaster().getSmId(), pibm.getErpmPurchasechallanMaster().getPcmPcmId());
            if (pibmList.size() > 0) {
                message = "Challan Number of this Supplier Already Exists";
            }

            pibmList = pibmDao.findBySupplierName_SupplierInvoiceNo(pibm.getInstitutionmaster().getImId(), pibm.getSubinstitutionmaster().getSimId(), varPIMID, pibm.getSuppliermaster().getSmId(), pibm.getPimSupplierInvoiceNo());
            if (pibmList.size() > 0) {
                message = "Supplier Invoice No Number of this Supplier Already Exists";
            } // if record does not match then save invoice master information and proceed further
            else {
                // if PimPimId() is null then it means we are updating an existing record
                if (pibm.getPimPimId() == null) {
                    pibm.setPimInvoiceRecvdDate(dt.convertStringToDate(getInvoicerecvDate()));
                    pibm.setPimSupplierInvoiceDate(dt.convertStringToDate(getSuplierinvoiceDate()));
                    invoicerecvDate = dt.convertDateToString(dt.convertStringToDate(getInvoicerecvDate()), "dd-MM-yyyy");
                    suplierinvoiceDate = dt.convertDateToString(dt.convertStringToDate(getSuplierinvoiceDate()), "dd-MM-yyyy");
                    if (pibm.getErpmPurchasechallanMaster() == null) {
                        pibm.setErpmPurchasechallanMaster(null);
                    }

                    if (pibm.getErpmPoMaster() == null) {
                        pibm.setErpmPoMaster(null);
                    }

                    pibmDao.save(pibm);

                    message = "Purchase Invoice record saved successfully. ";
                } else {

                    pibmList = pibmDao.findBySupplierName_ChallanNo(pibm.getInstitutionmaster().getImId(), pibm.getSubinstitutionmaster().getSimId(), varPIMID, pibm.getSuppliermaster().getSmId(), pibm.getErpmPurchasechallanMaster().getPcmPcmId());
                    if (pibmList.size() > 0) {
                        message = "Challan Number of this Supplier Already Exists";
                    }

                    pibmList = pibmDao.findBySupplierName_SupplierInvoiceNo(pibm.getInstitutionmaster().getImId(), pibm.getSubinstitutionmaster().getSimId(), varPIMID, pibm.getSuppliermaster().getSmId(), pibm.getPimSupplierInvoiceNo());
                    if (pibmList.size() > 0) {
                        message = "Supplier Invoice No Number of this Supplier Already Exists";
                    }
                    if (pibm.getErpmPurchasechallanMaster()== null) {
                        pibm.setErpmPurchasechallanMaster(null);
                    }

                    if (pibm.getErpmPoMaster() == null) {
                        pibm.setErpmPoMaster(null);
                    }
                    pibm.setPimInvoiceRecvdDate(dt.convertStringToDate(getInvoicerecvDate()));
                    pibm.setPimSupplierInvoiceDate(dt.convertStringToDate(getSuplierinvoiceDate()));
                    invoicerecvDate = dt.convertDateToString(dt.convertStringToDate(getInvoicerecvDate()), "dd-MM-yyyy");
                    suplierinvoiceDate = dt.convertDateToString(dt.convertStringToDate(getSuplierinvoiceDate()), "dd-MM-yyyy");
                    ErpmPurchaseinvoiceMaster pibmnew = pibmDao.findByErpmId(pibm.getPimPimId());
                    pibmnew = pibm;
                    pibmDao.update(pibmnew);
                    message = "Record Updated Successfully";

                }
                // the folowing code is to carry some information to the invoice detail screen
                Default_PO = pibm.getPimPimId();
                Default_ChallanNo = pibm.getPimPimId();
                pibm = pibmDao.findpimPimId(Default_PO);
                pibm = pibmDao.findpimPimId(Default_ChallanNo);

                if (pibm.getErpmPurchasechallanMaster() == null) {
                    podList = podDao.findItemListByPoMasterId(pibm.getErpmPoMaster().getPomPoMasterId());
                    erpmpidList = pidDao.findBypimId(pibm.getPimPimId());
                    prepare_lovs();
                    return SUCCESS;
                }
                if (pibm.getErpmPoMaster() == null) {
                    ppcdList = pcdDao.findBypcmPcmId(pibm.getErpmPurchasechallanMaster().getPcmPcmId());
                    erpmpidList = pidDao.findBypimId(pibm.getPimPimId());
                    prepare_lovs();
                    return "SUCCESS1";
                }
            }
            return "SUCCESS2";
        } catch (Exception e) {

            message = "Exception in Save method -> PurchaseInvoiceAction" + e.getMessage() + " Reported Cause is: " + e.getCause() + "CH :" + pibm.getErpmPurchasechallanMaster() + "PO :" + pibm.getErpmPoMaster();
            return ERROR;

        }
    }

    @Override
    public void validate() {
        try {

            if (pibm.getPimInvoiceType().toString().equals("Invoice Cum Challan") && pibm.getErpmPoMaster().getPomPoMasterId() == null) {
                addFieldError("pibm.erpmPoMaster.pomPoMasterId", "Purchase order No should not be blank");
            }

            if (pibm.getPimInvoiceType().toString().equals("Only Invoice") && pibm.getErpmPurchasechallanMaster().getPcmPcmId() == null) //     message = "VALIDATING  : " + pibm.getPimInvoiceType();
            {
                addFieldError("pibm.erpmPurchasechallanMaster.pcmPcmId", "Purchase Challan No should not be blank");
            }

            if (pibm.getPimInvoiceType() == null || pibm.getPimInvoiceType().isEmpty()) {
                addFieldError("pibm.pimInvoiceType", "Please select InvoiceType from the list");
            }

            if (pibm.getInstitutionmaster().getImId() == null) {
                addFieldError("pibm.institutionmaster.imId", "Please select institution from the list");
            }

            if (pibm.getSubinstitutionmaster().getSimId() == null) {
                addFieldError("pibm.subinstitutionmaster.simId", "Please select Subinsitute from the list");
            }

            if (pibm.getDepartmentmaster().getDmId() == null) {
                addFieldError("pibm.departmentmaster.dmId", "Please select Department from the list");
            }

            if (getInvoicerecvDate() == null) {
                addFieldError("getInvoicerecvDate()", "Please Select Date");
            }

            if (getSuplierinvoiceDate() == null) {
                addFieldError("getSuplierinvoiceDate()", "Please Select Date");
            }

            if (pibm.getPimSupplierInvoiceNo().isEmpty()) {
                addFieldError("pibm.pimSupplierInvoiceNo", "Please Select Supplier Invoice No");
            }

            if (pibm.getSuppliermaster().getSmId() == null) {
                addFieldError("pibm.suppliermaster.smId", "Please Select Supplier");
            }
            prepare_lovs();
        } catch (NullPointerException e) {
        }
    }

    @SkipValidation
    public String SaveInvoiceDetailforPO() throws Exception {

        try {
            Integer TotPOItemQty = 0;
            if (pid.getPidPidId() == null) {
                if (pid.getErpmItemMaster().getErpmimId() == null) {
                    message = "Please select Item first.";
                } else {

                    pid.setErpmPurchaseinvoiceMaster(pibm);

                    if (pid.getPidQuantity().intValue() < 1) {

                        message = "Quantity Shouldn't Zero.";
                        podList = podDao.findItemListByPoMasterId(pibm.getErpmPoMaster().getPomPoMasterId());
                        return "SUCCESS1";
                    }
                    poDetail = podDao.findBy_pomPoMasterId_ItemId(pid.getErpmPurchaseinvoiceMaster().getErpmPoMaster().getPomPoMasterId(), pid.getErpmItemMaster().getErpmimId());

                    erpmpidList = pidDao.findBy_pomPoMasterId_ItemId_pidPidId(pibm.getErpmPoMaster().getPomPoMasterId(), pid.getErpmItemMaster().getErpmimId(), 0);

                    if (erpmpidList.size() > 0) {
                        for (int i = 0; i < erpmpidList.size(); i++) {
                            TotPOItemQty = TotPOItemQty + erpmpidList.get(i).getPidQuantity().intValue();
                        }
                    }
                    if (poDetail.getPodQuantity().intValue() < (pid.getPidQuantity().intValue() + TotPOItemQty)) {

                        message = "You cannot receive more than the PO Quantity";

                        podList = podDao.findItemListByPoMasterId(pibm.getErpmPoMaster().getPomPoMasterId());

                        erpmpidList = pidDao.findBypimId(pibm.getPimPimId());
                        return "SUCCESS1";
                    } else {

                        if (getChecked() == true) {
                            pid.setPcdQNQChecked('Y');
                        } else {
                            pid.setPcdQNQChecked('N');
                        }

                        if (getVerified() == true) {
                            pid.setPcdQNQVerified('Y');
                        } else {
                            pid.setPcdQNQVerified('N');
                        }

                        pid.setPcdVerifiedBy(getCheckAndVerifiedRemarks());
                        pid.setPidDiscount(poDetail.getPodDiscount());
                        pid.setPidRate(poDetail.getPodRate());

                        pidDao.save(pid);

                        Integer max = stockRecDao.findMaxSrNo(pid.getErpmItemMaster().getErpmimId(),
                                Short.valueOf(getSession().getAttribute("imId").toString()),
                                Integer.valueOf(getSession().getAttribute("simId").toString()),
                                Integer.valueOf(getSession().getAttribute("dmId").toString()));

                        item = itemmDao.findByErpmimId(pid.getErpmItemMaster().getErpmimId());

                        if (item.getErpmimSerialNoApplicable() == 'Y') {

                            for (int i = 0; i < pid.getPidQuantity().intValue(); i++) {
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
                                pibm = pibmDao.findpimPimId(pibm.getPimPimId());

                                stockRec.setInstitutionmaster(pibm.getInstitutionmaster());
                                stockRec.setSubinstitutionmaster(pibm.getSubinstitutionmaster());
                                stockRec.setDepartmentmaster(pibm.getDepartmentmaster());
                                stockRec.setSuppliermaster(pibm.getSuppliermaster());
                                stockRec.setErpmItemMaster(pid.getErpmItemMaster());
                                stockRec.setStInStockSince(pibm.getPimInvoiceRecvdDate());

                                stockRec.setStInvoiceNo(pibm.getPimSupplierInvoiceNo());
                                stockRec.setStInvoiceDate(pibm.getPimSupplierInvoiceDate());
                                stockRec.setStPoNo(String.valueOf(pibm.getErpmPoMaster().getPomPoNo()));
                                stockRec.setStPoDate(pibm.getErpmPoMaster().getPomPoDate());
                                stockRec.setStChallanNo(pibm.getPimSupplierInvoiceNo());
                                stockRec.setStChallanDate(pibm.getPimSupplierInvoiceDate());
//
                                stockRec.setStQuantity(BigDecimal.ONE);
                                stockRec.setStUnitRate(pid.getPidRate());
                                stockRec.setStStockSerialNo(serialNoCode);

                                stockRecDao.save(stockRec);

                                setPidPidId(pid.getPidPidId());
                                message = "Program record saved successfully.";
                            }
                        } //  serial no applicable if ends
                        else {   //  serial no applicable else starts
                            pibm = pibmDao.findpimPimId(pibm.getPimPimId());

                            stockRec.setInstitutionmaster(pibm.getInstitutionmaster());
                            stockRec.setSubinstitutionmaster(pibm.getSubinstitutionmaster());
                            stockRec.setDepartmentmaster(pibm.getDepartmentmaster());
                            stockRec.setSuppliermaster(pibm.getSuppliermaster());
                            stockRec.setErpmItemMaster(pid.getErpmItemMaster());
                            stockRec.setStInStockSince(pibm.getPimInvoiceRecvdDate());

                            stockRec.setStInvoiceNo(pibm.getPimSupplierInvoiceNo());
                            stockRec.setStInvoiceDate(pibm.getPimSupplierInvoiceDate());
                            stockRec.setStPoNo(String.valueOf(pibm.getErpmPoMaster().getPomPoNo()));
                            stockRec.setStPoDate(pibm.getErpmPoMaster().getPomPoDate());
                            stockRec.setStChallanNo(pibm.getPimSupplierInvoiceNo());
                            stockRec.setStChallanDate(pibm.getPimSupplierInvoiceDate());

                            stockRec.setStQuantity(pid.getPidQuantity());
                            stockRec.setStUnitRate(pid.getPidRate());
                            stockRecDao.save(stockRec);
                        }  //  serial no applicable else ends
                    }
                } //  Item id <> null
            } else //  pid.getPidPidId() <> null
            {

                ErpmPurchaseinvoiceDetail newpid = pidDao.findByErpId(pid.getPidPidId());
                newpid = pid;
                newpid.setErpmPurchaseinvoiceMaster(pibm);

                poDetail = podDao.findBy_pomPoMasterId_ItemId(pid.getErpmPurchaseinvoiceMaster().getErpmPoMaster().getPomPoMasterId(), pid.getErpmItemMaster().getErpmimId());

                erpmpidList = pidDao.findBy_pomPoMasterId_ItemId_pidPidId(pibm.getErpmPoMaster().getPomPoMasterId(), pid.getErpmItemMaster().getErpmimId(), 0);

                if (erpmpidList.size() > 0) {
                    for (int i = 0; i < erpmpidList.size(); i++) {
                        TotPOItemQty = TotPOItemQty + erpmpidList.get(i).getPidQuantity().intValue();
                    }
                }
                if (poDetail.getPodQuantity().intValue() < (pid.getPidQuantity().intValue() + TotPOItemQty)) {

                    message = "You cannot receive more than the PO Quantity";

                    podList = podDao.findItemListByPoMasterId(pibm.getErpmPoMaster().getPomPoMasterId());

                    erpmpidList = pidDao.findBypimId(pibm.getPimPimId());
                    return "SUCCESS1";

                } else {
                    newpid.setPidDiscount(poDetail.getPodDiscount());
                    newpid.setPidRate(poDetail.getPodRate());

                    pidDao.update(newpid);
                    podList = podDao.findItemListByPoMasterId(pibm.getErpmPoMaster().getPomPoMasterId());
                    message = "Record Updated Successfully";
                }

            }


            Default_ItemName = pid.getPidPidId();
            pid = pidDao.findByErpId(Default_ItemName);

            poTaxList = poTaxDao.findByPOMasterID_ItemID(pibm.getErpmPoMaster().getPomPoMasterId(), pid.getErpmItemMaster().getErpmimId());

            pitaxList = pitaxDao.findBypidPidId(pid.getPidPidId());
            if (pitaxList.size() > 0) {
                for (int j = 0; j < pitaxList.size(); j++) {
                    pitaxDao.delete(pitaxList.get(j));
                }
            }

            Integer x = poTaxList.size();
            for (int j = 0; j < x; j++) {
                pitax.setErpmPurchaseinvoiceDetail(pid);
                pitax.setPitTaxName(poTaxList.get(j).getPotTaxName());
                pitax.setPitTaxPercent(poTaxList.get(j).getPotTaxPercent());
                pitax.setPitTaxOnValuePercent(poTaxList.get(j).getPotTaxOnValuePercent());
                pitax.setPitSurchargePercent(poTaxList.get(j).getPotSurchargePercent());
                pitaxDao.save(pitax);
            }

            pitaxList = pitaxDao.findBypidPidId(pid.getPidPidId());

            setVarPidpidId(pid.getPidPidId());


            return SUCCESS;
        } catch (Exception e) {

            if (e.getCause().toString().contains("PID_PIMID_ITEMID_UNIQUE")) {
                message = "The selected Items  already exists for Your Purchase Invoice Detail Please select other items";
            } else {
                message = "Exception in save method-> Purchase Invoice Detail" + e.getMessage() + " Reported Cause is: " + e.getCause();
            }
            return ERROR;
        }
    }

    public String BackToPurchaseInvoiceMaster() throws Exception {
        try {

            pibm = pibmDao.findByErpmId(pibm.getPimPimId());
            DateUtilities dt = new DateUtilities();
            invoicerecvDate = dt.convertDateToString(pibm.getPimInvoiceRecvdDate(), "dd-MM-yyyy");
            suplierinvoiceDate = dt.convertDateToString(pibm.getPimSupplierInvoiceDate(), "dd-MM-yyyy");
            prepare_lovs();

            return SUCCESS;
        } catch (Exception e) {
            message = message + e.getMessage();
            return ERROR;
        }
    }

    public String BackToPurchaseInvoiceMasterChaln() throws Exception {
        try {

            pibm = pibmDao.findByErpmId(pibm.getPimPimId());
            DateUtilities dt = new DateUtilities();
            invoicerecvDate = dt.convertDateToString(pibm.getPimInvoiceRecvdDate(), "dd-MM-yyyy");
            suplierinvoiceDate = dt.convertDateToString(pibm.getPimSupplierInvoiceDate(), "dd-MM-yyyy");
            prepare_lovs();

            return SUCCESS;
        } catch (Exception e) {
            message = message + e.getMessage();
            return ERROR;
        }
    }

    public String BackToPurchaseInvoiceDetail() throws Exception {
        try {
            pid = pidDao.findByErpId(pid.getPidPidId());
            pibm = pibmDao.findpimPimId(pid.getErpmPurchaseinvoiceMaster().getPimPimId());
            if (pibm.getErpmPurchasechallanMaster() == null) {
                podList = podDao.findItemListByPoMasterId(pibm.getErpmPoMaster().getPomPoMasterId());
                erpmpidList = pidDao.findBypimId(pibm.getPimPimId());
            }
            if (pibm.getErpmPoMaster() == null) {

                ppcdList = pcdDao.findBypcmPcmId(pibm.getErpmPurchasechallanMaster().getPcmPcmId());
                erpmpidList = pidDao.findBypimId(pibm.getPimPimId());
                return "SUCCESS1";
            }
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in BackToPurchaseInvoiceDetail method-> Purchase Invoice Detail" + e.getMessage() + " Reported Cause is: " + e.getCause();

            return ERROR;
        }
    }

    public String ViewSerialInfo() throws Exception {

        try {
            pid = pidDao.findByErpId(getPidPidId());

            item = itemmDao.findByErpmimId(pid.getErpmItemMaster().getErpmimId());
            if (item.getErpmimSerialNoApplicable() == 'Y') {

                viewStockRecList = viewStockRecDao.findByInvoiceNO_n_ItemId(pid.getErpmPurchaseinvoiceMaster().getPimSupplierInvoiceNo(), pid.getErpmItemMaster().getErpmimId());
                setPrdNoReadOnly(true);
                setVarPidpidId(getPidPidId());
                return SUCCESS;
            }

            return SUCCESS;
        } catch (Exception e) {
            return ERROR;
        }

    }

    public String ViewTaxInfoPO() throws Exception {
        try {

            pid = pidDao.findByErpId(getPidPidId());

            pibm = pibmDao.findpimPimId(pid.getErpmPurchaseinvoiceMaster().getPimPimId());

            pitaxList = pitaxDao.findBypidPidId(pid.getPidPidId());
            return SUCCESS;
        } catch (Exception e) {

            message = "Exception in ViewTaxInfoPO method-> Purchase Invoice Detail" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String ViewTaxInfoChallan() throws Exception {
        try {
            pid = pidDao.findByErpId(getPidPidId());

            pibm = pibmDao.findpimPimId(pid.getErpmPurchaseinvoiceMaster().getPimPimId());

            pitaxList = pitaxDao.findBypidPidId(pid.getPidPidId());
            return SUCCESS;
        } catch (Exception e) {

            message = "Exception in ViewTaxInfoChallan method-> Purchase Invoice Detail" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String EditPOStockInfo() throws Exception {
        try {

            pid = pidDao.findByErpId(getVarPidpidId());
            stockRec = stockRecDao.findbystid(getVSRID());
            viewStockRecList = viewStockRecDao.findByInvoiceNO_n_ItemId(stockRec.getStInvoiceNo(), stockRec.getErpmItemMaster().getErpmimId());

            setVarPidpidId(pid.getPidPidId());
            return SUCCESS;
        } catch (Exception e) {

            message = "Exception in EditPurchaseChallanStockInfo method -> ManagePurchaseChallanAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }

    }

    public String DeletePOStockInfo() throws Exception {

        try {

            stockRec = stockRecDao.findbystid(getVSRID());
            Integer stockId = eisdDao.findByStockSerialId(stockRec.getStId());
            pid = pidDao.findByErpId(getVarPidpidId());
            if (stockId == 0) {
                if (pid.getPidQuantity().intValue() > 1) {

                    stockRecDao.delete(stockRec);

                    pid.setPidQuantity(pid.getPidQuantity().subtract(BigDecimal.ONE));
                    pidDao.update(pid);

                    viewStockRecList = viewStockRecDao.findByInvoiceNO_n_ItemId(stockRec.getStInvoiceNo(), stockRec.getErpmItemMaster().getErpmimId());

                    return SUCCESS;
                }
                if (pid.getPidQuantity().intValue() == 1) {

                    stockRecDao.delete(stockRec);

                    pid.setPidQuantity(pid.getPidQuantity().subtract(BigDecimal.ONE));
                    pidDao.update(pid);
                    pidDao.delete(pid);
                    pibm = pibmDao.findpimPimId(pid.getErpmPurchaseinvoiceMaster().getPimPimId());
                    Default_PO = pibm.getPimPimId();
                    pibm = pibmDao.findpimPimId(Default_PO);
                    podList = podDao.findItemListByPoMasterId(pibm.getErpmPoMaster().getPomPoMasterId());
                    erpmpidList = pidDao.findBypimId(pibm.getPimPimId());
                    return "SUCCESS1";
                }

            } else if (stockId == 1) {

                viewStockRecList = viewStockRecDao.findByInvoiceNO_n_ItemId(stockRec.getStInvoiceNo(), stockRec.getErpmItemMaster().getErpmimId());
                setPrdNoReadOnly(true);
                message = "You cannot delete the Item, Item is issued";
            }
            return SUCCESS;
        } catch (Exception e) {

            message = "Exception in EditPurchaseChallanStockInfo method -> ManagePurchaseChallanAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String SavePOStokInfo() throws Exception {

        try {
            stockRec = stockRecDao.findbystid(getVSRID());
            stockRec.setStProductNo(getProductNo());
            stockRecDao.update(stockRec);
            viewStockRecList = viewStockRecDao.findByInvoiceNO_n_ItemId(stockRec.getStInvoiceNo(), stockRec.getErpmItemMaster().getErpmimId());

            pid = pidDao.findByErpId(getVarPidpidId());
            
            setVarPidpidId(getVarPidpidId());
            message = "" + getVarPidpidId();
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in EditPurchaseChallanStockInfo method ->" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String AddPOStokInfo() throws Exception {
        try {

            Integer TotPOItemQty = 0;

            pid = pidDao.findByErpId(getVarPidpidId());

            pibm = pibmDao.findpimPimId(pid.getErpmPurchaseinvoiceMaster().getPimPimId());

            poDetail = podDao.findBy_pomPoMasterId_ItemId(pid.getErpmPurchaseinvoiceMaster().getErpmPoMaster().getPomPoMasterId(), pid.getErpmItemMaster().getErpmimId());

            erpmpidList = pidDao.findBy_pomPoMasterId_ItemId_pidPidId(pibm.getErpmPoMaster().getPomPoMasterId(), pid.getErpmItemMaster().getErpmimId(), pid.getPidPidId());

            if (erpmpidList.size() > 0) {
                for (int i = 0; i < erpmpidList.size(); i++) {
                    TotPOItemQty = TotPOItemQty + erpmpidList.get(i).getPidQuantity().intValue();
                }
            }

            if (poDetail.getPodQuantity().intValue() <= (pid.getPidQuantity().intValue() + TotPOItemQty)) {

                message = "You cannot receive more than the PO Quantity";

            } else {
                Integer max = stockRecDao.findMaxSrNo(pid.getErpmItemMaster().getErpmimId(),
                        pibm.getInstitutionmaster().getImId(),
                        pibm.getSubinstitutionmaster().getSimId(),
                        pibm.getDepartmentmaster().getDmId());

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

                stockRec.setInstitutionmaster(pibm.getInstitutionmaster());
                stockRec.setSubinstitutionmaster(pibm.getSubinstitutionmaster());
                stockRec.setDepartmentmaster(pibm.getDepartmentmaster());
                stockRec.setSuppliermaster(pibm.getSuppliermaster());
                stockRec.setErpmItemMaster(pid.getErpmItemMaster());
                stockRec.setStInStockSince(pibm.getPimInvoiceRecvdDate());

                stockRec.setStInvoiceNo(pibm.getPimSupplierInvoiceNo());
                stockRec.setStInvoiceDate(pibm.getPimSupplierInvoiceDate());
                stockRec.setStPoNo(String.valueOf(pibm.getErpmPoMaster().getPomPoNo()));
                stockRec.setStPoDate(pibm.getErpmPoMaster().getPomPoDate());
                stockRec.setStChallanNo(pibm.getPimSupplierInvoiceNo());
                stockRec.setStChallanDate(pibm.getPimSupplierInvoiceDate());

                stockRec.setStQuantity(BigDecimal.ONE);
                stockRec.setStUnitRate(pid.getPidRate());
                stockRec.setStStockSerialNo(serialNoCode);

                stockRecDao.save(stockRec);
                pid.setPidQuantity(pid.getPidQuantity().add(BigDecimal.ONE));
                pidDao.update(pid);
            }

            viewStockRecList = viewStockRecDao.findByInvoiceNO_n_ItemId(pid.getErpmPurchaseinvoiceMaster().getPimSupplierInvoiceNo(), pid.getErpmItemMaster().getErpmimId());

            return SUCCESS;

        } catch (Exception e) {

            message = "Exception in AddPOStokInfo method ->" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String BackPOStokInfo() throws Exception {
        try {

                pid = pidDao.findByErpId(getVarPidpidId());
                pibm = pibmDao.findpimPimId(pid.getErpmPurchaseinvoiceMaster().getPimPimId());


                if (pibm.getErpmPurchasechallanMaster() == null) {
                    Default_PO = pibm.getPimPimId();
                    pibm = pibmDao.findpimPimId(Default_PO);
                    podList = podDao.findItemListByPoMasterId(pibm.getErpmPoMaster().getPomPoMasterId());
                    erpmpidList = pidDao.findBypimId(pibm.getPimPimId());
                    
                    return SUCCESS;
                }
                if (pibm.getErpmPoMaster() == null) {
                    Default_ChallanNo = pibm.getPimPimId();
                    pibm = pibmDao.findpimPimId(Default_ChallanNo);
                    ppcdList = pcdDao.findBypcmPcmId(pibm.getErpmPurchasechallanMaster().getPcmPcmId());
                    erpmpidList = pidDao.findBypimId(pibm.getPimPimId());
                   
                    return "SUCCESS1";
                }
        
            return SUCCESS;
        } catch (Exception e) {

            message = "Exception in EditPurchaseChallanStockInfo method -> ManagePurchaseChallanAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String SaveInvoiceDetailForChallan() throws Exception {

        try {
            Integer TotPOItemQty = 0;
            Integer i = 0;

            if (pid.getPidPidId() == null) {

                if (pid.getErpmItemMaster().getErpmimId() == null) {
                    message = "Please select Item first.";
                } else {
                    pid.setErpmPurchaseinvoiceMaster(pibm);

                    if (pid.getPidQuantity().intValue() < 1) {

                        message = "Quantity Shouldn't Zero.";
                        ppcdList = pcdDao.findBypcmPcmId(pibm.getErpmPurchasechallanMaster().getPcmPcmId());
                        return "SUCCESS1";
                    }

                    pcm = pcmDao.findBypcmPcmId(pibm.getErpmPurchasechallanMaster().getPcmPcmId());

                    i = pcm.getErpmPoMaster().getPomPoMasterId();

                    poDetail = podDao.findBy_pomPoMasterId_ItemId(i, pid.getErpmItemMaster().getErpmimId());

                    challanDetail = pcdDao.findBypcmPcmId_n_ItemId(pibm.getErpmPurchasechallanMaster().getPcmPcmId(),
                            pid.getErpmItemMaster().getErpmimId());

                    erpmpidList = pidDao.findBy_pcmPcmId_ItemId_pidPidId(pibm.getErpmPurchasechallanMaster().getPcmPcmId(),
                            pid.getErpmItemMaster().getErpmimId(), 0);

                    if (erpmpidList.size() > 0) {
                        for (int j = 0; j < erpmpidList.size(); j++) {
                            TotPOItemQty = TotPOItemQty + erpmpidList.get(j).getPidQuantity().intValue();
                        }
                    }

                    if (challanDetail.getPcdRecvQuantity().intValue() < (pid.getPidQuantity().intValue() + TotPOItemQty)) {

                        message = "You cannot receive more than the PO Quantity";

                        ppcdList = pcdDao.findBypcmPcmId(pibm.getErpmPurchasechallanMaster().getPcmPcmId());

                        erpmpidList = pidDao.findBypimId(pibm.getPimPimId());
                        return "SUCCESS1";
                    } else {

                        if (getChecked() == true) {
                            pid.setPcdQNQChecked('Y');
                        } else {
                            pid.setPcdQNQChecked('N');
                        }

                        if (getVerified() == true) {
                            pid.setPcdQNQVerified('Y');
                        } else {
                            pid.setPcdQNQVerified('N');
                        }
//
                        pid.setPcdVerifiedBy(getCheckAndVerifiedRemarks());
                        pid.setPidDiscount(poDetail.getPodDiscount());
                        pid.setPidRate(poDetail.getPodRate());
                        pidDao.save(pid);
                        message = "Program record saved successfully.";
                    }
                }
            } else {

                ErpmPurchaseinvoiceDetail newpid = pidDao.findByErpId(pid.getPidPidId());

                newpid = pid;
                newpid.setErpmPurchaseinvoiceMaster(pibm);

                pcm = pcmDao.findBypcmPcmId(pibm.getErpmPurchasechallanMaster().getPcmPcmId());
                i = pcm.getErpmPoMaster().getPomPoMasterId();

                pid = pidDao.findByErpId(pid.getPidPidId());
                //we using this pid= pidDao.findByErpId(pid.getPidPidId()) becoz pid.getPidPidId() shows only id not full data..

                poDetail = podDao.findBy_pomPoMasterId_ItemId(i, pid.getErpmItemMaster().getErpmimId());

                challanDetail = pcdDao.findBypcmPcmId_n_ItemId(pibm.getErpmPurchasechallanMaster().getPcmPcmId(), pid.getErpmItemMaster().getErpmimId());

                erpmpidList = pidDao.findBy_pcmPcmId_ItemId_pidPidId(pibm.getErpmPurchasechallanMaster().getPcmPcmId(),
                        pid.getErpmItemMaster().getErpmimId(), 0);

                if (erpmpidList.size() > 0) {
                    for (int j = 0; j < erpmpidList.size(); j++) {
                        TotPOItemQty = TotPOItemQty + erpmpidList.get(j).getPidQuantity().intValue();
                    }
                }
                if (challanDetail.getPcdRecvQuantity().intValue() < (pid.getPidQuantity().intValue() + TotPOItemQty)) {

                    message = "You cannot receive more than the PO Quantity";

                    ppcdList = pcdDao.findBypcmPcmId(pibm.getErpmPurchasechallanMaster().getPcmPcmId());

                    erpmpidList = pidDao.findBypimId(pibm.getPimPimId());
                    return "SUCCESS1";
                }

                newpid.setPidDiscount(poDetail.getPodDiscount());
                newpid.setPidRate(poDetail.getPodRate());

                pidDao.update(newpid);

                message = "Record Updated Successfully";

            }
            Default_ItemName = pid.getPidPidId();

            pid = pidDao.findByErpId(Default_ItemName);

            poTaxList = poTaxDao.findByPOMasterID_ItemID(i, pid.getErpmItemMaster().getErpmimId());

            pitaxList = pitaxDao.findBypidPidId(pid.getPidPidId());
            if (pitaxList.size() > 0) {
                for (int j = 0; j < pitaxList.size(); j++) {
                    pitaxDao.delete(pitaxList.get(j));
                }
            }

            Integer x = poTaxList.size();
            for (int j = 0; j < x; j++) {
                pitax.setErpmPurchaseinvoiceDetail(pid);
                pitax.setPitTaxName(poTaxList.get(j).getPotTaxName());
                pitax.setPitTaxPercent(poTaxList.get(j).getPotTaxPercent());
                pitax.setPitTaxOnValuePercent(poTaxList.get(j).getPotTaxOnValuePercent());
                pitax.setPitSurchargePercent(poTaxList.get(j).getPotSurchargePercent());
                pitaxDao.save(pitax);
            }

            pitaxList = pitaxDao.findBypidPidId(pid.getPidPidId());
            setVarPidpidId(getPidPidId());
            challanDetail = pcdDao.findBypcmPcmId_n_ItemId(pibm.getErpmPurchasechallanMaster().getPcmPcmId(),
                    pid.getErpmItemMaster().getErpmimId());
            stockRecList = stockRecDao.findPCDId(challanDetail.getPcdPcdId());
            Integer k = stockRecList.size();
            for (int j = 0; j < k; j++) {
                ErpmStockReceived newstockRec = stockRecDao.findbystid(stockRecList.get(j).getStId());
                newstockRec.setStInvoiceNo(pibm.getPimSupplierInvoiceNo());
                newstockRec.setStUnitRate(pid.getPidRate());
                DateUtilities dt = new DateUtilities();
                newstockRec.setStInvoiceDate(dt.convertStringToDate(getSuplierinvoiceDate()));
                stockRecDao.update(newstockRec);
            }

            return SUCCESS;
        } catch (Exception e) {
            if (e.getCause().toString().contains("Duplicate Entry")) {
                message = "The selected Items  already exists for Your Purchase Invoice Detail Please select other items";
            } else {
                message = "Exception in save method-> Purchase Invoice Detail" + e.getMessage() + " Reported Cause is: " + e.getCause();
            }
            return ERROR;

        }
    }

    @SkipValidation
    public String showPOreportInInvoice() throws Exception {
        HashMap hm = new HashMap();

        // Get System properties
        Properties properties = System.getProperties();

        // Get the path separator symbol, which is unfortunatly different, in different OS platform.
        String pathSeparator = properties.getProperty("file.separator");

        pathSeparator = pathSeparator + pathSeparator;
        String repPath = "pico" + pathSeparator + "PrePurchase" + pathSeparator + "Reports" + pathSeparator + "Purchase_Order.jasper";

        String fileName = getSession().getServletContext().getRealPath(repPath);

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

            if (pibm.getErpmPoMaster().getPomPoMasterId() == null) {
            } else {
                whereCondition = "erpm_po_master.`POM_PO_Master_ID` = " + pibm.getErpmPoMaster().getPomPoMasterId();
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

    public String PrintReceiving() throws Exception {


        HashMap hm = new HashMap();

        // Get System properties
        Properties properties = System.getProperties();

        // Get the path separator symbol, which is unfortunatly different, in different OS platform.
        String pathSeparator = properties.getProperty("file.separator");

        pathSeparator = pathSeparator + pathSeparator;
        String repPath = "pico" + pathSeparator + "Purchase" + pathSeparator + "Reports" + pathSeparator + "Material_Received_Note_From_Invoice.jasper";

        String fileName = getSession().getServletContext().getRealPath(repPath);

        String whereCondition = "";

        try {

            Locale locale = ActionContext.getContext().getLocale();
            ResourceBundle bundle = ResourceBundle.getBundle("pico", locale);
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bundle.getString("dbName"), bundle.getString("mysqlUserName"), bundle.getString("mysqlPassword")); 

            HttpServletResponse response = ServletActionContext.getResponse();
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Content-Disposition", "attachment; filename=Material_Received_Note_From_Invoice.pdf");
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            if (getPimPimId() == null) {
                whereCondition = "erpm_purchaseinvoice_master.`PIM_PIM_ID` = 0";
            } else {
                whereCondition = "erpm_purchaseinvoice_master.`PIM_PIM_ID` = " + getPimPimId();
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

    public String CheckedandVerifiedforChallan() throws Exception {

        try {

            pid = pidDao.findByErpId(getPidPidId());

            Default_ChallanNo = pid.getErpmPurchaseinvoiceMaster().getPimPimId();
            pibm = pibmDao.findpimPimId(Default_ChallanNo);

            ppcdList = pcdDao.findBypcmPcmId(pibm.getErpmPurchasechallanMaster().getPcmPcmId());

            erpmpidList = pidDao.findBypimId(pibm.getPimPimId());

            if (pid.getPcdQNQVerified() == 'Y') {
                setVerified(true);
            } else {
                setVerified(false);
            }
            if (pid.getPcdQNQChecked() == 'Y') {
                setChecked(true);
            } else {
                setChecked(false);
            }

            setCheckAndVerifiedRemarks(pid.getPcdVerifiedBy());
            setPidDisable(true);
            setBtnCheckAndVerifySave(false);
            setVarPidpidId(getPidPidId());
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in CheckedandVerifiedforChallan method" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String CheckedandVerifiedforPO() throws Exception {

        try {
            pid = pidDao.findByErpId(getPidPidId());

            Default_PO = pid.getErpmPurchaseinvoiceMaster().getPimPimId();

            pibm = pibmDao.findpimPimId(Default_PO);

            podList = podDao.findItemListByPoMasterId(pibm.getErpmPoMaster().getPomPoMasterId());
            erpmpidList = pidDao.findBypimId(pibm.getPimPimId());

            if (pid.getPcdQNQVerified() == 'Y') {
                setVerified(true);
            } else {
                setVerified(false);
            }
            if (pid.getPcdQNQChecked() == 'Y') {
                setChecked(true);
            } else {
                setChecked(false);
            }

            setCheckAndVerifiedRemarks(pid.getPcdVerifiedBy());
            setPidDisable(true);
            setBtnCheckAndVerifySave(false);
            setVarPidpidId(getPidPidId());

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in CheckedandVerifiedforPO method" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String SaveCheckAndVerifyforChallan() throws Exception {

        try {

            pid = pidDao.findByErpId(getVarPidpidId());

            Default_ChallanNo = pid.getErpmPurchaseinvoiceMaster().getPimPimId();
            pibm = pibmDao.findpimPimId(Default_ChallanNo);

            ErpmPurchaseinvoiceDetail pid1 = pidDao.findByErpId(pid.getPidPidId());

            if (getChecked() == true) {
                pid1.setPcdQNQChecked('Y');
            } else {
                pid1.setPcdQNQChecked('N');
            }

            if (getVerified() == true) {
                pid1.setPcdQNQVerified('Y');
            } else {
                pid1.setPcdQNQVerified('N');
            }

            pid1.setPcdVerifiedBy(getCheckAndVerifiedRemarks());
            pidDao.update(pid1);

            erpmpidList = pidDao.findBypimId(pibm.getPimPimId());
            setChecked(false);
            setVerified(false);
            setCheckAndVerifiedRemarks("");
            pid.setPidQuantity(null);
            message = "Check and Verify For Challan updated successfully ";
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in SaveCheckAndVerifyforChallan method" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String SaveCheckAndVerifyforPO() throws Exception {

        try {

            pid = pidDao.findByErpId(getVarPidpidId());

            Default_PO = pid.getErpmPurchaseinvoiceMaster().getPimPimId();

            pibm = pibmDao.findpimPimId(Default_PO);

            ErpmPurchaseinvoiceDetail pid1 = pidDao.findByErpId(pid.getPidPidId());

            if (getChecked() == true) {
                pid1.setPcdQNQChecked('Y');
            } else {
                pid1.setPcdQNQChecked('N');
            }

            if (getVerified() == true) {
                pid1.setPcdQNQVerified('Y');
            } else {
                pid1.setPcdQNQVerified('N');
            }


            pid1.setPcdVerifiedBy(getCheckAndVerifiedRemarks());
            pidDao.update(pid1);

            erpmpidList = pidDao.findBypimId(pibm.getPimPimId());
            setChecked(false);
            setVerified(false);
            setCheckAndVerifiedRemarks("");
            pid.setPidQuantity(null);
            message = "Check and Verify For PO updated successfully ";


            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in SaveCheckAndVerifyforPO method" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String GotoDetail() throws Exception {
        DateUtilities dt = new DateUtilities();

        try {
            pibm = pibmDao.findpimPimId(getPimPimId());

            if (pibm.getErpmPurchasechallanMaster() == null) {
                podList = podDao.findItemListByPoMasterId(pibm.getErpmPoMaster().getPomPoMasterId());
                erpmpidList = pidDao.findBypimId(pibm.getPimPimId());
                setSuplierinvoiceDate(dt.convertDateToString(pibm.getPimSupplierInvoiceDate(), "dd-MM-yyyy"));
                return SUCCESS;
            }
            ppcdList = pcdDao.findBypcmPcmId(pibm.getErpmPurchasechallanMaster().getPcmPcmId());
            erpmpidList = pidDao.findBypimId(pibm.getPimPimId());
            setSuplierinvoiceDate(dt.convertDateToString(pibm.getPimSupplierInvoiceDate(), "dd-MM-yyyy"));
            return "SUCCESS1";

        } catch (Exception e) {
            message = "Exception in GotoDetail method" + e.getMessage() + " Reported Cause is: " + e.getCause();
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


            whereCondition = "gfr_program_mapping.`GPM_Program_ID` = 32";

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
