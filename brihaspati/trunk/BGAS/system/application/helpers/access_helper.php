<?php  if ( ! defined('BASEPATH')) exit('No direct script access allowed');

/*
 * Check if the currently logger in user has the necessary permissions
 * to permform the given action
 *
 * Valid permissions strings are given below :
 *
 * 'view entry'
 * 'create entry'
 * 'edit entry'
 * 'delete entry'
 * 'print entry'
 * 'email entry'
 * 'download entry'
 * 'create ledger'
 * 'edit ledger'
 * 'delete ledger'
 * 'create group'
 * 'edit group'
 * 'delete group'
 * 'create tag'
 * 'edit tag'
 * 'delete tag'
 * 'view reports'
 * 'view log'
 * 'clear log'
 * 'change account settings'
 * 'cf account'
 * 'backup account'
 * 'administer'
 */

if ( ! function_exists('check_access'))
{
	function check_access($action_name)
	{
		$CI =& get_instance();
		$user_role = $CI->session->userdata('user_role');
		$permissions['administrator'] = array(
			'upload logo',
			'view entry',
			'create entry',
			'print entry',
			'email entry',
			'download entry',
			'print selected entry',
			'create ledger',
			'edit ledger',
			'delete ledger',
			'create group',
			'edit group',
			'delete group',
			'create tag',
			'edit tag',
			'delete tag',
			'view reports',
			'view log',
			'clear log',
			'change account settings',
			'cf account',
			'backup account',
			'create budget',
			'edit budget',
			'delete budget',
			'reappropriate budget',
			'administer',
			'add student',
			'edit student',
			'delete student',
			'add employee',
			'edit employee',
			'delete employee',
			'add party',
			'edit party',
			'delete party',
			'change password',
		);
		$permissions['manager'] = array(
			'view entry',
			'create entry',
			'print entry',
			'print selected entry',
			'email entry',
			'download entry',
			'create ledger',
			'edit ledger',
			'delete ledger',
			'create group',
			'edit group',
			'delete group',
			'create tag',
			'edit tag',
			'delete tag',
			'view reports',
			'view log',
			'clear log',
			'change account settings',
			'cf account',
			'backup account',
			'create budget',
			'edit budget',
			'change password',
			'delete budget',
			'reappropriate budget',
		);
		$permissions['accountant'] = array(
			'view entry',
			'create entry',
			'print entry',
			'print selected entry',
			'email entry',
			'download entry',
			'create ledger',
			'edit ledger',
			'delete ledger',
			'create group',
			'edit group',
			'delete group',
			'create tag',
			'edit tag',
			'delete tag',
			'view reports',
			'view log',
			'clear log',
			'change password',
		);
		$permissions['dataentry'] = array(
			'view entry',
			'create entry',
			'print entry',
			'print selected entry',
			'email entry',
			'download entry',
			'create ledger',
			'edit ledger',
			'change password',
		);
		$permissions['guest'] = array(
			'view entry',
			'print entry',
			'print selected entry',
			'email entry',
			'download entry',
			'change password',
		);

		if ( ! isset($user_role))
			return FALSE;

		/* If user is administrator then always allow access */
/*		if ($user_role == "administrator")
			return TRUE;
*/
		if ( ! isset($permissions[$user_role]))
			return FALSE;

		if (in_array($action_name, $permissions[$user_role]))
			return TRUE;
		else
			return FALSE;
	}
}

/* End of file access_helper.php */
/* Location: ./system/application/helpers/access_helper.php */
