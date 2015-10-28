<?session_start();
$logged_userid=$_SESSON['loginuserid'];
?>
<html>
	<html>
	<head>
	<h2><title>Telephone Billing System IIT Kanpur: Help Detail</title></h2>
	</head>
<body>
	<pre><h3 align="center">
	<font face="Verdana,Arial,Helvetica,sans-serif" color="#003399">OTBS IITK Welcomes You..!</font></h3>
<h7 align="left"><font color="#003399">Bill Upload Help:</h7></font>

1)   Login first of all successfully and click on button <font color="#003399">ADMIN ACTION</font>.
2)   Now click on button <font color="#003399">UPLOAD BILL</font>.
3)   This will display the Browse file page.
4)   Now either type file name with its full path or browse file by clicking  on button <font color="#003399">Browse</font>.

5)  <font color="red">Note that:-</font> 
    *    This file shoud have text file only (means file sholud <font color="#003399">'.txt' extention</font> with its name).
    *    Fields of this text file should have seprated by field seprator (<font color="003399"> | </font>) only.

6)   If original file is <font color="#003399">ms-access</font> (with extention <font color="#003399">'.mdb'</font>), then use following steps to change 
     it into uploadable format<font color="#003399">('.txt' format with field delimiter | ):-</font>

     a)  Right click on MS Access file and then click on open(<font color="#003399">OR Double click on it</font>).
     b)  Now there will be appear a window with title <font color="#003399">Security Warning</font>. Click on button <font color="#003399">open/Yes</font>.
     c)  Now there will be another window having too many options. Choose option <font color="003399">tables</font> from 
	 Objec list.
     d)  Open this file (named as <font color="003399">DETAILED</font>) by double clicking on it (or right click and then 
	 click on open) If there are blank lines/line on the top, delete these blank lines. 

     e)  Go to (window option) <font color="003399">file</font> and then click on <font color="003399">Export</font>).
     f)  Type the appropriate <font color="003399">file name</font> and select <font color="003399">Save as type</font> as <font color="003399">Text Files(*.txt;*.csv;
	 *.tab;*.asc)</font> and then click on button <font color="003399">Export All</font>.

     g)  Select option<font color="003399"> Delimited - Charactors such as coma or tab separate each field</font> on this 
	 new window appearing due to earlier step.
     h)  Click on button <font color="003399">Advanced</font> given on the same window.
     i)  Select option <font color="003399"> Delimited</font> and type <font color="003399">Field Delimeter</font> as <font color="003399">| </font>by deleting default field deli-
	 mter<font color="003399">(,)</font>
     j)  Choose option <font color="003399">Text Qualifier:</font> as <font color="003399">{none}</font> from drop down list (just below).
     k)  Now click on button <font color="003399">OK</font>.
     <!--k)  Again click on button <font color="003399">Next</font> <font color="003399"> finish</font>-->
     l)  Select option <font color="003399">Other</font> and then write<font color="003399"> | </font>and <font color="003399"> Text Qualifier</font> as<font color="003399"> {none}</font>(Although this step 
	 is set by default by covering the earlier steps).
     m)  Again click on button <font color="003399">Next</font> and then click on button <font color="003399"> finish</font>
     n)  Now click on button <font color="003399"> OK</font> of last window, which will show name and path of this '.txt' file.
     o)  Finally open this (converted) text file. If there are some lines/line <font color="003399">having value | in 
	 each field.</font> Delete such all lines (if there are such blank lines) and exit by saving 
	 the changes.          

<font color="red">Check before uploading file:-</font> 
    *    Final text file should <font color="003399">not </font>have any black lines or lines having value(s) <font color="#003399">|</font> in each field 
	 (specially on the top and bottom of the file).
    *    Field of this file should <font color="003399"> not </font> inclosed by any <font color="003399">" " or ' ' etc..</font>   
    *    Field of this file should have seprated only by<font color="003399"> | </font>
   
7)   Finally click on button <font color="#003399">Upload</font>.


	</pre>

	</body>
	<html>


