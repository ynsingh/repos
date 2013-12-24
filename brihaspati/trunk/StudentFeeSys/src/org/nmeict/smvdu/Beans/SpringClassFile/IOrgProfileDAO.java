/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nmeict.smvdu.Beans.SpringClassFile;

import java.util.List;
import org.nmeict.smvdu.Beans.OrgProfile;
import org.springframework.transaction.annotation.Transactional;

/** 
 * IOrgprOfileDAO Interface provides methods of Data Access Layer. 
 * The data access layer manages all the logic to persist and retrieve the data from database.
 * 
 */
public interface IOrgProfileDAO {
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
  
   
   //public OrgProfile loadAllDetails();
  
    
    /**
	  * Search OrgProfile
	  *
	  * @param  String orgProfileEntity
	  */
    
    public OrgProfile searchOrgProfile(String orgEntity);
    
    
}
