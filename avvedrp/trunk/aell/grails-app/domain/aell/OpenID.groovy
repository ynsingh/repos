package aell



class OpenID {

	String url

	String userId

	static constraints = {
		url unique: true
	}
}
