package org.bss.brihaspatisync.reflector.network.http;

/**
 * @(#)HttpServer.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011-2013 ETRG, IIT Kanpur.
 */

import org.bss.brihaspatisync.reflector.util.RuntimeDataObject;
import org.bss.brihaspatisync.reflector.buffer_mgt.MyHashTable;

import org.bss.brihaspatisync.reflector.buffer_mgt.BufferMgt;

import org.bss.brihaspatisync.reflector.util.RuntimeDataObject;
import org.bss.brihaspatisync.reflector.network.serverdata.UserListUtil;
import org.bss.brihaspatisync.reflector.network.serverdata.HandraiseAction;


/**
 * @author <a href="mailto:arvindjss17@gmail.com"> Arvind Pal  </a>Created a basic multithreaded HttpServer on 05Feb2011, 31Dec2012
 */

public class HttpGetPost {
	
	private static HttpGetPost httpserver=null;
	private HandraiseAction handraiseAction=new HandraiseAction();
	
	public static HttpGetPost getController() throws Exception {
              	if(httpserver==null)
                       	httpserver=new HttpGetPost();
                return httpserver;
        }
	
	public String putValue_CH_WB(String req,String lecture_id,String username) {
		try {
			/**
 			 ** split() is used to get lecture_id and the data from this request , 
			 ** where data_value[0] contains lecture_id and data_value[1] contains data.
                         */
				
                        String data_value[]=req.split("req");
			if(data_value[1].startsWith("HandRaiseAction")){
                        	data_value[1]=java.net.URLDecoder.decode(data_value[1]).replaceAll("HandRaiseAction","");
                                handraiseAction.setValue(data_value[1]);
                    	}else {
                        	String s=data_value[0];
                                String strarray[]=s.split(",");
                                data_value[0]=strarray[1];
                                RuntimeDataObject runtimeObject=RuntimeDataObject.getController();
                                if(strarray[0].equals("instructor")){
                                	/** ip for master reflector */
                                        runtimeObject.setMastrerReflecterCourseid(strarray[1]);
                            	}
                                MyHashTable temp_ht=runtimeObject.getMyHashTable();
                                /** set All course id */
                                runtimeObject.setCourseID(data_value[0]);
                                if(runtimeObject.getStatusCourseidIP(data_value[0]+"#"+username)) {
                                	/** set course id and ip */
                                        runtimeObject.setCourseid_IP(data_value[0]+"#"+username);
                             	}

                                if(data_value[2].startsWith("parent")) {
					data_value[2]=data_value[2].replace("parent","");
                                        if(!data_value[2].equals("")) {
                                        	//org.bss.brihaspatisync.reflector.network.ref_to_ref.CommonDataObject.getController().get_setStatusCourseId(data_value[0],data_value[2]);
                                     	}
                         	} 
				if(!temp_ht.getStatus("ch_wb"+lecture_id)) {
					BufferMgt buffer_mgt= new BufferMgt();
					temp_ht.setValues("ch_wb"+lecture_id,buffer_mgt);
					buffer_mgt.putByte(data_value[1].getBytes(),username,"ch_wb"+lecture_id);
					return null;
               	                } else if(temp_ht.getStatus("ch_wb"+lecture_id)) {
                       	                BufferMgt buffer_mgt=temp_ht.getValues("ch_wb"+lecture_id);
					if(!data_value[1].equals("nodata"))
						buffer_mgt.putByte(data_value[1].getBytes(),username,"ch_wb"+lecture_id);
					byte[] ch_wb_data=buffer_mgt.sendData(username,"ch_wb"+lecture_id);
					String userlist_data=UserListUtil.getContriller().getDataForVector(data_value[0]);
					if(userlist_data.equals("")) userlist_data="nodata";
					String ch_wb="";
					if(ch_wb_data == null) ch_wb="nodata";
					else ch_wb=new String(ch_wb_data);
					String userlist_ch_wb_data=userlist_data+"  "+ch_wb;
					return userlist_ch_wb_data;
                                }
                     	}   
                }catch(Exception ex){System.out.println("Error in in http post and get server "+ex.getMessage());}
		return null;
        }
}

