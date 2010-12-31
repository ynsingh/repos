



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

        String usercode
	/** enabled */
	boolean enabled

	String email

	boolean emailShow

	/** description */
	String description = ''

	/** plain password to create a MD5 password */
	String pass = '[secret]'

	static constraints = {
		//username(blank: false, unique: true)
                 username(email:true,blank:false)
                 usercode(blank:false)
		//userRealName(blank: false)
                userRealName(nullable: true)
                email(nullable: true)
		passwd(blank: false)
		enabled()
	}
}
