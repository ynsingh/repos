package org.bss.brihaspatisync.reflector.network.tcp;

/**
 * TCPUtil.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2009
 */

import java.util.Vector;

import org.bss.brihaspatisync.reflector.buffer_mgt.MyHashTable;
import org.bss.brihaspatisync.reflector.util.RuntimeDataObject;
import org.bss.brihaspatisync.reflector.network.serverdata.VectorClass;

/**
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a> 
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 */

public class TCPUtil {

	private static TCPUtil tcps=null;

	private MaintainLog log=MaintainLog.getController();
		
	private MyHashTable temp_ht=RuntimeDataObject.getController().getUserListMyHashTable();
	
	/**
        * Controller for the class.
        */

	private TCPUtil(){}
	
	public static TCPUtil getController(){
                if(tcps==null){
                        tcps=new TCPUtil();
                }
                return tcps;
        }
	
	public void getString(String course_id,String value ) {
		try {
			if(!(temp_ht.getStatusCourseId(course_id))){
				temp_ht.setCourseIdUserListVector(course_id,new VectorClass());
                        }
			VectorClass vectorclass=temp_ht.getCourseIdUserListVector(course_id);
			log.setString("Vector Value ---------------- > "+vectorclass.getBackupTreeValue().toString());
			vectorclass.setBackupTreeValue(value);
		}catch(Exception e){
			log.setString("Error in method getString in TCPUtil.java class !! "+e.getMessage());
		}
	}

}


