package org.bss.brihaspatisync.reflector.network.serverdata;

/**
 * UserListTimer.java
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2007-2008 ETRG,IIT Kanpur.
 */


import java.util.Vector;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 */

public class VectorClass {

	private String userlistvector="";
	
	private Vector backupTreeVector=new Vector(3);
	
        protected VectorClass() { }
	
	protected void addValue(String str){
		if(!str.equals("noUser"))
                	userlistvector=str;
		else
			userlistvector="";
        }
	
	public String getValue(){
		return userlistvector;
        }
	
	/** set Backup tree Vector
         * [{#GParent_ip},{#Parent_ip},{#Client_ip}]
         */
		
        public void setBackupTreeValue(String value){
		try {
			if(backupTreeVector.size()==0){
        	                backupTreeVector.add(0,"#GParent");
				backupTreeVector.add(1,"#Parent");
                        	backupTreeVector.add(2,"#Client");
			}else {
				if(value.startsWith("#GParent")) {		
					backupTreeVector.setElementAt(value,0); 
				}else if(value.startsWith("#Parent")) {
					backupTreeVector.setElementAt(value,1);
				}else if(value.startsWith("#Client")) {
					backupTreeVector.setElementAt(value,2); 
				}
			}
		}catch(Exception e){System.out.println("error in VectorClass.java  "+e.getMessage());}	 
        }
	
       	public Vector getBackupTreeValue(){
		return backupTreeVector;
        }
}

	
