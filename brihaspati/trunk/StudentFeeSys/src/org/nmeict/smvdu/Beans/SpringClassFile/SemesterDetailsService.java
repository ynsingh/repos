package org.nmeict.smvdu.Beans.SpringClassFile;

import java.util.List;

import org.hibernate.Session;
import org.nmeict.smvdu.Beans.SemesterMaster;
import org.nmeict.smvdu.HibernateHelper.HibernateDataSourceConnection;

public class SemesterDetailsService implements ISemesterDetailsService{

	private SemesterDetailsDAO semesterDetailsDAO = new SemesterDetailsDAO();
	
	@Override
	public void addAllSemester(SemesterMaster semesterMaster) {
		getSemesterDetailsDAO().addAllSemester(semesterMaster);
	}

	public SemesterDetailsDAO getSemesterDetailsDAO() {
		return semesterDetailsDAO;
	}

	public void setSemesterDetailsDAO(SemesterDetailsDAO semesterDetailsDAO) {
		this.semesterDetailsDAO = semesterDetailsDAO;
	}

	@Override
	public void update(List<SemesterMaster> semesterDetails) {
		getSemesterDetailsDAO().update(semesterDetails);
		
	}

	@Override
	public List<SemesterMaster> loadAllSemesterDetails(int semCode) {
			
		return getSemesterDetailsDAO().loadAllSemesterDetails(semCode); 
	}
        
        @Override
        public void deleteSemester(SemesterMaster semesterMaster){
                getSemesterDetailsDAO().deleteSemester(semesterMaster);
        }
}
