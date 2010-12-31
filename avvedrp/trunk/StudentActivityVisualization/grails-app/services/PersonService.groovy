class PersonService {

    boolean transactional = true

    def serviceMethod() {

    }

    def checkDuplicateUser(def userName)
    {

        def personInstance = Person.findAll("from Person P where P.username='"+userName+"'")
        return personInstance
    }

    def checkValidUser(def institute,def userName,def ucode)
    {

        def userInstance = User.find("from User S where S.email='"+userName+"' and S.institution="+institute+" and S.code='"+ucode+"'")
        return userInstance
    }

    def getInstituteInfo(def institute)
    {

        def instituteInstance = Institute.find("from Institute I where I.id='"+institute+"'")
        return instituteInstance
    }

    def getUserById(def userid)
    {
	
	def userInstance = Person.find("from Person P where P.id='"+userid+"'")
        return userInstance
    }
}
