package org.nmeict.smvdu.Beans.SpringClassFile;

import java.util.List;

import org.nmeict.smvdu.Beans.StudentMaster;

public class StudentFeeMasterService implements IStudentFeeMasterService{

	private StudentFeeMasterDAO studentFeeMasterDAO = new StudentFeeMasterDAO();
	
	
	
	public StudentFeeMasterDAO getStudentFeeMasterDAO() {
		return studentFeeMasterDAO;
	}

	public void setStudentFeeMasterDAO(StudentFeeMasterDAO studentFeeMasterDAO) {
		this.studentFeeMasterDAO = studentFeeMasterDAO;
	}

	@Override
	public void addStudentFeeMaster(StudentMaster studentFeeMaster) {
		getStudentFeeMasterDAO().addStudentFeeMaster(studentFeeMaster);
		
	}

	@Override
	public void update(List<StudentMaster> studentFeeMaster) {
		getStudentFeeMasterDAO().update(studentFeeMaster);
		
	}

	@Override
	public List<StudentMaster> loadAllFeeDetails() {
		
		return getStudentFeeMasterDAO().loadAllFeeDetails();
	}

	@Override
	public StudentMaster loadStudentDetail(String entryId) {
		// TODO Auto-generated method stub
		return null;
	}

}
