security {

	// see DefaultSecurityConfig.groovy for all settable/overridable properties

	active = true

	loginUserDomainClass = "User"
	authorityDomainClass = "Role"
	requestMapClass = "Requestmap"
	System.out.println("testing")
	
	requestMapString = """
		CONVERT_URL_TO_LOWERCASE_BEFORE_COMPARISON
		PATTERN_TYPE_APACHE_ANT

		/login/**=IS_AUTHENTICATED_ANONYMOUSLY
        /grantAllocation/**=IS_AUTHENTICATED_ANONYMOUSLY
        /grantAllocationSplit/**=IS_AUTHENTICATED_ANONYMOUSLY
         /grantExpense/**=IS_AUTHENTICATED_ANONYMOUSLY
         /grantPeriod/**=IS_AUTHENTICATED_ANONYMOUSLY
          /grantReciept/**=IS_AUTHENTICATED_ANONYMOUSLY
			/party/**=IS_AUTHENTICATED_ANONYMOUSLY
			/projects/**=IS_AUTHENTICATED_ANONYMOUSLY
			/partyGrantAgency/**=IS_AUTHENTICATED_ANONYMOUSLY
			   
		/admin/**=ROLE_USER
		/book/test/**=IS_AUTHENTICATED_FULLY
		/book/**=ROLE_SUPERVISOR
		/**=IS_AUTHENTICATED_ANONYMOUSLY
	"""

}
