******************************************************************
			RESRICTED VARIABLES
******************************************************************

Brihaspati2 uses Texen Api for multilingual purpose.
Use of this api has resulted to restrict few variables in 
Templates(*.vm files).Remember these will be parsed by Texen.

	(01) $generator
	(02) $outputDirectory
	(03) $strings
	(04) $files
	(05) $properties
	(06) $velocityCount *

 	*$velocityCount  can be used as it is as Control.java 
	chages it to $Control_velocityCount and than parses it.
 	It than converts it back to $velocityCount
