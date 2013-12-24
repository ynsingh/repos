package org.nmeict.smvdu.Beans.SpringClassFile;

import java.util.List;

import org.nmeict.smvdu.Beans.OrgImageDetails;

public class OrgImageDetailsService implements IOrgImageDetailsService{

	
	
	
	OrgImageDetailsDAO orgImageDetailsDAO = new OrgImageDetailsDAO();
	
	@Override 
	public void addOrgProfileImage(OrgImageDetails orgImageDetails) {
		getOrgImageDetailsDAO().addOrgProfileImage(orgImageDetails);
		
	}

	@Override
	public void deleteOrgProfileImage(OrgImageDetails orgImageDetails) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<OrgImageDetails> orgProfileImageList(String orgId, String userId) {
		// TODO Auto-generated method stub
		return getOrgImageDetailsDAO().orgProfileImageList(orgId, userId);
	}

	public OrgImageDetailsDAO getOrgImageDetailsDAO() {
		return orgImageDetailsDAO;
	}

	public void setOrgImageDetailsDAO(OrgImageDetailsDAO orgImageDetailsDAO) {
		this.orgImageDetailsDAO = orgImageDetailsDAO;
	}

	@Override
	public OrgImageDetails defaultImageProfile(String orgId, String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
