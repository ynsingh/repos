package in.ac.dei.edrp.cms.daoimpl.studentprogramswitch;

import in.ac.dei.edrp.cms.dao.Studentprogramswitch.Studentprogramswitchdao;
import in.ac.dei.edrp.cms.domain.studentprogramswitch.Studentprogramswitch;

import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.apache.log4j.Logger;
public class Studentprogramswitchdaoimpl extends SqlMapClientDaoSupport
		implements Studentprogramswitchdao {

	@SuppressWarnings("unchecked")
	public List<Studentprogramswitch> getProgramswitches(Studentprogramswitch student) {
		System.out.println("inside impl student program switch");
		List<Studentprogramswitch> switches = getSqlMapClientTemplate()
				.queryForList("studentprogramswitch.getSwitches",student);
		if (switches.size() > 0) {
			return (switches);
		} else {
			return null;
		}
			
		
		
	}

	@SuppressWarnings("unchecked")
	public List<Studentprogramswitch> getsemesterdate(
			Studentprogramswitch semesterdetail) {
		List<Studentprogramswitch> semesterdate = getSqlMapClientTemplate()
				.queryForList("studentprogramswitch.getsemsterdate",
						semesterdetail);
		if (semesterdate.size() > 0) {
			return (semesterdate);
		} else {
			return null;
		}
		
		
		
	}

	@SuppressWarnings("unchecked")
	public List<Studentprogramswitch> getstudentforswitches(
			Studentprogramswitch student) {
		
		List<Studentprogramswitch> studentlist=new ArrayList<Studentprogramswitch>();
		List<Studentprogramswitch> studentlistforprog=new ArrayList<Studentprogramswitch>();
		if(student.getFinalSemStatus().equalsIgnoreCase("F")){
			System.out.println("inside final impl");
			studentlist = getSqlMapClientTemplate()
				.queryForList("studentprogramswitch.getstudentforSwitchesFinal",student);
			studentlistforprog=getSqlMapClientTemplate().queryForList("studentprogramswitch.getstudentforSwitchesFinalSem",student);
			
			for(int i=0;i<studentlistforprog.size();i++){
				for(int j=0;j<studentlist.size();j++){
					if(studentlistforprog.get(i).getRollNumber().equalsIgnoreCase(studentlist.get(j).getRollNumber())){
						studentlist.remove(j);
					}
				}
			}
		}
		else{ 
			studentlist = getSqlMapClientTemplate()
				.queryForList("studentprogramswitch.getstudentforSwitches",
						student);
		}
		if (studentlist.size() > 0) {
			return (studentlist);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Studentprogramswitch> switchstudent(
			Studentprogramswitch semesterdetail) {
		int y = 0;
		List<Studentprogramswitch> switchstatus;
		if (semesterdetail.getSwitchstatus().equalsIgnoreCase("SWT")) {
			y = getSqlMapClientTemplate().update(
					"studentprogramswitch.switchstudent", semesterdetail);
		} else {
			y = getSqlMapClientTemplate().update(
					"studentprogramswitch.unswitchstudent", semesterdetail);
		}
		if (y == 1) {
			List<Studentprogramswitch> studentswitchstatus = getSqlMapClientTemplate()
					.queryForList("studentprogramswitch.getswitchstatus",
							semesterdetail);
			if (studentswitchstatus.size() > 0) {
				return studentswitchstatus;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Studentprogramswitch> getSessionDate() {
		List<Studentprogramswitch> sessionDate = getSqlMapClientTemplate()
					.queryForList("studentprogramswitch.getsessiondate");
		if (sessionDate.size() > 0) {
			return (sessionDate);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Studentprogramswitch> switchstudentPST(
			Studentprogramswitch semesterdetail) {
		int y = 0;
		 getSqlMapClientTemplate().insert("studentprogramswitch.insertRecordPST", semesterdetail);
	
		List<Studentprogramswitch> studentswitchstatus = getSqlMapClientTemplate()
				.queryForList("studentprogramswitch.getswitchstatusInsert",semesterdetail);
		if (studentswitchstatus.size() > 0) {
			return studentswitchstatus;
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Studentprogramswitch> deleteswitchstudentPST(
			Studentprogramswitch semesterdetail) {
		int y = 0;
		y = getSqlMapClientTemplate().delete("studentprogramswitch.deleteRecordPST", semesterdetail);
		
		if (y == 1) {
			List<Studentprogramswitch> studentswitchstatus = getSqlMapClientTemplate()
					.queryForList("studentprogramswitch.getswitchstatusFinal",semesterdetail);
			if (studentswitchstatus.size() > 0) {
				return studentswitchstatus;
			} else {
				return null; 
			}
		}else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Studentprogramswitch> selectSwitchAll(
			Studentprogramswitch semesterdetail) {
		List<Studentprogramswitch> studentlist=new ArrayList<Studentprogramswitch>();
		int x=0,y = 0;
		if(semesterdetail.getFinalSemStatus().equalsIgnoreCase("F")){
			studentlist = getSqlMapClientTemplate().queryForList("studentprogramswitch.getallstudentforSwitchesFinal",semesterdetail);
			if(studentlist.size()>0){
				for(int i=0;i<studentlist.size();i++){
					semesterdetail.setRollNumber(studentlist.get(i).getRollNumber());
					semesterdetail.setPrvswitchstatus(studentlist.get(i).getSwitchstatus());
					getSqlMapClientTemplate().insert("studentprogramswitch.insertRecordPST", semesterdetail);
					x++;
				}
			}
		}
		else{
			studentlist = getSqlMapClientTemplate().queryForList("studentprogramswitch.getallstudentforSwitches",semesterdetail);
			if(studentlist.size()>0){
				for(int i=0;i<studentlist.size();i++){
					semesterdetail.setRollNumber(studentlist.get(i).getRollNumber());
					semesterdetail.setPrvswitchstatus(studentlist.get(i).getSwitchstatus());
					y = getSqlMapClientTemplate().update("studentprogramswitch.switchstudent", semesterdetail);
					x=x+y;
				}
			}
		}
		return studentlist;
	}
}
