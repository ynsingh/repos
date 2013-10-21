/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package General;

import pojo.hibernate.FileMaster;
import pojo.hibernate.FileMasterDAO;
import pojo.hibernate.FileDetail;
import pojo.hibernate.FileDetailDAO;
import pojo.hibernate.Institutionmaster;
import pojo.hibernate.InstitutionmasterDAO;
import pojo.hibernate.Subinstitutionmaster;
import pojo.hibernate.SubinstitutionmasterDAO;
import pojo.hibernate.Departmentmaster;
import pojo.hibernate.DepartmentmasterDAO;
import pojo.hibernate.ErpmGenMaster;
import pojo.hibernate.ErpmGenMasterDao;
import pojo.hibernate.Employeemaster;
import pojo.hibernate.EmployeemasterDAO;
import pojo.hibernate.Erpmusers;
import pojo.hibernate.ErpmusersDAO;
import utils.DevelopmentSupport;
import utils.DateUtilities;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
/**
 *
 * @author kazim
 */
public class DespatchFile extends DevelopmentSupport {

   // private FileMaster fm = new FileMaster();
    //private FileMasterDAO fmDao = new FileMasterDAO();

    private FileDetail fd = new FileDetail();
    private FileDetailDAO fdDao = new FileDetailDAO();


    private ErpmGenMasterDao erpmGenMasterDao = new ErpmGenMasterDao();
    private List<ErpmGenMaster> fileTypeList = new ArrayList<ErpmGenMaster>();

    private InstitutionmasterDAO imDao = new InstitutionmasterDAO();
    private List<Institutionmaster> imList = new ArrayList<Institutionmaster>();

    private SubinstitutionmasterDAO simDao = new SubinstitutionmasterDAO();
    private List<Subinstitutionmaster> simList = new ArrayList<Subinstitutionmaster>();

    private DepartmentmasterDAO dmDao = new DepartmentmasterDAO();
    private List<Departmentmaster> dmList = new ArrayList<Departmentmaster>();

    private EmployeemasterDAO empDao = new EmployeemasterDAO();
    private List<Employeemaster> empList = new ArrayList<Employeemaster>();

    private List<FileDetail> filesDespatched = new ArrayList<FileDetail>();

    private ErpmusersDAO erpmuDao = new  ErpmusersDAO();


    private String message;
    private String fileDate;
    private String confidential;
    private Short defaultInsitute;
    private Integer defaultSubInstitute;
    private Integer defaultDepartment;
    private Integer defaultEmployee;
    private Integer defaultFileType;

    public void setfd(FileDetail fd) {
        this.fd = fd;
    }

    public FileDetail getfd() {
        return this.fd;
    }


    public List<ErpmGenMaster> getfileTypeList() {
        return this.fileTypeList;
    }

    public void setfileTypeList(List<ErpmGenMaster> fileTypeList) {
        this.fileTypeList = fileTypeList;
    }

    public List<Institutionmaster> getimList() {
        return this.imList;
    }

    public void setimList(List<Institutionmaster> imList) {
        this.imList = imList;
    }

    public List<Subinstitutionmaster> getsimList() {
        return this.simList;
    }

    public void setsimList(List<Subinstitutionmaster> simList) {
        this.simList = simList;
    }

    public List<Departmentmaster> getdmList() {
        return this.dmList;
    }

    public void setdmList(List<Departmentmaster> dmList) {
        this.dmList = dmList;
    }

    public List<Employeemaster> getempList() {
        return this.empList;
    }

    public void setempList(List<Employeemaster> empList) {
        this.empList = empList;
    }

    public void setfileDate(String fileDate) {
        this.fileDate = fileDate;
    }

    public String getfileDate() {
        return this.fileDate;
    }

   public void setconfidential(String confidential) {
        this.confidential = confidential;
    }

    public String getconfidential() {
        return this.confidential;
    }

    public void setdefaultInsitute(Short defaultInsitute) {
        this.defaultInsitute = defaultInsitute;
    }

    public Short getdefaultInsitute() {
        return this.defaultInsitute;
    }

    public void setdefaultSubInstitute(Integer defaultSubInstitute) {
        this.defaultSubInstitute = defaultSubInstitute;
    }

    public Integer getdefaultSubInstitute() {
        return this.defaultSubInstitute;
    }

    public void setdefaultDepartment(Integer defaultDepartment) {
        this.defaultDepartment = defaultDepartment;
    }

    public Integer getdefaultDepartment() {
        return this.defaultDepartment;
    }

    public void setdefaultEmployee(Integer defaultEmployee) {
        this.defaultEmployee = defaultEmployee;
    }

    public Integer getdefaultEmployee() {
        return this.defaultEmployee;
    }

    public void setdefaultFileType(Integer defaultFileType) {
        this.defaultFileType = defaultFileType;
    }

    public Integer getdefaultFileType() {
        return this.defaultFileType;
    }

    public void setfilesDespatched(List<FileDetail> filesDespatched) {
        this.filesDespatched = filesDespatched;
    }

    public List<FileDetail> getfilesDespatched() {
        return this.filesDespatched;
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


            //Initialize LOVs
            InitializeLOVs();

            //Set the object as erpmim as Null
            
            message = message + "In Despatch Now" ;//+ fm_new.

            return SUCCESS;
        } catch (Exception e) {
           message = "Exception in -> FileMasterAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    private void InitializeLOVs() {

        //Prepare LOV for User's institutions
        imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
        simList = simDao.findAllSubInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
        dmList = dmDao.findAllDepartmentsForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
        empList = empDao.findAllEmployeesForUserInstitutions(Integer.valueOf(getSession().getAttribute("userid").toString()));
        //Prepare LOV of File Types
        fileTypeList = erpmGenMasterDao.findByErpmGmType(Short.parseShort("18".toString()));

        //Set file despatch date t o system's date
       /* Date dt = new Date();
        DateUtilities d = new DateUtilities();
        try {
            setfileDate(d.convertDateToString(dt, "dd-MM-yyyy"));
        } catch (Exception e)
        {}

        //Set Number of Pages in File as 1
        fm.setFilePages(1);
*/
        return;
    }

    public String SaveDespatchFile() throws Exception {
        try{
            //If File Despatch is New
            if(fd.getFdId() == null) {

               DateUtilities d = new DateUtilities();

               fd.setFdDespatchDate(d.convertStringToDate(fileDate));
               fd.setFdDespatchNo(fdDao.getDespatchNumber(fd.getFileMaster().getFileId()));
              //  fd.setFileMaster(fm);

               fdDao.save(fd);

               //Prepare list of File Despatched
               filesDespatched = fdDao.getFiles(fd.getFileMaster().getFileId());
               
              message="File Despatched ";

            }
            else
            {
                //This part updates File despatch

            }
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in -> DespatchFileAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }

    }


  /*
   public String SaveFileMaster() throws Exception {
        try {
            //If File is New
            if(fm.getFileId() == null) {

                //Generate the  File Id
                DateUtilities d = new DateUtilities();
                fm.setFileNumber(d.convertDateToString(new Date(), "yyyy") + "/" + (fmDao.countFiles(fm.getDepartmentmaster().getDmId()).intValue() + 1));

                //Set User who created the file
                fm.setErpmusers(erpmuDao.findByUserId(Integer.parseInt(getSession().getAttribute("userid").toString())));

                if(getconfidential().compareTo("0") == 0)
                    fm.setFileConfidential(Boolean.FALSE);
                else
                    fm.setFileConfidential(Boolean.TRUE);

                //Save File
                fmDao.save(fm);

                //Display meesage containing File Id
                FileMaster fm_new = fmDao.findFile(fm.getFileId());
                setDefaults(fm_new);
                message =   "File Created with Id :"  + fm_new.getDepartmentmaster().getDmShortName() + "/" +
                            fm_new.getFileNumber() ;

            }
            else
            {
             // This part handles Update of File

                FileMaster fm2 = fmDao.findFile(fm.getFileId());
                fm.setFileNumber(fm2.getFileNumber());
                if(getconfidential().compareTo("0") == 0)
                    fm.setFileConfidential(Boolean.FALSE);
                else
                    fm.setFileConfidential(Boolean.TRUE);

                fm2 = fm;

                fmDao.update(fm2);
                setDefaults(fm2);
                message = "File Updated Successfully " + fm2.getFileNumber();

            }

            InitializeLOVs();
            return SUCCESS;
            }
            catch (Exception e) {
                message = "Exception in -> SaveFileMasterAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }

    }



    private void setDefaults(FileMaster fm) {
            setdefaultInsitute(fm.getInstitutionmaster().getImId());
            setdefaultSubInstitute(fm.getSubinstitutionmaster().getSimId());
            setdefaultDepartment(fm.getDepartmentmaster().getDmId());
            setdefaultEmployee(fm.getEmployeemaster().getEmpId());
            setdefaultFileType(fm.getErpmGenMaster().getErpmgmEgmId());

    }
public void validate() {

    try{
            if (fm.getInstitutionmaster().getImId() == null)
                addFieldError("fm.institutionmaster.imId", "Please select institution");
            if (fm.getSubinstitutionmaster().getSimId() == null)
                addFieldError("fm.subinstitutionmaster.simId", "Please select Sub-Institution");
            if (fm.getDepartmentmaster().getDmId() == null)
                addFieldError("fm.departmentmaster.dmId", "Please select department");
            if (fm.getEmployeemaster().getEmpId()== null)
                addFieldError("fm.employeemaster.empId", "Please select signatory");
            if (fm.getErpmGenMaster().getErpmgmEgmId() == null)
                addFieldError("fm.erpmGenMaster.erpmgmEgmId", "Please select File Type");
            if(fm.getFilePages() == null)
                addFieldError("fm.getFilePages()", "Please give pages in the file");

            //Initialize LOVs
            InitializeLOVs();
    }
    catch(NullPointerException npe){};
}
*/
}