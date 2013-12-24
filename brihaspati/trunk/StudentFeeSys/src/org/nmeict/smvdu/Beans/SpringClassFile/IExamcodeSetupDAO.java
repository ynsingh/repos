package org.nmeict.smvdu.Beans.SpringClassFile;

import java.util.List;

import org.nmeict.smvdu.Beans.ExamCodeSetUp;

public interface IExamcodeSetupDAO {

	public void addExamcodeSetup(ExamCodeSetUp examCodeSetup);
	public void update(List<ExamCodeSetUp> examCodeSetup);
	public List<ExamCodeSetUp> loadAllExamcodeSetup(int ecsCode);
}
