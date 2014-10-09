/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PrePurchase;

import java.util.*;
import org.apache.struts2.interceptor.validation.SkipValidation;
import pojo.hibernate.*;
import utils.DateUtilities;
import utils.DevelopmentSupport;

/**
 *
 * @author Saeed
 */
public class TenderScheduleAction extends DevelopmentSupport {

    private String message;
    private ErpmTenderSchedule tenschdl;
    private ErpmTenderScheduleDetail tenschdlDet;
    short i = 23;
    private String schdDate;
    private String strhr;
    private String strmin;
    private String strtime;
    private Integer tenschlId;
    private Integer tenschlDetId;
    private static Integer default_TenderNo;
    private Boolean tsdDisable;
    private static Integer vartscTscId;
    private List<ErpmTenderSchedule> tenschdlList = new ArrayList<ErpmTenderSchedule>();
    private ErpmTenderScheduleDAO tenschdlDao = new ErpmTenderScheduleDAO();
    private List<ErpmTenderScheduleDetail> tenschdlDetList = new ArrayList<ErpmTenderScheduleDetail>();
    private List<ErpmTenderScheduleDetail> tenschdlDetList1 = new ArrayList<ErpmTenderScheduleDetail>();
    private ErpmTenderScheduleDetailDAO tenschdlDetDao = new ErpmTenderScheduleDetailDAO();
    private List<Institutionmaster> imList = new ArrayList<Institutionmaster>();
    private InstitutionmasterDAO imDao = new InstitutionmasterDAO();
    private List<Subinstitutionmaster> simList = new ArrayList<Subinstitutionmaster>();
    private SubinstitutionmasterDAO simDao = new SubinstitutionmasterDAO();
    private List<Departmentmaster> dmList = new ArrayList<Departmentmaster>();
    private DepartmentmasterDAO dmDao = new DepartmentmasterDAO();
    private List<ErpmTenderMaster> tnoList = new ArrayList<ErpmTenderMaster>();
    private ErpmTenderMasterDAO tnoDao = new ErpmTenderMasterDAO();
    private List<ErpmGenMaster> gmIdList = new ArrayList<ErpmGenMaster>();
    private ErpmGenMasterDao gmDao = new ErpmGenMasterDao();

    public static Integer getDefault_TenderNo() {
        return default_TenderNo;
    }

    public static void setDefault_TenderNo(Integer default_TenderNo) {
        TenderScheduleAction.default_TenderNo = default_TenderNo;
    }

    public static Integer getVartscTscId() {
        return vartscTscId;
    }

    public static void setVartscTscId(Integer vartscTscId) {
        TenderScheduleAction.vartscTscId = vartscTscId;
    }

    public Boolean getTsdDisable() {
        return tsdDisable;
    }

    public void setTsdDisable(Boolean tsdDisable) {
        this.tsdDisable = tsdDisable;
    }

    public Integer getTenschlDetId() {
        return tenschlDetId;
    }

    public void setTenschlDetId(Integer tenschlDetId) {
        this.tenschlDetId = tenschlDetId;
    }

    public List<ErpmTenderScheduleDetail> getTenschdlDetList() {
        return tenschdlDetList;
    }

    public void setTenschdlDetList(List<ErpmTenderScheduleDetail> tenschdlDetList) {
        this.tenschdlDetList = tenschdlDetList;
    }

    public ErpmTenderScheduleDetail getTenschdlDet() {
        return tenschdlDet;
    }

    public void setTenschdlDet(ErpmTenderScheduleDetail tenschdlDet) {
        this.tenschdlDet = tenschdlDet;
    }

    public Integer getTenschlId() {
        return tenschlId;
    }

    public void setTenschlId(Integer tenschlId) {
        this.tenschlId = tenschlId;
    }

    public List<ErpmTenderSchedule> getTenschdlList() {
        return tenschdlList;
    }

    public void setTenschdlList(List<ErpmTenderSchedule> tenschdlList) {
        this.tenschdlList = tenschdlList;
    }

    public String getStrhr() {
        return strhr;
    }

    public void setStrhr(String strhr) {
        this.strhr = strhr;
    }

    public String getStrmin() {
        return strmin;
    }

    public void setStrmin(String strmin) {
        this.strmin = strmin;
    }

    public String getStrtime() {
        return strtime;
    }

    public void setStrtime(String strtime) {
        this.strtime = strtime;
    }

    public String getSchdDate() {
        return schdDate;
    }

    public void setSchdDate(String schdDate) {
        this.schdDate = schdDate;
    }

    public List<ErpmGenMaster> getGmIdList() {
        return gmIdList;
    }

    public void setGmIdList(List<ErpmGenMaster> gmIdList) {
        this.gmIdList = gmIdList;
    }

    public List<ErpmTenderMaster> getTnoList() {
        return tnoList;
    }

    public void setTnoList(List<ErpmTenderMaster> tnoList) {
        this.tnoList = tnoList;
    }

    public ErpmTenderSchedule getTenschdl() {
        return tenschdl;
    }

    public void setTenschdl(ErpmTenderSchedule tenschdl) {
        this.tenschdl = tenschdl;
    }

    public List<Departmentmaster> getDmList() {
        return dmList;
    }

    public void setDmList(List<Departmentmaster> dmList) {
        this.dmList = dmList;
    }

    public List<Institutionmaster> getImList() {
        return imList;
    }

    public void setImList(List<Institutionmaster> imList) {
        this.imList = imList;
    }

    public List<Subinstitutionmaster> getSimList() {
        return simList;
    }

    public void setSimList(List<Subinstitutionmaster> simList) {
        this.simList = simList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String execute() throws Exception {
        try {

            prepare_lovs();


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

       // tnoList = tnoDao.findAll();
         tnoList = tnoDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
    }

    public String save() throws Exception {
        String varTenderNo;
        Integer varTenderScheduleId;

        try {

            if (tenschdl.getTscTscId() == null) {
                varTenderScheduleId = 0;
            } else {
                varTenderScheduleId = tenschdl.getTscTscId();
            }

            tenschdlList = tenschdlDao.findByTenderMasterId(tenschdl.getErpmTenderMaster().getTmTmId(), varTenderScheduleId);

            if (tenschdlList.size() > 0) {
                varTenderNo = tenschdlList.get(0).getErpmTenderMaster().getTmTenderNo();
                message = "Schedule(s) for Tender No. '" + varTenderNo + "' Already exists. Please Browse and Edit its Schedule";
                return INPUT;
            }
            if (tenschdl.getTscTscId() == null) {

                tenschdlDao.save(tenschdl);
                message = "Record saved successfully.";

            } else {

                ErpmTenderSchedule newtenschdl = tenschdlDao.findBytscTscId(tenschdl.getTscTscId());

                newtenschdl = tenschdl;
                tenschdlDao.update(newtenschdl);
                message = "Tender Schedule's Record updated successfully.";
            }
            Date d = new Date();
            DateUtilities d1 = new DateUtilities();
            setSchdDate(d1.convertDateToString(d, "dd-MM-yyyy"));
            gmIdList = gmDao.findByErpmGmType(i);
            tenschdlDetList = tenschdlDetDao.findbytscTscId(tenschdl.getTscTscId());
            setVartscTscId(tenschdl.getTscTscId());
            setDefault_TenderNo(tenschdl.getErpmTenderMaster().getTmTmId());
//                default_TenderNo = tenschdl.getErpmTenderMaster().getTmTmId();
//                tenschdl = tenschdlDao.findBytscTscId(default_TenderNo);
            setTsdDisable(true);
            return SUCCESS;

        } catch (Exception e) {
            message = message + e.getMessage();
            return ERROR;
        }
    }

    public String AddTenderScheduleDetail() throws Exception {

        try {

            Integer tenderScdlDetID;

            if (tenschdlDet.getTscdTscdId() == null) {
                tenderScdlDetID = 0;
            } else {
                tenderScdlDetID = tenschdlDet.getTscdTscdId();
            }
            tenschdlDetList = tenschdlDetDao.findByTenderNo_ScheduleNO(getDefault_TenderNo(), tenderScdlDetID, tenschdlDet.getTscdScheduleNo());

            tenschdlDetList1 = tenschdlDetDao.findByTenderNo_ScheduleType(getDefault_TenderNo(), tenderScdlDetID, tenschdlDet.getErpmGenMaster().getErpmgmEgmId());

            if (tenschdlDetList.size() > 0) {

                message = "Schedule No. '" + tenschdlDet.getTscdScheduleNo() + "'  Already exists. Please Edit Then Save ";

                DateUtilities dt = new DateUtilities();

                setStrtime(getStrhr() + ":" + getStrmin());
                tenschdlDet.setTscdScheduleTime(getStrtime().toString());

                tenschdlDet.setTscdScheduleDate(dt.convertStringToDate(getSchdDate()));

                tenschdlDetList = tenschdlDetDao.findbytscTscId(tenschdl.getTscTscId());
                tenschdl = tenschdlDao.findByTenderMasterId(getDefault_TenderNo());
                setDefault_TenderNo(getDefault_TenderNo());
                setTsdDisable(true);
                gmIdList = gmDao.findByErpmGmType(i);
                setTsdDisable(true);
                return INPUT;
            }

            if (tenschdlDetList1.size() > 0) {

                message = "Schedule Type ,"  + "Already exists. Please Edit Then Save";

                DateUtilities dt = new DateUtilities();

                setStrtime(getStrhr() + ":" + getStrmin());
                tenschdlDet.setTscdScheduleTime(getStrtime().toString());

                tenschdlDet.setTscdScheduleDate(dt.convertStringToDate(getSchdDate()));

                tenschdlDetList = tenschdlDetDao.findbytscTscId(tenschdl.getTscTscId());

                tenschdl = tenschdlDao.findByTenderMasterId(getDefault_TenderNo());
                setDefault_TenderNo(getDefault_TenderNo());
                setTsdDisable(true);
                gmIdList = gmDao.findByErpmGmType(i);
                setTsdDisable(true);
                return INPUT;

            }

            message = "Please Select ";
            if (tenschdlDet.getTscdScheduleNo() == 0) {
                message = message + "Schedule No";
            } else {
                if (tenschdlDet.getErpmGenMaster().getErpmgmEgmId() == null) {
                    message = message + "Schedule Type";
                } else {
                    if (tenschdlDet.getTscdVenue() == null || tenschdlDet.getTscdVenue().isEmpty()) {

                        message = message + " : Venue";
                    } else {
                        if (schdDate == null || schdDate.isEmpty()) {
                            message = message + " : Date";
                        } else {
                            if (strhr == null || strhr.isEmpty()) {
                                message = message + " : Time(Hr)";
                            } else {
                                if (strmin == null || strmin.isEmpty()) {
                                    message = message + " : Time(Min)";
                                } else {
                                    if (tenschdlDet.getTscdTscdId() == null) {

                                        tenschdlDet.setErpmTenderSchedule(tenschdl);

                                        setStrtime(getStrhr() + ":" + getStrmin());
                                        tenschdlDet.setTscdScheduleTime(getStrtime().toString());

                                        DateUtilities dt = new DateUtilities();
                                        tenschdlDet.setTscdScheduleDate(dt.convertStringToDate(getSchdDate()));

                                        tenschdlDetDao.save(tenschdlDet);

                                        message = "Record saved successfully.";

                                    } else {

                                        //
                                        ErpmTenderScheduleDetail newtenschdlDet = tenschdlDetDao.findBytscdTscdId(tenschdlDet.getTscdTscdId());
                                        tenschdlDet.setErpmTenderSchedule(tenschdl);
                                        DateUtilities dt = new DateUtilities();

                                        setStrtime(getStrhr() + ":" + getStrmin());
                                        tenschdlDet.setTscdScheduleTime(getStrtime().toString());

                                        tenschdlDet.setTscdScheduleDate(dt.convertStringToDate(getSchdDate()));
                                        newtenschdlDet = tenschdlDet;
                                        //                        newtenschdlDet.setErpmGenMaster(tenschdlDet.getErpmGenMaster());
                                        tenschdlDetDao.update(newtenschdlDet);

                                        message = "Record update successfully.";
                                    }
                                }
                            }
                        }
                    }
                }
            }

            tenschdlDetList = tenschdlDetDao.findbytscTscId(tenschdl.getTscTscId());
            setVartscTscId(tenschdl.getTscTscId());
            gmIdList = gmDao.findByErpmGmType(i);
            tenschdl = tenschdlDao.findByTenderMasterId(getDefault_TenderNo());
            setDefault_TenderNo(getDefault_TenderNo());
            setTsdDisable(true);
            return SUCCESS;
        } catch (Exception e) {
            message = message + e.getMessage();
            return ERROR;
        }
    }

    @SkipValidation
    public String Browse() throws Exception {
        try {

        //    tenschdlList = tenschdlDao.findAll();
             tenschdlList = tenschdlDao.findByImId(Short.parseShort(getSession().getAttribute("imId").toString()));
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Browse method of PurchaseInvoiceAction cause :" + e.getCause();
            return ERROR;
        }
    }

    @SkipValidation
    public String Edit() throws Exception {
        try {

            tenschdl = tenschdlDao.findBytscTscId(getTenschlId());

            prepare_lovs();
//            message = "" +  tenschdl.getSubinstitutionmaster().getSimId() + " : "+Integer.valueOf(getSession().getAttribute("userid").toString()) ;            
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Edit method -> DepartmentAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String Back() throws Exception {
        try {

            tenschdl = tenschdlDao.findBytscTscId(tenschdl.getTscTscId());


            prepare_lovs();
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Back method -> " + e.getCause();
            return ERROR;
        }
    }

    @SkipValidation
    public String EditTenderScheduleDetail() throws Exception {
        try {

            tenschdlDet = tenschdlDetDao.findBytscdTscdId(getTenschlDetId());


            DateUtilities d1 = new DateUtilities();
            setSchdDate(d1.convertDateToString(tenschdlDet.getTscdScheduleDate(), "dd-MM-yyyy"));

            strtime = tenschdlDet.getTscdScheduleTime().toString();
            strhr = strtime.substring(0, 2).toString();
            strmin = strtime.substring(3).toString();
            setStrhr(strhr);
            setStrmin(strmin);

            tenschdl = tenschdlDao.findByTenderMasterId(getDefault_TenderNo());
            gmIdList = gmDao.findByErpmGmType(i);
            tenschdlDetList = tenschdlDetDao.findbytscTscId(tenschdl.getTscTscId());
            setDefault_TenderNo(getDefault_TenderNo());
            setTsdDisable(true);


            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in EditTenderScheduleDetail method -> " + e.getCause();
            return ERROR;
        }
    }

    @SkipValidation
    public String Clear() throws Exception {
        try {

            tenschdl = null;
            strhr = null;
            strmin = null;
    //        tenschdlList = tenschdlDao.findAll();
             tenschdlList = tenschdlDao.findByImId(Short.parseShort(getSession().getAttribute("imId").toString()));            
            gmIdList = gmDao.findByErpmGmType(i);

            prepare_lovs();
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in -> TenderSchedule Clear method Cause is: " + e.getCause();
            return ERROR;
        }
    }

    @SkipValidation
    public String ClearTenderScheduleDetail() throws Exception {
        try {

            tenschdlDet = null;
            strhr = null;
            strmin = null;


            schdDate = null;
            tenschdlDetList = tenschdlDetDao.findbytscTscId(tenschdl.getTscTscId());
            Date d = new Date();
            DateUtilities d1 = new DateUtilities();
            setSchdDate(d1.convertDateToString(d, "dd-MM-yyyy"));
            gmIdList = gmDao.findByErpmGmType(i);
            tenschdl = tenschdlDao.findByTenderMasterId(getDefault_TenderNo());
            setDefault_TenderNo(getDefault_TenderNo());
            setTsdDisable(true);
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in -> TenderScheduleDetail Clear method Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String Delete() throws Exception {
        try {


            tenschdl = tenschdlDao.findBytscTscId(getTenschlId());

            tenschdlDao.delete(tenschdl);
            message = "Record deleted successfull";
    //        tenschdlList = tenschdlDao.findAll();
             tenschdlList = tenschdlDao.findByImId(Short.parseShort(getSession().getAttribute("imId").toString()));            
            return SUCCESS;
        } catch (Exception e) {
             if (e.getCause().toString().contains("java.sql.BatchUpdateException: Cannot delete or update a parent row")) {
                 message = "This record cannot be Deleted. It is being used in other Tables." ;
             } else {
            message = "Exception in Delete method -> ProgramAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
	     }
            return ERROR;
        }
    }

    public String DeleteTenderScheduleDetail() throws Exception {
        try {

            tenschdlDet = tenschdlDetDao.findBytscdTscdId(getTenschlDetId());
            tenschdlDetDao.delete(tenschdlDet);
            tenschdlDet=null;

            message = "Record deleted successfull";

            tenschdlDetList = tenschdlDetDao.findbytscTscId(getVartscTscId());
          
            tenschdl = tenschdlDao.findBytscTscId(getVartscTscId());

            Date d = new Date();
            DateUtilities d1 = new DateUtilities();
            setSchdDate(d1.convertDateToString(d, "dd-MM-yyyy"));
            gmIdList = gmDao.findByErpmGmType(i);
            setTsdDisable(true);
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in DeleteTenderScheduleDetail method -> ProgramAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    @Override
    public void validate() {
        try {

            if (tenschdl.getInstitutionmaster().getImId() == null) {
                addFieldError("tenschdl.institutionmaster.imId", "Please select Institutionmaster ");
            }

            if (tenschdl.getSubinstitutionmaster().getSimId() == null) {
                addFieldError("tenschdl.subinstitutionmaster.simId", "Please select Subinstitutionmaster.");
            }

            if (tenschdl.getDepartmentmaster().getDmId() == null) {
                addFieldError("tenschdl.departmentmaster.dmId", "Please select Department.");
            }

            if (tenschdl.getErpmTenderMaster().getTmTmId() == null) {
                addFieldError("tenschdl.erpmTenderMaster.tmTmId", "Please Select Tender No.");
            }

//            if (tenschdl.getTscScheduleNo() == 0) {
//                addFieldError("tenschdl.tscScheduleNo", "Please enter Schedule No.");
//            }
//
//            if (tenschdl.getErpmGenMaster().getErpmgmEgmId() == null) {
//                addFieldError("tenschdl.erpmGenMaster.erpmgmEgmId", "Please Select Schedule Type.");
//            }
//
//            if (tenschdl.getTscVenue() == null) {
//                addFieldError("tenschdl.tscVenue", "Please enter Venue.");
//            }
//
//            if (tenschdl.getTscScheduleTime() == null) {
//                addFieldError("strhr", "Please Select Correct Time.");
//            }
////
//            if (tenschdl.getTscScheduleTime() == null) {
//                addFieldError("strmin", "Please Select Correct Time.");
//            }

        } catch (Exception e) {
            message = e.getMessage();

        }
    }
}
