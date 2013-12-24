package org.nmeict.smvdu.Beans.SpringClassFile;

import java.util.List;

import org.nmeict.smvdu.Beans.StudentRegMaster;

public interface IStudentRegistrationMasterDAO {
	public void addStudentKeyDetails(StudentRegMaster studentRegMaster);
	public void update(List<StudentRegMaster> studentRegMaster);
	public List<StudentRegMaster> loadAllStudent();
	public List<StudentRegMaster> loadAllQueryStudent(String query);
}
