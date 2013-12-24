package org.nmeict.smvdu.Beans.SpringClassFile;

import java.util.List;

import org.nmeict.smvdu.Beans.OrgImageDetails;

public interface IOrgImageDetailsDAO {

	public void addOrgProfileImage(OrgImageDetails orgImageDetails);
	public void deleteOrgProfileImage(OrgImageDetails orgImageDetails);
	public List<OrgImageDetails> orgProfileImageList(String orgId,String userId);
	public OrgImageDetails defaultImageProfile(String orgId,String userId);
	
}
