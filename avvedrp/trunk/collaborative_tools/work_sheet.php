<div align="left">
<br />
<a href="javascript:showGrid();"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Show/Hide worksheet</a>
</div>
<br />
<link rel="stylesheet" type="text/css" href="Scripts/grid/gt_grid.css" />  
<script type="text/javascript" src="Scripts/grid/gt_msg_en.js"></script>
<script type="text/javascript" src="Scripts/grid/gt_const.js"></script>
<script type="text/javascript" src="Scripts/grid/gt_grid_all.js"></script>
<script type="text/javascript" >  
<!-- All the scripts will go here  --> 
var data1 = [

//{serialNo:"010-1",length:"SP",Time1:"Charles",Time2:"Marks"},

//{serialNo:"010-2",length:"SP",Time1:"Vincent",Time2:"Harrison",},

[1,0.40,155,154.5],
[2,0.35,145,146],
[3,0.30,135,134.5]

];

var dsOption= {

    fields :[

        {name : 'serialNo',type : 'float'},

        {name : 'length',type : 'float' },

        {name : 'Time1' ,type : 'float' },

        {name : 'Time2',type : 'float'},

        {name : 'Meanf' ,type: 'float' ,  initValue : function(record){
               var avg =(record[2]+record[3])/2;

           avg = parseInt(avg*100)/100;

                return avg;
            } },

        {name : 'Period' ,type: 'float' ,initValue : function(record){
               var time =(record[4])/20;

           time = parseInt(time*100)/100;

                return time;
            }},
     {name : 'lengthtime' ,type: 'float',initValue : function(record){
               var lbytsquare =(record[1])/(record[5]*record[5]);
               lbytsquare = parseInt(lbytsquare*100000)/100000;
               return lbytsquare;
            } }
   
    ],

    recordType : 'array',
 
data: data1
	
} 


var colsOption = [

     {id: 'serialNo' , header: "Trial No" , width :80,editor: {  type :"text"  } },

     {id: 'length' , header: "length in m" , width :100 ,editor: {  type :"text"  } },

       {id: 'Time1' , header: "Time1 for 20 Ocsillation" , width :181 ,editor: {  type :"text"  } },

       {id: 'Time2' , header: "Time2 for 20 Ocsillation" , width :181 ,editor: {  type :"text"  } },

       {id: 'Meanf' , header: "Mean" , width :90 },
	   

       {id: 'Period' , header: "Period" , width :80},

       {id: 'lengthtime' , header: "l/T Square " , width :80}

];

var gridOption={
  id : "grid1",
	width: "800",  
	height: "200",  	
	 container : 'grid1_container', 
	// loadURL : 'Scripts/grid/export_php/testList.php',

	exportURL : 'Scripts/grid/export_php/testList.php?export=true',
	 exportFileName : 'test_export_doc',
	 //defaultRecord : [3,6,7,6],

	dataset : dsOption ,
	columns : colsOption ,
	  customHead : 'myHead1',
	toolbarPosition : 'bottom',
	toolbarContent : 'add del reload  save | print xls pdf chart | '
	//toolbarContent : 'pdf' 


};
//function masterCompleted(grid){
    //var colObj=grid1.columnMap['Mean'];
	  //colObj.group();
//}

//showGrid();

function showGrid(){

	 if (Sigma.$('grid1_container').style.display!="none") {
	  Sigma.$('grid1_container').style.display="none";
		//grid1_container.visibility=hidden;
    }else{
	 Sigma.$('grid1_container').style.display='';
	    mygrid.onShow();
	   
		
	   }
}
var mygrid=new Sigma.Grid(gridOption);
//Sigma.$('grid1_container').style.display="none";
Sigma.Util.onLoad( Sigma.Grid.render(mygrid) );
mygrid.visibility = 'hidden'; 
//grid1_container.visibility=hidden;
//document.all.grid1_container.style.visibility = 'hidden'; 

</script>  
 <table id="myHead1" style="display:none">
<tr >
	
	<td rowspan="2" columnId='serialNo'>Trial No</td>
	<td  rowspan="2" columnId='length'>Length</td>
   <td colspan="3">Time for 20 Ocsillation </td>
   <td rowspan="2" columnId='Period'>Period</td>
	<td rowspan="2" columnId='lengthtime'>l/T<sup>2</sup> </td>
     <tr>
	<td columnId='Time1'>Time1 </td>
	<td columnId='Time2'>Time2</td>
	<td columnId='Meanf'>Mean</td>
    
	</tr>
</tr>
</table>

<div style="padding-left:5%" >
 <div   id="grid1_container" style="width:800px;height:300px">  

</div>  
</div>
</div>