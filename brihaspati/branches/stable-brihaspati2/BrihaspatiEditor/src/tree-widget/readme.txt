Instructions on how to use the Javascript Navigation
Version $Id: readme.txt,v 1.1 1998/03/25 20:22:40 ynsingh Exp $
Author: Paul Sharples
-----------------------------------------------------

Contents
--------
1. General
2. Building the Tree Nodes

1. General
----------
The javascript navigation system relies on various HTML, CSS and JS Files.
Most of these you can leave alone and wont have to edit, however there is one
file you will have to edit in order to create the complete widget - CPOrgs.js
CPOrgs contains the "mapping" of all items in the tree - i.e. what title to display, 
which node to add it to, where to Hyperlink to, Etc.

This file is loaded into a javascript object(CPModel.js) which models the behaviour 
you describe using a particular syntax. Each line starts with "CPAPI." ... then some 
property to change...

The tree widget can show multiple tree views by using organizations, which are the notion of having
more than one "avenue" through the material.  With multiple organizations a drop down list will
appear above the tree allowing you to "jump" to a different set of tree items. (all of which must be described)
The syntax for describing an organization corresponds to 'Array' type syntax.

Example..

CPAPI.orgArray(0).organizationName = "organization";
.... info for building this organization
CPAPI.orgArray(1).organizationName = "another organization";
.... info for building this organization
CPAPI.orgArray(2).organizationName = "yet another organization";
.... info for building this organization

This example is telling the widget that you have three organizations, so it should show 3 items in the drop down list.


2. Building the Tree Nodes
--------------------------
Below is an example of how this works showing a very simple help system with one organization and 2 items added to the tree...

CPAPI.packageName = "Help System";
CPAPI._defaultOrg = 0;
CPAPI.showSearch = true;
CPAPI.orgArray(0).organizationName = "Contents";
CPAPI.orgArray(0).organizationIdentifier = "helpsystem";
CPAPI.orgArray(0).itemArray(0).itemTitle = "Introduction";
CPAPI.orgArray(0).itemArray(0).itemIdentifier = "intro";
CPAPI.orgArray(0).itemArray(0).itemParent = "menu";
CPAPI.orgArray(0).itemArray(0).itemHyper = "topic1/subtopic1.html";
CPAPI.orgArray(0).itemArray(0).keyWords = "contact,comments,version";
CPAPI.orgArray(0).itemArray(0).comments = "This page includes introductory comments for the help system.";
CPAPI.orgArray(0).itemArray(1).itemTitle = "What does the Scorm Player do?";
CPAPI.orgArray(0).itemArray(1).itemIdentifier = "whatitdoes";
CPAPI.orgArray(0).itemArray(1).itemParent = "intro";
CPAPI.orgArray(0).itemArray(1).itemHyper = "topic1/subtopic2.html";
CPAPI.orgArray(0).itemArray(1).keyWords = "what";
CPAPI.orgArray(0).itemArray(1).comments = "This page describes the  and capabilities of Reload Scorm 1.2 Player";

Explanation
-----------
CPAPI.packageName = "Help System";
	- This property is used to set the actual name of the package/system you are modelling. (mandatory)

CPAPI._defaultOrg = 0;
	- This property is used to say which tree view to show from a selection of drop down values. (mandatory)
	  If there is only one selection (i.e. only one organization) then the tree will not display the drop down list.
	  i.e. CPAPI._defaultOrg = 0; would tell the widget to display the organization at index (0) in its list of
	  organizations.  This would equate to showing the tree nodes starting with "CPAPI.orgArray(0)."

CPAPI.showSearch = true;
	- This property is used to tell the widget whether or not to show the search frame. (optional)
	  (by default you can miss this line out and the widget will not show the search frame).
	  
We next start to build our tree nodes for organization(0).  Here we are now describing the first item
------------------------------------------------------------------------------------------------------
	  
CPAPI.orgArray(0).itemArray(0).itemTitle = "Introduction";
	- This property is used to show the title of this item in the widget (mandatory)
	
CPAPI.orgArray(0).itemArray(0).itemIdentifier = "intro";
	- This property is used to uniquely identify this tree node - a javascript variable name. (mandatory)
	
CPAPI.orgArray(0).itemArray(0).itemParent = "menu";
	- This property is used to tell the tree widget which tree node to add this one to. (mandatory)
	 "menu" is the root node. You can also add the node to another node name which you specified in ".itemIdentifier"
	 
CPAPI.orgArray(0).itemArray(0).itemHyper = "topic1/subtopic1.html";
	- This property is used to hold the hyperlink you want to go to when the user clicks the link. (optional)
	
CPAPI.orgArray(0).itemArray(0).keyWords = "contact,comments,version";
	- This property is used to hold any keywords you wish to use for this item in the search. (optional)
	
CPAPI.orgArray(0).itemArray(0).comments = "This page includes introductory comments for the help system.";
	- This property is used to hold any comments you wish to use for this item in the search. (optional)
	
	


	
	  
