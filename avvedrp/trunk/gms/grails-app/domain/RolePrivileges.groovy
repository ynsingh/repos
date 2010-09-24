class RolePrivileges {
	Authority role;
	Party party
	String controllerName;
	String actionName;
	String description;
    String createdBy;
    Date createdDate;
    String modifiedBy;
    Date modifiedDate;
    static constraints={
    	description(nullable:true)
        createdBy(nullable:true)
        createdDate(nullable:true)
        modifiedBy(nullable:true)
        modifiedDate(nullable:true)
    } 


}
