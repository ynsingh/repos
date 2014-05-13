<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Number
{
	var $res = array();
	//var $res="";
	var $ones=array();
	var $tens=array();
	var $Gn = "";
	var $kn = "";
	var $Dn = "";
	var $number="";
	var $n="";
	
	function Number()
	{
		return;
	}

	function convert_number($number) 
	{
		$CI =& get_instance();
		$CI->load->library('session');
		if (($number < 0) || ($number > 999999999)) {
			throw new Exception("Number is out of range");
		}
		$cn = floor($number / 10000000);
		/* Crores (giga) */
		$number -= $cn * 10000000;
		$Gn = floor($number / 100000);
		/* Lakhs (giga) */
		$number -= $Gn * 100000;
		$kn = floor($number / 1000);
		/* Thousands (kilo) */
		$number -= $kn * 1000;
		$Hn = floor($number / 100);
		/* Hundreds (hecto) */
		$number -= $Hn * 100;
		$Dn = floor($number / 10);
		/* Tens (deca) */
		$n = $number % 10;
		/* Ones */
	 
		$res = "";

		if ($cn) {
			$res .= $this->convert_number($cn) . " Crore";
		}
		if ($Gn) {
			$res .= (empty($res) ? "" : " ") .$this->convert_number($Gn) . " Lakh";
		} 
		if ($kn) {
			$res .= (empty($res) ? "" : " ") .$this->convert_number($kn) . " Thousand";
		} 
		if ($Hn) {
			$res .= (empty($res) ? "" : " ") .$this->convert_number($Hn) . " Hundred ";
		}
		$ones = array("", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eightteen", "Nineteen");
		$tens = array("", "", "Twenty", "Thirty", "Fourty", "Fifty", "Sixty", "Seventy", "Eigthy", "Ninety");
		if ($Dn || $n) {
			if ($Dn < 2) {
				$res .= $ones[$Dn * 10 + $n];
			} else {
				$res .= $tens[$Dn];
				if ($n) {
					$res .= "-" . $ones[$n];
				}
			}
		}
		if (empty($res)) {
			$res = "zero";
		}
		$res1 =$res . '_'; 
		$pieces = explode("_", $res1);
		@$pieces1 = explode("_", $pieces);
		return $res;
	}
}
?>
