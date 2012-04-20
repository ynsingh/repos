package org.iitk.brihaspati.controller;

/*
 * @(#)DirectoryController.java
 *
 *  Copyright (c) 2012 ETRG,IIT Kanpur.
 *  All Rights Reserved.
 *
 *  Redistribution and use in source and binary forms, with or
 *  without modification, are permitted provided that the following
 *  conditions are met:
 *
 *  Redistributions of source code must retain the above copyright
 *  notice, this  list of conditions and the following disclaimer.
 *
 *  Redistribution in binary form must reproducuce the above copyright
 *  notice, this list of conditions and the following disclaimer in
 *  the documentation and/or other materials provided with the
 *  distribution.
 *
 *
 *  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 *  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 *  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 *  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 *  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 *  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *
 *  Contributors: Members of ETRG, I.I.T. Kanpur
 *
 */

/**
 *  @author: <a href="mailto:vipulk@iitk.ac.in">vipul kumar pal</a>
 */


import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;
import bus.Directory;
import java.sql.Connection;
import java.sql.Statement;
import db.DirectoryManagerDaoJdbc;
import java.util.Vector;
import java.util.List;
import java.sql.ResultSet;
import org.iitk.brihaspati.om.TelephoneDirectory;
import org.iitk.brihaspati.om.TelephoneDirectoryPeer;
import org.iitk.brihaspati.utils.Pagination;
import org.iitk.brihaspati.utils.ErrorDumpUtil;
import org.iitk.brihaspati.utils.MD5;
import org.apache.torque.Torque; 
import javax.servlet.ServletContext;
import org.apache.torque.TorqueException;
import org.apache.torque.util.Criteria;

public class DirectoryController extends SimpleFormController {

	private Torque set=null;
        private ServletContext context=null;
	public static String url;	
    	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Map myModel = new HashMap();
		String mode=request.getParameter("mode");
		myModel.put("mod",mode);
		String pg=request.getParameter("pg");
		myModel.put("page",pg);

		String gp=request.getParameter("frm");
		String ip=request.getParameter("ip");
		String port=request.getParameter("port");
		String random=request.getParameter("random");
		String mail=request.getParameter("mail");
		String str=request.getParameter("str");
		url=request.getParameter("url");
		myModel.put("ip",ip);
		myModel.put("port",port);
		myModel.put("random",random);
		myModel.put("mail",mail);
		myModel.put("str",str);
		myModel.put("url",url);
		String ms=null;

                try{
			ms=MD5.getMD5(ip+random+mail);
		}catch (Exception exc) {}
		Vector l=new Vector();
        	if(str.equals(ms)){
                try {
			set=new Torque();
                        String tempFileName = getServletContext().getRealPath("Torque.properties");
                        set.init(tempFileName);

			List st=null;
			String match=request.getParameter("matchstring");
			myModel.put("match",match);

			String role=null;
                        String show=null;
			if(mode.equals("prot")){
				role="instructor";
                                show="prot";
			}else if(mode.equals("stud")){
				role="student";
                                show="stud";				
			}
			myModel.put("role",role);
                        myModel.put("show",show);
			
				Criteria crit = new Criteria();
				String table="TELEPHONE_DIRECTORY";
			if( (match!=null) && (!match.equals("")) ) {
				String query = request.getParameter("querylist");
                                if(query.equals("name")){
                                        str="NAME";
				}
                                else if(query.equals("mailid"))
                                        str="MAIL_ID";
                                else if(query.equals("address"))
                                        str="ADDRESS";
                                else if(query.equals("department"))
                                        str="DEPARTMENT";

                                crit=new Criteria();
				crit.add(table,str,(Object)("%"+match+"%"),crit.LIKE);
                                st=TelephoneDirectoryPeer.doSelect(crit);
				}
				else{
                                	Criteria crit1=new Criteria();
					crit1.addGroupByColumn(TelephoneDirectoryPeer.USER_ID);
                                	st=TelephoneDirectoryPeer.doSelect(crit1);
				}
			int size = 0;
			for(int i=0;i<st.size();i++){
				TelephoneDirectory element=(TelephoneDirectory)(st.get(i));
				size++;
				Directory dir = new Directory();
				int id=element.getId();
				dir.setId(id);
				dir.setName(element.getName());
				dir.setMailid(element.getMailId());
				dir.setAddress(element.getAddress());

				if(mode.equals("psnl")){
                                        String officeno=element.getOfficeNo();
                                        String []off = officeno.split("-");
                                        if(officeno.startsWith("1") || officeno.startsWith("2") || officeno.startsWith("3"))
                                                dir.setOfficeno(off[1]+off[2]+off[3]+off[4]);
                                        else{   dir.setOfficeno("");    }

                                        String mobileno=element.getMobileNo();
                                        String []temp = mobileno.split("-");
                                        if(mobileno.startsWith("1") || mobileno.startsWith("2") || mobileno.startsWith("3"))
                                                dir.setMobileno(temp[1]+temp[2]+temp[3]+temp[4]);
                                        else{dir.setMobileno("");}

                                        String homeno = element.getHomeNo();
                                        String []home = homeno.split("-");
                                        if(homeno.startsWith("1") || homeno.startsWith("2") || homeno.startsWith("3"))
                                                dir.setHomeno(home[1]+home[2]+home[3]+home[4]);
                                        else{dir.setHomeno("");}

                                        String otherno = element.getOtherNo();
                                        String []other = otherno.split("-");
                                        if(otherno.startsWith("1") || otherno.startsWith("2") || otherno.startsWith("3"))
                                                dir.setOtherno(other[1]+other[2]+other[3]+other[4]);
                                        else{dir.setOtherno("");}
                                }
				else if(mode.equals("prot")){
                                        String officeno=element.getOfficeNo();
                                        String []off = officeno.split("-");
                                        if(officeno.startsWith("1") || officeno.startsWith("2"))
                                                dir.setOfficeno(off[1]+off[2]+off[3]+off[4]);
                                        else{   dir.setOfficeno("");    }

                                        String mobileno=element.getMobileNo();
                                        String []temp = mobileno.split("-");
                                        if(mobileno.startsWith("1") || mobileno.startsWith("2"))
                                                dir.setMobileno(temp[1]+temp[2]+temp[3]+temp[4]);
                                        else{dir.setMobileno("");}

                                        String homeno = element.getHomeNo();
                                        String []home = homeno.split("-");
                                        if(homeno.startsWith("1") || homeno.startsWith("2"))
                                                dir.setHomeno(home[1]+home[2]+home[3]+home[4]);
                                        else{dir.setHomeno("");}

                                        String otherno = element.getOtherNo();
                                        String []other = otherno.split("-");
                                        if(otherno.startsWith("1") || otherno.startsWith("2"))
                                                dir.setOtherno(other[1]+other[2]+other[3]+other[4]);
                                        else{dir.setOtherno("");}

                                }
				else if(mode.equals("stud")){
                                        String officeno=element.getOfficeNo();
                                        String []off = officeno.split("-");
                                        if(officeno.startsWith("1"))
                                                dir.setOfficeno(off[1]+off[2]+off[3]+off[4]);
                                        else{   dir.setOfficeno("");    }

                                        String mobileno=element.getMobileNo();
                                        String []temp = mobileno.split("-");
                                        if(mobileno.startsWith("1"))
                                                dir.setMobileno(temp[1]+temp[2]+temp[3]+temp[4]);
                                        else{dir.setMobileno("");}

                                        String homeno = element.getHomeNo();
                                        String []home = homeno.split("-");
                                        if(homeno.startsWith("1"))
                                                dir.setHomeno(home[1]+home[2]+home[3]+home[4]);
                                        else{dir.setHomeno("");}

                                        String otherno = element.getOtherNo();
                                        String []other = otherno.split("-");
                                        if(otherno.startsWith("1"))
                                                dir.setOtherno(other[1]+other[2]+other[3]+other[4]);
                                        else{dir.setOtherno("");}

                                }

				dir.setDepartment(element.getDepartment());
				dir.setDesignation(element.getDesignation());
				dir.setState(element.getState());
				l.add(dir);
			}
			if(size==0){
                                        myModel.put("ErrorMsg","No Data find Respected Your search");
                                        }

			myModel.put("list",l);

                        int AdminConf = 10;
                        myModel.put("AdminConf",new Integer(AdminConf));
                        myModel.put("AdminConf_str",Integer.toString(AdminConf));
                        String Index=request.getParameter("startIndex");
			int startIndex;
			if(Index==null){
				startIndex=0;
			}else{
				startIndex=Integer.parseInt(Index);;
			}
                        String status=new String();
                        int t_size=size;
			if(size!=0){
                                        status="Noblank";
                                        int value[]=new int[7];
                                        value=Pagination.linkVisibility(startIndex,t_size,AdminConf);
                                        int k=value[6];
                                        myModel.put("k",String.valueOf(k));
                                        Integer total_size=new Integer(t_size);
                                        myModel.put("total_size",total_size);
                                        int eI=value[1];
                                        Integer endIndex=new Integer(eI);
					myModel.put("stIndex",value[0]);

                                        myModel.put("endIndex",endIndex);
                                        int check_first=value[2];
                                        myModel.put("check_first",String.valueOf(check_first));
                                        int check_pre=value[3];
                                        myModel.put("check_pre",String.valueOf(check_pre));
                                        int check_last1=value[4];
                                        myModel.put("check_last1",String.valueOf(check_last1));
                                        int check_last=value[5];
                                        myModel.put("check_last",String.valueOf(check_last));

                                        myModel.put("startIndex",String.valueOf(eI));
                                        Vector splitlist=Pagination.listDivide(l,startIndex,AdminConf);

                                        myModel.put("dirList",splitlist);
                                }
			else
                                {
                                        status="blank";
                                }
                                myModel.put("status",status);


		}catch(Exception es){}
		}else{
			logger.info("U R NOT AUTHORIZED");
		}
		return new ModelAndView("directory", "model1", myModel);
	}
}

