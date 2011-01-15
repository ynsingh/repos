package in.ac.dei.edrp.server;

import in.ac.dei.edrp.client.CM_ProgramInfoGetter;
import in.ac.dei.edrp.client.CM_entityInfoGetter;
import in.ac.dei.edrp.client.CM_instituteInfoGetter;
import in.ac.dei.edrp.client.CM_passwordPolicyGetter;
import in.ac.dei.edrp.client.CM_progMasterInfoGetter;
import in.ac.dei.edrp.client.CM_progTermDetailGetter;
import in.ac.dei.edrp.client.CM_sessionInfoGetter;
import in.ac.dei.edrp.client.RPCFiles.CM_connect;
import in.ac.dei.edrp.server.SharedFiles.Log4JInitServlet;
import in.ac.dei.edrp.server.SharedFiles.SqlMapManager;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ibatis.sqlmap.client.SqlMapClient;

//import java.util.*;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class CM_connectImpl extends RemoteServiceServlet implements CM_connect {
//    Connection con;
    SqlMapClient client = SqlMapManager.getSqlMapClient();
    Log4JInitServlet logObj = new Log4JInitServlet();


    /**
     * Method to return current session of the institute
     * @param instituteID
     */
    public String methodCurrentSession(String instituteID) {
        String session = "";

        try {
            CM_sessionInfoGetter CMSIG = new CM_sessionInfoGetter();
            CM_sessionInfoGetter[] CMSIG1;
            CMSIG.setInstituteID(instituteID);

            List<?> li;
            li = client.queryForList("selectcurrentSession", CMSIG);
            CMSIG1 = li.toArray(new CM_sessionInfoGetter[li.size()]);
            session = CMSIG1[0].getNewSession();
           
            return session;
        } catch (Exception e) {
        	logObj.logger.error("Exception is in methodCurrentSession :" + e);
        }

        return null;
    }

    /**
     * Method to fetch instituteID
     * @param string
     * @param type
     * @return
     */

    public CM_instituteInfoGetter[] methodInstituteID(String string, int type) {
        try {
            List<?> li;

            if (type == 1) {
                li = client.queryForList("selectFromUserID", string);

                return li.toArray(new CM_instituteInfoGetter[li.size()]);
            } else if (type == 2) {
                li = client.queryForList("selectFromInstiName", string);

                return li.toArray(new CM_instituteInfoGetter[li.size()]);
            } else {
                return null;
            }
        } catch (Exception ex) {
        	logObj.logger.error(ex);
        }

        return null;
    }

    /**
     * Method to return Institute Name and Current Session
     */

//    public String methodShowOldSession(String userid) {
//        String Value = "";
//        String InstituteID;
//        String InstituteName;
//        String session = "";
//        List<?> li;
//
//        try {
//            createconnection();
//       
//            
//            /*
//             * Temporarily fixed userid
//             * userid="instituteadmin";
//             */
//
//            userid="instituteadmin";
//            
//            // retrieving instituteID of user
//            CM_instituteInfoGetter[] CMIIG = methodInstituteID(userid, 1);
//            InstituteID = CMIIG[0].getId();
//
//            // retrieving instituteName
//            li = client.queryForList("selectInstituteName", CMIIG[0]);
//            CMIIG = li.toArray(new CM_instituteInfoGetter[li.size()]);
//            InstituteName = CMIIG[0].getName();
//            Value = InstituteName;
//  
//            // This query retreives current session
//            session = methodCurrentSession(InstituteID);
//          
//            // Concatenating values of ResultSet into single string
//            Value = Value + '|' + session + '|';
//        
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }
//
//        return Value;
//    }

    /*********************************** Super Admin **********************************************/

    /**
     * Method for Adding Institute Details
     *
     * @throws Exception
     */
//    public String methodAddInstitute(String name, String id, String address,
//        String city, String state, String pin) throws Exception {
//        try {
//            createconnection();
//
//            String query = "insert into institute values(?,?,?,?,?,?)";
//            PreparedStatement pstatement = con.prepareStatement(query);
//            pstatement.setString(1, id);
//            pstatement.setString(2, name);
//            pstatement.setString(3, address);
//            pstatement.setString(4, city);
//            pstatement.setString(5, state);
//            pstatement.setString(6, pin);
//            pstatement.executeUpdate();
//
//            CM_sessionInfoGetter insert = new CM_sessionInfoGetter();
//            insert.setInstituteID(id);
//            insert.setOldSession("0000-00");
//            insert.setNewSession("0000-00");
//            insert.setCurrentSemester("NULL");
//            insert.setCurrentStatus("1");
//            client.insert("insertNewSession", insert);
//
//            //            String sessionQuery = "insert into instituteSession values(?,0000-00,0000-00,NULL,1)";
//            //            PreparedStatement pstatement1 = con.prepareStatement(sessionQuery);
//            //            pstatement1.setString(1, id);
//            //            pstatement1.executeUpdate();
//        } catch (Exception ex) {
//            System.out.println(ex);
//            throw new Exception(ex);
//        }
//
//        return null;
//    }

    /**
     * Method to populate the institutes which donot have institute
     * administrators
     *
     * @throws Exception
     */
//    public String methodInstitutePopulate() throws Exception {
//        String Result = "";
//
//        try {
//            createconnection();
//
//            String query = " select instituteName from institute where instituteID NOT IN (select distinct(instituteID) from userInfo)";
//            PreparedStatement pstatement = con.prepareStatement(query);
//            ResultSet resultset = pstatement.executeQuery();
//
//            while (resultset.next()) {
//                Result = Result + resultset.getString(1) + '|';
//            }
//        } catch (Exception ex) {
//            System.out.println(ex);
//            throw new Exception(ex);
//        }
//
//        return Result;
//    }

    /**
     * Method to add institute administrators
     *
     * @throws Exception
     */
//    public String methodAddInstituteAdmin(String instituteName, String uID,
//        String password) throws Exception {
//        String instituteID = "";
//
//        try {
//            createconnection();
//
//            // instituteID = methodInstituteID(instituteName, 2);
//            CM_instituteInfoGetter[] CMIIG = methodInstituteID(instituteName, 2);
//            instituteID = CMIIG[0].getId();
//
//            String query = "insert into userInfo values(?,?,SHA1(?),2)";
//            PreparedStatement pstatement = con.prepareStatement(query);
//            pstatement.setString(1, instituteID);
//            pstatement.setString(2, uID);
//            pstatement.setString(3, password);
//            pstatement.executeUpdate();
//        } catch (Exception ex) {
//            System.out.println(ex);
//            throw new Exception(ex);
//        }
//
//        return null;
//    }

    //    /**
    //     * Method for generating list of institutes
    //     *
    //     * @throws Exception
    //     */
    //    @SuppressWarnings("unchecked")
    //    public CM_instituteInfoGetter[] methodInstituteList()
    //        throws Exception {
    //        try {
    //            createconnection();
    //
    //            List li = client.queryForList("selectAllInstituteDetails", null);
    //
    //            return (CM_instituteInfoGetter[]) li.toArray(new CM_instituteInfoGetter[li.size()]);
    //        } catch (Exception e) {
    //            throw new Exception(e);
    //        }
    //    }
    //    
    //    

    /**
     * Method for generating list of institutes
     *
     * @throws Exception
     */
    
//    public CM_instituteInfoGetter[] methodInstituteList(String criteria,
//        String value) throws Exception {
//        try {
//            createconnection();
//
//            List<?> li = null;
//
//            if (criteria == null) {
//                li = client.queryForList("selectAllInstituteDetails", null);
//            }
//            else if (criteria.equalsIgnoreCase("Institute Name")) {
//                li = client.queryForList("selectAllInstituteDetailsWithInstiName",
//                        value);
//            }
//            else if (criteria.equalsIgnoreCase("Institute ID")) {
//                li = client.queryForList("selectAllInstituteDetailsWithInstiID",
//                        value);
//            }
//            else if (criteria.equalsIgnoreCase("Institute City")) {
//                li = client.queryForList("selectAllInstituteDetailsWithInstiCity",
//                        value);
//            }
//            else if (criteria.equalsIgnoreCase("Institute State")) {
//                li = client.queryForList("selectAllInstituteDetailsWithInstiState",
//                        value);
//            }
//
//            System.out.println("size+" + li.size() + criteria + value);
//
//            return li.toArray(new CM_instituteInfoGetter[li.size()]);
//        } catch (Exception e) {
//            throw new Exception(e);
//        }
//    }

    /**
     * Method for changing login password
     *
     * @throws Exception
     */
//    public void changePassword(String userid, String currentPassword,
//        String newPassword) throws Exception {
//        try {
//            createconnection();
//
//            String query = "select password from userInfo where uID=?";
//            PreparedStatement pstatement = con.prepareStatement(query);
//            pstatement.setString(1, userid);
//
//            ResultSet resultset = pstatement.executeQuery();
//            resultset.next();
//
//            if (resultset.getString(1).equals(currentPassword)) {
//                String changepwdQuery = "update userInfo set password=? where uID=?";
//                PreparedStatement pstatement1 = con.prepareStatement(changepwdQuery);
//                pstatement1.setString(1, newPassword);
//                pstatement1.setString(2, userid);
//                pstatement1.executeUpdate();
//            } else {
//                throw new Exception("Current password is invalid");
//            }
//        } catch (Exception e) {
//            throw new Exception(e);
//        }
//    }

    /**
     * Method for changing login password for other users of the system
     *
     * @throws Exception
     */
//    public void changeUserPassword(String userid, String newPassword)
//        throws Exception {
//        try {
//            createconnection();
//
//            String query = "select uID from userInfo where uID=?";
//            PreparedStatement pstatement = con.prepareStatement(query);
//            pstatement.setString(1, userid);
//
//            ResultSet resultset = pstatement.executeQuery();
//
//            // resultset.next();
//            if (resultset.next()) {
//                String changepwdQuery = "update userInfo set password=? where uID=?";
//                PreparedStatement pstatement1 = con.prepareStatement(changepwdQuery);
//                pstatement1.setString(1, newPassword);
//                pstatement1.setString(2, userid);
//                pstatement1.executeUpdate();
//            } else {
//                throw new Exception("No user exist with " + userid + " userID");
//            }
//        } catch (Exception e) {
//            throw new Exception(e);
//        }
//    }

    /******************************************************************************************************/

   
    
    /**********************************************17 MARCH **************************************/

//    public void methodEntityAuthorityList(String user_id,String page_name){
//    	try{
//    		/*
//    		 * 
//    		 * select * from USER_FUNCTION_AUTHORITY where user_id='user_id' AND page='page_name';
//    		 * 
//    		 * select user_group_name from USER_INFO where user_id='E00010001000001';
//    		 * 
//    		 * select entity_type,default_authority from USER_GROUP where authority=1 AND default_authority LIKE '1___' AND  user_group_name ='Institute Admin';
//    		 *
//    		 *
//    		 *
//    		 *
//    		 *
//    		 */
//    	
//    		
//    	}catch (Exception e) {
//		}
//  
//    }
    
    
    public CM_entityInfoGetter[] methodEntityList(String user_id)throws Exception
    {
    	try
    	{
    		String university_id=user_id.substring(1,5);
    		
    		List<?> li=client.queryForList("entityList",university_id);
    		
    		CM_entityInfoGetter[] entityInfo=li.toArray(new CM_entityInfoGetter[li.size()]);
    		
    		return entityInfo;
    		
    	}catch (Exception e) {
    		logObj.logger.error(e);
			throw new Exception(e);
		}
    }
    
    
    public CM_entityInfoGetter[] methodParentEntityList(String user_id,CM_entityInfoGetter entityInfo)throws Exception
    {		
    	CM_entityInfoGetter[] entityInfo1 = null;
    	try
    	{
    		String university_id=user_id.substring(1,5);
    		
    		entityInfo.setUniversity_id(university_id);
    		
       		List<?> li2=client.queryForList("selectEntityType",entityInfo);
    		entityInfo1=li2.toArray(new CM_entityInfoGetter[li2.size()]);
    		
    		  		
    		 entityInfo1[0].setUniversity_id(university_id);
    		 entityInfo1[0].setLevel(entityInfo1[0].getLevel());
    		
    		List<?> li=client.queryForList("parentList", entityInfo1[0]);
    		
    		CM_entityInfoGetter[] entityInfo2=li.toArray(new CM_entityInfoGetter[li.size()]);
    	
    		return entityInfo2;
    		
    	}catch (Exception e) {
    		logObj.logger.error("parent entity list "+e);
			throw new Exception("parent entity list "+e);
		}
	
    }
    
    
    
    public void methodAddEntity(String user_id,CM_entityInfoGetter entityInfo)throws Exception
	{
    	String nextEntityID = null;
		
    	try{
    		
    		CM_entityInfoGetter[] entityInfo1;
    		
    		String university_id=user_id.substring(1,5);
    	
    		entityInfo.setUniversity_id(university_id);
    		
    		String value=university_id+'%';
    		
    		List<?> li=client.queryForList("selectMaxUniEntityID",value);
    		
    		entityInfo1=li.toArray(new CM_entityInfoGetter[li.size()]);
//    		System.out.println("entity result length "+entityInfo1.length);
    		//    		if(entityInfo1.length==0){
    			if(entityInfo1[0].getEntity_id()==null){
    			nextEntityID = "0001";
    		}else{
    		
    		entityInfo.setEntity_id(entityInfo1[0].getEntity_id());
    		
    		//generating next entity_id
    		
    		 int code = (Integer.parseInt(entityInfo.getEntity_id())) + 1;
        
        		if(code/10000 == 0){
        				 nextEntityID = (""+code);
        	                	 
       	      		 if(code/1000 == 0){
               		 		nextEntityID = ("0"+code);
        	                    	 
      	           		  if(code/100 == 0){
     	               		 	 nextEntityID = ("00"+code);
        	                               	 
        	          		   if(code/10 == 0){
                              		nextEntityID = ("000"+code);
  	                           } 		
      	                  } 
         	         }
            	 }
    		
    		}		
    		
    		entityInfo.setEntity_id(university_id+nextEntityID);
    		
    		String parentEntityName=entityInfo.getParent_entity_name();
//    	System.out.println("entity_type "+entityInfo.getEntity_type());
    		List<?> li1=null;
    		if(entityInfo.getEntity_type().equalsIgnoreCase("institute")){
    			li1=client.queryForList("selectParentUni",parentEntityName);
    		}else{
    			li1=client.queryForList("selectParentEntity",parentEntityName);
    		}
    		
    		
    		entityInfo1=li1.toArray(new CM_entityInfoGetter[li1.size()]);
    		
    
    		
    		entityInfo.setParent_entity_id(entityInfo1[0].getEntity_id());
    		
    		String date = (new java.sql.Timestamp(new java.util.Date().getTime()).toString()
                    .substring(0,
19));
    	
    		
    		entityInfo1[0].setUniversity_id(university_id);
    		entityInfo1[0].setEntity_type(entityInfo.getEntity_type());
    		
    		List<?> li2=client.queryForList("selectEntityType",entityInfo1[0]);
    		entityInfo1=li2.toArray(new CM_entityInfoGetter[li2.size()]);
    		
    		entityInfo.setLevel(entityInfo1[0].getLevel());
    	
    		
    		entityInfo.setEntity_type(entityInfo1[0].getEntity_type());
    		
    		
    		entityInfo.setInsert_time(date);
    		entityInfo.setCreator_id(user_id);
    		entityInfo.setModification_time(null);
    		entityInfo.setModifier_id(null);
    		
    		client.insert("insertNewEntity",entityInfo);
  	
    	}catch (Exception e) {
    		logObj.logger.error(e);
    		throw new Exception(e);
    		
		}
      	
	}  
    
    
public CM_entityInfoGetter[] methodPopulateEntitySuggest(String user_id,String entityType,String criteria)throws Exception  
{
	try{
	
		CM_entityInfoGetter entityObject=new CM_entityInfoGetter();
		CM_entityInfoGetter[] entityInfo1;
		
		String university_id=user_id.substring(1,5);

		entityObject.setUniversity_id(university_id);
		entityObject.setEntity_type(entityType);
		
		List<?> li2=client.queryForList("selectEntityType",entityObject);
		entityInfo1=li2.toArray(new CM_entityInfoGetter[li2.size()]);
		entityObject.setEntity_type(entityInfo1[0].getEntity_type());
				
		if(criteria.equalsIgnoreCase("name"))
		{
			List<?> li=client.queryForList("entityNameList",entityObject);
    		
    		entityInfo1=li.toArray(new CM_entityInfoGetter[li.size()]);
    		return entityInfo1;	
		}else if(criteria.equalsIgnoreCase("city")){
			
			List<?> li=client.queryForList("entityCityList",entityObject);
    		
    		entityInfo1=li.toArray(new CM_entityInfoGetter[li.size()]);
    		return entityInfo1;	
		}else
		{
			System.out.println("Wrong criteria");
			throw new Exception("Invalid criteria");
		}
		
	}catch (Exception e) {
		logObj.logger.error(e);
		throw new Exception(e);
	}
	
	
}
    
    
public CM_entityInfoGetter[] methodPopulateEntity(String user_id,String entityType,String criteria,String value)throws Exception  
{
	try{
	
		CM_entityInfoGetter entityObject=new CM_entityInfoGetter();
		CM_entityInfoGetter[] entityInfo1;
		
		String university_id=user_id.substring(1,5);

		entityObject.setUniversity_id(university_id);
		entityObject.setEntity_type(entityType);
		
		List<?> li2=client.queryForList("selectEntityType",entityObject);
		entityInfo1=li2.toArray(new CM_entityInfoGetter[li2.size()]);
		entityObject.setEntity_type(entityInfo1[0].getEntity_type());
			
	
		
		if(entityObject.getEntity_type().equalsIgnoreCase("ins")){
			
			if(criteria.equalsIgnoreCase("name"))
			{
				entityObject.setEntity_name(value);
				List<?> li=client.queryForList("selectFromNameUniWise",entityObject);
	    		
	    		entityInfo1=li.toArray(new CM_entityInfoGetter[li.size()]);
	    		return entityInfo1;	
			}else if(criteria.equalsIgnoreCase("city")){
				entityObject.setEntity_city(value);
				List<?> li=client.queryForList("selectFromCityUniWise",entityObject);
	    		
	    		entityInfo1=li.toArray(new CM_entityInfoGetter[li.size()]);
	    		return entityInfo1;	
			}else
			{
				System.out.println("Wrong criteria for manage");
				throw new Exception("Invalid criteria for manage page");
			}
			}
		
		
		
		else{
		
		if(criteria.equalsIgnoreCase("name"))
		{
			entityObject.setEntity_name(value);
			List<?> li=client.queryForList("selectFromName",entityObject);
    		
    		entityInfo1=li.toArray(new CM_entityInfoGetter[li.size()]);
    		return entityInfo1;	
		}else if(criteria.equalsIgnoreCase("city")){
			entityObject.setEntity_city(value);
			List<?> li=client.queryForList("selectFromCity",entityObject);
    		
    		entityInfo1=li.toArray(new CM_entityInfoGetter[li.size()]);
    		return entityInfo1;	
		}else
		{
			System.out.println("Wrong criteria for manage");
			throw new Exception("Invalid criteria for manage page");
		}
		}
		
	}catch (Exception e) {
		logObj.logger.error(e);
		throw new Exception(e);
	}
	
	
} 


public String methodEntityNameFromEntityID(String entity_id)throws Exception
{
	try{
		CM_entityInfoGetter[] entityInfo1;
		List<?> li=client.queryForList("parentEntityName",entity_id);
		
		entityInfo1=li.toArray(new CM_entityInfoGetter[li.size()]);
		String result=entityInfo1[0].getEntity_name();
		return result;
	}catch (Exception e) {
		logObj.logger.error(e);
		throw new Exception(e);
	}
		
}

public String methodUniversityName(String uni_id)throws Exception
{
	try{
		CM_entityInfoGetter[] entityInfo1;
		List<?> li=client.queryForList("universityName",uni_id);
		
		entityInfo1=li.toArray(new CM_entityInfoGetter[li.size()]);
		String result=entityInfo1[0].getEntity_name();
		return result;
	}catch (Exception e) {
		logObj.logger.error(e);
		throw new Exception(e);
	}
	
}


public void methodUpdateEntity(String user_id,CM_entityInfoGetter entityInfo)throws Exception
{
	try{
		CM_entityInfoGetter[] entityInfo1;
		String parentEntityName=entityInfo.getParent_entity_name();
		
		System.out.println(entityInfo.getEntity_type());
		List<?> li1;
		if(entityInfo.getEntity_type().equalsIgnoreCase("institute")){
			li1=client.queryForList("selectParentUni",parentEntityName);
		}else{
			li1=client.queryForList("selectParentEntity",parentEntityName);
		}
		
		entityInfo1=li1.toArray(new CM_entityInfoGetter[li1.size()]);
		
		entityInfo.setParent_entity_id(entityInfo1[0].getEntity_id());
		
		String date = (new java.sql.Timestamp(new java.util.Date().getTime()).toString()
                .substring(0,
19));
		
		entityInfo.setModification_time(date);
		entityInfo.setModifier_id(user_id);	
		client.update("updateEntity",entityInfo);
		
		
	}catch (Exception e) {
		logObj.logger.error(e);
		throw new Exception(e);
	}
}


public void methodDeleteEntity(String entity_id)throws Exception
{
	try{
		client.delete("deleteEntity",entity_id);
	}catch (Exception e) {
		logObj.logger.error(e);
		throw new Exception(e);
	}
	
}


public CM_ProgramInfoGetter[] methodUniversityProgramMode(String user_id)throws Exception
{
	try{
		String university_id=user_id.substring(1,5);
		List<?> list;
		list=client.queryForList("uniMode", university_id);
		return list.toArray(new CM_ProgramInfoGetter[list.size()]);
	}catch (Exception e) {
		logObj.logger.error(e);
		throw new Exception(e);
	}
}



public CM_ProgramInfoGetter[] methodUniversityProgramType(String user_id)throws Exception
{
	try{
		String university_id=user_id.substring(1,5);
		List<?> list;
		list=client.queryForList("uniType", university_id);
		return list.toArray(new CM_ProgramInfoGetter[list.size()]);
	}catch (Exception e) {
		logObj.logger.error(e);
		throw new Exception(e);
	}
}


/*
 * (non-Javadoc)
 * @see in.ac.dei.edrp.client.CM_connect#methodAddProgDetails(java.lang.String, in.ac.dei.edrp.client.CM_progMasterInfoGetter)
 */
public void methodAddProgDetails(String user_id, CM_progMasterInfoGetter progInfo) throws Exception
{
	try{
		String university_id=user_id.substring(1,5);
		String code="PRGMID";
		
		progInfo.setUniversity_id(university_id);
		progInfo.setSystem_code(code);
		
		
		List<?> li;
		li=client.queryForList("sysvalue",progInfo);
		CM_progMasterInfoGetter[] sysinfo=li.toArray(new CM_progMasterInfoGetter[li.size()]);
		
//		progInfo.setProgram_id(university_id+sysinfo[0].getProgram_id());
		int value=Integer.parseInt(sysinfo[0].getProgram_id())+1;
		String value1=null;		
        
		if(value/1000 == 0){
			value1 = (""+value);
	                	 
	      		 if(value/100 == 0){
	      			value1 = ("0"+value);
	                    	 
	           		  if(value/10 == 0){
	           			value1 = ("00"+value);
	                               	 
	          		  	
	                  } 
 	         }
    	 }
	
		progInfo.setSystem_value(value1);
		progInfo.setProgram_id(university_id+value1);
		client.update("updatesysvalue",progInfo);
			
		
		String date = (new java.sql.Timestamp(new java.util.Date().getTime()).toString()
                .substring(0,
19));
		progInfo.setInsert_time(date);
		progInfo.setCreator_id(user_id);
		progInfo.setModification_time(null);
		progInfo.setModifier_id(null);
				
		client.insert("insertintoprogmaster", progInfo);
		
		String[] startdate=progInfo.getStart_day_Month();
		for(int i=0;i<startdate.length;i++){
		progInfo.setStartdate(startdate[i]);
		client.insert("insertprogduration", progInfo);
		}
		
		
//		System.out.println("branch?? "+progInfo.isBranch());
	
		
		if(progInfo.isBranch()){

			String[][] arr=progInfo.getBranchSpec();

			if(arr!=null){

			if(arr.length>0){

				
				for(int i=0;i<arr.length;i++){
					progInfo.setBranchcode(arr[i][0]);
					client.insert("insertbranchdetail", progInfo);
					for(int j=1;j<arr[i].length;j++){

					progInfo.setSpecialization_code(arr[i][j]);
					client.insert("insertspecdetail", progInfo);
					}
				
				}
			}
			}

			if(progInfo.getBranch_code().length>0){
			String branch[]=progInfo.getBranch_code();

			if(branch.length>0){
		for(int k=0;k<progInfo.getBranch_code().length;k++){
			if(branch[k]!=null){
			progInfo.setBranchcode(branch[k]);
			progInfo.setSpecialization_code("000");
			client.insert("insertbranchdetail", progInfo);
			client.insert("insertspecdetail", progInfo);
			}
		}
			}
		}
			}
		

		
		if(progInfo.isSpecilization()){
			progInfo.setBranchcode("000");
			progInfo.setBranchname("NULL");
			client.insert("insertbranchdetail", progInfo);
			
//			System.out.println("coming upto here");
		
		String[] progLevelSpec=progInfo.getSpecialization_name();
		System.out.println(progLevelSpec.length);
		for(int i=0;i<progLevelSpec.length;i++){
//			System.out.println("inserting specs "+i +"      "+progLevelSpec[i]);
			progInfo.setBranchcode("000");

			progInfo.setSpecialization_code(progLevelSpec[i]);

		client.insert("insertspecdetail", progInfo);
		}
		}
		

		if(progInfo.isReservation())
		{
		String[] reserve=progInfo.getCategory1();
		String[] percent=progInfo.getPercentageSeats1();
		for(int i=0;i<reserve.length;i++){
			progInfo.setCategory(reserve[i]);
			progInfo.setPercentage_seats(percent[i]);
		client.insert("insertseatreserve", progInfo);
		}
		}
	}catch (Exception ex) {
		System.out.println("exception in methodAddProgDetails  "+ex);
		logObj.logger.error("methodAddProgDetails  "+ex);
		throw new Exception(ex);
	}
	
}


public CM_progMasterInfoGetter[] methodprogList(String user_id)
{
	try{
		String university_id=user_id.substring(1,5);
		CM_progMasterInfoGetter CMPMIG=new CM_progMasterInfoGetter();
		CMPMIG.setUniversity_id(university_id+"%");
		List<?> list=client.queryForList("selectProgList",CMPMIG);
		return list.toArray(new CM_progMasterInfoGetter[list.size()]);
		
	}catch (Exception e) {
		logObj.logger.error("exception in methodprogList "+e);
//		throw new Exception(e);
	}
	return null;
}




public CM_progMasterInfoGetter[] methodProgMasterDetailsForManage(String user_id,CM_progMasterInfoGetter object, String criteria)throws Exception
{
	try
{
		List<?> li = null;
	if(criteria.equalsIgnoreCase("code"))
	{
		li=client.queryForList("progBasicDetailFromCode",object);
	}else{
	 li=client.queryForList("progBasicDetail",object);
	}
	return li.toArray(new CM_progMasterInfoGetter[li.size()]);
}catch (Exception e) {
	logObj.logger.error(e);
	throw new Exception(e);
}
	
	
}



public CM_progMasterInfoGetter[] methodProgDurationDetailsForManage(String user_id,CM_progMasterInfoGetter object, String criteria)throws Exception
{
	try
{
		List<?> li = null;
	if(criteria.equalsIgnoreCase("code"))
	{
		List<?> list=client.queryForList("progBasicDetailFromCode",object);
		CM_progMasterInfoGetter[] object1=list.toArray(new CM_progMasterInfoGetter[list.size()]);
		object.setProgram_id(object1[0].getProgram_id());
		li=client.queryForList("progDurationDetail",object);
	}else{
	 li=client.queryForList("progDurationDetail",object);
	}
	return li.toArray(new CM_progMasterInfoGetter[li.size()]);
}catch (Exception e) {
	logObj.logger.error(e);
	throw new Exception(e);
}
}



public CM_progMasterInfoGetter[] methodProgBranchDetailsForManage(String user_id,CM_progMasterInfoGetter object, String criteria)throws Exception
{
	try
{
		List<?> li = null;
	if(criteria.equalsIgnoreCase("code"))
	{
		List<?> list=client.queryForList("progBasicDetailFromCode",object);
		CM_progMasterInfoGetter[] object1=list.toArray(new CM_progMasterInfoGetter[list.size()]);
		object.setProgram_id(object1[0].getProgram_id());
		li=client.queryForList("progBranchDetail",object);
	}else{
	 li=client.queryForList("progBranchDetail",object);
	}
	return li.toArray(new CM_progMasterInfoGetter[li.size()]);
}catch (Exception e) {
	logObj.logger.error(e);
	throw new Exception(e);
}
}


public CM_progMasterInfoGetter[] methodProgSpecDetailsForManage(String user_id,CM_progMasterInfoGetter object, String criteria)throws Exception
{
	try
{
		List<?> li = null;
	if(criteria.equalsIgnoreCase("code"))
	{
		List<?> list=client.queryForList("progBasicDetailFromCode",object);
		CM_progMasterInfoGetter[] object1=list.toArray(new CM_progMasterInfoGetter[list.size()]);
		object.setProgram_id(object1[0].getProgram_id());
		li=client.queryForList("progSpecDetail",object);
	}else{
	 li=client.queryForList("progSpecDetail",object);
	}
	return li.toArray(new CM_progMasterInfoGetter[li.size()]);
}catch (Exception e) {
	logObj.logger.error(e);
	throw new Exception(e);
}
}
    
    
public CM_progMasterInfoGetter[] methodProgReserveDetailsForManage(String user_id,CM_progMasterInfoGetter object, String criteria)throws Exception
{
	try
{
		List<?> li = null;
	if(criteria.equalsIgnoreCase("code"))
	{
		List<?> list=client.queryForList("progBasicDetailFromCode",object);
		CM_progMasterInfoGetter[] object1=list.toArray(new CM_progMasterInfoGetter[list.size()]);
		object.setProgram_id(object1[0].getProgram_id());
		li=client.queryForList("progReserveDetail",object);
	}else{
	 li=client.queryForList("progReserveDetail",object);
	}
	return li.toArray(new CM_progMasterInfoGetter[li.size()]);
}catch (Exception e) {
	logObj.logger.error(e);
	throw new Exception(e);
}
}
    
    
public CM_progMasterInfoGetter[] methodBranchList(String user_id)throws Exception
{
	try
{
	String university_id=user_id.substring(1,5);

	List<?> li = null;

 li=client.queryForList("branchList",university_id);

return li.toArray(new CM_progMasterInfoGetter[li.size()]);
	
}catch (Exception e) {
	logObj.logger.error(e);
	throw new Exception(e);
} 
}


public CM_progMasterInfoGetter[] methodSpecList(String user_id)throws Exception
{
	try
{
	String university_id=user_id.substring(1,5);

	List<?> li = null;

 li=client.queryForList("specList",university_id);

return li.toArray(new CM_progMasterInfoGetter[li.size()]);
	
}catch (Exception e) {
	logObj.logger.error(e);
	throw new Exception(e);
} 
}


public CM_progMasterInfoGetter[] methodCategoryList(String user_id)throws Exception
{
	try
{
	String university_id=user_id.substring(1,5);

	List<?> li = null;

 li=client.queryForList("categoryList",university_id);

return li.toArray(new CM_progMasterInfoGetter[li.size()]);
	
}catch (Exception e) {
	logObj.logger.error(e);
	throw new Exception(e);
} 
}


public void methodUpdateProgBasicDetails(String user_id,CM_progMasterInfoGetter object) throws Exception
{
	try{
		String date = (new java.sql.Timestamp(new java.util.Date().getTime()).toString()
                .substring(0,
19));
		object.setModifier_id(user_id);
		object.setModification_time(date);
		client.update("updateProgBasicDetail",object);
		
	}catch (Exception e) {
		logObj.logger.error(e);
		throw new Exception(e);
	}
}



public void methodDeleteProg(String program_id) throws Exception
{
	try{
		CM_progMasterInfoGetter object=new CM_progMasterInfoGetter();
		object.setProgram_id(program_id);
		
		client.delete("deleteProgBasicDetails",object);
		object.setStartdate("%");
		client.delete("deleteProgDurationDetails",object);
		object.setBranchcode("%");
		client.delete("deleteProgBranchDetails",object);
		object.setSpecialization_code("%");
		client.delete("deleteProgSpecDetails",object);
		object.setCategory_code("%");
		client.delete("deleteProgReserveDetails",object);
		
	}catch (Exception e) {
		logObj.logger.error(e);
		throw new Exception(e);
	}
}

public void methodProgDurationDelete(String program_id,String startdate)throws Exception
{
	try{
		CM_progMasterInfoGetter object=new CM_progMasterInfoGetter();
		object.setProgram_id(program_id);
		object.setStartdate(startdate);
		
		client.delete("deleteProgDurationDetails",object);

		
	}catch (Exception e) {
		logObj.logger.error(e);
		throw new Exception(e);
	}
	
}




public void methodSpecDelete(String program_id,String specialization_code)throws Exception
{
	try{
		CM_progMasterInfoGetter object=new CM_progMasterInfoGetter();
		object.setProgram_id(program_id);
		object.setSpecialization_code(specialization_code);
		
		client.delete("deleteProgSpecDetails",object);

		
	}catch (Exception e) {
		logObj.logger.error(e);
		throw new Exception(e);
	}
	
}

public void methodBranchDelete(String program_id,String branchcode)throws Exception
{
	try{
		CM_progMasterInfoGetter object=new CM_progMasterInfoGetter();
		object.setProgram_id(program_id);
		object.setBranchcode(branchcode);
		
		client.delete("deleteProgBranchDetails",object);

		
	}catch (Exception e) {
		logObj.logger.error(e);
		throw new Exception(e);
	}
	
}

public void methodSeatReservationDelete(String program_id,String category_code)throws Exception
{
	try{
		CM_progMasterInfoGetter object=new CM_progMasterInfoGetter();
		object.setProgram_id(program_id);
		object.setCategory_code(category_code);
		
		client.delete("deleteProgReserveDetails",object);

		
	}catch (Exception e) {
		logObj.logger.error(e);
		throw new Exception(e);
	}
	
}


public void methodUpdateProgDurationDetails(String user_id,CM_progMasterInfoGetter object) throws Exception
{
	try{
		String date = (new java.sql.Timestamp(new java.util.Date().getTime()).toString()
                .substring(0,
19));
		object.setModifier_id(user_id);
		object.setModification_time(date);
		client.update("updateProgDurationDetails1",object);
		client.update("updateProgDurationDetails2",object);
		
	}catch (Exception e) {
		logObj.logger.error(e);
		throw new Exception(e);
	}
}

public void methodUpdateProgReserveDetails(String user_id,CM_progMasterInfoGetter object) throws Exception
{
	try{
		String date = (new java.sql.Timestamp(new java.util.Date().getTime()).toString()
                .substring(0,
19));
		object.setModifier_id(user_id);
		object.setModification_time(date);
		client.update("updateSeatReserve",object);
	}catch (Exception e) {
		logObj.logger.error(e);
		throw new Exception(e);
	}
}


public void methodAddStartDate(String user_id,CM_progMasterInfoGetter object) throws Exception
{
	try{
		
		String date = (new java.sql.Timestamp(new java.util.Date().getTime()).toString()
                .substring(0,
19));
		object.setModifier_id(null);
		object.setModification_time(null);
		object.setInsert_time(date);
		object.setCreator_id(user_id);
		client.insert("insertprogduration",object);
		
		
	}catch (Exception e) {
		logObj.logger.error(e);
		throw new Exception(e);
	}
}



public void methodAddAnotherBranch(String user_id,CM_progMasterInfoGetter progInfo,String branch) throws Exception
{
try{
	
	String date = (new java.sql.Timestamp(new java.util.Date().getTime()).toString()
            .substring(0,
19));
	progInfo.setModifier_id(null);
	progInfo.setModification_time(null);
	progInfo.setInsert_time(date);
	progInfo.setCreator_id(user_id);
	
if(branch.equalsIgnoreCase("with")){
String[] arr=progInfo.getBranch_code();
	
		progInfo.setBranchcode(arr[0]);
		
		client.insert("insertbranchdetail", progInfo);

		for(int j=1;j<arr.length;j++){
		progInfo.setSpecialization_code(arr[j]);
		client.insert("insertspecdetail", progInfo);
		}
	
}
else{
	client.insert("insertbranchdetail", progInfo);
	progInfo.setSpecialization_code("000");
	client.insert("insertspecdetail", progInfo);
}
}catch (Exception e) {
	logObj.logger.error(e);
	throw new Exception(e);
}
}


public void methodAddAnotherSpec(String user_id,CM_progMasterInfoGetter progInfo) throws Exception
{
	try{
		
		String date = (new java.sql.Timestamp(new java.util.Date().getTime()).toString()
	            .substring(0,
	19));
		progInfo.setModifier_id(null);
		progInfo.setModification_time(null);
		progInfo.setInsert_time(date);
		progInfo.setCreator_id(user_id);
		progInfo.setBranchcode("000");
		client.insert("insertspecdetail", progInfo);
		
	}catch (Exception e) {
		logObj.logger.error(e);
		throw new Exception(e);
	}
}

/*
 * (non-Javadoc)
 * @see in.ac.dei.edrp.client.CM_connect#methodAddAnotherCategory(java.lang.String, in.ac.dei.edrp.client.CM_progMasterInfoGetter)
 */
public void methodAddAnotherCategory(String user_id,CM_progMasterInfoGetter progInfo) throws Exception
{
	try{
		
		String date = (new java.sql.Timestamp(new java.util.Date().getTime()).toString()
	            .substring(0,
	19));
		progInfo.setModifier_id(null);
		progInfo.setModification_time(null);
		progInfo.setInsert_time(date);
		progInfo.setCreator_id(user_id);
		
		client.insert("insertseatreserve", progInfo);
		
	}catch (Exception e) {
		logObj.logger.error(e);
		throw new Exception(e);
	}
}


/*
 * (non-Javadoc)
 * @see in.ac.dei.edrp.client.CM_connect#methodPasswordPolicy()
 */

public CM_passwordPolicyGetter[] methodPasswordPolicy()
    throws Exception {
    try {
        CM_passwordPolicyGetter[] CMPPG;

        List<?> li;
        li = client.queryForList("selectPasswordPolicy", null);
        CMPPG = li.toArray(new CM_passwordPolicyGetter[li.size()]);

        return CMPPG;
    } catch (Exception e1) {
    	logObj.logger.error(e1);
        throw new Exception(e1);
    }
}


/*
 * (non-Javadoc)
 * @see in.ac.dei.edrp.client.CM_connect#methodNumberOfTerms(java.lang.String)
 * 
 */

public Integer methodNumberOfTerms(String program_id) throws Exception
{
	Integer i=0;
	try{
		CM_progMasterInfoGetter progInfo[] ;
		List<?> list;
        list = client.queryForList("noOfTerms", program_id);
		progInfo= (CM_progMasterInfoGetter[]) list.toArray(new CM_progMasterInfoGetter[list.size()]);
		i=Integer.parseInt(progInfo[0].getNo_of_terms());
		return i;
	}catch(Exception e){
		logObj.logger.error(e);
		throw new Exception(e);
		
	}
	}


/*
 * Method to add details of term of program
 */
public void methodAddTermDetails(String user_id,CM_progTermDetailGetter object) throws Exception
{
try{
	CM_progTermDetailGetter[] duplicacy=methodGetTermDetails(object);
	if(duplicacy.length>0){
		throw new Exception("Record for this term of program already exists");
	}else{
	
		String date = (new java.sql.Timestamp(new java.util.Date().getTime()).toString()
	            .substring(0,
	19));
		object.setModifier_id(null);
		object.setModification_time(null);
		object.setInsert_time(date);
		object.setCreator_id(user_id);
		
		client.insert("insertTermDetails",object);
		
	}
	
}catch (Exception e) {
	logObj.logger.error("Exception in methodAddTermDetails"+e);
	throw e;
}
}

/*
 * Method for getting list of program Term Details
 */
public CM_progTermDetailGetter[] methodGetTermDetails(CM_progTermDetailGetter object) throws Exception
{
	try{
//		System.out.println("termid on server checking for duplicacy "+object.getEntity_program_term_id());
		List<?> list=client.queryForList("termList",object);
		CM_progTermDetailGetter[] result=(CM_progTermDetailGetter[]) list.toArray(new CM_progTermDetailGetter[list.size()]);
	return result;	
	}catch (Exception e) {
		logObj.logger.error("Exception in methodGetTermDetails"+e);
		throw new Exception(e);
	}
}

/*
 * Method for updating program term details
 */
public void methodUpdateTermDetails(String user_id,CM_progTermDetailGetter object) throws Exception
{
	try{
		
		String date = (new java.sql.Timestamp(new java.util.Date().getTime()).toString()
	            .substring(0,
	19));
		object.setModifier_id(user_id);
		object.setModification_time(date);
	
		
		client.update("updateTermDetails",object);
			
	}catch (Exception e) {
		logObj.logger.error("Exception in methodUpdateTermDetails"+e);
		throw new Exception(e);
	}
}

/*
 * Method for deleting details of term of program
 */
public void methodDeleteProgTermDetail(CM_progTermDetailGetter object) throws Exception
{
	try{
			
		client.delete("deleteTermDetails",object);
				
	}catch (Exception e) {
		logObj.logger.error(e);
		throw new Exception(e);
	}
}








    
}











