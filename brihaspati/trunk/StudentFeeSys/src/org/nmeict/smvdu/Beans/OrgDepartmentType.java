package org.nmeict.smvdu.Beans;
// Generated Apr 3, 2013 8:35:55 PM by Hibernate Tools 3.2.1.GA


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
import javax.faces.model.SelectItem;

import org.nmeict.smvdu.Beans.SpringClassFile.IOrgDepartmentTypeService;
import org.nmeict.smvdu.Beans.SpringClassFile.OrgDepartmentTypeService;
import org.nmeict.smvdu.HibernateHelper.OrgProfileSessionDetails;

/**
 * OrgDepartmentType generated by hbm2java
 */
@ManagedBean(name="orgDepartmentType")
@RequestScoped
public class OrgDepartmentType  implements java.io.Serializable {


	private Integer odtSeqNo;
    private OrgProfile orgProfile;
    private String orgDepartmentName;
    private String depatmentCreaterId;
    private String depatmentModifierId;
    private Date depatmentCreaterDate;
    private Date depatmentModifierDate;
    private Set otherFeeHeadMasters = new HashSet(0);
    private Set examCodeSetUps = new HashSet(0);
    private Set studentRegMasters = new HashSet(0);
    private Set degreeTypes = new HashSet(0);
    private Set branchMasters = new HashSet(0);
    private Set studentMasters = new HashSet(0);
    private Set semesterMasters = new HashSet(0);
    
    
    
    
    private SelectItem[] items;
     private List<OrgDepartmentType> loadAllDepartmentType = new ArrayList<OrgDepartmentType>();
     private UIData dataGrid;
     private IOrgDepartmentTypeService iOrgDepartmentTypeService = new OrgDepartmentTypeService();
    

     public OrgDepartmentType() {
     }

     public OrgDepartmentType(OrgProfile orgProfile, String orgDepartmentName, String depatmentCreaterId, String depatmentModifierId, Date depatmentCreaterDate, Date depatmentModifierDate, Set otherFeeHeadMasters, Set examCodeSetUps, Set studentRegMasters, Set degreeTypes, Set branchMasters, Set studentMasters, Set semesterMasters) {
        this.orgProfile = orgProfile;
        this.orgDepartmentName = orgDepartmentName;
        this.depatmentCreaterId = depatmentCreaterId;
        this.depatmentModifierId = depatmentModifierId;
        this.depatmentCreaterDate = depatmentCreaterDate;
        this.depatmentModifierDate = depatmentModifierDate;
        this.otherFeeHeadMasters = otherFeeHeadMasters;
        this.examCodeSetUps = examCodeSetUps;
        this.studentRegMasters = studentRegMasters;
        this.degreeTypes = degreeTypes;
        this.branchMasters = branchMasters;
        this.studentMasters = studentMasters;
        this.semesterMasters = semesterMasters;
     }
     public Set getOtherFeeHeadMasters() {
		return otherFeeHeadMasters;
	}

	public void setOtherFeeHeadMasters(Set otherFeeHeadMasters) {
		this.otherFeeHeadMasters = otherFeeHeadMasters;
	}

	public Set getSemesterMasters() {
		return semesterMasters;
	}

	public void setSemesterMasters(Set semesterMasters) {
		this.semesterMasters = semesterMasters;
	}

	
	public Integer getOdtSeqNo() {
         return this.odtSeqNo;
     }
     
     public Set getStudentMasters() {
		return studentMasters;
	}

	public void setStudentMasters(Set studentMasters) {
		this.studentMasters = studentMasters;
	}

	public void setOdtSeqNo(Integer odtSeqNo) {
         this.odtSeqNo = odtSeqNo;
     }
     public OrgProfile getOrgProfile() {
         return this.orgProfile;
     }
     
     public void setOrgProfile(OrgProfile orgProfile) {
         this.orgProfile = orgProfile;
     }
     public String getOrgDepartmentName() {
         return this.orgDepartmentName;
     }
     
     public void setOrgDepartmentName(String orgDepartmentName) {
         this.orgDepartmentName = orgDepartmentName;
     }
     public String getDepatmentCreaterId() {
         return this.depatmentCreaterId;
     }
     
     public void setDepatmentCreaterId(String depatmentCreaterId) {
         this.depatmentCreaterId = depatmentCreaterId;
     }
     public String getDepatmentModifierId() {
         return this.depatmentModifierId;
     }
     
     public void setDepatmentModifierId(String depatmentModifierId) {
         this.depatmentModifierId = depatmentModifierId;
     }
     public Date getDepatmentCreaterDate() {
         return this.depatmentCreaterDate;
     }
     
     public void setDepatmentCreaterDate(Date depatmentCreaterDate) {
         this.depatmentCreaterDate = depatmentCreaterDate;
     }
     public Date getDepatmentModifierDate() {
         return this.depatmentModifierDate;
     }
     
     public void setDepatmentModifierDate(Date depatmentModifierDate) {
         this.depatmentModifierDate = depatmentModifierDate;
     }
     public Set getDegreeTypes() {
         return this.degreeTypes;
     }
     
     public void setDegreeTypes(Set degreeTypes) {
         this.degreeTypes = degreeTypes;
     }
     
     public Set getExamCodeSetUps() {
         return this.examCodeSetUps;
     }
     
     public void setExamCodeSetUps(Set examCodeSetUps) {
         this.examCodeSetUps = examCodeSetUps;
     }
     
	public Set getStudentRegMasters() {
		return studentRegMasters;
	}

	public void setStudentRegMasters(Set studentRegMasters) {
		this.studentRegMasters = studentRegMasters;
	}

	public UIData getDataGrid() {
		return dataGrid;
	}

	public void setDataGrid(UIData dataGrid) {
		this.dataGrid = dataGrid;
	}

	    
	public Set getBranchMasters() {
		return branchMasters;
	}

	public void setBranchMasters(Set branchMasters) {
		this.branchMasters = branchMasters;
	}

	public SelectItem[] getItems() {
		
		List<OrgDepartmentType> departmentType = getiOrgDepartmentTypeService().loadAllOrgDepartmentType();
		items = new SelectItem[departmentType.size()];
		for(int i=0;i<departmentType.size();i++)
		{
			OrgDepartmentType dt = departmentType.get(i);
			SelectItem si = new SelectItem(dt.getOdtSeqNo(),dt.getOrgDepartmentName()); 
			items[i] = si;
		}
	
		return items;
	}

	public void setItems(SelectItem[] items) {
		this.items = items;
	}

	public List<OrgDepartmentType> getLoadAllDepartmentType() {
		loadAllDepartmentType = getiOrgDepartmentTypeService().loadAllOrgDepartmentType();
		dataGrid.setValue(loadAllDepartmentType); 
		return loadAllDepartmentType;
	}

	public void setLoadAllDepartmentType(
			List<OrgDepartmentType> loadAllDepartmentType) {
		this.loadAllDepartmentType = loadAllDepartmentType;
	}

	public IOrgDepartmentTypeService getiOrgDepartmentTypeService() {
		return iOrgDepartmentTypeService;
	}

	public void setiOrgDepartmentTypeService(
			IOrgDepartmentTypeService iOrgDepartmentTypeService) {
		this.iOrgDepartmentTypeService = iOrgDepartmentTypeService;
	}

	public String saveDepartment()
	{
		try
		{
                    List<OrgDepartmentType> listOrgDepartmentType = (ArrayList<OrgDepartmentType>) dataGrid.getValue();
                    for(OrgDepartmentType odt : listOrgDepartmentType ){
                            if(odt.getOrgDepartmentName().equalsIgnoreCase(this.getOrgDepartmentName())){
                                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, orgDepartmentName+" : Already exist", "" ));
                                return null;
                            }
                        
                    }
                        this.setOrgDepartmentName(this.getOrgDepartmentName().toUpperCase());
			OrgProfile op = new OrgProfile();
			op.setOrgId(new OrgProfileSessionDetails().getUserId());
                        //System.out.println("\n\nDepartmenttttttttttttt==="+new OrgProfileSessionDetails().getUserId());
			this.setDepatmentCreaterDate(new Date());
			this.setDepatmentCreaterId(new OrgProfileSessionDetails().getUserId());
			this.setOrgProfile(op);
			getiOrgDepartmentTypeService().addOrgDepartmentType(this);
			//FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, ""+orgDepartmentName+" : Saved Successfully", ""));
                        return  "OrgDepartmentType.xhtml?faces-redirect=true";
                                //FacesContext.getCurrentInstance().addMessage(\"\", new FacesMessage(FacesMessage.SEVERITY_INFO, orgDepartmentName+ \" : Saved Successfully.\", \"\"))?faces-redirect=true";
		}   
		catch (Exception e) 
		{ 
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, ""+e, ""));
                        return null;
		}
	}

	public void update()
	{
		try
		{
			List<OrgDepartmentType> listOrgDepartmentType = (ArrayList<OrgDepartmentType>) dataGrid.getValue();
			getiOrgDepartmentTypeService().update(listOrgDepartmentType);
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Updated Successfully", ""));
		}
		catch(Exception ex)
		{
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, ""+ex, ""));
		}
	}
        public String delete(OrgDepartmentType odtDelete){
            try{
                List<OrgDepartmentType> listOrgDepartmentType = (ArrayList<OrgDepartmentType>) dataGrid.getValue();
                
                for(OrgDepartmentType odt : listOrgDepartmentType ){
                    if(odt.equals(odtDelete)){
                        getiOrgDepartmentTypeService().deleteOrgDepartment(odt);
                        break;
                    }
                }
                //FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, odt.getOrgDepartmentName()+" : is Deleted.", "" ));
                return  "OrgDepartmentType.xhtml?faces-redirect=true";
            }
            catch(Exception e){
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Department could not Deleted"+e, ""));
                return null; 
            }
        }
}


