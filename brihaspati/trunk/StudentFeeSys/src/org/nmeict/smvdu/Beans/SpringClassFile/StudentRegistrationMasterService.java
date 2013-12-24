package org.nmeict.smvdu.Beans.SpringClassFile;

import java.util.List;

import org.nmeict.smvdu.Beans.StudentRegMaster;

public class StudentRegistrationMasterService implements IStudentRegistrationMasterService{

	public StudentRegistrationMasterDAO studentRegistrationMasterDAO = new StudentRegistrationMasterDAO();
	public StudentRegistrationMasterDAO getStudentRegistrationMasterDAO() {
		return studentRegistrationMasterDAO;
	}

	public void setStudentRegistrationMasterDAO(
			StudentRegistrationMasterDAO studentRegistrationMasterDAO) {
		this.studentRegistrationMasterDAO = studentRegistrationMasterDAO;
	}

	@Override
	public void addStudentKeyDetails(StudentRegMaster studentRegMaster) {
		getStudentRegistrationMasterDAO().addStudentKeyDetails(studentRegMaster);
		
	}

	
	@Override
	public void update(List<StudentRegMaster> studentRegMaster) {
			getStudentRegistrationMasterDAO().update(studentRegMaster);
		
	}

	@Override
	public List<StudentRegMaster> loadAllStudent() {
			
		return getStudentRegistrationMasterDAO().loadAllStudent(); 
	}

	@Override
	public List<StudentRegMaster> loadAllQueryStudent(String query) {
		// TODO Auto-generated method stub
		return getStudentRegistrationMasterDAO().loadAllQueryStudent(query);
	}

}
