package org.nmeict.smvdu.Beans.SpringClassFile;

import java.util.List;

import org.nmeict.smvdu.Beans.OrgDepartmentType;

public interface IOrgDepartmentTypeService {

	public void addOrgDepartmentType(OrgDepartmentType orgDepartmentType);
	public void update(List<OrgDepartmentType> orgDepartmentType);
	public List<OrgDepartmentType> loadAllOrgDepartmentType();
        public void deleteOrgDepartment(OrgDepartmentType orgDepartmentType);
	
	
	
}
