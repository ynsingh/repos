package org.nmeict.smvdu.Beans.SpringClassFile;

import java.util.List;

import org.nmeict.smvdu.Beans.OrgDepartmentType;

public class OrgDepartmentTypeService implements IOrgDepartmentTypeService{

	
	private OrgDepartmentTypeDAO orgDepartmentTypeDAO = new OrgDepartmentTypeDAO();
	
	@Override
	public void addOrgDepartmentType(OrgDepartmentType orgDepartmentType) {
			getOrgDepartmentTypeDAO().addOrgDepartmentType(orgDepartmentType);
		
	}

	@Override
	
	
	public void update(List<OrgDepartmentType> orgDepartmentType) {
		getOrgDepartmentTypeDAO().update(orgDepartmentType);
		
	}

	public OrgDepartmentTypeDAO getOrgDepartmentTypeDAO() {
		return orgDepartmentTypeDAO;
	}

	public void setOrgDepartmentTypeDAO(OrgDepartmentTypeDAO orgDepartmentTypeDAO) {
		this.orgDepartmentTypeDAO = orgDepartmentTypeDAO;
	}

	@Override
	public List<OrgDepartmentType> loadAllOrgDepartmentType() {
		// TODO Auto-generated method stub
			return getOrgDepartmentTypeDAO().loadAllOrgDepartmentType();
	}
        
        
        public void deleteOrgDepartment(OrgDepartmentType orgDepartmentType){
            getOrgDepartmentTypeDAO().deleteOrgDepartment(orgDepartmentType);
        }
}
