package org.nmeict.smvdu.Beans.SpringClassFile;

import java.util.List;

import org.nmeict.smvdu.Beans.DegreeType;

public class DegreeTypeService implements IDegreeType{

	private DegreeTypeDAO degreeTypeDAO = new DegreeTypeDAO();
	
	 
	public DegreeTypeDAO getDegreeTypeDAO() {
		return degreeTypeDAO;
	}

	public void setDegreeTypeDAO(DegreeTypeDAO degreeTypeDAO) {
		this.degreeTypeDAO = degreeTypeDAO;
	}

	@Override
	public void addDegreeType(DegreeType degreeType) 
	{
		getDegreeTypeDAO().addDegreeType(degreeType); 
		
	}

	@Override
	public void updateDegreeType(List<DegreeType> degreeType) {
		getDegreeTypeDAO().updateDegreeType(degreeType); 
		
	}

	@Override
	public List<DegreeType> allDegreeType(int dCode) {
		
		return getDegreeTypeDAO().allDegreeType(dCode); 
	}

	@Override
	public DegreeType loadDegreeType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteDegreeType(DegreeType degreeType) {
		// TODO Auto-generated method stub
                return getDegreeTypeDAO().deleteDegreeType(degreeType);
		//return false;
	}

}
