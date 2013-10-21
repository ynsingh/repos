<html>
<head>




  <style type="text/css">
#listContainer{
  margin-top:15px;
}
 
#expList ul, li {
    list-style: none;
    margin:0;
    padding:0;
    cursor: pointer;
}
#expList p {
    margin:0;
    display:block;
}
#expList p:hover {
    background-color:#121212;
}
#expList li {
    line-height:140%;
    text-indent:0px;
    background-position: 1px 8px;
    padding-left: 16px;
    background-repeat: no-repeat;
}
 
/* Collapsed state for list element */
#expList .collapsed {
    background-image: url(plus.gif);
}
/* Expanded state for list element
/* NOTE: This class must be located UNDER the collapsed one */
#expList .expanded {
    background-image: url(minus.gif);
}

 
</style>
<title>Jquery Accordion Demo</title>
		<link rel="stylesheet" type="text/css" href="style.css" />
<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>

<script language="JavaScript" type="text/javascript">


function prepareList() {
    $('#expList').find('li:has(ul)')
    .click( function(event) {
        if (this == event.target) {
            $(this).toggleClass('expanded');
            $(this).children('ul').toggle('medium');
        }
        return false;
    })
    .addClass('collapsed')
    .children('ul').hide();
};
 
$(document).ready( function() {
    prepareList()
});
function Institution()
{

parent.frame3.location="abc.txt";
}

function CollegeFacultySchool()
{

parent.frame3.location="http://www.pageresource.com/html/frame4.htm";
}
function Department()
{

parent.frame3.location="http://www.pageresource.com/html/frame4.htm";
}

function Employee()
{

parent.frame3.location="http://www.pageresource.com/html/frame4.htm";
}

function Committees()
{

parent.frame3.location="http://www.pageresource.com/html/frame4.htm";
}

function WorkFlow()
{

parent.frame3.location="http://www.pageresource.com/html/frame4.htm";
}

function UserRoll()
{

parent.frame3.location="http://www.pageresource.com/html/frame4.htm";
}

function CapitalItems()
{

parent.frame3.location="http://www.pageresource.com/html/frame4.htm";
}
function GeneralMaster()
{

parent.frame3.location="http://www.pageresource.com/html/frame4.htm";
}
function Program()
{

parent.frame3.location="http://www.pageresource.com/html/frame4.htm";
}
function SubModule()
{

parent.frame3.location="http://www.pageresource.com/html/frame4.htm";
}
function News()
{

parent.frame3.location="http://www.pageresource.com/html/frame4.htm";
}
function PrePurchaseItems()
{

parent.frame3.location="http://www.pageresource.com/html/frame4.htm";
}
function Suppliers()
{

parent.frame3.location="http://www.pageresource.com/html/frame4.htm";
}


function ItemsRates()
{

parent.frame3.location="http://www.pageresource.com/html/frame4.htm";
}


function ItemsRates()
{

parent.frame3.location="http://www.pageresource.com/html/frame4.htm";
}


function Indents()
{

parent.frame3.location="http://www.pageresource.com/html/frame4.htm";
}


function PurchaseOrder()
{

parent.frame3.location="http://www.pageresource.com/html/frame4.htm";
}


function PurchaseChallan()
{

parent.frame3.location="http://www.pageresource.com/html/frame4.htm";
}


function PurchaseInvoice()
{

parent.frame3.location="http://www.pelicanparts.com/techarticles/911_prepurchase/911_prepurchase.htm";
}
function IssueItems()
{

parent.frame3.location="http://www.barcodesinc.com/articles/what-is-inventory-mangement.htm";
}
function OppeningStock()
{

parent.frame3.location="http://www.pageresource.com/html/frame4.htm";
}
function ReceiveItems()
{

parent.frame3.location="http://en.wikipedia.org/wiki/Enterprise_resource_planning";
}
function ReturnItems()
{

parent.frame3.location="http://www.pageresource.com/html/frame4.htm";
}
function StockReport()
{

parent.frame3.location="http://www.adipalaz.com/experiments/jquery/nested_accordion.html";
}
function CurrentProfile()
{

parent.frame3.location="http://www.pageresource.com/html/frame4.htm";
}
function UserProfile()
{

parent.frame3.location="http://www.pageresource.com/html/frame4.htm";
}



		</script>		
		</head>
<body>

  
<form method ="post" name="inventory" action="CreateIm" >
 
 <table border="0" cellpadding="2" cellspacing="2">
<div id="listContainer">
            <ul id="expList">
                <li>
                   Administration
                    <ul>
                        <li>
                           Organization
                            <ul>
                                <li>
                                   <span><a href="javascript:change7()" onclick="Institution();">-Institution</a></span>
                                 </li>                                
                                 <li>
                                    <span><a href="javascript:College/Faculty/School()" onclick="CollegeFacultySchool();">-College/Faculty/School</a></span>
                                </li>
                                <li>
                                    <span><a href="javascript:Department()" onclick="Department();">-Department</a></span>
                                </li>
                                <li>
                                    <span><a href="javascript:Employee()" onclick="Employee();">-Employee</a></span>
                                </li>
                                 <li>
                                    <span><a href="javascript:Committees()" onclick="Committees();">-Committees.</a></span>
                                </li>
                                <li>
                                    <span><a href="javascript:WorkFlow()" onclick="WorkFlow();">-WorkFlow</a></span>
                                </li>
                                 
                            </ul>
                        </li>
                        <li>
                           <a href="javascript:UserRoll()" onclick="UserRoll();">-User Roll</a>
                        </li>
                        <li>
                           <a href="javascript:AthorizationRequest()" onclick="AthorizationRequest();">-Athorization Request</a>
                        </li>
                        <li>
                           <a href="javascript:GeneralMaster()" onclick="GeneralMaster();">-General Master</a>
                        </li>

                        <li>
                           <a href="javascript:CapitalItems()" onclick="CapitalItems();">-Capital Items</a>
                        </li>
                        <li>
                           <a href="javascript:BudgetHead()" onclick="BudgetHead();">-Budget Head</a>
                        </li>
                        <li>
                           <a href="javascript:BudgetAllocation()" onclick="BudgetAllocation();">-Budget Allocation</a>
                        </li>
                        <li>
                           <a href="javascript:GenaralTerms()" onclick="GenaralTerms();">-Genaral Terms</a>
                        </li>
                        <li>
                           <a href="javascript:Program()" onclick="Program();">-Program</a>
                        </li>
                        <li>
                           <a href="javascript:SubModule()" onclick="SubModule();">-Sub Module</a>
                        </li>
                        <li>
                           <a href="javascript:News()" onclick="News();">-News</a>
                        </li>
                        
                    </ul>
                </li>
                <li>
                  PrePurchase
                    <ul>
                      <li>
                         <span><a href="javascript:PrePurchaseItems()" onclick="PrePurchaseItems();">-PrePurchase Items</a></span>
                      </li>
                       <li>
                         <span><a href="javascript:Suppliers()" onclick="Suppliers();">-Suppliers</a></span>
                      </li>
                      <li>
                         <span><a href="javascript:ItemsRates()" onclick="ItemsRates();">-Items Rates</a></span>
                      </li>
                      <li>
                         <span><a href="javascript:Indents()" onclick="Indents();">-Indents</a></span>
                      </li>
                      <li>
                         <span><a href="javascript:PurchaseOrder()" onclick="PurchaseOrder();">-Purchase Order</a></span>
                      </li>

                    </ul>
                </li>
                <li>
                   Purchase
                    <ul>
                        <li>
                             <a href="javascript:PurchaseChallan()" onclick="PurchaseChallan();">-Purchase Challan</a>
                        </li>
                        <li>
                           <a href="javascript:PurchaseInvoice()" onclick="PurchaseInvoice();">-Purchase Invoice</a>
                            
                        </li>
                    </ul>
                </li>
                <li>
                   Inventory
                    <ul>
                        <li>
                            <a href="javascript:IssueItems()" onclick="IssueItems();">-Issue Items</a>
                        </li>
                        <li>
                           <a href="javascript:OppeningStock()" onclick="OppeningStock();">-Oppening Stock</a>
                            
                        </li>
                        <li>
                          <a href="javascript:ReceiveItems()" onclick="ReceiveItems();">-Receive Items</a>
                            
                        </li>
                         <li>
                           <a href="javascript:ReturnItems()" onclick="ReturnItems();">-Return Items</a>
                            
                        </li>
                        <li>
                          <a href="javascript:StockReport()" onclick="StockReport();">-Stock Report</a>
                            
                        </li>
                    </ul>
                </li>
                <li>
                   User Profile
                    <ul>
                        <li>
                             <a href="javascript:CurrentProfile()" onclick="CurrentProfile();">-Current Profile</a>
                        </li>
                        <li>
                            <a href="javascript:UserProfile()" onclick="UserProfile();">-User Profile</a>
                            
                        </li>
                    </ul>
                </li>
            </ul>
        </div>

 
 
 </table>         
 </form>      


</body>
</html>