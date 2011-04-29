



/**
 * User domain class.
 */
class Person {
	static transients = ['pass']
	static hasMany = [authorities: Authority]
	static belongsTo = Authority

	/** Username */
	String username
	/** User Real Name*/
	String userRealName
	/** MD5 Password */
	String passwd

  
	/** enabled */
	boolean enabled

	
	/** description */
	String description = ''

	/** plain password to create a MD5 password */
	String pass = '[secret]'

	static constraints = {
                 username(email:true,blank:false)
                 userRealName(nullable: true)               
		         passwd(blank: false)
		         enabled()
	}
}
