/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nmeict.smvdu.Beans.SpringClassFile;

import java.util.List;
import org.nmeict.smvdu.Beans.OrgProfile;

/**
 *
 * IOrgProfileService Interface provides methods to process the business logic.
 */
public interface IOrgProfileService {
    
     /**
	  * Add New OrgProfile
	  *
	  * @param  OrgProfile orgProfile
	  */
    public void addNewOrgProfile(OrgProfile orgProfile);
    
    
    /**
	  * Update OrgProfile
	  *
	  * @param  OrgProfile orgProfile
	  */
    public void updateOrgProfile(OrgProfile orgProfile);
    
    
    
    /**
	  * 
	  *
	  * Load All Orgnisation (Institute's/College/University)
	  */
    
    
    
    public List<OrgProfile> loadOrgProfile();
    
    
    
     
    
    
    
    
    
    
    /**
	  * Search OrgProfile
	  *
	  * @param  String orgProfileEntity
	  */
    
    
    
    public OrgProfile searchOrgProfile(String orgEntity);
   
    
}
