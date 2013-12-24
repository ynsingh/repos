/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nmeict.smvdu.Beans.SpringClassFile;

import org.nmeict.smvdu.Beans.OrgLoginDetails;
import org.nmeict.smvdu.Beans.OrgProfile;

/**
 *
 * @author KESU
 */
public interface IOrgLoginProfile {
    public boolean validateOrgUser(OrgLoginDetails orgLoginDetails);
    /**
	  * Search OrgProfile
	  *
	  * @param  String orgProfileEntity
	  */
  
   
   public OrgProfile loadAllDetails(String orgId);
  
}
