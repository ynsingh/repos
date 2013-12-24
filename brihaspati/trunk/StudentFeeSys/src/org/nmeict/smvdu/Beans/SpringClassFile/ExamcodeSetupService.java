package org.nmeict.smvdu.Beans.SpringClassFile;

import java.util.List;

import org.nmeict.smvdu.Beans.ExamCodeSetUp;

public class ExamcodeSetupService implements IExamcodeSetupService{

	private ExamcodeSetupDAO examcodeSetupDAO = new ExamcodeSetupDAO(); 
	
	
	public ExamcodeSetupDAO getExamcodeSetupDAO() {
		return examcodeSetupDAO;
	}

	public void setExamcodeSetupDAO(ExamcodeSetupDAO examcodeSetupDAO) {
		this.examcodeSetupDAO = examcodeSetupDAO;
	}

	@Override
	public void addExamcodeSetup(ExamCodeSetUp examCodeSetup) {
		getExamcodeSetupDAO().addExamcodeSetup(examCodeSetup);
		
	}

	

	@Override
	public List<ExamCodeSetUp> loadAllExamcodeSetup(int ecsCode) {
		return getExamcodeSetupDAO().loadAllExamcodeSetup(ecsCode); 
	}

	@Override
	public void update(List<ExamCodeSetUp> examCodeSetup) {
		getExamcodeSetupDAO().update(examCodeSetup); 
		
	}

}
