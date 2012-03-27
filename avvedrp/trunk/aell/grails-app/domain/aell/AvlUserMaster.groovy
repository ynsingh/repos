package aell

class AvlUserMaster {
	String username
	String password
	Integer universityId
	boolean enabled=true
	boolean accountExpired=false
	boolean accountLocked=false
	boolean passwordExpired=false
    String emailId
    String userStatus
	static hasMany = [openIds: OpenID]
    static mapping = {
	   id column: "user_id"
	   username column:"user_name"
	   version false
	   }
    static constraints = {
		username blank: false, unique: true
		password blank: false
		universityId blank: false
		emailId blank: false
		enabled nullable:false, blank:false
		accountExpired nullable:false, blank:false
		accountLocked nullable:false, blank:false
		passwordExpired nullable:false, blank:false
		userStatus blank: false
		userStatus(inList:["A","D"])
    }
	Set<AvlRoleMaster> getAuthorities() {
		AvlRoleUserRel.findAllByUser(this).collect { it.role } as Set
	}
	def beforeInsert(){
		accountExpired=0
		accountLocked=0
		passwordExpired=0
	}
}
