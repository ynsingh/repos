package aell

class ContentHomeService {

    boolean transactional = true
		

    AvlUniversityMaster universityMasterInfo(Integer universityId) {
		def avlUniversityMasterInstance=AvlUniversityMaster.find("from AvlUniversityMaster as u where id=?",[universityId])
		return  avlUniversityMasterInstance
	}
	
		
   AvlUniversityMaster universityMasterInfoOnURL(String urlOfSite) {
				def avlUniversityMasterInstance = AvlUniversityMaster.findByUrlOfSite(urlOfSite)
				return avlUniversityMasterInstance
			}
	
}
