package in.ac.dei.edrp.cms.daoimpl.studentprogramswitch;

import in.ac.dei.edrp.cms.dao.Studentprogramswitch.Studentprogramswitchdao;
import in.ac.dei.edrp.cms.domain.studentprogramswitch.Studentprogramswitch;

import java.util.List;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;


import org.apache.log4j.Logger;
public class Studentprogramswitchdaoimpl  extends SqlMapClientDaoSupport 
implements Studentprogramswitchdao {
	
	public List<Studentprogramswitch> getProgramswitches(){
	
		System.out.println("inside impl student program switch" );
		List<Studentprogramswitch> switches =  
			getSqlMapClientTemplate().queryForList("studentprogramswitch.getSwitches");

				
		if (switches.size()>0){
			return(switches);
		}else{
			return null ;
		}
			
		
		
	}



	public List<Studentprogramswitch> getsemesterdate(Studentprogramswitch semesterdetail) {
		List<Studentprogramswitch> semesterdate =  
			getSqlMapClientTemplate().queryForList("studentprogramswitch.getsemsterdate",semesterdetail);
		
		if (semesterdate.size()>0){
			return(semesterdate);
		}else{
			return null ;
		}
		
		
		
	}

	public List<Studentprogramswitch> getstudentforswitches(
			Studentprogramswitch student) {
		List<Studentprogramswitch> studentlist =  
			getSqlMapClientTemplate().queryForList("studentprogramswitch.getstudentforSwitches" ,student);

				
		if (studentlist.size()>0){
			return(studentlist);
		}else{
			return null ;
		}	
	}



	
		public List<Studentprogramswitch> switchstudent(
				Studentprogramswitch semesterdetail) {
		
		int y = 0;
			 List<Studentprogramswitch> switchstatus ;
		        if(semesterdetail.getSwitchstatus().equalsIgnoreCase("SWT")){
		      
		        	y = getSqlMapClientTemplate().update("studentprogramswitch.switchstudent", semesterdetail) ;
		
		        }else{
		        	
		        	 y = getSqlMapClientTemplate().update("studentprogramswitch.unswitchstudent", semesterdetail) ;
		        }
		
		if (y==1){
			List<Studentprogramswitch> studentswitchstatus = 
				getSqlMapClientTemplate().queryForList("studentprogramswitch.getswitchstatus" ,semesterdetail);
							
			if (studentswitchstatus.size()>0){
				return studentswitchstatus ;
			}else {
				return null;
			}

		}else{
			return null;	
		}
		
		
		// TODO Auto-generated method stub
		
	}




	}
	
	

