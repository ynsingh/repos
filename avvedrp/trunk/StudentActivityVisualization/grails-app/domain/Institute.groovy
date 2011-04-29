/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ahis
 */
class Institute {
    
    String univ_id
	String user_id
	String inst_name
	String inst_address
    String inst_email
	static constraints = {
                 inst_name(blank:false)
                 inst_address(blank:false)
                 inst_email(blank:false)
	}
}

