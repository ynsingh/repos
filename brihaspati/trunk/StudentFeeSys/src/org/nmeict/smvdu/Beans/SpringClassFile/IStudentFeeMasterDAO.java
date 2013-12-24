package org.nmeict.smvdu.Beans.SpringClassFile;

import java.util.List;

import org.nmeict.smvdu.Beans.StudentMaster;

public interface IStudentFeeMasterDAO {
	public void addStudentFeeMaster(StudentMaster studentFeeMaster);
	public void update(List<StudentMaster> studentFeeMaster);
	public List<StudentMaster> loadAllFeeDetails();
}
