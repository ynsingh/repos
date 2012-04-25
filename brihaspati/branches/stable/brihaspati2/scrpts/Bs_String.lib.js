/**
* 
* @package    javascript_core
* @subpackage util
*/


function bs_trim(input) {
  var ret = input.replace(/^\s*/, '');
  return ret.replace(/\s*$/, '');
}
function bs_ltrim(input) {
  return input.replace(/^\s*/, '');
}
function bs_rtrim(input) {
	return ret.replace(/\s*$/, '');
}


/**
* sprintf implementation in javascript.
* 
* got the original version from: from http://jan.moesen.nu/code/javascript/sprintf-and-printf-in-javascript/
* modifications: 
*  - throws bool false
*  - can be called with 2 params - specify the args in the 2nd param.
* 
* for documentation and how to use see http://www.php.net/sprintf
* 
* example 1:
*   var input  = "'30' -> decimal: %d / bin = %b / oct = %o / hex = %x / HEX = %X";
*   var output = sprintf(input, 30, 30, 30, 30, 30);
* 
* example 2: (specifying the args in an array)
*   var input  = "the quick brown %s jumps over the lazy %s.";
*   var output = sprintf(input, new Array('fox', 'dog'));
* 
* @param  string input
* @param  array args (read above)
* @throws bool false.
*/
function sprintf() {
	if (!arguments || arguments.length < 1 || !RegExp) {
		return false;
	}
	var str = arguments[0];
	if ((arguments.length == 2) && (typeof(arguments[1]) == 'object')) {
		var args = arguments[1];
	} else {
		var args = arguments;
	}
	var re = /([^%]*)%('.|0|\x20)?(-)?(\d+)?(\.\d+)?(%|b|c|d|u|f|o|s|x|X)(.*)/;
	var a = b = [], numSubstitutions = 0, numMatches = 0;
	while (a = re.exec(str))
	{
		var leftpart = a[1], pPad = a[2], pJustify = a[3], pMinLength = a[4];
		var pPrecision = a[5], pType = a[6], rightPart = a[7];
		
		//alert(a + '\n' + [a[0], leftpart, pPad, pJustify, pMinLength, pPrecision);

		numMatches++;
		if (pType == '%')
		{
			subst = '%';
		}
		else
		{
			numSubstitutions++;
			if (numSubstitutions > args.length) {
				alert('Error! Not enough function arguments (' + (args.length) + ', excluding the string)\nfor the number of substitution parameters in string (' + numSubstitutions + ' so far).');
			}
			var param = args[numSubstitutions -1];
			var pad = '';
			       if (pPad && pPad.substr(0,1) == "'") pad = leftpart.substr(1,1);
			  else if (pPad) pad = pPad;
			var justifyRight = true;
			       if (pJustify && pJustify === "-") justifyRight = false;
			var minLength = -1;
			       if (pMinLength) minLength = parseInt(pMinLength);
			var precision = -1;
			       if (pPrecision && pType == 'f') precision = parseInt(pPrecision.substring(1));
			var subst = param;
			       if (pType == 'b') subst = parseInt(param).toString(2);
			  else if (pType == 'c') subst = String.fromCharCode(parseInt(param));
			  else if (pType == 'd') subst = parseInt(param) ? parseInt(param) : 0;
			  else if (pType == 'u') subst = Math.abs(param);
			  else if (pType == 'f') subst = (precision > -1) ? Math.round(parseFloat(param) * Math.pow(10, precision)) / Math.pow(10, precision): parseFloat(param);
			  else if (pType == 'o') subst = parseInt(param).toString(8);
			  else if (pType == 's') subst = param;
			  else if (pType == 'x') subst = ('' + parseInt(param).toString(16)).toLowerCase();
			  else if (pType == 'X') subst = ('' + parseInt(param).toString(16)).toUpperCase();
		}
		str = leftpart + subst + rightPart;
	}
	return str;
}

