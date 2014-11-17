<%-- 
    Document   : hello world
    Created on : 2 Nov, 2011, 3:32:50 PM
    Author     : erp05
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERP Mission - A Project sponsored by NMEICT, MHRD, Govt. of India</title>
    </head>
    <body>
        <h1 align="center">Indent WorkFlow</h1>
	<p>Indent Workflow is a three step process. Before generating indent we will have to prepare the followings,</p>
	<p><b>I</b>	First of all we will check if the sufficient fund is available in the Budget if the items are to be purchased.  If not then we will have to allocate amount by entering data into Administration > Budget Allocation screen. Since in the Step 1 of Indent, Budget Amount Available will be fetched, after the change of Budget Head Type.</p>
	<p><b>II</b>        Then we have to maintain Item category by entering data into Administration > Master Data > Item Category screen.</p>
	<p><b>III</b>      Then we have to purchase Item by entering data into Pre-purchase > Purchase Items screen.</p>
	<p><b>IV</b>      Then we have to maintain Supplier by entering data into Pre-purchase > Suppliers screen.</p>
	<p><b>V</b>	     Then we have to check whether the Item which we are going to indent and is to be purchased, is having rates approved by the competent authority or not. If not then the approved rate is to be entered into Pre-Purchase > Item Rates screen.</p>
	<p><b>VI</b>      Then we have to maintain Workflow for Purchased Item by entering data into Administration > Master Data > Workflow screen. He will have to define the Authorities/Committees before entering workflow. You will have to define Actions (which an authority or committee is authorized to take). Though this Workflow preparation will be one time process which will be defined by the Institute Administrator as per the ordinances of the Institute.</p>
	<p>Steps of Indent Workflow are discussed below,</p>
	<p><b>Step 1</b>       User will have to enter the Indent Title after selecting Institution name, College/Faculty/School name, Department name, currency of purchase and Budget heads type. User enters the Budget Heads Type, then amount allocated is fetched and populated, then enter indent signatory, then user click on save/next button.</p>
	<p><b>Step 2</b>       In step 2 screen, a new form is open to add details of items for which indent is to be generated. As user enters item name and quantity, then item Rates list gets populated for the user to select the desired Rate of the desired supplier.</p>
	<p>After selecting an item rate it populates Rate, Applicable Taxes, Rate validity	 period etc information. If the rates are not available then it opens up a new browser window to search the selected Item's rate through google's search engine.</p>
	<p>User will have to click Add Item for adding the items into the indent after filling up the Item details above, If user wants to add more item details then user will have to again fill up details & click on Add Item button.</p>
	<p>After the Indent Items have been put in then the user can submit this indent, by clicking Submit indent Button, on the Workflow for approval from the corresponding authority.
</p>
	<p><b>Step 3</b>     In step 3 screen, it will ask you on which Workflow you want to submit the indent. It will also ask you what action you want to take. After taking action, we click on Take Action Button and Indent workflow is completed.</p>
    </body>
</html>
