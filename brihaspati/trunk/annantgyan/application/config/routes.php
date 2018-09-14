<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/*
| -------------------------------------------------------------------------
| URI ROUTING
| -------------------------------------------------------------------------
| This file lets you re-map URI requests to specific controller functions.
|
| Typically there is a one-to-one relationship between a URL string
| and its corresponding controller class/method. The segments in a
| URL normally follow this pattern:
|
|	example.com/class/method/id/
|
| In some instances, however, you may want to remap this relationship
| so that a different class/function is called than the one
| corresponding to the URL.
|
| Please see the user guide for complete details:
|
|	https://codeigniter.com/user_guide/general/routing.html
|
| -------------------------------------------------------------------------
| RESERVED ROUTES
| -------------------------------------------------------------------------
|
| There are three reserved routes:
|
|	$route['default_controller'] = 'welcome';
|
| This route indicates which controller class should be loaded if the
| URI contains no data. In the above example, the "welcome" class
| would be loaded.
|
|	$route['404_override'] = 'errors/page_missing';
|
| This route will tell the Router which controller/method to use if those
| provided in the URL cannot be matched to a valid route.
|
|	$route['translate_uri_dashes'] = FALSE;
|
| This is not exactly a route, but allows you to automatically route
| controller and method names that contain dashes. '-' isn't a valid
| class or method name character, so it requires translation.
| When you set this option to TRUE, it will replace ALL dashes in the
| controller and method URI segments.
|
| Examples:	my-controller/index	-> my_controller/index
|		my-controller/my-method	-> my_controller/my_method
*/
$route['Annantgyan-Admin'] = 'Admin';

$route['default_controller'] = 'welcome';
$route['Sign-In'] = 'Header/signin';
$route['New-Registration'] = 'Header/signup';
$route['Contact-Us'] = 'Header/contactus';
$route['About-Us'] = 'Header/about';
$route['Vission'] = 'Header/vission';
$route['Mission'] = 'Header/mission';
$route['Course-Registration'] = 'Header/ongoingworkshop';

$route['Registration'] = 'workshop/courseenroll';
$route['Sign-Up'] = 'Header/signup';
$route['Director/Founder-Message'] = 'Header/foundermsg';

$route['Online-Certified-Crash-Course'] = 'Header/crashcourse';
$route['Online-Certified-Workshop'] = 'Header/certworkshop';
$route['Certified-Fun-Learning-Courses/Workshop'] = 'Header/certfuncourse';
$route['General-Subject-Related-Links'] = 'Header/certsubmaterial';

$route['Basic-Innovative-Scientific-Projects'] = 'Header/inno_basicproject';
$route['Advance-Innovative-Scientific-Projects'] = 'Header/inno_advanproject';
$route['Innovative-Projects-For-Undergraduate-Students'] = 'Header/inno_graduproject';
$route['Innovative-Ideas-For-Postgraduate-Students'] = 'Header/inno_postgrproject';
$route['Innovative-Ideas-For-Rural-Urban-Area-People'] = 'Header/inno_ruralurban';

$route['Time-And-Stress-Management-Skill'] = 'Header/skillstressmgt';
$route['Public-Speaking-Skills'] = 'Header/skillpubicspeaker';
$route['Effective-Communication-Skills'] = 'Header/skilleffectivecomm';
$route['Interview-Preparation-Skill'] = 'Header/skillinterview';
$route['Report-Papers-Thesis-Writing-Skills'] = 'Header/skillreport';

//$route['Courses'] = 'login/course_login';
//$route['Feedback'] = 'login/usrfeedback';
//$route['FAQ'] = 'login/usrfaq';
//$route['Course-Detail'] = 'login/usrcoucalender';
//$route['Course-Home'] = 'login/usr_login';
//$route['404_override'] = '';
$route['translate_uri_dashes'] = FALSE;
