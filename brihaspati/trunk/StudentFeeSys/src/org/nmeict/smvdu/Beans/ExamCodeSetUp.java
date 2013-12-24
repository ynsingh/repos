package org.nmeict.smvdu.Beans;
// Generated Mar 30, 2013 8:32:59 PM by Hibernate Tools 3.2.1.GA


import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.nmeict.smvdu.Beans.SpringClassFile.ExamcodeSetupService;
import org.nmeict.smvdu.Beans.SpringClassFile.IExamcodeSetupService;
import org.nmeict.smvdu.HibernateHelper.OrgProfileSessionDetails;


@ManagedBean(name="examCodeSetUp")
@RequestScoped
/**
 * ExamCodeSetUp generated by hbm2java
 */
public class ExamCodeSetUp  implements java.io.Serializable {


     private Integer seqNo;
     private OrgProfile orgProfile;
     private Date semesterBegningDate;
     private Date semesterEndingDate;
     private Set adminFeeAdminMasters = new HashSet(0);
     private Set semesterMasters = new HashSet(0);
     private DegreeType degreeType;
     private OrgDepartmentType orgDepartmentType;
     private boolean flag = true;
     private Integer ecsDegreeType;
     private Integer ecsOrgDepartmentType;
     private String semesterName;
     private String degreeName;
     private String departmentName;
     private int ecsCode;
     private IExamcodeSetupService iExamcodeSetupService = new ExamcodeSetupService();
     private UIData dataGrid;
     private List<ExamCodeSetUp> loadAllExamCodeSetUp = new ArrayList<ExamCodeSetUp>();
     private SelectItem[] items;
     private ExamCodeSetUp oneExamSetupDetail;
     public ExamCodeSetUp() {
     }

     public ExamCodeSetUp(OrgProfile orgProfile, OrgDepartmentType orgDepartmentType, DegreeType degreeType, Date semesterBegningDate, Date semesterEndingDate, String semesterName, Set adminFeeAdminMasters, Set semesterMasters) {
        this.orgProfile = orgProfile;
        this.orgDepartmentType = orgDepartmentType;
        this.degreeType = degreeType;
        this.semesterBegningDate = semesterBegningDate;
        this.semesterEndingDate = semesterEndingDate;
        this.semesterName = semesterName;
        this.adminFeeAdminMasters = adminFeeAdminMasters;
        this.semesterMasters = semesterMasters;
     }
    
     
     

	public ExamCodeSetUp getOneExamSetupDetail() {
		return oneExamSetupDetail;
	}

	public void setOneExamSetupDetail(ExamCodeSetUp oneExamSetupDetail) {
		
		this.oneExamSetupDetail = oneExamSetupDetail;
	}

	public OrgDepartmentType getOrgDepartmentType() {
         return this.orgDepartmentType;
     }
     
     public void setOrgDepartmentType(OrgDepartmentType orgDepartmentType) {
         this.orgDepartmentType = orgDepartmentType;
     }
    public Integer getSeqNo() {
        return this.seqNo;
    }
    
    public Set getSemesterMasters() {
		return semesterMasters;
	}

	public void setSemesterMasters(Set semesterMasters) {
		this.semesterMasters = semesterMasters;
	}

	public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }
    public OrgProfile getOrgProfile() {
        return this.orgProfile;
    }
    
    public void setOrgProfile(OrgProfile orgProfile) {
        this.orgProfile = orgProfile;
    }
    
    
    public Date getSemesterBegningDate() {
        return this.semesterBegningDate;
    }
    
    public DegreeType getDegreeType() {
		return degreeType;
	}


	public void setDegreeType(DegreeType degreeType) {
		this.degreeType = degreeType;
	}


	public void setSemesterBegningDate(Date semesterBegningDate) {
        this.semesterBegningDate = semesterBegningDate;
    }
    public Date getSemesterEndingDate() {
        return this.semesterEndingDate;
    }
    
    public void setSemesterEndingDate(Date semesterEndingDate) {
        this.semesterEndingDate = semesterEndingDate;
    }
    public String getSemesterName() {
        return this.semesterName;
    }
    
    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }
   
    
    
    public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Integer getEcsOrgDepartmentType() {
		return ecsOrgDepartmentType;
	}

	public void setEcsOrgDepartmentType(Integer ecsOrgDepartmentType) {
		this.ecsOrgDepartmentType = ecsOrgDepartmentType;
	}

	public Integer getEcsDegreeType() {
		return ecsDegreeType;
	}


	public void setEcsDegreeType(Integer ecsDegreeType) {
		this.ecsDegreeType = ecsDegreeType;
	}


	public Set getAdminFeeAdminMasters() {
        return this.adminFeeAdminMasters;
    }
    
    public String getDegreeName() {
		return degreeName;
	}


	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}


	public void setAdminFeeAdminMasters(Set adminFeeAdminMasters) {
        this.adminFeeAdminMasters = adminFeeAdminMasters;
    }

	

	public IExamcodeSetupService getiExamcodeSetupService() {
		return iExamcodeSetupService;
	}


	public void setiExamcodeSetupService(IExamcodeSetupService iExamcodeSetupService) {
		this.iExamcodeSetupService = iExamcodeSetupService;
	}

	
    
	public UIData getDataGrid() {
		return dataGrid;
	}


	public void setDataGrid(UIData dataGrid) {
		this.dataGrid = dataGrid;
	}

	

	
	
	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public List<ExamCodeSetUp> getLoadAllExamCodeSetUp() {
		loadAllExamCodeSetUp = getiExamcodeSetupService().loadAllExamcodeSetup(ecsCode);
		for(ExamCodeSetUp ecs : loadAllExamCodeSetUp)
		{
			System.out.println("Examcode : "+ecs.getDegreeName()+" : id : "+ecs.getSeqNo()); 
		}
		dataGrid.setValue(loadAllExamCodeSetUp);
		return loadAllExamCodeSetUp;
	}


	public void setLoadAllExamCodeSetUp(List<ExamCodeSetUp> loadAllExamCodeSetUp) {
		this.loadAllExamCodeSetUp = loadAllExamCodeSetUp;
	}

	

	public void examCodeValue(ValueChangeEvent event)
	{
		
		if((Object)event.getNewValue()!=null)
		{
			
			ecsCode = (Integer)event.getNewValue();
                        //System.out.print("\n\nExamCodeSetupifffffff==="+ecsCode);
			flag = false;
		}
		else
		{
			flag = true;
			ecsCode=0;
                        //System.out.print("\n\nExamCodeSetup elseeeeeeeeeee==="+ecsCode);
		}
		
	}
	
	
	
	public SelectItem[] getItems() {
                //System.out.print("\n\nExamCodeSetup getItem====Ecode==="+ecsCode);
		List<ExamCodeSetUp> examSesionCode = getiExamcodeSetupService().loadAllExamcodeSetup(ecsCode);
		items = new SelectItem[examSesionCode.size()];
		for(int i=0;i<examSesionCode.size();i++)
		{
			ExamCodeSetUp dt = examSesionCode.get(i);
			SelectItem si = new SelectItem(dt.getSeqNo(),dt.getSemesterName());
			items[i] = si;
		}
		ecsCode=0;
		return items;
	}

	public void setItems(SelectItem[] items) {
		this.items = items;
	}

	//public void saveExamcode()
        public String saveExamcode()
	{
		try
		{
                    //System.out.println("\n\n\n=========11111111=="+this.getSemesterBegningDate());
                    FacesContext fc = FacesContext.getCurrentInstance();
                    FacesMessage message = null;
                    if(this.getSemesterName().isEmpty()){
                        message = new FacesMessage();
                        message.setSeverity(FacesMessage.SEVERITY_ERROR);
                        message.setSummary("Please Enter Session Name. It Cann't  be Empty.");
                        //message.setDetail("Degree Doesn't Exist in Selected Department.");
                        fc.addMessage("", message);
                        return null;
                    }
                    
                    if(this.getSemesterBegningDate()== null){
                        message = new FacesMessage();
                        message.setSeverity(FacesMessage.SEVERITY_ERROR);
                        message.setSummary("Please Enter Session Start Date. It Cann't  be Empty.");
                        //message.setDetail("Degree Doesn't Exist in Selected Department.");
                        fc.addMessage("", message);
                        return null;
                    }
                    
                    if(this.getSemesterEndingDate() == null){
                        message = new FacesMessage();
                        message.setSeverity(FacesMessage.SEVERITY_ERROR);
                        message.setSummary("Please Enter Session End Date. It Cann't  be Empty.");
                        //message.setDetail("Degree Doesn't Exist in Selected Department.");
                        fc.addMessage("", message);
                        return null;
                    }
                    
                    if(this.getEcsOrgDepartmentType() == 0){
                        message = new FacesMessage();
                        message.setSeverity(FacesMessage.SEVERITY_ERROR);
                        message.setSummary("Please Enter Department. It Cann't  be Empty.");
                        //message.setDetail("Degree Doesn't Exist in Selected Department.");
                        fc.addMessage("", message);
                        return null;
                    }
                    
                    if(this.getEcsDegreeType() == 0){
                        message = new FacesMessage();
                        message.setSeverity(FacesMessage.SEVERITY_ERROR);
                        message.setSummary("Please Enter Degree. It Cann't  be Empty.");
                        //message.setDetail("Degree Doesn't Exist in Selected Department.");
                        fc.addMessage("", message);
                        return null;
                    }
                        
                    OrgProfile op =  new OrgProfile();
                    DegreeType dt = new DegreeType();
                    OrgDepartmentType odt = new OrgDepartmentType();
                    dt.setSeqNo(this.getEcsDegreeType());
                    odt.setOdtSeqNo(this.getEcsOrgDepartmentType());
                    op.setOrgId(new OrgProfileSessionDetails().getOrgProfileSession().getOrgId());
                    this.setOrgProfile(op);
                    this.setDegreeType(dt); 
                    this.setOrgDepartmentType(odt);
                    getiExamcodeSetupService().addExamcodeSetup(this);
                                //FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, ""+semesterName+" : Saved Successfully", ""));
                    return "SessionSetup.xhtml?faces-redirect=true";
		}
		catch(Exception ex)
		{
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, ""+ex, ""));
                        return null; 
		}
	}

	@SuppressWarnings("unchecked")
	public void update()
	{
		try
		{
			List<ExamCodeSetUp> listOfExamCode = (ArrayList<ExamCodeSetUp>) dataGrid.getValue();
			getiExamcodeSetupService().update(listOfExamCode); 
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Updated Successfully", ""));
		}
		catch(Exception ex)
		{
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, ""+ex, ""));	
		}
		
	}
	
	
}


