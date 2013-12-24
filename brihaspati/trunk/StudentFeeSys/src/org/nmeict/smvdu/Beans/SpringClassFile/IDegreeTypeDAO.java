package org.nmeict.smvdu.Beans.SpringClassFile;

import java.util.List;

import org.nmeict.smvdu.Beans.DegreeType;

public interface IDegreeTypeDAO {

public void addDegreeType(DegreeType degreeType);
	
	
	public void updateDegreeType(List<DegreeType> degreeType);
	
	
	public List<DegreeType> allDegreeType(int dCode);

	
    public DegreeType loadDegreeType();
	
	
	public boolean deleteDegreeType(DegreeType degreeType);
}
